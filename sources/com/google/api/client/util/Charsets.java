package com.google.api.client.util;

import com.facebook.stetho.common.Utf8Charset;
import java.nio.charset.Charset;

public final class Charsets {
    public static final Charset ISO_8859_1 = Charset.forName("ISO-8859-1");
    public static final Charset UTF_8 = Charset.forName(Utf8Charset.NAME);

    private Charsets() {
    }
}
