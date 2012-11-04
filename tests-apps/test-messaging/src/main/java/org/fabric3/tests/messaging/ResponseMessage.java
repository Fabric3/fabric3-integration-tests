package org.fabric3.tests.messaging;

import java.io.Serializable;

/**
 *
 */
public class ResponseMessage implements Serializable {
    private static final long serialVersionUID = 5733197758657279113L;

    private String runtime;
    private long sequence;
    private long correlation;
    private Message.Type type;

    public ResponseMessage(String runtime, long sequence, long correlation, Message.Type type) {
        this.runtime = runtime;
        this.sequence = sequence;
        this.correlation = correlation;
        this.type = type;
    }

    public String getRuntime() {
        return runtime;
    }

    public long getSequence() {
        return sequence;
    }

    public long getCorrelation() {
        return correlation;
    }

    public Message.Type getType() {
        return type;
    }
}
