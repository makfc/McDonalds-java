package com.android.volley;

import com.facebook.internal.ServerProtocol;

public final class BuildConfig {
    public static final boolean DEBUG = Boolean.parseBoolean(ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
}
