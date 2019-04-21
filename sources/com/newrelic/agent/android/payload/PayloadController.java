package com.newrelic.agent.android.payload;

import com.newrelic.agent.android.Agent;
import com.newrelic.agent.android.AgentConfiguration;
import com.newrelic.agent.android.agentdata.AgentDataReporter;
import com.newrelic.agent.android.crash.CrashReporter;
import com.newrelic.agent.android.harvest.Harvest;
import com.newrelic.agent.android.harvest.HarvestLifecycleAware;
import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import com.newrelic.agent.android.metric.MetricNames;
import com.newrelic.agent.android.payload.PayloadSender.CompletionHandler;
import com.newrelic.agent.android.stats.StatsEngine;
import com.newrelic.agent.android.stats.TicToc;
import com.newrelic.agent.android.util.NamedThreadFactory;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PayloadController implements HarvestLifecycleAware {
    public static final long PAYLOAD_COLLECTOR_TIMEOUT = 3000;
    public static final long PAYLOAD_REQUEUE_PERIOD_MS = 120000;
    protected static final Runnable dequeueRunnable = new C41681();
    protected static AtomicReference<PayloadController> instance = new AtomicReference(null);
    protected static final AgentLog log = AgentLogManager.getAgentLog();
    protected static boolean opportunisticUploads = false;
    protected static Lock payloadQueueLock = new ReentrantLock(false);
    protected static ConcurrentLinkedQueue<PayloadReaper> payloadReaperQueue = null;
    protected static ConcurrentLinkedQueue<PayloadReaper> payloadReaperRetryQueue = null;
    protected static ThrottledScheduledThreadPoolExecutor queueExecutor = null;
    protected static Map<String, Future> reapersInFlight = null;
    protected static ScheduledFuture<?> requeueFuture = null;
    protected static final Runnable requeueRunnable = new C41692();
    private final AgentConfiguration agentConfiguration;

    /* renamed from: com.newrelic.agent.android.payload.PayloadController$1 */
    static class C41681 implements Runnable {
        C41681() {
        }

        public void run() {
            if (PayloadController.isInitialized()) {
                ((PayloadController) PayloadController.instance.get()).dequeuePayloadSenders();
            }
        }
    }

    /* renamed from: com.newrelic.agent.android.payload.PayloadController$2 */
    static class C41692 implements Runnable {
        C41692() {
        }

        public void run() {
            if (PayloadController.isInitialized()) {
                ((PayloadController) PayloadController.instance.get()).requeuePayloadSenders();
            }
        }
    }

    protected static class ThrottledScheduledThreadPoolExecutor extends ScheduledThreadPoolExecutor {
        protected static final int THROTTLE_LIMIT = 16;
        protected static final int THROTTLE_SLEEP = 50;

        public ThrottledScheduledThreadPoolExecutor(int i, ThreadFactory threadFactory) {
            super(i, threadFactory);
        }

        public <T> Future<T> submit(Callable<T> callable) {
            if (getQueue().size() >= 16) {
                StatsEngine.get().inc(MetricNames.SUPPORTABILITY_HEX_UPLOAD_THROTTLED);
            }
            return super.submit(callable);
        }
    }

    public static PayloadController initialize(AgentConfiguration agentConfiguration) {
        if (instance.compareAndSet(null, new PayloadController(agentConfiguration))) {
            payloadReaperQueue = new ConcurrentLinkedQueue();
            payloadReaperRetryQueue = new ConcurrentLinkedQueue();
            queueExecutor = new ThrottledScheduledThreadPoolExecutor(agentConfiguration.getIOThreadSize(), new NamedThreadFactory("NR-PayloadWorker"));
            requeueFuture = queueExecutor.scheduleAtFixedRate(requeueRunnable, PAYLOAD_REQUEUE_PERIOD_MS, PAYLOAD_REQUEUE_PERIOD_MS, TimeUnit.MILLISECONDS);
            reapersInFlight = new ConcurrentHashMap();
            opportunisticUploads = false;
            CrashReporter crashReporter = CrashReporter.initialize(agentConfiguration);
            if (crashReporter != null) {
                crashReporter.start();
            } else {
                log.warning("PayloadController.initialize: No crash reporter - crash reporting will be disabled");
            }
            AgentDataReporter agentDataReporter = AgentDataReporter.initialize(agentConfiguration);
            if (agentDataReporter != null) {
                agentDataReporter.start();
            } else {
                log.warning("PayloadController.initialize: No payload reporter - payload reporting will be disabled");
            }
            Harvest.addHarvestListener((HarvestLifecycleAware) instance.get());
        }
        return (PayloadController) instance.get();
    }

    public static void shutdown() {
        if (isInitialized()) {
            try {
                Harvest.removeHarvestListener((HarvestLifecycleAware) instance.get());
                if (requeueFuture != null) {
                    requeueFuture.cancel(true);
                    requeueFuture = null;
                }
                queueExecutor.shutdown();
                try {
                    CrashReporter.shutdown();
                    AgentDataReporter.shutdown();
                    if (!queueExecutor.awaitTermination(PAYLOAD_COLLECTOR_TIMEOUT, TimeUnit.MILLISECONDS)) {
                        log.warning("PayloadController.shutdown: upload thread(s) timed-out before handler");
                        queueExecutor.shutdownNow();
                    }
                } catch (InterruptedException e) {
                }
                instance.set(null);
            } catch (Throwable th) {
                instance.set(null);
            }
        }
    }

    public static Future submitPayload(PayloadSender payloadSender) {
        return submitPayload(payloadSender, null);
    }

    public static Future submitPayload(PayloadSender payloadSender, CompletionHandler completionHandler) {
        Future future = null;
        TicToc timer = new TicToc();
        if (isInitialized()) {
            timer.tic();
            PayloadReaper payloadReaper = new PayloadReaper(payloadSender, completionHandler) {
                public PayloadSender call() throws Exception {
                    PayloadSender sender = super.call();
                    if (!(sender == null || sender.isSuccessfulResponse() || !sender.shouldRetry())) {
                        PayloadController.payloadReaperRetryQueue.offer(this);
                    }
                    PayloadController.reapersInFlight.remove(getUuid());
                    return sender;
                }
            };
            payloadReaperQueue.remove(payloadReaper);
            payloadReaperRetryQueue.remove(payloadReaper);
            future = (Future) reapersInFlight.get(payloadReaper.getUuid());
            if (future != null) {
                log.warning("PayloadController.submitPayload: [" + payloadReaper.getUuid() + "] is already in progress.");
            } else if (payloadSender.shouldUploadOpportunistically()) {
                future = queueExecutor.submit(payloadReaper);
                reapersInFlight.put(payloadReaper.getUuid(), future);
            } else {
                payloadReaperQueue.offer(payloadReaper);
            }
            log.debug("PayloadController.submitPayload: " + String.valueOf(timer.toc()) + "ms. waiting to submit payload.");
        }
        return future;
    }

    protected static Future submitPayload(PayloadReaper payloadReaper) {
        if (!isInitialized()) {
            return null;
        }
        payloadReaperQueue.remove(payloadReaper);
        payloadReaperRetryQueue.remove(payloadReaper);
        Future future = (Future) reapersInFlight.get(payloadReaper.getUuid());
        if (future != null) {
            log.warning("PayloadController.submitPayload: [" + payloadReaper.getUuid() + "] is already in progress.");
            return future;
        }
        future = queueExecutor.submit(payloadReaper);
        reapersInFlight.put(payloadReaper.getUuid(), future);
        return future;
    }

    public static boolean shouldUploadOpportunistically() {
        return opportunisticUploads && Agent.hasReachableNetworkConnection(null);
    }

    public static Future submitCallable(Callable callable) {
        return queueExecutor.submit(callable);
    }

    public static boolean isInitialized() {
        return instance.get() != null;
    }

    protected PayloadController(AgentConfiguration agentConfiguration) {
        this.agentConfiguration = agentConfiguration;
    }

    private final void dequeuePayloadSenders() {
        if (payloadQueueLock.tryLock()) {
            while (!payloadReaperQueue.isEmpty()) {
                try {
                    PayloadReaper payloadReaper = (PayloadReaper) payloadReaperQueue.poll();
                    if (payloadReaper != null) {
                        submitPayload(payloadReaper);
                    }
                } catch (Exception e) {
                    log.error("PayloadController.dequeuePayloadSenders(): " + e);
                } catch (Throwable th) {
                    payloadQueueLock.unlock();
                }
            }
            payloadQueueLock.unlock();
        }
    }

    private void requeuePayloadSenders() {
        if (payloadQueueLock.tryLock()) {
            while (!payloadReaperRetryQueue.isEmpty()) {
                try {
                    PayloadReaper payloadReaper = (PayloadReaper) payloadReaperRetryQueue.poll();
                    if (payloadReaper != null) {
                        if (payloadReaper.sender.getPayload().isStale((long) this.agentConfiguration.getPayloadTTL())) {
                            log.warning("PayloadController.requeuePayloadSenders: Will not re-queue stale payload.");
                        } else {
                            submitPayload(payloadReaper);
                        }
                    }
                } finally {
                    payloadQueueLock.unlock();
                }
            }
        }
    }

    /* Access modifiers changed, original: protected */
    public boolean uploadOpportunistically() {
        return opportunisticUploads;
    }

    public void onHarvestComplete() {
    }

    public void onHarvestStart() {
    }

    public void onHarvestStop() {
    }

    public void onHarvestBefore() {
    }

    public void onHarvest() {
        queueExecutor.submit(dequeueRunnable);
    }

    public void onHarvestFinalize() {
    }

    public void onHarvestError() {
    }

    public void onHarvestSendFailed() {
    }

    public void onHarvestConnected() {
    }

    public void onHarvestDisconnected() {
    }

    public void onHarvestDisabled() {
    }

    public static boolean payloadInFlight(Payload payload) {
        return reapersInFlight.containsKey(payload.getUuid());
    }
}
