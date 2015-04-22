package org.fabric3.tests.binding.handler;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPMessage;

import org.fabric3.spi.container.binding.BindingHandler;
import org.fabric3.spi.container.binding.BindingHandlerRegistry;
import org.fabric3.spi.container.invocation.Message;
import org.oasisopen.sca.Constants;
import org.oasisopen.sca.annotation.Destroy;
import org.oasisopen.sca.annotation.EagerInit;
import org.oasisopen.sca.annotation.Init;
import org.oasisopen.sca.annotation.Reference;

/**
 *
 */
@EagerInit
public class SOAPHandler implements BindingHandler<SOAPMessage> {
    private static final QName BINDING = new QName(Constants.SCA_NS, "binding.ws");

    private BindingHandlerRegistry registry;

    public SOAPHandler(@Reference BindingHandlerRegistry registry) {
        this.registry = registry;
    }

    @Init
    public void init() {
        registry.register(this);
    }

    @Destroy
    public void destroy() {
        registry.unregister(this);
    }

    public QName getType() {
        return BINDING;
    }

    public void handleOutbound(Message message, SOAPMessage context) {

    }

    public void handleInbound(SOAPMessage soapMessage, Message message) {

    }
}
