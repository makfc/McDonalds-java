package com.mcdonalds.app.ordering.alipay;

public final class Base64 {
    private static char PAD = '=';
    private static byte[] base64Alphabet = new byte[128];
    private static char[] lookUpBase64Alphabet = new char[64];

    static {
        int i;
        for (i = 0; i < 128; i++) {
            base64Alphabet[i] = (byte) -1;
        }
        for (i = 90; i >= 65; i--) {
            base64Alphabet[i] = (byte) (i - 65);
        }
        for (i = 122; i >= 97; i--) {
            base64Alphabet[i] = (byte) ((i - 97) + 26);
        }
        for (i = 57; i >= 48; i--) {
            base64Alphabet[i] = (byte) ((i - 48) + 52);
        }
        base64Alphabet[43] = (byte) 62;
        base64Alphabet[47] = (byte) 63;
        for (i = 0; i <= 25; i++) {
            lookUpBase64Alphabet[i] = (char) (i + 65);
        }
        i = 26;
        int j = 0;
        while (i <= 51) {
            lookUpBase64Alphabet[i] = (char) (j + 97);
            i++;
            j++;
        }
        i = 52;
        j = 0;
        while (i <= 61) {
            lookUpBase64Alphabet[i] = (char) (j + 48);
            i++;
            j++;
        }
        lookUpBase64Alphabet[62] = '+';
        lookUpBase64Alphabet[63] = '/';
    }
}
