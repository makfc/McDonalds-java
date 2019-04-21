package com.mcdonalds.sdk.services.network;

import android.content.Context;
import android.os.Build.VERSION;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.RequestQueue.RequestFilter;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.connectors.middleware.request.MWCheckinRequest;
import com.mcdonalds.sdk.connectors.middleware.request.MWDeliveryCheckOutRequest;
import com.mcdonalds.sdk.connectors.middleware.request.MWSignInAndAuthenticateRequest;
import com.mcdonalds.sdk.connectors.middleware.request.MWSocialSignInRequest;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.squareup.okhttp.OkHttpClient;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class RequestManager {
    public static final int DEFAULT_RETRY_COUNT = 2;
    public static final int DEFAULT_TIMEOUT_SECS = 30;
    private static final String RETRY_KEY = "SDKParameters.network.retryCount";
    private static final String TIMEOUT_KEY = "SDKParameters.network.timeoutSecs";
    private static RequestManagerServiceConnection mServiceConnection;
    private static RequestManager sInstance;
    private Context mContext;
    private ImageLoader mImageLoader;
    private RequestQueue mRequestQueue;

    /* renamed from: com.mcdonalds.sdk.services.network.RequestManager$1 */
    class C41351 implements RequestFilter {
        C41351() {
        }

        public boolean apply(Request<?> request) {
            return true;
        }
    }

    private class MySSLSocketFactory extends SSLSocketFactory {
        SSLSocketFactory sslSocketFactory;

        public MySSLSocketFactory(SSLSocketFactory sslSocketFactory) {
            this.sslSocketFactory = sslSocketFactory;
        }

        public String[] getDefaultCipherSuites() {
            return this.sslSocketFactory.getDefaultCipherSuites();
        }

        public String[] getSupportedCipherSuites() {
            return this.sslSocketFactory.getSupportedCipherSuites();
        }

        public SSLSocket createSocket(Socket s, String host, int port, boolean autoClose) throws IOException {
            SSLSocket socket = (SSLSocket) this.sslSocketFactory.createSocket(s, host, port, autoClose);
            socket.setEnabledProtocols(new String[]{"TLSv1.2"});
            return socket;
        }

        public Socket createSocket(String host, int port) throws IOException, UnknownHostException {
            SSLSocket socket = (SSLSocket) this.sslSocketFactory.createSocket(host, port);
            socket.setEnabledProtocols(new String[]{"TLSv1.2"});
            return socket;
        }

        public Socket createSocket(String host, int port, InetAddress localHost, int localPort) throws IOException, UnknownHostException {
            SSLSocket socket = (SSLSocket) this.sslSocketFactory.createSocket(host, port, localHost, localPort);
            socket.setEnabledProtocols(new String[]{"TLSv1.2"});
            return socket;
        }

        public Socket createSocket(InetAddress host, int port) throws IOException {
            SSLSocket socket = (SSLSocket) this.sslSocketFactory.createSocket(host, port);
            socket.setEnabledProtocols(new String[]{"TLSv1.2"});
            return socket;
        }

        public Socket createSocket(InetAddress address, int port, InetAddress localAddress, int localPort) throws IOException {
            SSLSocket socket = (SSLSocket) this.sslSocketFactory.createSocket(address, port, localAddress, localPort);
            socket.setEnabledProtocols(new String[]{"TLSv1.2"});
            return socket;
        }
    }

    public static RequestManager getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new RequestManager(context);
        }
        return sInstance;
    }

    public RequestManager(Context context) {
        this.mContext = context;
        this.mRequestQueue = createAndStartRequestQueue();
    }

    protected RequestManager(Context context, RequestQueue requestQueue) {
        this.mContext = context;
        this.mRequestQueue = requestQueue;
    }

    public static RequestManagerServiceConnection register(Context context) {
        if (mServiceConnection == null) {
            mServiceConnection = new RequestManagerServiceConnection(getInstance(context));
        }
        return mServiceConnection;
    }

    public static void unregister(Context context, RequestManagerServiceConnection connection) {
    }

    /* Access modifiers changed, original: 0000 */
    public <T, E> void processRequest(RequestProvider<T, E> provider, AsyncListener listener) {
        int timeout;
        int retryCount;
        Configuration config = Configuration.getSharedInstance();
        if (config.hasKey(TIMEOUT_KEY)) {
            timeout = config.getIntForKey(TIMEOUT_KEY);
        } else {
            timeout = 30;
        }
        if (config.hasKey(RETRY_KEY)) {
            retryCount = config.getIntForKey(RETRY_KEY);
        } else {
            retryCount = 2;
        }
        if ((provider instanceof MWSignInAndAuthenticateRequest) || (provider instanceof MWSocialSignInRequest)) {
            processRequest(provider, listener, timeout, retryCount);
        } else if ((provider instanceof MWCheckinRequest) || (provider instanceof MWDeliveryCheckOutRequest)) {
            processRequest(provider, listener, 35, 2);
        } else {
            processRequest(provider, listener, 30, 2);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public <T, E> void processRequest(RequestProvider<T, E> provider, AsyncListener listener, int timeoutSecs, int retryCount) {
        int method;
        switch (provider.getMethodType()) {
            case GET:
                method = 0;
                break;
            case POST:
                method = 1;
                break;
            case PUT:
                method = 2;
                break;
            case DELETE:
                method = 3;
                break;
            default:
                throw new IllegalArgumentException("You must specify an HTTP method");
        }
        SessionManager.getInstance().applyCurrentToken(provider);
        switch (provider.getRequestType()) {
            case JSON:
                GsonRequest<T, E> gsonRequest = new GsonRequest(this.mContext, method, provider, listener);
                gsonRequest.setRetryPolicy(new DefaultRetryPolicy((int) TimeUnit.SECONDS.toMillis((long) timeoutSecs), retryCount, 1.0f));
                gsonRequest.setShouldCache(false);
                this.mRequestQueue.add(gsonRequest);
                return;
            case IMAGE:
                MCDImageRequest<T, E> imageRequest = new MCDImageRequest(provider, listener);
                imageRequest.setShouldCache(false);
                this.mRequestQueue.add(imageRequest);
                return;
            case HTML:
                HtmlRequest<T, E> htmlRequest = new HtmlRequest(this.mContext, method, provider, listener);
                htmlRequest.setRetryPolicy(new DefaultRetryPolicy(30000, 0, 1.0f));
                this.mRequestQueue.add(htmlRequest);
                return;
            default:
                throw new IllegalArgumentException("You must specify a Request Type");
        }
    }

    public void processUrlIntoNetworkImageView(String url, NetworkImageView networkImageView, int defaultImageResId, int errorImageResId) {
        if (this.mImageLoader == null) {
            this.mImageLoader = new ImageLoader(this.mRequestQueue, new MCDImageCache(this.mContext));
        }
        if (defaultImageResId == 0) {
            defaultImageResId = 17301567;
        }
        networkImageView.setDefaultImageResId(defaultImageResId);
        if (errorImageResId == 0) {
            errorImageResId = 17301624;
        }
        networkImageView.setErrorImageResId(errorImageResId);
        networkImageView.setImageUrl(url, this.mImageLoader);
    }

    private RequestQueue createAndStartRequestQueue() {
        OkHttpClient client = new OkHttpClient();
        if (Configuration.getSharedInstance().getBooleanForKey("forceTLS12forAPI22orBelow", true) && VERSION.SDK_INT < 21) {
            try {
                SSLContext sslContext = SSLContext.getInstance("TLSv1.2");
                sslContext.init(null, null, null);
                client.setSslSocketFactory(new MySSLSocketFactory(sslContext.getSocketFactory()));
            } catch (Exception e) {
            }
        }
        return Volley.newRequestQueue(this.mContext, new OkHttpStack(client));
    }

    public void clearRequestQueue() {
        this.mRequestQueue.cancelAll(new C41351());
    }
}
