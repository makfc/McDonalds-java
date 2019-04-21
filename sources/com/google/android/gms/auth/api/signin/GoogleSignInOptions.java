package com.google.android.gms.auth.api.signin;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.google.android.gms.auth.api.signin.internal.zze;
import com.google.android.gms.common.api.Api.ApiOptions.Optional;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class GoogleSignInOptions extends AbstractSafeParcelable implements Optional {
    public static final Creator<GoogleSignInOptions> CREATOR = new zzb();
    public static final GoogleSignInOptions DEFAULT_SIGN_IN = new Builder().requestId().requestProfile().build();
    private static Comparator<Scope> zzacv = new C21011();
    public static final Scope zzacw = new Scope("profile");
    public static final Scope zzacx = new Scope("email");
    public static final Scope zzacy = new Scope("openid");
    final int versionCode;
    private Account zzZB;
    private boolean zzacA;
    private final boolean zzacB;
    private final boolean zzacC;
    private String zzacD;
    private String zzacE;
    private final ArrayList<Scope> zzacz;

    /* renamed from: com.google.android.gms.auth.api.signin.GoogleSignInOptions$1 */
    class C21011 implements Comparator<Scope> {
        C21011() {
        }

        /* renamed from: zza */
        public int compare(Scope scope, Scope scope2) {
            return scope.zzrw().compareTo(scope2.zzrw());
        }
    }

    public static final class Builder {
        private Account zzZB;
        private boolean zzacA;
        private boolean zzacB;
        private boolean zzacC;
        private String zzacD;
        private String zzacE;
        private Set<Scope> zzacF = new HashSet();

        public GoogleSignInOptions build() {
            if (this.zzacA && (this.zzZB == null || !this.zzacF.isEmpty())) {
                requestId();
            }
            return new GoogleSignInOptions(this.zzacF, this.zzZB, this.zzacA, this.zzacB, this.zzacC, this.zzacD, this.zzacE, null);
        }

        public Builder requestId() {
            this.zzacF.add(GoogleSignInOptions.zzacy);
            return this;
        }

        public Builder requestProfile() {
            this.zzacF.add(GoogleSignInOptions.zzacw);
            return this;
        }
    }

    GoogleSignInOptions(int i, ArrayList<Scope> arrayList, Account account, boolean z, boolean z2, boolean z3, String str, String str2) {
        this.versionCode = i;
        this.zzacz = arrayList;
        this.zzZB = account;
        this.zzacA = z;
        this.zzacB = z2;
        this.zzacC = z3;
        this.zzacD = str;
        this.zzacE = str2;
    }

    private GoogleSignInOptions(Set<Scope> set, Account account, boolean z, boolean z2, boolean z3, String str, String str2) {
        this(2, new ArrayList(set), account, z, z2, z3, str, str2);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        try {
            GoogleSignInOptions googleSignInOptions = (GoogleSignInOptions) obj;
            if (this.zzacz.size() != googleSignInOptions.zzpj().size() || !this.zzacz.containsAll(googleSignInOptions.zzpj())) {
                return false;
            }
            if (this.zzZB == null) {
                if (googleSignInOptions.getAccount() != null) {
                    return false;
                }
            } else if (!this.zzZB.equals(googleSignInOptions.getAccount())) {
                return false;
            }
            if (TextUtils.isEmpty(this.zzacD)) {
                if (!TextUtils.isEmpty(googleSignInOptions.zzpn())) {
                    return false;
                }
            } else if (!this.zzacD.equals(googleSignInOptions.zzpn())) {
                return false;
            }
            return this.zzacC == googleSignInOptions.zzpm() && this.zzacA == googleSignInOptions.zzpk() && this.zzacB == googleSignInOptions.zzpl();
        } catch (ClassCastException e) {
            return false;
        }
    }

    public Account getAccount() {
        return this.zzZB;
    }

    public int hashCode() {
        ArrayList arrayList = new ArrayList();
        Iterator it = this.zzacz.iterator();
        while (it.hasNext()) {
            arrayList.add(((Scope) it.next()).zzrw());
        }
        Collections.sort(arrayList);
        return new zze().zzq(arrayList).zzq(this.zzZB).zzq(this.zzacD).zzX(this.zzacC).zzX(this.zzacA).zzX(this.zzacB).zzpv();
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzb.zza(this, parcel, i);
    }

    public ArrayList<Scope> zzpj() {
        return new ArrayList(this.zzacz);
    }

    public boolean zzpk() {
        return this.zzacA;
    }

    public boolean zzpl() {
        return this.zzacB;
    }

    public boolean zzpm() {
        return this.zzacC;
    }

    public String zzpn() {
        return this.zzacD;
    }

    public String zzpo() {
        return this.zzacE;
    }
}
