package com.newrelic.agent.android.crash;

import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import java.util.concurrent.atomic.AtomicBoolean;

public class UncaughtExceptionHandler implements java.lang.Thread.UncaughtExceptionHandler {
    protected static final AgentLog log = AgentLogManager.getAgentLog();
    private final CrashReporter crashReporter;
    protected final AtomicBoolean handledException = new AtomicBoolean(false);
    private java.lang.Thread.UncaughtExceptionHandler previousExceptionHandler = null;

    public UncaughtExceptionHandler(CrashReporter crashReporter) {
        this.crashReporter = crashReporter;
    }

    public void installExceptionHandler() {
        java.lang.Thread.UncaughtExceptionHandler currentExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        if (currentExceptionHandler == null) {
            log.debug("Installing New Relic crash handler.");
        } else if (currentExceptionHandler instanceof UncaughtExceptionHandler) {
            log.debug("New Relic crash handler already installed.");
            return;
        } else {
            this.previousExceptionHandler = currentExceptionHandler;
            log.debug("Installing New Relic crash handler and chaining " + this.previousExceptionHandler.getClass().getName());
        }
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    /* JADX WARNING: Failed to extract finally block: empty outs */
    /* JADX WARNING: Missing block: B:36:?, code skipped:
            return;
     */
    public void uncaughtException(java.lang.Thread r10, java.lang.Throwable r11) {
        /*
        r9 = this;
        r8 = 1;
        r7 = 0;
        r3 = com.newrelic.agent.android.Agent.getUnityInstrumentationFlag();
        r6 = "YES";
        r3 = r3.equals(r6);
        if (r3 != 0) goto L_0x0020;
    L_0x000e:
        r3 = r9.handledException;
        r3 = r3.compareAndSet(r7, r8);
        if (r3 != 0) goto L_0x0020;
    L_0x0016:
        r3 = com.newrelic.agent.android.stats.StatsEngine.get();
        r6 = "Supportability/AgentHealth/Recursion/UncaughtExceptionHandler";
        r3.inc(r6);
    L_0x001f:
        return;
    L_0x0020:
        r3 = r9.crashReporter;	 Catch:{ all -> 0x00f2 }
        r0 = r3.getAgentConfiguration();	 Catch:{ all -> 0x00f2 }
        r3 = r9.crashReporter;	 Catch:{ all -> 0x00f2 }
        r3 = r3.isEnabled();	 Catch:{ all -> 0x00f2 }
        if (r3 == 0) goto L_0x0036;
    L_0x002e:
        r3 = com.newrelic.agent.android.FeatureFlag.CrashReporting;	 Catch:{ all -> 0x00f2 }
        r3 = com.newrelic.agent.android.FeatureFlag.featureEnabled(r3);	 Catch:{ all -> 0x00f2 }
        if (r3 != 0) goto L_0x0050;
    L_0x0036:
        r3 = log;	 Catch:{ all -> 0x00f2 }
        r6 = "A crash has been detected but crash reporting is disabled!";
        r3.debug(r6);	 Catch:{ all -> 0x00f2 }
        r9.chainExceptionHandler(r10, r11);	 Catch:{ all -> 0x00f2 }
        r3 = com.newrelic.agent.android.Agent.getUnityInstrumentationFlag();
        r6 = "YES";
        r3 = r3.equals(r6);
        if (r3 != 0) goto L_0x001f;
    L_0x004c:
        r9.chainExceptionHandler(r10, r11);
        goto L_0x001f;
    L_0x0050:
        r3 = log;	 Catch:{ all -> 0x00f2 }
        r6 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00f2 }
        r6.<init>();	 Catch:{ all -> 0x00f2 }
        r7 = "A crash has been detected in ";
        r6 = r6.append(r7);	 Catch:{ all -> 0x00f2 }
        r7 = r10.getStackTrace();	 Catch:{ all -> 0x00f2 }
        r8 = 0;
        r7 = r7[r8];	 Catch:{ all -> 0x00f2 }
        r7 = r7.getClassName();	 Catch:{ all -> 0x00f2 }
        r6 = r6.append(r7);	 Catch:{ all -> 0x00f2 }
        r7 = " and will be reported ASAP.";
        r6 = r6.append(r7);	 Catch:{ all -> 0x00f2 }
        r6 = r6.toString();	 Catch:{ all -> 0x00f2 }
        r3.debug(r6);	 Catch:{ all -> 0x00f2 }
        r6 = log;	 Catch:{ all -> 0x00f2 }
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00f2 }
        r3.<init>();	 Catch:{ all -> 0x00f2 }
        r7 = "Analytics data is currently ";
        r7 = r3.append(r7);	 Catch:{ all -> 0x00f2 }
        r3 = r0.getEnableAnalyticsEvents();	 Catch:{ all -> 0x00f2 }
        if (r3 == 0) goto L_0x00ef;
    L_0x008c:
        r3 = "enabled ";
    L_0x008e:
        r3 = r7.append(r3);	 Catch:{ all -> 0x00f2 }
        r3 = r3.toString();	 Catch:{ all -> 0x00f2 }
        r6.debug(r3);	 Catch:{ all -> 0x00f2 }
        r1 = com.newrelic.agent.android.analytics.AnalyticsControllerImpl.getInstance();	 Catch:{ all -> 0x00f2 }
        r3 = 1;
        r1.setEnabled(r3);	 Catch:{ all -> 0x00f2 }
        r4 = com.newrelic.agent.android.harvest.Harvest.getMillisSinceStart();	 Catch:{ all -> 0x00f2 }
        r6 = 0;
        r3 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));
        if (r3 == 0) goto L_0x00b5;
    L_0x00ab:
        r3 = "sessionDuration";
        r6 = (float) r4;	 Catch:{ all -> 0x00f2 }
        r7 = 1148846080; // 0x447a0000 float:1000.0 double:5.676053805E-315;
        r6 = r6 / r7;
        r7 = 0;
        r1.setAttribute(r3, r6, r7);	 Catch:{ all -> 0x00f2 }
    L_0x00b5:
        r2 = new com.newrelic.agent.android.crash.Crash;	 Catch:{ all -> 0x00f2 }
        r3 = r1.getSessionAttributes();	 Catch:{ all -> 0x00f2 }
        r6 = r1.getEventManager();	 Catch:{ all -> 0x00f2 }
        r6 = r6.getQueuedEvents();	 Catch:{ all -> 0x00f2 }
        r7 = r0.getEnableAnalyticsEvents();	 Catch:{ all -> 0x00f2 }
        r2.<init>(r11, r3, r6, r7);	 Catch:{ all -> 0x00f2 }
        r3 = r9.crashReporter;	 Catch:{ all -> 0x00f2 }
        r3.storeAndReportCrash(r2);	 Catch:{ all -> 0x00f2 }
        r3 = com.newrelic.agent.android.Agent.getUnityInstrumentationFlag();	 Catch:{ all -> 0x00f2 }
        r6 = "YES";
        r3 = r3.equals(r6);	 Catch:{ all -> 0x00f2 }
        if (r3 != 0) goto L_0x00de;
    L_0x00db:
        com.newrelic.agent.android.payload.PayloadController.shutdown();	 Catch:{ all -> 0x00f2 }
    L_0x00de:
        r3 = com.newrelic.agent.android.Agent.getUnityInstrumentationFlag();
        r6 = "YES";
        r3 = r3.equals(r6);
        if (r3 != 0) goto L_0x001f;
    L_0x00ea:
        r9.chainExceptionHandler(r10, r11);
        goto L_0x001f;
    L_0x00ef:
        r3 = "disabled";
        goto L_0x008e;
    L_0x00f2:
        r3 = move-exception;
        r6 = com.newrelic.agent.android.Agent.getUnityInstrumentationFlag();
        r7 = "YES";
        r6 = r6.equals(r7);
        if (r6 != 0) goto L_0x0102;
    L_0x00ff:
        r9.chainExceptionHandler(r10, r11);
    L_0x0102:
        throw r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.newrelic.agent.android.crash.UncaughtExceptionHandler.uncaughtException(java.lang.Thread, java.lang.Throwable):void");
    }

    /* Access modifiers changed, original: protected */
    public void chainExceptionHandler(Thread thread, Throwable throwable) {
        if (this.previousExceptionHandler != null) {
            log.debug("Chaining crash reporting duties to " + this.previousExceptionHandler.getClass().getSimpleName());
            this.previousExceptionHandler.uncaughtException(thread, throwable);
        }
    }

    public void resetExceptionHandler() {
        if (this.previousExceptionHandler != null) {
            Thread.setDefaultUncaughtExceptionHandler(this.previousExceptionHandler);
            this.previousExceptionHandler = null;
            this.handledException.set(false);
        }
    }

    public java.lang.Thread.UncaughtExceptionHandler getPreviousExceptionHandler() {
        return this.previousExceptionHandler;
    }
}
