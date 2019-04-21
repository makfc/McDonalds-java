package android.databinding.adapters;

import android.databinding.InverseBindingListener;
import android.databinding.InverseBindingMethods;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;

@InverseBindingMethods
public class RatingBarBindingAdapter {

    /* renamed from: android.databinding.adapters.RatingBarBindingAdapter$1 */
    static class C00291 implements OnRatingBarChangeListener {
        final /* synthetic */ OnRatingBarChangeListener val$listener;
        final /* synthetic */ InverseBindingListener val$ratingChange;

        public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
            if (this.val$listener != null) {
                this.val$listener.onRatingChanged(ratingBar, rating, fromUser);
            }
            this.val$ratingChange.onChange();
        }
    }
}
