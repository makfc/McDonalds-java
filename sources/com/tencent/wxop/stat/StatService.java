package com.tencent.wxop.stat;

import android.content.Context;
import java.util.Properties;

public class StatService {
    public static void onPause(Context context) {
        StatServiceImpl.onPause(context, null);
    }

    public static void onResume(Context context) {
        StatServiceImpl.onResume(context, null);
    }

    public static boolean startStatService(Context context, String str, String str2) {
        return StatServiceImpl.startStatService(context, str, str2, null);
    }

    public static void trackCustomKVEvent(Context context, String str, Properties properties) {
        StatServiceImpl.trackCustomKVEvent(context, str, properties, null);
    }
}
