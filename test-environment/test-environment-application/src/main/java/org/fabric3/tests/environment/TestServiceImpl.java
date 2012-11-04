package org.fabric3.tests.environment;

import org.oasisopen.sca.annotation.EagerInit;
import org.oasisopen.sca.annotation.Init;
import org.oasisopen.sca.annotation.Scope;

/**
 *
 */
@EagerInit
@Scope("COMPOSITE")
public class TestServiceImpl implements TestService {

    @Init
    public void init() {

    }

    public String invoke(String msg) {
        return msg;
    }
}
