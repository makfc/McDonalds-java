package com.mcdonalds.sdk.modules.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.mcdonalds.sdk.modules.AppModel;
import com.mcdonalds.sdk.modules.storelocator.Store;
import java.util.ArrayList;
import java.util.List;

public class MarketCatalog extends AppModel implements Parcelable {
    public static final Creator<MarketCatalog> CREATOR = new C40541();
    private List<Category> mCategories;
    private String mCategoriesVersion;
    private List<Facility> mFacilities;
    private String mFacilitiesVersion;
    private List<FeedBackType> mFeedBackTypes;
    private String mFeedBackTypesVersion;
    private List<MenuType> mMenuTypes;
    private String mMenuTypesVersion;
    private String mNamesVersion;
    private List<PaymentMethod> mPaymentMethods;
    private String mPaymentMethodsVersion;
    private List<Promotion> mPromotions;
    private String mPromotionsVersion;
    private String mRecipesVersion;
    private List<SocialNetwork> mSocialNetworks;
    private String mSocialNetworksVersion;
    private List<Store> mStores;
    private String mStoresVersion;
    private List<TenderType> mTenderTypes;
    private String mTenderTypesVersion;

    /* renamed from: com.mcdonalds.sdk.modules.models.MarketCatalog$1 */
    static class C40541 implements Creator<MarketCatalog> {
        C40541() {
        }

        public MarketCatalog createFromParcel(Parcel source) {
            return new MarketCatalog(source);
        }

        public MarketCatalog[] newArray(int size) {
            return new MarketCatalog[size];
        }
    }

    public String getCategoriesVersion() {
        return this.mCategoriesVersion;
    }

    public void setCategoriesVersion(String categoriesVersion) {
        this.mCategoriesVersion = categoriesVersion;
    }

    public String getFacilitiesVersion() {
        return this.mFacilitiesVersion;
    }

    public void setFacilitiesVersion(String facilitiesVersion) {
        this.mFacilitiesVersion = facilitiesVersion;
    }

    public String getStoresVersion() {
        return this.mStoresVersion;
    }

    public void setStoresVersion(String storesVersion) {
        this.mStoresVersion = storesVersion;
    }

    public String getPaymentMethodsVersion() {
        return this.mPaymentMethodsVersion;
    }

    public void setPaymentMethodsVersion(String paymentMethodsVersion) {
        this.mPaymentMethodsVersion = paymentMethodsVersion;
    }

    public String getFeedBackTypesVersion() {
        return this.mFeedBackTypesVersion;
    }

    public void setFeedBackTypesVersion(String feedBackTypesVersion) {
        this.mFeedBackTypesVersion = feedBackTypesVersion;
    }

    public String getTenderTypesVersion() {
        return this.mTenderTypesVersion;
    }

    public void setTenderTypesVersion(String tenderTypesVersion) {
        this.mTenderTypesVersion = tenderTypesVersion;
    }

    public String getPromotionsVersion() {
        return this.mPromotionsVersion;
    }

    public void setPromotionsVersion(String promotionsVersion) {
        this.mPromotionsVersion = promotionsVersion;
    }

    public String getMenuTypesVersion() {
        return this.mMenuTypesVersion;
    }

    public void setMenuTypesVersion(String menuTypesVersion) {
        this.mMenuTypesVersion = menuTypesVersion;
    }

    public String getSocialNetworksVersion() {
        return this.mSocialNetworksVersion;
    }

    public void setSocialNetworksVersion(String socialNetworksVersion) {
        this.mSocialNetworksVersion = socialNetworksVersion;
    }

    public String getNamesVersion() {
        return this.mNamesVersion;
    }

    public void setNamesVersion(String namesVersion) {
        this.mNamesVersion = namesVersion;
    }

    public String getRecipesVersion() {
        return this.mRecipesVersion;
    }

    public void setRecipesVersion(String recipesVersion) {
        this.mRecipesVersion = recipesVersion;
    }

    public List<Category> getCategories() {
        return this.mCategories;
    }

    public void setCategories(List<Category> categories) {
        this.mCategories = categories;
    }

    public List<Facility> getFacilities() {
        return this.mFacilities;
    }

    public void setFacilities(List<Facility> facilities) {
        this.mFacilities = facilities;
    }

    public List<Store> getStores() {
        return this.mStores;
    }

