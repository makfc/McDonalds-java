package com.mcdonalds.sdk.services.network;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.autonavi.amap.mapcore.VTMCDataCache;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.C3883R;
import com.mcdonalds.sdk.connectors.middleware.MiddlewareConnectorUtils;
import com.mcdonalds.sdk.connectors.middleware.response.MWJSONResponse;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.mcdonalds.sdk.services.log.MCDLog;
import com.mcdonalds.sdk.services.network.RequestProvider.MethodType;
import com.mcdonalds.sdk.services.network.RequestProvider.RequestType;
import com.newrelic.agent.android.instrumentation.GsonInstrumentation;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public final class GsonRequest<V, E> extends Request<V> {
    private static final String DEBUG_ERROR_FORMAT = "Network Error:\nStatus Code: %s\nCause: %s";
    private static final int ERROR_INVALID_TOKEN = -1037;
    private static final int ERROR_LANGUAGE_NOT_SUPPORTED = -1030;
    private static final int ERROR_WECHAT_TOKEN_EXPIRE = -2105;
    public static final String HEADER_PARAM_TOKEN = "Token";
    private static final String LANGUAGE_NAME_FORMAT = "\"languageName\":\"%s\"";
    private static final Pattern LANGUAGE_PATTERN = Pattern.compile("\"languageName\":\".*?\"");
    private static final String PROTOCOL_CHARSET = "utf-8";
    private static final String PROTOCOL_CONTENT_TYPE = "application/json";
    private static final String PROTOCOL_CONTENT_TYPE_KEY = "Content-Type";
    private final AsyncListener<V> mAsyncListener;
    private final Class<V> mClazz;
    private final Context mContext;
    private final Gson mGson;
    private final Map<String, String> mHeaders;
    private final RequestProvider<V, E> mRequestProvider;
    private boolean mSkipListener;

    public GsonRequest(Context context, int method, RequestProvider<V, E> provider, AsyncListener<V> listener) {
        Map hashMap;
        super(method, provider.getURLString(), null);
        this.mContext = context;
        this.mRequestProvider = provider;
        this.mAsyncListener = listener;
        GsonBuilder gsonBuilder = new GsonBuilder();
        List<? extends CustomTypeAdapter> customTypeAdapters = provider.getCustomTypeAdapters();
        if (customTypeAdapters != null) {
            for (CustomTypeAdapter customTypeAdapter : customTypeAdapters) {
                if (customTypeAdapter.getSerializer() != null) {
                    gsonBuilder = gsonBuilder.registerTypeAdapter(customTypeAdapter.getType(), customTypeAdapter.getSerializer());
                }
                if (customTypeAdapter.getDeserializer() != null) {
                    gsonBuilder = gsonBuilder.registerTypeAdapter(customTypeAdapter.getType(), customTypeAdapter.getDeserializer());
                }
            }
        }
        this.mGson = gsonBuilder.create();
        this.mClazz = provider.getResponseClass();
        if (provider.getHeaders() == null) {
            hashMap = new HashMap();
        } else {
            hashMap = provider.getHeaders();
        }
        this.mHeaders = hashMap;
        if (provider.getMethodType() == MethodType.DELETE) {
            this.mHeaders.put("Content-Type", PROTOCOL_CONTENT_TYPE);
        }
    }

    public Map<String, String> getHeaders() throws AuthFailureError {
        return this.mHeaders != null ? this.mHeaders : super.getHeaders();
    }

    /* Access modifiers changed, original: protected */
    public Response<V> parseNetworkResponse(NetworkResponse response) {
        try {
            if (isLogEnabled()) {
                Log.d("RESPONSE: " + getUrl(), new String(response.data, HttpHeaderParser.parseCharset(response.headers)));
            }
            Reader reader = new InputStreamReader(new ByteArrayInputStream(response.data));
            Gson gson = this.mGson;
            Class cls = this.mClazz;
            Response<V> parsed = Response.success(!(gson instanceof Gson) ? gson.fromJson(reader, cls) : GsonInstrumentation.fromJson(gson, reader, cls), HttpHeaderParser.parseCacheHeaders(response));
            checkForErrors(parsed.result);
            return parsed;
        } catch (JsonSyntaxException e) {
            return Response.error(new ParseError(e));
        } catch (UnsupportedEncodingException e2) {
            return Response.error(new ParseError(e2));
        } catch (NullPointerException npe) {
            return Response.error(new ParseError(npe));
        }
    }

    private void onErrorResponse(VolleyError error) {
        AsyncException exception = null;
        MCDLog.debug(getDebugMessage(error));
        if (!(error.networkResponse == null || error.networkResponse.data == null || error.networkResponse.statusCode != VTMCDataCache.MAXSIZE)) {
            Response<V> parsed = parseNetworkResponse(error.networkResponse);
            if (!(parsed == null || parsed.result == null || !(parsed.result instanceof MWJSONResponse))) {
                exception = MiddlewareConnectorUtils.exceptionFromResults((MWJSONResponse) parsed.result, null);
                AsyncException.reportAPIInvalidError(exception);
                if (!(exception == null || this.mAsyncListener == null)) {
                    this.mAsyncListener.onResponse(null, null, exception);
                }
            }
        }
        if (exception == null) {
            String message;
            if ("".equals("DEV") && error != null) {
                message = getDebugMessage(error);
            } else if (error instanceof TimeoutError) {
                message = this.mContext.getString(C3883R.string.error_no_network_connection);
            } else {
                message = this.mContext.getString(C3883R.string.offline_warning);
            }
            if (this.mAsyncListener != null) {
                this.mAsyncListener.onResponse(null, null, new AsyncException(message));
            }
        }
    }

    private String getDebugMessage(@NonNull VolleyError error) {
        String status = "not specified";
        NetworkResponse response = error.networkResponse;
        if (response != null) {
            MCDLog.debug(new String(response.data));
            status = String.valueOf(response.statusCode);
        }
        return String.format(DEBUG_ERROR_FORMAT, new Object[]{status, error.getMessage()});
    }

    public void deliverError(VolleyError error) {
        super.deliverError(error);
        onErrorResponse(error);
    }

    /* Access modifiers changed, original: protected */
    public void deliverResponse(V response) {
        if (this.mAsyncListener != null && !this.mSkipListener) {
            this.mAsyncListener.onResponse(response, null, null);
        }
    }

    public byte[] getBody() throws AuthFailureError {
        try {
            if (this.mRequestProvider.getBody() == null) {
                return null;
            }
            return this.mRequestProvider.getBody().getBytes(PROTOCOL_CHARSET);
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    public String getBodyContentType() {
        return PROTOCOL_CONTENT_TYPE;
    }

    private boolean isLogEnabled() {
        return Configuration.getSharedInstance().getBooleanForKey("log.logsToConsole") && Configuration.getSharedInstance().getBooleanForKey("log.network");
    }

    private void checkForErrors(V result) {
        if (getResultCode(result) == ERROR_LANGUAGE_NOT_SUPPORTED) {
            retryWithDefaultLanguage();
        }
    }

    private static int getResultCode(Object response) {
        if (response instanceof MWJSONResponse) {
            return ((MWJSONResponse) response).getResultCode();
        }
        return -1;
    }

    private void retryWithDefaultLanguage() {
        this.mSkipListener = true;
        String originalBody = this.mRequestProvider.getBody();
        String languageName = Configuration.getSharedInstance().getLocalization().getDefaultLanguage();
        RequestManager.register(this.mContext).processRequest(fromProvider(LANGUAGE_PATTERN.matcher(originalBody).replaceFirst(String.format(LANGUAGE_NAME_FORMAT, new Object[]{languageName}))), this.mAsyncListener);
    }

    @NonNull
    private RequestProvider<V, E> fromProvider(final String body) {
        return new RequestProvider<V, E>() {
            public MethodType getMethodType() {
                return GsonRequest.this.mRequestProvider.getMethodType();
            }

            public RequestType getRequestType() {
                return GsonRequest.this.mRequestProvider.getRequestType();
            }

            public String getURLString() {
                return GsonRequest.this.mRequestProvider.getURLString();
            }

            public Map<String, String> getHeaders() {
                return GsonRequest.this.mRequestProvider.getHeaders();
            }

            public String getBody() {
                return body;
            }

            public void setBody(E e) {
            }

            public Class<V> getResponseClass() {
                return GsonRequest.this.mRequestProvider.getResponseClass();
            }

            public List<? extends CustomTypeAdapter> getCustomTypeAdapters() {
                return GsonRequest.this.mRequestProvider.getCustomTypeAdapters();
            }
        };
    }
}
