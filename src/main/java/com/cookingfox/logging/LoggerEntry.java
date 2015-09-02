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

        StringBuilder callerBuilder = new StringBuilder();

        if (settings.useFileCaller) {
            callerBuilder.append(stackTrace.getFileName());

            if (settings.fileCallerAddLineNumber) {
                callerBuilder.append(':');
                callerBuilder.append(stackTrace.getLineNumber());
            }
        } else {
            String callerClassName = stackTrace.getClassName();

            // simple class name: extract class name (strip package)
            if (settings.classCallerUseSimpleName && callerClassName.contains(".")) {
                callerClassName = callerClassName.substring(callerClassName.lastIndexOf(".") + 1);
            }

            callerBuilder.append(callerClassName);

            if (settings.classCallerAddMethodName) {
                callerBuilder.append('#');
                callerBuilder.append(stackTrace.getMethodName());
            }
        }

        return callerBuilder.toString();
    }

    @Override
    public String getMessage() {
        String logMessage = message;

        // arguments have been passed: use in String format
        if (messageArgs.length > 0) {
            logMessage = String.format(logMessage, messageArgs);
        }

        return logMessage;
    }
}
