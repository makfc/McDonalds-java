package com.mcdonalds.sdk.connectors.middleware.request;

import com.google.gson.internal.LinkedTreeMap;
import com.mcdonalds.sdk.services.configuration.Configuration;
import java.util.ArrayList;
import java.util.Iterator;

public abstract class MWConfigurationRequest<RequestClass, ResponseClass> extends MWRequest<RequestClass, ResponseClass> {
    private static final String PARAMS_PATH = "modules.Configuration.availableConfigs";
    protected static final String URL_PATH = "/application/configuration";
    protected MWGETQueryArgs mQueryArgs;
    private boolean validRequest;

    public abstract String getConfigURL();

    public abstract String getConfigurationID();

    public abstract String getUSConfigurationID();

    MWConfigurationRequest() {
        LinkedTreeMap<String, String> params = null;
        Iterator it = ((ArrayList) Configuration.getSharedInstance().getValueForKey(PARAMS_PATH)).iterator();
        while (it.hasNext()) {
            LinkedTreeMap<String, String> param = (LinkedTreeMap) it.next();
            String paramID = (String) param.get("id");
            if (paramID != null && (paramID.toLowerCase().equals(getConfigurationID().toLowerCase()) || paramID.toLowerCase().equals(getUSConfigurationID().toLowerCase()))) {
                this.validRequest = true;
                params = param;
                break;
            }
        }
        if (params != null) {
            this.mQueryArgs = new MWGETQueryArgs();
            this.mQueryArgs.putAll(params);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public String getEndpoint() {
        return URL_PATH;
    }

    /* Access modifiers changed, original: 0000 */
    public String getQueryString() {
        return this.mQueryArgs.toString();
    }

    public boolean isValidRequest() {
        return this.validRequest;
    }
}
