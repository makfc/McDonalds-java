package p041io.fabric.sdk.android.services.settings;

import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
import p041io.fabric.sdk.android.Fabric;
import p041io.fabric.sdk.android.Kit;
import p041io.fabric.sdk.android.services.common.AbstractSpiCall;
import p041io.fabric.sdk.android.services.common.CommonUtils;
import p041io.fabric.sdk.android.services.network.HttpMethod;
import p041io.fabric.sdk.android.services.network.HttpRequest;
import p041io.fabric.sdk.android.services.network.HttpRequest.HttpRequestException;
import p041io.fabric.sdk.android.services.network.HttpRequestFactory;

/* renamed from: io.fabric.sdk.android.services.settings.DefaultSettingsSpiCall */
class DefaultSettingsSpiCall extends AbstractSpiCall implements SettingsSpiCall {
    public DefaultSettingsSpiCall(Kit kit, String protocolAndHostOverride, String url, HttpRequestFactory requestFactory) {
        this(kit, protocolAndHostOverride, url, requestFactory, HttpMethod.GET);
    }

    DefaultSettingsSpiCall(Kit kit, String protocolAndHostOverride, String url, HttpRequestFactory requestFactory, HttpMethod method) {
        super(kit, protocolAndHostOverride, url, requestFactory, method);
    }

    public JSONObject invoke(SettingsRequest requestData) {
        JSONObject toReturn;
        HttpRequest httpRequest = null;
        try {
            Map<String, String> queryParams = getQueryParamsFor(requestData);
            httpRequest = applyHeadersTo(getHttpRequest(queryParams), requestData);
            Fabric.getLogger().mo34403d("Fabric", "Requesting settings from " + getUrl());
            Fabric.getLogger().mo34403d("Fabric", "Settings query params were: " + queryParams);
            toReturn = handleResponse(httpRequest);
            if (httpRequest != null) {
                Fabric.getLogger().mo34403d("Fabric", "Settings request ID: " + httpRequest.header("X-REQUEST-ID"));
            }
        } catch (HttpRequestException e) {
            Fabric.getLogger().mo34406e("Fabric", "Settings request failed.", e);
            toReturn = null;
            if (httpRequest != null) {
                Fabric.getLogger().mo34403d("Fabric", "Settings request ID: " + httpRequest.header("X-REQUEST-ID"));
            }
        } catch (Throwable th) {
            if (httpRequest != null) {
                Fabric.getLogger().mo34403d("Fabric", "Settings request ID: " + httpRequest.header("X-REQUEST-ID"));
            }
        }
        return toReturn;
    }

    /* Access modifiers changed, original: 0000 */
    public JSONObject handleResponse(HttpRequest httpRequest) {
        int statusCode = httpRequest.code();
        Fabric.getLogger().mo34403d("Fabric", "Settings result was: " + statusCode);
        if (requestWasSuccessful(statusCode)) {
            return getJsonObjectFrom(httpRequest.body());
        }
        Fabric.getLogger().mo34405e("Fabric", "Failed to retrieve settings from " + getUrl());
        return null;
    }

    /* Access modifiers changed, original: 0000 */
    public boolean requestWasSuccessful(int httpStatusCode) {
        return httpStatusCode == 200 || httpStatusCode == 201 || httpStatusCode == 202 || httpStatusCode == 203;
    }

    private JSONObject getJsonObjectFrom(String httpRequestBody) {
        try {
            return JSONObjectInstrumentation.init(httpRequestBody);
        } catch (Exception e) {
            Fabric.getLogger().mo34404d("Fabric", "Failed to parse settings JSON from " + getUrl(), e);
            Fabric.getLogger().mo34403d("Fabric", "Settings response " + httpRequestBody);
            return null;
        }
    }

    private Map<String, String> getQueryParamsFor(SettingsRequest requestData) {
        Map<String, String> queryParams = new HashMap();
        queryParams.put("build_version", requestData.buildVersion);
        queryParams.put("display_version", requestData.displayVersion);
        queryParams.put("source", Integer.toString(requestData.source));
        if (requestData.iconHash != null) {
            queryParams.put("icon_hash", requestData.iconHash);
        }
        String instanceId = requestData.instanceId;
        if (!CommonUtils.isNullOrEmpty(instanceId)) {
            queryParams.put("instance", instanceId);
        }
        return queryParams;
    }

    private HttpRequest applyHeadersTo(HttpRequest request, SettingsRequest requestData) {
        applyNonNullHeader(request, "X-CRASHLYTICS-API-KEY", requestData.apiKey);
        applyNonNullHeader(request, "X-CRASHLYTICS-API-CLIENT-TYPE", "android");
        applyNonNullHeader(request, "X-CRASHLYTICS-API-CLIENT-VERSION", this.kit.getVersion());
        applyNonNullHeader(request, "Accept", "application/json");
        applyNonNullHeader(request, "X-CRASHLYTICS-DEVICE-MODEL", requestData.deviceModel);
        applyNonNullHeader(request, "X-CRASHLYTICS-OS-BUILD-VERSION", requestData.osBuildVersion);
        applyNonNullHeader(request, "X-CRASHLYTICS-OS-DISPLAY-VERSION", requestData.osDisplayVersion);
        applyNonNullHeader(request, "X-CRASHLYTICS-INSTALLATION-ID", requestData.installationId);
        return request;
    }

    private void applyNonNullHeader(HttpRequest request, String key, String value) {
        if (value != null) {
            request.header(key, value);
        }
    }
}
