package p041io.fabric.sdk.android.services.network;

import com.facebook.stetho.common.Utf8Charset;
import com.mcdonalds.sdk.connectors.middleware.response.MWDeliveryStatusResponse;
import com.newrelic.agent.android.instrumentation.HttpInstrumentation;
import com.newrelic.agent.android.instrumentation.TransactionStateUtil;
import com.newrelic.agent.android.util.SafeJsonPrimitive;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.security.PrivilegedAction;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Callable;
import java.util.zip.GZIPInputStream;

/* renamed from: io.fabric.sdk.android.services.network.HttpRequest */
public class HttpRequest {
    private static ConnectionFactory CONNECTION_FACTORY = ConnectionFactory.DEFAULT;
    private static final String[] EMPTY_STRINGS = new String[0];
    private int bufferSize = 8192;
    private HttpURLConnection connection = null;
    private String httpProxyHost;
    private int httpProxyPort;
    private boolean ignoreCloseExceptions = true;
    private boolean multipart;
    private RequestOutputStream output;
    private final String requestMethod;
    private boolean uncompress = false;
    public final URL url;

    /* renamed from: io.fabric.sdk.android.services.network.HttpRequest$1 */
    static class C46061 implements PrivilegedAction<String> {
        final /* synthetic */ String val$name;
        final /* synthetic */ String val$value;

        public String run() {
            return System.setProperty(this.val$name, this.val$value);
        }
    }

    /* renamed from: io.fabric.sdk.android.services.network.HttpRequest$2 */
    static class C46072 implements PrivilegedAction<String> {
        final /* synthetic */ String val$name;

        public String run() {
            return System.clearProperty(this.val$name);
        }
    }

    /* renamed from: io.fabric.sdk.android.services.network.HttpRequest$Operation */
    protected static abstract class Operation<V> implements Callable<V> {
        public abstract void done() throws IOException;

        public abstract V run() throws HttpRequestException, IOException;

        protected Operation() {
        }

        public V call() throws HttpRequestException {
            boolean thrown;
            try {
                Object run = run();
                try {
                    done();
                } catch (IOException e) {
                    if (!false) {
                        throw new HttpRequestException(e);
                    }
                }
                return run;
            } catch (HttpRequestException e2) {
                thrown = true;
                throw e2;
            } catch (IOException e3) {
                thrown = true;
                throw new HttpRequestException(e3);
            } catch (Throwable th) {
                try {
                    done();
                } catch (IOException e32) {
                    if (!thrown) {
                        throw new HttpRequestException(e32);
                    }
                }
            }
        }
    }

    /* renamed from: io.fabric.sdk.android.services.network.HttpRequest$CloseOperation */
    protected static abstract class CloseOperation<V> extends Operation<V> {
        private final Closeable closeable;
        private final boolean ignoreCloseExceptions;

        protected CloseOperation(Closeable closeable, boolean ignoreCloseExceptions) {
            this.closeable = closeable;
            this.ignoreCloseExceptions = ignoreCloseExceptions;
        }

        /* Access modifiers changed, original: protected */
        public void done() throws IOException {
            if (this.closeable instanceof Flushable) {
                ((Flushable) this.closeable).flush();
            }
            if (this.ignoreCloseExceptions) {
                try {
                    this.closeable.close();
                    return;
                } catch (IOException e) {
                    return;
                }
            }
            this.closeable.close();
        }
    }

    /* renamed from: io.fabric.sdk.android.services.network.HttpRequest$3 */
    class C46083 extends CloseOperation<HttpRequest> {
        final /* synthetic */ HttpRequest this$0;
        final /* synthetic */ OutputStream val$output;

        /* Access modifiers changed, original: protected */
        public HttpRequest run() throws HttpRequestException, IOException {
            return this.this$0.receive(this.val$output);
        }
    }

    /* renamed from: io.fabric.sdk.android.services.network.HttpRequest$4 */
    class C46094 extends CloseOperation<HttpRequest> {
        final /* synthetic */ HttpRequest this$0;
        final /* synthetic */ Appendable val$appendable;
        final /* synthetic */ BufferedReader val$reader;

