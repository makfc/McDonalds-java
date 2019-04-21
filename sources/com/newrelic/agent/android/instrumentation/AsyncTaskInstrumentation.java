package com.newrelic.agent.android.instrumentation;

import android.annotation.TargetApi;
import android.os.AsyncTask;
import com.newrelic.agent.android.api.p047v2.TraceFieldInterface;
import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import com.newrelic.agent.android.tracing.TraceMachine;
import com.newrelic.agent.android.tracing.TracingInactiveException;
import com.newrelic.agent.android.util.ExceptionHelper;
import java.util.concurrent.Executor;

public class AsyncTaskInstrumentation {
    private static final AgentLog log = AgentLogManager.getAgentLog();

    protected AsyncTaskInstrumentation() {
    }

    @ReplaceCallSite
    @TargetApi(14)
    public static final <Params, Progress, Result> AsyncTask execute(AsyncTask<Params, Progress, Result> task, Params... params) {
        try {
            ((TraceFieldInterface) task)._nr_setTrace(TraceMachine.getCurrentTrace());
        } catch (ClassCastException e) {
            ExceptionHelper.recordSupportabilityMetric(e, "TraceFieldInterface");
            log.error("Not a TraceFieldInterface: " + e.getMessage());
        } catch (TracingInactiveException | NoSuchFieldError e2) {
        }
        return task.execute(params);
    }

    @ReplaceCallSite
    @TargetApi(11)
    public static final <Params, Progress, Result> AsyncTask executeOnExecutor(AsyncTask<Params, Progress, Result> task, Executor exec, Params... params) {
        try {
            ((TraceFieldInterface) task)._nr_setTrace(TraceMachine.getCurrentTrace());
        } catch (ClassCastException e) {
            ExceptionHelper.recordSupportabilityMetric(e, "TraceFieldInterface");
            log.error("Not a TraceFieldInterface: " + e.getMessage());
        } catch (TracingInactiveException | NoSuchFieldError e2) {
        }
        return task.executeOnExecutor(exec, params);
    }
}
