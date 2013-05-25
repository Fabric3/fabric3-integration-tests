package org.fabric3.binding.zeromq.test.service;

import junit.framework.TestCase;
import org.oasisopen.sca.annotation.Reference;

/**
 *
 */
public class TestOneWayServiceClient extends TestCase {

    @Reference
    protected TestOneWayService testService;

    @Reference
    protected ServiceLatch serviceLatch;

    public void testInvoke() throws Exception {
        testService.invoke("test");
        serviceLatch.await();
    }
}