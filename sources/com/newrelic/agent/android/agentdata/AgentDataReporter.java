package com.newrelic.agent.android.agentdata;

import com.newrelic.agent.android.AgentConfiguration;
import com.newrelic.agent.android.harvest.Harvest;
import com.newrelic.agent.android.harvest.HarvestLifecycleAware;
import com.newrelic.agent.android.metric.MetricNames;
import com.newrelic.agent.android.payload.Payload;
import com.newrelic.agent.android.payload.PayloadController;
import com.newrelic.agent.android.payload.PayloadReporter;
import com.newrelic.agent.android.payload.PayloadSender;
import com.newrelic.agent.android.payload.PayloadSender.CompletionHandler;
import com.newrelic.agent.android.payload.PayloadStore;
import com.newrelic.agent.android.stats.StatsEngine;
import com.newrelic.com.google.flatbuffers.FlatBufferBuilder;
import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicReference;

public class AgentDataReporter extends PayloadReporter implements HarvestLifecycleAware {
    protected static final AtomicReference<AgentDataReporter> instance = new AtomicReference(null);
    private static boolean reportExceptions = false;
    protected final PayloadStore<Payload> payloadStore;
    protected final Callable reportCachedAgentDataCallable = new C41421();

    /* renamed from: com.newrelic.agent.android.agentdata.AgentDataReporter$1 */
    class C41421 implements Callable {
        C41421() {
        }

        public Object call() throws Exception {
            AgentDataReporter.this.reportCachedAgentData();
            return null;
        }
    }

    /* renamed from: com.newrelic.agent.android.agentdata.AgentDataReporter$2 */
    class C41432 implements CompletionHandler {
        C41432() {
        }

        public void onResponse(PayloadSender payloadSender) {
            if (payloadSender.isSuccessfulResponse() && AgentDataReporter.this.payloadStore != null) {
                AgentDataReporter.this.payloadStore.delete(payloadSender.getPayload());
            }
        }

        public void onException(PayloadSender payloadSender, Exception e) {
            AgentDataReporter.log.error("AgentDataReporter.reportAgentData(Payload): " + e);
        }
    }

    public static AgentDataReporter getInstance() {
        return (AgentDataReporter) instance.get();
    }

    public static AgentDataReporter initialize(AgentConfiguration agentConfiguration) {
        instance.compareAndSet(null, new AgentDataReporter(agentConfiguration));
        reportExceptions = agentConfiguration.getReportHandledExceptions();
        return (AgentDataReporter) instance.get();
    }

    public static void shutdown() {
        if (isInitialized()) {
            try {
                ((AgentDataReporter) instance.get()).stop();
            } finally {
                instance.set(null);
            }
        }
    }

    public static boolean reportAgentData(byte[] bytes) {
        if (!isInitialized()) {
            log.error("AgentDataReporter not initialized");
            return false;
        } else if (!reportExceptions) {
            return false;
        } else {
            ((AgentDataReporter) instance.get()).storeAndReportAgentData(new Payload(bytes));
            return true;
        }
    }

    protected static boolean isInitialized() {
        return instance.get() != null;
    }

    protected AgentDataReporter(AgentConfiguration agentConfiguration) {
        super(agentConfiguration);
        this.payloadStore = agentConfiguration.getPayloadStore();
        this.isEnabled.set(agentConfiguration.getReportHandledExceptions());
    }

    public void start() {
        if (!PayloadController.isInitialized()) {
            log.error("AgentDataReporter.start(): Must initialize PayloadController first.");
        } else if (isEnabled() && this.isStarted.compareAndSet(false, true)) {
            PayloadController.submitCallable(this.reportCachedAgentDataCallable);
            Harvest.addHarvestListener(this);
        }
    }

    public void stop() {
        Harvest.removeHarvestListener(this);
    }

    /* Access modifiers changed, original: protected */
    public void reportCachedAgentData() {
        if (!isInitialized()) {
            log.error("AgentDataReporter not initialized");
        } else if (this.payloadStore != null) {
            for (Payload payload : this.payloadStore.fetchAll()) {
                if (!isPayloadStale(payload)) {
                    reportAgentData(payload);
                }
            }
        }
    }

    /* Access modifiers changed, original: protected */
    public Future reportAgentData(Payload payload) {
        return PayloadController.submitPayload(new AgentDataSender(payload, getAgentConfiguration()), new C41432());
    }

    public Future storeAndReportAgentData(Payload payload) {
        if (this.payloadStore != null && payload.isPersisted() && this.payloadStore.store(payload)) {
            payload.setPersisted(false);
        }
        return reportAgentData(payload);
    }

    private boolean isPayloadStale(Payload payload) {
        if (!payload.isStale((long) this.agentConfiguration.getPayloadTTL())) {
            return false;
        }
        this.payloadStore.delete(payload);
        log.info("Payload [" + payload.getUuid() + "] has become stale, and has been removed");
        StatsEngine.get().inc(MetricNames.SUPPORTABILITY_PAYLOAD_REMOVED_STALE);
        return true;
    }

    private byte[] submitBatchedPayload(final List<Payload> payloads) {
        if (!payloads.isEmpty()) {
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            for (Payload payload : payloads) {
                try {
                    output.write(payload.getBytes());
                } catch (Exception e) {
                }
            }
            ByteBuffer byteBuffer = new FlatBufferBuilder(ByteBuffer.wrap(output.toByteArray())).dataBuffer().slice();
            byteBuffer.get(new byte[byteBuffer.remaining()]);
            PayloadController.submitPayload(new AgentDataSender(byteBuffer.array(), ((AgentDataReporter) instance.get()).agentConfiguration), new CompletionHandler() {
                public void onResponse(PayloadSender payloadSender) {
                    if (payloadSender.isSuccessfulResponse() && AgentDataReporter.this.payloadStore != null) {
                        for (Payload payload : payloads) {
                            AgentDataReporter.this.payloadStore.delete(payload);
                        }
                    }
                }

                public void onException(PayloadSender payloadSender, Exception e) {
                    AgentDataReporter.log.error("AgentDataReporter.submitBatchedPayload(List<Payload>): " + e);
                }
            });
        }
        return null;
    }

    public void onHarvestStart() {
    }

    public void onHarvestStop() {
    }

    public void onHarvestBefore() {
    }

    public void onHarvest() {
        PayloadController.submitCallable(this.reportCachedAgentDataCallable);
    }

    public void onHarvestFinalize() {
    }

    public void onHarvestError() {
    }

    public void onHarvestSendFailed() {
    }

    public void onHarvestComplete() {
    }

    public void onHarvestConnected() {
    }

    public void onHarvestDisconnected() {
    }

    public void onHarvestDisabled() {
    }
}
