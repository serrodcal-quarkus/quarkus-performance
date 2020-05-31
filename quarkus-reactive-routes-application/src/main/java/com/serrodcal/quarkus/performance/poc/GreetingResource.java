package com.serrodcal.quarkus.performance.poc;

import io.quarkus.vertx.web.Route;
import io.vertx.core.http.HttpHeaders;
import io.vertx.ext.web.RoutingContext;
import io.vertx.core.http.HttpMethod;
import org.apache.http.HttpStatus;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class GreetingResource {

    private static final Logger logger = Logger.getLogger(GreetingResource.class);

    private final String TEXT_PLAIN = "text/plain";

    @Inject
    GreetingService service;

    @Route(path = "/greeting/:name", methods = HttpMethod.GET)
    void greeting(RoutingContext rc) {
        logger.info("GreetingResource.greeting()");
        logger.debug("GreetingResource.greeting("+ rc.pathParam("name") +")");
        this.service.greeting(rc.pathParam("name")).subscribe().with(
                result -> rc.response().putHeader(HttpHeaders.CONTENT_TYPE, this.TEXT_PLAIN).end(result),
                failure -> rc.response().setStatusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR).putHeader(HttpHeaders.CONTENT_TYPE, this.TEXT_PLAIN).end(failure.getMessage())
        );
    }
}