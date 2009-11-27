package org.fabric3.tests.binding.metro.weather;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;


@WebService(name = "WeatherPortType", portName = "WeatherPort", targetNamespace = "urn:weather")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface WeatherPortType {

    @WebMethod
    @WebResult(name = "weatherResponse", targetNamespace = "urn:weather", partName = "weatherResponse")
    public WeatherResponse getWeather(@WebParam(name = "weatherRequest", targetNamespace = "urn:weather", partName = "weatherRequest")
    WeatherRequest weatherRequest) throws WeatherFaultException;

}
