package com.cookingfox.logging.adapter;

/**
 * Created by Abel de Beer <abel@cookingfox.nl> on 31/08/15.
 */
public class SysOutAdapter implements Adapter {

    private enum Level {
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

    @Override
    public void debug(String caller, String message) {
        log(Level.DEBUG, caller, message);
    }

    @Override
    public void error(String caller, String message) {
        log(Level.ERROR, caller, message);
    }

    @Override
    public void info(String caller, String message) {
        log(Level.INFO, caller, message);
    }

    @Override
    public void verbose(String caller, String message) {
        log(Level.VERBOSE, caller, message);
    }

    @Override
    public void warn(String caller, String message) {
        log(Level.WARN, caller, message);
    }

    //----------------------------------------------------------------------------------------------
    // PRIVATE METHODS
    //----------------------------------------------------------------------------------------------

    private void log(Level level, String caller, String message) {
        System.out.println(String.format("%s/%s: %s", level.id, caller, message));
    }

}
