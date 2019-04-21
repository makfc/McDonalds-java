package com.squareup.okhttp;

import com.newrelic.agent.android.instrumentation.TransactionStateUtil;
import com.newrelic.agent.android.instrumentation.okhttp2.OkHttp2Instrumentation;
import com.squareup.okhttp.Headers.Builder;
import com.squareup.okhttp.internal.DiskLruCache;
import com.squareup.okhttp.internal.DiskLruCache.Editor;
import com.squareup.okhttp.internal.DiskLruCache.Snapshot;
import com.squareup.okhttp.internal.InternalCache;
import com.squareup.okhttp.internal.Util;
import com.squareup.okhttp.internal.http.CacheRequest;
import com.squareup.okhttp.internal.http.CacheStrategy;
import com.squareup.okhttp.internal.http.HttpMethod;
import com.squareup.okhttp.internal.http.OkHeaders;
import com.squareup.okhttp.internal.http.StatusLine;
import java.io.Closeable;
import java.io.IOException;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ByteString;
import okio.ForwardingSink;
import okio.ForwardingSource;
import okio.Okio;
import okio.Sink;
import okio.Source;

public final class Cache {
    private final DiskLruCache cache;
    private int hitCount;
    final InternalCache internalCache;
    private int networkCount;
    private int requestCount;
    private int writeAbortCount;
    private int writeSuccessCount;

    /* renamed from: com.squareup.okhttp.Cache$1 */
    class C42621 implements InternalCache {
        final /* synthetic */ Cache this$0;

        public Response get(Request request) throws IOException {
            return this.this$0.get(request);
        }

        public CacheRequest put(Response response) throws IOException {
            return this.this$0.put(response);
        }

        public void remove(Request request) throws IOException {
            this.this$0.remove(request);
        }

        public void update(Response cached, Response network) throws IOException {
            this.this$0.update(cached, network);
        }

        public void trackConditionalCacheHit() {
            this.this$0.trackConditionalCacheHit();
        }

        public void trackResponse(CacheStrategy cacheStrategy) {
            this.this$0.trackResponse(cacheStrategy);
        }
    }

    /* renamed from: com.squareup.okhttp.Cache$2 */
    class C42632 implements Iterator<String> {
        boolean canRemove;
        final Iterator<Snapshot> delegate;
        String nextUrl;

        public boolean hasNext() {
            if (this.nextUrl != null) {
                return true;
            }
            this.canRemove = false;
            while (this.delegate.hasNext()) {
                Snapshot snapshot = (Snapshot) this.delegate.next();
                try {
                    this.nextUrl = Okio.buffer(snapshot.getSource(0)).readUtf8LineStrict();
                    snapshot.close();
                    return true;
                } catch (IOException e) {
                    snapshot.close();
                } catch (Throwable th) {
                    snapshot.close();
                    throw th;
                }
            }
            return false;
        }

        public String next() {
            if (hasNext()) {
                String result = this.nextUrl;
                this.nextUrl = null;
                this.canRemove = true;
                return result;
            }
            throw new NoSuchElementException();
        }

        public void remove() {
            if (this.canRemove) {
                this.delegate.remove();
                return;
            }
            throw new IllegalStateException("remove() before next()");
        }
    }

    private final class CacheRequestImpl implements CacheRequest {
        private Sink body;
        private Sink cacheOut;
        private boolean done;
        private final Editor editor;

        public CacheRequestImpl(final Editor editor) throws IOException {
            this.editor = editor;
            this.cacheOut = editor.newSink(1);
            this.body = new ForwardingSink(this.cacheOut, Cache.this) {
                public void close() throws IOException {
                    synchronized (Cache.this) {
                        if (CacheRequestImpl.this.done) {
                            return;
                        }
                        CacheRequestImpl.this.done = true;
                        Cache.this.writeSuccessCount = Cache.this.writeSuccessCount + 1;
                        super.close();
                        editor.commit();
                    }
                }
            };
        }

