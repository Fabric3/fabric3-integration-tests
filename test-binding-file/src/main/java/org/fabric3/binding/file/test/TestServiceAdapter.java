/*
* Fabric3
* Copyright (c) 2009-2012 Metaform Systems
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

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.fabric3.binding.file.api.InvalidDataException;
import org.fabric3.binding.file.api.ServiceAdapter;

/**
 * @version $Rev$ $Date$
 */
@SuppressWarnings({"ResultOfMethodCallIgnored"})
public class TestServiceAdapter implements ServiceAdapter {

    public Object[] beforeInvoke(File file) throws InvalidDataException {
        InputStream headerStream = null;
        InputStream contentsStream = null;
        try {
            headerStream = new FileInputStream(file);
            File contents = getContentsFile(file);
            contentsStream = new FileInputStream(contents);
            return new Object[]{headerStream, contentsStream};
        } catch (FileNotFoundException e) {
            close(headerStream);
            close(contentsStream);
            throw new InvalidDataException(e);
        }
    }

    public void afterInvoke(File file, Object[] payload) {
        close((InputStream) payload[0]);
        close((InputStream) payload[1]);
    }

    public void error(File file, File errorDirectory, Exception e) throws IOException {
        File contentsFile = getContentsFile(file);
        move(file, errorDirectory);
        move(contentsFile, errorDirectory);
    }

    public void delete(File file) {
        File contents = getContentsFile(file);
        file.delete();
        contents.delete();
    }

    public void archive(File file, File archiveDirectory) {
        move(file, archiveDirectory);
        File contentsFile = getContentsFile(file);
        move(contentsFile, archiveDirectory);
    }

    private File getContentsFile(File file) {
        String id = file.getName().substring(6, file.getName().indexOf(".xml"));
        String filename = "file" + id + ".xml";
        return new File(file.getParentFile(), filename);
    }

    private void move(File file, File destinationDirectory) {
        File destFile = new File(destinationDirectory, file.getName());
        InputStream input = null;
        OutputStream output = null;
        try {
            input = new FileInputStream(file);
            output = new FileOutputStream(destFile);
            copy(input, output);
        } catch (IOException e) {
            close(input);
            close(output);
        }
        file.delete();
    }

    private void close(Closeable stream) {
        if (stream != null) {
            try {
                stream.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static int copy(InputStream input, OutputStream output) throws IOException {
        byte[] buffer = new byte[1024];
        int count = 0;
        int n;
        while (-1 != (n = input.read(buffer))) {
            output.write(buffer, 0, n);
            count += n;
        }
        return count;
    }

}
