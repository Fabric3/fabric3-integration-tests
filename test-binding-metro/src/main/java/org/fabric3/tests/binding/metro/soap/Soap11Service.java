package org.fabric3.tests.binding.metro.soap;

import javax.jws.WebService;

@WebService
public interface Soap11Service {

    public String sayHello(String name);

}