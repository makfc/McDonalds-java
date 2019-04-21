package com.google.api.client.auth.oauth2;

import com.autonavi.amap.mapcore.VTMCDataCache;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpExecuteInterceptor;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.HttpUnsuccessfulResponseHandler;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.util.Clock;
import com.google.api.client.util.Lists;
import com.google.api.client.util.Preconditions;
import com.mcdonalds.sdk.connectors.middleware.response.MWDeliveryStatusResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Logger;

public class Credential implements HttpExecuteInterceptor, HttpRequestInitializer, HttpUnsuccessfulResponseHandler {
    static final Logger LOGGER = Logger.getLogger(Credential.class.getName());
    private String accessToken;
    private final HttpExecuteInterceptor clientAuthentication;
    private final Clock clock;
    private Long expirationTimeMilliseconds;
    private final JsonFactory jsonFactory;
    private final Lock lock = new ReentrantLock();
    private final AccessMethod method;
    private final Collection<CredentialRefreshListener> refreshListeners;
    private String refreshToken;
    private final HttpRequestInitializer requestInitializer;
    private final String tokenServerEncodedUrl;
    private final HttpTransport transport;

    public interface AccessMethod {
        String getAccessTokenFromRequest(HttpRequest httpRequest);

        void intercept(HttpRequest httpRequest, String str) throws IOException;
    }

    public static class Builder {
        HttpExecuteInterceptor clientAuthentication;
        Clock clock = Clock.SYSTEM;
        JsonFactory jsonFactory;
        final AccessMethod method;
        Collection<CredentialRefreshListener> refreshListeners = Lists.newArrayList();
        HttpRequestInitializer requestInitializer;
        GenericUrl tokenServerUrl;
        HttpTransport transport;

        public Builder(AccessMethod method) {
            this.method = (AccessMethod) Preconditions.checkNotNull(method);
        }

        public Credential build() {
            return new Credential(this);
        }

        public Builder setTransport(HttpTransport transport) {
            this.transport = transport;
            return this;
        }

        public Builder setClock(Clock clock) {
            this.clock = (Clock) Preconditions.checkNotNull(clock);
            return this;
        }

        public Builder setJsonFactory(JsonFactory jsonFactory) {
            this.jsonFactory = jsonFactory;
            return this;
        }

        public Builder setTokenServerEncodedUrl(String tokenServerEncodedUrl) {
            this.tokenServerUrl = tokenServerEncodedUrl == null ? null : new GenericUrl(tokenServerEncodedUrl);
            return this;
        }

        public Builder setClientAuthentication(HttpExecuteInterceptor clientAuthentication) {
            this.clientAuthentication = clientAuthentication;
            return this;
        }

        public Builder setRequestInitializer(HttpRequestInitializer requestInitializer) {
            this.requestInitializer = requestInitializer;
            return this;
        }

        public Builder addRefreshListener(CredentialRefreshListener refreshListener) {
            this.refreshListeners.add(Preconditions.checkNotNull(refreshListener));
            return this;
        }

        public final Collection<CredentialRefreshListener> getRefreshListeners() {
            return this.refreshListeners;
        }
    }

    protected Credential(Builder builder) {
        this.method = (AccessMethod) Preconditions.checkNotNull(builder.method);
        this.transport = builder.transport;
        this.jsonFactory = builder.jsonFactory;
        this.tokenServerEncodedUrl = builder.tokenServerUrl == null ? null : builder.tokenServerUrl.build();
        this.clientAuthentication = builder.clientAuthentication;
        this.requestInitializer = builder.requestInitializer;
        this.refreshListeners = Collections.unmodifiableCollection(builder.refreshListeners);
        this.clock = (Clock) Preconditions.checkNotNull(builder.clock);
    }

