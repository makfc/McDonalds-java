package com.mcdonalds.app.social;

import android.content.Context;
import com.ensighten.Ensighten;
import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.auth.oauth2.AuthorizationCodeFlow.Builder;
import com.google.api.client.auth.oauth2.ClientParametersAuthentication;
import com.google.api.client.auth.openidconnect.IdTokenResponse;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonGenerator;
import com.google.api.client.json.JsonParser;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.mcdonalds.sdk.modules.models.SocialNetwork;
import com.mcdonalds.sdk.services.log.MCDLog;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class OAuth2Helper {
    private static FileDataStoreFactory DATA_STORE_FACTORY = null;
    private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
    private static final JsonFactory JSON_FACTORY = getJsonFactory();
    private AuthorizationCodeFlow flow;
    private Oauth2Params oauth2Params;

    /* renamed from: com.mcdonalds.app.social.OAuth2Helper$1 */
    static class C36981 extends JsonFactory {
        C36981() {
        }

        public JsonParser createJsonParser(InputStream inputStream) throws IOException {
            Ensighten.evaluateEvent(this, "createJsonParser", new Object[]{inputStream});
            MCDLog.temp("createJsonParser");
            return null;
        }

        public JsonParser createJsonParser(InputStream inputStream, Charset charset) throws IOException {
            Ensighten.evaluateEvent(this, "createJsonParser", new Object[]{inputStream, charset});
            MCDLog.temp("createJsonParser");
            return null;
        }

        public JsonParser createJsonParser(String s) throws IOException {
            Ensighten.evaluateEvent(this, "createJsonParser", new Object[]{s});
            MCDLog.temp("createJsonParser");
            return null;
        }

        public JsonGenerator createJsonGenerator(OutputStream outputStream, Charset charset) throws IOException {
            Ensighten.evaluateEvent(this, "createJsonGenerator", new Object[]{outputStream, charset});
            MCDLog.temp("createJsonGenerator");
            return null;
        }
    }

    private static FileDataStoreFactory getDataStoreFactory(Context context) throws IOException {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.social.OAuth2Helper", "getDataStoreFactory", new Object[]{context});
        if (DATA_STORE_FACTORY == null) {
            DATA_STORE_FACTORY = new FileDataStoreFactory(context.getApplicationContext().getDir("CREDENTIALS_CACHE_NAME", 0));
        }
        return DATA_STORE_FACTORY;
    }

    public OAuth2Helper(Context context, int socialNetworkType) {
        this(context, socialNetworkType, Oauth2Params.getOauthParamsForSocialNetworkType(socialNetworkType));
    }

    public OAuth2Helper(Context context, int socialNetworkType, Oauth2Params oauth2Params) {
        this.oauth2Params = null;
        try {
            this.oauth2Params = oauth2Params;
            this.flow = new Builder(oauth2Params.getAccessMethod(), HTTP_TRANSPORT, JSON_FACTORY, new GenericUrl(oauth2Params.getTokenServerUrl()), new ClientParametersAuthentication(oauth2Params.getClientId(), oauth2Params.getClientSecret()), oauth2Params.getClientId(), oauth2Params.getAuthorizationServerEncodedUrl()).setCredentialDataStore(getDataStoreFactory(context).getDataStore(SocialNetwork.getNameForType(socialNetworkType))).build();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public Oauth2Params getOauth2Params() {
        Ensighten.evaluateEvent(this, "getOauth2Params", null);
        return this.oauth2Params;
    }

    public String getAuthorizationUrl() {
        Ensighten.evaluateEvent(this, "getAuthorizationUrl", null);
        return this.flow.newAuthorizationUrl().setRedirectUri(this.oauth2Params.getRedirectUri()).setScopes(convertScopesToString(this.oauth2Params.getScope())).build();
    }

    public void retrieveAndStoreAccessToken(String authorizationCode) throws IOException {
        Ensighten.evaluateEvent(this, "retrieveAndStoreAccessToken", new Object[]{authorizationCode});
        MCDLog.temp("retrieveAndStoreAccessToken for code " + authorizationCode);
        IdTokenResponse tokenResponse = IdTokenResponse.execute(this.flow.newTokenRequest(authorizationCode).setScopes(convertScopesToString(this.oauth2Params.getScope())).setRedirectUri(this.oauth2Params.getRedirectUri()));
        this.oauth2Params.setUserId(tokenResponse.parseIdToken().getPayload().getSubject());
        MCDLog.temp("Found tokenResponse :");
        MCDLog.temp("Access Token : " + tokenResponse.getAccessToken());
        MCDLog.temp("Refresh Token : " + tokenResponse.getRefreshToken());
        this.flow.createAndStoreCredential(tokenResponse, this.oauth2Params.getUserId());
    }

    private Collection<String> convertScopesToString(String scopesConcat) {
        Ensighten.evaluateEvent(this, "convertScopesToString", new Object[]{scopesConcat});
        String[] scopes = scopesConcat.split(",");
        Collection<String> collection = new ArrayList();
        Collections.addAll(collection, scopes);
        return collection;
    }

    private static JsonFactory getJsonFactory() {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.social.OAuth2Helper", "getJsonFactory", null);
        return new C36981();
    }
}
