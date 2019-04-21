package com.mcdonalds.sdk.services.data.repository;

import android.content.Context;
import android.database.Cursor;
import com.mcdonalds.sdk.modules.models.PaymentMethod;
import com.mcdonalds.sdk.services.data.provider.Contract.PaymentMethods;
import java.util.ArrayList;
import java.util.List;

public class PaymentMethodRepository {
    public static List<PaymentMethod> getAll(Context context) {
        Cursor cursor = context.getContentResolver().query(PaymentMethods.CONTENT_URI, null, null, null, null);
        if (cursor == null) {
            return null;
        }
        cursor.moveToFirst();
        List<PaymentMethod> paymentMethods = new ArrayList(cursor.getCount());
        while (!cursor.isAfterLast()) {
            PaymentMethod paymentMethod = new PaymentMethod();
            paymentMethod.populateFromCursor(cursor);
            paymentMethods.add(paymentMethod);
            cursor.moveToNext();
        }
        cursor.close();
        return paymentMethods;
    }

    public static PaymentMethod getById(int paymentId, Context context) {
        Cursor cursor = context.getContentResolver().query(PaymentMethods.CONTENT_URI, null, String.format("%s=?", new Object[]{"id"}), new String[]{String.valueOf(paymentId)}, null);
        if (cursor == null) {
            return null;
        }
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            PaymentMethod paymentMethod = new PaymentMethod();
            paymentMethod.populateFromCursor(cursor);
            cursor.close();
            return paymentMethod;
        }
        cursor.close();
        return null;
    }
}
