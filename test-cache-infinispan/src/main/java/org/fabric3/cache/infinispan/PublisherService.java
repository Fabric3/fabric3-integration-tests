package org.fabric3.cache.infinispan;

import java.util.concurrent.ConcurrentMap;

/**
 * @version $Rev$ $Date$
 */
public interface PublisherService<K, V> {

    void insertIntoCache(ConcurrentMap itemsToInsert);

}
