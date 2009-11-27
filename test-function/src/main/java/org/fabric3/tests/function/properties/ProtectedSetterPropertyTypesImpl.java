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
public class ProtectedSetterPropertyTypesImpl implements PropertyTypes {

    private Foo foo;

    private boolean booleanPrimitive;
    private byte bytePrimitive;
    private short shortPrimitive;
    private int intPrimitive;
    private long longPrimitive;
    private float floatPrimitive;
    private double doublePrimitive;

    private Boolean booleanValue;
    private Byte byteValue;
    private Short shortValue;
    private Integer integerValue;
    private Long longValue;
    private Float floatValue;
    private Double doubleValue;
    private Class<?> classValue;

    private String string;
    private URI uriValue;
    private URL urlValue;
    private Date dateValue;
    private Calendar calendarValue;

    private int[] intArray;
    private Map<String, String> mapValue;
    private Properties propertiesValue;
    private List<String> listValue;
    private Map<QName, Class<?>> mapOfQNameToClassValue;

    public Foo getFoo() {
        return foo;
    }

    @Property
    protected void setFoo(Foo foo) {
        this.foo = foo;
    }

    public boolean getBooleanPrimitive() {
        return booleanPrimitive;
    }

    @Property
    protected void setBooleanPrimitive(boolean booleanPrimitive) {
        this.booleanPrimitive = booleanPrimitive;
    }

    public byte getBytePrimitive() {
        return bytePrimitive;
    }

    @Property
    protected void setBytePrimitive(byte bytePrimitive) {
        this.bytePrimitive = bytePrimitive;
    }

    public short getShortPrimitive() {
        return shortPrimitive;
    }

    @Property
    protected void setShortPrimitive(short shortPrimitive) {
        this.shortPrimitive = shortPrimitive;
    }

    public int getIntPrimitive() {
        return intPrimitive;
    }

    @Property
    protected void setIntPrimitive(int intPrimitive) {
        this.intPrimitive = intPrimitive;
    }

    public long getLongPrimitive() {
        return longPrimitive;
    }

    @Property
    protected void setLongPrimitive(long longPrimitive) {
        this.longPrimitive = longPrimitive;
    }

    public float getFloatPrimitive() {
        return floatPrimitive;
    }

    @Property
    protected void setFloatPrimitive(float floatPrimitive) {
        this.floatPrimitive = floatPrimitive;
    }

    public double getDoublePrimitive() {
        return doublePrimitive;
    }

    @Property
    protected void setDoublePrimitive(double doublePrimitive) {
        this.doublePrimitive = doublePrimitive;
    }

    public Boolean getBooleanValue() {
        return booleanValue;
    }

    @Property
    protected void setBooleanValue(Boolean booleanValue) {
        this.booleanValue = booleanValue;
    }

    public Byte getByteValue() {
        return byteValue;
    }

    @Property
    protected void setByteValue(Byte byteValue) {
        this.byteValue = byteValue;
    }

    public Short getShortValue() {
        return shortValue;
    }

    @Property
    protected void setShortValue(Short shortValue) {
        this.shortValue = shortValue;
    }

    public Integer getIntegerValue() {
        return integerValue;
    }

    @Property
    protected void setIntegerValue(Integer integerValue) {
        this.integerValue = integerValue;
    }

    public Long getLongValue() {
        return longValue;
    }

    @Property
    protected void setLongValue(Long longValue) {
        this.longValue = longValue;
    }

    public Float getFloatValue() {
        return floatValue;
    }

    @Property
    protected void setFloatValue(Float floatValue) {
        this.floatValue = floatValue;
    }

    public Double getDoubleValue() {
        return doubleValue;
    }

    @Property
    protected void setDoubleValue(Double doubleValue) {
        this.doubleValue = doubleValue;
    }

    public Class<?> getClassValue() {
        return classValue;
    }

    @Property
    protected void setClassValue(Class<?> classValue) {
        this.classValue = classValue;
    }

    public String getString() {
        return string;
    }

    @Property
    protected void setString(String string) {
        this.string = string;
    }

    public URI getUriValue() {
        return uriValue;
    }

    @Property
    protected void setUriValue(URI uriValue) {
        this.uriValue = uriValue;
    }

    public URL getUrlValue() {
        return urlValue;
    }

    @Property
    protected void setUrlValue(URL urlValue) {
        this.urlValue = urlValue;
    }

    public Date getDateValue() {
        return dateValue;
    }

    @Property
    protected void setDateValue(Date dateValue) {
        this.dateValue = dateValue;
    }

    public Calendar getCalendarValue() {
        return calendarValue;
    }

    @Property
    protected void setCalendarValue(Calendar calendarValue) {
        this.calendarValue = calendarValue;
    }

    public int[] getIntArray() {
        return intArray;
    }

    @Property(required = false)
    protected void setIntArray(int[] intArray) {
        this.intArray = intArray;
    }

    public Map<String, String> getMapValue() {
        return mapValue;
    }

    @Property
    protected void setMapValue(Map<String, String> mapValue) {
        this.mapValue = mapValue;
    }

    public Properties getPropertiesValue() {
        return propertiesValue;
    }

    @Property
    protected void setPropertiesValue(Properties propertiesValue) {
        this.propertiesValue = propertiesValue;
    }

    @Property
    protected void setListValue(List<String> listValue) {
        this.listValue = listValue;
    }

    public List<String> getListValue() {
        return listValue;
    }

    @Property
    protected void setMapOfQNameToClassValue(Map<QName, Class<?>> mapOfQNameToClassValue) {
        this.mapOfQNameToClassValue = mapOfQNameToClassValue;
    }

    public Map<QName, Class<?>> getMapOfQNameToClassValue() {
        return mapOfQNameToClassValue;
    }
}