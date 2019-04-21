package com.newrelic.agent.android;

import com.newrelic.agent.android.harvest.AgentHealth;
import com.newrelic.agent.android.harvest.AgentHealthException;
import com.newrelic.agent.android.harvest.Harvest;
import com.newrelic.agent.android.harvest.HarvestAdapter;
import com.newrelic.agent.android.measurement.http.HttpTransactionMeasurement;
import com.newrelic.agent.android.metric.Metric;
import com.newrelic.agent.android.tracing.ActivityTrace;
import com.newrelic.agent.android.tracing.Trace;
import com.newrelic.agent.android.util.NamedThreadFactory;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TaskQueue extends HarvestAdapter {
    private static final long DEQUEUE_PERIOD_MS = 1000;
    private static Future dequeueFuture;
    private static final Runnable dequeueTask = new C41401();
    private static final ConcurrentLinkedQueue<Object> queue = new ConcurrentLinkedQueue();
    private static final ScheduledExecutorService queueExecutor = Executors.newSingleThreadScheduledExecutor(new NamedThreadFactory("TaskQueue"));

    /* renamed from: com.newrelic.agent.android.TaskQueue$1 */
    static class C41401 implements Runnable {
        C41401() {
        }

        public void run() {
            TaskQueue.dequeue();
        }
    }

    public static void queue(Object object) {
        queue.add(object);
    }

    public static void backgroundDequeue() {
        queueExecutor.execute(dequeueTask);
    }

    public static void synchronousDequeue() {
        try {
            queueExecutor.submit(dequeueTask).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e2) {
            e2.printStackTrace();
        }
    }

    public static void start() {
        if (dequeueFuture == null) {
            dequeueFuture = queueExecutor.scheduleAtFixedRate(dequeueTask, 0, 1000, TimeUnit.MILLISECONDS);
        }
    }

    public static void stop() {
        if (dequeueFuture != null) {
            dequeueFuture.cancel(true);
            dequeueFuture = null;
        }
    }

    private static void dequeue() {
        if (queue.size() != 0) {
            Measurements.setBroadcastNewMeasurements(false);
            while (!queue.isEmpty()) {
                try {
                    Object object = queue.remove();
                    if (object instanceof ActivityTrace) {
                        Harvest.addActivityTrace((ActivityTrace) object);
                    } else if (object instanceof Metric) {
                        Harvest.addMetric((Metric) object);
                    } else if (object instanceof AgentHealthException) {
                        Harvest.addAgentHealthException((AgentHealthException) object);
                    } else if (object instanceof Trace) {
                        Measurements.addTracedMethod((Trace) object);
                    } else if (object instanceof HttpTransactionMeasurement) {
                        Measurements.addHttpTransaction((HttpTransactionMeasurement) object);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    AgentHealth.noticeException(e);
                }
            }
            Measurements.broadcast();
            Measurements.setBroadcastNewMeasurements(true);
        }
    }

    public static int size() {
        return queue.size();
    }

    public static void clear() {
        queue.clear();
    }
}
