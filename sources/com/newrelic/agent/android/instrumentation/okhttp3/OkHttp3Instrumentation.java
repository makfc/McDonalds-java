package com.newrelic.agent.android.instrumentation.okhttp3;

import com.newrelic.agent.android.instrumentation.HttpURLConnectionExtension;
import com.newrelic.agent.android.instrumentation.HttpsURLConnectionExtension;
import com.newrelic.agent.android.instrumentation.ReplaceCallSite;
import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.OkUrlFactory;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.Internal;
import okhttp3.internal.http.StreamAllocation;

public class OkHttp3Instrumentation {
    private static final AgentLog log = AgentLogManager.getAgentLog();

    public static class OkHttp32 {
        @ReplaceCallSite
        public static void callEnqueue(Internal internal, Call call, Callback responseCallback, boolean forWebSocket) {
            try {
                if (call instanceof CallExtension) {
                    call = ((CallExtension) call).getImpl();
                }
                Method callEnqueue = Internal.class.getMethod("callEnqueue", new Class[]{Call.class, Callback.class, Boolean.TYPE});
                if (callEnqueue != null) {
                    callEnqueue.invoke(internal, new Object[]{call, responseCallback, Boolean.valueOf(forWebSocket)});
                    return;
                }
                OkHttp3Instrumentation.logReflectionError("callEnqueue(Lokhttp3/Call;Lokhttp3/Callback;Z)V");
            } catch (Exception e) {
                OkHttp3Instrumentation.log.error(e.getMessage());
            }
        }

        @ReplaceCallSite
        public static StreamAllocation callEngineGetStreamAllocation(Internal internal, Call call) {
            try {
                if (call instanceof CallExtension) {
                    call = ((CallExtension) call).getImpl();
                }
                Method callEngineGetStreamAllocation = Internal.class.getMethod("callEngineGetStreamAllocation", new Class[]{Call.class});
                if (callEngineGetStreamAllocation != null) {
                    return (StreamAllocation) callEngineGetStreamAllocation.invoke(internal, new Object[]{call});
                }
                OkHttp3Instrumentation.logReflectionError("callEngineGetStreamAllocation(Lokhttp3/Call;)Lokhttp3/internal/http/StreamAllocation;");
                return null;
            } catch (Exception e) {
                OkHttp3Instrumentation.log.error(e.getMessage());
                return null;
            }
        }
    }

    public static class OkHttp34 {
        @ReplaceCallSite
        public static void setCallWebSocket(Internal internal, Call call) {
            try {
                if (call instanceof CallExtension) {
                    call = ((CallExtension) call).getImpl();
                }
                Method setCallWebSocket = Internal.class.getMethod("setCallWebSocket", new Class[]{Call.class});
                if (setCallWebSocket != null) {
                    setCallWebSocket.invoke(internal, new Object[]{call});
                    return;
                }
                OkHttp3Instrumentation.logReflectionError("setCallWebSocket(Lokhttp3/Call;)V");
            } catch (Exception e) {
                OkHttp3Instrumentation.log.error(e.getMessage());
            }
        }

        @ReplaceCallSite
        public static okhttp3.internal.connection.StreamAllocation callEngineGetStreamAllocation(Internal internal, Call call) {
            try {
                if (call instanceof CallExtension) {
                    call = ((CallExtension) call).getImpl();
                }
                Method callEngineGetStreamAllocation = Internal.class.getMethod("callEngineGetStreamAllocation", new Class[]{Call.class});
                if (callEngineGetStreamAllocation != null) {
                    return (okhttp3.internal.connection.StreamAllocation) callEngineGetStreamAllocation.invoke(internal, new Object[]{call});
                }
                OkHttp3Instrumentation.logReflectionError("callEngineGetStreamAllocation(Lokhttp3/Call;)Lokhttp3/internal/connection/StreamAllocation;");
                return null;
            } catch (Exception e) {
                OkHttp3Instrumentation.log.error(e.getMessage());
                return null;
            }
        }
    }

    public static class OkHttp35 {
        @ReplaceCallSite
        public static Call newWebSocketCall(Internal internal, OkHttpClient client, Request request) {
            try {
                Method newWebSocketCall = Internal.class.getMethod("newWebSocketCall", new Class[]{OkHttpClient.class, Request.class});
                if (newWebSocketCall != null) {
                    return new CallExtension(client, request, (Call) newWebSocketCall.invoke(internal, new Object[]{client, request}));
                }
                OkHttp3Instrumentation.logReflectionError("newWebSocketCall(Lokhttp3/OkHttpClient;Lokhttp3/Request;)Lokhttp3/Call;");
                return null;
            } catch (Exception e) {
                OkHttp3Instrumentation.log.error(e.getMessage());
                return null;
            }
        }
    }

    private OkHttp3Instrumentation() {
    }

    @ReplaceCallSite
    public static Request build(Builder builder) {
        return new RequestBuilderExtension(builder).build();
    }

    @ReplaceCallSite
    public static Call newCall(OkHttpClient client, Request request) {
        return new CallExtension(client, request, client.newCall(request));
    }

    @ReplaceCallSite
    public static Response.Builder body(Response.Builder builder, ResponseBody body) {
        return new ResponseBuilderExtension(builder).body(body);
    }

    @ReplaceCallSite
    public static Response.Builder newBuilder(Response.Builder builder) {
        return new ResponseBuilderExtension(builder);
    }

    @ReplaceCallSite(isStatic = false, scope = "okhttp3.OkUrlFactory")
    public static HttpURLConnection open(OkUrlFactory factory, URL url) {
        HttpURLConnection conn = factory.open(url);
        String protocol = url.getProtocol();
        if (protocol.equals("http")) {
            return new HttpURLConnectionExtension(conn);
        }
        if (protocol.equals("https") && (conn instanceof HttpsURLConnection)) {
            return new HttpsURLConnectionExtension((HttpsURLConnection) conn);
        }
        return new HttpURLConnectionExtension(conn);
    }

    private static void logReflectionError(String signature) {
        String crlf = System.getProperty("line.separator");
        log.error("Unable to resolve method \"" + signature + "\"." + crlf + "This is usually due to building the app with unsupported OkHttp versions." + crlf + "Check your build configuration for compatibility.");
    }
}
