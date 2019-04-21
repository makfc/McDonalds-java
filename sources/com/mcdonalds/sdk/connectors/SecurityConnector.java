package com.mcdonalds.sdk.connectors;

import android.content.Context;
import com.mcdonalds.sdk.AsyncListener;

public interface SecurityConnector {
    void retriveSecurityToken(Context context, String str, AsyncListener<String> asyncListener);
}
