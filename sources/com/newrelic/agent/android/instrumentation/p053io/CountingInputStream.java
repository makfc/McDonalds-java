package com.newrelic.agent.android.instrumentation.p053io;

import com.newrelic.agent.android.Agent;
import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* renamed from: com.newrelic.agent.android.instrumentation.io.CountingInputStream */
public final class CountingInputStream extends InputStream implements StreamCompleteListenerSource {
    private static final AgentLog log = AgentLogManager.getAgentLog();
    private final ByteBuffer buffer;
    private long count = 0;
    private boolean enableBuffering = false;
    private final InputStream impl;
    private final StreamCompleteListenerManager listenerManager = new StreamCompleteListenerManager();

    public CountingInputStream(InputStream impl) throws IOException {
        if (impl == null) {
            throw new IOException("CountingInputStream: input stream cannot be null");
        }
        this.impl = impl;
        if (this.enableBuffering) {
            this.buffer = ByteBuffer.allocate(Agent.getResponseBodyLimit());
            fillBuffer();
            return;
        }
        this.buffer = null;
    }

    public CountingInputStream(InputStream impl, boolean enableBuffering) throws IOException {
        if (impl == null) {
            throw new IOException("CountingInputStream: input stream cannot be null");
        }
        this.impl = impl;
        this.enableBuffering = enableBuffering;
        if (enableBuffering) {
            this.buffer = ByteBuffer.allocate(Agent.getResponseBodyLimit());
            fillBuffer();
            return;
        }
        this.buffer = null;
    }

    public void addStreamCompleteListener(StreamCompleteListener streamCompleteListener) {
        this.listenerManager.addStreamCompleteListener(streamCompleteListener);
    }

    public void removeStreamCompleteListener(StreamCompleteListener streamCompleteListener) {
        this.listenerManager.removeStreamCompleteListener(streamCompleteListener);
    }

