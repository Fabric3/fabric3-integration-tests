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
package org.fabric3.tests.wsdl.matching.multicomplextype;

import junit.framework.TestCase;
import org.oasisopen.sca.annotation.Reference;

import org.fabric3.tests.wsdl.matching.multicomplextype.AddNumbersPortType;
import org.fabric3.tests.wsdl.matching.multicomplextype.AddNumbersType;
import org.fabric3.tests.wsdl.matching.multicomplextype.MappedAddNumbersPortType;

/**
 * @version $Rev$ $Date$
 */
public class MultiComplexTypeTestClient extends TestCase {

    @Reference
    protected AddNumbersPortType portType;

    @Reference
    protected MappedAddNumbersPortType mappedPortType;

    public void testPortTypeInvoke() throws Exception {
        AddNumbersType param1 = new AddNumbersType();
        param1.getArg().add(1);

        AddNumbersType param2 = new AddNumbersType();
        param2.getArg().add(1);

        assertEquals(2, portType.addNumbers(param1, param2));
    }


    /**
     * Verifies a Java-based contract that uses a @WebMethod to map an operation to a WSDL-based contract
     *
     * @throws Exception
     */
    public void testMappedPortTypeInvoke() throws Exception {
        AddNumbersType param1 = new AddNumbersType();
        param1.getArg().add(1);

        AddNumbersType param2 = new AddNumbersType();
        param2.getArg().add(1);
        assertEquals(2, mappedPortType.mappedAddNumbers(param1, param2));
    }

}
