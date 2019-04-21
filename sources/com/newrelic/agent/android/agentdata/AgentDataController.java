package com.newrelic.agent.android.agentdata;

import com.newrelic.agent.android.Agent;
import com.newrelic.agent.android.AgentConfiguration;
import com.newrelic.agent.android.FeatureFlag;
import com.newrelic.agent.android.agentdata.builder.AgentDataBuilder;
import com.newrelic.agent.android.analytics.AnalyticAttribute;
import com.newrelic.agent.android.analytics.AnalyticsControllerImpl;
import com.newrelic.agent.android.crash.Crash;
import com.newrelic.agent.android.harvest.crash.ApplicationInfo;
import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import com.newrelic.agent.android.util.ExceptionHelper;
import com.newrelic.com.google.flatbuffers.FlatBufferBuilder;
import com.newrelic.mobile.fbs.AgentDataBundle;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class AgentDataController {
    protected static final AgentConfiguration agentConfiguration = new AgentConfiguration();
    private static final AgentLog log = AgentLogManager.getAgentLog();

    static FlatBufferBuilder buildAgentDataFromHandledException(Exception e, Map<String, Object> exceptionAttributes) {
        UUID buildUuid;
        Map<String, Object> handledException = new HashMap();
        Map<String, Object> sessionAttributes = new HashMap();
        ApplicationInfo applicationInfo = new ApplicationInfo(Agent.getApplicationInformation());
        try {
            buildUuid = UUID.fromString(Crash.getBuildId());
        } catch (IllegalArgumentException ie) {
            buildUuid = UUID.randomUUID();
            ExceptionHelper.recordSupportabilityMetric(ie, "RandomUUID");
        }
        handledException.put(HexAttributes.HEX_ATTR_APP_UUID_HI, Long.valueOf(buildUuid.getLeastSignificantBits()));
        handledException.put(HexAttributes.HEX_ATTR_APP_UUID_LO, Long.valueOf(buildUuid.getMostSignificantBits()));
        handledException.put(HexAttributes.HEX_ATTR_APP_VERSION, applicationInfo.getApplicationVersion());
        handledException.put("appBuild", applicationInfo.getApplicationBuild());
        handledException.put("sessionId", agentConfiguration.getSessionID());
        handledException.put(HexAttributes.HEX_ATTR_TIMESTAMP_MS, Long.valueOf(System.currentTimeMillis()));
        handledException.put(HexAttributes.HEX_ATTR_MESSAGE, e.getMessage());
        handledException.put(HexAttributes.HEX_ATTR_CAUSE, getRootCause(e).toString());
        handledException.put("name", e.getClass().toString());
        handledException.put(HexAttributes.HEX_ATTR_THREAD, threadSetFromStackElements(e.getStackTrace()));
        handledException.putAll(exceptionAttributes);
        for (AnalyticAttribute attribute : AnalyticsControllerImpl.getInstance().getSessionAttributes()) {
            switch (attribute.getAttributeDataType()) {
                case STRING:
                    sessionAttributes.put(attribute.getName(), attribute.getStringValue());
                    break;
                case FLOAT:
                    sessionAttributes.put(attribute.getName(), Float.valueOf(attribute.getFloatValue()));
                    break;
                case BOOLEAN:
                    sessionAttributes.put(attribute.getName(), Boolean.valueOf(attribute.getBooleanValue()));
                    break;
                default:
                    break;
            }
        }
        long sessionDuration = Agent.getImpl().getSessionDurationMillis();
        if (0 == sessionDuration) {
            log.error("Harvest instance is not running! Session duration will be invalid");
        } else {
            sessionAttributes.put(AnalyticAttribute.SESSION_TIME_SINCE_LOAD_ATTRIBUTE, Float.valueOf(((float) sessionDuration) / 1000.0f));
        }
        sessionAttributes.putAll(exceptionAttributes);
        Set<Map<String, Object>> agentData = new HashSet();
        agentData.add(handledException);
        return AgentDataBuilder.startAndFinishAgentData(sessionAttributes, agentData);
    }

    static FlatBufferBuilder buildAgentDataFromHandledException(Exception e) {
        return buildAgentDataFromHandledException(e, new HashMap());
    }

    protected static Throwable getRootCause(Throwable throwable) {
        if (throwable != null) {
            try {
                Throwable cause = throwable.getCause();
                if (cause == null) {
                    return throwable;
                }
                return getRootCause(cause);
            } catch (Exception e) {
                if (throwable != null) {
                    return throwable;
                }
            }
        }
        return new Throwable("Unknown cause");
    }

    protected static List<Map<String, Object>> threadSetFromStackElements(StackTraceElement[] ste) {
        List<Map<String, Object>> thread = new ArrayList();
        for (StackTraceElement ele : ste) {
            Map<String, Object> frame = new LinkedHashMap();
            frame.put(HexAttributes.HEX_ATTR_CLASS_NAME, ele.getClassName());
            frame.put(HexAttributes.HEX_ATTR_METHOD_NAME, ele.getMethodName());
            frame.put(HexAttributes.HEX_ATTR_LINE_NUMBER, Integer.valueOf(ele.getLineNumber()));
            frame.put(HexAttributes.HEX_ATTR_FILENAME, ele.getFileName());
            thread.add(frame);
        }
        return thread;
    }

    public static boolean sendAgentData(Exception e, Map<String, Object> attributes) {
        if (FeatureFlag.featureEnabled(FeatureFlag.HandledExceptions)) {
            try {
                ByteBuffer byteBuffer = buildAgentDataFromHandledException(e, attributes).dataBuffer().slice();
                byte[] modifiedBytes = new byte[byteBuffer.remaining()];
                byteBuffer.get(modifiedBytes);
                log.audit(AgentDataBuilder.toJsonString(AgentDataBundle.getRootAsAgentDataBundle(ByteBuffer.wrap(modifiedBytes)), 0));
                boolean reported = AgentDataReporter.reportAgentData(modifiedBytes);
                if (reported) {
                    return reported;
                }
                log.error("HandledException: exception " + e.getClass().getName() + " failed to record data.");
                return reported;
            } catch (Exception e2) {
                log.error("HandledException: exception " + e.getClass().getName() + " failed to record data.");
            }
        }
        return false;
    }
}
