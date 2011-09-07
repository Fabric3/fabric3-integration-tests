package org.fabric3.tests.drools;

import junit.framework.TestCase;
import org.oasisopen.sca.annotation.Reference;

import org.fabric3.tests.drools.message.HelloMessage;
import org.fabric3.tests.drools.message.HelloService;

/**
 * @version $Rev$ $Date$
 */
public class HelloWorldClient extends TestCase {

    @Reference
    protected HelloService helloService;

    public void testInvoke() throws Exception {
        HelloMessage message = new HelloMessage();
        message.setMessage("Hello from client");
        message = helloService.hello(message);
        assertEquals("Goodbye cruel world", message.getMessage());
    }
}
