package com.cookingfox.logging;

import com.cookingfox.logging.api.Entry;

/**
 * Created by abeldebeer on 02/09/15.
 */
public class LoggerEntry implements Entry {
    private final String message;
    private final Object[] messageArgs;

    String defaultCaller;
    Settings settings;
    StackTraceElement stackTrace;

    public LoggerEntry(String message, Object[] messageArgs) {
        this.message = message;
        this.messageArgs = messageArgs;
    }

    @Override
    public String getCaller() {
        if (null == stackTrace) {
            return defaultCaller;
        }

        String caller = stackTrace.getClassName();

        // simple class name: extract class name (strip package)
        if (settings.useSimpleClassName && caller.contains(".")) {
            caller = caller.substring(caller.lastIndexOf(".") + 1);
        }

        return caller;
    }

    @Override
    public String getMessage() {
        String logMessage = message;

        // arguments have been passed: use in String format
        if (messageArgs.length > 0) {
            logMessage = String.format(logMessage, messageArgs);
        }

        // include caller method name if stack trace is valid
        if (null != stackTrace && settings.includeMethodName) {
            logMessage = String.format("%s | %s", stackTrace.getMethodName(), logMessage);
        }

        return logMessage;
    }
}
