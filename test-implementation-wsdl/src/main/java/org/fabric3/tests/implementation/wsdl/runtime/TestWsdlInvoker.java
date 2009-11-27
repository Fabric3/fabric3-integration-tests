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
package org.fabric3.tests.implementation.wsdl.runtime;

import org.w3c.dom.Node;

import org.fabric3.spi.invocation.Message;
import org.fabric3.spi.invocation.MessageImpl;
import org.fabric3.spi.wire.Interceptor;

/**
 * @version $Rev$ $Date$
 */
public class TestWsdlInvoker implements Interceptor {
    private TestWsdlComponent component;

    public TestWsdlInvoker(TestWsdlComponent component) {
        this.component = component;
    }

    public Message invoke(Message msg) {
        Object body = msg.getBody();
        Object returnValue;
        if (body == null) {
            returnValue = component.invoke(null);
        } else {
            if (body.getClass().isArray()) {
                Object[] bodyArray = (Object[]) body;
                if (bodyArray.length != 1) {
                    throw new AssertionError("Illegal parameter length: " + bodyArray.length);
                }
                returnValue = component.invoke(bodyArray[0]);
            } else if (body instanceof Node) {
                returnValue = component.invoke(body);
            } else {
                throw new AssertionError("Unexpected input format");
            }
        }
        Message ret = new MessageImpl();
        if (returnValue instanceof Exception && !(returnValue instanceof RuntimeException)) {
            // checked exception was thrown
            ret.setBodyWithFault(returnValue);
        } else {
            ret.setBody(returnValue);
        }
        return ret;
    }

    public void setNext(Interceptor next) {
        throw new UnsupportedOperationException();
    }

    public Interceptor getNext() {
        return null;
    }
}
