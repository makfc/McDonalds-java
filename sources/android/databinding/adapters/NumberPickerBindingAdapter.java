package android.databinding.adapters;

import android.databinding.BindingMethods;
import android.databinding.InverseBindingListener;
import android.databinding.InverseBindingMethods;
import android.widget.NumberPicker;
import android.widget.NumberPicker.OnValueChangeListener;

@InverseBindingMethods
@BindingMethods
public class NumberPickerBindingAdapter {

    /* renamed from: android.databinding.adapters.NumberPickerBindingAdapter$1 */
    static class C00261 implements OnValueChangeListener {
        final /* synthetic */ InverseBindingListener val$attrChange;
        final /* synthetic */ OnValueChangeListener val$listener;

        public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
            if (this.val$listener != null) {
                this.val$listener.onValueChange(picker, oldVal, newVal);
            }
            this.val$attrChange.onChange();
        }
    }
}
