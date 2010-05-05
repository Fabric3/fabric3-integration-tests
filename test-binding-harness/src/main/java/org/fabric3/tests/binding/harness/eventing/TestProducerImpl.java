package org.fabric3.tests.binding.harness.eventing;

import org.fabric3.api.annotation.Producer;

/**
 * @version $Rev$ $Date$
 */
public class TestProducerImpl implements TestProducer {

    @Producer
    protected ProducerChannel channel;

    public void produce(String message) {
        channel.sendEvent(message);
        channel.sendEvent(new TestEvent(message));
    }
}
