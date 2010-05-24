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
package org.fabric3.tests.standalone.cluster.bindingsca.app1;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

import org.oasisopen.sca.annotation.Reference;
import org.oasisopen.sca.annotation.Scope;
import org.oasisopen.sca.annotation.Service;

import org.fabric3.api.annotation.monitor.Monitor;
import org.fabric3.api.annotation.Producer;
import org.fabric3.tests.standalone.cluster.bindingsca.api.TestEvent;
import org.fabric3.tests.standalone.cluster.bindingsca.api.TestService;
import org.fabric3.tests.standalone.cluster.bindingsca.api.TestServiceCallback;


/**
 * @version $Rev$ $Date$
 */
@Service(names = {TestClientService.class, TestServiceCallback.class})
@Scope("COMPOSITE")
public class TestClient implements TestClientService, TestServiceCallback {
    private TestService testService;
    private TestMonitor monitor;
    private TestEventStream stream;

    private Map<String, CountDownLatch> latches = new ConcurrentHashMap<String, CountDownLatch>();

    public TestClient(@Reference(name = "testService") TestService testService, @Producer("producer") TestEventStream stream, @Monitor TestMonitor monitor) {
        this.testService = testService;
        this.stream = stream;
        this.monitor = monitor;
    }

    public String invoke(String message) {
        TestEvent event = new TestEvent("test");
        stream.fireEvent(event);
        monitor.message("Received invoke: " + message);
        CountDownLatch latch = new CountDownLatch(1);
        latches.put(message, latch);
        testService.invoke(message);
        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new AssertionError(e);
        }
        return message;
    }

    public void onResponse(String message) {
        monitor.message("Received callback: " + message);
        CountDownLatch latch = latches.get(message);
        latch.countDown();
    }


}
