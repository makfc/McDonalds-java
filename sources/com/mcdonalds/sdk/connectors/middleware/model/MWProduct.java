package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;
import com.mcdonalds.sdk.connectors.middleware.helpers.MWCatalogManager;
import com.mcdonalds.sdk.connectors.middleware.helpers.MWConnectorShared;
import com.mcdonalds.sdk.modules.models.Product;
import java.io.Serializable;
import java.util.List;

public class MWProduct implements Serializable {
    @SerializedName("POD")
    public List<MWPOD> PODs;
    @SerializedName("AcceptsLight")
    public boolean acceptsLight;
    @SerializedName("AcceptsOnly")
    public boolean acceptsOnly;
    @SerializedName("Categories")
    public List<MWCategory> categories;
    @SerializedName("Dimensions")
    public List<MWDimension> dimensions;
    @SerializedName("DisplayImageName")
    public String displayImageName;
    @SerializedName("ExtendedMenuTypeID")
    public List<Integer> extendedMenuTypeID;
    @SerializedName("FamilyGroupID")
    public int familyGroupID;
    @SerializedName("IsMcCafe")
    public boolean isMcCafe;
    @SerializedName("IsPromotional")
    public boolean isPromotional;
    @SerializedName("IsPromotionalChoice")
    public boolean isPromotionalChoice;
    @SerializedName("IsSalable")
    public boolean isSalable;
    @SerializedName("MaxChoiceOptionsMOT")
    public Object maxChoiceOptionsMOT;
    @SerializedName("MaxQttyAllowedPerOrder")
    public int maxQttyAllowedPerOrder;
    @SerializedName("MenuTypeID")
    public int menuTypeID;
    @SerializedName("Names")
    public MWNameInfo name;
    @SerializedName("Nutrition")
    public MWNutrition nutrition;
    @SerializedName("ProductCode")
    public int productCode;
    @SerializedName("ProductType")
    public int productType;
    @SerializedName("ProductUnit")
    public String productUnit;
    @SerializedName("PromotionEndDate")
    public String promotionEndDate;
    @SerializedName("PromotionRestriction")
    public Object promotionRestriction;
    @SerializedName("PromotionStartDate")
    public String promotionStartDate;
    @SerializedName("PromotionalLabel")
    public String promotionalLabel;
    @SerializedName("PromotionsAssociated")
    public Object promotionsAssociated;
    @SerializedName("Recipe")
    public MWRecipe recipe;
    @SerializedName("RecipeID")
    public Integer recipeID;
    @SerializedName("StaticData")
    public List<Integer> staticData;
    @SerializedName("TimeRestriction")
    public List<MWOpeningHourItem> timeRestriction;

