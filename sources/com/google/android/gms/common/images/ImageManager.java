package com.google.android.gms.common.images;

import android.annotation.TargetApi;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.ParcelFileDescriptor;
import android.os.ResultReceiver;
import android.os.SystemClock;
import android.support.p000v4.util.LruCache;
import android.util.Log;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.internal.zzpj;
import com.newrelic.agent.android.instrumentation.BitmapFactoryInstrumentation;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;

public final class ImageManager {
    private static final Object zzapv = new Object();
    private static HashSet<Uri> zzapw = new HashSet();
    private final Context mContext;
    private final Handler mHandler;
    private final zzb zzapA;
    private final zzpj zzapB;
    private final Map<zza, ImageReceiver> zzapC;
    private final Map<Uri, ImageReceiver> zzapD;
    private final Map<Uri, Long> zzapE;
    private final ExecutorService zzapz;

    @KeepName
    private final class ImageReceiver extends ResultReceiver {
        private final Uri mUri;
        private final ArrayList<zza> zzapF = new ArrayList();

        ImageReceiver(Uri uri) {
            super(new Handler(Looper.getMainLooper()));
            this.mUri = uri;
        }

        public void onReceiveResult(int i, Bundle bundle) {
            ImageManager.this.zzapz.execute(new zzc(this.mUri, (ParcelFileDescriptor) bundle.getParcelable("com.google.android.gms.extra.fileDescriptor")));
        }

        public void zzb(zza zza) {
            com.google.android.gms.common.internal.zzb.zzdc("ImageReceiver.addImageRequest() must be called in the main thread");
            this.zzapF.add(zza);
        }

        public void zzc(zza zza) {
            com.google.android.gms.common.internal.zzb.zzdc("ImageReceiver.removeImageRequest() must be called in the main thread");
            this.zzapF.remove(zza);
        }

        public void zztb() {
            Intent intent = new Intent("com.google.android.gms.common.images.LOAD_IMAGE");
            intent.putExtra("com.google.android.gms.extras.uri", this.mUri);
            intent.putExtra("com.google.android.gms.extras.resultReceiver", this);
            intent.putExtra("com.google.android.gms.extras.priority", 3);
            ImageManager.this.mContext.sendBroadcast(intent);
        }
    }

    public interface OnImageLoadedListener {
        void onImageLoaded(Uri uri, Drawable drawable, boolean z);
    }

    @TargetApi(11)
    private static final class zza {
    }

    private static final class zzb extends LruCache<zza, Bitmap> {
        /* Access modifiers changed, original: protected */
        /* renamed from: zza */
        public int sizeOf(zza zza, Bitmap bitmap) {
            return bitmap.getHeight() * bitmap.getRowBytes();
        }

        /* Access modifiers changed, original: protected */
        /* renamed from: zza */
        public void entryRemoved(boolean z, zza zza, Bitmap bitmap, Bitmap bitmap2) {
            super.entryRemoved(z, zza, bitmap, bitmap2);
        }
    }

    private final class zzc implements Runnable {
        private final Uri mUri;
        private final ParcelFileDescriptor zzapH;

        public zzc(Uri uri, ParcelFileDescriptor parcelFileDescriptor) {
            this.mUri = uri;
            this.zzapH = parcelFileDescriptor;
        }

        public void run() {
            com.google.android.gms.common.internal.zzb.zzdd("LoadBitmapFromDiskRunnable can't be executed in the main thread");
            boolean z = false;
            Bitmap bitmap = null;
            if (this.zzapH != null) {
                try {
                    bitmap = BitmapFactoryInstrumentation.decodeFileDescriptor(this.zzapH.getFileDescriptor());
                } catch (OutOfMemoryError e) {
                    String valueOf = String.valueOf(this.mUri);
                    Log.e("ImageManager", new StringBuilder(String.valueOf(valueOf).length() + 34).append("OOM while loading bitmap for uri: ").append(valueOf).toString(), e);
                    z = true;
                }
                try {
                    this.zzapH.close();
                } catch (IOException e2) {
                    Log.e("ImageManager", "closed failed", e2);
                }
            }
            CountDownLatch countDownLatch = new CountDownLatch(1);
            ImageManager.this.mHandler.post(new zzf(this.mUri, bitmap, z, countDownLatch));
            try {
                countDownLatch.await();
            } catch (InterruptedException e3) {
                String valueOf2 = String.valueOf(this.mUri);
                Log.w("ImageManager", new StringBuilder(String.valueOf(valueOf2).length() + 32).append("Latch interrupted while posting ").append(valueOf2).toString());
            }
        }
    }

    private final class zzd implements Runnable {
        final /* synthetic */ ImageManager zzapG;
        private final zza zzapI;

