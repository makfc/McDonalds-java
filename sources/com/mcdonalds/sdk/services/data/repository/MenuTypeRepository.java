package com.mcdonalds.sdk.services.data.repository;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.Nullable;
import com.mcdonalds.sdk.modules.models.MenuType;
import com.mcdonalds.sdk.services.data.provider.Contract.MenuTypes;
import java.util.ArrayList;
import java.util.List;

public class MenuTypeRepository {
    public static List<MenuType> getValid(Context context) {
        return get(context, String.format("%s=?", new Object[]{"is_valid"}), new String[]{"1"});
    }

    @Nullable
    private static List<MenuType> get(Context context, String selection, String[] selectionArgs) {
        Cursor cursor = context.getContentResolver().query(MenuTypes.CONTENT_URI, null, selection, selectionArgs, "id");
        if (cursor == null) {
            return null;
        }
        cursor.moveToFirst();
        List<MenuType> menuTypes = new ArrayList(cursor.getCount());
        while (!cursor.isAfterLast()) {
            MenuType menuType = new MenuType();
            menuType.populateFromCursor(cursor);
            menuTypes.add(menuType);
            cursor.moveToNext();
        }
        cursor.close();
        return menuTypes;
    }
}
