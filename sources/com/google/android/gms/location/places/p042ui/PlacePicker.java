package com.google.android.gms.location.places.p042ui;

import android.app.Activity;
import android.content.Intent;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.p042ui.zza.zza;

/* renamed from: com.google.android.gms.location.places.ui.PlacePicker */
public class PlacePicker extends zza {

    /* renamed from: com.google.android.gms.location.places.ui.PlacePicker$IntentBuilder */
    public static class IntentBuilder extends zza {
        public IntentBuilder() {
            super("com.google.android.gms.location.places.ui.PICK_PLACE");
            this.mIntent.putExtra("gmscore_client_jar_version", GoogleApiAvailability.GOOGLE_PLAY_SERVICES_VERSION_CODE);
        }

        public Intent build(Activity activity) throws GooglePlayServicesRepairableException, GooglePlayServicesNotAvailableException {
            return super.build(activity);
        }
    }

    private PlacePicker() {
    }
}
