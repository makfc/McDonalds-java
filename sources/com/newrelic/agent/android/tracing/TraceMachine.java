package com.newrelic.agent.android.tracing;

import com.autonavi.amap.mapcore.VTMCDataCache;
import com.newrelic.agent.android.FeatureFlag;
import com.newrelic.agent.android.Measurements;
import com.newrelic.agent.android.TaskQueue;
import com.newrelic.agent.android.api.p047v2.TraceFieldInterface;
import com.newrelic.agent.android.api.p047v2.TraceMachineInterface;
import com.newrelic.agent.android.harvest.ActivityHistory;
import com.newrelic.agent.android.harvest.ActivitySighting;
import com.newrelic.agent.android.harvest.AgentHealth;
import com.newrelic.agent.android.harvest.Harvest;
import com.newrelic.agent.android.harvest.HarvestAdapter;
import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import com.newrelic.agent.android.stats.StatsEngine;
import com.newrelic.agent.android.util.ExceptionHelper;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class TraceMachine extends HarvestAdapter {
    public static final String ACTIVITY_BACKGROUND_METRIC_PREFIX = "Mobile/Activity/Background/Name/";
    public static final String ACTIVITY_METRIC_PREFIX = "Mobile/Activity/Name/";
    public static final String ACTIVTY_DISPLAY_NAME_PREFIX = "Display ";
    public static int HEALTHY_TRACE_TIMEOUT = VTMCDataCache.MAXSIZE;
    public static final String NR_TRACE_FIELD = "_nr_trace";
    public static final String NR_TRACE_TYPE = "Lcom/newrelic/agent/android/tracing/Trace;";
    private static final Object TRACE_MACHINE_LOCK = new Object();
    public static int UNHEALTHY_TRACE_TIMEOUT = 60000;
    private static final List<ActivitySighting> activityHistory = new CopyOnWriteArrayList();
    public static final AtomicBoolean enabled = new AtomicBoolean(true);
    private static final AgentLog log = AgentLogManager.getAgentLog();
    private static final ThreadLocal<Trace> threadLocalTrace = new ThreadLocal();
    private static final ThreadLocal<TraceStack> threadLocalTraceStack = new ThreadLocal();
    private static final Collection<TraceLifecycleAware> traceListeners = new CopyOnWriteArrayList();
    private static TraceMachine traceMachine = null;
    private static TraceMachineInterface traceMachineInterface;
    private ActivityTrace activityTrace;

    private static class TraceStack extends Stack<Trace> {
        private TraceStack() {
        }
    }

    protected static boolean isEnabled() {
        return enabled.get() && FeatureFlag.featureEnabled(FeatureFlag.InteractionTracing);
    }

    protected TraceMachine(Trace rootTrace) {
        this.activityTrace = new ActivityTrace(rootTrace);
        Harvest.addHarvestListener(this);
    }

    public static TraceMachine getTraceMachine() {
        return traceMachine;
    }

    public static void addTraceListener(TraceLifecycleAware listener) {
        traceListeners.add(listener);
    }

    public static void removeTraceListener(TraceLifecycleAware listener) {
        traceListeners.remove(listener);
    }

    public static void setTraceMachineInterface(TraceMachineInterface traceMachineInterface) {
        traceMachineInterface = traceMachineInterface;
    }

    public static void startTracing(String name) {
        startTracing(name, false);
    }

    public static void startTracing(String name, boolean customName) {
        startTracing(name, customName, false);
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    public static void startTracing(java.lang.String r10, boolean r11, boolean r12) {
        /*
        r9 = 0;
        r3 = isEnabled();	 Catch:{ Exception -> 0x00c0 }
        if (r3 != 0) goto L_0x0008;
    L_0x0007:
        return;
    L_0x0008:
        if (r12 != 0) goto L_0x0012;
    L_0x000a:
        r3 = com.newrelic.agent.android.FeatureFlag.DefaultInteractions;	 Catch:{ Exception -> 0x00c0 }
        r3 = com.newrelic.agent.android.FeatureFlag.featureEnabled(r3);	 Catch:{ Exception -> 0x00c0 }
        if (r3 == 0) goto L_0x0007;
    L_0x0012:
        r3 = com.newrelic.agent.android.harvest.Harvest.shouldCollectActivityTraces();	 Catch:{ Exception -> 0x00c0 }
        if (r3 == 0) goto L_0x0007;
    L_0x0018:
        r4 = TRACE_MACHINE_LOCK;	 Catch:{ Exception -> 0x00c0 }
        monitor-enter(r4);	 Catch:{ Exception -> 0x00c0 }
        r3 = isTracingActive();	 Catch:{ all -> 0x00bd }
        if (r3 == 0) goto L_0x0026;
    L_0x0021:
        r3 = traceMachine;	 Catch:{ all -> 0x00bd }
        r3.completeActivityTrace();	 Catch:{ all -> 0x00bd }
    L_0x0026:
        r3 = threadLocalTrace;	 Catch:{ all -> 0x00bd }
        r3.remove();	 Catch:{ all -> 0x00bd }
        r3 = threadLocalTraceStack;	 Catch:{ all -> 0x00bd }
        r5 = new com.newrelic.agent.android.tracing.TraceMachine$TraceStack;	 Catch:{ all -> 0x00bd }
        r6 = 0;
        r5.<init>();	 Catch:{ all -> 0x00bd }
        r3.set(r5);	 Catch:{ all -> 0x00bd }
        r2 = new com.newrelic.agent.android.tracing.Trace;	 Catch:{ all -> 0x00bd }
        r2.<init>();	 Catch:{ all -> 0x00bd }
        if (r11 == 0) goto L_0x00d9;
    L_0x003d:
        r2.displayName = r10;	 Catch:{ all -> 0x00bd }
    L_0x003f:
        r3 = r2.displayName;	 Catch:{ all -> 0x00bd }
        r3 = formatActivityMetricName(r3);	 Catch:{ all -> 0x00bd }
        r2.metricName = r3;	 Catch:{ all -> 0x00bd }
        r3 = r2.displayName;	 Catch:{ all -> 0x00bd }
        r3 = formatActivityBackgroundMetricName(r3);	 Catch:{ all -> 0x00bd }
        r2.metricBackgroundName = r3;	 Catch:{ all -> 0x00bd }
        r6 = java.lang.System.currentTimeMillis();	 Catch:{ all -> 0x00bd }
        r2.entryTimestamp = r6;	 Catch:{ all -> 0x00bd }
        r3 = log;	 Catch:{ all -> 0x00bd }
        r5 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00bd }
        r5.<init>();	 Catch:{ all -> 0x00bd }
        r6 = "Started trace of ";
        r5 = r5.append(r6);	 Catch:{ all -> 0x00bd }
        r5 = r5.append(r10);	 Catch:{ all -> 0x00bd }
        r6 = ":";
        r5 = r5.append(r6);	 Catch:{ all -> 0x00bd }
        r6 = r2.myUUID;	 Catch:{ all -> 0x00bd }
        r6 = r6.toString();	 Catch:{ all -> 0x00bd }
        r5 = r5.append(r6);	 Catch:{ all -> 0x00bd }
        r5 = r5.toString();	 Catch:{ all -> 0x00bd }
        r3.debug(r5);	 Catch:{ all -> 0x00bd }
        r3 = new com.newrelic.agent.android.tracing.TraceMachine;	 Catch:{ all -> 0x00bd }
        r3.<init>(r2);	 Catch:{ all -> 0x00bd }
        traceMachine = r3;	 Catch:{ all -> 0x00bd }
        r3 = traceMachine;	 Catch:{ all -> 0x00bd }
        r2.traceMachine = r3;	 Catch:{ all -> 0x00bd }
        pushTraceContext(r2);	 Catch:{ all -> 0x00bd }
        r3 = traceMachine;	 Catch:{ all -> 0x00bd }
        r3 = r3.activityTrace;	 Catch:{ all -> 0x00bd }
        r5 = getLastActivitySighting();	 Catch:{ all -> 0x00bd }
        r3.previousActivity = r5;	 Catch:{ all -> 0x00bd }
        r3 = activityHistory;	 Catch:{ all -> 0x00bd }
        r5 = new com.newrelic.agent.android.harvest.ActivitySighting;	 Catch:{ all -> 0x00bd }
        r6 = r2.entryTimestamp;	 Catch:{ all -> 0x00bd }
        r8 = r2.displayName;	 Catch:{ all -> 0x00bd }
        r5.<init>(r6, r8);	 Catch:{ all -> 0x00bd }
        r3.add(r5);	 Catch:{ all -> 0x00bd }
        r3 = traceListeners;	 Catch:{ all -> 0x00bd }
        r3 = r3.iterator();	 Catch:{ all -> 0x00bd }
    L_0x00a9:
        r5 = r3.hasNext();	 Catch:{ all -> 0x00bd }
        if (r5 == 0) goto L_0x00e1;
    L_0x00af:
        r1 = r3.next();	 Catch:{ all -> 0x00bd }
        r1 = (com.newrelic.agent.android.tracing.TraceLifecycleAware) r1;	 Catch:{ all -> 0x00bd }
        r5 = traceMachine;	 Catch:{ all -> 0x00bd }
        r5 = r5.activityTrace;	 Catch:{ all -> 0x00bd }
        r1.onTraceStart(r5);	 Catch:{ all -> 0x00bd }
        goto L_0x00a9;
    L_0x00bd:
        r3 = move-exception;
        monitor-exit(r4);	 Catch:{ all -> 0x00bd }
        throw r3;	 Catch:{ Exception -> 0x00c0 }
    L_0x00c0:
        r0 = move-exception;
        r3 = log;
        r4 = "Caught error while initializing TraceMachine, shutting it down";
        r3.error(r4, r0);
        com.newrelic.agent.android.harvest.AgentHealth.noticeException(r0);
        traceMachine = r9;
        r3 = threadLocalTrace;
        r3.remove();
        r3 = threadLocalTraceStack;
        r3.remove();
        goto L_0x0007;
    L_0x00d9:
        r3 = formatActivityDisplayName(r10);	 Catch:{ all -> 0x00bd }
        r2.displayName = r3;	 Catch:{ all -> 0x00bd }
        goto L_0x003f;
    L_0x00e1:
        monitor-exit(r4);	 Catch:{ all -> 0x00bd }
        goto L_0x0007;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.newrelic.agent.android.tracing.TraceMachine.startTracing(java.lang.String, boolean, boolean):void");
    }

    public static void haltTracing() {
        synchronized (TRACE_MACHINE_LOCK) {
            if (isTracingInactive()) {
                return;
            }
            TraceMachine finishedMachine = traceMachine;
            traceMachine = null;
            finishedMachine.activityTrace.discard();
            endLastActivitySighting();
            Harvest.removeHarvestListener(finishedMachine);
            threadLocalTrace.remove();
            threadLocalTraceStack.remove();
        }
    }

    public static void endTrace() {
        traceMachine.completeActivityTrace();
    }

    public static void endTrace(String id) {
        try {
            if (getActivityTrace().rootTrace.myUUID.toString().equals(id)) {
                traceMachine.completeActivityTrace();
            }
        } catch (TracingInactiveException e) {
        }
    }

    public static String formatActivityMetricName(String name) {
        return ACTIVITY_METRIC_PREFIX + name;
    }

    public static String formatActivityBackgroundMetricName(String name) {
        return ACTIVITY_BACKGROUND_METRIC_PREFIX + name;
    }

    public static String formatActivityDisplayName(String name) {
        return ACTIVTY_DISPLAY_NAME_PREFIX + name;
    }

    private static Trace registerNewTrace(String name) throws TracingInactiveException {
        if (isTracingInactive()) {
            log.debug("Tried to register a new trace but tracing is inactive!");
            throw new TracingInactiveException();
        }
        Trace parentTrace = getCurrentTrace();
        Trace childTrace = new Trace(name, parentTrace.myUUID, traceMachine);
        try {
            traceMachine.activityTrace.addTrace(childTrace);
            log.verbose("Registering trace of " + name + " with parent " + parentTrace.displayName);
            parentTrace.addChild(childTrace);
            return childTrace;
        } catch (Exception e) {
            throw new TracingInactiveException();
        }
    }

    /* Access modifiers changed, original: protected */
    public void completeActivityTrace() {
        synchronized (TRACE_MACHINE_LOCK) {
            if (isTracingInactive()) {
                return;
            }
            TraceMachine finishedMachine = traceMachine;
            traceMachine = null;
            finishedMachine.activityTrace.complete();
            endLastActivitySighting();
            for (TraceLifecycleAware listener : traceListeners) {
                listener.onTraceComplete(finishedMachine.activityTrace);
            }
            Harvest.removeHarvestListener(finishedMachine);
        }
    }

    public static void enterNetworkSegment(String name) {
        try {
            if (!isTracingInactive()) {
                if (getCurrentTrace().getType() == TraceType.NETWORK) {
                    exitMethod();
                }
                enterMethod(null, name, null);
                getCurrentTrace().setType(TraceType.NETWORK);
            }
        } catch (TracingInactiveException e) {
        } catch (Exception e2) {
            log.error("Caught error while calling enterNetworkSegment()", e2);
            AgentHealth.noticeException(e2);
        }
    }

    public static void enterMethod(String name) {
        enterMethod(null, name, null);
    }

    public static void enterMethod(String name, ArrayList<String> annotationParams) {
        enterMethod(null, name, annotationParams);
    }

    public static void enterMethod(Trace trace, String name, ArrayList<String> annotationParams) {
        try {
            if (!isTracingInactive()) {
                long currentTime = System.currentTimeMillis();
                long lastUpdatedAt = traceMachine.activityTrace.lastUpdatedAt;
                long inception = traceMachine.activityTrace.startedAt;
                if (((long) HEALTHY_TRACE_TIMEOUT) + lastUpdatedAt < currentTime && !traceMachine.activityTrace.hasMissingChildren()) {
                    log.error(String.format("LastUpdated[%d] CurrentTime[%d] Trigger[%d]", new Object[]{Long.valueOf(lastUpdatedAt), Long.valueOf(currentTime), Long.valueOf(currentTime - lastUpdatedAt)}));
                    log.debug("Completing activity trace after hitting healthy timeout (" + HEALTHY_TRACE_TIMEOUT + "ms)");
                    traceMachine.completeActivityTrace();
                } else if (((long) UNHEALTHY_TRACE_TIMEOUT) + inception < currentTime) {
                    log.debug("Completing activity trace after hitting unhealthy timeout (" + UNHEALTHY_TRACE_TIMEOUT + "ms)");
                    traceMachine.completeActivityTrace();
                } else {
                    loadTraceContext(trace);
                    Trace childTrace = registerNewTrace(name);
                    pushTraceContext(childTrace);
                    childTrace.scope = getCurrentScope();
                    childTrace.setAnnotationParams(annotationParams);
                    for (TraceLifecycleAware listener : traceListeners) {
                        listener.onEnterMethod();
                    }
                    childTrace.entryTimestamp = System.currentTimeMillis();
                }
            }
        } catch (TracingInactiveException e) {
        } catch (Exception e2) {
            log.error("Caught error while calling enterMethod()", e2);
            AgentHealth.noticeException(e2);
        }
    }

    public static void exitMethod() {
        try {
            if (!isTracingInactive()) {
                Trace trace = (Trace) threadLocalTrace.get();
                if (trace == null) {
                    log.debug("threadLocalTrace is null");
                    return;
                }
                trace.exitTimestamp = System.currentTimeMillis();
                if (trace.threadId == 0 && traceMachineInterface != null) {
                    trace.threadId = traceMachineInterface.getCurrentThreadId();
                    trace.threadName = traceMachineInterface.getCurrentThreadName();
                }
                for (TraceLifecycleAware listener : traceListeners) {
                    listener.onExitMethod();
                }
                try {
                    trace.complete();
                    ((TraceStack) threadLocalTraceStack.get()).pop();
                    if (((TraceStack) threadLocalTraceStack.get()).empty()) {
                        threadLocalTrace.set(null);
                    } else {
                        Trace parentTrace = (Trace) ((TraceStack) threadLocalTraceStack.get()).peek();
                        threadLocalTrace.set(parentTrace);
                        parentTrace.childExclusiveTime += trace.getDurationAsMilliseconds();
                    }
                    if (trace.getType() == TraceType.TRACE) {
                        TaskQueue.queue(trace);
                    }
                } catch (TracingInactiveException e) {
                    threadLocalTrace.remove();
                    threadLocalTraceStack.remove();
                    if (trace.getType() == TraceType.TRACE) {
                        TaskQueue.queue(trace);
                    }
                }
            }
        } catch (Exception e2) {
            log.error("Caught error while calling exitMethod()", e2);
            AgentHealth.noticeException(e2);
        }
    }

    private static void pushTraceContext(Trace trace) {
        if (!isTracingInactive() && trace != null) {
            TraceStack traceStack = (TraceStack) threadLocalTraceStack.get();
            if (traceStack.empty()) {
                traceStack.push(trace);
            } else if (traceStack.peek() != trace) {
                traceStack.push(trace);
            }
            threadLocalTrace.set(trace);
        }
    }

    private static void loadTraceContext(Trace trace) {
        if (!isTracingInactive()) {
            if (threadLocalTrace.get() == null) {
                threadLocalTrace.set(trace);
                threadLocalTraceStack.set(new TraceStack());
                if (trace != null) {
                    ((TraceStack) threadLocalTraceStack.get()).push(trace);
                } else {
                    return;
                }
            } else if (trace == null) {
                if (((TraceStack) threadLocalTraceStack.get()).isEmpty()) {
                    log.debug("No context to load!");
                    threadLocalTrace.set(null);
                    return;
                }
                trace = (Trace) ((TraceStack) threadLocalTraceStack.get()).peek();
                threadLocalTrace.set(trace);
            }
            log.verbose("Trace " + trace.myUUID.toString() + " is now active");
        }
    }

    public static void unloadTraceContext(Object object) {
        try {
            if (!isTracingInactive()) {
                if (traceMachineInterface == null || !traceMachineInterface.isUIThread()) {
                    if (threadLocalTrace.get() != null) {
                        log.verbose("Trace " + ((Trace) threadLocalTrace.get()).myUUID.toString() + " is now inactive");
                    }
                    threadLocalTrace.remove();
                    threadLocalTraceStack.remove();
                    try {
                        ((TraceFieldInterface) object)._nr_setTrace(null);
                    } catch (ClassCastException e) {
                        ExceptionHelper.recordSupportabilityMetric(e, "TraceFieldInterface");
                        log.error("Not a TraceFieldInterface: " + e.getMessage());
                    }
                }
            }
        } catch (Exception e2) {
            log.error("Caught error while calling unloadTraceContext()", e2);
            AgentHealth.noticeException(e2);
        }
    }

    public static Trace getCurrentTrace() throws TracingInactiveException {
        if (isTracingInactive()) {
            throw new TracingInactiveException();
        }
        Trace trace = (Trace) threadLocalTrace.get();
        return trace != null ? trace : getRootTrace();
    }

    public static Map<String, Object> getCurrentTraceParams() throws TracingInactiveException {
        return getCurrentTrace().getParams();
    }

    public static void setCurrentTraceParam(String key, Object value) {
        if (!isTracingInactive()) {
            try {
                getCurrentTrace().getParams().put(key, value);
            } catch (TracingInactiveException e) {
            }
        }
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    public static void setCurrentDisplayName(java.lang.String r7) {
        /*
        r3 = TRACE_MACHINE_LOCK;
        monitor-enter(r3);
        r2 = getTraceMachine();	 Catch:{ all -> 0x0050 }
        traceMachine = r2;	 Catch:{ all -> 0x0050 }
        r2 = traceMachine;	 Catch:{ all -> 0x0050 }
        if (r2 == 0) goto L_0x004e;
    L_0x000d:
        r2 = getCurrentTrace();	 Catch:{ TracingInactiveException -> 0x004b }
        r2.displayName = r7;	 Catch:{ TracingInactiveException -> 0x004b }
        r2 = traceListeners;	 Catch:{ TracingInactiveException -> 0x004b }
        r2 = r2.iterator();	 Catch:{ TracingInactiveException -> 0x004b }
    L_0x0019:
        r4 = r2.hasNext();	 Catch:{ TracingInactiveException -> 0x004b }
        if (r4 == 0) goto L_0x004e;
    L_0x001f:
        r1 = r2.next();	 Catch:{ TracingInactiveException -> 0x004b }
        r1 = (com.newrelic.agent.android.tracing.TraceLifecycleAware) r1;	 Catch:{ TracingInactiveException -> 0x004b }
        r4 = traceMachine;	 Catch:{ Exception -> 0x002d }
        r4 = r4.activityTrace;	 Catch:{ Exception -> 0x002d }
        r1.onTraceRename(r4);	 Catch:{ Exception -> 0x002d }
        goto L_0x0019;
    L_0x002d:
        r0 = move-exception;
        r4 = log;	 Catch:{ TracingInactiveException -> 0x004b }
        r5 = new java.lang.StringBuilder;	 Catch:{ TracingInactiveException -> 0x004b }
        r5.<init>();	 Catch:{ TracingInactiveException -> 0x004b }
        r6 = "Cannot name trace. Tracing is not available: ";
        r5 = r5.append(r6);	 Catch:{ TracingInactiveException -> 0x004b }
        r6 = r0.toString();	 Catch:{ TracingInactiveException -> 0x004b }
        r5 = r5.append(r6);	 Catch:{ TracingInactiveException -> 0x004b }
        r5 = r5.toString();	 Catch:{ TracingInactiveException -> 0x004b }
        r4.error(r5);	 Catch:{ TracingInactiveException -> 0x004b }
        goto L_0x0019;
    L_0x004b:
        r0 = move-exception;
        monitor-exit(r3);	 Catch:{ all -> 0x0050 }
    L_0x004d:
        return;
    L_0x004e:
        monitor-exit(r3);	 Catch:{ all -> 0x0050 }
        goto L_0x004d;
    L_0x0050:
        r2 = move-exception;
        monitor-exit(r3);	 Catch:{ all -> 0x0050 }
        throw r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.newrelic.agent.android.tracing.TraceMachine.setCurrentDisplayName(java.lang.String):void");
    }

    public static void setRootDisplayName(String name) {
        if (!isTracingInactive()) {
            try {
                Trace rootTrace = getRootTrace();
                Measurements.renameActivity(rootTrace.displayName, name);
                renameActivityHistory(rootTrace.displayName, name);
                rootTrace.metricName = formatActivityMetricName(name);
                rootTrace.metricBackgroundName = formatActivityBackgroundMetricName(name);
                rootTrace.displayName = name;
                getCurrentTrace().scope = getCurrentScope();
            } catch (TracingInactiveException e) {
            }
        }
    }

    private static void renameActivityHistory(String oldName, String newName) {
        for (ActivitySighting activitySighting : activityHistory) {
            if (activitySighting.getName().equals(oldName)) {
                activitySighting.setName(newName);
            }
        }
    }

    public static String getCurrentScope() {
        try {
            if (isTracingInactive()) {
                return null;
            }
            if (traceMachineInterface == null || traceMachineInterface.isUIThread()) {
                return traceMachine.activityTrace.rootTrace.metricName;
            }
            return traceMachine.activityTrace.rootTrace.metricBackgroundName;
        } catch (Exception e) {
            log.error("Caught error while calling getCurrentScope()", e);
            AgentHealth.noticeException(e);
            return null;
        }
    }

    public static boolean isTracingActive() {
        return traceMachine != null;
    }

    public static boolean isTracingInactive() {
        return !isTracingActive();
    }

    public void storeCompletedTrace(Trace trace) {
        try {
            if (isTracingInactive()) {
                log.debug("Attempted to store a completed trace with no trace machine!");
            } else {
                this.activityTrace.addCompletedTrace(trace);
            }
        } catch (Exception e) {
            log.error("Caught error while calling storeCompletedTrace()", e);
            AgentHealth.noticeException(e);
        }
    }

    public static Trace getRootTrace() throws TracingInactiveException {
        try {
            return traceMachine.activityTrace.rootTrace;
        } catch (NullPointerException e) {
            throw new TracingInactiveException();
        }
    }

    public static ActivityTrace getActivityTrace() throws TracingInactiveException {
        try {
            return traceMachine.activityTrace;
        } catch (NullPointerException e) {
            throw new TracingInactiveException();
        }
    }

    public static ActivityHistory getActivityHistory() {
        return new ActivityHistory(activityHistory);
    }

    public static ActivitySighting getLastActivitySighting() {
        if (activityHistory.isEmpty()) {
            return null;
        }
        return (ActivitySighting) activityHistory.get(activityHistory.size() - 1);
    }

    public static void endLastActivitySighting() {
        ActivitySighting activitySighting = getLastActivitySighting();
        if (activitySighting != null) {
            activitySighting.end(System.currentTimeMillis());
        }
    }

    public static void clearActivityHistory() {
        activityHistory.clear();
    }

    public void onHarvestBefore() {
        if (isTracingActive()) {
            long currentTime = System.currentTimeMillis();
            long lastUpdatedAt = traceMachine.activityTrace.lastUpdatedAt;
            long inception = traceMachine.activityTrace.startedAt;
            if (((long) HEALTHY_TRACE_TIMEOUT) + lastUpdatedAt < currentTime && !traceMachine.activityTrace.hasMissingChildren()) {
                log.debug("Completing activity trace after hitting healthy timeout (" + HEALTHY_TRACE_TIMEOUT + "ms)");
                completeActivityTrace();
                StatsEngine.get().inc("Supportability/AgentHealth/HealthyActivityTraces");
                return;
            } else if (((long) UNHEALTHY_TRACE_TIMEOUT) + inception < currentTime) {
                log.debug("Completing activity trace after hitting unhealthy timeout (" + UNHEALTHY_TRACE_TIMEOUT + "ms)");
                completeActivityTrace();
                StatsEngine.get().inc("Supportability/AgentHealth/UnhealthyActivityTraces");
                return;
            } else {
                return;
            }
        }
        log.debug("TraceMachine is inactive");
    }

    public void onHarvestSendFailed() {
        try {
            traceMachine.activityTrace.incrementReportAttemptCount();
        } catch (NullPointerException e) {
        }
    }
}
