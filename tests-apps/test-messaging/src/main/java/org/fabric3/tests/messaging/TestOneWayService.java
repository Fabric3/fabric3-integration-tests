package org.fabric3.tests.messaging;

import org.oasisopen.sca.annotation.OneWay;

/**
 *
 */
public interface TestOneWayService {

    @OneWay
    void invoke(Message message);
    
}
