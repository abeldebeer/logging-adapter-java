package com.cookingfox.logging.adapter;

import com.cookingfox.logging.Level;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Log adapter implementation using System.out.println.
 */
public class SystemOutAdapter implements Adapter {

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
    public void debug(String caller, String message) {
        log(Level.DEBUG, caller, message);
    }

    @Override
    public void error(String caller, String message) {
        log(Level.ERROR, caller, message);
    }

    @Override
    public void info(String caller, String message) {
        log(Level.INFO, caller, message);
    }

    @Override
    public void verbose(String caller, String message) {
        log(Level.VERBOSE, caller, message);
    }

    @Override
    public void warn(String caller, String message) {
        log(Level.WARN, caller, message);
    }

    //----------------------------------------------------------------------------------------------
    // PRIVATE METHODS
    //----------------------------------------------------------------------------------------------

    /**
     * Output log message using System.out.println.
     *
     * @param level   The logging level.
     * @param caller  The calling class name.
     * @param message The log message.
     */
    private void log(Level level, String caller, String message) {
        // use first character of level name
        String levelId = level.toString().substring(0, 1);

        System.out.println(dateFormat.format(new Date()) + " " +
                levelId + "/" + caller +
                "(" + Thread.currentThread().getId() + ") : " + message);
    }

}
