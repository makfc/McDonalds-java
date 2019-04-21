package com.squareup.okhttp;

import com.squareup.okhttp.internal.Util;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public final class Dispatcher {
    private final Deque<Call> executedCalls = new ArrayDeque();
    private ExecutorService executorService;
    private int maxRequests = 64;
    private int maxRequestsPerHost = 5;
    private final Deque<AsyncCall> readyCalls = new ArrayDeque();
    private final Deque<AsyncCall> runningCalls = new ArrayDeque();

    public synchronized ExecutorService getExecutorService() {
        if (this.executorService == null) {
            this.executorService = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60, TimeUnit.SECONDS, new SynchronousQueue(), Util.threadFactory("OkHttp Dispatcher", false));
        }
        return this.executorService;
    }

    /* Access modifiers changed, original: declared_synchronized */
    public synchronized void enqueue(AsyncCall call) {
        if (this.runningCalls.size() >= this.maxRequests || runningCallsForHost(call) >= this.maxRequestsPerHost) {
            this.readyCalls.add(call);
        } else {
            this.runningCalls.add(call);
            getExecutorService().execute(call);
        }
    }

    /* Access modifiers changed, original: declared_synchronized */
    public synchronized void finished(AsyncCall call) {
        if (this.runningCalls.remove(call)) {
            promoteCalls();
        } else {
            throw new AssertionError("AsyncCall wasn't running!");
        }
    }

    private void promoteCalls() {
        if (this.runningCalls.size() < this.maxRequests && !this.readyCalls.isEmpty()) {
            Iterator<AsyncCall> i = this.readyCalls.iterator();
            while (i.hasNext()) {
                AsyncCall call = (AsyncCall) i.next();
                if (runningCallsForHost(call) < this.maxRequestsPerHost) {
                    i.remove();
                    this.runningCalls.add(call);
                    getExecutorService().execute(call);
                }
                if (this.runningCalls.size() >= this.maxRequests) {
                    return;
                }
            }
        }
    }

    private int runningCallsForHost(AsyncCall call) {
        int result = 0;
        for (AsyncCall c : this.runningCalls) {
            if (c.host().equals(call.host())) {
                result++;
            }
        }
        return result;
    }

    /* Access modifiers changed, original: declared_synchronized */
    public synchronized void executed(Call call) {
        this.executedCalls.add(call);
    }

    /* Access modifiers changed, original: declared_synchronized */
    public synchronized void finished(Call call) {
        if (!this.executedCalls.remove(call)) {
            throw new AssertionError("Call wasn't in-flight!");
        }
    }
}
