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

import javax.ws.rs.NotFoundException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;

import junit.framework.TestCase;
import org.fabric3.tests.rs.Message;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.oasisopen.sca.annotation.Property;

/**
 *
 */
public class TestClient extends TestCase {

    @Property
    protected String baseMessageUri;

    @Property
    protected String baseFilterUri;

    @Property
    protected String baseFilterNameUri;

    @Property
    protected String baseFilterNameMethodUri;

    @Property
    protected String baseJsonMessageUri;

    @Property
    protected String baseStatelessUri;

    @Property
    protected URI baseInterfaceUri;

    public TestClient() {
    }

    public void testJAXBCreate() {
        Message message = new Message(1L, "this is a test");
        message.setText("this is a test");

        Client client = ClientBuilder.newClient();
        UriBuilder uri = UriBuilder.fromUri(baseMessageUri);
        WebTarget resource = client.target(uri.path("message").build());

        Response response = resource.request().put(Entity.entity(message, MediaType.APPLICATION_XML));
        assertEquals(201, response.getStatus());

        resource = client.target(UriBuilder.fromUri(baseMessageUri).path("message").path("1").build());
        Message result = resource.request(MediaType.APPLICATION_XML).get(Message.class);
        assertEquals("this is a test", result.getText());
    }

    public void testJSONCreate() {
        Message message = new Message(1L, "this is a test");
        message.setText("this is a test");

        Client client = ClientBuilder.newClient();
        client.register(JacksonFeature.class);
        UriBuilder uri = UriBuilder.fromUri(baseJsonMessageUri);
        WebTarget resource = client.target(uri.path("message").build());
        Response response = resource.request(MediaType.APPLICATION_JSON).put(Entity.entity(message, MediaType.APPLICATION_JSON));
        assertEquals(201, response.getStatus());

        resource = client.target(UriBuilder.fromUri(baseJsonMessageUri).path("message").path("1").build());
        Message result = resource.request(MediaType.APPLICATION_JSON).get(Message.class);
        assertEquals("this is a test", result.getText());
    }

    public void testNotExist() {
        Client client = ClientBuilder.newClient();
        WebTarget resource = client.target(UriBuilder.fromUri(baseMessageUri).path("message").path("2").build());
        try {
            resource.request().get(Message.class);
            fail();
        } catch (NotFoundException e) {
            // expected
        }
    }

    public void testStateless() {
        Client client = ClientBuilder.newClient();
        UriBuilder uri = UriBuilder.fromUri(baseStatelessUri);
        WebTarget resource = client.target(uri.build());
        String response = resource.request().get(String.class);
        assertEquals("0", response);
        response = resource.request().get(String.class);
        assertEquals("0", response);
    }

    public void testInterface() {
        Client client = ClientBuilder.newClient();
        UriBuilder uri = UriBuilder.fromUri(baseInterfaceUri);
        WebTarget resource = client.target(uri.build());
        String response = resource.request().get(String.class);
        assertEquals("test", response);
    }

    public void testFilters() {
        Message message = new Message(1L, "this is a test");
        message.setText("this is a test");

        Client client = ClientBuilder.newClient();

        WebTarget resource = client.target(UriBuilder.fromUri(baseFilterUri).path("message").path("1").build());
        Message result = resource.request(MediaType.APPLICATION_XML).get(Message.class);
        assertEquals("this is a test", result.getText());

        resource = client.target(UriBuilder.fromUri(baseFilterNameUri).path("message").path("1").build());
        result = resource.request(MediaType.APPLICATION_XML).get(Message.class);
        assertEquals("this is a test", result.getText());

        resource = client.target(UriBuilder.fromUri(baseFilterNameMethodUri).path("message").path("1").build());
        result = resource.request(MediaType.APPLICATION_XML).get(Message.class);
        assertEquals("this is a test", result.getText());

        resource = client.target(UriBuilder.fromUri(baseFilterNameMethodUri).path("messagenofilter").path("1").build());
        result = resource.request(MediaType.APPLICATION_XML).get(Message.class);
        assertEquals("this is a test", result.getText());

    }

}
