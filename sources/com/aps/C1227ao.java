package com.aps;

import android.telephony.CellLocation;
import android.telephony.PhoneStateListener;
import android.telephony.ServiceState;
import android.telephony.SignalStrength;

/* renamed from: com.aps.ao */
final class C1227ao extends PhoneStateListener {
    /* renamed from: a */
    private /* synthetic */ C1225am f4277a;

    private C1227ao(C1225am c1225am) {
        this.f4277a = c1225am;
    }

    /* synthetic */ C1227ao(C1225am c1225am, byte b) {
        this(c1225am);
    }

    public final void onCellLocationChanged(CellLocation cellLocation) {
        try {
            this.f4277a.f4270t = System.currentTimeMillis();
            this.f4277a.f4273x = cellLocation;
            super.onCellLocationChanged(cellLocation);
        } catch (Exception e) {
        }
    }

    public final void onServiceStateChanged(ServiceState serviceState) {
        try {
            if (serviceState.getState() == 0) {
                this.f4277a.f4261k = true;
                String[] a = C1225am.m5381b(this.f4277a.f4252b);
                this.f4277a.f4265o = Integer.parseInt(a[0]);
                this.f4277a.f4266p = Integer.parseInt(a[1]);
            } else {
                this.f4277a.f4261k = false;
            }
            super.onServiceStateChanged(serviceState);
        } catch (Exception e) {
        }
    }

    public final void onSignalStrengthsChanged(SignalStrength signalStrength) {
        try {
            if (this.f4277a.f4259i) {
                this.f4277a.f4260j = signalStrength.getCdmaDbm();
            } else {
                this.f4277a.f4260j = signalStrength.getGsmSignalStrength();
                if (this.f4277a.f4260j == 99) {
                    this.f4277a.f4260j = -1;
                } else {
                    this.f4277a.f4260j = (this.f4277a.f4260j * 2) - 113;
                }
            }
            super.onSignalStrengthsChanged(signalStrength);
        } catch (Exception e) {
        }
    }
}
