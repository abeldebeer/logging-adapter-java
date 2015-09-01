package com.cookingfox.logging.adapter;

/**
 * Created by Abel de Beer <abel@cookingfox.nl> on 31/08/15.
 */
public class AndroidAdapter implements Adapter {
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
