package com.cookingfox.logging;

import java.lang.reflect.Constructor;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Abel de Beer <abel@cookingfox.nl> on 31/08/15.
 */
public class Log {

    static final String PACKAGE_NAME = Log.class.getPackage().getName();
    static final Settings SETTINGS = new Settings();
    static final Map<String, Adapter> ADAPTERS = new LinkedHashMap<String, Adapter>();
    static Constructor adapterConstructor;

    //----------------------------------------------------------------------------------------------
    // PUBLIC METHODS
    //----------------------------------------------------------------------------------------------

    public static Settings init() {
        return SETTINGS;
    }

    //----------------------------------------------------------------------------------------------
    // LOG METHODS
    //----------------------------------------------------------------------------------------------

    public static void d(String message, Object... args) {
        if (isEnabled()) getAdapter().debug(msg(message, args));
    }

    public static void e(String message, Object... args) {
        if (isEnabled()) getAdapter().error(msg(message, args));
    }

    public static void i(String message, Object... args) {
        if (isEnabled()) getAdapter().info(msg(message, args));
    }

    public static void v(String message, Object... args) {
        if (isEnabled()) getAdapter().verbose(msg(message, args));
    }

    public static void w(String message, Object... args) {
        if (isEnabled()) getAdapter().warn(msg(message, args));
    }

    //----------------------------------------------------------------------------------------------
    // PRIVATE METHODS
    //----------------------------------------------------------------------------------------------

    private static Adapter createAdapter(String caller) {
        try {
            if (adapterConstructor == null) {
                adapterConstructor = SETTINGS.adapterClass.getConstructor(String.class);
            }

            return (Adapter) adapterConstructor.newInstance(caller);
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
            return null;
        }
    }

    private synchronized static Adapter getAdapter() {
        String caller = getCallerClassName();
        Adapter adapter = ADAPTERS.get(caller);

        if (adapter == null) {
            adapter = createAdapter(caller);
            ADAPTERS.put(caller, adapter);
        }

        return adapter;
    }

    private static String getCallerClassName() {
        StackTraceElement[] trace = Thread.currentThread().getStackTrace();
        StackTraceElement previous = null;
        String callerClassName = null;

        for (int i = trace.length - 1; i >= 0; i--) {
            StackTraceElement current = trace[i];

            if (previous != null && current.getClassName().startsWith(PACKAGE_NAME)) {
                callerClassName = previous.getClassName();
                break;
            }

            previous = current;
        }

        return callerClassName;
    }

    private static boolean isEnabled() {
        return SETTINGS.enabled && SETTINGS.adapterClass != null;
    }

    private static String msg(String message, Object... args) {
        return args.length > 0 ? String.format(message, args) : message;
    }

}
