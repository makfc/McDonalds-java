package com.mcdonalds.sdk.modules.models;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import android.util.SparseArray;
import com.mcdonalds.sdk.connectors.middleware.helpers.MWNutritionConnectorHelper;
import com.mcdonalds.sdk.connectors.middleware.model.MWOpeningHourItem;
import com.mcdonalds.sdk.connectors.middleware.model.MWPOD;
import com.mcdonalds.sdk.modules.models.Order.PriceType;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.mcdonalds.sdk.services.data.database.DatabaseModel;
import com.mcdonalds.sdk.services.data.database.DatabaseModel.DatabaseField;
import com.mcdonalds.sdk.services.data.database.DatabaseModel.ForeignKey;
import com.mcdonalds.sdk.utils.ListUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

public class Product extends DatabaseModel implements Parcelable, Comparable<Product> {
    public static final String COLUMN_ACCEPTS_LIGHT = "accepts_light";
    public static final String COLUMN_ADVERTISABLE_BASE_PRODUCT_ID = "advertisable_base_product_id";
    public static final String COLUMN_CAROUSEL_IMAGE_NAME = "carousel_image_name";
    public static final String COLUMN_CAROUSEL_IMAGE_URL = "carousel_image_url";
    public static final String COLUMN_CUSTOMER_FRIENDLY = "customer_friendly";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_DISPLAY_ORDER = "display_order";
    public static final String COLUMN_DO_NOT_SHOW = "do_not_show";
    public static final String COLUMN_EXTERNAL_ID = "external_id";
    public static final String COLUMN_HAS_OFFERS = "has_offers";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_IMAGE_URL = "image_url";
    public static final String COLUMN_LONG_NAME = "long_name";
    public static final String COLUMN_MAX_QTTY_ALLOWED_PER_ORDER = "max_qtty_allowed_per_order";
    public static final String COLUMN_MENU_TYPE_ID = "menu_type_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PRODUCT_TYPE = "product_type";
    public static final String COLUMN_RECIPE_ALLERGENS = "recipe_allergens";
    public static final String COLUMN_RECIPE_COMPONENTS_STRING = "recipe_components_string";
    public static final String COLUMN_RECIPE_ID = "recipe_id";
    public static final String COLUMN_SHORT_NAME = "short_name";
    public static final String COLUMN_THUMBNAIL_IMAGE_NAME = "thumbnail_image_name";
    public static final String COLUMN_THUMBNAIL_IMAGE_URL = "thumbnail_image_url";
    public static final String COLUMN_TIME_RESTRICTION_FROM = "time_restriction_from";
    public static final String COLUMN_TIME_RESTRICTION_TO = "time_restriction_to";
    public static final String COLUMN_VALID = "valid";
    public static final Creator<Product> CREATOR = new C40783();
    public static final String TABLE_NAME = "products";
    private boolean mAcceptsLight;
    private int mAdvertisableBaseProductId;
    private Product mAdvertisableProduct;
    private AdvertisablePromotion mAdvertisablePromotion;
    private int mAdvertisableWeekDay = -1;
    private double mBasePriceDelivery;
    private double mBasePriceEatIn;
    private double mBasePriceTakeOut;
    private ImageInfo mCarouselImage;
    private List<Category> mCategories;
    private List<Integer> mCategoryIds;
    private List<Ingredient> mChoices;
    private List<Ingredient> mComments;
    private Boolean mCustomerFriendly;
    private String mDescription;
    private List<ProductDimension> mDimensions;
    private Category mDisplayCategory;
    private Integer mDisplayOrder;
    private String mDoNotShow;
    private double mEnergy;
    private List<Integer> mExtendedMenuTypeIDs;
    private Integer mExternalId;
    private List<Ingredient> mExtras;
    private List<String> mFooters;
    private Boolean mHasChoice;
    private Boolean mHasNonSingleChoiceChoice;
    private boolean mHasOffers;
    private ImageInfo mHeroImage;
    private List<Nutrient> mHighlightedNutrients;
    private List<Nutrient> mHighlightedNutrientsSortedById;
    private String mId;
    private String mImageUrl;
    private List<Ingredient> mIngredients;
    private Boolean mIsSingleChoice;
    private String mLongName;
    private String mMarketingName;
    private Integer mMaxQttyAllowedPerOrder;
    private String mMenuItemNumber;
    private Integer mMenuTypeID;
    private List<MenuType> mMenuTypes;
    private String mName;
    private Boolean mNeedsFullDetails;
    private List<Nutrient> mNutrients;
    private List<Pod> mPODs;
    private double mPriceDelivery;
    private double mPriceEatIn;
    private double mPriceTakeOut;
    private ProductType mProductType;
    private String mProductUnit;
    private List<Allergen> mRecipeAllergens;
    private String mRecipeAllergensString;
    private List<RecipeComponent> mRecipeComponents;
    private String mRecipeComponentsString;
    private Integer mRecipeId;
    private Nutrient mServingSizeEnglish;
    private Nutrient mServingSizeMetric;
    private String mShortName;
    private List<Nutrient> mStandardNutrients;
    private List<Nutrient> mStandardNutrientsSortedById;
    private StoreProduct mStoreProduct;
    private ImageInfo mThumbnailImage;
    private TimeRestriction mTimeRestriction;
    private List<TimeRestriction> mTimeRestrictions;
    private Boolean mValid;
    private double mkCal;

    /* renamed from: com.mcdonalds.sdk.modules.models.Product$1 */
    class C40761 implements Comparator<Nutrient> {
        C40761() {
        }

        public int compare(Nutrient lhs, Nutrient rhs) {
            return Integer.parseInt(lhs.getId()) - Integer.parseInt(rhs.getId());
        }
    }

    /* renamed from: com.mcdonalds.sdk.modules.models.Product$2 */
    class C40772 implements Comparator<Nutrient> {
        C40772() {
        }

        public int compare(Nutrient lhs, Nutrient rhs) {
            return Integer.parseInt(lhs.getId()) - Integer.parseInt(rhs.getId());
        }
    }

