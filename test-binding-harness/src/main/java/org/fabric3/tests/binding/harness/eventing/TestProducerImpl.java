package org.fabric3.tests.binding.harness.eventing;

import org.fabric3.api.annotation.Producer;

/**
 *
 */
public class TestProducerImpl implements TestProducer {

    @Producer
    protected ProducerChannel channel;

    public void setChannel(ProducerChannel channel) {
        this.channel = channel;
    }

    public void produce(String message) {
        channel.sendEvent(message);
        channel.sendEvent(new TestEvent(message));
    }
}
