package com.mcdonalds.sdk.services.data.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.provider.BaseColumns;
import android.support.annotation.Nullable;
import com.mcdonalds.sdk.modules.AppModel;
import java.util.ArrayList;
import java.util.List;

public abstract class DatabaseModel extends AppModel implements BaseColumns {
    public static final String TYPE_INTEGER = "integer";
    public static final String TYPE_REAL = "real";
    public static final String TYPE_TEXT = "text";

    public static class DatabaseField {
        public String name;
        public String type;

        public DatabaseField(String name, String type) {
            this.name = name;
            this.type = type;
        }
    }

    public static class ForeignKey {
        public static final int RELATION_MANY_TO_MANY = 2;
        public static final int RELATION_MANY_TO_ONE = 0;
        public static final int RELATION_ONE_TO_ONE = 1;
        public String[] columnNames;
        public String[] referencedColumnNames;
        public String referencedTableName;
        public String relationTableName;
        public int relationType;

        public ForeignKey(String columnName, String referencedTableName, String referencedColumnName) {
            this(new String[]{columnName}, referencedTableName, new String[]{referencedColumnName});
        }

        public ForeignKey(String[] columnNames, String referencedTableName, String referencedColumnName) {
            this(columnNames, referencedTableName, new String[]{referencedColumnName}, 0, null);
        }

        public ForeignKey(String[] columnNames, String referencedTableName, String[] referencedColumnNames) {
            this(columnNames, referencedTableName, referencedColumnNames, 0, null);
        }

        public ForeignKey(String columnName, String referencedTableName, String referencedColumnName, int relationType, @Nullable String relationTableName) {
            this(new String[]{columnName}, referencedTableName, new String[]{referencedColumnName}, relationType, relationTableName);
        }

        public ForeignKey(String[] columnNames, String referencedTableName, String referencedColumnName, int relationType, @Nullable String relationTableName) {
            this(columnNames, referencedTableName, new String[]{referencedColumnName}, relationType, relationTableName);
        }

        public ForeignKey(String[] columnNames, String referencedTableName, String[] referencedColumnNames, int relationType, @Nullable String relationTableName) {
            this.columnNames = columnNames;
            this.referencedTableName = referencedTableName;
            this.referencedColumnNames = referencedColumnNames;
            this.relationType = relationType;
            this.relationTableName = relationTableName;
        }
    }

    public abstract List<DatabaseField> getFields();

    public abstract List<ContentValues> getForeignKeyValue(String str);

    public abstract List<ForeignKey> getForeignKeys();

    public abstract String[] getPrimaryKeyNames();

    public abstract String getSelection();

    public abstract String[] getSelectionArgs();

    public abstract String getTableName();

    public abstract ContentValues getValues();

    public abstract void populateFromCursor(Cursor cursor);

    /* Access modifiers changed, original: protected */
    public int getIntForBoolean(boolean value) {
        if (value) {
            return 1;
        }
        return 0;
    }

    /* Access modifiers changed, original: protected */
    public boolean getBooleanForInt(int value) {
        return value != 0;
    }

    /* Access modifiers changed, original: protected */
    public List<ContentValues> getRelationValues(List<? extends DatabaseModel> modelList) {
        List<ContentValues> values = new ArrayList();
        if (modelList != null) {
            for (DatabaseModel model : modelList) {
                values.add(model.getValues());
            }
        }
        return !values.isEmpty() ? values : null;
    }
}
