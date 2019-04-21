package com.google.api.client.util;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.SignatureException;

public final class SecurityUtils {
    public static Signature getSha1WithRsaSignatureAlgorithm() throws NoSuchAlgorithmException {
        return Signature.getInstance("SHA1withRSA");
    }

    public static byte[] sign(Signature signatureAlgorithm, PrivateKey privateKey, byte[] contentBytes) throws InvalidKeyException, SignatureException {
        signatureAlgorithm.initSign(privateKey);
        signatureAlgorithm.update(contentBytes);
        return signatureAlgorithm.sign();
    }

    private SecurityUtils() {
    }
}
