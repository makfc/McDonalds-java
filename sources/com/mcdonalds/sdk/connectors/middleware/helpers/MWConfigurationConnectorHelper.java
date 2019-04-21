package com.mcdonalds.sdk.connectors.middleware.helpers;

import android.support.annotation.NonNull;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.connectors.ConfigurationConnector;
import com.mcdonalds.sdk.connectors.middleware.request.MWGeoFencingConfigurationRequest;
import com.mcdonalds.sdk.connectors.middleware.request.MWServerConfigurationRequest;
import com.mcdonalds.sdk.connectors.middleware.response.MWGeoFencingConfigurationResponse;
import com.mcdonalds.sdk.modules.models.GeoFencingConfiguration;
import com.mcdonalds.sdk.services.network.SessionManager;
import java.util.Map;

public class MWConfigurationConnectorHelper implements ConfigurationConnector {
    private final MWConnectorShared mSharedData;

    public MWConfigurationConnectorHelper(MWConnectorShared sharedData) {
        this.mSharedData = sharedData;
    }

    public void getServerConfiguration(@NonNull final AsyncListener<Map<String, Object>> listener) {
        MWServerConfigurationRequest serverConfigRequest = new MWServerConfigurationRequest(SessionManager.getInstance().getToken());
        if (serverConfigRequest.isValidRequest()) {
            this.mSharedData.getNetworkConnection().processRequest(serverConfigRequest, new AsyncListener<Map<String, Object>>() {
                public void onResponse(Map<String, Object> response, AsyncToken token, AsyncException exception) {
                    listener.onResponse(response, token, exception);
                }
            });
        } else {
            listener.onResponse(null, null, new AsyncException("Not A Valid Request"));
        }
    }

    public void getGeoFencingConfiguration(final AsyncListener<GeoFencingConfiguration> listener) {
        MWGeoFencingConfigurationRequest configRequest = new MWGeoFencingConfigurationRequest();
        if (configRequest.isValidRequest()) {
            this.mSharedData.getNetworkConnection().processRequest(configRequest, new AsyncListener<MWGeoFencingConfigurationResponse>() {
                public void onResponse(MWGeoFencingConfigurationResponse response, AsyncToken token, AsyncException exception) {
                    if (listener != null) {
                        listener.onResponse(GeoFencingConfiguration.fromMWGeoFencingConfiguration(response), token, exception);
                    }
                }
            });
        } else {
            listener.onResponse(null, null, new AsyncException("Not A Valid Request"));
        }
    }
}
