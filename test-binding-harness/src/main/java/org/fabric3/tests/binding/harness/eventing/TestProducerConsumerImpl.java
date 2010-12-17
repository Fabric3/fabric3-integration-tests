package org.fabric3.tests.binding.harness.eventing;

import java.util.concurrent.CountDownLatch;

import org.oasisopen.sca.annotation.Scope;
import org.oasisopen.sca.annotation.Service;

import org.fabric3.api.annotation.Consumer;
import org.fabric3.api.annotation.Producer;

/**
 * @version $Rev$ $Date$
 */
@Scope("COMPOSITE")
@Service(names={TestConsumer.class, TestProducer.class})
public class TestProducerConsumerImpl implements TestConsumer, TestProducer {
    private CountDownLatch latch;
    private ProducerChannel channel;

    public TestProducerConsumerImpl(@Producer("channel") ProducerChannel channel) {
        this.channel = channel;
    }

    public void setWaitCount(int count) {
        latch = new CountDownLatch(count);
    }

    public void waitOnEvents() throws InterruptedException {
        latch.await();
    }

    @Consumer("channel")
    public void onEvent(Object event) {
        if (event instanceof TestEvent) {
            latch.countDown();
            return;
        } else if (event instanceof String) {
            latch.countDown();
            return;
        }
        throw new IllegalArgumentException("Unknown event type: " + event);
    }

    @Override
    public void produce(String message) {
        channel.sendEvent("test");
    }
}
