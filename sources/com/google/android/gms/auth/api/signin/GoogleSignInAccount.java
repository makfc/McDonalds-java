package com.google.android.gms.auth.api.signin;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.util.zze;
import com.google.android.gms.common.util.zzh;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GoogleSignInAccount extends AbstractSafeParcelable {
    public static final Creator<GoogleSignInAccount> CREATOR = new zza();
    public static zze zzacm = zzh.zzuW();
    private static Comparator<Scope> zzacv = new C21001();
    final int versionCode;
    private String zzBc;
    private String zzabK;
    List<Scope> zzabj;
    private String zzacn;
    private String zzaco;
    private Uri zzacp;
    private String zzacq;
    private long zzacr;
    private String zzacs;
    private String zzact;
    private String zzacu;

    /* renamed from: com.google.android.gms.auth.api.signin.GoogleSignInAccount$1 */
    class C21001 implements Comparator<Scope> {
        C21001() {
        }

        /* renamed from: zza */
        public int compare(Scope scope, Scope scope2) {
            return scope.zzrw().compareTo(scope2.zzrw());
        }
    }

    GoogleSignInAccount(int i, String str, String str2, String str3, String str4, Uri uri, String str5, long j, String str6, List<Scope> list, String str7, String str8) {
        this.versionCode = i;
        this.zzBc = str;
        this.zzabK = str2;
        this.zzacn = str3;
        this.zzaco = str4;
        this.zzacp = uri;
        this.zzacq = str5;
        this.zzacr = j;
        this.zzacs = str6;
        this.zzabj = list;
        this.zzact = str7;
        this.zzacu = str8;
    }

    public static GoogleSignInAccount zza(@Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5, @Nullable String str6, @Nullable Uri uri, @Nullable Long l, @NonNull String str7, @NonNull Set<Scope> set) {
        if (l == null) {
            l = Long.valueOf(zzacm.currentTimeMillis() / 1000);
        }
        return new GoogleSignInAccount(3, str, str2, str3, str4, uri, null, l.longValue(), zzaa.zzdl(str7), new ArrayList((Collection) zzaa.zzz(set)), str5, str6);
    }

    @Nullable
    public static GoogleSignInAccount zzcm(@Nullable String str) throws JSONException {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        JSONObject init = JSONObjectInstrumentation.init(str);
        String optString = init.optString("photoUrl", null);
        Uri parse = !TextUtils.isEmpty(optString) ? Uri.parse(optString) : null;
        long parseLong = Long.parseLong(init.getString("expirationTime"));
        HashSet hashSet = new HashSet();
        JSONArray jSONArray = init.getJSONArray("grantedScopes");
        int length = jSONArray.length();
        for (int i = 0; i < length; i++) {
            hashSet.add(new Scope(jSONArray.getString(i)));
        }
        return zza(init.optString("id"), init.optString("tokenId", null), init.optString("email", null), init.optString("displayName", null), init.optString("givenName", null), init.optString("familyName", null), parse, Long.valueOf(parseLong), init.getString("obfuscatedIdentifier"), hashSet).zzcn(init.optString("serverAuthCode", null));
    }

    private JSONObject zzpi() {
        JSONObject jSONObject = new JSONObject();
        try {
            if (getId() != null) {
                jSONObject.put("id", getId());
            }
            if (getIdToken() != null) {
                jSONObject.put("tokenId", getIdToken());
            }
            if (getEmail() != null) {
                jSONObject.put("email", getEmail());
            }
            if (getDisplayName() != null) {
                jSONObject.put("displayName", getDisplayName());
            }
            if (getGivenName() != null) {
                jSONObject.put("givenName", getGivenName());
            }
            if (getFamilyName() != null) {
                jSONObject.put("familyName", getFamilyName());
            }
            if (getPhotoUrl() != null) {
                jSONObject.put("photoUrl", getPhotoUrl().toString());
            }
            if (getServerAuthCode() != null) {
                jSONObject.put("serverAuthCode", getServerAuthCode());
            }
            jSONObject.put("expirationTime", this.zzacr);
            jSONObject.put("obfuscatedIdentifier", zzpf());
            JSONArray jSONArray = new JSONArray();
            Collections.sort(this.zzabj, zzacv);
            for (Scope zzrw : this.zzabj) {
                jSONArray.put(zzrw.zzrw());
            }
            jSONObject.put("grantedScopes", jSONArray);
            return jSONObject;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean equals(Object obj) {
        return !(obj instanceof GoogleSignInAccount) ? false : ((GoogleSignInAccount) obj).zzpg().equals(zzpg());
    }

    @Nullable
    public String getDisplayName() {
        return this.zzaco;
    }

    @Nullable
    public String getEmail() {
        return this.zzacn;
    }

    @Nullable
    public String getFamilyName() {
        return this.zzacu;
    }

    @Nullable
    public String getGivenName() {
        return this.zzact;
    }

    @Nullable
    public String getId() {
        return this.zzBc;
    }

    @Nullable
    public String getIdToken() {
        return this.zzabK;
    }

    @Nullable
    public Uri getPhotoUrl() {
        return this.zzacp;
    }

    @Nullable
    public String getServerAuthCode() {
        return this.zzacq;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zza.zza(this, parcel, i);
    }

    public GoogleSignInAccount zzcn(String str) {
        this.zzacq = str;
        return this;
    }

    public long zzpe() {
        return this.zzacr;
    }

    @NonNull
    public String zzpf() {
        return this.zzacs;
    }

    public String zzpg() {
        JSONObject zzpi = zzpi();
        return !(zzpi instanceof JSONObject) ? zzpi.toString() : JSONObjectInstrumentation.toString(zzpi);
    }
}
