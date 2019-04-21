package com.admaster.jice.p004a;

import android.support.p000v4.util.Pair;
import android.text.TextUtils;
import android.util.Log;
import com.admaster.jice.p005b.HttpConfig;
import com.admaster.jice.p007d.LOG;
import com.admaster.jice.p007d.ManagerUtils;
import com.admaster.jice.p007d.PrintUtil;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.util.Calendar;
import java.util.List;
import org.json.JSONObject;

/* compiled from: JiceCore */
/* renamed from: com.admaster.jice.a.h */
class C0460h extends Thread {
    /* renamed from: a */
    final /* synthetic */ C0456d f46a;
    /* renamed from: b */
    private boolean f47b = false;
    /* renamed from: c */
    private final C0455b f48c;
    /* renamed from: d */
    private final List<Pair<String, String>> f49d;
    /* renamed from: e */
    private Object f50e = null;

    public C0460h(C0456d c0456d, C0455b c0455b, List<Pair<String, String>> list) {
        this.f46a = c0456d;
        this.f48c = c0455b;
        this.f49d = list;
        int size = list.size();
        if (this.f48c == C0455b.NORMAL) {
            c0456d.f41k = size;
        } else {
            c0456d.f42l = size;
        }
        this.f47b = false;
        this.f50e = new Object();
    }

    public boolean isInterrupted() {
        this.f47b = true;
        this.f49d.clear();
        m67c();
        return super.isInterrupted();
    }

    public void run() {
        this.f46a.f31a.m28a("execute:[" + Thread.currentThread().getId() + "]" + "  allist:" + this.f49d.size() + "  failcount:" + this.f46a.f42l + "  normalcount:" + this.f46a.f41k);
        synchronized (this.f50e) {
            for (int i = 0; i < this.f49d.size(); i++) {
                Pair pair = (Pair) this.f49d.get(i);
                if (this.f47b) {
                    m67c();
                    this.f46a.f31a.m28a("EventTask:" + Thread.currentThread() + " has interupted!");
                    PrintUtil.m246a(this.f46a.f32b, "EXCEPTION", Thread.currentThread() + " has interupted!", "EventTask", "");
                    return;
                }
                if (this.f48c == C0455b.NORMAL) {
                    m66b(pair);
                } else {
                    m64a(pair);
                }
            }
        }
    }

    /* renamed from: a */
    private void m63a() {
        C0456d c0456d;
        if (this.f48c == C0455b.NORMAL) {
            c0456d = this.f46a;
            c0456d.f41k = c0456d.f41k - 1;
            return;
        }
        c0456d = this.f46a;
        c0456d.f42l = c0456d.f42l - 1;
    }

    /* renamed from: b */
    private int m65b() {
        if (this.f48c == C0455b.NORMAL) {
            return this.f46a.f41k;
        }
        return this.f46a.f42l;
    }

    /* renamed from: c */
    private void m67c() {
        this.f46a.f41k = 0;
        this.f46a.f42l = 0;
    }

    /* renamed from: a */
    private void m64a(Pair<String, String> pair) {
        Exception e;
        try {
            Pair pair2;
            String[] split;
            Pair pair3;
            String str = (String) pair.second;
            String[] split2 = str.split("#");
            if (TextUtils.isEmpty((CharSequence) pair.first) || split2 == null || split2.length < 3) {
                try {
                    JSONObject jSONObject;
                    Object obj;
                    Log.e("JiceError", "judgeEventHasExpired expire legal format:" + str + "   event:" + ((String) pair.first));
                    if (TextUtils.isEmpty((CharSequence) pair.first)) {
                        jSONObject = new JSONObject();
                        obj = "judgeEventHasExpired event legal format:" + ((String) pair.first);
                    } else {
                        jSONObject = JSONObjectInstrumentation.init((String) pair.first);
                        obj = "judgeEventHasExpired expire legal format:" + ((String) pair.second);
                    }
                    jSONObject.put("errormsg", obj);
                    this.f46a.f31a.f28f.mo7597a(C0455b.FAILED, (String) pair.first);
                    this.f46a.f40j.remove(pair);
                    PrintUtil.m246a(this.f46a.f32b, "EXCEPTION", "the event of expire is legal format", (String) pair.first, (String) pair.second);
                    String a = this.f46a.m39a(System.currentTimeMillis());
                    pair2 = new Pair(!(jSONObject instanceof JSONObject) ? jSONObject.toString() : JSONObjectInstrumentation.toString(jSONObject), a);
                    try {
                        this.f46a.f31a.f28f.mo7598a(C0455b.FAILED, !(jSONObject instanceof JSONObject) ? jSONObject.toString() : JSONObjectInstrumentation.toString(jSONObject), a);
                        this.f46a.f40j.add(pair2);
                        split = a.split("#");
                        pair3 = pair2;
                    } catch (Exception e2) {
                        e = e2;
                        pair3 = pair2;
                    }
                } catch (Exception e3) {
                    e = e3;
                    e.printStackTrace();
                    this.f46a.f40j.remove(pair);
                    this.f46a.f31a.f28f.mo7597a(C0455b.FAILED, (String) pair.first);
                    m63a();
                    return;
                }
            }
            split = split2;
            long longValue = Long.valueOf(split[0]).longValue();
            long longValue2 = Long.valueOf(split[1]).longValue();
            int intValue = Integer.valueOf(split[2]).intValue();
            long currentTimeMillis = System.currentTimeMillis();
            if (604800000 + longValue <= currentTimeMillis) {
                this.f46a.f40j.remove(pair3);
                this.f46a.f31a.f28f.mo7597a(this.f48c, (String) pair3.first);
                m63a();
                PrintUtil.m246a(this.f46a.f32b, "INVALIDED", "record has invalided,delete it ", (String) pair3.first, (String) pair3.second);
            } else if (((long) intValue) < 10) {
                m66b(pair3);
            } else if (currentTimeMillis > longValue2) {
                Calendar instance = Calendar.getInstance();
                instance.set(instance.get(1), instance.get(2), instance.get(5), 23, 59, 59);
                long timeInMillis = instance.getTimeInMillis();
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(String.valueOf(longValue));
                stringBuffer.append("#");
                stringBuffer.append(String.valueOf(timeInMillis));
                stringBuffer.append("#");
                stringBuffer.append("1");
                this.f46a.f31a.f28f.mo7598a(this.f48c, (String) pair3.first, stringBuffer.toString());
                this.f46a.f40j.remove(pair3);
                pair2 = new Pair((String) pair3.first, stringBuffer.toString());
                this.f46a.f40j.add(pair2);
                m66b(pair2);
            } else {
                this.f46a.f40j.remove(pair3);
                m63a();
            }
        } catch (Exception e4) {
            m63a();
            e4.printStackTrace();
        }
    }

