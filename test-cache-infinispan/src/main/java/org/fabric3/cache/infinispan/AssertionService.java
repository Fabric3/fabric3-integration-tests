package org.fabric3.cache.infinispan;

import org.oasisopen.sca.annotation.Remotable;

import java.util.concurrent.ConcurrentMap;

/**
 * @version $Rev$ $Date$
 */
@Remotable
public interface AssertionService<K, V> {

    boolean assertCount(int exceptedCount);

    boolean assertItems(ConcurrentMap<K, V> items);

}
