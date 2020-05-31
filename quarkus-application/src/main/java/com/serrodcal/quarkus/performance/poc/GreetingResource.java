package com.serrodcal.quarkus.performance.poc;

import org.jboss.logging.Logger;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/greeting")
public class GreetingResource {

    private static final Logger logger = Logger.getLogger(GreetingResource.class);

    @Inject
    GreetingService service;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("{name}")
    public String greeting(@PathParam String name) {
        logger.info("GreetingResource.greeting()");
        logger.debug("GreetingResource.greeting("+ name +")");
        return this.service.greeting(name);
    }
}