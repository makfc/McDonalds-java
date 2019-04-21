package com.mcdonalds.sdk.modules.security;

import android.content.Context;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.connectors.ConnectorManager;
import com.mcdonalds.sdk.connectors.SecurityConnector;
import com.mcdonalds.sdk.modules.BaseModule;
import com.mcdonalds.sdk.services.configuration.Configuration;

public class SecurityModule extends BaseModule {
    public static final String CONNECTOR_KEY = "connector";
    public static final String NAME = "security";
    private SecurityConnector mConnector = ((SecurityConnector) ConnectorManager.getConnector((String) Configuration.getSharedInstance().getValueForKey("modules.security.connector")));
    private Context mContext;

    public SecurityModule(Context context) {
        this.mContext = context;
    }

    public void retriveSecurityToken(String merchantId, AsyncListener<String> sessionIdListener) {
        this.mConnector.retriveSecurityToken(this.mContext, merchantId, sessionIdListener);
    }
}
