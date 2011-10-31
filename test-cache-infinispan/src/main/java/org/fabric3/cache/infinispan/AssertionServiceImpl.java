package org.fabric3.cache.infinispan;

import java.util.Map;
import java.util.concurrent.ConcurrentMap;

import org.oasisopen.sca.annotation.Scope;

import org.fabric3.api.annotation.Cache;
import org.fabric3.api.annotation.scope.Scopes;

/**
 * @version $Rev$ $Date$
 */
@Scope(Scopes.COMPOSITE)
public class AssertionServiceImpl implements AssertionService {

    @Cache(name = "dataIndexCache")
    protected ConcurrentMap<Integer, String> cache;

    public boolean assertCount(int count) {
        return cache.size() == count;
    }

    public boolean assertItems(Map<Integer, String> items) {
        if (cache.size() != items.size()) {
            return false;
        }

        for (Map.Entry<Integer, String> item : items.entrySet()) {
            String value = cache.get(item.getKey());
            if (null == value || !value.equals(((Map.Entry) item).getValue())) {
                return false;
            }
        }
        return true;
    }
}
