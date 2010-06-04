package org.fabric3.tests.standalone.cluster.scope;

import org.oasisopen.sca.annotation.EagerInit;
import org.oasisopen.sca.annotation.Init;
import org.oasisopen.sca.annotation.Scope;

import org.fabric3.api.annotation.monitor.Monitor;

/**
 * @version $Rev$ $Date$
 */
@Scope("DOMAIN")
@EagerInit
public class SingletonComponent implements SingletonService, TestSingletonMBean {
    private TestMonitor monitor;

    public SingletonComponent(@Monitor TestMonitor monitor) {
        this.monitor = monitor;
    }

    @Init
    public void init() {
        monitor.message("Singleton initialized");
    }

    public String invoke(String message) {
        return message;
    }
}
