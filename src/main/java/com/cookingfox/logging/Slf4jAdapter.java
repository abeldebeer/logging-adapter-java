package com.cookingfox.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Abel de Beer <abel@cookingfox.nl> on 31/08/15.
 */
public class Slf4jAdapter implements Adapter {
    private Logger logger;

    public Slf4jAdapter(String caller) {
        logger = LoggerFactory.getLogger(caller);
    }

    @Override
    public void debug(String message) {
        logger.debug(message);
    }

    @Override
    public void error(String message) {
        logger.error(message);
    }

    @Override
    public void info(String message) {
        logger.info(message);
    }

    @Override
    public void verbose(String message) {
        logger.trace(message);
    }

    @Override
    public void warn(String message) {
        logger.warn(message);
    }
}
