package android.databinding.adapters;

import android.databinding.BindingMethods;
import android.view.View;
import android.view.ViewGroup.OnHierarchyChangeListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

@BindingMethods
public class ViewGroupBindingAdapter {

    /* renamed from: android.databinding.adapters.ViewGroupBindingAdapter$1 */
    static class C00371 implements OnHierarchyChangeListener {
        final /* synthetic */ OnChildViewAdded val$added;
        final /* synthetic */ OnChildViewRemoved val$removed;

        public void onChildViewAdded(View parent, View child) {
            if (this.val$added != null) {
                this.val$added.onChildViewAdded(parent, child);
            }
        }

        public void onChildViewRemoved(View parent, View child) {
            if (this.val$removed != null) {
                this.val$removed.onChildViewRemoved(parent, child);
            }
        }
    }

    /* renamed from: android.databinding.adapters.ViewGroupBindingAdapter$2 */
    static class C00382 implements AnimationListener {
        final /* synthetic */ OnAnimationEnd val$end;
        final /* synthetic */ OnAnimationRepeat val$repeat;
        final /* synthetic */ OnAnimationStart val$start;

        public void onAnimationStart(Animation animation) {
            if (this.val$start != null) {
                this.val$start.onAnimationStart(animation);
            }
        }

        public void onAnimationEnd(Animation animation) {
            if (this.val$end != null) {
                this.val$end.onAnimationEnd(animation);
            }
        }

        public void onAnimationRepeat(Animation animation) {
            if (this.val$repeat != null) {
                this.val$repeat.onAnimationRepeat(animation);
            }
        }
    }

    public interface OnAnimationEnd {
        void onAnimationEnd(Animation animation);
    }

    public interface OnAnimationRepeat {
        void onAnimationRepeat(Animation animation);
    }

    public interface OnAnimationStart {
        void onAnimationStart(Animation animation);
    }

    public interface OnChildViewAdded {
        void onChildViewAdded(View view, View view2);
    }

    public interface OnChildViewRemoved {
        void onChildViewRemoved(View view, View view2);
    }
}
