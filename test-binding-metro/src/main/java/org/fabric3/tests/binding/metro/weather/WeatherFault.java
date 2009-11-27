package org.fabric3.tests.binding.metro.weather;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for WeatherFault complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="WeatherFault">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="code" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="detail" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WeatherFault", propOrder = {
        "code",
        "detail"
})
public class WeatherFault {

    @XmlElement(required = true)
    protected String code;
    @XmlElement(required = true)
    protected String detail;

    /**
     * Gets the value of the code property.
     *
     * @return possible object is {@link String }
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets the value of the code property.
     *
     * @param value allowed object is {@link String }
     */
    public void setCode(String value) {
        this.code = value;
    }

    /**
     * Gets the value of the detail property.
     *
     * @return possible object is {@link String }
     */
    public String getDetail() {
        return detail;
    }

    /**
     * Sets the value of the detail property.
     *
     * @param value allowed object is {@link String }
     */
    public void setDetail(String value) {
        this.detail = value;
    }

}
