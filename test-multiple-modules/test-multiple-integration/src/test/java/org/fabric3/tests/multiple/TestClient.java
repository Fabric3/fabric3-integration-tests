package org.fabric3.tests.multiple;

import junit.framework.TestCase;
import org.oasisopen.sca.annotation.Reference;

/**
 *
 */
public class TestClient extends TestCase {
    @Reference
    protected TestService service;

    public void testInvoke() {
        assertEquals("message", service.invoke("message"));
    }
}
