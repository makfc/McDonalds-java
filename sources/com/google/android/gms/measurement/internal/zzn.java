package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.support.p000v4.widget.ExploreByTouchHelper;
import android.text.TextUtils;
import com.facebook.internal.AnalyticsEvents;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.util.zze;
import com.google.android.gms.internal.zzom;
import java.io.ByteArrayInputStream;
import java.security.MessageDigest;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import javax.security.auth.x500.X500Principal;

public class zzn extends zzaa {
    private static final X500Principal zzbcU = new X500Principal("CN=Android Debug,O=Android,C=US");
    private String zzPx;
    private String zzVd;
    private String zzVe;
    private String zzbbr;
    private String zzbby;
    private int zzbcV;
    private long zzbcW;

    zzn(zzx zzx) {
        super(zzx);
    }

    public /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    /* Access modifiers changed, original: 0000 */
    public String zzEb() {
        zzma();
        return this.zzbbr;
    }

    /* Access modifiers changed, original: 0000 */
    public String zzEh() {
        zzma();
        return this.zzbby;
    }

    /* Access modifiers changed, original: 0000 */
    public long zzEi() {
        return zzFo().zzEi();
    }

    /* Access modifiers changed, original: 0000 */
    public long zzEj() {
        zzma();
        return this.zzbcW;
    }

    /* Access modifiers changed, original: 0000 */
    public int zzFC() {
        zzma();
        return this.zzbcV;
    }

    /* Access modifiers changed, original: 0000 */
    public boolean zzFD() {
        try {
            PackageInfo packageInfo = getContext().getPackageManager().getPackageInfo(getContext().getPackageName(), 64);
            if (!(packageInfo == null || packageInfo.signatures == null || packageInfo.signatures.length <= 0)) {
                return ((X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(packageInfo.signatures[0].toByteArray()))).getSubjectX500Principal().equals(zzbcU);
            }
        } catch (CertificateException e) {
            zzFm().zzFE().zzj("Error obtaining certificate", e);
        } catch (NameNotFoundException e2) {
            zzFm().zzFE().zzj("Package name not found", e2);
        }
        return true;
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

    /* Access modifiers changed, original: protected */
    public void zzbl(Status status) {
        if (status == null) {
            zzFm().zzFE().log("GoogleService failed to initialize (no status)");
        } else {
            zzFm().zzFE().zze("GoogleService failed to initialize, status", Integer.valueOf(status.getStatusCode()), status.getStatusMessage());
        }
    }

    /* Access modifiers changed, original: 0000 */
    public AppMetadata zzfs(String str) {
        return new AppMetadata(zziC(), zzEb(), zzkV(), (long) zzFC(), zzEh(), zzEi(), zzEj(), str, this.zzbbl.isEnabled(), !zzFn().zzbdU, zzFn().zzEd());
    }

    /* Access modifiers changed, original: 0000 */
    public String zziC() {
        zzma();
        return this.zzPx;
    }

    public /* bridge */ /* synthetic */ void zzkN() {
        super.zzkN();
    }

    /* Access modifiers changed, original: protected */
    public void zzkO() {
        String str = AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN;
        int i = ExploreByTouchHelper.INVALID_ID;
        String str2 = AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN;
        PackageManager packageManager = getContext().getPackageManager();
        String packageName = getContext().getPackageName();
        String installerPackageName = packageManager.getInstallerPackageName(packageName);
        if (installerPackageName == null) {
            installerPackageName = "manual_install";
        } else if ("com.android.vending".equals(installerPackageName)) {
            installerPackageName = "";
        }
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(getContext().getPackageName(), 0);
            if (packageInfo != null) {
                CharSequence applicationLabel = packageManager.getApplicationLabel(packageInfo.applicationInfo);
                if (!TextUtils.isEmpty(applicationLabel)) {
                    str2 = applicationLabel.toString();
                }
                str = packageInfo.versionName;
                i = packageInfo.versionCode;
            }
        } catch (NameNotFoundException e) {
            zzFm().zzFE().zzj("Error retrieving package info: appName", str2);
        }
        this.zzPx = packageName;
        this.zzbby = installerPackageName;
        this.zzVe = str;
        this.zzbcV = i;
        this.zzVd = str2;
        MessageDigest zzbZ = zzal.zzbZ("MD5");
        if (zzbZ == null) {
            zzFm().zzFE().log("Could not get MD5 instance");
            this.zzbcW = -1;
        } else {
            this.zzbcW = 0;
            try {
                if (!zzFD()) {
                    PackageInfo packageInfo2 = packageManager.getPackageInfo(getContext().getPackageName(), 64);
                    if (packageInfo2.signatures != null && packageInfo2.signatures.length > 0) {
                        this.zzbcW = zzal.zzs(zzbZ.digest(packageInfo2.signatures[0].toByteArray()));
                    }
                }
            } catch (NameNotFoundException e2) {
                zzFm().zzFE().zzj("Package name not found", e2);
            }
        }
        Status zzc = zzFo().zzmW() ? zzom.zzc(getContext(), "-", true) : zzom.zzas(getContext());
        boolean z = zzc != null && zzc.isSuccess();
        if (!z) {
            zzbl(zzc);
        }
        if (z) {
            Boolean zzEN = zzFo().zzEN();
            if (zzFo().zzEM()) {
                zzFm().zzFJ().log("Collection disabled with firebase_analytics_collection_deactivated=1");
                z = false;
            } else if (zzEN != null && !zzEN.booleanValue()) {
                zzFm().zzFJ().log("Collection disabled with firebase_analytics_collection_enabled=0");
                z = false;
            } else if (zzEN == null && zzFo().zzsB()) {
                zzFm().zzFJ().log("Collection disabled with google_app_measurement_enable=0");
                z = false;
            } else {
                zzFm().zzFL().log("Collection enabled");
                z = true;
            }
        } else {
            z = false;
        }
        this.zzbbr = "";
        if (!zzFo().zzmW()) {
            try {
                String zzsA = zzom.zzsA();
                if (TextUtils.isEmpty(zzsA)) {
                    zzsA = "";
                }
                this.zzbbr = zzsA;
                if (z) {
                    zzFm().zzFL().zze("App package, google app id", this.zzPx, this.zzbbr);
                }
            } catch (IllegalStateException e3) {
                zzFm().zzFE().zzj("getGoogleAppId or isMeasurementEnabled failed with exception", e3);
            }
        }
    }

    /* Access modifiers changed, original: 0000 */
    public String zzkV() {
        zzma();
        return this.zzVe;
    }

    public /* bridge */ /* synthetic */ void zzlP() {
        super.zzlP();
    }

    public /* bridge */ /* synthetic */ zze zzlQ() {
        return super.zzlQ();
    }
}
