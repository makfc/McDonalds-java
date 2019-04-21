package com.google.android.gms.tagmanager;

import android.content.Context;
import android.content.res.Resources.NotFoundException;
import com.facebook.stetho.common.Utf8Charset;
import com.google.android.gms.internal.zzabr.zza;
import com.google.android.gms.internal.zzabt;
import com.google.android.gms.internal.zzabt.zzc;
import com.google.android.gms.internal.zzabt.zzg;
import com.google.android.gms.internal.zzaf.zzf;
import com.google.android.gms.internal.zzami;
import com.google.android.gms.internal.zzamj;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONException;

class zzcu implements zzf {
    private final Context mContext;
    private final String zzbnR;
    private zzbm<zza> zzbqr;
    private final ExecutorService zzbqx = Executors.newSingleThreadExecutor();

    /* renamed from: com.google.android.gms.tagmanager.zzcu$1 */
    class C27271 implements Runnable {
        C27271() {
        }

        public void run() {
            zzcu.this.zzKp();
        }
    }

    zzcu(Context context, String str) {
        this.mContext = context;
        this.zzbnR = str;
    }

    private zzc zza(ByteArrayOutputStream byteArrayOutputStream) {
        zzc zzc = null;
        try {
            return zzbg.zzgC(byteArrayOutputStream.toString(Utf8Charset.NAME));
        } catch (UnsupportedEncodingException e) {
            zzbn.zzaU("Failed to convert binary resource to string for JSON parsing; the file format is not UTF-8 format.");
            return zzc;
        } catch (JSONException e2) {
            zzbn.zzaW("Failed to extract the container from the resource file. Resource is a UTF-8 encoded string but doesn't contain a JSON container");
            return zzc;
        }
    }

    private void zzd(zza zza) throws IllegalArgumentException {
        if (zza.zzjG == null && zza.zzbwa == null) {
            throw new IllegalArgumentException("Resource and SupplementedResource are NULL.");
        }
    }

    private zzc zzz(byte[] bArr) {
        try {
            zzc zzb = zzabt.zzb(zzf.zzc(bArr));
            if (zzb == null) {
                return zzb;
            }
            zzbn.m7502v("The container was successfully loaded from the resource (using binary file)");
            return zzb;
        } catch (zzami e) {
            zzbn.m7501e("The resource file is corrupted. The container cannot be extracted from the binary file");
            return null;
        } catch (zzg e2) {
            zzbn.zzaW("The resource file is invalid. The container from the binary file is invalid");
            return null;
        }
    }

    public synchronized void release() {
        this.zzbqx.shutdown();
    }

    public void zzJu() {
        this.zzbqx.execute(new C27271());
    }

    /* Access modifiers changed, original: 0000 */
    public void zzKp() {
        if (this.zzbqr == null) {
            throw new IllegalStateException("Callback must be set before execute");
        }
        this.zzbqr.zzJt();
        zzbn.m7502v("Attempting to load resource from disk");
        if ((zzci.zzKh().zzKi() == zza.CONTAINER || zzci.zzKh().zzKi() == zza.CONTAINER_DEBUG) && this.zzbnR.equals(zzci.zzKh().getContainerId())) {
            this.zzbqr.zza(zzbm.zza.NOT_AVAILABLE);
            return;
        }
        try {
            FileInputStream fileInputStream = new FileInputStream(zzKq());
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                zzabt.zzc(fileInputStream, byteArrayOutputStream);
                zza zzD = zza.zzD(byteArrayOutputStream.toByteArray());
                zzd(zzD);
                this.zzbqr.onSuccess(zzD);
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    zzbn.zzaW("Error closing stream for reading resource from disk");
                }
            } catch (IOException e2) {
                this.zzbqr.zza(zzbm.zza.IO_ERROR);
                zzbn.zzaW("Failed to read the resource from disk");
                try {
                    fileInputStream.close();
                } catch (IOException e3) {
                    zzbn.zzaW("Error closing stream for reading resource from disk");
                }
            } catch (IllegalArgumentException e4) {
                this.zzbqr.zza(zzbm.zza.IO_ERROR);
                zzbn.zzaW("Failed to read the resource from disk. The resource is inconsistent");
                try {
                    fileInputStream.close();
                } catch (IOException e5) {
                    zzbn.zzaW("Error closing stream for reading resource from disk");
                }
            } catch (Throwable th) {
                try {
                    fileInputStream.close();
                } catch (IOException e6) {
                    zzbn.zzaW("Error closing stream for reading resource from disk");
                }
                throw th;
            }
            zzbn.m7502v("The Disk resource was successfully read.");
        } catch (FileNotFoundException e7) {
            zzbn.zzaU("Failed to find the resource in the disk");
            this.zzbqr.zza(zzbm.zza.NOT_AVAILABLE);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public File zzKq() {
        String valueOf = String.valueOf("resource_");
        String valueOf2 = String.valueOf(this.zzbnR);
        return new File(this.mContext.getDir("google_tagmanager", 0), valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf));
    }

    public void zza(zzbm<zza> zzbm) {
        this.zzbqr = zzbm;
    }

    public void zzb(final zza zza) {
        this.zzbqx.execute(new Runnable() {
            public void run() {
                zzcu.this.zzc(zza);
            }
        });
    }

    /* Access modifiers changed, original: 0000 */
    public boolean zzc(zza zza) {
        File zzKq = zzKq();
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(zzKq);
            try {
                fileOutputStream.write(zzamj.toByteArray(zza));
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    zzbn.zzaW("error closing stream for writing resource to disk");
                }
                return true;
            } catch (IOException e2) {
                zzbn.zzaW("Error writing resource to disk. Removing resource from disk.");
                zzKq.delete();
                try {
                    fileOutputStream.close();
                    return false;
                } catch (IOException e3) {
                    zzbn.zzaW("error closing stream for writing resource to disk");
                    return false;
                }
            } catch (Throwable th) {
                try {
                    fileOutputStream.close();
                } catch (IOException e4) {
                    zzbn.zzaW("error closing stream for writing resource to disk");
                }
                throw th;
            }
        } catch (FileNotFoundException e5) {
            zzbn.m7501e("Error opening resource file for writing");
            return false;
        }
    }

    public zzc zzkN(int i) {
        try {
            InputStream openRawResource = this.mContext.getResources().openRawResource(i);
            String valueOf = String.valueOf(this.mContext.getResources().getResourceName(i));
            zzbn.m7502v(new StringBuilder(String.valueOf(valueOf).length() + 66).append("Attempting to load a container from the resource ID ").append(i).append(" (").append(valueOf).append(")").toString());
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                zzabt.zzc(openRawResource, byteArrayOutputStream);
                zzc zza = zza(byteArrayOutputStream);
                if (zza == null) {
                    return zzz(byteArrayOutputStream.toByteArray());
                }
                zzbn.m7502v("The container was successfully loaded from the resource (using JSON file format)");
                return zza;
            } catch (IOException e) {
                String valueOf2 = String.valueOf(this.mContext.getResources().getResourceName(i));
                zzbn.zzaW(new StringBuilder(String.valueOf(valueOf2).length() + 67).append("Error reading the default container with resource ID ").append(i).append(" (").append(valueOf2).append(")").toString());
                return null;
            }
        } catch (NotFoundException e2) {
            zzbn.zzaW("Failed to load the container. No default container resource found with the resource ID " + i);
            return null;
        }
    }
}
