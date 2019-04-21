package com.newrelic.agent.android.crash;

import com.newrelic.agent.android.AgentConfiguration;
import com.newrelic.agent.android.metric.MetricNames;
import com.newrelic.agent.android.payload.PayloadController;
import com.newrelic.agent.android.payload.PayloadReporter;
import com.newrelic.agent.android.payload.PayloadSender;
import com.newrelic.agent.android.payload.PayloadSender.CompletionHandler;
import com.newrelic.agent.android.stats.StatsEngine;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicReference;

public class CrashReporter extends PayloadReporter {
    protected static AtomicReference<CrashReporter> instance = new AtomicReference(null);
    private static boolean reportCrashes = false;
    protected final CrashStore crashStore;
    private final UncaughtExceptionHandler uncaughtExceptionHandler = new UncaughtExceptionHandler(this);

    /* renamed from: com.newrelic.agent.android.crash.CrashReporter$1 */
    class C41551 implements Callable {
        C41551() {
        }

        public Void call() {
            CrashReporter.this.reportSavedCrashes();
            CrashReporter.this.reportSupportabilityMetrics();
            return null;
        }
    }

    public static CrashReporter getInstance() {
        return (CrashReporter) instance.get();
    }

    public static CrashReporter initialize(AgentConfiguration agentConfiguration) {
        instance.compareAndSet(null, new CrashReporter(agentConfiguration));
        return (CrashReporter) instance.get();
    }

    public static void shutdown() {
        if (isInitialized()) {
            ((CrashReporter) instance.get()).stop();
            instance.set(null);
        }
    }

    public static void setReportCrashes(boolean reportCrashes) {
        if (isInitialized()) {
            CrashReporter crashReporter = (CrashReporter) instance.get();
            reportCrashes = reportCrashes;
        }
    }

    public static UncaughtExceptionHandler getUncaughtExceptionHandler() {
        if (isInitialized()) {
            return ((CrashReporter) instance.get()).uncaughtExceptionHandler;
        }
        return null;
    }

    protected static boolean isInitialized() {
        return instance.get() != null;
    }

    protected CrashReporter(AgentConfiguration agentConfiguration) {
        super(agentConfiguration);
        this.crashStore = agentConfiguration.getCrashStore();
        this.isEnabled.set(agentConfiguration.getReportCrashes());
    }

    public void start() {
        if (!isInitialized()) {
            log.error("AgentDataReporter.start(): Must initialize PayloadController first.");
        } else if (isEnabled() && this.isStarted.compareAndSet(false, true)) {
            PayloadController.submitCallable(new C41551());
            this.uncaughtExceptionHandler.installExceptionHandler();
            reportCrashes = this.agentConfiguration.getReportCrashes();
        }
    }

    /* Access modifiers changed, original: protected */
    public void stop() {
    }

    /* Access modifiers changed, original: protected */
    public void reportSavedCrashes() {
        if (this.crashStore != null) {
            for (Crash crash : this.crashStore.fetchAll()) {
                if (crash.isStale()) {
                    this.crashStore.delete(crash);
                    log.info("Crash [" + crash.getUuid().toString() + "] has become stale, and has been removed");
                    StatsEngine.get().inc(MetricNames.SUPPORTABILITY_CRASH_REMOVED_STALE);
                } else {
                    reportCrash(crash);
                }
            }
        }
    }

    /* Access modifiers changed, original: protected */
    public Future reportCrash(final Crash crash) {
        if (!reportCrashes) {
            log.warning("CrashReporter.reportCrash(Crash): attempted to report null crash.");
        } else if (crash != null) {
            return PayloadController.submitPayload(new CrashSender(crash, this.agentConfiguration), new CompletionHandler() {
                public void onResponse(PayloadSender payloadSender) {
                    if (payloadSender.isSuccessfulResponse() && CrashReporter.this.crashStore != null) {
                        CrashReporter.this.crashStore.delete(crash);
                    }
                }

                public void onException(PayloadSender payloadSender, Exception e) {
                    CrashReporter.log.error("Crash upload failed: " + e);
                }
            });
        }
        return null;
    }

    /* Access modifiers changed, original: protected */
    public void storeAndReportCrash(Crash crash) {
        if (this.crashStore == null) {
            log.warning("CrashReporter.storeAndReportCrash(Crash): attempted to store crash without a crash store.");
        } else if (crash == null) {
            log.warning("CrashReporter.storeAndReportCrash(Crash): attempted to store null crash.");
        } else if (!this.crashStore.store(crash)) {
            log.warning("CrashReporter.storeAndReportCrash(Crash): failed to store passed crash.");
        }
        try {
            Future future = reportCrash(crash);
            if (future != null) {
                future.get();
            }
        } catch (Exception e) {
            log.warning("CrashReporter.storeAndReportCrash(Crash): " + e);
        }
    }
}
