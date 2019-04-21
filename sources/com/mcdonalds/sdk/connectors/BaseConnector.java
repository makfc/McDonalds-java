package com.mcdonalds.sdk.connectors;

import android.content.Context;
import com.mcdonalds.sdk.services.network.RequestManagerServiceConnection;

public class BaseConnector {
    private RequestManagerServiceConnection mConnection = null;
    private Context mContext = null;

    public RequestManagerServiceConnection getNetworkConnection() {
        return this.mConnection;
    }

    public void setConnection(RequestManagerServiceConnection connection) {
        this.mConnection = connection;
    }

    public Context getContext() {
        return this.mContext;
    }

    public void setContext(Context context) {
        this.mContext = context;
    }
}
