package org.fabric3.tests.eventing;

import java.util.concurrent.CountDownLatch;

import org.fabric3.api.annotation.Consumer;
import org.fabric3.api.annotation.scope.Composite;

/**
 *
 */
@Composite
public class ContextConsumer {

    @Consumer
    public void process(CountDownLatch latch) {
        latch.countDown();
    }
}
