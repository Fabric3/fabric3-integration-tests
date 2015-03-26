package org.fabric3.tests.eventing;

import com.lmax.disruptor.RingBuffer;
import org.fabric3.api.annotation.Producer;
import org.fabric3.api.annotation.scope.Composite;
import org.fabric3.tests.binding.harness.eventing.TestProducer;
import org.junit.Assert;

/**
 *
 */
@Composite
public class TestDirectProducer implements TestProducer {

    @Producer
    protected RingBuffer ringBuffer;

    public void produce(String message) {
        Assert.assertNotNull(ringBuffer);
    }
}
