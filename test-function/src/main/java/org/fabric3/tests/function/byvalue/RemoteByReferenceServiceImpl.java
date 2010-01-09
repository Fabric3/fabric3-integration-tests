package org.fabric3.tests.function.byvalue;

import java.util.List;

import org.oasisopen.sca.annotation.AllowsPassByReference;

/**
 * @version $Rev$ $Date$
 */
public class RemoteByReferenceServiceImpl implements RemoteService {

    @AllowsPassByReference
    public List<String> invoke(List<String> list) {
        list.remove(0);
        return list;
    }

}
