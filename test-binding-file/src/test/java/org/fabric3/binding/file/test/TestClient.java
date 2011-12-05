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
import org.oasisopen.sca.annotation.Property;
import org.oasisopen.sca.annotation.Reference;

/**
 * @version $Rev$ $Date$
 */
@SuppressWarnings({"ResultOfMethodCallIgnored"})
public class TestClient extends TestCase {
    private static final File RUNTIME_BASE = new File(System.getProperty("java.io.tmpdir"), ".f3");
    private static final File BASE = new File(RUNTIME_BASE, "inbox");
    private static final File BASE_OUT = new File(RUNTIME_BASE, "outbox");

    private File dropDir = new File(BASE, "drop");
    private File outputDir = new File(BASE_OUT, "dropoutput");
    private File errorDir = new File(BASE, "droperror");

    private File xmlFile;
    private File outputFile;

    @Reference
    protected LatchService latchService;

    @Property
    public void setDropDir(String dropDir) {
        this.dropDir = new File(BASE, dropDir);
    }

    @Property
    public void setOutputDir(String outputDir) {
        this.outputDir = new File(BASE_OUT, outputDir);
    }

    @Property
    public void setErrorDir(String errorDir) {
        this.errorDir = new File(BASE, errorDir);
    }

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
        outputFile = new File(outputDir, "test.xml");

        FileWriter writer = null;
        try {
            xmlFile = new File(dropDir, "test.xml");
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
        if (dropDir.exists()) {
            FileHelper.deleteContents(dropDir);
        } else {
            dropDir.mkdirs();
        }
        if (errorDir.exists()) {
            FileHelper.deleteContents(errorDir);
        } else {
            errorDir.mkdirs();
        }
        if (outputDir.exists()) {
            FileHelper.deleteContents(outputDir);
        } else {
            outputDir.mkdirs();
        }
    }

    private void cleanup() {
        FileHelper.delete(dropDir);
        FileHelper.delete(errorDir);
        FileHelper.delete(outputDir);
    }

}
