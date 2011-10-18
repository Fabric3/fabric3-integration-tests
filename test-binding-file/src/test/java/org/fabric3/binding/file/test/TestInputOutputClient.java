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
package org.fabric3.binding.file.test;

import java.io.File;
import java.io.FileWriter;

import junit.framework.TestCase;
import org.oasisopen.sca.annotation.Reference;

/**
 * @version $Rev$ $Date$
 */
public class TestInputOutputClient extends TestCase {
    private static final File RUNTIME_BASE = new File(System.getProperty("java.io.tmpdir"), ".f3");
    private static final File BASE = new File(RUNTIME_BASE, "inbox");
    private static final File BASE_OUTPUT = new File(RUNTIME_BASE, "outbox");
    private static final File DROP_DIR = new File(BASE, "drop");
    private static final File OUTPUT_DIR = new File(BASE_OUTPUT, "dropoutput");
    private static final File ERROR_DIRECTORY = new File(BASE, "droperror");
    private File xmlFile;
    private File outputFile;

    @Reference
    protected LatchService latchService;

    public void testInvoke() throws Exception {
        latchService.await();
        while (xmlFile.exists()) {
            Thread.sleep(10);
        }
        while (!outputFile.exists()) {
            Thread.sleep(10);
        }
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        clear();
        outputFile = new File(OUTPUT_DIR, "testouput.xml");

        FileWriter writer = null;
        try {
            xmlFile = new File(DROP_DIR, "test.xml");
            writer = new FileWriter(xmlFile);
            writer.write("<?xml version='1.0' encoding='UTF-8'?><test/>");
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
        cleanup();
    }

    private void clear() {
        if (DROP_DIR.exists()) {
            FileHelper.deleteContents(DROP_DIR);
        } else {
            DROP_DIR.mkdirs();
        }
        if (ERROR_DIRECTORY.exists()) {
            FileHelper.deleteContents(ERROR_DIRECTORY);
        } else {
            ERROR_DIRECTORY.mkdirs();
        }
        if (OUTPUT_DIR.exists()) {
            FileHelper.deleteContents(OUTPUT_DIR);
        } else {
            OUTPUT_DIR.mkdirs();
        }
    }

    private void cleanup() {
        FileHelper.delete(DROP_DIR);
        FileHelper.delete(ERROR_DIRECTORY);
        FileHelper.delete(OUTPUT_DIR);
    }

}
