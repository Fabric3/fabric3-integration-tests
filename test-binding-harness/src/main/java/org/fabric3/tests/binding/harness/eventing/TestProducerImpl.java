package org.fabric3.tests.binding.harness.eventing;

import org.fabric3.api.annotation.Producer;

/**
 *
 */
public class TestProducerImpl implements TestProducer {

    @Producer
    protected ProducerStringChannel channel;

    // required for Spring
    public void setChannel(ProducerStringChannel channel) {
        this.channel = channel;
    }

    public void produce(String message) {
        channel.sendEvent(message);
    }
}
