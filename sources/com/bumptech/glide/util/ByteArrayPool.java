package com.bumptech.glide.util;

import android.util.Log;
import java.util.Queue;

public final class ByteArrayPool {
    private static final ByteArrayPool BYTE_ARRAY_POOL = new ByteArrayPool();
    private final Queue<byte[]> tempQueue = Util.createQueue(0);

    public static ByteArrayPool get() {
        return BYTE_ARRAY_POOL;
    }

    private ByteArrayPool() {
    }

    public byte[] getBytes() {
        byte[] result;
        synchronized (this.tempQueue) {
            result = (byte[]) this.tempQueue.poll();
        }
        if (result == null) {
            result = new byte[65536];
            if (Log.isLoggable("ByteArrayPool", 3)) {
                Log.d("ByteArrayPool", "Created temp bytes");
            }
        }
        return result;
    }

    public boolean releaseBytes(byte[] bytes) {
        if (bytes.length != 65536) {
            return false;
        }
        boolean accepted = false;
        synchronized (this.tempQueue) {
            if (this.tempQueue.size() < 32) {
                accepted = true;
                this.tempQueue.offer(bytes);
            }
        }
        return accepted;
    }
}
