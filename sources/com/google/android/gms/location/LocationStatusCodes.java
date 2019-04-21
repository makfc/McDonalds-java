package com.google.android.gms.location;

import com.google.android.gms.common.api.Status;

@Deprecated
public final class LocationStatusCodes {
    private LocationStatusCodes() {
    }

    public static int zzib(int i) {
        return (i < 0 || i > 1) ? (1000 > i || i > 1002) ? 1 : i : i;
    }

    public static Status zzic(int i) {
        switch (i) {
            case 1:
                i = 13;
                break;
        }
        return new Status(i);
    }
}
