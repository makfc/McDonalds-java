package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;
import com.mcdonalds.sdk.modules.models.AdvertisablePromotionEntity;
import com.mcdonalds.sdk.utils.ListUtils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class MWAdvertisable {
    @SerializedName("i18n")
    public String advertisableName;
    @SerializedName("Conditions")
    public MWAdvertisableConditions conditions;
    @SerializedName("Id")
    /* renamed from: id */
    public int f6064id;
    @SerializedName("IsAdvertisable")
    public boolean isAdvertisable;
    @SerializedName("LongDescription")
    public String longDescription;
    @SerializedName("Name")
    public String name;
    @SerializedName("ProductSets")
    public List<MWAdvertisableProductSet> productSets;
    @SerializedName("ShortDescription")
    public String shortDescription;
    @SerializedName("TimeRestriction")
    public List<Object> timeRestriction;

    public List<AdvertisablePromotionEntity> toAdvertisablePromotionEntity(int storeId) {
        int baseProductId;
        int swapProductId;
        List<AdvertisablePromotionEntity> list = new ArrayList();
        if (ListUtils.isEmpty(this.productSets) || this.productSets.get(0) == null || ListUtils.isEmpty(((MWAdvertisableProductSet) this.productSets.get(0)).swapMapping) || ((MWAdvertisableProductSet) this.productSets.get(0)).swapMapping.get(0) == null) {
            baseProductId = -1;
        } else {
            baseProductId = Integer.valueOf(((MWAdvertisableSwapMapping) ((MWAdvertisableProductSet) this.productSets.get(0)).swapMapping.get(0)).regular).intValue();
        }
        if (ListUtils.isEmpty(this.productSets) || this.productSets.get(0) == null || ListUtils.isEmpty(((MWAdvertisableProductSet) this.productSets.get(0)).swapMapping) || ((MWAdvertisableProductSet) this.productSets.get(0)).swapMapping.get(0) == null) {
            swapProductId = -1;
        } else {
            swapProductId = Integer.valueOf(((MWAdvertisableSwapMapping) ((MWAdvertisableProductSet) this.productSets.get(0)).swapMapping.get(0)).swap).intValue();
        }
        if (!(this.conditions == null || ListUtils.isEmpty(this.conditions.dayOfWeekConditions))) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("E", Locale.getDefault());
            for (String dayOfWeek : this.conditions.dayOfWeekConditions) {
                try {
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(dateFormat.parse(dayOfWeek));
                    int weekday = calendar.get(7);
                    AdvertisablePromotionEntity advertisablePromotionEntity = new AdvertisablePromotionEntity();
                    advertisablePromotionEntity.setStoreId(storeId);
                    advertisablePromotionEntity.setBaseProductId(baseProductId);
                    advertisablePromotionEntity.setSwapProductId(swapProductId);
                    advertisablePromotionEntity.setWeekday(weekday);
                    list.add(advertisablePromotionEntity);
                } catch (ParseException e) {
                }
            }
        }
        return list;
    }
}
