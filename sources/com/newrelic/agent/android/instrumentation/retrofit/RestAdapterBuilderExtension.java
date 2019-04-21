package com.newrelic.agent.android.instrumentation.retrofit;

import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import java.util.concurrent.Executor;
import retrofit.Endpoint;
import retrofit.ErrorHandler;
import retrofit.Profiler;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RestAdapter.Builder;
import retrofit.RestAdapter.Log;
import retrofit.RestAdapter.LogLevel;
import retrofit.client.Client;
import retrofit.client.Client.Provider;
import retrofit.converter.Converter;

public class RestAdapterBuilderExtension extends Builder {
    private static final AgentLog log = AgentLogManager.getAgentLog();
    private Builder impl;

    public RestAdapterBuilderExtension(Builder impl) {
        this.impl = impl;
    }

    public Builder setEndpoint(String endpoint) {
        return this.impl.setEndpoint(endpoint);
    }

    public Builder setEndpoint(Endpoint endpoint) {
        return this.impl.setEndpoint(endpoint);
    }

    public Builder setClient(Client client) {
        log.debug("RestAdapterBuilderExtension.setClient() wrapping client " + client + " with new ClientExtension.");
        return this.impl.setClient(new ClientExtension(client));
    }

    public Builder setClient(Provider clientProvider) {
        return this.impl.setClient(clientProvider);
    }

    public Builder setExecutors(Executor httpExecutor, Executor callbackExecutor) {
        return this.impl.setExecutors(httpExecutor, callbackExecutor);
    }

    public Builder setRequestInterceptor(RequestInterceptor requestInterceptor) {
        return this.impl.setRequestInterceptor(requestInterceptor);
    }

    public Builder setConverter(Converter converter) {
        return this.impl.setConverter(converter);
    }

    public Builder setProfiler(Profiler profiler) {
        return this.impl.setProfiler(profiler);
    }

    public Builder setErrorHandler(ErrorHandler errorHandler) {
        return this.impl.setErrorHandler(errorHandler);
    }

    public Builder setLog(Log log) {
        return this.impl.setLog(log);
    }

    public Builder setLogLevel(LogLevel logLevel) {
        return this.impl.setLogLevel(logLevel);
    }

    public RestAdapter build() {
        return this.impl.build();
    }
}
