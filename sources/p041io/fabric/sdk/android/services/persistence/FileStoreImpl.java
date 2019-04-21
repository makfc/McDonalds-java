package p041io.fabric.sdk.android.services.persistence;

import android.content.Context;
import java.io.File;
import p041io.fabric.sdk.android.Fabric;
import p041io.fabric.sdk.android.Kit;

/* renamed from: io.fabric.sdk.android.services.persistence.FileStoreImpl */
public class FileStoreImpl implements FileStore {
    private final String contentPath;
    private final Context context;
    private final String legacySupport;

    public FileStoreImpl(Kit kit) {
        if (kit.getContext() == null) {
            throw new IllegalStateException("Cannot get directory before context has been set. Call Fabric.with() first");
        }
        this.context = kit.getContext();
        this.contentPath = kit.getPath();
        this.legacySupport = "Android/" + this.context.getPackageName();
    }

    public File getFilesDir() {
        return prepare(this.context.getFilesDir());
    }

    /* Access modifiers changed, original: 0000 */
    public File prepare(File file) {
        if (file == null) {
            Fabric.getLogger().mo34403d("Fabric", "Null File");
        } else if (file.exists() || file.mkdirs()) {
            return file;
        } else {
            Fabric.getLogger().mo34411w("Fabric", "Couldn't create file");
        }
        return null;
    }
}
