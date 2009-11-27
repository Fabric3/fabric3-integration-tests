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

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.TimeZone;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import javax.xml.namespace.QName;

import junit.framework.TestCase;
import org.oasisopen.sca.annotation.Reference;

/**
 * @version $Rev$ $Date$
 */
public class PropertyTypeTest extends TestCase {
    @Reference
    public PropertyTypes service;

    public void testFoo() {
        assertEquals("Bar", service.getFoo().bar);
    }

    public void testBoolean() {
        assertEquals(true, service.getBooleanPrimitive());
    }

    public void testByte() {
        assertEquals((byte) 12, service.getBytePrimitive());
    }

    public void testShort() {
        assertEquals((short) 1234, service.getShortPrimitive());
    }

    public void testInteger() {
        assertEquals(12345678, service.getIntPrimitive());
    }

    public void testLong() {
        assertEquals(123451234512345l, service.getLongPrimitive());
    }

    public void testFloat() {
        assertEquals(1.2345f, service.getFloatPrimitive());
    }

    public void testDouble() {
        assertEquals(1.2345e10, service.getDoublePrimitive());
    }

    public void testString() {
        assertEquals("Hello World", service.getString());
    }

    public void testBooleanValue() {
        assertEquals(Boolean.TRUE, service.getBooleanValue());
    }

    public void testByteValue() {
        assertEquals(Byte.valueOf((byte) 12), service.getByteValue());
    }

    public void testShortValue() {
        assertEquals(Short.valueOf((short) 1234), service.getShortValue());
    }

    public void testIntegerValue() {
        assertEquals(Integer.valueOf(12345678), service.getIntegerValue());
    }

    public void testLongValue() {
        assertEquals(Long.valueOf(123451234512345l), service.getLongValue());
    }

    public void testFloatValue() {
        assertEquals(1.2345f, service.getFloatValue());
    }

    public void testDoubleValue() {
        assertEquals(1.2345e10, service.getDoubleValue());
    }

    public void testClassValue() {
        assertEquals(PropertyTypes.class, service.getClassValue());
    }

    public void testURI() {
        assertEquals(URI.create("urn:fabric3:test"), service.getUriValue());
    }

    public void testURL() throws MalformedURLException {
        assertEquals(new URL("file://./root"), service.getUrlValue());
    }

    public void testDate() throws ParseException {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyy-MM-d'T'HH:mm:ss'Z'");
        assertEquals(dateFormatter.parse("2007-10-31T01:00:00Z"), service.getDateValue());
    }

    public void testCalendar() {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.clear();
        calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
        calendar.set(2007, Calendar.OCTOBER, 31, 1, 0, 0);

        assertEquals(calendar.getTime(), service.getCalendarValue().getTime());
    }

/*
    public void testIntArray() {
        assertTrue(Arrays.equals(new int[]{1,2,3}, service.getIntArray()));
    }
*/

    public void testMap() {
        Map<String, String> map = service.getMapValue();
        assertEquals("1", map.get("one"));
        assertEquals("2", map.get("two"));
    }

    public void testProperties() {
        Properties properties = service.getPropertiesValue();
        assertEquals("value1", properties.getProperty("prop1"));
        assertEquals("value2", properties.getProperty("prop2"));
    }

    public void testList() {
        List<String> list = service.getListValue();
        assertEquals("value1", list.get(0));
        assertEquals("value2", list.get(1));
    }

    public void testMapOfQNameToClass() {
        Map<QName, Class<?>> map = service.getMapOfQNameToClassValue();
        assertEquals(map.get(new QName("urn:foo", "one")), String.class);
        assertEquals(map.get(new QName("urn:foo", "two")), Date.class);
    }
}
