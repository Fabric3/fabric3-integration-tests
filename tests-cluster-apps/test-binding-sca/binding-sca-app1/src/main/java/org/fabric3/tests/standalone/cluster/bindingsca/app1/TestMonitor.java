package org.fabric3.tests.standalone.cluster.bindingsca.app1;

import org.fabric3.api.annotation.monitor.Severe;

/**
 *
 */
public interface TestMonitor {

    @Severe
    void message(String message);
}
