package com.rdisoftware.security;

import android.util.Base64;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class RdiSecurityCrypto {
    public final String computeHash(String applicationId, String applicationVersion, String applicationNonce) {
        return base64StringEncode(md5("=PA64E47237FC34714AF852B795DAF8DEC\\o/" + applicationVersion + "o|o" + applicationId + "=/" + applicationNonce));
    }

    private final String md5(String input) {
        String stringMD5 = "";
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(input.getBytes());
            byte[] messageDigest = digest.digest();
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                String h = Integer.toHexString(aMessageDigest & 255);
                while (h.length() < 2) {
                    h = "0" + h;
                }
                hexString.append(h);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return stringMD5;
        }
    }

    private final String base64StringEncode(String input) {
        return new String(Base64.encode(input.getBytes(), 2));
    }
}
