package org.fabric3.tests.binding.metro.interaction;

import org.oasisopen.sca.annotation.OneWay;

/**
 * @version $Rev$ $Date$
 */
public interface OneWayService {

    @OneWay
    void invoke();

}
