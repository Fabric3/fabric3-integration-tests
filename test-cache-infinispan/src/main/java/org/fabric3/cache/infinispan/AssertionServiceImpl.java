package org.fabric3.cache.infinispan;

import org.fabric3.api.annotation.Resource;
import org.fabric3.api.annotation.scope.Scopes;
import org.oasisopen.sca.annotation.EagerInit;
import org.oasisopen.sca.annotation.Init;
import org.oasisopen.sca.annotation.Scope;
import org.oasisopen.sca.annotation.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentMap;

/**
 * @version $Rev$ $Date$
 */
@EagerInit
@Scope(Scopes.COMPOSITE)
@Service(AssertionService.class)
public class AssertionServiceImpl implements AssertionService<Integer, String> {

    @Resource(name = "dataIndexCache")
    ConcurrentMap<Integer, String> cache;

    @Init
    public void init() {
        System.out.println("Assertion service started.");
    }

    public boolean assertCount(int exceptedCount) {
        return cache.size() == exceptedCount;
    }

    public boolean assertItems(ConcurrentMap items) {
        if (cache.size() != items.size()) {
            return false;
        }

        for (Object item : items.entrySet()) {
            String value = cache.get(((Map.Entry) item).getKey());
            if (null == value || !value.equals(((Map.Entry) item).getValue())) {
                return false;
            }
        }
        return true;
    }
}
