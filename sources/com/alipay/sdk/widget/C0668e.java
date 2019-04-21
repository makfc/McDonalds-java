package com.alipay.sdk.widget;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.alipay.sdk.util.C0646c;

/* renamed from: com.alipay.sdk.widget.e */
public class C0668e {
    /* renamed from: a */
    private static boolean f693a = (VERSION.SDK_INT >= 11);

    /* renamed from: a */
    public static Dialog m1120a(Context context, String str, String str2, String str3, OnClickListener onClickListener, String str4, OnClickListener onClickListener2) {
        Builder a = C0668e.m1119a(context, str, str3, onClickListener, str4, onClickListener2);
        a.setTitle(str);
        a.setMessage(str2);
        AlertDialog create = a.create();
        create.setCanceledOnTouchOutside(false);
        create.setOnKeyListener(new C0669f());
        try {
            create.show();
        } catch (Throwable th) {
            C0646c.m1015a("msp", th);
        }
        return create;
    }

    /* renamed from: a */
    private static Builder m1119a(Context context, String str, String str2, OnClickListener onClickListener, String str3, OnClickListener onClickListener2) {
        Builder builder = new Builder(context);
        if (f693a) {
            if (!(TextUtils.isEmpty(str3) || onClickListener2 == null)) {
                builder.setPositiveButton(str3, onClickListener2);
            }
            if (!(TextUtils.isEmpty(str2) || onClickListener == null)) {
                builder.setNegativeButton(str2, onClickListener);
            }
        } else {
            if (!(TextUtils.isEmpty(str2) || onClickListener == null)) {
                builder.setPositiveButton(str2, onClickListener);
            }
            if (!(TextUtils.isEmpty(str3) || onClickListener2 == null)) {
                builder.setNegativeButton(str3, onClickListener2);
            }
        }
        return builder;
    }
}
