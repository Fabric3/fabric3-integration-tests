/*
 * See the NOTICE file distributed with this work for information
 * regarding copyright ownership.  This file is licensed
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package tests.rs;

import javax.ws.rs.core.UriBuilder;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import junit.framework.TestCase;
import org.osoa.sca.annotations.Property;

/**
 * @version $Rev$ $Date$
 */
public class TestClient extends TestCase {

    @Property
    protected String hostURI;

    public TestClient() {
    }

    public void testEcho() {
        UriBuilder uri = UriBuilder.fromUri(hostURI).path("echo");
        WebResource resource = Client.create().resource(uri.path("Hello").build());
        assertEquals("Hello World", resource.post(String.class, "World"));
    }

    public void testEntity() {
        UriBuilder uri = UriBuilder.fromUri(hostURI).path("echo");
        Entity entity = new Entity();
        entity.setValue("World");
        ClientConfig cc = new DefaultClientConfig();
        cc.getClasses().add(EntityProvider.class);
        Client c = Client.create(cc);
        WebResource resource = c.resource(uri.path("Hello").build());
        ClientResponse response = resource.accept("application/entity").type("application/entity").post(ClientResponse.class, entity);
        assertNotNull(response);
        entity = response.getEntity(Entity.class);
        assertNotNull(entity);
        assertEquals("Hello World", entity.getValue());
    }

}
