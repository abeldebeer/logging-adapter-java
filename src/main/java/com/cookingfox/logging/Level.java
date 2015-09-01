package com.cookingfox.logging;

/**
 * Log level enumeration.
 */
public enum Level {

    /**
     * The ERROR level designates error events that might still allow the application to continue
     * running.
     */
    ERROR,

    /**
     * The WARN level designates potentially harmful situations.
     */
    WARN,

    /**
     * The INFO level designates informational messages that highlight the progress of the
     * application at coarse-grained level.
     */
    INFO,

    /**
     * The DEBUG level designates fine-grained informational events that are most useful to debug
     * an application.
     */
    DEBUG,

    /**
     * The VERBOSE has the lowest possible rank and is intended to turn on all logging.
     */
    VERBOSE

}
