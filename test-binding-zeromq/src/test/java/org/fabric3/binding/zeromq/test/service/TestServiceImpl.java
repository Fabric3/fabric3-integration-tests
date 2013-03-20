package org.fabric3.binding.zeromq.test.service;

/**
 *
 */
public class TestServiceImpl implements TestService {
    public String invoke(String message) {
        return message;
    }
}
