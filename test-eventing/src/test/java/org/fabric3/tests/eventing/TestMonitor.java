package org.fabric3.tests.eventing;

import org.fabric3.api.annotation.monitor.Info;

/**
 *
 */
public interface TestMonitor {

    @Info
    void test(String message);
}
