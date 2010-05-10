package org.fabric3.tests.spring;

/**
 * @version $Rev$ $Date$
 */
public class SCAServiceImpl implements TestService {

    public String invoke(String message) {
        return message;
    }

    public void invokeCheckedException() throws TestException {
        throw new TestException();
    }

    public void invokeUnCheckedException() {
        throw new TestRuntimeException();
    }

}