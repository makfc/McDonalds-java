package org.acra.util;

import android.util.Base64;
import com.facebook.stetho.common.Utf8Charset;
import com.mcdonalds.sdk.connectors.middleware.response.MWDeliveryStatusResponse;
import com.newrelic.agent.android.instrumentation.HttpInstrumentation;
import com.newrelic.agent.android.instrumentation.TransactionStateUtil;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.GeneralSecurityException;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import org.acra.ACRA;
import org.acra.sender.HttpSender.Method;
import org.acra.sender.HttpSender.Type;

public final class HttpRequest {
    private int connectionTimeOut = 3000;
    private Map<String, String> headers;
    private String login;
    private String password;
    private int socketTimeOut = 3000;

    public final void setLogin(String login) {
        this.login = login;
    }

    public final void setPassword(String password) {
        this.password = password;
    }

    public final void setConnectionTimeOut(int connectionTimeOut) {
        this.connectionTimeOut = connectionTimeOut;
    }

    public final void setSocketTimeOut(int socketTimeOut) {
        this.socketTimeOut = socketTimeOut;
    }

    public final void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public final void send(URL url, Method method, String content, Type type) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) HttpInstrumentation.openConnection(url.openConnection());
        if (httpURLConnection instanceof HttpsURLConnection) {
            try {
                HttpsURLConnection httpsURLConnection = (HttpsURLConnection) httpURLConnection;
                TrustManagerFactory instance = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
                instance.init(ACRA.getConfig().keyStore());
                SSLContext instance2 = SSLContext.getInstance("TLS");
                instance2.init(null, instance.getTrustManagers(), null);
                httpsURLConnection.setSSLSocketFactory(instance2.getSocketFactory());
            } catch (GeneralSecurityException e) {
                ACRA.log.mo23350e(ACRA.LOG_TAG, "Could not configure SSL for ACRA request to " + url, e);
            }
        }
        if (!(this.login == null || this.password == null)) {
            httpURLConnection.setRequestProperty("Authorization", "Basic " + new String(Base64.encode((this.login + ":" + this.password).getBytes(Utf8Charset.NAME), 0), Utf8Charset.NAME));
        }
        httpURLConnection.setConnectTimeout(this.connectionTimeOut);
        httpURLConnection.setReadTimeout(this.socketTimeOut);
        httpURLConnection.setRequestProperty("User-Agent", "Android");
        httpURLConnection.setRequestProperty("Accept", "text/html,application/xml,application/json,application/xhtml+xml,text/html;q=0.9,text/plain;q=0.8,image/png,*/*;q=0.5");
        httpURLConnection.setRequestProperty(TransactionStateUtil.CONTENT_TYPE_HEADER, type.getContentType());
        if (this.headers != null) {
            for (String str : this.headers.keySet()) {
                httpURLConnection.setRequestProperty(str, (String) this.headers.get(str));
            }
        }
        byte[] bytes = content.getBytes(Utf8Charset.NAME);
        httpURLConnection.setRequestMethod(method.name());
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setFixedLengthStreamingMode(bytes.length);
        System.setProperty("http.keepAlive", "false");
        httpURLConnection.connect();
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(httpURLConnection.getOutputStream());
        bufferedOutputStream.write(bytes);
        bufferedOutputStream.flush();
        bufferedOutputStream.close();
        ACRA.log.mo23347d(ACRA.LOG_TAG, "Sending request to " + url);
        int responseCode = httpURLConnection.getResponseCode();
        ACRA.log.mo23347d(ACRA.LOG_TAG, "Request response : " + responseCode + " : " + httpURLConnection.getResponseMessage());
        if (responseCode >= 200 && responseCode < 300) {
            ACRA.log.mo23347d(ACRA.LOG_TAG, "Request received by server");
        } else if (responseCode == 403) {
            ACRA.log.mo23347d(ACRA.LOG_TAG, "Data validation error on server - request will be discarded");
        } else if (responseCode == 409) {
            ACRA.log.mo23347d(ACRA.LOG_TAG, "Server has already received this post - request will be discarded");
        } else if (responseCode < MWDeliveryStatusResponse.STATUS_ORDER_DELIVERED || responseCode >= 600) {
            ACRA.log.mo23353w(ACRA.LOG_TAG, "Could not send ACRA Post - request will be discarded");
        } else {
            throw new IOException("Host returned error code " + responseCode);
        }
        httpURLConnection.disconnect();
    }

    public static String getParamsAsFormString(Map<?, ?> parameters) throws UnsupportedEncodingException {
        StringBuilder stringBuilder = new StringBuilder();
        for (Object next : parameters.keySet()) {
            if (stringBuilder.length() != 0) {
                stringBuilder.append('&');
            }
            Object obj = parameters.get(next);
            if (obj == null) {
                obj = "";
            }
            stringBuilder.append(URLEncoder.encode(next.toString(), Utf8Charset.NAME));
            stringBuilder.append('=');
            stringBuilder.append(URLEncoder.encode(obj.toString(), Utf8Charset.NAME));
        }
        return stringBuilder.toString();
    }
}
