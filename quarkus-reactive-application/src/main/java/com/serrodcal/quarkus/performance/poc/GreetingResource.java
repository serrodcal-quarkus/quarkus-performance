package com.serrodcal.quarkus.performance.poc;

import io.smallrye.mutiny.Uni;
import org.jboss.logging.Logger;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/greeting")
public class GreetingResource {

    private static final Logger logger = Logger.getLogger(GreetingResource.class);

    @Inject
    GreetingService service;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("{name}")
    public Uni<Response> greeting(@PathParam String name) {
        logger.info("GreetingResource.greeting()");
        logger.debug("GreetingResource.greeting("+ name +")");
        return this.service.greeting(name)
                .onItem().apply(result -> Response.status(Response.Status.OK).entity(result).build())
                .onFailure().recoverWithItem(failure -> Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(failure.getMessage()).build());
    }
}