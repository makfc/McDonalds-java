package com.android.volley.toolbox;

import android.os.SystemClock;
import com.android.volley.Cache;
import com.android.volley.Cache.Entry;
import com.android.volley.VolleyLog;
import com.facebook.stetho.common.Utf8Charset;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class DiskBasedCache implements Cache {
    private final Map<String, CacheHeader> mEntries;
    private final int mMaxCacheSizeInBytes;
    private final File mRootDirectory;
    private long mTotalSize;

    static class CacheHeader {
        public String etag;
        public String key;
        public long lastModified;
        public Map<String, String> responseHeaders;
        public long serverDate;
        public long size;
        public long softTtl;
        public long ttl;

        private CacheHeader() {
        }

        public CacheHeader(String key, Entry entry) {
            this.key = key;
            this.size = (long) entry.data.length;
            this.etag = entry.etag;
            this.serverDate = entry.serverDate;
            this.lastModified = entry.lastModified;
            this.ttl = entry.ttl;
            this.softTtl = entry.softTtl;
            this.responseHeaders = entry.responseHeaders;
        }

        public static CacheHeader readHeader(InputStream is) throws IOException {
            CacheHeader entry = new CacheHeader();
            if (DiskBasedCache.readInt(is) != 538247942) {
                throw new IOException();
            }
            entry.key = DiskBasedCache.readString(is);
            entry.etag = DiskBasedCache.readString(is);
            if (entry.etag.equals("")) {
                entry.etag = null;
            }
            entry.serverDate = DiskBasedCache.readLong(is);
            entry.lastModified = DiskBasedCache.readLong(is);
            entry.ttl = DiskBasedCache.readLong(is);
            entry.softTtl = DiskBasedCache.readLong(is);
            entry.responseHeaders = DiskBasedCache.readStringStringMap(is);
            return entry;
        }

        public Entry toCacheEntry(byte[] data) {
            Entry e = new Entry();
            e.data = data;
            e.etag = this.etag;
            e.serverDate = this.serverDate;
            e.lastModified = this.lastModified;
            e.ttl = this.ttl;
            e.softTtl = this.softTtl;
            e.responseHeaders = this.responseHeaders;
            return e;
        }

        public boolean writeHeader(OutputStream os) {
            try {
                DiskBasedCache.writeInt(os, 538247942);
                DiskBasedCache.writeString(os, this.key);
                DiskBasedCache.writeString(os, this.etag == null ? "" : this.etag);
                DiskBasedCache.writeLong(os, this.serverDate);
                DiskBasedCache.writeLong(os, this.lastModified);
                DiskBasedCache.writeLong(os, this.ttl);
                DiskBasedCache.writeLong(os, this.softTtl);
                DiskBasedCache.writeStringStringMap(this.responseHeaders, os);
                os.flush();
                return true;
            } catch (IOException e) {
                VolleyLog.m5262d("%s", e.toString());
                return false;
            }
        }
    }

    private static class CountingInputStream extends FilterInputStream {
        private int bytesRead;

        private CountingInputStream(InputStream in) {
            super(in);
            this.bytesRead = 0;
        }

        public int read() throws IOException {
            int result = super.read();
            if (result != -1) {
                this.bytesRead++;
            }
            return result;
        }

        public int read(byte[] buffer, int offset, int count) throws IOException {
            int result = super.read(buffer, offset, count);
            if (result != -1) {
                this.bytesRead += result;
            }
            return result;
        }
    }

    public DiskBasedCache(File rootDirectory, int maxCacheSizeInBytes) {
        this.mEntries = new LinkedHashMap(16, 0.75f, true);
        this.mTotalSize = 0;
        this.mRootDirectory = rootDirectory;
        this.mMaxCacheSizeInBytes = maxCacheSizeInBytes;
    }

    public DiskBasedCache(File rootDirectory) {
        this(rootDirectory, 5242880);
    }

    public synchronized void clear() {
        File[] files = this.mRootDirectory.listFiles();
        if (files != null) {
            for (File file : files) {
                file.delete();
            }
        }
        this.mEntries.clear();
        this.mTotalSize = 0;
        VolleyLog.m5262d("Cache cleared.", new Object[0]);
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x0067 A:{SYNTHETIC, Splitter:B:28:0x0067} */
    public synchronized com.android.volley.Cache.Entry get(java.lang.String r13) {
        /*
        r12 = this;
        r7 = 0;
        monitor-enter(r12);
        r8 = r12.mEntries;	 Catch:{ all -> 0x006b }
        r4 = r8.get(r13);	 Catch:{ all -> 0x006b }
        r4 = (com.android.volley.toolbox.DiskBasedCache.CacheHeader) r4;	 Catch:{ all -> 0x006b }
        if (r4 != 0) goto L_0x000e;
    L_0x000c:
        monitor-exit(r12);
        return r7;
    L_0x000e:
        r5 = r12.getFileForKey(r13);	 Catch:{ all -> 0x006b }
        r0 = 0;
        r1 = new com.android.volley.toolbox.DiskBasedCache$CountingInputStream;	 Catch:{ IOException -> 0x0042 }
        r8 = new java.io.BufferedInputStream;	 Catch:{ IOException -> 0x0042 }
        r9 = new java.io.FileInputStream;	 Catch:{ IOException -> 0x0042 }
        r9.<init>(r5);	 Catch:{ IOException -> 0x0042 }
        r8.<init>(r9);	 Catch:{ IOException -> 0x0042 }
        r9 = 0;
        r1.<init>(r8);	 Catch:{ IOException -> 0x0042 }
        com.android.volley.toolbox.DiskBasedCache.CacheHeader.readHeader(r1);	 Catch:{ IOException -> 0x0073, all -> 0x0070 }
        r8 = r5.length();	 Catch:{ IOException -> 0x0073, all -> 0x0070 }
        r10 = r1.bytesRead;	 Catch:{ IOException -> 0x0073, all -> 0x0070 }
        r10 = (long) r10;	 Catch:{ IOException -> 0x0073, all -> 0x0070 }
        r8 = r8 - r10;
        r8 = (int) r8;	 Catch:{ IOException -> 0x0073, all -> 0x0070 }
        r2 = streamToBytes(r1, r8);	 Catch:{ IOException -> 0x0073, all -> 0x0070 }
        r8 = r4.toCacheEntry(r2);	 Catch:{ IOException -> 0x0073, all -> 0x0070 }
        if (r1 == 0) goto L_0x003e;
    L_0x003b:
        r1.close();	 Catch:{ IOException -> 0x0040 }
    L_0x003e:
        r7 = r8;
        goto L_0x000c;
    L_0x0040:
        r6 = move-exception;
        goto L_0x000c;
    L_0x0042:
        r3 = move-exception;
    L_0x0043:
        r8 = "%s: %s";
        r9 = 2;
        r9 = new java.lang.Object[r9];	 Catch:{ all -> 0x0064 }
        r10 = 0;
        r11 = r5.getAbsolutePath();	 Catch:{ all -> 0x0064 }
        r9[r10] = r11;	 Catch:{ all -> 0x0064 }
        r10 = 1;
        r11 = r3.toString();	 Catch:{ all -> 0x0064 }
        r9[r10] = r11;	 Catch:{ all -> 0x0064 }
        com.android.volley.VolleyLog.m5262d(r8, r9);	 Catch:{ all -> 0x0064 }
        r12.remove(r13);	 Catch:{ all -> 0x0064 }
        if (r0 == 0) goto L_0x000c;
    L_0x005e:
        r0.close();	 Catch:{ IOException -> 0x0062 }
        goto L_0x000c;
    L_0x0062:
        r6 = move-exception;
        goto L_0x000c;
    L_0x0064:
        r8 = move-exception;
    L_0x0065:
        if (r0 == 0) goto L_0x006a;
    L_0x0067:
        r0.close();	 Catch:{ IOException -> 0x006e }
    L_0x006a:
        throw r8;	 Catch:{ all -> 0x006b }
    L_0x006b:
        r7 = move-exception;
        monitor-exit(r12);
        throw r7;
    L_0x006e:
        r6 = move-exception;
        goto L_0x000c;
    L_0x0070:
        r8 = move-exception;
        r0 = r1;
        goto L_0x0065;
    L_0x0073:
        r3 = move-exception;
        r0 = r1;
        goto L_0x0043;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.volley.toolbox.DiskBasedCache.get(java.lang.String):com.android.volley.Cache$Entry");
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x005c A:{SYNTHETIC, Splitter:B:29:0x005c} */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x0053 A:{SYNTHETIC} */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0061 A:{SYNTHETIC, Splitter:B:32:0x0061} */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x006a A:{SYNTHETIC, Splitter:B:37:0x006a} */
    public synchronized void initialize() {
        /*
        r13 = this;
        monitor-enter(r13);
        r9 = r13.mRootDirectory;	 Catch:{ all -> 0x006e }
        r9 = r9.exists();	 Catch:{ all -> 0x006e }
        if (r9 != 0) goto L_0x0024;
    L_0x0009:
        r9 = r13.mRootDirectory;	 Catch:{ all -> 0x006e }
        r9 = r9.mkdirs();	 Catch:{ all -> 0x006e }
        if (r9 != 0) goto L_0x0022;
    L_0x0011:
        r9 = "Unable to create cache dir %s";
        r10 = 1;
        r10 = new java.lang.Object[r10];	 Catch:{ all -> 0x006e }
        r11 = 0;
        r12 = r13.mRootDirectory;	 Catch:{ all -> 0x006e }
        r12 = r12.getAbsolutePath();	 Catch:{ all -> 0x006e }
        r10[r11] = r12;	 Catch:{ all -> 0x006e }
        com.android.volley.VolleyLog.m5263e(r9, r10);	 Catch:{ all -> 0x006e }
    L_0x0022:
        monitor-exit(r13);
        return;
    L_0x0024:
        r9 = r13.mRootDirectory;	 Catch:{ all -> 0x006e }
        r4 = r9.listFiles();	 Catch:{ all -> 0x006e }
        if (r4 == 0) goto L_0x0022;
    L_0x002c:
        r0 = r4;
        r8 = r0.length;	 Catch:{ all -> 0x006e }
        r7 = 0;
    L_0x002f:
        if (r7 >= r8) goto L_0x0022;
    L_0x0031:
        r3 = r0[r7];	 Catch:{ all -> 0x006e }
        r5 = 0;
        r6 = new java.io.BufferedInputStream;	 Catch:{ IOException -> 0x0059 }
        r9 = new java.io.FileInputStream;	 Catch:{ IOException -> 0x0059 }
        r9.<init>(r3);	 Catch:{ IOException -> 0x0059 }
        r6.<init>(r9);	 Catch:{ IOException -> 0x0059 }
        r2 = com.android.volley.toolbox.DiskBasedCache.CacheHeader.readHeader(r6);	 Catch:{ IOException -> 0x0076, all -> 0x0073 }
        r10 = r3.length();	 Catch:{ IOException -> 0x0076, all -> 0x0073 }
        r2.size = r10;	 Catch:{ IOException -> 0x0076, all -> 0x0073 }
        r9 = r2.key;	 Catch:{ IOException -> 0x0076, all -> 0x0073 }
        r13.putEntry(r9, r2);	 Catch:{ IOException -> 0x0076, all -> 0x0073 }
        if (r6 == 0) goto L_0x0052;
    L_0x004f:
        r6.close();	 Catch:{ IOException -> 0x0056 }
    L_0x0052:
        r5 = r6;
    L_0x0053:
        r7 = r7 + 1;
        goto L_0x002f;
    L_0x0056:
        r9 = move-exception;
        r5 = r6;
        goto L_0x0053;
    L_0x0059:
        r1 = move-exception;
    L_0x005a:
        if (r3 == 0) goto L_0x005f;
    L_0x005c:
        r3.delete();	 Catch:{ all -> 0x0067 }
    L_0x005f:
        if (r5 == 0) goto L_0x0053;
    L_0x0061:
        r5.close();	 Catch:{ IOException -> 0x0065 }
        goto L_0x0053;
    L_0x0065:
        r9 = move-exception;
        goto L_0x0053;
    L_0x0067:
        r9 = move-exception;
    L_0x0068:
        if (r5 == 0) goto L_0x006d;
    L_0x006a:
        r5.close();	 Catch:{ IOException -> 0x0071 }
    L_0x006d:
        throw r9;	 Catch:{ all -> 0x006e }
    L_0x006e:
        r9 = move-exception;
        monitor-exit(r13);
        throw r9;
    L_0x0071:
        r10 = move-exception;
        goto L_0x006d;
    L_0x0073:
        r9 = move-exception;
        r5 = r6;
        goto L_0x0068;
    L_0x0076:
        r1 = move-exception;
        r5 = r6;
        goto L_0x005a;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.volley.toolbox.DiskBasedCache.initialize():void");
    }

    public synchronized void put(String key, Entry entry) {
        pruneIfNeeded(entry.data.length);
        File file = getFileForKey(key);
        try {
            BufferedOutputStream fos = new BufferedOutputStream(new FileOutputStream(file));
            CacheHeader e = new CacheHeader(key, entry);
            if (e.writeHeader(fos)) {
                fos.write(entry.data);
                fos.close();
                putEntry(key, e);
            } else {
                fos.close();
                VolleyLog.m5262d("Failed to write header for %s", file.getAbsolutePath());
                throw new IOException();
            }
        } catch (IOException e2) {
            if (!file.delete()) {
                VolleyLog.m5262d("Could not clean up file %s", file.getAbsolutePath());
            }
        }
    }

    public synchronized void remove(String key) {
        boolean deleted = getFileForKey(key).delete();
        removeEntry(key);
        if (!deleted) {
            VolleyLog.m5262d("Could not delete cache entry for key=%s, filename=%s", key, getFilenameForKey(key));
        }
    }

    private String getFilenameForKey(String key) {
        int firstHalfLength = key.length() / 2;
        return String.valueOf(key.substring(0, firstHalfLength).hashCode()) + String.valueOf(key.substring(firstHalfLength).hashCode());
    }

    public File getFileForKey(String key) {
        return new File(this.mRootDirectory, getFilenameForKey(key));
    }

    private void pruneIfNeeded(int neededSpace) {
        if (this.mTotalSize + ((long) neededSpace) >= ((long) this.mMaxCacheSizeInBytes)) {
            if (VolleyLog.DEBUG) {
                VolleyLog.m5265v("Pruning old cache entries.", new Object[0]);
            }
            long before = this.mTotalSize;
            int prunedFiles = 0;
            long startTime = SystemClock.elapsedRealtime();
            Iterator<Map.Entry<String, CacheHeader>> iterator = this.mEntries.entrySet().iterator();
            while (iterator.hasNext()) {
                CacheHeader e = (CacheHeader) ((Map.Entry) iterator.next()).getValue();
                if (getFileForKey(e.key).delete()) {
                    this.mTotalSize -= e.size;
                } else {
                    VolleyLog.m5262d("Could not delete cache entry for key=%s, filename=%s", e.key, getFilenameForKey(e.key));
                }
                iterator.remove();
                prunedFiles++;
                if (((float) (this.mTotalSize + ((long) neededSpace))) < ((float) this.mMaxCacheSizeInBytes) * 0.9f) {
                    break;
                }
            }
            if (VolleyLog.DEBUG) {
                VolleyLog.m5265v("pruned %d files, %d bytes, %d ms", Integer.valueOf(prunedFiles), Long.valueOf(this.mTotalSize - before), Long.valueOf(SystemClock.elapsedRealtime() - startTime));
            }
        }
    }

    private void putEntry(String key, CacheHeader entry) {
        if (this.mEntries.containsKey(key)) {
            this.mTotalSize += entry.size - ((CacheHeader) this.mEntries.get(key)).size;
        } else {
            this.mTotalSize += entry.size;
        }
        this.mEntries.put(key, entry);
    }

    private void removeEntry(String key) {
        CacheHeader entry = (CacheHeader) this.mEntries.get(key);
        if (entry != null) {
            this.mTotalSize -= entry.size;
            this.mEntries.remove(key);
        }
    }

    private static byte[] streamToBytes(InputStream in, int length) throws IOException {
        byte[] bytes = new byte[length];
        int pos = 0;
        while (pos < length) {
            int count = in.read(bytes, pos, length - pos);
            if (count == -1) {
                break;
            }
            pos += count;
        }
        if (pos == length) {
            return bytes;
        }
        throw new IOException("Expected " + length + " bytes, read " + pos + " bytes");
    }

    private static int read(InputStream is) throws IOException {
        int b = is.read();
        if (b != -1) {
            return b;
        }
        throw new EOFException();
    }

    static void writeInt(OutputStream os, int n) throws IOException {
        os.write((n >> 0) & 255);
        os.write((n >> 8) & 255);
        os.write((n >> 16) & 255);
        os.write((n >> 24) & 255);
    }

    static int readInt(InputStream is) throws IOException {
        return (((0 | (read(is) << 0)) | (read(is) << 8)) | (read(is) << 16)) | (read(is) << 24);
    }

    static void writeLong(OutputStream os, long n) throws IOException {
        os.write((byte) ((int) (n >>> null)));
        os.write((byte) ((int) (n >>> 8)));
        os.write((byte) ((int) (n >>> 16)));
        os.write((byte) ((int) (n >>> 24)));
        os.write((byte) ((int) (n >>> 32)));
        os.write((byte) ((int) (n >>> 40)));
        os.write((byte) ((int) (n >>> 48)));
        os.write((byte) ((int) (n >>> 56)));
    }

    static long readLong(InputStream is) throws IOException {
        return (((((((0 | ((((long) read(is)) & 255) << null)) | ((((long) read(is)) & 255) << 8)) | ((((long) read(is)) & 255) << 16)) | ((((long) read(is)) & 255) << 24)) | ((((long) read(is)) & 255) << 32)) | ((((long) read(is)) & 255) << 40)) | ((((long) read(is)) & 255) << 48)) | ((((long) read(is)) & 255) << 56);
    }

    static void writeString(OutputStream os, String s) throws IOException {
        byte[] b = s.getBytes(Utf8Charset.NAME);
        writeLong(os, (long) b.length);
        os.write(b, 0, b.length);
    }

    static String readString(InputStream is) throws IOException {
        return new String(streamToBytes(is, (int) readLong(is)), Utf8Charset.NAME);
    }

    static void writeStringStringMap(Map<String, String> map, OutputStream os) throws IOException {
        if (map != null) {
            writeInt(os, map.size());
            for (Map.Entry<String, String> entry : map.entrySet()) {
                writeString(os, (String) entry.getKey());
                writeString(os, (String) entry.getValue());
            }
            return;
        }
        writeInt(os, 0);
    }

    static Map<String, String> readStringStringMap(InputStream is) throws IOException {
        int size = readInt(is);
        Map<String, String> result = size == 0 ? Collections.emptyMap() : new HashMap(size);
        for (int i = 0; i < size; i++) {
            result.put(readString(is).intern(), readString(is).intern());
        }
        return result;
    }
}
