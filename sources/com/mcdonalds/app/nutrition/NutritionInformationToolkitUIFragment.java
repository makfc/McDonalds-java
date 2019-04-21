package com.mcdonalds.app.nutrition;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.web.WebActivity;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.modules.models.Nutrient;
import com.mcdonalds.sdk.services.configuration.Configuration;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class NutritionInformationToolkitUIFragment extends NutritionInformationFragment implements OnClickListener {
    private ImageView iv_ingredients_information;
    Comparator<Nutrient> mNutrientComparator = new C33121();

    /* renamed from: com.mcdonalds.app.nutrition.NutritionInformationToolkitUIFragment$1 */
    class C33121 implements Comparator<Nutrient> {
        C33121() {
        }

        public int compare(Nutrient lhs, Nutrient rhs) {
            Ensighten.evaluateEvent(this, "compare", new Object[]{lhs, rhs});
            return (int) (Double.parseDouble(lhs.getId()) - Double.parseDouble(rhs.getId()));
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(C2658R.layout.fragment_nutrition_information_digital_toolkit_ui, container, false);
        rootView.findViewById(C2358R.C2357id.nutritional_information_container_new_ui).setVisibility(0);
        this.ll_nutrition_information_header_new_ui = (LinearLayout) rootView.findViewById(C2358R.C2357id.ll_nutrition_information_header_new_ui);
        this.ll_nutrition_information_header_new_ui.setOnClickListener(this);
        this.ll_ingredients_information_header_new_ui = (LinearLayout) rootView.findViewById(C2358R.C2357id.ll_ingredients_information_header_new_ui);
        this.ll_ingredients_information_header_new_ui.setOnClickListener(this);
        this.isNutritionCollapsed = false;
        this.isInfridientsCollapsed = false;
        this.ingredients_header_arrow_new_ui = (ImageView) rootView.findViewById(C2358R.C2357id.ingredients_header_arrow_new_ui);
        if (this.isInfridientsCollapsed) {
            this.ingredients_header_arrow_new_ui.setImageResource(C2358R.C2359drawable.arrow_gray_down);
        } else {
            this.ingredients_header_arrow_new_ui.setImageResource(C2358R.C2359drawable.arrow_gray_up);
        }
        this.nutrition_information_header_arrow_new_ui = (ImageView) rootView.findViewById(C2358R.C2357id.nutrition_information_header_arrow_new_ui);
        if (this.isNutritionCollapsed) {
            this.nutrition_information_header_arrow_new_ui.setImageResource(C2358R.C2359drawable.arrow_gray_down);
        } else {
            this.nutrition_information_header_arrow_new_ui.setImageResource(C2358R.C2359drawable.arrow_gray_up);
        }
        this.iv_ingredients_information = (ImageView) rootView.findViewById(C2358R.C2357id.iv_ingredients_information);
        this.iv_ingredients_information.setOnClickListener(this);
        this.iv_ingredients_information = (ImageView) rootView.findViewById(C2358R.C2357id.iv_nutrition_information);
        this.iv_ingredients_information.setOnClickListener(this);
        this.nutrition_information_layout_new_ui_Layout = (LinearLayout) rootView.findViewById(C2358R.C2357id.nutrition_information_layout_new_ui);
        this.components_list_layout_new_ui_Layout = (LinearLayout) rootView.findViewById(C2358R.C2357id.components_list_layout_new_ui);
        this.mNutritionDetailsHighlightLayout = (LinearLayout) rootView.findViewById(C2358R.C2357id.nutrition_highlight_layout_new_ui);
        this.mNutritionDetailsLayout = (LinearLayout) rootView.findViewById(C2358R.C2357id.nutrition_details_list_new_ui);
        this.mRecipeComponentsLayout = (LinearLayout) rootView.findViewById(C2358R.C2357id.components_list_layout_new_ui);
        this.mImportantNotesLayout = (LinearLayout) rootView.findViewById(C2358R.C2357id.important_note_list_layout_new_ui);
        this.mItemImage = (ImageView) rootView.findViewById(2131820643);
        this.mDescription = (TextView) rootView.findViewById(C2358R.C2357id.nutritional_product_description);
        if (getActivity().getActionBar() != null) {
            getActivity().getActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(C2658R.color.xlight_gray_background)));
        }
        return rootView;
    }

    /* Access modifiers changed, original: protected */
    public void fillRecipeLayout(String productName, String ingredients, String allergen, String additionAllergen) {
        Ensighten.evaluateEvent(this, "fillRecipeLayout", new Object[]{productName, ingredients, allergen, additionAllergen});
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
        if (TextUtils.isEmpty(additionAllergen)) {
            componentAdditionalAllergenView.setVisibility(8);
        } else {
            componentAdditionalAllergenView.setText(String.format(getString(C2658R.string.text_additional_allergens_prefix), new Object[]{additionAllergen}));
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
            this.mDescription.setVisibility(0);
            if (this.mRecipe.getDescription() != null) {
                this.mDescription.setText(Html.fromHtml(this.mRecipe.getDescription()));
            }
            List<Nutrient> standardNutrients = this.mRecipe.getStandardNutrients();
            if (standardNutrients != null) {
                Collections.sort(standardNutrients, this.mNutrientComparator);
                for (Nutrient nutrient : standardNutrients) {
                    String unit;
                    View nutritionDetailHighlightItem = inflater.inflate(C2658R.layout.nutrition_left_highlight_item_new_ui, null);
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
            List<Nutrient> highlightedNutrients = this.mRecipe.getHighlightedNutrients();
            if (highlightedNutrients != null) {
                Collections.sort(highlightedNutrients, this.mNutrientComparator);
                for (Nutrient nutrient2 : highlightedNutrients) {
                    View nutritionDetailItem = inflater.inflate(C2658R.layout.nutritional_information_item_new_ui, null);
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
                        if (nutrient2.getName().equalsIgnoreCase("Total Fat")) {
                            nutritionalInfoNameView.setText("Fat");
                        } else {
                            nutritionalInfoNameView.setText(nutrient2.getName());
                        }
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
                    TextView nutritional_information_dailyValueView = (TextView) nutritionDetailItem.findViewById(C2358R.C2357id.nutritional_information_dailyValue);
                    if (!(nutritional_information_dailyValueView.getVisibility() == 8 || dailyValue.isEmpty() || dailyValue.length() <= 1)) {
                        nutritional_information_dailyValueView.setText(" (" + dailyValue + ")");
                    }
                    this.mNutritionDetailsLayout.addView(nutritionDetailItem, new LayoutParams(-1, -2, 1.0f));
                }
            }
            if (this.mRecipe.getHeroImage() != null && !TextUtils.isEmpty(this.mRecipe.getHeroImage().getUrl())) {
                this.mItemImage.setVisibility(0);
                Glide.with(getContext()).load(this.mRecipe.getHeroImage().getUrl()).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(this.mItemImage);
            } else if (TextUtils.isEmpty(this.mRecipe.getImageUrl())) {
                this.mItemImage.setVisibility(8);
            } else {
                this.mItemImage.setVisibility(0);
                Glide.with(getContext()).load(this.mRecipe.getImageUrl()).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(this.mItemImage);
            }
        }
    }

    public void onClick(View v) {
        Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
        switch (v.getId()) {
            case C2358R.C2357id.iv_nutrition_information /*2131821359*/:
            case C2358R.C2357id.iv_ingredients_information /*2131821366*/:
                String urlDisclaimer = (String) Configuration.getSharedInstance().getValueForKey("interface.legalCopy.legalDisclaimer");
                if (!TextUtils.isEmpty(urlDisclaimer)) {
                    openLink(urlDisclaimer);
                    return;
                }
                return;
            default:
                super.onClick(v);
                return;
        }
    }

    private void openLink(String mLink) {
        Ensighten.evaluateEvent(this, "openLink", new Object[]{mLink});
        Bundle mArgs = new Bundle();
        mArgs.putString("link", mLink);
        startActivity(WebActivity.class, "web", mArgs);
    }
}
