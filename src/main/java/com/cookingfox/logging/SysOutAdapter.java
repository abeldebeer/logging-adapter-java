package com.cookingfox.logging;

/**
 * Created by Abel de Beer <abel@cookingfox.nl> on 31/08/15.
 */
public class SysOutAdapter implements Adapter {

    enum Level {
        VERBOSE(2, "V"),
        DEBUG(3, "D"),
        INFO(4, "I"),
        WARN(5, "W"),
        ERROR(6, "E");

        int code;
        String id;

        Level(int code, String id) {
            this.code = code;
            this.id = id;
        }
    }

    private final String caller;

    public SysOutAdapter(String caller) {
        this.caller = caller;
    }

    @Override
    public void debug(String message) {
        log(Level.DEBUG, message);
    }

    @Override
    public void error(String message) {
        log(Level.ERROR, message);
    }

    @Override
    public void info(String message) {
        log(Level.INFO, message);
    }

    @Override
    public void verbose(String message) {
        log(Level.VERBOSE, message);
    }

    @Override
    public void warn(String message) {
        log(Level.WARN, message);
    }

    //----------------------------------------------------------------------------------------------
    // PRIVATE METHODS
    //----------------------------------------------------------------------------------------------

    private void log(Level level, String message) {
        System.out.println(String.format("%s/%s: %s", level.id, caller, message));
    }

}
