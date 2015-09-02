package com.cookingfox.logging.api;

/**
 * Class representing one logging entry.
 */
public interface Entry {

    /**
     * Returns the name / identifier of the element that called the Logger method.
     */
    String getCaller();

    /**
     * Returns the message the user wants logged.
     */
    String getMessage();

}