    /* renamed from: com.mcdonalds.sdk.modules.models.Product$3 */
    static class C40783 implements Creator<Product> {
        C40783() {
        }

        public Product createFromParcel(Parcel source) {
            return new Product(source);
        }

        public Product[] newArray(int size) {
            return new Product[size];
        }
    }

    public enum ProductType {
        Product(Integer.valueOf(0)),
        Ingredient(Integer.valueOf(1)),
        Meal(Integer.valueOf(2)),
        Comment(Integer.valueOf(4)),
        GiftCertificate(Integer.valueOf(5)),
        DeliveryFee(Integer.valueOf(7)),
        Choice(Integer.valueOf(9)),
        NonFood(Integer.valueOf(10));
        
        private static SparseArray<ProductType> mProducts;
        private Integer mCode;

        static {
            mProducts = null;
        }

        public static ProductType valueToProductType(Integer intType) {
            if (mProducts == null) {
                mProducts = new SparseArray();
                for (ProductType productType : values()) {
                    mProducts.put(productType.integerValue().intValue(), productType);
                }
            }
            return (ProductType) mProducts.get(intType.intValue());
        }

        private ProductType(Integer code) {
            this.mCode = code;
        }

        public Integer integerValue() {
            return this.mCode;
        }
    }

    public static Product getChoiceWithinChoiceFiller() {
        Product product = new Product();
        product.setId("0");
        product.setProductType(ProductType.Choice);
        product.setPODs(new ArrayList());
        return product;
    }

    public String getTableName() {
        return TABLE_NAME;
    }

    public String[] getPrimaryKeyNames() {
        return new String[]{"external_id"};
    }

    public List<DatabaseField> getFields() {
        return Arrays.asList(new DatabaseField[]{new DatabaseField("external_id", DatabaseModel.TYPE_INTEGER), new DatabaseField("recipe_id", DatabaseModel.TYPE_INTEGER), new DatabaseField("id", "text"), new DatabaseField("display_order", DatabaseModel.TYPE_INTEGER), new DatabaseField("name", "text"), new DatabaseField("short_name", "text"), new DatabaseField("long_name", "text"), new DatabaseField(COLUMN_MENU_TYPE_ID, DatabaseModel.TYPE_INTEGER), new DatabaseField(COLUMN_PRODUCT_TYPE, DatabaseModel.TYPE_INTEGER), new DatabaseField("image_url", "text"), new DatabaseField("carousel_image_name", "text"), new DatabaseField("carousel_image_url", "text"), new DatabaseField("thumbnail_image_name", "text"), new DatabaseField("thumbnail_image_url", "text"), new DatabaseField(COLUMN_RECIPE_COMPONENTS_STRING, "text"), new DatabaseField(COLUMN_RECIPE_ALLERGENS, "text"), new DatabaseField("description", "text"), new DatabaseField("do_not_show", "text"), new DatabaseField("valid", DatabaseModel.TYPE_INTEGER), new DatabaseField("customer_friendly", DatabaseModel.TYPE_INTEGER), new DatabaseField(COLUMN_HAS_OFFERS, DatabaseModel.TYPE_INTEGER), new DatabaseField(COLUMN_ACCEPTS_LIGHT, DatabaseModel.TYPE_INTEGER), new DatabaseField(COLUMN_MAX_QTTY_ALLOWED_PER_ORDER, DatabaseModel.TYPE_INTEGER), new DatabaseField(COLUMN_TIME_RESTRICTION_FROM, "text"), new DatabaseField(COLUMN_TIME_RESTRICTION_TO, "text")});
    }

    public List<ForeignKey> getForeignKeys() {
        return Collections.singletonList(new ForeignKey(COLUMN_MENU_TYPE_ID, MenuType.TABLE_NAME, "id"));
    }

    public List<ContentValues> getForeignKeyValue(String table) {
        return null;
    }

    public ContentValues getValues() {
        ContentValues values = new ContentValues();
        values.put("external_id", this.mExternalId);
        values.put("recipe_id", this.mRecipeId);
        values.put("id", this.mId);
        values.put("display_order", this.mDisplayOrder);
        values.put("name", this.mName);
        values.put("short_name", this.mShortName);
        values.put("long_name", this.mLongName);
        values.put(COLUMN_MENU_TYPE_ID, this.mMenuTypeID);
        if (this.mProductType != null) {
            values.put(COLUMN_PRODUCT_TYPE, this.mProductType.integerValue());
        } else {
            values.putNull(COLUMN_PRODUCT_TYPE);
        }
        values.put("image_url", this.mImageUrl);
        if (this.mCarouselImage != null) {
            values.put("carousel_image_name", this.mCarouselImage.getImageName());
            values.put("carousel_image_url", this.mCarouselImage.getUrl());
        } else {
            values.putNull("carousel_image_name");
            values.putNull("carousel_image_url");
        }
        if (this.mThumbnailImage != null) {
            values.put("thumbnail_image_name", this.mThumbnailImage.getImageName());
            values.put("thumbnail_image_url", this.mThumbnailImage.getUrl());
        } else {
            values.putNull("thumbnail_image_name");
            values.putNull("thumbnail_image_url");
        }
        values.put(COLUMN_RECIPE_COMPONENTS_STRING, this.mRecipeComponentsString);
        values.put(COLUMN_RECIPE_ALLERGENS, this.mRecipeAllergensString);
        values.put("description", this.mDescription);
        values.put("do_not_show", this.mDoNotShow);
        values.put("valid", this.mValid);
        values.put("customer_friendly", this.mCustomerFriendly);
        values.put(COLUMN_HAS_OFFERS, Boolean.valueOf(this.mHasOffers));
        values.put(COLUMN_ACCEPTS_LIGHT, Boolean.valueOf(this.mAcceptsLight));
        values.put(COLUMN_MAX_QTTY_ALLOWED_PER_ORDER, this.mMaxQttyAllowedPerOrder);
        if (this.mTimeRestrictions != null) {
            boolean first = true;
            StringBuilder fromList = new StringBuilder();
            StringBuilder toList = new StringBuilder();
            for (TimeRestriction r : this.mTimeRestrictions) {
                if (r != null) {
                    String from = r.getFromTime();
                    String to = r.getToTime();
                    if (!(from == null || to == null)) {
                        if (first) {
                            first = false;
                        } else {
                            fromList.append(",");
                            toList.append(",");
                        }
                        fromList.append(from);
                        toList.append(to);
                    }
                }
            }
            values.put(COLUMN_TIME_RESTRICTION_FROM, fromList.toString());
            values.put(COLUMN_TIME_RESTRICTION_TO, toList.toString());
        } else {
            values.putNull(COLUMN_TIME_RESTRICTION_FROM);
            values.putNull(COLUMN_TIME_RESTRICTION_TO);
        }
        return values;
    }

