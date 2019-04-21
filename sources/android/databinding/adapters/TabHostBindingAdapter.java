package android.databinding.adapters;

import android.databinding.InverseBindingListener;
import android.widget.TabHost.OnTabChangeListener;

public class TabHostBindingAdapter {

    /* renamed from: android.databinding.adapters.TabHostBindingAdapter$1 */
    static class C00331 implements OnTabChangeListener {
        final /* synthetic */ InverseBindingListener val$attrChange;
        final /* synthetic */ OnTabChangeListener val$listener;

        public void onTabChanged(String tabId) {
            if (this.val$listener != null) {
                this.val$listener.onTabChanged(tabId);
            }
            this.val$attrChange.onChange();
        }
    }
}
