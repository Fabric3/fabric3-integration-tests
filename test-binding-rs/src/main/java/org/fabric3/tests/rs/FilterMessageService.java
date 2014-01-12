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

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.oasisopen.sca.annotation.Scope;

/**
 *
 */
@Path("/")
@Consumes({MediaType.APPLICATION_XML})
@Produces({MediaType.APPLICATION_XML})
@Scope("COMPOSITE")
public class FilterMessageService {

    @GET
    @Path("message/{id}")
    public Message retrieve(@PathParam("id") Long id) {
        try {
            if (!TestRequestFilter.SET) {
                throw new AssertionError("Global filter not set");
            }
            if (TestNameRequestFilter.SET) {
                throw new AssertionError("Name filter improperly applied");
            }
            return new Message(id, "this is a test");
        } finally {
            TestRequestFilter.SET = false;
            TestNameRequestFilter.SET = false;
        }
    }

}
