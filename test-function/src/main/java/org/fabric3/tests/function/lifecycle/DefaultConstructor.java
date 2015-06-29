package org.fabric3.tests.function.lifecycle;

import org.fabric3.api.annotation.scope.Composite;
import org.oasisopen.sca.annotation.EagerInit;

/**
 * Verifies the default ctor is invoked
 */
@EagerInit
@Composite
public class DefaultConstructor {

    public DefaultConstructor() {
    }

    public DefaultConstructor(String test) {
        throw new AssertionError("Wrong ctor selected");
    }

}
