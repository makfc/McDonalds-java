package com.google.android.gms.gcm;

import java.util.regex.Pattern;

public class GcmPubSub {
    private static final Pattern zzaSQ = Pattern.compile("/topics/[a-zA-Z0-9-_.~%]{1,900}");
}
