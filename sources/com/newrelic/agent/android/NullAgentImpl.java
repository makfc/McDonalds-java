package com.newrelic.agent.android;

import com.newrelic.agent.android.api.common.TransactionData;
import com.newrelic.agent.android.harvest.ApplicationInformation;
import com.newrelic.agent.android.harvest.DeviceInformation;
import com.newrelic.agent.android.harvest.EnvironmentInformation;
import com.newrelic.agent.android.stats.TicToc;
import com.newrelic.agent.android.util.Encoder;
import com.newrelic.agent.android.util.SafeJsonPrimitive;
import java.util.ArrayList;
import java.util.List;

public class NullAgentImpl implements AgentImpl {
    public static final NullAgentImpl instance = new NullAgentImpl();
    private int responseBodyLimit;
    private TicToc sessionDurationMillis = new TicToc();

    /* renamed from: com.newrelic.agent.android.NullAgentImpl$1 */
    class C41391 implements Encoder {
        C41391() {
        }

        public String encode(byte[] bytes) {
            return new String(bytes);
        }
    }

    public void addTransactionData(TransactionData transactionData) {
    }

    public List<TransactionData> getAndClearTransactionData() {
        return new ArrayList();
    }

    public void mergeTransactionData(List<TransactionData> list) {
    }

    public String getCrossProcessId() {
        return null;
    }

    public int getStackTraceLimit() {
        return 0;
    }

    public int getResponseBodyLimit() {
        return this.responseBodyLimit;
    }

    public void setResponseBodyLimit(int responseBodyLimit) {
        this.responseBodyLimit = responseBodyLimit;
    }

    public void start() {
        this.sessionDurationMillis.tic();
    }

    public void stop() {
        this.sessionDurationMillis.toc();
    }

    public void disable() {
    }

    public boolean isDisabled() {
        return true;
    }

    public String getNetworkCarrier() {
        return "unknown";
    }

    public String getNetworkWanType() {
        return "unknown";
    }

    public void setLocation(String countryCode, String adminRegion) {
    }

    public Encoder getEncoder() {
        return new C41391();
    }

    public DeviceInformation getDeviceInformation() {
        DeviceInformation devInfo = new DeviceInformation();
        devInfo.setOsName("Android");
        devInfo.setOsVersion("2.3");
        devInfo.setOsBuild("a.b.c");
        devInfo.setManufacturer("Fake");
        devInfo.setModel("NullAgent");
        devInfo.setAgentName("AndroidAgent");
        devInfo.setAgentVersion("2.123");
        devInfo.setDeviceId("389C9738-A761-44DE-8A66-1668CFD67DA1");
        devInfo.setArchitecture("Fake Arch");
        devInfo.setRunTime("1.7.0");
        devInfo.setSize("Fake Size");
        return devInfo;
    }

    public ApplicationInformation getApplicationInformation() {
        return new ApplicationInformation(SafeJsonPrimitive.NULL_STRING, "0.0", SafeJsonPrimitive.NULL_STRING, "0");
    }

    public EnvironmentInformation getEnvironmentInformation() {
        return new EnvironmentInformation(0, 1, "none", "none", new long[]{0, 0});
    }

    public boolean updateSavedConnectInformation() {
        return false;
    }

    public long getSessionDurationMillis() {
        return this.sessionDurationMillis.peek();
    }

    public boolean hasReachableNetworkConnection(String reachableHost) {
        return true;
    }
}
