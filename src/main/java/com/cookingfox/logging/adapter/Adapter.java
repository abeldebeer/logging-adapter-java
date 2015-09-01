package com.cookingfox.logging.adapter;

/**
 * Created by Abel de Beer <abel@cookingfox.nl> on 31/08/15.
 */
public interface Adapter {
    void debug(String caller, String message);

    void error(String caller, String message);

    void info(String caller, String message);

    void verbose(String caller, String message);

    void warn(String caller, String message);
}
