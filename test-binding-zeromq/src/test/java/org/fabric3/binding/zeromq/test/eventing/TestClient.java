package org.fabric3.binding.zeromq.test.eventing;

import junit.framework.TestCase;
import org.oasisopen.sca.annotation.Reference;

import org.fabric3.tests.binding.harness.eventing.TestConsumer;
import org.fabric3.tests.binding.harness.eventing.TestProducer;

/**
 *
 */
public class TestClient extends TestCase {

    @Reference
    protected TestProducer producer;

    @Reference
    protected TestConsumer consumer;

    public void testDurableProduce() throws Exception {
        consumer.setWaitCount(1);
        Thread.sleep(10000);
        for (int i = 0; i < 1002; i++) {
            producer.produce("message");
        }
        consumer.waitOnEvents();
    }


}