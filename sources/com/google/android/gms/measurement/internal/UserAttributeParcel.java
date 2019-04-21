package com.google.android.gms.measurement.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.zzaa;

public class UserAttributeParcel extends AbstractSafeParcelable {
    public static final zzaj CREATOR = new zzaj();
    public final String name;
    public final int versionCode;
    public final String zzasH;
    public final String zzbcr;
    public final long zzbgc;
    public final Long zzbgd;
    public final Float zzbge;
    public final Double zzbgf;

    UserAttributeParcel(int i, String str, long j, Long l, Float f, String str2, String str3, Double d) {
        Double d2 = null;
        this.versionCode = i;
        this.name = str;
        this.zzbgc = j;
        this.zzbgd = l;
        this.zzbge = null;
        if (i == 1) {
            if (f != null) {
                d2 = Double.valueOf(f.doubleValue());
            }
            this.zzbgf = d2;
        } else {
            this.zzbgf = d;
        }
        this.zzasH = str2;
        this.zzbcr = str3;
    }

    UserAttributeParcel(zzak zzak) {
        this(zzak.mName, zzak.zzbgg, zzak.zzRF, zzak.zzPx);
    }

    UserAttributeParcel(String str, long j, Object obj, String str2) {
        zzaa.zzdl(str);
        this.versionCode = 2;
        this.name = str;
        this.zzbgc = j;
        this.zzbcr = str2;
        if (obj == null) {
            this.zzbgd = null;
            this.zzbge = null;
            this.zzbgf = null;
            this.zzasH = null;
        } else if (obj instanceof Long) {
            this.zzbgd = (Long) obj;
            this.zzbge = null;
            this.zzbgf = null;
            this.zzasH = null;
        } else if (obj instanceof String) {
            this.zzbgd = null;
            this.zzbge = null;
            this.zzbgf = null;
            this.zzasH = (String) obj;
        } else if (obj instanceof Double) {
            this.zzbgd = null;
            this.zzbge = null;
            this.zzbgf = (Double) obj;
            this.zzasH = null;
        } else {
            throw new IllegalArgumentException("User attribute given of un-supported type");
        }
    }

    public Object getValue() {
        return this.zzbgd != null ? this.zzbgd : this.zzbgf != null ? this.zzbgf : this.zzasH != null ? this.zzasH : null;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzaj.zza(this, parcel, i);
    }
}
