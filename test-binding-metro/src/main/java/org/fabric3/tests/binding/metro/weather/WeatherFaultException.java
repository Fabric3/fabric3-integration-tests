package org.fabric3.tests.binding.metro.weather;

import javax.xml.ws.WebFault;


@WebFault(name = "weatherFault", targetNamespace = "urn:weather")
public class WeatherFaultException extends Exception {
    private static final long serialVersionUID = 4149042745728181426L;

    private WeatherFault faultInfo;

    public WeatherFaultException(String message, WeatherFault faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    public WeatherFaultException(String message, WeatherFault faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    public WeatherFault getFaultInfo() {
        return faultInfo;
    }

}
