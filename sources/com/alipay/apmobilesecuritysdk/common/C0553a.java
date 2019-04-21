package com.alipay.apmobilesecuritysdk.common;

import android.content.Context;
import com.alipay.apmobilesecuritysdk.p017e.C0566h;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/* renamed from: com.alipay.apmobilesecuritysdk.common.a */
public final class C0553a {
    /* renamed from: a */
    public static boolean m646a(Context context) {
        List arrayList = new ArrayList();
        arrayList.add(C0566h.m706e(context));
        return C0553a.m647a(arrayList) || C0553a.m647a(new RushTimeUtil$1());
    }

    /* renamed from: a */
    private static boolean m647a(List<String> list) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        simpleDateFormat.setLenient(false);
        int random = ((int) (((Math.random() * 24.0d) * 60.0d) * 60.0d)) * 1;
        try {
            for (String split : list) {
                String[] split2 = split.split("&");
                if (split2 != null && split2.length == 2) {
                    Date date = new Date();
                    Date parse = simpleDateFormat.parse(split2[0] + " 00:00:00");
                    Date parse2 = simpleDateFormat.parse(split2[1] + " 23:59:59");
                    Calendar instance = Calendar.getInstance();
                    instance.setTime(parse2);
                    instance.add(13, random);
                    parse2 = instance.getTime();
                    if (date.after(parse) && date.before(parse2)) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
        }
        return false;
    }
}
