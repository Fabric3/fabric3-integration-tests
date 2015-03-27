package org.fabric3.tests.eventing;

import junit.framework.TestCase;
import org.fabric3.api.annotation.monitor.Monitor;
import org.fabric3.tests.binding.harness.eventing.TestConsumer;
import org.fabric3.tests.binding.harness.eventing.TestProducer;
import org.fabric3.tests.binding.harness.eventing.TestUnTypedProducer;
import org.oasisopen.sca.annotation.Reference;

/**
 *
 */
public class TestClient extends TestCase {

    @Reference
    protected TestProducer producer;

    @Reference
    protected ContextService contextService;

    @Reference
    protected TestUnTypedProducer unTypedProducer;

    @Reference
    protected TestConsumer consumer;

    @Reference
    protected TestProducer directProducer;

    @Monitor
    protected TestMonitor monitor;

    public void testMonitor() throws Exception {
        monitor.test("test");
    }

    public void testProduce() throws Exception {
        consumer.setWaitCount(2);
        producer.produce("message");
        producer.produce("message");
        directProducer.produce("message");
        consumer.waitOnEvents();
    }

    public void testTypeFilteringOnConsumer() throws Exception {
        unTypedProducer.produce();
    }

    public void testContext() throws Exception {
        contextService.invoke();

    }
}
