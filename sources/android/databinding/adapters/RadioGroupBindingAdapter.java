package android.databinding.adapters;

import android.databinding.InverseBindingListener;
import android.databinding.InverseBindingMethods;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

@InverseBindingMethods
public class RadioGroupBindingAdapter {

    /* renamed from: android.databinding.adapters.RadioGroupBindingAdapter$1 */
    static class C00281 implements OnCheckedChangeListener {
        final /* synthetic */ InverseBindingListener val$attrChange;
        final /* synthetic */ OnCheckedChangeListener val$listener;

        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (this.val$listener != null) {
                this.val$listener.onCheckedChanged(group, checkedId);
            }
            this.val$attrChange.onChange();
        }
    }
}
