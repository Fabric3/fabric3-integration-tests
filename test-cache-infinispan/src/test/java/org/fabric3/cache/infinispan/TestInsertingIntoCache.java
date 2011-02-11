package org.fabric3.cache.infinispan;

import junit.framework.TestCase;
import org.fabric3.api.MonitorChannel;
import org.fabric3.api.annotation.Resource;
import org.fabric3.api.annotation.monitor.Monitor;
import org.fabric3.api.annotation.scope.Scopes;
import org.oasisopen.sca.annotation.EagerInit;
import org.oasisopen.sca.annotation.Reference;
import org.oasisopen.sca.annotation.Scope;

import java.text.MessageFormat;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @version $Rev$ $Date$
 */
@EagerInit
@Scope(Scopes.COMPOSITE)
public class TestInsertingIntoCache extends TestCase {

    @Reference
    protected AssertionService<Integer, String> assertionService;

    @Reference
    protected PublisherService<Integer, String> publisherService;

    @Monitor
    protected MonitorChannel monitor;

    @Resource(name = "dataIndexCache")
    ConcurrentMap<Integer, String> cache;

    public void testCacheConfiguration() throws Exception {
        assertTrue(assertionService.assertCount(0));

        int countToInsert = 10000;

        ConcurrentMap<Integer, String> temp = new ConcurrentHashMap<Integer, String>();
        for (int i = 0; i < countToInsert; i++) {
            temp.put(i, Integer.toString(i));
        }

        long startDate = System.currentTimeMillis();

        publisherService.insertIntoCache(temp);
        assertTrue(assertionService.assertCount(countToInsert));
        assertTrue(assertionService.assertItems(temp));

        long endDate = System.currentTimeMillis();
        monitor.info(MessageFormat.format("Inserting of {0} items took: {1} ms", countToInsert, endDate - startDate));

        cache.clear();
        assertionService.assertCount(0);
    }
}
