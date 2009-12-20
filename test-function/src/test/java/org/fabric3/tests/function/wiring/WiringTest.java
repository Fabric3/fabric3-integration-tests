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
package org.fabric3.tests.function.wiring;

import junit.framework.TestCase;
import org.oasisopen.sca.annotation.Reference;

/**
 * @version $Rev$ $Date$
 */
public class WiringTest extends TestCase {
    private TestService service;

    @Reference
    public void setTestService(TestService service) {
        this.service = service;
    }

    /**
     * Tests a wire that is explicitly targeted with a "target="  on a reference
     */
    public void testTargetedWire() {
        assertNotNull(service.getService());
    }

    /**
     * Tests a wire that is explicitly targeted with a "target=" on a constructor
     */
    public void testTargetedConstructorWire() {
        assertNotNull(service.getConstructorService());
    }

    /**
     * Tests a reference configured on the component without a 'target=' and promoted on the composite:
     * <pre>
     *      <component name="TestComponent">
     *          ...
     *          <reference name="promotedReference"/>
     *      </component>
     * <p/>
     *      <reference name="promotedReference" promote="TestComponent/promotedReference">...
     * <pre>
     */
//    public void testPromotedReferences() {
//        assertNotNull(service.getPromotedReference());
//    }

    /**
     * Tests a reference configured solely via promotion:
     * <pre>
     *      <component name="TestComponent">
     *          ...
     *          <!-- no <reference name="promotedReference" -->
     *      </component>
     * <p/>
     *      <reference name="promotedReference" promote="TestComponent/promotedReference">...
     * <pre>
     */
//    public void testNonConfiguredPromotedReferences() {
//        assertNotNull(service.getNonConfiguredPromotedReference());
//    }

    /**
     * Verifies a reference of multiplicity 0..n does not need to be configured
     */
    public void testOptionalNonSetReference() {
        assertNull(service.getOptionalNonSetReference());
    }
}
