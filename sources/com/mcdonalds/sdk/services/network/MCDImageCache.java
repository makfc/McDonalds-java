package com.mcdonalds.sdk.services.network;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.os.AsyncTask;
import android.support.p000v4.media.session.PlaybackStateCompat;
import android.support.p000v4.util.LruCache;
import android.text.TextUtils;
import com.android.volley.toolbox.ImageLoader.ImageCache;
import com.jakewharton.disklrucache.DiskLruCache;
import com.jakewharton.disklrucache.DiskLruCache.Editor;
import com.jakewharton.disklrucache.DiskLruCache.Snapshot;
import com.mcdonalds.sdk.services.log.MCDLog;
import com.newrelic.agent.android.api.p047v2.TraceFieldInterface;
import com.newrelic.agent.android.instrumentation.AsyncTaskInstrumentation;
import com.newrelic.agent.android.instrumentation.BitmapFactoryInstrumentation;
import com.newrelic.agent.android.tracing.Trace;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.concurrent.Executor;
import java.util.zip.CRC32;

public class MCDImageCache implements ImageCache {
    public static final int APP_VERSION = 1;
    public static final String CACHE_PATH = ".images";
    public static final long CACHE_SIZE = 52428800;
    public static final int IO_BUFFER_SIZE = 8192;
    public static final String LOG_TAG = MCDImageCache.class.getSimpleName();
    private static final HashMap<String, Boolean> RUNNING_TASKS = new HashMap();
    public static final int VALUE_COUNT = 1;
    private CRC32 hashBuilder = new CRC32();
    private DiskLruCache mDiskLruCache;
    private LruCache<String, Bitmap> mLruCache;

    private class WriteOnDiskTask extends AsyncTask<String, String, Boolean> implements TraceFieldInterface {
        public Trace _nr_trace;
        private Bitmap mBitmap;
        private String mKey;

        public void _nr_setTrace(Trace trace) {
            try {
                this._nr_trace = trace;
            } catch (Exception e) {
            }
        }

        public WriteOnDiskTask(Bitmap bitmap) {
            this.mBitmap = bitmap;
        }

        /* Access modifiers changed, original: protected|varargs */
        public Boolean doInBackground(String... params) {
            this.mKey = params[0];
            MCDImageCache.RUNNING_TASKS.put(this.mKey, Boolean.valueOf(true));
            return Boolean.valueOf(MCDImageCache.this.putBitmapInDisk(this.mKey, this.mBitmap));
        }

        /* Access modifiers changed, original: protected */
        public void onPostExecute(Boolean result) {
            MCDImageCache.RUNNING_TASKS.put(this.mKey, Boolean.valueOf(false));
            this.mBitmap = null;
            this.mKey = null;
        }
    }

    public MCDImageCache(Context context) {
        File cacheDirectory = CacheUtils.getDiskCacheDir(context, CACHE_PATH);
        int cacheSize = ((int) (Runtime.getRuntime().maxMemory() / PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID)) / 8;
        this.mLruCache = new LruCache(cacheSize);
        try {
            this.mDiskLruCache = DiskLruCache.open(cacheDirectory, 1, 1, CACHE_SIZE);
        } catch (IOException e) {
            MCDLog.error(LOG_TAG, "Could not create disk cache");
        }
    }

    public Bitmap getBitmap(String url) {
        Bitmap bmp = (Bitmap) this.mLruCache.get(url);
        if (this.mDiskLruCache == null || bmp != null || TextUtils.isEmpty(url)) {
            return bmp;
        }
        return getBitmapFromDisk(getHash(url));
    }

    public void putBitmap(String url, Bitmap bitmap) {
        this.mLruCache.put(url, bitmap);
        if (this.mDiskLruCache != null) {
            String hash = getHash(url);
            if (!TextUtils.isEmpty(hash)) {
                Boolean running = (Boolean) RUNNING_TASKS.get(hash);
                if (running == null || !running.booleanValue()) {
                    WriteOnDiskTask task = new WriteOnDiskTask(bitmap);
                    Executor executor = AsyncTask.THREAD_POOL_EXECUTOR;
                    String[] strArr = new String[]{hash};
                    if (task instanceof AsyncTask) {
                        AsyncTaskInstrumentation.executeOnExecutor(task, executor, strArr);
                    } else {
                        task.executeOnExecutor(executor, strArr);
                    }
                }
            }
        }
    }

    private Bitmap getBitmapFromDisk(String url) {
        Bitmap bitmap = null;
        try {
            Snapshot snapshot = this.mDiskLruCache.get(url);
            if (snapshot != null) {
                InputStream in = snapshot.getInputStream(0);
                if (in != null) {
                    bitmap = BitmapFactoryInstrumentation.decodeStream(new BufferedInputStream(in, 8192));
                }
            }
            return bitmap;
        } catch (IOException e) {
            return null;
        }
    }

    private boolean putBitmapInDisk(String url, Bitmap bitmap) {
        try {
            Editor editor = this.mDiskLruCache.edit(url);
            if (editor == null) {
                return false;
            }
            if (writeBitmapToFile(bitmap, editor)) {
                this.mDiskLruCache.flush();
                editor.commit();
                return true;
            }
            editor.abort();
            return false;
        } catch (IOException e) {
            return false;
        }
    }

    private boolean writeBitmapToFile(Bitmap bitmap, Editor editor) throws IOException {
        Throwable th;
        OutputStream out = null;
        try {
            OutputStream out2 = new BufferedOutputStream(editor.newOutputStream(0), 8192);
            try {
                boolean compress = bitmap.compress(CompressFormat.PNG, 80, out2);
                if (out2 != null) {
                    out2.close();
                }
                return compress;
            } catch (Throwable th2) {
                th = th2;
                out = out2;
            }
        } catch (Throwable th3) {
            th = th3;
            if (out != null) {
                out.close();
            }
            throw th;
        }
    }

    private String getHash(String url) {
        if (TextUtils.isEmpty(url)) {
            return null;
        }
        this.hashBuilder.update(url.getBytes());
        return Long.toHexString(this.hashBuilder.getValue());
    }
}
