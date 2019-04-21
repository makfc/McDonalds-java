package com.newrelic.agent.android.instrumentation.p053io;

import java.io.IOException;
import java.io.OutputStream;

/* renamed from: com.newrelic.agent.android.instrumentation.io.CountingOutputStream */
public final class CountingOutputStream extends OutputStream implements StreamCompleteListenerSource {
    private long count = 0;
    private final OutputStream impl;
    private final StreamCompleteListenerManager listenerManager = new StreamCompleteListenerManager();

    public CountingOutputStream(OutputStream impl) throws IOException {
        if (impl == null) {
            throw new IOException("CountingOutputStream: output stream cannot be null");
        }
        this.impl = impl;
    }

    public void addStreamCompleteListener(StreamCompleteListener streamCompleteListener) {
        this.listenerManager.addStreamCompleteListener(streamCompleteListener);
    }

    public void removeStreamCompleteListener(StreamCompleteListener streamCompleteListener) {
        this.listenerManager.removeStreamCompleteListener(streamCompleteListener);
    }

    public long getCount() {
        return this.count;
    }

    public void write(int oneByte) throws IOException {
        try {
            this.impl.write(oneByte);
            this.count++;
        } catch (IOException e) {
            notifyStreamError(e);
            throw e;
        }
    }

    public void write(byte[] buffer) throws IOException {
        try {
            this.impl.write(buffer);
            this.count += (long) buffer.length;
        } catch (IOException e) {
            notifyStreamError(e);
            throw e;
        }
    }

    public void write(byte[] buffer, int offset, int count) throws IOException {
        try {
            this.impl.write(buffer, offset, count);
            this.count += (long) count;
        } catch (IOException e) {
            notifyStreamError(e);
            throw e;
        }
    }

    public void flush() throws IOException {
        try {
            this.impl.flush();
        } catch (IOException e) {
            notifyStreamError(e);
            throw e;
        }
    }

    public void close() throws IOException {
        try {
            this.impl.close();
            notifyStreamComplete();
        } catch (IOException e) {
            notifyStreamError(e);
            throw e;
        }
    }

    private void notifyStreamComplete() {
        if (!this.listenerManager.isComplete()) {
            this.listenerManager.notifyStreamComplete(new StreamCompleteEvent(this, this.count));
        }
    }

    private void notifyStreamError(Exception e) {
        if (!this.listenerManager.isComplete()) {
            this.listenerManager.notifyStreamError(new StreamCompleteEvent(this, this.count, e));
        }
    }
}
