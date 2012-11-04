package org.fabric3.cache.infinispan;

import java.util.Map;
import java.util.concurrent.ConcurrentMap;

import org.oasisopen.sca.annotation.Scope;
import org.oasisopen.sca.annotation.Service;

import org.fabric3.api.annotation.Resource;
import org.fabric3.api.annotation.scope.Scopes;

/**
 *
 */
@Scope(Scopes.COMPOSITE)
@Service(PublisherService.class)
public class PublisherServiceImpl implements PublisherService {

    @Resource(name = "dataIndexCache")
    protected ConcurrentMap<Integer, String> cache;

    public void insert(Map<Integer, String> items) {
        cache.putAll(items);
    }
}
