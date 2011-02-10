package org.fabric3.cache.infinispan;

import org.fabric3.api.annotation.Resource;

import java.util.concurrent.ConcurrentMap;

/**
 * @version $Rev$ $Date$
 */
public class CachePublisher implements PublisherService {

    @Resource(name = "dataIndexCache")
    ConcurrentMap<Integer, String> cache;

    public void generateToCache(int itemsCount) {
        for (int i = 0; i < itemsCount; i++) {
            cache.put(i, String.valueOf(i));
        }
    }
}
