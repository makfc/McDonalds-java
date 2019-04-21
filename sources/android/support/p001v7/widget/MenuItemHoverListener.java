package android.support.p001v7.widget;

import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.p001v7.view.menu.MenuBuilder;
import android.view.MenuItem;

@RestrictTo
/* renamed from: android.support.v7.widget.MenuItemHoverListener */
public interface MenuItemHoverListener {
    void onItemHoverEnter(@NonNull MenuBuilder menuBuilder, @NonNull MenuItem menuItem);

    void onItemHoverExit(@NonNull MenuBuilder menuBuilder, @NonNull MenuItem menuItem);
}
