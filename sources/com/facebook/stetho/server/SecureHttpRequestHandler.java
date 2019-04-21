package com.facebook.stetho.server;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Credentials;
import android.net.LocalSocket;
import com.facebook.stetho.common.LogUtil;
import com.facebook.stetho.common.Utf8Charset;
import java.io.IOException;
import org.apache.http.HttpConnection;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.entity.StringEntity;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpRequestHandler;

public abstract class SecureHttpRequestHandler implements HttpRequestHandler {
    private static final Class<?> TAG = SecureHttpRequestHandler.class;
    private final Context mContext;

    public abstract void handleSecured(HttpRequest httpRequest, HttpResponse httpResponse, HttpContext httpContext) throws HttpException, IOException;

    public SecureHttpRequestHandler(Context context) {
        this.mContext = context;
    }

    @SuppressLint({"LogMethodNoExceptionInCatch"})
    public final void handle(HttpRequest request, HttpResponse response, HttpContext context) throws HttpException, IOException {
        try {
            ensureSecureRequest(request, context);
            handleSecured(request, response, context);
        } catch (PeerAuthorizationException e) {
            LogUtil.m7447e("Unauthorized request: " + e.getMessage());
            response.setStatusCode(403);
            response.setEntity(new StringEntity(e.getMessage() + "\n", Utf8Charset.NAME));
        }
    }

    private void ensureSecureRequest(HttpRequest request, HttpContext context) throws PeerAuthorizationException, IOException {
        HttpConnection conn = (HttpConnection) context.getAttribute("http.connection");
        if (conn instanceof LocalSocketHttpServerConnection) {
            enforcePermission(this.mContext, ((LocalSocketHttpServerConnection) conn).getSocket());
            return;
        }
        throw new PeerAuthorizationException("Unexpected connection class: " + conn.getClass().getName());
    }

    private static void enforcePermission(Context context, LocalSocket peer) throws IOException, PeerAuthorizationException {
        Credentials credentials = peer.getPeerCredentials();
        int uid = credentials.getUid();
        int pid = credentials.getPid();
        if (LogUtil.isLoggable(2)) {
            LogUtil.m7456v("Got request from uid=%d, pid=%d", Integer.valueOf(uid), Integer.valueOf(pid));
        }
        String requiredPermission = "android.permission.DUMP";
        if (context.checkPermission(requiredPermission, pid, uid) != 0) {
            throw new PeerAuthorizationException("Peer pid=" + pid + ", uid=" + uid + " does not have " + requiredPermission);
        }
    }
}
