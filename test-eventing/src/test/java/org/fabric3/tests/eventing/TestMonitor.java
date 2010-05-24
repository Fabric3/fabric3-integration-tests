package org.fabric3.tests.eventing;

import org.fabric3.api.annotation.monitor.Severe;

/**
 * @version $Rev$ $Date$
 */
public interface TestMonitor {

    @Severe
    void test(String message);
}
