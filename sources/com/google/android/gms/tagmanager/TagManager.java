package com.google.android.gms.tagmanager;

import android.annotation.TargetApi;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Looper;
import android.support.annotation.RawRes;
import android.support.annotation.RequiresPermission;
import com.google.android.gms.common.api.PendingResult;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class TagManager {
    private static TagManager zzbry;
    private final Context mContext;
    private final DataLayer zzbnS;
    private final zzs zzbqs;
    private final zza zzbrv;
    private final zzda zzbrw;
    private final ConcurrentMap<zzo, Boolean> zzbrx;

    /* renamed from: com.google.android.gms.tagmanager.TagManager$1 */
    class C27161 implements zzb {
        C27161() {
        }

        public void zzW(Map<String, Object> map) {
            Object obj = map.get("event");
            if (obj != null) {
                TagManager.this.zzgJ(obj.toString());
            }
        }
    }

    public interface zza {
        zzp zza(Context context, TagManager tagManager, Looper looper, String str, int i, zzs zzs);
    }

    /* renamed from: com.google.android.gms.tagmanager.TagManager$2 */
    class C27172 implements zza {
        C27172() {
        }

        public zzp zza(Context context, TagManager tagManager, Looper looper, String str, int i, zzs zzs) {
            return new zzp(context, tagManager, looper, str, i, zzs);
        }
    }

    /* renamed from: com.google.android.gms.tagmanager.TagManager$3 */
    class C27183 implements ComponentCallbacks2 {
        C27183() {
        }

        public void onConfigurationChanged(Configuration configuration) {
        }

        public void onLowMemory() {
        }

        public void onTrimMemory(int i) {
            if (i == 20) {
                TagManager.this.dispatch();
            }
        }
    }

    TagManager(Context context, zza zza, DataLayer dataLayer, zzda zzda) {
        if (context == null) {
            throw new NullPointerException("context cannot be null");
        }
        this.mContext = context.getApplicationContext();
        this.zzbrw = zzda;
        this.zzbrv = zza;
        this.zzbrx = new ConcurrentHashMap();
        this.zzbnS = dataLayer;
        this.zzbnS.zza(new C27161());
        this.zzbnS.zza(new zzd(this.mContext));
        this.zzbqs = new zzs();
        zzKH();
    }

    @RequiresPermission
    public static TagManager getInstance(Context context) {
        TagManager tagManager;
        synchronized (TagManager.class) {
            if (zzbry == null) {
                if (context == null) {
                    zzbn.m7501e("TagManager.getInstance requires non-null context.");
                    throw new NullPointerException();
                }
                zzbry = new TagManager(context, new C27172(), new DataLayer(new zzw(context)), zzdb.zzKB());
            }
            tagManager = zzbry;
        }
        return tagManager;
    }

    @TargetApi(14)
    private void zzKH() {
        if (VERSION.SDK_INT >= 14) {
            this.mContext.registerComponentCallbacks(new C27183());
        }
    }

    private void zzgJ(String str) {
        for (zzo zzgl : this.zzbrx.keySet()) {
            zzgl.zzgl(str);
        }
    }

    public void dispatch() {
        this.zzbrw.dispatch();
    }

    public DataLayer getDataLayer() {
        return this.zzbnS;
    }

    public PendingResult<ContainerHolder> loadContainerPreferFresh(String str, @RawRes int i) {
        zzp zza = this.zzbrv.zza(this.mContext, this, null, str, i, this.zzbqs);
        zza.zzJr();
        return zza;
    }

    public void setVerboseLoggingEnabled(boolean z) {
        zzbn.setLogLevel(z ? 2 : 5);
    }

    public void zza(zzo zzo) {
        this.zzbrx.put(zzo, Boolean.valueOf(true));
    }

    public boolean zzb(zzo zzo) {
        return this.zzbrx.remove(zzo) != null;
    }

    /* Access modifiers changed, original: declared_synchronized */
    public synchronized boolean zzr(Uri uri) {
        boolean z;
        zzci zzKh = zzci.zzKh();
        if (zzKh.zzr(uri)) {
            String containerId = zzKh.getContainerId();
            switch (zzKh.zzKi()) {
                case NONE:
                    for (zzo zzo : this.zzbrx.keySet()) {
                        if (zzo.getContainerId().equals(containerId)) {
                            zzo.zzgn(null);
                            zzo.refresh();
                        }
                    }
                    break;
                case CONTAINER:
                case CONTAINER_DEBUG:
                    for (zzo zzo2 : this.zzbrx.keySet()) {
                        if (zzo2.getContainerId().equals(containerId)) {
                            zzo2.zzgn(zzKh.zzKj());
                            zzo2.refresh();
                        } else if (zzo2.zzJm() != null) {
                            zzo2.zzgn(null);
                            zzo2.refresh();
                        }
                    }
                    break;
            }
            z = true;
        } else {
            z = false;
        }
        return z;
    }
}
