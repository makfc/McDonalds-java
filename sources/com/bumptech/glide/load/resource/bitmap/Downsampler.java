package com.bumptech.glide.load.resource.bitmap;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory.Options;
import android.os.Build.VERSION;
import android.support.p000v4.widget.ExploreByTouchHelper;
import android.util.Log;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.ImageHeaderParser.ImageType;
import com.bumptech.glide.util.MarkEnforcingInputStream;
import com.bumptech.glide.util.Util;
import com.newrelic.agent.android.instrumentation.BitmapFactoryInstrumentation;
import java.io.IOException;
import java.io.InputStream;
import java.util.EnumSet;
import java.util.Queue;
import java.util.Set;

public abstract class Downsampler implements BitmapDecoder<InputStream> {
    public static final Downsampler AT_LEAST = new C16081();
    public static final Downsampler AT_MOST = new C16092();
    public static final Downsampler NONE = new C16103();
    private static final Queue<Options> OPTIONS_QUEUE = Util.createQueue(0);
    private static final Set<ImageType> TYPES_THAT_USE_POOL = EnumSet.of(ImageType.JPEG, ImageType.PNG_A, ImageType.PNG);

    /* renamed from: com.bumptech.glide.load.resource.bitmap.Downsampler$1 */
    static class C16081 extends Downsampler {
        C16081() {
        }

        /* Access modifiers changed, original: protected */
        public int getSampleSize(int inWidth, int inHeight, int outWidth, int outHeight) {
            return Math.min(inHeight / outHeight, inWidth / outWidth);
        }

        public String getId() {
            return "AT_LEAST.com.bumptech.glide.load.data.bitmap";
        }
    }

    /* renamed from: com.bumptech.glide.load.resource.bitmap.Downsampler$2 */
    static class C16092 extends Downsampler {
        C16092() {
        }

        /* Access modifiers changed, original: protected */
        public int getSampleSize(int inWidth, int inHeight, int outWidth, int outHeight) {
            int i = 1;
            int maxIntegerFactor = (int) Math.ceil((double) Math.max(((float) inHeight) / ((float) outHeight), ((float) inWidth) / ((float) outWidth)));
            int lesserOrEqualSampleSize = Math.max(1, Integer.highestOneBit(maxIntegerFactor));
            if (lesserOrEqualSampleSize >= maxIntegerFactor) {
                i = 0;
            }
            return lesserOrEqualSampleSize << i;
        }

        public String getId() {
            return "AT_MOST.com.bumptech.glide.load.data.bitmap";
        }
    }

    /* renamed from: com.bumptech.glide.load.resource.bitmap.Downsampler$3 */
    static class C16103 extends Downsampler {
        C16103() {
        }

        /* Access modifiers changed, original: protected */
        public int getSampleSize(int inWidth, int inHeight, int outWidth, int outHeight) {
            return 0;
        }

        public String getId() {
            return "NONE.com.bumptech.glide.load.data.bitmap";
        }
    }

    public abstract int getSampleSize(int i, int i2, int i3, int i4);

