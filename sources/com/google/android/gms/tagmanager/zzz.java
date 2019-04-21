package com.google.android.gms.tagmanager;

import android.content.Context;

public class zzz implements zzas {
    private static final Object zzbnE = new Object();
    private static zzz zzboR;
    private zzat zzboS;
    private zzck zzboh;

    private zzz(Context context) {
        this(zzau.zzbo(context), new zzcz());
    }

    zzz(zzat zzat, zzck zzck) {
        this.zzboS = zzat;
        this.zzboh = zzck;
    }

    public static zzas zzbh(Context context) {
        zzz zzz;
        synchronized (zzbnE) {
            if (zzboR == null) {
                zzboR = new zzz(context);
            }
            zzz = zzboR;
        }
        return zzz;
    }

    public boolean zzgw(String str) {
        if (this.zzboh.zznY()) {
            this.zzboS.zzgA(str);
            return true;
        }
        zzbn.zzaW("Too many urls sent too quickly with the TagManagerSender, rate limiting invoked.");
        return false;
    }
}
