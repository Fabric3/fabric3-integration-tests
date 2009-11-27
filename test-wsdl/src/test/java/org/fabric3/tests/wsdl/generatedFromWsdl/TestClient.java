package org.fabric3.tests.wsdl.generatedFromWsdl;

import junit.framework.TestCase;
import org.oasisopen.sca.annotation.Reference;

/**
 * @version $Rev$ $Date$
 */
@SuppressWarnings({"JavaDoc"})
public class TestClient extends TestCase {

    @Reference
    protected WeatherPortType service;

    /**
     * Verifies an invocation from Java classes generated from WSDL using the JDK wsimport utility to a WSDL-based service.
     *
     */
    public void testInvoke() throws Exception {
        WeatherRequest request = new WeatherRequest();
        request.setCity("Rome");
        WeatherResponse response = service.getWeather(request);
        assertEquals("Hot", response.getForecast());
    }
}
