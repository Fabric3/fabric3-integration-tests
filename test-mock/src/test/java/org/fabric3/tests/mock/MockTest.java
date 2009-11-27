/*
* Fabric3
* Copyright (c) 2009 Metaform Systems
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
package org.fabric3.tests.mock;

import junit.framework.TestCase;
import org.easymock.IMocksControl;
import org.oasisopen.sca.annotation.Reference;

/**
 * @version $Rev$ $Date$
 */
public class MockTest extends TestCase {

    private static final String ARG1 = "VALUE1";
    private static final String ARG2 = "VALUE2";
    private static final Long ARG3 = 1L;

    private OverloadedService mockedOverloadedService;

    private MockService1 mockService1;
    private MockService2 mockService2;
    private UserComponent userComponent;
    private IMocksControl control;

    @Reference
    public void setControl(IMocksControl control) {
        this.control = control;
    }

    @Reference
    public void setMockService1(MockService1 mockService1) {
        this.mockService1 = mockService1;
    }

    @Reference
    public void setMockService2(MockService2 mockService2) {
        this.mockService2 = mockService2;
    }

    @Reference
    public void setUserComponent(UserComponent userComponent) {
        this.userComponent = userComponent;
    }

    @Reference
    public void setOverloadedService(OverloadedService mockedOverloadedService) {
        this.mockedOverloadedService = mockedOverloadedService;
    }

    public void testMock() {

        control.reset();

        mockService1.doMock1("test");
        mockService2.doMock2(1);
        mockService2.doMock0(1);

        control.replay();

        userComponent.userMethod();

        control.verify();

    }

    public void testNoMock() {

        control.reset();

        mockService1.doMock1("test");
        mockService2.doMock2(1);

        control.replay();

        // fail after the try block as we don't want to catch the AssertionError it would throw
        boolean fail = true;
        try {
            control.verify();
        } catch (AssertionError e) {
            fail = false;
        }
        if (fail) {
            fail("Expected an error");
        }
    }

    public void testUsingControlToCreateMock() {
        control.reset();
        MockService0 mock = control.createMock(MockService0.class);
        mock.doMock0(1);
        control.replay();
        mock.doMock0(1);
        control.verify();
    }

    public void testMockingOverloadedInvocation() throws Exception {

        control.reset();

        mockedOverloadedService.doWork(ARG1, ARG2);
        mockedOverloadedService.doWork(ARG1, ARG2, ARG3);
        control.replay();

        mockedOverloadedService.doWork(ARG1, ARG2);
        mockedOverloadedService.doWork(ARG1, ARG2, ARG3);
        control.verify();
    }

}
