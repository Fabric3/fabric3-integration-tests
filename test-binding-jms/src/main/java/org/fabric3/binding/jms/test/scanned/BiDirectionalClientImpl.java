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
package org.fabric3.binding.jms.test.scanned;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.fabric3.api.Namespaces;
import org.fabric3.api.annotation.model.Component;
import org.fabric3.api.annotation.scope.Composite;
import org.fabric3.api.binding.jms.annotation.JMS;
import org.fabric3.api.binding.jms.annotation.JMSConfiguration;

/**
 *
 */
@Composite
@Component(composite = Namespaces.F3_PREFIX + "TestComposite")
public class BiDirectionalClientImpl implements BiDirectionalClient, ConsumerCallback {
    private CountDownLatch latch = new CountDownLatch(1);

    private volatile String lastResult;

    @JMS(value = @JMSConfiguration(destination = "ScannedBiDirectionalQueue"), callback = @JMSConfiguration(destination = "ScannedBiDirectionalCallbackQueue"))
    protected BiDirectionalConsumer consumer;

    public String invoke(String message) {
        consumer.onReceive(message);
        try {
            boolean result = latch.await(10000, TimeUnit.MILLISECONDS);
            if (!result) {
                throw new AssertionError("Callback not received");
            }
            return lastResult;
        } catch (InterruptedException e) {
            throw new AssertionError(e);
        }
    }

    public void onResponse(String message) {
        lastResult = message;
        latch.countDown();
    }
}