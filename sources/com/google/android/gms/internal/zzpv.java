package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import com.google.android.gms.common.util.zzs;

public class zzpv {
    protected final Context mContext;

    public zzpv(Context context) {
        this.mContext = context;
    }

    public ApplicationInfo getApplicationInfo(String str, int i) throws NameNotFoundException {
        return this.mContext.getPackageManager().getApplicationInfo(str, i);
    }

    public PackageInfo getPackageInfo(String str, int i) throws NameNotFoundException {
        return this.mContext.getPackageManager().getPackageInfo(str, i);
    }

    @TargetApi(19)
    public boolean zzg(int i, String str) {
        if (zzs.zzve()) {
            try {
                ((AppOpsManager) this.mContext.getSystemService("appops")).checkPackage(i, str);
                return true;
            } catch (SecurityException e) {
                return false;
            }
        }
        String[] packagesForUid = this.mContext.getPackageManager().getPackagesForUid(i);
        if (str == null || packagesForUid == null) {
            return false;
        }
        for (Object equals : packagesForUid) {
            if (str.equals(equals)) {
                return true;
            }
        }
        return false;
    }
}
