package com.crashlytics.android.core;

public class UserMetaData {
    public static final UserMetaData EMPTY = new UserMetaData();
    public final String email;
    /* renamed from: id */
    public final String f5584id;
    public final String name;

    public UserMetaData() {
        this(null, null, null);
    }

    public UserMetaData(String id, String name, String email) {
        this.f5584id = id;
        this.name = name;
        this.email = email;
    }
}
