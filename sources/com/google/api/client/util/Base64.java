package com.google.api.client.util;

public class Base64 {
    public static String encodeBase64String(byte[] binaryData) {
        return com.google.api.client.repackaged.org.apache.commons.codec.binary.Base64.encodeBase64String(binaryData);
    }

    public static byte[] decodeBase64(String base64String) {
        return com.google.api.client.repackaged.org.apache.commons.codec.binary.Base64.decodeBase64(base64String);
    }

    private Base64() {
    }
}
