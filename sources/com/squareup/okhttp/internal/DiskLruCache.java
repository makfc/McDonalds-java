package com.squareup.okhttp.internal;

import com.newrelic.agent.android.tracing.ActivityTrace;
import com.squareup.okhttp.internal.p054io.FileSystem;
import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.NoSuchElementException;
import java.util.concurrent.Executor;
import java.util.regex.Pattern;
import okio.Buffer;
import okio.BufferedSink;
import okio.Okio;
import okio.Sink;
import okio.Source;
import okio.Timeout;

public final class DiskLruCache implements Closeable {
    static final /* synthetic */ boolean $assertionsDisabled = (!DiskLruCache.class.desiredAssertionStatus());
    static final Pattern LEGAL_KEY_PATTERN = Pattern.compile("[a-z0-9_-]{1,120}");
    private static final Sink NULL_SINK = new C42834();
    private final int appVersion;
    private final Runnable cleanupRunnable;
    private boolean closed;
    private final File directory;
    private final Executor executor;
    private final FileSystem fileSystem;
    private boolean hasJournalErrors;
    private boolean initialized;
    private final File journalFile;
    private final File journalFileBackup;
    private final File journalFileTmp;
    private BufferedSink journalWriter;
    private final LinkedHashMap<String, Entry> lruEntries;
    private long maxSize;
    private long nextSequenceNumber;
    private int redundantOpCount;
    private long size;
    private final int valueCount;

    /* renamed from: com.squareup.okhttp.internal.DiskLruCache$1 */
    class C42801 implements Runnable {
        final /* synthetic */ DiskLruCache this$0;

