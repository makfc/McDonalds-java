package com.amap.api.mapcore.util;

import android.content.Context;
import android.text.TextUtils;
import java.io.File;
import java.io.IOException;
import java.util.List;

/* renamed from: com.amap.api.mapcore.util.bo */
public class OfflineMapRemoveTask {
    /* renamed from: a */
    private Context f1421a;

    public OfflineMapRemoveTask(Context context) {
        this.f1421a = context;
    }

    /* renamed from: a */
    public void mo8940a(CityObject cityObject) {
        m1923b(cityObject);
    }

    /* renamed from: b */
    private boolean m1923b(CityObject cityObject) {
        if (cityObject == null) {
            return false;
        }
        boolean a = m1922a(cityObject.getAdcode(), this.f1421a);
        if (a) {
            cityObject.mo8861h();
            return a;
        }
        cityObject.mo8860g();
        return false;
    }

    /* renamed from: a */
    private boolean m1922a(String str, Context context) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        List<String> b = OfflineDBOperation.m1975a(context).mo8981b(str);
        String a = Util.m2350a(context);
        for (String str2 : b) {
            try {
                File file = new File(a + "vmap/" + str2);
                if (file.exists() && !Utility.m2184b(file)) {
                    Utility.m2180a("deleteDownload delete some thing wrong!");
                    return false;
                }
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            } catch (Exception e2) {
                e2.printStackTrace();
                return false;
            }
        }
        try {
            Utility.m2187c(a + "vmap/");
            Utility.m2181a(str, context);
            return true;
        } catch (IOException e3) {
            e3.printStackTrace();
            return false;
        } catch (Exception e22) {
            e22.printStackTrace();
            return false;
        }
    }
}
