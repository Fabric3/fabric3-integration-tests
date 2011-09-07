package org.fabric3.tests.drools.message;

/**
 * @version $Rev$ $Date$
 */
public class TestMessage {
    public static final int HELLO = 0;
    public static final int GOODBYE = 1;

    private String message;
    private int status = HELLO;

    public TestMessage() {
    }

    public TestMessage(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}