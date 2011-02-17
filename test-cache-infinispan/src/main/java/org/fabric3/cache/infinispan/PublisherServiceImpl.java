package org.fabric3.cache.infinispan;

import org.fabric3.api.annotation.Resource;
import org.fabric3.api.annotation.scope.Scopes;
import org.oasisopen.sca.annotation.EagerInit;
import org.oasisopen.sca.annotation.Init;
import org.oasisopen.sca.annotation.Scope;
import org.oasisopen.sca.annotation.Service;

import java.util.concurrent.ConcurrentMap;

/**
 * @version $Rev$ $Date$
 */
@EagerInit
@Scope(Scopes.COMPOSITE)
@Service(PublisherService.class)
public class PublisherServiceImpl implements PublisherService<Integer, String> {

    @Resource(name = "dataIndexCache")
    ConcurrentMap<Integer, String> cache;

    @Init
    public void init() {
        System.out.println("Publish service started.");
    }

    public void insertIntoCache(ConcurrentMap itemsToInsert) {
        cache.putAll(itemsToInsert);
    }
}
