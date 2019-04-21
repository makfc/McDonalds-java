package com.rdisoftware.security;

public class RdiSecurity {
    public static String computeHash(String applicationId, String applicationVersion, String applicationNonce) {
        return new RdiSecurityCrypto().computeHash(applicationId, applicationVersion, applicationNonce);
    }
}
