package com.mcdonalds.sdk.modules;

import android.support.annotation.NonNull;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.connectors.ConfigurationConnector;
import com.mcdonalds.sdk.connectors.ConnectorManager;
import com.mcdonalds.sdk.modules.models.GeoFencingConfiguration;
import com.mcdonalds.sdk.services.configuration.Configuration;
import java.util.Map;

public class ConfigurationModule extends BaseModule {
    private static final String CONNECTOR_KEY = "connector";
    public static final String NAME = "Configuration";
    private ConfigurationConnector mConnector = ((ConfigurationConnector) ConnectorManager.getConnector((String) Configuration.getSharedInstance().getValueForKey("modules.Configuration.connector")));

    public AsyncToken getServerConfiguration(@NonNull final AsyncListener<Map<String, Object>> listener) {
        final AsyncToken asyncToken = new AsyncToken("getServerConfiguration");
        this.mConnector.getServerConfiguration(new AsyncListener<Map<String, Object>>() {
            public void onResponse(Map<String, Object> response, AsyncToken token, AsyncException exception) {
                if (listener != null) {
                    listener.onResponse(response, asyncToken, exception);
                }
            }
        });
        return asyncToken;
    }

    public void getGeoFencingConfiguration(AsyncListener<GeoFencingConfiguration> listener) {
        if (this.mConnector != null) {
            this.mConnector.getGeoFencingConfiguration(listener);
        }
    }
}
