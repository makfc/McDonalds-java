package com.facebook.stetho.dumpapp;

import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

public class StreamFramer implements Closeable {
    private static final byte EXIT_FRAME_PREFIX = (byte) 120;
    private static final byte STDERR_FRAME_PREFIX = (byte) 50;
    private static final byte STDOUT_FRAME_PREFIX = (byte) 49;
    private final DataOutputStream mMultiplexedOutputStream;
    private final PrintStream mStderr = new PrintStream(new FramingOutputStream(this.mMultiplexedOutputStream, STDERR_FRAME_PREFIX));
    private final PrintStream mStdout = new PrintStream(new BufferedOutputStream(new FramingOutputStream(this.mMultiplexedOutputStream, STDOUT_FRAME_PREFIX)));

    private class FramingOutputStream extends OutputStream {
        private boolean mIsClosed = false;
        private final OutputStream mOut;
        private final byte mPrefix;

        FramingOutputStream(OutputStream innerStream, byte prefix) {
            this.mOut = innerStream;
            this.mPrefix = prefix;
        }

        public void write(byte[] buffer, int offset, int length) throws IOException {
            if (this.mIsClosed) {
                throw new IOException("Stream is closed");
            } else if (length > 0) {
                try {
                    synchronized (StreamFramer.this) {
                        StreamFramer.this.writeIntFrame(this.mPrefix, length);
                        this.mOut.write(buffer, offset, length);
                        this.mOut.flush();
                    }
                } catch (IOException e) {
                    throw new DumpappOutputBrokenException(e);
                }
            }
        }

        public void write(int oneByte) throws IOException {
            byte[] buffer = new byte[]{(byte) oneByte};
            write(buffer, 0, buffer.length);
        }

        public void write(byte[] buffer) throws IOException {
            write(buffer, 0, buffer.length);
        }

        public void close() throws IOException {
            this.mIsClosed = true;
        }
    }

    public StreamFramer(OutputStream output) throws IOException {
        this.mMultiplexedOutputStream = new DataOutputStream(output);
    }

    public PrintStream getStdout() {
        return this.mStdout;
    }

    public PrintStream getStderr() {
        return this.mStderr;
    }

    public synchronized void writeExitCode(int exitCode) throws IOException {
        this.mStdout.flush();
        this.mStderr.flush();
        writeIntFrame(EXIT_FRAME_PREFIX, exitCode);
    }

    public synchronized void close() throws IOException {
        this.mMultiplexedOutputStream.close();
    }

    private void writeIntFrame(byte type, int intParameter) throws IOException {
        this.mMultiplexedOutputStream.write(type);
        this.mMultiplexedOutputStream.writeInt(intParameter);
    }
}
