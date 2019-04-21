package com.p044ta.utdid2.device;

import com.p044ta.utdid2.p055a.p056a.C4311a;
import com.p044ta.utdid2.p055a.p056a.C4315b;
import com.p044ta.utdid2.p055a.p056a.C4321f;

/* renamed from: com.ta.utdid2.device.e */
public class C4336e {
    /* renamed from: d */
    public String mo33754d(String str) {
        return C4311a.m7756b(str);
    }

    /* renamed from: e */
    public String mo33755e(String str) {
        String b = C4311a.m7756b(str);
        if (C4321f.isEmpty(b)) {
            return null;
        }
        try {
            return new String(C4315b.decode(b, 0));
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
