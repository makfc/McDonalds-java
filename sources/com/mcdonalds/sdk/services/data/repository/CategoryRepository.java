package com.mcdonalds.sdk.services.data.repository;

import android.content.Context;
import android.database.Cursor;
import com.mcdonalds.sdk.modules.models.Category;
import com.mcdonalds.sdk.services.data.provider.Contract.Categories;
import java.util.ArrayList;
import java.util.List;

public class CategoryRepository {
    public static List<Category> getAll(Context context) {
        Cursor cursor = context.getContentResolver().query(Categories.CONTENT_URI, null, null, null, "category_id");
        if (cursor == null) {
            return null;
        }
        cursor.moveToFirst();
        List<Category> categories = new ArrayList(cursor.getCount());
        while (!cursor.isAfterLast()) {
            Category category = new Category();
            category.populateFromCursor(cursor);
            categories.add(category);
            cursor.moveToNext();
        }
        cursor.close();
        return categories;
    }
}
