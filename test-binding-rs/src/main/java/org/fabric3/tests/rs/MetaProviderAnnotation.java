package org.fabric3.tests.rs;

import javax.ws.rs.Consumes;
import javax.ws.rs.ext.Provider;

/**
 *
 */
@Provider
@Consumes({"application/foo"})
@java.lang.annotation.Target({java.lang.annotation.ElementType.TYPE})
@java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
public @interface MetaProviderAnnotation {
}
