package com.mcdonalds.sdk.services.network;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;

public class MCDImageRequest<V, E> extends ImageRequest {

    private static class MCDImageResponseErrorListenerWrapper implements ErrorListener {
        private final AsyncListener mListener;

        private MCDImageResponseErrorListenerWrapper(AsyncListener listener) {
            this.mListener = listener;
        }

        public void onErrorResponse(VolleyError volleyError) {
            this.mListener.onResponse(null, null, new AsyncException(volleyError.getLocalizedMessage()));
        }
    }

    private static class MCDImageResponseSuccessListenerWrapper<V> implements Listener<V> {
        private final AsyncListener mListener;

        private MCDImageResponseSuccessListenerWrapper(AsyncListener listener) {
            this.mListener = listener;
        }

        public void onResponse(V response) {
            this.mListener.onResponse(response, null, null);
        }
    }

    private MCDImageRequest(String url, Listener<Bitmap> listener, int maxWidth, int maxHeight, Config decodeConfig, ErrorListener errorListener) {
        super(url, listener, maxWidth, maxHeight, decodeConfig, errorListener);
    }

    public MCDImageRequest(RequestProvider<V, E> provider, AsyncListener<V> listener) {
        super(provider.getURLString(), new MCDImageResponseSuccessListenerWrapper(listener), 0, 0, null, new MCDImageResponseErrorListenerWrapper(listener));
    }
}
