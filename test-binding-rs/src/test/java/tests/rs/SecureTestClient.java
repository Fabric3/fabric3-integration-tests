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

import javax.ws.rs.ForbiddenException;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.client.ClientResponseContext;
import javax.ws.rs.client.ClientResponseFilter;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import junit.framework.TestCase;
import org.fabric3.tests.rs.security.UsernamePasswordToken;
import org.glassfish.jersey.client.filter.HttpBasicAuthFilter;
import org.oasisopen.sca.annotation.Property;

/**
 *
 */
public class SecureTestClient extends TestCase {

    @Property
    protected String authentication;
    @Property
    protected String baseUri;

    UriBuilder uri;
    private Client client;

    public void testSecureAccessFail() {
        WebTarget resource = client.target(uri.path("").build());

        try {
            resource.request(MediaType.APPLICATION_JSON).get(String.class);
            fail();
        } catch (NotAuthorizedException e) {
            assertEquals(401, e.getResponse().getStatus());
            assertNotNull(e.getResponse().getHeaders().get("WWW-Authenticate"));
        }
    }

    public void testBasicAuthInvalidPassword() {
        client.register(new HttpBasicAuthFilter("foo", "invalid"));
        WebTarget resource = client.target(uri.path("").build());
        try {
            resource.request(MediaType.APPLICATION_JSON).get(String.class);
            fail();
        } catch (ForbiddenException e) {
            assertEquals(403, e.getResponse().getStatus());
        }
    }

    public void testBasicAuthNotAuthorized() {
        client.register(new HttpBasicAuthFilter("baz", "fred"));
        WebTarget resource = client.target(uri.path("").build());
        try {
            resource.request(MediaType.APPLICATION_JSON).get(String.class);
            fail();
        } catch (ForbiddenException e) {
            assertEquals(403, e.getResponse().getStatus());
        }
    }

    public void testBasicAuth() {
        client.register(new HttpBasicAuthFilter("foo", "bar"));
        WebTarget resource = client.target(uri.path("").build());
        resource.request(MediaType.APPLICATION_JSON).get(String.class);
    }

    public void testFabricLogin() {
        final List<NewCookie> cookies = new ArrayList<>();
        client.register(new ClientResponseFilter() {
            public void filter(ClientRequestContext requestContext, ClientResponseContext responseContext) throws IOException {
                cookies.addAll(responseContext.getCookies().values());
            }
        });
        client.register(new ClientRequestFilter() {

            public void filter(ClientRequestContext cr) {
                for (NewCookie cookie : cookies) {
                    cr.getHeaders().putSingle("Cookie", cookie.getName() + "=" + cookie.getValue());
                }
            }
        });
        UriBuilder authBuilder = UriBuilder.fromUri(authentication);
        WebTarget authResource = client.target(authBuilder.path("").build());
        Entity<UsernamePasswordToken> entity = Entity.entity(new UsernamePasswordToken("foo", "bar"), MediaType.APPLICATION_JSON);
        authResource.request(MediaType.APPLICATION_JSON_TYPE).post(entity);
        WebTarget resource = client.target(uri.path("").build());
        resource.request(MediaType.APPLICATION_JSON).get(String.class);
    }

    protected void setUp() throws Exception {
        super.setUp();

        client = ClientBuilder.newClient();
        client.register(JacksonJaxbJsonProvider.class);

        uri = UriBuilder.fromUri(baseUri);

    }
}
