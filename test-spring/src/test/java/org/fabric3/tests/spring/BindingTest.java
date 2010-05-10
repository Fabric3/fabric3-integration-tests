package org.fabric3.tests.spring;

import junit.framework.TestCase;
import org.osoa.sca.annotations.Reference;

/**
 * @version $Rev$ $Date$
 */
public class BindingTest extends TestCase {

    @Reference
    protected TestService service;

    public void testInvoke() throws Exception {
        assertEquals("test", service.invoke("test"));
    }
}