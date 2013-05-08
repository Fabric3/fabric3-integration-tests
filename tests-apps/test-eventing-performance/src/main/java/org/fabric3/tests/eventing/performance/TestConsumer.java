package org.fabric3.tests.eventing.performance;

import org.fabric3.api.annotation.Consumer;
import org.fabric3.api.annotation.management.Management;
import org.fabric3.api.annotation.management.ManagementOperation;
import org.oasisopen.sca.annotation.Scope;

/**
 *
 */
@Scope("COMPOSITE")
@Management
public class TestConsumer {
    private int received;
    private long start;

    @Consumer
    public void onReceive(String msg) {
        if (received == 1000000) {
            System.out.println("Started");
            start = System.nanoTime();
        } else if (received == 2000000) {
            long elapsed = System.nanoTime() - start;
            double rate = elapsed / 1000000;
            System.out.println("Total time: " + elapsed + "  Rate: " + rate);
        }
        received++;

    }

    @ManagementOperation
    public void reset() {
        received = 0;
        start = 0;
    }

}
