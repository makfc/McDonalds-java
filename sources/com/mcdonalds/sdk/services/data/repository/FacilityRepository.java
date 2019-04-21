package com.mcdonalds.sdk.services.data.repository;

import android.content.Context;
import android.database.Cursor;
import android.util.SparseArray;
import com.mcdonalds.sdk.modules.models.Facility;
import com.mcdonalds.sdk.services.data.provider.Contract.Facilities;
import java.util.ArrayList;
import java.util.List;

public class FacilityRepository {
    public static List<Facility> getAll(Context context) {
        Cursor cursor = context.getContentResolver().query(Facilities.CONTENT_URI, null, null, null, "id");
        if (cursor == null) {
            return null;
        }
        cursor.moveToFirst();
        List<Facility> arrayList = new ArrayList();
        while (!cursor.isAfterLast()) {
            Facility facility = new Facility();
            facility.populateFromCursor(cursor);
            arrayList.add(facility);
            cursor.moveToNext();
        }
        return arrayList;
    }

    public static SparseArray<Facility> getFacilityMap(Context context) {
        Cursor cursor = context.getContentResolver().query(Facilities.CONTENT_URI, null, null, null, "id");
        if (cursor == null) {
            return null;
        }
        cursor.moveToFirst();
        SparseArray<Facility> sparseArray = new SparseArray();
        while (!cursor.isAfterLast()) {
            Facility facility = new Facility();
            facility.populateFromCursor(cursor);
            sparseArray.put(facility.getID(), facility);
            cursor.moveToNext();
        }
        return sparseArray;
    }
}
