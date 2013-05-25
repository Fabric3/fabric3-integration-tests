package org.fabric3.binding.zeromq.test.service;

import org.oasisopen.sca.annotation.OneWay;

/**
 *
 */
public interface TestOneWayService {

    @OneWay
    void invoke(String message);


}
