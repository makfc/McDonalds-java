package p041io.fabric.sdk.android;

import android.content.Context;
import java.io.File;
import java.util.Collection;
import p041io.fabric.sdk.android.services.common.IdManager;
import p041io.fabric.sdk.android.services.concurrency.DependsOn;
import p041io.fabric.sdk.android.services.concurrency.Task;

/* renamed from: io.fabric.sdk.android.Kit */
public abstract class Kit<Result> implements Comparable<Kit> {
    Context context;
    final DependsOn dependsOnAnnotation = ((DependsOn) getClass().getAnnotation(DependsOn.class));
    Fabric fabric;
    IdManager idManager;
    InitializationCallback<Result> initializationCallback;
    InitializationTask<Result> initializationTask = new InitializationTask(this);

    public abstract Result doInBackground();

    public abstract String getIdentifier();

    public abstract String getVersion();

    /* Access modifiers changed, original: 0000 */
    public void injectParameters(Context context, Fabric fabric, InitializationCallback<Result> callback, IdManager idManager) {
        this.fabric = fabric;
        this.context = new FabricContext(context, getIdentifier(), getPath());
        this.initializationCallback = callback;
        this.idManager = idManager;
    }

    /* Access modifiers changed, original: final */
    public final void initialize() {
        this.initializationTask.executeOnExecutor(this.fabric.getExecutorService(), (Void) null);
    }

    /* Access modifiers changed, original: protected */
    public boolean onPreExecute() {
        return true;
    }

    /* Access modifiers changed, original: protected */
    public void onPostExecute(Result result) {
    }

    /* Access modifiers changed, original: protected */
    public void onCancelled(Result result) {
    }

    /* Access modifiers changed, original: protected */
    public IdManager getIdManager() {
        return this.idManager;
    }

    public Context getContext() {
        return this.context;
    }

    public Fabric getFabric() {
        return this.fabric;
    }

    public String getPath() {
        return ".Fabric" + File.separator + getIdentifier();
    }

    public int compareTo(Kit another) {
        if (containsAnnotatedDependency(another)) {
            return 1;
        }
        if (another.containsAnnotatedDependency(this)) {
            return -1;
        }
        if (hasAnnotatedDependency() && !another.hasAnnotatedDependency()) {
            return 1;
        }
        if (hasAnnotatedDependency() || !another.hasAnnotatedDependency()) {
            return 0;
        }
        return -1;
    }

    /* Access modifiers changed, original: 0000 */
    public boolean containsAnnotatedDependency(Kit target) {
        if (!hasAnnotatedDependency()) {
            return false;
        }
        for (Class<?> dep : this.dependsOnAnnotation.value()) {
            if (dep.isAssignableFrom(target.getClass())) {
                return true;
            }
        }
        return false;
    }

    /* Access modifiers changed, original: 0000 */
    public boolean hasAnnotatedDependency() {
        return this.dependsOnAnnotation != null;
    }

    /* Access modifiers changed, original: protected */
    public Collection<Task> getDependencies() {
        return this.initializationTask.getDependencies();
    }
}
