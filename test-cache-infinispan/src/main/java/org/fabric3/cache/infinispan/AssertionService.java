package org.fabric3.cache.infinispan;

import java.util.Map;

/**
 *
 */
public interface AssertionService {

    boolean assertCount(int count);

    boolean assertItems(Map<Integer, String> items);

}
