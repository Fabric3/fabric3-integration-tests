package org.fabric3.tests.binding.metro.interaction;

import org.oasisopen.sca.annotation.OneWay;

/**
 *
 */
public interface OneWayService {

    @OneWay
    void invoke();

}
