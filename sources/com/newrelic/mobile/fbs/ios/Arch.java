package com.newrelic.mobile.fbs.ios;

public final class Arch {
    public static final byte arm64 = (byte) 1;
    public static final byte armv7 = (byte) 0;
    public static final String[] names = new String[]{"armv7", "arm64"};

    private Arch() {
    }

    public static String name(int e) {
        return names[e];
    }
}
