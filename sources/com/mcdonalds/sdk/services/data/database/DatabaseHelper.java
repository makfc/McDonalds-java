package com.mcdonalds.sdk.services.data.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.mcdonalds.sdk.modules.models.AdvertisablePromotionEntity;
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
import com.mcdonalds.sdk.services.data.database.DatabaseModel.DatabaseField;
import com.mcdonalds.sdk.services.data.database.DatabaseModel.ForeignKey;
import com.newrelic.agent.android.instrumentation.SQLiteInstrumentation;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "mcd.db";
    public static final int DATABASE_VERSION = 9;
    private static final DatabaseModel[] MODELS = new DatabaseModel[]{new SocialNetwork(), new Category(), new Facility(), new MenuType(), new PaymentMethod(), new FeedBackType(), new Pod(), new ProductDimension(), new Ingredient(), new Product(), new Promotion(), new TenderType(), new StoreCatalog(), new StoreProduct(), new StoreProductCategory(), new AdvertisablePromotionEntity(), new Nutrient(), new RecipeComponent(), new Allergen(), new RecipeFooter()};
    private static DatabaseHelper sInstance;

    private DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 9);
    }

    public static synchronized DatabaseHelper getInstance(Context context) {
        DatabaseHelper databaseHelper;
        synchronized (DatabaseHelper.class) {
            if (sInstance == null) {
                sInstance = new DatabaseHelper(context);
            }
            databaseHelper = sInstance;
        }
        return databaseHelper;
    }

    public static synchronized void reset(Context context) {
        synchronized (DatabaseHelper.class) {
            sInstance = new DatabaseHelper(context);
        }
    }

    public static DatabaseQueryBuilder select() {
        return new DatabaseQueryBuilder("select");
    }

    public static DatabaseQueryBuilder update() {
        return new DatabaseQueryBuilder("update");
    }

    public static DatabaseQueryBuilder delete() {
        return new DatabaseQueryBuilder("delete");
    }

    public void onCreate(SQLiteDatabase db) {
        try {
            for (DatabaseModel model : MODELS) {
                for (String sql : getCreateTableString(model)) {
                    if (db instanceof SQLiteDatabase) {
                        SQLiteInstrumentation.execSQL(db, sql);
                    } else {
                        db.execSQL(sql);
                    }
                }
            }
        } catch (SQLiteException e) {
            Log.e("SQLite", "Database was deleted");
        }
    }

    public void destroy(SQLiteDatabase db) {
        try {
            for (DatabaseModel model : MODELS) {
                for (String sql : getDropTableString(model)) {
                    if (db instanceof SQLiteDatabase) {
                        SQLiteInstrumentation.execSQL(db, sql);
                    } else {
                        db.execSQL(sql);
                    }
                }
            }
        } catch (SQLiteException e) {
            Log.e("SQLite", "Database was deleted");
        }
    }

    public void onConfigure(SQLiteDatabase db) {
        try {
            String str = "PRAGMA auto_vacuum = FULL";
            if (db instanceof SQLiteDatabase) {
                SQLiteInstrumentation.execSQL(db, str);
            } else {
                db.execSQL(str);
            }
        } catch (SQLiteException e) {
            Log.e("SQLite", "Database was deleted");
        }
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        clear(db);
    }

    public void clear(SQLiteDatabase db) {
        destroy(db);
        onCreate(db);
    }

    private String[] getCreateTableString(DatabaseModel model) {
        int i;
        ArrayList<String> createStrings = new ArrayList();
        String tableName = model.getTableName();
        String[] primaryKeys = model.getPrimaryKeyNames();
        List<ForeignKey> foreignKeys = model.getForeignKeys();
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("create table if not exists %s (\n", new Object[]{tableName}));
        List<DatabaseField> fields = model.getFields();
        int fieldsSize = fields.size();
        for (i = 0; i < fieldsSize; i++) {
            DatabaseField field = (DatabaseField) fields.get(i);
            builder.append(String.format("  %s %s", new Object[]{field.name, field.type}));
            if (i < fieldsSize - 1) {
                builder.append(",\n");
            }
        }
        if (primaryKeys != null && primaryKeys.length > 0) {
            builder.append(",\n PRIMARY KEY(");
            for (i = 0; i < primaryKeys.length; i++) {
                builder.append(String.format("%s", new Object[]{primaryKeys[i]}));
                if (i < primaryKeys.length - 1) {
                    builder.append(", ");
                }
            }
            builder.append(") ON CONFLICT REPLACE\n");
        }
        ArrayList<String> relationTableCreationList = new ArrayList();
        if (foreignKeys != null) {
            int foreignKeysSize = foreignKeys.size();
            for (i = 0; i < foreignKeysSize; i++) {
                ForeignKey key = (ForeignKey) foreignKeys.get(i);
                if (key.relationType == 2) {
                    relationTableCreationList.add(getRelationTableCreationString(model, key));
                } else {
                    int j;
                    if (i <= foreignKeysSize - 1) {
                        builder.append(",\n");
                    }
                    if (key.relationType == 1) {
                        builder.append(String.format("  UNIQUE (%s) ON CONFLICT REPLACE,\n", new Object[]{key.columnNames[0]}));
                    }
                    builder.append("  foreign key (");
                    String[] columnNames = key.columnNames;
                    for (j = 0; j < columnNames.length; j++) {
                        if (j > 0) {
                            builder.append(", ");
                        }
                        builder.append(String.format("%s", new Object[]{columnNames[j]}));
                    }
                    builder.append(String.format(") references %s(", new Object[]{key.referencedTableName}));
                    String[] referencedColumnNames = key.referencedColumnNames;
                    for (j = 0; j < referencedColumnNames.length; j++) {
                        if (j > 0) {
                            builder.append(", ");
                        }
                        builder.append(String.format("%s", new Object[]{referencedColumnNames[j]}));
                    }
                    builder.append(") ON DELETE CASCADE");
                }
            }
        }
        builder.append("\n);\n\n");
        createStrings.addAll(relationTableCreationList);
        createStrings.add(builder.toString());
        return (String[]) createStrings.toArray(new String[createStrings.size()]);
    }

    private String[] getDropTableString(DatabaseModel model) {
        ArrayList<String> dropStrings = new ArrayList();
        String tableName = model.getTableName();
        dropStrings.add(String.format("drop table if exists %s;\n", new Object[]{tableName}));
        if (model.getForeignKeys() != null) {
            for (ForeignKey key : model.getForeignKeys()) {
                if (key.relationType == 2) {
                    String relationTableName = key.relationTableName;
                    if (relationTableName == null) {
                        relationTableName = String.format("%s_%s", new Object[]{tableName, key.referencedTableName});
                    }
                    dropStrings.add(String.format("drop table if exists %s;\n", new Object[]{relationTableName}));
                }
            }
        }
        return (String[]) dropStrings.toArray(new String[dropStrings.size()]);
    }

    private String getRelationTableCreationString(DatabaseModel model, ForeignKey key) {
        String name;
        String columnName;
        int i;
        StringBuilder builder = new StringBuilder();
        String tableName = model.getTableName();
        String referencedTableName = key.referencedTableName;
        String relationTableName = key.relationTableName;
        if (relationTableName == null) {
            relationTableName = String.format("%s_%s", new Object[]{tableName, referencedTableName});
        }
        builder.append(String.format("create table if not exists %s (\n", new Object[]{relationTableName}));
        String type = "";
        List<DatabaseField> fields = model.getFields();
        String[] columnNames = key.columnNames;
        for (DatabaseField field : fields) {
            if (field.name.equals(columnNames[0])) {
                type = field.type;
                break;
            }
        }
        for (String columnName2 : columnNames) {
            name = String.format("%s_%s", new Object[]{tableName, columnName2});
            builder.append(String.format("  %s %s,\n", new Object[]{name, type}));
        }
        for (String referencedColumnName : key.referencedColumnNames) {
            name = String.format("%s_%s", new Object[]{referencedTableName, referencedColumnName});
            builder.append(String.format("  %s %s,\n", new Object[]{name, type}));
        }
        builder.append("  foreign key (");
        for (i = 0; i < columnNames.length; i++) {
            if (i > 0) {
                builder.append(", ");
            }
            columnName2 = columnNames[i];
            name = String.format("%s_%s", new Object[]{tableName, columnName2});
            builder.append(String.format("%s", new Object[]{name}));
        }
        builder.append(String.format(") references %s(", new Object[]{tableName}));
        for (i = 0; i < columnNames.length; i++) {
            if (i > 0) {
                builder.append(", ");
            }
            builder.append(String.format("%s", new Object[]{columnNames[i]}));
        }
        builder.append(") ON DELETE CASCADE,\n");
        builder.append("  foreign key (");
        String[] referencedColumnNames = key.referencedColumnNames;
        for (i = 0; i < referencedColumnNames.length; i++) {
            if (i > 0) {
                builder.append(", ");
            }
            columnName2 = referencedColumnNames[i];
            name = String.format("%s_%s", new Object[]{referencedTableName, columnName2});
            builder.append(String.format("%s", new Object[]{name}));
        }
        builder.append(String.format(") references %s(", new Object[]{referencedTableName}));
        for (i = 0; i < referencedColumnNames.length; i++) {
            if (i > 0) {
                builder.append(", ");
            }
            builder.append(String.format("%s", new Object[]{referencedColumnNames[i]}));
        }
        builder.append(") ON DELETE CASCADE,\n");
        builder.append("  primary key (");
        for (String columnName22 : columnNames) {
            name = String.format("%s_%s", new Object[]{tableName, columnName22});
            builder.append(String.format("%s, ", new Object[]{name}));
        }
        for (i = 0; i < referencedColumnNames.length; i++) {
            if (i > 0) {
                builder.append(", ");
            }
            columnName22 = referencedColumnNames[i];
            name = String.format("%s_%s", new Object[]{referencedTableName, columnName22});
            builder.append(String.format("%s", new Object[]{name}));
        }
        builder.append(") ON CONFLICT REPLACE\n");
        builder.append("\n);\n\n");
        return builder.toString();
    }
}
