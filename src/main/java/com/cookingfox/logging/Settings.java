package com.cookingfox.logging;

import com.cookingfox.logging.adapter.Adapter;

import java.util.LinkedHashSet;

/**
 * Configuration for Logger.
 */
public class Settings {

    //----------------------------------------------------------------------------------------------
    // PRIVATE PROPERTIES
    //----------------------------------------------------------------------------------------------

    /**
     * Collection of log adapter instances.
     */
    final LinkedHashSet<Adapter> adapters;

    /**
     * Whether logging is enabled.
     */
    boolean enabled;

    /**
     * Whether a simple class name should be used for the caller.
     */
    boolean useSimpleClassName;

    //----------------------------------------------------------------------------------------------
    // CONSTRUCTOR
    //----------------------------------------------------------------------------------------------

    Settings() {
        adapters = new LinkedHashSet<>();
        enabled = true;
        useSimpleClassName = false;
    }

    //----------------------------------------------------------------------------------------------
    // PUBLIC METHODS
    //----------------------------------------------------------------------------------------------

    /**
     * Add a log Adapter instance.
     *
     * @param adapter The adapter to add.
     * @return The current Settings instance, so method calls are chainable.
     */
    public Settings addAdapter(Adapter adapter) {
        adapters.add(adapter);
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

    /**
     * Enable to use a simple caller class name in the log messages. Default: false.
     *
     * @param useSimpleClassName Whether to use a simple class name for the caller.
     * @return The current Settings instance, so method calls are chainable.
     */
    public Settings useSimpleClassName(boolean useSimpleClassName) {
        this.useSimpleClassName = useSimpleClassName;
        return this;
    }

}
