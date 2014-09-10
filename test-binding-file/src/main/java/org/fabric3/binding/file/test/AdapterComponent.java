/*
* Fabric3
* Copyright (c) 2009-2014 Metaform Systems
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

import org.fabric3.api.binding.file.InvalidDataException;
import org.fabric3.api.binding.file.ReferenceAdapter;
import org.fabric3.api.binding.file.ServiceAdapter;
import org.oasisopen.sca.annotation.Scope;

import java.io.*;

/**
 *
 */
@SuppressWarnings({"ResultOfMethodCallIgnored"})
@Scope("COMPOSITE")
public class AdapterComponent implements ServiceAdapter, ReferenceAdapter {

    public Object[] beforeInvoke(File file) throws InvalidDataException {
        InputStream headerStream = new ByteArrayInputStream(new byte[0]);
        InputStream contentsStream = new ByteArrayInputStream(new byte[0]);
        return new Object[]{headerStream, contentsStream};
    }

    public void afterInvoke(File file, Object[] payload) {
        close((InputStream) payload[0]);
        close((InputStream) payload[1]);
    }

    public void error(File file, File errorDirectory, Exception e) throws IOException {
        move(file, errorDirectory);
    }

    public void delete(File file) {
        file.delete();
    }

    public void archive(File file, File archiveDirectory) {
        move(file, archiveDirectory);
    }

    public OutputStream createOutputStream(File file) throws IOException {
        return new BufferedOutputStream(new FileOutputStream(file));
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
            e.printStackTrace();
        }  finally {
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
