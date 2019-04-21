package com.mcdonalds.sdk.connectors.autonavi;

import android.net.Uri;
import com.facebook.stetho.common.Utf8Charset;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

public class AutoNavi {

    public static class Builder {
        private android.net.Uri.Builder mUriBuilder;

        public Builder(String baseUrl, String tableId, String apiKey) {
            this.mUriBuilder = Uri.parse(baseUrl).buildUpon();
            this.mUriBuilder.appendQueryParameter(Parameters.TABLE_ID, tableId);
            this.mUriBuilder.appendQueryParameter(Parameters.API_KEY, apiKey);
        }

        public Builder setMethod(String method) {
            this.mUriBuilder.appendPath(method);
            return this;
        }

        public Builder setCenter(String center) {
            this.mUriBuilder.appendQueryParameter(Parameters.CENTER, center);
            return this;
        }

        public Builder setRadius(int radius) {
            this.mUriBuilder.appendQueryParameter(Parameters.RADIUS, String.valueOf(radius));
            return this;
        }

        public Builder setLimit(int limit) {
            this.mUriBuilder.appendQueryParameter(Parameters.LIMIT, String.valueOf(limit));
            return this;
        }

        public Builder setId(String id) {
            this.mUriBuilder.appendQueryParameter(Parameters.FILTER, String.format("StoreCode:%s", new Object[]{id}));
            return this;
        }

        public Builder setKeywords(String keywords) {
            this.mUriBuilder.appendQueryParameter(Parameters.KEYWORDS, keywords);
            return this;
        }

        public Builder setFilters(List<String> filters) {
            StringBuilder sb = new StringBuilder();
            int size = filters.size();
            for (int i = 0; i < size; i++) {
                sb.append('+').append((String) filters.get(i)).append(":1");
            }
            this.mUriBuilder.appendQueryParameter(Parameters.FILTER, sb.toString().replaceFirst("\\+", ""));
            return this;
        }

        public String build() {
            try {
                return URLDecoder.decode(this.mUriBuilder.build().toString(), Utf8Charset.NAME);
            } catch (UnsupportedEncodingException e) {
                return this.mUriBuilder.build().toString().replaceAll("%2B", "+").replaceAll("%2C", ",").replaceAll("%3A", ":");
            }
        }
    }

    public static class Methods {
        public static final String AROUND = "around";
        public static final String LIST = "list";
    }

    public static class Parameters {
        public static final String API_KEY = "key";
        public static final String CENTER = "center";
        public static final String FILTER = "filter";
        /* renamed from: ID */
        public static final String f6679ID = "_id";
        public static final String KEYWORDS = "keywords";
        public static final String LIMIT = "limit";
        public static final String RADIUS = "radius";
        public static final String TABLE_ID = "tableid";
    }
}
