package com.mcdonalds.sdk.services.network;

import com.android.volley.toolbox.HurlStack;
import com.newrelic.agent.android.instrumentation.okhttp2.OkHttp2Instrumentation;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.OkUrlFactory;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class OkHttpStack extends HurlStack {
    private final OkUrlFactory mFactory;

    public OkHttpStack(OkHttpClient client) {
        if (client == null) {
            throw new NullPointerException("Client must not be null.");
        }
        this.mFactory = new OkUrlFactory(client);
    }

    /* Access modifiers changed, original: protected */
    public HttpURLConnection createConnection(URL url) throws IOException {
        OkUrlFactory okUrlFactory = this.mFactory;
        return !(okUrlFactory instanceof OkUrlFactory) ? okUrlFactory.open(url) : OkHttp2Instrumentation.open(okUrlFactory, url);
    }
}
