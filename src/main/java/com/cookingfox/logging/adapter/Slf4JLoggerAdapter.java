package com.cookingfox.logging.adapter;

import com.cookingfox.logging.api.Entry;
import com.cookingfox.logging.api.LoggerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Log adapter implementation using SLF4J library.
 */
public class Slf4JLoggerAdapter implements LoggerAdapter {

    //----------------------------------------------------------------------------------------------
    // PRIVATE PROPERTIES
    //----------------------------------------------------------------------------------------------

    /**
     * Collection of loggers, where the key is the caller class name.
     */
    private final Map<String, Logger> loggers = new LinkedHashMap<>();

    //----------------------------------------------------------------------------------------------
    // PUBLIC METHODS
    //----------------------------------------------------------------------------------------------

    @Override
    public void debug(Entry entry) {
        getLogger(entry.getCaller()).debug(entry.getMessage());
    }

    @Override
    public void error(Entry entry) {
        getLogger(entry.getCaller()).error(entry.getMessage());
    }

    @Override
    public void info(Entry entry) {
        getLogger(entry.getCaller()).info(entry.getMessage());
    }

    @Override
    public void verbose(Entry entry) {
        getLogger(entry.getCaller()).trace(entry.getMessage());
    }

    @Override
    public void warn(Entry entry) {
        getLogger(entry.getCaller()).warn(entry.getMessage());
    }

    //----------------------------------------------------------------------------------------------
    // PRIVATE METHODS
    //----------------------------------------------------------------------------------------------

    /**
     * Creates and / or returns an SLF4J Logger instance for the calling class.
     *
     * @param caller The name of calling class.
     * @return SLF4J Logger instance.
     */
    private Logger getLogger(String caller) {
        Logger logger = loggers.get(caller);

        if (null == logger) {
            logger = LoggerFactory.getLogger(caller);
            loggers.put(caller, logger);
        }

        return logger;
    }

}
