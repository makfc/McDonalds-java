package com.mcdonalds.sdk.services.data.proxy;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.mcdonalds.sdk.McDonalds;
import com.mcdonalds.sdk.modules.models.ImageInfo;
import com.mcdonalds.sdk.modules.models.Ingredient;
import com.mcdonalds.sdk.modules.models.MenuType;
import com.mcdonalds.sdk.modules.models.Pod;
import com.mcdonalds.sdk.modules.models.Product;
import com.mcdonalds.sdk.modules.models.ProductDimension;
import com.mcdonalds.sdk.modules.models.StoreProduct;
import com.mcdonalds.sdk.services.data.repository.ProductRepository;
import java.util.Calendar;
import java.util.List;

public class ProductProxy extends Product {
    public static final Creator<ProductProxy> CREATOR = new C41281();
    private boolean choicesLoaded;

    /* renamed from: com.mcdonalds.sdk.services.data.proxy.ProductProxy$1 */
    static class C41281 implements Creator<ProductProxy> {
        C41281() {
        }

        public ProductProxy createFromParcel(Parcel in) {
            return new ProductProxy(in);
        }

        public ProductProxy[] newArray(int size) {
            return new ProductProxy[size];
        }
    }

    public ProductProxy() {
        this.choicesLoaded = false;
    }

    public Integer getExternalId() {
        return super.getExternalId();
    }

    public String getLongName() {
        if (isAdvertisable().booleanValue()) {
            return getAdvertisableProduct().getLongName();
        }
        return super.getLongName();
    }

    public String getShortName() {
        if (isAdvertisable().booleanValue()) {
            return getAdvertisableProduct().getShortName();
        }
        return super.getShortName();
    }

    public String getMarketingName() {
        if (isAdvertisable().booleanValue()) {
            return getAdvertisableProduct().getMarketingName();
        }
        return super.getMarketingName();
    }

    public String getName() {
        if (isAdvertisable().booleanValue()) {
            return getAdvertisableProduct().getName();
        }
        return super.getName();
    }

    public ImageInfo getThumbnailImage() {
        if (!isAdvertisable().booleanValue() || getAdvertisableProduct().getThumbnailImage() == null) {
            return super.getThumbnailImage();
        }
        return getAdvertisableProduct().getThumbnailImage();
    }

    public ImageInfo getCarouselImage() {
        if (!isAdvertisable().booleanValue() || getAdvertisableProduct().getCarouselImage() == null) {
            return super.getCarouselImage();
        }
        return getAdvertisableProduct().getCarouselImage();
    }

    public List<Ingredient> getIngredients() {
        if (isAdvertisable().booleanValue()) {
            return ProductRepository.getProductIngredients(getContext(), getAdvertisableProduct());
        }
        List<Ingredient> ingredients = super.getIngredients();
        if (ingredients != null) {
            return ingredients;
        }
        ingredients = ProductRepository.getProductIngredients(getContext(), this);
        setIngredients(ingredients);
        return ingredients;
    }

    public List<Ingredient> getExtras() {
        if (isAdvertisable().booleanValue()) {
            return ProductRepository.getProductExtras(getContext(), getAdvertisableProduct());
        }
        List<Ingredient> extras = super.getExtras();
        if (extras != null) {
            return extras;
        }
        extras = ProductRepository.getProductExtras(getContext(), this);
        setExtras(extras);
        return extras;
    }

    public List<Ingredient> getChoices() {
        List<Ingredient> choices = super.getChoices();
        if (!hasChoice().booleanValue() || choices != null) {
            return choices;
        }
        choices = ProductRepository.getProductChoices(getContext(), this);
        setChoices(choices);
        this.choicesLoaded = true;
        return choices;
    }

    public List<ProductDimension> getDimensions() {
        List<ProductDimension> dimensions = getStoreProduct().getDimensions();
        if (dimensions != null) {
            return dimensions;
        }
        dimensions = ProductRepository.getProductDimensions(getContext(), getStoreProduct());
        getStoreProduct().setDimensions(dimensions);
        return dimensions;
    }

    public List<MenuType> getMenuTypes() {
        List<MenuType> menuTypes = getStoreProduct().getMenuTypes();
        if (menuTypes != null) {
            return menuTypes;
        }
        menuTypes = ProductRepository.getProductMenuTypes(getContext(), getStoreProduct());
        getStoreProduct().setMenuTypes(menuTypes);
        return menuTypes;
    }

