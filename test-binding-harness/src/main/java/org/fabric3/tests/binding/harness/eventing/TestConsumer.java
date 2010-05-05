package org.fabric3.tests.binding.harness.eventing;

/**
 * @version $Rev$ $Date$
 */
public interface TestConsumer {

    void setWaitCount(int count);

    void waitOnEvents() throws InterruptedException;

}
