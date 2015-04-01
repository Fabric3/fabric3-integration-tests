package org.fabric3.tests.eventing;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.RingBuffer;
import org.fabric3.api.ChannelContext;
import org.fabric3.api.ChannelEvent;
import org.fabric3.api.annotation.Channel;
import org.fabric3.api.annotation.scope.Composite;
import org.junit.Assert;

/**
 *
 */
@Composite
public class ContextServiceImpl implements ContextService {
    private static final EventTranslatorOneArg<ChannelEvent, CountDownLatch> TRANSLATOR = new EventTranslatorOneArg<ChannelEvent, CountDownLatch>() {
        public void translateTo(ChannelEvent event, long sequence, CountDownLatch latch) {
            event.setEvent(latch);
        }
    };
    @Channel("ContextChannel")
    protected ChannelContext context;

    @Channel("ContextChannel2")
    protected ChannelContext context2;

    @Channel("ContextRingBufferChannel")
    protected ChannelContext ringBufferContext;

    @SuppressWarnings("unchecked")
    public void invoke() {
        CountDownLatch latch = new CountDownLatch(1);
        context.getProducer(LatchChannel.class).process(latch);
        try {
            Assert.assertTrue(latch.await(2000, TimeUnit.MILLISECONDS));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        latch = new CountDownLatch(1);
        RingBuffer<ChannelEvent> producer = ringBufferContext.getProducer(RingBuffer.class);
        producer.publishEvent(TRANSLATOR, latch);
        try {
            Assert.assertTrue(latch.await(2000, TimeUnit.MILLISECONDS));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        latch = new CountDownLatch(1);
        Object handle = context2.subscribe(CountDownLatch.class, CountDownLatch::countDown);
        Assert.assertNotNull(handle);
        context2.getProducer(LatchChannel.class).process(latch);

        try {
            Assert.assertTrue(latch.await(2000, TimeUnit.MILLISECONDS));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
