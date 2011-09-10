package org.fabric3.tests.environment;

import org.oasisopen.sca.annotation.EagerInit;
import org.oasisopen.sca.annotation.Init;
import org.oasisopen.sca.annotation.Scope;

/**
 * @version $Rev$ $Date$
 */
@EagerInit
@Scope("COMPOSITE")
public class TestErrorServiceImpl implements TestService {

    @Init
    public void init() {
        throw new RuntimeException("Loaded wrong composite");
    }

    public String invoke(String msg) {
        return msg;
    }
}
