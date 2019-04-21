package p041io.fabric.sdk.android.services.settings;

import p041io.fabric.sdk.android.Kit;
import p041io.fabric.sdk.android.services.network.HttpMethod;
import p041io.fabric.sdk.android.services.network.HttpRequestFactory;

/* renamed from: io.fabric.sdk.android.services.settings.UpdateAppSpiCall */
public class UpdateAppSpiCall extends AbstractAppSpiCall {
    public /* bridge */ /* synthetic */ boolean invoke(AppRequestData appRequestData) {
        return super.invoke(appRequestData);
    }

    public UpdateAppSpiCall(Kit kit, String protocolAndHostOverride, String url, HttpRequestFactory requestFactory) {
        super(kit, protocolAndHostOverride, url, requestFactory, HttpMethod.PUT);
    }
}
