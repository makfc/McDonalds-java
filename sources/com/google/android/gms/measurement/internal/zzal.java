package com.google.android.gms.measurement.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ServiceInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.internal.zzamc;
import com.google.android.gms.internal.zzsn.zzb;
import com.google.android.gms.internal.zzsn.zzc;
import com.google.android.gms.internal.zzsn.zzd;
import com.google.android.gms.internal.zzsn.zze;
import com.google.android.gms.internal.zzsn.zzf;
import com.google.android.gms.internal.zzsp;
import com.google.android.gms.internal.zzsp.zza;
import com.google.android.gms.internal.zzsp.zzg;
import com.google.android.gms.measurement.AppMeasurement;
import com.newrelic.agent.android.analytics.AnalyticAttribute;
import com.newrelic.agent.android.util.SafeJsonPrimitive;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.BitSet;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class zzal extends zzz {
    zzal(zzx zzx) {
        super(zzx);
    }

    public static boolean zzK(Bundle bundle) {
        return bundle.getLong("_c") == 1;
    }

    public static boolean zzZ(String str, String str2) {
        return (str == null && str2 == null) ? true : str == null ? false : str.equals(str2);
    }

    private Object zza(int i, Object obj, boolean z) {
        if (obj == null) {
            return null;
        }
        if ((obj instanceof Long) || (obj instanceof Double)) {
            return obj;
        }
        if (obj instanceof Integer) {
            return Long.valueOf((long) ((Integer) obj).intValue());
        }
        if (obj instanceof Byte) {
            return Long.valueOf((long) ((Byte) obj).byteValue());
        }
        if (obj instanceof Short) {
            return Long.valueOf((long) ((Short) obj).shortValue());
        }
        if (!(obj instanceof Boolean)) {
            return obj instanceof Float ? Double.valueOf(((Float) obj).doubleValue()) : ((obj instanceof String) || (obj instanceof Character) || (obj instanceof CharSequence)) ? zza(String.valueOf(obj), i, z) : null;
        } else {
            return Long.valueOf(((Boolean) obj).booleanValue() ? 1 : 0);
        }
    }

    public static String zza(zzb zzb) {
        int i = 0;
        if (zzb == null) {
            return SafeJsonPrimitive.NULL_STRING;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\nevent_filter {\n");
        zza(stringBuilder, 0, "filter_id", zzb.zzbgm);
        zza(stringBuilder, 0, "event_name", zzb.zzbgn);
        zza(stringBuilder, 1, "event_count_filter", zzb.zzbgq);
        stringBuilder.append("  filters {\n");
        zzc[] zzcArr = zzb.zzbgo;
        int length = zzcArr.length;
        while (i < length) {
            zza(stringBuilder, 2, zzcArr[i]);
            i++;
        }
        zza(stringBuilder, 1);
        stringBuilder.append("}\n}\n");
        return stringBuilder.toString();
    }

    public static String zza(zze zze) {
        if (zze == null) {
            return SafeJsonPrimitive.NULL_STRING;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\nproperty_filter {\n");
        zza(stringBuilder, 0, "filter_id", zze.zzbgm);
        zza(stringBuilder, 0, "property_name", zze.zzbgC);
        zza(stringBuilder, 1, zze.zzbgD);
        stringBuilder.append("}\n");
        return stringBuilder.toString();
    }

    private static void zza(StringBuilder stringBuilder, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            stringBuilder.append("  ");
        }
    }

    private static void zza(StringBuilder stringBuilder, int i, zzc zzc) {
        if (zzc != null) {
            zza(stringBuilder, i);
            stringBuilder.append("filter {\n");
            zza(stringBuilder, i, "complement", zzc.zzbgu);
            zza(stringBuilder, i, "param_name", zzc.zzbgv);
            zza(stringBuilder, i + 1, "string_filter", zzc.zzbgs);
            zza(stringBuilder, i + 1, "number_filter", zzc.zzbgt);
            zza(stringBuilder, i);
            stringBuilder.append("}\n");
        }
    }

    private static void zza(StringBuilder stringBuilder, int i, zzsp.zze zze) {
        if (zze != null) {
            zza(stringBuilder, i);
            stringBuilder.append("bundle {\n");
            zza(stringBuilder, i, "protocol_version", zze.zzbhd);
            zza(stringBuilder, i, AnalyticAttribute.APPLICATION_PLATFORM_ATTRIBUTE, zze.zzbhl);
            zza(stringBuilder, i, "gmp_version", zze.zzbhp);
            zza(stringBuilder, i, "uploading_gmp_version", zze.zzbhq);
            zza(stringBuilder, i, "gmp_app_id", zze.zzbbK);
            zza(stringBuilder, i, "app_id", zze.appId);
            zza(stringBuilder, i, "app_version", zze.zzaUf);
            zza(stringBuilder, i, "app_version_major", zze.zzbhy);
            zza(stringBuilder, i, "firebase_instance_id", zze.zzbbS);
            zza(stringBuilder, i, "dev_cert_hash", zze.zzbhu);
            zza(stringBuilder, i, "app_store", zze.zzbbL);
            zza(stringBuilder, i, "upload_timestamp_millis", zze.zzbhg);
            zza(stringBuilder, i, "start_timestamp_millis", zze.zzbhh);
            zza(stringBuilder, i, "end_timestamp_millis", zze.zzbhi);
            zza(stringBuilder, i, "previous_bundle_start_timestamp_millis", zze.zzbhj);
            zza(stringBuilder, i, "previous_bundle_end_timestamp_millis", zze.zzbhk);
            zza(stringBuilder, i, "app_instance_id", zze.zzbht);
            zza(stringBuilder, i, "resettable_device_id", zze.zzbhr);
            zza(stringBuilder, i, "device_id", zze.zzbhB);
            zza(stringBuilder, i, "limited_ad_tracking", zze.zzbhs);
            zza(stringBuilder, i, "os_version", zze.osVersion);
            zza(stringBuilder, i, "device_model", zze.zzbhm);
            zza(stringBuilder, i, "user_default_language", zze.zzbhn);
            zza(stringBuilder, i, "time_zone_offset_minutes", zze.zzbho);
            zza(stringBuilder, i, "bundle_sequential_index", zze.zzbhv);
            zza(stringBuilder, i, "service_upload", zze.zzbhw);
            zza(stringBuilder, i, "health_monitor", zze.zzbbO);
            zza(stringBuilder, i, zze.zzbhf);
            zza(stringBuilder, i, zze.zzbhx);
            zza(stringBuilder, i, zze.zzbhe);
            zza(stringBuilder, i);
            stringBuilder.append("}\n");
        }
    }

    private static void zza(StringBuilder stringBuilder, int i, String str, zzd zzd) {
        if (zzd != null) {
            zza(stringBuilder, i);
            stringBuilder.append(str);
            stringBuilder.append(" {\n");
            if (zzd.zzbgw != null) {
                Object obj = "UNKNOWN_COMPARISON_TYPE";
                switch (zzd.zzbgw.intValue()) {
                    case 1:
                        obj = "LESS_THAN";
                        break;
                    case 2:
                        obj = "GREATER_THAN";
                        break;
                    case 3:
                        obj = "EQUAL";
                        break;
                    case 4:
                        obj = "BETWEEN";
                        break;
                }
                zza(stringBuilder, i, "comparison_type", obj);
            }
            zza(stringBuilder, i, "match_as_float", zzd.zzbgx);
            zza(stringBuilder, i, "comparison_value", zzd.zzbgy);
            zza(stringBuilder, i, "min_comparison_value", zzd.zzbgz);
            zza(stringBuilder, i, "max_comparison_value", zzd.zzbgA);
            zza(stringBuilder, i);
            stringBuilder.append("}\n");
        }
    }

    private static void zza(StringBuilder stringBuilder, int i, String str, zzf zzf) {
        if (zzf != null) {
            zza(stringBuilder, i);
            stringBuilder.append(str);
            stringBuilder.append(" {\n");
            if (zzf.zzbgE != null) {
                Object obj = "UNKNOWN_MATCH_TYPE";
                switch (zzf.zzbgE.intValue()) {
                    case 1:
                        obj = "REGEXP";
                        break;
                    case 2:
                        obj = "BEGINS_WITH";
                        break;
                    case 3:
                        obj = "ENDS_WITH";
                        break;
                    case 4:
                        obj = "PARTIAL";
                        break;
                    case 5:
                        obj = "EXACT";
                        break;
                    case 6:
                        obj = "IN_LIST";
                        break;
                }
                zza(stringBuilder, i, "match_type", obj);
            }
            zza(stringBuilder, i, "expression", zzf.zzbgF);
            zza(stringBuilder, i, "case_sensitive", zzf.zzbgG);
            if (zzf.zzbgH.length > 0) {
                zza(stringBuilder, i + 1);
                stringBuilder.append("expression_list {\n");
                for (String str2 : zzf.zzbgH) {
                    zza(stringBuilder, i + 2);
                    stringBuilder.append(str2);
                    stringBuilder.append("\n");
                }
                stringBuilder.append("}\n");
            }
            zza(stringBuilder, i);
            stringBuilder.append("}\n");
        }
    }

    private static void zza(StringBuilder stringBuilder, int i, String str, zzsp.zzf zzf) {
        int i2 = 0;
        if (zzf != null) {
            int i3;
            int i4;
            int i5 = i + 1;
            zza(stringBuilder, i5);
            stringBuilder.append(str);
            stringBuilder.append(" {\n");
            if (zzf.zzbhD != null) {
                zza(stringBuilder, i5 + 1);
                stringBuilder.append("results: ");
                long[] jArr = zzf.zzbhD;
                int length = jArr.length;
                i3 = 0;
                i4 = 0;
                while (i3 < length) {
                    Long valueOf = Long.valueOf(jArr[i3]);
                    int i6 = i4 + 1;
                    if (i4 != 0) {
                        stringBuilder.append(", ");
                    }
                    stringBuilder.append(valueOf);
                    i3++;
                    i4 = i6;
                }
                stringBuilder.append(10);
            }
            if (zzf.zzbhC != null) {
                zza(stringBuilder, i5 + 1);
                stringBuilder.append("status: ");
                long[] jArr2 = zzf.zzbhC;
                int length2 = jArr2.length;
                i3 = 0;
                while (i2 < length2) {
                    Long valueOf2 = Long.valueOf(jArr2[i2]);
                    i4 = i3 + 1;
                    if (i3 != 0) {
                        stringBuilder.append(", ");
                    }
                    stringBuilder.append(valueOf2);
                    i2++;
                    i3 = i4;
                }
                stringBuilder.append(10);
            }
            zza(stringBuilder, i5);
            stringBuilder.append("}\n");
        }
    }

    private static void zza(StringBuilder stringBuilder, int i, String str, Object obj) {
        if (obj != null) {
            zza(stringBuilder, i + 1);
            stringBuilder.append(str);
            stringBuilder.append(": ");
            stringBuilder.append(obj);
            stringBuilder.append(10);
        }
    }

    private static void zza(StringBuilder stringBuilder, int i, zza[] zzaArr) {
        if (zzaArr != null) {
            int i2 = i + 1;
            for (zza zza : zzaArr) {
                if (zza != null) {
                    zza(stringBuilder, i2);
                    stringBuilder.append("audience_membership {\n");
                    zza(stringBuilder, i2, "audience_id", zza.zzbgi);
                    zza(stringBuilder, i2, "new_audience", zza.zzbgU);
                    zza(stringBuilder, i2, "current_data", zza.zzbgS);
                    zza(stringBuilder, i2, "previous_data", zza.zzbgT);
                    zza(stringBuilder, i2);
                    stringBuilder.append("}\n");
                }
            }
        }
    }

    private static void zza(StringBuilder stringBuilder, int i, zzsp.zzb[] zzbArr) {
        if (zzbArr != null) {
            int i2 = i + 1;
            for (zzsp.zzb zzb : zzbArr) {
                if (zzb != null) {
                    zza(stringBuilder, i2);
                    stringBuilder.append("event {\n");
                    zza(stringBuilder, i2, "name", zzb.name);
                    zza(stringBuilder, i2, "timestamp_millis", zzb.zzbgX);
                    zza(stringBuilder, i2, "previous_timestamp_millis", zzb.zzbgY);
                    zza(stringBuilder, i2, "count", zzb.count);
                    zza(stringBuilder, i2, zzb.zzbgW);
                    zza(stringBuilder, i2);
                    stringBuilder.append("}\n");
                }
            }
        }
    }

    private static void zza(StringBuilder stringBuilder, int i, zzsp.zzc[] zzcArr) {
        if (zzcArr != null) {
            int i2 = i + 1;
            for (zzsp.zzc zzc : zzcArr) {
                if (zzc != null) {
                    zza(stringBuilder, i2);
                    stringBuilder.append("param {\n");
                    zza(stringBuilder, i2, "name", zzc.name);
                    zza(stringBuilder, i2, "string_value", zzc.zzasH);
                    zza(stringBuilder, i2, "int_value", zzc.zzbha);
                    zza(stringBuilder, i2, "double_value", zzc.zzbgf);
                    zza(stringBuilder, i2);
                    stringBuilder.append("}\n");
                }
            }
        }
    }

    private static void zza(StringBuilder stringBuilder, int i, zzg[] zzgArr) {
        if (zzgArr != null) {
            int i2 = i + 1;
            for (zzg zzg : zzgArr) {
                if (zzg != null) {
                    zza(stringBuilder, i2);
                    stringBuilder.append("user_property {\n");
                    zza(stringBuilder, i2, "set_timestamp_millis", zzg.zzbhF);
                    zza(stringBuilder, i2, "name", zzg.name);
                    zza(stringBuilder, i2, "string_value", zzg.zzasH);
                    zza(stringBuilder, i2, "int_value", zzg.zzbha);
                    zza(stringBuilder, i2, "double_value", zzg.zzbgf);
                    zza(stringBuilder, i2);
                    stringBuilder.append("}\n");
                }
            }
        }
    }

    public static boolean zza(long[] jArr, int i) {
        return i < jArr.length * 64 && (jArr[i / 64] & (1 << (i % 64))) != 0;
    }

    public static long[] zza(BitSet bitSet) {
        int length = (bitSet.length() + 63) / 64;
        long[] jArr = new long[length];
        int i = 0;
        while (i < length) {
            jArr[i] = 0;
            int i2 = 0;
            while (i2 < 64 && (i * 64) + i2 < bitSet.length()) {
                if (bitSet.get((i * 64) + i2)) {
                    jArr[i] = jArr[i] | (1 << i2);
                }
                i2++;
            }
            i++;
        }
        return jArr;
    }

    public static String zzb(zzsp.zzd zzd) {
        if (zzd == null) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\nbatch {\n");
        if (zzd.zzbhb != null) {
            for (zzsp.zze zze : zzd.zzbhb) {
                if (zze != null) {
                    zza(stringBuilder, 1, zze);
                }
            }
        }
        stringBuilder.append("}\n");
        return stringBuilder.toString();
    }

    public static boolean zzb(Context context, String str, boolean z) {
        try {
            ActivityInfo receiverInfo = context.getPackageManager().getReceiverInfo(new ComponentName(context, str), 2);
            if (receiverInfo != null && receiverInfo.enabled && (!z || receiverInfo.exported)) {
                return true;
            }
        } catch (NameNotFoundException e) {
        }
        return false;
    }

    static MessageDigest zzbZ(String str) {
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= 2) {
                return null;
            }
            try {
                MessageDigest instance = MessageDigest.getInstance(str);
                if (instance != null) {
                    return instance;
                }
                i = i2 + 1;
            } catch (NoSuchAlgorithmException e) {
            }
        }
    }

    static boolean zzfG(String str) {
        zzaa.zzdl(str);
        return str.charAt(0) != '_';
    }

    private int zzfP(String str) {
        return "_ldl".equals(str) ? zzFo().zzEC() : zzFo().zzEB();
    }

    public static boolean zzfQ(String str) {
        return !TextUtils.isEmpty(str) && str.startsWith("_");
    }

    public static boolean zzj(Context context, String str) {
        try {
            ServiceInfo serviceInfo = context.getPackageManager().getServiceInfo(new ComponentName(context, str), 4);
            if (serviceInfo != null && serviceInfo.enabled) {
                return true;
            }
        } catch (NameNotFoundException e) {
        }
        return false;
    }

    static long zzs(byte[] bArr) {
        long j = null;
        zzaa.zzz(bArr);
        zzaa.zzai(bArr.length > 0);
        long j2 = 0;
        int length = bArr.length - 1;
        while (length >= 0 && length >= bArr.length - 8) {
            j2 += (((long) bArr[length]) & 255) << j;
            j += 8;
            length--;
        }
        return j2;
    }

    public /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    public /* bridge */ /* synthetic */ void zzFb() {
        super.zzFb();
    }

    public /* bridge */ /* synthetic */ zzc zzFc() {
        return super.zzFc();
    }

    public /* bridge */ /* synthetic */ zzac zzFd() {
        return super.zzFd();
    }

    public /* bridge */ /* synthetic */ zzn zzFe() {
        return super.zzFe();
    }

    public /* bridge */ /* synthetic */ zzg zzFf() {
        return super.zzFf();
    }

    public /* bridge */ /* synthetic */ zzad zzFg() {
        return super.zzFg();
    }

    public /* bridge */ /* synthetic */ zze zzFh() {
        return super.zzFh();
    }

    public /* bridge */ /* synthetic */ zzal zzFi() {
        return super.zzFi();
    }

    public /* bridge */ /* synthetic */ zzv zzFj() {
        return super.zzFj();
    }

    public /* bridge */ /* synthetic */ zzaf zzFk() {
        return super.zzFk();
    }

    public /* bridge */ /* synthetic */ zzw zzFl() {
        return super.zzFl();
    }

    public /* bridge */ /* synthetic */ zzp zzFm() {
        return super.zzFm();
    }

    public /* bridge */ /* synthetic */ zzt zzFn() {
        return super.zzFn();
    }

    public /* bridge */ /* synthetic */ zzd zzFo() {
        return super.zzFo();
    }

    /* Access modifiers changed, original: 0000 */
    public boolean zzX(String str, String str2) {
        if (str2 == null) {
            zzFm().zzFE().zzj("Name is required and can't be null. Type", str);
            return false;
        } else if (str2.length() == 0) {
            zzFm().zzFE().zzj("Name is required and can't be empty. Type", str);
            return false;
        } else if (Character.isLetter(str2.charAt(0))) {
            int i = 1;
            while (i < str2.length()) {
                char charAt = str2.charAt(i);
                if (charAt == '_' || Character.isLetterOrDigit(charAt)) {
                    i++;
                } else {
                    zzFm().zzFE().zze("Name must consist of letters, digits or _ (underscores). Type, name", str, str2);
                    return false;
                }
            }
            return true;
        } else {
            zzFm().zzFE().zze("Name must start with a letter. Type, name", str, str2);
            return false;
        }
    }

    /* Access modifiers changed, original: 0000 */
    public boolean zzY(String str, String str2) {
        if (str2 == null) {
            zzFm().zzFE().zzj("Name is required and can't be null. Type", str);
            return false;
        } else if (str2.length() == 0) {
            zzFm().zzFE().zzj("Name is required and can't be empty. Type", str);
            return false;
        } else {
            char charAt = str2.charAt(0);
            if (Character.isLetter(charAt) || charAt == '_') {
                int i = 1;
                while (i < str2.length()) {
                    char charAt2 = str2.charAt(i);
                    if (charAt2 == '_' || Character.isLetterOrDigit(charAt2)) {
                        i++;
                    } else {
                        zzFm().zzFE().zze("Name must start with a letter or _ (underscores). Type, name", str, str2);
                        return false;
                    }
                }
                return true;
            }
            zzFm().zzFE().zze("Name must start with a letter or _ (underscores). Type, name", str, str2);
            return false;
        }
    }

    public Bundle zza(String str, Bundle bundle, @Nullable List<String> list, boolean z) {
        if (bundle == null) {
            return null;
        }
        Bundle bundle2 = new Bundle(bundle);
        int zzEv = zzFo().zzEv();
        int i = 0;
        for (String str2 : bundle.keySet()) {
            int zzfL;
            if (list == null || !list.contains(str2)) {
                zzfL = z ? zzfL(str2) : 0;
                if (zzfL == 0) {
                    zzfL = zzfM(str2);
                }
            } else {
                zzfL = 0;
            }
            if (zzfL != 0) {
                if (zzb(bundle2, zzfL)) {
                    bundle2.putString("_ev", zza(str2, zzFo().zzEy(), true));
                }
                bundle2.remove(str2);
            } else if (zzk(str2, bundle.get(str2)) || "_ev".equals(str2)) {
                if (zzfG(str2)) {
                    i++;
                    if (i > zzEv) {
                        zzFm().zzFE().zze("Event can't contain more then " + zzEv + " params", str, bundle);
                        zzb(bundle2, 5);
                        bundle2.remove(str2);
                    }
                }
                i = i;
            } else {
                if (zzb(bundle2, 4)) {
                    bundle2.putString("_ev", zza(str2, zzFo().zzEy(), true));
                }
                bundle2.remove(str2);
            }
        }
        return bundle2;
    }

    public String zza(String str, int i, boolean z) {
        return str.length() > i ? z ? String.valueOf(str.substring(0, i)).concat("...") : null : str;
    }

    public void zza(Bundle bundle, String str, Object obj) {
        if (bundle != null) {
            if (obj instanceof Long) {
                bundle.putLong(str, ((Long) obj).longValue());
            } else if (obj instanceof String) {
                bundle.putString(str, String.valueOf(obj));
            } else if (obj instanceof Double) {
                bundle.putDouble(str, ((Double) obj).doubleValue());
            } else if (str != null) {
                zzFm().zzFI().zze("Not putting event parameter. Invalid value type. name, type", str, obj != null ? obj.getClass().getSimpleName() : null);
            }
        }
    }

    public void zza(zzsp.zzc zzc, Object obj) {
        zzaa.zzz(obj);
        zzc.zzasH = null;
        zzc.zzbha = null;
        zzc.zzbgf = null;
        if (obj instanceof String) {
            zzc.zzasH = (String) obj;
        } else if (obj instanceof Long) {
            zzc.zzbha = (Long) obj;
        } else if (obj instanceof Double) {
            zzc.zzbgf = (Double) obj;
        } else {
            zzFm().zzFE().zzj("Ignoring invalid (type) event param value", obj);
        }
    }

    public void zza(zzg zzg, Object obj) {
        zzaa.zzz(obj);
        zzg.zzasH = null;
        zzg.zzbha = null;
        zzg.zzbgf = null;
        if (obj instanceof String) {
            zzg.zzasH = (String) obj;
        } else if (obj instanceof Long) {
            zzg.zzbha = (Long) obj;
        } else if (obj instanceof Double) {
            zzg.zzbgf = (Double) obj;
        } else {
            zzFm().zzFE().zzj("Ignoring invalid (type) user attribute value", obj);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public boolean zza(String str, String str2, int i, Object obj) {
        if (obj == null || (obj instanceof Long) || (obj instanceof Float) || (obj instanceof Integer) || (obj instanceof Byte) || (obj instanceof Short) || (obj instanceof Boolean) || (obj instanceof Double)) {
            return true;
        }
        if (!(obj instanceof String) && !(obj instanceof Character) && !(obj instanceof CharSequence)) {
            return false;
        }
        String valueOf = String.valueOf(obj);
        if (valueOf.length() <= i) {
            return true;
        }
        zzFm().zzFG().zzd("Value is too long; discarded. Value kind, name, value length", str, str2, Integer.valueOf(valueOf.length()));
        return false;
    }

    public byte[] zza(zzsp.zzd zzd) {
        try {
            byte[] bArr = new byte[zzd.getSerializedSize()];
            zzamc zzO = zzamc.zzO(bArr);
            zzd.writeTo(zzO);
            zzO.zzWU();
            return bArr;
        } catch (IOException e) {
            zzFm().zzFE().zzj("Data loss. Failed to serialize batch", e);
            return null;
        }
    }

    public boolean zzb(Bundle bundle, int i) {
        if (bundle == null || bundle.getLong("_err") != 0) {
            return false;
        }
        bundle.putLong("_err", (long) i);
        return true;
    }

    @WorkerThread
    public boolean zzbN(String str) {
        zzkN();
        if (getContext().checkCallingOrSelfPermission(str) == 0) {
            return true;
        }
        zzFm().zzFK().zzj("Permission not granted", str);
        return false;
    }

    public boolean zzc(long j, long j2) {
        return j == 0 || j2 <= 0 || Math.abs(zzlQ().currentTimeMillis() - j) > j2;
    }

    /* Access modifiers changed, original: 0000 */
    public boolean zzc(String str, int i, String str2) {
        if (str2 == null) {
            zzFm().zzFE().zzj("Name is required and can't be null. Type", str);
            return false;
        } else if (str2.length() <= i) {
            return true;
        } else {
            zzFm().zzFE().zzd("Name is too long. Type, maximum supported length, name", str, Integer.valueOf(i), str2);
            return false;
        }
    }

    /* Access modifiers changed, original: 0000 */
    public boolean zzc(String str, Map<String, String> map, String str2) {
        if (str2 == null) {
            zzFm().zzFE().zzj("Name is required and can't be null. Type", str);
            return false;
        } else if (str2.startsWith("firebase_")) {
            zzFm().zzFE().zze("Name starts with reserved prefix. Type, name", str, str2);
            return false;
        } else if (map == null || !map.containsKey(str2)) {
            return true;
        } else {
            zzFm().zzFE().zze("Name is reserved. Type, name", str, str2);
            return false;
        }
    }

    public void zze(int i, String str, String str2) {
        Bundle bundle = new Bundle();
        zzb(bundle, i);
        if (!TextUtils.isEmpty(str)) {
            bundle.putString(str, str2);
        }
        this.zzbbl.zzFd().zze("auto", "_err", bundle);
    }

    public int zzfI(String str) {
        return !zzY("event", str) ? 2 : !zzc("event", AppMeasurement.zza.zzbbm, str) ? 13 : zzc("event", zzFo().zzEw(), str) ? 0 : 2;
    }

    public int zzfK(String str) {
        return !zzY("user property", str) ? 6 : !zzc("user property", AppMeasurement.zze.zzbbo, str) ? 15 : zzc("user property", zzFo().zzEx(), str) ? 0 : 6;
    }

    public int zzfL(String str) {
        return !zzX("event param", str) ? 3 : !zzc("event param", null, str) ? 14 : zzc("event param", zzFo().zzEy(), str) ? 0 : 3;
    }

    public int zzfM(String str) {
        return !zzY("event param", str) ? 3 : !zzc("event param", null, str) ? 14 : zzc("event param", zzFo().zzEy(), str) ? 0 : 3;
    }

    public boolean zzfN(String str) {
        if (TextUtils.isEmpty(str)) {
            zzFm().zzFE().log("Measurement Service called without google_app_id");
            return false;
        } else if (!str.startsWith("1:")) {
            zzFm().zzFG().zzj("Measurement Service called with unknown id version", str);
            return true;
        } else if (zzfO(str)) {
            return true;
        } else {
            zzFm().zzFE().zzj("Invalid google_app_id. Firebase Analytics disabled. See", "https://goo.gl/FZRIUV");
            return false;
        }
    }

    /* Access modifiers changed, original: 0000 */
    public boolean zzfO(String str) {
        zzaa.zzz(str);
        return str.matches("^1:\\d+:android:[a-f0-9]+$");
    }

    public byte[] zzh(byte[] bArr) throws IOException {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            gZIPOutputStream.write(bArr);
            gZIPOutputStream.close();
            byteArrayOutputStream.close();
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            zzFm().zzFE().zzj("Failed to gzip content", e);
            throw e;
        }
    }

    public boolean zzk(String str, Object obj) {
        return zzfQ(str) ? zza("param", str, zzFo().zzEA(), obj) : zza("param", str, zzFo().zzEz(), obj);
    }

    public /* bridge */ /* synthetic */ void zzkN() {
        super.zzkN();
    }

    public Object zzl(String str, Object obj) {
        if ("_ev".equals(str)) {
            return zza(zzFo().zzEA(), obj, true);
        }
        return zza(zzfQ(str) ? zzFo().zzEA() : zzFo().zzEz(), obj, false);
    }

    public /* bridge */ /* synthetic */ void zzlP() {
        super.zzlP();
    }

    public /* bridge */ /* synthetic */ com.google.android.gms.common.util.zze zzlQ() {
        return super.zzlQ();
    }

    public int zzm(String str, Object obj) {
        return "_ldl".equals(str) ? zza("user property referrer", str, zzfP(str), obj) : zza("user property", str, zzfP(str), obj) ? 0 : 7;
    }

    public Object zzn(String str, Object obj) {
        return "_ldl".equals(str) ? zza(zzfP(str), obj, true) : zza(zzfP(str), obj, false);
    }

    public byte[] zzr(byte[] bArr) throws IOException {
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
            GZIPInputStream gZIPInputStream = new GZIPInputStream(byteArrayInputStream);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr2 = new byte[1024];
            while (true) {
                int read = gZIPInputStream.read(bArr2);
                if (read <= 0) {
                    gZIPInputStream.close();
                    byteArrayInputStream.close();
                    return byteArrayOutputStream.toByteArray();
                }
                byteArrayOutputStream.write(bArr2, 0, read);
            }
        } catch (IOException e) {
            zzFm().zzFE().zzj("Failed to ungzip content", e);
            throw e;
        }
    }

    public long zzt(byte[] bArr) {
        zzaa.zzz(bArr);
        MessageDigest zzbZ = zzbZ("MD5");
        if (zzbZ != null) {
            return zzs(zzbZ.digest(bArr));
        }
        zzFm().zzFE().log("Failed to get MD5");
        return 0;
    }
}
