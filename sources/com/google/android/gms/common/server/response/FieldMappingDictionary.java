package com.google.android.gms.common.server.response;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.server.response.FastJsonResponse.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FieldMappingDictionary extends AbstractSafeParcelable {
    public static final zzc CREATOR = new zzc();
    private final int mVersionCode;
    private final HashMap<String, Map<String, Field<?, ?>>> zzasT;
    private final ArrayList<Entry> zzasU = null;
    private final String zzasV;

    public static class Entry extends AbstractSafeParcelable {
        public static final zzd CREATOR = new zzd();
        final String className;
        final int versionCode;
        final ArrayList<FieldMapPair> zzasW;

        Entry(int i, String str, ArrayList<FieldMapPair> arrayList) {
            this.versionCode = i;
            this.className = str;
            this.zzasW = arrayList;
        }

        Entry(String str, Map<String, Field<?, ?>> map) {
            this.versionCode = 1;
            this.className = str;
            this.zzasW = zzT(map);
        }

        private static ArrayList<FieldMapPair> zzT(Map<String, Field<?, ?>> map) {
            if (map == null) {
                return null;
            }
            ArrayList<FieldMapPair> arrayList = new ArrayList();
            for (String str : map.keySet()) {
                arrayList.add(new FieldMapPair(str, (Field) map.get(str)));
            }
            return arrayList;
        }

        public void writeToParcel(Parcel parcel, int i) {
            zzd zzd = CREATOR;
            zzd.zza(this, parcel, i);
        }

        /* Access modifiers changed, original: 0000 */
        public HashMap<String, Field<?, ?>> zzuu() {
            HashMap hashMap = new HashMap();
            int size = this.zzasW.size();
            for (int i = 0; i < size; i++) {
                FieldMapPair fieldMapPair = (FieldMapPair) this.zzasW.get(i);
                hashMap.put(fieldMapPair.zzaB, fieldMapPair.zzasX);
            }
            return hashMap;
        }
    }

    public static class FieldMapPair extends AbstractSafeParcelable {
        public static final zzb CREATOR = new zzb();
        final int versionCode;
        final String zzaB;
        final Field<?, ?> zzasX;

        FieldMapPair(int i, String str, Field<?, ?> field) {
            this.versionCode = i;
            this.zzaB = str;
            this.zzasX = field;
        }

        FieldMapPair(String str, Field<?, ?> field) {
            this.versionCode = 1;
            this.zzaB = str;
            this.zzasX = field;
        }

        public void writeToParcel(Parcel parcel, int i) {
            zzb zzb = CREATOR;
            zzb.zza(this, parcel, i);
        }
    }

    FieldMappingDictionary(int i, ArrayList<Entry> arrayList, String str) {
        this.mVersionCode = i;
        this.zzasT = zze(arrayList);
        this.zzasV = (String) zzaa.zzz(str);
        zzur();
    }

    private static HashMap<String, Map<String, Field<?, ?>>> zze(ArrayList<Entry> arrayList) {
        HashMap hashMap = new HashMap();
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            Entry entry = (Entry) arrayList.get(i);
            hashMap.put(entry.className, entry.zzuu());
        }
        return hashMap;
    }

    /* Access modifiers changed, original: 0000 */
    public int getVersionCode() {
        return this.mVersionCode;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (String str : this.zzasT.keySet()) {
            stringBuilder.append(str).append(":\n");
            Map map = (Map) this.zzasT.get(str);
            for (String str2 : map.keySet()) {
                stringBuilder.append("  ").append(str2).append(": ");
                stringBuilder.append(map.get(str2));
            }
        }
        return stringBuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzc zzc = CREATOR;
        zzc.zza(this, parcel, i);
    }

    public Map<String, Field<?, ?>> zzdq(String str) {
        return (Map) this.zzasT.get(str);
    }

    public void zzur() {
        for (String str : this.zzasT.keySet()) {
            Map map = (Map) this.zzasT.get(str);
            for (String str2 : map.keySet()) {
                ((Field) map.get(str2)).zza(this);
            }
        }
    }

    /* Access modifiers changed, original: 0000 */
    public ArrayList<Entry> zzus() {
        ArrayList arrayList = new ArrayList();
        for (String str : this.zzasT.keySet()) {
            arrayList.add(new Entry(str, (Map) this.zzasT.get(str)));
        }
        return arrayList;
    }

    public String zzut() {
        return this.zzasV;
    }
}
