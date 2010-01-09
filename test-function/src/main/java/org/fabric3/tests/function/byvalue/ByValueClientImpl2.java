package org.fabric3.tests.function.byvalue;

import java.util.List;

import org.oasisopen.sca.annotation.Reference;
import org.oasisopen.sca.annotation.AllowsPassByReference;

/**
 * Tests the use of  @AllowsPassByReference on an implementation class
 * @version $Rev$ $Date$
 */
@AllowsPassByReference
public class ByValueClientImpl2 implements ByValueClient {

    @Reference
    protected RemoteService byReferenceService;

    public List<String> invoke(List<String> list) {
        throw new UnsupportedOperationException();
    }

    public List<String> invokeByReference(List<String> list) {
        List<String> result = byReferenceService.invoke(list);
        if (!list.isEmpty()) {
            throw new AssertionError("Pass-by-reference not enforced - List should be empty");
        }
        return result;
    }
}