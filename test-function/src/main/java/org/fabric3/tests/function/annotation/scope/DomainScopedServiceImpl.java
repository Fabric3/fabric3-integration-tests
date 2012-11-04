package org.fabric3.tests.function.annotation.scope;

import org.oasisopen.sca.annotation.Scope;

/**
 *
 */
@Scope("DOMAIN")
public class DomainScopedServiceImpl implements DomainScopedService {
    private int value;

    public int getValue() {
        return value;
    }

    public void incrementValue() {
        value++;
    }
}
