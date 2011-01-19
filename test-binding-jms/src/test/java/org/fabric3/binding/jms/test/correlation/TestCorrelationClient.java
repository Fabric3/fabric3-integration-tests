package org.fabric3.binding.jms.test.correlation;

import junit.framework.TestCase;
import org.oasisopen.sca.annotation.Reference;

import org.fabric3.binding.jms.test.primitives.PrimitiveService;

/**
 * @version $Rev$ $Date$
 */
public class TestCorrelationClient extends TestCase {

    @Reference
    protected PrimitiveService correlationIdService;

    @Reference
    protected PrimitiveService messageIdService;

    @Reference
    protected PrimitiveService noCorrelationService;


    public void testCorrelationId() {
        assertEquals(1, correlationIdService.testInt(1));
    }

    public void testMessageId() {
        assertEquals(1, messageIdService.testInt(1));
    }

    public void testNoCorrelationId() {
        assertEquals(1, noCorrelationService.testInt(1));
    }

}