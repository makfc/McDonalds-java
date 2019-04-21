package com.alipay.sdk.app.statistic;

import android.content.Context;
import android.text.TextUtils;
import com.alipay.sdk.packet.impl.C0635d;
import com.alipay.sdk.util.C0652i;
import java.io.IOException;

/* renamed from: com.alipay.sdk.app.statistic.b */
final class C0591b implements Runnable {
    /* renamed from: a */
    final /* synthetic */ Context f512a;
    /* renamed from: b */
    final /* synthetic */ String f513b;

    C0591b(Context context, String str) {
        this.f512a = context;
        this.f513b = str;
    }

    public void run() {
        C0635d c0635d = new C0635d();
        try {
            String b = C0652i.m1036b(this.f512a, "alipay_cashier_statistic_record", null);
            if (!(TextUtils.isEmpty(b) || c0635d.mo8074a(this.f512a, b) == null)) {
                C0652i.m1037b(this.f512a, "alipay_cashier_statistic_record");
            }
        } catch (Throwable th) {
        }
        try {
            if (!TextUtils.isEmpty(this.f513b)) {
                c0635d.mo8074a(this.f512a, this.f513b);
            }
        } catch (IOException e) {
            C0652i.m1035a(this.f512a, "alipay_cashier_statistic_record", this.f513b);
        } catch (Throwable th2) {
        }
    }
}
