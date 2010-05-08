package org.fabric3.tests.binding.harness.eventing;

import org.oasisopen.sca.annotation.Scope;

import org.fabric3.api.annotation.Consumer;

/**
 * @version $Rev$ $Date$
 */
@Scope("COMPOSITE")
public class TestTypedConsumer {

    @Consumer("event")
    public void onEvent(TestEvent event) {
        throw new AssertionError("Should not be called");
    }

}