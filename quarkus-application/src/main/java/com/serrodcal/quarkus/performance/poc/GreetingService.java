package com.serrodcal.quarkus.performance.poc;

import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GreetingService {

    private static final Logger logger = Logger.getLogger(GreetingService.class);

    public String greeting(String name) {
        logger.info("GreetingService.greeting()");
        logger.debug("GreetingService.greeting("+ name +")");
        return "Hi, " + name;
    }

}
