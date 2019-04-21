package android.databinding.adapters;

import android.databinding.InverseBindingListener;
import android.databinding.InverseBindingMethods;
import android.widget.CalendarView;
import android.widget.CalendarView.OnDateChangeListener;

@InverseBindingMethods
public class CalendarViewBindingAdapter {

    /* renamed from: android.databinding.adapters.CalendarViewBindingAdapter$1 */
    static class C00231 implements OnDateChangeListener {
        final /* synthetic */ InverseBindingListener val$attrChange;
        final /* synthetic */ OnDateChangeListener val$onDayChange;

        public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
            if (this.val$onDayChange != null) {
                this.val$onDayChange.onSelectedDayChange(view, year, month, dayOfMonth);
            }
            this.val$attrChange.onChange();
        }
    }
}