    @Deprecated
    public Product getProduct(MWCatalogManager manager, boolean isUsingECP3, String languageId, boolean isEnableMultipleMenuTypes, MWConnectorShared sharedData) {
        return null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:70:0x024a  */
    public com.mcdonalds.sdk.modules.models.Product getProduct(com.mcdonalds.sdk.connectors.utils.SerializableSparseArray<com.mcdonalds.sdk.connectors.middleware.model.MWMenuType> r33, com.mcdonalds.sdk.connectors.utils.SerializableSparseArray<com.mcdonalds.sdk.connectors.middleware.model.MWProductPrice> r34, com.mcdonalds.sdk.connectors.utils.SerializableSparseArray<com.mcdonalds.sdk.connectors.middleware.model.MWNameInfo> r35) {
        /*
        r32 = this;
        r11 = com.mcdonalds.sdk.connectors.middleware.MiddlewareConnectorUtils.isUsingECP3();
        if (r11 == 0) goto L_0x00c3;
    L_0x0006:
        r0 = r32;
        r0 = r0.recipe;
        r28 = r0;
        if (r28 == 0) goto L_0x00c0;
    L_0x000e:
        r8 = 1;
    L_0x000f:
        r4 = new com.mcdonalds.sdk.modules.models.Product;
        r4.<init>();
        r0 = r32;
        r0 = r0.productCode;
        r28 = r0;
        r28 = java.lang.Integer.valueOf(r28);
        r0 = r28;
        r4.setExternalId(r0);
        r0 = r32;
        r0 = r0.recipeID;
        r28 = r0;
        r0 = r28;
        r4.setRecipeId(r0);
        if (r8 == 0) goto L_0x00d0;
    L_0x0030:
        r0 = r32;
        r0 = r0.recipeID;
        r28 = r0;
        r28 = r28.toString();
    L_0x003a:
        r0 = r28;
        r4.setId(r0);
        r0 = r32;
        r0 = r0.productType;
        r28 = r0;
        r28 = java.lang.Integer.valueOf(r28);
        r28 = com.mcdonalds.sdk.modules.models.Product.ProductType.valueToProductType(r28);
        r0 = r28;
        r4.setProductType(r0);
        r0 = r32;
        r0 = r0.productUnit;
        r28 = r0;
        r0 = r28;
        r4.setProductUnit(r0);
        r0 = r32;
        r0 = r0.acceptsLight;
        r28 = r0;
        r0 = r28;
        r4.setAcceptsLight(r0);
        r28 = com.mcdonalds.sdk.services.configuration.Configuration.getSharedInstance();
        r13 = r28.getCurrentLanguage();
        r15 = new java.util.ArrayList;
        r15.<init>();
        r28 = "enableMultipleMenuTypes";
        r6 = com.mcdonalds.sdk.services.configuration.AppParameters.getBooleanForParameter(r28);
        if (r33 == 0) goto L_0x0100;
    L_0x007d:
        if (r6 == 0) goto L_0x00d4;
    L_0x007f:
        r0 = r32;
        r0 = r0.extendedMenuTypeID;
        r28 = r0;
        if (r28 == 0) goto L_0x00fd;
    L_0x0087:
        r0 = r32;
        r0 = r0.extendedMenuTypeID;
        r28 = r0;
        r0 = r28;
        r4.setExtendedMenuTypeIDs(r0);
        r0 = r32;
        r0 = r0.extendedMenuTypeID;
        r28 = r0;
        r29 = r28.iterator();
    L_0x009c:
        r28 = r29.hasNext();
        if (r28 == 0) goto L_0x00fd;
    L_0x00a2:
        r28 = r29.next();
        r28 = (java.lang.Integer) r28;
        r14 = r28.intValue();
        r0 = r33;
        r16 = r0.get(r14);
        r16 = (com.mcdonalds.sdk.connectors.middleware.model.MWMenuType) r16;
        if (r16 == 0) goto L_0x009c;
    L_0x00b6:
        r28 = r16.toMenuType();
        r0 = r28;
        r15.add(r0);
        goto L_0x009c;
    L_0x00c0:
        r8 = 0;
        goto L_0x000f;
    L_0x00c3:
        r0 = r32;
        r0 = r0.recipeID;
        r28 = r0;
        if (r28 == 0) goto L_0x00ce;
    L_0x00cb:
        r8 = 1;
    L_0x00cc:
        goto L_0x000f;
    L_0x00ce:
        r8 = 0;
        goto L_0x00cc;
    L_0x00d0:
        r28 = "";
        goto L_0x003a;
    L_0x00d4:
        r0 = r32;
        r0 = r0.menuTypeID;
        r28 = r0;
        r0 = r33;
        r1 = r28;
        r28 = r0.get(r1);
        if (r28 == 0) goto L_0x016b;
    L_0x00e4:
        r0 = r32;
        r0 = r0.menuTypeID;
        r28 = r0;
        r0 = r33;
        r1 = r28;
        r16 = r0.get(r1);
        r16 = (com.mcdonalds.sdk.connectors.middleware.model.MWMenuType) r16;
        r28 = r16.toMenuType();
        r0 = r28;
        r15.add(r0);
    L_0x00fd:
        r4.setMenuTypes(r15);
    L_0x0100:
        r0 = r32;
        r0 = r0.menuTypeID;
        r28 = r0;
        r28 = java.lang.Integer.valueOf(r28);
        r0 = r28;
        r4.setMenuTypeID(r0);
        if (r34 == 0) goto L_0x01bd;
    L_0x0111:
        r0 = r32;
        r0 = r0.productCode;
        r28 = r0;
        r0 = r34;
        r1 = r28;
        r24 = r0.get(r1);
        r24 = (com.mcdonalds.sdk.connectors.middleware.model.MWProductPrice) r24;
        if (r24 == 0) goto L_0x01bd;
    L_0x0123:
        r0 = r24;
        r0 = r0.prices;
        r28 = r0;
        r28 = com.mcdonalds.sdk.utils.ListUtils.isEmpty(r28);
        if (r28 != 0) goto L_0x01bd;
    L_0x012f:
        r0 = r24;
        r0 = r0.prices;
        r28 = r0;
        r28 = r28.iterator();
    L_0x0139:
        r29 = r28.hasNext();
        if (r29 == 0) goto L_0x01bd;
    L_0x013f:
        r18 = r28.next();
        r18 = (com.mcdonalds.sdk.connectors.middleware.model.MWPrice) r18;
        r0 = r18;
        r0 = r0.price;
        r29 = r0;
        if (r29 == 0) goto L_0x0139;
    L_0x014d:
        r0 = r18;
        r0 = r0.priceTypeID;
        r29 = r0;
        r29 = r29.intValue();
        switch(r29) {
            case 1: goto L_0x015b;
            case 2: goto L_0x019c;
            case 3: goto L_0x01ac;
            default: goto L_0x015a;
        };
    L_0x015a:
        goto L_0x0139;
    L_0x015b:
        r0 = r18;
        r0 = r0.price;
        r29 = r0;
        r30 = r29.doubleValue();
        r0 = r30;
        r4.setPriceEatIn(r0);
        goto L_0x0139;
    L_0x016b:
        r0 = r32;
        r0 = r0.menuTypeID;
        r28 = r0;
        r29 = 2;
        r0 = r28;
        r1 = r29;
        if (r0 != r1) goto L_0x00fd;
    L_0x0179:
        r9 = 0;
    L_0x017a:
        r28 = r33.size();
        r0 = r28;
        if (r9 >= r0) goto L_0x00fd;
    L_0x0182:
        r0 = r33;
        r12 = r0.keyAt(r9);
        r0 = r33;
        r16 = r0.get(r12);
        r16 = (com.mcdonalds.sdk.connectors.middleware.model.MWMenuType) r16;
        r28 = r16.toMenuType();
        r0 = r28;
        r15.add(r0);
        r9 = r9 + 1;
        goto L_0x017a;
    L_0x019c:
        r0 = r18;
        r0 = r0.price;
        r29 = r0;
        r30 = r29.doubleValue();
        r0 = r30;
        r4.setPriceTakeOut(r0);
        goto L_0x0139;
    L_0x01ac:
        r0 = r18;
        r0 = r0.price;
        r29 = r0;
        r30 = r29.doubleValue();
        r0 = r30;
        r4.setPriceDelivery(r0);
        goto L_0x0139;
    L_0x01bd:
        r0 = r32;
        r0 = r0.categories;
        r28 = r0;
        if (r28 == 0) goto L_0x01fa;
    L_0x01c5:
        r0 = r32;
        r0 = r0.categories;
        r28 = r0;
        r26 = r28.size();
        r25 = new java.util.ArrayList;
        r25.<init>(r26);
        r9 = 0;
    L_0x01d5:
        r0 = r26;
        if (r9 >= r0) goto L_0x01f5;
    L_0x01d9:
        r0 = r32;
        r0 = r0.categories;
        r28 = r0;
        r0 = r28;
        r28 = r0.get(r9);
        r28 = (com.mcdonalds.sdk.connectors.middleware.model.MWCategory) r28;
        r28 = r28.toCategory();
        r0 = r25;
        r1 = r28;
        r0.add(r1);
        r9 = r9 + 1;
        goto L_0x01d5;
    L_0x01f5:
        r0 = r25;
        r4.setCategories(r0);
    L_0x01fa:
        r20 = 0;
        if (r11 == 0) goto L_0x0348;
    L_0x01fe:
        r0 = r32;
        r0 = r0.name;
        r20 = r0;
    L_0x0204:
        if (r20 == 0) goto L_0x026b;
    L_0x0206:
        r19 = 0;
        r0 = r20;
        r0 = r0.names;
        r23 = r0;
        r28 = r23.iterator();
    L_0x0212:
        r29 = r28.hasNext();
        if (r29 == 0) goto L_0x0248;
    L_0x0218:
        r17 = r28.next();
        r17 = (com.mcdonalds.sdk.connectors.middleware.model.MWName) r17;
        r0 = r17;
        r0 = r0.languageID;
        r22 = r0;
        r29 = "-";
        r0 = r22;
        r1 = r29;
        r29 = r0.lastIndexOf(r1);
        r5 = r29 + 1;
        r29 = 0;
        r30 = r5 + -1;
        r0 = r22;
        r1 = r29;
        r2 = r30;
        r21 = r0.substring(r1, r2);
        r0 = r21;
        r29 = r0.equals(r13);
        if (r29 == 0) goto L_0x0212;
    L_0x0246:
        r19 = r17;
    L_0x0248:
        if (r19 == 0) goto L_0x026b;
    L_0x024a:
        r0 = r19;
        r0 = r0.name;
        r28 = r0;
        r0 = r28;
        r4.setName(r0);
        r0 = r19;
        r0 = r0.shortName;
        r28 = r0;
        r0 = r28;
        r4.setShortName(r0);
        r0 = r19;
        r0 = r0.longName;
        r28 = r0;
        r0 = r28;
        r4.setLongName(r0);
    L_0x026b:
        r28 = "Core";
        r0 = r28;
        r4.setDoNotShow(r0);
        r0 = r32;
        r0 = r0.PODs;
        r28 = r0;
        r0 = r28;
        r4.setPODs(r0);
        r0 = r32;
        r0 = r0.nutrition;
        r28 = r0;
        if (r28 == 0) goto L_0x02b2;
    L_0x0285:
        r0 = r32;
        r0 = r0.nutrition;
        r28 = r0;
        r28 = r28.allNutrients();
        r0 = r28;
        r4.setNutrients(r0);
        r0 = r32;
        r0 = r0.nutrition;
        r28 = r0;
        r28 = r28.getEnergy();
        r0 = r28;
        r4.setEnergy(r0);
        r0 = r32;
        r0 = r0.nutrition;
        r28 = r0;
        r28 = r28.getkCal();
        r0 = r28;
        r4.setKCal(r0);
    L_0x02b2:
        r0 = r32;
        r0 = r0.displayImageName;
        r28 = r0;
        r28 = android.text.TextUtils.isEmpty(r28);
        if (r28 != 0) goto L_0x0322;
    L_0x02be:
        r28 = new java.lang.StringBuilder;
        r28.<init>();
        r29 = "staticDataBaseURL";
        r29 = com.mcdonalds.sdk.services.configuration.AppParameters.getAppParameter(r29);
        r28 = r28.append(r29);
        r0 = r32;
        r0 = r0.displayImageName;
        r29 = r0;
        r28 = r28.append(r29);
        r10 = r28.toString();
        r27 = new com.mcdonalds.sdk.modules.models.ImageInfo;
        r27.<init>();
        r0 = r32;
        r0 = r0.displayImageName;
        r28 = r0;
        r27.setImageName(r28);
        r28 = com.mcdonalds.sdk.connectors.middleware.helpers.MWConnectorShared.ImageSize.SMALL;
        r0 = r28;
        r28 = com.mcdonalds.sdk.connectors.middleware.helpers.MWConnectorShared.getFullImagePath(r10, r0);
        r27.setUrl(r28);
        r0 = r27;
        r4.setThumbnailImage(r0);
        r28 = r27.getUrl();
        r0 = r28;
        r4.setImageUrl(r0);
        r7 = new com.mcdonalds.sdk.modules.models.ImageInfo;
        r7.<init>();
        r0 = r32;
        r0 = r0.displayImageName;
        r28 = r0;
        r0 = r28;
        r7.setImageName(r0);
        r28 = com.mcdonalds.sdk.connectors.middleware.helpers.MWConnectorShared.ImageSize.LARGE;
        r0 = r28;
        r28 = com.mcdonalds.sdk.connectors.middleware.helpers.MWConnectorShared.getFullImagePath(r10, r0);
        r0 = r28;
        r7.setUrl(r0);
        r4.setCarouselImage(r7);
    L_0x0322:
        r0 = r32;
        r0 = r0.maxQttyAllowedPerOrder;
        r28 = r0;
        r28 = java.lang.Integer.valueOf(r28);
        r0 = r28;
        r4.setMaxQttyAllowedPerOrder(r0);
        r28 = 1;
        r28 = java.lang.Boolean.valueOf(r28);
        r0 = r28;
        r4.setNeedsFullDetails(r0);
        r0 = r32;
        r0 = r0.timeRestriction;
        r28 = r0;
        r0 = r28;
        r4.setTimeRestriction(r0);
        return r4;
    L_0x0348:
        if (r35 == 0) goto L_0x0204;
    L_0x034a:
        r0 = r32;
        r0 = r0.productCode;
        r28 = r0;
        r0 = r35;
        r1 = r28;
        r20 = r0.get(r1);
        r20 = (com.mcdonalds.sdk.connectors.middleware.model.MWNameInfo) r20;
        goto L_0x0204;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mcdonalds.sdk.connectors.middleware.model.MWProduct.getProduct(com.mcdonalds.sdk.connectors.utils.SerializableSparseArray, com.mcdonalds.sdk.connectors.utils.SerializableSparseArray, com.mcdonalds.sdk.connectors.utils.SerializableSparseArray):com.mcdonalds.sdk.modules.models.Product");
    }
}
