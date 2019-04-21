package com.mcdonalds.sdk.connectors.google;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import com.mcdonalds.sdk.C3883R;
import com.mcdonalds.sdk.services.log.MCDLog;
import com.newrelic.agent.android.api.p047v2.TraceFieldInterface;
import com.newrelic.agent.android.tracing.Trace;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class GeocodingAsyncTask extends AsyncTask<Object, Void, Address> implements TraceFieldInterface {
    public Trace _nr_trace;
    private Context mContext;
    private GeocodingAsyncTaskListener mListener;

    public void _nr_setTrace(Trace trace) {
        try {
            this._nr_trace = trace;
        } catch (Exception e) {
        }
    }

    /* Access modifiers changed, original: protected|varargs */
    public Address doInBackground(Object... params) {
        if (params.length != 3) {
            MCDLog.fatal("Improper execution of GeocodingTask, parameter count incorrect");
            return null;
        }
        String address = params[0];
        this.mContext = (Context) params[1];
        this.mListener = (GeocodingAsyncTaskListener) params[2];
        try {
            List<Address> results = new Geocoder(this.mContext, Locale.US).getFromLocationName(address, 1);
            return results.size() > 0 ? (Address) results.get(0) : null;
        } catch (IOException e) {
            return null;
        }
    }

    /* Access modifiers changed, original: protected */
    public void onPostExecute(Address address) {
        if (address != null) {
            this.mListener.onResponse(Double.valueOf(address.getLatitude()), Double.valueOf(address.getLongitude()), null);
        } else {
            this.mListener.onResponse(null, null, this.mContext.getString(C3883R.string.error_could_not_determine_address));
        }
    }
}
