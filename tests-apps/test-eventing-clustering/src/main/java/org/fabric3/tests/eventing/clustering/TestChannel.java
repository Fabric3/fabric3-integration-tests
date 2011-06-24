package org.fabric3.tests.eventing.clustering;

/**
 * @version $Rev$ $Date$
 */
public interface TestChannel {

    void send(Message msg);
}
