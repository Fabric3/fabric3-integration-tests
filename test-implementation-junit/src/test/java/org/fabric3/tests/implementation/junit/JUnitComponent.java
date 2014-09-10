/*
 * Fabric3
 * Copyright (c) 2009-2014 Metaform Systems
 *
 * Fabric3 is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of
 * the License, or (at your option) any later version, with the
 * following exception:
 *
 * Linking this software statically or dynamically with other
 * modules is making a combined work based on this software.
 * Thus, the terms and conditions of the GNU General Public
 * License cover the whole combination.
 *
 * As a special exception, the copyright holders of this software
 * give you permission to link this software with independent
 * modules to produce an executable, regardless of the license
 * terms of these independent modules, and to copy and distribute
 * the resulting executable under terms of your choice, provided
 * that you also meet, for each linked independent module, the
 * terms and conditions of the license of that module. An
 * independent module is a module which is not derived from or
 * based on this software. If you modify this software, you may
 * extend this exception to your version of the software, but
 * you are not obligated to do so. If you do not wish to do so,
 * delete this exception statement from your version.
 *
 * Fabric3 is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty
 * of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 *
 * You should have received a copy of the
 * GNU General Public License along with Fabric3.
 * If not, see <http://www.gnu.org/licenses/>.
*/
package org.fabric3.tests.implementation.junit;

import java.io.IOException;

import org.fabric3.api.implementation.junit.Fabric3Runner;
import org.fabric3.tests.implementation.TestService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.oasisopen.sca.annotation.Reference;

/**
 *
 */
@RunWith(Fabric3Runner.class)
public class JUnitComponent {
    private boolean invoked;

    @Reference
    protected TestService service;

    @Before
    public void before() {
        invoked = true;
    }

    @After
    public void after() {

    }

    @Test
    public void testBefore() {
        Assert.assertTrue(invoked);
    }

    @Test(expected = IOException.class)
    public void handleExpectedException() throws Exception {
        throw new IOException("This exception should be ignored");
    }

    @Test
    public void invokeService() {
        service.invoke();
    }

}
