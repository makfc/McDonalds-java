package com.newrelic.agent.android;

import com.newrelic.agent.android.api.common.TransactionData;
import com.newrelic.agent.android.harvest.ApplicationInformation;
import com.newrelic.agent.android.harvest.DeviceInformation;
import com.newrelic.agent.android.harvest.EnvironmentInformation;
import com.newrelic.agent.android.util.Encoder;
import java.util.List;

public interface AgentImpl {
    void addTransactionData(TransactionData transactionData);

    void disable();

    List<TransactionData> getAndClearTransactionData();

    ApplicationInformation getApplicationInformation();

    String getCrossProcessId();

    DeviceInformation getDeviceInformation();

    Encoder getEncoder();

    EnvironmentInformation getEnvironmentInformation();

    String getNetworkCarrier();

    String getNetworkWanType();

    int getResponseBodyLimit();

    long getSessionDurationMillis();

    int getStackTraceLimit();

    boolean hasReachableNetworkConnection(String str);

    boolean isDisabled();

    void mergeTransactionData(List<TransactionData> list);

    void setLocation(String str, String str2);

    void start();

    void stop();

    boolean updateSavedConnectInformation();
}
