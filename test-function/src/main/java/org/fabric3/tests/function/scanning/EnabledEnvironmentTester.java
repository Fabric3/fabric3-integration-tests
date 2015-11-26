package org.fabric3.tests.function.scanning;

import org.fabric3.api.annotation.model.Component;
import org.fabric3.api.annotation.scope.Scopes;
import org.oasisopen.sca.annotation.EagerInit;
import org.oasisopen.sca.annotation.Init;
import org.oasisopen.sca.annotation.Scope;

/**
 *
 */
@EagerInit
@Scope(Scopes.COMPOSITE)
@Component(environments = "test")
public class EnabledEnvironmentTester {

    @Init
    public void init() {
        throw new AssertionError("Component should not be enabled");
    }

}
