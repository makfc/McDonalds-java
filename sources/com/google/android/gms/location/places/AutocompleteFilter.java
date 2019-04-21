package com.google.android.gms.location.places;

import android.os.Parcel;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.zzz;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class AutocompleteFilter extends AbstractSafeParcelable {
    public static final zzc CREATOR = new zzc();
    final int mVersionCode;
    final boolean zzaWu;
    final List<Integer> zzaWv;
    final int zzaWw;

    public static final class Builder {
        private boolean zzaWu = false;
        private int zzaWw = 0;

        public AutocompleteFilter build() {
            return new AutocompleteFilter(1, false, Arrays.asList(new Integer[]{Integer.valueOf(this.zzaWw)}));
        }
    }

    AutocompleteFilter(int i, boolean z, List<Integer> list) {
        boolean z2 = true;
        this.mVersionCode = i;
        this.zzaWv = list;
        this.zzaWw = zzg(list);
        if (this.mVersionCode < 1) {
            if (z) {
                z2 = false;
            }
            this.zzaWu = z2;
            return;
        }
        this.zzaWu = z;
    }

    private static int zzg(@Nullable Collection<Integer> collection) {
        return (collection == null || collection.isEmpty()) ? 0 : ((Integer) collection.iterator().next()).intValue();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AutocompleteFilter)) {
            return false;
        }
        return this.zzaWw == this.zzaWw && this.zzaWu == ((AutocompleteFilter) obj).zzaWu;
    }

    public int hashCode() {
        return zzz.hashCode(Boolean.valueOf(this.zzaWu), Integer.valueOf(this.zzaWw));
    }

    public String toString() {
        return zzz.zzy(this).zzg("includeQueryPredictions", Boolean.valueOf(this.zzaWu)).zzg("typeFilter", Integer.valueOf(this.zzaWw)).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzc.zza(this, parcel, i);
    }
}
