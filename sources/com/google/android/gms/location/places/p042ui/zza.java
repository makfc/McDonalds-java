package com.google.android.gms.location.places.p042ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources.Theme;
import android.util.TypedValue;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.zzc;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.internal.PlaceEntity;

/* renamed from: com.google.android.gms.location.places.ui.zza */
abstract class zza {

    /* renamed from: com.google.android.gms.location.places.ui.zza$zza */
    protected static abstract class zza {
        protected final Intent mIntent;

        public zza(String str) {
            this.mIntent = new Intent(str);
            this.mIntent.setPackage("com.google.android.gms");
        }

        /* Access modifiers changed, original: protected */
        public Intent build(Activity activity) throws GooglePlayServicesRepairableException, GooglePlayServicesNotAvailableException {
            Theme theme = activity.getTheme();
            TypedValue typedValue = new TypedValue();
            TypedValue typedValue2 = new TypedValue();
            if (theme.resolveAttribute(16843827, typedValue, true) && !this.mIntent.hasExtra("primary_color")) {
                this.mIntent.putExtra("primary_color", typedValue.data);
            }
            if (theme.resolveAttribute(16843828, typedValue2, true) && !this.mIntent.hasExtra("primary_color_dark")) {
                this.mIntent.putExtra("primary_color_dark", typedValue2.data);
            }
            GoogleApiAvailability.getInstance().zzaf(activity);
            return this.mIntent;
        }
    }

    zza() {
    }

    public static Place getPlace(Context context, Intent intent) {
        zzaa.zzb((Object) intent, (Object) "intent must not be null");
        zzaa.zzb((Object) context, (Object) "context must not be null");
        return (Place) zzc.zza(intent, "selected_place", PlaceEntity.CREATOR);
    }

    public static Status getStatus(Context context, Intent intent) {
        zzaa.zzb((Object) intent, (Object) "intent must not be null");
        zzaa.zzb((Object) context, (Object) "context must not be null");
        return (Status) zzc.zza(intent, "status", Status.CREATOR);
    }
}
