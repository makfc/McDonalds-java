package com.google.android.gms.phenotype;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.facebook.stetho.common.Utf8Charset;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.zzz;
import com.newrelic.agent.android.util.SafeJsonPrimitive;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Comparator;

public class Flag extends AbstractSafeParcelable implements Comparable<Flag> {
    public static final Creator<Flag> CREATOR = new zzb();
    private static final Charset UTF_8 = Charset.forName(Utf8Charset.NAME);
    public static final zza zzbkJ = new zza();
    final int mVersionCode;
    public final String name;
    final boolean zzaTF;
    final double zzaTH;
    final String zzasH;
    final long zzbkF;
    final byte[] zzbkG;
    public final int zzbkH;
    public final int zzbkI;

    public static class zza implements Comparator<Flag> {
        /* renamed from: zza */
        public int compare(Flag flag, Flag flag2) {
            return flag.zzbkI == flag2.zzbkI ? flag.name.compareTo(flag2.name) : flag.zzbkI - flag2.zzbkI;
        }
    }

    Flag(int i, String str, long j, boolean z, double d, String str2, byte[] bArr, int i2, int i3) {
        this.mVersionCode = i;
        this.name = str;
        this.zzbkF = j;
        this.zzaTF = z;
        this.zzaTH = d;
        this.zzasH = str2;
        this.zzbkG = bArr;
        this.zzbkH = i2;
        this.zzbkI = i3;
    }

    private static int compare(byte b, byte b2) {
        return b - b2;
    }

    private static int compare(int i, int i2) {
        return i < i2 ? -1 : i == i2 ? 0 : 1;
    }

    private static int compare(long j, long j2) {
        return j < j2 ? -1 : j == j2 ? 0 : 1;
    }

    private static int compare(String str, String str2) {
        return str == str2 ? 0 : str == null ? -1 : str2 == null ? 1 : str.compareTo(str2);
    }

    private static int compare(boolean z, boolean z2) {
        return z == z2 ? 0 : z ? 1 : -1;
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Flag)) {
            return false;
        }
        Flag flag = (Flag) obj;
        if (this.mVersionCode != flag.mVersionCode || !zzz.equal(this.name, flag.name) || this.zzbkH != flag.zzbkH || this.zzbkI != flag.zzbkI) {
            return false;
        }
        switch (this.zzbkH) {
            case 1:
                return this.zzbkF == flag.zzbkF;
            case 2:
                return this.zzaTF == flag.zzaTF;
            case 3:
                return this.zzaTH == flag.zzaTH;
            case 4:
                return zzz.equal(this.zzasH, flag.zzasH);
            case 5:
                return Arrays.equals(this.zzbkG, flag.zzbkG);
            default:
                throw new AssertionError("Invalid enum value: " + this.zzbkH);
        }
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("Flag(");
        stringBuilder.append(this.mVersionCode);
        stringBuilder.append(", ");
        stringBuilder.append(this.name);
        stringBuilder.append(", ");
        switch (this.zzbkH) {
            case 1:
                stringBuilder.append(this.zzbkF);
                break;
            case 2:
                stringBuilder.append(this.zzaTF);
                break;
            case 3:
                stringBuilder.append(this.zzaTH);
                break;
            case 4:
                stringBuilder.append("'");
                stringBuilder.append(this.zzasH);
                stringBuilder.append("'");
                break;
            case 5:
                if (this.zzbkG != null) {
                    stringBuilder.append("'");
                    stringBuilder.append(new String(this.zzbkG, UTF_8));
                    stringBuilder.append("'");
                    break;
                }
                stringBuilder.append(SafeJsonPrimitive.NULL_STRING);
                break;
            default:
                throw new AssertionError("Invalid enum value: " + this.zzbkH);
        }
        stringBuilder.append(", ");
        stringBuilder.append(this.zzbkH);
        stringBuilder.append(", ");
        stringBuilder.append(this.zzbkI);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzb.zza(this, parcel, i);
    }

    /* renamed from: zza */
    public int compareTo(Flag flag) {
        int i = 0;
        int compareTo = this.name.compareTo(flag.name);
        if (compareTo != 0) {
            return compareTo;
        }
        compareTo = compare(this.zzbkH, flag.zzbkH);
        if (compareTo != 0) {
            return compareTo;
        }
        switch (this.zzbkH) {
            case 1:
                return compare(this.zzbkF, flag.zzbkF);
            case 2:
                return compare(this.zzaTF, flag.zzaTF);
            case 3:
                return Double.compare(this.zzaTH, flag.zzaTH);
            case 4:
                return compare(this.zzasH, flag.zzasH);
            case 5:
                if (this.zzbkG == flag.zzbkG) {
                    return 0;
                }
                if (this.zzbkG == null) {
                    return -1;
                }
                if (flag.zzbkG == null) {
                    return 1;
                }
                while (i < Math.min(this.zzbkG.length, flag.zzbkG.length)) {
                    compareTo = compare(this.zzbkG[i], flag.zzbkG[i]);
                    if (compareTo != 0) {
                        return compareTo;
                    }
                    i++;
                }
                return compare(this.zzbkG.length, flag.zzbkG.length);
            default:
                throw new AssertionError("Invalid enum value: " + this.zzbkH);
        }
    }
}
