package com.admaster.square.api;

import android.content.Context;
import android.text.TextUtils;
import android.util.Base64;
import com.admaster.square.utils.ConnectUtil;
import com.admaster.square.utils.Constant;
import com.admaster.square.utils.CustomPreferenceUtil;
import com.admaster.square.utils.NetWorkInfoUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.Semaphore;

/* renamed from: com.admaster.square.api.o */
public class SendActiveMessageThread extends Thread {
    /* renamed from: a */
    private String f243a;
    /* renamed from: b */
    private Context f244b;
    /* renamed from: c */
    private boolean f245c = false;
    /* renamed from: d */
    private Semaphore f246d;

    public void interrupt() {
        this.f245c = true;
        super.interrupt();
    }

    SendActiveMessageThread(String str, Context context) {
        this.f243a = str;
        this.f244b = context;
        this.f246d = new Semaphore(1, true);
    }

    public void run() {
        mo7790a();
    }

    /* renamed from: a */
    public synchronized void mo7790a() {
        try {
            this.f246d.acquire();
            ArrayList arrayList = new ArrayList(CustomPreferenceUtil.m407a(this.f244b, this.f243a).getAll().keySet());
            if (arrayList != null && arrayList.size() > 0) {
                int size;
                if (arrayList.size() % Constant.f272n == 0) {
                    size = arrayList.size() / Constant.f272n;
                } else {
                    size = (arrayList.size() / Constant.f272n) + 1;
                }
                int i = size * Constant.f272n;
                if (size > 0 && i > 0) {
                    ArrayList arrayList2 = null;
                    int i2 = 0;
                    while (i2 < size) {
                        ArrayList arrayList3;
                        if (size <= 1) {
                            arrayList3 = new ArrayList(arrayList.subList(0, arrayList.size()));
                        } else if (i < arrayList.size()) {
                            arrayList3 = arrayList2;
                        } else if ((i2 + 1) * Constant.f272n > arrayList.size()) {
                            arrayList3 = new ArrayList(arrayList.subList(Constant.f272n * i2, arrayList.size()));
                        } else {
                            arrayList3 = new ArrayList(arrayList.subList(Constant.f272n * i2, (i2 + 1) * Constant.f272n));
                        }
                        if (arrayList3 != null) {
                            try {
                                if (arrayList3.size() >= Constant.f272n) {
                                    Thread.sleep((long) Constant.f273o);
                                }
                            } catch (Exception e) {
                                Logger.m364b(e.getMessage());
                            }
                        }
                        mo7791a(arrayList3);
                        i2++;
                        arrayList2 = arrayList3;
                    }
                }
            }
            this.f246d.release();
        } catch (Exception e2) {
            Logger.m364b(e2.getMessage());
            this.f246d.release();
        } catch (Throwable th) {
            this.f246d.release();
        }
        return;
    }

    /* renamed from: a */
    public void mo7791a(ArrayList<String> arrayList) {
        if (arrayList != null && arrayList.size() > 0) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                String str = (String) it.next();
                if (!this.f245c && NetWorkInfoUtil.m452a(this.f244b)) {
                    try {
                        m381a(new String(Base64.decode(str, 2)));
                    } catch (Exception e) {
                        Logger.m364b(e.getMessage());
                    }
                } else {
                    return;
                }
            }
        }
    }

    /* renamed from: a */
    private void m381a(String str) {
        ConnectUtil a = ConnectUtil.m398a();
        if (!TextUtils.isEmpty(a.mo7805b())) {
            String a2 = a.mo7803a(Constant.f259a, str);
            Logger.m364b("admaster request json result:" + a2);
            if (a2 != null && a2.contains("200")) {
                m382a(this.f243a, str);
            }
        }
    }

    /* renamed from: a */
    private void m382a(String str, String str2) {
        try {
            CustomPreferenceUtil.m415c(this.f244b, str, Base64.encodeToString(str2.getBytes(), 2));
            CustomPreferenceUtil.m412a(this.f244b, "sp_store", "is_install", true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
