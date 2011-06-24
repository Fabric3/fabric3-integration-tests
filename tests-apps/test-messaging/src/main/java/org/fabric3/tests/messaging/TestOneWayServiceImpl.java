package org.fabric3.tests.messaging;

import org.oasisopen.sca.annotation.Scope;

/**
 * @version $Rev$ $Date$
 */
@Scope("COMPOSITE")
public class TestOneWayServiceImpl implements TestOneWayService {
    private long start = -1;

    public void invoke(Message message) {
        if (Message.Type.START == message.getType()) {
            start = System.currentTimeMillis();
        } else if (Message.Type.END == message.getType()) {
            System.out.println("Invoked: " + message.getSequence() + " [" + (System.currentTimeMillis() - start) + "]");
        }
    }

}
