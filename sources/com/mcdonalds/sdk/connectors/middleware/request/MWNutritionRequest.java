package com.mcdonalds.sdk.connectors.middleware.request;

import com.mcdonalds.sdk.connectors.middleware.MiddlewareConnector;
import com.mcdonalds.sdk.connectors.middleware.deserializer.MWBooleanDeserializer;
import com.mcdonalds.sdk.connectors.middleware.deserializer.MWComponentsDeserializer;
import com.mcdonalds.sdk.connectors.middleware.deserializer.MWDateDeserializer;
import com.mcdonalds.sdk.connectors.middleware.deserializer.MWDoubleDeserializer;
import com.mcdonalds.sdk.connectors.middleware.deserializer.MWRelationTypeDeserializer;
import com.mcdonalds.sdk.connectors.middleware.deserializer.MWStringDeserializer;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.mcdonalds.sdk.services.network.CustomTypeAdapter;
import com.mcdonalds.sdk.services.network.RequestProvider.MethodType;
import com.mcdonalds.sdk.services.network.RequestProvider.RequestType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class MWNutritionRequest<T> extends MWRequest<T, Void> {
    private ArrayList<CustomTypeAdapter> mCustomDeserializers;
    private MWRequestHeaders mHeaders;
    protected MWGETQueryArgs mQueryArgs;

    @Deprecated
    public MWNutritionRequest(MiddlewareConnector ignored, String notUsed) {
        this();
    }

    public MWNutritionRequest() {
        this.mHeaders = getHeaderMap();
        this.mCustomDeserializers = new ArrayList();
        this.mCustomDeserializers.add(new MWBooleanDeserializer());
        this.mCustomDeserializers.add(new MWDoubleDeserializer());
        this.mCustomDeserializers.add(new MWStringDeserializer());
        this.mCustomDeserializers.add(new MWDateDeserializer());
        this.mCustomDeserializers.add(new MWRelationTypeDeserializer());
        this.mCustomDeserializers.add(new MWComponentsDeserializer());
        this.mQueryArgs = new MWGETQueryArgs(true);
        this.mQueryArgs.put("language", Configuration.getSharedInstance().getNutritionLanguageName());
    }

    public void addCustomDeserializer(CustomTypeAdapter customTypeAdapter) {
        this.mCustomDeserializers.add(customTypeAdapter);
    }

    public MethodType getMethodType() {
        return MethodType.GET;
    }

    public RequestType getRequestType() {
        return RequestType.JSON;
    }

    public Map<String, String> getHeaders() {
        return this.mHeaders;
    }

    public String getBody() {
        return null;
    }

    public void setBody(Void body) {
    }

    public List<? extends CustomTypeAdapter> getCustomTypeAdapters() {
        return this.mCustomDeserializers;
    }

    /* Access modifiers changed, original: 0000 */
    public String getQueryString() {
        return this.mQueryArgs.toString();
    }
}
