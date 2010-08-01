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
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import junit.framework.TestCase;
import org.osoa.sca.annotations.Property;

import org.fabric3.tests.rs.Message;

/**
 * @version $Rev$ $Date$
 */
public class TestClient extends TestCase {

    @Property
    protected String baseMessageUri;
    @Property
    protected String baseStatelessUri;

    public TestClient() {
    }

    public void testJAXBCreate() {
        Client client = Client.create();
        UriBuilder uri = UriBuilder.fromUri(baseMessageUri);
        WebResource resource = client.resource(uri.path("message").build());
        Message message = new Message();
        message.setId(1L);
        message.setText("this is a test");
        resource.put(message);
        resource = client.resource(uri.path("1").build());
        Message response = resource.get(Message.class);
        assertEquals("this is a test", response.getText());
    }

    public void testNotExist() {
        Client client = Client.create();
        UriBuilder uri = UriBuilder.fromUri(baseMessageUri);
        WebResource resource = client.resource(uri.path("2").build());
        try {
            resource.get(Message.class);
            fail();
        } catch (UniformInterfaceException e) {
            assertEquals(ClientResponse.Status.NOT_FOUND, e.getResponse().getClientResponseStatus());
        }
    }

    public void testStateless() {
        Client client = Client.create();
        UriBuilder uri = UriBuilder.fromUri(baseStatelessUri);
        WebResource resource = client.resource(uri.build());
        String response = resource.get(String.class);
        assertEquals("0", response);
        response = resource.get(String.class);
        assertEquals("0", response);

    }

}
