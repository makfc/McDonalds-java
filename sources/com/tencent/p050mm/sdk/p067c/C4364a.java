package com.tencent.p050mm.sdk.p067c;

import android.net.Uri;
import android.provider.BaseColumns;
import com.tencent.p050mm.sdk.p066b.C4353b;

/* renamed from: com.tencent.mm.sdk.c.a */
public final class C4364a {

    /* renamed from: com.tencent.mm.sdk.c.a$a */
    public static final class C4362a {
        /* renamed from: a */
        public static Object m7906a(int i, String str) {
            switch (i) {
                case 1:
                    return Integer.valueOf(str);
                case 2:
                    return Long.valueOf(str);
                case 3:
                    return str;
                case 4:
                    return Boolean.valueOf(str);
                case 5:
                    return Float.valueOf(str);
                case 6:
                    return Double.valueOf(str);
                default:
                    try {
                        C4353b.m7889b("MicroMsg.SDK.PluginProvider.Resolver", "unknown type");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return null;
            }
        }
    }

    /* renamed from: com.tencent.mm.sdk.c.a$b */
    public static final class C4363b implements BaseColumns {
        public static final Uri CONTENT_URI = Uri.parse("content://com.tencent.mm.sdk.plugin.provider/sharedpref");
    }
}
