package org.fabric3.tests.binding.metro.weather;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each Java content interface and Java element interface generated in the weather package. <p>An
 * ObjectFactory allows you to programatically construct new instances of the Java representation for XML content. The Java representation of XML
 * content can consist of schema derived interfaces and classes representing the binding of schema type definitions, element declarations and model
 * groups.  Factory methods for each of these are provided in this class.
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _WeatherRequest_QNAME = new QName("urn:weather", "weatherRequest");
    private final static QName _WeatherFault_QNAME = new QName("urn:weather", "weatherFault");
    private final static QName _WeatherResponse_QNAME = new QName("urn:weather", "weatherResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: weather
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link WeatherRequest }
     */
    public WeatherRequest createWeatherRequest() {
        return new WeatherRequest();
    }

    /**
     * Create an instance of {@link WeatherFault }
     */
    public WeatherFault createWeatherFault() {
        return new WeatherFault();
    }

    /**
     * Create an instance of {@link WeatherResponse }
     */
    public WeatherResponse createWeatherResponse() {
        return new WeatherResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WeatherRequest }{@code >}}
     */
    @XmlElementDecl(namespace = "urn:weather", name = "weatherRequest")
    public JAXBElement<WeatherRequest> createWeatherRequest(WeatherRequest value) {
        return new JAXBElement<WeatherRequest>(_WeatherRequest_QNAME, WeatherRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WeatherFault }{@code >}}
     */
    @XmlElementDecl(namespace = "urn:weather", name = "weatherFault")
    public JAXBElement<WeatherFault> createWeatherFault(WeatherFault value) {
        return new JAXBElement<WeatherFault>(_WeatherFault_QNAME, WeatherFault.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WeatherResponse }{@code >}}
     */
    @XmlElementDecl(namespace = "urn:weather", name = "weatherResponse")
    public JAXBElement<WeatherResponse> createWeatherResponse(WeatherResponse value) {
        return new JAXBElement<WeatherResponse>(_WeatherResponse_QNAME, WeatherResponse.class, null, value);
    }

}
