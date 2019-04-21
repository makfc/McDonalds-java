package com.google.android.gms.tagmanager;

import com.facebook.internal.ServerProtocol;
import com.google.android.gms.internal.zzag.zza;
import com.newrelic.agent.android.util.SafeJsonPrimitive;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class zzdl {
    private static final Object zzbrG = null;
    private static Long zzbrH = new Long(0);
    private static Double zzbrI = new Double(0.0d);
    private static zzdk zzbrJ = zzdk.zzar(0);
    private static String zzbrK = new String("");
    private static Boolean zzbrL = new Boolean(false);
    private static List<Object> zzbrM = new ArrayList(0);
    private static Map<Object, Object> zzbrN = new HashMap();
    private static zza zzbrO = zzQ(zzbrK);

    private static double getDouble(Object obj) {
        if (obj instanceof Number) {
            return ((Number) obj).doubleValue();
        }
        zzbn.m7501e("getDouble received non-Number");
        return 0.0d;
    }

    public static Object zzKN() {
        return null;
    }

    public static Long zzKO() {
        return zzbrH;
    }

    public static Boolean zzKQ() {
        return zzbrL;
    }

    public static zzdk zzKR() {
        return zzbrJ;
    }

    public static String zzKS() {
        return zzbrK;
    }

    public static zza zzKT() {
        return zzbrO;
    }

    public static String zzL(Object obj) {
        return obj == null ? zzbrK : obj.toString();
    }

    public static zzdk zzM(Object obj) {
        return obj instanceof zzdk ? (zzdk) obj : zzS(obj) ? zzdk.zzar(zzT(obj)) : zzR(obj) ? zzdk.zza(Double.valueOf(getDouble(obj))) : zzgO(zzL(obj));
    }

    public static Long zzN(Object obj) {
        return zzS(obj) ? Long.valueOf(zzT(obj)) : zzgP(zzL(obj));
    }

    public static Boolean zzP(Object obj) {
        return obj instanceof Boolean ? (Boolean) obj : zzgR(zzL(obj));
    }

    public static zza zzQ(Object obj) {
        boolean z = false;
        zza zza = new zza();
        if (obj instanceof zza) {
            return (zza) obj;
        }
        ArrayList arrayList;
        boolean z2;
        boolean z3;
        if (obj instanceof String) {
            zza.type = 1;
            zza.zzjJ = (String) obj;
        } else if (obj instanceof List) {
            zza.type = 2;
            List<Object> list = (List) obj;
            arrayList = new ArrayList(list.size());
            z2 = false;
            for (Object zzQ : list) {
                zza zzQ2 = zzQ(zzQ);
                if (zzQ2 == zzbrO) {
                    return zzbrO;
                }
                z3 = z2 || zzQ2.zzjT;
                arrayList.add(zzQ2);
                z2 = z3;
            }
            zza.zzjK = (zza[]) arrayList.toArray(new zza[0]);
            z = z2;
        } else if (obj instanceof Map) {
            zza.type = 3;
            Set<Entry> entrySet = ((Map) obj).entrySet();
            arrayList = new ArrayList(entrySet.size());
            ArrayList arrayList2 = new ArrayList(entrySet.size());
            z2 = false;
            for (Entry entry : entrySet) {
                zza zzQ3 = zzQ(entry.getKey());
                zza zzQ4 = zzQ(entry.getValue());
                if (zzQ3 == zzbrO || zzQ4 == zzbrO) {
                    return zzbrO;
                }
                z3 = z2 || zzQ3.zzjT || zzQ4.zzjT;
                arrayList.add(zzQ3);
                arrayList2.add(zzQ4);
                z2 = z3;
            }
            zza.zzjL = (zza[]) arrayList.toArray(new zza[0]);
            zza.zzjM = (zza[]) arrayList2.toArray(new zza[0]);
            z = z2;
        } else if (zzR(obj)) {
            zza.type = 1;
            zza.zzjJ = obj.toString();
        } else if (zzS(obj)) {
            zza.type = 6;
            zza.zzjP = zzT(obj);
        } else if (obj instanceof Boolean) {
            zza.type = 8;
            zza.zzjQ = ((Boolean) obj).booleanValue();
        } else {
            String str = "Converting to Value from unknown object type: ";
            String valueOf = String.valueOf(obj == null ? SafeJsonPrimitive.NULL_STRING : obj.getClass().toString());
            zzbn.m7501e(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            return zzbrO;
        }
        zza.zzjT = z;
        return zza;
    }

    private static boolean zzR(Object obj) {
        return (obj instanceof Double) || (obj instanceof Float) || ((obj instanceof zzdk) && ((zzdk) obj).zzKI());
    }

    private static boolean zzS(Object obj) {
        return (obj instanceof Byte) || (obj instanceof Short) || (obj instanceof Integer) || (obj instanceof Long) || ((obj instanceof zzdk) && ((zzdk) obj).zzKJ());
    }

    private static long zzT(Object obj) {
        if (obj instanceof Number) {
            return ((Number) obj).longValue();
        }
        zzbn.m7501e("getInt64 received non-Number");
        return 0;
    }

    public static String zzg(zza zza) {
        return zzL(zzl(zza));
    }

    public static zza zzgN(String str) {
        zza zza = new zza();
        zza.type = 5;
        zza.zzjO = str;
        return zza;
    }

    private static zzdk zzgO(String str) {
        try {
            return zzdk.zzgM(str);
        } catch (NumberFormatException e) {
            zzbn.m7501e(new StringBuilder(String.valueOf(str).length() + 33).append("Failed to convert '").append(str).append("' to a number.").toString());
            return zzbrJ;
        }
    }

    private static Long zzgP(String str) {
        zzdk zzgO = zzgO(str);
        return zzgO == zzbrJ ? zzbrH : Long.valueOf(zzgO.longValue());
    }

    private static Boolean zzgR(String str) {
        return ServerProtocol.DIALOG_RETURN_SCOPES_TRUE.equalsIgnoreCase(str) ? Boolean.TRUE : "false".equalsIgnoreCase(str) ? Boolean.FALSE : zzbrL;
    }

    public static zzdk zzh(zza zza) {
        return zzM(zzl(zza));
    }

    public static Long zzi(zza zza) {
        return zzN(zzl(zza));
    }

    public static Boolean zzk(zza zza) {
        return zzP(zzl(zza));
    }

    public static Object zzl(zza zza) {
        int i = 0;
        if (zza == null) {
            return null;
        }
        zza[] zzaArr;
        int length;
        switch (zza.type) {
            case 1:
                return zza.zzjJ;
            case 2:
                ArrayList arrayList = new ArrayList(zza.zzjK.length);
                zzaArr = zza.zzjK;
                length = zzaArr.length;
                while (i < length) {
                    Object zzl = zzl(zzaArr[i]);
                    if (zzl == null) {
                        return null;
                    }
                    arrayList.add(zzl);
                    i++;
                }
                return arrayList;
            case 3:
                if (zza.zzjL.length != zza.zzjM.length) {
                    String str = "Converting an invalid value to object: ";
                    String valueOf = String.valueOf(zza.toString());
                    zzbn.m7501e(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
                    return null;
                }
                HashMap hashMap = new HashMap(zza.zzjM.length);
                while (i < zza.zzjL.length) {
                    Object zzl2 = zzl(zza.zzjL[i]);
                    Object zzl3 = zzl(zza.zzjM[i]);
                    if (zzl2 == null || zzl3 == null) {
                        return null;
                    }
                    hashMap.put(zzl2, zzl3);
                    i++;
                }
                return hashMap;
            case 4:
                zzbn.m7501e("Trying to convert a macro reference to object");
                return null;
            case 5:
                zzbn.m7501e("Trying to convert a function id to object");
                return null;
            case 6:
                return Long.valueOf(zza.zzjP);
            case 7:
                StringBuffer stringBuffer = new StringBuffer();
                zzaArr = zza.zzjR;
                length = zzaArr.length;
                while (i < length) {
                    String zzg = zzg(zzaArr[i]);
                    if (zzg == zzbrK) {
                        return null;
                    }
                    stringBuffer.append(zzg);
                    i++;
                }
                return stringBuffer.toString();
            case 8:
                return Boolean.valueOf(zza.zzjQ);
            default:
                zzbn.m7501e("Failed to convert a value of type: " + zza.type);
                return null;
        }
    }
}
