package com.facebook;

import android.os.Handler;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class RequestBatch extends AbstractList<Request> {
    private static AtomicInteger idGenerator = new AtomicInteger();
    private String batchApplicationId;
    private Handler callbackHandler;
    private List<Callback> callbacks;
    /* renamed from: id */
    private final String f6018id;
    private List<Request> requests;
    private int timeoutInMilliseconds;

    public interface Callback {
        void onBatchCompleted(RequestBatch requestBatch);
    }

    public interface OnProgressCallback extends Callback {
        void onBatchProgress(RequestBatch requestBatch, long j, long j2);
    }

    public RequestBatch() {
        this.requests = new ArrayList();
        this.timeoutInMilliseconds = 0;
        this.f6018id = Integer.valueOf(idGenerator.incrementAndGet()).toString();
        this.callbacks = new ArrayList();
        this.requests = new ArrayList();
    }

    public RequestBatch(Collection<Request> requests) {
        this.requests = new ArrayList();
        this.timeoutInMilliseconds = 0;
        this.f6018id = Integer.valueOf(idGenerator.incrementAndGet()).toString();
        this.callbacks = new ArrayList();
        this.requests = new ArrayList(requests);
    }

    public RequestBatch(Request... requests) {
        this.requests = new ArrayList();
        this.timeoutInMilliseconds = 0;
        this.f6018id = Integer.valueOf(idGenerator.incrementAndGet()).toString();
        this.callbacks = new ArrayList();
        this.requests = Arrays.asList(requests);
    }

    public RequestBatch(RequestBatch requests) {
        this.requests = new ArrayList();
        this.timeoutInMilliseconds = 0;
        this.f6018id = Integer.valueOf(idGenerator.incrementAndGet()).toString();
        this.callbacks = new ArrayList();
        this.requests = new ArrayList(requests);
        this.callbackHandler = requests.callbackHandler;
        this.timeoutInMilliseconds = requests.timeoutInMilliseconds;
        this.callbacks = new ArrayList(requests.callbacks);
    }

    public int getTimeout() {
        return this.timeoutInMilliseconds;
    }

    public void setTimeout(int timeoutInMilliseconds) {
        if (timeoutInMilliseconds < 0) {
            throw new IllegalArgumentException("Argument timeoutInMilliseconds must be >= 0.");
        }
        this.timeoutInMilliseconds = timeoutInMilliseconds;
    }

    public void addCallback(Callback callback) {
        if (!this.callbacks.contains(callback)) {
            this.callbacks.add(callback);
        }
    }

    public void removeCallback(Callback callback) {
        this.callbacks.remove(callback);
    }

    public final boolean add(Request request) {
        return this.requests.add(request);
    }

    public final void add(int location, Request request) {
        this.requests.add(location, request);
    }

    public final void clear() {
        this.requests.clear();
    }

    public final Request get(int i) {
        return (Request) this.requests.get(i);
    }

    public final Request remove(int location) {
        return (Request) this.requests.remove(location);
    }

    public final Request set(int location, Request request) {
        return (Request) this.requests.set(location, request);
    }

    public final int size() {
        return this.requests.size();
    }

    /* Access modifiers changed, original: final */
    public final String getId() {
        return this.f6018id;
    }

    /* Access modifiers changed, original: final */
    public final Handler getCallbackHandler() {
        return this.callbackHandler;
    }

    /* Access modifiers changed, original: final */
    public final void setCallbackHandler(Handler callbackHandler) {
        this.callbackHandler = callbackHandler;
    }

    /* Access modifiers changed, original: final */
    public final List<Request> getRequests() {
        return this.requests;
    }

    /* Access modifiers changed, original: final */
    public final List<Callback> getCallbacks() {
        return this.callbacks;
    }

    /* Access modifiers changed, original: final */
    public final String getBatchApplicationId() {
        return this.batchApplicationId;
    }

    /* Access modifiers changed, original: final */
    public final void setBatchApplicationId(String batchApplicationId) {
        this.batchApplicationId = batchApplicationId;
    }

    public final List<Response> executeAndWait() {
        return executeAndWaitImpl();
    }

    public final RequestAsyncTask executeAsync() {
        return executeAsyncImpl();
    }

    /* Access modifiers changed, original: 0000 */
    public List<Response> executeAndWaitImpl() {
        return Request.executeBatchAndWait(this);
    }

    /* Access modifiers changed, original: 0000 */
    public RequestAsyncTask executeAsyncImpl() {
        return Request.executeBatchAsync(this);
    }
}
