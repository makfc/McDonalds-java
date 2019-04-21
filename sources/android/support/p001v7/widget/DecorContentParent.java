package android.support.p001v7.widget;

import android.support.annotation.RestrictTo;
import android.support.p001v7.view.menu.MenuPresenter.Callback;
import android.view.Menu;
import android.view.Window;

@RestrictTo
/* renamed from: android.support.v7.widget.DecorContentParent */
public interface DecorContentParent {
    boolean canShowOverflowMenu();

    void dismissPopups();

    boolean hideOverflowMenu();

    void initFeature(int i);

    boolean isOverflowMenuShowPending();

    boolean isOverflowMenuShowing();

    void setMenu(Menu menu, Callback callback);

    void setMenuPrepared();

    void setWindowCallback(Window.Callback callback);

    void setWindowTitle(CharSequence charSequence);

    boolean showOverflowMenu();
}
