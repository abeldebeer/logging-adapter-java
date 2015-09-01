package com.cookingfox.logging.adapter;

/**
 * General log adapter interface.
 */
public interface Adapter {

    /**
     * Log a message at debug level.
     *
     * @param caller  The name of the calling class.
     * @param message The message to log.
     */
    void debug(String caller, String message);

    /**
     * Log a message at error level.
     *
     * @param caller  The name of the calling class.
     * @param message The message to log.
     */
    void error(String caller, String message);

    /**
     * Log a message at info level.
     *
     * @param caller  The name of the calling class.
     * @param message The message to log.
     */
    void info(String caller, String message);

    /**
     * Log a message at verbose level.
     *
     * @param caller  The name of the calling class.
     * @param message The message to log.
     */
    void verbose(String caller, String message);

    /**
     * Log a message at warning level.
     *
     * @param caller  The name of the calling class.
     * @param message The message to log.
     */
    void warn(String caller, String message);

}
