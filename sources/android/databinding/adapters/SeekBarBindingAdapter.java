package android.databinding.adapters;

import android.databinding.InverseBindingListener;
import android.databinding.InverseBindingMethods;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

@InverseBindingMethods
public class SeekBarBindingAdapter {

    /* renamed from: android.databinding.adapters.SeekBarBindingAdapter$1 */
    static class C00321 implements OnSeekBarChangeListener {
        final /* synthetic */ InverseBindingListener val$attrChanged;
        final /* synthetic */ OnProgressChanged val$progressChanged;
        final /* synthetic */ OnStartTrackingTouch val$start;
        final /* synthetic */ OnStopTrackingTouch val$stop;

        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            if (this.val$progressChanged != null) {
                this.val$progressChanged.onProgressChanged(seekBar, progress, fromUser);
            }
            if (this.val$attrChanged != null) {
                this.val$attrChanged.onChange();
            }
        }

        public void onStartTrackingTouch(SeekBar seekBar) {
            if (this.val$start != null) {
                this.val$start.onStartTrackingTouch(seekBar);
            }
        }

        public void onStopTrackingTouch(SeekBar seekBar) {
            if (this.val$stop != null) {
                this.val$stop.onStopTrackingTouch(seekBar);
            }
        }
    }

    public interface OnProgressChanged {
        void onProgressChanged(SeekBar seekBar, int i, boolean z);
    }

    public interface OnStartTrackingTouch {
        void onStartTrackingTouch(SeekBar seekBar);
    }

    public interface OnStopTrackingTouch {
        void onStopTrackingTouch(SeekBar seekBar);
    }
}