    /* renamed from: b */
    private synchronized void m66b(Pair<String, String> pair) {
        try {
            synchronized (this.f50e) {
                if (this.f46a.f38h.mo7714a(HttpConfig.m166e(), (String) pair.first, HttpConfig.m167f())) {
                    this.f46a.f31a.f28f.mo7597a(this.f48c, (String) pair.first);
                    if (this.f48c == C0455b.NORMAL) {
                        this.f46a.f39i.remove(pair);
                    } else {
                        this.f46a.f40j.remove(pair);
                    }
                    this.f46a.f31a.m28a(this.f48c + " task:" + ManagerUtils.m234b((String) pair.first) + "[" + ((String) pair.second) + "]" + "  index:" + m65b() + " flush success has decrease on list");
                } else {
                    if (TextUtils.isEmpty((CharSequence) pair.first) || TextUtils.isEmpty((CharSequence) pair.second)) {
                        Log.e("JiceError", "executeTask failed expire:" + ((String) pair.second) + "   event:" + ((String) pair.first));
                    }
                    if (this.f48c == C0455b.NORMAL) {
                        this.f46a.f31a.f28f.mo7597a(C0455b.NORMAL, (String) pair.first);
                        this.f46a.f31a.f28f.mo7598a(C0455b.FAILED, (String) pair.first, (String) pair.second);
                        this.f46a.f39i.remove(pair);
                        this.f46a.f40j.add(pair);
                        this.f46a.f31a.m28a(this.f48c + "  task:" + ManagerUtils.m234b((String) pair.first) + "[" + ((String) pair.second) + "]" + "  index:" + m65b() + " flush failed, remove from normal list and cache to fail list");
                    } else {
                        String[] split = ((String) pair.second).split("#");
                        if (split == null || split.length <= 2) {
                            this.f46a.f40j.remove(pair);
                            this.f46a.f31a.m28a(this.f48c + "  task:" + ManagerUtils.m234b((String) pair.first) + "[" + ((String) pair.second) + "]" + "  index:" + m65b() + " invalid data format");
                            PrintUtil.m246a(this.f46a.f32b, "EXCEPTION", "this record  has beyond retry day and count", (String) pair.first, (String) pair.second);
                            Log.e("JiceError", "executeTask failed invalid data format:" + ((String) pair.second) + "   event:" + ((String) pair.first));
                        } else {
                            int intValue = Integer.valueOf(split[2]).intValue() + 1;
                            if (((long) intValue) <= 10) {
                                this.f46a.f40j.remove(pair);
                                StringBuffer stringBuffer = new StringBuffer();
                                stringBuffer = new StringBuffer();
                                stringBuffer.append(split[0]);
                                stringBuffer.append("#");
                                stringBuffer.append(split[1]);
                                stringBuffer.append("#");
                                stringBuffer.append(String.valueOf(intValue));
                                this.f46a.f31a.f28f.mo7598a(this.f48c, (String) pair.first, stringBuffer.toString());
                                this.f46a.f40j.add(new Pair((String) pair.first, stringBuffer.toString()));
                                this.f46a.f31a.m28a(this.f48c + "  task:" + ManagerUtils.m234b((String) pair.first) + "[" + ((String) pair.second) + "]" + "  index:" + m65b() + " flush failed,update expire data:" + stringBuffer.toString() + "  filelistCount:" + this.f46a.f40j.size());
                            } else {
                                this.f46a.f40j.remove(pair);
                                this.f46a.f31a.m28a(this.f48c + "  task:" + ManagerUtils.m234b((String) pair.first) + "[" + ((String) pair.second) + "]" + "  index:" + m65b() + " has beyond current day limit,today don`t try send :" + this.f46a.f40j.size());
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            LOG.m225a("JiceSDK", "executeTask request exception", e);
        }
        m63a();
        return;
    }
}
