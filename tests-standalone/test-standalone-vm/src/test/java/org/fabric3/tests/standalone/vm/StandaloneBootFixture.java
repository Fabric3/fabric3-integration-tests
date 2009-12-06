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
package org.fabric3.tests.standalone.vm;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;

import junit.extensions.TestSetup;
import junit.framework.Test;

/**
 * A test fixture that boots the F3 server once for a suite of tests.
 *
 * @version $Rev$ $Date$
 */
public class StandaloneBootFixture extends TestSetup {
    private static final String IDEA_DEBUG = "-Xdebug -Xrunjdwp:transport=dt_socket,address=5005,server=y,suspend=y -Xmx512M -XX:MaxPermSize=512M";
    private static final File RUNTIME_DIR = new File(".." + File.separator
            + "test-standalone-setup" + File.separator
            + "target" + File.separator
            + "image" + File.separator + "bin");

    public StandaloneBootFixture(Test test) {
        super(test);
    }

    protected void setUp() throws Exception {
        Runtime.getRuntime().exec("java -jar shutdown.jar", new String[0], RUNTIME_DIR);
        Process process = Runtime.getRuntime().exec("java -jar server.jar", new String[0], RUNTIME_DIR);
//        Process process = Runtime.getRuntime().exec("java "+IDEA_DEBUG +" -jar server.jar", new String[0], RUNTIME_DIR);
        InputStream stream = process.getErrorStream();
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        while (stream.available() == 0) {
            Thread.sleep(100);
        }
        int i;
        while ((i = stream.read()) != -1) {
            os.write(i);
            String output = new String(os.toByteArray());
            if (output.indexOf("SEVERE") >= 0 && stream.available() == 0) {
                output = new String(os.toByteArray());
                System.out.println(output);
                throw new AssertionError("Boot exception reported");
            }
            if (output.indexOf("Fabric3 ready [") != -1) {
                System.out.println("Runtime booted");
                break;

            }
        }
    }

    protected void tearDown() throws Exception {
        Process shutdown = Runtime.getRuntime().exec("java -jar shutdown.jar", new String[0], RUNTIME_DIR);
        shutdown.waitFor();
    }


}
