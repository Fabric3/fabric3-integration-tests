package org.fabric3.tests.function.byvalue;

import java.util.List;

/**
 *
 */
public class RemoteByValueServiceImpl implements RemoteService {

    public List<String> invoke(List<String> list) {
        list.remove(0);
        return list;
    }

}