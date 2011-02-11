package org.fabric3.cache.infinispan;

import org.oasisopen.sca.annotation.Remotable;

import java.util.concurrent.ConcurrentMap;

/**
 * @version $Rev$ $Date$
 */
@Remotable
public interface PublisherService<K, V> {

    void insertIntoCache(ConcurrentMap<K, V> itemsToInsert);

}
