package okio;

import android.support.p000v4.media.session.PlaybackStateCompat;
import java.io.IOException;
import java.io.OutputStream;

final class RealBufferedSink implements BufferedSink {
    public final Buffer buffer;
    private boolean closed;
    public final Sink sink;

    /* renamed from: okio.RealBufferedSink$1 */
    class C46311 extends OutputStream {
        C46311() {
        }

        public void write(int b) throws IOException {
            if (RealBufferedSink.this.closed) {
                throw new IOException("closed");
            }
            RealBufferedSink.this.buffer.writeByte((byte) b);
            RealBufferedSink.this.emitCompleteSegments();
        }

        public void write(byte[] data, int offset, int byteCount) throws IOException {
            if (RealBufferedSink.this.closed) {
                throw new IOException("closed");
            }
            RealBufferedSink.this.buffer.write(data, offset, byteCount);
            RealBufferedSink.this.emitCompleteSegments();
        }

        public void flush() throws IOException {
            if (!RealBufferedSink.this.closed) {
                RealBufferedSink.this.flush();
            }
        }

        public void close() throws IOException {
            RealBufferedSink.this.close();
        }

        public String toString() {
            return RealBufferedSink.this + ".outputStream()";
        }
    }

    public RealBufferedSink(Sink sink, Buffer buffer) {
        if (sink == null) {
            throw new IllegalArgumentException("sink == null");
        }
        this.buffer = buffer;
        this.sink = sink;
    }

    public RealBufferedSink(Sink sink) {
        this(sink, new Buffer());
    }

    public Buffer buffer() {
        return this.buffer;
    }

    public void write(Buffer source, long byteCount) throws IOException {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        this.buffer.write(source, byteCount);
        emitCompleteSegments();
    }

    public BufferedSink write(ByteString byteString) throws IOException {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        this.buffer.write(byteString);
        return emitCompleteSegments();
    }

    public BufferedSink writeUtf8(String string) throws IOException {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        this.buffer.writeUtf8(string);
        return emitCompleteSegments();
    }

    public BufferedSink write(byte[] source) throws IOException {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        this.buffer.write(source);
        return emitCompleteSegments();
    }

    public BufferedSink write(byte[] source, int offset, int byteCount) throws IOException {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        this.buffer.write(source, offset, byteCount);
        return emitCompleteSegments();
    }

    public long writeAll(Source source) throws IOException {
        if (source == null) {
            throw new IllegalArgumentException("source == null");
        }
        long totalBytesRead = 0;
        while (true) {
            long readCount = source.read(this.buffer, PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH);
            if (readCount == -1) {
                return totalBytesRead;
            }
            totalBytesRead += readCount;
            emitCompleteSegments();
        }
    }

    public BufferedSink writeByte(int b) throws IOException {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        this.buffer.writeByte(b);
        return emitCompleteSegments();
    }

    public BufferedSink writeShort(int s) throws IOException {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        this.buffer.writeShort(s);
        return emitCompleteSegments();
    }

    public BufferedSink writeInt(int i) throws IOException {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        this.buffer.writeInt(i);
        return emitCompleteSegments();
    }

    public BufferedSink writeIntLe(int i) throws IOException {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        this.buffer.writeIntLe(i);
        return emitCompleteSegments();
    }

    public BufferedSink writeDecimalLong(long v) throws IOException {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        this.buffer.writeDecimalLong(v);
        return emitCompleteSegments();
    }

    public BufferedSink writeHexadecimalUnsignedLong(long v) throws IOException {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        this.buffer.writeHexadecimalUnsignedLong(v);
        return emitCompleteSegments();
    }

    public BufferedSink emitCompleteSegments() throws IOException {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        long byteCount = this.buffer.completeSegmentByteCount();
        if (byteCount > 0) {
            this.sink.write(this.buffer, byteCount);
        }
        return this;
    }

    public BufferedSink emit() throws IOException {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        long byteCount = this.buffer.size();
        if (byteCount > 0) {
            this.sink.write(this.buffer, byteCount);
        }
        return this;
    }

    public OutputStream outputStream() {
        return new C46311();
    }

    public void flush() throws IOException {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        if (this.buffer.size > 0) {
            this.sink.write(this.buffer, this.buffer.size);
        }
        this.sink.flush();
    }

    public void close() throws IOException {
        if (!this.closed) {
            Throwable thrown = null;
            try {
                if (this.buffer.size > 0) {
                    this.sink.write(this.buffer, this.buffer.size);
                }
            } catch (Throwable e) {
                thrown = e;
            }
            try {
                this.sink.close();
            } catch (Throwable e2) {
                if (thrown == null) {
                    thrown = e2;
                }
            }
            this.closed = true;
            if (thrown != null) {
                Util.sneakyRethrow(thrown);
            }
        }
    }

    public Timeout timeout() {
        return this.sink.timeout();
    }

    public String toString() {
        return "buffer(" + this.sink + ")";
    }
}
