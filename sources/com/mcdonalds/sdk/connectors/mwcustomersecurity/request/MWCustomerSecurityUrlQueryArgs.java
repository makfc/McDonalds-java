package com.mcdonalds.sdk.connectors.mwcustomersecurity.request;

import android.net.Uri.Builder;
import com.mcdonalds.sdk.connectors.mwcustomersecurity.MWCustomerSecurityConnector;
import java.util.LinkedHashMap;
import java.util.Locale;

public class MWCustomerSecurityUrlQueryArgs extends LinkedHashMap<String, String> {
    private static final String SCHEME = "http";
    private final Builder builder = new Builder();
    private final String mConfigBaseHost;
    private final String mConfigBaseUrl;

    public MWCustomerSecurityUrlQueryArgs(MWCustomerSecurityConnector connector, String path) {
        this.mConfigBaseUrl = connector.getConfigBaseUrl();
        this.mConfigBaseHost = this.mConfigBaseUrl.split("http://")[1];
        this.builder.scheme(SCHEME).encodedAuthority(this.mConfigBaseHost).encodedPath(path).build();
        loadDefaults();
    }

    private void loadDefaults() {
        put("locale", Locale.getDefault().toString().replace('_', '-'));
    }

    public String toString() {
        int count = 0;
        StringBuilder fullQueryPath = new StringBuilder();
        for (String key : keySet()) {
            fullQueryPath.append(key).append("=").append((String) get(key));
            if (count < keySet().size() - 1) {
                fullQueryPath.append("&");
            }
            count++;
        }
        this.builder.encodedQuery(fullQueryPath.toString());
        return this.builder.toString();
    }
}
