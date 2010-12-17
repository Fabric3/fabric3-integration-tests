package org.fabric3.binding.jms.test.eventing;

import junit.framework.TestCase;
import org.oasisopen.sca.annotation.Reference;

import org.fabric3.tests.binding.harness.eventing.TestConsumer;
import org.fabric3.tests.binding.harness.eventing.TestProducer;

/**
 * @version $Rev$ $Date$
 */
public class TestClient extends TestCase {

    @Reference
    protected TestProducer durableProducer;

    @Reference
    protected TestConsumer durableConsumer;

    @Reference
    protected TestProducer loopbackProducer;

    @Reference
    protected TestConsumer loopbackConsumer;


    public void testDurableProduce() throws Exception {
        durableConsumer.setWaitCount(2);
        durableProducer.produce("message");
        durableProducer.produce("message");
        durableConsumer.waitOnEvents();
    }

    public void testSameConsumerProducerName() throws Exception {
        loopbackConsumer.setWaitCount(1);
        loopbackProducer.produce("test");
        loopbackConsumer.waitOnEvents();
    }

}