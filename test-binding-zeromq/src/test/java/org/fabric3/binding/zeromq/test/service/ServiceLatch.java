package org.fabric3.binding.zeromq.test.service;

/**
 *
 */
public interface ServiceLatch {

    void countDown();

    void await() throws InterruptedException;
}
