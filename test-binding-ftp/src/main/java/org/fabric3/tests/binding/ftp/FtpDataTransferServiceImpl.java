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
package org.fabric3.tests.binding.ftp;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import org.oasisopen.sca.annotation.Context;

import org.fabric3.api.Fabric3RequestContext;

/**
 * @version $Rev$ $Date$
 */
public class FtpDataTransferServiceImpl implements FtpDataTransferService {

    @Context
    protected Fabric3RequestContext context;

    public void transferData(String fileName, InputStream data) throws Exception {
        String type = context.getHeader(String.class, "f3.contentType");
        if ("BINARY".equals(type)) {
            handleBinary(data);
        } else {
            handleText(fileName, data);
        }
    }

    private void handleBinary(InputStream data) throws IOException {
        // test basic binary transfer
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        byte buffer[] = new byte[1024];
        for (int count; (count = data.read(buffer, 0, buffer.length)) > 0;) {
            stream.write(buffer, 0, count);
        }
        byte[] expected = new byte[]{0x9};
        if (!Arrays.equals(expected, stream.toByteArray())) {
            throw new AssertionError("Invalid binary file received");
        }
    }

    private void handleText(String fileName, InputStream data) {
        try {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            byte buffer[] = new byte[1024];
            for (int count; (count = data.read(buffer, 0, buffer.length)) > 0;) {
                stream.write(buffer, 0, count);
            }
            if (!"test".equals(new String(stream.toByteArray()))) {
                throw new AssertionError("Invalid String received");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


}
