package com.facebook.stetho.websocket;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

class Frame {
    public static final byte OPCODE_BINARY_FRAME = (byte) 2;
    public static final byte OPCODE_CONNECTION_CLOSE = (byte) 8;
    public static final byte OPCODE_CONNECTION_PING = (byte) 9;
    public static final byte OPCODE_CONNECTION_PONG = (byte) 10;
    public static final byte OPCODE_TEXT_FRAME = (byte) 1;
    public boolean fin;
    public boolean hasMask;
    public byte[] maskingKey;
    public byte opcode;
    public byte[] payloadData;
    public long payloadLen;
    public boolean rsv1;
    public boolean rsv2;
    public boolean rsv3;

    Frame() {
    }

    public void readFrom(BufferedInputStream input) throws IOException {
        decodeFirstByte(readByteOrThrow(input));
        byte maskAndFirstLengthBits = readByteOrThrow(input);
        this.hasMask = (maskAndFirstLengthBits & 128) != 0;
        this.payloadLen = decodeLength((byte) (maskAndFirstLengthBits & -129), input);
        this.maskingKey = this.hasMask ? decodeMaskingKey(input) : null;
        this.payloadData = new byte[((int) this.payloadLen)];
        readBytesOrThrow(input, this.payloadData, 0, (int) this.payloadLen);
        MaskingHelper.unmask(this.maskingKey, this.payloadData, 0, (int) this.payloadLen);
    }

    public void writeTo(BufferedOutputStream output) throws IOException {
        output.write(encodeFirstByte());
        byte[] lengthAndMaskBit = encodeLength(this.payloadLen);
        if (this.hasMask) {
            lengthAndMaskBit[0] = (byte) (lengthAndMaskBit[0] | 128);
        }
        output.write(lengthAndMaskBit, 0, lengthAndMaskBit.length);
        if (this.hasMask) {
            throw new UnsupportedOperationException("Writing masked data not implemented");
        }
        output.write(this.payloadData, 0, (int) this.payloadLen);
    }

    private void decodeFirstByte(byte b) {
        boolean z;
        boolean z2 = true;
        this.fin = (b & 128) != 0;
        if ((b & 64) != 0) {
            z = true;
        } else {
            z = false;
        }
        this.rsv1 = z;
        if ((b & 32) != 0) {
            z = true;
        } else {
            z = false;
        }
        this.rsv2 = z;
        if ((b & 16) == 0) {
            z2 = false;
        }
        this.rsv3 = z2;
        this.opcode = (byte) (b & 15);
    }

    private byte encodeFirstByte() {
        byte b = (byte) 0;
        if (this.fin) {
            b = (byte) 128;
        }
        if (this.rsv1) {
            b = (byte) (b | 64);
        }
        if (this.rsv2) {
            b = (byte) (b | 32);
        }
        if (this.rsv3) {
            b = (byte) (b | 16);
        }
        return (byte) ((this.opcode & 15) | b);
    }

    private long decodeLength(byte firstLenByte, InputStream in) throws IOException {
        if (firstLenByte <= (byte) 125) {
            return (long) firstLenByte;
        }
        if (firstLenByte == (byte) 126) {
            return (long) (((readByteOrThrow(in) & 255) << 8) | (readByteOrThrow(in) & 255));
        }
        if (firstLenByte == Byte.MAX_VALUE) {
            long len = 0;
            for (int i = 0; i < 8; i++) {
                len = (len | ((long) (readByteOrThrow(in) & 255))) << 8;
            }
            return len;
        }
        throw new IOException("Unexpected length byte: " + firstLenByte);
    }

    private static byte[] encodeLength(long len) {
        if (len <= 125) {
            return new byte[]{(byte) ((int) len)};
        } else if (len <= 65535) {
            return new byte[]{(byte) 126, (byte) ((int) ((len >> 8) & 255)), (byte) ((int) (len & 255))};
        } else {
            return new byte[]{Byte.MAX_VALUE, (byte) ((int) ((len >> 56) & 255)), (byte) ((int) ((len >> 48) & 255)), (byte) ((int) ((len >> 40) & 255)), (byte) ((int) ((len >> 32) & 255)), (byte) ((int) ((len >> 24) & 255)), (byte) ((int) ((len >> 16) & 255)), (byte) ((int) ((len >> 8) & 255)), (byte) ((int) (len & 255))};
        }
    }

    private static byte[] decodeMaskingKey(InputStream in) throws IOException {
        byte[] key = new byte[4];
        readBytesOrThrow(in, key, 0, key.length);
        return key;
    }

    private static void readBytesOrThrow(InputStream in, byte[] buf, int offset, int count) throws IOException {
        while (count > 0) {
            int n = in.read(buf, offset, count);
            if (n == -1) {
                throw new EOFException();
            }
            count -= n;
            offset += n;
        }
    }

    private static byte readByteOrThrow(InputStream in) throws IOException {
        int b = in.read();
        if (b != -1) {
            return (byte) b;
        }
        throw new EOFException();
    }
}
