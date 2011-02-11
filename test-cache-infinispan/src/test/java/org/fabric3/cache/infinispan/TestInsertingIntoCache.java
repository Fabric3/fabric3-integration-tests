package org.fabric3.cache.infinispan;

import junit.framework.TestCase;
import org.fabric3.api.MonitorChannel;
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
    protected AssertionService<Integer, String> AssertionService;

    @Reference
    protected PublisherService<Integer, String> PublisherService;

    @Monitor
    protected MonitorChannel monitor;

    public void testCacheConfiguration() throws Exception {
        assertTrue(AssertionService.assertCount(0));

        int countToInsert = 10000;

        ConcurrentMap<Integer, String> temp = new ConcurrentHashMap<Integer, String>();
        for (int i = 0; i < countToInsert; i++) {
            temp.put(i, Integer.toString(i));
        }

        long startDate = System.currentTimeMillis();

        PublisherService.insertIntoCache(temp);
        assertTrue(AssertionService.assertCount(countToInsert));
        assertTrue(AssertionService.assertItems(temp));

        long endDate = System.currentTimeMillis();
        monitor.info(MessageFormat.format("Inserting of {0} items took: {1} ms", countToInsert, endDate - startDate));
    }
}
