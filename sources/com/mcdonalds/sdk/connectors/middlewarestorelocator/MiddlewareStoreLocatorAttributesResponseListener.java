package com.mcdonalds.sdk.connectors.middlewarestorelocator;

import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.connectors.middlewarestorelocator.model.MiddlewareStoreLocatorAttribute;
import java.util.ArrayList;
import java.util.List;

public class MiddlewareStoreLocatorAttributesResponseListener implements AsyncListener<MiddlewareStoreLocatorAttribute[]> {
    private AsyncListener<List<String>> mAttributesListener;

    public MiddlewareStoreLocatorAttributesResponseListener(AsyncListener<List<String>> attributesListener) {
        this.mAttributesListener = attributesListener;
    }

    public void onResponse(MiddlewareStoreLocatorAttribute[] response, AsyncToken token, AsyncException exception) {
        if (exception != null) {
            this.mAttributesListener.onResponse(null, null, exception);
            return;
        }
        List<String> attributes = new ArrayList();
        for (MiddlewareStoreLocatorAttribute attribute : response) {
            attributes.add(attribute.type);
        }
        this.mAttributesListener.onResponse(attributes, null, null);
    }
}
