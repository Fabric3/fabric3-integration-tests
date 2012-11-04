package org.fabric3.tests.binding.handler;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPMessage;

import org.oasisopen.sca.Constants;
import org.oasisopen.sca.annotation.Destroy;
import org.oasisopen.sca.annotation.EagerInit;
import org.oasisopen.sca.annotation.Init;
import org.oasisopen.sca.annotation.Reference;

import org.fabric3.spi.binding.handler.BindingHandler;
import org.fabric3.spi.binding.handler.BindingHandlerRegistry;
import org.fabric3.spi.invocation.Message;

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
