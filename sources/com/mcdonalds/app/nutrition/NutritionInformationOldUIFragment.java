package com.mcdonalds.app.nutrition;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.modules.models.Nutrient;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class NutritionInformationOldUIFragment extends NutritionInformationFragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(C2658R.layout.fragment_nutrition_information, container, false);
        rootView.findViewById(C2358R.C2357id.nutritional_information_container).setVisibility(0);
        this.mNutritionDetailsHighlightLayout = (LinearLayout) rootView.findViewById(C2358R.C2357id.nutrition_highlight_layout);
        this.mNutritionDetailsLayout = (LinearLayout) rootView.findViewById(C2358R.C2357id.nutrition_details_list);
        this.mRecipeComponentsLayout = (LinearLayout) rootView.findViewById(C2358R.C2357id.components_list_layout);
        this.mImportantNotesLayout = (LinearLayout) rootView.findViewById(C2358R.C2357id.important_note_list_layout);
        this.mItemImage = (ImageView) rootView.findViewById(2131820643);
        this.mDescription = (TextView) rootView.findViewById(C2358R.C2357id.nutritional_product_description);
        if (getActivity().getActionBar() != null) {
            getActivity().getActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(C2658R.color.xlight_gray_background)));
        }
        return rootView;
    }

    /* Access modifiers changed, original: protected */
    public void fillRecipeLayout(String productName, String ingredients, String allergen, String additionalAllergen) {
        Ensighten.evaluateEvent(this, "fillRecipeLayout", new Object[]{productName, ingredients, allergen, additionalAllergen});
        View componentDetailView = ((LayoutInflater) getActivity().getSystemService("layout_inflater")).inflate(C2658R.layout.old_ingredients_list_item, null);
        TextView componentNameView = (TextView) componentDetailView.findViewById(C2358R.C2357id.ingredient_name);
        TextView componentDescriptionView = (TextView) componentDetailView.findViewById(C2358R.C2357id.ingredient_description);
        TextView componentAllergenView = (TextView) componentDetailView.findViewById(C2358R.C2357id.allergens_text);
        TextView componentAdditionalAllergenView = (TextView) componentDetailView.findViewById(C2358R.C2357id.additiona_allergens_text);
        if (productName == null || !productName.isEmpty()) {
            componentNameView.setText(productName);
        } else {
            componentDetailView.findViewById(C2358R.C2357id.ingredient_name).setVisibility(8);
            componentDetailView.findViewById(C2358R.C2357id.top_divider).setVisibility(8);
            componentDetailView.findViewById(C2358R.C2357id.bottom_divider).setVisibility(8);
        }
        if (ingredients == null || ingredients.isEmpty()) {
            componentDescriptionView.setVisibility(8);
            componentDetailView.findViewById(C2358R.C2357id.top_divider).setVisibility(8);
            componentDetailView.findViewById(C2358R.C2357id.bottom_divider).setVisibility(8);
        } else {
            componentDescriptionView.setText(ingredients);
            componentDescriptionView.setText(Html.fromHtml(ingredients));
        }
        if (TextUtils.isEmpty(allergen)) {
            componentAllergenView.setVisibility(8);
        } else {
            componentAllergenView.setText(String.format(getString(C2658R.string.text_allergens_prefix), new Object[]{allergen}));
        }
        if (TextUtils.isEmpty(additionalAllergen)) {
            componentAdditionalAllergenView.setVisibility(8);
        } else {
            componentAdditionalAllergenView.setText(String.format(getString(C2658R.string.text_additional_allergens_prefix), new Object[]{additionalAllergen}));
        }
        this.mRecipeComponentsLayout.addView(componentDetailView, new LayoutParams(-1, -2, 1.0f));
    }

    /* Access modifiers changed, original: protected */
    public void populateNutritionDetails() {
        Ensighten.evaluateEvent(this, "populateNutritionDetails", null);
        if (getActivity() != null) {
            String dailyValue;
            NumberFormat format = DecimalFormat.getInstance();
            format.setRoundingMode(RoundingMode.FLOOR);
            format.setMinimumFractionDigits(0);
            format.setMaximumFractionDigits(2);
            LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService("layout_inflater");
            if (this.mRecipe.getHighlightedNutrients() != null) {
                for (Nutrient nutrient : this.mRecipe.getHighlightedNutrients()) {
                    String unit;
                    View nutritionDetailHighlightItem = inflater.inflate(C2658R.layout.nutrition_left_highlight_item, null);
                    TextView highlightValue = (TextView) nutritionDetailHighlightItem.findViewById(C2358R.C2357id.highlight_value);
                    TextView highlightPercentageView = (TextView) nutritionDetailHighlightItem.findViewById(C2358R.C2357id.highlight_percentage);
                    TextView highlightName = (TextView) nutritionDetailHighlightItem.findViewById(C2358R.C2357id.highlight_name);
                    if (nutrient.getUnit() == null) {
                        unit = "";
                    } else {
                        unit = nutrient.getUnit();
                    }
                    if (nutrient.getAdultDailyValue() == null) {
                        dailyValue = "";
                    } else {
                        dailyValue = nutrient.getAdultDailyValue() + "%";
                    }
                    highlightValue.setText(String.format("%s%s", new Object[]{nutrient.getValue(), unit}));
                    if (dailyValue.isEmpty()) {
                        highlightPercentageView.setVisibility(8);
                    } else {
                        highlightPercentageView.setText(" (" + dailyValue + ")");
                    }
                    highlightName.setText(nutrient.getName());
                    this.mNutritionDetailsHighlightLayout.addView(nutritionDetailHighlightItem, new LayoutParams(-1, -2, 1.0f));
                }
            }
            if (this.mRecipe.getStandardNutrients() != null) {
                for (Nutrient nutrient2 : this.mRecipe.getStandardNutrients()) {
                    View nutritionDetailItem = inflater.inflate(C2658R.layout.nutritional_information_item, null);
                    TextView nutritionalInfoNameView = (TextView) nutritionDetailItem.findViewById(C2358R.C2357id.nutritional_information_name);
                    TextView nutritionalInfoValueView = (TextView) nutritionDetailItem.findViewById(C2358R.C2357id.nutritional_information_value);
                    if (nutrient2.getUnit() == null) {
                        if (nutrient2.getValue() == null) {
                            nutritionalInfoNameView.setVisibility(8);
                            nutritionalInfoValueView.setVisibility(8);
                        } else {
                            nutritionalInfoNameView.setText(nutrient2.getName());
                            nutritionalInfoValueView.setText(nutrient2.getValue());
                        }
                    } else if (nutrient2.getName() == null) {
                        nutritionalInfoNameView.setVisibility(8);
                        nutritionalInfoValueView.setVisibility(8);
                    } else {
                        nutritionalInfoNameView.setText(nutrient2.getName());
                        if (nutrient2.getValue() == null) {
                            nutritionalInfoNameView.setVisibility(8);
                            nutritionalInfoValueView.setVisibility(8);
                        } else {
                            nutritionalInfoValueView.setText(nutrient2.getValue() + " " + nutrient2.getUnit());
                        }
                    }
                    if (nutrient2.getAdultDailyValue() == null) {
                        dailyValue = "";
                    } else {
                        dailyValue = nutrient2.getAdultDailyValue() + "%";
                    }
                    if (!(nutritionalInfoValueView.getVisibility() == 8 || dailyValue.isEmpty() || dailyValue.length() <= 1)) {
                        nutritionalInfoValueView.setText(nutritionalInfoValueView.getText().toString() + " (" + dailyValue + ")");
                    }
                    this.mNutritionDetailsLayout.addView(nutritionDetailItem, new LayoutParams(-1, -2, 1.0f));
                }
            }
            if (this.mRecipe.getHeroImage() != null && !TextUtils.isEmpty(this.mRecipe.getHeroImage().getUrl())) {
                this.mItemImage.setVisibility(0);
                if (getContext() != null) {
                    Glide.with(getContext()).load(this.mRecipe.getHeroImage().getUrl()).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(this.mItemImage);
                }
            } else if (TextUtils.isEmpty(this.mRecipe.getImageUrl())) {
                this.mItemImage.setVisibility(8);
            } else {
                this.mItemImage.setVisibility(0);
                if (getContext() != null) {
                    Glide.with(getContext()).load(this.mRecipe.getImageUrl()).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(this.mItemImage);
                }
            }
        }
    }
}