    /* JADX WARNING: Missing block: B:31:?, code skipped:
            return r1;
     */
    public int read() throws java.io.IOException {
        /*
        r10 = this;
        r8 = 1;
        r3 = r10.enableBuffering;
        if (r3 == 0) goto L_0x0020;
    L_0x0006:
        r4 = r10.buffer;
        monitor-enter(r4);
        r6 = 1;
        r3 = r10.bufferHasBytes(r6);	 Catch:{ all -> 0x002f }
        if (r3 == 0) goto L_0x001f;
    L_0x0011:
        r1 = r10.readBuffer();	 Catch:{ all -> 0x002f }
        if (r1 < 0) goto L_0x001c;
    L_0x0017:
        r6 = r10.count;	 Catch:{ all -> 0x002f }
        r6 = r6 + r8;
        r10.count = r6;	 Catch:{ all -> 0x002f }
    L_0x001c:
        monitor-exit(r4);	 Catch:{ all -> 0x002f }
        r2 = r1;
    L_0x001e:
        return r2;
    L_0x001f:
        monitor-exit(r4);	 Catch:{ all -> 0x002f }
    L_0x0020:
        r3 = r10.impl;	 Catch:{ IOException -> 0x0036 }
        r1 = r3.read();	 Catch:{ IOException -> 0x0036 }
        if (r1 < 0) goto L_0x0032;
    L_0x0028:
        r4 = r10.count;	 Catch:{ IOException -> 0x0036 }
        r4 = r4 + r8;
        r10.count = r4;	 Catch:{ IOException -> 0x0036 }
    L_0x002d:
        r2 = r1;
        goto L_0x001e;
    L_0x002f:
        r3 = move-exception;
        monitor-exit(r4);	 Catch:{ all -> 0x002f }
        throw r3;
    L_0x0032:
        r10.notifyStreamComplete();	 Catch:{ IOException -> 0x0036 }
        goto L_0x002d;
    L_0x0036:
        r0 = move-exception;
        r10.notifyStreamError(r0);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.newrelic.agent.android.instrumentation.p053io.CountingInputStream.read():int");
    }

    public int read(byte[] b) throws IOException {
        int n;
        int numBytesFromBuffer = 0;
        int inputBufferRemaining = b.length;
        if (this.enableBuffering) {
            synchronized (this.buffer) {
                if (bufferHasBytes((long) inputBufferRemaining)) {
                    n = readBufferBytes(b);
                    if (n >= 0) {
                        this.count += (long) n;
                        return n;
                    }
                    throw new IOException("readBufferBytes failed");
                }
                int remaining = this.buffer.remaining();
                if (remaining > 0) {
                    numBytesFromBuffer = readBufferBytes(b, 0, remaining);
                    if (numBytesFromBuffer < 0) {
                        throw new IOException("partial read from buffer failed");
                    }
                    inputBufferRemaining -= numBytesFromBuffer;
                    this.count += (long) numBytesFromBuffer;
                }
            }
        }
        try {
            n = this.impl.read(b, numBytesFromBuffer, inputBufferRemaining);
            if (n >= 0) {
                this.count += (long) n;
                return n + numBytesFromBuffer;
            } else if (numBytesFromBuffer > 0) {
                return numBytesFromBuffer;
            } else {
                notifyStreamComplete();
                return n;
            }
        } catch (IOException e) {
            log.error(e.toString());
            System.out.println("NOTIFY STREAM ERROR: " + e);
            e.printStackTrace();
            notifyStreamError(e);
            throw e;
        }
    }

    public int read(byte[] b, int off, int len) throws IOException {
        int n;
        int numBytesFromBuffer = 0;
        int inputBufferRemaining = len;
        if (this.enableBuffering) {
            synchronized (this.buffer) {
                if (bufferHasBytes((long) inputBufferRemaining)) {
                    n = readBufferBytes(b, off, len);
                    if (n >= 0) {
                        this.count += (long) n;
                        return n;
                    }
                    throw new IOException("readBufferBytes failed");
                }
                int remaining = this.buffer.remaining();
                if (remaining > 0) {
                    numBytesFromBuffer = readBufferBytes(b, off, remaining);
                    if (numBytesFromBuffer < 0) {
                        throw new IOException("partial read from buffer failed");
                    }
                    inputBufferRemaining -= numBytesFromBuffer;
                    this.count += (long) numBytesFromBuffer;
                }
            }
        }
        try {
            n = this.impl.read(b, off + numBytesFromBuffer, inputBufferRemaining);
            if (n >= 0) {
                this.count += (long) n;
                return n + numBytesFromBuffer;
            } else if (numBytesFromBuffer > 0) {
                return numBytesFromBuffer;
            } else {
                notifyStreamComplete();
                return n;
            }
        } catch (IOException e) {
            notifyStreamError(e);
            throw e;
        }
    }

    public long skip(long byteCount) throws IOException {
        long toSkip = byteCount;
        if (this.enableBuffering) {
            synchronized (this.buffer) {
                if (bufferHasBytes(byteCount)) {
                    this.buffer.position((int) byteCount);
                    this.count += byteCount;
                    return byteCount;
                }
                toSkip = byteCount - ((long) this.buffer.remaining());
                if (toSkip > 0) {
                    this.buffer.position(this.buffer.remaining());
                } else {
                    throw new IOException("partial read from buffer (skip) failed");
                }
            }
        }
        try {
            long n = this.impl.skip(toSkip);
            this.count += n;
            return n;
        } catch (IOException e) {
            notifyStreamError(e);
            throw e;
        }
    }

    public int available() throws IOException {
        int remaining = 0;
        if (this.enableBuffering) {
            remaining = this.buffer.remaining();
        }
        try {
            return this.impl.available() + remaining;
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

    public void mark(int readlimit) {
        if (markSupported()) {
            this.impl.mark(readlimit);
        }
    }

    public boolean markSupported() {
        return this.impl.markSupported();
    }

    public void reset() throws IOException {
        if (markSupported()) {
            try {
                this.impl.reset();
            } catch (IOException e) {
                notifyStreamError(e);
                throw e;
            }
        }
    }

    private int readBuffer() {
        if (bufferEmpty()) {
            return -1;
        }
        return this.buffer.get();
    }

    private int readBufferBytes(byte[] bytes) {
        return readBufferBytes(bytes, 0, bytes.length);
    }

    private int readBufferBytes(byte[] bytes, int offset, int length) {
        if (bufferEmpty()) {
            return -1;
        }
        int remainingBefore = this.buffer.remaining();
        this.buffer.get(bytes, offset, length);
        return remainingBefore - this.buffer.remaining();
    }

    private boolean bufferHasBytes(long num) {
        return ((long) this.buffer.remaining()) >= num;
    }

    private boolean bufferEmpty() {
        if (this.buffer.hasRemaining()) {
            return false;
        }
        return true;
    }

    public void fillBuffer() {
        if (this.buffer != null && this.buffer.hasArray()) {
            synchronized (this.buffer) {
                int bytesRead = 0;
                try {
                    bytesRead = this.impl.read(this.buffer.array(), 0, this.buffer.capacity());
                } catch (IOException e) {
                    log.error(e.toString());
                }
                if (bytesRead <= 0) {
                    this.buffer.limit(0);
                } else if (bytesRead < this.buffer.capacity()) {
                    this.buffer.limit(bytesRead);
                }
            }
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

    public void setBufferingEnabled(boolean enableBuffering) {
        this.enableBuffering = enableBuffering;
    }

    public String getBufferAsString() {
        if (this.buffer == null) {
            return "";
        }
        String str;
        synchronized (this.buffer) {
            byte[] buf = new byte[this.buffer.limit()];
            for (int i = 0; i < this.buffer.limit(); i++) {
                buf[i] = this.buffer.get(i);
            }
            str = new String(buf);
        }
        return str;
    }
}
