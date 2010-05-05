package org.fabric3.tests.standalone.cluster.bindingsca.api;

import java.io.Serializable;

/**
 * @version $Rev$ $Date$
 */
public class TestEvent implements Serializable {
    private static final long serialVersionUID = -8339771886381152290L;
    private String message;

    public TestEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}