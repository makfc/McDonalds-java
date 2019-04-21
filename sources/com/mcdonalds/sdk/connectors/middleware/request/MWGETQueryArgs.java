package com.mcdonalds.sdk.connectors.middleware.request;

import com.amap.api.services.district.DistrictSearchQuery;
import com.mcdonalds.sdk.connectors.middleware.MiddlewareConnector;
import com.mcdonalds.sdk.connectors.middleware.model.MWGetQueryArgsComplexObject;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.newrelic.agent.android.analytics.AnalyticAttribute;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Stack;

public class MWGETQueryArgs extends LinkedHashMap<String, Object> implements MWGetQueryArgsComplexObject {
    private static final int AVG_QUERY_ARGS_PAIR_LENGTH = 17;
    private final String mConfigBasePath;

    private static class QueryArgsPair {
        private String mName = null;
        private String mValue = null;

        QueryArgsPair(String name, String value) {
            this.mName = name;
            this.mValue = value;
        }

        public String getName() {
            return this.mName;
        }

        public String getValue() {
            return this.mValue;
        }
    }

    @Deprecated
    public MWGETQueryArgs(MiddlewareConnector ignored) {
        this();
    }

    @Deprecated
    public MWGETQueryArgs(MiddlewareConnector ignored, boolean useNutritionDefaults) {
        this(useNutritionDefaults);
    }

    public MWGETQueryArgs() {
        this(false);
    }

    public MWGETQueryArgs(boolean useNutritionDefaults) {
        this.mConfigBasePath = MiddlewareConnector.CONFIG_BASE_PATH;
        if (useNutritionDefaults) {
            loadNutritionDefaults();
        } else {
            loadDefaults();
        }
    }

    private void loadNutritionDefaults() {
        Configuration config = Configuration.getSharedInstance();
        put(DistrictSearchQuery.KEYWORDS_COUNTRY, config.getValueForKey(this.mConfigBasePath + ".country"));
        put("language", config.getNutritionLanguage());
    }

    private void loadDefaults() {
        Configuration config = Configuration.getSharedInstance();
        put("marketId", config.getValueForKey(this.mConfigBasePath + ".marketId"));
        put("application", config.getValueForKey(this.mConfigBasePath + ".application"));
        put("languageName", config.getCurrentLanguageTag());
        put(AnalyticAttribute.APPLICATION_PLATFORM_ATTRIBUTE, "android");
    }

    private String createArgName(Stack<String> pathComponents) {
        if (pathComponents.isEmpty()) {
            return null;
        }
        StringBuilder builder = new StringBuilder((String) pathComponents.get(0));
        int size = pathComponents.size();
        for (int i = 1; i < size; i++) {
            builder.append('[');
            builder.append((String) pathComponents.get(i));
            builder.append(']');
        }
        return builder.toString();
    }

    private void createQueryArgsPairs(Stack<String> pathComponents, List<QueryArgsPair> queryArgs, MWGetQueryArgsComplexObject obj) {
        if (obj != this) {
            throw new RuntimeException("Complex Query Parameters not supported.");
        }
        List<String> queryPropertyNames = obj.getQueryPropertyNames();
        int size = queryPropertyNames.size();
        for (int i = 0; i < size; i++) {
            String key = (String) queryPropertyNames.get(i);
            pathComponents.push(key);
            Object value = obj.getQueryProperty(key);
            if (value instanceof List) {
                createQueryArgsPairs((Stack) pathComponents, (List) queryArgs, (List) value);
            } else if (value instanceof MWGetQueryArgsComplexObject) {
                createQueryArgsPairs((Stack) pathComponents, (List) queryArgs, (MWGetQueryArgsComplexObject) value);
            } else {
                queryArgs.add(new QueryArgsPair(createArgName(pathComponents), value == null ? "" : value.toString()));
            }
            pathComponents.pop();
        }
    }

    private void createQueryArgsPairs(Stack<String> pathComponents, List<QueryArgsPair> queryArgs, List list) {
        if (!list.isEmpty()) {
            StringBuilder builder = new StringBuilder();
            builder.append('[');
            int size = list.size();
            for (int i = 0; i < size; i++) {
                if (i > 0) {
                    builder.append(',');
                }
                builder.append(list.get(i).toString());
            }
            builder.append(']');
            queryArgs.add(new QueryArgsPair(createArgName(pathComponents), builder.toString()));
        }
    }

    public String toString() {
        List queryArgs = new ArrayList();
        createQueryArgsPairs(new Stack(), queryArgs, (MWGetQueryArgsComplexObject) this);
        return queryArgs.isEmpty() ? "" : encode(queryArgs);
    }

    private String encode(List<QueryArgsPair> queryArgs) {
        int size = queryArgs.size();
        StringBuilder query = new StringBuilder(size * 17);
        for (int i = 0; i < size; i++) {
            QueryArgsPair param = (QueryArgsPair) queryArgs.get(i);
            if (i > 0) {
                query.append('&');
            }
            query.append(String.format("%s=%s", new Object[]{param.getName(), param.getValue()}));
        }
        return query.toString();
    }

    public List<String> getQueryPropertyNames() {
        return new ArrayList(keySet());
    }

    public Object getQueryProperty(String name) {
        return get(name);
    }
}
