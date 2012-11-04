package org.fabric3.tests.spring;

/**
 *
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