    public String getSelection() {
        return String.format("%s=?", new Object[]{"external_id"});
    }

    public String[] getSelectionArgs() {
        return new String[]{Integer.toString(this.mExternalId.intValue())};
    }

    public void populateFromCursor(Cursor cursor) {
        populateFromCursor(cursor, null);
    }

    public void populateFromCursor(Cursor cursor, String alias) {
        String prefix = "";
        if (!(alias == null || alias.isEmpty())) {
            prefix = String.format("%s_", new Object[]{alias});
        }
        this.mExternalId = Integer.valueOf(cursor.getInt(cursor.getColumnIndex(prefix + "external_id")));
        this.mRecipeId = Integer.valueOf(cursor.getInt(cursor.getColumnIndex(prefix + "recipe_id")));
        this.mId = cursor.getString(cursor.getColumnIndex(prefix + "id"));
        this.mDisplayOrder = Integer.valueOf(cursor.getInt(cursor.getColumnIndex(prefix + "display_order")));
        this.mName = cursor.getString(cursor.getColumnIndex(prefix + "name"));
        this.mShortName = cursor.getString(cursor.getColumnIndex(prefix + "short_name"));
        this.mLongName = cursor.getString(cursor.getColumnIndex(prefix + "long_name"));
        this.mMenuTypeID = Integer.valueOf(cursor.getInt(cursor.getColumnIndex(prefix + COLUMN_MENU_TYPE_ID)));
        int productTypeIndex = cursor.getColumnIndex(prefix + COLUMN_PRODUCT_TYPE);
        if (cursor.getType(productTypeIndex) != 0) {
            this.mProductType = ProductType.valueToProductType(Integer.valueOf(cursor.getInt(productTypeIndex)));
        }
        this.mImageUrl = cursor.getString(cursor.getColumnIndex(prefix + "image_url"));
        String carouselImageName = cursor.getString(cursor.getColumnIndex(prefix + "carousel_image_name"));
        String carouselImageUrl = cursor.getString(cursor.getColumnIndex(prefix + "carousel_image_url"));
        this.mCarouselImage = new ImageInfo();
        this.mCarouselImage.setImageName(carouselImageName);
        this.mCarouselImage.setUrl(carouselImageUrl);
        String thumbnailImageName = cursor.getString(cursor.getColumnIndex(prefix + "thumbnail_image_name"));
        String thumbnailImageUrl = cursor.getString(cursor.getColumnIndex(prefix + "thumbnail_image_url"));
        this.mThumbnailImage = new ImageInfo();
        this.mThumbnailImage.setImageName(thumbnailImageName);
        this.mThumbnailImage.setUrl(thumbnailImageUrl);
        this.mRecipeComponentsString = cursor.getString(cursor.getColumnIndex(prefix + COLUMN_RECIPE_COMPONENTS_STRING));
        this.mRecipeAllergensString = cursor.getString(cursor.getColumnIndex(prefix + COLUMN_RECIPE_ALLERGENS));
        this.mDescription = cursor.getString(cursor.getColumnIndex(prefix + "description"));
        this.mDoNotShow = cursor.getString(cursor.getColumnIndex(prefix + "do_not_show"));
        this.mValid = Boolean.valueOf(getBooleanForInt(cursor.getInt(cursor.getColumnIndex(prefix + "valid"))));
        int advertisableBasProductIdIndex = cursor.getColumnIndex(prefix + COLUMN_ADVERTISABLE_BASE_PRODUCT_ID);
        if (advertisableBasProductIdIndex != -1) {
            this.mAdvertisableBaseProductId = cursor.getInt(advertisableBasProductIdIndex);
        }
        this.mCustomerFriendly = Boolean.valueOf(getBooleanForInt(cursor.getInt(cursor.getColumnIndex(prefix + "customer_friendly"))));
        this.mHasOffers = getBooleanForInt(cursor.getInt(cursor.getColumnIndex(prefix + COLUMN_HAS_OFFERS)));
        this.mAcceptsLight = getBooleanForInt(cursor.getInt(cursor.getColumnIndex(prefix + COLUMN_ACCEPTS_LIGHT)));
        this.mMaxQttyAllowedPerOrder = Integer.valueOf(cursor.getInt(cursor.getColumnIndex(prefix + COLUMN_MAX_QTTY_ALLOWED_PER_ORDER)));
        String from = cursor.getString(cursor.getColumnIndex(prefix + COLUMN_TIME_RESTRICTION_FROM));
        String to = cursor.getString(cursor.getColumnIndex(prefix + COLUMN_TIME_RESTRICTION_TO));
        this.mTimeRestrictions = new ArrayList();
        if (from == null || to == null) {
            this.mTimeRestriction = null;
            return;
        }
        String[] fromList = from.split(",");
        String[] toList = to.split(",");
        if (fromList.length == toList.length) {
            int i = 0;
            while (i < fromList.length) {
                if (!(fromList[i].isEmpty() || toList[i].isEmpty())) {
                    this.mTimeRestrictions.add(new TimeRestriction(fromList[i], toList[i]));
                }
                i++;
            }
        }
        if (this.mTimeRestrictions.size() > 0) {
            this.mTimeRestriction = (TimeRestriction) this.mTimeRestrictions.get(0);
        }
    }

