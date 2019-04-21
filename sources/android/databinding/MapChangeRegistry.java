package android.databinding;

import android.databinding.CallbackRegistry.NotifierCallback;
import android.databinding.ObservableMap.OnMapChangedCallback;

public class MapChangeRegistry extends CallbackRegistry<OnMapChangedCallback, ObservableMap, Object> {
    private static NotifierCallback<OnMapChangedCallback, ObservableMap, Object> NOTIFIER_CALLBACK = new C00011();

    /* renamed from: android.databinding.MapChangeRegistry$1 */
    static class C00011 extends NotifierCallback<OnMapChangedCallback, ObservableMap, Object> {
        C00011() {
        }

        public void onNotifyCallback(OnMapChangedCallback callback, ObservableMap sender, int arg, Object arg2) {
            callback.onMapChanged(sender, arg2);
        }
    }

    public MapChangeRegistry() {
        super(NOTIFIER_CALLBACK);
    }
}
