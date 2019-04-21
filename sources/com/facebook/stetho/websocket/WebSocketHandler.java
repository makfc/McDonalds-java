package com.facebook.stetho.websocket;

import android.content.Context;
import android.util.Base64;
import com.facebook.stetho.common.Utf8Charset;
import com.facebook.stetho.server.LocalSocketHttpServerConnection;
import com.facebook.stetho.server.SecureHttpRequestHandler;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.annotation.Nullable;
import org.apache.http.ConnectionClosedException;
import org.apache.http.Header;
import org.apache.http.HttpConnection;
import org.apache.http.HttpException;
import org.apache.http.HttpMessage;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.HttpServerConnection;
import org.apache.http.entity.StringEntity;
import org.apache.http.protocol.HttpContext;

public class WebSocketHandler extends SecureHttpRequestHandler {
    private static final String HEADER_CONNECTION = "Connection";
    private static final String HEADER_CONNECTION_UPGRADE = "Upgrade";
    private static final String HEADER_SEC_WEBSOCKET_ACCEPT = "Sec-WebSocket-Accept";
    private static final String HEADER_SEC_WEBSOCKET_KEY = "Sec-WebSocket-Key";
    private static final String HEADER_SEC_WEBSOCKET_PROTOCOL = "Sec-WebSocket-Protocol";
    private static final String HEADER_SEC_WEBSOCKET_VERSION = "Sec-WebSocket-Version";
    private static final String HEADER_SEC_WEBSOCKET_VERSION_13 = "13";
    private static final String HEADER_UPGRADE = "Upgrade";
    private static final String HEADER_UPGRADE_WEBSOCKET = "websocket";
    private static final String SERVER_KEY_GUID = "258EAFA5-E914-47DA-95CA-C5AB0DC85B11";
    private final SimpleEndpoint mEndpoint;

    private static class RawSocketUpgradeHelper {
        private final HttpServerConnection mConn;
        private final InputStream mIn;
        private final OutputStream mOut;

        public static RawSocketUpgradeHelper fromApacheContext(HttpContext context) throws IOException {
            LocalSocketHttpServerConnection conn = (LocalSocketHttpServerConnection) context.getAttribute("http.connection");
            return new RawSocketUpgradeHelper(conn, joinInputStreams(new ByteArrayInputStream(conn.clearInputBuffer()), conn.getSocket().getInputStream()), conn.getSocket().getOutputStream());
        }

        private RawSocketUpgradeHelper(HttpServerConnection conn, InputStream in, OutputStream out) {
            this.mConn = conn;
            this.mIn = in;
            this.mOut = out;
        }

        private static InputStream joinInputStreams(InputStream... streams) throws IOException {
            return new CompositeInputStream(streams);
        }

        public HttpServerConnection getServerConnection() {
            return this.mConn;
        }

        public InputStream getInputStream() {
            return this.mIn;
        }

        public OutputStream getOutputStream() {
            return this.mOut;
        }
    }

    public WebSocketHandler(Context context, SimpleEndpoint endpoint) {
        super(context);
        this.mEndpoint = endpoint;
    }

    public void handleSecured(HttpRequest request, HttpResponse response, HttpContext context) throws IOException, HttpException {
        if (isSupportableUpgradeRequest(request)) {
            HttpConnection conn = (HttpConnection) context.getAttribute("http.connection");
            try {
                doUpgrade(request, response, context);
                throw new ConnectionClosedException("EOF");
            } finally {
                try {
                    conn.close();
                } catch (IOException e) {
                }
            }
        } else {
            response.setStatusCode(501);
            response.setReasonPhrase("Not Implemented");
            response.setEntity(new StringEntity("Not a supported WebSocket upgrade request\n"));
        }
    }

    private static boolean isSupportableUpgradeRequest(HttpRequest request) {
        return HEADER_UPGRADE_WEBSOCKET.equalsIgnoreCase(getFirstHeaderValue(request, "Upgrade")) && "Upgrade".equals(getFirstHeaderValue(request, HEADER_CONNECTION)) && "13".equals(getFirstHeaderValue(request, HEADER_SEC_WEBSOCKET_VERSION));
    }

    private void doUpgrade(HttpRequest request, HttpResponse response, HttpContext context) throws IOException, HttpException {
        RawSocketUpgradeHelper rawSocketHelper = RawSocketUpgradeHelper.fromApacheContext(context);
        response.setStatusCode(101);
        response.setReasonPhrase("Switching Protocols");
        response.addHeader("Upgrade", HEADER_UPGRADE_WEBSOCKET);
        response.addHeader(HEADER_CONNECTION, "Upgrade");
        String clientKey = getFirstHeaderValue(request, HEADER_SEC_WEBSOCKET_KEY);
        if (clientKey != null) {
            response.addHeader(HEADER_SEC_WEBSOCKET_ACCEPT, generateServerKey(clientKey));
        }
        forceSendResponse(rawSocketHelper.getServerConnection(), response);
        new WebSocketSession(rawSocketHelper.getInputStream(), rawSocketHelper.getOutputStream(), this.mEndpoint).handle();
    }

    private static String generateServerKey(String clientKey) {
        try {
            String serverKey = clientKey + SERVER_KEY_GUID;
            MessageDigest sha1 = MessageDigest.getInstance("SHA-1");
            sha1.update(Utf8Charset.encodeUTF8(serverKey));
            return Base64.encodeToString(sha1.digest(), 2);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @Nullable
    private static String getFirstHeaderValue(HttpMessage message, String headerName) {
        Header header = message.getFirstHeader(headerName);
        return header != null ? header.getValue() : null;
    }

    private void forceSendResponse(HttpServerConnection conn, HttpResponse response) throws IOException, HttpException {
        conn.sendResponseHeader(response);
        conn.flush();
    }
}
