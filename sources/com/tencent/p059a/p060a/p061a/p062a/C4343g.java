package com.tencent.p059a.p060a.p061a.p062a;

import android.content.Context;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/* renamed from: com.tencent.a.a.a.a.g */
public final class C4343g {
    /* renamed from: V */
    private static C4343g f6774V = null;
    /* renamed from: U */
    private Map<Integer, C4338f> f6775U = null;
    /* renamed from: b */
    private int f6776b = 0;
    /* renamed from: c */
    private Context f6777c = null;

    private C4343g(Context context) {
        this.f6777c = context.getApplicationContext();
        this.f6775U = new HashMap(3);
        this.f6775U.put(Integer.valueOf(1), new C4342e(context));
        this.f6775U.put(Integer.valueOf(2), new C4339b(context));
        this.f6775U.put(Integer.valueOf(4), new C4341d(context));
    }

    /* renamed from: E */
    public static synchronized C4343g m7865E(Context context) {
        C4343g c4343g;
        synchronized (C4343g.class) {
            if (f6774V == null) {
                f6774V = new C4343g(context);
            }
            c4343g = f6774V;
        }
        return c4343g;
    }

    /* renamed from: b */
    private C4340c m7866b(List<Integer> list) {
        if (list.size() >= 0) {
            for (Integer num : list) {
                C4338f c4338f = (C4338f) this.f6775U.get(num);
                if (c4338f != null) {
                    C4340c o = c4338f.mo33760o();
                    if (o != null && C4344h.m7875c(o.f6773c)) {
                        return o;
                    }
                }
            }
        }
        return new C4340c();
    }

    /* renamed from: a */
    public final void mo33763a(String str) {
        C4340c p = mo33764p();
        p.f6773c = str;
        if (!C4344h.m7874b(p.f6771a)) {
            p.f6771a = C4344h.m7869a(this.f6777c);
        }
        if (!C4344h.m7874b(p.f6772b)) {
            p.f6772b = C4344h.m7873b(this.f6777c);
        }
        p.f6770T = System.currentTimeMillis();
        for (Entry value : this.f6775U.entrySet()) {
            ((C4338f) value.getValue()).mo33756a(p);
        }
    }

    /* renamed from: p */
    public final C4340c mo33764p() {
        return m7866b(new ArrayList(Arrays.asList(new Integer[]{Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(4)})));
    }
}
