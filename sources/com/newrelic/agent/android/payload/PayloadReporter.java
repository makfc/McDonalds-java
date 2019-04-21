package com.newrelic.agent.android.payload;

import com.newrelic.agent.android.AgentConfiguration;
import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class PayloadReporter {
    protected static final AgentLog log = AgentLogManager.getAgentLog();
    protected final AgentConfiguration agentConfiguration;
    protected final AtomicBoolean isEnabled = new AtomicBoolean(true);
    protected final AtomicBoolean isStarted = new AtomicBoolean(false);

    public abstract void start();

    public abstract void stop();

    public PayloadReporter(AgentConfiguration agentConfiguration) {
        this.agentConfiguration = agentConfiguration;
    }

    public boolean isEnabled() {
        return this.isEnabled.get();
    }

    public void setEnabled(boolean enabled) {
        this.isEnabled.set(enabled);
    }

    public AgentConfiguration getAgentConfiguration() {
        return this.agentConfiguration;
    }

    /* Access modifiers changed, original: protected */
    public void reportSupportabilityMetrics() {
    }
}
