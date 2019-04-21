package com.ensighten;

public class Version {
    public static final int major = 2;
    public static final int minor = 4;
    public static final int revision = 1;

    public static String getLabel() {
        return String.format("%s.%s.%s", new Object[]{Integer.valueOf(2), Integer.valueOf(4), Integer.valueOf(1)});
    }
}
