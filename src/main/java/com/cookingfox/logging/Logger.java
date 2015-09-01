package com.cookingfox.logging;

import com.cookingfox.logging.adapter.Adapter;

/**
 * Helper class for logging. Use {@link #init()} to initialize and configure the Logger. Use the
 * other static methods for logging.
 *
 * @see Level
 */
public final class Logger {

    //----------------------------------------------------------------------------------------------
    // PRIVATE STATIC PROPERTIES
    //----------------------------------------------------------------------------------------------

    /**
     * Static Logger instance (singleton).
     */
    private static Logger INSTANCE;

    /**
     * Name of this class, used for filtering the stack trace entries when trying to determine the
     * calling class name.
     *
     * @see #getCallerClassName()
     */
    private static final String LOGGER_CLASS_NAME = Logger.class.getName();

    /**
     * The stack trace always includes a few entries that are related to the Logger internals, so
     * these can be skipped when trying to determine the calling class name.
     *
     * @see #getCallerClassName()
     */
    private static final int MIN_STACK_OFFSET = 3;

    //----------------------------------------------------------------------------------------------
    // INTERNAL STATIC METHODS
    //----------------------------------------------------------------------------------------------

    /**
     * Creates and / or returns the singleton Logger instance.
     */
    static Logger getInstance() {
        return INSTANCE == null ? INSTANCE = new Logger() : INSTANCE;
    }

    /**
     * Resets the logger. Only for internal use.
     */
    static void reset() {
        INSTANCE = null;
    }

    //----------------------------------------------------------------------------------------------
    // PUBLIC STATIC METHODS
    //----------------------------------------------------------------------------------------------

    /**
     * Initializes the Logger and returns its {@link Settings} object. Use the Settings object to
     * add adapters and configure the behavior of the logger.
     */
    public static Settings init() {
        return getInstance().settings;
    }

    /**
     * Log a message at debug level.
     *
     * @param message The message to log.
     * @param args    Values that need to be included in the log message
     *                (through {@link String#format(String, Object...)}).
     * @see Level#DEBUG
     */
    public static void debug(String message, Object... args) {
        getInstance().log(Level.DEBUG, message, args);
    }

    /**
     * Log a message at error level.
     *
     * @param message The message to log.
     * @param args    Values that need to be included in the log message
     *                (through {@link String#format(String, Object...)}).
     * @see Level#ERROR
     */
    public static void error(String message, Object... args) {
        getInstance().log(Level.ERROR, message, args);
    }

    /**
     * Log a message at info level.
     *
     * @param message The message to log.
     * @param args    Values that need to be included in the log message
     *                (through {@link String#format(String, Object...)}).
     * @see Level#INFO
     */
    public static void info(String message, Object... args) {
        getInstance().log(Level.INFO, message, args);
    }

    /**
     * Log a message at verbose level.
     *
     * @param message The message to log.
     * @param args    Values that need to be included in the log message
     *                (through {@link String#format(String, Object...)}).
     * @see Level#VERBOSE
     */
    public static void verbose(String message, Object... args) {
        getInstance().log(Level.VERBOSE, message, args);
    }

    /**
     * Log a message at warning level.
     *
     * @param message The message to log.
     * @param args    Values that need to be included in the log message
     *                (through {@link String#format(String, Object...)}).
     * @see Level#WARN
     */
    public static void warn(String message, Object... args) {
        getInstance().log(Level.WARN, message, args);
    }

    //----------------------------------------------------------------------------------------------
    // PRIVATE PROPERTIES
    //----------------------------------------------------------------------------------------------

    /**
     * Logger configuration helper.
     */
    private final Settings settings;

    //----------------------------------------------------------------------------------------------
    // CONSTRUCTOR
    //----------------------------------------------------------------------------------------------

    /**
     * Private constructor: use the public static methods for access.
     */
    private Logger() {
        settings = new Settings();
    }

    //----------------------------------------------------------------------------------------------
    // PRIVATE METHODS
    //----------------------------------------------------------------------------------------------

    private void log(Level level, String message, Object... args) {
        // disabled or no adapter: skip
        if (!settings.enabled || settings.adapters.isEmpty()) {
            return;
        }

        String caller = getCallerClassName();

        // simple class name: extract class name (strip package)
        if (settings.useSimpleClassName && caller.contains(".")) {
            caller = caller.substring(caller.lastIndexOf(".") + 1);
        }

        // arguments have been passed: use in String format
        if (args.length > 0) {
            message = String.format(message, args);
        }

        // call the log method corresponding to the level
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

    /**
     * Returns the name of the calling class by analyzing the stack trace. If somehow it cannot be
     * detected, this method will return the name of this Logger class.
     */
    private String getCallerClassName() {
        StackTraceElement[] stack = Thread.currentThread().getStackTrace();

        // the first few entries of the stack trace are always related to the Logger process, so try
        // to find the first entry that does is not the Logger.
        for (int i = MIN_STACK_OFFSET; i < stack.length - MIN_STACK_OFFSET; i++) {
            String className = stack[i].getClassName();

            if (!className.equals(LOGGER_CLASS_NAME)) {
                return className;
            }
        }

        // default to logger class name
        return LOGGER_CLASS_NAME;
    }

}
