package com.mcdonalds.app.social;

import com.ensighten.Ensighten;
import com.google.api.client.auth.oauth2.BearerToken;
import com.google.api.client.auth.oauth2.Credential.AccessMethod;

public class Oauth2Params {
    private AccessMethod accessMethod;
    private String apiUrl;
    private String authorizationServerEncodedUrl;
    private String clientId;
    private String clientSecret;
    private String redirectUri;
    private String scope;
    private String tokenServerUrl;
    private String userId;

    public static Oauth2Params getOauthParamsForSocialNetworkType(int socialNetworkType) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.social.Oauth2Params", "getOauthParamsForSocialNetworkType", new Object[]{new Integer(socialNetworkType)});
        switch (socialNetworkType) {
            case 1:
                return new Oauth2Params("92692804416-omhvm18ngfa8406lmnuab0n5eefaao8u.apps.googleusercontent.com", "GgOOCSAOCWHtB9WgeVcdFeMU", "https://www.googleapis.com/oauth2/v3/token", "https://accounts.google.com/o/oauth2/auth", BearerToken.authorizationHeaderAccessMethod(), "profile", "https://localhost/oauth2callback", "plus", "https://www.googleapis.com/plus/v1/people/me/activities/public");
            case 2:
                return new Oauth2Params("925309634186488", "ca9dd29a9eaeab3727a744609a88e9b0", "https://graph.facebook.com/oauth/access_token", "https://graph.facebook.com/oauth/authorize", BearerToken.authorizationHeaderAccessMethod(), "public_profile email", "https://www.facebook.com/connect/login_success.html", "facebook", "");
            case 5:
                return new Oauth2Params("q9n46zFXsgIxgBwt8P5KB6MVt", "aaHK4qXcm6JZ1PGvU99BjZFNQDezb4iii2aztRA3PUT4QaJFT5", "https://api.twitter.com/oauth/access_token", "https://api.twitter.com/oauth/authorize", BearerToken.authorizationHeaderAccessMethod(), "https://api.twitter.com/", "http://www.mcdonalds.com", "twitter", "https://api.twitter.com/oauth/request_token");
            default:
                return null;
        }
    }

    private Oauth2Params(String clientId, String clientSecret, String tokenServerUrl, String authorizationServerEncodedUrl, AccessMethod accessMethod, String scope, String redirectUri, String userId, String apiUrl) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.tokenServerUrl = tokenServerUrl;
        this.authorizationServerEncodedUrl = authorizationServerEncodedUrl;
        this.accessMethod = accessMethod;
        this.scope = scope;
        this.redirectUri = redirectUri;
        this.userId = userId;
        this.apiUrl = apiUrl;
    }

    public String getClientId() {
        Ensighten.evaluateEvent(this, "getClientId", null);
        return this.clientId;
    }

    public String getClientSecret() {
        Ensighten.evaluateEvent(this, "getClientSecret", null);
        return this.clientSecret;
    }

    public String getScope() {
        Ensighten.evaluateEvent(this, "getScope", null);
        return this.scope;
    }

    public String getRedirectUri() {
        Ensighten.evaluateEvent(this, "getRedirectUri", null);
        return this.redirectUri;
    }

    public String getTokenServerUrl() {
        Ensighten.evaluateEvent(this, "getTokenServerUrl", null);
        return this.tokenServerUrl;
    }

    public String getAuthorizationServerEncodedUrl() {
        Ensighten.evaluateEvent(this, "getAuthorizationServerEncodedUrl", null);
        return this.authorizationServerEncodedUrl;
    }

    public AccessMethod getAccessMethod() {
        Ensighten.evaluateEvent(this, "getAccessMethod", null);
        return this.accessMethod;
    }

    public String getUserId() {
        Ensighten.evaluateEvent(this, "getUserId", null);
        return this.userId;
    }

    public void setUserId(String userId) {
        Ensighten.evaluateEvent(this, "setUserId", new Object[]{userId});
        this.userId = userId;
    }
}
