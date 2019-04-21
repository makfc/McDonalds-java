package com.google.android.gms.phenotype;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.zzz;
import com.newrelic.agent.android.util.SafeJsonPrimitive;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class Configuration extends AbstractSafeParcelable implements Comparable<Configuration> {
    public static final Creator<Configuration> CREATOR = new zza();
    final int mVersionCode;
    public final int zzbkB;
    public final Flag[] zzbkC;
    public final String[] zzbkD;
    public final Map<String, Flag> zzbkE = new TreeMap();

    Configuration(int i, int i2, Flag[] flagArr, String[] strArr) {
        this.mVersionCode = i;
        this.zzbkB = i2;
        this.zzbkC = flagArr;
        for (Flag flag : flagArr) {
            this.zzbkE.put(flag.name, flag);
        }
        this.zzbkD = strArr;
        if (this.zzbkD != null) {
            Arrays.sort(this.zzbkD);
        }
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Configuration)) {
            return false;
        }
        Configuration configuration = (Configuration) obj;
        return this.mVersionCode == configuration.mVersionCode && this.zzbkB == configuration.zzbkB && zzz.equal(this.zzbkE, configuration.zzbkE) && Arrays.equals(this.zzbkD, configuration.zzbkD);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("Configuration(");
        stringBuilder.append(this.mVersionCode);
        stringBuilder.append(", ");
        stringBuilder.append(this.zzbkB);
        stringBuilder.append(", ");
        stringBuilder.append("(");
        for (Flag append : this.zzbkE.values()) {
            stringBuilder.append(append);
            stringBuilder.append(", ");
        }
        stringBuilder.append(")");
        stringBuilder.append(", ");
        stringBuilder.append("(");
        if (this.zzbkD != null) {
            for (String append2 : this.zzbkD) {
                stringBuilder.append(append2);
                stringBuilder.append(", ");
            }
        } else {
            stringBuilder.append(SafeJsonPrimitive.NULL_STRING);
        }
        stringBuilder.append(")");
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        zza.zza(this, parcel, i);
    }

    /* renamed from: zza */
    public int compareTo(Configuration configuration) {
        return this.zzbkB - configuration.zzbkB;
    }
}
