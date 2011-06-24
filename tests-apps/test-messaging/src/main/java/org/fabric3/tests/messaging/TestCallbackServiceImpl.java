package org.fabric3.tests.messaging;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

import org.oasisopen.sca.annotation.Callback;
import org.oasisopen.sca.annotation.Scope;

/**
 * @version $Rev$ $Date$
 */
@Scope("COMPOSITE")
public class TestCallbackServiceImpl implements TestCallbackService {
    private CallbackService callback;
    private String uuid = UUID.randomUUID().toString();
    private AtomicLong sequence = new AtomicLong();
    private long start = -1;

    @Callback
    public void setCallback(CallbackService callback) {
        this.callback = callback;
    }

    public void invoke(Message message) {
        callback.onResponse(new ResponseMessage(uuid, sequence.getAndIncrement(), message.getSequence(), message.getType()));
        if (Message.Type.START == message.getType()) {
            start = System.currentTimeMillis();
        } else if (Message.Type.END == message.getType()) {
            System.out.println("Invoked: " + message.getSequence() + " [" + (System.currentTimeMillis() - start) + "]");
        }
    }

}
