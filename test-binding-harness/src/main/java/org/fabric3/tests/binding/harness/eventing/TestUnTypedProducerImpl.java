package org.fabric3.tests.binding.harness.eventing;

import org.fabric3.api.annotation.Producer;

/**
 * @version $Rev$ $Date$
 */
public class TestUnTypedProducerImpl implements TestUnTypedProducer {

    @Producer
    protected UnTypedProducerChannel channel;

    public void produce() {
        channel.sendEvent(new Object());
    }
}