package org.fabric3.tests.eventing;

import junit.framework.TestCase;
import org.oasisopen.sca.annotation.Reference;

import org.fabric3.tests.binding.harness.eventing.TestConsumer;
import org.fabric3.tests.binding.harness.eventing.TestProducer;

/**
 * @version $Rev$ $Date$
 */
public class TestClient extends TestCase {

    @Reference
    protected TestProducer producer;

    @Reference
    protected TestConsumer consumer;

    public void testProduce() throws Exception {
        consumer.setWaitCount(2);
        producer.produce("message");
        consumer.waitOnEvents();
    }
}
