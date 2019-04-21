package com.tencent.p050mm.sdk.p064a.p065a;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.tencent.p050mm.sdk.p066b.C4353b;
import com.tencent.p050mm.sdk.p066b.C4361h;

/* renamed from: com.tencent.mm.sdk.a.a.a */
public final class C4348a {

    /* renamed from: com.tencent.mm.sdk.a.a.a$a */
    public static class C4347a {
        /* renamed from: Y */
        public String f6782Y;
        /* renamed from: Z */
        public Bundle f6783Z;
        /* renamed from: aa */
        public String f6784aa;
        /* renamed from: ab */
        public String f6785ab;
    }

    /* renamed from: a */
    public static boolean m7879a(Context context, C4347a c4347a) {
        if (context == null) {
            C4353b.m7889b("MicroMsg.SDK.MMessage", "send fail, invalid argument");
            return false;
        } else if (C4361h.m7904h(c4347a.f6785ab)) {
            C4353b.m7889b("MicroMsg.SDK.MMessage", "send fail, action is null");
            return false;
        } else {
            String str = null;
            if (!C4361h.m7904h(c4347a.f6784aa)) {
                str = c4347a.f6784aa + ".permission.MM_MESSAGE";
            }
            Intent intent = new Intent(c4347a.f6785ab);
            if (c4347a.f6783Z != null) {
                intent.putExtras(c4347a.f6783Z);
            }
            String packageName = context.getPackageName();
            intent.putExtra("_mmessage_sdkVersion", 587268097);
            intent.putExtra("_mmessage_appPackage", packageName);
            intent.putExtra("_mmessage_content", c4347a.f6782Y);
            intent.putExtra("_mmessage_checksum", C4349b.m7880a(c4347a.f6782Y, 587268097, packageName));
            context.sendBroadcast(intent, str);
            C4353b.m7892e("MicroMsg.SDK.MMessage", "send mm message, intent=" + intent + ", perm=" + str);
            return true;
        }
    }
}
