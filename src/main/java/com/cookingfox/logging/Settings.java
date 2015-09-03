package com.cookingfox.logging;

import com.cookingfox.logging.api.LoggerAdapter;

import java.util.LinkedHashSet;

/**
 * Configuration for Logger.
 */
public class Settings {

    //----------------------------------------------------------------------------------------------
    // PRIVATE PROPERTIES
    //----------------------------------------------------------------------------------------------

    /**
     * Whether the caller line number should be added.
     */
    boolean callerAddLineNumber;

    /**
     * Whether the caller method name should be added.
     */
    boolean callerAddMethodName;

    /**
     * Whether a simple class name (without package) should be used.
     */
    boolean callerUseSimpleName;

    /**
     * Whether logging is enabled.
     */
    boolean enabled;

    /**
     * Collection of LoggerAdapter instances.
     */
    final LinkedHashSet<LoggerAdapter> loggerAdapters;

    //----------------------------------------------------------------------------------------------
    // CONSTRUCTOR
    //----------------------------------------------------------------------------------------------

    Settings() {
        callerAddLineNumber = false;
        callerAddMethodName = false;
        callerUseSimpleName = false;
        enabled = true;
        loggerAdapters = new LinkedHashSet<>();
    }

    //----------------------------------------------------------------------------------------------
    // PUBLIC METHODS
    //----------------------------------------------------------------------------------------------

    /**
     * Add a LoggerAdapter instance.
     *
     * @param loggerAdapter The LoggerAdapter to add.
     * @return The current Settings instance, so method calls are chainable.
     */
    public Settings addLoggerAdapter(LoggerAdapter loggerAdapter) {
        loggerAdapters.add(loggerAdapter);
        return this;
    }

    /**
     * Pass `true` to add the caller line number. Default: false.
     *
     * @param value Whether the caller line number should be added.
     * @return The current Settings instance, so method calls are chainable.
     */
    public Settings callerAddLineNumber(boolean value) {
        callerAddLineNumber = value;
        return this;
    }

    /**
     * Pass `true` to add the caller method name. Default: false.
     *
     * @param value Whether the caller method name should be added.
     * @return The current Settings instance, so method calls are chainable.
     */
    public Settings callerAddMethodName(boolean value) {
        callerAddMethodName = value;
        return this;
    }

    /**
     * Pass `true` to use a simple class name (without package) for the caller. Default: false.
     *
     * @param value Whether a simple class name should be used.
     * @return The current Settings instance, so method calls are chainable.
     */
    public Settings callerUseSimpleName(boolean value) {
        callerUseSimpleName = value;
        return this;
    }

    /**
     * Enable or disable logging. Default: true.
     *
     * @param value Whether logging is enabled.
     * @return The current Settings instance, so method calls are chainable.
     */
    public Settings setEnabled(boolean value) {
        enabled = value;
        return this;
    }

}
