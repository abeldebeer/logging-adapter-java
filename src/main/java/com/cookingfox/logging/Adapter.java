package com.cookingfox.logging;

/**
 * Created by Abel de Beer <abel@cookingfox.nl> on 31/08/15.
 */
public interface Adapter {
    void debug(String message);

    void error(String message);

    void info(String message);

    void verbose(String message);

    void warn(String message);
}
