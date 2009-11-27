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

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.oasisopen.sca.annotation.Reference;
import org.oasisopen.sca.annotation.Service;
import org.osoa.sca.annotations.Property;

/**
 * @version $Rev$ $Date$
 */
@Service(EchoService.class)
@Path("/Hello")
public class EchoResource implements EchoService {

    public
    @Reference
    EchoService service;
    public
    @Property
    String message;

    public EchoResource() {
        System.out.println("Hello");
    }

    @POST
    @Produces("text/plain")
    @Consumes("application/x-www-form-urlencoded")
    public String hello(String name) {
        return message + " " + service.hello(name);
    }

    @POST
    @Produces("application/entity")
    public Entity helloEntity(Entity entity) {
        entity.setValue(message + " " + service.helloEntity(entity).getValue());
        return entity;
    }
}
