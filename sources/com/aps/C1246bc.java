package com.aps;

import android.telephony.CellLocation;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;

/* renamed from: com.aps.bc */
public final class C1246bc {
    /* renamed from: a */
    int f4360a = Integer.MAX_VALUE;
    /* renamed from: b */
    int f4361b = Integer.MAX_VALUE;
    /* renamed from: c */
    int f4362c = Integer.MAX_VALUE;
    /* renamed from: d */
    int f4363d = Integer.MAX_VALUE;
    /* renamed from: e */
    int f4364e = Integer.MAX_VALUE;

    C1246bc(CellLocation cellLocation) {
        if (cellLocation == null) {
            return;
        }
        if (cellLocation instanceof GsmCellLocation) {
            GsmCellLocation gsmCellLocation = (GsmCellLocation) cellLocation;
            this.f4364e = gsmCellLocation.getCid();
            this.f4363d = gsmCellLocation.getLac();
        } else if (cellLocation instanceof CdmaCellLocation) {
            CdmaCellLocation cdmaCellLocation = (CdmaCellLocation) cellLocation;
            this.f4362c = cdmaCellLocation.getBaseStationId();
            this.f4361b = cdmaCellLocation.getNetworkId();
            this.f4360a = cdmaCellLocation.getSystemId();
        }
    }
}
