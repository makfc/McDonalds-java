package com.admaster.square.api;

import android.content.Context;
import android.text.TextUtils;
import com.admaster.square.utils.AndroidUtil;
import com.admaster.square.utils.ConnectUtil;
import com.admaster.square.utils.Constant;
import com.admaster.square.utils.CustomPreferenceUtil;
import com.admaster.square.utils.FailedMessage;
import com.admaster.square.utils.NetWorkInfoUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.Semaphore;

/* renamed from: com.admaster.square.api.p */
public class SendMessageThread implements Runnable {
    /* renamed from: a */
    private String f247a;
    /* renamed from: b */
    private Context f248b;
    /* renamed from: c */
    private Semaphore f249c = new Semaphore(1, true);

    SendMessageThread(String str, Context context) {
        this.f247a = str;
        this.f248b = context;
    }

    public void run() {
        mo7794a();
    }

    /* renamed from: a */
    public synchronized void mo7794a() {
        try {
            this.f249c.acquire();
            ArrayList arrayList = new ArrayList(CustomPreferenceUtil.m407a(this.f248b, this.f247a).getAll().keySet());
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
                        mo7796a(arrayList3);
                        i2++;
                        arrayList2 = arrayList3;
                    }
                }
            }
            this.f249c.release();
        } catch (InterruptedException e2) {
            Logger.m364b(e2.getMessage());
            this.f249c.release();
        } catch (Throwable th) {
            this.f249c.release();
        }
        return;
    }

    /* renamed from: a */
    public void mo7796a(ArrayList<String> arrayList) {
        if (arrayList != null && arrayList.size() > 0) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                String str = (String) it.next();
                if (NetWorkInfoUtil.m452a(this.f248b)) {
                    try {
                        m385a(str);
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
    private void m385a(String str) {
        ConnectUtil a = ConnectUtil.m398a();
        if (!TextUtils.isEmpty(a.mo7805b())) {
            FailedMessage failedMessage = (FailedMessage) CustomPreferenceUtil.m416d(this.f248b, this.f247a, str);
            if (failedMessage == null) {
                return;
            }
            if (!AndroidUtil.m394a(failedMessage.mo7832c()) || failedMessage.mo7833d() <= 30) {
                String a2 = a.mo7803a(Constant.f259a, failedMessage.mo7826a());
                Logger.m364b("admaster request json result:" + a2);
                if (a2 == null || !a2.contains("200")) {
                    mo7795a(this.f248b, failedMessage, str);
                } else {
                    m386a(this.f247a, str);
                }
            }
        }
    }

    /* renamed from: a */
    private void m386a(String str, String str2) {
        CustomPreferenceUtil.m415c(this.f248b, str, str2);
    }

    /* renamed from: a */
    public void mo7795a(Context context, FailedMessage failedMessage, String str) {
        long currentTimeMillis = System.currentTimeMillis();
        long b = failedMessage.mo7830b();
        long c = failedMessage.mo7832c();
        boolean b2 = AndroidUtil.m396b(b);
        boolean a = AndroidUtil.m394a(c);
        if (!b2) {
            m386a(this.f247a, str);
        } else if (a) {
            int d = failedMessage.mo7833d();
            if (d <= 30) {
                failedMessage.mo7827a(d + 1);
                failedMessage.mo7831b(currentTimeMillis);
                CustomPreferenceUtil.m410a(context, this.f247a, str, (Object) failedMessage);
            }
        } else {
            failedMessage.mo7827a(1);
            failedMessage.mo7831b(currentTimeMillis);
            CustomPreferenceUtil.m410a(context, this.f247a, str, (Object) failedMessage);
        }
    }
}
