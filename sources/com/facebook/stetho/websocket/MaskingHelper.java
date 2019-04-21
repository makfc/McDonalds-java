package com.facebook.stetho.websocket;

class MaskingHelper {
    MaskingHelper() {
    }

    public static void unmask(byte[] key, byte[] data, int offset, int count) {
        int i = 0;
        while (true) {
            int i2 = i;
            int count2 = count;
            int i3 = offset;
            count = count2 - 1;
            if (count2 > 0) {
                offset = i3 + 1;
                i = i2 + 1;
                data[i3] = (byte) (data[i3] ^ key[i2 % key.length]);
            } else {
                return;
            }
        }
    }
}
