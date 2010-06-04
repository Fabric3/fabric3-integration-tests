/*
 * Fabric3
 * Copyright (c) 2009 Metaform Systems
 *
 * Fabric3 is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of
 * the License, or (at your option) any later version, with the
 * following exception:
 *
 * Linking this software statically or dynamically with other
 * modules is making a combined work based on this software.
 * Thus, the terms and conditions of the GNU General Public
 * License cover the whole combination.
 *
 * As a special exception, the copyright holders of this software
 * give you permission to link this software with independent
 * modules to produce an executable, regardless of the license
 * terms of these independent modules, and to copy and distribute
 * the resulting executable under terms of your choice, provided
 * that you also meet, for each linked independent module, the
 * terms and conditions of the license of that module. An
 * independent module is a module which is not derived from or
 * based on this software. If you modify this software, you may
 * extend this exception to your version of the software, but
 * you are not obligated to do so. If you do not wish to do so,
 * delete this exception statement from your version.
 *
 * Fabric3 is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty
 * of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 *
 * You should have received a copy of the
 * GNU General Public License along with Fabric3.
 * If not, see <http://www.gnu.org/licenses/>.
*/
package org.fabric3.tests.standalone.cluster;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.CountDownLatch;

import junit.extensions.TestSetup;
import junit.framework.Test;

/**
 * A test fixture that boots a multi-zoned (clustered) domain topology:
 * <pre>
 *          - Zone1 contains one instance
 *          - Zone2 contains two instances
 * </pre>
 * <p/>
 * This topology allows for a battery of integration tests to be run via multiple contribution deployments.
 *
 * @version $Rev$ $Date$
 */
public class ClusterBootFixture extends TestSetup {
    private static final String DEBUG = "-Xdebug -Xrunjdwp:transport=dt_socket,address=5005,server=y,suspend=y -Xmx512M -XX:MaxPermSize=512M";

    private static final File CONTROLLER_DIR = new File(".." + File.separator
            + "test-standalone-cluster-setup" + File.separator
            + "controller" + File.separator
            + "target" + File.separator
            + "image" + File.separator + "bin");

    private static final File ZONE1_DIR = new File(".." + File.separator
            + "test-standalone-cluster-setup" + File.separator
            + "zone1" + File.separator
            + "target" + File.separator
            + "image" + File.separator + "bin");

    private static final File ZONE2_DIR = new File(".." + File.separator
            + "test-standalone-cluster-setup" + File.separator
            + "zone2" + File.separator
            + "target" + File.separator
            + "image" + File.separator + "bin");

    public ClusterBootFixture(Test test) {
        super(test);
    }

    protected void setUp() throws Exception {
        CountDownLatch latch = new CountDownLatch(4);
        RuntimeBooter controller = new RuntimeBooter(CONTROLLER_DIR, latch, "controller", "controller", false, false);
        RuntimeBooter zone1_participant1 = new RuntimeBooter(ZONE1_DIR, latch, "participant1", "zone1", true, false);
        RuntimeBooter zone2_participant1 = new RuntimeBooter(ZONE2_DIR, latch, "participant1", "zone2", true, false);
        RuntimeBooter zone2_participant2 = new RuntimeBooter(ZONE2_DIR, latch, "participant2", "zone2", true, false);
        new Thread(controller).start();
        new Thread(zone1_participant1).start();
        new Thread(zone2_participant1).start();
        Thread.sleep(1000);
        new Thread(zone2_participant2).start();
        latch.await();
    }

    protected void tearDown() throws Exception {
        CountDownLatch latch = new CountDownLatch(4);
        new Thread(new RuntimeShutdown(CONTROLLER_DIR, latch, "controller", "controller", "1199")).start();
        new Thread(new RuntimeShutdown(ZONE1_DIR, latch, "participant1", "zone1", "1200")).start();
        new Thread(new RuntimeShutdown(ZONE2_DIR, latch, "participant1", "zone2", "1300")).start();
        new Thread(new RuntimeShutdown(ZONE2_DIR, latch, "participant2", "zone2", "1301")).start();
        latch.await();
    }

    private class RuntimeBooter implements Runnable {
        private File dir;
        private CountDownLatch latch;
        private String runtimeName;
        private String zone;
        private boolean debug;
        private boolean cloneParticipant;

        private RuntimeBooter(File dir, CountDownLatch latch, String runtimeName, String zone, boolean cloneParticipant, boolean debug) {
            this.dir = dir;
            this.latch = latch;
            this.runtimeName = runtimeName;
            this.zone = zone;
            this.cloneParticipant = cloneParticipant;
            this.debug = debug;
        }

        public void run() {
            try {
                System.out.println("Booting " + zone + ":" + runtimeName + " debug: " + debug);
                Process process;
                if (debug) {
                    if (cloneParticipant) {
                        process =
                                Runtime.getRuntime().exec("java " + DEBUG + " -jar server.jar clone:participant " + runtimeName,
                                                          new String[0],
                                                          dir);
                    } else {
                        process =
                                Runtime.getRuntime().exec("java " + DEBUG + " -jar server.jar " + runtimeName, new String[0], dir);
                    }
                } else {
                    if (cloneParticipant) {
                        process = Runtime.getRuntime().exec("java -jar server.jar clone:participant " + runtimeName, new String[0], dir);
                    } else {
                        process = Runtime.getRuntime().exec("java -jar server.jar " + runtimeName, new String[0], dir);
                    }
                }
                InputStream stream = process.getInputStream();
                ByteArrayOutputStream os = new ByteArrayOutputStream();
                while (stream.available() == 0) {
                    Thread.sleep(100);
                }
                int i;
                while ((i = stream.read()) != -1) {
                    os.write(i);
                    String output = new String(os.toByteArray());
                    if (i == "\n".getBytes()[0]) {
                        synchronized (System.out) {
                            System.out.print("[Runtime:" + zone + ":" + runtimeName + "]" + output);
                        }
                        os = new ByteArrayOutputStream();
                    }
                    if (output.indexOf("SEVERE") >= 0 && stream.available() == 0) {
                        throw new AssertionError("Boot exception reported");
                    }
                    if (output.indexOf("Fabric3 ready [") != -1) {
                        break;

                    }
                }

                latch.countDown();

                os = new ByteArrayOutputStream();
                while ((i = stream.read()) != -1) {
                    os.write(i);
                    if (i == "\n".getBytes()[0]) {
                        synchronized (System.out) {
                            String output = new String(os.toByteArray());
                            System.out.print("[Runtime:" + zone + ":" + runtimeName + "]" + output);
                        }
                        os = new ByteArrayOutputStream();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }


    private class RuntimeShutdown implements Runnable {
        private File dir;
        private CountDownLatch latch;
        private String runtimeName;
        private String jmxPort;
        private String zone;

        private RuntimeShutdown(File dir, CountDownLatch latch, String runtimeName, String zone, String jmxPort) {
            this.dir = dir;
            this.latch = latch;
            this.zone = zone;
            this.runtimeName = runtimeName;
            this.jmxPort = jmxPort;
        }

        public void run() {
            try {
                System.out.println("Shutting down " + zone + ":" + runtimeName);
                Process shutdown = Runtime.getRuntime().exec("java -jar shutdown.jar -p " + jmxPort, new String[0], dir);
                shutdown.waitFor();
                System.out.println("Shutdown " + zone + ":" + runtimeName);
                latch.countDown();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