        public void run() {
            com.google.android.gms.common.internal.zzb.zzdc("LoadImageRunnable must be executed on the main thread");
            ImageReceiver imageReceiver = (ImageReceiver) this.zzapG.zzapC.get(this.zzapI);
            if (imageReceiver != null) {
                this.zzapG.zzapC.remove(this.zzapI);
                imageReceiver.zzc(this.zzapI);
            }
            zza zza = this.zzapI.zzapK;
            if (zza.uri == null) {
                this.zzapI.zza(this.zzapG.mContext, this.zzapG.zzapB, true);
                return;
            }
            Bitmap zza2 = this.zzapG.zza(zza);
            if (zza2 != null) {
                this.zzapI.zza(this.zzapG.mContext, zza2, true);
                return;
            }
            Long l = (Long) this.zzapG.zzapE.get(zza.uri);
            if (l != null) {
                if (SystemClock.elapsedRealtime() - l.longValue() < 3600000) {
                    this.zzapI.zza(this.zzapG.mContext, this.zzapG.zzapB, true);
                    return;
                }
                this.zzapG.zzapE.remove(zza.uri);
            }
            this.zzapI.zza(this.zzapG.mContext, this.zzapG.zzapB);
            imageReceiver = (ImageReceiver) this.zzapG.zzapD.get(zza.uri);
            if (imageReceiver == null) {
                imageReceiver = new ImageReceiver(zza.uri);
                this.zzapG.zzapD.put(zza.uri, imageReceiver);
            }
            imageReceiver.zzb(this.zzapI);
            if (!(this.zzapI instanceof com.google.android.gms.common.images.zza.zzc)) {
                this.zzapG.zzapC.put(this.zzapI, imageReceiver);
            }
            synchronized (ImageManager.zzapv) {
                if (!ImageManager.zzapw.contains(zza.uri)) {
                    ImageManager.zzapw.add(zza.uri);
                    imageReceiver.zztb();
                }
            }
        }
    }

    @TargetApi(14)
    private static final class zze implements ComponentCallbacks2 {
        private final zzb zzapA;

        public void onConfigurationChanged(Configuration configuration) {
        }

        public void onLowMemory() {
            this.zzapA.evictAll();
        }

        public void onTrimMemory(int i) {
            if (i >= 60) {
                this.zzapA.evictAll();
            } else if (i >= 20) {
                this.zzapA.trimToSize(this.zzapA.size() / 2);
            }
        }
    }

    private final class zzf implements Runnable {
        private final Bitmap mBitmap;
        private final Uri mUri;
        private boolean zzapJ;
        private final CountDownLatch zzqF;

        public zzf(Uri uri, Bitmap bitmap, boolean z, CountDownLatch countDownLatch) {
            this.mUri = uri;
            this.mBitmap = bitmap;
            this.zzapJ = z;
            this.zzqF = countDownLatch;
        }

        private void zza(ImageReceiver imageReceiver, boolean z) {
            ArrayList zza = imageReceiver.zzapF;
            int size = zza.size();
            for (int i = 0; i < size; i++) {
                zza zza2 = (zza) zza.get(i);
                if (z) {
                    zza2.zza(ImageManager.this.mContext, this.mBitmap, false);
                } else {
                    ImageManager.this.zzapE.put(this.mUri, Long.valueOf(SystemClock.elapsedRealtime()));
                    zza2.zza(ImageManager.this.mContext, ImageManager.this.zzapB, false);
                }
                if (!(zza2 instanceof com.google.android.gms.common.images.zza.zzc)) {
                    ImageManager.this.zzapC.remove(zza2);
                }
            }
        }

        public void run() {
            com.google.android.gms.common.internal.zzb.zzdc("OnBitmapLoadedRunnable must be executed in the main thread");
            boolean z = this.mBitmap != null;
            if (ImageManager.this.zzapA != null) {
                if (this.zzapJ) {
                    ImageManager.this.zzapA.evictAll();
                    System.gc();
                    this.zzapJ = false;
                    ImageManager.this.mHandler.post(this);
                    return;
                } else if (z) {
                    ImageManager.this.zzapA.put(new zza(this.mUri), this.mBitmap);
                }
            }
            ImageReceiver imageReceiver = (ImageReceiver) ImageManager.this.zzapD.remove(this.mUri);
            if (imageReceiver != null) {
                zza(imageReceiver, z);
            }
            this.zzqF.countDown();
            synchronized (ImageManager.zzapv) {
                ImageManager.zzapw.remove(this.mUri);
            }
        }
    }

    private Bitmap zza(zza zza) {
        return this.zzapA == null ? null : (Bitmap) this.zzapA.get(zza);
    }
}
