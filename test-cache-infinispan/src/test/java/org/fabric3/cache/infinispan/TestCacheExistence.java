package org.fabric3.cache.infinispan;

import java.io.Serializable;
import java.util.concurrent.ConcurrentMap;

import junit.framework.TestCase;

import org.fabric3.api.annotation.Resource;

/**
 *
 */
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
