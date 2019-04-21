package com.google.android.gms.tagmanager;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.google.android.gms.internal.zzabt;
import com.google.android.gms.internal.zzabw;
import com.google.android.gms.internal.zzabx;
import com.google.android.gms.internal.zzaf.zzj;
import com.google.android.gms.tagmanager.zzbm.zza;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

class zzcs implements Runnable {
    private final Context mContext;
    private final String zzbnR;
    private volatile String zzboo;
    private final zzabx zzbqp;
    private final String zzbqq;
    private zzbm<zzj> zzbqr;
    private volatile zzs zzbqs;
    private volatile String zzbqt;

    zzcs(Context context, String str, zzabx zzabx, zzs zzs) {
        this.mContext = context;
        this.zzbqp = zzabx;
        this.zzbnR = str;
        this.zzbqs = zzs;
        String valueOf = String.valueOf("/r?id=");
        String valueOf2 = String.valueOf(str);
        this.zzbqq = valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
        this.zzboo = this.zzbqq;
        this.zzbqt = null;
    }

    public zzcs(Context context, String str, zzs zzs) {
        this(context, str, new zzabx(), zzs);
    }

    private boolean zzKk() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.mContext.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
            return true;
        }
        zzbn.m7502v("...no network connectivity");
        return false;
    }

    private void zzKl() {
        String valueOf;
        if (zzKk()) {
            zzbn.m7502v("Start loading resource from network ...");
            String zzKm = zzKm();
            zzabw zzMM = this.zzbqp.zzMM();
            String valueOf2;
            try {
                InputStream zzho = zzMM.zzho(zzKm);
                try {
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    zzabt.zzc(zzho, byteArrayOutputStream);
                    zzj zzd = zzj.zzd(byteArrayOutputStream.toByteArray());
                    valueOf2 = String.valueOf(zzd);
                    zzbn.m7502v(new StringBuilder(String.valueOf(valueOf2).length() + 43).append("Successfully loaded supplemented resource: ").append(valueOf2).toString());
                    if (zzd.zzjG == null && zzd.zzjF.length == 0) {
                        String str = "No change for container: ";
                        valueOf2 = String.valueOf(this.zzbnR);
                        zzbn.m7502v(valueOf2.length() != 0 ? str.concat(valueOf2) : new String(str));
                    }
                    this.zzbqr.onSuccess(zzd);
                    zzbn.m7502v("Load resource from network finished.");
                } catch (IOException e) {
                    valueOf = String.valueOf(e.getMessage());
                    zzbn.zzd(new StringBuilder((String.valueOf(zzKm).length() + 51) + String.valueOf(valueOf).length()).append("Error when parsing downloaded resources from url: ").append(zzKm).append(" ").append(valueOf).toString(), e);
                    this.zzbqr.zza(zza.SERVER_ERROR);
                    zzMM.close();
                }
            } catch (FileNotFoundException e2) {
                valueOf2 = this.zzbnR;
                zzbn.zzaW(new StringBuilder((String.valueOf(zzKm).length() + 79) + String.valueOf(valueOf2).length()).append("No data is retrieved from the given url: ").append(zzKm).append(". Make sure container_id: ").append(valueOf2).append(" is correct.").toString());
                this.zzbqr.zza(zza.SERVER_ERROR);
            } catch (IOException e3) {
                valueOf = String.valueOf(e3.getMessage());
                zzbn.zzd(new StringBuilder((String.valueOf(zzKm).length() + 40) + String.valueOf(valueOf).length()).append("Error when loading resources from url: ").append(zzKm).append(" ").append(valueOf).toString(), e3);
                this.zzbqr.zza(zza.IO_ERROR);
            } finally {
                zzMM.close();
            }
        } else {
            this.zzbqr.zza(zza.NOT_AVAILABLE);
        }
    }

    public void run() {
        if (this.zzbqr == null) {
            throw new IllegalStateException("callback must be set before execute");
        }
        this.zzbqr.zzJt();
        zzKl();
    }

    /* Access modifiers changed, original: 0000 */
    public String zzKm() {
        String valueOf = String.valueOf(this.zzbqs.zzJv());
        String str = this.zzboo;
        String valueOf2 = String.valueOf("&v=a65833898");
        Object stringBuilder = new StringBuilder(((String.valueOf(valueOf).length() + 0) + String.valueOf(str).length()) + String.valueOf(valueOf2).length()).append(valueOf).append(str).append(valueOf2).toString();
        if (!(this.zzbqt == null || this.zzbqt.trim().equals(""))) {
            valueOf = String.valueOf(stringBuilder);
            str = String.valueOf("&pv=");
            valueOf2 = this.zzbqt;
            stringBuilder = new StringBuilder(((String.valueOf(valueOf).length() + 0) + String.valueOf(str).length()) + String.valueOf(valueOf2).length()).append(valueOf).append(str).append(valueOf2).toString();
        }
        if (!zzci.zzKh().zzKi().equals(zza.CONTAINER_DEBUG)) {
            return stringBuilder;
        }
        str = String.valueOf(stringBuilder);
        valueOf = String.valueOf("&gtm_debug=x");
        return valueOf.length() != 0 ? str.concat(valueOf) : new String(str);
    }

    /* Access modifiers changed, original: 0000 */
    public void zza(zzbm<zzj> zzbm) {
        this.zzbqr = zzbm;
    }

    /* Access modifiers changed, original: 0000 */
    public void zzgF(String str) {
        String str2 = "Setting previous container version: ";
        String valueOf = String.valueOf(str);
        zzbn.zzaU(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
        this.zzbqt = str;
    }

    /* Access modifiers changed, original: 0000 */
    public void zzgq(String str) {
        if (str == null) {
            this.zzboo = this.zzbqq;
            return;
        }
        String str2 = "Setting CTFE URL path: ";
        String valueOf = String.valueOf(str);
        zzbn.zzaU(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
        this.zzboo = str;
    }
}
