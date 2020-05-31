package com.serrodcal.quarkus.performance.poc;

import io.quarkus.vertx.ConsumeEvent;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GreetingService {

    private static final Logger logger = Logger.getLogger(GreetingService.class);

    @ConsumeEvent("greeting")
    public String greeting (String name) {
        logger.info("GreetingService.greeting()");
        logger.debug("GreetingService.greeting("+ name +")");
        return "Hi, " + name;
    }

}
