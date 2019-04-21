package com.mcdonalds.sdk.connectors.mwcustomersecurity.request;

import com.mcdonalds.sdk.connectors.middleware.MiddlewareConnector;
import com.mcdonalds.sdk.connectors.mwcustomersecurity.MWCustomerSecurityConnector;
import com.mcdonalds.sdk.services.configuration.Configuration;
import java.util.LinkedHashMap;
import java.util.Locale;

public class MWCustomerSecurityRequestHeaders extends LinkedHashMap<String, String> {
    private final String JSON_CONTENT_TYPE = "application/json";
    private final String mConfigBasePath = MWCustomerSecurityConnector.CONFIG;

    public MWCustomerSecurityRequestHeaders() {
        putDefaults(Configuration.getSharedInstance().getStringForKey(this.mConfigBasePath + ".mcd_apikey"));
    }

    public MWCustomerSecurityRequestHeaders(String apiKeyPath) {
        putDefaults(Configuration.getSharedInstance().getStringForKey(apiKeyPath + ".mcd_apikey"));
    }

    /* Access modifiers changed, original: 0000 */
    public void putDefaults(String apiKey) {
        String locale = Locale.getDefault().toString().replace('_', '-');
        put(MiddlewareConnector.CONFIG_HEADER_API_KEY, apiKey);
        put("MarketId", Configuration.getSharedInstance().getValueForKey(this.mConfigBasePath + ".marketId"));
        put("mcd-clientid", (String) Configuration.getSharedInstance().getValueForKey(this.mConfigBasePath + ".mcd-clientid"));
        put("mcd-clientsecret", (String) Configuration.getSharedInstance().getValueForKey(this.mConfigBasePath + ".mcd-clientsecret"));
        put("locale", locale);
    }
}
