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

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import junit.framework.TestCase;
import org.oasisopen.sca.annotation.Property;

/**
 * Tests that a media type can be handled by a custom JAX-RS MessageBodyReader contributed as an extension to the JAX-RS binding (test-binding-rs-extension).
 */
public class CustomMediaTestClient extends TestCase {

    @Property
    protected String baseUri;

    public void testJSONCreate() {
        Client client = ClientBuilder.newClient();
        WebTarget resource = client.target(UriBuilder.fromUri(baseUri).path("message").path("1").build());
        Response result = resource.request("application/foo").header("Content-Type", "application/foo").post(Entity.entity("test", "application/foo"));
        assertEquals(200, result.getStatus());
    }

}
