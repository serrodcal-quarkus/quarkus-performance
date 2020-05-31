package com.serrodcal.quarkus.performance.poc;

import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.core.eventbus.EventBus;
import io.vertx.mutiny.core.eventbus.Message;
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
    EventBus bus;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("{name}")
    public Uni<String> greeting(@PathParam String name) {
        logger.info("Request received in EventResource.greeting()");
        logger.debug("Name " + name + " received in EventResource.greeting()");
        return bus.<String>request("greeting", name).onItem().apply(Message::body);
    }

}