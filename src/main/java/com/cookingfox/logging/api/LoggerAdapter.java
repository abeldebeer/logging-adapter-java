package com.cookingfox.logging.api;

/**
 * General log adapter interface.
 */
public interface LoggerAdapter {

    /**
     * Log a message at debug level.
     *
     * @param entry The log entry.
     */
    void debug(Entry entry);

    /**
     * Log a message at error level.
     *
     * @param entry The log entry.
     */
    void error(Entry entry);

    /**
     * Log a message at info level.
     *
     * @param entry The log entry.
     */
    void info(Entry entry);

    /**
     * Log a message at verbose level.
     *
     * @param entry The log entry.
     */
    void verbose(Entry entry);

    /**
     * Log a message at warning level.
     *
     * @param entry The log entry.
     */
    void warn(Entry entry);

}
