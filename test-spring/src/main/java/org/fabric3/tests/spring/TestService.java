package org.fabric3.tests.spring;

/**
 * @version $Rev$ $Date$
 */
public interface TestService {

    String invoke(String message);

    void invokeCheckedException() throws TestException;

    void invokeUnCheckedException();

}