        public HttpRequest run() throws IOException {
            CharBuffer buffer = CharBuffer.allocate(this.this$0.bufferSize);
            while (true) {
                int read = this.val$reader.read(buffer);
                if (read == -1) {
                    return this.this$0;
                }
                buffer.rewind();
                this.val$appendable.append(buffer, 0, read);
                buffer.rewind();
            }
        }
    }

    /* renamed from: io.fabric.sdk.android.services.network.HttpRequest$5 */
    class C46105 extends CloseOperation<HttpRequest> {
        final /* synthetic */ HttpRequest this$0;
        final /* synthetic */ BufferedReader val$reader;
        final /* synthetic */ Writer val$writer;

        public HttpRequest run() throws IOException {
            return this.this$0.copy(this.val$reader, this.val$writer);
        }
    }

    /* renamed from: io.fabric.sdk.android.services.network.HttpRequest$FlushOperation */
    protected static abstract class FlushOperation<V> extends Operation<V> {
        private final Flushable flushable;

        /* Access modifiers changed, original: protected */
        public void done() throws IOException {
            this.flushable.flush();
        }
    }

    /* renamed from: io.fabric.sdk.android.services.network.HttpRequest$8 */
    class C46138 extends FlushOperation<HttpRequest> {
        final /* synthetic */ HttpRequest this$0;
        final /* synthetic */ Reader val$input;
        final /* synthetic */ Writer val$writer;

        /* Access modifiers changed, original: protected */
        public HttpRequest run() throws IOException {
            return this.this$0.copy(this.val$input, this.val$writer);
        }
    }

    /* renamed from: io.fabric.sdk.android.services.network.HttpRequest$Base64 */
    public static class Base64 {
        private static final byte[] _STANDARD_ALPHABET = new byte[]{(byte) 65, (byte) 66, (byte) 67, (byte) 68, (byte) 69, (byte) 70, (byte) 71, (byte) 72, (byte) 73, (byte) 74, (byte) 75, (byte) 76, (byte) 77, (byte) 78, (byte) 79, (byte) 80, (byte) 81, (byte) 82, (byte) 83, (byte) 84, (byte) 85, (byte) 86, (byte) 87, (byte) 88, (byte) 89, (byte) 90, (byte) 97, (byte) 98, (byte) 99, (byte) 100, (byte) 101, (byte) 102, (byte) 103, (byte) 104, (byte) 105, (byte) 106, (byte) 107, (byte) 108, (byte) 109, (byte) 110, (byte) 111, (byte) 112, (byte) 113, (byte) 114, (byte) 115, (byte) 116, (byte) 117, (byte) 118, (byte) 119, (byte) 120, (byte) 121, (byte) 122, (byte) 48, (byte) 49, (byte) 50, (byte) 51, (byte) 52, (byte) 53, (byte) 54, (byte) 55, (byte) 56, (byte) 57, (byte) 43, (byte) 47};

        private Base64() {
        }
    }

    /* renamed from: io.fabric.sdk.android.services.network.HttpRequest$ConnectionFactory */
    public interface ConnectionFactory {
        public static final ConnectionFactory DEFAULT = new C46141();

        /* renamed from: io.fabric.sdk.android.services.network.HttpRequest$ConnectionFactory$1 */
        static class C46141 implements ConnectionFactory {
            C46141() {
            }

            public HttpURLConnection create(URL url) throws IOException {
                return (HttpURLConnection) HttpInstrumentation.openConnection(url.openConnection());
            }

            public HttpURLConnection create(URL url, Proxy proxy) throws IOException {
                return (HttpURLConnection) HttpInstrumentation.openConnectionWithProxy(url.openConnection(proxy));
            }
        }

        HttpURLConnection create(URL url) throws IOException;

        HttpURLConnection create(URL url, Proxy proxy) throws IOException;
    }

    /* renamed from: io.fabric.sdk.android.services.network.HttpRequest$HttpRequestException */
    public static class HttpRequestException extends RuntimeException {
        protected HttpRequestException(IOException cause) {
            super(cause);
        }

        public IOException getCause() {
            return (IOException) super.getCause();
        }
    }

    /* renamed from: io.fabric.sdk.android.services.network.HttpRequest$RequestOutputStream */
    public static class RequestOutputStream extends BufferedOutputStream {
        private final CharsetEncoder encoder;

