
package org.fabric3.tests.binding.metro.helloworld;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the helloworld package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _HelloFault_QNAME = new QName("urn:helloworld", "helloFault");
    private final static QName _Response_QNAME = new QName("urn:helloworld", "response");
    private final static QName _Request_QNAME = new QName("urn:helloworld", "request");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: helloworld
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link HelloFault }
     * 
     */
    public HelloFault createHelloFault() {
        return new HelloFault();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HelloFault }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:helloworld", name = "helloFault")
    public JAXBElement<HelloFault> createHelloFault(HelloFault value) {
        return new JAXBElement<HelloFault>(_HelloFault_QNAME, HelloFault.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:helloworld", name = "response")
    public JAXBElement<String> createResponse(String value) {
        return new JAXBElement<String>(_Response_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:helloworld", name = "request")
    public JAXBElement<String> createRequest(String value) {
        return new JAXBElement<String>(_Request_QNAME, String.class, null, value);
    }

}
