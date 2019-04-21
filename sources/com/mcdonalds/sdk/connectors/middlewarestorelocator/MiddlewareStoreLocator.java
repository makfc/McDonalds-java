package com.mcdonalds.sdk.connectors.middlewarestorelocator;

import android.net.Uri;
import android.net.Uri.Builder;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MiddlewareStoreLocator {
    private List<String> mAttributeParams;
    private Map<String, String> mBaseParams;
    private Map<String, String> mLocationParams;
    private Builder mUriBuilder;

    public MiddlewareStoreLocator(String baseUrl, String market, String locale, int pageSize) {
        this.mUriBuilder = Uri.parse(baseUrl).buildUpon();
        setBaseParams(market, locale, pageSize);
    }

    public MiddlewareStoreLocator searchByDistance(double latitude, double longitude, int distance) {
        addSearchParam(MiddlewareStoreLocatorLocationParam.LATITUDE, String.valueOf(latitude));
        addSearchParam(MiddlewareStoreLocatorLocationParam.LONGITUDE, String.valueOf(longitude));
        addSearchParam(MiddlewareStoreLocatorLocationParam.DISTANCE, String.valueOf(distance));
        return this;
    }

    public void addSearchParam(MiddlewareStoreLocatorSearchParam param, String value) {
        this.mBaseParams.put(param.toString(), value);
    }

    public void addSearchParam(MiddlewareStoreLocatorLocationParam param, String value) {
        if (this.mLocationParams == null) {
            this.mLocationParams = new LinkedHashMap();
        }
        this.mLocationParams.put(param.toString(), value);
    }

    public void addFilterParam(String filter) {
        if (this.mAttributeParams == null) {
            this.mAttributeParams = new ArrayList();
        }
        this.mAttributeParams.add(filter);
    }

    private void setBaseParams(String market, String locale, int pageSize) {
        if (this.mBaseParams == null) {
            this.mBaseParams = new LinkedHashMap();
        }
        this.mBaseParams.put(MiddlewareStoreLocatorSearchParam.Market.toString(), market);
        this.mBaseParams.put(MiddlewareStoreLocatorSearchParam.PageSize.toString(), String.valueOf(pageSize));
        this.mBaseParams.put(MiddlewareStoreLocatorSearchParam.Locale.toString(), locale);
    }

    public MiddlewareStoreLocator setAttributes(List<String> attributes) {
        if (this.mAttributeParams == null) {
            this.mAttributeParams = new ArrayList();
        }
        for (String attributeValue : attributes) {
            this.mAttributeParams.add(attributeValue);
        }
        return this;
    }

    public String build() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('{');
        buildBaseJSON(stringBuilder);
        buildLocationJSON(stringBuilder);
        buildAttributeJSON(stringBuilder);
        stringBuilder.append('}');
        this.mUriBuilder.appendQueryParameter(MiddlewareStoreLocatorSearchParam.Query.toString(), stringBuilder.toString());
        return this.mUriBuilder.build().toString();
    }

    private void buildBaseJSON(StringBuilder stringBuilder) {
        int baseCount = 0;
        for (String key : this.mBaseParams.keySet()) {
            String value = (String) this.mBaseParams.get(key);
            stringBuilder.append('\"');
            stringBuilder.append(key);
            stringBuilder.append("\":\"");
            stringBuilder.append(value);
            stringBuilder.append('\"');
            baseCount++;
            if (baseCount < this.mBaseParams.size()) {
                stringBuilder.append(',');
            }
        }
    }

    private void buildLocationJSON(StringBuilder stringBuilder) {
        if (this.mLocationParams != null && !this.mLocationParams.isEmpty()) {
            stringBuilder.append(",\"locationCriteria\":{");
            int locationCount = 0;
            for (String key : this.mLocationParams.keySet()) {
                String value = (String) this.mLocationParams.get(key);
                stringBuilder.append('\"');
                stringBuilder.append(key);
                stringBuilder.append("\":\"");
                stringBuilder.append(value);
                stringBuilder.append('\"');
                locationCount++;
                if (locationCount < this.mLocationParams.size()) {
                    stringBuilder.append(',');
                }
            }
            stringBuilder.append('}');
        }
    }

    private void buildAttributeJSON(StringBuilder stringBuilder) {
        if (this.mAttributeParams != null && !this.mAttributeParams.isEmpty()) {
            stringBuilder.append(",\"storeAttributes\":[");
            int attributeCount = 0;
            for (String value : this.mAttributeParams) {
                stringBuilder.append("{\"type\":\"");
                stringBuilder.append(value);
                stringBuilder.append("\"}");
                attributeCount++;
                if (attributeCount < this.mAttributeParams.size()) {
                    stringBuilder.append(',');
                }
            }
            stringBuilder.append(']');
        }
    }
}
