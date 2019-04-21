package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.internal.zzsn.zzf;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

class zzag {
    final boolean zzbdD;
    final int zzbfX;
    final boolean zzbfY;
    final String zzbfZ;
    final List<String> zzbga;
    final String zzbgb;

    public zzag(zzf zzf) {
        boolean z;
        boolean z2 = false;
        zzaa.zzz(zzf);
        if (zzf.zzbgE == null || zzf.zzbgE.intValue() == 0) {
            z = false;
        } else {
            if (zzf.zzbgE.intValue() == 6) {
                if (zzf.zzbgH == null || zzf.zzbgH.length == 0) {
                    z = false;
                }
            } else if (zzf.zzbgF == null) {
                z = false;
            }
            z = true;
        }
        if (z) {
            this.zzbfX = zzf.zzbgE.intValue();
            if (zzf.zzbgG != null && zzf.zzbgG.booleanValue()) {
                z2 = true;
            }
            this.zzbfY = z2;
            if (this.zzbfY || this.zzbfX == 1 || this.zzbfX == 6) {
                this.zzbfZ = zzf.zzbgF;
            } else {
                this.zzbfZ = zzf.zzbgF.toUpperCase(Locale.ENGLISH);
            }
            this.zzbga = zzf.zzbgH == null ? null : zza(zzf.zzbgH, this.zzbfY);
            if (this.zzbfX == 1) {
                this.zzbgb = this.zzbfZ;
            } else {
                this.zzbgb = null;
            }
        } else {
            this.zzbfX = 0;
            this.zzbfY = false;
            this.zzbfZ = null;
            this.zzbga = null;
            this.zzbgb = null;
        }
        this.zzbdD = z;
    }

    private List<String> zza(String[] strArr, boolean z) {
        if (z) {
            return Arrays.asList(strArr);
        }
        ArrayList arrayList = new ArrayList();
        for (String toUpperCase : strArr) {
            arrayList.add(toUpperCase.toUpperCase(Locale.ENGLISH));
        }
        return arrayList;
    }

    public Boolean zzfF(String str) {
        if (!this.zzbdD || str == null) {
            return null;
        }
        CharSequence str2;
        if (!(this.zzbfY || this.zzbfX == 1)) {
            str2 = str2.toUpperCase(Locale.ENGLISH);
        }
        switch (this.zzbfX) {
            case 1:
                return Boolean.valueOf(Pattern.compile(this.zzbgb, this.zzbfY ? 0 : 66).matcher(str2).matches());
            case 2:
                return Boolean.valueOf(str2.startsWith(this.zzbfZ));
            case 3:
                return Boolean.valueOf(str2.endsWith(this.zzbfZ));
            case 4:
                return Boolean.valueOf(str2.contains(this.zzbfZ));
            case 5:
                return Boolean.valueOf(str2.equals(this.zzbfZ));
            case 6:
                return Boolean.valueOf(this.zzbga.contains(str2));
            default:
                return null;
        }
    }
}
