package org.fabric3.tests.function.annotation;

import org.fabric3.api.annotation.model.Component;
import org.fabric3.api.annotation.scope.Composite;
import org.oasisopen.sca.annotation.EagerInit;

/**
 *
 */
@Composite
@EagerInit
@Component
@java.lang.annotation.Target({java.lang.annotation.ElementType.TYPE})
@java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
public @interface MetaAnnotation {
}
