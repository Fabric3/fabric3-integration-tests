package org.fabric3.tests.standalone.cluster.bindingsca.app2;

import org.fabric3.api.annotation.logging.Severe;

/**
 * @version $Rev$ $Date$
 */
public interface TestMonitor {

    @Severe
    void message(String message);
}