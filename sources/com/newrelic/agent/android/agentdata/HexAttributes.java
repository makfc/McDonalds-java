package com.newrelic.agent.android.agentdata;

import com.newrelic.agent.android.analytics.AnalyticAttribute;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class HexAttributes {
    public static final String HEX_ATTR_APP_BUILD_ID = "appBuild";
    public static final String HEX_ATTR_APP_UUID_HI = "appUuidHigh";
    public static final String HEX_ATTR_APP_UUID_LO = "appUuidLow";
    public static final String HEX_ATTR_APP_VERSION = "appVersion";
    public static final String HEX_ATTR_CAUSE = "cause";
    public static final String HEX_ATTR_CLASS_NAME = "className";
    public static final String HEX_ATTR_FILENAME = "fileName";
    public static final String HEX_ATTR_LINE_NUMBER = "lineNumber";
    public static final String HEX_ATTR_MESSAGE = "message";
    public static final String HEX_ATTR_METHOD_NAME = "methodName";
    public static final String HEX_ATTR_NAME = "name";
    public static final String HEX_ATTR_SESSION_ID = "sessionId";
    public static final String HEX_ATTR_THREAD = "thread";
    public static final String HEX_ATTR_THREAD_CRASHED = "crashed";
    public static final String HEX_ATTR_THREAD_ID = "threadId";
    public static final String HEX_ATTR_THREAD_NUMBER = "threadNumber";
    public static final String HEX_ATTR_THREAD_PRI = "priority";
    public static final String HEX_ATTR_THREAD_STATE = "state";
    public static final String HEX_ATTR_TIMESTAMP_MS = "timestampMs";
    public static final Set<String> HEX_REQUIRED_ATTRIBUTES = new HashSet(Arrays.asList(new String[]{"appBuild", HEX_ATTR_APP_UUID_HI, HEX_ATTR_APP_UUID_LO, "sessionId", HEX_ATTR_MESSAGE, HEX_ATTR_CAUSE, "name", HEX_ATTR_TIMESTAMP_MS, AnalyticAttribute.SESSION_TIME_SINCE_LOAD_ATTRIBUTE}));
    public static final Set<String> HEX_SESSION_ATTR_WHITELIST = new HashSet(Arrays.asList(new String[]{AnalyticAttribute.OS_NAME_ATTRIBUTE, AnalyticAttribute.OS_VERSION_ATTRIBUTE, AnalyticAttribute.OS_BUILD_ATTRIBUTE, AnalyticAttribute.OS_MAJOR_VERSION_ATTRIBUTE, AnalyticAttribute.DEVICE_MANUFACTURER_ATTRIBUTE, AnalyticAttribute.DEVICE_MODEL_ATTRIBUTE, AnalyticAttribute.UUID_ATTRIBUTE, AnalyticAttribute.CARRIER_ATTRIBUTE, AnalyticAttribute.NEW_RELIC_VERSION_ATTRIBUTE, AnalyticAttribute.MEM_USAGE_MB_ATTRIBUTE, "sessionId", AnalyticAttribute.APPLICATION_PLATFORM_ATTRIBUTE, AnalyticAttribute.APPLICATION_PLATFORM_VERSION_ATTRIBUTE, AnalyticAttribute.RUNTIME_ATTRIBUTE, AnalyticAttribute.ARCHITECTURE_ATTRIBUTE, "appBuild"}));
}
