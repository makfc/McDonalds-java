package com.mcdonalds.sdk.services.data;

import android.content.Context;
import android.support.p000v4.util.LruCache;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.jakewharton.disklrucache.DiskLruCache;
import com.jakewharton.disklrucache.DiskLruCache.Editor;
import com.jakewharton.disklrucache.DiskLruCache.Snapshot;
import com.mcdonalds.sdk.connectors.middleware.MiddlewareConnectorUtils;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.mcdonalds.sdk.services.log.SafeLog;
import com.mcdonalds.sdk.services.network.CacheUtils;
import com.newrelic.agent.android.instrumentation.GsonInstrumentation;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.Set;

public class DiskCacheManager {
    private static final int DEFAULT_CACHE_SIZE = 262144000;
    private static final int DEFAULT_CACHE_SIZE_ECP3 = 10485760;
    private static final int DEFAULT_MEM_CACHE_SIZE = 20;
    private static final String HASH_ALGORITHM = "MD5";
    private static final String KEY_DISK_CACHE_SIZE = "diskCacheMaxSize";
    private static final String KEY_SET_CACHE_KEY = "key_set_cache_key";
    private static final String LOG_TAG = DiskCacheManager.class.getSimpleName();
    private static final int MB_UNIT = 1048576;
    private static final String STRING_ENCODING = "UTF-8";
    private static final int VALUE_COUNT = 1;
    private static DiskCacheManager sInstance;
    private final File mCacheDirectory;
    private DiskLruCache mDiskCache;
    private final Gson mGson = new Gson();
    private Set<String> mKeySet;
    private final LruCache mMemCache = new LruCache(20);

    /* renamed from: com.mcdonalds.sdk.services.data.DiskCacheManager$1 */
    class C41161 extends TypeToken<Set<String>> {
        C41161() {
        }
    }

