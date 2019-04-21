package com.alipay.sdk.util;

import android.content.Context;
import android.graphics.BitmapFactory.Options;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import com.alipay.sdk.encrypt.C0619a;
import com.newrelic.agent.android.instrumentation.BitmapFactoryInstrumentation;
import java.io.ByteArrayInputStream;
import java.io.Closeable;

/* renamed from: com.alipay.sdk.util.j */
public class C0653j {
    /* renamed from: a */
    public static Drawable m1038a(String str, Context context) {
        return C0653j.m1039a(str, context, 480);
    }

    /* renamed from: a */
    public static Drawable m1039a(String str, Context context, int i) {
        Closeable closeable;
        Throwable th;
        Closeable byteArrayInputStream;
        try {
            byteArrayInputStream = new ByteArrayInputStream(C0619a.m896a(str));
            try {
                Options options = new Options();
                if (i <= 0) {
                    i = 240;
                }
                options.inDensity = i;
                options.inTargetDensity = context.getResources().getDisplayMetrics().densityDpi;
                BitmapDrawable bitmapDrawable = new BitmapDrawable(context.getResources(), BitmapFactoryInstrumentation.decodeStream(byteArrayInputStream, null, options));
                C0653j.m1040a(byteArrayInputStream);
                return bitmapDrawable;
            } catch (Throwable th2) {
                th = th2;
                C0653j.m1040a(byteArrayInputStream);
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            byteArrayInputStream = null;
            C0653j.m1040a(byteArrayInputStream);
            throw th;
        }
    }

    /* renamed from: a */
    private static void m1040a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Throwable th) {
            }
        }
    }
}
