package com.google.android.gms.common.server.converter;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.server.response.FastJsonResponse.zza;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public final class StringToIntConverter extends AbstractSafeParcelable implements zza<String, Integer> {
    public static final zzb CREATOR = new zzb();
    private final int mVersionCode;
    private final HashMap<String, Integer> zzasE;
    private final HashMap<Integer, String> zzasF;
    private final ArrayList<Entry> zzasG;

    public static final class Entry extends AbstractSafeParcelable {
        public static final zzc CREATOR = new zzc();
        final int versionCode;
        final String zzasH;
        final int zzasI;

        Entry(int i, String str, int i2) {
            this.versionCode = i;
            this.zzasH = str;
            this.zzasI = i2;
        }

        Entry(String str, int i) {
            this.versionCode = 1;
            this.zzasH = str;
            this.zzasI = i;
        }

        public void writeToParcel(Parcel parcel, int i) {
            zzc zzc = CREATOR;
            zzc.zza(this, parcel, i);
        }
    }

    public StringToIntConverter() {
        this.mVersionCode = 1;
        this.zzasE = new HashMap();
        this.zzasF = new HashMap();
        this.zzasG = null;
    }

    StringToIntConverter(int i, ArrayList<Entry> arrayList) {
        this.mVersionCode = i;
        this.zzasE = new HashMap();
        this.zzasF = new HashMap();
        this.zzasG = null;
        zzd((ArrayList) arrayList);
    }

    private void zzd(ArrayList<Entry> arrayList) {
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            Entry entry = (Entry) it.next();
            zzh(entry.zzasH, entry.zzasI);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public int getVersionCode() {
        return this.mVersionCode;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzb zzb = CREATOR;
        zzb.zza(this, parcel, i);
    }

    /* renamed from: zzd */
    public String convertBack(Integer num) {
        String str = (String) this.zzasF.get(num);
        return (str == null && this.zzasE.containsKey("gms_unknown")) ? "gms_unknown" : str;
    }

    public StringToIntConverter zzh(String str, int i) {
        this.zzasE.put(str, Integer.valueOf(i));
        this.zzasF.put(Integer.valueOf(i), str);
        return this;
    }

    /* Access modifiers changed, original: 0000 */
    public ArrayList<Entry> zzuc() {
        ArrayList arrayList = new ArrayList();
        for (String str : this.zzasE.keySet()) {
            arrayList.add(new Entry(str, ((Integer) this.zzasE.get(str)).intValue()));
        }
        return arrayList;
    }

    public int zzud() {
        return 7;
    }

    public int zzue() {
        return 0;
    }
}
