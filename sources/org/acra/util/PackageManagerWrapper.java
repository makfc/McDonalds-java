package org.acra.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import org.acra.ACRA;

public final class PackageManagerWrapper {
    private final Context context;

    public PackageManagerWrapper(Context context) {
        this.context = context;
    }

    public final boolean hasPermission(String permission) {
        PackageManager packageManager = this.context.getPackageManager();
        if (packageManager == null) {
            return false;
        }
        try {
            if (packageManager.checkPermission(permission, this.context.getPackageName()) == 0) {
                return true;
            }
            return false;
        } catch (RuntimeException e) {
            return false;
        }
    }

    public final PackageInfo getPackageInfo() {
        PackageInfo packageInfo = null;
        PackageManager packageManager = this.context.getPackageManager();
        if (packageManager == null) {
            return packageInfo;
        }
        try {
            return packageManager.getPackageInfo(this.context.getPackageName(), 0);
        } catch (NameNotFoundException e) {
            ACRA.log.mo23352v(ACRA.LOG_TAG, "Failed to find PackageInfo for current App : " + this.context.getPackageName());
            return packageInfo;
        } catch (RuntimeException e2) {
            return packageInfo;
        }
    }
}
