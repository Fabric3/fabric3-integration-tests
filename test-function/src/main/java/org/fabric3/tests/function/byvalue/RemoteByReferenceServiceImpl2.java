package org.fabric3.tests.function.byvalue;

import java.util.List;

import org.oasisopen.sca.annotation.AllowsPassByReference;

/**
 * Tests use of @AllowsPassByReference on the implmentation class
 * @version $Rev$ $Date$
 */
@AllowsPassByReference
public class RemoteByReferenceServiceImpl2 implements RemoteService {

    public List<String> invoke(List<String> list) {
        list.remove(0);
        return list;
    }

}