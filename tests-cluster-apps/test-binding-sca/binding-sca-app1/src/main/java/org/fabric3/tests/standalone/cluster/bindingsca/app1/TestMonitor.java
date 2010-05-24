package org.fabric3.tests.standalone.cluster.bindingsca.app1;

/**
 * @version $Rev$ $Date$
 */
public interface TestMonitor {

    @org.fabric3.api.annotation.monitor.Error
    void message(String message);
}
