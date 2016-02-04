package com.cookingfox.logging;

import com.cookingfox.logging.api.Entry;

import java.util.Arrays;
import java.util.Locale;

/**
 * Class representing one Logger entry.
 */
public class LoggerEntry implements Entry {

    //----------------------------------------------------------------------------------------------
    // PROPERTIES
    //----------------------------------------------------------------------------------------------

    /**
     * The user message to log.
     */
    private final String message;

    /**
     * An array of arguments to include in the message.
     *
     * @see String#format(Locale, String, Object...)
     */
    private final Object[] messageArgs;

    /**
     * Default caller class name, when no valid stack trace could be generated.
     */
    String defaultCallerClassName;

    /**
     * Logger settings.
     */
    Settings settings;

    /**
     * Caller stack trace.
     */
    StackTraceElement stackTrace;

    //----------------------------------------------------------------------------------------------
    // CONSTRUCTOR
    //----------------------------------------------------------------------------------------------

    /**
     * @param message     The message to log.
     * @param messageArgs Values that need to be included in the log message
     *                    (through {@link String#format(String, Object...)}).
     */
    public LoggerEntry(String message, Object[] messageArgs) {
        this.message = message;
        this.messageArgs = messageArgs;
    }

    //----------------------------------------------------------------------------------------------
    // PUBLIC METHODS
    //----------------------------------------------------------------------------------------------

    @Override
    public String getCaller() {
        // no stack trace? use default caller class name
        if (null == stackTrace) {
            return defaultCallerClassName;
        }

        StringBuilder callerBuilder = new StringBuilder();
        String callerClassName = stackTrace.getClassName();

        // simple class name: extract class name (strip package)
        if (settings.callerUseSimpleName && callerClassName.contains(".")) {
            callerClassName = callerClassName.substring(callerClassName.lastIndexOf(".") + 1);
        }

        // add class name
        callerBuilder.append(callerClassName);

        // add method name
        if (settings.callerAddMethodName) {
            callerBuilder.append('#');
            callerBuilder.append(stackTrace.getMethodName());
        }

        // add caller line number
        if (settings.callerAddLineNumber) {
            callerBuilder.append(':');
            callerBuilder.append(stackTrace.getLineNumber());
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

    @Override
    public String toString() {
        return String.format("%s{message='%s', messageArgs=%s}", getClass().getSimpleName(),
                message, Arrays.toString(messageArgs));
    }

}
