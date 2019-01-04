package com.petrego.domain;

public final class LogUtils {

    private LogUtils() { }

    public static String logMessage(final MessageCode code, final String message) {
        return String.format("%s(%d) - %s", code.name(), code.getCode(), message);
    }
}
