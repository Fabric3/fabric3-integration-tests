/*
 * Fabric3
 * Copyright (c) 2009-2014 Metaform Systems
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
package org.fabric3.tests.monitor.performance;

import java.util.concurrent.atomic.AtomicBoolean;

import org.fabric3.api.annotation.management.Management;
import org.fabric3.api.annotation.management.ManagementOperation;
import org.fabric3.api.annotation.monitor.Monitor;
import org.fabric3.api.annotation.monitor.Severe;
import org.oasisopen.sca.annotation.Scope;

/**
 *
 */
@Management
@Scope("COMPOSITE")
public class TestMonitorClient implements TestService {
    public static final int MIN = 100000;
    public static final int MAX = 200000;

    private Runner runner1 = new Runner(1);
    private Runner runner2 = new Runner(2);
    private Runner runner3 = new Runner(3);
    private Runner runner4 = new Runner(4);
    private Runner runner5 = new Runner(5);
    private Runner runner6 = new Runner(6);
    private AtomicBoolean stop = new AtomicBoolean(true);

    @Monitor
    TestMonitor monitor;

    @ManagementOperation
    public void fireSingle() {
        long start = System.nanoTime();
        monitor.send("This is a test");
        System.out.println("Time last event (client): " + (System.nanoTime() - start));
    }

    @ManagementOperation
    public void start() {
        stop.set(false);
        for (int i = 0; i < MIN; i++) {
            monitor.send("This is a test");
        }
        new Thread(runner1).start();
        new Thread(runner2).start();
        new Thread(runner3).start();
        new Thread(runner4).start();
        new Thread(runner5).start();
        new Thread(runner6).start();
    }

    @ManagementOperation
    public void stop() {
        stop.set(true);
    }

    @ManagementOperation
    public void error() {
        monitor.error(new Exception("Some error!"));
    }

    public void invoke() {
    }

    private class Runner implements Runnable {

        private int number;

        private long elapsedTime;
        private int counter;

        private Runner(int number) {
            this.number = number;
        }

        public void run() {
            counter = 0;
            elapsedTime = 0;
            while (!stop.get()) {
                long start = System.nanoTime();
                monitor.send("This is a test");
                long stop = System.nanoTime();
                if (counter >= MIN) {
                    long time = stop - start;
                    elapsedTime = elapsedTime + time;
                }
                if (counter == MAX) {
                    long lastClient = stop - start;
                    double average = (double) elapsedTime / (double) (MAX - MIN);
                    System.out.println("Client " + number + ": Time last event : " + lastClient + " Elapsed: " + elapsedTime + " Avg: " + average);
                }
                counter++;
            }
            System.out.println("Done: " + number);
        }
    }

    public interface TestMonitor {

        @Severe
        void send(String message);

        @Severe("An error was encountered")
        void error(Exception e);

    }

}
