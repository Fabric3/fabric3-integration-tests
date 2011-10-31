package org.fabric3.cache.infinispan;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;

import junit.framework.TestCase;
import org.oasisopen.sca.annotation.Reference;

import org.fabric3.api.MonitorChannel;
import org.fabric3.api.annotation.Resource;
import org.fabric3.api.annotation.monitor.Monitor;

/**
 * @version $Rev$ $Date$
 */
public class TestInsertingIntoCache extends TestCase {

    @Reference(required = true)
    protected AssertionService assertionReference;

    @Reference(required = true)
    protected PublisherService publisherReference;

    @Monitor
    protected MonitorChannel monitor;

    @Resource(name = "dataIndexCache")
    ConcurrentMap<Integer, String> cache;

    public void testCacheConfiguration() throws Exception {
        assertTrue(assertionReference.assertCount(0));

        int countToInsert = 10000;

        Map<Integer, String> temp = new HashMap<Integer, String>();
        for (int i = 0; i < countToInsert; i++) {
            temp.put(i, Integer.toString(i));
        }

        long startDate = System.currentTimeMillis();

        publisherReference.insert(temp);
        assertTrue(assertionReference.assertCount(countToInsert));
        assertTrue(assertionReference.assertItems(temp));

        long endDate = System.currentTimeMillis();
        monitor.info(MessageFormat.format("Inserting of {0} items took: {1} ms", countToInsert, endDate - startDate));

        cache.clear();
        assertionReference.assertCount(0);
    }
}
