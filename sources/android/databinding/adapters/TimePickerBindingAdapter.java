package android.databinding.adapters;

import android.databinding.InverseBindingListener;
import android.widget.TimePicker;
import android.widget.TimePicker.OnTimeChangedListener;

public class TimePickerBindingAdapter {

    /* renamed from: android.databinding.adapters.TimePickerBindingAdapter$1 */
    static class C00351 implements OnTimeChangedListener {
        final /* synthetic */ InverseBindingListener val$hourChange;
        final /* synthetic */ OnTimeChangedListener val$listener;
        final /* synthetic */ InverseBindingListener val$minuteChange;

        public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
            if (this.val$listener != null) {
                this.val$listener.onTimeChanged(view, hourOfDay, minute);
            }
            if (this.val$hourChange != null) {
                this.val$hourChange.onChange();
            }
            if (this.val$minuteChange != null) {
                this.val$minuteChange.onChange();
            }
        }
    }
}
