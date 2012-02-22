package org.fabric3.tests.binding.handler;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPMessage;

import org.oasisopen.sca.Constants;
import org.oasisopen.sca.annotation.EagerInit;

import org.fabric3.spi.binding.handler.BindingHandler;
import org.fabric3.spi.invocation.Message;

/**
 * @version $Rev$ $Date$
 */
@EagerInit
public class SOAPHandler implements BindingHandler<SOAPMessage> {
    private static final QName BINDING = new QName(Constants.SCA_NS, "binding.ws");

    public QName getType() {
        return BINDING;
    }

    public void handle(SOAPMessage soapMessage, Message message) {

    }
}
