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
package org.fabric3.tests.standalone.vm;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.Set;
import javax.management.InstanceNotFoundException;
import javax.management.MBeanException;
import javax.management.MBeanServerConnection;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.ReflectionException;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

import junit.framework.TestCase;
import org.junit.Assert;

import org.fabric3.admin.api.DomainController;
import org.fabric3.admin.impl.DomainControllerImpl;
import org.fabric3.management.contribution.ContributionInfo;

/**
 * Runs basic smoketests for the standalone runtime booted in single-VM mode.
 *
 * @version $Rev$ $Date$
 */
public class DeployUndeploy extends TestCase {

    private static final File APP_DIR = new File(".." + File.separator
            + "test-standalone-app" + File.separator
            + "target" + File.separator
            + "test-standalone-app-0.1-SNAPSHOT.jar");

    private static String DOMAIN_ADDRESS = "service:jmx:rmi:///jndi/rmi://localhost:1099/server";


    public void testDeployUndeploy() throws Exception {
        Exception exception = null;
        DomainController domain = new DomainControllerImpl();
        for (int i = 0; i < 50; i++) {   // wait 5 seconds
            Thread.sleep(100);
            try {
                domain.connect();
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
        URL url = APP_DIR.toURI().toURL();
        URI uri = URI.create("test-standalone-app-0.1-SNAPSHOT.jar");
        // deploy and undeploy twice
        for (int i = 0; i < 2; i++) {   // wait 5 seconds
            domain.store(url, uri);
            domain.install(uri);
            domain.deploy(uri);
            assertEquals("test", invokeTestService());
            domain.undeploy(uri);
            domain.uninstall(uri);
            domain.remove(uri);
        }
        domain.disconnect();
    }


    private String invokeTestService()
            throws IOException, ReflectionException, InstanceNotFoundException, MBeanException, MalformedObjectNameException {
        JMXServiceURL url = new JMXServiceURL(DOMAIN_ADDRESS);
        JMXConnector jmxc = JMXConnectorFactory.connect(url, null);
        MBeanServerConnection conn = jmxc.getMBeanServerConnection();
        ObjectName oName = new ObjectName("fabric3:SubDomain=domain, type=component, name=TestService");
        return (String) conn.invoke(oName, "invoke", new Object[]{}, new String[]{});
    }


}