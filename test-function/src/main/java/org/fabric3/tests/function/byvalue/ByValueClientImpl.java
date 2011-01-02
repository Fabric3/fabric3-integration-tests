package org.fabric3.tests.function.byvalue;

import java.util.List;

import org.oasisopen.sca.annotation.AllowsPassByReference;
import org.oasisopen.sca.annotation.Reference;

/**
 * @version $Rev$ $Date$
 */
public class ByValueClientImpl implements ByValueClient {

    @Reference
    protected RemoteService byValueService;

    @Reference
    @AllowsPassByReference
    protected RemoteService byReferenceService;

    public List<String> invoke(List<String> list) {
        List<String> result = byValueService.invoke(list);
        if (list.isEmpty()) {
            throw new AssertionError("Pass-by-value not enforced - List should be unmodified");
        }
        return result;
    }

    public List<String> invokeByReference(List<String> list) {
        List<String> result = byReferenceService.invoke(list);
        if (!list.isEmpty()) {
            throw new AssertionError("Pass-by-reference not enforced - List should be empty");
        }
        return result;
    }
}
