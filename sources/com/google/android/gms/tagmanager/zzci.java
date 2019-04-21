package com.google.android.gms.tagmanager;

import android.net.Uri;
import com.facebook.stetho.common.Utf8Charset;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

class zzci {
    private static zzci zzbqb;
    private volatile String zzbnR;
    private volatile zza zzbqc;
    private volatile String zzbqd;
    private volatile String zzbqe;

    enum zza {
        NONE,
        CONTAINER,
        CONTAINER_DEBUG
    }

    zzci() {
        clear();
    }

    static zzci zzKh() {
        zzci zzci;
        synchronized (zzci.class) {
            if (zzbqb == null) {
                zzbqb = new zzci();
            }
            zzci = zzbqb;
        }
        return zzci;
    }

    private String zzgE(String str) {
        return str.split("&")[0].split("=")[1];
    }

    private String zzs(Uri uri) {
        return uri.getQuery().replace("&gtm_debug=x", "");
    }

    /* Access modifiers changed, original: 0000 */
    public void clear() {
        this.zzbqc = zza.NONE;
        this.zzbqd = null;
        this.zzbnR = null;
        this.zzbqe = null;
    }

    /* Access modifiers changed, original: 0000 */
    public String getContainerId() {
        return this.zzbnR;
    }

    /* Access modifiers changed, original: 0000 */
    public zza zzKi() {
        return this.zzbqc;
    }

    /* Access modifiers changed, original: 0000 */
    public String zzKj() {
        return this.zzbqd;
    }

    /* Access modifiers changed, original: declared_synchronized */
    public synchronized boolean zzr(Uri uri) {
        boolean z = true;
        synchronized (this) {
            try {
                String decode = URLDecoder.decode(uri.toString(), Utf8Charset.NAME);
                String str;
                String valueOf;
                if (decode.matches("^tagmanager.c.\\S+:\\/\\/preview\\/p\\?id=\\S+&gtm_auth=\\S+&gtm_preview=\\d+(&gtm_debug=x)?$")) {
                    str = "Container preview url: ";
                    valueOf = String.valueOf(decode);
                    zzbn.m7502v(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
                    if (decode.matches(".*?&gtm_debug=x$")) {
                        this.zzbqc = zza.CONTAINER_DEBUG;
                    } else {
                        this.zzbqc = zza.CONTAINER;
                    }
                    this.zzbqe = zzs(uri);
                    if (this.zzbqc == zza.CONTAINER || this.zzbqc == zza.CONTAINER_DEBUG) {
                        decode = String.valueOf("/r?");
                        valueOf = String.valueOf(this.zzbqe);
                        this.zzbqd = valueOf.length() != 0 ? decode.concat(valueOf) : new String(decode);
                    }
                    this.zzbnR = zzgE(this.zzbqe);
                } else if (!decode.matches("^tagmanager.c.\\S+:\\/\\/preview\\/p\\?id=\\S+&gtm_preview=$")) {
                    str = "Invalid preview uri: ";
                    String valueOf2 = String.valueOf(decode);
                    zzbn.zzaW(valueOf2.length() != 0 ? str.concat(valueOf2) : new String(str));
                    z = false;
                } else if (zzgE(uri.getQuery()).equals(this.zzbnR)) {
                    decode = "Exit preview mode for container: ";
                    valueOf = String.valueOf(this.zzbnR);
                    zzbn.m7502v(valueOf.length() != 0 ? decode.concat(valueOf) : new String(decode));
                    this.zzbqc = zza.NONE;
                    this.zzbqd = null;
                } else {
                    z = false;
                }
            } catch (UnsupportedEncodingException e) {
                z = false;
            }
        }
        return z;
    }
}
