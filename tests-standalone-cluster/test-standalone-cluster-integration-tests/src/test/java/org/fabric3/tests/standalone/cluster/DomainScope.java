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
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.Set;
import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

import junit.framework.TestCase;
import org.junit.Assert;

import org.fabric3.admin.api.DomainController;
import org.fabric3.admin.impl.DomainControllerImpl;
import org.fabric3.management.contribution.ContributionInfo;

/**
 * Integration tests for the domain scope.
 *
 * @version $Rev$ $Date$
 */
public class DomainScope extends TestCase {
    private static final int WAIT = 20000;
    private static String PARTICIPANT1_ADDRESS = "service:jmx:rmi:///jndi/rmi://localhost:1300/server";
    private static String PARTICIPANT2_ADDRESS = "service:jmx:rmi:///jndi/rmi://localhost:1301/server";

    private static final File APP_DIR = new File(".." + File.separator
            + ".." + File.separator
            + "tests-cluster-apps" + File.separator
            + "domain-scope" + File.separator
            + "target" + File.separator
            + "domain-scope-0.1-SNAPSHOT.jar");


    private static final URI APP_URI = URI.create("domain-scope-0.1-SNAPSHOT.jar");

    private DomainController domain;

    /**
     * Verifies a clustered domain-scope (singleton) deployment. The domain-scoped component is deployed to a zone (zone2) containing multiple
     * runtimes. This test verifies a component instance is only activated on a single runtime.
     *
     * @throws Exception no expected exception should be thrown
     */
    public void testDomainScopeDeployment() throws Exception {
        JMXServiceURL url = new JMXServiceURL(PARTICIPANT1_ADDRESS);
        JMXConnector jmxc = JMXConnectorFactory.connect(url, null);
        MBeanServerConnection conn = jmxc.getMBeanServerConnection();
        ObjectName oName = new ObjectName("fabric3:SubDomain=domain, type=component, name=SingletonComponent");
        boolean firstRuntimeInvoked = false;
        IOException firstException = null;
        try {
            assertEquals("invoke", conn.invoke(oName, "invoke", new Object[]{"invoke"}, new String[]{String.class.getName()}));
            firstRuntimeInvoked = true;
        } catch (IOException e) {
            firstException = e;
            // may occur
        }
        jmxc.close();

        url = new JMXServiceURL(PARTICIPANT2_ADDRESS);
        jmxc = JMXConnectorFactory.connect(url, null);
        conn = jmxc.getMBeanServerConnection();
        boolean secondRuntimeInvoked = false;
        IOException secondException = null;
        try {
            assertEquals("invoke", conn.invoke(oName, "invoke", new Object[]{"invoke"}, new String[]{String.class.getName()}));
            secondRuntimeInvoked = true;
        } catch (IOException e) {
            // may occur
            secondException = e;
        }
        if (firstRuntimeInvoked && secondRuntimeInvoked) {
            // make sure the component was not activated on both runtimes
            printExceptions(firstException, secondException);
            fail("Domain scoped component was activated on two participants in the same zone");
        } else if (!firstRuntimeInvoked && !secondRuntimeInvoked) {
            // make sure the component was activated on one runtime
            printExceptions(firstException, secondException);
            fail("Domain scoped component not activated");
        }
        jmxc.close();
    }

    private void printExceptions(IOException firstException, IOException secondException) {
        if (firstException != null) {
            firstException.printStackTrace();
        }
        if (secondException != null) {
            secondException.printStackTrace();
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
        URL apiUrl = APP_DIR.toURI().toURL();
        try {
            domain.store(apiUrl, APP_URI);
            domain.install(APP_URI);
            domain.deploy(APP_URI);
            Thread.sleep(WAIT);
            System.out.println("Application deployed");
        } catch (Exception e) {
            ExceptionHelper.handleValidationError(e);
            throw e;
        }
    }

    private void removeApplications() throws Exception {
        domain.undeploy(APP_URI, true);
        domain.uninstall(APP_URI);
        domain.remove(APP_URI);

        domain.disconnect();

        Thread.sleep(WAIT);
        System.out.println("Application removed");
    }


}