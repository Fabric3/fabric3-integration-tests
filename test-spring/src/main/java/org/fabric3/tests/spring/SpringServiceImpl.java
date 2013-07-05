package org.fabric3.tests.spring;

import org.fabric3.api.Fabric3RequestContext;
import org.oasisopen.sca.annotation.Context;

/**
 *
 */
public class SpringServiceImpl implements TestService {
    private String testProperty;

    @Context
    Fabric3RequestContext context;

    private TestService service;

    public TestService getService() {
        return service;
    }

    public void setService(TestService service) {
        this.service = service;
    }

    public String getTestProperty() {
        return testProperty;
    }

    public void setTestProperty(String testProperty) {
        this.testProperty = testProperty;
    }

    public String invoke(String message) {
        if (context == null) {
            throw new AssertionError("Context not injected");
        }
        context.getCurrentSubject();

        return service.invoke(message);
    }

    public void invokeCheckedException() throws TestException {
        throw new TestException();
    }

    public void invokeUnCheckedException() {
        throw new TestRuntimeException();
    }

}
