package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.content.Context;
import android.view.View;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient.Builder;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.internal.zztw;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public final class zzg {
    private final Account zzZB;
    private final String zzaaN;
    private final Set<Scope> zzald;
    private final int zzalf;
    private final View zzalg;
    private final String zzalh;
    private final Set<Scope> zzaro;
    private final Map<Api<?>, zza> zzarp;
    private final zztw zzarq;
    private Integer zzarr;

    public static final class zza {
        public final Set<Scope> zzacF;
        public final boolean zzars;
    }

    public zzg(Account account, Set<Scope> set, Map<Api<?>, zza> map, int i, View view, String str, String str2, zztw zztw) {
        Map map2;
        this.zzZB = account;
        this.zzald = set == null ? Collections.EMPTY_SET : Collections.unmodifiableSet(set);
        if (map2 == null) {
            map2 = Collections.EMPTY_MAP;
        }
        this.zzarp = map2;
        this.zzalg = view;
        this.zzalf = i;
        this.zzaaN = str;
        this.zzalh = str2;
        this.zzarq = zztw;
        HashSet hashSet = new HashSet(this.zzald);
        for (zza zza : this.zzarp.values()) {
            hashSet.addAll(zza.zzacF);
        }
        this.zzaro = Collections.unmodifiableSet(hashSet);
    }

    public static zzg zzau(Context context) {
        return new Builder(context).zzrt();
    }

    public Account getAccount() {
        return this.zzZB;
    }

    public Set<Scope> zzb(Api<?> api) {
        zza zza = (zza) this.zzarp.get(api);
        if (zza == null || zza.zzacF.isEmpty()) {
            return this.zzald;
        }
        HashSet hashSet = new HashSet(this.zzald);
        hashSet.addAll(zza.zzacF);
        return hashSet;
    }

    public void zzc(Integer num) {
        this.zzarr = num;
    }

    public zztw zztB() {
        return this.zzarq;
    }

    public Integer zztC() {
        return this.zzarr;
    }

    public Account zztk() {
        return this.zzZB != null ? this.zzZB : new Account("<<default account>>", "com.google");
    }

    public Set<Scope> zztv() {
        return this.zzald;
    }

    public Set<Scope> zztw() {
        return this.zzaro;
    }

    public Map<Api<?>, zza> zztx() {
        return this.zzarp;
    }

    public String zzty() {
        return this.zzaaN;
    }

    public String zztz() {
        return this.zzalh;
    }
}
