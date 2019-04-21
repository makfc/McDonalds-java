package p041io.fabric.sdk.android.services.common;

import android.content.Context;
import p041io.fabric.sdk.android.Fabric;
import p041io.fabric.sdk.android.services.cache.MemoryValueCache;
import p041io.fabric.sdk.android.services.cache.ValueLoader;

/* renamed from: io.fabric.sdk.android.services.common.InstallerPackageNameProvider */
public class InstallerPackageNameProvider {
    private final MemoryValueCache<String> installerPackageNameCache = new MemoryValueCache();
    private final ValueLoader<String> installerPackageNameLoader = new C45971();

    /* renamed from: io.fabric.sdk.android.services.common.InstallerPackageNameProvider$1 */
    class C45971 implements ValueLoader<String> {
        C45971() {
        }

        public String load(Context context) throws Exception {
            String installerPackageName = context.getPackageManager().getInstallerPackageName(context.getPackageName());
            return installerPackageName == null ? "" : installerPackageName;
        }
    }

    public String getInstallerPackageName(Context appContext) {
        try {
            String name = (String) this.installerPackageNameCache.get(appContext, this.installerPackageNameLoader);
            if ("".equals(name)) {
                return null;
            }
            return name;
        } catch (Exception e) {
            Fabric.getLogger().mo34406e("Fabric", "Failed to determine installer package name", e);
            return null;
        }
    }
}
