package org.fabric3.tests.binding.harness.eventing;

/**
 *
 */
public interface TestConsumer {

    void setWaitCount(int count);

    void waitOnEvents() throws InterruptedException;

}
