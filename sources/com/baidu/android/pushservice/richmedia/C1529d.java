package com.baidu.android.pushservice.richmedia;

import com.baidu.android.pushservice.p036h.C1425a;
import com.baidu.android.pushservice.richmedia.C1527c.C1526a;

/* renamed from: com.baidu.android.pushservice.richmedia.d */
public class C1529d {
    /* renamed from: a */
    public static C1527c m6882a(C1526a c1526a, String str) {
        C1527c c1527c = new C1527c();
        c1527c.mo14046a(c1526a);
        switch (c1526a) {
            case REQ_TYPE_GET_ZIP:
                c1527c.mo14049b(str);
                c1527c.mo14047a("GET");
                break;
            default:
                C1425a.m6444e("RequestFactory", "illegal request type " + c1526a);
                break;
        }
        return c1527c;
    }
}
