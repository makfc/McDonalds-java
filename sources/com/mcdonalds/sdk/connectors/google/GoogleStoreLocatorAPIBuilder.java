package com.mcdonalds.sdk.connectors.google;

import com.facebook.stetho.common.Utf8Charset;
import com.mcdonalds.sdk.connectors.autonavi.AutoNavi.Parameters;
import com.mcdonalds.sdk.connectors.middleware.model.DCSProfile;
import com.mcdonalds.sdk.services.log.MCDLog;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public final class GoogleStoreLocatorAPIBuilder {
    private static final String INTERSECTS_KEY = "intersects";
    private static final String LIMIT_KEY = "limit";
    private static final String MAX_RESULTS_KEY = "maxResults";
    private static final String ORDERBY_KEY = "orderBy";
    private static final String SELECT_KEY = "select";
    private static final String STORE_KEY = "NatlStrNumber";
    private static final String WHERE_KEY = "where";
    private String mAPIKey;
    private String mAPIMethod;
    private String mBaseURL;
    private final List<String> mEncodingExclusionKeys = new ArrayList();
    private String mEndPoint;
    private final Map<String, String> mFilters = new HashMap();
    private String mMaxResults;
    private final Map<String, String> mParameters = new LinkedHashMap();
    private String mStoreId;
    private String mTableId;
    private StringBuilder mURLBuilder;

    private GoogleStoreLocatorAPIBuilder() {
    }

    public GoogleStoreLocatorAPIBuilder(String baseURL, String endPoint, String tableId, String APIMethod, String APIKey, String maxResults) {
        this.mEncodingExclusionKeys.add(SELECT_KEY);
        this.mBaseURL = baseURL;
        this.mEndPoint = endPoint;
        this.mTableId = tableId;
        this.mAPIMethod = APIMethod;
        this.mAPIKey = APIKey;
        this.mMaxResults = maxResults;
        resetUrlBuilder();
        this.mParameters.put("version", "published");
        this.mParameters.put(Parameters.API_KEY, this.mAPIKey);
    }

    public void setArea(Double latitude, Double longitude, Double radius) {
        this.mParameters.put(INTERSECTS_KEY, "CIRCLE(" + longitude + " " + latitude + ", " + radius + ")");
        String selectString = null;
        try {
            selectString = "geometry,AddressLine,EntityID,PrimaryCity,Subdivision,PostalCode,CountryRegion,StoreType,PlayLand,DriveThru,WiFi,GiftCards,MobileOffers,MobileOrdering,storeURL,applicationURL,PhoneNumber,NatlStrNumber,SiteIdNumber,Region," + URLEncoder.encode("ST_DISTANCE(geometry,ST_POINT(" + longitude + "," + latitude + ")) AS distance", Utf8Charset.NAME);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.mParameters.put(SELECT_KEY, selectString);
        this.mParameters.put(ORDERBY_KEY, "distance");
        this.mParameters.put("limit", this.mMaxResults);
        this.mParameters.put(MAX_RESULTS_KEY, this.mMaxResults);
        this.mStoreId = null;
        resetUrlBuilder();
    }

    public void setStore(String storeId) {
        this.mStoreId = storeId;
        if (this.mParameters.containsKey(INTERSECTS_KEY)) {
            this.mParameters.remove(INTERSECTS_KEY);
            this.mParameters.remove(SELECT_KEY);
            this.mParameters.remove(ORDERBY_KEY);
        }
        resetUrlBuilder();
    }

    public void setFilter(GoogleAPIFilter filterType, Boolean value) {
        this.mFilters.put(filterType.toString(), value.booleanValue() ? DCSProfile.INDICATOR_TRUE : DCSProfile.INDICATOR_FALSE);
    }

    public void clearFilters() {
        this.mFilters.clear();
        this.mParameters.remove(WHERE_KEY);
    }

    public String toString() {
        Iterator<Entry<String, String>> iterator;
        Entry<String, String> entry;
        StringBuilder builder = new StringBuilder(this.mURLBuilder.toString());
        if (this.mStoreId == null && this.mFilters.size() > 0) {
            StringBuilder filterBuilder = new StringBuilder();
            iterator = this.mFilters.entrySet().iterator();
            while (iterator.hasNext()) {
                entry = (Entry) iterator.next();
                filterBuilder.append((String) entry.getKey()).append("='").append((String) entry.getValue()).append('\'');
                if (iterator.hasNext()) {
                    filterBuilder.append(" AND ");
                }
            }
            this.mParameters.put(WHERE_KEY, filterBuilder.toString());
        }
        iterator = this.mParameters.entrySet().iterator();
        while (iterator.hasNext()) {
            entry = (Entry) iterator.next();
            try {
                if (this.mEncodingExclusionKeys.contains(entry.getKey())) {
                    builder.append((String) entry.getKey()).append('=').append((String) entry.getValue());
                } else {
                    builder.append((String) entry.getKey()).append('=').append(URLEncoder.encode((String) entry.getValue(), Utf8Charset.NAME));
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            if (iterator.hasNext()) {
                builder.append('&');
            }
        }
        String result = builder.toString();
        MCDLog.debug(result);
        return result;
    }

    private void resetUrlBuilder() {
        this.mURLBuilder = new StringBuilder();
        appendURLComponentString(this.mBaseURL);
        appendURLComponentString(this.mEndPoint);
        appendURLComponentString(this.mTableId);
        if (this.mStoreId != null) {
            appendURLComponentString(this.mAPIMethod);
            appendAPIMethod(this.mStoreId);
            return;
        }
        appendAPIMethod(this.mAPIMethod);
    }

    private void appendURLComponentString(String component) {
        String value = component;
        if (component.charAt(0) == '/') {
            value = component.substring(1);
        }
        if (!component.endsWith("/")) {
            value = value + "/";
        }
        this.mURLBuilder.append(value);
    }

    private void appendAPIMethod(String method) {
        appendURLComponentString(method);
        this.mURLBuilder.setCharAt(this.mURLBuilder.length() - 1, '?');
    }
}
