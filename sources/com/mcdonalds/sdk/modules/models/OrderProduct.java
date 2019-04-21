package com.mcdonalds.sdk.modules.models;

import android.content.res.Resources;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.mcdonalds.sdk.C3883R;
import com.mcdonalds.sdk.McDonalds;
import com.mcdonalds.sdk.connectors.utils.SerializableSparseArray;
import com.mcdonalds.sdk.modules.AppModel;
import com.mcdonalds.sdk.modules.models.Order.PriceType;
import com.mcdonalds.sdk.modules.models.Product.ProductType;
import com.mcdonalds.sdk.services.log.MCDLog;
import com.mcdonalds.sdk.utils.ListUtils;
import com.mcdonalds.sdk.utils.SDKUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class OrderProduct extends AppModel implements Parcelable {
    public static final Creator<OrderProduct> CREATOR = new C25694();
    private static final Integer DRINK = Integer.valueOf(2);
    public static final String DUMMY_PRODUCT_ID = "0";
    private static final String USE_LONG_NAME = "interface.ordering.useLongName";
    private static final HashMap<String, Integer> sPODNames = new HashMap();
    List<String> AllPods;
    private transient Comparator<Choice> choiceComparator;
    private transient Comparator<CustomerOrderProduct> customerOrderProductComparator;
    private int mBasePriceReferenceProductCode;
    private List<Choice> mChoices;
    private List<OrderProduct> mComments;
    private boolean mCostInclusive;
    private Map<Integer, OrderProduct> mCustomizations;
    private String mDisplayName;
    private ImageInfo mDisplayThumbnail;
    private int mFavoriteId;
    private String mFavoriteName;
    private boolean mHasTimeRestrictions;
    private List<OrderProduct> mIngredients;
    private boolean mIsLight;
    private boolean mIsMeal;
    private boolean mOutOfStock;
    private Product mProduct;
    private String mProductCode;
    private int mPromoQuantity;
    private int mQuantity;
    private String mRecipeId;
    private boolean mTimeRestrictionsDoNotCoincide;
    private boolean mUnavailable;
    private boolean mUnavailableCurrentDayPart;
    private transient Comparator<OrderProduct> orderProductComparator;
    private ProductView productView;

    /* renamed from: com.mcdonalds.sdk.modules.models.OrderProduct$1 */
    class C25661 implements Comparator<OrderProduct> {
        C25661() {
        }

        public int compare(OrderProduct left, OrderProduct right) {
            if (left.getBaseProductCode() != right.getBaseProductCode()) {
                return left.getBaseProductCode() - right.getBaseProductCode();
            }
            if (SDKUtils.doubleEquals(left.getTotalPrice(PriceType.EatIn), right.getTotalPrice(PriceType.EatIn))) {
                return left.getTotalPrice(PriceType.EatIn) - right.getTotalPrice(PriceType.EatIn) > 0.0d ? -1 : 1;
            } else {
                if (left.getCustomizations().size() != right.getCustomizations().size()) {
                    return left.getCustomizations().size() - right.getCustomizations().size();
                }
                return 0;
            }
        }
    }

    /* renamed from: com.mcdonalds.sdk.modules.models.OrderProduct$2 */
    class C25672 implements Comparator<Choice> {
        C25672() {
        }

        public int compare(Choice left, Choice right) {
            if (OrderProduct.this.orderProductComparator.compare(left, right) != 0) {
                return OrderProduct.this.orderProductComparator.compare(left, right);
            }
            if (left.getSelection() == null && right.getSelection() != null) {
                return 1;
            }
            if (left.getSelection() != null && right.getSelection() == null) {
                return -1;
            }
            if (left.getSelection() == null || right.getSelection() == null || left.getSelection().getBaseProductCode() == right.getSelection().getBaseProductCode()) {
                return 0;
            }
            return left.getSelection().getBaseProductCode() - right.getSelection().getBaseProductCode();
        }
    }

    /* renamed from: com.mcdonalds.sdk.modules.models.OrderProduct$3 */
    class C25683 implements Comparator<CustomerOrderProduct> {
        C25683() {
        }

        public int compare(CustomerOrderProduct left, CustomerOrderProduct right) {
            if (left.getProductCode() != right.getProductCode()) {
                return left.getProductCode().intValue() - right.getProductCode().intValue();
            }
            if (left.getChoiceSolution() == null && right.getChoiceSolution() != null) {
                return 1;
            }
            if (left.getChoiceSolution() != null && right.getChoiceSolution() == null) {
                return -1;
            }
            if (left.getChoiceSolution() == null || right.getChoiceSolution() == null || left.getChoiceSolution().getProductCode() == right.getChoiceSolution().getProductCode()) {
                return 0;
            }
            return left.getChoiceSolution().getProductCode().intValue() - right.getChoiceSolution().getProductCode().intValue();
        }
    }

    /* renamed from: com.mcdonalds.sdk.modules.models.OrderProduct$4 */
    static class C25694 implements Creator<OrderProduct> {
        C25694() {
        }

        public OrderProduct createFromParcel(Parcel in) {
            return new OrderProduct(in);
        }

        public OrderProduct[] newArray(int size) {
            return new OrderProduct[size];
        }
    }

    static {
        sPODNames.put(Pod.FRONT_COUNTER, Integer.valueOf(C3883R.string.label_front_counter));
        sPODNames.put(Pod.COLD_KIOSK, Integer.valueOf(C3883R.string.label_cold_kiosk));
        sPODNames.put(Pod.DRIVETHRU, Integer.valueOf(C3883R.string.label_drive_thru));
    }

    public OrderProduct() {
        this.mIngredients = new ArrayList();
        this.mChoices = new ArrayList();
        this.mComments = new ArrayList();
        this.mCustomizations = new HashMap();
        this.mIsMeal = false;
        this.AllPods = Arrays.asList(new String[]{Pod.DELIVERY, Pod.KIOSK, Pod.PICKUP, Pod.DRIVETHRU, Pod.FRONT_COUNTER, Pod.WALK_THRU, Pod.COLD_KIOSK, Pod.HANDHELD, Pod.MC_CAFE, Pod.MC_EXPRESS});
        this.orderProductComparator = new C25661();
        this.choiceComparator = new C25672();
        this.customerOrderProductComparator = new C25683();
    }

    public OrderProduct(OrderProduct otherOrderProduct) {
        this.mIngredients = new ArrayList();
        this.mChoices = new ArrayList();
        this.mComments = new ArrayList();
        this.mCustomizations = new HashMap();
        this.mIsMeal = false;
        this.AllPods = Arrays.asList(new String[]{Pod.DELIVERY, Pod.KIOSK, Pod.PICKUP, Pod.DRIVETHRU, Pod.FRONT_COUNTER, Pod.WALK_THRU, Pod.COLD_KIOSK, Pod.HANDHELD, Pod.MC_CAFE, Pod.MC_EXPRESS});
        this.orderProductComparator = new C25661();
        this.choiceComparator = new C25672();
        this.customerOrderProductComparator = new C25683();
        setProduct(otherOrderProduct.getProduct());
        setProductCode(otherOrderProduct.getProductCode());
        setQuantity(otherOrderProduct.getQuantity());
        setIsLight(otherOrderProduct.getIsLight());
        setCostInclusive(otherOrderProduct.isCostInclusive());
        setPromoQuantity(otherOrderProduct.getPromoQuantity());
        setIngredients(otherOrderProduct.getIngredients());
        setChoices(otherOrderProduct.getChoices());
        setComments(otherOrderProduct.getComments());
        setRealChoices(otherOrderProduct.getRealChoices());
        for (int i = 0; i < otherOrderProduct.getChoiceSolutions().size(); i++) {
            getChoiceSolutions().append(otherOrderProduct.getChoiceSolutions().keyAt(i), otherOrderProduct.getChoiceSolutions().valueAt(i));
        }
        getCustomizations().putAll(otherOrderProduct.getCustomizations());
        setFavoriteId(Integer.valueOf(otherOrderProduct.getFavoriteId()));
        setFavoriteName(otherOrderProduct.getFavoriteName());
        this.mIsMeal = otherOrderProduct.isMeal();
    }

    public static OrderProduct cloneProductForEditing(OrderProduct orderProduct) {
        OrderProduct newProduct = createProduct(orderProduct.getProduct(), Integer.valueOf(orderProduct.getQuantity()));
        newProduct.setCostInclusive(orderProduct.isCostInclusive());
        newProduct.setIsLight(orderProduct.getIsLight());
        newProduct.setFavoriteId(Integer.valueOf(orderProduct.getFavoriteId()));
        newProduct.setFavoriteName(orderProduct.getFavoriteName());
        if (newProduct == null) {
            return null;
        }
        if (orderProduct.isMeal()) {
            List<OrderProduct> ingredients = new ArrayList();
            for (OrderProduct ingredient : orderProduct.getIngredients()) {
                ingredients.add(cloneProductForEditing(ingredient));
            }
            newProduct.setIngredients(ingredients);
        }
        List<Choice> choices = new ArrayList();
        for (Choice oldChoice : orderProduct.getRealChoices()) {
            Choice c = (Choice) cloneProductChoices(oldChoice);
            if (c != null) {
                choices.add(c);
            }
        }
        newProduct.setRealChoices(choices);
        for (Entry<Integer, OrderProduct> oldCustomization : orderProduct.getCustomizations().entrySet()) {
            newProduct.addCustomization((Integer) oldCustomization.getKey(), cloneProductForEditing((OrderProduct) oldCustomization.getValue()));
        }
        return newProduct;
    }

    private static OrderProduct cloneProductChoices(OrderProduct product) {
        if (product == null) {
            return null;
        }
        if (!(product instanceof Choice)) {
            return cloneProductForEditing(product);
        }
        OrderProduct oldChoice = (Choice) product;
        OrderProduct newChoice = new Choice(oldChoice);
        if (oldChoice.getSelection() == null) {
            return null;
        }
        newChoice.setSelection(cloneProductChoices(oldChoice.getSelection()));
        return newChoice;
    }

    public static OrderProduct getChoiceWithinChoiceProduct(OrderProduct subProduct) {
        if (subProduct == null) {
            return null;
        }
        OrderProduct orderProduct = subProduct;
        while (orderProduct instanceof Choice) {
            orderProduct = ((Choice) orderProduct).getSelection();
        }
        if (orderProduct == null || !orderProduct.getProduct().getProductType().equals(ProductType.Product)) {
            return null;
        }
        return orderProduct;
    }

    public static OrderProduct getChoiceWithinChoice(OrderProduct choice, OrderProduct choiceSolution) {
        OrderProduct zeroedOrderProduct = new OrderProduct();
        zeroedOrderProduct.setProductCode("0");
        zeroedOrderProduct.setQuantity(0);
        Product product = Product.getChoiceWithinChoiceFiller();
        product.setPODObjects(choice.getProduct().getPODObjects());
        zeroedOrderProduct.setProduct(product);
        OrderProduct choiceCopy = new OrderProduct(choice);
        choiceCopy.getChoices().clear();
        choiceCopy.getChoiceSolutions().put(0, choiceSolution);
        zeroedOrderProduct.getChoices().add(choiceCopy);
        zeroedOrderProduct.getChoiceSolutions().put(0, choiceSolution);
        return zeroedOrderProduct;
    }

    @Nullable
    public static OrderProduct createProduct(Product product, Integer quantity) {
        OrderProduct orderProduct = null;
        if (product == null) {
            MCDLog.error("OrderProduct", "product is null");
        } else if (product.getId() == null) {
            MCDLog.error("OrderProduct", "product.getId() is null");
        } else {
            orderProduct = new OrderProduct(product, quantity);
            List<Ingredient> comments = product.getComments();
            if (comments != null) {
                int size = comments.size();
                for (int i = 0; i < size; i++) {
                    orderProduct.addComments(createProduct((Ingredient) comments.get(i), quantity));
                }
            }
        }
        return orderProduct;
    }

    protected OrderProduct(Product product, Integer quantity) {
        String productId;
        boolean z = true;
        this.mIngredients = new ArrayList();
        this.mChoices = new ArrayList();
        this.mComments = new ArrayList();
        this.mCustomizations = new HashMap();
        this.mIsMeal = false;
        this.AllPods = Arrays.asList(new String[]{Pod.DELIVERY, Pod.KIOSK, Pod.PICKUP, Pod.DRIVETHRU, Pod.FRONT_COUNTER, Pod.WALK_THRU, Pod.COLD_KIOSK, Pod.HANDHELD, Pod.MC_CAFE, Pod.MC_EXPRESS});
        this.orderProductComparator = new C25661();
        this.choiceComparator = new C25672();
        this.customerOrderProductComparator = new C25683();
        if (product.getExternalId() == null) {
            productId = product.getId();
        } else {
            productId = product.getExternalId().toString();
        }
        setProduct(product);
        if (product.getProductType() != ProductType.Meal) {
            z = false;
        }
        setMeal(z);
        setProductCode(productId);
        setQuantity(quantity.intValue());
        setIsLight(false);
        setPromoQuantity(0);
    }

    @Nullable
    public static OrderProduct createProduct(Ingredient ingredient, Integer quantity) {
        OrderProduct orderProduct = createProduct(ingredient.getProduct(), quantity);
        if (orderProduct != null) {
            orderProduct.setCostInclusive(ingredient.getCostInclusive());
            orderProduct.setBasePriceReferenceProductCode(ingredient.getReferencePriceProductCode());
            orderProduct.getChoices();
        }
        return orderProduct;
    }

    public double getCustomizationPrice(OrderProduct product, Ingredient ingredient, PriceType priceType) {
        if (ingredient == null || ingredient.getProduct().getPrice(priceType) <= 0.0d) {
            return 0.0d;
        }
        double difference = 0.0d;
        if (product.getQuantity() > ingredient.getChargeThreshold()) {
            difference = (double) (product.getQuantity() - ingredient.getChargeThreshold());
        } else if (product.getQuantity() < ingredient.getRefundThreshold()) {
            difference = (double) (product.getQuantity() - ingredient.getRefundThreshold());
        }
        return difference * ingredient.getProduct().getPrice(priceType);
    }

    public double getBasePrice(PriceType priceType) {
        return this.mProduct.getBasePrice(priceType);
    }

    public double getTotalPrice(PriceType priceType) {
        return getTotalPrice(priceType, false);
    }

    public double getTotalPrice(PriceType priceType, boolean allowDownCharge) {
        double ret = 0.0d;
        SerializableSparseArray<Ingredient> ingredientSparseArray = new SerializableSparseArray();
        if (!(getProduct() == null || isCostInclusive())) {
            if (this.productView != null) {
                ret = 0.0d + this.productView.getUnitPrice().doubleValue();
            } else {
                ret = 0.0d + getProduct().getPrice(priceType);
            }
            addAllIngredientsToSerializableSparseArray(ingredientSparseArray, getProduct().getIngredients());
            addAllIngredientsToSerializableSparseArray(ingredientSparseArray, getProduct().getExtras());
        }
        if (getCustomizations() != null) {
            for (OrderProduct customization : getCustomizations().values()) {
                if (customization.productView != null) {
                    ret += customization.productView.getUnitPrice().doubleValue() * ((double) customization.getQuantity());
                } else {
                    ret += customization.getCustomizationPrice(customization, (Ingredient) ingredientSparseArray.get(customization.getProduct().getExternalId().intValue()), priceType);
                }
            }
        }
        if (!(getIngredients() == null || getProduct() == null)) {
            for (OrderProduct productIngredient : getRealIngredients()) {
                if (productIngredient.getCustomizations() != null) {
                    ingredientSparseArray.clear();
                    addAllIngredientsToSerializableSparseArray(ingredientSparseArray, productIngredient.getProduct().getIngredients());
                    addAllIngredientsToSerializableSparseArray(ingredientSparseArray, productIngredient.getProduct().getExtras());
                    for (OrderProduct customization2 : productIngredient.getCustomizations().values()) {
                        if (ingredientSparseArray.get(customization2.getProduct().getExternalId().intValue()) != null) {
                            if (customization2.productView != null) {
                                ret += customization2.productView.getUnitPrice().doubleValue() * ((double) customization2.getQuantity());
                            } else {
                                ret += customization2.getCustomizationPrice(customization2, (Ingredient) ingredientSparseArray.get(customization2.getProduct().getExternalId().intValue()), priceType);
                            }
                        }
                    }
                }
            }
        }
        if (!ListUtils.isEmpty(this.mChoices)) {
            for (Choice choice : this.mChoices) {
                if (choice != null) {
                    double choicePrice = choice.getTotalPrice(priceType, true);
                    if (!allowDownCharge && choice.getBasePrice(priceType) > choicePrice) {
                        choicePrice = choice.getBasePrice(priceType);
                    }
                    ret += choicePrice;
                }
            }
        }
        return ((double) getQuantity()) * ret;
    }

    public boolean isChoice() {
        return (this.mProduct == null || this.mProduct.getProductType() == null || !this.mProduct.getProductType().equals(ProductType.Choice)) ? false : true;
    }

    public boolean isSingleChoice() {
        return this.mProduct.isSingleChoice().booleanValue();
    }

    public boolean hasSubChoices() {
        return (this.mProduct == null || this.mProduct.getChoices() == null || this.mProduct.getChoices().isEmpty()) ? false : true;
    }

    public double getTotalEnergy() {
        double ret = 0.0d;
        if (!ListUtils.isEmpty(getIngredients()) && isMeal()) {
            for (OrderProduct orderProduct : getIngredients()) {
                ret += orderProduct.getTotalEnergy();
            }
        }
        List<Choice> realChoices = getRealChoices();
        if (realChoices != null) {
            for (Choice choice : realChoices) {
                OrderProduct actualProduct = getChoiceWithinChoiceProduct(choice);
                if (actualProduct != null) {
                    ret += actualProduct.getTotalEnergy();
                }
            }
        }
        if (!(getProduct() == null || getProduct().getEnergy() == null || isMeal())) {
            ret += getProduct().getEnergy().doubleValue();
        }
        return ((double) getQuantity()) * ret;
    }

    public double getTotalSecondaryEnergy() {
        double ret = 0.0d;
        if (!ListUtils.isEmpty(getIngredients()) && isMeal()) {
            for (OrderProduct orderProduct : getIngredients()) {
                ret += orderProduct.getTotalSecondaryEnergy();
            }
        }
        List<Choice> realChoices = getRealChoices();
        if (realChoices != null) {
            for (Choice choice : realChoices) {
                OrderProduct actualProduct = getChoiceWithinChoiceProduct(choice);
                if (actualProduct != null) {
                    ret += actualProduct.getTotalSecondaryEnergy();
                }
            }
        }
        if (!(getProduct() == null || getProduct().getEnergy() == null || isMeal())) {
            ret += getProduct().getKCal().doubleValue();
        }
        return ((double) getQuantity()) * ret;
    }

    public String getTotalEnergyUnit() {
        Nutrient energyNutrient;
        if (isMeal()) {
            if (getIngredients() == null || getIngredients().isEmpty()) {
                return null;
            }
            energyNutrient = ((OrderProduct) getIngredients().get(0)).getProduct().getEnergyNutrient();
            if (energyNutrient != null) {
                return energyNutrient.getUnit();
            }
            return null;
        } else if (getProduct() == null) {
            return null;
        } else {
            energyNutrient = getProduct().getEnergyNutrient();
            if (energyNutrient != null) {
                return energyNutrient.getUnit();
            }
            return null;
        }
    }

    public String getCustomizationsString() {
        if (getProduct() == null || getCustomizations() == null || getCustomizations().isEmpty()) {
            return "";
        }
        SerializableSparseArray<Ingredient> ingredientSparseArray = new SerializableSparseArray();
        addAllIngredientsToSerializableSparseArray(ingredientSparseArray, getProduct().getIngredients());
        addAllIngredientsToSerializableSparseArray(ingredientSparseArray, getProduct().getExtras());
        StringBuilder builder = new StringBuilder("");
        Iterator<OrderProduct> iterator = getCustomizations().values().iterator();
        while (iterator.hasNext()) {
            OrderProduct customization = (OrderProduct) iterator.next();
            Ingredient ingredient = (Ingredient) ingredientSparseArray.get(customization.getProduct().getExternalId().intValue());
            if (ingredient != null) {
                builder.append(getCustomizationText(ingredient, customization));
            }
            if (iterator.hasNext()) {
                builder.append(", ");
            }
        }
        return builder.toString();
    }

    private void addAllIngredientsToSerializableSparseArray(SerializableSparseArray<Ingredient> ingredientSparseArray, List<Ingredient> ingredientList) {
        if (ingredientList != null) {
            for (Ingredient ingredient : ingredientList) {
                ingredientSparseArray.put(ingredient.getProduct().getExternalId().intValue(), ingredient);
            }
        }
    }

    private List<OrderProduct> getSubProducts() {
        List<OrderProduct> ret = new ArrayList();
        if (getIngredients() != null) {
            ret.addAll(getIngredients());
        }
        if (getChoiceSolutions() != null) {
            for (int i = 0; i < getChoiceSolutions().size(); i++) {
                ret.add(getChoiceSolutions().valueAt(i));
            }
        }
        return ret;
    }

    private String getCustomizationText(Ingredient ingredient, OrderProduct customization) {
        Product product = customization.getProduct();
        Resources resources = McDonalds.getContext().getResources();
        if (customization.getQuantity() == 0) {
            return resources.getString(C3883R.string.customization_label_no, new Object[]{product.getName()});
        }
        boolean isPortion = customization.getProduct().getAcceptsLight() && ingredient.getMinQuantity() == 0 && ingredient.getDefaultQuantity() == 1 && ingredient.getMaxQuantity() == 2;
        if (isPortion) {
            if (customization.getIsLight()) {
                return resources.getString(C3883R.string.customization_label_light, new Object[]{product.getName()});
            }
            switch (customization.getQuantity()) {
                case 1:
                    return resources.getString(C3883R.string.customization_label_regular, new Object[]{product.getName()});
                case 2:
                    return resources.getString(C3883R.string.customization_label_extra, new Object[]{product.getName()});
            }
        }
        String unit = product.getProductUnit();
        if (unit != null) {
            int resourceId = resources.getIdentifier(unit.toLowerCase() + (customization.getQuantity() == 1 ? "_singular" : "_plural"), "string", McDonalds.getContext().getApplicationContext().getPackageName());
            if (resourceId != 0) {
                String unitSuffix = resources.getString(resourceId);
                return resources.getString(C3883R.string.customization_label_for_product_unit, new Object[]{Integer.valueOf(customization.getQuantity()), unitSuffix, product.getName()});
            }
        }
        return resources.getString(C3883R.string.customization_label_quantity, new Object[]{Integer.valueOf(customization.getQuantity()), product.getName()});
    }

    public boolean availableAtPOD(String pod) {
        if (!this.mProduct.getPODs().contains(pod)) {
            return false;
        }
        List<OrderProduct> subproducts = getSubProducts();
        if (!subproducts.isEmpty()) {
            for (OrderProduct sub : subproducts) {
                if (!sub.availableAtPOD(pod)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean checkDayPart(int dayPart) {
        if (getProduct() == null) {
            return true;
        }
        List<MenuType> menuTypes = getProduct().getMenuTypes();
        if (!ListUtils.isEmpty(menuTypes)) {
            for (MenuType menuType : menuTypes) {
                if (menuType.getID() == dayPart) {
                    return true;
                }
            }
        }
        return false;
    }

    public void addIngredient(OrderProduct ingredient) {
        this.mIngredients.add(ingredient);
    }

    public void addChoice(Choice choice) {
        this.mChoices.add(choice);
    }

    @Deprecated
    public void addChoice(OrderProduct orderProduct) {
        addChoice(Choice.createChoice(orderProduct));
    }

    public void addComments(OrderProduct comments) {
        this.mComments.add(comments);
    }

    @Deprecated
    public void setChoiceSolutions(SerializableSparseArray<OrderProduct> choiceSolutions) {
        for (int i = 0; i < choiceSolutions.size(); i++) {
            int key = choiceSolutions.keyAt(i);
            setChoiceSolution(key, (OrderProduct) choiceSolutions.get(key));
        }
    }

    @Deprecated
    public void setChoiceSolution(int index, OrderProduct choiceSolution) {
        List<Choice> choices = getRealChoices();
        if (index < choices.size()) {
            ((Choice) choices.get(index)).setSelection(choiceSolution);
        }
    }

    public void addCustomization(Integer productId, OrderProduct customization) {
        this.mCustomizations.put(productId, customization);
    }

    public void setIngredients(List<OrderProduct> ingredients) {
        this.mIngredients = ingredients;
    }

    @Deprecated
    public void setChoices(List<OrderProduct> choices) {
        this.mChoices = new ArrayList();
        for (OrderProduct choice : choices) {
            addChoice(choice);
        }
    }

    public void setRealChoices(List<Choice> mChoices) {
        this.mChoices = mChoices;
    }

    public void setComments(List<OrderProduct> comments) {
        this.mComments = comments;
    }

    public Product getProduct() {
        return this.mProduct;
    }

    public void setProduct(Product product) {
        this.mProduct = product;
        if (product != null) {
            setRecipeId(product.getId());
            setDisplayName(product.getName());
            setDisplayThumbnailImage(product.getThumbnailImage());
        }
    }

    public boolean isUnavailable() {
        return this.mUnavailable;
    }

    public boolean isOutOfStock() {
        return this.mOutOfStock;
    }

    public boolean hasTimeRestrictions() {
        return this.mHasTimeRestrictions;
    }

    public boolean timeRestrictionsDoNotCoincide() {
        return this.mTimeRestrictionsDoNotCoincide;
    }

    public boolean isUnavailableCurrentDayPart() {
        return this.mUnavailableCurrentDayPart;
    }

    public void setUnavailable(boolean mUnavailable) {
        this.mUnavailable = mUnavailable;
    }

    public void setOutOfStock(boolean mOutOfStock) {
        this.mOutOfStock = mOutOfStock;
    }

    public void setHasTimeRestrictions(boolean mHasTimeRestrictions) {
        this.mHasTimeRestrictions = mHasTimeRestrictions;
    }

    public void setUnavailableCurrentDayPart(boolean mUnavailableCurrentDayPart) {
        this.mUnavailableCurrentDayPart = mUnavailableCurrentDayPart;
    }

    public void setTimeRestrictionsDoNotCoincide(boolean mTimeRestrictionsDoNotCoincide) {
        this.mTimeRestrictionsDoNotCoincide = mTimeRestrictionsDoNotCoincide;
    }

    public boolean isCostInclusive() {
        return this.mCostInclusive;
    }

    public void setCostInclusive(boolean mCostInclusive) {
        this.mCostInclusive = mCostInclusive;
    }

    public String getRecipeId() {
        return this.mRecipeId;
    }

    public void setRecipeId(String mRecipeId) {
        this.mRecipeId = mRecipeId;
    }

    public ImageInfo getDisplayThumbnailImage() {
        return this.mDisplayThumbnail;
    }

    public void setDisplayThumbnailImage(ImageInfo thumbnailImage) {
        this.mDisplayThumbnail = thumbnailImage;
    }

    public String getDisplayName() {
        return this.mDisplayName;
    }

    public void setDisplayName(String mDisplayName) {
        this.mDisplayName = mDisplayName;
    }

    public String getProductCode() {
        String baseProductCode = String.valueOf(getProduct().getExternalId());
        if (!this.mProductCode.equals(baseProductCode)) {
            this.mProductCode = baseProductCode;
        }
        return this.mProductCode;
    }

    public int getBaseProductCode() {
        if (this.mProduct.isAdvertisable().booleanValue()) {
            return this.mProduct.getAdvertisableBaseProductId();
        }
        return this.mProduct.getExternalId().intValue();
    }

    public void setProductCode(String productCode) {
        this.mProductCode = productCode;
    }

    public int getQuantity() {
        if (getProductCode().equals("0")) {
            SerializableSparseArray<OrderProduct> choiceSolutions = getChoiceSolutions();
            if (!(choiceSolutions == null || choiceSolutions.size() <= 0 || choiceSolutions.get(0) == null)) {
                return ((OrderProduct) choiceSolutions.get(0)).getQuantity();
            }
        }
        return this.mQuantity;
    }

    public void setQuantity(int quantity) {
        this.mQuantity = quantity;
    }

    public boolean isMeal() {
        return this.mIsMeal;
    }

    public void setMeal(boolean isMeal) {
        this.mIsMeal = isMeal;
    }

    public List<OrderProduct> getIngredients() {
        if (ListUtils.isEmpty(this.mIngredients)) {
            int size;
            int i;
            List<Ingredient> ingredients = getProduct().getIngredients();
            if (ingredients != null) {
                size = ingredients.size();
                for (i = 0; i < size; i++) {
                    Ingredient ingredient = (Ingredient) ingredients.get(i);
                    addIngredient(createProduct(ingredient, Integer.valueOf(ingredient.getDefaultQuantity())));
                }
            }
            List<Ingredient> extras = getProduct().getExtras();
            if (extras != null) {
                size = extras.size();
                for (i = 0; i < size; i++) {
                    Ingredient extra = (Ingredient) extras.get(i);
                    addIngredient(createProduct(extra, Integer.valueOf(extra.getDefaultQuantity())));
                }
            }
        }
        return this.mIngredients;
    }

    public List<OrderProduct> getRealIngredients() {
        List<OrderProduct> ingredients = new ArrayList();
        ingredients.addAll(getIngredients());
        List<Ingredient> extras = getProduct().getExtras();
        if (extras != null) {
            int size = extras.size();
            for (int i = 0; i < size; i++) {
                ingredients.remove(createProduct((Ingredient) extras.get(i), Integer.valueOf(this.mQuantity)));
            }
        }
        return ingredients;
    }

    public List<Choice> getRealChoices() {
        if (ListUtils.isEmpty(this.mChoices)) {
            List<Ingredient> choices = getProduct().getChoices();
            if (choices != null) {
                for (Ingredient choice : choices) {
                    for (int i = 0; i < choice.getDefaultQuantity(); i++) {
                        addChoice(Choice.createChoice(choice, Integer.valueOf(1)));
                    }
                }
            }
        }
        return this.mChoices;
    }

    @Deprecated
    public List<OrderProduct> getChoices() {
        List<OrderProduct> compatChoices = new ArrayList();
        compatChoices.addAll(getRealChoices());
        return compatChoices;
    }

    public List<OrderProduct> getComments() {
        return this.mComments;
    }

    @Deprecated
    public SerializableSparseArray<OrderProduct> getChoiceSolutions() {
        SerializableSparseArray<OrderProduct> compatChoiceSolutions = new SerializableSparseArray();
        List<Choice> choices = getRealChoices();
        for (int i = 0; i < choices.size(); i++) {
            OrderProduct choiceSolution = ((Choice) choices.get(i)).getSelection();
            if (choiceSolution != null) {
                compatChoiceSolutions.put(i, choiceSolution);
            }
        }
        return compatChoiceSolutions;
    }

    public Map<Integer, OrderProduct> getCustomizations() {
        return this.mCustomizations;
    }

    public void setCustomizations(Map<Integer, OrderProduct> customizations) {
        this.mCustomizations = customizations;
    }

    public boolean getIsLight() {
        return this.mIsLight;
    }

    public void setIsLight(boolean isLight) {
        this.mIsLight = isLight;
    }

    public int getPromoQuantity() {
        return this.mPromoQuantity;
    }

    public void setPromoQuantity(int promoQuantity) {
        this.mPromoQuantity = promoQuantity;
    }

    public int getBasePriceReferenceProductCode() {
        return this.mBasePriceReferenceProductCode;
    }

    public void setBasePriceReferenceProductCode(int basePriceReferenceProductCode) {
        this.mBasePriceReferenceProductCode = basePriceReferenceProductCode;
    }

    public int getFavoriteId() {
        return this.mFavoriteId;
    }

    public void setFavoriteId(Integer favoriteId) {
        if (favoriteId != null) {
            this.mFavoriteId = favoriteId.intValue();
        }
    }

    public String getFavoriteName() {
        return this.mFavoriteName;
    }

    public void setFavoriteName(String favoriteName) {
        this.mFavoriteName = favoriteName;
    }

    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o == this) {
            return true;
        }
        if (o instanceof OrderProduct) {
            return areProductsSame((OrderProduct) o);
        } else if (o instanceof ProductView) {
            return equals((ProductView) o, true);
        } else {
            if (!(o instanceof CustomerOrderProduct)) {
                return super.equals(o);
            }
            CustomerOrderProduct custProd = (CustomerOrderProduct) o;
            if (custProd.getProductCode().intValue() == 0 && custProd.getQuantity().intValue() == 0 && !ListUtils.isEmpty(custProd.getChoices())) {
                return equals(custProd.getChoices().get(0));
            }
            if (!custProd.getProductCode().equals(Integer.valueOf(getBaseProductCode()))) {
                return false;
            }
            if ((getCustomizations() == null && custProd.getCustomizations() != null) || ((getCustomizations() != null && getCustomizations().size() > 0 && custProd.getCustomizations() == null) || (getCustomizations() != null && custProd.getCustomizations() != null && getCustomizations().size() != custProd.getCustomizations().size()))) {
                return false;
            }
            if (custProd.getCustomizations() != null) {
                for (CustomerOrderProduct custProdCustomization : custProd.getCustomizations()) {
                    OrderProduct customizationOrderProduct = (OrderProduct) getCustomizations().get(custProdCustomization.getProductCode());
                    if (customizationOrderProduct != null) {
                        if (!customizationOrderProduct.equals(custProdCustomization)) {
                        }
                    }
                    return false;
                }
            }
            if (isMeal()) {
                if ((getRealIngredients() == null && custProd.getComponents() != null) || ((getRealIngredients() != null && custProd.getComponents() == null) || getRealIngredients().size() != custProd.getComponents().size())) {
                    return false;
                }
                if (!(getRealIngredients() == null || custProd.getComponents() == null)) {
                    List<OrderProduct> sortedThisMainProducts = new ArrayList(getRealIngredients());
                    Collections.sort(sortedThisMainProducts, this.orderProductComparator);
                    List<CustomerOrderProduct> sortedThatMainProducts = new ArrayList(custProd.getComponents());
                    Collections.sort(sortedThatMainProducts, this.customerOrderProductComparator);
                    for (int jj = 0; jj < sortedThatMainProducts.size(); jj++) {
                        if (!((OrderProduct) sortedThisMainProducts.get(jj)).equals((CustomerOrderProduct) sortedThatMainProducts.get(jj))) {
                            return false;
                        }
                    }
                }
            }
            if ((getRealChoices() == null && custProd.getChoices() != null) || ((getRealChoices() != null && getRealChoices().size() > 0 && custProd.getChoices() == null) || (getRealChoices() != null && custProd.getChoices() != null && getRealChoices().size() != custProd.getChoices().size()))) {
                return false;
            }
            Choice thisChoice;
            if (!(getRealChoices() == null || custProd.getChoices() == null)) {
                List<Choice> sortedThisChoice = new ArrayList(getRealChoices());
                Collections.sort(sortedThisChoice, this.choiceComparator);
                List<CustomerOrderProduct> sortedThatChoice = new ArrayList(custProd.getChoices());
                Collections.sort(sortedThatChoice, this.customerOrderProductComparator);
                for (int ii = 0; ii < sortedThisChoice.size(); ii++) {
                    thisChoice = (Choice) sortedThisChoice.get(ii);
                    CustomerOrderProduct thatChoice = (CustomerOrderProduct) sortedThatChoice.get(ii);
                    if (thatChoice.getProductCode().intValue() == 0 && thatChoice.getQuantity().intValue() == 0 && thisChoice.getSelection() != null) {
                        return false;
                    }
                    if (thisChoice.getBaseProductCode() != thatChoice.getProductCode().intValue()) {
                        return false;
                    }
                    OrderProduct thisChoiceSelection = thisChoice.getSelection();
                    CustomerOrderProduct thatChoiceSelection = thatChoice.getChoiceSolution();
                    if (thisChoiceSelection == null && thatChoiceSelection != null && thatChoiceSelection.getProductCode().intValue() != 0) {
                        return false;
                    }
                    if (thisChoiceSelection != null && !thisChoiceSelection.equals(thatChoiceSelection)) {
                        return false;
                    }
                }
            }
            if (this instanceof Choice) {
                thisChoice = (Choice) this;
                if ((thisChoice.getSelection() == null && custProd.getChoiceSolution() != null) || (thisChoice.getSelection() != null && custProd.getChoiceSolution() == null)) {
                    return false;
                }
                if (!(thisChoice.getSelection() == null || thisChoice.getSelection().equals(custProd.getChoiceSolution()))) {
                    return false;
                }
            }
            return true;
        }
    }

    public boolean equals(ProductView productView, boolean checkChoicesAndCustomizations) {
        if (productView == null) {
            return false;
        }
        boolean equals = productView.getProductCode().intValue() == Integer.parseInt(getProductCode());
        if (!equals || !checkChoicesAndCustomizations) {
            return equals;
        }
        int choiceSize = getRealChoices() == null ? 0 : getRealChoices().size();
        ArrayList<ProductView> productViewChoices = productView.getChoices();
        int productViewChoiceSize = productViewChoices == null ? 0 : productViewChoices.size();
        if (choiceSize != productViewChoiceSize) {
            return false;
        }
        int i;
        for (i = 0; i < productViewChoiceSize; i++) {
            ProductView productViewChoice = ((ProductView) productViewChoices.get(i)).getActualChoice();
            OrderProduct choiceSolution = ((Choice) getRealChoices().get(i)).getActualChoice();
            if (choiceSolution != null && !choiceSolution.equals(productViewChoice, true)) {
                return false;
            }
        }
        int customizationSize = getCustomizations().size();
        ArrayList<ProductView> productViewCustomizations = productView.getCustomizations();
        int productViewCustomizationSize = productViewCustomizations == null ? 0 : productViewCustomizations.size();
        if (customizationSize != productViewCustomizationSize) {
            return false;
        }
        for (i = 0; i < productViewCustomizationSize; i++) {
            ProductView productViewCustomization = (ProductView) productViewCustomizations.get(i);
            OrderProduct customization = (OrderProduct) getCustomizations().get(productViewCustomization.getProductCode());
            if (customization == null || !customization.equals(productViewCustomization, false)) {
                return false;
            }
        }
        return equals;
    }

    public OrderProduct getActualChoice() {
        if (!(this instanceof Choice)) {
            return this;
        }
        Choice choice = (Choice) this;
        if (choice.getSelection() != null) {
            return choice.getSelection().getActualChoice();
        }
        return null;
    }

    public void attachProductViewToOrderProduct(ProductView productView) {
        Iterator it;
        this.productView = productView;
        this.mQuantity = productView.getQuantity().intValue();
        for (Choice choice : getRealChoices()) {
            if (!(ListUtils.isEmpty(productView.getChoices()) || choice.getSelection() == null)) {
                it = productView.getChoices().iterator();
                while (it.hasNext()) {
                    ProductView choiceProductView = (ProductView) it.next();
                    if (choice.getSelection().equals(choiceProductView, true)) {
                        choice.getSelection().attachProductViewToOrderProduct(choiceProductView);
                    } else if (choiceProductView.getProductCode().intValue() == 0 && choice.getSelection().getActualChoice().equals(choiceProductView.getActualChoice(), true)) {
                        choice.getSelection().getActualChoice().attachProductViewToOrderProduct(choiceProductView.getActualChoice());
                    }
                }
            }
        }
        Map<Integer, OrderProduct> customizations = getCustomizations();
        if (customizations != null) {
            for (Entry<Integer, OrderProduct> entry : customizations.entrySet()) {
                OrderProduct customization = (OrderProduct) entry.getValue();
                if (!ListUtils.isEmpty(productView.getChoices())) {
                    it = productView.getChoices().iterator();
                    while (it.hasNext()) {
                        ProductView customizationProductView = (ProductView) it.next();
                        if (customization.equals(customizationProductView, true)) {
                            customization.attachProductViewToOrderProduct(customizationProductView);
                        }
                    }
                }
            }
        }
    }

    public static String getPODDisplayName(String pod, Resources resources) {
        if (sPODNames.containsKey(pod)) {
            return resources.getString(((Integer) sPODNames.get(pod)).intValue());
        }
        return pod;
    }

    public String toString() {
        return "OrderProduct{mRecipe=" + this.mProduct + ", mProductCode=\"" + getProductCode() + "\", mQuantity=" + this.mQuantity + ", mIsLight=" + this.mIsLight + ", mPromoQuantity=" + this.mPromoQuantity + ", mIngredients=" + this.mIngredients + ", mChoices=" + this.mChoices + ", mComments=" + this.mComments + ", mCustomizations=" + this.mCustomizations + ", mIsMeal=" + this.mIsMeal + "}";
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        int i;
        int i2 = 1;
        dest.writeParcelable(this.mProduct, flags);
        dest.writeString(this.mRecipeId);
        dest.writeString(getProductCode());
        dest.writeString(this.mFavoriteName);
        dest.writeString(this.mDisplayName);
        dest.writeParcelable(this.mDisplayThumbnail, flags);
        dest.writeInt(this.mQuantity);
        dest.writeInt(this.mFavoriteId);
        dest.writeInt(this.mPromoQuantity);
        dest.writeInt(this.mBasePriceReferenceProductCode);
        if (this.mIsLight) {
            i = 1;
        } else {
            i = 0;
        }
        dest.writeByte((byte) i);
        dest.writeTypedList(this.mIngredients);
        dest.writeTypedList(this.mChoices);
        dest.writeTypedList(this.mComments);
        dest.writeInt(this.mCustomizations.size());
        for (Entry<Integer, OrderProduct> entry : this.mCustomizations.entrySet()) {
            dest.writeValue(entry.getKey());
            dest.writeParcelable((Parcelable) entry.getValue(), flags);
        }
        dest.writeByte((byte) (this.mUnavailable ? 1 : 0));
        if (this.mOutOfStock) {
            i = 1;
        } else {
            i = 0;
        }
        dest.writeByte((byte) i);
        if (this.mHasTimeRestrictions) {
            i = 1;
        } else {
            i = 0;
        }
        dest.writeByte((byte) i);
        if (this.mUnavailableCurrentDayPart) {
            i = 1;
        } else {
            i = 0;
        }
        dest.writeByte((byte) i);
        if (this.mTimeRestrictionsDoNotCoincide) {
            i = 1;
        } else {
            i = 0;
        }
        dest.writeByte((byte) i);
        if (this.mCostInclusive) {
            i = 1;
        } else {
            i = 0;
        }
        dest.writeByte((byte) i);
        if (!this.mIsMeal) {
            i2 = 0;
        }
        dest.writeByte((byte) i2);
        dest.writeStringList(this.AllPods);
    }

    protected OrderProduct(Parcel in) {
        boolean z;
        boolean z2 = true;
        this.mIngredients = new ArrayList();
        this.mChoices = new ArrayList();
        this.mComments = new ArrayList();
        this.mCustomizations = new HashMap();
        this.mIsMeal = false;
        this.AllPods = Arrays.asList(new String[]{Pod.DELIVERY, Pod.KIOSK, Pod.PICKUP, Pod.DRIVETHRU, Pod.FRONT_COUNTER, Pod.WALK_THRU, Pod.COLD_KIOSK, Pod.HANDHELD, Pod.MC_CAFE, Pod.MC_EXPRESS});
        this.orderProductComparator = new C25661();
        this.choiceComparator = new C25672();
        this.customerOrderProductComparator = new C25683();
        this.mProduct = (Product) in.readParcelable(Product.class.getClassLoader());
        this.mRecipeId = in.readString();
        this.mProductCode = in.readString();
        this.mFavoriteName = in.readString();
        this.mDisplayName = in.readString();
        this.mDisplayThumbnail = (ImageInfo) in.readParcelable(ImageInfo.class.getClassLoader());
        this.mQuantity = in.readInt();
        this.mFavoriteId = in.readInt();
        this.mPromoQuantity = in.readInt();
        this.mBasePriceReferenceProductCode = in.readInt();
        if (in.readByte() != (byte) 0) {
            z = true;
        } else {
            z = false;
        }
        this.mIsLight = z;
        this.mIngredients = in.createTypedArrayList(CREATOR);
        this.mChoices = in.createTypedArrayList(Choice.CREATOR);
        this.mComments = in.createTypedArrayList(CREATOR);
        int size = in.readInt();
        for (int i = 0; i < size; i++) {
            this.mCustomizations.put((Integer) in.readValue(Integer.class.getClassLoader()), (OrderProduct) in.readParcelable(OrderProduct.class.getClassLoader()));
        }
        this.mUnavailable = in.readByte() != (byte) 0;
        if (in.readByte() != (byte) 0) {
            z = true;
        } else {
            z = false;
        }
        this.mOutOfStock = z;
        if (in.readByte() != (byte) 0) {
            z = true;
        } else {
            z = false;
        }
        this.mHasTimeRestrictions = z;
        if (in.readByte() != (byte) 0) {
            z = true;
        } else {
            z = false;
        }
        this.mUnavailableCurrentDayPart = z;
        if (in.readByte() != (byte) 0) {
            z = true;
        } else {
            z = false;
        }
        this.mTimeRestrictionsDoNotCoincide = z;
        if (in.readByte() != (byte) 0) {
            z = true;
        } else {
            z = false;
        }
        this.mCostInclusive = z;
        if (in.readByte() == (byte) 0) {
            z2 = false;
        }
        this.mIsMeal = z2;
        this.AllPods = in.createStringArrayList();
    }

    public Double getTotalCustomizationsPrice(PriceType priceType, boolean allowDownCharge) {
        double ret = 0.0d;
        SerializableSparseArray<Ingredient> ingredientSparseArray = new SerializableSparseArray();
        if (getProduct() != null) {
            addAllIngredientsToSerializableSparseArray(ingredientSparseArray, getProduct().getIngredients());
            addAllIngredientsToSerializableSparseArray(ingredientSparseArray, getProduct().getExtras());
        }
        if (getCustomizations() != null) {
            for (OrderProduct customization : getCustomizations().values()) {
                ret += customization.getCustomizationPrice(customization, (Ingredient) ingredientSparseArray.get(customization.getProduct().getExternalId().intValue()), priceType);
            }
        }
        return Double.valueOf(((double) getQuantity()) * ret);
    }

    private boolean areProductsSame(OrderProduct product) {
        if (Integer.parseInt(product.getProductCode()) != Integer.parseInt(getProductCode())) {
            return false;
        }
        if ((getCustomizations() == null && product.getCustomizations() != null) || ((getCustomizations() != null && getCustomizations().size() > 0 && product.getCustomizations() == null) || (getCustomizations() != null && product.getCustomizations() != null && getCustomizations().size() != product.getCustomizations().size()))) {
            return false;
        }
        if (product.getCustomizations() != null) {
            for (OrderProduct productCustomization : product.getCustomizations().values()) {
                OrderProduct customizationOrderProduct = (OrderProduct) getCustomizations().get(productCustomization);
                if (customizationOrderProduct != null) {
                    if (!customizationOrderProduct.equals(productCustomization)) {
                    }
                }
                return false;
            }
        }
        if (isMeal()) {
            if ((getRealIngredients() == null && product.getIngredients() != null) || ((getRealIngredients() != null && product.getIngredients() == null) || getRealIngredients().size() != product.getIngredients().size())) {
                return false;
            }
            if (!(getRealIngredients() == null || product.getRealIngredients() == null)) {
                List<OrderProduct> sortedThisMainProducts = new ArrayList(getRealIngredients());
                Collections.sort(sortedThisMainProducts, this.orderProductComparator);
                List<OrderProduct> sortedThatMainProducts = new ArrayList(product.getRealIngredients());
                Collections.sort(sortedThatMainProducts, this.orderProductComparator);
                for (int jj = 0; jj < sortedThatMainProducts.size(); jj++) {
                    if (!((OrderProduct) sortedThisMainProducts.get(jj)).equals((OrderProduct) sortedThatMainProducts.get(jj))) {
                        return false;
                    }
                }
            }
        }
        if ((getRealChoices() == null && product.getRealChoices() != null) || ((getRealChoices() != null && getRealChoices().size() > 0 && product.getRealChoices() == null) || (getRealChoices() != null && product.getRealChoices() != null && getRealChoices().size() != product.getRealChoices().size()))) {
            return false;
        }
        if (!(getRealChoices() == null || product.getRealChoices() == null)) {
            List<Choice> sortedThisChoice = new ArrayList(getRealChoices());
            Collections.sort(sortedThisChoice, this.choiceComparator);
            List<Choice> sortedThatChoice = new ArrayList(product.getRealChoices());
            Collections.sort(sortedThatChoice, this.choiceComparator);
            for (int ii = 0; ii < sortedThisChoice.size(); ii++) {
                Choice thisChoice = (Choice) sortedThisChoice.get(ii);
                Choice thatChoice = (Choice) sortedThatChoice.get(ii);
                if (thatChoice.getBaseProductCode() == 0 && thatChoice.getQuantity() == 0 && thisChoice.getSelection() != null) {
                    return false;
                }
                if (thisChoice.getBaseProductCode() != thatChoice.getBaseProductCode()) {
                    return false;
                }
                OrderProduct thisChoiceSelection = thisChoice.getSelection();
                OrderProduct thatChoiceSelection = thatChoice.getSelection();
                if (thisChoiceSelection == null && thatChoiceSelection != null && thatChoiceSelection.getBaseProductCode() != 0) {
                    return false;
                }
                if (thisChoiceSelection != null && !thisChoiceSelection.equals(thatChoiceSelection)) {
                    return false;
                }
            }
        }
        return true;
    }
}
