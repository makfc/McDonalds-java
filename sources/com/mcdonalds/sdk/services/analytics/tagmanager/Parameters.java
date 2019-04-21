package com.mcdonalds.sdk.services.analytics.tagmanager;

public final class Parameters {
    public static final String ACTION = "action";
    public static final String CATEGORY = "category";
    public static final String LABEL = "label";
    public static final String SCREEN_NAME = "screenName";

    public static final class Events {
        public static final String ACTION = "MCDTagEventGAIAction";
        public static final String OPEN_SCREEN = "MCDTagEventGAIOpenScreen";
        public static final String TRANSACTION = "MCDTagEventGAITransaction";
    }
}
