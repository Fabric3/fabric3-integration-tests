package org.fabric3.binding.zeromq.test.service;

import junit.framework.TestCase;
import org.oasisopen.sca.annotation.Reference;

/**
 *
 */
public class TestServiceClient extends TestCase {

    @Reference
    protected TestService testService;

    @Reference
    protected ScannedServiceClient scannedService;

    public void testInvoke() throws Exception {
        assertEquals("test", testService.invoke("test"));
    }

    public void testScannedService() throws Exception {
        assertEquals("test", scannedService.message("test"));
    }
}