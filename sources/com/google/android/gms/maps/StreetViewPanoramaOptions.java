package com.google.android.gms.maps;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.maps.internal.zza;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;

public final class StreetViewPanoramaOptions implements SafeParcelable {
    public static final zzb CREATOR = new zzb();
    private final int mVersionCode;
    private StreetViewPanoramaCamera zzaZT;
    private String zzaZU;
    private LatLng zzaZV;
    private Integer zzaZW;
    private Boolean zzaZX;
    private Boolean zzaZY;
    private Boolean zzaZZ;
    private Boolean zzaZg;
    private Boolean zzaZm;

    public StreetViewPanoramaOptions() {
        this.zzaZX = Boolean.valueOf(true);
        this.zzaZm = Boolean.valueOf(true);
        this.zzaZY = Boolean.valueOf(true);
        this.zzaZZ = Boolean.valueOf(true);
        this.mVersionCode = 1;
    }

    StreetViewPanoramaOptions(int i, StreetViewPanoramaCamera streetViewPanoramaCamera, String str, LatLng latLng, Integer num, byte b, byte b2, byte b3, byte b4, byte b5) {
        this.zzaZX = Boolean.valueOf(true);
        this.zzaZm = Boolean.valueOf(true);
        this.zzaZY = Boolean.valueOf(true);
        this.zzaZZ = Boolean.valueOf(true);
        this.mVersionCode = i;
        this.zzaZT = streetViewPanoramaCamera;
        this.zzaZV = latLng;
        this.zzaZW = num;
        this.zzaZU = str;
        this.zzaZX = zza.zza(b);
        this.zzaZm = zza.zza(b2);
        this.zzaZY = zza.zza(b3);
        this.zzaZZ = zza.zza(b4);
        this.zzaZg = zza.zza(b5);
    }

    public int describeContents() {
        return 0;
    }

    public String getPanoramaId() {
        return this.zzaZU;
    }

    public LatLng getPosition() {
        return this.zzaZV;
    }

    public Integer getRadius() {
        return this.zzaZW;
    }

    public StreetViewPanoramaCamera getStreetViewPanoramaCamera() {
        return this.zzaZT;
    }

    /* Access modifiers changed, original: 0000 */
    public int getVersionCode() {
        return this.mVersionCode;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzb.zza(this, parcel, i);
    }

    /* Access modifiers changed, original: 0000 */
    public byte zzDK() {
        return zza.zze(this.zzaZX);
    }

    /* Access modifiers changed, original: 0000 */
    public byte zzDL() {
        return zza.zze(this.zzaZY);
    }

    /* Access modifiers changed, original: 0000 */
    public byte zzDM() {
        return zza.zze(this.zzaZZ);
    }

    /* Access modifiers changed, original: 0000 */
    public byte zzDu() {
        return zza.zze(this.zzaZg);
    }

    /* Access modifiers changed, original: 0000 */
    public byte zzDy() {
        return zza.zze(this.zzaZm);
    }
}
