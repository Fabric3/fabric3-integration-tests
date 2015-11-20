/*
 * Fabric3
 * Copyright (c) 2009-2014 Metaform Systems
 *
 * Fabric3 is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of
 * the License, or (at your option) any later version, with the
 * following exception:
 *
 * Linking this software statically or dynamically with other
 * modules is making a combined work based on this software.
 * Thus, the terms and conditions of the GNU General Public
 * License cover the whole combination.
 *
 * As a special exception, the copyright holders of this software
 * give you permission to link this software with independent
 * modules to produce an executable, regardless of the license
 * terms of these independent modules, and to copy and distribute
 * the resulting executable under terms of your choice, provided
 * that you also meet, for each linked independent module, the
 * terms and conditions of the license of that module. An
 * independent module is a module which is not derived from or
 * based on this software. If you modify this software, you may
 * extend this exception to your version of the software, but
 * you are not obligated to do so. If you do not wish to do so,
 * delete this exception statement from your version.
 *
 * Fabric3 is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty
 * of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 *
 * You should have received a copy of the
 * GNU General Public License along with Fabric3.
 * If not, see <http://www.gnu.org/licenses/>.
*/
package org.fabric3.tests.rs;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

import org.fabric3.api.Namespaces;
import org.fabric3.api.annotation.model.Component;

/**
 *
 */
@Provider
@Priority(Priorities.AUTHENTICATION)
@Component(composite = Namespaces.F3_PREFIX + "ScannedComposite")
public class TestRequestFilter implements ContainerRequestFilter {
    public static boolean SET = false;

    @Context
    protected UriInfo uriInfo;

    @Context
    protected Application application;

    @Context
    protected Request request;

    @Context
    protected HttpHeaders headers;

    @Context
    protected SecurityContext securityContext;

    public void filter(ContainerRequestContext requestContext) throws IOException {
        if (uriInfo.getPath() == null) {
            throw new AssertionError("UriInfo not valid");
        }

        if (application == null) {
            throw new AssertionError("Application is null");
        }

        if (request == null) {
            throw new AssertionError("Request is null");
        }

        if (headers == null) {
            throw new AssertionError("HttpHeaders are null");
        }

        if (securityContext == null) {
            throw new AssertionError("SecurityContext is null");
        }

        SET = true;
    }
}


