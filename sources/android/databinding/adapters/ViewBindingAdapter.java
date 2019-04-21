package android.databinding.adapters;

import android.annotation.TargetApi;
import android.databinding.BindingMethods;
import android.view.View;
import android.view.View.OnAttachStateChangeListener;

@BindingMethods
public class ViewBindingAdapter {
    public static int FADING_EDGE_HORIZONTAL = 1;
    public static int FADING_EDGE_NONE = 0;
    public static int FADING_EDGE_VERTICAL = 2;

    /* renamed from: android.databinding.adapters.ViewBindingAdapter$1 */
    static class C00361 implements OnAttachStateChangeListener {
        final /* synthetic */ OnViewAttachedToWindow val$attach;
        final /* synthetic */ OnViewDetachedFromWindow val$detach;

        public void onViewAttachedToWindow(View v) {
            if (this.val$attach != null) {
                this.val$attach.onViewAttachedToWindow(v);
            }
        }

        public void onViewDetachedFromWindow(View v) {
            if (this.val$detach != null) {
                this.val$detach.onViewDetachedFromWindow(v);
            }
        }
    }

    @TargetApi(12)
    public interface OnViewAttachedToWindow {
        void onViewAttachedToWindow(View view);
    }

    @TargetApi(12)
    public interface OnViewDetachedFromWindow {
        void onViewDetachedFromWindow(View view);
    }
}
