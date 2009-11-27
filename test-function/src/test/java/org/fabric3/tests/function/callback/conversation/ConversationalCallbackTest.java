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
package org.fabric3.tests.function.callback.conversation;

import junit.framework.TestCase;
import org.oasisopen.sca.annotation.Reference;

import org.fabric3.tests.function.callback.common.CallbackData;

/**
 * Tests for stateless calbacks.
 *
 * @version $Rev$ $Date$
 */
public class ConversationalCallbackTest extends TestCase {
    @Reference
    protected ConversationalClientService client;

    @Reference
    protected ConversationalCallbackClientEndsService conversationalCallbackClientEndsService;

    @Reference
    protected ConversationalClientService conversationalToCompositeClient;

    /**
     * Verfies a callback is routed back to the correct conversational client instance.
     *
     * @throws Throwable
     */
    public void txestConversationalCallback() throws Throwable {
        CallbackData data = new CallbackData(1);
        client.invoke(data);
        data.getLatch().await();
        if (data.isError()) {
            throw data.getException();
        }
        assertTrue(data.isCalledBack());
        assertEquals(3, client.getCount());
    }

    public void testCallbackEndsConversation() throws Throwable {
        conversationalCallbackClientEndsService.invoke();
        // the next call must create another conversation
        conversationalCallbackClientEndsService.invoke();
    }

    /**
     * Verifies a conversational client is called back when it invokes a composite-scoped component which in turn invokes another composite-scoped
     * component via a non-blocking operation. The fabric must route back to the orignal conversational client instance as the invocation sequence is
     * processed on different threads.
     *
     * @throws Throwable
     */
    public void txestConversationalToCompositeCallback() throws Throwable {
        CallbackData data = new CallbackData(1);
        conversationalToCompositeClient.invoke(data);
        data.getLatch().await();
        if (data.isError()) {
            throw data.getException();
        }
        assertTrue(data.isCalledBack());
        assertEquals(3, conversationalToCompositeClient.getCount());
    }

}