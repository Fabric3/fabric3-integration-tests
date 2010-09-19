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

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.UriBuilder;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientRequest;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.ClientFilter;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import junit.framework.TestCase;
import org.codehaus.jackson.jaxrs.JacksonJaxbJsonProvider;
import org.osoa.sca.annotations.Property;

import org.fabric3.tests.rs.security.UsernamePasswordToken;

/**
 * @version $Rev$ $Date$
 */
public class SecureTestClient extends TestCase {

    @Property
    protected String authentication;
    @Property
    protected String baseUri;

    UriBuilder uri;
    private Client client;

    public void testSecureAccessFail() {
        WebResource resource = client.resource(uri.path("").build());

        try {
            resource.type(MediaType.APPLICATION_JSON).get(String.class);
            fail();
        } catch (UniformInterfaceException e) {
            assertEquals(401, e.getResponse().getStatus());
            assertNotNull(e.getResponse().getHeaders().get("WWW-Authenticate"));
        }
    }

    public void testBasicAuthInvalidPassword() {
        client.addFilter(new HTTPBasicAuthFilter("foo", "invalid"));
        WebResource resource = client.resource(uri.path("").build());
        try {
            resource.type(MediaType.APPLICATION_JSON).get(String.class);
            fail();
        } catch (UniformInterfaceException e) {
            assertEquals(403, e.getResponse().getStatus());
        }
    }

    public void testBasicAuthNotAuthorized() {
        client.addFilter(new HTTPBasicAuthFilter("baz", "fred"));
        WebResource resource = client.resource(uri.path("").build());
        try {
            resource.type(MediaType.APPLICATION_JSON).get(String.class);
            fail();
        } catch (UniformInterfaceException e) {
            assertEquals(403, e.getResponse().getStatus());
        }
    }

    public void testBasicAuth() {
        client.addFilter(new HTTPBasicAuthFilter("foo", "bar"));
        WebResource resource = client.resource(uri.path("").build());
        resource.type(MediaType.APPLICATION_JSON).get(String.class);
    }

    public void testFabricLogin() {
        client.addFilter(new ClientFilter() {
            private List<NewCookie> cookies = new ArrayList<NewCookie>();

            @Override
            public ClientResponse handle(ClientRequest cr) throws ClientHandlerException {
                for (NewCookie cookie : cookies) {
                    cr.getHeaders().putSingle("Cookie", cookie.getName() + "=" + cookie.getValue());
                }
                ClientResponse resp = getNext().handle(cr);
                for (NewCookie cookie : resp.getCookies()) {
                    cookies.add(cookie);
                }
                return resp;
            }
        });
        UriBuilder authBuilder = UriBuilder.fromUri(authentication);
        WebResource authResource = client.resource(authBuilder.path("").build());
        authResource.type(MediaType.APPLICATION_JSON_TYPE).post(new UsernamePasswordToken("foo", "bar"));
        WebResource resource = client.resource(uri.path("").build());
        resource.type(MediaType.APPLICATION_JSON).get(String.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        ClientConfig cc = new DefaultClientConfig();
        cc.getClasses().add(JacksonJaxbJsonProvider.class);
        client = Client.create(cc);

        uri = UriBuilder.fromUri(baseUri);

    }
}