    public void intercept(HttpRequest request) throws IOException {
        this.lock.lock();
        try {
            Long expiresIn = getExpiresInSeconds();
            if (this.accessToken == null || (expiresIn != null && expiresIn.longValue() <= 60)) {
                refreshToken();
                if (this.accessToken == null) {
                    return;
                }
            }
            this.method.intercept(request, this.accessToken);
            this.lock.unlock();
        } finally {
            this.lock.unlock();
        }
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    public boolean handleResponse(com.google.api.client.http.HttpRequest r11, com.google.api.client.http.HttpResponse r12, boolean r13) {
        /*
        r10 = this;
        r6 = 1;
        r7 = 0;
        r5 = 0;
        r2 = 0;
        r8 = r12.getHeaders();
        r1 = r8.getAuthenticateAsList();
        if (r1 == 0) goto L_0x0031;
    L_0x000e:
        r4 = r1.iterator();
    L_0x0012:
        r8 = r4.hasNext();
        if (r8 == 0) goto L_0x0031;
    L_0x0018:
        r0 = r4.next();
        r0 = (java.lang.String) r0;
        r8 = "Bearer ";
        r8 = r0.startsWith(r8);
        if (r8 == 0) goto L_0x0012;
    L_0x0026:
        r2 = 1;
        r8 = com.google.api.client.auth.oauth2.BearerToken.INVALID_TOKEN_ERROR;
        r8 = r8.matcher(r0);
        r5 = r8.find();
    L_0x0031:
        if (r2 != 0) goto L_0x003c;
    L_0x0033:
        r8 = r12.getStatusCode();
        r9 = 401; // 0x191 float:5.62E-43 double:1.98E-321;
        if (r8 != r9) goto L_0x005d;
    L_0x003b:
        r5 = r6;
    L_0x003c:
        if (r5 == 0) goto L_0x0072;
    L_0x003e:
        r8 = r10.lock;	 Catch:{ IOException -> 0x0068 }
        r8.lock();	 Catch:{ IOException -> 0x0068 }
        r8 = r10.accessToken;	 Catch:{ all -> 0x0061 }
        r9 = r10.method;	 Catch:{ all -> 0x0061 }
        r9 = r9.getAccessTokenFromRequest(r11);	 Catch:{ all -> 0x0061 }
        r8 = com.google.api.client.util.Objects.equal(r8, r9);	 Catch:{ all -> 0x0061 }
        if (r8 == 0) goto L_0x0057;
    L_0x0051:
        r8 = r10.refreshToken();	 Catch:{ all -> 0x0061 }
        if (r8 == 0) goto L_0x005f;
    L_0x0057:
        r8 = r10.lock;	 Catch:{ IOException -> 0x0068 }
        r8.unlock();	 Catch:{ IOException -> 0x0068 }
    L_0x005c:
        return r6;
    L_0x005d:
        r5 = r7;
        goto L_0x003c;
    L_0x005f:
        r6 = r7;
        goto L_0x0057;
    L_0x0061:
        r6 = move-exception;
        r8 = r10.lock;	 Catch:{ IOException -> 0x0068 }
        r8.unlock();	 Catch:{ IOException -> 0x0068 }
        throw r6;	 Catch:{ IOException -> 0x0068 }
    L_0x0068:
        r3 = move-exception;
        r6 = LOGGER;
        r8 = java.util.logging.Level.SEVERE;
        r9 = "unable to refresh token";
        r6.log(r8, r9, r3);
    L_0x0072:
        r6 = r7;
        goto L_0x005c;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.api.client.auth.oauth2.Credential.handleResponse(com.google.api.client.http.HttpRequest, com.google.api.client.http.HttpResponse, boolean):boolean");
    }

    public void initialize(HttpRequest request) throws IOException {
        request.setInterceptor(this);
        request.setUnsuccessfulResponseHandler(this);
    }

    public final String getAccessToken() {
        this.lock.lock();
        try {
            String str = this.accessToken;
            return str;
        } finally {
            this.lock.unlock();
        }
    }

    public Credential setAccessToken(String accessToken) {
        this.lock.lock();
        try {
            this.accessToken = accessToken;
            return this;
        } finally {
            this.lock.unlock();
        }
    }

    public final String getRefreshToken() {
        this.lock.lock();
        try {
            String str = this.refreshToken;
            return str;
        } finally {
            this.lock.unlock();
        }
    }

    public Credential setRefreshToken(String refreshToken) {
        this.lock.lock();
        if (refreshToken != null) {
            try {
                boolean z = (this.jsonFactory == null || this.transport == null || this.clientAuthentication == null || this.tokenServerEncodedUrl == null) ? false : true;
                Preconditions.checkArgument(z, "Please use the Builder and call setJsonFactory, setTransport, setClientAuthentication and setTokenServerUrl/setTokenServerEncodedUrl");
            } catch (Throwable th) {
                this.lock.unlock();
            }
        }
        this.refreshToken = refreshToken;
        this.lock.unlock();
        return this;
    }

    public final Long getExpirationTimeMilliseconds() {
        this.lock.lock();
        try {
            Long l = this.expirationTimeMilliseconds;
            return l;
        } finally {
            this.lock.unlock();
        }
    }

    public Credential setExpirationTimeMilliseconds(Long expirationTimeMilliseconds) {
        this.lock.lock();
        try {
            this.expirationTimeMilliseconds = expirationTimeMilliseconds;
            return this;
        } finally {
            this.lock.unlock();
        }
    }

    public final Long getExpiresInSeconds() {
        this.lock.lock();
        try {
            if (this.expirationTimeMilliseconds == null) {
                return null;
            }
            Long valueOf = Long.valueOf((this.expirationTimeMilliseconds.longValue() - this.clock.currentTimeMillis()) / 1000);
            this.lock.unlock();
            return valueOf;
        } finally {
            this.lock.unlock();
        }
    }

    public Credential setExpiresInSeconds(Long expiresIn) {
        return setExpirationTimeMilliseconds(expiresIn == null ? null : Long.valueOf(this.clock.currentTimeMillis() + (expiresIn.longValue() * 1000)));
    }

    public final boolean refreshToken() throws IOException {
        boolean statusCode4xx;
        this.lock.lock();
        try {
            TokenResponse tokenResponse = executeRefreshToken();
            if (tokenResponse != null) {
                setFromTokenResponse(tokenResponse);
                for (CredentialRefreshListener refreshListener : this.refreshListeners) {
                    refreshListener.onTokenResponse(this, tokenResponse);
                }
                this.lock.unlock();
                return true;
            }
        } catch (TokenResponseException e) {
            if (MWDeliveryStatusResponse.STATUS_ORDER_DELIVERED > e.getStatusCode() || e.getStatusCode() >= VTMCDataCache.MAXSIZE) {
                statusCode4xx = false;
            } else {
                statusCode4xx = true;
            }
            if (e.getDetails() != null && statusCode4xx) {
                setAccessToken(null);
                setExpiresInSeconds(null);
            }
            for (CredentialRefreshListener refreshListener2 : this.refreshListeners) {
                refreshListener2.onTokenErrorResponse(this, e.getDetails());
            }
            if (statusCode4xx) {
                throw e;
            }
        } catch (Throwable th) {
            this.lock.unlock();
        }
        this.lock.unlock();
        return false;
    }

    public Credential setFromTokenResponse(TokenResponse tokenResponse) {
        setAccessToken(tokenResponse.getAccessToken());
        if (tokenResponse.getRefreshToken() != null) {
            setRefreshToken(tokenResponse.getRefreshToken());
        }
        setExpiresInSeconds(tokenResponse.getExpiresInSeconds());
        return this;
    }

    /* Access modifiers changed, original: protected */
    public TokenResponse executeRefreshToken() throws IOException {
        if (this.refreshToken == null) {
            return null;
        }
        return new RefreshTokenRequest(this.transport, this.jsonFactory, new GenericUrl(this.tokenServerEncodedUrl), this.refreshToken).setClientAuthentication(this.clientAuthentication).setRequestInitializer(this.requestInitializer).execute();
    }
}
