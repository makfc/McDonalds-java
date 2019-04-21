package com.newrelic.agent.android.util;

import android.util.Base64;

public class AndroidEncoder implements Encoder {
    public String encode(byte[] bytes) {
        return Base64.encodeToString(bytes, 0);
    }
}
