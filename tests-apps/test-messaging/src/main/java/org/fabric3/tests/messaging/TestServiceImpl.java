package org.fabric3.tests.messaging;

import org.oasisopen.sca.annotation.Scope;

/**
 *
 */
@Scope("COMPOSITE")
public class TestServiceImpl implements TestService {
    private long start = -1;

    public String invoke(Message message) {
        if (Message.Type.START == message.getType()) {
            start = System.currentTimeMillis();
        } else if (Message.Type.END == message.getType()) {
            System.out.println("Invoked: " + message.getSequence() + " [" + (System.currentTimeMillis() - start) + "]");
        }  else {
//            System.out.println("Invoked "+ message.getSequence());
        }
        return String.valueOf(message.getSequence());
    }
}
