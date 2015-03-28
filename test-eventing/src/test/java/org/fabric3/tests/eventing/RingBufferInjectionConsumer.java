package org.fabric3.tests.eventing;

import java.nio.channels.Channel;

import com.lmax.disruptor.RingBuffer;
import org.fabric3.api.ChannelEvent;
import org.fabric3.api.annotation.Consumer;
import org.fabric3.api.annotation.scope.Composite;
import org.junit.Assert;
import org.oasisopen.sca.annotation.EagerInit;
import org.oasisopen.sca.annotation.Init;

/**
 *
 */
@Composite
@EagerInit
public class RingBufferInjectionConsumer {
    private RingBuffer<Channel> ctorBuffer;
    private RingBuffer<Channel> methodBuffer;

    @Consumer
    protected RingBuffer<ChannelEvent> ringBuffer;

    @Consumer
    public void setMethodBuffer(RingBuffer<Channel> methodBuffer) {
        this.methodBuffer = methodBuffer;
    }

    public RingBufferInjectionConsumer(@Consumer("ctorBuffer") RingBuffer<Channel> buffer) {
        this.ctorBuffer = buffer;
    }

    @Init
    protected void init() {
        Assert.assertNotNull(ctorBuffer);
        Assert.assertNotNull(ringBuffer);
        Assert.assertNotNull(methodBuffer);
    }

}
