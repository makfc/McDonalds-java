package com.bumptech.glide.load.engine.prefill;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.engine.cache.MemoryCache;
import com.bumptech.glide.load.resource.bitmap.BitmapResource;
import com.bumptech.glide.util.Util;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.Set;
import java.util.concurrent.TimeUnit;

final class BitmapPreFillRunner implements Runnable {
    private static final Clock DEFAULT_CLOCK = new Clock();
    static final long MAX_BACKOFF_MS = TimeUnit.SECONDS.toMillis(1);
    private final BitmapPool bitmapPool;
    private final Clock clock;
    private long currentDelay;
    private final Handler handler;
    private boolean isCancelled;
    private final MemoryCache memoryCache;
    private final Set<PreFillType> seenTypes;
    private final PreFillQueue toPrefill;

    static class Clock {
        Clock() {
        }

        public long now() {
            return SystemClock.currentThreadTimeMillis();
        }
    }

    private static class UniqueKey implements Key {
        private UniqueKey() {
        }

        public void updateDiskCacheKey(MessageDigest messageDigest) throws UnsupportedEncodingException {
        }
    }

    private boolean allocate() {
        long start = this.clock.now();
        while (!this.toPrefill.isEmpty() && !isGcDetected(start)) {
            PreFillType toAllocate = this.toPrefill.remove();
            Bitmap bitmap = Bitmap.createBitmap(toAllocate.getWidth(), toAllocate.getHeight(), toAllocate.getConfig());
            if (getFreeMemoryCacheBytes() >= Util.getBitmapByteSize(bitmap)) {
                this.memoryCache.put(new UniqueKey(), BitmapResource.obtain(bitmap, this.bitmapPool));
            } else {
                addToBitmapPool(toAllocate, bitmap);
            }
            if (Log.isLoggable("PreFillRunner", 3)) {
                Log.d("PreFillRunner", "allocated [" + toAllocate.getWidth() + "x" + toAllocate.getHeight() + "] " + toAllocate.getConfig() + " size: " + Util.getBitmapByteSize(bitmap));
            }
        }
        return (this.isCancelled || this.toPrefill.isEmpty()) ? false : true;
    }

    private boolean isGcDetected(long startTimeMs) {
        return this.clock.now() - startTimeMs >= 32;
    }

    private int getFreeMemoryCacheBytes() {
        return this.memoryCache.getMaxSize() - this.memoryCache.getCurrentSize();
    }

    private void addToBitmapPool(PreFillType toAllocate, Bitmap bitmap) {
        if (this.seenTypes.add(toAllocate)) {
            Bitmap fromPool = this.bitmapPool.get(toAllocate.getWidth(), toAllocate.getHeight(), toAllocate.getConfig());
            if (fromPool != null) {
                this.bitmapPool.put(fromPool);
            }
        }
        this.bitmapPool.put(bitmap);
    }

    public void run() {
        if (allocate()) {
            this.handler.postDelayed(this, getNextDelay());
        }
    }

    private long getNextDelay() {
        long result = this.currentDelay;
        this.currentDelay = Math.min(this.currentDelay * 4, MAX_BACKOFF_MS);
        return result;
    }
}
