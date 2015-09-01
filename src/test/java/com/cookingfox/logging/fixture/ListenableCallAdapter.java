package com.cookingfox.logging.fixture;

import com.cookingfox.logging.Level;
import com.cookingfox.logging.adapter.Adapter;

/**
 * Log adapter implementation that uses a listener to inspect the log calls.
 */
public class ListenableCallAdapter implements Adapter {

    private final CallListener listener;

    public ListenableCallAdapter(CallListener listener) {
        this.listener = listener;
    }

    public void debug(String caller, String message) {
        listener.onCall(new Call(caller, message, Level.DEBUG));
    }

    public void error(String caller, String message) {
        listener.onCall(new Call(caller, message, Level.ERROR));
    }

    public void info(String caller, String message) {
        listener.onCall(new Call(caller, message, Level.INFO));
    }

    public void verbose(String caller, String message) {
        listener.onCall(new Call(caller, message, Level.VERBOSE));
    }

    public void warn(String caller, String message) {
        listener.onCall(new Call(caller, message, Level.WARN));
    }

    public static class Call {
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
