package org.fabric3.tests.wsdl.matching.multicomplextype;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;


@WebService(name = "AddNumbersPortType", targetNamespace = "urn:addnumbers:mct")
@SOAPBinding(style = SOAPBinding.Style.RPC)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface MappedAddNumbersPortType {


    @WebMethod (operationName = "addNumbers")
    @WebResult(name = "result", partName = "result")
    public int mappedAddNumbers(
        @WebParam(name = "arg1", partName = "arg1")
        AddNumbersType arg1,
        @WebParam(name = "arg2", partName = "arg2")
        AddNumbersType arg2);

}