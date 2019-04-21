package com.mcdonalds.sdk.services.data.repository;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.Nullable;
import com.mcdonalds.sdk.services.data.database.DatabaseModel.ForeignKey;
import com.mcdonalds.sdk.services.data.provider.Contract;

public class DatabaseModelRepository {
    protected static Cursor getRelatedModels(Context context, String tableName, @Nullable String relationName, String[] identifier, ForeignKey foreignKey) {
        return getRelatedModels(context, tableName, relationName, identifier, foreignKey, null);
    }

    protected static Cursor getRelatedModels(Context context, String tableName, @Nullable String relationName, String[] identifier, ForeignKey foreignKey, String orderBy) {
        if (relationName == null || relationName.isEmpty()) {
            relationName = String.format("%s_%s", new Object[]{tableName, foreignKey.referencedTableName});
        }
        String referencedIdColumn = String.format("%s_%s", new Object[]{foreignKey.referencedTableName, foreignKey.referencedColumnNames[0]});
        StringBuilder modelSubQueryBuilder = new StringBuilder();
        modelSubQueryBuilder.append(String.format("select %s from %s where ", new Object[]{referencedIdColumn, relationName}));
        for (int i = 0; i < foreignKey.columnNames.length; i++) {
            if (i > 0) {
                modelSubQueryBuilder.append(" AND ");
            }
            String columnName = foreignKey.columnNames[i];
            String modelId = String.format("%s_%s", new Object[]{tableName, columnName});
            modelSubQueryBuilder.append(String.format("%s = ?", new Object[]{modelId}));
        }
        String selection = String.format("%s in (%s)", new Object[]{foreignKey.referencedColumnNames[0], modelSubQueryBuilder.toString()});
        return context.getContentResolver().query(Contract.getContentUri(foreignKey.referencedTableName), null, selection, identifier, orderBy);
    }
}
