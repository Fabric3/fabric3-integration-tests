package org.fabric3.tests.eventing.clustering;

import org.oasisopen.sca.annotation.Scope;

import org.fabric3.api.annotation.Consumer;

/**
 *
 */
@Scope("COMPOSITE")
public class TestConsumer {

    @Consumer
    public void onReceive(Message msg) {
        System.out.println("Received: " + msg.getSequence() + "[" + msg.getRuntime() + "]");
    }


}
