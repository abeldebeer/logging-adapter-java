package com.cookingfox.logging;

/**
 * Created by Abel de Beer <abel@cookingfox.nl> on 31/08/15.
 */
public class AndroidAdapter implements Adapter {
    final String caller;

    public AndroidAdapter(String caller) {
        this.caller = caller;
    }

    @Override
    public void debug(String message) {
        android.util.Log.d(caller, message);
    }

    @Override
    public void error(String message) {
        android.util.Log.e(caller, message);
    }

    @Override
    public void info(String message) {
        android.util.Log.i(caller, message);
    }

    @Override
    public void verbose(String message) {
        android.util.Log.v(caller, message);
    }

    @Override
    public void warn(String message) {
        android.util.Log.w(caller, message);
    }
}
