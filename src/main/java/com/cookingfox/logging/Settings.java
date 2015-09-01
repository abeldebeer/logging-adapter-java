package com.cookingfox.logging;

import com.cookingfox.logging.adapter.Adapter;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by Abel de Beer <abel@cookingfox.nl> on 01/09/15.
 */
public class Settings {
    final Set<Adapter> adapters;
    boolean enabled;
    boolean useSimpleClassName;

    Settings() {
        adapters = new LinkedHashSet<>();
        enabled = true;
        useSimpleClassName = false;
    }

    public Settings addAdapter(Adapter adapter) {
        adapters.add(adapter);
        return this;
    }

    public Settings setEnabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public Settings useSimpleClassName(boolean useSimpleClassName) {
        this.useSimpleClassName = useSimpleClassName;
        return this;
    }
}
