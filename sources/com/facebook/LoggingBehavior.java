package com.facebook;

public enum LoggingBehavior {
    REQUESTS,
    INCLUDE_ACCESS_TOKENS,
    INCLUDE_RAW_RESPONSES,
    CACHE,
    APP_EVENTS,
    DEVELOPER_ERRORS;
    
    @Deprecated
    public static final LoggingBehavior INSIGHTS = null;

    static {
        INSIGHTS = APP_EVENTS;
    }
}
