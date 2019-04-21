package com.mcdonalds.sdk.connectors;

import android.support.annotation.NonNull;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.modules.models.GeoFencingConfiguration;
import java.util.Map;

public interface ConfigurationConnector {
    void getGeoFencingConfiguration(AsyncListener<GeoFencingConfiguration> asyncListener);

    void getServerConfiguration(@NonNull AsyncListener<Map<String, Object>> asyncListener);
}