        public RequestOutputStream(OutputStream stream, String charset, int bufferSize) {
            super(stream, bufferSize);
            this.encoder = Charset.forName(HttpRequest.getValidCharset(charset)).newEncoder();
        }

        public RequestOutputStream write(String value) throws IOException {
            ByteBuffer bytes = this.encoder.encode(CharBuffer.wrap(value));
            super.write(bytes.array(), 0, bytes.limit());
            return this;
        }
    }

    private static String getValidCharset(String charset) {
        return (charset == null || charset.length() <= 0) ? Utf8Charset.NAME : charset;
    }

    private static StringBuilder addPathSeparator(String baseUrl, StringBuilder result) {
        if (baseUrl.indexOf(58) + 2 == baseUrl.lastIndexOf(47)) {
            result.append('/');
        }
        return result;
    }

    private static StringBuilder addParamPrefix(String baseUrl, StringBuilder result) {
        int queryStart = baseUrl.indexOf(63);
        int lastChar = result.length() - 1;
        if (queryStart == -1) {
            result.append('?');
        } else if (queryStart < lastChar && baseUrl.charAt(lastChar) != '&') {
            result.append('&');
        }
        return result;
    }

    public static String encode(CharSequence url) throws HttpRequestException {
        try {
            URL parsed = new URL(url.toString());
            String host = parsed.getHost();
            int port = parsed.getPort();
            if (port != -1) {
                host = host + ':' + Integer.toString(port);
            }
            try {
                String encoded = new URI(parsed.getProtocol(), host, parsed.getPath(), parsed.getQuery(), parsed.getRef()).toASCIIString();
                int paramsStart = encoded.indexOf(63);
                if (paramsStart <= 0 || paramsStart + 1 >= encoded.length()) {
                    return encoded;
                }
                return encoded.substring(0, paramsStart + 1) + encoded.substring(paramsStart + 1).replace("+", "%2B").replace("#", "%23");
            } catch (URISyntaxException e) {
                IOException io = new IOException("Parsing URI failed");
                io.initCause(e);
                throw new HttpRequestException(io);
            }
        } catch (IOException e2) {
            throw new HttpRequestException(e2);
        }
    }

    public static String append(CharSequence url, Map<?, ?> params) {
        String baseUrl = url.toString();
        if (params == null || params.isEmpty()) {
            return baseUrl;
        }
        StringBuilder result = new StringBuilder(baseUrl);
        HttpRequest.addPathSeparator(baseUrl, result);
        HttpRequest.addParamPrefix(baseUrl, result);
        Iterator<?> iterator = params.entrySet().iterator();
        Entry<?, ?> entry = (Entry) iterator.next();
        result.append(entry.getKey().toString());
        result.append('=');
        Object value = entry.getValue();
        if (value != null) {
            result.append(value);
        }
        while (iterator.hasNext()) {
            result.append('&');
            entry = (Entry) iterator.next();
            result.append(entry.getKey().toString());
            result.append('=');
            value = entry.getValue();
            if (value != null) {
                result.append(value);
            }
        }
        return result.toString();
    }

    public static HttpRequest get(CharSequence url) throws HttpRequestException {
        return new HttpRequest(url, "GET");
    }

    public static HttpRequest get(CharSequence baseUrl, Map<?, ?> params, boolean encode) {
        String url = HttpRequest.append(baseUrl, params);
        if (encode) {
            url = HttpRequest.encode(url);
        }
        return HttpRequest.get(url);
    }

    public static HttpRequest post(CharSequence url) throws HttpRequestException {
        return new HttpRequest(url, "POST");
    }

    public static HttpRequest post(CharSequence baseUrl, Map<?, ?> params, boolean encode) {
        String url = HttpRequest.append(baseUrl, params);
        if (encode) {
            url = HttpRequest.encode(url);
        }
        return HttpRequest.post(url);
    }

    public static HttpRequest put(CharSequence url) throws HttpRequestException {
        return new HttpRequest(url, "PUT");
    }

    public static HttpRequest delete(CharSequence url) throws HttpRequestException {
        return new HttpRequest(url, "DELETE");
    }

    public HttpRequest(CharSequence url, String method) throws HttpRequestException {
        try {
            this.url = new URL(url.toString());
            this.requestMethod = method;
        } catch (MalformedURLException e) {
            throw new HttpRequestException(e);
        }
    }

