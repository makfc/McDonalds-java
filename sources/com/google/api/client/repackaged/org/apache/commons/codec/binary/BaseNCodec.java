package com.google.api.client.repackaged.org.apache.commons.codec.binary;

import com.google.api.client.repackaged.org.apache.commons.codec.BinaryDecoder;
import com.google.api.client.repackaged.org.apache.commons.codec.BinaryEncoder;

public abstract class BaseNCodec implements BinaryDecoder, BinaryEncoder {
    protected final byte PAD = (byte) 61;
    protected byte[] buffer;
    private final int chunkSeparatorLength;
    protected int currentLinePos;
    private final int encodedBlockSize;
    protected boolean eof;
    protected final int lineLength;
    protected int modulus;
    protected int pos;
    private int readPos;
    private final int unencodedBlockSize;

    public abstract void decode(byte[] bArr, int i, int i2);

    public abstract void encode(byte[] bArr, int i, int i2);

    public abstract boolean isInAlphabet(byte b);

    protected BaseNCodec(int unencodedBlockSize, int encodedBlockSize, int lineLength, int chunkSeparatorLength) {
        this.unencodedBlockSize = unencodedBlockSize;
        this.encodedBlockSize = encodedBlockSize;
        int i = (lineLength <= 0 || chunkSeparatorLength <= 0) ? 0 : (lineLength / encodedBlockSize) * encodedBlockSize;
        this.lineLength = i;
        this.chunkSeparatorLength = chunkSeparatorLength;
    }

    /* Access modifiers changed, original: 0000 */
    public int available() {
        return this.buffer != null ? this.pos - this.readPos : 0;
    }

    /* Access modifiers changed, original: protected */
    public int getDefaultBufferSize() {
        return 8192;
    }

    private void resizeBuffer() {
        if (this.buffer == null) {
            this.buffer = new byte[getDefaultBufferSize()];
            this.pos = 0;
            this.readPos = 0;
            return;
        }
        byte[] b = new byte[(this.buffer.length * 2)];
        System.arraycopy(this.buffer, 0, b, 0, this.buffer.length);
        this.buffer = b;
    }

    /* Access modifiers changed, original: protected */
    public void ensureBufferSize(int size) {
        if (this.buffer == null || this.buffer.length < this.pos + size) {
            resizeBuffer();
        }
    }

    /* Access modifiers changed, original: 0000 */
    public int readResults(byte[] b, int bPos, int bAvail) {
        if (this.buffer != null) {
            int len = Math.min(available(), bAvail);
            System.arraycopy(this.buffer, this.readPos, b, bPos, len);
            this.readPos += len;
            if (this.readPos < this.pos) {
                return len;
            }
            this.buffer = null;
            return len;
        }
        return this.eof ? -1 : 0;
    }

    private void reset() {
        this.buffer = null;
        this.pos = 0;
        this.readPos = 0;
        this.currentLinePos = 0;
        this.modulus = 0;
        this.eof = false;
    }

    public byte[] decode(String pArray) {
        return decode(StringUtils.getBytesUtf8(pArray));
    }

    public byte[] decode(byte[] pArray) {
        reset();
        if (pArray == null || pArray.length == 0) {
            return pArray;
        }
        decode(pArray, 0, pArray.length);
        decode(pArray, 0, -1);
        byte[] result = new byte[this.pos];
        readResults(result, 0, result.length);
        return result;
    }

    public byte[] encode(byte[] pArray) {
        reset();
        if (pArray == null || pArray.length == 0) {
            return pArray;
        }
        encode(pArray, 0, pArray.length);
        encode(pArray, 0, -1);
        byte[] buf = new byte[(this.pos - this.readPos)];
        readResults(buf, 0, buf.length);
        return buf;
    }

    /* Access modifiers changed, original: protected */
    public boolean containsAlphabetOrPad(byte[] arrayOctet) {
        if (arrayOctet == null) {
            return false;
        }
        for (byte element : arrayOctet) {
            if ((byte) 61 == element || isInAlphabet(element)) {
                return true;
            }
        }
        return false;
    }

    public long getEncodedLength(byte[] pArray) {
        long len = ((long) (((pArray.length + this.unencodedBlockSize) - 1) / this.unencodedBlockSize)) * ((long) this.encodedBlockSize);
        if (this.lineLength > 0) {
            return len + ((((((long) this.lineLength) + len) - 1) / ((long) this.lineLength)) * ((long) this.chunkSeparatorLength));
        }
        return len;
    }
}
