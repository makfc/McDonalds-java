package com.threatmetrix.TrustDefender;

import com.facebook.stetho.common.Utf8Charset;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Pattern;

/* renamed from: com.threatmetrix.TrustDefender.ai */
class C4491ai {
    /* renamed from: a */
    private static final String f7392a = C4549w.m8585a(C4491ai.class);
    /* renamed from: b */
    private static final char[] f7393b = "0123456789abcdef".toCharArray();
    /* renamed from: c */
    private static final MessageDigest f7394c;
    /* renamed from: d */
    private static final MessageDigest f7395d;
    /* renamed from: e */
    private static final Pattern f7396e = Pattern.compile("^[a-zA-Z0-9]{8}$");

    C4491ai() {
    }

    static {
        String str;
        MessageDigest temp = null;
        C4549w.m8594c(f7392a, "Getting SHA1 digest");
        try {
            temp = MessageDigest.getInstance("SHA1");
        } catch (NoSuchAlgorithmException e) {
            str = f7392a;
        }
        f7395d = temp;
        temp = null;
        if (!NativeGatherer.m8207a().mo34053b()) {
            C4549w.m8594c(f7392a, "Getting MD5 digest");
            try {
                temp = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException e2) {
                str = f7392a;
            }
        }
        f7394c = temp;
    }

    /* renamed from: a */
    static String m8338a(String in) throws InterruptedException {
        if (NativeGatherer.m8207a().mo34053b()) {
            return NativeGatherer.m8207a().mo34058e(in);
        }
        try {
            return URLEncoder.encode(in, Utf8Charset.NAME);
        } catch (UnsupportedEncodingException e) {
            String str = f7392a;
            return null;
        }
    }

    /* renamed from: b */
    static String m8344b(String inputStr) throws InterruptedException {
        if (inputStr == null || inputStr.isEmpty()) {
            return "";
        }
        String result;
        if (NativeGatherer.m8207a().mo34053b()) {
            result = NativeGatherer.m8207a().mo34050b(inputStr);
            if (result == null) {
                return "";
            }
            return result;
        } else if (f7394c == null) {
            return "";
        } else {
            synchronized (f7394c) {
                f7394c.update(inputStr.getBytes());
                byte[] outputData = f7394c.digest();
                f7394c.reset();
                result = C4491ai.m8342a(outputData);
            }
            return result;
        }
    }

    /* renamed from: k */
    private static String m8354k(String inputStr) throws InterruptedException {
        if (inputStr == null || inputStr.isEmpty()) {
            return "";
        }
        String result;
        if (NativeGatherer.m8207a().mo34053b()) {
            result = NativeGatherer.m8207a().mo34057d(inputStr);
            if (result == null) {
                return "";
            }
            return result;
        } else if (f7395d == null) {
            return "";
        } else {
            synchronized (f7395d) {
                f7395d.update(inputStr.getBytes());
                byte[] outputData = f7395d.digest();
                f7395d.reset();
                result = C4491ai.m8342a(outputData);
            }
            return result;
        }
    }

