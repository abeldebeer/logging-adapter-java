package com.cookingfox.logging.adapter;

import com.cookingfox.logging.api.Entry;
import com.cookingfox.logging.api.LoggerAdapter;

/**
 * Log adapter implementation using Android logging utility.
 */
public class AndroidLoggerAdapter implements LoggerAdapter {

    //----------------------------------------------------------------------------------------------
    // PRIVATE CONSTANTS
    //----------------------------------------------------------------------------------------------

    private static final String MESSAGE_EMPTY = "[empty]";

    //----------------------------------------------------------------------------------------------
    // PUBLIC METHODS
    //----------------------------------------------------------------------------------------------

    @Override
    public void debug(Entry entry) {
        android.util.Log.d(entry.getCaller(), getMessage(entry));
    }

    @Override
    public void error(Entry entry) {
        android.util.Log.e(entry.getCaller(), getMessage(entry));
    }

    @Override
    public void info(Entry entry) {
        android.util.Log.i(entry.getCaller(), getMessage(entry));
    }

    @Override
    public void verbose(Entry entry) {
        android.util.Log.v(entry.getCaller(), getMessage(entry));
    }

    @Override
    public void warn(Entry entry) {
        android.util.Log.w(entry.getCaller(), getMessage(entry));
    }

    //----------------------------------------------------------------------------------------------
    // PRIVATE METHODS
    //----------------------------------------------------------------------------------------------

    /**
     * Re-format the log message.
     */
    private String getMessage(Entry entry) {
        String logMessage = entry.getMessage();

        // Android's logcat has some strange behaviour when a log message is empty.
        if (logMessage.isEmpty()) {
            logMessage = MESSAGE_EMPTY;
        }

        return logMessage;
    }

}
