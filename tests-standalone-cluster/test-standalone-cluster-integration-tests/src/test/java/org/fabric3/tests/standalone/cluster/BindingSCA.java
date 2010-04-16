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
package org.fabric3.tests.standalone.cluster;

import java.io.File;
import java.net.URI;
import java.net.URL;
import java.util.Set;
import java.util.UUID;

import junit.framework.TestCase;
import org.junit.Assert;

import org.fabric3.admin.api.DomainController;
import org.fabric3.admin.impl.DomainControllerImpl;
import org.fabric3.management.contribution.ContributionInfo;
import org.fabric3.management.contribution.ContributionLockedManagementException;
import org.fabric3.tests.standalone.cluster.bindingsca.client.TestClientService;
import org.fabric3.tests.standalone.cluster.bindingsca.client.TestClientServiceService;

/**
 * @version $Rev$ $Date$
 */
public class BindingSCA extends TestCase {
    private static final int WAIT = 5000;

    private static final File APP_API_DIR = new File(".." + File.separator
            + ".." + File.separator
            + "tests-cluster-apps" + File.separator
            + "test-binding-sca" + File.separator
            + "binding-sca-app-api" + File.separator
            + "target" + File.separator
            + "binding-sca-app-api-0.1-SNAPSHOT.jar");

    private static final File APP1_DIR = new File(".." + File.separator
            + ".." + File.separator
            + "tests-cluster-apps" + File.separator
            + "test-binding-sca" + File.separator
            + "binding-sca-app1" + File.separator
            + "target" + File.separator
            + "binding-sca-app1-0.1-SNAPSHOT.jar");

    private static final File APP2_DIR = new File(".." + File.separator
            + ".." + File.separator
            + "tests-cluster-apps" + File.separator
            + "test-binding-sca" + File.separator
            + "binding-sca-app2" + File.separator
            + "target" + File.separator
            + "binding-sca-app2-0.1-SNAPSHOT.jar");

    private static final URI API_URI = URI.create("binding-sca-app-api-0.1-SNAPSHOT.jar");
    private static final URI APP1_URI = URI.create("binding-sca-app1-0.1-SNAPSHOT.jar");
    private static final URI APP2_URI = URI.create("binding-sca-app2-0.1-SNAPSHOT.jar");

    private DomainController domain;

    /**
     * * Verifies the following scenario:
     * <pre>
     *      - Application 2 is deployed to zone2 containing a bidirectional service, TestService
     *      - Application 1 is deployed to zone1 containing a web service endpoint, TestClientService, wired to TestService in zone2
     *      - A wire must be created using the JMS binding.sca implementation that flows an invocation from TestClientService to TestService and a
     *        callback from TestService to TestClientService.
     * </pre>
     *
     * @throws Exception no expected exception should be thrown
     */
    public void testWebServicesInvoke() throws Exception {
        TestClientServiceService endpoint = new TestClientServiceService();
        TestClientService service = endpoint.getTestClientServicePort();
        String message = UUID.randomUUID().toString();
        assertEquals(message, service.invoke(message));
    }

    /**
     * Verifies a contribution cannot be uninstalled while a contained deployable composite is deployed. It is expected that the controller will throw
     * a {@link ContributionLockedManagementException}.
     *
     * @throws Exception no expected exception should be thrown
     */
    public void testUndeployWhileLocked() throws Exception {
        try {
            domain.uninstall(APP2_URI);
            fail("Contribution locked exception expected");
        } catch (ContributionLockedManagementException e) {
            // expected
        }
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        connect();
        deployApplications();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        removeApplications();
    }

    private void connect() throws Exception {
        Exception exception = null;
        domain = new DomainControllerImpl();
        for (int i = 0; i < 50; i++) {   // wait 5 seconds
            Thread.sleep(100);
            try {
                if (!domain.isConnected()) {
                    domain.connect();
                }
                Set<ContributionInfo> infos = domain.stat();
                Assert.assertFalse(infos.isEmpty());
                exception = null;
                break;
            } catch (Exception e) {
                exception = e;
            }
        }
        if (exception != null) {
            throw exception;
        }
    }

    private void deployApplications() throws Exception {
        URL apiUrl = APP_API_DIR.toURI().toURL();
        URL app1Url = APP1_DIR.toURI().toURL();
        URL app2Url = APP2_DIR.toURI().toURL();
        try {
            domain.store(apiUrl, API_URI);
            domain.install(API_URI);

            domain.store(app2Url, APP2_URI);
            domain.install(APP2_URI);

            domain.store(app1Url, APP1_URI);
            domain.install(APP1_URI);

            domain.deploy(APP2_URI);
            domain.deploy(APP1_URI);

            Thread.sleep(WAIT);
            System.out.println("Applications deployed");
        } catch (Exception e) {
            ExceptionHelper.handleValidationError(e);
            throw e;
        }
    }

    private void removeApplications() throws Exception {
        domain.undeploy(APP1_URI, false);
        domain.undeploy(APP2_URI, false);

        domain.uninstall(APP1_URI);
        domain.remove(APP1_URI);

        domain.uninstall(APP2_URI);
        domain.remove(APP2_URI);

        domain.uninstall(API_URI);
        domain.remove(API_URI);

        domain.disconnect();

        Thread.sleep(WAIT);
        System.out.println("Applications removed");
    }


}