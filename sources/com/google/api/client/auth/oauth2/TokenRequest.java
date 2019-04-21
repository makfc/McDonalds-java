package com.google.api.client.auth.oauth2;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpExecuteInterceptor;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.UrlEncodedContent;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.util.GenericData;
import com.google.api.client.util.Joiner;
import com.google.api.client.util.Key;
import com.google.api.client.util.Preconditions;
import com.newrelic.agent.android.util.SafeJsonPrimitive;
import java.io.IOException;
import java.util.Collection;

public class TokenRequest extends GenericData {
    HttpExecuteInterceptor clientAuthentication;
    @Key("grant_type")
    private String grantType;
    private final JsonFactory jsonFactory;
    HttpRequestInitializer requestInitializer;
    @Key("scope")
    private String scopes;
    private GenericUrl tokenServerUrl;
    private final HttpTransport transport;

    /* renamed from: com.google.api.client.auth.oauth2.TokenRequest$1 */
    class C23531 implements HttpRequestInitializer {
        C23531() {
        }

        public void initialize(HttpRequest request) throws IOException {
            if (TokenRequest.this.requestInitializer != null) {
                TokenRequest.this.requestInitializer.initialize(request);
            }
            final HttpExecuteInterceptor interceptor = request.getInterceptor();
            request.setInterceptor(new HttpExecuteInterceptor() {
                public void intercept(HttpRequest request) throws IOException {
                    if (interceptor != null) {
                        interceptor.intercept(request);
                    }
                    if (TokenRequest.this.clientAuthentication != null) {
                        TokenRequest.this.clientAuthentication.intercept(request);
                    }
                }
            });
        }
    }

    public TokenRequest(HttpTransport transport, JsonFactory jsonFactory, GenericUrl tokenServerUrl, String grantType) {
        this.transport = (HttpTransport) Preconditions.checkNotNull(transport);
        this.jsonFactory = (JsonFactory) Preconditions.checkNotNull(jsonFactory);
        setTokenServerUrl(tokenServerUrl);
        setGrantType(grantType);
    }

    public TokenRequest setRequestInitializer(HttpRequestInitializer requestInitializer) {
        this.requestInitializer = requestInitializer;
        return this;
    }

    public TokenRequest setClientAuthentication(HttpExecuteInterceptor clientAuthentication) {
        this.clientAuthentication = clientAuthentication;
        return this;
    }

    public TokenRequest setTokenServerUrl(GenericUrl tokenServerUrl) {
        this.tokenServerUrl = tokenServerUrl;
        Preconditions.checkArgument(tokenServerUrl.getFragment() == null);
        return this;
    }

    public TokenRequest setScopes(Collection<String> scopes) {
        this.scopes = scopes == null ? null : Joiner.m7512on(SafeJsonPrimitive.NULL_CHAR).join(scopes);
        return this;
    }

    public TokenRequest setGrantType(String grantType) {
        this.grantType = (String) Preconditions.checkNotNull(grantType);
        return this;
    }

    public final HttpResponse executeUnparsed() throws IOException {
        HttpRequest request = this.transport.createRequestFactory(new C23531()).buildPostRequest(this.tokenServerUrl, new UrlEncodedContent(this));
        request.setParser(new JsonObjectParser(this.jsonFactory));
        request.setThrowExceptionOnExecuteError(false);
        HttpResponse response = request.execute();
        if (response.isSuccessStatusCode()) {
            return response;
        }
        throw TokenResponseException.from(this.jsonFactory, response);
    }

    public TokenResponse execute() throws IOException {
        return (TokenResponse) executeUnparsed().parseAs(TokenResponse.class);
    }

    public TokenRequest set(String fieldName, Object value) {
        return (TokenRequest) super.set(fieldName, value);
    }
}
