package com.cookingfox.logging;

import com.cookingfox.logging.adapter.Adapter;

/**
 * Created by Abel de Beer <abel@cookingfox.nl> on 01/09/15.
 */
public final class Logger {

    //----------------------------------------------------------------------------------------------
    // PRIVATE ENUMS
    //----------------------------------------------------------------------------------------------

    private enum Level {
        DEBUG, ERROR, INFO, VERBOSE, WARN
    }

    //----------------------------------------------------------------------------------------------
    // PRIVATE STATIC PROPERTIES
    //----------------------------------------------------------------------------------------------

    private static Logger INSTANCE;

    //----------------------------------------------------------------------------------------------
    // INTERNAL STATIC METHODS
    //----------------------------------------------------------------------------------------------

    static void reset() {
        INSTANCE = null;
    }

    //----------------------------------------------------------------------------------------------
    // PUBLIC STATIC METHODS
    //----------------------------------------------------------------------------------------------

    public static Settings init() {
        if (null == INSTANCE) {
            INSTANCE = new Logger();
        }

        return INSTANCE.settings;
    }

    public static void d(String message, Object... args) {
        INSTANCE.call(Level.DEBUG, message, args);
    }

    public static void e(String message, Object... args) {
        INSTANCE.call(Level.ERROR, message, args);
    }

    public static void i(String message, Object... args) {
        INSTANCE.call(Level.INFO, message, args);
    }

    public static void v(String message, Object... args) {
        INSTANCE.call(Level.VERBOSE, message, args);
    }

    public static void w(String message, Object... args) {
        INSTANCE.call(Level.WARN, message, args);
    }

    //----------------------------------------------------------------------------------------------
    // PRIVATE PROPERTIES
    //----------------------------------------------------------------------------------------------

    private final String loggerClassName;
    private final Settings settings;

    //----------------------------------------------------------------------------------------------
    // CONSTRUCTOR
    //----------------------------------------------------------------------------------------------

    private Logger() {
        loggerClassName = getClass().getName();
        settings = new Settings();
    }

    //----------------------------------------------------------------------------------------------
    // PRIVATE METHODS
    //----------------------------------------------------------------------------------------------

    private void call(Level level, String message, Object... args) {
        if (!settings.enabled || settings.adapters.isEmpty()) {
            return;
        }

        String caller = getCallerClassName();

        if (settings.useSimpleClassName) {
            caller = caller.substring(caller.lastIndexOf('.') + 1);
        }

        if (args.length > 0) {
            message = String.format(message, args);
        }

        for (Adapter adapter : settings.adapters) {
            switch (level) {
                case DEBUG:
                    adapter.debug(caller, message);
                    break;
                case ERROR:
                    adapter.error(caller, message);
                    break;
                case INFO:
                    adapter.info(caller, message);
                    break;
                case VERBOSE:
                    adapter.verbose(caller, message);
                    break;
                case WARN:
                    adapter.warn(caller, message);
                    break;
            }
        }
    }

    private String getCallerClassName() {
        boolean foundLogger = false;

        for (StackTraceElement trace : Thread.currentThread().getStackTrace()) {
            String traceClassName = trace.getClassName();
            boolean traceIsLogger = traceClassName.equals(loggerClassName);

            if (foundLogger && !traceIsLogger) {
                return traceClassName;
            }

            foundLogger = traceIsLogger;
        }

        // default to logger class name
        return loggerClassName;
    }

}
