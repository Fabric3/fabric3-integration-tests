package org.fabric3.tests.binding.harness.eventing;

/**
 *
 */
public interface ProducerChannel {

    void sendEvent(TestEvent message);

}
