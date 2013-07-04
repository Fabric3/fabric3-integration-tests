package org.fabric3.tests.spring;

/**
 *
 */
public class SpringServiceImpl implements TestService {
    private String testProperty;

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
        return service.invoke(message);
    }

    public void invokeCheckedException() throws TestException {
        throw new TestException();
    }

    public void invokeUnCheckedException() {
        throw new TestRuntimeException();
    }

}
