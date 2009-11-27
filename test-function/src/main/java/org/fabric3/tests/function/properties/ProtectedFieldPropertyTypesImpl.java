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
public class ProtectedFieldPropertyTypesImpl implements PropertyTypes {

    @Property
    protected Foo foo;

    @Property
    protected boolean booleanPrimitive;
    @Property
    protected byte bytePrimitive;
    @Property
    protected short shortPrimitive;
    @Property
    protected int intPrimitive;
    @Property
    protected long longPrimitive;
    @Property
    protected float floatPrimitive;
    @Property
    protected double doublePrimitive;

    @Property
    protected Boolean booleanValue;
    @Property
    protected Byte byteValue;
    @Property
    protected Short shortValue;
    @Property
    protected Integer integerValue;
    @Property
    protected Long longValue;
    @Property
    protected Float floatValue;
    @Property
    protected Double doubleValue;
    @Property
    protected Class<?> classValue;

    @Property
    protected String string;
    @Property
    protected URI uriValue;
    @Property
    protected URL urlValue;
    @Property
    protected Date dateValue;
    @Property
    protected Calendar calendarValue;

    @Property(required = false)
    protected int[] intArray;
    @Property
    protected Map<String, String> mapValue;
    @Property
    protected Properties propertiesValue;
    @Property
    protected List<String> listValue;
    @Property
    protected Map<QName, Class<?>> mapOfQNameToClassValue;

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