    /* JADX WARNING: Unknown top exception splitter block from list: {B:8:0x003d=Splitter:B:8:0x003d, B:36:0x00cf=Splitter:B:36:0x00cf} */
    public android.graphics.Bitmap decode(java.io.InputStream r28, com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool r29, int r30, int r31, com.bumptech.glide.load.DecodeFormat r32) {
        /*
        r27 = this;
        r16 = com.bumptech.glide.util.ByteArrayPool.get();
        r17 = r16.getBytes();
        r18 = r16.getBytes();
        r10 = getDefaultOptions();
        r9 = new com.bumptech.glide.load.resource.bitmap.RecyclableBufferedInputStream;
        r0 = r28;
        r1 = r18;
        r9.<init>(r0, r1);
        r21 = com.bumptech.glide.util.ExceptionCatchingInputStream.obtain(r9);
        r23 = new com.bumptech.glide.util.MarkEnforcingInputStream;
        r0 = r23;
        r1 = r21;
        r0.<init>(r1);
        r3 = 5242880; // 0x500000 float:7.34684E-39 double:2.590327E-317;
        r0 = r21;
        r0.mark(r3);	 Catch:{ all -> 0x0079 }
        r24 = 0;
        r3 = new com.bumptech.glide.load.resource.bitmap.ImageHeaderParser;	 Catch:{ IOException -> 0x009f }
        r0 = r21;
        r3.<init>(r0);	 Catch:{ IOException -> 0x009f }
        r24 = r3.getOrientation();	 Catch:{ IOException -> 0x009f }
        r21.reset();	 Catch:{ IOException -> 0x008b }
    L_0x003d:
        r0 = r17;
        r10.inTempStorage = r0;	 Catch:{ all -> 0x0079 }
        r0 = r27;
        r1 = r23;
        r22 = r0.getDimensions(r1, r9, r10);	 Catch:{ all -> 0x0079 }
        r3 = 0;
        r5 = r22[r3];	 Catch:{ all -> 0x0079 }
        r3 = 1;
        r6 = r22[r3];	 Catch:{ all -> 0x0079 }
        r4 = com.bumptech.glide.load.resource.bitmap.TransformationUtils.getExifOrientationDegrees(r24);	 Catch:{ all -> 0x0079 }
        r3 = r27;
        r7 = r30;
        r8 = r31;
        r14 = r3.getRoundedSampleSize(r4, r5, r6, r7, r8);	 Catch:{ all -> 0x0079 }
        r7 = r27;
        r8 = r23;
        r11 = r29;
        r12 = r5;
        r13 = r6;
        r15 = r32;
        r19 = r7.downsampleWithSize(r8, r9, r10, r11, r12, r13, r14, r15);	 Catch:{ all -> 0x0079 }
        r26 = r21.getException();	 Catch:{ all -> 0x0079 }
        if (r26 == 0) goto L_0x00e4;
    L_0x0071:
        r3 = new java.lang.RuntimeException;	 Catch:{ all -> 0x0079 }
        r0 = r26;
        r3.<init>(r0);	 Catch:{ all -> 0x0079 }
        throw r3;	 Catch:{ all -> 0x0079 }
    L_0x0079:
        r3 = move-exception;
        r16.releaseBytes(r17);
        r0 = r16;
        r1 = r18;
        r0.releaseBytes(r1);
        r21.release();
        releaseOptions(r10);
        throw r3;
    L_0x008b:
        r20 = move-exception;
        r3 = "Downsampler";
        r7 = 5;
        r3 = android.util.Log.isLoggable(r3, r7);	 Catch:{ all -> 0x0079 }
        if (r3 == 0) goto L_0x003d;
    L_0x0095:
        r3 = "Downsampler";
        r7 = "Cannot reset the input stream";
        r0 = r20;
        android.util.Log.w(r3, r7, r0);	 Catch:{ all -> 0x0079 }
        goto L_0x003d;
    L_0x009f:
        r20 = move-exception;
        r3 = "Downsampler";
        r7 = 5;
        r3 = android.util.Log.isLoggable(r3, r7);	 Catch:{ all -> 0x00cb }
        if (r3 == 0) goto L_0x00b2;
    L_0x00a9:
        r3 = "Downsampler";
        r7 = "Cannot determine the image orientation from header";
        r0 = r20;
        android.util.Log.w(r3, r7, r0);	 Catch:{ all -> 0x00cb }
    L_0x00b2:
        r21.reset();	 Catch:{ IOException -> 0x00b6 }
        goto L_0x003d;
    L_0x00b6:
        r20 = move-exception;
        r3 = "Downsampler";
        r7 = 5;
        r3 = android.util.Log.isLoggable(r3, r7);	 Catch:{ all -> 0x0079 }
        if (r3 == 0) goto L_0x003d;
    L_0x00c0:
        r3 = "Downsampler";
        r7 = "Cannot reset the input stream";
        r0 = r20;
        android.util.Log.w(r3, r7, r0);	 Catch:{ all -> 0x0079 }
        goto L_0x003d;
    L_0x00cb:
        r3 = move-exception;
        r21.reset();	 Catch:{ IOException -> 0x00d0 }
    L_0x00cf:
        throw r3;	 Catch:{ all -> 0x0079 }
    L_0x00d0:
        r20 = move-exception;
        r7 = "Downsampler";
        r8 = 5;
        r7 = android.util.Log.isLoggable(r7, r8);	 Catch:{ all -> 0x0079 }
        if (r7 == 0) goto L_0x00cf;
    L_0x00da:
        r7 = "Downsampler";
        r8 = "Cannot reset the input stream";
        r0 = r20;
        android.util.Log.w(r7, r8, r0);	 Catch:{ all -> 0x0079 }
        goto L_0x00cf;
    L_0x00e4:
        r25 = 0;
        if (r19 == 0) goto L_0x0109;
    L_0x00e8:
        r0 = r19;
        r1 = r29;
        r2 = r24;
        r25 = com.bumptech.glide.load.resource.bitmap.TransformationUtils.rotateImageExif(r0, r1, r2);	 Catch:{ all -> 0x0079 }
        r0 = r19;
        r1 = r25;
        r3 = r0.equals(r1);	 Catch:{ all -> 0x0079 }
        if (r3 != 0) goto L_0x0109;
    L_0x00fc:
        r0 = r29;
        r1 = r19;
        r3 = r0.put(r1);	 Catch:{ all -> 0x0079 }
        if (r3 != 0) goto L_0x0109;
    L_0x0106:
        r19.recycle();	 Catch:{ all -> 0x0079 }
    L_0x0109:
        r16.releaseBytes(r17);
        r0 = r16;
        r1 = r18;
        r0.releaseBytes(r1);
        r21.release();
        releaseOptions(r10);
        return r25;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.resource.bitmap.Downsampler.decode(java.io.InputStream, com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool, int, int, com.bumptech.glide.load.DecodeFormat):android.graphics.Bitmap");
    }

    private int getRoundedSampleSize(int degreesToRotate, int inWidth, int inHeight, int outWidth, int outHeight) {
        int targetHeight;
        int targetWidth;
        int exactSampleSize;
        if (outHeight == ExploreByTouchHelper.INVALID_ID) {
            targetHeight = inHeight;
        } else {
            targetHeight = outHeight;
        }
        if (outWidth == ExploreByTouchHelper.INVALID_ID) {
            targetWidth = inWidth;
        } else {
            targetWidth = outWidth;
        }
        if (degreesToRotate == 90 || degreesToRotate == 270) {
            exactSampleSize = getSampleSize(inHeight, inWidth, targetWidth, targetHeight);
        } else {
            exactSampleSize = getSampleSize(inWidth, inHeight, targetWidth, targetHeight);
        }
        return Math.max(1, exactSampleSize == 0 ? 0 : Integer.highestOneBit(exactSampleSize));
    }

    private Bitmap downsampleWithSize(MarkEnforcingInputStream is, RecyclableBufferedInputStream bufferedStream, Options options, BitmapPool pool, int inWidth, int inHeight, int sampleSize, DecodeFormat decodeFormat) {
        Config config = getConfig(is, decodeFormat);
        options.inSampleSize = sampleSize;
        options.inPreferredConfig = config;
        if ((options.inSampleSize == 1 || 19 <= VERSION.SDK_INT) && shouldUsePool(is)) {
            setInBitmap(options, pool.getDirty((int) Math.ceil(((double) inWidth) / ((double) sampleSize)), (int) Math.ceil(((double) inHeight) / ((double) sampleSize)), config));
        }
        return decodeStream(is, bufferedStream, options);
    }

    private static boolean shouldUsePool(InputStream is) {
        if (19 <= VERSION.SDK_INT) {
            return true;
        }
        is.mark(1024);
        try {
            boolean contains = TYPES_THAT_USE_POOL.contains(new ImageHeaderParser(is).getType());
            try {
                is.reset();
                return contains;
            } catch (IOException e) {
                if (!Log.isLoggable("Downsampler", 5)) {
                    return contains;
                }
                Log.w("Downsampler", "Cannot reset the input stream", e);
                return contains;
            }
        } catch (IOException e2) {
            if (Log.isLoggable("Downsampler", 5)) {
                Log.w("Downsampler", "Cannot determine the image type from header", e2);
            }
            try {
                is.reset();
            } catch (IOException e22) {
                if (Log.isLoggable("Downsampler", 5)) {
                    Log.w("Downsampler", "Cannot reset the input stream", e22);
                }
            }
            return false;
        } catch (Throwable th) {
            try {
                is.reset();
            } catch (IOException e222) {
                if (Log.isLoggable("Downsampler", 5)) {
                    Log.w("Downsampler", "Cannot reset the input stream", e222);
                }
            }
            throw th;
        }
    }

    private static Config getConfig(InputStream is, DecodeFormat format) {
        if (format == DecodeFormat.ALWAYS_ARGB_8888 || format == DecodeFormat.PREFER_ARGB_8888 || VERSION.SDK_INT == 16) {
            return Config.ARGB_8888;
        }
        boolean hasAlpha = false;
        is.mark(1024);
        try {
            hasAlpha = new ImageHeaderParser(is).hasAlpha();
            try {
                is.reset();
            } catch (IOException e) {
                if (Log.isLoggable("Downsampler", 5)) {
                    Log.w("Downsampler", "Cannot reset the input stream", e);
                }
            }
        } catch (IOException e2) {
            if (Log.isLoggable("Downsampler", 5)) {
                Log.w("Downsampler", "Cannot determine whether the image has alpha or not from header for format " + format, e2);
            }
            try {
                is.reset();
            } catch (IOException e22) {
                if (Log.isLoggable("Downsampler", 5)) {
                    Log.w("Downsampler", "Cannot reset the input stream", e22);
                }
            }
        } catch (Throwable th) {
            try {
                is.reset();
            } catch (IOException e222) {
                if (Log.isLoggable("Downsampler", 5)) {
                    Log.w("Downsampler", "Cannot reset the input stream", e222);
                }
            }
            throw th;
        }
        if (hasAlpha) {
            return Config.ARGB_8888;
        }
        return Config.RGB_565;
    }

    public int[] getDimensions(MarkEnforcingInputStream is, RecyclableBufferedInputStream bufferedStream, Options options) {
        options.inJustDecodeBounds = true;
        decodeStream(is, bufferedStream, options);
        options.inJustDecodeBounds = false;
        return new int[]{options.outWidth, options.outHeight};
    }

    private static Bitmap decodeStream(MarkEnforcingInputStream is, RecyclableBufferedInputStream bufferedStream, Options options) {
        if (options.inJustDecodeBounds) {
            is.mark(5242880);
        } else {
            bufferedStream.fixMarkLimit();
        }
        Bitmap result = BitmapFactoryInstrumentation.decodeStream(is, null, options);
        try {
            if (options.inJustDecodeBounds) {
                is.reset();
            }
        } catch (IOException e) {
            if (Log.isLoggable("Downsampler", 6)) {
                Log.e("Downsampler", "Exception loading inDecodeBounds=" + options.inJustDecodeBounds + " sample=" + options.inSampleSize, e);
            }
        }
        return result;
    }

    @TargetApi(11)
    private static void setInBitmap(Options options, Bitmap recycled) {
        if (11 <= VERSION.SDK_INT) {
            options.inBitmap = recycled;
        }
    }

    @TargetApi(11)
    private static synchronized Options getDefaultOptions() {
        Options decodeBitmapOptions;
        synchronized (Downsampler.class) {
            synchronized (OPTIONS_QUEUE) {
                decodeBitmapOptions = (Options) OPTIONS_QUEUE.poll();
            }
            if (decodeBitmapOptions == null) {
                decodeBitmapOptions = new Options();
                resetOptions(decodeBitmapOptions);
            }
        }
        return decodeBitmapOptions;
    }

    private static void releaseOptions(Options decodeBitmapOptions) {
        resetOptions(decodeBitmapOptions);
        synchronized (OPTIONS_QUEUE) {
            OPTIONS_QUEUE.offer(decodeBitmapOptions);
        }
    }

    @TargetApi(11)
    private static void resetOptions(Options decodeBitmapOptions) {
        decodeBitmapOptions.inTempStorage = null;
        decodeBitmapOptions.inDither = false;
        decodeBitmapOptions.inScaled = false;
        decodeBitmapOptions.inSampleSize = 1;
        decodeBitmapOptions.inPreferredConfig = null;
        decodeBitmapOptions.inJustDecodeBounds = false;
        decodeBitmapOptions.outWidth = 0;
        decodeBitmapOptions.outHeight = 0;
        decodeBitmapOptions.outMimeType = null;
        if (11 <= VERSION.SDK_INT) {
            decodeBitmapOptions.inBitmap = null;
            decodeBitmapOptions.inMutable = true;
        }
    }
}
