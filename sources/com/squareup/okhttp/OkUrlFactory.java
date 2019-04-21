package com.squareup.okhttp;

import com.newrelic.agent.android.instrumentation.okhttp2.OkHttp2Instrumentation;
import com.squareup.okhttp.internal.huc.HttpURLConnectionImpl;
import com.squareup.okhttp.internal.huc.HttpsURLConnectionImpl;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import java.net.URLStreamHandlerFactory;

public final class OkUrlFactory implements Cloneable, URLStreamHandlerFactory {
    private final OkHttpClient client;

    public OkUrlFactory(OkHttpClient client) {
        this.client = client;
    }

    public OkUrlFactory clone() {
        return new OkUrlFactory(this.client.clone());
    }

    public HttpURLConnection open(URL url) {
        return open(url, this.client.getProxy());
    }

    /* Access modifiers changed, original: 0000 */
    public HttpURLConnection open(URL url, Proxy proxy) {
        String protocol = url.getProtocol();
        OkHttpClient copy = this.client.copyWithDefaults();
        copy.setProxy(proxy);
        if (protocol.equals("http")) {
            return new HttpURLConnectionImpl(url, copy);
        }
        if (protocol.equals("https")) {
            return new HttpsURLConnectionImpl(url, copy);
        }
        throw new IllegalArgumentException("Unexpected protocol: " + protocol);
    }

    public URLStreamHandler createURLStreamHandler(final String protocol) {
        if (protocol.equals("http") || protocol.equals("https")) {
            return new URLStreamHandler() {
                /* Access modifiers changed, original: protected */
                public URLConnection openConnection(URL url) {
                    OkUrlFactory okUrlFactory = OkUrlFactory.this;
                    return !(okUrlFactory instanceof OkUrlFactory) ? okUrlFactory.open(url) : OkHttp2Instrumentation.open(okUrlFactory, url);
                }

                /* Access modifiers changed, original: protected */
                public URLConnection openConnection(URL url, Proxy proxy) {
                    return OkUrlFactory.this.open(url, proxy);
                }

                /* Access modifiers changed, original: protected */
                public int getDefaultPort() {
                    if (protocol.equals("http")) {
                        return 80;
                    }
                    if (protocol.equals("https")) {
                        return 443;
                    }
                    throw new AssertionError();
                }
            };
        }
        return null;
    }
}
