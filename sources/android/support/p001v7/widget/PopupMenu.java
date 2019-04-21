package android.support.p001v7.widget;

import android.support.p001v7.view.menu.MenuBuilder;
import android.support.p001v7.view.menu.MenuBuilder.Callback;
import android.support.p001v7.view.menu.MenuPopupHelper;
import android.support.p001v7.view.menu.ShowableListMenu;
import android.view.MenuItem;
import android.widget.PopupWindow.OnDismissListener;

/* renamed from: android.support.v7.widget.PopupMenu */
public class PopupMenu {
    OnMenuItemClickListener mMenuItemClickListener;
    OnDismissListener mOnDismissListener;
    final MenuPopupHelper mPopup;

    /* renamed from: android.support.v7.widget.PopupMenu$1 */
    class C04001 implements Callback {
        final /* synthetic */ PopupMenu this$0;

        public boolean onMenuItemSelected(MenuBuilder menu, MenuItem item) {
            if (this.this$0.mMenuItemClickListener != null) {
                return this.this$0.mMenuItemClickListener.onMenuItemClick(item);
            }
            return false;
        }

        public void onMenuModeChange(MenuBuilder menu) {
        }
    }

    /* renamed from: android.support.v7.widget.PopupMenu$2 */
    class C04012 implements OnDismissListener {
        final /* synthetic */ PopupMenu this$0;

        public void onDismiss() {
            if (this.this$0.mOnDismissListener != null) {
                this.this$0.mOnDismissListener.onDismiss(this.this$0);
            }
        }
    }

    /* renamed from: android.support.v7.widget.PopupMenu$3 */
    class C04023 extends ForwardingListener {
        final /* synthetic */ PopupMenu this$0;

        /* Access modifiers changed, original: protected */
        public boolean onForwardingStarted() {
            this.this$0.show();
            return true;
        }

        /* Access modifiers changed, original: protected */
        public boolean onForwardingStopped() {
            this.this$0.dismiss();
            return true;
        }

        public ShowableListMenu getPopup() {
            return this.this$0.mPopup.getPopup();
        }
    }

    /* renamed from: android.support.v7.widget.PopupMenu$OnDismissListener */
    public interface OnDismissListener {
        void onDismiss(PopupMenu popupMenu);
    }

    /* renamed from: android.support.v7.widget.PopupMenu$OnMenuItemClickListener */
    public interface OnMenuItemClickListener {
        boolean onMenuItemClick(MenuItem menuItem);
    }

    public void show() {
        this.mPopup.show();
    }

    public void dismiss() {
        this.mPopup.dismiss();
    }
}
