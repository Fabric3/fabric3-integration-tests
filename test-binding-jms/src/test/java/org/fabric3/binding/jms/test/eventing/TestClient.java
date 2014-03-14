package org.fabric3.binding.jms.test.eventing;

import junit.framework.TestCase;
import org.fabric3.api.annotation.Producer;
import org.fabric3.binding.jms.test.jaxb.WeatherCondition;
import org.fabric3.binding.jms.test.jaxb.WeatherResponse;
import org.fabric3.tests.binding.harness.eventing.TestConsumer;
import org.fabric3.tests.binding.harness.eventing.TestProducer;
import org.oasisopen.sca.annotation.Reference;

/**
 *
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

    @Producer
    protected WeatherChannel weatherChannel;

    @Reference
    protected TestConsumer weatherConsumer;

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

    public void testJAXBChannels() throws Exception {
        weatherConsumer.setWaitCount(1);
        WeatherResponse message = new WeatherResponse();
        message.setCondition(WeatherCondition.SUNNY);
        weatherChannel.publish(message);
        weatherConsumer.waitOnEvents();
    }

}