    public static DiskCacheManager getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new DiskCacheManager(context);
        }
        return sInstance;
    }

    private DiskCacheManager(Context context) {
        this.mCacheDirectory = CacheUtils.getDiskCacheDir(context, "/objectcache");
        open();
        initializeKeySet();
    }

    private void open() {
        long cacheSize = 262144000;
        if (Configuration.getSharedInstance().hasKey(KEY_DISK_CACHE_SIZE)) {
            cacheSize = Configuration.getSharedInstance().getLongForKey(KEY_DISK_CACHE_SIZE) * 1048576;
        } else if (MiddlewareConnectorUtils.isUsingECP3()) {
            cacheSize = 10485760;
        }
        try {
            this.mDiskCache = DiskLruCache.open(this.mCacheDirectory, -1, 1, cacheSize);
        } catch (IOException e) {
            SafeLog.m7746e(LOG_TAG, "error creating object cache", e);
        }
    }

    public void clear() throws IOException {
        this.mDiskCache.delete();
        this.mMemCache.evictAll();
        this.mKeySet.clear();
        open();
    }

    public synchronized boolean delete(String key) throws IOException {
        this.mMemCache.remove(key);
        this.mKeySet.remove(key);
        if (!key.equals(KEY_SET_CACHE_KEY)) {
            updateKeySetInCache();
        }
        return this.mDiskCache.remove(getHashOf(key));
    }

    public boolean hasObjectForKey(String key) throws IOException {
        return this.mKeySet.contains(key);
    }

    public <T> T get(String key, Type type) throws IOException {
        T ob = this.mMemCache.get(key);
        if (ob != null) {
            return ob;
        }
        Snapshot snapshot = this.mDiskCache.get(getHashOf(key));
        if (snapshot != null) {
            String value = snapshot.getString(0);
            Gson gson = this.mGson;
            ob = !(gson instanceof Gson) ? gson.fromJson(value, type) : GsonInstrumentation.fromJson(gson, value, type);
        }
        return ob;
    }

    public <T> T get(String key, Type type, boolean serialize) throws IOException {
        if (!serialize) {
            return get(key, type);
        }
        T ob = this.mMemCache.get(key);
        if (ob != null) {
            return ob;
        }
        Snapshot snapshot = this.mDiskCache.get(getHashOf(key));
        if (snapshot == null) {
            return ob;
        }
        JsonReader jsonReader = new JsonReader(new BufferedReader(new InputStreamReader(snapshot.getInputStream(0))));
        jsonReader.setLenient(true);
        Gson gson = this.mGson;
        return !(gson instanceof Gson) ? gson.fromJson(jsonReader, type) : GsonInstrumentation.fromJson(gson, jsonReader, type);
    }

    public synchronized void put(String key, Object object) throws IOException {
        this.mKeySet.add(key);
        this.mMemCache.put(key, object);
        Editor editor = null;
        try {
            editor = this.mDiskCache.edit(getHashOf(key));
            if (editor != null) {
                Gson gson = this.mGson;
                if (writeValueToCache(!(gson instanceof Gson) ? gson.toJson(object) : GsonInstrumentation.toJson(gson, object), editor)) {
                    this.mDiskCache.flush();
                    editor.commit();
                } else {
                    editor.abort();
                }
                if (!key.equals(KEY_SET_CACHE_KEY)) {
                    updateKeySetInCache();
                }
            }
        } catch (IOException e) {
            if (editor != null) {
                editor.abort();
            }
            throw e;
        }
    }

    public synchronized void put(String key, Object object, boolean serialize) throws IOException {
        if (serialize) {
            this.mKeySet.add(key);
            this.mMemCache.put(key, object);
            Editor editor = null;
            try {
                editor = this.mDiskCache.edit(getHashOf(key));
                if (editor != null) {
                    Appendable bufferedWriter = new BufferedWriter(new OutputStreamWriter(editor.newOutputStream(0)));
                    Gson gson = this.mGson;
                    if (gson instanceof Gson) {
                        GsonInstrumentation.toJson(gson, object, bufferedWriter);
                    } else {
                        gson.toJson(object, bufferedWriter);
                    }
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    editor.commit();
                    this.mDiskCache.flush();
                    if (!key.equals(KEY_SET_CACHE_KEY)) {
                        updateKeySetInCache();
                    }
                }
            } catch (IOException e) {
                if (editor != null) {
                    editor.abort();
                }
                throw e;
            }
        }
        put(key, object);
    }

    /* Access modifiers changed, original: protected */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x001e  */
    public boolean writeValueToCache(java.lang.String r4, com.jakewharton.disklrucache.DiskLruCache.Editor r5) throws java.io.IOException {
        /*
        r3 = this;
        r0 = 0;
        r1 = new java.io.BufferedOutputStream;	 Catch:{ all -> 0x001b }
        r2 = 0;
        r2 = r5.newOutputStream(r2);	 Catch:{ all -> 0x001b }
        r1.<init>(r2);	 Catch:{ all -> 0x001b }
        r2 = "UTF-8";
        r2 = r4.getBytes(r2);	 Catch:{ all -> 0x0022 }
        r1.write(r2);	 Catch:{ all -> 0x0022 }
        if (r1 == 0) goto L_0x0019;
    L_0x0016:
        r1.close();
    L_0x0019:
        r2 = 1;
        return r2;
    L_0x001b:
        r2 = move-exception;
    L_0x001c:
        if (r0 == 0) goto L_0x0021;
    L_0x001e:
        r0.close();
    L_0x0021:
        throw r2;
    L_0x0022:
        r2 = move-exception;
        r0 = r1;
        goto L_0x001c;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mcdonalds.sdk.services.data.DiskCacheManager.writeValueToCache(java.lang.String, com.jakewharton.disklrucache.DiskLruCache$Editor):boolean");
    }

    /* Access modifiers changed, original: protected */
    public String getHashOf(String string) throws UnsupportedEncodingException {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(HASH_ALGORITHM);
            messageDigest.update(string.getBytes("UTF-8"));
            return new BigInteger(1, messageDigest.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            return string;
        }
    }

    private void initializeKeySet() {
        try {
            this.mKeySet = (Set) get(KEY_SET_CACHE_KEY, new C41161().getType());
            if (this.mKeySet == null) {
                this.mKeySet = new HashSet();
            }
        } catch (IOException e) {
            this.mKeySet = new HashSet();
        }
    }

    private void updateKeySetInCache() throws IOException {
        put(KEY_SET_CACHE_KEY, this.mKeySet);
    }
}
