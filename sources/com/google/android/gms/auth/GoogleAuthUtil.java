package com.google.android.gms.auth;

import android.content.Context;
import java.io.IOException;

public final class GoogleAuthUtil extends zzd {
    public static final String KEY_ANDROID_PACKAGE_NAME = zzd.KEY_ANDROID_PACKAGE_NAME;
    public static final String KEY_CALLER_UID = zzd.KEY_CALLER_UID;

    private GoogleAuthUtil() {
    }

    @Deprecated
    public static String getToken(Context context, String str, String str2) throws IOException, UserRecoverableAuthException, GoogleAuthException {
        return zzd.getToken(context, str, str2);
    }
}
