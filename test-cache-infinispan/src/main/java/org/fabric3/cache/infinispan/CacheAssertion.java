package org.fabric3.cache.infinispan;

import org.fabric3.api.annotation.Resource;

import java.util.Map;
import java.util.concurrent.ConcurrentMap;

/**
 * @version $Rev$ $Date$
 */
public class CacheAssertion implements AssertionService<Integer, String> {

    @Resource(name = "dataIndexCache")
    ConcurrentMap<Integer, String> cache;

    public boolean assertCount(int exceptedCount) {
        return cache.size() == exceptedCount;
    }

    public boolean assertItems(ConcurrentMap<Integer, String> items) {
        if (cache.size() != items.size()) {
            return false;
        }

        for (Map.Entry<Integer, String> item : items.entrySet()) {
            String value = cache.get(item.getKey());
            if (null == value || !value.equals(item.getValue())) {
                return false;
            }
        }
        return true;
    }
}
