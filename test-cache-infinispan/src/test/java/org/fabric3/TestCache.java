package org.fabric3;

import junit.framework.TestCase;
import org.fabric3.api.annotation.Resource;
import org.fabric3.api.annotation.scope.Scopes;
import org.oasisopen.sca.annotation.EagerInit;
import org.oasisopen.sca.annotation.Init;
import org.oasisopen.sca.annotation.Scope;

import java.util.concurrent.ConcurrentMap;

/**
 * @version $Rev$ $Date$
 */
@EagerInit
@Scope(Scopes.COMPOSITE)
public class TestCache extends TestCase {

    @Resource(name = "dataIndexCache")
    protected ConcurrentMap cache;

    public void testCacheConfiguration() throws Exception {
        assertNotNull(cache);
    }

    @Init
    public void init() {
        System.out.println("init");
    }
}
