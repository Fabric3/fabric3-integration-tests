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
package org.fabric3.tests.function.callback.stateless;

import junit.framework.TestCase;
import org.oasisopen.sca.annotation.Reference;

import org.fabric3.tests.function.callback.common.CallbackData;

/**
 * Tests for stateless callbacks.
 *
 * @version $Rev$ $Date$
 */
public class StatelessCallbackTest extends TestCase {
    @Reference
    protected ClientService client1;

    @Reference
    protected ClientService client2;

    /**
     * Verifies the case where two clients are wired to the same target bidirectional service and the callback is made to the correct client.
     *
     * @throws Exception
     */
    public void testSimpleCallback() throws Exception {
        CallbackData data = new CallbackData(1);
        client1.invoke(data);
        data.getLatch().await();
        assertTrue(data.isCalledBack());
    }

    /**
     * Verifies a callback from a forward invocation through a ServiceReference
     *
     * @throws Exception
     */
    public void testServiceReferenceCallback() throws Exception {
//        CountDownLatch latch = new CountDownLatch(1);
//        client1.invokeServiceReferenceCallback(latch);
//        latch.await();
//        assertTrue(client1.isCallback());
//        // test that the other client was not issued a callback
//        assertFalse(client2.isCallback());
    }

    /**
     * Verifies callbacks are routed for a sequence of two forward invocations:
     * <pre>
     * CallbackClient--->ForwardService--->EndService
     * </pre>
     *
     * @throws Exception
     */
    public void testMultipleHopCallback() throws Exception {
//        CountDownLatch latch = new CountDownLatch(1);
//        client1.invokeMultipleHops(latch);
//        latch.await();
//        assertTrue(client1.isCallback());
//        assertFalse(client2.isCallback());
    }

    /**
     * Verifies a callback is routed through a CallableReference passed to another service.
     *
     * @throws Exception
     */
    public void testNoCallbackServiceReference() throws Exception {
//        CountDownLatch latch = new CountDownLatch(2);
//        client1.invokeNoCallbackServiceReference(latch);
//        latch.await();
//        assertTrue(client1.isCallback());
//        assertFalse(client2.isCallback());
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }
}
