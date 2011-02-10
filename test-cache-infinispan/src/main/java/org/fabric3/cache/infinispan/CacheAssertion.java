package org.fabric3.cache.infinispan;

import org.fabric3.api.annotation.Resource;

import java.util.concurrent.ConcurrentMap;

/**
 * @version $Rev$ $Date$
 */
public class CacheAssertion implements AssertionService {

    @Resource(name = "dataIndexCache")
    ConcurrentMap<Integer, String> cache;

    public boolean assertCount(int exceptedCount) {
        return cache.size() == exceptedCount;
    }
}
