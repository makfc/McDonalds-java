package com.facebook.stetho.server;

import android.annotation.SuppressLint;
import android.net.LocalServerSocket;
import android.net.LocalSocket;
import com.facebook.stetho.common.LogUtil;
import com.facebook.stetho.common.ProcessUtil;
import com.facebook.stetho.common.Util;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.BindException;
import java.net.SocketException;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.Nonnull;
import org.apache.http.ConnectionClosedException;
import org.apache.http.HttpException;
import org.apache.http.HttpServerConnection;
import org.apache.http.impl.DefaultConnectionReuseStrategy;
import org.apache.http.impl.DefaultHttpResponseFactory;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.BasicHttpProcessor;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpRequestHandlerRegistry;
import org.apache.http.protocol.HttpService;
import org.apache.http.protocol.ResponseConnControl;
import org.apache.http.protocol.ResponseContent;
import org.apache.http.protocol.ResponseDate;
import org.apache.http.protocol.ResponseServer;

public class LocalSocketHttpServer {
    private static final int MAX_BIND_RETRIES = 2;
    private static final String SOCKET_NAME_PREFIX = "stetho_";
    private static final String SOCKET_NAME_SUFFIX = "_devtools_remote";
    private static final int TIME_BETWEEN_BIND_RETRIES_MS = 1000;
    private static final String WORKDER_THREAD_NAME_PREFIX = "StethoWorker";
    private static final AtomicInteger sThreadId = new AtomicInteger();
    private final String mAddress;
    private Thread mListenerThread;
    private final RegistryInitializer mRegistryInitializer;
    private LocalServerSocket mServerSocket;
    private boolean mStopped;

    private static class WorkerThread extends Thread {
        private final HttpServerConnection conn;
        private final HttpService httpservice;

        public WorkerThread(HttpService httpservice, HttpServerConnection conn) {
            super(LocalSocketHttpServer.WORKDER_THREAD_NAME_PREFIX + LocalSocketHttpServer.sThreadId.incrementAndGet());
            this.httpservice = httpservice;
            this.conn = conn;
        }

        @SuppressLint({"LogMethodNoExceptionInCatch"})
        public void run() {
            HttpContext context = new BasicHttpContext(null);
            try {
                if (!Thread.interrupted() && this.conn.isOpen()) {
                    this.httpservice.handleRequest(this.conn, context);
                }
                try {
                    this.conn.close();
                } catch (IOException e) {
                }
            } catch (ConnectionClosedException ex) {
                LogUtil.m7460w("Client closed connection: %s", ex);
                try {
                    this.conn.close();
                } catch (IOException e2) {
                }
            } catch (IOException ex2) {
                LogUtil.m7460w("I/O error: %s", ex2);
                try {
                    this.conn.close();
                } catch (IOException e3) {
                }
            } catch (HttpException ex3) {
                LogUtil.m7460w("Unrecoverable HTTP protocol violation: %s", ex3);
                try {
                    this.conn.close();
                } catch (IOException e4) {
                }
            } catch (Throwable th) {
                try {
                    this.conn.close();
                } catch (IOException e5) {
                }
                throw th;
            }
        }
    }

    public LocalSocketHttpServer(RegistryInitializer registryInitializer) {
        this(registryInitializer, null);
    }

    public LocalSocketHttpServer(RegistryInitializer registryInitializer, String address) {
        this.mRegistryInitializer = (RegistryInitializer) Util.throwIfNull(registryInitializer);
        this.mAddress = address;
    }

