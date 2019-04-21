package com.google.android.gms.common.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.autonavi.amap.mapcore.MapTilsCacheAndResManager;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class zzd<T extends SafeParcelable> extends AbstractDataBuffer<T> {
    private static final String[] zzapc = new String[]{MapTilsCacheAndResManager.AUTONAVI_DATA_PATH};
    private final Creator<T> zzapd;

    public zzd(DataHolder dataHolder, Creator<T> creator) {
        super(dataHolder);
        this.zzapd = creator;
    }

    /* renamed from: zzbO */
    public T get(int i) {
        byte[] zzg = this.zzamz.zzg(MapTilsCacheAndResManager.AUTONAVI_DATA_PATH, i, this.zzamz.zzbP(i));
        Parcel obtain = Parcel.obtain();
        obtain.unmarshall(zzg, 0, zzg.length);
        obtain.setDataPosition(0);
        SafeParcelable safeParcelable = (SafeParcelable) this.zzapd.createFromParcel(obtain);
        obtain.recycle();
        return safeParcelable;
    }
}
