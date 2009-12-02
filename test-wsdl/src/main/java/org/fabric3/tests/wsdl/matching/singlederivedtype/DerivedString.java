package org.fabric3.tests.wsdl.matching.singlederivedtype;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DerivedString.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="DerivedString">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Yin"/>
 *     &lt;enumeration value="Yang"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "DerivedString", namespace = "urn:helloworld:sdt")
@XmlEnum
public enum DerivedString {

    @XmlEnumValue("Yin")
    YIN("Yin"),
    @XmlEnumValue("Yang")
    YANG("Yang");
    private final String value;

    DerivedString(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static DerivedString fromValue(String v) {
        for (DerivedString c: DerivedString.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
