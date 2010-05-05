package org.fabric3.tests.binding.harness.eventing;

import java.io.Serializable;

/**
 * @version $Rev$ $Date$
 */
public class TestEvent implements Serializable {
    private static final long serialVersionUID = -1981814369955519624L;
    private String message;

    public TestEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
