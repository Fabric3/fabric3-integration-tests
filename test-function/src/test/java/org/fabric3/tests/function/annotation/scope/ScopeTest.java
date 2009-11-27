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

    private ConversationalService annotatedConversationalService;
    private ConversationalService conversationalService;

    private StatelessService annotatedStatelessService;
    private StatelessService statelessService;

    private CompositeService compositeServiceOne;
    private CompositeService compositeServiceTwo;

    private CompositeService annotatedCompositeServiceOne;
    private CompositeService annotatedCompositeServiceTwo;


    public void testAnnotatedCompositeScope() throws Exception {
        assertEquals("Unexpected initial value", 0, annotatedCompositeServiceOne.getValue());
        assertEquals("Unexpected initial value", 0, annotatedCompositeServiceTwo.getValue());

        annotatedCompositeServiceOne.incrementValue();
        assertEquals("Unexpected value", 1, annotatedCompositeServiceOne.getValue());
        assertEquals("Unexpected value", 1, annotatedCompositeServiceTwo.getValue());

        annotatedCompositeServiceOne.incrementValue();
        assertEquals("Unexpected value", 2, annotatedCompositeServiceOne.getValue());
        assertEquals("Unexpected value", 2, annotatedCompositeServiceTwo.getValue());
    }

    public void testCompositeScope() throws Exception {
        assertEquals("Unexpected initial value", 0, compositeServiceOne.getValue());
        assertEquals("Unexpected initial value", 0, compositeServiceTwo.getValue());

        compositeServiceOne.incrementValue();
        assertEquals("Unexpected value", 1, compositeServiceOne.getValue());
        assertEquals("Unexpected value", 1, compositeServiceTwo.getValue());

        compositeServiceOne.incrementValue();
        assertEquals("Unexpected value", 2, compositeServiceOne.getValue());
        assertEquals("Unexpected value", 2, compositeServiceTwo.getValue());
    }

    public void testAnnotatedStatelessScope() throws Exception {
        assertEquals("Unexpected initial value", 0, annotatedStatelessService.getValue());

        annotatedStatelessService.incrementValue();
        assertEquals("Unexpected value", 0, annotatedStatelessService.getValue());

        annotatedStatelessService.incrementValue();
        assertEquals("Unexpected value", 0, annotatedStatelessService.getValue());
    }

    public void testStatelessScope() throws Exception {
        assertEquals("Unexpected initial value", 0, statelessService.getValue());

        statelessService.incrementValue();
        assertEquals("Unexpected value", 0, statelessService.getValue());

        statelessService.incrementValue();
        assertEquals("Unexpected value", 0, statelessService.getValue());
    }

    public void testAnnotatedConversationalScope() throws Exception {
        assertEquals("Unexpected initial value", 0, annotatedConversationalService.getValue());

        annotatedConversationalService.incrementValue();
        assertEquals("Unexpected value", 1, annotatedConversationalService.getValue());

        annotatedConversationalService.incrementValue();
        assertEquals("Unexpected value", 2, annotatedConversationalService.getValue());
    }

    public void testConversationalScope() throws Exception {
        assertEquals("Unexpected initial value", 0, conversationalService.getValue());

        conversationalService.incrementValue();
        assertEquals("Unexpected value", 1, conversationalService.getValue());

        conversationalService.incrementValue();
        assertEquals("Unexpected value", 2, conversationalService.getValue());
    }

    @Reference
    public void setAnnotatedConversationalService(ConversationalService annotatedConversationalService) {
        this.annotatedConversationalService = annotatedConversationalService;
    }

    @Reference
    public void setConversationalService(ConversationalService conversationalService) {
        this.conversationalService = conversationalService;
    }

    @Reference
    public void setAnnotatedStatelessService(StatelessService annotatedStatelessService) {
        this.annotatedStatelessService = annotatedStatelessService;
    }

    @Reference
    public void setStatelessService(StatelessService statelessService) {
        this.statelessService = statelessService;
    }

    @Reference
    public void setCompositeServiceOne(CompositeService compositeServiceOne) {
        this.compositeServiceOne = compositeServiceOne;
    }

    @Reference
    public void setCompositeServiceTwo(CompositeService compositeServiceTwo) {
        this.compositeServiceTwo = compositeServiceTwo;
    }

    @Reference
    public void setAnnotatedCompositeServiceOne(CompositeService annotatedCompositeServiceOne) {
        this.annotatedCompositeServiceOne = annotatedCompositeServiceOne;
    }

    @Reference
    public void setAnnotatedCompositeServiceTwo(CompositeService annotatedCompositeServiceTwo) {
        this.annotatedCompositeServiceTwo = annotatedCompositeServiceTwo;
    }


}
