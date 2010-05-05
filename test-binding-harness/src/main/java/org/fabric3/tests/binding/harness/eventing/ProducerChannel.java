package org.fabric3.tests.binding.harness.eventing;

/**
 * @version $Rev$ $Date$
 */
public interface ProducerChannel {

    void sendEvent(String message);

    void sendEvent(TestEvent message);

}
