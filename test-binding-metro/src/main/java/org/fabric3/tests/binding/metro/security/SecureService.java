package org.fabric3.tests.binding.metro.security;

import javax.jws.WebService;

@WebService
public interface SecureService {

    public String sayHello(String name);

}