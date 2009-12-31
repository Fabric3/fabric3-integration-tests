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
package org.fabric3.tests.function.properties;

import java.util.List;
import java.util.Map;
import java.util.Set;

import junit.framework.TestCase;
import org.oasisopen.sca.annotation.Property;

/**
 * @version $Rev$ $Date$
 */
public class MultiValuePropertyTest extends TestCase {
    @Property
    protected String[] arrayType;

    @Property
    protected int[] intArrayType;

    @Property
    protected Set<String> setType;

    @Property
    protected List<Integer> listType;

    @Property
    protected Foo[] complexArrayType;

    @Property
    protected List<Foo> complexListType;

    @Property
    protected Set<Foo> complexSetType;

    @Property
    protected Map<Foo, Foo> complexMapType;

    public void testArrayType() {
        assertEquals(2, arrayType.length);
    }

    public void testSetType() {
        assertEquals(2, setType.size());
    }

    public void testListType() {
        assertEquals(2, listType.size());
        int actual = listType.get(0);
        assertEquals(1, actual);
    }

    public void testIntArrayType() {
        assertEquals(2, intArrayType.length);
        int actual = intArrayType[0];
        assertEquals(1, actual);
    }

    public void testComplexArrayType() {
        assertEquals(2, complexArrayType.length);
        Foo foo1 = complexArrayType[0];
        assertEquals("Bar", foo1.bar);
        Foo foo2 = complexArrayType[1];
        assertEquals("Baz", foo2.bar);
    }

    public void testComplexListType() {
        assertEquals(2, complexListType.size());
        Foo foo1 = complexListType.get(0);
        assertEquals("Bar", foo1.bar);
        Foo foo2 = complexListType.get(1);
        assertEquals("Baz", foo2.bar);
    }

    public void testComplexSetType() {
        assertEquals(2, complexSetType.size());
        Foo fooBar = new Foo("Bar");
        Foo fooBaz = new Foo("Baz");
        assertTrue(complexSetType.contains(fooBar));
        assertTrue(complexSetType.contains(fooBaz));
    }

    public void testComplexMapType() {
        assertEquals(2, complexMapType.size());
        Foo fooBar = new Foo("Bar");
        Foo fooBaz = new Foo("Baz");
        assertEquals(fooBar, complexMapType.get(fooBar));
        assertEquals(fooBaz, complexMapType.get(fooBaz));
    }

}