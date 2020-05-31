package com.serrodcal.quarkus.performance.poc;

import io.smallrye.mutiny.Uni;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GreetingService {

    private static final Logger logger = Logger.getLogger(GreetingService.class);

    public Uni<String> greeting(String name) {
        logger.info("GreetingService.greeting()");
        logger.debug("GreetingService.greeting("+ name +")");
        return Uni.createFrom().item("Hi, " + name);
    }

}
