package com.amap.api.services.poisearch;

import android.content.Context;
import com.amap.api.services.core.C1076q;

/* renamed from: com.amap.api.services.poisearch.g */
abstract class PoiHandler<T, V> extends C1076q<T, V> {
    public PoiHandler(Context context, T t) {
        super(context, t);
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public boolean mo11983a(String str) {
        if (str == null || str.equals("") || str.equals("[]")) {
            return true;
        }
        return false;
    }
}
