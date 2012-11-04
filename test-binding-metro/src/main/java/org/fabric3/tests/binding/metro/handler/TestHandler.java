package org.fabric3.tests.binding.metro.handler;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPMessage;

import org.oasisopen.sca.annotation.Scope;

import org.fabric3.spi.binding.handler.BindingHandler;
import org.fabric3.spi.invocation.Message;

/**
 *
 */
@Scope("COMPOSITE")
public class TestHandler implements BindingHandler<SOAPMessage>{
    public static boolean OUTBOUND_INVOKED = false;
    public static boolean INBOUND_INVOKED = false;

    public QName getType() {
        return null;
    }

    public void handleOutbound(Message message, SOAPMessage context) {
       OUTBOUND_INVOKED = true;
    }

    public void handleInbound(SOAPMessage context, Message message) {
       INBOUND_INVOKED = true;
    }
}
