package org.fabric3.tests.binding.harness.eventing;

import java.util.concurrent.CountDownLatch;

import org.oasisopen.sca.annotation.Scope;

import org.fabric3.api.annotation.Consumer;

/**
 * @version $Rev$ $Date$
 */
@Scope("COMPOSITE")
public class TestConsumerImpl implements TestConsumer {
    private CountDownLatch latch;

    public void setWaitCount(int count) {
        latch = new CountDownLatch(count);
    }

    public void waitOnEvents() throws InterruptedException {
      latch.await();
    }

    @Consumer("event")
    public void onEvent(Object event) {
        if (event instanceof TestEvent) {
            latch.countDown();
            return;
        } else if ((event instanceof String)) {
            latch.countDown();
            return;
        }
        throw new IllegalArgumentException("Unknown event type: " + event);
    }

}
