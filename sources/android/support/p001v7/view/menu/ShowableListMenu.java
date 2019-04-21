package android.support.p001v7.view.menu;

import android.support.annotation.RestrictTo;
import android.widget.ListView;

@RestrictTo
/* renamed from: android.support.v7.view.menu.ShowableListMenu */
public interface ShowableListMenu {
    void dismiss();

    ListView getListView();

    boolean isShowing();

    void show();
}
