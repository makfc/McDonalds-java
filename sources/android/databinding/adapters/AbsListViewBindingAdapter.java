package android.databinding.adapters;

import android.databinding.BindingMethods;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;

@BindingMethods
public class AbsListViewBindingAdapter {

    /* renamed from: android.databinding.adapters.AbsListViewBindingAdapter$1 */
    static class C00211 implements OnScrollListener {
        final /* synthetic */ OnScroll val$scrollListener;
        final /* synthetic */ OnScrollStateChanged val$scrollStateListener;

        public void onScrollStateChanged(AbsListView view, int scrollState) {
            if (this.val$scrollStateListener != null) {
                this.val$scrollStateListener.onScrollStateChanged(view, scrollState);
            }
        }

        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            if (this.val$scrollListener != null) {
                this.val$scrollListener.onScroll(view, firstVisibleItem, visibleItemCount, totalItemCount);
            }
        }
    }

    public interface OnScroll {
        void onScroll(AbsListView absListView, int i, int i2, int i3);
    }

    public interface OnScrollStateChanged {
        void onScrollStateChanged(AbsListView absListView, int i);
    }
}
