/*
* Fabric3
* Copyright (c) 2009 Metaform Systems
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
package org.fabric3.timer.quartz;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import javax.transaction.Status;
import javax.transaction.SystemException;
import javax.transaction.TransactionManager;

import junit.framework.TestCase;

import org.fabric3.api.annotation.Resource;
import org.fabric3.timer.spi.TimerService;

/**
 * @version $Rev$ $Date$
 */
public class QuartzTestComponent extends TestCase {

    @Resource(mappedName = "TransactionalTimerService")
    protected TimerService trxTimerService;
    @Resource(mappedName = "NonTransactionalTimerService")
    protected TimerService timerService;

    @Resource(mappedName = "TransactionManager")
    protected TransactionManager tm;

    public void testTransactionalScheduleInterval() throws Exception {
        TrxTestRunnable runnable = new TrxTestRunnable(2);  // test multiple firings
        trxTimerService.scheduleWithFixedDelay(runnable, 0, 100, TimeUnit.MILLISECONDS);
        runnable.await();
        assertTrue(runnable.isTrxStarted());
    }

    public void testNonTransactionalScheduleInterval() throws Exception {
        NoTrxTestRunnable runnable = new NoTrxTestRunnable(2);  // test multiple firings
        timerService.scheduleWithFixedDelay(runnable, 0, 100, TimeUnit.MILLISECONDS);
        runnable.await();
        assertTrue(runnable.isNoTrx());
    }

    public void testTransactionalScheduleWithDelay() throws Exception {
        TrxTestRunnable runnable = new TrxTestRunnable(1); // fires once
        trxTimerService.schedule(runnable, 100, TimeUnit.MILLISECONDS);
        runnable.await();
        assertTrue(runnable.isTrxStarted());
    }

    public void testNonTransactionalScheduleWithDelay() throws Exception {
        NoTrxTestRunnable runnable = new NoTrxTestRunnable(1);   // fires once
        timerService.schedule(runnable, 100, TimeUnit.MILLISECONDS);
        runnable.await();
        assertTrue(runnable.isNoTrx());
    }

    private class TrxTestRunnable implements Runnable {
        private CountDownLatch latch;
        private boolean trxStarted;

        private TrxTestRunnable(int num) {
            latch = new CountDownLatch(num);
        }

        public void run() {
            try {
                if (tm.getStatus() == Status.STATUS_ACTIVE) {
                    trxStarted = true;
                }
                latch.countDown();
            } catch (SystemException e) {
                // this will cause the test to fail by not setting noTrx
            }
        }

        public boolean isTrxStarted() {
            return trxStarted;
        }

        public void await() throws InterruptedException {
            latch.await();
        }
    }


    private class NoTrxTestRunnable implements Runnable {
        private CountDownLatch latch;
        private boolean noTrx;

        private NoTrxTestRunnable(int num) {
            latch = new CountDownLatch(num);
        }

        public void run() {
            try {
                if (tm.getStatus() == Status.STATUS_NO_TRANSACTION) {
                    noTrx = true;
                }
                latch.countDown();
            } catch (SystemException e) {
                // this will cause the test to fail
            }
        }

        public boolean isNoTrx() {
            return noTrx;
        }

        public void await() throws InterruptedException {
            latch.await();
        }
    }

}
