package org.fabric3.tests.wsdl.matching.singleelementcomplextype;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the test.wsdl.matching.singleelementcomplextype package. 
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

    private final static QName _SayHelloRequest_QNAME = new QName("urn:helloworld:sect", "sayHelloRequest");
    private final static QName _SayHelloResponse_QNAME = new QName("urn:helloworld:sect", "sayHelloResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: test.wsdl.matching.singleelementcomplextype
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SayHelloRequestType }
     * 
     */
    public SayHelloRequestType createSayHelloRequestType() {
        return new SayHelloRequestType();
    }

    /**
     * Create an instance of {@link SayHelloResponseType }
     * 
     */
    public SayHelloResponseType createSayHelloResponseType() {
        return new SayHelloResponseType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SayHelloRequestType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:helloworld", name = "sayHelloRequest")
    public JAXBElement<SayHelloRequestType> createSayHelloRequest(SayHelloRequestType value) {
        return new JAXBElement<SayHelloRequestType>(_SayHelloRequest_QNAME, SayHelloRequestType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SayHelloResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:helloworld", name = "sayHelloResponse")
    public JAXBElement<SayHelloResponseType> createSayHelloResponse(SayHelloResponseType value) {
        return new JAXBElement<SayHelloResponseType>(_SayHelloResponse_QNAME, SayHelloResponseType.class, null, value);
    }

}
