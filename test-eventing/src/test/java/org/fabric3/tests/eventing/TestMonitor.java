package org.fabric3.tests.eventing;

/**
 * @version $Rev$ $Date$
 */
public interface TestMonitor {

    @org.fabric3.api.annotation.monitor.Error
    void test(String message);
}
