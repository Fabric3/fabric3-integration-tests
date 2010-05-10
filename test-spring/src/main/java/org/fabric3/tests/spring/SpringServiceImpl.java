package org.fabric3.tests.spring;

/**
 * @version $Rev$ $Date$
 */
public class SpringServiceImpl implements TestService {

    private TestService service;

    public TestService getService() {
        return service;
    }

    public void setService(TestService service) {
        this.service = service;
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
