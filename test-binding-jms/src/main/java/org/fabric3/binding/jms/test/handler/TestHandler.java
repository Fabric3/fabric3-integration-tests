package org.fabric3.binding.jms.test.handler;

import javax.xml.namespace.QName;

import org.fabric3.spi.container.binding.BindingHandler;
import org.fabric3.spi.container.invocation.Message;
import org.oasisopen.sca.annotation.Scope;

/**
 *
 */
@Scope("COMPOSITE")
public class TestHandler implements BindingHandler<javax.jms.Message> {
    public static boolean OUTBOUND_INVOKED = false;
    public static boolean INBOUND_INVOKED = false;

    public QName getType() {
        return null;
    }

    public void handleOutbound(Message message, javax.jms.Message context) {
       OUTBOUND_INVOKED = true;
    }

    public void handleInbound(javax.jms.Message context, Message message) {
       INBOUND_INVOKED = true;
    }
}
