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
 * A test fixture that boots the F3 server once for a suite of tests.
 *
 * @version $Rev$ $Date$
 */
public class ClusterBootFixture extends TestSetup {
    private static final String IDEA_DEBUG = "-Xdebug -Xrunjdwp:transport=dt_socket,address=5005,server=y,suspend=y -Xmx512M -XX:MaxPermSize=512M";

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
        CountDownLatch latch = new CountDownLatch(3);
        RuntimeBooter controller = new RuntimeBooter(CONTROLLER_DIR, latch, "controller", "controller", false);
        RuntimeBooter zone1 = new RuntimeBooter(ZONE1_DIR, latch, "participant", "zone1", false);
        RuntimeBooter zone2 = new RuntimeBooter(ZONE2_DIR, latch, "participant", "zone2", false);
        new Thread(controller).start();
        new Thread(zone1).start();
        new Thread(zone2).start();
        latch.await();
    }

    protected void tearDown() throws Exception {
        CountDownLatch latch = new CountDownLatch(3);
        new Thread(new RuntimeShutdown(CONTROLLER_DIR, latch, "controller", "1199")).start();
        new Thread(new RuntimeShutdown(ZONE1_DIR, latch, "participant", "1200")).start();
        new Thread(new RuntimeShutdown(ZONE2_DIR, latch, "participant", "1201")).start();
        latch.await();
    }

    private class RuntimeBooter implements Runnable {
        private File dir;
        private CountDownLatch latch;
        private String mode;
        private String name;
        private boolean debug;

        private RuntimeBooter(File dir, CountDownLatch latch, String mode, String name, boolean debug) {
            this.dir = dir;
            this.latch = latch;
            this.mode = mode;
            this.name = name;
            this.debug = debug;
        }

        public void run() {
            try {
                System.out.println("Booting " + mode + " debug: " + debug);
                Process process;
                if (debug) {
                    process = Runtime.getRuntime().exec("java " + IDEA_DEBUG + " -jar server.jar " + mode, new String[0], dir);
                } else {
                    process = Runtime.getRuntime().exec("java -jar server.jar " + mode, new String[0], dir);
                }
                InputStream stream = process.getErrorStream();
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
                            System.out.print("[Runtime:" + name + "]" + output);
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
                            System.out.print("[Runtime:" + name + "]" + output);
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
        private String mode;
        private String jmxPort;

        private RuntimeShutdown(File dir, CountDownLatch latch, String mode, String jmxPort) {
            this.dir = dir;
            this.latch = latch;
            this.mode = mode;
            this.jmxPort = jmxPort;
        }

        public void run() {
            try {
                Process shutdown = Runtime.getRuntime().exec("java -jar shutdown.jar -p " + jmxPort, new String[0], dir);
                shutdown.waitFor();
                System.out.println("Shutdown " + mode);
                latch.countDown();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
