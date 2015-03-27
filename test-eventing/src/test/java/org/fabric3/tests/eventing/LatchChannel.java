package org.fabric3.tests.eventing;

import java.util.concurrent.CountDownLatch;

/**
 *
 */
public interface LatchChannel {

    void process(CountDownLatch latch);
}
