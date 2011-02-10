package org.fabric3.cache.infinispan;

import junit.framework.TestCase;
import org.fabric3.api.annotation.Resource;
import org.fabric3.api.annotation.scope.Scopes;
import org.oasisopen.sca.annotation.EagerInit;
import org.oasisopen.sca.annotation.Scope;

import java.io.Serializable;
import java.util.concurrent.ConcurrentMap;

/**
 * @version $Rev$ $Date$
 */
@EagerInit
@Scope(Scopes.COMPOSITE)
public class TestCacheExistence extends TestCase {

    @Resource(name = "dataIndexCache")
    protected ConcurrentMap<Integer, String> cache;

    @Resource(name = "dataIndexCache2")
    protected ConcurrentMap<Serializable, Serializable> cache2;

    public void testCacheConfiguration() throws Exception {
        assertNotNull(cache);
        assertNotNull(cache2);
    }
}
