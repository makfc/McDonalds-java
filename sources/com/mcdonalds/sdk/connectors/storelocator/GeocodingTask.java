package com.mcdonalds.sdk.connectors.storelocator;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.connectors.StoreLocatorConnector;
import com.mcdonalds.sdk.modules.storelocator.Store;
import com.mcdonalds.sdk.services.log.SafeLog;
import com.newrelic.agent.android.api.p047v2.TraceFieldInterface;
import com.newrelic.agent.android.tracing.Trace;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class GeocodingTask extends AsyncTask<AsyncToken, Integer, Boolean> implements TraceFieldInterface {
    private static final String LOG_TAG = GeocodingTask.class.getSimpleName();
    public Trace _nr_trace;
    private AsyncListener<List<Store>> mAsycListener;
    private StoreLocatorConnector mConnector;
    private Context mContext;
    private StoreLocatorConnectorQueryParameters mQuery;
    private AsyncToken mToken;

    public void _nr_setTrace(Trace trace) {
        try {
            this._nr_trace = trace;
        } catch (Exception e) {
        }
    }

    public GeocodingTask(Context context, StoreLocatorConnector connector, StoreLocatorConnectorQueryParameters query, AsyncListener<List<Store>> listener) {
        this.mContext = context;
        this.mConnector = connector;
        this.mQuery = query;
        this.mAsycListener = listener;
    }

    /* Access modifiers changed, original: protected|varargs */
    public Boolean doInBackground(AsyncToken... params) {
        this.mToken = params[0];
        String search = this.mQuery.getSearchString();
        if (search != null) {
            try {
                List<Address> addresses = new Geocoder(this.mContext, Locale.US).getFromLocationName(search, 1);
                if (!addresses.isEmpty()) {
                    Address address = (Address) addresses.get(0);
                    this.mQuery.setLatitude(Double.valueOf(address.getLatitude()));
                    this.mQuery.setLongitude(Double.valueOf(address.getLongitude()));
                    return Boolean.valueOf(true);
                }
            } catch (IOException e) {
                SafeLog.m7745e(LOG_TAG, "error trying to geocode location");
            }
        }
        return Boolean.valueOf(false);
    }

    /* Access modifiers changed, original: protected */
    public void onPostExecute(@NonNull Boolean result) {
        super.onPostExecute(result);
        if (result.booleanValue()) {
            this.mConnector.requestStores(this.mQuery, this.mAsycListener);
            return;
        }
        this.mAsycListener.onResponse(null, this.mToken, new AsyncException("We were not able to find this address"));
    }
}
