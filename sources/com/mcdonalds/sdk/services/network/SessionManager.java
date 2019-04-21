package com.mcdonalds.sdk.services.network;

import android.support.annotation.NonNull;
import com.mcdonalds.sdk.connectors.middleware.request.MWCustomerRegisterRequest;
import com.mcdonalds.sdk.connectors.middleware.request.MWCustomerResendActivationRequest;
import com.mcdonalds.sdk.connectors.middleware.request.MWCustomerResetPasswordRequest;
import com.mcdonalds.sdk.connectors.middleware.request.MWGetStoreAdvertisableRequest;
import com.mcdonalds.sdk.services.data.LocalDataManager;
import java.util.Map;
import java.util.Observable;

public class SessionManager extends Observable {
    private static final String CACHE_KEY_REFRESH_TOKEN = "cache_refresh_token";
    private static final String CACHE_KEY_TOKEN = "cache_token";
    private static final String CACHE_KEY_TOKEN_AUTHENTICATED = "cache_token_authenticated";
    private static final String KEY_AUTHORIZATION = "Authorization";
    private static final String KEY_TOKEN = "Token";
    private static final String KEY_USER_NAME = "userName";
    private static SessionManager sInstance;
    private boolean mIsTokenAuthenticated;
    private String mRefreshToken;
    private boolean mRefreshing;
    private String mToken;

    public SessionManager() {
        LocalDataManager localDataManager = LocalDataManager.getSharedInstance();
        this.mToken = localDataManager.getString(CACHE_KEY_TOKEN, null);
        this.mRefreshToken = localDataManager.getString(CACHE_KEY_REFRESH_TOKEN, null);
        this.mIsTokenAuthenticated = localDataManager.getBoolean(CACHE_KEY_TOKEN_AUTHENTICATED, false);
    }

    public void applyCurrentToken(RequestProvider request) {
        if (isTokenNeeded(request)) {
            String tokenKey = null;
            String newToken = null;
            String tokenValue;
            if (request.getHeaders().containsKey("Token")) {
                tokenKey = "Token";
                tokenValue = (String) request.getHeaders().get("Token");
                newToken = getToken();
            } else if (request.getHeaders().containsKey(KEY_AUTHORIZATION)) {
                tokenKey = KEY_AUTHORIZATION;
                tokenValue = "Bearer " + request.getHeaders().get(KEY_AUTHORIZATION);
                newToken = "Bearer " + getToken();
            }
            if (tokenKey != null) {
                request.getHeaders().put(tokenKey, newToken);
            }
        }
    }

    public boolean isTokenNeeded(RequestProvider request) {
        return request.getHeaders() != null && (request.getHeaders().containsKey("Token") || request.getHeaders().containsKey(KEY_AUTHORIZATION));
    }

    public boolean isAuthenticationNeeded(@NonNull RequestProvider request) {
        return ((!containsHeader(request.getHeaders(), KEY_USER_NAME) && !containsParameter(request.getBody(), KEY_USER_NAME) && !containsParameter(request.getURLString(), KEY_USER_NAME)) || (request instanceof MWCustomerRegisterRequest) || (request instanceof MWCustomerResetPasswordRequest) || (request instanceof MWCustomerResendActivationRequest) || (request instanceof MWGetStoreAdvertisableRequest)) ? false : true;
    }

    public void updateRequestToken(String token, RequestProvider request) {
        Map headers = request.getHeaders();
        if (headers == null) {
            return;
        }
        if (headers.containsKey("Token")) {
            headers.put("Token", token);
        } else if (headers.containsKey(KEY_AUTHORIZATION)) {
            headers.put(KEY_AUTHORIZATION, "Bearer " + token);
        }
    }

    private boolean containsParameter(String body, String key) {
        return body != null && body.contains(key);
    }

    private boolean containsHeader(Map headers, String key) {
        return headers != null && headers.containsKey(key);
    }

    public String getToken() {
        return this.mToken != null ? this.mToken : "";
    }

    public void clearToken() {
        this.mToken = null;
        this.mRefreshToken = null;
        this.mIsTokenAuthenticated = false;
        this.mRefreshing = false;
        LocalDataManager.getSharedInstance().remove(CACHE_KEY_TOKEN);
        LocalDataManager.getSharedInstance().remove(CACHE_KEY_REFRESH_TOKEN);
        LocalDataManager.getSharedInstance().remove(CACHE_KEY_TOKEN_AUTHENTICATED);
    }

    public void setToken(String token) {
        if (!this.mIsTokenAuthenticated || token == null) {
            this.mToken = token;
            LocalDataManager.getSharedInstance().set(CACHE_KEY_TOKEN, token);
        }
    }

    public String getRefreshToken() {
        return this.mRefreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.mRefreshToken = refreshToken;
        LocalDataManager.getSharedInstance().set(CACHE_KEY_REFRESH_TOKEN, refreshToken);
    }

    public void notifyTokenRefreshed() {
        this.mRefreshing = false;
        setChanged();
        notifyObservers();
    }

    public static SessionManager getInstance() {
        if (sInstance == null) {
            sInstance = new SessionManager();
        }
        return sInstance;
    }

    public static void setInstance(SessionManager sessionManager) {
        sInstance = sessionManager;
    }

    public boolean isTokenAuthenticated() {
        return this.mIsTokenAuthenticated;
    }

    public void setTokenAuthenticated(boolean tokenAuthenticated) {
        this.mIsTokenAuthenticated = tokenAuthenticated;
        LocalDataManager.getSharedInstance().set(CACHE_KEY_TOKEN_AUTHENTICATED, tokenAuthenticated);
    }

    public void setRefreshing(boolean refreshing) {
        this.mRefreshing = refreshing;
    }

    public boolean isRefreshing() {
        return this.mRefreshing;
    }
}
