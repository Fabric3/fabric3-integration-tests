package org.fabric3.binding.jms.test.activation;

import junit.framework.TestCase;
import org.oasisopen.sca.annotation.Reference;

import org.fabric3.binding.jms.test.primitives.PrimitiveService;

/**
 * @version $Rev$ $Date$
 */
public class TestActivationClient extends TestCase {

    @Reference
    protected PrimitiveService activationService;

    public void testActivationSpec() {
        assertEquals(1, activationService.testInt(1));
    }

}