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
     * Whether the caller method name should be added for the "class caller".
     */
    boolean classCallerAddMethodName;

    /**
     * Whether a simple class name should be used for the "class caller".
     */
    boolean classCallerUseSimpleName;

    /**
     * Whether logging is enabled.
     */
    boolean enabled;

    /**
     * Whether the caller line number should be added for the "file caller".
     */
    boolean fileCallerAddLineNumber;

    /**
     * Collection of LoggerAdapter instances.
     */
    final LinkedHashSet<LoggerAdapter> loggerAdapters;

    /**
     * Whether the "file caller" should be used.
     */
    boolean useFileCaller;

    //----------------------------------------------------------------------------------------------
    // CONSTRUCTOR
    //----------------------------------------------------------------------------------------------

    Settings() {
        loggerAdapters = new LinkedHashSet<>();
        classCallerUseSimpleName = false;
        classCallerAddMethodName = false;
        enabled = true;
        fileCallerAddLineNumber = true;
        useFileCaller = false;
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
     * Pass `true` to add the caller method name for the "class caller". Default: false.
     *
     * @param value Whether the caller method name should be added.
     * @return The current Settings instance, so method calls are chainable.
     */
    public Settings classCallerAddMethodName(boolean value) {
        classCallerAddMethodName = value;
        return this;
    }

    /**
     * Pass `true` to use a simple class name for the "class caller". Default: false.
     *
     * @param value Whether a simple class name should be used.
     * @return The current Settings instance, so method calls are chainable.
     */
    public Settings classCallerUseSimpleName(boolean value) {
        classCallerUseSimpleName = value;
        return this;
    }

    /**
     * Pass `true` to add the caller line number for the "file caller". Default: true.
     *
     * @param value Whether the caller line number should be added.
     * @return The current Settings instance, so method calls are chainable.
     */
    public Settings fileCallerAddLineNumber(boolean value) {
        fileCallerAddLineNumber = value;
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

    /**
     * Pass `true` to use the "file caller" mode. Default: false.
     *
     * @param value Whether "file caller" mode should be used.
     * @return The current Settings instance, so method calls are chainable.
     */
    public Settings useFileCaller(boolean value) {
        useFileCaller = value;
        return this;
    }

}
