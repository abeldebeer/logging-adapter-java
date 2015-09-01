package com.cookingfox.logging.adapter;

/**
 * Log adapter implementation using Android logging utility.
 */
public class AndroidAdapter implements Adapter {

    //----------------------------------------------------------------------------------------------
    // PUBLIC METHODS
    //----------------------------------------------------------------------------------------------

    @Override
    public void debug(String caller, String message) {
        android.util.Log.d(caller, message);
    }

    @Override
    public void error(String caller, String message) {
        android.util.Log.e(caller, message);
    }

    @Override
    public void info(String caller, String message) {
        android.util.Log.i(caller, message);
    }

    @Override
    public void verbose(String caller, String message) {
        android.util.Log.v(caller, message);
    }

    @Override
    public void warn(String caller, String message) {
        android.util.Log.w(caller, message);
    }

}
