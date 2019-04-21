package com.mcdonalds.sdk.services.data.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteStatement;
import com.autonavi.amap.mapcore.MapCore;
import com.mcdonalds.sdk.modules.models.Allergen;
import com.mcdonalds.sdk.modules.models.Category;
import com.mcdonalds.sdk.modules.models.Facility;
import com.mcdonalds.sdk.modules.models.FeedBackType;
import com.mcdonalds.sdk.modules.models.Ingredient;
import com.mcdonalds.sdk.modules.models.MenuType;
import com.mcdonalds.sdk.modules.models.Nutrient;
import com.mcdonalds.sdk.modules.models.PaymentMethod;
import com.mcdonalds.sdk.modules.models.Pod;
import com.mcdonalds.sdk.modules.models.Product;
import com.mcdonalds.sdk.modules.models.ProductDimension;
import com.mcdonalds.sdk.modules.models.Promotion;
import com.mcdonalds.sdk.modules.models.RecipeComponent;
import com.mcdonalds.sdk.modules.models.RecipeFooter;
import com.mcdonalds.sdk.modules.models.SocialNetwork;
import com.mcdonalds.sdk.modules.models.StoreCatalog;
import com.mcdonalds.sdk.modules.models.StoreProduct;
import com.mcdonalds.sdk.modules.models.StoreProductCategory;
import com.mcdonalds.sdk.modules.models.TenderType;
import com.mcdonalds.sdk.services.data.database.DatabaseModel.ForeignKey;
import com.mcdonalds.sdk.utils.ListUtils;
import com.newrelic.agent.android.instrumentation.SQLiteInstrumentation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class Transaction {
    private SQLiteDatabase mDatabase;

    public Transaction(Context context) {
        init(context);
    }

    private synchronized void init(Context context) throws SQLiteException {
        this.mDatabase = DatabaseHelper.getInstance(context).getWritableDatabase();
        this.mDatabase.beginTransaction();
    }

    public void insert(DatabaseModel model) throws SQLiteException {
        SQLiteDatabase sQLiteDatabase = this.mDatabase;
        String tableName = model.getTableName();
        ContentValues values = model.getValues();
        if (sQLiteDatabase instanceof SQLiteDatabase) {
            SQLiteInstrumentation.insert(sQLiteDatabase, tableName, null, values);
        } else {
            sQLiteDatabase.insert(tableName, null, values);
        }
        if (model.getForeignKeys() != null) {
            updateManyToManyRelationships(model, false);
        }
    }

    public void insert(Collection<? extends DatabaseModel> modelCollection) throws SQLiteException {
        insert(modelCollection, true);
    }

    public void insert(Collection<? extends DatabaseModel> modelCollection, boolean clearPreviousRecord) throws SQLiteException {
        if (!ListUtils.isEmpty(modelCollection)) {
            List<? extends DatabaseModel> models = new ArrayList(modelCollection);
            String tableName = ((DatabaseModel) models.get(0)).getTableName();
            List<ContentValues> valuesList = new ArrayList();
            for (DatabaseModel model : models) {
                valuesList.add(model.getValues());
                if (model.getForeignKeys() != null) {
                    updateManyToManyRelationships(model, false, clearPreviousRecord);
                }
            }
            insertMultiple(tableName, null, valuesList);
        }
    }

    public void insertFast(Collection<? extends DatabaseModel> modelCollection, boolean clearPreviousRecord) {
        if (!ListUtils.isEmpty(modelCollection)) {
            List<? extends DatabaseModel> models = new ArrayList(modelCollection);
            String tableName = ((DatabaseModel) models.get(0)).getTableName();
            List<ContentValues> valuesList = new ArrayList();
            if (((DatabaseModel) models.get(0)).getForeignKeys() != null) {
                updateManyToManyRelationshipsFast(models, false, clearPreviousRecord);
            }
            for (DatabaseModel model : models) {
                valuesList.add(model.getValues());
            }
            insertMultiple(tableName, null, valuesList);
        }
    }

    public void update(DatabaseModel model) throws SQLiteException {
        SQLiteDatabase sQLiteDatabase = this.mDatabase;
        String tableName = model.getTableName();
        ContentValues values = model.getValues();
        String selection = model.getSelection();
        String[] selectionArgs = model.getSelectionArgs();
        if (sQLiteDatabase instanceof SQLiteDatabase) {
            SQLiteInstrumentation.update(sQLiteDatabase, tableName, values, selection, selectionArgs);
        } else {
            sQLiteDatabase.update(tableName, values, selection, selectionArgs);
        }
        if (model.getForeignKeys() != null) {
            updateManyToManyRelationships(model, false);
        }
    }

    public void delete(DatabaseModel model) throws SQLiteException {
        SQLiteDatabase sQLiteDatabase = this.mDatabase;
        String tableName = model.getTableName();
        String selection = model.getSelection();
        String[] selectionArgs = model.getSelectionArgs();
        if (sQLiteDatabase instanceof SQLiteDatabase) {
            SQLiteInstrumentation.delete(sQLiteDatabase, tableName, selection, selectionArgs);
        } else {
            sQLiteDatabase.delete(tableName, selection, selectionArgs);
        }
        if (model.getForeignKeys() != null) {
            updateManyToManyRelationships(model, true);
        }
    }

    public void clearAllData() {
        for (String table : new String[]{SocialNetwork.TABLE_NAME, Category.TABLE_NAME, Facility.TABLE_NAME, MenuType.TABLE_NAME, PaymentMethod.TABLE_NAME, FeedBackType.TABLE_NAME, Pod.TABLE_NAME, ProductDimension.TABLE_NAME, Ingredient.TABLE_NAME, "products_ingredients", "products_extras", "products_choices", Product.TABLE_NAME, Promotion.TABLE_NAME, TenderType.TABLE_NAME, "store_catalogs_promotions", "store_catalogs_facilities", StoreCatalog.TABLE_NAME, "store_product_pods", "store_product_dimensions", "store_product_menu_types", StoreProduct.TABLE_NAME, StoreProductCategory.TABLE_NAME, Nutrient.TABLE_NAME, RecipeComponent.TABLE_NAME, Allergen.TABLE_NAME, RecipeFooter.TABLE_NAME}) {
            try {
                SQLiteDatabase sQLiteDatabase = this.mDatabase;
                if (sQLiteDatabase instanceof SQLiteDatabase) {
                    SQLiteInstrumentation.delete(sQLiteDatabase, table, null, null);
                } else {
                    sQLiteDatabase.delete(table, null, null);
                }
            } catch (SQLException e) {
            }
        }
    }

    public void commit() throws SQLiteException {
        this.mDatabase.setTransactionSuccessful();
    }

    public void finish() {
        if (this.mDatabase != null && this.mDatabase.isOpen()) {
            try {
                this.mDatabase.endTransaction();
            } catch (SQLiteException e) {
            }
        }
    }

    private void insertMultiple(String table, String nullColumnHack, List<ContentValues> valuesList) {
        if (!ListUtils.isEmpty(valuesList)) {
            ContentValues initialValues = (ContentValues) valuesList.get(0);
            Set<String> keySet = initialValues.keySet();
            SQLiteStatement statement = getCompiledStatement(table, nullColumnHack, initialValues);
            for (ContentValues values : valuesList) {
                int index = 1;
                for (String colName : keySet) {
                    Object value = values.get(colName);
                    if (value instanceof Integer) {
                        statement.bindLong(index, ((Integer) value).longValue());
                    } else if (value instanceof Long) {
                        statement.bindLong(index, ((Long) value).longValue());
                    } else if (value instanceof Boolean) {
                        statement.bindLong(index, ((Boolean) value).booleanValue() ? 1 : 0);
                    } else if (value instanceof Double) {
                        statement.bindDouble(index, ((Double) value).doubleValue());
                    } else if (value instanceof String) {
                        statement.bindString(index, (String) value);
                    } else {
                        statement.bindNull(index);
                    }
                    index++;
                }
                statement.executeInsert();
            }
            statement.close();
        }
    }

    private SQLiteStatement getCompiledStatement(String table, String nullColumnHack, ContentValues initialValues) {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT");
        sql.append(" INTO ");
        sql.append(table);
        sql.append('(');
        int size = (initialValues == null || initialValues.size() <= 0) ? 0 : initialValues.size();
        if (size > 0) {
            int i = 0;
            for (String colName : initialValues.keySet()) {
                sql.append(i > 0 ? "," : "");
                sql.append(colName);
                i++;
            }
            sql.append(')');
            sql.append(" VALUES (");
            i = 0;
            while (i < size) {
                sql.append(i > 0 ? ",?" : "?");
                i++;
            }
        } else {
            sql.append(nullColumnHack);
            sql.append(") VALUES (NULL");
        }
        sql.append(')');
        return this.mDatabase.compileStatement(sql.toString());
    }

    private void updateManyToManyRelationships(DatabaseModel model, boolean delete) {
        updateManyToManyRelationships(model, delete, true);
    }

    private void updateManyToManyRelationships(DatabaseModel model, boolean delete, boolean clearPreviousRecord) {
        String tableName = model.getTableName();
        for (ForeignKey key : model.getForeignKeys()) {
            if (key.relationType == 2) {
                String relationTableName = key.relationTableName;
                if (relationTableName == null) {
                    relationTableName = String.format("%s_%s", new Object[]{tableName, key.referencedTableName});
                }
                if (clearPreviousRecord) {
                    StringBuilder selectionBuilder = new StringBuilder();
                    String[] selectionArgs = new String[key.columnNames.length];
                    for (int i = 0; i < key.columnNames.length; i++) {
                        String name = String.format("%s_%s", new Object[]{tableName, key.columnNames[i]});
                        String value = model.getValues().getAsString(columnName);
                        if (i > 0) {
                            selectionBuilder.append(" AND ");
                        }
                        selectionBuilder.append(String.format("%s=?", new Object[]{name}));
                        selectionArgs[i] = value;
                    }
                    SQLiteDatabase sQLiteDatabase = this.mDatabase;
                    String stringBuilder = selectionBuilder.toString();
                    if (sQLiteDatabase instanceof SQLiteDatabase) {
                        SQLiteInstrumentation.delete(sQLiteDatabase, relationTableName, stringBuilder, selectionArgs);
                    } else {
                        sQLiteDatabase.delete(relationTableName, stringBuilder, selectionArgs);
                    }
                }
                if (!delete) {
                    String valueKey = key.relationTableName;
                    if (valueKey == null) {
                        valueKey = key.referencedTableName;
                    }
                    List<ContentValues> valuesList = model.getForeignKeyValue(valueKey);
                    if (valuesList != null) {
                        List<ContentValues> relationValuesList = new ArrayList();
                        String[] strArr = key.referencedColumnNames;
                        int length = strArr.length;
                        int i2 = 0;
                        while (true) {
                            int i3 = i2;
                            if (i3 >= length) {
                                break;
                            }
                            String formattedName = String.format("%s_%s", new Object[]{key.referencedTableName, strArr[i3]});
                            for (ContentValues values : valuesList) {
                                String referencedColumnValue = values.getAsString(referencedColumnName);
                                ContentValues relationValues = new ContentValues();
                                for (String columnName : key.columnNames) {
                                    relationValues.put(String.format("%s_%s", new Object[]{tableName, columnName}), model.getValues().getAsString(r25[i2]));
                                }
                                relationValues.put(formattedName, referencedColumnValue);
                                relationValuesList.add(relationValues);
                            }
                            i2 = i3 + 1;
                        }
                        insertMultiple(relationTableName, null, relationValuesList);
                    }
                }
            }
        }
    }

    private void updateManyToManyRelationshipsFast(List<? extends DatabaseModel> models, boolean delete, boolean clearPreviousRecord) {
        int i;
        int j;
        String columnName;
        List<ForeignKey> foreignKeys = ((DatabaseModel) models.get(0)).getForeignKeys();
        HashMap<String, List<ContentValues>> accum = new HashMap();
        String tableName = ((DatabaseModel) models.get(0)).getTableName();
        String[] defaultRelationTableName = new String[foreignKeys.size()];
        String[][] tableColumnName = new String[foreignKeys.size()][];
        String[][] refTableColName = new String[foreignKeys.size()][];
        for (i = 0; i < foreignKeys.size(); i++) {
            ForeignKey foreignKey = (ForeignKey) foreignKeys.get(i);
            defaultRelationTableName[i] = String.format("%s_%s", new Object[]{tableName, foreignKey.referencedTableName});
            tableColumnName[i] = new String[foreignKey.columnNames.length];
            for (j = 0; j < foreignKey.columnNames.length; j++) {
                columnName = foreignKey.columnNames[j];
                tableColumnName[i][j] = String.format("%s_%s", new Object[]{tableName, columnName});
            }
            refTableColName[i] = new String[foreignKey.referencedColumnNames.length];
            for (j = 0; j < foreignKey.referencedColumnNames.length; j++) {
                refTableColName[i][j] = String.format("%s_%s", new Object[]{foreignKey.referencedTableName, foreignKey.referencedColumnNames[j]});
            }
        }
        for (DatabaseModel model : models) {
            ContentValues modelContentValues = model.getValues();
            for (i = 0; i < foreignKeys.size(); i++) {
                ForeignKey key = (ForeignKey) foreignKeys.get(i);
                if (key.relationType == 2) {
                    String relationTableName = key.relationTableName;
                    if (relationTableName == null) {
                        relationTableName = defaultRelationTableName[i];
                    }
                    if (clearPreviousRecord) {
                        StringBuilder selectionBuilder = new StringBuilder();
                        String[] selectionArgs = new String[key.columnNames.length];
                        for (j = 0; j < key.columnNames.length; j++) {
                            columnName = key.columnNames[j];
                            String name = tableColumnName[i][j];
                            String value = modelContentValues.getAsString(columnName);
                            if (j > 0) {
                                selectionBuilder.append(" AND ");
                            }
                            selectionBuilder.append(name).append("=?");
                            selectionArgs[j] = value;
                        }
                        SQLiteDatabase sQLiteDatabase = this.mDatabase;
                        String stringBuilder = selectionBuilder.toString();
                        if (sQLiteDatabase instanceof SQLiteDatabase) {
                            SQLiteInstrumentation.delete(sQLiteDatabase, relationTableName, stringBuilder, selectionArgs);
                        } else {
                            sQLiteDatabase.delete(relationTableName, stringBuilder, selectionArgs);
                        }
                    }
                    if (!delete) {
                        String valueKey = key.relationTableName;
                        if (valueKey == null) {
                            valueKey = key.referencedTableName;
                        }
                        List<ContentValues> valuesList = model.getForeignKeyValue(valueKey);
                        if (valuesList != null) {
                            ContentValues contentValues;
                            List<ContentValues> relationValuesList = (List) accum.get(relationTableName);
                            if (relationValuesList == null) {
                                relationValuesList = new ArrayList();
                                accum.put(relationTableName, relationValuesList);
                            }
                            ContentValues rvNameValues = new ContentValues();
                            for (j = 0; j < key.columnNames.length; j++) {
                                contentValues = rvNameValues;
                                contentValues.put(tableColumnName[i][j], modelContentValues.getAsString(key.columnNames[j]));
                            }
                            for (j = 0; j < key.referencedColumnNames.length; j++) {
                                String referencedColumnName = key.referencedColumnNames[j];
                                String formattedName = refTableColName[i][j];
                                for (ContentValues values : valuesList) {
                                    String referencedColumnValue = values.getAsString(referencedColumnName);
                                    contentValues = new ContentValues(rvNameValues);
                                    contentValues.put(formattedName, referencedColumnValue);
                                    relationValuesList.add(contentValues);
                                }
                            }
                        }
                    }
                }
            }
        }
        for (String key2 : accum.keySet()) {
            batchInsertMultiple(key2, (List) accum.get(key2));
        }
    }

    private void batchInsertMultiple(String table, List<ContentValues> valuesList) {
        if (!ListUtils.isEmpty(valuesList)) {
            int j;
            ContentValues values;
            int index;
            Object value;
            ContentValues initialValues = (ContentValues) valuesList.get(0);
            Set<String> keySet = initialValues.keySet();
            int maxValueListInsertPerBatch = MapCore.MAPRENDER_CAN_STOP_AND_FULLSCREEN_RENDEROVER / keySet.size();
            int valueListCount = valuesList.size();
            int remainingBatchInsert = valueListCount % maxValueListInsertPerBatch;
            int batchInsertCount = valueListCount;
            if (valueListCount >= maxValueListInsertPerBatch) {
                batchInsertCount = maxValueListInsertPerBatch;
            }
            SQLiteStatement statement = getBatchedCompiledStatement(table, initialValues, batchInsertCount);
            if (statement != null) {
                for (int i = 0; i < valueListCount / batchInsertCount; i++) {
                    for (j = 0; j < batchInsertCount; j++) {
                        values = (ContentValues) valuesList.get((i * batchInsertCount) + j);
                        index = (keySet.size() * j) + 1;
                        for (String colName : keySet) {
                            value = values.get(colName);
                            if (value instanceof Integer) {
                                statement.bindLong(index, ((Integer) value).longValue());
                            } else if (value instanceof Long) {
                                statement.bindLong(index, ((Long) value).longValue());
                            } else if (value instanceof Boolean) {
                                statement.bindLong(index, ((Boolean) value).booleanValue() ? 1 : 0);
                            } else if (value instanceof Double) {
                                statement.bindDouble(index, ((Double) value).doubleValue());
                            } else if (value instanceof String) {
                                statement.bindString(index, (String) value);
                            } else {
                                statement.bindNull(index);
                            }
                            index++;
                        }
                    }
                    statement.executeInsert();
                    statement.clearBindings();
                }
                statement.close();
            }
            if (valueListCount > maxValueListInsertPerBatch && remainingBatchInsert > 0) {
                statement = getBatchedCompiledStatement(table, initialValues, remainingBatchInsert);
                if (statement != null) {
                    for (j = 0; j < remainingBatchInsert; j++) {
                        values = (ContentValues) valuesList.get(((valueListCount / maxValueListInsertPerBatch) * maxValueListInsertPerBatch) + j);
                        index = (keySet.size() * j) + 1;
                        for (String colName2 : keySet) {
                            value = values.get(colName2);
                            if (value instanceof Integer) {
                                statement.bindLong(index, ((Integer) value).longValue());
                            } else if (value instanceof Long) {
                                statement.bindLong(index, ((Long) value).longValue());
                            } else if (value instanceof Boolean) {
                                statement.bindLong(index, ((Boolean) value).booleanValue() ? 1 : 0);
                            } else if (value instanceof Double) {
                                statement.bindDouble(index, ((Double) value).doubleValue());
                            } else if (value instanceof String) {
                                statement.bindString(index, (String) value);
                            } else {
                                statement.bindNull(index);
                            }
                            index++;
                        }
                    }
                    statement.executeInsert();
                    statement.close();
                }
            }
        }
    }

    private SQLiteStatement getBatchedCompiledStatement(String table, ContentValues initialValues, int maxValueListInsertPerBatch) {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT");
        sql.append(" INTO ");
        sql.append(table);
        sql.append('(');
        int size = (initialValues == null || initialValues.size() <= 0) ? 0 : initialValues.size();
        if (size <= 0) {
            return null;
        }
        int i = 0;
        for (String colName : initialValues.keySet()) {
            sql.append(i > 0 ? "," : "");
            sql.append(colName);
            i++;
        }
        sql.append(')');
        sql.append(" VALUES ");
        for (int j = 0; j < maxValueListInsertPerBatch; j++) {
            if (j > 0) {
                sql.append(',');
            }
            sql.append('(');
            i = 0;
            while (i < size) {
                sql.append(i > 0 ? ",?" : "?");
                i++;
            }
            sql.append(')');
        }
        return this.mDatabase.compileStatement(sql.toString());
    }
}
