package com.cookingfox.logging.fixtures;

import com.cookingfox.logging.adapter.Adapter;

/**
 * Created by Abel de Beer <abel@cookingfox.nl> on 01/09/15.
 */
public class ListenableCallAdapter implements Adapter {
    private final CallListener listener;

    public ListenableCallAdapter(CallListener listener) {
        this.listener = listener;
    }

    public void debug(String caller, String message) {
        listener.onCall(new Call(caller, message, Call.Level.DEBUG));
    }

    public void error(String caller, String message) {
        listener.onCall(new Call(caller, message, Call.Level.ERROR));
    }

    public void info(String caller, String message) {
        listener.onCall(new Call(caller, message, Call.Level.INFO));
    }

    public void verbose(String caller, String message) {
        listener.onCall(new Call(caller, message, Call.Level.VERBOSE));
    }

    public void warn(String caller, String message) {
        listener.onCall(new Call(caller, message, Call.Level.WARN));
    }

    public static class Call {
        public enum Level {
            DEBUG, ERROR, INFO, VERBOSE, WARN
        }

        public String caller;
        public String message;
        public Level level;

        public Call(String caller, String message, Level level) {
            this.caller = caller;
            this.message = message;
            this.level = level;
        }

        @Override
        public String toString() {
            return "Call{" +
                    "caller='" + caller + '\'' +
                    ", message='" + message + '\'' +
                    ", level=" + level +
                    '}';
        }
    }

    public interface CallListener {
        void onCall(Call call);
    }
}
