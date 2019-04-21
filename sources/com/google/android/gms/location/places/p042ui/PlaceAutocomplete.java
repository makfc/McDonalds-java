package com.google.android.gms.location.places.p042ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.p042ui.zza.zza;
import com.google.android.gms.maps.model.LatLngBounds;
import com.mcdonalds.sdk.connectors.autonavi.AutoNavi.Parameters;

/* renamed from: com.google.android.gms.location.places.ui.PlaceAutocomplete */
public class PlaceAutocomplete extends zza {

    /* renamed from: com.google.android.gms.location.places.ui.PlaceAutocomplete$IntentBuilder */
    public static class IntentBuilder extends zza {
        public IntentBuilder(int i) {
            super("com.google.android.gms.location.places.ui.AUTOCOMPLETE");
            this.mIntent.putExtra("gmscore_client_jar_version", GoogleApiAvailability.GOOGLE_PLAY_SERVICES_VERSION_CODE);
            this.mIntent.putExtra("mode", i);
            this.mIntent.putExtra("origin", 2);
        }

        public Intent build(Activity activity) throws GooglePlayServicesRepairableException, GooglePlayServicesNotAvailableException {
            return super.build(activity);
        }

        public IntentBuilder setBoundsBias(@Nullable LatLngBounds latLngBounds) {
            if (latLngBounds != null) {
                this.mIntent.putExtra("bounds", latLngBounds);
            } else {
                this.mIntent.removeExtra("bounds");
            }
            return this;
        }

        public IntentBuilder setFilter(@Nullable AutocompleteFilter autocompleteFilter) {
            if (autocompleteFilter != null) {
                this.mIntent.putExtra(Parameters.FILTER, autocompleteFilter);
            } else {
                this.mIntent.removeExtra(Parameters.FILTER);
            }
            return this;
        }

        public IntentBuilder zzeR(@Nullable String str) {
            if (str != null) {
                this.mIntent.putExtra("initial_query", str);
            } else {
                this.mIntent.removeExtra("initial_query");
            }
            return this;
        }

        public IntentBuilder zziH(int i) {
            this.mIntent.putExtra("origin", i);
            return this;
        }
    }

    private PlaceAutocomplete() {
    }

    public static Place getPlace(Context context, Intent intent) {
        return zza.getPlace(context, intent);
    }

    public static Status getStatus(Context context, Intent intent) {
        return zza.getStatus(context, intent);
    }
}
