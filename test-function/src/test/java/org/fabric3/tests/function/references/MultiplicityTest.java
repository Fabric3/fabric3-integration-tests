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
package org.fabric3.tests.function.references;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import junit.framework.TestCase;
import org.oasisopen.sca.annotation.Reference;

import org.fabric3.tests.function.common.IdentityService;

/**
 * @version $Rev$ $Date$
 */
public class MultiplicityTest extends TestCase {

    @Reference
    public List<IdentityService> listField;

    private List<IdentityService> listSetter;

    private List<IdentityService> listCdi1;

    private List<IdentityService> listCdi2;

    @Reference
    public void setListSetter(List<IdentityService> listSetter) {
        this.listSetter = listSetter;
    }

    public MultiplicityTest(@Reference(name = "listCdi1") List<IdentityService> listCdi1,
                            @Reference(name = "listCdi2") List<IdentityService> listCdi2) {
        this.listCdi1 = listCdi1;
        this.listCdi2 = listCdi2;
    }

    public void testListCdi1() {
        checkContent(listCdi1);
    }

    public void testListCdi2() {
        checkContent(listCdi2);
    }

    public void testListSetter() {
        checkContent(listSetter);
    }

    public void testListField() {
        checkContent(listField);
    }

    private static final Set<String> IDS;

    static {
        IDS = new HashSet<String>(3);
        IDS.add("map.one");
        IDS.add("map.two");
        IDS.add("map.three");
    }

    private void checkContent(Collection<IdentityService> refs) {
        assertEquals(3, refs.size());
        for (IdentityService ref : refs) {
            // Sets dont guarantee insert order
            assertTrue(IDS.contains(ref.getIdentity()));
        }
    }
}
