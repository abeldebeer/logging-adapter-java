package com.cookingfox.logging.adapter;

import com.cookingfox.logging.api.Entry;
import com.cookingfox.logging.api.LoggerAdapter;

/**
 * Log adapter implementation using Android logging utility.
 */
public class AndroidLoggerAdapter implements LoggerAdapter {

    //----------------------------------------------------------------------------------------------
    // PUBLIC METHODS
    //----------------------------------------------------------------------------------------------

    @Override
    public void debug(Entry entry) {
        android.util.Log.d(entry.getCaller(), entry.getMessage());
    }

    @Override
    public void error(Entry entry) {
        android.util.Log.e(entry.getCaller(), entry.getMessage());
    }

    @Override
    public void info(Entry entry) {
        android.util.Log.i(entry.getCaller(), entry.getMessage());
    }

    @Override
    public void verbose(Entry entry) {
        android.util.Log.v(entry.getCaller(), entry.getMessage());
    }

    @Override
    public void warn(Entry entry) {
        android.util.Log.w(entry.getCaller(), entry.getMessage());
    }

}
