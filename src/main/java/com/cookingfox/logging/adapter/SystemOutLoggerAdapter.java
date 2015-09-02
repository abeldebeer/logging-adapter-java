package com.cookingfox.logging.adapter;

import com.cookingfox.logging.Level;
import com.cookingfox.logging.api.Entry;
import com.cookingfox.logging.api.LoggerAdapter;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Log adapter implementation using System.out.println.
 */
public class SystemOutLoggerAdapter implements LoggerAdapter {

    //----------------------------------------------------------------------------------------------
    // PRIVATE PROPERTIES
    //----------------------------------------------------------------------------------------------

    /**
     * Date format to use for log message.
     */
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd HH:mm:ss.SSS");

    //----------------------------------------------------------------------------------------------
    // PUBLIC METHODS
    //----------------------------------------------------------------------------------------------

    @Override
    public void debug(Entry entry) {
        log(Level.DEBUG, entry);
    }

    @Override
    public void error(Entry entry) {
        log(Level.ERROR, entry);
    }

    @Override
    public void info(Entry entry) {
        log(Level.INFO, entry);
    }

    @Override
    public void verbose(Entry entry) {
        log(Level.VERBOSE, entry);
    }

    @Override
    public void warn(Entry entry) {
        log(Level.WARN, entry);
    }

    //----------------------------------------------------------------------------------------------
    // PRIVATE METHODS
    //----------------------------------------------------------------------------------------------

    /**
     * Output log message using System.out.println.
     *
     * @param level The logging level.
     * @param entry The log entry.
     */
    private void log(Level level, Entry entry) {
        // use first character of level name
        String levelId = level.toString().substring(0, 1);

        System.out.println(dateFormat.format(new Date()) + " " +
                levelId + "/" + entry.getCaller() +
                "(" + Thread.currentThread().getId() + ") : " + entry.getMessage());
    }

}
