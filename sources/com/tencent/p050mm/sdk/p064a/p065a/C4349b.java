package com.tencent.p050mm.sdk.p064a.p065a;

import com.tencent.p050mm.p063a.C4345a;

/* renamed from: com.tencent.mm.sdk.a.a.b */
public final class C4349b {
    /* renamed from: a */
    public static byte[] m7880a(String str, int i, String str2) {
        StringBuffer stringBuffer = new StringBuffer();
        if (str != null) {
            stringBuffer.append(str);
        }
        stringBuffer.append(i);
        stringBuffer.append(str2);
        stringBuffer.append("mMcShCsTr");
        return C4345a.m7878c(stringBuffer.toString().substring(1, 9).getBytes()).getBytes();
    }
}
