package org.fabric3.tests.binding.metro.soap;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPMessage;

import org.oasisopen.sca.annotation.Reference;

import org.fabric3.spi.container.binding.handler.BindingHandler;
import org.fabric3.spi.container.invocation.Message;
import org.fabric3.tests.binding.metro.wsdl.HelloJavaService;

public class TestSoapBindingHandler implements BindingHandler<SOAPMessage> {

    public static final String TEST_HANDLER_HEADER = "TestHandlerHeader";

    @Reference
    private HelloJavaService h;

    public QName getType() {
        return null;
    }

    public void handleOutbound(Message message, SOAPMessage context) {
    }

    public void handleInbound(SOAPMessage context, Message message) {
        message.getWorkContext().setHeader(TEST_HANDLER_HEADER, "bugaga");

    }

}
