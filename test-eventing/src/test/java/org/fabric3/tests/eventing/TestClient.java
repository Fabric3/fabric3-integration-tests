package org.fabric3.tests.eventing;

import junit.framework.TestCase;
import org.oasisopen.sca.annotation.Reference;

import org.fabric3.api.annotation.Monitor;
import org.fabric3.tests.binding.harness.eventing.TestConsumer;
import org.fabric3.tests.binding.harness.eventing.TestProducer;
import org.fabric3.tests.binding.harness.eventing.TestUnTypedProducer;

/**
 * @version $Rev$ $Date$
 */
public class TestClient extends TestCase {

    @Reference
    protected TestProducer producer;

    @Reference
    protected TestUnTypedProducer unTypedProducer;

    @Reference
    protected TestConsumer consumer;

    @Monitor("MonitorChannel")
    protected TestMonitor monitor;

    public void testMonitor() throws Exception {
        monitor.test("test");
    }

    public void testProduce() throws Exception {
        consumer.setWaitCount(2);
        producer.produce("message");
        consumer.waitOnEvents();
    }

    public void testTypeFilteringOnConsumer() throws Exception {
        unTypedProducer.produce();
    }
}
