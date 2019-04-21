package com.baidu.android.pushservice.util;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.baidu.android.pushservice.p036h.C1425a;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.baidu.android.pushservice.util.e */
public class C1537e {
    /* renamed from: a */
    private static Map<Long, C1539f> f5374a;

    /* renamed from: a */
    public static synchronized void m6907a(long j) {
        synchronized (C1537e.class) {
            if (f5374a.containsKey(Long.valueOf(j))) {
                f5374a.remove(f5374a.get(Long.valueOf(j)));
            }
        }
    }

    /* renamed from: a */
    public static void m6908a(Context context, Intent intent) {
        C1425a.m6442c("CrossAppMessageCenter", "receiveIntent: " + intent.toUri(0));
        if (intent.hasExtra("bd.cross.request.COMMAND_TYPE")) {
            String stringExtra = intent.getStringExtra("bd.cross.request.COMMAND_TYPE");
            if (!TextUtils.isEmpty(stringExtra)) {
                if (stringExtra.equals("bd.cross.command.MESSAGE_ACK") || stringExtra.equals("bd.cross.command.ULTRON_ACK")) {
                    long longExtra = intent.getLongExtra("bd.cross.request.ID", 0);
                    if (longExtra != 0 && f5374a != null) {
                        C1425a.m6442c("CrossAppMessageCenter", "requestId: " + longExtra + "  found: " + f5374a.containsKey(Long.valueOf(longExtra)));
                        if (f5374a.containsKey(Long.valueOf(longExtra))) {
                            ((C1539f) f5374a.get(Long.valueOf(longExtra))).mo14062a(intent);
                            f5374a.remove(f5374a.get(Long.valueOf(longExtra)));
                        }
                    }
                } else if (stringExtra.equals("bd.cross.command.ULTRON_DELIVER")) {
                    C1425a.m6442c("CrossAppMessageCenter", "requestId: " + intent.getLongExtra("bd.cross.request.ID", 0));
                    stringExtra = intent.getStringExtra("bd.cross.request.SOURCE_SERVICE");
                    String stringExtra2 = intent.getStringExtra("bd.cross.request.SOURCE_PACKAGE");
                    if (!TextUtils.isEmpty(stringExtra) && !TextUtils.isEmpty(stringExtra2)) {
                        intent.setPackage(stringExtra2);
                        intent.setClassName(stringExtra2, stringExtra);
                        intent.setAction("com.baidu.android.pushservice.action.CROSS_REQUEST");
                        intent.putExtra("bd.cross.request.SENDING", false);
                        intent.putExtra("bd.cross.request.RESULT_CODE", (short) 10);
                        intent.putExtra("bd.cross.request.RESULT_DATA", "{DATA:\"OK\"}");
                        intent.putExtra("bd.cross.request.COMMAND_TYPE", "bd.cross.command.ULTRON_ACK");
                        context.startService(intent);
                    }
                }
            }
        }
    }

    /* renamed from: a */
    public static synchronized void m6909a(C1539f c1539f) {
        synchronized (C1537e.class) {
            if (f5374a == null) {
                f5374a = Collections.synchronizedMap(new HashMap());
            }
            if (f5374a.containsKey(Long.valueOf(c1539f.mo14061a()))) {
                ((C1539f) f5374a.remove(c1539f)).mo14061a();
            }
            f5374a.put(Long.valueOf(c1539f.mo14061a()), c1539f);
        }
    }
}
