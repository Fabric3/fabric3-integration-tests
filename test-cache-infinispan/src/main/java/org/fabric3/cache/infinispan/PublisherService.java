package org.fabric3.cache.infinispan;

import java.util.Map;

/**
 *
 */
public interface PublisherService {

    void insert(Map<Integer, String> items);

}