        /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
        public void abort() {
            /*
            r2 = this;
            r1 = com.squareup.okhttp.Cache.this;
            monitor-enter(r1);
            r0 = r2.done;	 Catch:{ all -> 0x001f }
            if (r0 == 0) goto L_0x0009;
        L_0x0007:
            monitor-exit(r1);	 Catch:{ all -> 0x001f }
        L_0x0008:
            return;
        L_0x0009:
            r0 = 1;
            r2.done = r0;	 Catch:{ all -> 0x001f }
            r0 = com.squareup.okhttp.Cache.this;	 Catch:{ all -> 0x001f }
            r0.writeAbortCount = r0.writeAbortCount + 1;	 Catch:{ all -> 0x001f }
            monitor-exit(r1);	 Catch:{ all -> 0x001f }
            r0 = r2.cacheOut;
            com.squareup.okhttp.internal.Util.closeQuietly(r0);
            r0 = r2.editor;	 Catch:{ IOException -> 0x001d }
            r0.abort();	 Catch:{ IOException -> 0x001d }
            goto L_0x0008;
        L_0x001d:
            r0 = move-exception;
            goto L_0x0008;
        L_0x001f:
            r0 = move-exception;
            monitor-exit(r1);	 Catch:{ all -> 0x001f }
            throw r0;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.squareup.okhttp.Cache$CacheRequestImpl.abort():void");
        }

        public Sink body() {
            return this.body;
        }
    }

    private static class CacheResponseBody extends ResponseBody {
        private final BufferedSource bodySource;
        private final String contentLength;
        private final String contentType;
        private final Snapshot snapshot;

        public CacheResponseBody(final Snapshot snapshot, String contentType, String contentLength) {
            this.snapshot = snapshot;
            this.contentType = contentType;
            this.contentLength = contentLength;
            this.bodySource = Okio.buffer(new ForwardingSource(snapshot.getSource(1)) {
                public void close() throws IOException {
                    snapshot.close();
                    super.close();
                }
            });
        }

        public MediaType contentType() {
            return this.contentType != null ? MediaType.parse(this.contentType) : null;
        }

        public long contentLength() {
            try {
                return this.contentLength != null ? Long.parseLong(this.contentLength) : -1;
            } catch (NumberFormatException e) {
                return -1;
            }
        }

        public BufferedSource source() {
            return this.bodySource;
        }
    }

    private static final class Entry {
        private final int code;
        private final Handshake handshake;
        private final String message;
        private final Protocol protocol;
        private final String requestMethod;
        private final Headers responseHeaders;
        private final String url;
        private final Headers varyHeaders;

        public Entry(Source in) throws IOException {
            try {
                int i;
                BufferedSource source = Okio.buffer(in);
                this.url = source.readUtf8LineStrict();
                this.requestMethod = source.readUtf8LineStrict();
                Builder varyHeadersBuilder = new Builder();
                int varyRequestHeaderLineCount = Cache.readInt(source);
                for (i = 0; i < varyRequestHeaderLineCount; i++) {
                    varyHeadersBuilder.addLenient(source.readUtf8LineStrict());
                }
                this.varyHeaders = varyHeadersBuilder.build();
                StatusLine statusLine = StatusLine.parse(source.readUtf8LineStrict());
                this.protocol = statusLine.protocol;
                this.code = statusLine.code;
                this.message = statusLine.message;
                Builder responseHeadersBuilder = new Builder();
                int responseHeaderLineCount = Cache.readInt(source);
                for (i = 0; i < responseHeaderLineCount; i++) {
                    responseHeadersBuilder.addLenient(source.readUtf8LineStrict());
                }
                this.responseHeaders = responseHeadersBuilder.build();
                if (isHttps()) {
                    String blank = source.readUtf8LineStrict();
                    if (blank.length() > 0) {
                        throw new IOException("expected \"\" but was \"" + blank + "\"");
                    }
                    this.handshake = Handshake.get(source.readUtf8LineStrict(), readCertificateList(source), readCertificateList(source));
                } else {
                    this.handshake = null;
                }
                in.close();
            } catch (Throwable th) {
                in.close();
            }
        }

        public Entry(Response response) {
            this.url = response.request().urlString();
            this.varyHeaders = OkHeaders.varyHeaders(response);
            this.requestMethod = response.request().method();
            this.protocol = response.protocol();
            this.code = response.code();
            this.message = response.message();
            this.responseHeaders = response.headers();
            this.handshake = response.handshake();
        }

        public void writeTo(Editor editor) throws IOException {
            int i;
            BufferedSink sink = Okio.buffer(editor.newSink(0));
            sink.writeUtf8(this.url);
            sink.writeByte(10);
            sink.writeUtf8(this.requestMethod);
            sink.writeByte(10);
            sink.writeDecimalLong((long) this.varyHeaders.size());
            sink.writeByte(10);
            int size = this.varyHeaders.size();
            for (i = 0; i < size; i++) {
                sink.writeUtf8(this.varyHeaders.name(i));
                sink.writeUtf8(": ");
                sink.writeUtf8(this.varyHeaders.value(i));
                sink.writeByte(10);
            }
            sink.writeUtf8(new StatusLine(this.protocol, this.code, this.message).toString());
            sink.writeByte(10);
            sink.writeDecimalLong((long) this.responseHeaders.size());
            sink.writeByte(10);
            size = this.responseHeaders.size();
            for (i = 0; i < size; i++) {
                sink.writeUtf8(this.responseHeaders.name(i));
                sink.writeUtf8(": ");
                sink.writeUtf8(this.responseHeaders.value(i));
                sink.writeByte(10);
            }
            if (isHttps()) {
                sink.writeByte(10);
                sink.writeUtf8(this.handshake.cipherSuite());
                sink.writeByte(10);
                writeCertList(sink, this.handshake.peerCertificates());
                writeCertList(sink, this.handshake.localCertificates());
            }
            sink.close();
        }

        private boolean isHttps() {
            return this.url.startsWith("https://");
        }

        private List<Certificate> readCertificateList(BufferedSource source) throws IOException {
            int length = Cache.readInt(source);
            if (length == -1) {
                return Collections.emptyList();
            }
            try {
                CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
                List<Certificate> result = new ArrayList(length);
                for (int i = 0; i < length; i++) {
                    String line = source.readUtf8LineStrict();
                    Buffer bytes = new Buffer();
                    bytes.write(ByteString.decodeBase64(line));
                    result.add(certificateFactory.generateCertificate(bytes.inputStream()));
                }
                return result;
            } catch (CertificateException e) {
                throw new IOException(e.getMessage());
            }
        }

        private void writeCertList(BufferedSink sink, List<Certificate> certificates) throws IOException {
            try {
                sink.writeDecimalLong((long) certificates.size());
                sink.writeByte(10);
                int size = certificates.size();
                for (int i = 0; i < size; i++) {
                    sink.writeUtf8(ByteString.m8637of(((Certificate) certificates.get(i)).getEncoded()).base64());
                    sink.writeByte(10);
                }
            } catch (CertificateEncodingException e) {
                throw new IOException(e.getMessage());
            }
        }

        public boolean matches(Request request, Response response) {
            return this.url.equals(request.urlString()) && this.requestMethod.equals(request.method()) && OkHeaders.varyMatches(response, this.varyHeaders, request);
        }

        public Response response(Request request, Snapshot snapshot) {
            String contentType = this.responseHeaders.get(TransactionStateUtil.CONTENT_TYPE_HEADER);
            String contentLength = this.responseHeaders.get(TransactionStateUtil.CONTENT_LENGTH_HEADER);
            Request.Builder headers = new Request.Builder().url(this.url).method(this.requestMethod, null).headers(this.varyHeaders);
            Response.Builder headers2 = new Response.Builder().request(!(headers instanceof Request.Builder) ? headers.build() : OkHttp2Instrumentation.build(headers)).protocol(this.protocol).code(this.code).message(this.message).headers(this.responseHeaders);
            CacheResponseBody cacheResponseBody = new CacheResponseBody(snapshot, contentType, contentLength);
            return (!(headers2 instanceof Response.Builder) ? headers2.body(cacheResponseBody) : OkHttp2Instrumentation.body(headers2, cacheResponseBody)).handshake(this.handshake).build();
        }
    }

    private static String urlToKey(Request request) {
        return Util.md5Hex(request.urlString());
    }

    /* Access modifiers changed, original: 0000 */
    public Response get(Request request) {
        try {
            Closeable snapshot = this.cache.get(urlToKey(request));
            if (snapshot == null) {
                return null;
            }
            try {
                Entry entry = new Entry(snapshot.getSource(0));
                Response response = entry.response(request, snapshot);
                if (entry.matches(request, response)) {
                    return response;
                }
                Util.closeQuietly(response.body());
                return null;
            } catch (IOException e) {
                Util.closeQuietly(snapshot);
                return null;
            }
        } catch (IOException e2) {
            return null;
        }
    }

    private CacheRequest put(Response response) throws IOException {
        String requestMethod = response.request().method();
        if (HttpMethod.invalidatesCache(response.request().method())) {
            try {
                remove(response.request());
                return null;
            } catch (IOException e) {
                return null;
            }
        } else if (!requestMethod.equals("GET") || OkHeaders.hasVaryAll(response)) {
            return null;
        } else {
            Entry entry = new Entry(response);
            try {
                Editor editor = this.cache.edit(urlToKey(response.request()));
                if (editor == null) {
                    return null;
                }
                entry.writeTo(editor);
                return new CacheRequestImpl(editor);
            } catch (IOException e2) {
                abortQuietly(null);
                return null;
            }
        }
    }

    private void remove(Request request) throws IOException {
        this.cache.remove(urlToKey(request));
    }

    private void update(Response cached, Response network) {
        Entry entry = new Entry(network);
        try {
            Editor editor = ((CacheResponseBody) cached.body()).snapshot.edit();
            if (editor != null) {
                entry.writeTo(editor);
                editor.commit();
            }
        } catch (IOException e) {
            abortQuietly(null);
        }
    }

    private void abortQuietly(Editor editor) {
        if (editor != null) {
            try {
                editor.abort();
            } catch (IOException e) {
            }
        }
    }

    private synchronized void trackResponse(CacheStrategy cacheStrategy) {
        this.requestCount++;
        if (cacheStrategy.networkRequest != null) {
            this.networkCount++;
        } else if (cacheStrategy.cacheResponse != null) {
            this.hitCount++;
        }
    }

    private synchronized void trackConditionalCacheHit() {
        this.hitCount++;
    }

    private static int readInt(BufferedSource source) throws IOException {
        try {
            long result = source.readDecimalLong();
            String line = source.readUtf8LineStrict();
            if (result >= 0 && result <= 2147483647L && line.isEmpty()) {
                return (int) result;
            }
            throw new IOException("expected an int but was \"" + result + line + "\"");
        } catch (NumberFormatException e) {
            throw new IOException(e.getMessage());
        }
    }
}