    private Proxy createProxy() {
        return new Proxy(Type.HTTP, new InetSocketAddress(this.httpProxyHost, this.httpProxyPort));
    }

    private HttpURLConnection createConnection() {
        try {
            HttpURLConnection connection;
            if (this.httpProxyHost != null) {
                connection = CONNECTION_FACTORY.create(this.url, createProxy());
            } else {
                connection = CONNECTION_FACTORY.create(this.url);
            }
            connection.setRequestMethod(this.requestMethod);
            return connection;
        } catch (IOException e) {
            throw new HttpRequestException(e);
        }
    }

    public String toString() {
        return method() + SafeJsonPrimitive.NULL_CHAR + url();
    }

    public HttpURLConnection getConnection() {
        if (this.connection == null) {
            this.connection = createConnection();
        }
        return this.connection;
    }

    public int code() throws HttpRequestException {
        try {
            closeOutput();
            return getConnection().getResponseCode();
        } catch (IOException e) {
            throw new HttpRequestException(e);
        }
    }

    /* Access modifiers changed, original: protected */
    public ByteArrayOutputStream byteStream() {
        int size = contentLength();
        if (size > 0) {
            return new ByteArrayOutputStream(size);
        }
        return new ByteArrayOutputStream();
    }

    public String body(String charset) throws HttpRequestException {
        OutputStream output = byteStream();
        try {
            copy(buffer(), output);
            return output.toString(HttpRequest.getValidCharset(charset));
        } catch (IOException e) {
            throw new HttpRequestException(e);
        }
    }

    public String body() throws HttpRequestException {
        return body(charset());
    }

    public BufferedInputStream buffer() throws HttpRequestException {
        return new BufferedInputStream(stream(), this.bufferSize);
    }

    public InputStream stream() throws HttpRequestException {
        InputStream stream;
        if (code() < MWDeliveryStatusResponse.STATUS_ORDER_DELIVERED) {
            try {
                stream = getConnection().getInputStream();
            } catch (IOException e) {
                throw new HttpRequestException(e);
            }
        }
        stream = getConnection().getErrorStream();
        if (stream == null) {
            try {
                stream = getConnection().getInputStream();
            } catch (IOException e2) {
                throw new HttpRequestException(e2);
            }
        }
        if (!this.uncompress || !"gzip".equals(contentEncoding())) {
            return stream;
        }
        try {
            return new GZIPInputStream(stream);
        } catch (IOException e22) {
            throw new HttpRequestException(e22);
        }
    }

    public HttpRequest receive(OutputStream output) throws HttpRequestException {
        try {
            return copy(buffer(), output);
        } catch (IOException e) {
            throw new HttpRequestException(e);
        }
    }

    public HttpRequest connectTimeout(int timeout) {
        getConnection().setConnectTimeout(timeout);
        return this;
    }

    public HttpRequest header(String name, String value) {
        getConnection().setRequestProperty(name, value);
        return this;
    }

    public HttpRequest header(Entry<String, String> header) {
        return header((String) header.getKey(), (String) header.getValue());
    }

    public String header(String name) throws HttpRequestException {
        closeOutputQuietly();
        return getConnection().getHeaderField(name);
    }

    public int intHeader(String name) throws HttpRequestException {
        return intHeader(name, -1);
    }

    public int intHeader(String name, int defaultValue) throws HttpRequestException {
        closeOutputQuietly();
        return getConnection().getHeaderFieldInt(name, defaultValue);
    }

    public String parameter(String headerName, String paramName) {
        return getParam(header(headerName), paramName);
    }

    /* Access modifiers changed, original: protected */
    public String getParam(String value, String paramName) {
        if (value == null || value.length() == 0) {
            return null;
        }
        int length = value.length();
        int start = value.indexOf(59) + 1;
        if (start == 0 || start == length) {
            return null;
        }
        int end = value.indexOf(59, start);
        if (end == -1) {
            end = length;
        }
        while (start < end) {
            int nameEnd = value.indexOf(61, start);
            if (nameEnd != -1 && nameEnd < end && paramName.equals(value.substring(start, nameEnd).trim())) {
                String paramValue = value.substring(nameEnd + 1, end).trim();
                int valueLength = paramValue.length();
                if (valueLength != 0) {
                    if (valueLength > 2 && '\"' == paramValue.charAt(0) && '\"' == paramValue.charAt(valueLength - 1)) {
                        return paramValue.substring(1, valueLength - 1);
                    }
                    return paramValue;
                }
            }
            start = end + 1;
            end = value.indexOf(59, start);
            if (end == -1) {
                end = length;
            }
        }
        return null;
    }

