package com.google.android.gms.location.places;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.internal.zzz;
import com.google.android.gms.maps.model.LatLng;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AddPlaceRequest extends AbstractSafeParcelable {
    public static final Creator<AddPlaceRequest> CREATOR = new zzb();
    private final String mName;
    final int mVersionCode;
    private final String zzaCQ;
    private final LatLng zzaWq;
    private final List<Integer> zzaWr;
    private final String zzaWs;
    private final Uri zzaWt;

    AddPlaceRequest(int i, String str, LatLng latLng, String str2, List<Integer> list, String str3, Uri uri) {
        boolean z = false;
        this.mVersionCode = i;
        this.mName = zzaa.zzdl(str);
        this.zzaWq = (LatLng) zzaa.zzz(latLng);
        this.zzaCQ = zzaa.zzdl(str2);
        this.zzaWr = new ArrayList((Collection) zzaa.zzz(list));
        zzaa.zzb(!this.zzaWr.isEmpty(), (Object) "At least one place type should be provided.");
        if (!(TextUtils.isEmpty(str3) && uri == null)) {
            z = true;
        }
        zzaa.zzb(z, (Object) "One of phone number or URI should be provided.");
        this.zzaWs = str3;
        this.zzaWt = uri;
    }

    public String getAddress() {
        return this.zzaCQ;
    }

    public LatLng getLatLng() {
        return this.zzaWq;
    }

    public String getName() {
        return this.mName;
    }

    @Nullable
    public String getPhoneNumber() {
        return this.zzaWs;
    }

    public List<Integer> getPlaceTypes() {
        return this.zzaWr;
    }

    @Nullable
    public Uri getWebsiteUri() {
        return this.zzaWt;
    }

    public String toString() {
        return zzz.zzy(this).zzg("name", this.mName).zzg("latLng", this.zzaWq).zzg("address", this.zzaCQ).zzg("placeTypes", this.zzaWr).zzg("phoneNumer", this.zzaWs).zzg("websiteUri", this.zzaWt).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzb.zza(this, parcel, i);
    }
}
