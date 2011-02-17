package org.fabric3.cache.infinispan;

import java.util.concurrent.ConcurrentMap;

/**
 * @version $Rev$ $Date$
 */
public interface AssertionService<K, V> {

    boolean assertCount(int exceptedCount);

    boolean assertItems(ConcurrentMap items);

}
