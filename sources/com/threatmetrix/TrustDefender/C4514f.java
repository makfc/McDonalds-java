package com.threatmetrix.TrustDefender;

import android.annotation.TargetApi;
import android.content.Context;
import android.telephony.CellIdentityCdma;
import android.telephony.CellIdentityGsm;
import android.telephony.CellIdentityLte;
import android.telephony.CellIdentityWcdma;
import android.telephony.CellInfo;
import android.telephony.CellInfoCdma;
import android.telephony.CellInfoGsm;
import android.telephony.CellInfoLte;
import android.telephony.CellInfoWcdma;
import android.telephony.CellSignalStrength;
import android.telephony.TelephonyManager;
import com.threatmetrix.TrustDefender.C4532g.C4518b.C4516a;
import com.threatmetrix.TrustDefender.C4532g.C4518b.C4517b;
import com.threatmetrix.TrustDefender.C4532g.C4530n;
import com.threatmetrix.TrustDefender.C4543p.C4541a;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* renamed from: com.threatmetrix.TrustDefender.f */
class C4514f {
    /* renamed from: a */
    private static final String f7579a = C4549w.m8585a(C4514f.class);

    C4514f() {
    }

    @TargetApi(18)
    /* renamed from: a */
    static Map<String, String> m8473a(Context context) {
        Map<String, String> cellTowerInformation = new HashMap();
        List<CellInfo> cellInfo = C4514f.m8476c(context);
        if (cellInfo == null || cellInfo.size() <= 0) {
            return cellTowerInformation;
        }
        for (CellInfo info : cellInfo) {
            if (info.isRegistered()) {
                switch (C4514f.m8472a(info)) {
                    case WCDMA:
                        C4514f.m8474a(((CellInfoWcdma) info).getCellSignalStrength(), ((CellInfoWcdma) info).getCellIdentity().toString(), cellTowerInformation);
                        break;
                    case GSM:
                        C4514f.m8474a(((CellInfoGsm) info).getCellSignalStrength(), ((CellInfoGsm) info).getCellIdentity().toString(), cellTowerInformation);
                        break;
                    case LTE:
                        C4514f.m8474a(((CellInfoLte) info).getCellSignalStrength(), ((CellInfoLte) info).getCellIdentity().toString(), cellTowerInformation);
                        break;
                    case CDMA:
                        C4514f.m8474a(((CellInfoCdma) info).getCellSignalStrength(), ((CellInfoCdma) info).getCellIdentity().toString(), cellTowerInformation);
                        break;
                }
                if (cellTowerInformation.size() < 3) {
                    return null;
                }
            }
        }
        return cellTowerInformation;
    }