    public List<Pod> getPODObjects() {
        List<Pod> pods = getStoreProduct().getPODs();
        if (pods != null) {
            return pods;
        }
        pods = ProductRepository.getProductPods(getContext(), getStoreProduct());
        getStoreProduct().setPODs(pods);
        return pods;
    }

    public StoreProduct getStoreProduct() {
        StoreProduct storeProduct = super.getStoreProduct();
        if (storeProduct == null) {
            storeProduct = ProductRepository.getStoreProduct(getContext(), super.getExternalId().intValue());
            setStoreProduct(storeProduct);
        }
        if (storeProduct == null) {
            return new StoreProduct();
        }
        return storeProduct;
    }

    public double getPriceEatIn() {
        if (isAdvertisable().booleanValue()) {
            return getAdvertisableProduct().getPriceEatIn();
        }
        return getStoreProduct().getPriceEatIn();
    }

    public double getPriceTakeOut() {
        if (isAdvertisable().booleanValue()) {
            return getAdvertisableProduct().getPriceTakeOut();
        }
        return getStoreProduct().getPriceTakeOut();
    }

    public double getPriceDelivery() {
        if (isAdvertisable().booleanValue()) {
            return getAdvertisableProduct().getPriceDelivery();
        }
        return getStoreProduct().getPriceDelivery();
    }

    public double getBasePriceEatIn() {
        if (isAdvertisable().booleanValue()) {
            return getAdvertisableProduct().getBasePriceEatIn();
        }
        return getStoreProduct().getBasePriceEatIn();
    }

    public double getBasePriceTakeOut() {
        if (isAdvertisable().booleanValue()) {
            return getAdvertisableProduct().getBasePriceTakeOut();
        }
        return getStoreProduct().getBasePriceTakeOut();
    }

    public double getBasePriceDelivery() {
        if (isAdvertisable().booleanValue()) {
            return getAdvertisableProduct().getBasePriceDelivery();
        }
        return getStoreProduct().getBasePriceDelivery();
    }

    public Boolean isSingleChoice() {
        boolean retValue = false;
        if (getStoreProduct().isSingleChoice() != null) {
            retValue = getStoreProduct().isSingleChoice().booleanValue();
        }
        return Boolean.valueOf(retValue);
    }

    public Boolean hasChoice() {
        boolean retValue = false;
        if (getStoreProduct().hasChoice() != null) {
            retValue = getStoreProduct().hasChoice().booleanValue();
        }
        return Boolean.valueOf(retValue);
    }

    public Boolean hasNonSingleChoiceChoice() {
        boolean retValue = false;
        if (getStoreProduct().hasNonSingleChoiceChoice() != null) {
            retValue = getStoreProduct().hasNonSingleChoiceChoice().booleanValue();
        }
        return Boolean.valueOf(retValue);
    }

    public Double getEnergy() {
        return getStoreProduct().getEnergy();
    }

    public Double getKCal() {
        return Double.valueOf(getStoreProduct().getKCal());
    }

    public Product getAdvertisableProduct() {
        Product advertisableProduct = super.getAdvertisableProduct();
        int weekday = Calendar.getInstance().get(7);
        int advertisableWeekDay = getAdvertisableWeekDay();
        if ((weekday == advertisableWeekDay && advertisableProduct != null) || advertisableWeekDay == -1) {
            return advertisableProduct;
        }
        setAdvertisableWeekDay(weekday);
        advertisableProduct = ProductRepository.getByProductCode(getContext(), super.getExternalId().intValue(), false).getAdvertisableProduct();
        setAdvertisableProduct(advertisableProduct);
        return advertisableProduct;
    }

    public int getAdvertisableBaseProductId() {
        if (isAdvertisable().booleanValue()) {
            return super.getExternalId().intValue();
        }
        return super.getAdvertisableBaseProductId();
    }

    private Context getContext() {
        return McDonalds.getContext();
    }

    protected ProductProxy(Parcel in) {
        super(in);
        this.choicesLoaded = in.readByte() != (byte) 0;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeByte((byte) (this.choicesLoaded ? 1 : 0));
    }
}
