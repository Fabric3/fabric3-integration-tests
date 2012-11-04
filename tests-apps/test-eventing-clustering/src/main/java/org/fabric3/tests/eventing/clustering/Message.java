package org.fabric3.tests.eventing.clustering;

import java.io.Serializable;

/**
 *
 */
public class Message implements Serializable {
    private static final long serialVersionUID = 5733197758657279113L;

    private String runtime;
    private long sequence;

    public Message(String runtime, long sequence) {
        this.runtime = runtime;
        this.sequence = sequence;
    }

    public String getRuntime() {
        return runtime;
    }

    public long getSequence() {
        return sequence;
    }
}
