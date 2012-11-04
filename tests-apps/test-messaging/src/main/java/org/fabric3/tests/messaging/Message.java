package org.fabric3.tests.messaging;

import java.io.Serializable;

/**
 *
 */
public class Message implements Serializable {
    private static final long serialVersionUID = 5733197758657279113L;
    private Type type;

    public enum Type {
        START, CONTINUE, END
    }
    private String runtime;
    private long sequence;

    public Message(String runtime, long sequence, Type type) {
        this.runtime = runtime;
        this.sequence = sequence;
        this.type = type;
    }

    public String getRuntime() {
        return runtime;
    }

    public long getSequence() {
        return sequence;
    }

    public Type getType() {
        return type;
    }
}
