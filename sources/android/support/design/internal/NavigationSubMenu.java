package android.support.design.internal;

import android.content.Context;
import android.support.annotation.RestrictTo;
import android.support.p001v7.view.menu.MenuBuilder;
import android.support.p001v7.view.menu.MenuItemImpl;
import android.support.p001v7.view.menu.SubMenuBuilder;

@RestrictTo
public class NavigationSubMenu extends SubMenuBuilder {
    public NavigationSubMenu(Context context, NavigationMenu menu, MenuItemImpl item) {
        super(context, menu, item);
    }

    public void onItemsChanged(boolean structureChanged) {
        super.onItemsChanged(structureChanged);
        ((MenuBuilder) getParentMenu()).onItemsChanged(structureChanged);
    }
}
