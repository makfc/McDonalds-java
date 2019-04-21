package com.newrelic.agent.android.instrumentation.retrofit;

import com.newrelic.agent.android.instrumentation.ReplaceCallSite;
import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import retrofit.RestAdapter.Builder;
import retrofit.client.Client;

public final class RetrofitInstrumentation {
    private static final AgentLog log = AgentLogManager.getAgentLog();

    private RetrofitInstrumentation() {
    }

    @ReplaceCallSite
    public static Builder setClient(Builder builder, Client client) {
        return new RestAdapterBuilderExtension(builder).setClient(client);
    }
}
