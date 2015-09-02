package com.cookingfox.logging.fixture;

import com.cookingfox.logging.Level;
import com.cookingfox.logging.api.Entry;
import com.cookingfox.logging.api.LoggerAdapter;

/**
 * Log adapter implementation that uses a listener to inspect the log calls.
 */
public class ListenableCallLoggerAdapter implements LoggerAdapter {

    private final CallListener listener;

    public ListenableCallLoggerAdapter(CallListener listener) {
        this.listener = listener;
    }

    public void debug(Entry entry) {
        listener.onCall(entry, Level.DEBUG);
    }

    public void error(Entry entry) {
        listener.onCall(entry, Level.ERROR);
    }

    public void info(Entry entry) {
        listener.onCall(entry, Level.INFO);
    }

    public void verbose(Entry entry) {
        listener.onCall(entry, Level.VERBOSE);
    }

    public void warn(Entry entry) {
        listener.onCall(entry, Level.WARN);
    }

    public interface CallListener {
        void onCall(Entry entry, Level level);
    }

}
