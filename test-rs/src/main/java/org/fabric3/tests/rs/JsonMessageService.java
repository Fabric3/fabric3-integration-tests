/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
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
package org.fabric3.tests.rs;

import java.net.URI;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.oasisopen.sca.annotation.Scope;


/**
 * @version $Rev$ $Date$
 */
@Path("/")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
@Scope("COMPOSITE")
public class JsonMessageService {
    private Map<Long, Message> messages = new ConcurrentHashMap<Long, Message>();

    @PUT
    @Path("/message")
    public Response create(Message message) {
        Long id = message.getId();
        messages.put(id, message);
        return Response.created(URI.create(id.toString())).build();
    }

    @GET
    @Path("message/{id}")
    public Message retrieve(@PathParam("id") Long id) {
        Message message = messages.get(id);
        if (message == null) {
            Response response = Response.status((Response.StatusType) Response.Status.NOT_FOUND).entity(new Reason("Not found")).build();
            throw new WebApplicationException(response);
        }
        return message;
    }

    @DELETE
    @Path("message/{id}")
    public Response delete(@PathParam("id") Long id) {
        messages.remove(id);
        return Response.ok().build();
    }


}
