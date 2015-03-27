package org.fabric3.tests.eventing;

import java.util.concurrent.CountDownLatch;

import com.lmax.disruptor.RingBuffer;
import org.fabric3.api.ChannelContext;
import org.fabric3.api.ChannelEvent;
import org.fabric3.api.annotation.Channel;
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
public class RingBufferContextConsumer {

    @Channel("ContextRingBufferChannel")
    protected ChannelContext context;
    private RingBuffer<ChannelEvent> ringBuffer;

    @Init
    protected void init() {
        ringBuffer = context.getConsumer(RingBuffer.class);
        Assert.assertNotNull(ringBuffer);
    }

    @Consumer
    public void process(ChannelEvent event) {
        event.getEvent(CountDownLatch.class).countDown();
    }
}
