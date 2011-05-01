package org.fabric3.tests.jpa;

import org.oasisopen.sca.annotation.Reference;
import org.oasisopen.sca.annotation.Scope;

import org.fabric3.api.annotation.management.Management;
import org.fabric3.api.annotation.management.ManagementOperation;

/**
 * @version $Rev$ $Date$
 */
@Management
@Scope("COMPOSITE")
public class TestComponent {


    public TestComponent(@Reference Store store) {
        this.store = store;
    }

    private Store store;

    @ManagementOperation
    public void operation() {
        store.create("jim");
    }

}

