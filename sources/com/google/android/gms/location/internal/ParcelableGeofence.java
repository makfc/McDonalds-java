package com.google.android.gms.location.internal;

import android.annotation.SuppressLint;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.location.Geofence;
import java.util.Locale;

public class ParcelableGeofence extends AbstractSafeParcelable implements Geofence {
    public static final zzo CREATOR = new zzo();
    private final int mVersionCode;
    private final String zzII;
    private final int zzaUS;
    private final short zzaUU;
    private final double zzaUV;
    private final double zzaUW;
    private final float zzaUX;
    private final int zzaUY;
    private final int zzaUZ;
    private final long zzaWm;

    public ParcelableGeofence(int i, String str, int i2, short s, double d, double d2, float f, long j, int i3, int i4) {
        zzeL(str);
        zze(f);
        zza(d, d2);
        int zzii = zzii(i2);
        this.mVersionCode = i;
        this.zzaUU = s;
        this.zzII = str;
        this.zzaUV = d;
        this.zzaUW = d2;
        this.zzaUX = f;
        this.zzaWm = j;
        this.zzaUS = zzii;
        this.zzaUY = i3;
        this.zzaUZ = i4;
    }

    private static void zza(double d, double d2) {
        if (d > 90.0d || d < -90.0d) {
            throw new IllegalArgumentException("invalid latitude: " + d);
        } else if (d2 > 180.0d || d2 < -180.0d) {
            throw new IllegalArgumentException("invalid longitude: " + d2);
        }
    }

    private static void zze(float f) {
        if (f <= 0.0f) {
            throw new IllegalArgumentException("invalid radius: " + f);
        }
    }

    private static void zzeL(String str) {
        if (str == null || str.length() > 100) {
            String str2 = "requestId is null or too long: ";
            String valueOf = String.valueOf(str);
            throw new IllegalArgumentException(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
        }
    }

    private static int zzii(int i) {
        int i2 = i & 7;
        if (i2 != 0) {
            return i2;
        }
        throw new IllegalArgumentException("No supported transition specified: " + i);
    }

    @SuppressLint({"DefaultLocale"})
    private static String zzij(int i) {
        switch (i) {
            case 1:
                return "CIRCLE";
            default:
                return null;
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof ParcelableGeofence)) {
            return false;
        }
        ParcelableGeofence parcelableGeofence = (ParcelableGeofence) obj;
        return this.zzaUX != parcelableGeofence.zzaUX ? false : this.zzaUV != parcelableGeofence.zzaUV ? false : this.zzaUW != parcelableGeofence.zzaUW ? false : this.zzaUU == parcelableGeofence.zzaUU;
    }

    public long getExpirationTime() {
        return this.zzaWm;
    }

    public double getLatitude() {
        return this.zzaUV;
    }

    public double getLongitude() {
        return this.zzaUW;
    }

    public float getRadius() {
        return this.zzaUX;
    }

    public String getRequestId() {
        return this.zzII;
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public int hashCode() {
        long doubleToLongBits = Double.doubleToLongBits(this.zzaUV);
        int i = ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32))) + 31;
        long doubleToLongBits2 = Double.doubleToLongBits(this.zzaUW);
        return (((((((i * 31) + ((int) (doubleToLongBits2 ^ (doubleToLongBits2 >>> 32)))) * 31) + Float.floatToIntBits(this.zzaUX)) * 31) + this.zzaUU) * 31) + this.zzaUS;
    }

    public String toString() {
        return String.format(Locale.US, "Geofence[%s id:%s transitions:%d %.6f, %.6f %.0fm, resp=%ds, dwell=%dms, @%d]", new Object[]{zzij(this.zzaUU), this.zzII, Integer.valueOf(this.zzaUS), Double.valueOf(this.zzaUV), Double.valueOf(this.zzaUW), Float.valueOf(this.zzaUX), Integer.valueOf(this.zzaUY / 1000), Integer.valueOf(this.zzaUZ), Long.valueOf(this.zzaWm)});
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzo zzo = CREATOR;
        zzo.zza(this, parcel, i);
    }

    public short zzCB() {
        return this.zzaUU;
    }

    public int zzCC() {
        return this.zzaUS;
    }

    public int zzCD() {
        return this.zzaUY;
    }

    public int zzCE() {
        return this.zzaUZ;
    }
}
