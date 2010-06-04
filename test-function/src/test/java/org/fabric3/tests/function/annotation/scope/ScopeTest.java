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
package org.fabric3.tests.function.annotation.scope;

import junit.framework.TestCase;
import org.oasisopen.sca.annotation.Reference;

public class ScopeTest extends TestCase {

    @Reference
    protected ConversationalService annotatedConversationalService;
    @Reference
    protected ConversationalService conversationalService;

    @Reference
    protected StatelessService annotatedStatelessService;
    @Reference
    protected StatelessService statelessService;

    @Reference
    protected CompositeService compositeServiceOne;
    @Reference
    protected CompositeService compositeServiceTwo;

    @Reference
    protected CompositeService annotatedCompositeServiceOne;
    @Reference
    protected CompositeService annotatedCompositeServiceTwo;

    @Reference
    protected DomainScopedService domainService;

    public void testAnnotatedCompositeScope() throws Exception {
        assertEquals(0, annotatedCompositeServiceOne.getValue());
        assertEquals(0, annotatedCompositeServiceTwo.getValue());

        annotatedCompositeServiceOne.incrementValue();
        assertEquals(1, annotatedCompositeServiceOne.getValue());
        assertEquals(1, annotatedCompositeServiceTwo.getValue());

        annotatedCompositeServiceOne.incrementValue();
        assertEquals(2, annotatedCompositeServiceOne.getValue());
        assertEquals(2, annotatedCompositeServiceTwo.getValue());
    }

    public void testCompositeScope() throws Exception {
        assertEquals(0, compositeServiceOne.getValue());
        assertEquals(0, compositeServiceTwo.getValue());

        compositeServiceOne.incrementValue();
        assertEquals(1, compositeServiceOne.getValue());
        assertEquals(1, compositeServiceTwo.getValue());

        compositeServiceOne.incrementValue();
        assertEquals(2, compositeServiceOne.getValue());
        assertEquals(2, compositeServiceTwo.getValue());
    }

    public void testAnnotatedStatelessScope() throws Exception {
        assertEquals(0, annotatedStatelessService.getValue());

        annotatedStatelessService.incrementValue();
        assertEquals(0, annotatedStatelessService.getValue());

        annotatedStatelessService.incrementValue();
        assertEquals(0, annotatedStatelessService.getValue());
    }

    public void testStatelessScope() throws Exception {
        assertEquals(0, statelessService.getValue());

        statelessService.incrementValue();
        assertEquals(0, statelessService.getValue());

        statelessService.incrementValue();
        assertEquals(0, statelessService.getValue());
    }

    public void testAnnotatedConversationalScope() throws Exception {
        assertEquals(0, annotatedConversationalService.getValue());

        annotatedConversationalService.incrementValue();
        assertEquals(1, annotatedConversationalService.getValue());

        annotatedConversationalService.incrementValue();
        assertEquals(2, annotatedConversationalService.getValue());
    }

    public void testConversationalScope() throws Exception {
        assertEquals(0, conversationalService.getValue());

        conversationalService.incrementValue();
        assertEquals(1, conversationalService.getValue());

        conversationalService.incrementValue();
        assertEquals(2, conversationalService.getValue());
    }

    public void testDomainScope() throws Exception {
        assertEquals(0, domainService.getValue());

        domainService.incrementValue();
        assertEquals(1, domainService.getValue());
    }
}
