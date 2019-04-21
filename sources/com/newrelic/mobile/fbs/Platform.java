package com.newrelic.mobile.fbs;

public final class Platform {
    public static final byte Android = (byte) 0;
    public static final byte iOS = (byte) 1;
    public static final String[] names = new String[]{"Android", "iOS", "tvOS"};
    public static final byte tvOS = (byte) 2;

    private Platform() {
    }

    public static String name(int e) {
        return names[e];
    }
}
