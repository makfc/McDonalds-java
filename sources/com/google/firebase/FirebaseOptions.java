package com.google.firebase;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.internal.zzah;
import com.google.android.gms.common.internal.zzz;
import com.google.android.gms.common.util.zzw;

public final class FirebaseOptions {
    private final String zzaeg;
    private final String zzaoh;
    private final String zzaok;
    private final String zzaol;
    private final String zzbEN;
    private final String zzbEO;

    public static final class Builder {
    }

    private FirebaseOptions(@NonNull String str, @NonNull String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5, @Nullable String str6) {
        zzaa.zza(!zzw.zzdv(str), "ApplicationId must be set.");
        this.zzaeg = str;
        this.zzaoh = str2;
        this.zzbEN = str3;
        this.zzbEO = str4;
        this.zzaok = str5;
        this.zzaol = str6;
    }

    public static FirebaseOptions fromResource(Context context) {
        zzah zzah = new zzah(context);
        String string = zzah.getString("google_app_id");
        return TextUtils.isEmpty(string) ? null : new FirebaseOptions(string, zzah.getString("google_api_key"), zzah.getString("firebase_database_url"), zzah.getString("ga_trackingId"), zzah.getString("gcm_defaultSenderId"), zzah.getString("google_storage_bucket"));
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof FirebaseOptions)) {
            return false;
        }
        FirebaseOptions firebaseOptions = (FirebaseOptions) obj;
        return zzz.equal(this.zzaeg, firebaseOptions.zzaeg) && zzz.equal(this.zzaoh, firebaseOptions.zzaoh) && zzz.equal(this.zzbEN, firebaseOptions.zzbEN) && zzz.equal(this.zzbEO, firebaseOptions.zzbEO) && zzz.equal(this.zzaok, firebaseOptions.zzaok) && zzz.equal(this.zzaol, firebaseOptions.zzaol);
    }

    public String getApplicationId() {
        return this.zzaeg;
    }

    public String getGcmSenderId() {
        return this.zzaok;
    }

    public int hashCode() {
        return zzz.hashCode(this.zzaeg, this.zzaoh, this.zzbEN, this.zzbEO, this.zzaok, this.zzaol);
    }

    public String toString() {
        return zzz.zzy(this).zzg("applicationId", this.zzaeg).zzg("apiKey", this.zzaoh).zzg("databaseUrl", this.zzbEN).zzg("gcmSenderId", this.zzaok).zzg("storageBucket", this.zzaol).toString();
    }
}
