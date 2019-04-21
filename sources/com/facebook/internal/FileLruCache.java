package com.facebook.internal;

import android.content.Context;
import com.facebook.LoggingBehavior;
import com.facebook.Settings;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidParameterException;
import java.util.Date;
import java.util.PriorityQueue;
import java.util.concurrent.atomic.AtomicLong;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public final class FileLruCache {
    private static final String HEADER_CACHEKEY_KEY = "key";
    private static final String HEADER_CACHE_CONTENT_TAG_KEY = "tag";
    static final String TAG = FileLruCache.class.getSimpleName();
    private static final AtomicLong bufferIndex = new AtomicLong();
    private final File directory;
    private boolean isTrimInProgress;
    private boolean isTrimPending;
    private AtomicLong lastClearCacheTime = new AtomicLong(0);
    private final Limits limits;
    private final Object lock;
    private final String tag;

    private interface StreamCloseCallback {
        void onClose();
    }

    /* renamed from: com.facebook.internal.FileLruCache$3 */
    class C19383 implements Runnable {
        C19383() {
        }

        public void run() {
            FileLruCache.this.trim();
        }
    }

    private static class BufferFile {
        private static final String FILE_NAME_PREFIX = "buffer";
        private static final FilenameFilter filterExcludeBufferFiles = new C19391();
        private static final FilenameFilter filterExcludeNonBufferFiles = new C19402();

        /* renamed from: com.facebook.internal.FileLruCache$BufferFile$1 */
        static class C19391 implements FilenameFilter {
            C19391() {
            }

            public boolean accept(File dir, String filename) {
                return !filename.startsWith(BufferFile.FILE_NAME_PREFIX);
            }
        }

        /* renamed from: com.facebook.internal.FileLruCache$BufferFile$2 */
        static class C19402 implements FilenameFilter {
            C19402() {
            }

            public boolean accept(File dir, String filename) {
                return filename.startsWith(BufferFile.FILE_NAME_PREFIX);
            }
        }

        private BufferFile() {
        }

        static void deleteAll(File root) {
            File[] filesToDelete = root.listFiles(excludeNonBufferFiles());
            if (filesToDelete != null) {
                for (File file : filesToDelete) {
                    file.delete();
                }
            }
        }

        static FilenameFilter excludeBufferFiles() {
            return filterExcludeBufferFiles;
        }

        static FilenameFilter excludeNonBufferFiles() {
            return filterExcludeNonBufferFiles;
        }

        static File newFile(File root) {
            return new File(root, FILE_NAME_PREFIX + Long.valueOf(FileLruCache.bufferIndex.incrementAndGet()).toString());
        }
    }

    private static class CloseCallbackOutputStream extends OutputStream {
        final StreamCloseCallback callback;
        final OutputStream innerStream;

        CloseCallbackOutputStream(OutputStream innerStream, StreamCloseCallback callback) {
            this.innerStream = innerStream;
            this.callback = callback;
        }

        public void close() throws IOException {
            try {
                this.innerStream.close();
            } finally {
                this.callback.onClose();
            }
        }

        public void flush() throws IOException {
            this.innerStream.flush();
        }

        public void write(byte[] buffer, int offset, int count) throws IOException {
            this.innerStream.write(buffer, offset, count);
        }

        public void write(byte[] buffer) throws IOException {
            this.innerStream.write(buffer);
        }

        public void write(int oneByte) throws IOException {
            this.innerStream.write(oneByte);
        }
    }

    private static final class CopyingInputStream extends InputStream {
        final InputStream input;
        final OutputStream output;

        CopyingInputStream(InputStream input, OutputStream output) {
            this.input = input;
            this.output = output;
        }

        public int available() throws IOException {
            return this.input.available();
        }

        public void close() throws IOException {
            try {
                this.input.close();
            } finally {
                this.output.close();
            }
        }

        public void mark(int readlimit) {
            throw new UnsupportedOperationException();
        }

        public boolean markSupported() {
            return false;
        }

        public int read(byte[] buffer) throws IOException {
            int count = this.input.read(buffer);
            if (count > 0) {
                this.output.write(buffer, 0, count);
            }
            return count;
        }

        public int read() throws IOException {
            int b = this.input.read();
            if (b >= 0) {
                this.output.write(b);
            }
            return b;
        }

        public int read(byte[] buffer, int offset, int length) throws IOException {
            int count = this.input.read(buffer, offset, length);
            if (count > 0) {
                this.output.write(buffer, offset, count);
            }
            return count;
        }

        public synchronized void reset() {
            throw new UnsupportedOperationException();
        }

        public long skip(long byteCount) throws IOException {
            byte[] buffer = new byte[1024];
            long total = 0;
            while (total < byteCount) {
                int count = read(buffer, 0, (int) Math.min(byteCount - total, (long) buffer.length));
                if (count < 0) {
                    break;
                }
                total += (long) count;
            }
            return total;
        }
    }

    public static final class Limits {
        private int byteCount = 1048576;
        private int fileCount = 1024;

        /* Access modifiers changed, original: 0000 */
        public int getByteCount() {
            return this.byteCount;
        }

        /* Access modifiers changed, original: 0000 */
        public int getFileCount() {
            return this.fileCount;
        }

        /* Access modifiers changed, original: 0000 */
        public void setByteCount(int n) {
            if (n < 0) {
                throw new InvalidParameterException("Cache byte-count limit must be >= 0");
            }
            this.byteCount = n;
        }

        /* Access modifiers changed, original: 0000 */
        public void setFileCount(int n) {
            if (n < 0) {
                throw new InvalidParameterException("Cache file count limit must be >= 0");
            }
            this.fileCount = n;
        }
    }

    private static final class ModifiedFile implements Comparable<ModifiedFile> {
        private static final int HASH_MULTIPLIER = 37;
        private static final int HASH_SEED = 29;
        private final File file;
        private final long modified;

        ModifiedFile(File file) {
            this.file = file;
            this.modified = file.lastModified();
        }

        /* Access modifiers changed, original: 0000 */
        public File getFile() {
            return this.file;
        }

        /* Access modifiers changed, original: 0000 */
        public long getModified() {
            return this.modified;
        }

        public int compareTo(ModifiedFile another) {
            if (getModified() < another.getModified()) {
                return -1;
            }
            if (getModified() > another.getModified()) {
                return 1;
            }
            return getFile().compareTo(another.getFile());
        }

        public boolean equals(Object another) {
            return (another instanceof ModifiedFile) && compareTo((ModifiedFile) another) == 0;
        }

        public int hashCode() {
            return ((this.file.hashCode() + 1073) * 37) + ((int) (this.modified % 2147483647L));
        }
    }

    private static final class StreamHeader {
        private static final int HEADER_VERSION = 0;

        private StreamHeader() {
        }

        static void writeHeader(OutputStream stream, JSONObject header) throws IOException {
            byte[] headerBytes = (!(header instanceof JSONObject) ? header.toString() : JSONObjectInstrumentation.toString(header)).getBytes();
            stream.write(0);
            stream.write((headerBytes.length >> 16) & 255);
            stream.write((headerBytes.length >> 8) & 255);
            stream.write((headerBytes.length >> 0) & 255);
            stream.write(headerBytes);
        }

        static JSONObject readHeader(InputStream stream) throws IOException {
            if (stream.read() != 0) {
                return null;
            }
            int headerSize = 0;
            for (int i = 0; i < 3; i++) {
                int b = stream.read();
                if (b == -1) {
                    Logger.log(LoggingBehavior.CACHE, FileLruCache.TAG, "readHeader: stream.read returned -1 while reading header size");
                    return null;
                }
                headerSize = (headerSize << 8) + (b & 255);
            }
            byte[] headerBytes = new byte[headerSize];
            int count = 0;
            while (count < headerBytes.length) {
                int readCount = stream.read(headerBytes, count, headerBytes.length - count);
                if (readCount < 1) {
                    Logger.log(LoggingBehavior.CACHE, FileLruCache.TAG, "readHeader: stream.read stopped at " + Integer.valueOf(count) + " when expected " + headerBytes.length);
                    return null;
                }
                count += readCount;
            }
            try {
                Object parsed = new JSONTokener(new String(headerBytes)).nextValue();
                if (parsed instanceof JSONObject) {
                    return (JSONObject) parsed;
                }
                Logger.log(LoggingBehavior.CACHE, FileLruCache.TAG, "readHeader: expected JSONObject, got " + parsed.getClass().getCanonicalName());
                return null;
            } catch (JSONException e) {
                throw new IOException(e.getMessage());
            }
        }
    }

    public FileLruCache(Context context, String tag, Limits limits) {
        this.tag = tag;
        this.limits = limits;
        this.directory = new File(context.getCacheDir(), tag);
        this.lock = new Object();
        if (this.directory.mkdirs() || this.directory.isDirectory()) {
            BufferFile.deleteAll(this.directory);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public long sizeInBytesForTest() {
        synchronized (this.lock) {
            while (true) {
                if (!this.isTrimPending && !this.isTrimInProgress) {
                    break;
                }
                try {
                    this.lock.wait();
                } catch (InterruptedException e) {
                }
            }
            while (true) {
            }
        }
        File[] files = this.directory.listFiles();
        long total = 0;
        if (files != null) {
            for (File file : files) {
                total += file.length();
            }
        }
        return total;
    }

    public InputStream get(String key) throws IOException {
        return get(key, null);
    }

    public InputStream get(String key, String contentTag) throws IOException {
        File file = new File(this.directory, Utility.md5hash(key));
        try {
            FileInputStream input = new FileInputStream(file);
            BufferedInputStream buffered = new BufferedInputStream(input, 8192);
            try {
                JSONObject header = StreamHeader.readHeader(buffered);
                FileInputStream fileInputStream;
                if (header == null) {
                    fileInputStream = input;
                    return null;
                }
                String foundKey = header.optString("key");
                if (foundKey == null || !foundKey.equals(key)) {
                    if (null == null) {
                        buffered.close();
                    }
                    fileInputStream = input;
                    return null;
                }
                String headerContentTag = header.optString(HEADER_CACHE_CONTENT_TAG_KEY, null);
                if ((contentTag != null || headerContentTag == null) && (contentTag == null || contentTag.equals(headerContentTag))) {
                    long accessTime = new Date().getTime();
                    Logger.log(LoggingBehavior.CACHE, TAG, "Setting lastModified to " + Long.valueOf(accessTime) + " for " + file.getName());
                    file.setLastModified(accessTime);
                    if (!true) {
                        buffered.close();
                    }
                    fileInputStream = input;
                    return buffered;
                }
                if (null == null) {
                    buffered.close();
                }
                fileInputStream = input;
                return null;
            } finally {
                if (null == null) {
                    buffered.close();
                }
            }
        } catch (IOException e) {
            return null;
        }
    }

    /* Access modifiers changed, original: 0000 */
    public OutputStream openPutStream(String key) throws IOException {
        return openPutStream(key, null);
    }

    public OutputStream openPutStream(String key, String contentTag) throws IOException {
        final File buffer = BufferFile.newFile(this.directory);
        buffer.delete();
        if (buffer.createNewFile()) {
            try {
                FileOutputStream file = new FileOutputStream(buffer);
                final long bufferFileCreateTime = System.currentTimeMillis();
                final String str = key;
                BufferedOutputStream buffered = new BufferedOutputStream(new CloseCallbackOutputStream(file, new StreamCloseCallback() {
                    public void onClose() {
                        if (bufferFileCreateTime < FileLruCache.this.lastClearCacheTime.get()) {
                            buffer.delete();
                        } else {
                            FileLruCache.this.renameToTargetAndTrim(str, buffer);
                        }
                    }
                }), 8192);
                try {
                    JSONObject header = new JSONObject();
                    header.put("key", key);
                    if (!Utility.isNullOrEmpty(contentTag)) {
                        header.put(HEADER_CACHE_CONTENT_TAG_KEY, contentTag);
                    }
                    StreamHeader.writeHeader(buffered, header);
                    if (!true) {
                        buffered.close();
                    }
                    return buffered;
                } catch (JSONException e) {
                    Logger.log(LoggingBehavior.CACHE, 5, TAG, "Error creating JSON header for cache file: " + e);
                    throw new IOException(e.getMessage());
                } catch (Throwable th) {
                    if (!false) {
                        buffered.close();
                    }
                }
            } catch (FileNotFoundException e2) {
                Logger.log(LoggingBehavior.CACHE, 5, TAG, "Error creating buffer output stream: " + e2);
                throw new IOException(e2.getMessage());
            }
        }
        throw new IOException("Could not create file at " + buffer.getAbsolutePath());
    }

    public void clearCache() {
        final File[] filesToDelete = this.directory.listFiles(BufferFile.excludeBufferFiles());
        this.lastClearCacheTime.set(System.currentTimeMillis());
        if (filesToDelete != null) {
            Settings.getExecutor().execute(new Runnable() {
                public void run() {
                    for (File file : filesToDelete) {
                        file.delete();
                    }
                }
            });
        }
    }

    private void renameToTargetAndTrim(String key, File buffer) {
        if (!buffer.renameTo(new File(this.directory, Utility.md5hash(key)))) {
            buffer.delete();
        }
        postTrim();
    }

    public InputStream interceptAndPut(String key, InputStream input) throws IOException {
        return new CopyingInputStream(input, openPutStream(key));
    }

    public String toString() {
        return "{FileLruCache: tag:" + this.tag + " file:" + this.directory.getName() + "}";
    }

    private void postTrim() {
        synchronized (this.lock) {
            if (!this.isTrimPending) {
                this.isTrimPending = true;
                Settings.getExecutor().execute(new C19383());
            }
        }
    }

    private void trim() {
        synchronized (this.lock) {
            this.isTrimPending = false;
            this.isTrimInProgress = true;
        }
        try {
            File file;
            Logger.log(LoggingBehavior.CACHE, TAG, "trim started");
            PriorityQueue<ModifiedFile> heap = new PriorityQueue();
            long size = 0;
            long count = 0;
            File[] filesToTrim = this.directory.listFiles(BufferFile.excludeBufferFiles());
            if (filesToTrim != null) {
                for (File file2 : filesToTrim) {
                    ModifiedFile modified = new ModifiedFile(file2);
                    heap.add(modified);
                    Logger.log(LoggingBehavior.CACHE, TAG, "  trim considering time=" + Long.valueOf(modified.getModified()) + " name=" + modified.getFile().getName());
                    size += file2.length();
                    count++;
                }
            }
            while (true) {
                if (size > ((long) this.limits.getByteCount()) || count > ((long) this.limits.getFileCount())) {
                    file2 = ((ModifiedFile) heap.remove()).getFile();
                    Logger.log(LoggingBehavior.CACHE, TAG, "  trim removing " + file2.getName());
                    size -= file2.length();
                    count--;
                    file2.delete();
                } else {
                    synchronized (this.lock) {
                        this.isTrimInProgress = false;
                        this.lock.notifyAll();
                    }
                    return;
                }
            }
        } catch (Throwable th) {
            synchronized (this.lock) {
                this.isTrimInProgress = false;
                this.lock.notifyAll();
            }
        }
    }
}
