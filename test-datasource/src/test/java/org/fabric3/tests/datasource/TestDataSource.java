package org.fabric3.tests.datasource;

import javax.sql.DataSource;

import junit.framework.TestCase;

import org.fabric3.api.annotation.Resource;

/**
 *
 */
public class TestDataSource extends TestCase {

    @Resource(name = "ds1")
    protected DataSource datasource;

    @Resource(name = "ds2")
    protected DataSource datasource2;

    @Resource(name = "ds2Alias")
    protected DataSource datasource2Alias;

    public void testDataSourceConfiguration() throws Exception {
        assertNotNull(datasource);
        assertNotNull(datasource2);
        assertNotNull(datasource2Alias);
    }

}