        public void run() {
            int i = 0;
            synchronized (this.this$0) {
                if (!this.this$0.initialized) {
                    i = 1;
                }
                if ((i | this.this$0.closed) != 0) {
                    return;
                }
                try {
                    this.this$0.trimToSize();
                    if (this.this$0.journalRebuildRequired()) {
                        this.this$0.rebuildJournal();
                        this.this$0.redundantOpCount = 0;
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    /* renamed from: com.squareup.okhttp.internal.DiskLruCache$3 */
    class C42823 implements Iterator<Snapshot> {
        final Iterator<Entry> delegate;
        Snapshot nextSnapshot;
        Snapshot removeSnapshot;
        final /* synthetic */ DiskLruCache this$0;

        public boolean hasNext() {
            if (this.nextSnapshot != null) {
                return true;
            }
            synchronized (this.this$0) {
                if (this.this$0.closed) {
                    return false;
                }
                while (this.delegate.hasNext()) {
                    Snapshot snapshot = ((Entry) this.delegate.next()).snapshot();
                    if (snapshot != null) {
                        this.nextSnapshot = snapshot;
                        return true;
                    }
                }
                return false;
            }
        }

        public Snapshot next() {
            if (hasNext()) {
                this.removeSnapshot = this.nextSnapshot;
                this.nextSnapshot = null;
                return this.removeSnapshot;
            }
            throw new NoSuchElementException();
        }

        public void remove() {
            if (this.removeSnapshot == null) {
                throw new IllegalStateException("remove() before next()");
            }
            try {
                this.this$0.remove(this.removeSnapshot.key);
            } catch (IOException e) {
            } finally {
                this.removeSnapshot = null;
            }
        }
    }

    /* renamed from: com.squareup.okhttp.internal.DiskLruCache$4 */
    static class C42834 implements Sink {
        C42834() {
        }

        public void write(Buffer source, long byteCount) throws IOException {
            source.skip(byteCount);
        }

        public void flush() throws IOException {
        }

        public Timeout timeout() {
            return Timeout.NONE;
        }

        public void close() throws IOException {
        }
    }

    public final class Editor {
        private boolean committed;
        private final Entry entry;
        private boolean hasErrors;
        private final boolean[] written;

        /* synthetic */ Editor(DiskLruCache x0, Entry x1, C42801 x2) {
            this(x1);
        }

        private Editor(Entry entry) {
            this.entry = entry;
            this.written = entry.readable ? null : new boolean[DiskLruCache.this.valueCount];
        }

        public Sink newSink(int index) throws IOException {
            Sink c42841;
            synchronized (DiskLruCache.this) {
                if (this.entry.currentEditor != this) {
                    throw new IllegalStateException();
                }
                if (!this.entry.readable) {
                    this.written[index] = true;
                }
                try {
                    c42841 = new FaultHidingSink(DiskLruCache.this.fileSystem.sink(this.entry.dirtyFiles[index])) {
                        /* Access modifiers changed, original: protected */
                        public void onException(IOException e) {
                            synchronized (DiskLruCache.this) {
                                Editor.this.hasErrors = true;
                            }
                        }
                    };
                } catch (FileNotFoundException e) {
                    c42841 = DiskLruCache.NULL_SINK;
                }
            }
            return c42841;
        }

        public void commit() throws IOException {
            synchronized (DiskLruCache.this) {
                if (this.hasErrors) {
                    DiskLruCache.this.completeEdit(this, false);
                    DiskLruCache.this.removeEntry(this.entry);
                } else {
                    DiskLruCache.this.completeEdit(this, true);
                }
                this.committed = true;
            }
        }

        public void abort() throws IOException {
            synchronized (DiskLruCache.this) {
                DiskLruCache.this.completeEdit(this, false);
            }
        }
    }

    private final class Entry {
        private final File[] cleanFiles;
        private Editor currentEditor;
        private final File[] dirtyFiles;
        private final String key;
        private final long[] lengths;
        private boolean readable;
        private long sequenceNumber;

        /* synthetic */ Entry(DiskLruCache x0, String x1, C42801 x2) {
            this(x1);
        }

        private Entry(String key) {
            this.key = key;
            this.lengths = new long[DiskLruCache.this.valueCount];
            this.cleanFiles = new File[DiskLruCache.this.valueCount];
            this.dirtyFiles = new File[DiskLruCache.this.valueCount];
            StringBuilder fileBuilder = new StringBuilder(key).append('.');
            int truncateTo = fileBuilder.length();
            for (int i = 0; i < DiskLruCache.this.valueCount; i++) {
                fileBuilder.append(i);
                this.cleanFiles[i] = new File(DiskLruCache.this.directory, fileBuilder.toString());
                fileBuilder.append(".tmp");
                this.dirtyFiles[i] = new File(DiskLruCache.this.directory, fileBuilder.toString());
                fileBuilder.setLength(truncateTo);
            }
        }

        private void setLengths(String[] strings) throws IOException {
            if (strings.length != DiskLruCache.this.valueCount) {
                throw invalidLengths(strings);
            }
            int i = 0;
            while (i < strings.length) {
                try {
                    this.lengths[i] = Long.parseLong(strings[i]);
                    i++;
                } catch (NumberFormatException e) {
                    throw invalidLengths(strings);
                }
            }
        }

        /* Access modifiers changed, original: 0000 */
        public void writeLengths(BufferedSink writer) throws IOException {
            for (long length : this.lengths) {
                writer.writeByte(32).writeDecimalLong(length);
            }
        }

        private IOException invalidLengths(String[] strings) throws IOException {
            throw new IOException("unexpected journal line: " + Arrays.toString(strings));
        }

        /* Access modifiers changed, original: 0000 */
        public Snapshot snapshot() {
            if (Thread.holdsLock(DiskLruCache.this)) {
                Source[] sources = new Source[DiskLruCache.this.valueCount];
                long[] lengths = (long[]) this.lengths.clone();
                int i = 0;
                while (i < DiskLruCache.this.valueCount) {
                    try {
                        sources[i] = DiskLruCache.this.fileSystem.source(this.cleanFiles[i]);
                        i++;
                    } catch (FileNotFoundException e) {
                        i = 0;
                        while (i < DiskLruCache.this.valueCount && sources[i] != null) {
                            Util.closeQuietly(sources[i]);
                            i++;
                        }
                        return null;
                    }
                }
                return new Snapshot(DiskLruCache.this, this.key, this.sequenceNumber, sources, lengths, null);
            }
            throw new AssertionError();
        }
    }

    public final class Snapshot implements Closeable {
        private final String key;
        private final long[] lengths;
        private final long sequenceNumber;
        private final Source[] sources;

        /* synthetic */ Snapshot(DiskLruCache x0, String x1, long x2, Source[] x3, long[] x4, C42801 x5) {
            this(x1, x2, x3, x4);
        }

        private Snapshot(String key, long sequenceNumber, Source[] sources, long[] lengths) {
            this.key = key;
            this.sequenceNumber = sequenceNumber;
            this.sources = sources;
            this.lengths = lengths;
        }

        public Editor edit() throws IOException {
            return DiskLruCache.this.edit(this.key, this.sequenceNumber);
        }

        public Source getSource(int index) {
            return this.sources[index];
        }

        public void close() {
            for (Closeable in : this.sources) {
                Util.closeQuietly(in);
            }
        }
    }

    public synchronized void initialize() throws IOException {
        if (!$assertionsDisabled && !Thread.holdsLock(this)) {
            throw new AssertionError();
        } else if (!this.initialized) {
            if (this.fileSystem.exists(this.journalFileBackup)) {
                if (this.fileSystem.exists(this.journalFile)) {
                    this.fileSystem.delete(this.journalFileBackup);
                } else {
                    this.fileSystem.rename(this.journalFileBackup, this.journalFile);
                }
            }
            if (this.fileSystem.exists(this.journalFile)) {
                try {
                    readJournal();
                    processJournal();
                    this.initialized = true;
                } catch (IOException journalIsCorrupt) {
                    Platform.get().logW("DiskLruCache " + this.directory + " is corrupt: " + journalIsCorrupt.getMessage() + ", removing");
                    delete();
                    this.closed = false;
                }
            }
            rebuildJournal();
            this.initialized = true;
        }
    }

    private void readJournal() throws IOException {
        Closeable source = Okio.buffer(this.fileSystem.source(this.journalFile));
        int lineCount;
        try {
            String magic = source.readUtf8LineStrict();
            String version = source.readUtf8LineStrict();
            String appVersionString = source.readUtf8LineStrict();
            String valueCountString = source.readUtf8LineStrict();
            String blank = source.readUtf8LineStrict();
            if ("libcore.io.DiskLruCache".equals(magic) && "1".equals(version) && Integer.toString(this.appVersion).equals(appVersionString) && Integer.toString(this.valueCount).equals(valueCountString) && "".equals(blank)) {
                lineCount = 0;
                while (true) {
                    readJournalLine(source.readUtf8LineStrict());
                    lineCount++;
                }
            } else {
                throw new IOException("unexpected journal header: [" + magic + ", " + version + ", " + valueCountString + ", " + blank + "]");
            }
        } catch (EOFException e) {
            this.redundantOpCount = lineCount - this.lruEntries.size();
            if (source.exhausted()) {
                this.journalWriter = newJournalWriter();
            } else {
                rebuildJournal();
            }
            Util.closeQuietly(source);
        } catch (Throwable th) {
            Util.closeQuietly(source);
        }
    }

    private BufferedSink newJournalWriter() throws FileNotFoundException {
        return Okio.buffer(new FaultHidingSink(this.fileSystem.appendingSink(this.journalFile)) {
            static final /* synthetic */ boolean $assertionsDisabled = (!DiskLruCache.class.desiredAssertionStatus());

            /* Access modifiers changed, original: protected */
            public void onException(IOException e) {
                if ($assertionsDisabled || Thread.holdsLock(DiskLruCache.this)) {
                    DiskLruCache.this.hasJournalErrors = true;
                    return;
                }
                throw new AssertionError();
            }
        });
    }

    private void readJournalLine(String line) throws IOException {
        int firstSpace = line.indexOf(32);
        if (firstSpace == -1) {
            throw new IOException("unexpected journal line: " + line);
        }
        String key;
        int keyBegin = firstSpace + 1;
        int secondSpace = line.indexOf(32, keyBegin);
        if (secondSpace == -1) {
            key = line.substring(keyBegin);
            if (firstSpace == "REMOVE".length() && line.startsWith("REMOVE")) {
                this.lruEntries.remove(key);
                return;
            }
        }
        key = line.substring(keyBegin, secondSpace);
        Entry entry = (Entry) this.lruEntries.get(key);
        if (entry == null) {
            entry = new Entry(this, key, null);
            this.lruEntries.put(key, entry);
        }
        if (secondSpace != -1 && firstSpace == "CLEAN".length() && line.startsWith("CLEAN")) {
            String[] parts = line.substring(secondSpace + 1).split(" ");
            entry.readable = true;
            entry.currentEditor = null;
            entry.setLengths(parts);
        } else if (secondSpace == -1 && firstSpace == "DIRTY".length() && line.startsWith("DIRTY")) {
            entry.currentEditor = new Editor(this, entry, null);
        } else if (secondSpace != -1 || firstSpace != "READ".length() || !line.startsWith("READ")) {
            throw new IOException("unexpected journal line: " + line);
        }
    }

    private void processJournal() throws IOException {
        this.fileSystem.delete(this.journalFileTmp);
        Iterator<Entry> i = this.lruEntries.values().iterator();
        while (i.hasNext()) {
            Entry entry = (Entry) i.next();
            int t;
            if (entry.currentEditor == null) {
                for (t = 0; t < this.valueCount; t++) {
                    this.size += entry.lengths[t];
                }
            } else {
                entry.currentEditor = null;
                for (t = 0; t < this.valueCount; t++) {
                    this.fileSystem.delete(entry.cleanFiles[t]);
                    this.fileSystem.delete(entry.dirtyFiles[t]);
                }
                i.remove();
            }
        }
    }

    private synchronized void rebuildJournal() throws IOException {
        if (this.journalWriter != null) {
            this.journalWriter.close();
        }
        BufferedSink writer = Okio.buffer(this.fileSystem.sink(this.journalFileTmp));
        try {
            writer.writeUtf8("libcore.io.DiskLruCache").writeByte(10);
            writer.writeUtf8("1").writeByte(10);
            writer.writeDecimalLong((long) this.appVersion).writeByte(10);
            writer.writeDecimalLong((long) this.valueCount).writeByte(10);
            writer.writeByte(10);
            for (Entry entry : this.lruEntries.values()) {
                if (entry.currentEditor != null) {
                    writer.writeUtf8("DIRTY").writeByte(32);
                    writer.writeUtf8(entry.key);
                    writer.writeByte(10);
                } else {
                    writer.writeUtf8("CLEAN").writeByte(32);
                    writer.writeUtf8(entry.key);
                    entry.writeLengths(writer);
                    writer.writeByte(10);
                }
            }
            writer.close();
            if (this.fileSystem.exists(this.journalFile)) {
                this.fileSystem.rename(this.journalFile, this.journalFileBackup);
            }
            this.fileSystem.rename(this.journalFileTmp, this.journalFile);
            this.fileSystem.delete(this.journalFileBackup);
            this.journalWriter = newJournalWriter();
            this.hasJournalErrors = false;
        } catch (Throwable th) {
            writer.close();
        }
    }

    public synchronized Snapshot get(String key) throws IOException {
        Snapshot snapshot;
        initialize();
        checkNotClosed();
        validateKey(key);
        Entry entry = (Entry) this.lruEntries.get(key);
        if (entry == null || !entry.readable) {
            snapshot = null;
        } else {
            snapshot = entry.snapshot();
            if (snapshot == null) {
                snapshot = null;
            } else {
                this.redundantOpCount++;
                this.journalWriter.writeUtf8("READ").writeByte(32).writeUtf8(key).writeByte(10);
                if (journalRebuildRequired()) {
                    this.executor.execute(this.cleanupRunnable);
                }
            }
        }
        return snapshot;
    }

    public Editor edit(String key) throws IOException {
        return edit(key, -1);
    }

    /* JADX WARNING: Missing block: B:14:0x002b, code skipped:
            if (com.squareup.okhttp.internal.DiskLruCache.Entry.access$900(r1) == null) goto L_0x002d;
     */
    private synchronized com.squareup.okhttp.internal.DiskLruCache.Editor edit(java.lang.String r5, long r6) throws java.io.IOException {
        /*
        r4 = this;
        r0 = 0;
        monitor-enter(r4);
        r4.initialize();	 Catch:{ all -> 0x0064 }
        r4.checkNotClosed();	 Catch:{ all -> 0x0064 }
        r4.validateKey(r5);	 Catch:{ all -> 0x0064 }
        r2 = r4.lruEntries;	 Catch:{ all -> 0x0064 }
        r1 = r2.get(r5);	 Catch:{ all -> 0x0064 }
        r1 = (com.squareup.okhttp.internal.DiskLruCache.Entry) r1;	 Catch:{ all -> 0x0064 }
        r2 = -1;
        r2 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1));
        if (r2 == 0) goto L_0x0025;
    L_0x0019:
        if (r1 == 0) goto L_0x0023;
    L_0x001b:
        r2 = r1.sequenceNumber;	 Catch:{ all -> 0x0064 }
        r2 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1));
        if (r2 == 0) goto L_0x0025;
    L_0x0023:
        monitor-exit(r4);
        return r0;
    L_0x0025:
        if (r1 == 0) goto L_0x002d;
    L_0x0027:
        r2 = r1.currentEditor;	 Catch:{ all -> 0x0064 }
        if (r2 != 0) goto L_0x0023;
    L_0x002d:
        r2 = r4.journalWriter;	 Catch:{ all -> 0x0064 }
        r3 = "DIRTY";
        r2 = r2.writeUtf8(r3);	 Catch:{ all -> 0x0064 }
        r3 = 32;
        r2 = r2.writeByte(r3);	 Catch:{ all -> 0x0064 }
        r2 = r2.writeUtf8(r5);	 Catch:{ all -> 0x0064 }
        r3 = 10;
        r2.writeByte(r3);	 Catch:{ all -> 0x0064 }
        r2 = r4.journalWriter;	 Catch:{ all -> 0x0064 }
        r2.flush();	 Catch:{ all -> 0x0064 }
        r2 = r4.hasJournalErrors;	 Catch:{ all -> 0x0064 }
        if (r2 != 0) goto L_0x0023;
    L_0x004d:
        if (r1 != 0) goto L_0x005a;
    L_0x004f:
        r1 = new com.squareup.okhttp.internal.DiskLruCache$Entry;	 Catch:{ all -> 0x0064 }
        r2 = 0;
        r1.<init>(r4, r5, r2);	 Catch:{ all -> 0x0064 }
        r2 = r4.lruEntries;	 Catch:{ all -> 0x0064 }
        r2.put(r5, r1);	 Catch:{ all -> 0x0064 }
    L_0x005a:
        r0 = new com.squareup.okhttp.internal.DiskLruCache$Editor;	 Catch:{ all -> 0x0064 }
        r2 = 0;
        r0.<init>(r4, r1, r2);	 Catch:{ all -> 0x0064 }
        r1.currentEditor = r0;	 Catch:{ all -> 0x0064 }
        goto L_0x0023;
    L_0x0064:
        r2 = move-exception;
        monitor-exit(r4);
        throw r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.squareup.okhttp.internal.DiskLruCache.edit(java.lang.String, long):com.squareup.okhttp.internal.DiskLruCache$Editor");
    }

    private synchronized void completeEdit(Editor editor, boolean success) throws IOException {
        Entry entry = editor.entry;
        if (entry.currentEditor != editor) {
            throw new IllegalStateException();
        }
        int i;
        if (success) {
            if (!entry.readable) {
                i = 0;
                while (i < this.valueCount) {
                    if (!editor.written[i]) {
                        editor.abort();
                        throw new IllegalStateException("Newly created entry didn't create value for index " + i);
                    } else if (!this.fileSystem.exists(entry.dirtyFiles[i])) {
                        editor.abort();
                        break;
                    } else {
                        i++;
                    }
                }
            }
        }
        for (i = 0; i < this.valueCount; i++) {
            File dirty = entry.dirtyFiles[i];
            if (!success) {
                this.fileSystem.delete(dirty);
            } else if (this.fileSystem.exists(dirty)) {
                File clean = entry.cleanFiles[i];
                this.fileSystem.rename(dirty, clean);
                long oldLength = entry.lengths[i];
                long newLength = this.fileSystem.size(clean);
                entry.lengths[i] = newLength;
                this.size = (this.size - oldLength) + newLength;
            }
        }
        this.redundantOpCount++;
        entry.currentEditor = null;
        if ((entry.readable | success) != 0) {
            entry.readable = true;
            this.journalWriter.writeUtf8("CLEAN").writeByte(32);
            this.journalWriter.writeUtf8(entry.key);
            entry.writeLengths(this.journalWriter);
            this.journalWriter.writeByte(10);
            if (success) {
                long j = this.nextSequenceNumber;
                this.nextSequenceNumber = 1 + j;
                entry.sequenceNumber = j;
            }
        } else {
            this.lruEntries.remove(entry.key);
            this.journalWriter.writeUtf8("REMOVE").writeByte(32);
            this.journalWriter.writeUtf8(entry.key);
            this.journalWriter.writeByte(10);
        }
        this.journalWriter.flush();
        if (this.size > this.maxSize || journalRebuildRequired()) {
            this.executor.execute(this.cleanupRunnable);
        }
    }

    private boolean journalRebuildRequired() {
        return this.redundantOpCount >= ActivityTrace.MAX_TRACES && this.redundantOpCount >= this.lruEntries.size();
    }

    public synchronized boolean remove(String key) throws IOException {
        boolean z;
        initialize();
        checkNotClosed();
        validateKey(key);
        Entry entry = (Entry) this.lruEntries.get(key);
        if (entry == null) {
            z = false;
        } else {
            z = removeEntry(entry);
        }
        return z;
    }

    private boolean removeEntry(Entry entry) throws IOException {
        if (entry.currentEditor != null) {
            entry.currentEditor.hasErrors = true;
        }
        for (int i = 0; i < this.valueCount; i++) {
            this.fileSystem.delete(entry.cleanFiles[i]);
            this.size -= entry.lengths[i];
            entry.lengths[i] = 0;
        }
        this.redundantOpCount++;
        this.journalWriter.writeUtf8("REMOVE").writeByte(32).writeUtf8(entry.key).writeByte(10);
        this.lruEntries.remove(entry.key);
        if (journalRebuildRequired()) {
            this.executor.execute(this.cleanupRunnable);
        }
        return true;
    }

    public synchronized boolean isClosed() {
        return this.closed;
    }

    private synchronized void checkNotClosed() {
        if (isClosed()) {
            throw new IllegalStateException("cache is closed");
        }
    }

    public synchronized void close() throws IOException {
        if (!this.initialized || this.closed) {
            this.closed = true;
        } else {
            for (Entry entry : (Entry[]) this.lruEntries.values().toArray(new Entry[this.lruEntries.size()])) {
                if (entry.currentEditor != null) {
                    entry.currentEditor.abort();
                }
            }
            trimToSize();
            this.journalWriter.close();
            this.journalWriter = null;
            this.closed = true;
        }
    }

    private void trimToSize() throws IOException {
        while (this.size > this.maxSize) {
            removeEntry((Entry) this.lruEntries.values().iterator().next());
        }
    }

    public void delete() throws IOException {
        close();
        this.fileSystem.deleteContents(this.directory);
    }

    private void validateKey(String key) {
        if (!LEGAL_KEY_PATTERN.matcher(key).matches()) {
            throw new IllegalArgumentException("keys must match regex [a-z0-9_-]{1,120}: \"" + key + "\"");
        }
    }
}
