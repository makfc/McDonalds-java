package com.mcdonalds.sdk.modules.models;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.mcdonalds.sdk.connectors.middleware.helpers.MWNutritionConnectorHelper;
import com.mcdonalds.sdk.connectors.middleware.model.RelationItem;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.mcdonalds.sdk.services.data.database.DatabaseModel;
import com.mcdonalds.sdk.services.data.database.DatabaseModel.DatabaseField;
import com.mcdonalds.sdk.services.data.database.DatabaseModel.ForeignKey;
import com.mcdonalds.sdk.utils.ListUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class NutritionRecipe extends DatabaseModel implements Parcelable {
    public static final String COLUMN_ADDITIONAL_ALLERGENS_STRING = "additional_allergens_string";
    public static final String COLUMN_ALLERGENS_STRING = "allergens_string";
    public static final String COLUMN_CAROUSEL_IMAGE_NAME = "carousel_image_name";
    public static final String COLUMN_CAROUSEL_IMAGE_URL = "carousel_image_url";
    public static final String COLUMN_COMPONENTS_STRING = "components_string";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_DISPLAY_ORDER = "display_order";
    public static final String COLUMN_EXTERNAL_ID = "external_id";
    public static final String COLUMN_HERO_IMAGE_NAME = "hero_image_name";
    public static final String COLUMN_HERO_IMAGE_URL = "hero_image_url";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_IMAGE_URL = "image_url";
    public static final String COLUMN_MARKETING_NAME = "marketing_name";
    public static final String COLUMN_MENU_ITEM_NUMBER = "menu_item_number";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_NEEDS_FULL_DETAILS = "needs_full_details";
    public static final String COLUMN_SERVING_SIZE_UNIT = "serving_size_unit";
    public static final String COLUMN_SERVING_SIZE_VALUE = "serving_size_value";
    public static final String COLUMN_THUMBNAIL_IMAGE_NAME = "thumbnail_image_name";
    public static final String COLUMN_THUMBNAIL_IMAGE_URL = "thumbnail_image_url";
    public static final Creator<NutritionRecipe> CREATOR = new C40581();
    public static final String TABLE_NAME = "nutrition_recipe";
    private String mAdditionalAllergensString;
    private String mAdditionalIngredientStatement;
    private List<Allergen> mAllergens;
    private String mAllergensString;
    private ImageInfo mCarouselImage;
    private List<Category> mCategories;
    private String mCategoryMarketingName;
    private List<RecipeComponent> mComponents;
    private String mComponentsString;
    private String mDescription;
    private int mDisplayOrder;
    private String mDoNotShow;
    private int mExternalId;
    private List<String> mFooters;
    private ImageInfo mHeroImage;
    private List<Nutrient> mHighlightedNutrients;
    private String mId;
    private String mImageUrl;
    private String mMarketingName;
    private String mMenuItemNumber;
    private String mName;
    private boolean mNeedsFullDetails;
    private List<Nutrient> mNutrients;
    private List<RelationItem> mRelationItems;
    private String mServingSizeUnit;
    private String mServingSizeValue;
    private List<Nutrient> mStandardNutrients;
    private ImageInfo mThumbnailImage;

    /* renamed from: com.mcdonalds.sdk.modules.models.NutritionRecipe$1 */
    static class C40581 implements Creator<NutritionRecipe> {
        C40581() {
        }

        public NutritionRecipe createFromParcel(Parcel in) {
            return new NutritionRecipe(in);
        }

        public NutritionRecipe[] newArray(int size) {
            return new NutritionRecipe[size];
        }
    }

    public String getTableName() {
        return TABLE_NAME;
    }

    public String[] getPrimaryKeyNames() {
        return new String[]{"id"};
    }

    public List<DatabaseField> getFields() {
        return Arrays.asList(new DatabaseField[]{new DatabaseField("id", "text"), new DatabaseField("external_id", DatabaseModel.TYPE_INTEGER), new DatabaseField("name", "text"), new DatabaseField("marketing_name", "text"), new DatabaseField("description", "text"), new DatabaseField("display_order", DatabaseModel.TYPE_INTEGER), new DatabaseField(COLUMN_MENU_ITEM_NUMBER, "text"), new DatabaseField(COLUMN_NEEDS_FULL_DETAILS, DatabaseModel.TYPE_INTEGER), new DatabaseField(COLUMN_HERO_IMAGE_NAME, "text"), new DatabaseField(COLUMN_HERO_IMAGE_URL, "text"), new DatabaseField("carousel_image_name", "text"), new DatabaseField("carousel_image_url", "text"), new DatabaseField("thumbnail_image_name", "text"), new DatabaseField("thumbnail_image_url", "text"), new DatabaseField("image_url", "text"), new DatabaseField(COLUMN_SERVING_SIZE_UNIT, "text"), new DatabaseField(COLUMN_SERVING_SIZE_VALUE, "text"), new DatabaseField(COLUMN_COMPONENTS_STRING, "text"), new DatabaseField(COLUMN_ALLERGENS_STRING, "text"), new DatabaseField(COLUMN_ADDITIONAL_ALLERGENS_STRING, "text")});
    }

    public List<ForeignKey> getForeignKeys() {
        return Arrays.asList(new ForeignKey[]{new ForeignKey("id", Category.TABLE_NAME, "category_id", 2, null), new ForeignKey("id", Nutrient.TABLE_NAME, "id", 2, null), new ForeignKey("id", RecipeComponent.TABLE_NAME, "product_code", 2, null), new ForeignKey("id", Allergen.TABLE_NAME, "id", 2, null)});
    }

    public String getSelection() {
        return String.format("%s=?", new Object[]{"id"});
    }

    public List<ContentValues> getForeignKeyValue(String key) {
        return null;
    }

    public ContentValues getValues() {
        ContentValues values = new ContentValues();
        values.put("id", this.mId);
        values.put("external_id", Integer.valueOf(this.mExternalId));
        values.put("name", this.mName);
        values.put("marketing_name", this.mMarketingName);
        values.put("description", this.mDescription);
        values.put("display_order", Integer.valueOf(this.mDisplayOrder));
        values.put(COLUMN_MENU_ITEM_NUMBER, this.mMenuItemNumber);
        values.put(COLUMN_NEEDS_FULL_DETAILS, Boolean.valueOf(this.mNeedsFullDetails));
        if (this.mHeroImage != null) {
            values.put(COLUMN_HERO_IMAGE_NAME, this.mHeroImage.getImageName());
            values.put(COLUMN_HERO_IMAGE_URL, this.mHeroImage.getUrl());
        }
        if (this.mCarouselImage != null) {
            values.put("carousel_image_name", this.mCarouselImage.getImageName());
            values.put("carousel_image_url", this.mCarouselImage.getUrl());
        }
        if (this.mThumbnailImage != null) {
            values.put("thumbnail_image_name", this.mThumbnailImage.getImageName());
            values.put("thumbnail_image_url", this.mThumbnailImage.getUrl());
        }
        values.put("image_url", this.mImageUrl);
        values.put(COLUMN_SERVING_SIZE_UNIT, this.mServingSizeUnit);
        values.put(COLUMN_SERVING_SIZE_VALUE, this.mServingSizeValue);
        values.put(COLUMN_COMPONENTS_STRING, this.mComponentsString);
        values.put(COLUMN_ALLERGENS_STRING, this.mAllergensString);
        values.put(COLUMN_ADDITIONAL_ALLERGENS_STRING, this.mAdditionalAllergensString);
        return values;
    }

    public String[] getSelectionArgs() {
        return new String[]{this.mId};
    }

    public void populateFromCursor(Cursor cursor) {
        this.mId = cursor.getString(cursor.getColumnIndex("id"));
        this.mExternalId = cursor.getInt(cursor.getColumnIndex("external_id"));
        this.mName = cursor.getString(cursor.getColumnIndex("name"));
        this.mMarketingName = cursor.getString(cursor.getColumnIndex("marketing_name"));
        this.mDescription = cursor.getString(cursor.getColumnIndex("description"));
        this.mDisplayOrder = cursor.getInt(cursor.getColumnIndex("display_order"));
        this.mMenuItemNumber = cursor.getString(cursor.getColumnIndex(COLUMN_MENU_ITEM_NUMBER));
        this.mNeedsFullDetails = getBooleanForInt(cursor.getInt(cursor.getColumnIndex(COLUMN_NEEDS_FULL_DETAILS)));
        String heroImageName = cursor.getString(cursor.getColumnIndex(COLUMN_HERO_IMAGE_NAME));
        String heroImageUrl = cursor.getString(cursor.getColumnIndex(COLUMN_HERO_IMAGE_URL));
        this.mHeroImage = new ImageInfo();
        this.mHeroImage.setImageName(heroImageName);
        this.mHeroImage.setUrl(heroImageUrl);
        String carouselImageName = cursor.getString(cursor.getColumnIndex("carousel_image_name"));
        String carouselImageUrl = cursor.getString(cursor.getColumnIndex("carousel_image_url"));
        this.mCarouselImage = new ImageInfo();
        this.mCarouselImage.setImageName(carouselImageName);
        this.mCarouselImage.setUrl(carouselImageUrl);
        String thumbnailImageName = cursor.getString(cursor.getColumnIndex("thumbnail_image_name"));
        String thumbnailImageUrl = cursor.getString(cursor.getColumnIndex("thumbnail_image_url"));
        this.mThumbnailImage = new ImageInfo();
        this.mThumbnailImage.setImageName(thumbnailImageName);
        this.mThumbnailImage.setUrl(thumbnailImageUrl);
        this.mImageUrl = cursor.getString(cursor.getColumnIndex("image_url"));
        this.mServingSizeUnit = cursor.getString(cursor.getColumnIndex(COLUMN_SERVING_SIZE_UNIT));
        this.mServingSizeValue = cursor.getString(cursor.getColumnIndex(COLUMN_SERVING_SIZE_VALUE));
        this.mComponentsString = cursor.getString(cursor.getColumnIndex(COLUMN_COMPONENTS_STRING));
        this.mAllergensString = cursor.getString(cursor.getColumnIndex(COLUMN_ALLERGENS_STRING));
        this.mAdditionalAllergensString = cursor.getString(cursor.getColumnIndex(COLUMN_ADDITIONAL_ALLERGENS_STRING));
    }

    public String getAdditionalIngredientStatement() {
        return this.mAdditionalIngredientStatement;
    }

    public void setAdditionalIngredientStatement(String additionalIngredientStatement) {
        this.mAdditionalIngredientStatement = additionalIngredientStatement;
    }

    public String getId() {
        return this.mId;
    }

    public void setId(String id) {
        this.mId = id;
    }

    public int getExternalId() {
        return this.mExternalId;
    }

    public void setExternalId(int externalId) {
        this.mExternalId = externalId;
    }

    public String getName() {
        return this.mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getMarketingName() {
        return this.mMarketingName;
    }

    public void setMarketingName(String marketingName) {
        this.mMarketingName = marketingName;
    }

    public String getDescription() {
        return this.mDescription;
    }

    public void setDescription(String description) {
        this.mDescription = description;
    }

    public int getDisplayOrder() {
        return this.mDisplayOrder;
    }

    public void setDisplayOrder(int displayOrder) {
        this.mDisplayOrder = displayOrder;
    }

    public String getMenuItemNumber() {
        return this.mMenuItemNumber;
    }

    public void setMenuItemNumber(String menuItemNumber) {
        this.mMenuItemNumber = menuItemNumber;
    }

    public boolean getNeedsFullDetails() {
        return this.mNeedsFullDetails;
    }

    public void setNeedsFullDetails(boolean needsFullDetails) {
        this.mNeedsFullDetails = needsFullDetails;
    }

    public List<Category> getCategories() {
        return this.mCategories;
    }

    public void setCategories(List<Category> categories) {
        this.mCategories = categories;
    }

    public Integer getCategoryId() {
        return getCategories() != null ? Integer.valueOf(((Category) getCategories().get(0)).getID()) : null;
    }

    public ImageInfo getHeroImage() {
        return this.mHeroImage;
    }

    public void setHeroImage(ImageInfo heroImage) {
        this.mHeroImage = heroImage;
    }

    public ImageInfo getCarouselImage() {
        return this.mCarouselImage;
    }

    public void setCarouselImage(ImageInfo carouselImage) {
        this.mCarouselImage = carouselImage;
    }

    public ImageInfo getThumbnailImage() {
        return this.mThumbnailImage;
    }

    public void setThumbnailImage(ImageInfo thumbnailImage) {
        this.mThumbnailImage = thumbnailImage;
    }

    public String getImageUrl() {
        return this.mImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.mImageUrl = imageUrl;
    }

    public String getServingSizeValue() {
        return this.mServingSizeValue;
    }

    public void setServingSizeValue(String servingSizeValue) {
        this.mServingSizeValue = servingSizeValue;
    }

    public String getServingSizeString(boolean useMetric) {
        if (TextUtils.isEmpty(this.mServingSizeValue) || TextUtils.isEmpty(this.mServingSizeUnit)) {
            return "";
        }
        return String.format(Locale.getDefault(), "%s %s", new Object[]{this.mServingSizeValue, this.mServingSizeUnit});
    }

    public String getServingSizeString() {
        return this.mServingSizeValue;
    }

    public String getServingSizeUnit() {
        return this.mServingSizeUnit;
    }

    public void setServingSizeUnit(String servingSizeUnit) {
        this.mServingSizeUnit = servingSizeUnit;
    }

    public List<Nutrient> getNutrients() {
        return this.mNutrients;
    }

    public void setNutrients(List<Nutrient> nutrients) {
        this.mNutrients = nutrients;
        buildNutrientLists();
    }

    public List<RecipeComponent> getComponents() {
        return this.mComponents;
    }

    public void setComponents(List<RecipeComponent> components) {
        this.mComponents = components;
    }

    public String getComponentsString() {
        return this.mComponentsString;
    }

    public void setComponentsString(String componentsString) {
        this.mComponentsString = componentsString;
    }

    public List<Allergen> getAllergens() {
        return this.mAllergens;
    }

    public void setAllergens(List<Allergen> allergens) {
        this.mAllergens = allergens;
    }

    public String getAllergensString() {
        return this.mAllergensString;
    }

    public void setAllergensString(String additionalAllergensString) {
        this.mAdditionalAllergensString = additionalAllergensString;
    }

    public void setAdditionalAllergensString(String allergensString) {
        this.mAllergensString = allergensString;
    }

    public String getAdditionalAllergensString() {
        return this.mAdditionalAllergensString;
    }

    public List<String> getFooters() {
        return this.mFooters;
    }

    public void setFooters(List<String> footers) {
        this.mFooters = footers;
    }

    public boolean shouldShow() {
        return this.mDoNotShow != null && this.mDoNotShow.equals(MWNutritionConnectorHelper.CORE_KEY);
    }

    public void setDoNotShow(String doNotShow) {
        this.mDoNotShow = doNotShow;
    }

    public List<Nutrient> getHighlightedNutrients() {
        return this.mHighlightedNutrients;
    }

    public List<Nutrient> getStandardNutrients() {
        return this.mStandardNutrients;
    }

    public String getCategoryMarketingName() {
        return this.mCategoryMarketingName;
    }

    public void setCategoryMarketingName(String categoryMarketingName) {
        this.mCategoryMarketingName = categoryMarketingName;
    }

    private void buildNutrientLists() {
        if (getNutrients() != null) {
            List<Nutrient> highlighted = new ArrayList();
            List<Nutrient> standard = new ArrayList();
            String currentConnector = (String) Configuration.getSharedInstance().getValueForKey("modules.nutritionInfo.connector");
            List<Double> nutrientIds = (List) Configuration.getSharedInstance().getValueForKey("connectors." + currentConnector + ".nutritionInfo.nutrientIds");
            if (!ListUtils.isEmpty(nutrientIds)) {
                Nutrient[] nutrientsSorted = new Nutrient[nutrientIds.size()];
                for (Nutrient nutrient : this.mNutrients) {
                    int index = nutrientIds.indexOf(Double.valueOf(Double.parseDouble(nutrient.getId())));
                    if (index != -1) {
                        nutrientsSorted[index] = nutrient;
                    }
                }
                this.mNutrients.clear();
                for (Nutrient nutrient2 : nutrientsSorted) {
                    if (nutrient2 != null) {
                        this.mNutrients.add(nutrient2);
                    }
                }
            }
            List<String> highlightedNutrients = (List) Configuration.getSharedInstance().getValueForKey("connectors." + currentConnector + ".nutritionInfo.highlightedNutrients");
            if (highlightedNutrients == null) {
                highlightedNutrients = Arrays.asList(new String[]{"Energy", "Calories", "Protein", "Total Fat", "SaturatedFat", "Carbohydrates", "Sodium"});
            }
            for (Nutrient nutrient22 : getNutrients()) {
                double id = Double.parseDouble(nutrient22.getId());
                if (nutrientIds == null || nutrientIds.contains(Double.valueOf(id))) {
                    if (isNutrientInList(highlightedNutrients, nutrient22.getName())) {
                        highlighted.add(nutrient22);
                    } else {
                        standard.add(nutrient22);
                    }
                }
            }
            this.mHighlightedNutrients = highlighted;
            this.mStandardNutrients = standard;
        }
    }

    private boolean isNutrientInList(List<String> highlightedNutrients, String nutrientName) {
        for (String name : highlightedNutrients) {
            if (name.equalsIgnoreCase(nutrientName)) {
                return true;
            }
        }
        return false;
    }

    protected NutritionRecipe(Parcel in) {
        this.mId = in.readString();
        this.mExternalId = in.readInt();
        this.mName = in.readString();
        this.mMarketingName = in.readString();
        this.mDescription = in.readString();
        this.mDisplayOrder = in.readInt();
        this.mMenuItemNumber = in.readString();
        this.mNeedsFullDetails = in.readByte() != (byte) 0;
        this.mCategories = in.createTypedArrayList(Category.CREATOR);
        this.mHeroImage = (ImageInfo) in.readParcelable(ImageInfo.class.getClassLoader());
        this.mCarouselImage = (ImageInfo) in.readParcelable(ImageInfo.class.getClassLoader());
        this.mThumbnailImage = (ImageInfo) in.readParcelable(ImageInfo.class.getClassLoader());
        this.mImageUrl = in.readString();
        this.mServingSizeValue = in.readString();
        this.mServingSizeUnit = in.readString();
        this.mNutrients = in.createTypedArrayList(Nutrient.CREATOR);
        this.mComponents = in.createTypedArrayList(RecipeComponent.CREATOR);
        this.mComponentsString = in.readString();
        this.mAllergens = in.createTypedArrayList(Allergen.CREATOR);
        this.mAllergensString = in.readString();
        this.mAdditionalAllergensString = in.readString();
        this.mFooters = in.createStringArrayList();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mId);
        dest.writeInt(this.mExternalId);
        dest.writeString(this.mName);
        dest.writeString(this.mMarketingName);
        dest.writeString(this.mDescription);
        dest.writeInt(this.mDisplayOrder);
        dest.writeString(this.mMenuItemNumber);
        dest.writeByte((byte) (this.mNeedsFullDetails ? 1 : 0));
        dest.writeTypedList(this.mCategories);
        dest.writeParcelable(this.mHeroImage, flags);
        dest.writeParcelable(this.mCarouselImage, flags);
        dest.writeParcelable(this.mThumbnailImage, flags);
        dest.writeString(this.mImageUrl);
        dest.writeString(this.mServingSizeValue);
        dest.writeString(this.mServingSizeUnit);
        dest.writeTypedList(this.mNutrients);
        dest.writeTypedList(this.mComponents);
        dest.writeString(this.mComponentsString);
        dest.writeTypedList(this.mAllergens);
        dest.writeString(this.mAllergensString);
        dest.writeString(this.mAdditionalAllergensString);
        dest.writeStringList(this.mFooters);
    }

    public int describeContents() {
        return 0;
    }

    public List<RelationItem> getRelationItems() {
        return this.mRelationItems;
    }

    public void setRelationItems(List<RelationItem> relationItems) {
        this.mRelationItems = relationItems;
    }
}