    public Integer getDisplayOrderForCategory(int categoryId) {
        if (getCategories() == null) {
            return null;
        }
        for (Category category : getCategories()) {
            if (categoryId == category.getID()) {
                return Integer.valueOf(category.getDisplayOrder());
            }
        }
        return null;
    }

    public Nutrient getEnergyNutrient() {
        if (getNutrients() != null) {
            for (Nutrient nutrient : getNutrients()) {
                if (nutrient.getName().equals("Energy")) {
                    return nutrient;
                }
            }
        }
        return null;
    }

    public void setEnergy(double energy) {
        this.mEnergy = energy;
    }

    public Double getEnergy() {
        return Double.valueOf(this.mEnergy);
    }

    public void setKCal(double kCal) {
        this.mkCal = kCal;
    }

    public Double getKCal() {
        return Double.valueOf(this.mkCal);
    }

    public Product(String recipeId, String name, List<Nutrient> nutrients) {
        this.mId = recipeId;
        this.mName = name;
        this.mNutrients = nutrients;
    }

    public void update(Product product) {
        if (product.mExternalId.equals(this.mExternalId)) {
            this.mRecipeId = product.mRecipeId;
            this.mId = product.mId;
            this.mName = product.mName;
            this.mShortName = product.mShortName;
            this.mLongName = product.mLongName;
            this.mMenuTypeID = product.mMenuTypeID;
            this.mProductType = product.mProductType;
            this.mImageUrl = product.mImageUrl;
            this.mCarouselImage = product.mCarouselImage;
            this.mThumbnailImage = product.mThumbnailImage;
            this.mRecipeComponentsString = product.mRecipeComponentsString;
            this.mRecipeAllergensString = product.mRecipeAllergensString;
            this.mDescription = product.mDescription;
            this.mDoNotShow = product.mDoNotShow;
            this.mValid = product.mValid;
            this.mCustomerFriendly = product.mCustomerFriendly;
            this.mIsSingleChoice = product.mIsSingleChoice;
            this.mHasChoice = product.mHasChoice;
            this.mHasOffers = product.mHasOffers;
            this.mAcceptsLight = product.mAcceptsLight;
            this.mMaxQttyAllowedPerOrder = product.mMaxQttyAllowedPerOrder;
            this.mTimeRestriction = product.mTimeRestriction;
            if (!(product.mName == null || product.mName.isEmpty())) {
                this.mName = product.mName;
            }
            if (!(product.mShortName == null || product.mShortName.isEmpty())) {
                this.mShortName = product.mShortName;
            }
            if (!(product.mLongName == null || product.mLongName.isEmpty())) {
                this.mLongName = product.mLongName;
            }
            List<MenuType> menuTypes = product.getMenuTypes();
            if (!ListUtils.isEmpty(menuTypes)) {
                setMenuTypes(menuTypes);
            }
            if (product.mPODs != null) {
                this.mPODs = product.mPODs;
            }
            List<ProductDimension> dimensions = product.getDimensions();
            if (dimensions != null) {
                setDimensions(dimensions);
            }
            List<Ingredient> ingredients = product.getIngredients();
            if (ingredients != null) {
                setIngredients(ingredients);
            }
            List<Ingredient> extras = product.getExtras();
            if (extras != null) {
                setExtras(extras);
            }
            List<Ingredient> choices = product.getChoices();
            if (choices != null) {
                setChoices(choices);
            }
        }
    }

    public String getId() {
        return this.mId;
    }

    public void setId(String id) {
        this.mId = id;
    }

    public Integer getRecipeId() {
        return this.mRecipeId;
    }

    public void setRecipeId(Integer recipeId) {
        this.mRecipeId = recipeId;
    }

    public Integer getExternalId() {
        return this.mExternalId;
    }

    public void setExternalId(Integer externalId) {
        this.mExternalId = externalId;
    }

    public Integer getCategoryId() {
        return getCategories() != null ? Integer.valueOf(((Category) getCategories().get(0)).getID()) : null;
    }

    public String getMenuItemNumber() {
        return this.mMenuItemNumber;
    }

    public void setMenuItemNumber(String menuItemNumber) {
        this.mMenuItemNumber = menuItemNumber;
    }

    public List<String> getFooters() {
        return this.mFooters;
    }

    public void setFooters(List<String> footers) {
        this.mFooters = footers;
    }

