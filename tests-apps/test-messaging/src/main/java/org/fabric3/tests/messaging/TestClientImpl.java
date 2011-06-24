package org.fabric3.tests.messaging;

import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

import org.oasisopen.sca.ServiceUnavailableException;
import org.oasisopen.sca.annotation.Reference;
import org.oasisopen.sca.annotation.Scope;

import org.fabric3.api.annotation.Resource;
import org.fabric3.api.annotation.management.Management;
import org.fabric3.api.annotation.management.ManagementOperation;

/**
 * @version $Rev$ $Date$
 */
@Management
@Scope("COMPOSITE")
public class TestClientImpl implements CallbackService {

    private enum Type {
        ONEWAY, REQUEST_REPLY, CALLBACK
    }

    private String uuid = UUID.randomUUID().toString();
    private AtomicLong sequence = new AtomicLong();
    public int clients = 20;
    private int number = 1000;
    private long start;

    @Resource
    ExecutorService executorService;

    @Reference
    protected TestService service;

    @Reference
    protected TestOneWayService oneWayService;

    @Reference
    protected TestCallbackService callbackService;

    private AtomicBoolean stop = new AtomicBoolean(true);

    @ManagementOperation
    public void setClients(int clients) {
        this.clients = clients;
    }

    @ManagementOperation
    public int getClients() {
        return clients;
    }

    @ManagementOperation
    public void setNumber(int number) {
        this.number = number;
    }

    @ManagementOperation
    public int getNumber() {
        return number;
    }

    @ManagementOperation
    public String startOneWay() {
        if (!stop.getAndSet(false)) {
            return uuid;
        }
        executorService.execute(new Multiplexer(Type.ONEWAY));
        return uuid;
    }

    @ManagementOperation
    public String startCallback() {
        if (!stop.getAndSet(false)) {
            return uuid;
        }
        executorService.execute(new Multiplexer(Type.CALLBACK));
        return uuid;
    }

    @ManagementOperation
    public String startRequestReply() {
        if (!stop.getAndSet(false)) {
            return uuid;
        }
        executorService.execute(new Multiplexer(Type.REQUEST_REPLY));
        return uuid;
    }

    @ManagementOperation
    public void stop() {
        stop.set(true);
    }

    public void onResponse(ResponseMessage message) {
        if (Message.Type.START == message.getType()) {
            start = System.currentTimeMillis();
        } else if (Message.Type.END == message.getType()) {
            System.out.println("Final callback: " + message.getSequence() + " [" + (System.currentTimeMillis() - start) + "]");
        }
    }

    private abstract class AbstractRunner implements Runnable {
        private CountDownLatch latch;

        private AbstractRunner(CountDownLatch latch) {
            this.latch = latch;
        }

        public void run() {

            long start = System.currentTimeMillis();
            invoke(Message.Type.START);
            int i = 0;
            while (!stop.get() && i < number) {
                try {
                    invoke(Message.Type.CONTINUE);
                    i++;
                } catch (RejectedExecutionException e) {
//                    e.printStackTrace();
                } catch (ServiceUnavailableException e) {
                    e.printStackTrace();
                }
            }
            invoke(Message.Type.END);
            latch.countDown();
            System.out.println("Runner time: " + (System.currentTimeMillis() - start));
        }

        protected abstract void invoke(Message.Type type);

    }

    private class CallbackRunner extends AbstractRunner {

        private CallbackRunner(CountDownLatch latch) {
            super(latch);
        }

        @Override
        protected void invoke(Message.Type type) {
            long number = sequence.incrementAndGet();
            callbackService.invoke(new Message(uuid, number, type));
        }
    }

    private class OneWayRunner extends AbstractRunner {

        private OneWayRunner(CountDownLatch latch) {
            super(latch);
        }

        @Override
        protected void invoke(Message.Type type) {
            long number = sequence.incrementAndGet();
            oneWayService.invoke(new Message(uuid, number, type));
        }
    }

    private class RequestReplyRunner extends AbstractRunner {

        private RequestReplyRunner(CountDownLatch latch) {
            super(latch);
        }

        @Override
        protected void invoke(Message.Type type) {
            long number = sequence.incrementAndGet();
            service.invoke(new Message(uuid, number, type));
        }
    }

    private class Multiplexer implements Runnable {
        private Type type;

        private Multiplexer(Type type) {
            this.type = type;
        }

        public void run() {
            CountDownLatch latch = new CountDownLatch(clients);
            long start = System.currentTimeMillis();
            switch (type) {

            case ONEWAY:
                for (int i = 0; i <= clients - 1; i++) {
                    executorService.execute(new OneWayRunner(latch));
                }
                break;
            case REQUEST_REPLY:
                for (int i = 0; i <= clients - 1; i++) {
                    executorService.execute(new RequestReplyRunner(latch));
                }
                break;
            case CALLBACK:
                for (int i = 0; i <= clients - 1; i++) {
                    executorService.execute(new CallbackRunner(latch));
                }
                break;
            }
            try {
                latch.await();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println("Total time:" + (System.currentTimeMillis() - start));

        }
    }

}
