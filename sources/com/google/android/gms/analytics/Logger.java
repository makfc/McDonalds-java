package com.google.android.gms.analytics;

@Deprecated
public interface Logger {

    @Deprecated
    public static class LogLevel {
    }

    @Deprecated
    void error(String str);

    @Deprecated
    int getLogLevel();

    @Deprecated
    void info(String str);

    @Deprecated
    void setLogLevel(int i);

    @Deprecated
    void verbose(String str);

    @Deprecated
    void warn(String str);
}
