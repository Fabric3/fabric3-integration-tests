package org.fabric3.cache.infinispan;

import org.fabric3.api.annotation.Resource;

import java.util.concurrent.ConcurrentMap;

/**
 * @version $Rev$ $Date$
 */
public class CachePublisher implements PublisherService<Integer, String> {

    @Resource(name = "dataIndexCache")
    ConcurrentMap<Integer, String> cache;

    public void insertIntoCache(ConcurrentMap<Integer, String> itemsToInsert) {
        cache.putAll(itemsToInsert);
    }
}
