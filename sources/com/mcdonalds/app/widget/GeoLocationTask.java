package com.mcdonalds.app.widget;

import android.location.Address;
import android.os.AsyncTask;
import com.ensighten.Ensighten;
import com.mcdonalds.app.storelocator.maps.model.LatLng;
import com.mcdonalds.sdk.services.location.LocationServicesManager;
import com.newrelic.agent.android.api.p047v2.TraceFieldInterface;
import com.newrelic.agent.android.tracing.Trace;
import java.util.ArrayList;
import java.util.List;

public class GeoLocationTask extends AsyncTask<String, Integer, List<Address>> implements TraceFieldInterface {
    public Trace _nr_trace;
    private GeolocationListener listener;
    private LatLng mNorthwestBound;
    private LatLng mSoutheastBound;

    public interface GeolocationListener {
        void onGeolocation(List<Address> list);
    }

    public void _nr_setTrace(Trace trace) {
        try {
            this._nr_trace = trace;
        } catch (Exception e) {
        }
    }

    public GeoLocationTask setBounds(LatLng northwestBound, LatLng southeastBound) {
        Ensighten.evaluateEvent(this, "setBounds", new Object[]{northwestBound, southeastBound});
        this.mNorthwestBound = northwestBound;
        this.mSoutheastBound = southeastBound;
        return this;
    }

    /* Access modifiers changed, original: protected|varargs */
    public List<Address> doInBackground(String... strings) {
        Ensighten.evaluateEvent(this, "doInBackground", new Object[]{strings});
        if (this.mSoutheastBound == null || this.mNorthwestBound == null) {
            return LocationServicesManager.getInstance().searchAddress(strings[0]);
        }
        return LocationServicesManager.getInstance().searchAddress(strings[0], this.mSoutheastBound.latitude, this.mSoutheastBound.longitude, this.mNorthwestBound.latitude, this.mNorthwestBound.longitude);
    }

    /* Access modifiers changed, original: protected */
    public void onPostExecute(List<Address> addresses) {
        Ensighten.evaluateEvent(this, "onPostExecute", new Object[]{addresses});
        if (!(this.listener == null || addresses == null)) {
            List<Address> addressList = new ArrayList();
            addressList.addAll(addresses);
            for (Address address : addresses) {
                if (address.getAdminArea() == null) {
                    addressList.remove(address);
                }
            }
            this.listener.onGeolocation(addressList);
        }
        this.listener = null;
    }

    public GeoLocationTask setListener(GeolocationListener listener) {
        Ensighten.evaluateEvent(this, "setListener", new Object[]{listener});
        this.listener = listener;
        return this;
    }
}
