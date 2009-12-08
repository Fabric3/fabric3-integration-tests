package org.fabric3.anttest;

import junit.framework.TestCase;
import org.oasisopen.sca.annotation.Reference;

/**
 * @version $Rev$ $Date$
 */
public class JUnitTest extends TestCase {

    @Reference
    protected Service service;

    public void testInvoke() throws Exception {
       assertEquals("hello", service.echo("hello"));
    }
}
