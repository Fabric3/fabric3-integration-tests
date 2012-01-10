package org.fabric3.tests.rs;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
public interface RestReferenceInterface {
    
	@GET
    @Path("message/{id}")
    Message retrieve(@PathParam("id") Integer id);
	
	
    @PUT
    @Path("message")    
    void putMsg(Message msg);
    
}
