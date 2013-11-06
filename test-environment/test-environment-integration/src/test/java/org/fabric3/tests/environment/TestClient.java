package org.fabric3.tests.environment;

import junit.framework.TestCase;
import org.oasisopen.sca.annotation.Reference;

/**
 *
 */
public class TestClient extends TestCase {
    @Reference
    protected TestService service;

    @Reference
    protected TestService systemConfigService;

    @Reference
    protected TestScannedService scannedService;

    public void testTemplateInComposite() {
        assertEquals("message", service.invoke("message"));
    }

    public void testTemplateInSystemConfig() {
        assertEquals("message", systemConfigService.invoke("message"));
    }

    public void testScannedTemplate() {
        assertEquals("message", scannedService.invoke("message"));
    }

}
