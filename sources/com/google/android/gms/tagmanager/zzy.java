package com.google.android.gms.tagmanager;

import android.util.Log;

public class zzy implements zzbo {
    private int zzXw = 5;

    /* renamed from: e */
    public void mo24895e(String str) {
        if (this.zzXw <= 6) {
            Log.e("GoogleTagManager", str);
        }
    }

    public void setLogLevel(int i) {
        this.zzXw = i;
    }

    /* renamed from: v */
    public void mo24897v(String str) {
        if (this.zzXw <= 2) {
            Log.v("GoogleTagManager", str);
        }
    }

    public void zzaU(String str) {
        if (this.zzXw <= 3) {
            Log.d("GoogleTagManager", str);
        }
    }

    public void zzaV(String str) {
        if (this.zzXw <= 4) {
            Log.i("GoogleTagManager", str);
        }
    }

    public void zzaW(String str) {
        if (this.zzXw <= 5) {
            Log.w("GoogleTagManager", str);
        }
    }

    public void zzb(String str, Throwable th) {
        if (this.zzXw <= 6) {
            Log.e("GoogleTagManager", str, th);
        }
    }

    public void zzd(String str, Throwable th) {
        if (this.zzXw <= 5) {
            Log.w("GoogleTagManager", str, th);
        }
    }
}
