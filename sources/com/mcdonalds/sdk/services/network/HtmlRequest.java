package com.mcdonalds.sdk.services.network;

import android.content.Context;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.mcdonalds.sdk.AsyncListener;
import java.io.UnsupportedEncodingException;

public class HtmlRequest<T, E> extends Request<String> {
    private static final String PROTOCOL_CHARSET = "utf-8";
    private AsyncListener<String> mAsyncListener;
    private RequestProvider<T, E> mRequestProvider;

    public HtmlRequest(Context context, int method, RequestProvider<T, E> requestProvider, AsyncListener<String> listener) {
        super(method, requestProvider.getURLString(), new ResponseErrorListenerWrapper(context, listener));
        this.mRequestProvider = requestProvider;
        this.mAsyncListener = listener;
    }

    /* Access modifiers changed, original: protected */
    public Response<String> parseNetworkResponse(NetworkResponse networkResponse) {
        try {
            return Response.success(new String(networkResponse.data, HttpHeaderParser.parseCharset(networkResponse.headers)), HttpHeaderParser.parseCacheHeaders(networkResponse));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        }
    }

    public byte[] getBody() {
        try {
            if (this.mRequestProvider.getBody() == null) {
                return null;
            }
            return this.mRequestProvider.getBody().getBytes(PROTOCOL_CHARSET);
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    /* Access modifiers changed, original: protected */
    public void deliverResponse(String response) {
        if (this.mAsyncListener != null) {
            this.mAsyncListener.onResponse(response, null, null);
        }
    }
}
