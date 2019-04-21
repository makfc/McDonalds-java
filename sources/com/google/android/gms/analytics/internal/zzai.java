package com.google.android.gms.analytics.internal;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import android.util.Pair;
import com.google.android.gms.common.internal.zzaa;
import java.util.UUID;

public class zzai extends zzd {
    private SharedPreferences zzYX;
    private long zzYY;
    private long zzYZ = -1;
    private final zza zzZa = new zza("monitoring", zzlS().zznC());

    public final class zza {
        private final String mName;
        private final long zzZb;

        private zza(String str, long j) {
            zzaa.zzdl(str);
            zzaa.zzaj(j > 0);
            this.mName = str;
            this.zzZb = j;
        }

        private void zzon() {
            long currentTimeMillis = zzai.this.zzlQ().currentTimeMillis();
            Editor edit = zzai.this.zzYX.edit();
            edit.remove(zzos());
            edit.remove(zzot());
            edit.putLong(zzor(), currentTimeMillis);
            edit.commit();
        }

        private long zzoo() {
            long zzoq = zzoq();
            return zzoq == 0 ? 0 : Math.abs(zzoq - zzai.this.zzlQ().currentTimeMillis());
        }

        private long zzoq() {
            return zzai.this.zzYX.getLong(zzor(), 0);
        }

        private String zzor() {
            return String.valueOf(this.mName).concat(":start");
        }

        private String zzos() {
            return String.valueOf(this.mName).concat(":count");
        }

        public void zzbU(String str) {
            if (zzoq() == 0) {
                zzon();
            }
            if (str == null) {
                str = "";
            }
            synchronized (this) {
                long j = zzai.this.zzYX.getLong(zzos(), 0);
                if (j <= 0) {
                    Editor edit = zzai.this.zzYX.edit();
                    edit.putString(zzot(), str);
                    edit.putLong(zzos(), 1);
                    edit.apply();
                    return;
                }
                Object obj = (UUID.randomUUID().getLeastSignificantBits() & Long.MAX_VALUE) < Long.MAX_VALUE / (j + 1) ? 1 : null;
                Editor edit2 = zzai.this.zzYX.edit();
                if (obj != null) {
                    edit2.putString(zzot(), str);
                }
                edit2.putLong(zzos(), j + 1);
                edit2.apply();
            }
        }

        public Pair<String, Long> zzop() {
            long zzoo = zzoo();
            if (zzoo < this.zzZb) {
                return null;
            }
            if (zzoo > this.zzZb * 2) {
                zzon();
                return null;
            }
            String string = zzai.this.zzYX.getString(zzot(), null);
            zzoo = zzai.this.zzYX.getLong(zzos(), 0);
            zzon();
            return (string == null || zzoo <= 0) ? null : new Pair(string, Long.valueOf(zzoo));
        }

        /* Access modifiers changed, original: protected */
        public String zzot() {
            return String.valueOf(this.mName).concat(":value");
        }
    }

    protected zzai(zzf zzf) {
        super(zzf);
    }

    public void zzbT(String str) {
        zzkN();
        zzma();
        Editor edit = this.zzYX.edit();
        if (TextUtils.isEmpty(str)) {
            edit.remove("installation_campaign");
        } else {
            edit.putString("installation_campaign", str);
        }
        if (!edit.commit()) {
            zzbJ("Failed to commit campaign data");
        }
    }

    /* Access modifiers changed, original: protected */
    public void zzkO() {
        this.zzYX = getContext().getSharedPreferences("com.google.android.gms.analytics.prefs", 0);
    }

    public long zzoh() {
        zzkN();
        zzma();
        if (this.zzYY == 0) {
            long j = this.zzYX.getLong("first_run", 0);
            if (j != 0) {
                this.zzYY = j;
            } else {
                j = zzlQ().currentTimeMillis();
                Editor edit = this.zzYX.edit();
                edit.putLong("first_run", j);
                if (!edit.commit()) {
                    zzbJ("Failed to commit first run time");
                }
                this.zzYY = j;
            }
        }
        return this.zzYY;
    }

    public zzal zzoi() {
        return new zzal(zzlQ(), zzoh());
    }

    public long zzoj() {
        zzkN();
        zzma();
        if (this.zzYZ == -1) {
            this.zzYZ = this.zzYX.getLong("last_dispatch", 0);
        }
        return this.zzYZ;
    }

    public void zzok() {
        zzkN();
        zzma();
        long currentTimeMillis = zzlQ().currentTimeMillis();
        Editor edit = this.zzYX.edit();
        edit.putLong("last_dispatch", currentTimeMillis);
        edit.apply();
        this.zzYZ = currentTimeMillis;
    }

    public String zzol() {
        zzkN();
        zzma();
        String string = this.zzYX.getString("installation_campaign", null);
        return TextUtils.isEmpty(string) ? null : string;
    }

    public zza zzom() {
        return this.zzZa;
    }
}