    public String charset() {
        return parameter(TransactionStateUtil.CONTENT_TYPE_HEADER, "charset");
    }

    public HttpRequest useCaches(boolean useCaches) {
        getConnection().setUseCaches(useCaches);
        return this;
    }

    public String contentEncoding() {
        return header("Content-Encoding");
    }

    public HttpRequest contentType(String contentType) {
        return contentType(contentType, null);
    }

    public HttpRequest contentType(String contentType, String charset) {
        if (charset == null || charset.length() <= 0) {
            return header(TransactionStateUtil.CONTENT_TYPE_HEADER, contentType);
        }
        String separator = "; charset=";
        return header(TransactionStateUtil.CONTENT_TYPE_HEADER, contentType + "; charset=" + charset);
    }

    public int contentLength() {
        return intHeader(TransactionStateUtil.CONTENT_LENGTH_HEADER);
    }

    /* Access modifiers changed, original: protected */
    public HttpRequest copy(InputStream input, OutputStream output) throws IOException {
        final InputStream inputStream = input;
        final OutputStream outputStream = output;
        return (HttpRequest) new CloseOperation<HttpRequest>(input, this.ignoreCloseExceptions) {
            public HttpRequest run() throws IOException {
                byte[] buffer = new byte[HttpRequest.this.bufferSize];
                while (true) {
                    int read = inputStream.read(buffer);
                    if (read == -1) {
                        return HttpRequest.this;
                    }
                    outputStream.write(buffer, 0, read);
                }
            }
        }.call();
    }

    /* Access modifiers changed, original: protected */
    public HttpRequest copy(Reader input, Writer output) throws IOException {
        final Reader reader = input;
        final Writer writer = output;
        return (HttpRequest) new CloseOperation<HttpRequest>(input, this.ignoreCloseExceptions) {
            public HttpRequest run() throws IOException {
                char[] buffer = new char[HttpRequest.this.bufferSize];
                while (true) {
                    int read = reader.read(buffer);
                    if (read == -1) {
                        return HttpRequest.this;
                    }
                    writer.write(buffer, 0, read);
                }
            }
        }.call();
    }

    /* Access modifiers changed, original: protected */
    public HttpRequest closeOutput() throws IOException {
        if (this.output != null) {
            if (this.multipart) {
                this.output.write("\r\n--00content0boundary00--\r\n");
            }
            if (this.ignoreCloseExceptions) {
                try {
                    this.output.close();
                } catch (IOException e) {
                }
            } else {
                this.output.close();
            }
            this.output = null;
        }
        return this;
    }

    /* Access modifiers changed, original: protected */
    public HttpRequest closeOutputQuietly() throws HttpRequestException {
        try {
            return closeOutput();
        } catch (IOException e) {
            throw new HttpRequestException(e);
        }
    }

    /* Access modifiers changed, original: protected */
    public HttpRequest openOutput() throws IOException {
        if (this.output == null) {
            getConnection().setDoOutput(true);
            this.output = new RequestOutputStream(getConnection().getOutputStream(), getParam(getConnection().getRequestProperty(TransactionStateUtil.CONTENT_TYPE_HEADER), "charset"), this.bufferSize);
        }
        return this;
    }

    /* Access modifiers changed, original: protected */
    public HttpRequest startPart() throws IOException {
        if (this.multipart) {
            this.output.write("\r\n--00content0boundary00\r\n");
        } else {
            this.multipart = true;
            contentType("multipart/form-data; boundary=00content0boundary00").openOutput();
            this.output.write("--00content0boundary00\r\n");
        }
        return this;
    }

