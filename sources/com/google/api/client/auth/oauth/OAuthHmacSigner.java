package com.google.api.client.auth.oauth;

import com.google.api.client.util.Base64;
import com.google.api.client.util.Beta;
import com.google.api.client.util.StringUtils;
import java.security.GeneralSecurityException;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

@Beta
public final class OAuthHmacSigner implements OAuthSigner {
    public String clientSharedSecret;
    public String tokenSharedSecret;

    public String getSignatureMethod() {
        return "HMAC-SHA1";
    }

    public String computeSignature(String signatureBaseString) throws GeneralSecurityException {
        StringBuilder keyBuf = new StringBuilder();
        String clientSharedSecret = this.clientSharedSecret;
        if (clientSharedSecret != null) {
            keyBuf.append(OAuthParameters.escape(clientSharedSecret));
        }
        keyBuf.append('&');
        String tokenSharedSecret = this.tokenSharedSecret;
        if (tokenSharedSecret != null) {
            keyBuf.append(OAuthParameters.escape(tokenSharedSecret));
        }
        SecretKey secretKey = new SecretKeySpec(StringUtils.getBytesUtf8(keyBuf.toString()), "HmacSHA1");
        Mac mac = Mac.getInstance("HmacSHA1");
        mac.init(secretKey);
        return Base64.encodeBase64String(mac.doFinal(StringUtils.getBytesUtf8(signatureBaseString)));
    }
}
