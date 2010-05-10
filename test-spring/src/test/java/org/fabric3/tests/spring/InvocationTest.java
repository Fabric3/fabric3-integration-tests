package org.fabric3.tests.spring;

import junit.framework.TestCase;
import org.osoa.sca.annotations.Reference;

/**
 * @version $Rev$ $Date$
 */
public class InvocationTest extends TestCase {

    @Reference
    protected TestService service;

    @Reference
    protected TestService nonTypedService;

    public void testInvoke() throws Exception {
        assertEquals("test", service.invoke("test"));
    }

    public void testUnCheckedExceptionInvoke() throws Exception {
        try {
            service.invokeUnCheckedException();
            fail();
        } catch (TestRuntimeException e) {
            // expected
        }
    }

    public void testCheckedExceptionInvoke() throws Exception {
        try {
            service.invokeCheckedException();
            fail();
        } catch (TestException e) {
            // expected
        }
    }

    public void testNonTypedService() throws Exception {
         assertEquals("test", nonTypedService.invoke("test"));
    }

}
