package com.tencent.p050mm.sdk.p064a;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.tencent.p050mm.sdk.p064a.p065a.C4349b;
import com.tencent.p050mm.sdk.p066b.C4353b;
import com.tencent.p050mm.sdk.p066b.C4361h;

/* renamed from: com.tencent.mm.sdk.a.a */
public final class C4350a {

    /* renamed from: com.tencent.mm.sdk.a.a$a */
    public static class C4346a {
        /* renamed from: W */
        public String f6778W;
        /* renamed from: X */
        public String f6779X;
        /* renamed from: Y */
        public String f6780Y;
        /* renamed from: Z */
        public Bundle f6781Z;
        public int flags = -1;

        public final String toString() {
            return "targetPkgName:" + this.f6778W + ", targetClassName:" + this.f6779X + ", content:" + this.f6780Y + ", flags:" + this.flags + ", bundle:" + this.f6781Z;
        }
    }

    /* renamed from: a */
    public static boolean m7881a(Context context, C4346a c4346a) {
        if (context == null) {
            C4353b.m7889b("MicroMsg.SDK.MMessageAct", "send fail, invalid argument");
            return false;
        } else if (C4361h.m7904h(c4346a.f6778W)) {
            C4353b.m7889b("MicroMsg.SDK.MMessageAct", "send fail, invalid targetPkgName, targetPkgName = " + c4346a.f6778W);
            return false;
        } else {
            if (C4361h.m7904h(c4346a.f6779X)) {
                c4346a.f6779X = c4346a.f6778W + ".wxapi.WXEntryActivity";
            }
            C4353b.m7892e("MicroMsg.SDK.MMessageAct", "send, targetPkgName = " + c4346a.f6778W + ", targetClassName = " + c4346a.f6779X);
            Intent intent = new Intent();
            intent.setClassName(c4346a.f6778W, c4346a.f6779X);
            if (c4346a.f6781Z != null) {
                intent.putExtras(c4346a.f6781Z);
            }
            String packageName = context.getPackageName();
            intent.putExtra("_mmessage_sdkVersion", 587268097);
            intent.putExtra("_mmessage_appPackage", packageName);
            intent.putExtra("_mmessage_content", c4346a.f6780Y);
            intent.putExtra("_mmessage_checksum", C4349b.m7880a(c4346a.f6780Y, 587268097, packageName));
            if (c4346a.flags == -1) {
                intent.addFlags(268435456).addFlags(134217728);
            } else {
                intent.setFlags(c4346a.flags);
            }
            try {
                context.startActivity(intent);
                C4353b.m7892e("MicroMsg.SDK.MMessageAct", "send mm message, intent=" + intent);
                return true;
            } catch (Exception e) {
                C4353b.m7888a("MicroMsg.SDK.MMessageAct", "send fail, ex = %s", e.getMessage());
                return false;
            }
        }
    }
}