    /* JADX WARNING: Unknown top exception splitter block from list: {B:23:0x0034=Splitter:B:23:0x0034, B:37:0x0057=Splitter:B:37:0x0057} */
    /* renamed from: c */
    static java.lang.String m8346c(java.lang.String r9) {
        /*
        r6 = com.threatmetrix.TrustDefender.C4491ai.m8348e(r9);
        if (r6 == 0) goto L_0x0009;
    L_0x0006:
        r0 = "";
    L_0x0008:
        return r0;
    L_0x0009:
        r2 = 0;
        r3 = new java.io.FileInputStream;	 Catch:{ FileNotFoundException -> 0x0062 }
        r3.<init>(r9);	 Catch:{ FileNotFoundException -> 0x0062 }
        r2 = r3;
    L_0x0010:
        r6 = f7395d;
        if (r6 == 0) goto L_0x005f;
    L_0x0014:
        if (r2 == 0) goto L_0x005f;
    L_0x0016:
        r6 = f7392a;
        r6 = 4096; // 0x1000 float:5.74E-42 double:2.0237E-320;
        r1 = new byte[r6];
        r7 = f7395d;
        monitor-enter(r7);
        r5 = 0;
    L_0x0020:
        r5 = r2.read(r1);	 Catch:{ IOException -> 0x002e }
        r6 = -1;
        if (r5 == r6) goto L_0x0047;
    L_0x0027:
        r6 = f7395d;	 Catch:{ IOException -> 0x002e }
        r8 = 0;
        r6.update(r1, r8, r5);	 Catch:{ IOException -> 0x002e }
        goto L_0x0020;
    L_0x002e:
        r6 = move-exception;
        r6 = f7392a;	 Catch:{ all -> 0x0053 }
        r2.close();	 Catch:{ IOException -> 0x004f }
    L_0x0034:
        r6 = f7395d;	 Catch:{ all -> 0x0058 }
        r4 = r6.digest();	 Catch:{ all -> 0x0058 }
        r6 = f7395d;	 Catch:{ all -> 0x0058 }
        r6.reset();	 Catch:{ all -> 0x0058 }
        monitor-exit(r7);	 Catch:{ all -> 0x0058 }
        r0 = com.threatmetrix.TrustDefender.C4491ai.m8342a(r4);
        r6 = f7392a;
        goto L_0x0008;
    L_0x0047:
        r2.close();	 Catch:{ IOException -> 0x004b }
        goto L_0x0034;
    L_0x004b:
        r6 = move-exception;
        r6 = f7392a;	 Catch:{ all -> 0x0058 }
        goto L_0x0034;
    L_0x004f:
        r6 = move-exception;
        r6 = f7392a;	 Catch:{ all -> 0x0058 }
        goto L_0x0034;
    L_0x0053:
        r6 = move-exception;
        r2.close();	 Catch:{ IOException -> 0x005b }
    L_0x0057:
        throw r6;	 Catch:{ all -> 0x0058 }
    L_0x0058:
        r6 = move-exception;
        monitor-exit(r7);
        throw r6;
    L_0x005b:
        r8 = move-exception;
        r8 = f7392a;	 Catch:{ all -> 0x0058 }
        goto L_0x0057;
    L_0x005f:
        r0 = "";
        goto L_0x0008;
    L_0x0062:
        r6 = move-exception;
        goto L_0x0010;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.threatmetrix.TrustDefender.C4491ai.m8346c(java.lang.String):java.lang.String");
    }

    /* renamed from: a */
    private static String m8342a(byte[] bytes) {
        char[] hexChars = new char[(bytes.length * 2)];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 255;
            hexChars[j * 2] = f7393b[v >>> 4];
            hexChars[(j * 2) + 1] = f7393b[v & 15];
        }
        return new String(hexChars);
    }

    /* renamed from: a */
    static String m8339a(String input, String key) throws InterruptedException {
        if (NativeGatherer.m8207a().mo34053b()) {
            return NativeGatherer.m8207a().mo34051b(input, key);
        }
        int c;
        int index;
        String lengthString = input.length() + "&";
        byte[] buffer = new byte[(input.length() + lengthString.length())];
        int k_len = key.length();
        int index2 = 0;
        int c2 = 0;
        int i = 0;
        while (i < lengthString.length()) {
            c = c2 + 1;
            index = index2 + 1;
            buffer[c2] = (byte) (((byte) lengthString.charAt(i)) ^ (((byte) key.charAt(index2)) & 10));
            if (index >= k_len) {
                index2 = 0;
            } else {
                index2 = index;
            }
            i++;
            c2 = c;
        }
        i = 0;
        while (i < input.length()) {
            c = c2 + 1;
            index = index2 + 1;
            buffer[c2] = (byte) (((byte) input.charAt(i)) ^ (((byte) key.charAt(index2)) & 10));
            if (index >= k_len) {
                index2 = 0;
            } else {
                index2 = index;
            }
            i++;
            c2 = c;
        }
        return C4491ai.m8342a(buffer);
    }

