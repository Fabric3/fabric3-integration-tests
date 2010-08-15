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

import java.net.URI;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import junit.framework.TestCase;
import org.codehaus.jackson.jaxrs.JacksonJaxbJsonProvider;
import org.osoa.sca.annotations.Property;

import org.fabric3.tests.rs.Message;

/**
 * @version $Rev$ $Date$
 */
public class TestClient extends TestCase {

    @Property
    protected String baseMessageUri;

    @Property
    protected String baseJsonMessageUri;

    @Property
    protected String baseStatelessUri;

    @Property
    protected URI baseInterfaceUri;

    public TestClient() {
    }

    public void testJAXBCreate() {
        Message message = new Message();
        message.setId(1L);
        message.setText("this is a test");

        Client client = Client.create();
        UriBuilder uri = UriBuilder.fromUri(baseMessageUri);
        WebResource resource = client.resource(uri.path("message").build());

        resource.put(message);

        resource = client.resource(uri.path("1").build());
        Message response = resource.get(Message.class);
        assertEquals("this is a test", response.getText());
    }

    public void testJSONCreate() {
        Message message = new Message();
        message.setId(1L);
        message.setText("this is a test");

        ClientConfig cc = new DefaultClientConfig();
        cc.getClasses().add(JacksonJaxbJsonProvider.class);
        Client client = Client.create(cc);
        UriBuilder uri = UriBuilder.fromUri(baseJsonMessageUri);
        WebResource resource = client.resource(uri.path("message").build());

        resource.type(MediaType.APPLICATION_JSON).put(message);
        resource = client.resource(uri.path("1").build());

        Message response = resource.type(MediaType.APPLICATION_JSON).get(Message.class);
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

    public void testInterface() {
        Client client = Client.create();
        UriBuilder uri = UriBuilder.fromUri(baseInterfaceUri);
        WebResource resource = client.resource(uri.build());
        String response = resource.get(String.class);
        assertEquals("test", response);
    }

}
