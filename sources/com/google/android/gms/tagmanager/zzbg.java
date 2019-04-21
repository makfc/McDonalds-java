package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzabt;
import com.google.android.gms.internal.zzabt.zzc;
import com.google.android.gms.internal.zzabt.zzd;
import com.google.android.gms.internal.zzae;
import com.google.android.gms.internal.zzag.zza;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class zzbg {
    private static zza zzJ(Object obj) throws JSONException {
        return zzdl.zzQ(zzK(obj));
    }

    static Object zzK(Object obj) throws JSONException {
        if (obj instanceof JSONArray) {
            throw new RuntimeException("JSONArrays are not supported");
        } else if (JSONObject.NULL.equals(obj)) {
            throw new RuntimeException("JSON nulls are not supported");
        } else if (!(obj instanceof JSONObject)) {
            return obj;
        } else {
            JSONObject jSONObject = (JSONObject) obj;
            HashMap hashMap = new HashMap();
            Iterator keys = jSONObject.keys();
            while (keys.hasNext()) {
                String str = (String) keys.next();
                hashMap.put(str, zzK(jSONObject.get(str)));
            }
            return hashMap;
        }
    }

    public static zzc zzgC(String str) throws JSONException {
        zza zzJ = zzJ(JSONObjectInstrumentation.init(str));
        zzd zzMB = zzc.zzMB();
        for (int i = 0; i < zzJ.zzjL.length; i++) {
            zzMB.zzc(zzabt.zza.zzMz().zzb(zzae.INSTANCE_NAME.toString(), zzJ.zzjL[i]).zzb(zzae.FUNCTION.toString(), zzdl.zzgN(zzn.zzJi())).zzb(zzn.zzJj(), zzJ.zzjM[i]).zzMA());
        }
        return zzMB.zzMD();
    }
}