    public String getName() {
        return this.mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public List<Nutrient> getNutrients() {
        return this.mNutrients;
    }

    public void setNutrients(List<Nutrient> nutrients) {
        this.mNutrients = nutrients;
    }

    public List<Nutrient> getHighlightedNutrients() {
        if (this.mHighlightedNutrients == null) {
            buildNutrientLists();
        }
        return this.mHighlightedNutrients;
    }

    public void setHighlightedNutrients(List<Nutrient> highlightedNutrients) {
        this.mHighlightedNutrients = highlightedNutrients;
    }

    public List<Nutrient> getStandardNutrients() {
        if (this.mStandardNutrients == null) {
            buildNutrientLists();
        }
        return this.mStandardNutrients;
    }

    public List<Nutrient> getStandardNutrientsSortedById() {
        if (this.mStandardNutrientsSortedById == null) {
            this.mStandardNutrientsSortedById = getStandardNutrients();
        }
        Collections.sort(this.mStandardNutrientsSortedById, new C40761());
        return this.mStandardNutrientsSortedById;
    }

    public List<Nutrient> getHighlightedNutrientsSortedById() {
        if (this.mHighlightedNutrientsSortedById == null) {
            this.mHighlightedNutrientsSortedById = getHighlightedNutrients();
        }
        Collections.sort(this.mHighlightedNutrientsSortedById, new C40772());
        return this.mHighlightedNutrientsSortedById;
    }

    public void setStandardNutrients(List<Nutrient> standardNutrients) {
        this.mStandardNutrients = standardNutrients;
    }

    public Nutrient getServingSizeEnglish() {
        return this.mServingSizeEnglish;
    }

    public void setServingSizeEnglish(Nutrient servingSizeEnglish) {
        this.mServingSizeEnglish = servingSizeEnglish;
    }

    public Nutrient getServingSizeMetric() {
        return this.mServingSizeMetric;
    }

    public void setServingSizeMetric(Nutrient servingSizeMetric) {
        this.mServingSizeMetric = servingSizeMetric;
    }

    public String getServingSizeString(boolean useMetric) {
        Nutrient servingSize;
        if (useMetric) {
            servingSize = getServingSizeMetric();
        } else {
            servingSize = getServingSizeEnglish();
        }
        if (servingSize == null || servingSize.getValue() == null) {
            return null;
        }
        String unit = servingSize.getUnit();
        if (unit == null) {
            unit = "";
        }
        return String.format(Locale.getDefault(), "%s %s", new Object[]{servingSize.getValue(), unit});
    }

    public ImageInfo getThumbnailImage() {
        return this.mThumbnailImage;
    }

    public void setThumbnailImage(ImageInfo thumbnailImage) {
        this.mThumbnailImage = thumbnailImage;
    }

    public ImageInfo getCarouselImage() {
        return this.mCarouselImage;
    }

    public void setCarouselImage(ImageInfo carouselImage) {
        this.mCarouselImage = carouselImage;
    }

    public List<RecipeComponent> getRecipeComponents() {
        return this.mRecipeComponents;
    }

    public void setRecipeComponents(List<RecipeComponent> recipeComponents) {
        this.mRecipeComponents = recipeComponents;
    }

    public String getRecipeComponentsString() {
        return this.mRecipeComponentsString;
    }

    public void setRecipeComponentsString(String recipeComponentsString) {
        this.mRecipeComponentsString = recipeComponentsString;
    }

    public String getDoNotShow() {
        return this.mDoNotShow;
    }

    public boolean shouldShow() {
        return this.mDoNotShow != null && this.mDoNotShow.equals(MWNutritionConnectorHelper.CORE_KEY);
    }

    public void setDoNotShow(String doNotShow) {
        this.mDoNotShow = doNotShow;
    }

    public Boolean isAdvertisable() {
        return Boolean.valueOf(getAdvertisableProduct() != null);
    }

    public Product getAdvertisableProduct() {
        return this.mAdvertisableProduct;
    }

    public void setAdvertisableProduct(Product advertisableProduct) {
        this.mAdvertisableProduct = advertisableProduct;
    }

    public int getAdvertisableBaseProductId() {
        return this.mAdvertisableBaseProductId;
    }

    public Boolean getValid() {
        return this.mValid;
    }

    public void setValid(Boolean valid) {
        this.mValid = valid;
    }

    public Boolean getCustomerFriendly() {
        return this.mCustomerFriendly;
    }

    public void setCustomerFriendly(Boolean customerFriendly) {
        this.mCustomerFriendly = customerFriendly;
    }

    public Boolean getNeedsFullDetails() {
        return this.mNeedsFullDetails;
    }

    public void setNeedsFullDetails(Boolean needsFullDetails) {
        this.mNeedsFullDetails = needsFullDetails;
    }

    public Boolean isSingleChoice() {
        return this.mIsSingleChoice;
    }

    public void setSingleChoice(Boolean mIsSingleChoice) {
        this.mIsSingleChoice = mIsSingleChoice;
    }

    public Boolean hasChoice() {
        return this.mHasChoice;
    }

    public void setHasChoice(Boolean hasChoice) {
        this.mHasChoice = hasChoice;
    }

    public Boolean hasNonSingleChoiceChoice() {
        return this.mHasNonSingleChoiceChoice;
    }

    public void setHasNonSingleChoiceChoice(Boolean hasNonSingleChoiceChoice) {
        this.mHasNonSingleChoiceChoice = hasNonSingleChoiceChoice;
    }

    public List<ProductDimension> getDimensions() {
        return this.mDimensions;
    }

    public void setDimensions(List<ProductDimension> dimensions) {
        this.mDimensions = dimensions;
    }

    public List<Ingredient> getIngredients() {
        return this.mIngredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.mIngredients = ingredients;
    }

    public List<Ingredient> getExtras() {
        return this.mExtras;
    }

    public void setExtras(List<Ingredient> extras) {
        this.mExtras = extras;
    }

    public List<Ingredient> getChoices() {
        return this.mChoices;
    }

    public void setChoices(List<Ingredient> choices) {
        this.mChoices = choices;
    }

    public List<Ingredient> getComments() {
        return this.mComments;
    }

    public void setComments(List<Ingredient> comments) {
        this.mComments = comments;
    }

    public double getPrice(PriceType priceType) {
        double price = getPriceEatIn();
        if (priceType == null) {
            return price;
        }
        if (priceType == PriceType.TakeOut) {
            return getPriceTakeOut();
        }
        if (priceType == PriceType.Delivery) {
            return getPriceDelivery();
        }
        return price;
    }

    public double getPriceEatIn() {
        return this.mPriceEatIn;
    }

    public void setPriceEatIn(double priceEatIn) {
        this.mPriceEatIn = priceEatIn;
    }

    public double getPriceTakeOut() {
        return this.mPriceTakeOut;
    }

    public void setPriceTakeOut(double priceTakeOut) {
        this.mPriceTakeOut = priceTakeOut;
    }

    public double getPriceDelivery() {
        return this.mPriceDelivery;
    }

    public void setPriceDelivery(double priceDelivery) {
        this.mPriceDelivery = priceDelivery;
    }

    public double getBasePrice(PriceType priceType) {
        double price = getBasePriceEatIn();
        if (priceType == null) {
            return price;
        }
        if (priceType == PriceType.TakeOut) {
            return getBasePriceTakeOut();
        }
        if (priceType == PriceType.Delivery) {
            return getBasePriceDelivery();
        }
        return price;
    }

    public double getBasePriceEatIn() {
        return this.mBasePriceEatIn;
    }

    public void setBasePriceEatIn(double mBasePriceEatIn) {
        this.mBasePriceEatIn = mBasePriceEatIn;
    }

    public double getBasePriceTakeOut() {
        return this.mBasePriceTakeOut;
    }

    public void setBasePriceTakeOut(double mBasePriceTakeOut) {
        this.mBasePriceTakeOut = mBasePriceTakeOut;
    }

    public double getBasePriceDelivery() {
        return this.mBasePriceDelivery;
    }

    public void setBasePriceDelivery(double mBasePriceDelivery) {
        this.mBasePriceDelivery = mBasePriceDelivery;
    }

    public ProductType getProductType() {
        return this.mProductType;
    }

    public void setProductType(ProductType productType) {
        this.mProductType = productType;
    }

    public String getShortName() {
        return this.mShortName;
    }

    public void setShortName(String shortName) {
        this.mShortName = shortName;
    }

    public String getLongName() {
        return this.mLongName != null ? this.mLongName : this.mName;
    }

    public void setLongName(String longName) {
        this.mLongName = longName;
    }

    public List<MenuType> getMenuTypes() {
        return this.mMenuTypes;
    }

    public void setMenuTypes(List<MenuType> menuTypes) {
        this.mMenuTypes = menuTypes;
    }

    public void buildNutrientLists() {
        if (getNutrients() != null) {
            List<Nutrient> highlighted = new ArrayList();
            List<Nutrient> standard = new ArrayList();
            String currentConnector = (String) Configuration.getSharedInstance().getValueForKey("modules.nutritionInfo.connector");
            List<Double> nutrientIds = (List) Configuration.getSharedInstance().getValueForKey("connectors." + currentConnector + ".nutritionInfo.nutrientIds");
            List<String> highlightedNutrients = (List) Configuration.getSharedInstance().getValueForKey("connectors." + currentConnector + ".nutritionInfo.highlightedNutrients");
            if (highlightedNutrients == null) {
                highlightedNutrients = Arrays.asList(new String[]{"Energy", "Calories", "Protein", "Total Fat", "SaturatedFat", "Carbohydrates", "Sodium"});
            }
            for (Nutrient nutrient : getNutrients()) {
                double id = Double.parseDouble(nutrient.getId());
                if (nutrientIds == null || nutrientIds.contains(Double.valueOf(id))) {
                    if (highlightedNutrients.contains(nutrient.getName())) {
                        highlighted.add(nutrient);
                    } else {
                        standard.add(nutrient);
                    }
                }
            }
            this.mHighlightedNutrients = highlighted;
            this.mStandardNutrients = standard;
        }
    }

    public String toString() {
        return "Recipe{mId=\"" + this.mId + "\", mRecipeId=" + this.mRecipeId + ", mExternalId=" + this.mExternalId + ", mCategoryId=" + getCategoryId() + ", mMenuItemNumber=\"" + this.mMenuItemNumber + "\", mFooters=" + this.mFooters + ", mName=\"" + this.mName + "\", mNutrients=" + this.mNutrients + ", mHighlightedNutrients=" + this.mHighlightedNutrients + ", mStandardNutrients=" + this.mStandardNutrients + ", mServingSizeEnglish=" + this.mServingSizeEnglish + ", mServingSizeMetric=" + this.mServingSizeMetric + ", mThumbnailImage=" + this.mThumbnailImage + ", mCarouselImage=" + this.mCarouselImage + ", mRecipeComponents=" + this.mRecipeComponents + ", mDoNotShow=\"" + this.mDoNotShow + "\", mValid=" + this.mValid + ", mCustomerFriendly=" + this.mCustomerFriendly + ", mDimensions=" + this.mDimensions + ", mIngredients=" + this.mIngredients + ", mExtras=" + this.mExtras + ", mChoices=" + this.mChoices + ", mComments=" + this.mComments + "}";
    }

    public String getImageUrl() {
        return this.mImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.mImageUrl = imageUrl;
    }

    public Integer getMenuTypeID() {
        return this.mMenuTypeID;
    }

    public void setMenuTypeID(Integer menuTypeID) {
        this.mMenuTypeID = menuTypeID;
    }

    public List<Integer> getExtendedMenuTypeIDs() {
        List<MenuType> menuTypes = getMenuTypes();
        List<Integer> extendedMenuTypeIds;
        if (menuTypes != null) {
            extendedMenuTypeIds = new ArrayList();
            for (MenuType menuType : menuTypes) {
                extendedMenuTypeIds.add(Integer.valueOf(menuType.getID()));
            }
            return extendedMenuTypeIds;
        } else if (this.mMenuTypeID == null) {
            return this.mExtendedMenuTypeIDs;
        } else {
            extendedMenuTypeIds = new ArrayList();
            extendedMenuTypeIds.add(this.mMenuTypeID);
            return extendedMenuTypeIds;
        }
    }

    public void setExtendedMenuTypeIDs(List<Integer> extendedMenuTypeIDs) {
        this.mExtendedMenuTypeIDs = extendedMenuTypeIDs;
    }

    public String getMarketingName() {
        return this.mMarketingName;
    }

    public void setMarketingName(String marketingName) {
        this.mMarketingName = marketingName;
    }

    public Integer getDisplayOrder() {
        return this.mDisplayOrder;
    }

    public void setDisplayOrder(Integer displayOrder) {
        this.mDisplayOrder = displayOrder;
    }

    public List<Category> getCategories() {
        return this.mCategories;
    }

    public void setCategories(List<Category> categories) {
        this.mCategories = categories;
    }

    public List<Allergen> getRecipeAllergens() {
        return this.mRecipeAllergens;
    }

    public void setRecipeAllergens(List<Allergen> recipeAllergens) {
        this.mRecipeAllergens = recipeAllergens;
    }

    public String getRecipeAllergensString() {
        return this.mRecipeAllergensString;
    }

    public void setRecipeAllergensString(String allergens) {
        this.mRecipeAllergensString = allergens;
    }

    public String getDescription() {
        return this.mDescription;
    }

    public void setDescription(String description) {
        this.mDescription = description;
    }

    public void setHeroImage(ImageInfo heroImage) {
        this.mHeroImage = heroImage;
    }

    public ImageInfo getHeroImage() {
        return this.mHeroImage;
    }

    public void setPODObjects(List<Pod> PODs) {
        this.mPODs = PODs;
    }

    public void setPODs(List<MWPOD> PODs) {
        this.mPODs = new ArrayList();
        if (PODs != null) {
            int size = PODs.size();
            for (int i = 0; i < size; i++) {
                MWPOD mwPod = (MWPOD) PODs.get(i);
                if (mwPod != null) {
                    this.mPODs.add(mwPod.toPod());
                }
            }
        }
    }

    public List<String> getPODs() {
        List<String> typeNames = new ArrayList();
        List<Pod> pods = getPODObjects();
        if (pods != null) {
            for (Pod pod : pods) {
                typeNames.add(pod.getTypeName());
            }
        }
        return typeNames;
    }

    public List<Pod> getPODObjects() {
        return this.mPODs;
    }

    public boolean hasOffers() {
        return this.mHasOffers;
    }

    public void setHasOffers(boolean hasOffers) {
        this.mHasOffers = hasOffers;
    }

    public boolean getAcceptsLight() {
        return this.mAcceptsLight;
    }

    public void setAcceptsLight(boolean acceptsLight) {
        this.mAcceptsLight = acceptsLight;
    }

    public void setTimeRestriction(List<MWOpeningHourItem> timeRestriction) {
        this.mTimeRestrictions = new ArrayList();
        if (timeRestriction != null && !timeRestriction.isEmpty()) {
            MWOpeningHourItem openingHourItem = (MWOpeningHourItem) timeRestriction.get(0);
            this.mTimeRestriction = new TimeRestriction(openingHourItem.fromTime.trim(), openingHourItem.toTime.trim());
            for (MWOpeningHourItem item : timeRestriction) {
                if (!(item == null || item.fromTime == null || item.toTime == null)) {
                    this.mTimeRestrictions.add(new TimeRestriction(item.fromTime.trim(), item.toTime.trim()));
                }
            }
        }
    }

    public TimeRestriction getTimeRestriction() {
        return this.mTimeRestriction;
    }

    public List<TimeRestriction> getTimeRestrictions() {
        return this.mTimeRestrictions;
    }

    public void setMaxQttyAllowedPerOrder(Integer maxQttyAllowedPerOrder) {
        this.mMaxQttyAllowedPerOrder = maxQttyAllowedPerOrder;
    }

    public Integer getMaxQttyAllowedPerOrder() {
        return this.mMaxQttyAllowedPerOrder;
    }

    public StoreProduct getStoreProduct() {
        return this.mStoreProduct;
    }

    public void setStoreProduct(StoreProduct storeProduct) {
        this.mStoreProduct = storeProduct;
    }

    public String getProductUnit() {
        return this.mProductUnit;
    }

    public void setProductUnit(String productUnit) {
        this.mProductUnit = productUnit;
    }

    public AdvertisablePromotion getAdvertisablePromotion() {
        return this.mAdvertisablePromotion;
    }

    public void setAdvertisablePromotion(AdvertisablePromotion advertisableDeal) {
        this.mAdvertisablePromotion = advertisableDeal;
    }

    public int getAdvertisableWeekDay() {
        return this.mAdvertisableWeekDay;
    }

    public void setAdvertisableWeekDay(int advertisableWeekDay) {
        this.mAdvertisableWeekDay = advertisableWeekDay;
    }

    public Category getDisplayCategory() {
        return this.mDisplayCategory;
    }

    public void setDisplayCategory(Category displayCategory) {
        this.mDisplayCategory = displayCategory;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        return this.mExternalId.equals(((Product) o).mExternalId);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        int i;
        byte b = (byte) 1;
        dest.writeString(this.mId);
        dest.writeValue(this.mRecipeId);
        dest.writeValue(getExternalId());
        dest.writeValue(this.mDisplayOrder);
        dest.writeString(this.mMenuItemNumber);
        dest.writeStringList(this.mFooters);
        dest.writeString(getName());
        dest.writeString(getMarketingName());
        dest.writeString(getShortName());
        dest.writeString(getLongName());
        dest.writeValue(this.mMenuTypeID);
        dest.writeTypedList(getMenuTypes());
        dest.writeList(this.mExtendedMenuTypeIDs);
        dest.writeTypedList(this.mNutrients);
        dest.writeTypedList(this.mHighlightedNutrients);
        dest.writeTypedList(this.mHighlightedNutrientsSortedById);
        dest.writeTypedList(this.mStandardNutrients);
        dest.writeTypedList(this.mStandardNutrientsSortedById);
        dest.writeParcelable(this.mServingSizeEnglish, 0);
        dest.writeParcelable(this.mServingSizeMetric, 0);
        dest.writeDouble(getEnergy().doubleValue());
        dest.writeParcelable(this.mThumbnailImage, 0);
        dest.writeParcelable(this.mCarouselImage, 0);
        dest.writeTypedList(this.mRecipeComponents);
        dest.writeString(this.mRecipeComponentsString);
        dest.writeTypedList(this.mRecipeAllergens);
        dest.writeString(this.mRecipeAllergensString);
        dest.writeString(this.mDescription);
        dest.writeString(this.mDoNotShow);
        dest.writeValue(this.mValid);
        dest.writeInt(this.mAdvertisableWeekDay);
        dest.writeInt(this.mAdvertisableBaseProductId);
        dest.writeValue(this.mCustomerFriendly);
        dest.writeValue(isSingleChoice());
        dest.writeValue(hasChoice());
        dest.writeValue(hasNonSingleChoiceChoice());
        if (this.mProductType == null) {
            i = -1;
        } else {
            i = this.mProductType.ordinal();
        }
        dest.writeInt(i);
        dest.writeString(this.mImageUrl);
        dest.writeValue(this.mNeedsFullDetails);
        dest.writeTypedList(getDimensions());
        dest.writeList(getIngredients());
        dest.writeList(getExtras());
        dest.writeList(getChoices());
        dest.writeList(this.mComments);
        dest.writeDouble(getPriceEatIn());
        dest.writeDouble(getPriceTakeOut());
        dest.writeDouble(getPriceDelivery());
        dest.writeDouble(getBasePriceEatIn());
        dest.writeDouble(getBasePriceTakeOut());
        dest.writeDouble(getBasePriceDelivery());
        dest.writeList(this.mCategoryIds);
        dest.writeTypedList(this.mCategories);
        dest.writeParcelable(this.mHeroImage, 0);
        dest.writeTypedList(getPODObjects());
        dest.writeByte(this.mHasOffers ? (byte) 1 : (byte) 0);
        if (!this.mAcceptsLight) {
            b = (byte) 0;
        }
        dest.writeByte(b);
        dest.writeParcelable(this.mTimeRestriction, 0);
        dest.writeValue(this.mMaxQttyAllowedPerOrder);
        dest.writeParcelable(this.mStoreProduct, 0);
        dest.writeParcelable(this.mAdvertisablePromotion, 0);
        dest.writeParcelable(getAdvertisableProduct(), 0);
        dest.writeDouble(getKCal().doubleValue());
        dest.writeParcelable(this.mDisplayCategory, 0);
        dest.writeList(this.mTimeRestrictions);
    }

    protected Product(Parcel in) {
        ProductType productType;
        boolean z = true;
        this.mId = in.readString();
        this.mRecipeId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.mExternalId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.mDisplayOrder = (Integer) in.readValue(Integer.class.getClassLoader());
        this.mMenuItemNumber = in.readString();
        this.mFooters = in.createStringArrayList();
        this.mName = in.readString();
        this.mMarketingName = in.readString();
        this.mShortName = in.readString();
        this.mLongName = in.readString();
        this.mMenuTypeID = (Integer) in.readValue(Integer.class.getClassLoader());
        this.mMenuTypes = in.createTypedArrayList(MenuType.CREATOR);
        this.mExtendedMenuTypeIDs = new ArrayList();
        in.readList(this.mExtendedMenuTypeIDs, Integer.class.getClassLoader());
        this.mNutrients = in.createTypedArrayList(Nutrient.CREATOR);
        this.mHighlightedNutrients = in.createTypedArrayList(Nutrient.CREATOR);
        this.mHighlightedNutrientsSortedById = in.createTypedArrayList(Nutrient.CREATOR);
        this.mStandardNutrients = in.createTypedArrayList(Nutrient.CREATOR);
        this.mStandardNutrientsSortedById = in.createTypedArrayList(Nutrient.CREATOR);
        this.mServingSizeEnglish = (Nutrient) in.readParcelable(Nutrient.class.getClassLoader());
        this.mServingSizeMetric = (Nutrient) in.readParcelable(Nutrient.class.getClassLoader());
        this.mEnergy = in.readDouble();
        this.mThumbnailImage = (ImageInfo) in.readParcelable(ImageInfo.class.getClassLoader());
        this.mCarouselImage = (ImageInfo) in.readParcelable(ImageInfo.class.getClassLoader());
        this.mRecipeComponents = in.createTypedArrayList(RecipeComponent.CREATOR);
        this.mRecipeComponentsString = in.readString();
        this.mRecipeAllergens = in.createTypedArrayList(Allergen.CREATOR);
        this.mRecipeAllergensString = in.readString();
        this.mDescription = in.readString();
        this.mDoNotShow = in.readString();
        this.mValid = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.mAdvertisableWeekDay = in.readInt();
        this.mAdvertisableBaseProductId = in.readInt();
        this.mCustomerFriendly = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.mIsSingleChoice = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.mHasChoice = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.mHasNonSingleChoiceChoice = (Boolean) in.readValue(Boolean.class.getClassLoader());
        int tmpMProductType = in.readInt();
        if (tmpMProductType == -1) {
            productType = null;
        } else {
            productType = ProductType.values()[tmpMProductType];
        }
        this.mProductType = productType;
        this.mImageUrl = in.readString();
        this.mNeedsFullDetails = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.mDimensions = in.createTypedArrayList(ProductDimension.CREATOR);
        this.mIngredients = new ArrayList();
        in.readList(this.mIngredients, Ingredient.class.getClassLoader());
        this.mExtras = new ArrayList();
        in.readList(this.mExtras, Ingredient.class.getClassLoader());
        this.mChoices = new ArrayList();
        in.readList(this.mChoices, Ingredient.class.getClassLoader());
        this.mComments = new ArrayList();
        in.readList(this.mComments, Ingredient.class.getClassLoader());
        this.mPriceEatIn = in.readDouble();
        this.mPriceTakeOut = in.readDouble();
        this.mPriceDelivery = in.readDouble();
        this.mBasePriceEatIn = in.readDouble();
        this.mBasePriceTakeOut = in.readDouble();
        this.mBasePriceDelivery = in.readDouble();
        this.mCategoryIds = new ArrayList();
        in.readList(this.mCategoryIds, Integer.class.getClassLoader());
        this.mCategories = in.createTypedArrayList(Category.CREATOR);
        this.mHeroImage = (ImageInfo) in.readParcelable(ImageInfo.class.getClassLoader());
        this.mPODs = in.createTypedArrayList(Pod.CREATOR);
        this.mHasOffers = in.readByte() != (byte) 0;
        if (in.readByte() == (byte) 0) {
            z = false;
        }
        this.mAcceptsLight = z;
        this.mTimeRestriction = (TimeRestriction) in.readParcelable(TimeRestriction.class.getClassLoader());
        this.mMaxQttyAllowedPerOrder = (Integer) in.readValue(Integer.class.getClassLoader());
        this.mStoreProduct = (StoreProduct) in.readParcelable(StoreProduct.class.getClassLoader());
        this.mAdvertisablePromotion = (AdvertisablePromotion) in.readParcelable(AdvertisablePromotion.class.getClassLoader());
        this.mAdvertisableProduct = (Product) in.readParcelable(Product.class.getClassLoader());
        this.mkCal = in.readDouble();
        this.mDisplayCategory = (Category) in.readParcelable(Category.class.getClassLoader());
        this.mTimeRestrictions = new ArrayList();
        in.readList(this.mTimeRestrictions, TimeRestriction.class.getClassLoader());
    }

    public int compareTo(@NonNull Product another) {
        return getDisplayOrder().intValue() - another.getDisplayOrder().intValue();
    }
}
