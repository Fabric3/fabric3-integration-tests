package org.fabric3.binding.jms.test.primitives;

import javax.jms.Message;
import javax.xml.namespace.QName;

import org.fabric3.spi.binding.handler.BindingHandler;

public class SystemOutTestHandler implements BindingHandler<Message> {

	public QName getType() {
		return null;
	}

	public void handleOutbound(org.fabric3.spi.invocation.Message message,
			Message context) {
		System.out.println("Out >>>> "+ message.getBody());
	}

	public void handleInbound(Message context,
			org.fabric3.spi.invocation.Message message) {
		System.out.println("In >>>> "+ message.getBody());
	}

}
