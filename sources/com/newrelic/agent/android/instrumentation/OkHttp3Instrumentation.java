package com.newrelic.agent.android.instrumentation;

import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import java.net.HttpURLConnection;
import javax.net.ssl.HttpsURLConnection;

public class OkHttp3Instrumentation {
    private static final AgentLog log = AgentLogManager.getAgentLog();

    @WrapReturn(className = "okhttp3/OkHttpClient", methodDesc = "(Ljava/net/URL;)Ljava/net/HttpURLConnection;", methodName = "open")
    public static HttpURLConnection open(HttpURLConnection connection) {
        if (connection instanceof HttpsURLConnection) {
            return new HttpsURLConnectionExtension((HttpsURLConnection) connection);
        }
        if (connection != null) {
            return new HttpURLConnectionExtension(connection);
        }
        return null;
    }

    @WrapReturn(className = "okhttp3/OkHttpClient", methodDesc = "(Ljava/net/URL;Ljava/net/Proxy)Ljava/net/HttpURLConnection;", methodName = "open")
    public static HttpURLConnection openWithProxy(HttpURLConnection connection) {
        if (connection instanceof HttpsURLConnection) {
            return new HttpsURLConnectionExtension((HttpsURLConnection) connection);
        }
        if (connection != null) {
            return new HttpURLConnectionExtension(connection);
        }
        return null;
    }

    @WrapReturn(className = "okhttp3/OkUrlFactory", methodDesc = "(Ljava/net/URL;)Ljava/net/HttpURLConnection;", methodName = "open")
    public static HttpURLConnection urlFactoryOpen(HttpURLConnection connection) {
        log.debug("OkHttpInstrumentation - wrapping return of call to OkUrlFactory.open...");
        if (connection instanceof HttpsURLConnection) {
            return new HttpsURLConnectionExtension((HttpsURLConnection) connection);
        }
        if (connection != null) {
            return new HttpURLConnectionExtension(connection);
        }
        return null;
    }
}
