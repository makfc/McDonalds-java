package com.google.android.gms.tagmanager;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Build.VERSION;
import com.newrelic.agent.android.instrumentation.HttpInstrumentation;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Locale;

class zzde implements zzac {
    private final Context mContext;
    private final String zzCy;
    private final zzb zzbrt;
    private final zza zzbru;

    public interface zza {
        void zza(zzar zzar);

        void zzb(zzar zzar);

        void zzc(zzar zzar);
    }

    interface zzb {
        HttpURLConnection zzd(URL url) throws IOException;
    }

    /* renamed from: com.google.android.gms.tagmanager.zzde$1 */
    class C27371 implements zzb {
        C27371() {
        }

        public HttpURLConnection zzd(URL url) throws IOException {
            return (HttpURLConnection) HttpInstrumentation.openConnection(url.openConnection());
        }
    }

    zzde(Context context, zza zza) {
        this(new C27371(), context, zza);
    }

    zzde(zzb zzb, Context context, zza zza) {
        this.zzbrt = zzb;
        this.mContext = context.getApplicationContext();
        this.zzbru = zza;
        this.zzCy = zza("GoogleTagManager", "4.00", VERSION.RELEASE, zzc(Locale.getDefault()), Build.MODEL, Build.ID);
    }

    static String zzc(Locale locale) {
        if (locale == null || locale.getLanguage() == null || locale.getLanguage().length() == 0) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(locale.getLanguage().toLowerCase());
        if (!(locale.getCountry() == null || locale.getCountry().length() == 0)) {
            stringBuilder.append("-").append(locale.getCountry().toLowerCase());
        }
        return stringBuilder.toString();
    }

    public void zzI(List<zzar> list) {
        Object obj;
        Throwable th;
        InputStream inputStream;
        Object obj2;
        Throwable th2;
        Object obj3;
        int min = Math.min(list.size(), 40);
        Object obj4 = 1;
        int i = 0;
        while (i < min) {
            zzar zzar = (zzar) list.get(i);
            URL zzd = zzd(zzar);
            if (zzd == null) {
                zzbn.zzaW("No destination: discarding hit.");
                this.zzbru.zzb(zzar);
                obj = obj4;
            } else {
                try {
                    HttpURLConnection zzd2 = this.zzbrt.zzd(zzd);
                    if (obj4 != null) {
                        try {
                            zzbs.zzbq(this.mContext);
                            obj4 = null;
                        } catch (Throwable th3) {
                            th = th3;
                            inputStream = null;
                            obj2 = obj4;
                            th2 = th;
                            if (inputStream != null) {
                                try {
                                    inputStream.close();
                                } catch (IOException e) {
                                    obj3 = obj2;
                                    obj2 = e;
                                    String str = "Exception sending hit: ";
                                    String valueOf = String.valueOf(obj2.getClass().getSimpleName());
                                    zzbn.zzaW(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
                                    zzbn.zzaW(obj2.getMessage());
                                    this.zzbru.zzc(zzar);
                                    obj = obj3;
                                    i++;
                                    obj4 = obj;
                                }
                            }
                            zzd2.disconnect();
                            throw th2;
                        }
                    }
                    zzd2.setRequestProperty("User-Agent", this.zzCy);
                    int responseCode = zzd2.getResponseCode();
                    InputStream inputStream2 = zzd2.getInputStream();
                    if (responseCode != 200) {
                        try {
                            zzbn.zzaW("Bad response: " + responseCode);
                            this.zzbru.zzc(zzar);
                        } catch (Throwable th32) {
                            th = th32;
                            inputStream = inputStream2;
                            obj2 = obj4;
                            th2 = th;
                        }
                    } else {
                        this.zzbru.zza(zzar);
                    }
                    if (inputStream2 != null) {
                        inputStream2.close();
                    }
                    zzd2.disconnect();
                    obj = obj4;
                } catch (IOException e2) {
                    IOException iOException = e2;
                    obj3 = obj4;
                }
            }
            i++;
            obj4 = obj;
        }
    }

    public boolean zzJF() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.mContext.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
            return true;
        }
        zzbn.m7502v("...no network connectivity");
        return false;
    }

    /* Access modifiers changed, original: 0000 */
    public String zza(String str, String str2, String str3, String str4, String str5, String str6) {
        return String.format("%s/%s (Linux; U; Android %s; %s; %s Build/%s)", new Object[]{str, str2, str3, str4, str5, str6});
    }

    /* Access modifiers changed, original: 0000 */
    public URL zzd(zzar zzar) {
        try {
            return new URL(zzar.zzJS());
        } catch (MalformedURLException e) {
            zzbn.m7501e("Error trying to parse the GTM url.");
            return null;
        }
    }
}
