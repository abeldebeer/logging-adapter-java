package com.cookingfox.logging.adapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Abel de Beer <abel@cookingfox.nl> on 31/08/15.
 */
public class Slf4jAdapter implements Adapter {
    private final Map<String, Logger> loggers = new LinkedHashMap<>();

    @Override
    public void debug(String caller, String message) {
        getLogger(caller).debug(message);
    }

    @Override
    public void error(String caller, String message) {
        getLogger(caller).error(message);
    }

    @Override
    public void info(String caller, String message) {
        getLogger(caller).info(message);
    }

    @Override
    public void verbose(String caller, String message) {
        getLogger(caller).trace(message);
    }

    @Override
    public void warn(String caller, String message) {
        getLogger(caller).warn(message);
    }

    private Logger getLogger(String caller) {
        Logger logger = loggers.get(caller);

        if (null == logger) {
            logger = LoggerFactory.getLogger(caller);
            loggers.put(caller, logger);
        }

        return logger;
    }
}
