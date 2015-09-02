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
     * Whether a simple class name should be used for the caller.
     */
    boolean classCallerUseSimpleName;

    /**
     * Whether logging is enabled.
     */
    boolean enabled;

    boolean fileCallerAddLineNumber;

    /**
     * Collection of LoggerAdapter instances.
     */
    final LinkedHashSet<LoggerAdapter> loggerAdapters;

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

    public Settings classCallerAddMethodName(boolean classCallerAddMethodName) {
        this.classCallerAddMethodName = classCallerAddMethodName;
        return this;
    }

    public Settings classCallerUseSimpleName(boolean classCallerUseSimpleName) {
        this.classCallerUseSimpleName = classCallerUseSimpleName;
        return this;
    }

    public Settings fileCallerAddLineNumber(boolean fileCallerAddLineNumber) {
        this.fileCallerAddLineNumber = fileCallerAddLineNumber;
        return this;
    }

    /**
     * Enable or disable logging. Default: true.
     *
     * @param enabled Whether logging is enabled.
     * @return The current Settings instance, so method calls are chainable.
     */
    public Settings setEnabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public Settings useFileCaller(boolean useFileCaller) {
        this.useFileCaller = useFileCaller;
        return this;
    }

}
