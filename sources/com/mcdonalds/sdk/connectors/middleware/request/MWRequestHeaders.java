package com.mcdonalds.sdk.connectors.middleware.request;

import android.text.TextUtils;
import com.mcdonalds.sdk.connectors.middleware.MiddlewareConnector;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.mcdonalds.sdk.services.network.GsonRequest;
import com.mcdonalds.sdk.services.network.SessionManager;
import java.util.LinkedHashMap;

public class MWRequestHeaders extends LinkedHashMap<String, String> {
    private static final String API_VERSIONS_CONFIG_PATH = ".apiVersions";
    private static final String CONFIG_HEADER_MARKET_ID = ".headerMarketId";
    private static final String CONFIG_MARKET_ID = ".marketId";
    private static final String CONFIG_MCD_API_KEY = ".mcd_apikey";
    private static final String DCS_CONFIG_PATH = ".DCSSecurity";
    private static final String HEADER_DCS_API_UID = "mcd-apiuid";
    private static final String HEADER_DCS_LOCALE = "mcd-locale";
    private static final String HEADER_DCS_MARKET_ID = "mcd-marketid";
    private static final String HEADER_DCS_SOURCE_APP = "mcd-sourceapp";
    private static final String HEADER_DCS_TOKEN = "Authorization";
    private static final String HEADER_MARKET_ID = "MarketId";
    private static final String HEADER_MCD_API = "mcd_apikey";
    private final String JSON_CONTENT_TYPE;
    private final String mAPIVersionsConfigBasePath;
    private final String mConfigBasePath;
    private final String mDCSConfigBasePath;

    @Deprecated
    public MWRequestHeaders(MiddlewareConnector ignored) {
        this();
    }

    public MWRequestHeaders() {
        this.JSON_CONTENT_TYPE = "application/json";
        this.mConfigBasePath = MiddlewareConnector.CONFIG_BASE_PATH;
        this.mDCSConfigBasePath = this.mConfigBasePath + DCS_CONFIG_PATH;
        this.mAPIVersionsConfigBasePath = this.mConfigBasePath + API_VERSIONS_CONFIG_PATH;
        putDefaults(null, null, null, null, null);
    }

    @Deprecated
    public MWRequestHeaders(MiddlewareConnector ignored, String ecpToken) {
        this(ecpToken);
    }

    public MWRequestHeaders(String ecpToken) {
        this(ecpToken, null);
    }

    @Deprecated
    public MWRequestHeaders(MiddlewareConnector ignored, String ecpToken, String nonce) {
        this(ecpToken, nonce);
    }

    public MWRequestHeaders(String ecpToken, String nonce) {
        this(ecpToken, nonce, null);
    }

    public MWRequestHeaders(String ecpToken, String nonce, String version) {
        this(ecpToken, nonce, version, null);
    }

    public MWRequestHeaders(String ecpToken, String nonce, String version, String apiKey) {
        this(ecpToken, nonce, version, apiKey, null);
    }

    public MWRequestHeaders(String ecpToken, String nonce, String version, String apiKey, String guestApiKey) {
        this.JSON_CONTENT_TYPE = "application/json";
        this.mConfigBasePath = MiddlewareConnector.CONFIG_BASE_PATH;
        this.mDCSConfigBasePath = this.mConfigBasePath + DCS_CONFIG_PATH;
        this.mAPIVersionsConfigBasePath = this.mConfigBasePath + API_VERSIONS_CONFIG_PATH;
        putDefaults(ecpToken, nonce, version, apiKey, guestApiKey);
    }

    /* Access modifiers changed, original: 0000 */
    public void putDefaults(String mwToken, String nonce, String version, String apiKey, String guestApiKey) {
        String versionBasePath = this.mAPIVersionsConfigBasePath + "." + version;
        if (!SessionManager.getInstance().isTokenAuthenticated()) {
            apiKey = guestApiKey;
        }
        if (apiKey == null) {
            apiKey = Configuration.getSharedInstance().getStringForKey(versionBasePath + CONFIG_MCD_API_KEY);
            if (apiKey == null) {
                apiKey = Configuration.getSharedInstance().getStringForKey(this.mConfigBasePath + CONFIG_MCD_API_KEY);
            }
        }
        put("mcd_apikey", apiKey);
        if (Configuration.getSharedInstance().hasKey(this.mConfigBasePath + CONFIG_HEADER_MARKET_ID)) {
            String headerMarketId = Configuration.getSharedInstance().getStringForKey(versionBasePath + CONFIG_HEADER_MARKET_ID);
            if (headerMarketId == null) {
                headerMarketId = Configuration.getSharedInstance().getStringForKey(this.mConfigBasePath + CONFIG_HEADER_MARKET_ID);
            }
            if (!TextUtils.isEmpty(headerMarketId)) {
                put(HEADER_MARKET_ID, headerMarketId);
                if (isUsingDCS()) {
                    put(HEADER_DCS_MARKET_ID, headerMarketId);
                }
            }
        } else {
            String marketId = Configuration.getSharedInstance().getStringForKey(versionBasePath + CONFIG_MARKET_ID);
            if (marketId == null) {
                marketId = Configuration.getSharedInstance().getStringForKey(this.mConfigBasePath + CONFIG_MARKET_ID);
            }
            put(HEADER_MARKET_ID, marketId);
            if (isUsingDCS()) {
                put(HEADER_DCS_MARKET_ID, marketId);
            }
        }
        if (mwToken != null) {
            if (isUsingDCS()) {
                put(HEADER_DCS_TOKEN, "Bearer " + mwToken);
            } else {
                put(GsonRequest.HEADER_PARAM_TOKEN, mwToken);
            }
        }
        if (nonce != null) {
            put("Nonce", nonce);
        }
        if (isUsingDCS()) {
            put(HEADER_DCS_API_UID, Configuration.getSharedInstance().getStringForKey(this.mDCSConfigBasePath + ".apiuid"));
            put(HEADER_DCS_SOURCE_APP, Configuration.getSharedInstance().getStringForKey(this.mConfigBasePath + ".application"));
            put(HEADER_DCS_LOCALE, Configuration.getSharedInstance().getCurrentLanguageTag());
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void putDefaults() {
        put("mcd_apikey", Configuration.getSharedInstance().getValueForKey(this.mConfigBasePath + CONFIG_MCD_API_KEY));
        put(HEADER_MARKET_ID, Configuration.getSharedInstance().getValueForKey(this.mConfigBasePath + CONFIG_MARKET_ID));
    }

    private boolean isUsingDCS() {
        return Configuration.getSharedInstance().hasKey(this.mDCSConfigBasePath);
    }
}
