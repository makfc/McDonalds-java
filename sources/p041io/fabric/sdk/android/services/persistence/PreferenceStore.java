package p041io.fabric.sdk.android.services.persistence;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/* renamed from: io.fabric.sdk.android.services.persistence.PreferenceStore */
public interface PreferenceStore {
    Editor edit();

    SharedPreferences get();

    boolean save(Editor editor);
}
