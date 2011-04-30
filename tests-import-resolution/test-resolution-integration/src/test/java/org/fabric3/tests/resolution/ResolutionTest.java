package org.fabric3.tests.resolution;

import java.net.URI;

import junit.framework.TestCase;
import org.oasisopen.sca.annotation.Property;

import org.fabric3.api.annotation.Resource;
import org.fabric3.spi.classloader.ClassLoaderRegistry;

/**
 * @version $Rev$ $Date$
 */
public class ResolutionTest extends TestCase {

    @Resource
    protected ClassLoaderRegistry registry;

    @Property
    protected String version;

    public void testResolved() throws Exception {
        // Validates the class which is present in all contributions is resolved from the same contribution classloader.
        // This means the imports successfully resolved to a single export for the contributions which import and export the same packages
        ClassLoader loader1 = registry.getClassLoader(URI.create("test-resolution-app1-"+version+".jar"));
        ClassLoader loader2 = registry.getClassLoader(URI.create("test-resolution-app2-"+version+".jar"));
        ClassLoader loader3 = registry.getClassLoader(URI.create("test-resolution-app3-"+version+".jar"));
        ClassLoader loader4 = registry.getClassLoader(URI.create("test-resolution-app4-"+version+".jar"));
        Class<?> clazz1 = loader1.loadClass("org.fabric3.tests.resolution.TestClass");
        Class<?> clazz2 = loader2.loadClass("org.fabric3.tests.resolution.TestClass");
        Class<?> clazz3 = loader3.loadClass("org.fabric3.tests.resolution.TestClass");
        Class<?> clazz4 = loader4.loadClass("org.fabric3.tests.resolution.TestClass");
        assertEquals(clazz1, clazz2);
        assertEquals(clazz1, clazz3);
        assertEquals(clazz1, clazz4);
    }
}
