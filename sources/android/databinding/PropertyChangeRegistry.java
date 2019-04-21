package android.databinding;

import android.databinding.CallbackRegistry.NotifierCallback;
import android.databinding.Observable.OnPropertyChangedCallback;

public class PropertyChangeRegistry extends CallbackRegistry<OnPropertyChangedCallback, Observable, Void> {
    private static final NotifierCallback<OnPropertyChangedCallback, Observable, Void> NOTIFIER_CALLBACK = new C00121();

    /* renamed from: android.databinding.PropertyChangeRegistry$1 */
    static class C00121 extends NotifierCallback<OnPropertyChangedCallback, Observable, Void> {
        C00121() {
        }

        public void onNotifyCallback(OnPropertyChangedCallback callback, Observable sender, int arg, Void notUsed) {
            callback.onPropertyChanged(sender, arg);
        }
    }

    public PropertyChangeRegistry() {
        super(NOTIFIER_CALLBACK);
    }
}
