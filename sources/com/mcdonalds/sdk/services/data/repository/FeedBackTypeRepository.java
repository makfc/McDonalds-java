package com.mcdonalds.sdk.services.data.repository;

import android.content.Context;
import android.database.Cursor;
import com.mcdonalds.sdk.modules.models.FeedBackType;
import com.mcdonalds.sdk.services.data.provider.Contract;

public class FeedBackTypeRepository {
    public static FeedBackType getForSendRating(Context context) {
        Cursor cursor = context.getContentResolver().query(Contract.getContentUri(FeedBackType.TABLE_NAME), null, String.format("%s like ?", new Object[]{"name"}), new String[]{"app"}, null);
        if (cursor == null || !cursor.moveToFirst()) {
            return null;
        }
        FeedBackType feedBackType = new FeedBackType();
        feedBackType.populateFromCursor(cursor);
        cursor.close();
        return feedBackType;
    }
}
