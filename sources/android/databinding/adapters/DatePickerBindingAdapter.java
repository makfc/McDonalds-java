package android.databinding.adapters;

import android.databinding.InverseBindingListener;
import android.databinding.InverseBindingMethods;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;

@InverseBindingMethods
public class DatePickerBindingAdapter {

    private static class DateChangedListener implements OnDateChangedListener {
        InverseBindingListener mDayChanged;
        OnDateChangedListener mListener;
        InverseBindingListener mMonthChanged;
        InverseBindingListener mYearChanged;

        private DateChangedListener() {
        }

        public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            if (this.mListener != null) {
                this.mListener.onDateChanged(view, year, monthOfYear, dayOfMonth);
            }
            if (this.mYearChanged != null) {
                this.mYearChanged.onChange();
            }
            if (this.mMonthChanged != null) {
                this.mMonthChanged.onChange();
            }
            if (this.mDayChanged != null) {
                this.mDayChanged.onChange();
            }
        }
    }
}
