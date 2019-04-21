package android.support.design.internal;

import android.content.Context;
import android.support.annotation.RestrictTo;
import android.support.p001v7.view.menu.MenuBuilder;
import android.support.p001v7.view.menu.MenuItemImpl;
import android.support.p001v7.view.menu.SubMenuBuilder;
import android.view.SubMenu;

@RestrictTo
public class NavigationMenu extends MenuBuilder {
    public NavigationMenu(Context context) {
        super(context);
    }

    public SubMenu addSubMenu(int group, int id, int categoryOrder, CharSequence title) {
        MenuItemImpl item = (MenuItemImpl) addInternal(group, id, categoryOrder, title);
        SubMenuBuilder subMenu = new NavigationSubMenu(getContext(), this, item);
        item.setSubMenu(subMenu);
        return subMenu;
    }
}