    /* renamed from: a */
    static String m8337a() throws InterruptedException {
        String str = f7392a;
        if (NativeGatherer.m8207a().mo34053b()) {
            return NativeGatherer.m8207a().mo34046a(32);
        }
        return UUID.randomUUID().toString().toLowerCase(Locale.US).replaceAll("-", "");
    }

    /* renamed from: b */
    static List<String> m8345b(String input, String delim) {
        List<String> l = new ArrayList();
        while (true) {
            int index = input.indexOf(delim);
            if (index == -1) {
                break;
            }
            if (index > 1) {
                l.add(input.substring(0, index));
            }
            input = input.substring(delim.length() + index);
        }
        if (!input.isEmpty()) {
            l.add(input);
        }
        return l;
    }

    /* renamed from: d */
    static Map<String, String> m8347d(String query) {
        Map<String, String> query_pairs = new LinkedHashMap();
        for (String pair : C4491ai.m8345b(query, "&")) {
            int idx = pair.indexOf("=");
            try {
                query_pairs.put(URLDecoder.decode(pair.substring(0, idx), Utf8Charset.NAME), URLDecoder.decode(pair.substring(idx + 1), Utf8Charset.NAME));
            } catch (UnsupportedEncodingException e) {
                String str = f7392a;
            }
        }
        return query_pairs;
    }

    /* renamed from: a */
    static String m8340a(List<String> list, String separator) {
        return C4491ai.m8341a(list, separator, false);
    }

    /* renamed from: a */
    static String m8341a(List<String> list, String separator, boolean addTrailingSep) {
        StringBuilder sb = new StringBuilder();
        for (String l : list) {
            if (!l.isEmpty()) {
                if (sb.length() > 0) {
                    sb.append(separator);
                }
                sb.append(l);
            }
        }
        if (addTrailingSep && sb.length() > 0) {
            sb.append(separator);
        }
        return sb.toString();
    }

    /* renamed from: e */
    static boolean m8348e(String input) {
        return input == null || input.isEmpty();
    }

    /* renamed from: f */
    static boolean m8349f(String input) {
        return (input == null || input.isEmpty()) ? false : true;
    }

    /* renamed from: g */
    static String m8350g(String sessionId) {
        StringBuilder result = new StringBuilder();
        int len = 0;
        for (int i = 0; i < sessionId.length() && len < 128; i++) {
            char ch = sessionId.charAt(i);
            if ((ch >= '0' && ch <= '9') || ((ch >= 'a' && ch <= 'z') || ch == '_' || ch == '/' || ch == '-')) {
                result.append(ch);
                len++;
            } else if (ch >= 'A' && ch <= 'Z') {
                result.append(Character.toLowerCase(ch));
                len++;
            }
        }
        return result.toString();
    }

    /* renamed from: h */
    static String m8351h(String plain) {
        if (!C4491ai.m8349f(plain)) {
            return "";
        }
        try {
            return C4491ai.m8354k(plain);
        } catch (InterruptedException e) {
            return "";
        }
    }

    /* renamed from: a */
    static void m8343a(String input, boolean hashable, String keyName, Map<String, String> result) {
        if (hashable) {
            input = C4491ai.m8351h(input);
        }
        if (C4491ai.m8349f(input)) {
            result.put(keyName, input);
        }
    }

    /* renamed from: i */
    static String m8352i(String userAgent) {
        if (C4491ai.m8348e(userAgent)) {
            return "";
        }
        StringBuilder result = new StringBuilder();
        byte[] bytes = userAgent.getBytes(Charset.forName("UTF8"));
        int i = 0;
        while (i < bytes.length) {
            if (bytes[i] < (byte) 32 || bytes[i] > Byte.MAX_VALUE) {
                result.append("\\x").append(String.format("%02x", new Object[]{Byte.valueOf(bytes[i])}));
            } else {
                result.append((char) bytes[i]);
            }
            i++;
        }
        return result.toString();
    }

    /* renamed from: j */
    static boolean m8353j(String orgId) {
        return C4491ai.m8349f(orgId) && f7396e.matcher(orgId).find();
    }
}
