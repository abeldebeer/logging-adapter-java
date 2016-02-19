package com.cookingfox.logging;

import com.cookingfox.logging.api.Entry;
import com.cookingfox.logging.api.LoggerAdapter;

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
        return null == INSTANCE ? INSTANCE = new Logger() : INSTANCE;
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
     * @see Level#DEBUG
     */
    public static void debug() {
        getInstance().log(Level.DEBUG, "");
    }

    /**
     * Log a message at debug level.
     *
     * @param value The message to log.
     * @see Level#DEBUG
     */
    public static void debug(Object value) {
        getInstance().log(Level.DEBUG, "%s", value);
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
     * @see Level#ERROR
     */
    public static void error() {
        getInstance().log(Level.ERROR, "");
    }

    /**
     * Log a message at error level.
     *
     * @param value The message to log.
     * @see Level#ERROR
     */
    public static void error(Object value) {
        getInstance().log(Level.ERROR, "%s", value);
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
     * @see Level#INFO
     */
    public static void info() {
        getInstance().log(Level.INFO, "");
    }

    /**
     * Log a message at info level.
     *
     * @param value The message to log.
     * @see Level#INFO
     */
    public static void info(Object value) {
        getInstance().log(Level.INFO, "%s", value);
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
     * @see Level#VERBOSE
     */
    public static void verbose() {
        getInstance().log(Level.VERBOSE, "");
    }

    /**
     * Log a message at verbose level.
     *
     * @param value The message to log.
     * @see Level#VERBOSE
     */
    public static void verbose(Object value) {
        getInstance().log(Level.VERBOSE, "%s", value);
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
     * Log a message at warn level.
     *
     * @see Level#WARN
     */
    public static void warn() {
        getInstance().log(Level.WARN, "");
    }

    /**
     * Log a message at warn level.
     *
     * @param value The message to log.
     * @see Level#WARN
     */
    public static void warn(Object value) {
        getInstance().log(Level.WARN, "%s", value);
    }

    /**
     * Log a message at warn level.
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

    /**
     * Output the log message.
     *
     * @param level   The logging level.
     * @param message The message to log.
     * @param args    Values that need to be included in the log message
     *                (through {@link String#format(String, Object...)}).
     */
    private void log(Level level, String message, Object... args) {
        final LoggerAdapter loggerAdapter = settings.loggerAdapter;

        // disabled or no adapter: skip
        if (!settings.enabled || loggerAdapter == null) {
            return;
        }

        final Entry entry = createLoggerEntry(message, args);

        // call the log method corresponding to the level
        switch (level) {
            case DEBUG:
                loggerAdapter.debug(entry);
                break;
            case ERROR:
                loggerAdapter.error(entry);
                break;
            case INFO:
                loggerAdapter.info(entry);
                break;
            case VERBOSE:
                loggerAdapter.verbose(entry);
                break;
            case WARN:
                loggerAdapter.warn(entry);
                break;
        }
    }

    /**
     * Create an Entry object using the logging parameters.
     *
     * @param message The message to log.
     * @param args    Values that need to be included in the log message
     *                (through {@link String#format(String, Object...)}).
     */
    private Entry createLoggerEntry(String message, Object[] args) {
        // message is null: change to empty string
        if (null == message) {
            message = "";
        }

        LoggerEntry entry = new LoggerEntry(message, args);
        entry.defaultCallerClassName = LOGGER_CLASS_NAME;
        entry.settings = settings;
        entry.stackTrace = getCallerStackTrace();

        return entry;
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
