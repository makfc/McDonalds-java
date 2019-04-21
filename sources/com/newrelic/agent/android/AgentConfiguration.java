package com.newrelic.agent.android;

import com.newrelic.agent.android.analytics.AnalyticAttributeStore;
import com.newrelic.agent.android.crash.CrashStore;
import com.newrelic.agent.android.payload.NullPayloadStore;
import com.newrelic.agent.android.payload.Payload;
import com.newrelic.agent.android.payload.PayloadStore;
import java.util.UUID;

public class AgentConfiguration {
    private static final String APP_TOKEN_HEADER = "X-App-License-Key";
    private static final String APP_VERSION_HEADER = "X-NewRelic-App-Version";
    private static final String DEFAULT_COLLECTOR_HOST = "mobile-collector.newrelic.com";
    private static final String DEFAULT_CRASH_COLLECTOR_HOST = "mobile-crash.newrelic.com";
    private static final String DEVICE_OS_NAME_HEADER = "X-NewRelic-OS-Name";
    private static final String HEX_COLLECTOR_PATH = "/mobile/f";
    private static final int HEX_COLLECTOR_TIMEOUT = 5000;
    private static final int NUM_IO_THREADS = 3;
    private static final int PAYLOAD_TTL = 172800000;
    private AnalyticAttributeStore analyticAttributeStore;
    private String appName;
    private ApplicationPlatform applicationPlatform = ApplicationPlatform.Native;
    private String applicationPlatformVersion = Agent.getVersion();
    private String applicationToken;
    private String collectorHost = DEFAULT_COLLECTOR_HOST;
    private String crashCollectorHost = DEFAULT_CRASH_COLLECTOR_HOST;
    private CrashStore crashStore;
    private String customApplicationVersion = null;
    private String customBuildId = null;
    private boolean enableAnalyticsEvents = true;
    private PayloadStore<Payload> payloadStore = new NullPayloadStore();
    private boolean reportCrashes = true;
    private boolean reportHandledExceptions = true;
    private String sessionID = provideSessionId();
    private boolean useLocationService;
    private boolean useSsl = true;

    public String getApplicationToken() {
        return this.applicationToken;
    }

    public void setApplicationToken(String applicationToken) {
        this.applicationToken = applicationToken;
    }

    public String getAppName() {
        return this.appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getCollectorHost() {
        return this.collectorHost;
    }

    public void setCollectorHost(String collectorHost) {
        this.collectorHost = collectorHost;
    }

    public String getCrashCollectorHost() {
        return this.crashCollectorHost;
    }

    public void setCrashCollectorHost(String crashCollectorHost) {
        this.crashCollectorHost = crashCollectorHost;
    }

    public boolean useSsl() {
        return this.useSsl;
    }

    public void setUseSsl(boolean useSsl) {
        this.useSsl = useSsl;
    }

    public boolean useLocationService() {
        return this.useLocationService;
    }

    public void setUseLocationService(boolean useLocationService) {
        this.useLocationService = useLocationService;
    }

    public boolean getReportCrashes() {
        return this.reportCrashes;
    }

    public void setReportCrashes(boolean reportCrashes) {
        this.reportCrashes = reportCrashes;
    }

    public CrashStore getCrashStore() {
        return this.crashStore;
    }

    public void setCrashStore(CrashStore crashStore) {
        this.crashStore = crashStore;
    }

    public boolean getReportHandledExceptions() {
        return this.reportHandledExceptions;
    }

    public void setReportHandledExceptions(boolean reportHandledExceptions) {
        this.reportHandledExceptions = reportHandledExceptions;
    }

    public AnalyticAttributeStore getAnalyticAttributeStore() {
        return this.analyticAttributeStore;
    }

    public void setAnalyticAttributeStore(AnalyticAttributeStore analyticAttributeStore) {
        this.analyticAttributeStore = analyticAttributeStore;
    }

    public boolean getEnableAnalyticsEvents() {
        return this.enableAnalyticsEvents;
    }

    public void setEnableAnalyticsEvents(boolean enableAnalyticsEvents) {
        this.enableAnalyticsEvents = enableAnalyticsEvents;
    }

    public String getSessionID() {
        return this.sessionID;
    }

    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }

    public String getCustomApplicationVersion() {
        return this.customApplicationVersion;
    }

    public void setCustomApplicationVersion(String customApplicationVersion) {
        this.customApplicationVersion = customApplicationVersion;
    }

    public String getCustomBuildIdentifier() {
        return this.customBuildId;
    }

    public void setCustomBuildIdentifier(String customBuildId) {
        this.customBuildId = customBuildId;
    }

    public ApplicationPlatform getApplicationPlatform() {
        return this.applicationPlatform;
    }

    public void setApplicationPlatform(ApplicationPlatform applicationPlatform) {
        this.applicationPlatform = applicationPlatform;
    }

    public String getApplicationPlatformVersion() {
        return (this.applicationPlatformVersion == null || this.applicationPlatformVersion.isEmpty()) ? Agent.getVersion() : this.applicationPlatformVersion;
    }

    public void setApplicationPlatformVersion(String applicationPlatformVersion) {
        this.applicationPlatformVersion = applicationPlatformVersion;
    }

    /* Access modifiers changed, original: protected */
    public String provideSessionId() {
        this.sessionID = UUID.randomUUID().toString();
        return this.sessionID;
    }

    public String getHexCollectorPath() {
        return HEX_COLLECTOR_PATH;
    }

    public String getHexCollectorHost() {
        return getCollectorHost();
    }

    public int getHexCollectorTimeout() {
        return 5000;
    }

    public String getAppTokenHeader() {
        return APP_TOKEN_HEADER;
    }

    public String getAppVersionHeader() {
        return APP_VERSION_HEADER;
    }

    public String getDeviceOsNameHeader() {
        return DEVICE_OS_NAME_HEADER;
    }

    public int getIOThreadSize() {
        return 3;
    }

    public PayloadStore getPayloadStore() {
        return this.payloadStore;
    }

    public void setPayloadStore(PayloadStore payloadStore) {
        this.payloadStore = payloadStore;
    }

    public int getPayloadTTL() {
        return PAYLOAD_TTL;
    }
}
