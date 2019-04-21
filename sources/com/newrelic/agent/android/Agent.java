package com.newrelic.agent.android;

import com.newrelic.agent.android.api.common.TransactionData;
import com.newrelic.agent.android.harvest.ApplicationInformation;
import com.newrelic.agent.android.harvest.DeviceInformation;
import com.newrelic.agent.android.util.Encoder;
import java.util.List;

public class Agent {
    public static final String DEFAULT_BUILD_ID = "";
    public static final String MONO_INSTRUMENTATION_FLAG = "NO";
    private static final AgentImpl NULL_AGENT_IMPL = new NullAgentImpl();
    public static final String UNITY_INSTRUMENTATION_FLAG = "NO";
    public static final String VERSION = "5.15.2";
    private static AgentImpl impl = NULL_AGENT_IMPL;
    private static Object implLock = new Object();

    public static void setImpl(AgentImpl impl) {
        synchronized (implLock) {
            if (impl == null) {
                impl = NULL_AGENT_IMPL;
            } else {
                impl = impl;
            }
        }
    }

    public static AgentImpl getImpl() {
        AgentImpl agentImpl;
        synchronized (implLock) {
            agentImpl = impl;
        }
        return agentImpl;
    }

    public static String getVersion() {
        return VERSION;
    }

    public static String getMonoInstrumentationFlag() {
        return "NO";
    }

    public static String getUnityInstrumentationFlag() {
        return "NO";
    }

    public static String getBuildId() {
        return (getUnityInstrumentationFlag().equals("YES") || getMonoInstrumentationFlag().equals("YES")) ? "" : "";
    }

    public static String getCrossProcessId() {
        return getImpl().getCrossProcessId();
    }

    public static int getStackTraceLimit() {
        return getImpl().getStackTraceLimit();
    }

    public static int getResponseBodyLimit() {
        return getImpl().getResponseBodyLimit();
    }

    public static void addTransactionData(TransactionData transactionData) {
        getImpl().addTransactionData(transactionData);
    }

    public static List<TransactionData> getAndClearTransactionData() {
        return getImpl().getAndClearTransactionData();
    }

    public static void mergeTransactionData(List<TransactionData> transactionDataList) {
        getImpl().mergeTransactionData(transactionDataList);
    }

    public static String getActiveNetworkCarrier() {
        return getImpl().getNetworkCarrier();
    }

    public static String getActiveNetworkWanType() {
        return getImpl().getNetworkWanType();
    }

    public static void disable() {
        getImpl().disable();
    }

    public static boolean isDisabled() {
        return getImpl().isDisabled();
    }

    public static void start() {
        getImpl().start();
    }

    public static void stop() {
        getImpl().stop();
    }

    public static void setLocation(String countryCode, String adminRegion) {
        getImpl().setLocation(countryCode, adminRegion);
    }

    public static Encoder getEncoder() {
        return getImpl().getEncoder();
    }

    public static DeviceInformation getDeviceInformation() {
        return getImpl().getDeviceInformation();
    }

    public static ApplicationInformation getApplicationInformation() {
        return getImpl().getApplicationInformation();
    }

    public static boolean hasReachableNetworkConnection(String reachableHost) {
        return getImpl().hasReachableNetworkConnection(reachableHost);
    }
}
