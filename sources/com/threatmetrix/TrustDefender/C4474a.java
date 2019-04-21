package com.threatmetrix.TrustDefender;

import android.content.Context;
import com.newrelic.agent.android.util.SafeJsonPrimitive;
import com.threatmetrix.TrustDefender.C4532g.C4515a;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* renamed from: com.threatmetrix.TrustDefender.a */
class C4474a {
    /* renamed from: a */
    private static final String f7329a = C4549w.m8585a(C4474a.class);

    C4474a() {
    }

    /* renamed from: a */
    private static String m8265a(String path) throws InterruptedException {
        if (NativeGatherer.m8207a().mo34053b()) {
            return NativeGatherer.m8207a().mo34047a(path);
        }
        String str = f7329a;
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            try {
                InputStream is = new FileInputStream(path);
                String md5String = null;
                byte[] buffer = new byte[8192];
                while (true) {
                    try {
                        int read = is.read(buffer);
                        if (read <= 0) {
                            break;
                        }
                        digest.update(buffer, 0, read);
                    } catch (IOException e) {
                        str = f7329a;
                        try {
                            is.close();
                        } catch (IOException e2) {
                            str = f7329a;
                        }
                    } catch (Throwable th) {
                        try {
                            is.close();
                        } catch (IOException e3) {
                            String str2 = f7329a;
                        }
                        throw th;
                    }
                }
                md5String = String.format("%32s", new Object[]{new BigInteger(1, digest.digest()).toString(16)}).replace(SafeJsonPrimitive.NULL_CHAR, '0');
                try {
                    is.close();
                } catch (IOException e4) {
                    str = f7329a;
                }
                str = f7329a;
                new StringBuilder("Got : ").append(md5String);
                return md5String;
            } catch (FileNotFoundException e5) {
                str = f7329a;
                return null;
            }
        } catch (NoSuchAlgorithmException e6) {
            str = f7329a;
            return null;
        }
    }

    /* renamed from: a */
    static String m8264a(Context context) throws InterruptedException {
        String sourceDir = new C4515a(context).mo34222b();
        C4549w.m8594c(f7329a, "sourceDir: " + sourceDir);
        if (C4491ai.m8349f(sourceDir)) {
            return C4474a.m8265a(sourceDir);
        }
        return null;
    }
}