    /* Access modifiers changed, original: protected */
    public HttpRequest writePartHeader(String name, String filename, String contentType) throws IOException {
        StringBuilder partBuffer = new StringBuilder();
        partBuffer.append("form-data; name=\"").append(name);
        if (filename != null) {
            partBuffer.append("\"; filename=\"").append(filename);
        }
        partBuffer.append('\"');
        partHeader("Content-Disposition", partBuffer.toString());
        if (contentType != null) {
            partHeader(TransactionStateUtil.CONTENT_TYPE_HEADER, contentType);
        }
        return send("\r\n");
    }

    public HttpRequest part(String name, String part) {
        return part(name, null, part);
    }

    public HttpRequest part(String name, String filename, String part) throws HttpRequestException {
        return part(name, filename, null, part);
    }

    public HttpRequest part(String name, String filename, String contentType, String part) throws HttpRequestException {
        try {
            startPart();
            writePartHeader(name, filename, contentType);
            this.output.write(part);
            return this;
        } catch (IOException e) {
            throw new HttpRequestException(e);
        }
    }

    public HttpRequest part(String name, Number part) throws HttpRequestException {
        return part(name, null, part);
    }

    public HttpRequest part(String name, String filename, Number part) throws HttpRequestException {
        return part(name, filename, part != null ? part.toString() : null);
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x001f A:{SYNTHETIC, Splitter:B:15:0x001f} */
    public p041io.fabric.sdk.android.services.network.HttpRequest part(java.lang.String r6, java.lang.String r7, java.lang.String r8, java.io.File r9) throws p041io.fabric.sdk.android.services.network.HttpRequest.HttpRequestException {
        /*
        r5 = this;
        r1 = 0;
        r2 = new java.io.BufferedInputStream;	 Catch:{ IOException -> 0x0015 }
        r3 = new java.io.FileInputStream;	 Catch:{ IOException -> 0x0015 }
        r3.<init>(r9);	 Catch:{ IOException -> 0x0015 }
        r2.<init>(r3);	 Catch:{ IOException -> 0x0015 }
        r3 = r5.part(r6, r7, r8, r2);	 Catch:{ IOException -> 0x002a, all -> 0x0027 }
        if (r2 == 0) goto L_0x0014;
    L_0x0011:
        r2.close();	 Catch:{ IOException -> 0x0023 }
    L_0x0014:
        return r3;
    L_0x0015:
        r0 = move-exception;
    L_0x0016:
        r3 = new io.fabric.sdk.android.services.network.HttpRequest$HttpRequestException;	 Catch:{ all -> 0x001c }
        r3.<init>(r0);	 Catch:{ all -> 0x001c }
        throw r3;	 Catch:{ all -> 0x001c }
    L_0x001c:
        r3 = move-exception;
    L_0x001d:
        if (r1 == 0) goto L_0x0022;
    L_0x001f:
        r1.close();	 Catch:{ IOException -> 0x0025 }
    L_0x0022:
        throw r3;
    L_0x0023:
        r4 = move-exception;
        goto L_0x0014;
    L_0x0025:
        r4 = move-exception;
        goto L_0x0022;
    L_0x0027:
        r3 = move-exception;
        r1 = r2;
        goto L_0x001d;
    L_0x002a:
        r0 = move-exception;
        r1 = r2;
        goto L_0x0016;
        */
        throw new UnsupportedOperationException("Method not decompiled: p041io.fabric.sdk.android.services.network.HttpRequest.part(java.lang.String, java.lang.String, java.lang.String, java.io.File):io.fabric.sdk.android.services.network.HttpRequest");
    }

    public HttpRequest part(String name, String filename, String contentType, InputStream part) throws HttpRequestException {
        try {
            startPart();
            writePartHeader(name, filename, contentType);
            copy(part, this.output);
            return this;
        } catch (IOException e) {
            throw new HttpRequestException(e);
        }
    }

    public HttpRequest partHeader(String name, String value) throws HttpRequestException {
        return send(name).send(": ").send(value).send("\r\n");
    }

    public HttpRequest send(CharSequence value) throws HttpRequestException {
        try {
            openOutput();
            this.output.write(value.toString());
            return this;
        } catch (IOException e) {
            throw new HttpRequestException(e);
        }
    }

    public URL url() {
        return getConnection().getURL();
    }

    public String method() {
        return getConnection().getRequestMethod();
    }
}
