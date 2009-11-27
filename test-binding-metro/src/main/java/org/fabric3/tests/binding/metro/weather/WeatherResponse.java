package org.fabric3.tests.binding.metro.weather;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for WeatherResponse complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="WeatherResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="forecast" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="temperature" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WeatherResponse", propOrder = {
        "forecast",
        "temperature"
})
public class WeatherResponse {

    @XmlElement(required = true)
    protected String forecast;
    protected double temperature;

    /**
     * Gets the value of the forecast property.
     *
     * @return possible object is {@link String }
     */
    public String getForecast() {
        return forecast;
    }

    /**
     * Sets the value of the forecast property.
     *
     * @param value allowed object is {@link String }
     */
    public void setForecast(String value) {
        this.forecast = value;
    }

    /**
     * Gets the value of the temperature property.
     */
    public double getTemperature() {
        return temperature;
    }

    /**
     * Sets the value of the temperature property.
     */
    public void setTemperature(double value) {
        this.temperature = value;
    }

}
