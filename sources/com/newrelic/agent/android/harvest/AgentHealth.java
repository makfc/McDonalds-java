package com.newrelic.agent.android.harvest;

import com.newrelic.agent.android.TaskQueue;
import com.newrelic.agent.android.harvest.type.HarvestableArray;
import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import com.newrelic.agent.android.stats.StatsEngine;
import com.newrelic.com.google.gson.JsonArray;
import java.text.MessageFormat;

public class AgentHealth extends HarvestableArray {
    public static final String DEFAULT_KEY = "Exception";
    private static final AgentLog log = AgentLogManager.getAgentLog();
    protected final AgentHealthExceptions agentHealthExceptions = new AgentHealthExceptions();

    public static void noticeException(Exception exception) {
        AgentHealthException agentHealthException = null;
        if (exception != null) {
            agentHealthException = new AgentHealthException(exception);
        }
        noticeException(agentHealthException);
    }

    public static void noticeException(AgentHealthException exception) {
        noticeException(exception, DEFAULT_KEY);
    }

    public static void noticeException(AgentHealthException exception, String key) {
        if (exception != null) {
            StatsEngine statsEngine = StatsEngine.get();
            if (statsEngine != null) {
                if (key == null) {
                    log.warning("Passed metric key is null. Defaulting to Exception");
                }
                String str = "Supportability/AgentHealth/{0}/{1}/{2}/{3}";
                Object[] objArr = new Object[4];
                if (key == null) {
                    key = DEFAULT_KEY;
                }
                objArr[0] = key;
                objArr[1] = exception.getSourceClass();
                objArr[2] = exception.getSourceMethod();
                objArr[3] = exception.getExceptionClass();
                statsEngine.inc(MessageFormat.format(str, objArr));
                TaskQueue.queue(exception);
                return;
            }
            log.error("StatsEngine is null. Exception not recorded.");
            return;
        }
        log.error("AgentHealthException is null. StatsEngine not updated");
    }

    public void addException(AgentHealthException exception) {
        this.agentHealthExceptions.add(exception);
    }

    public void clear() {
        this.agentHealthExceptions.clear();
    }

    public JsonArray asJsonArray() {
        JsonArray data = new JsonArray();
        if (!this.agentHealthExceptions.isEmpty()) {
            data.add(this.agentHealthExceptions.asJsonObject());
        }
        return data;
    }
}
