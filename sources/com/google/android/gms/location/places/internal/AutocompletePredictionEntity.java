package com.google.android.gms.location.places.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.zzz;
import com.google.android.gms.location.places.AutocompletePrediction;
import java.util.Collections;
import java.util.List;

public class AutocompletePredictionEntity extends AbstractSafeParcelable implements AutocompletePrediction {
    public static final Creator<AutocompletePredictionEntity> CREATOR = new zza();
    private static final List<SubstringEntity> zzaXt = Collections.emptyList();
    final int mVersionCode;
    final String zzaWV;
    final List<Integer> zzaWr;
    final List<SubstringEntity> zzaXA;
    final String zzaXu;
    final List<SubstringEntity> zzaXv;
    final int zzaXw;
    final String zzaXx;
    final List<SubstringEntity> zzaXy;
    final String zzaXz;

    public static class SubstringEntity extends AbstractSafeParcelable {
        public static final Creator<SubstringEntity> CREATOR = new zzu();
        final int mLength;
        final int mOffset;
        final int mVersionCode;

        public SubstringEntity(int i, int i2, int i3) {
            this.mVersionCode = i;
            this.mOffset = i2;
            this.mLength = i3;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof SubstringEntity)) {
                return false;
            }
            SubstringEntity substringEntity = (SubstringEntity) obj;
            return zzz.equal(Integer.valueOf(this.mOffset), Integer.valueOf(substringEntity.mOffset)) && zzz.equal(Integer.valueOf(this.mLength), Integer.valueOf(substringEntity.mLength));
        }

        public int hashCode() {
            return zzz.hashCode(Integer.valueOf(this.mOffset), Integer.valueOf(this.mLength));
        }

        public String toString() {
            return zzz.zzy(this).zzg("offset", Integer.valueOf(this.mOffset)).zzg("length", Integer.valueOf(this.mLength)).toString();
        }

        public void writeToParcel(Parcel parcel, int i) {
            zzu.zza(this, parcel, i);
        }
    }

    AutocompletePredictionEntity(int i, String str, List<Integer> list, int i2, String str2, List<SubstringEntity> list2, String str3, List<SubstringEntity> list3, String str4, List<SubstringEntity> list4) {
        this.mVersionCode = i;
        this.zzaWV = str;
        this.zzaWr = list;
        this.zzaXw = i2;
        this.zzaXu = str2;
        this.zzaXv = list2;
        this.zzaXx = str3;
        this.zzaXy = list3;
        this.zzaXz = str4;
        this.zzaXA = list4;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AutocompletePredictionEntity)) {
            return false;
        }
        AutocompletePredictionEntity autocompletePredictionEntity = (AutocompletePredictionEntity) obj;
        return zzz.equal(this.zzaWV, autocompletePredictionEntity.zzaWV) && zzz.equal(this.zzaWr, autocompletePredictionEntity.zzaWr) && zzz.equal(Integer.valueOf(this.zzaXw), Integer.valueOf(autocompletePredictionEntity.zzaXw)) && zzz.equal(this.zzaXu, autocompletePredictionEntity.zzaXu) && zzz.equal(this.zzaXv, autocompletePredictionEntity.zzaXv) && zzz.equal(this.zzaXx, autocompletePredictionEntity.zzaXx) && zzz.equal(this.zzaXy, autocompletePredictionEntity.zzaXy) && zzz.equal(this.zzaXz, autocompletePredictionEntity.zzaXz) && zzz.equal(this.zzaXA, autocompletePredictionEntity.zzaXA);
    }

    public int hashCode() {
        return zzz.hashCode(this.zzaWV, this.zzaWr, Integer.valueOf(this.zzaXw), this.zzaXu, this.zzaXv, this.zzaXx, this.zzaXy, this.zzaXz, this.zzaXA);
    }

    public String toString() {
        return zzz.zzy(this).zzg("placeId", this.zzaWV).zzg("placeTypes", this.zzaWr).zzg("fullText", this.zzaXu).zzg("fullTextMatchedSubstrings", this.zzaXv).zzg("primaryText", this.zzaXx).zzg("primaryTextMatchedSubstrings", this.zzaXy).zzg("secondaryText", this.zzaXz).zzg("secondaryTextMatchedSubstrings", this.zzaXA).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        zza.zza(this, parcel, i);
    }
}
