package p041io.fabric.sdk.android.services.settings;

import android.content.res.Resources.NotFoundException;
import java.io.InputStream;
import java.util.Locale;
import p041io.fabric.sdk.android.Fabric;
import p041io.fabric.sdk.android.Kit;
import p041io.fabric.sdk.android.KitInfo;
import p041io.fabric.sdk.android.services.common.AbstractSpiCall;
import p041io.fabric.sdk.android.services.common.CommonUtils;
import p041io.fabric.sdk.android.services.common.ResponseParser;
import p041io.fabric.sdk.android.services.network.HttpMethod;
import p041io.fabric.sdk.android.services.network.HttpRequest;
import p041io.fabric.sdk.android.services.network.HttpRequestFactory;

/* renamed from: io.fabric.sdk.android.services.settings.AbstractAppSpiCall */
abstract class AbstractAppSpiCall extends AbstractSpiCall implements AppSpiCall {
    public AbstractAppSpiCall(Kit kit, String protocolAndHostOverride, String url, HttpRequestFactory requestFactory, HttpMethod method) {
        super(kit, protocolAndHostOverride, url, requestFactory, method);
    }

    public boolean invoke(AppRequestData requestData) {
        HttpRequest httpRequest = applyMultipartDataTo(applyHeadersTo(getHttpRequest(), requestData), requestData);
        Fabric.getLogger().mo34403d("Fabric", "Sending app info to " + getUrl());
        if (requestData.icon != null) {
            Fabric.getLogger().mo34403d("Fabric", "App icon hash is " + requestData.icon.hash);
            Fabric.getLogger().mo34403d("Fabric", "App icon size is " + requestData.icon.width + "x" + requestData.icon.height);
        }
        int statusCode = httpRequest.code();
        Fabric.getLogger().mo34403d("Fabric", ("POST".equals(httpRequest.method()) ? "Create" : "Update") + " app request ID: " + httpRequest.header("X-REQUEST-ID"));
        Fabric.getLogger().mo34403d("Fabric", "Result was " + statusCode);
        if (ResponseParser.parse(statusCode) == 0) {
            return true;
        }
        return false;
    }

    private HttpRequest applyHeadersTo(HttpRequest request, AppRequestData requestData) {
        return request.header("X-CRASHLYTICS-API-KEY", requestData.apiKey).header("X-CRASHLYTICS-API-CLIENT-TYPE", "android").header("X-CRASHLYTICS-API-CLIENT-VERSION", this.kit.getVersion());
    }

    private HttpRequest applyMultipartDataTo(HttpRequest request, AppRequestData requestData) {
        request = request.part("app[identifier]", requestData.appId).part("app[name]", requestData.name).part("app[display_version]", requestData.displayVersion).part("app[build_version]", requestData.buildVersion).part("app[source]", Integer.valueOf(requestData.source)).part("app[minimum_sdk_version]", requestData.minSdkVersion).part("app[built_sdk_version]", requestData.builtSdkVersion);
        if (!CommonUtils.isNullOrEmpty(requestData.instanceIdentifier)) {
            request.part("app[instance_identifier]", requestData.instanceIdentifier);
        }
        if (requestData.icon != null) {
            InputStream is = null;
            try {
                is = this.kit.getContext().getResources().openRawResource(requestData.icon.iconResourceId);
                request.part("app[icon][hash]", requestData.icon.hash).part("app[icon][data]", "icon.png", "application/octet-stream", is).part("app[icon][width]", Integer.valueOf(requestData.icon.width)).part("app[icon][height]", Integer.valueOf(requestData.icon.height));
            } catch (NotFoundException e) {
                Fabric.getLogger().mo34406e("Fabric", "Failed to find app icon with resource ID: " + requestData.icon.iconResourceId, e);
            } finally {
                CommonUtils.closeOrLog(is, "Failed to close app icon InputStream.");
            }
        }
        if (requestData.sdkKits != null) {
            for (KitInfo kit : requestData.sdkKits) {
                request.part(getKitVersionKey(kit), kit.getVersion());
                request.part(getKitBuildTypeKey(kit), kit.getBuildType());
            }
        }
        return request;
    }

    /* Access modifiers changed, original: 0000 */
    public String getKitVersionKey(KitInfo kit) {
        return String.format(Locale.US, "app[build][libraries][%s][version]", new Object[]{kit.getIdentifier()});
    }

    /* Access modifiers changed, original: 0000 */
    public String getKitBuildTypeKey(KitInfo kit) {
        return String.format(Locale.US, "app[build][libraries][%s][type]", new Object[]{kit.getIdentifier()});
    }
}
