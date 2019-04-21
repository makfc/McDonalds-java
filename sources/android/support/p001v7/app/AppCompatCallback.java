package android.support.p001v7.app;

import android.support.annotation.Nullable;
import android.support.p001v7.view.ActionMode;
import android.support.p001v7.view.ActionMode.Callback;

/* renamed from: android.support.v7.app.AppCompatCallback */
public interface AppCompatCallback {
    void onSupportActionModeFinished(ActionMode actionMode);

    void onSupportActionModeStarted(ActionMode actionMode);

    @Nullable
    ActionMode onWindowStartingSupportActionMode(Callback callback);
}