    /* JADX WARNING: Missing block: B:8:0x0010, code skipped:
            if (r2.mAddress == null) goto L_0x001b;
     */
    /* JADX WARNING: Missing block: B:9:0x0012, code skipped:
            r0 = r2.mAddress;
     */
    /* JADX WARNING: Missing block: B:10:0x0014, code skipped:
            listenOnAddress(r0);
     */
    /* JADX WARNING: Missing block: B:15:0x001b, code skipped:
            r0 = getDefaultAddress();
     */
    /* JADX WARNING: Missing block: B:20:?, code skipped:
            return;
     */
    public void run() throws java.io.IOException {
        /*
        r2 = this;
        monitor-enter(r2);
        r1 = r2.mStopped;	 Catch:{ all -> 0x0018 }
        if (r1 == 0) goto L_0x0007;
    L_0x0005:
        monitor-exit(r2);	 Catch:{ all -> 0x0018 }
    L_0x0006:
        return;
    L_0x0007:
        r1 = java.lang.Thread.currentThread();	 Catch:{ all -> 0x0018 }
        r2.mListenerThread = r1;	 Catch:{ all -> 0x0018 }
        monitor-exit(r2);	 Catch:{ all -> 0x0018 }
        r1 = r2.mAddress;
        if (r1 == 0) goto L_0x001b;
    L_0x0012:
        r0 = r2.mAddress;
    L_0x0014:
        r2.listenOnAddress(r0);
        goto L_0x0006;
    L_0x0018:
        r1 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x0018 }
        throw r1;
    L_0x001b:
        r0 = getDefaultAddress();
        goto L_0x0014;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.stetho.server.LocalSocketHttpServer.run():void");
    }

    private void listenOnAddress(String address) throws IOException {
        this.mServerSocket = bindToSocket(address);
        LogUtil.m7451i("Listening on @" + address);
        HttpParams params = null;
        HttpService service = null;
        while (!Thread.interrupted()) {
            LocalSocketHttpServerConnection connection = new LocalSocketHttpServerConnection();
            try {
                LocalSocket socket = this.mServerSocket.accept();
                if (params == null) {
                    params = createParams();
                }
                if (service == null) {
                    service = createService(params);
                }
                connection.bind(socket, params);
                Thread t = new WorkerThread(service, connection);
                t.setDaemon(true);
                t.start();
            } catch (SocketException se) {
                if (!Thread.interrupted()) {
                    LogUtil.m7461w(se, "I/O error");
                }
            } catch (InterruptedIOException e) {
                return;
            } catch (IOException e2) {
                LogUtil.m7461w(e2, "I/O error initialising connection thread");
                return;
            }
        }
    }

    private static String getDefaultAddress() throws IOException {
        return SOCKET_NAME_PREFIX + ProcessUtil.getProcessName() + SOCKET_NAME_SUFFIX;
    }

    private HttpParams createParams() {
        return new BasicHttpParams().setIntParameter("http.socket.timeout", 5000).setIntParameter("http.socket.buffer-size", 8192).setBooleanParameter("http.connection.stalecheck", false).setBooleanParameter("http.tcp.nodelay", true).setParameter("http.origin-server", "Stetho").setParameter("http.protocol.version", "HTTP/1.1");
    }

    private HttpService createService(HttpParams params) {
        HttpRequestHandlerRegistry registry = this.mRegistryInitializer.getRegistry();
        BasicHttpProcessor httpproc = new BasicHttpProcessor();
        httpproc.addInterceptor(new ResponseDate());
        httpproc.addInterceptor(new ResponseServer());
        httpproc.addInterceptor(new ResponseContent());
        httpproc.addInterceptor(new ResponseConnControl());
        HttpService service = new HttpService(httpproc, new DefaultConnectionReuseStrategy(), new DefaultHttpResponseFactory());
        service.setParams(params);
        service.setHandlerResolver(registry);
        return service;
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* JADX WARNING: Missing block: B:7:0x000b, code skipped:
            r1.mListenerThread.interrupt();
     */
    /* JADX WARNING: Missing block: B:10:0x0012, code skipped:
            if (r1.mServerSocket == null) goto L_?;
     */
    /* JADX WARNING: Missing block: B:11:0x0014, code skipped:
            r1.mServerSocket.close();
     */
    /* JADX WARNING: Missing block: B:21:?, code skipped:
            return;
     */
    /* JADX WARNING: Missing block: B:22:?, code skipped:
            return;
     */
    /* JADX WARNING: Missing block: B:23:?, code skipped:
            return;
     */
    public void stop() {
        /*
        r1 = this;
        monitor-enter(r1);
        r0 = 1;
        r1.mStopped = r0;	 Catch:{ all -> 0x001c }
        r0 = r1.mListenerThread;	 Catch:{ all -> 0x001c }
        if (r0 != 0) goto L_0x000a;
    L_0x0008:
        monitor-exit(r1);	 Catch:{ all -> 0x001c }
    L_0x0009:
        return;
    L_0x000a:
        monitor-exit(r1);	 Catch:{ all -> 0x001c }
        r0 = r1.mListenerThread;
        r0.interrupt();
        r0 = r1.mServerSocket;	 Catch:{ IOException -> 0x001a }
        if (r0 == 0) goto L_0x0009;
    L_0x0014:
        r0 = r1.mServerSocket;	 Catch:{ IOException -> 0x001a }
        r0.close();	 Catch:{ IOException -> 0x001a }
        goto L_0x0009;
    L_0x001a:
        r0 = move-exception;
        goto L_0x0009;
    L_0x001c:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x001c }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.stetho.server.LocalSocketHttpServer.stop():void");
    }

    @Nonnull
    private static LocalServerSocket bindToSocket(String address) throws IOException {
        int retries = 2;
        IOException firstException = null;
        while (true) {
            int retries2 = retries;
            try {
                if (LogUtil.isLoggable(3)) {
                    LogUtil.m7443d("Trying to bind to @" + address);
                }
                return new LocalServerSocket(address);
            } catch (BindException be) {
                LogUtil.m7461w(be, "Binding error, sleep 1000 ms...");
                if (firstException == null) {
                    firstException = be;
                }
                Util.sleepUninterruptibly(1000);
                retries = retries2 - 1;
                if (retries2 <= 0) {
                    throw firstException;
                }
            }
        }
    }
}
