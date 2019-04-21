package com.mcdonalds.sdk.modules.models;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.mcdonalds.sdk.services.data.database.DatabaseModel;
import com.mcdonalds.sdk.services.data.database.DatabaseModel.DatabaseField;
import com.mcdonalds.sdk.services.data.database.DatabaseModel.ForeignKey;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class RecipeFooter extends DatabaseModel implements Parcelable {
    public static final String COLUMN_RECIPE_ID = "recipe_id";
    public static final String COLUMN_TEXT = "text";
    public static final Creator<RecipeFooter> CREATOR = new C40841();
    public static final String TABLE_NAME = "recipe_footer";
    private String mRecipeId;
    private String mText;

    /* renamed from: com.mcdonalds.sdk.modules.models.RecipeFooter$1 */
    static class C40841 implements Creator<RecipeFooter> {
        C40841() {
        }

        public RecipeFooter createFromParcel(Parcel in) {
            return new RecipeFooter(in);
        }

        public RecipeFooter[] newArray(int size) {
            return new RecipeFooter[size];
        }
    }

    public String getTableName() {
        return TABLE_NAME;
    }

    public String[] getPrimaryKeyNames() {
        return new String[]{"recipe_id"};
    }

    public List<DatabaseField> getFields() {
        return Arrays.asList(new DatabaseField[]{new DatabaseField("recipe_id", "text"), new DatabaseField("text", "text")});
    }

    public List<ForeignKey> getForeignKeys() {
        return Collections.singletonList(new ForeignKey("recipe_id", NutritionRecipe.TABLE_NAME, "id"));
    }

    public String getSelection() {
        return String.format("%s=?", new Object[]{"recipe_id"});
    }

    public List<ContentValues> getForeignKeyValue(String key) {
        return null;
    }

    public ContentValues getValues() {
        ContentValues values = new ContentValues();
        values.put("recipe_id", this.mRecipeId);
        values.put("text", this.mText);
        return values;
    }

    public String[] getSelectionArgs() {
        return new String[]{String.valueOf(this.mRecipeId)};
    }

    public void populateFromCursor(Cursor cursor) {
        this.mRecipeId = cursor.getString(cursor.getColumnIndex("recipe_id"));
        this.mText = cursor.getString(cursor.getColumnIndex("text"));
    }

    public String getRecipeId() {
        return this.mRecipeId;
    }

    public void setRecipeId(String recipeId) {
        this.mRecipeId = recipeId;
    }

    public String getText() {
        return this.mText;
    }

    public void setText(String text) {
        this.mText = text;
    }

    protected RecipeFooter(Parcel in) {
        this.mRecipeId = in.readString();
        this.mText = in.readString();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mRecipeId);
        dest.writeString(this.mText);
    }

    public int describeContents() {
        return 0;
    }
}
