package org.fabric3.tests.binding.metro.handler;

import org.oasisopen.sca.annotation.Scope;

/**
 *
 */
@Scope("COMPOSITE")
public class HandlerServiceImpl implements HandlerService {

    public boolean test(String message) {
        return TestHandler.INBOUND_INVOKED;
    }
}
