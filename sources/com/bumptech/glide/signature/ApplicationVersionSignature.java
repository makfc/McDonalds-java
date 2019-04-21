package com.bumptech.glide.signature;

import com.bumptech.glide.load.Key;
import java.util.concurrent.ConcurrentHashMap;

public final class ApplicationVersionSignature {
    private static final ConcurrentHashMap<String, Key> PACKAGE_NAME_TO_KEY = new ConcurrentHashMap();

    private ApplicationVersionSignature() {
    }
}
