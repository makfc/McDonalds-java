package com.baidu.android.pushservice.p028a.p029a;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;
import com.baidu.android.pushservice.p028a.p029a.C1307a.C1306a;
import com.baidu.android.pushservice.p039k.C1472f;
import java.util.ArrayList;

/* renamed from: com.baidu.android.pushservice.a.a.d */
public class C1311d {
    /* renamed from: a */
    public static final String f4668a = (Environment.getExternalStorageDirectory().getPath() + "/baidu/pushservice/imagecache/");
    /* renamed from: e */
    private static C1311d f4669e;
    /* renamed from: b */
    private C1316f f4670b = new C1316f(20);
    /* renamed from: c */
    private C1309b f4671c = new C1309b(f4668a, 1, this.f4672d, this.f4670b);
    /* renamed from: d */
    private int f4672d = 2073600;

    private C1311d() {
    }

    /* renamed from: a */
    public static C1311d m5919a() {
        if (f4669e == null) {
            f4669e = new C1311d();
        }
        return f4669e;
    }

    /* renamed from: a */
    private Bitmap[] m5921a(Uri... uriArr) {
        Bitmap[] bitmapArr = new Bitmap[uriArr.length];
        for (int i = 0; i < bitmapArr.length; i++) {
            if (C1312e.m5924a(uriArr[i])) {
                String a = C1472f.m6716a(uriArr[i].toString().getBytes(), false);
                if (!TextUtils.isEmpty(a)) {
                    bitmapArr[i] = this.f4670b.mo13564a(a);
                    if (bitmapArr[i] == null) {
                        bitmapArr[i] = this.f4671c.mo13558a(a);
                    }
                }
            } else {
                bitmapArr[i] = null;
            }
        }
        return bitmapArr;
    }

    /* renamed from: a */
    public void mo13561a(Context context, final C1306a c1306a, final Uri... uriArr) {
        if (uriArr == null || uriArr.length < 1) {
            c1306a.mo13554a(new Bitmap[0]);
            return;
        }
        final ArrayList arrayList = new ArrayList();
        for (int i = 0; i < uriArr.length; i++) {
            if (C1312e.m5924a(uriArr[i])) {
                String a = C1472f.m6716a(uriArr[i].toString().getBytes(), false);
                if (!TextUtils.isEmpty(a) && this.f4670b.mo13564a(a) == null && this.f4671c.mo13558a(a) == null) {
                    arrayList.add(uriArr[i]);
                }
            }
        }
        if (arrayList.isEmpty()) {
            c1306a.mo13554a(m5921a(uriArr));
        } else {
            new C1307a(this.f4672d, new C1306a() {
                /* renamed from: a */
                public void mo13554a(Bitmap... bitmapArr) {
                    if (bitmapArr != null) {
                        for (int i = 0; i < bitmapArr.length; i++) {
                            if (bitmapArr[i] != null) {
                                String a = C1472f.m6716a(((Uri) arrayList.get(i)).toString().getBytes(), false);
                                C1311d.this.f4671c.mo13557a(a, bitmapArr[i]);
                                C1311d.this.f4670b.mo13557a(a, bitmapArr[i]);
                            }
                        }
                    }
                    c1306a.mo13554a(C1311d.this.m5921a(uriArr));
                }
            }, (Uri[]) arrayList.toArray(new Uri[arrayList.size()])).start();
        }
    }
}
