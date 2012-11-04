package org.fabric3.tests.eventing.clustering;

import java.util.UUID;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

import org.oasisopen.sca.annotation.Scope;

import org.fabric3.api.annotation.Producer;
import org.fabric3.api.annotation.management.Management;
import org.fabric3.api.annotation.management.ManagementOperation;

/**
 *
 */
@Management
@Scope("COMPOSITE")
public class TestProducerImpl implements TestProducer {
    private String uuid = UUID.randomUUID().toString();
    private AtomicLong sequence = new AtomicLong();

    @Producer
    protected TestChannel channel;

    private Runner runner = new Runner();
    private AtomicBoolean stop = new AtomicBoolean(true);

    @ManagementOperation
    public String start() {
        if (!stop.getAndSet(false)) {
            return uuid;
        }
        new Thread(runner).start();
        return uuid;
    }

    @ManagementOperation
    public void stop() {
        stop.set(true);
    }

    private class Runner implements Runnable {

        public void run() {
            while (!stop.get()) {
                try {
                    long number = sequence.incrementAndGet();
                    channel.send(new Message(uuid, number));
                } catch (RejectedExecutionException e) {
//                    e.printStackTrace();
                }
            }
        }
    }

}
