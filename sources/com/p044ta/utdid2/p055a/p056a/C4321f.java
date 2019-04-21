package com.p044ta.utdid2.p055a.p056a;

import java.util.regex.Pattern;

/* renamed from: com.ta.utdid2.a.a.f */
public class C4321f {
    /* renamed from: a */
    private static final Pattern f6718a = Pattern.compile("([\t\r\n])+");

    public static boolean isEmpty(String str) {
        if (str == null || str.length() <= 0) {
            return true;
        }
        return false;
    }

    public static int hashCode(String str) {
        int i = 0;
        if (str.length() <= 0) {
            return 0;
        }
        char[] toCharArray = str.toCharArray();
        int i2 = 0;
        while (i < toCharArray.length) {
            i2 = (i2 * 31) + toCharArray[i];
            i++;
        }
        return i2;
    }
}
