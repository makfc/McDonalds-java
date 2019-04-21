package com.mcdonalds.sdk;

import android.app.Application;
import android.content.Context;

@Deprecated
public class SDKApplication extends Application {
    private static SDKApplication mInstance;

    @Deprecated
    public SDKApplication() {
        mInstance = this;
    }

    @Deprecated
    public static Context getContext() {
        return mInstance;
    }
}
