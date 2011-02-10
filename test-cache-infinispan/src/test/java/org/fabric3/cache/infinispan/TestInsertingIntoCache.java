package org.fabric3.cache.infinispan;

import junit.framework.TestCase;
import org.fabric3.api.MonitorChannel;
import org.fabric3.api.annotation.monitor.Monitor;
import org.fabric3.api.annotation.scope.Scopes;
import org.oasisopen.sca.annotation.EagerInit;
import org.oasisopen.sca.annotation.Reference;
import org.oasisopen.sca.annotation.Scope;

import java.text.MessageFormat;

/**
 * @version $Rev$ $Date$
 */
@EagerInit
@Scope(Scopes.COMPOSITE)
public class TestInsertingIntoCache extends TestCase {

    @Reference
    protected AssertionService AssertionService;

    @Reference
    protected PublisherService PublisherService;

    @Monitor
    protected MonitorChannel monitor;

    public void testCacheConfiguration() throws Exception {
        assertTrue(AssertionService.assertCount(0));

        int countToInsert = 1000;
        long startDate = System.currentTimeMillis();

        PublisherService.generateToCache(countToInsert);
        assertTrue(AssertionService.assertCount(countToInsert));

        long endDate = System.currentTimeMillis();
        monitor.info(MessageFormat.format("Inserting of {0} items took: {1} ms", countToInsert, endDate - startDate));
    }
}
