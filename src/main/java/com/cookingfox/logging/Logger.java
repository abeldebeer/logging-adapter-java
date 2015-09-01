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
     * @see #getCallerStackTrace()
     */
    private static final String LOGGER_CLASS_NAME = Logger.class.getName();

    /**
     * The stack trace always includes a few entries that are related to the Logger internals, so
     * these can be skipped when trying to determine the calling class name.
     *
     * @see #getCallerStackTrace()
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

        // arguments have been passed: use in String format
        if (args.length > 0) {
            message = String.format(message, args);
        }

        StackTraceElement caller = getCallerStackTrace();
        String callerClassName = LOGGER_CLASS_NAME;
        String callerMethodName = null;

        if (null != caller) {
            callerClassName = caller.getClassName();
            callerMethodName = caller.getMethodName();
        }

        // simple class name: extract class name (strip package)
        if (settings.useSimpleClassName && callerClassName.contains(".")) {
            callerClassName = callerClassName.substring(callerClassName.lastIndexOf(".") + 1);
        }

        // include caller method name if not null
        if (settings.includeMethodName && null != callerMethodName) {
            message = String.format("%s | %s", callerMethodName, message);
        }

        // call the log method corresponding to the level
        for (Adapter adapter : settings.adapters) {
            switch (level) {
                case DEBUG:
                    adapter.debug(callerClassName, message);
                    break;
                case ERROR:
                    adapter.error(callerClassName, message);
                    break;
                case INFO:
                    adapter.info(callerClassName, message);
                    break;
                case VERBOSE:
                    adapter.verbose(callerClassName, message);
                    break;
                case WARN:
                    adapter.warn(callerClassName, message);
                    break;
            }
        }
    }

    /**
     * Finds the caller in the stack trace.
     *
     * @return The stack trace element of the caller.
     */
    private StackTraceElement getCallerStackTrace() {
        StackTraceElement[] stack = Thread.currentThread().getStackTrace();

        // the first few entries of the stack trace are always related to the Logger process, so try
        // to find the first entry that is not the Logger.
        for (int i = MIN_STACK_OFFSET; i < stack.length - MIN_STACK_OFFSET; i++) {
            StackTraceElement trace = stack[i];

            if (!trace.getClassName().equals(LOGGER_CLASS_NAME)) {
                return trace;
            }
        }

        return null;
    }

}
