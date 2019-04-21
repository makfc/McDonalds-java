package com.mcdonalds.sdk.services.network;

import android.content.Context;
import android.support.annotation.NonNull;
import com.android.volley.NetworkResponse;
import com.android.volley.Response.ErrorListener;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.C3883R;
import com.mcdonalds.sdk.services.log.MCDLog;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import org.json.JSONException;

public class ResponseErrorListenerWrapper implements ErrorListener {
    private static final String DEBUG_ERROR_FORMAT = "Network Error:\nStatus Code: %s\nCause: %s";
    private final Context mContext;
    private final AsyncListener mListener;

    public ResponseErrorListenerWrapper(Context context, AsyncListener listener) {
        this.mContext = context;
        this.mListener = listener;
    }

    public void onErrorResponse(VolleyError error) {
        String message;
        MCDLog.debug(getDebugMessage(error));
        if ("".equals("DEV") && error != null) {
            message = getDebugMessage(error);
        } else if (error instanceof TimeoutError) {
            message = this.mContext.getString(C3883R.string.error_no_network_connection);
        } else {
            message = this.mContext.getString(C3883R.string.offline_warning);
        }
        if (this.mListener != null) {
            this.mListener.onResponse(null, null, new AsyncException(message));
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

    private String trimMessage(String json, String key) {
        try {
            return JSONObjectInstrumentation.init(json).getString(key);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
