package android.databinding.adapters;

import android.databinding.BindingMethods;
import android.databinding.InverseBindingListener;
import android.databinding.InverseBindingMethods;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

@InverseBindingMethods
@BindingMethods
public class AdapterViewBindingAdapter {

    public interface OnItemSelected {
        void onItemSelected(AdapterView<?> adapterView, View view, int i, long j);
    }

    public static class OnItemSelectedComponentListener implements OnItemSelectedListener {
        private final InverseBindingListener mAttrChanged;
        private final OnNothingSelected mNothingSelected;
        private final OnItemSelected mSelected;

        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if (this.mSelected != null) {
                this.mSelected.onItemSelected(parent, view, position, id);
            }
            if (this.mAttrChanged != null) {
                this.mAttrChanged.onChange();
            }
        }

        public void onNothingSelected(AdapterView<?> parent) {
            if (this.mNothingSelected != null) {
                this.mNothingSelected.onNothingSelected(parent);
            }
            if (this.mAttrChanged != null) {
                this.mAttrChanged.onChange();
            }
        }
    }

    public interface OnNothingSelected {
        void onNothingSelected(AdapterView<?> adapterView);
    }
}
