package com.threatmetrix.TrustDefender;

import android.annotation.TargetApi;
import android.content.Context;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import com.threatmetrix.TrustDefender.C4532g.C4518b.C4516a;
import com.threatmetrix.TrustDefender.C4532g.C4530n;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* renamed from: com.threatmetrix.TrustDefender.s */
class C4546s {
    /* renamed from: a */
    private static final String f7821a = C4549w.m8585a(C4546s.class);

    C4546s() {
    }

    @TargetApi(22)
    /* renamed from: a */
    static Map<String, String> m8576a(Context context) {
        Map<String, String> result = new HashMap();
        if (C4516a.f7584c >= 22 && C4530n.m8523f()) {
            List<String> actives = new ArrayList();
            Object subscriptionManager = SubscriptionManager.from(context);
            if (subscriptionManager != null) {
                try {
                    int idx;
                    List<SubscriptionInfo> activeSims = subscriptionManager.getActiveSubscriptionInfoList();
                    if (activeSims != null && activeSims.size() > 0) {
                        for (SubscriptionInfo info : activeSims) {
                            idx = info.getSimSlotIndex();
                            C4491ai.m8343a(info.getCarrierName().toString(), true, "cna" + idx, result);
                            C4491ai.m8343a(info.getDisplayName().toString(), true, "dna" + idx, result);
                            C4491ai.m8343a(String.valueOf(info.getIccId()), true, "ssa" + idx, result);
                            C4491ai.m8343a(info.getNumber(), true, "na" + idx, result);
                            C4491ai.m8343a(info.getCountryIso(), true, "ca" + idx, result);
                            result.put("ra" + idx, info.getDataRoaming() == 0 ? "disabled" : "enabled");
                            actives.add(info.getIccId() + info.getNumber());
                        }
                    }
                    Method getAllSubscribersInfo = C4485at.m8328b(SubscriptionManager.class, "getAllSubscriptionInfoList", new Class[0]);
                    if (getAllSubscribersInfo != null) {
                        Object list = C4485at.m8323a(subscriptionManager, getAllSubscribersInfo, new Object[0]);
                        if (list != null && (list instanceof List)) {
                            List<SubscriptionInfo> allSims = (List) list;
                            if (activeSims == null || activeSims.size() < allSims.size()) {
                                idx = 0;
                                for (SubscriptionInfo info2 : allSims) {
                                    if (!actives.contains(info2.getIccId() + info2.getNumber())) {
                                        C4491ai.m8343a(info2.getCarrierName().toString(), true, "cno" + idx, result);
                                        C4491ai.m8343a(info2.getDisplayName().toString(), true, "dno" + idx, result);
                                        C4491ai.m8343a(String.valueOf(info2.getIccId()), true, "sso" + idx, result);
                                        C4491ai.m8343a(info2.getNumber(), true, "no" + idx, result);
                                        C4491ai.m8343a(info2.getCountryIso(), true, "co" + idx, result);
                                        result.put("ro" + idx, info2.getDataRoaming() == 0 ? "disabled" : "enabled");
                                        idx++;
                                    }
                                }
                            }
                        }
                    }
                } catch (SecurityException e) {
                    String str = f7821a;
                } catch (Exception e2) {
                    C4549w.m8594c(f7821a, e2.getMessage());
                }
            }
        }
        return result;
    }
}
