package com.kochava.base;

import android.support.annotation.NonNull;
import java.util.Map;

public interface DeepLinkListener {
    void onDeepLink(@NonNull Map<String, String> map);
}
