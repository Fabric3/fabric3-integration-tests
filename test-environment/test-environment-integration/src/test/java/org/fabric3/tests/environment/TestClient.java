package org.fabric3.tests.environment;

import junit.framework.TestCase;
import org.osoa.sca.annotations.Reference;

/**
 * @version $Rev$ $Date$
 */
public class TestClient extends TestCase {
    @Reference
    protected TestService service;

    @Reference
    protected TestService systemConfigService;


    public void testTemplateInComposite() {
        assertEquals("message", service.invoke("message"));
    }

    public void testTemplateInSystemConfig() {
        assertEquals("message", systemConfigService.invoke("message"));
    }

}
