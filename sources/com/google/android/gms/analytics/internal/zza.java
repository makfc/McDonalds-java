package com.google.android.gms.analytics.internal;

import android.text.TextUtils;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Locale;

public class zza extends zzd {
    public static boolean zzVS;
    private Info zzVT;
    private final zzal zzVU;
    private String zzVV;
    private boolean zzVW = false;
    private Object zzVX = new Object();

    zza(zzf zzf) {
        super(zzf);
        this.zzVU = new zzal(zzf.zzlQ());
    }

    private boolean zza(Info info, Info info2) {
        Object obj = null;
        CharSequence id = info2 == null ? null : info2.getId();
        if (TextUtils.isEmpty(id)) {
            return true;
        }
        String zzmP = zzlW().zzmP();
        synchronized (this.zzVX) {
            String valueOf;
            String valueOf2;
            boolean zzbF;
            String valueOf3;
            if (!this.zzVW) {
                this.zzVV = zzlH();
                this.zzVW = true;
            } else if (TextUtils.isEmpty(this.zzVV)) {
                if (info != null) {
                    obj = info.getId();
                }
                if (obj == null) {
                    valueOf = String.valueOf(id);
                    valueOf2 = String.valueOf(zzmP);
                    zzbF = zzbF(valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf));
                    return zzbF;
                }
                valueOf3 = String.valueOf(obj);
                valueOf = String.valueOf(zzmP);
                this.zzVV = zzbE(valueOf.length() != 0 ? valueOf3.concat(valueOf) : new String(valueOf3));
            }
            valueOf3 = String.valueOf(id);
            valueOf = String.valueOf(zzmP);
            valueOf = zzbE(valueOf.length() != 0 ? valueOf3.concat(valueOf) : new String(valueOf3));
            if (TextUtils.isEmpty(valueOf)) {
                return false;
            } else if (valueOf.equals(this.zzVV)) {
                return true;
            } else {
                if (TextUtils.isEmpty(this.zzVV)) {
                    valueOf = zzmP;
                } else {
                    zzbG("Resetting the client id because Advertising Id changed.");
                    obj = zzlW().zzmQ();
                    zza("New client Id", obj);
                }
                String valueOf4 = String.valueOf(id);
                valueOf2 = String.valueOf(obj);
                zzbF = zzbF(valueOf2.length() != 0 ? valueOf4.concat(valueOf2) : new String(valueOf4));
                return zzbF;
            }
        }
    }

    private static String zzbE(String str) {
        if (zzao.zzbZ("MD5") == null) {
            return null;
        }
        return String.format(Locale.US, "%032X", new Object[]{new BigInteger(1, zzao.zzbZ("MD5").digest(str.getBytes()))});
    }

    private boolean zzbF(String str) {
        try {
            String zzbE = zzbE(str);
            zzbG("Storing hashed adid.");
            FileOutputStream openFileOutput = getContext().openFileOutput("gaClientIdData", 0);
            openFileOutput.write(zzbE.getBytes());
            openFileOutput.close();
            this.zzVV = zzbE;
            return true;
        } catch (IOException e) {
            zze("Error creating hash file", e);
            return false;
        }
    }

    private synchronized Info zzlF() {
        if (this.zzVU.zzx(1000)) {
            this.zzVU.start();
            Info zzlG = zzlG();
            if (zza(this.zzVT, zzlG)) {
                this.zzVT = zzlG;
            } else {
                zzbK("Failed to reset client id on adid change. Not using adid");
                this.zzVT = new Info("", false);
            }
        }
        return this.zzVT;
    }

    /* Access modifiers changed, original: protected */
    public void zzkO() {
    }

    public String zzlE() {
        zzma();
        Info zzlF = zzlF();
        CharSequence id = zzlF != null ? zzlF.getId() : null;
        return TextUtils.isEmpty(id) ? null : id;
    }

    /* Access modifiers changed, original: protected */
    public Info zzlG() {
        Info info = null;
        try {
            return AdvertisingIdClient.getAdvertisingIdInfo(getContext());
        } catch (IllegalStateException e) {
            zzbJ("IllegalStateException getting Ad Id Info. If you would like to see Audience reports, please ensure that you have added '<meta-data android:name=\"com.google.android.gms.version\" android:value=\"@integer/google_play_services_version\" />' to your application manifest file. See http://goo.gl/naFqQk for details.");
            return info;
        } catch (Throwable th) {
            if (zzVS) {
                return info;
            }
            zzVS = true;
            zzd("Error getting advertiser id", th);
            return info;
        }
    }

    /* Access modifiers changed, original: protected */
    public String zzlH() {
        Object e;
        String str = null;
        try {
            FileInputStream openFileInput = getContext().openFileInput("gaClientIdData");
            byte[] bArr = new byte[128];
            int read = openFileInput.read(bArr, 0, 128);
            if (openFileInput.available() > 0) {
                zzbJ("Hash file seems corrupted, deleting it.");
                openFileInput.close();
                getContext().deleteFile("gaClientIdData");
                return null;
            } else if (read <= 0) {
                zzbG("Hash file is empty.");
                openFileInput.close();
                return null;
            } else {
                String str2 = new String(bArr, 0, read);
                try {
                    openFileInput.close();
                    return str2;
                } catch (FileNotFoundException e2) {
                    return str2;
                } catch (IOException e3) {
                    IOException iOException = e3;
                    str = str2;
                    IOException e4 = iOException;
                    zzd("Error reading Hash file, deleting it", e4);
                    getContext().deleteFile("gaClientIdData");
                    return str;
                }
            }
        } catch (FileNotFoundException e5) {
            return null;
        } catch (IOException e6) {
            e4 = e6;
            zzd("Error reading Hash file, deleting it", e4);
            getContext().deleteFile("gaClientIdData");
            return str;
        }
    }

    public boolean zzlt() {
        zzma();
        Info zzlF = zzlF();
        return (zzlF == null || zzlF.isLimitAdTrackingEnabled()) ? false : true;
    }
}
