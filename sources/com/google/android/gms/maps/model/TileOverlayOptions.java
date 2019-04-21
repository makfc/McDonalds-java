package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.maps.model.internal.zzi;
import com.google.android.gms.maps.model.internal.zzi.zza;

public final class TileOverlayOptions implements SafeParcelable {
    public static final zzo CREATOR = new zzo();
    private final int mVersionCode;
    private float zzbax;
    private boolean zzbay;
    private zzi zzbbf;
    private TileProvider zzbbg;
    private boolean zzbbh;

    /* renamed from: com.google.android.gms.maps.model.TileOverlayOptions$1 */
    class C26621 implements TileProvider {
        private final zzi zzbbi = TileOverlayOptions.this.zzbbf;

        C26621() {
        }

        public Tile getTile(int i, int i2, int i3) {
            try {
                return this.zzbbi.getTile(i, i2, i3);
            } catch (RemoteException e) {
                return null;
            }
        }
    }

    /* renamed from: com.google.android.gms.maps.model.TileOverlayOptions$2 */
    class C26632 extends zza {
        final /* synthetic */ TileProvider zzbbk;

        public Tile getTile(int i, int i2, int i3) {
            return this.zzbbk.getTile(i, i2, i3);
        }
    }

    public TileOverlayOptions() {
        this.zzbay = true;
        this.zzbbh = true;
        this.mVersionCode = 1;
    }

    TileOverlayOptions(int i, IBinder iBinder, boolean z, float f, boolean z2) {
        this.zzbay = true;
        this.zzbbh = true;
        this.mVersionCode = i;
        this.zzbbf = zza.zzdu(iBinder);
        this.zzbbg = this.zzbbf == null ? null : new C26621();
        this.zzbay = z;
        this.zzbax = f;
        this.zzbbh = z2;
    }

    public int describeContents() {
        return 0;
    }

    public boolean getFadeIn() {
        return this.zzbbh;
    }

    /* Access modifiers changed, original: 0000 */
    public int getVersionCode() {
        return this.mVersionCode;
    }

    public float getZIndex() {
        return this.zzbax;
    }

    public boolean isVisible() {
        return this.zzbay;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzo.zza(this, parcel, i);
    }

    /* Access modifiers changed, original: 0000 */
    public IBinder zzDW() {
        return this.zzbbf.asBinder();
    }
}
