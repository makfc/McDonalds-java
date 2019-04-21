package com.admaster.square.api;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.admaster.square.utils.Order;
import com.mcdonalds.sdk.services.analytics.JiceArgs;
import com.newrelic.agent.android.analytics.AnalyticAttribute;
import java.lang.ref.WeakReference;

/* compiled from: ConvMobiHandler */
/* renamed from: com.admaster.square.api.b */
final class C0479b extends Handler {
    /* renamed from: b */
    private static /* synthetic */ int[] f162b;
    /* renamed from: a */
    private final WeakReference<ConvMobiHandler> f163a;

    /* renamed from: a */
    static /* synthetic */ int[] m297a() {
        int[] iArr = f162b;
        if (iArr == null) {
            iArr = new int[CustomEvent.values().length];
            try {
                iArr[CustomEvent.ADACTIVE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[CustomEvent.ADAPPLIST.ordinal()] = 13;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[CustomEvent.ADCRASH.ordinal()] = 7;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[CustomEvent.ADCUSTOM1.ordinal()] = 8;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[CustomEvent.ADCUSTOM2.ordinal()] = 9;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr[CustomEvent.ADCUSTOM3.ordinal()] = 10;
            } catch (NoSuchFieldError e6) {
            }
            try {
                iArr[CustomEvent.ADCUSTOM4.ordinal()] = 11;
            } catch (NoSuchFieldError e7) {
            }
            try {
                iArr[CustomEvent.ADCUSTOM5.ordinal()] = 12;
            } catch (NoSuchFieldError e8) {
            }
            try {
                iArr[CustomEvent.ADLOGIN.ordinal()] = 4;
            } catch (NoSuchFieldError e9) {
            }
            try {
                iArr[CustomEvent.ADORDER.ordinal()] = 5;
            } catch (NoSuchFieldError e10) {
            }
            try {
                iArr[CustomEvent.ADPLIST.ordinal()] = 14;
            } catch (NoSuchFieldError e11) {
            }
            try {
                iArr[CustomEvent.ADPURCHASE.ordinal()] = 6;
            } catch (NoSuchFieldError e12) {
            }
            try {
                iArr[CustomEvent.ADREG.ordinal()] = 3;
            } catch (NoSuchFieldError e13) {
            }
            try {
                iArr[CustomEvent.ADSTART.ordinal()] = 2;
            } catch (NoSuchFieldError e14) {
            }
            f162b = iArr;
        }
        return iArr;
    }

    protected C0479b(Looper looper, ConvMobiHandler convMobiHandler) {
        super(looper);
        this.f163a = new WeakReference(convMobiHandler);
    }

    public void handleMessage(Message message) {
        super.handleMessage(message);
        ConvMobiHandler convMobiHandler = (ConvMobiHandler) this.f163a.get();
        if (convMobiHandler != null) {
            m296a(convMobiHandler, message.getData(), CustomEvent.values()[message.arg1]);
        }
    }

    /* renamed from: a */
    private void m296a(ConvMobiHandler convMobiHandler, Bundle bundle, CustomEvent customEvent) {
        String string;
        long j;
        Order order;
        try {
            string = bundle.getString(AnalyticAttribute.USER_ID_ATTRIBUTE);
        } catch (Exception e) {
            string = "";
        }
        try {
            j = bundle.getLong("timer");
        } catch (Exception e2) {
            j = 0;
        }
        try {
            order = (Order) bundle.getSerializable(JiceArgs.EVENT_SUBMIT_ORDER);
        } catch (Exception e3) {
            order = null;
            Logger.m364b(e3.getMessage());
        }
        switch (C0479b.m297a()[customEvent.ordinal()]) {
            case 1:
            case 2:
                convMobiHandler.m282c();
                convMobiHandler.m279a(string, order, j, customEvent);
                convMobiHandler.m286g();
                return;
            case 3:
            case 4:
                if (TextUtils.isEmpty(string)) {
                    Logger.m364b("AdMasterConvMobi userId can't be null!,register or login request is failed");
                    return;
                } else {
                    convMobiHandler.m279a(string, order, j, customEvent);
                    return;
                }
            case 5:
            case 6:
                if (TextUtils.isEmpty(string)) {
                    Logger.m364b("AdMasterConvMobi userId can't be null! order or purchase is failed!");
                    return;
                } else if (order == null) {
                    Logger.m364b("AdMasterConvMobi order can't be null!");
                    return;
                } else {
                    convMobiHandler.m279a(string, order, j, customEvent);
                    return;
                }
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
                convMobiHandler.m279a(string, order, j, customEvent);
                return;
            default:
                return;
        }
    }
}
