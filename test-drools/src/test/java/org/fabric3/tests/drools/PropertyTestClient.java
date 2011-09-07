package org.fabric3.tests.drools;

import junit.framework.TestCase;
import org.oasisopen.sca.annotation.Reference;

import org.fabric3.tests.drools.message.HelloMessage;
import org.fabric3.tests.drools.message.TestMessage;
import org.fabric3.tests.drools.message.TestService;

/**
 * @version $Rev$ $Date$
 */
public class PropertyTestClient extends TestCase {

    @Reference
    protected TestService testService;

    public void testInvoke() throws Exception {
        TestMessage message = new TestMessage();
        message.setMessage("test");
        message = testService.apply(message);
        assertEquals("test value", message.getMessage());
    }
}
