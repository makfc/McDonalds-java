package com.google.android.gms.location.places;

import android.support.annotation.Nullable;
import com.google.android.gms.common.api.Api.ApiOptions.Optional;

public final class PlacesOptions implements Optional {
    @Nullable
    public final String zzaXl;
    @Nullable
    public final String zzaXm;
    public final int zzaXn;

    public static class Builder {
        private int zzaXn = 0;

        public PlacesOptions build() {
            return new PlacesOptions(this);
        }
    }

    private PlacesOptions(Builder builder) {
        this.zzaXl = null;
        this.zzaXm = null;
        this.zzaXn = 0;
    }
}