    @TargetApi(18)
    /* renamed from: b */
    static String m8475b(Context context) {
        StringBuilder cellId = new StringBuilder();
        List<CellInfo> cellInfo = C4514f.m8476c(context);
        boolean isFirst = true;
        C4541a typeOfFirstMatch = C4541a.LTE;
        if (cellInfo != null && cellInfo.size() > 0) {
            for (CellInfo info : cellInfo) {
                if (info.isRegistered()) {
                    C4541a type = C4514f.m8472a(info);
                    if (isFirst) {
                        isFirst = false;
                    } else {
                        if (typeOfFirstMatch.mo34256b() <= type.mo34256b()) {
                        }
                    }
                    typeOfFirstMatch = type;
                    int id;
                    int mcc;
                    int mnc;
                    int lac;
                    switch (type) {
                        case WCDMA:
                            CellIdentityWcdma ciw = ((CellInfoWcdma) info).getCellIdentity();
                            id = ciw.getCid();
                            mcc = ciw.getMcc();
                            mnc = ciw.getMnc();
                            lac = ciw.getLac();
                            if (!(id == Integer.MAX_VALUE || mcc == Integer.MAX_VALUE || mnc == Integer.MAX_VALUE || lac == Integer.MAX_VALUE)) {
                                cellId.append(type.mo34255a()).append(":").append(String.valueOf(id)).append(":").append(String.valueOf(mcc)).append(":").append(String.valueOf(mnc)).append(":").append(String.valueOf(lac));
                                break;
                            }
                        case GSM:
                            CellIdentityGsm cig = ((CellInfoGsm) info).getCellIdentity();
                            id = cig.getCid();
                            mcc = cig.getMcc();
                            mnc = cig.getMnc();
                            lac = cig.getLac();
                            if (!(id == Integer.MAX_VALUE || mcc == Integer.MAX_VALUE || mnc == Integer.MAX_VALUE || lac == Integer.MAX_VALUE)) {
                                cellId.append(type.mo34255a()).append(":").append(String.valueOf(id)).append(":").append(String.valueOf(mcc)).append(":").append(String.valueOf(mnc)).append(":").append(String.valueOf(lac));
                                break;
                            }
                        case LTE:
                            CellIdentityLte cil = ((CellInfoLte) info).getCellIdentity();
                            id = cil.getCi();
                            mcc = cil.getMcc();
                            mnc = cil.getMnc();
                            lac = cil.getTac();
                            if (!(id == Integer.MAX_VALUE || mcc == Integer.MAX_VALUE || mnc == Integer.MAX_VALUE || lac == Integer.MAX_VALUE)) {
                                cellId.append(type.mo34255a()).append(":").append(String.valueOf(id)).append(":").append(String.valueOf(mcc)).append(":").append(String.valueOf(mnc)).append(":").append(String.valueOf(lac));
                                break;
                            }
                        case CDMA:
                            CellIdentityCdma cic = ((CellInfoCdma) info).getCellIdentity();
                            id = cic.getBasestationId();
                            mnc = cic.getSystemId();
                            lac = cic.getNetworkId();
                            if (!(id == Integer.MAX_VALUE || mnc == Integer.MAX_VALUE || lac == Integer.MAX_VALUE)) {
                                cellId.append(type.mo34255a()).append(":").append(String.valueOf(id)).append(":").append(String.valueOf(mnc)).append(":").append(String.valueOf(lac));
                                break;
                            }
                        default:
                            break;
                    }
                }
            }
        }
        return cellId.toString();
    }

    @TargetApi(18)
    /* renamed from: c */
    private static List<CellInfo> m8476c(Context context) {
        if (!C4530n.m8518a()) {
            return null;
        }
        try {
            Object systemService = context.getSystemService("phone");
            if (systemService == null || !(systemService instanceof TelephonyManager)) {
                return null;
            }
            TelephonyManager telephonyManager = (TelephonyManager) systemService;
            if (C4516a.f7584c >= C4517b.f7596k) {
                return telephonyManager.getAllCellInfo();
            }
            return null;
        } catch (SecurityException e) {
            String str = f7579a;
            return null;
        } catch (Exception e2) {
            C4549w.m8594c(f7579a, e2.getMessage());
            return null;
        }
    }

    /* renamed from: a */
    private static C4541a m8472a(CellInfo cellInfo) {
        if (C4530n.m8524g() && (cellInfo instanceof CellInfoWcdma)) {
            return C4541a.WCDMA;
        }
        if (C4530n.m8525h() && (cellInfo instanceof CellInfoGsm)) {
            return C4541a.GSM;
        }
        if (C4530n.m8526i() && (cellInfo instanceof CellInfoLte)) {
            return C4541a.LTE;
        }
        if (C4530n.m8527j() && (cellInfo instanceof CellInfoCdma)) {
            return C4541a.CDMA;
        }
        return C4541a.UNKOWN;
    }

    @TargetApi(18)
    /* renamed from: a */
    private static void m8474a(CellSignalStrength cellSignalStrength, String cellIdentityInfo, Map<String, String> cellTowerInformation) {
        if (cellSignalStrength.getAsuLevel() != 99) {
            cellTowerInformation.put("sl_ASU", String.valueOf(cellSignalStrength.getAsuLevel()));
        }
        cellTowerInformation.put("ss_dBm", String.valueOf(cellSignalStrength.getDbm()));
        cellTowerInformation.put("sl_int", String.valueOf(cellSignalStrength.getLevel()));
        cellTowerInformation.put("cii", cellIdentityInfo);
    }
}
