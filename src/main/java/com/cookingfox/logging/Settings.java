package com.cookingfox.logging;

/**
 * Created by Abel de Beer <abel@cookingfox.nl> on 31/08/15.
 */
public class Settings {
    Class<? extends Adapter> adapterClass;
    boolean enabled = true;

    public Settings setAdapter(Class<? extends Adapter> adapterClass) {
        this.adapterClass = adapterClass;
        return this;
    }

    public Settings setEnabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }
}
