package com.p044ta.utdid2.p055a.p056a;

/* renamed from: com.ta.utdid2.a.a.e */
public class C4320e {

    /* renamed from: com.ta.utdid2.a.a.e$a */
    private static class C4319a {
        public int[] state;
        /* renamed from: x */
        public int f6716x;
        /* renamed from: y */
        public int f6717y;

        private C4319a() {
            this.state = new int[256];
        }
    }

    /* renamed from: a */
    public static byte[] m7761a(byte[] bArr) {
        if (bArr != null) {
            C4319a a = C4320e.m7760a("QrMgt8GGYI6T52ZY5AnhtxkLzb8egpFn3j5JELI8H6wtACbUnZ5cc3aYTsTRbmkAkRJeYbtx92LPBWm7nBO9UIl7y5i5MQNmUZNf5QENurR5tGyo7yJ2G0MBjWvy6iAtlAbacKP0SwOUeUWx5dsBdyhxa7Id1APtybSdDgicBDuNjI0mlZFUzZSS9dmN8lBD0WTVOMz0pRZbR3cysomRXOO1ghqjJdTcyDIxzpNAEszN8RMGjrzyU7Hjbmwi6YNK");
            if (a != null) {
                return C4320e.m7762a(bArr, a);
            }
        }
        return null;
    }

    /* renamed from: a */
    private static C4319a m7760a(String str) {
        int i = 0;
        if (str == null) {
            return null;
        }
        int i2;
        C4319a c4319a = new C4319a();
        for (i2 = 0; i2 < 256; i2++) {
            c4319a.state[i2] = i2;
        }
        c4319a.f6716x = 0;
        c4319a.f6717y = 0;
        i2 = 0;
        int i3 = 0;
        while (i < 256) {
            try {
                i2 = (i2 + (str.charAt(i3) + c4319a.state[i])) % 256;
                int i4 = c4319a.state[i];
                c4319a.state[i] = c4319a.state[i2];
                c4319a.state[i2] = i4;
                i3 = (i3 + 1) % str.length();
                i++;
            } catch (Exception e) {
                return null;
            }
        }
        return c4319a;
    }

    /* renamed from: a */
    private static byte[] m7762a(byte[] bArr, C4319a c4319a) {
        if (bArr == null || c4319a == null) {
            return null;
        }
        int i = c4319a.f6716x;
        int i2 = c4319a.f6717y;
        for (int i3 = 0; i3 < bArr.length; i3++) {
            i = (i + 1) % 256;
            i2 = (i2 + c4319a.state[i]) % 256;
            int i4 = c4319a.state[i];
            c4319a.state[i] = c4319a.state[i2];
            c4319a.state[i2] = i4;
            i4 = (c4319a.state[i] + c4319a.state[i2]) % 256;
            bArr[i3] = (byte) (c4319a.state[i4] ^ bArr[i3]);
        }
        c4319a.f6716x = i;
        c4319a.f6717y = i2;
        return bArr;
    }
}
