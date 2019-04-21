package com.crashlytics.android.beta;

import android.content.Context;
import p041io.fabric.sdk.android.services.cache.ValueLoader;

@Deprecated
public class DeviceTokenLoader implements ValueLoader<String> {
    public String load(Context context) throws Exception {
        return "";
    }
}
