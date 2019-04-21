package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;
import com.mcdonalds.sdk.modules.models.ImageInfo;
import com.mcdonalds.sdk.modules.models.NutritionRecipe;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public abstract class MWRecipeItem implements Serializable {
    private static final String RELATION_TYPE_OZ_CUP = "oz cup";
    private static final String RELATION_TYPE_PIECE = "piece";
    private static final String RELATION_TYPE_SIZE = "size";
    @SerializedName("attach_carousel_image")
    public MWCarouselImage carouselImage;
    protected final transient Comparator<Entry<String, String>> comparator = new C25051();
    @SerializedName("default_category")
    public MWDefaultCategory defaultCategory;
    @SerializedName("do_not_show")
    public String doNotShow;
    @SerializedName("external_id")
    public String externalId;
    @SerializedName("footer")
    public HashMap<String, String> footer;
    @SerializedName("id")
    /* renamed from: id */
    public String f6067id;
    @SerializedName("item_additional_allergen")
    public String itemAdditionalAllergen;
    @SerializedName("item_allergen")
    public String itemAllergen;
    @SerializedName("attach_item_hero_image")
    public MWItemHeroImage itemHeroImage;
    @SerializedName("relation_types")
    public MWMenuItemRelationTypes itemRelationTypes;
    @SerializedName("attach_item_thumbnail_image")
    public MWItemThumbnailImage itemThumbNailImage;
    @SerializedName("item_marketing_name")
    public String marketingName;
    @SerializedName("menu_item_no")
    public String menuItemNumber;
    @SerializedName("item_name")
    public String name;
    @SerializedName("nutrient_facts")
    public MWNutrientFacts nutrientFacts;
    @SerializedName("serving_size_imperial")
    public MWNutrient servingSizeImperial;
    @SerializedName("serving_size_metric")
    public MWNutrient servingSizeMetric;

    /* renamed from: com.mcdonalds.sdk.connectors.middleware.model.MWRecipeItem$1 */
    class C25051 implements Comparator<Entry<String, String>> {
        C25051() {
        }

        public int compare(Entry<String, String> lhsEntry, Entry<String, String> rhsEntry) {
            int lhs;
            int rhs;
            String lhsKey = ((String) lhsEntry.getKey()).replaceAll("[^0-9]+", "");
            String rhsKey = ((String) rhsEntry.getKey()).replaceAll("[^0-9]+", "");
            try {
                lhs = Integer.parseInt(lhsKey);
            } catch (NumberFormatException e) {
                lhs = Integer.MAX_VALUE;
            }
            try {
                rhs = Integer.parseInt(rhsKey);
            } catch (NumberFormatException e2) {
                rhs = Integer.MAX_VALUE;
            }
            if (lhs > rhs) {
                return 1;
            }
            return lhs < rhs ? -1 : 0;
        }
    }

    public NutritionRecipe toRecipe(String baseImagePath) {
        NutritionRecipe recipe = new NutritionRecipe();
        recipe.setId(this.f6067id);
        recipe.setDoNotShow(this.doNotShow);
        setRecipeImages(recipe, baseImagePath);
        setRecipeFooters(recipe);
        return recipe;
    }

    /* Access modifiers changed, original: protected */
    public void setRecipeImages(NutritionRecipe recipe, String baseImagePath) {
        if (this.carouselImage == null || this.carouselImage.isEmpty()) {
            recipe.setCarouselImage(null);
        } else {
            recipe.setCarouselImage(this.carouselImage.toImageInfo(baseImagePath));
        }
        if (this.itemThumbNailImage == null || this.itemThumbNailImage.isEmpty()) {
            recipe.setThumbnailImage(null);
        } else {
            ImageInfo recipeThumbnailImage = this.itemThumbNailImage.toImageInfo(baseImagePath);
            recipe.setThumbnailImage(recipeThumbnailImage);
            recipe.setImageUrl(recipeThumbnailImage.getUrl());
        }
        if (this.itemHeroImage == null || this.itemHeroImage.isEmpty()) {
            recipe.setHeroImage(null);
        } else {
            recipe.setHeroImage(this.itemHeroImage.toImageInfo(baseImagePath));
        }
    }

    /* Access modifiers changed, original: protected */
    public void setRecipeFooters(NutritionRecipe recipe) {
        if (this.footer != null) {
            List<Entry<String, String>> sortedNames = new ArrayList();
            List<Entry<String, String>> sortedValues = new ArrayList();
            for (Entry<String, String> mapEntry : this.footer.entrySet()) {
                if (((String) mapEntry.getKey()).contains("name")) {
                    sortedNames.add(mapEntry);
                } else {
                    sortedValues.add(mapEntry);
                }
            }
            if (sortedNames.size() == sortedValues.size()) {
                Collections.sort(sortedNames, this.comparator);
                Collections.sort(sortedValues, this.comparator);
                List<String> footers = new ArrayList();
                int size = sortedValues.size();
                for (int i = 0; i < size; i++) {
                    if (((String) ((Entry) sortedValues.get(i)).getValue()).toLowerCase().equals("yes")) {
                        footers.add(((Entry) sortedNames.get(i)).getValue());
                    }
                }
                recipe.setFooters(footers);
            }
        }
    }

    /* Access modifiers changed, original: protected */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0051 A:{LOOP_END, LOOP:1: B:16:0x004f->B:17:0x0051} */
    public void setRelationTypeToCategory(com.mcdonalds.sdk.modules.models.NutritionRecipe r10) {
        /*
        r9 = this;
        r6 = r9.itemRelationTypes;
        if (r6 != 0) goto L_0x0005;
    L_0x0004:
        return;
    L_0x0005:
        r6 = r9.itemRelationTypes;
        r2 = r6.getItemRelationType();
        if (r2 == 0) goto L_0x0004;
    L_0x000d:
        r6 = r2.isEmpty();
        if (r6 != 0) goto L_0x0004;
    L_0x0013:
        r6 = r2.iterator();
    L_0x0017:
        r7 = r6.hasNext();
        if (r7 == 0) goto L_0x0004;
    L_0x001d:
        r4 = r6.next();
        r4 = (com.mcdonalds.sdk.connectors.middleware.model.MWMenuItemRelationType) r4;
        r7 = r4.type;
        r8 = "size";
        r7 = r7.equalsIgnoreCase(r8);
        if (r7 != 0) goto L_0x0041;
    L_0x002d:
        r7 = r4.type;
        r8 = "oz cup";
        r7 = r7.equalsIgnoreCase(r8);
        if (r7 != 0) goto L_0x0041;
    L_0x0037:
        r7 = r4.type;
        r8 = "piece";
        r7 = r7.equalsIgnoreCase(r8);
        if (r7 == 0) goto L_0x0017;
    L_0x0041:
        r6 = r4.menuItemRelatedItems;
        r6 = r6.menuItemRelatedItemList;
        r5 = r6.size();
        r3 = new java.util.ArrayList;
        r3.<init>(r5);
        r0 = 0;
    L_0x004f:
        if (r0 >= r5) goto L_0x0065;
    L_0x0051:
        r6 = r4.menuItemRelatedItems;
        r6 = r6.menuItemRelatedItemList;
        r1 = r6.get(r0);
        r1 = (com.mcdonalds.sdk.connectors.middleware.model.MWMenuItemRelatedItem) r1;
        r6 = r1.toRelationItem();
        r3.add(r6);
        r0 = r0 + 1;
        goto L_0x004f;
    L_0x0065:
        r10.setRelationItems(r3);
        goto L_0x0004;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mcdonalds.sdk.connectors.middleware.model.MWRecipeItem.setRelationTypeToCategory(com.mcdonalds.sdk.modules.models.NutritionRecipe):void");
    }
}
