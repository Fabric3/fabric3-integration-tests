package org.fabric3.tests.function.wiring;

import org.fabric3.api.annotation.scope.Composite;
import org.oasisopen.sca.annotation.Service;

/**
 *
 */
@Service({MultipleService1.class, MultipleService2.class})
@Composite
public class MultipleServiceComponent implements MultipleService1, MultipleService2{

    public void test(String msg) {

    }

    public void invoke() {

    }
}
