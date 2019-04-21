package p049hk.com.aisoft.easyaddrui;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

/* renamed from: hk.com.aisoft.easyaddrui.TrailerInputStream */
class TrailerInputStream extends InputStream {
    /* renamed from: in */
    private final InputStream f7862in;
    private byte[] trailerBuffer;
    private final int trailerSize;

    public TrailerInputStream(InputStream in, int trailerSize) {
        boolean z;
        Validate.notNull(in, "InputStream cannot be null.", new Object[0]);
        if (trailerSize > -1) {
            z = true;
        } else {
            z = false;
        }
        Validate.isTrue(z, "Trailer size cannot be negative.", new Object[0]);
        this.f7862in = in;
        this.trailerSize = trailerSize;
    }

    private void fillTrailerBuffer() throws IOException {
        this.trailerBuffer = new byte[this.trailerSize];
        if (this.trailerSize != 0 && StreamUtils.readAllBytes(this.f7862in, this.trailerBuffer) != this.trailerBuffer.length) {
            throw new EOFException(String.format("Trailer size was %d bytes but stream only contained %d bytes.", new Object[]{Integer.valueOf(this.trailerSize), Integer.valueOf(StreamUtils.readAllBytes(this.f7862in, this.trailerBuffer))}));
        }
    }

    public int read() throws IOException {
        if (this.trailerBuffer == null) {
            fillTrailerBuffer();
        }
        int nextByte = this.f7862in.read();
        if (nextByte == -1 || this.trailerBuffer.length == 0) {
            return nextByte;
        }
        int result = this.trailerBuffer[0] & 255;
        System.arraycopy(this.trailerBuffer, 1, this.trailerBuffer, 0, this.trailerBuffer.length - 1);
        this.trailerBuffer[this.trailerBuffer.length - 1] = (byte) nextByte;
        return result;
    }

    public int read(byte[] b) throws IOException {
        return read(b, 0, b.length);
    }

    public int read(byte[] b, int off, int len) throws IOException {
        if (b == null) {
            throw new NullPointerException();
        } else if (off < 0 || len < 0 || len > b.length - off) {
            throw new IndexOutOfBoundsException();
        } else if (len == 0) {
            return 0;
        } else {
            if (this.trailerBuffer == null) {
                fillTrailerBuffer();
            }
            byte[] inputBuffer = new byte[len];
            int numBytesRead = this.f7862in.read(inputBuffer);
            if (numBytesRead == -1) {
                return numBytesRead;
            }
            if (this.trailerSize == 0) {
                System.arraycopy(inputBuffer, 0, b, off, numBytesRead);
                return numBytesRead;
            } else if (numBytesRead <= this.trailerSize) {
                System.arraycopy(this.trailerBuffer, 0, b, off, numBytesRead);
                System.arraycopy(this.trailerBuffer, numBytesRead, this.trailerBuffer, 0, this.trailerSize - numBytesRead);
                System.arraycopy(inputBuffer, 0, this.trailerBuffer, this.trailerSize - numBytesRead, numBytesRead);
                return numBytesRead;
            } else {
                System.arraycopy(this.trailerBuffer, 0, b, off, this.trailerSize);
                System.arraycopy(inputBuffer, 0, b, off + this.trailerSize, numBytesRead - this.trailerSize);
                System.arraycopy(inputBuffer, numBytesRead - this.trailerSize, this.trailerBuffer, 0, this.trailerSize);
                return numBytesRead;
            }
        }
    }

    public int available() throws IOException {
        if (this.trailerBuffer == null) {
            return Math.max(0, this.f7862in.available() - this.trailerSize);
        }
        return this.f7862in.available();
    }

    public void close() throws IOException {
        this.f7862in.close();
    }

    public boolean markSupported() {
        return false;
    }

    public byte[] getTrailer() {
        return (byte[]) this.trailerBuffer.clone();
    }
}
