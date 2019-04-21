package p041io.fabric.sdk.android.services.network;

import java.util.Map;

/* renamed from: io.fabric.sdk.android.services.network.HttpRequestFactory */
public interface HttpRequestFactory {
    HttpRequest buildHttpRequest(HttpMethod httpMethod, String str, Map<String, String> map);

    void setPinningInfoProvider(PinningInfoProvider pinningInfoProvider);
}
