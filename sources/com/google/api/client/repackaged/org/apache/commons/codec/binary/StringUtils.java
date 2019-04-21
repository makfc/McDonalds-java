package com.google.api.client.repackaged.org.apache.commons.codec.binary;

import com.facebook.stetho.common.Utf8Charset;
import java.io.UnsupportedEncodingException;

public class StringUtils {
    public static byte[] getBytesUtf8(String string) {
        return getBytesUnchecked(string, Utf8Charset.NAME);
    }

    public static byte[] getBytesUnchecked(String string, String charsetName) {
        if (string == null) {
            return null;
        }
        try {
            return string.getBytes(charsetName);
        } catch (UnsupportedEncodingException e) {
            throw newIllegalStateException(charsetName, e);
        }
    }

    private static IllegalStateException newIllegalStateException(String charsetName, UnsupportedEncodingException e) {
        return new IllegalStateException(charsetName + ": " + e);
    }

    public static String newString(byte[] bytes, String charsetName) {
        if (bytes == null) {
            return null;
        }
        try {
            return new String(bytes, charsetName);
        } catch (UnsupportedEncodingException e) {
            throw newIllegalStateException(charsetName, e);
        }
    }

    public static String newStringUtf8(byte[] bytes) {
        return newString(bytes, Utf8Charset.NAME);
    }
}
