package org.fabric3.tests.standalone.cluster.scope;

import org.fabric3.api.annotation.management.Management;

@Management
public interface TestSingletonMBean {

    String invoke(String message);

}