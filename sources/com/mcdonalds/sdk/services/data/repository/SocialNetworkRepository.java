package com.mcdonalds.sdk.services.data.repository;

import android.content.Context;
import android.database.Cursor;
import com.mcdonalds.sdk.modules.models.SocialNetwork;
import com.mcdonalds.sdk.services.data.provider.Contract.SocialNetworks;
import java.util.ArrayList;
import java.util.List;

public class SocialNetworkRepository {
    public static List<SocialNetwork> getValid(Context context) {
        Cursor cursor = context.getContentResolver().query(SocialNetworks.CONTENT_URI, null, String.format("%s=?", new Object[]{"is_valid"}), new String[]{"1"}, "id");
        if (cursor == null) {
            return null;
        }
        cursor.moveToFirst();
        List<SocialNetwork> socialNetworks = new ArrayList(cursor.getCount());
        while (!cursor.isAfterLast()) {
            SocialNetwork socialNetwork = new SocialNetwork();
            socialNetwork.populateFromCursor(cursor);
            socialNetworks.add(socialNetwork);
            cursor.moveToNext();
        }
        cursor.close();
        return socialNetworks;
    }

    public static SocialNetwork getById(Context context, int id) {
        Cursor cursor = context.getContentResolver().query(SocialNetworks.CONTENT_URI, null, String.format("%s=?", new Object[]{"id"}), new String[]{String.valueOf(id)}, "id");
        SocialNetwork socialNetwork = null;
        if (cursor != null) {
            cursor.moveToFirst();
            if (cursor.getCount() > 0) {
                socialNetwork = new SocialNetwork();
                socialNetwork.populateFromCursor(cursor);
            }
            cursor.close();
        }
        return socialNetwork;
    }
}
