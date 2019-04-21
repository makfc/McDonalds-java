package com.facebook;

public enum SessionLoginBehavior {
    SSO_WITH_FALLBACK(true, true),
    SSO_ONLY(true, false),
    SUPPRESS_SSO(false, true);
    
    private final boolean allowsKatanaAuth;
    private final boolean allowsWebViewAuth;

    private SessionLoginBehavior(boolean allowsKatanaAuth, boolean allowsWebViewAuth) {
        this.allowsKatanaAuth = allowsKatanaAuth;
        this.allowsWebViewAuth = allowsWebViewAuth;
    }

    /* Access modifiers changed, original: 0000 */
    public boolean allowsKatanaAuth() {
        return this.allowsKatanaAuth;
    }

    /* Access modifiers changed, original: 0000 */
    public boolean allowsWebViewAuth() {
        return this.allowsWebViewAuth;
    }
}
