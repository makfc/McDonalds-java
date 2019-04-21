package com.baidu.android.pushservice.p028a.p029a;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory.Options;
import android.net.Uri;
import com.baidu.android.pushservice.p034f.C1403b;
import com.baidu.android.pushservice.p036h.C1425a;
import com.newrelic.agent.android.instrumentation.BitmapFactoryInstrumentation;

/* renamed from: com.baidu.android.pushservice.a.a.a */
public class C1307a extends Thread {
    /* renamed from: a */
    private C1306a f4656a;
    /* renamed from: b */
    private Uri[] f4657b;
    /* renamed from: c */
    private int f4658c = 2073600;

    /* renamed from: com.baidu.android.pushservice.a.a.a$a */
    public interface C1306a {
        /* renamed from: a */
        void mo13554a(Bitmap... bitmapArr);
    }

    public C1307a(int i, C1306a c1306a, Uri... uriArr) {
        if (c1306a == null) {
            C1425a.m6444e("DownLoadThread", "listener is null");
            return;
        }
        this.f4658c = i;
        this.f4656a = c1306a;
        this.f4657b = uriArr;
    }

    /* renamed from: a */
    static int m5907a(Options options, int i, int i2) {
        int b = C1307a.m5910b(options, i, i2);
        if (b > 8) {
            return ((b + 7) / 8) * 8;
        }
        int i3 = 1;
        while (i3 < b) {
            i3 <<= 1;
        }
        return i3;
    }

    /* renamed from: a */
    private byte[] m5908a(Uri uri) {
        byte[] bArr = null;
        if (uri == null || uri.toString().length() == 0 || !C1312e.m5924a(uri)) {
            C1425a.m6444e("DownLoadThread", "getInputStreamFromUri function's uri param is error");
            return bArr;
        }
        try {
            return C1403b.m6268a(C1403b.m6259a(uri.toString(), "GET", null).mo13742a());
        } catch (Exception e) {
            C1425a.m6444e("DownLoadThread", "Uri can't open a inputstream");
            return bArr;
        }
    }

    /* renamed from: a */
    private Bitmap[] m5909a(Uri[] uriArr) {
        if (uriArr == null || uriArr.length < 1) {
            return null;
        }
        Bitmap[] bitmapArr = new Bitmap[uriArr.length];
        for (int i = 0; i < uriArr.length; i++) {
            byte[] a = m5908a(uriArr[i]);
            if (a != null) {
                try {
                    if (a.length > 0) {
                        Options options = new Options();
                        options.inJustDecodeBounds = true;
                        BitmapFactoryInstrumentation.decodeByteArray(a, 0, a.length, options);
                        options.inSampleSize = C1307a.m5907a(options, -1, this.f4658c);
                        options.inJustDecodeBounds = false;
                        bitmapArr[i] = BitmapFactoryInstrumentation.decodeByteArray(a, 0, a.length, options);
                    }
                } catch (OutOfMemoryError e) {
                    C1425a.m6444e("DownLoadThread", "out of memory err no bitmap found");
                    bitmapArr[i] = null;
                } catch (Exception e2) {
                    C1425a.m6444e("DownLoadThread", "IO exception" + e2);
                    bitmapArr[i] = null;
                }
            }
            bitmapArr[i] = null;
        }
        return bitmapArr;
    }

    /* renamed from: b */
    private static int m5910b(Options options, int i, int i2) {
        double d = (double) options.outWidth;
        double d2 = (double) options.outHeight;
        int ceil = i2 == -1 ? 1 : (int) Math.ceil(Math.sqrt((d * d2) / ((double) i2)));
        int min = i == -1 ? 128 : (int) Math.min(Math.floor(d / ((double) i)), Math.floor(d2 / ((double) i)));
        return min < ceil ? ceil : (i2 == -1 && i == -1) ? 1 : i != -1 ? min : ceil;
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public void mo13555a(Bitmap[] bitmapArr) {
        if (this.f4656a != null) {
            this.f4656a.mo13554a(bitmapArr);
        }
    }

    public void run() {
        mo13555a(m5909a(this.f4657b));
    }
}
