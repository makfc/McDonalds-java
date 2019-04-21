package com.google.firebase.auth;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

public class UserProfileChangeRequest extends AbstractSafeParcelable {
    public static final Creator<UserProfileChangeRequest> CREATOR = new zza();
    public final int mVersionCode;
    private String zzaBl;
    private String zzaco;
    private boolean zzbFl;
    private boolean zzbFm;
    private Uri zzbFn;

    public static class Builder {
    }

    UserProfileChangeRequest(int i, String str, String str2, boolean z, boolean z2) {
        this.mVersionCode = i;
        this.zzaco = str;
        this.zzaBl = str2;
        this.zzbFl = z;
        this.zzbFm = z2;
        this.zzbFn = TextUtils.isEmpty(str2) ? null : Uri.parse(str2);
    }

    @Nullable
    public String getDisplayName() {
        return this.zzaco;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zza.zza(this, parcel, i);
    }

    public String zzOo() {
        return this.zzaBl;
    }

    public boolean zzOp() {
        return this.zzbFl;
    }

    public boolean zzOq() {
        return this.zzbFm;
    }
}
