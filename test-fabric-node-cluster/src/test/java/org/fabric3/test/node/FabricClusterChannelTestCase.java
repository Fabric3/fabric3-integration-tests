/*
 * Fabric3
 * Copyright (c) 2009-2013 Metaform Systems
 *
 * Fabric3 is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of
 * the License, or (at your option) any later version, with the
 * following exception:
 *
 * Linking this software statically or dynamically with other
 * modules is making a combined work based on this software.
 * Thus, the terms and conditions of the GNU General Public
 * License cover the whole combination.
 *
 * As a special exception, the copyright holders of this software
 * give you permission to link this software with independent
 * modules to produce an executable, regardless of the license
 * terms of these independent modules, and to copy and distribute
 * the resulting executable under terms of your choice, provided
 * that you also meet, for each linked independent module, the
 * terms and conditions of the license of that module. An
 * independent module is a module which is not derived from or
 * based on this software. If you modify this software, you may
 * extend this exception to your version of the software, but
 * you are not obligated to do so. If you do not wish to do so,
 * delete this exception statement from your version.
 *
 * Fabric3 is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty
 * of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 *
 * You should have received a copy of the
 * GNU General Public License along with Fabric3.
 * If not, see <http://www.gnu.org/licenses/>.
*/
package org.fabric3.test.node;

import java.net.URL;

import junit.framework.TestCase;
import org.fabric3.api.node.Bootstrap;
import org.fabric3.api.node.Domain;
import org.fabric3.api.node.Fabric;

/**
 *
 */
public class FabricClusterChannelTestCase extends TestCase {
    public void testBlank() throws Exception {

    }

    public void testPublishToRemoteChannel() throws Exception {
        Fabric fabric1 = Bootstrap.initialize(getClass().getResource("/systemConfigZone1.xml"));
        fabric1.start();

        Fabric fabric2 = Bootstrap.initialize(getClass().getResource("/systemConfigZone2.xml"));
        fabric2.start();

        Domain domain1 = fabric1.getDomain();
        URL channelComposite = getClass().getClassLoader().getResource("remote.channel.composite");
        domain1.deploy(channelComposite);

        URL consumerComposite = getClass().getClassLoader().getResource("consumer.composite");
        domain1.deploy(consumerComposite);

        // wait for the runtimes to converge
        Thread.sleep(2000);

        Domain domain2 = fabric2.getDomain();
        TestChannel channel = domain2.getChannel(TestChannel.class, "TestChannel");

        Thread.sleep(1000);

        for (int i = 0; i < 100; i++) {
            channel.send("test");
        }

        // the latch service is released by the consumer
        LatchService latchService = domain1.getService(LatchService.class);
        latchService.await();

        fabric1.stop();
        fabric2.stop();
    }

    public void testSendViaProducerToRemoteChannel() throws Exception {
        Fabric fabric1 = Bootstrap.initialize(getClass().getResource("/systemConfigZone1.xml"));
        fabric1.start();

        Fabric fabric2 = Bootstrap.initialize(getClass().getResource("/systemConfigZone2.xml"));
        fabric2.start();

        Domain domain1 = fabric1.getDomain();
        URL channelComposite = getClass().getClassLoader().getResource("remote.channel.composite");
        domain1.deploy(channelComposite);

        URL consumerComposite = getClass().getClassLoader().getResource("consumer.composite");
        domain1.deploy(consumerComposite);

        // wait for the runtimes to converge
        Thread.sleep(2000);

        Domain domain2 = fabric2.getDomain();

        URL producerComposite = getClass().getClassLoader().getResource("producer.composite");
        domain2.deploy(producerComposite);

        Thread.sleep(1000);

        TestProducer producer = domain2.getService(TestProducer.class);

        for (int i = 0; i < 100; i++) {
            producer.send();
        }

        // the latch service is released by the consumer
        LatchService latchService = domain1.getService(LatchService.class);
        latchService.await();

        fabric1.stop();
        fabric2.stop();
    }

    public void testSendToRemoteConsumer() throws Exception {
        Fabric fabric1 = Bootstrap.initialize(getClass().getResource("/systemConfigZone1.xml"));
        fabric1.start();

        Fabric fabric2 = Bootstrap.initialize(getClass().getResource("/systemConfigZone2.xml"));
        fabric2.start();

        Domain domain1 = fabric1.getDomain();
        URL channelComposite = getClass().getClassLoader().getResource("remote.channel.composite");
        domain1.deploy(channelComposite);

        // wait for the runtimes to converge
        Thread.sleep(2000);

        Domain domain2 = fabric2.getDomain();

        URL consumerComposite = getClass().getClassLoader().getResource("consumer.composite");
        domain2.deploy(consumerComposite);

        TestChannel channel = domain1.getChannel(TestChannel.class, "TestChannel");

        Thread.sleep(1000);

        for (int i = 0; i < 100; i++) {
            channel.send("test");
        }

        // the latch service is released by the consumer
        LatchService latchService = domain2.getService(LatchService.class);
        latchService.await();

        fabric1.stop();
        fabric2.stop();
    }

    //
    //    public void testChannelNodeRestart() throws Exception {
    //        // TODO implement
    //    }
}
