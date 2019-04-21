package android.support.p000v4.widget;

import android.os.Build.VERSION;
import android.view.View.OnTouchListener;

/* renamed from: android.support.v4.widget.PopupMenuCompat */
public final class PopupMenuCompat {
    static final PopupMenuImpl IMPL;

    /* renamed from: android.support.v4.widget.PopupMenuCompat$PopupMenuImpl */
    interface PopupMenuImpl {
        OnTouchListener getDragToOpenListener(Object obj);
    }

    /* renamed from: android.support.v4.widget.PopupMenuCompat$BasePopupMenuImpl */
    static class BasePopupMenuImpl implements PopupMenuImpl {
        BasePopupMenuImpl() {
        }

        public OnTouchListener getDragToOpenListener(Object popupMenu) {
            return null;
        }
    }

    /* renamed from: android.support.v4.widget.PopupMenuCompat$KitKatPopupMenuImpl */
    static class KitKatPopupMenuImpl extends BasePopupMenuImpl {
        KitKatPopupMenuImpl() {
        }

        public OnTouchListener getDragToOpenListener(Object popupMenu) {
            return PopupMenuCompatKitKat.getDragToOpenListener(popupMenu);
        }
    }

    static {
        if (VERSION.SDK_INT >= 19) {
            IMPL = new KitKatPopupMenuImpl();
        } else {
            IMPL = new BasePopupMenuImpl();
        }
    }

    private PopupMenuCompat() {
    }

    public static OnTouchListener getDragToOpenListener(Object popupMenu) {
        return IMPL.getDragToOpenListener(popupMenu);
    }
}
