package com.baidu.android.pushservice;

import android.content.Context;
import android.os.Handler;
import com.baidu.android.pushservice.util.C1533a;
import com.baidu.android.pushservice.util.C1554p;

public class ADPushManager {
    private static Handler mHandler = null;

    /* renamed from: com.baidu.android.pushservice.ADPushManager$1 */
    static class C12801 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ Context f4584a;

        public void run() {
            ADPushManager.setPushADMsgEnable(this.f4584a, C1533a.m6890a(this.f4584a));
        }
    }

    public static void setPushADMsgEnable(Context context, boolean z) {
        C1554p.m6975a(context, z);
        if (z) {
            C1533a.m6887a(context, 1);
        } else {
            C1533a.m6887a(context, 2);
        }
    }
}