    public void setStores(List<Store> stores) {
        this.mStores = stores;
    }

    public List<PaymentMethod> getPaymentMethods() {
        return this.mPaymentMethods;
    }

    public void setPaymentMethods(List<PaymentMethod> paymentMethods) {
        this.mPaymentMethods = paymentMethods;
    }

    public List<FeedBackType> getFeedBackTypes() {
        return this.mFeedBackTypes;
    }

    public void setFeedBackTypes(List<FeedBackType> feedBackTypes) {
        this.mFeedBackTypes = feedBackTypes;
    }

    public List<TenderType> getTenderTypes() {
        return this.mTenderTypes;
    }

    public void setTenderTypes(List<TenderType> tenderTypes) {
        this.mTenderTypes = tenderTypes;
    }

    public List<Promotion> getPromotions() {
        return this.mPromotions;
    }

    public void setPromotions(List<Promotion> promotions) {
        this.mPromotions = promotions;
    }

    public List<MenuType> getMenuTypes() {
        return this.mMenuTypes;
    }

    public void setMenuTypes(List<MenuType> menuTypes) {
        this.mMenuTypes = menuTypes;
    }

    public List<SocialNetwork> getSocialNetworks() {
        return this.mSocialNetworks;
    }

    public void setSocialNetworks(List<SocialNetwork> socialNetworks) {
        this.mSocialNetworks = socialNetworks;
    }

    public boolean isEmptyCatalog() {
        return this.mCategories.isEmpty() && this.mFacilities.isEmpty() && this.mStores.isEmpty() && this.mPaymentMethods.isEmpty() && this.mFeedBackTypes.isEmpty() && this.mTenderTypes.isEmpty() && this.mPromotions.isEmpty();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mCategoriesVersion);
        dest.writeTypedList(this.mCategories);
        dest.writeString(this.mFacilitiesVersion);
        dest.writeList(this.mFacilities);
        dest.writeString(this.mStoresVersion);
        dest.writeList(this.mStores);
        dest.writeString(this.mPaymentMethodsVersion);
        dest.writeList(this.mPaymentMethods);
        dest.writeString(this.mFeedBackTypesVersion);
        dest.writeList(this.mFeedBackTypes);
        dest.writeString(this.mTenderTypesVersion);
        dest.writeList(this.mTenderTypes);
        dest.writeString(this.mPromotionsVersion);
        dest.writeList(this.mPromotions);
        dest.writeString(this.mMenuTypesVersion);
        dest.writeTypedList(this.mMenuTypes);
        dest.writeString(this.mSocialNetworksVersion);
        dest.writeList(this.mSocialNetworks);
        dest.writeString(this.mNamesVersion);
        dest.writeString(this.mRecipesVersion);
    }

    protected MarketCatalog(Parcel in) {
        this.mCategoriesVersion = in.readString();
        this.mCategories = in.createTypedArrayList(Category.CREATOR);
        this.mFacilitiesVersion = in.readString();
        this.mFacilities = new ArrayList();
        in.readList(this.mFacilities, Facility.class.getClassLoader());
        this.mStoresVersion = in.readString();
        this.mStores = new ArrayList();
        in.readList(this.mStores, Store.class.getClassLoader());
        this.mPaymentMethodsVersion = in.readString();
        this.mPaymentMethods = new ArrayList();
        in.readList(this.mPaymentMethods, PaymentMethod.class.getClassLoader());
        this.mFeedBackTypesVersion = in.readString();
        this.mFeedBackTypes = new ArrayList();
        in.readList(this.mFeedBackTypes, FeedBackType.class.getClassLoader());
        this.mTenderTypesVersion = in.readString();
        this.mTenderTypes = new ArrayList();
        in.readList(this.mTenderTypes, TenderType.class.getClassLoader());
        this.mPromotionsVersion = in.readString();
        this.mPromotions = new ArrayList();
        in.readList(this.mPromotions, Promotion.class.getClassLoader());
        this.mMenuTypesVersion = in.readString();
        this.mMenuTypes = in.createTypedArrayList(MenuType.CREATOR);
        this.mSocialNetworksVersion = in.readString();
        this.mSocialNetworks = new ArrayList();
        in.readList(this.mSocialNetworks, SocialNetwork.class.getClassLoader());
        this.mNamesVersion = in.readString();
        this.mRecipesVersion = in.readString();
    }
}
