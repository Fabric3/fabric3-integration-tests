package org.fabric3.tests.messaging;

import org.oasisopen.sca.annotation.OneWay;

/**
 *
 */
public interface TestConsumer {

    @OneWay
    void publish(Message message);
    
}
