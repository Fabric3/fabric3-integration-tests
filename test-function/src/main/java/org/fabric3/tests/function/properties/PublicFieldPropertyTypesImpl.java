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
package org.fabric3.tests.function.properties;

import java.net.URI;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.xml.namespace.QName;

import org.oasisopen.sca.annotation.Property;

/**
 *
 */
public class PublicFieldPropertyTypesImpl implements PropertyTypes {

    @Property
    public Foo foo;
    @Property
    public boolean booleanPrimitive;
    @Property
    public byte bytePrimitive;
    @Property
    public short shortPrimitive;
    @Property
    public int intPrimitive;
    @Property
    public long longPrimitive;
    @Property
    public float floatPrimitive;
    @Property
    public double doublePrimitive;

    @Property
    public Boolean booleanValue;
    @Property
    public Byte byteValue;
    @Property
    public Short shortValue;
    @Property
    public Integer integerValue;
    @Property
    public Long longValue;
    @Property
    public Float floatValue;
    @Property
    public Double doubleValue;
    @Property
    public Class<?> classValue;

    @Property
    public String string;
    @Property
    public URI uriValue;
    @Property
    public URL urlValue;
    @Property
    public Date dateValue;
    @Property
    public Calendar calendarValue;

    @Property(required = false)
    public int[] intArray;
    @Property
    public Map<String, String> mapValue;
    @Property
    public Properties propertiesValue;
    @Property
    public List<String> listValue;
    @Property
    public Map<QName, Class<?>> mapOfQNameToClassValue;

    public Foo getFoo() {
        return foo;
    }

    public boolean getBooleanPrimitive() {
        return booleanPrimitive;
    }

    public byte getBytePrimitive() {
        return bytePrimitive;
    }

    public short getShortPrimitive() {
        return shortPrimitive;
    }

    public int getIntPrimitive() {
        return intPrimitive;
    }

    public long getLongPrimitive() {
        return longPrimitive;
    }

    public float getFloatPrimitive() {
        return floatPrimitive;
    }

    public double getDoublePrimitive() {
        return doublePrimitive;
    }

    public Boolean getBooleanValue() {
        return booleanValue;
    }

    public Byte getByteValue() {
        return byteValue;
    }

    public Short getShortValue() {
        return shortValue;
    }

    public Integer getIntegerValue() {
        return integerValue;
    }

    public Long getLongValue() {
        return longValue;
    }

    public Float getFloatValue() {
        return floatValue;
    }

    public Double getDoubleValue() {
        return doubleValue;
    }

    public Class<?> getClassValue() {
        return classValue;
    }

    public String getString() {
        return string;
    }

    public URI getUriValue() {
        return uriValue;
    }

    public URL getUrlValue() {
        return urlValue;
    }

    public Date getDateValue() {
        return dateValue;
    }

    public Calendar getCalendarValue() {
        return calendarValue;
    }

    public int[] getIntArray() {
        return intArray;
    }

    public Map<String, String> getMapValue() {
        return mapValue;
    }

    public Properties getPropertiesValue() {
        return propertiesValue;
    }

    public List<String> getListValue() {
        return listValue;
    }

    public Map<QName, Class<?>> getMapOfQNameToClassValue() {
        return mapOfQNameToClassValue;
    }
}
