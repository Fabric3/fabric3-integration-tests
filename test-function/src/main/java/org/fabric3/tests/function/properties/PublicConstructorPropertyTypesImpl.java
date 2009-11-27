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
 * @version $Rev$ $Date$
 */
public class PublicConstructorPropertyTypesImpl implements PropertyTypes {

    private final Foo foo;
    private final boolean booleanPrimitive;
    private final byte bytePrimitive;
    private final short shortPrimitive;
    private final int intPrimitive;
    private final long longPrimitive;
    private final float floatPrimitive;
    private final double doublePrimitive;

    private final Boolean booleanValue;
    private final Byte byteValue;
    private final Short shortValue;
    private final Integer integerValue;
    private final Long longValue;
    private final Float floatValue;
    private final Double doubleValue;
    private final Class<?> classValue;

    private final String string;
    private final URI uriValue;
    private final URL urlValue;
    private final Date dateValue;
    private final Calendar calendarValue;

    private final int[] intArray;
    private final Map<String, String> mapValue;
    private final Properties propertiesValue;
    private final List<String> listValue;
    private final Map<QName, Class<?>> mapOfQNameToClassValue;

    public PublicConstructorPropertyTypesImpl(@Property(name = "booleanPrimitive") boolean booleanPrimitive,
                                              @Property(name = "bytePrimitive") byte bytePrimitive,
                                              @Property(name = "shortPrimitive") short shortPrimitive,
                                              @Property(name = "intPrimitive") int intPrimitive,
                                              @Property(name = "longPrimitive") long longPrimitive,
                                              @Property(name = "floatPrimitive") float floatPrimitive,
                                              @Property(name = "doublePrimitive") double doublePrimitive,
                                              @Property(name = "booleanValue") Boolean booleanValue,
                                              @Property(name = "byteValue") Byte byteValue,
                                              @Property(name = "shortValue") Short shortValue,
                                              @Property(name = "integerValue") Integer integerValue,
                                              @Property(name = "longValue") Long longValue,
                                              @Property(name = "floatValue") Float floatValue,
                                              @Property(name = "doubleValue") Double doubleValue,
                                              @Property(name = "classValue") Class<?> classValue,
                                              @Property(name = "string") String string,
                                              @Property(name = "uriValue") URI uriValue,
                                              @Property(name = "urlValue") URL urlValue,
                                              @Property(name = "dateValue") Date dateValue,
                                              @Property(name = "calendarValue") Calendar calendarValue,
                                              @Property(name = "intArray", required = false) int[] intArray,
                                              @Property(name = "mapValue") Map<String, String> mapValue,
                                              @Property(name = "propertiesValue") Properties propertiesValue,
                                              @Property(name = "listValue") List<String> listValue,
                                              @Property(name = "mapOfQNameToClassValue") Map<QName, Class<?>> mapOfQNameToClassValue,
                                              @Property(name = "foo") Foo foo) {
        this.booleanPrimitive = booleanPrimitive;
        this.bytePrimitive = bytePrimitive;
        this.shortPrimitive = shortPrimitive;
        this.intPrimitive = intPrimitive;
        this.longPrimitive = longPrimitive;
        this.floatPrimitive = floatPrimitive;
        this.doublePrimitive = doublePrimitive;
        this.booleanValue = booleanValue;
        this.byteValue = byteValue;
        this.shortValue = shortValue;
        this.integerValue = integerValue;
        this.longValue = longValue;
        this.floatValue = floatValue;
        this.doubleValue = doubleValue;
        this.classValue = classValue;
        this.string = string;
        this.uriValue = uriValue;
        this.urlValue = urlValue;
        this.dateValue = dateValue;
        this.calendarValue = calendarValue;
        this.intArray = intArray;
        this.mapValue = mapValue;
        this.propertiesValue = propertiesValue;
        this.listValue = listValue;
        this.mapOfQNameToClassValue = mapOfQNameToClassValue;
        this.foo = foo;
    }

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