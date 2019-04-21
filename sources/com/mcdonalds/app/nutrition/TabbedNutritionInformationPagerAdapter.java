package com.mcdonalds.app.nutrition;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout.Tab;
import android.support.p000v4.view.PagerAdapter;
import android.text.Html;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.analytics.datalayer.view.DataLayerClickListener;
import com.mcdonalds.app.p043ui.URLNavigationActivity;
import com.mcdonalds.app.web.WebActivity;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.modules.models.Allergen;
import com.mcdonalds.sdk.modules.models.Nutrient;
import com.mcdonalds.sdk.modules.models.RecipeComponent;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.mcdonalds.sdk.utils.ListUtils;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class TabbedNutritionInformationPagerAdapter extends PagerAdapter implements OnClickListener {
    private List<Allergen> mAllergens;
    private List<String> mComponentAllergens;
    private Context mContext;
    private List<String> mFooters;
    private List<RecipeComponent> mIngredients;
    private List<Nutrient> mNutrients;
    private NutritionInformationPresenter mPresenter;
    private String mRecipeComponentsString;
    private List<Integer> mTitles = new ArrayList();

    public TabbedNutritionInformationPagerAdapter(Context context, NutritionInformationPresenter presenter, List<Nutrient> nutrients, List<Allergen> allergens, List<String> componentAllergens, List<RecipeComponent> components, List<String> footers, String recipeComponentsString) {
        this.mContext = context;
        this.mPresenter = presenter;
        this.mNutrients = nutrients;
        this.mAllergens = allergens;
        this.mComponentAllergens = componentAllergens;
        this.mIngredients = components;
        this.mRecipeComponentsString = recipeComponentsString;
        this.mFooters = footers;
        this.mTitles.add(Integer.valueOf(C2658R.string.nutrition_tab_nutrition));
        if (this.mPresenter.shouldShowAllergens()) {
            this.mTitles.add(Integer.valueOf(C2658R.string.nutrition_tab_allergen));
        }
        if (this.mPresenter.shouldShowIngredients()) {
            this.mTitles.add(Integer.valueOf(C2658R.string.nutrition_tab_ingredients));
        }
    }

    public int getCount() {
        Ensighten.evaluateEvent(this, "getCount", null);
        return this.mTitles.size();
    }

    public boolean isViewFromObject(View view, Object object) {
        Ensighten.evaluateEvent(this, "isViewFromObject", new Object[]{view, object});
        return view == object;
    }

    public Object instantiateItem(ViewGroup container, int position) {
        View view;
        Ensighten.evaluateEvent(this, "instantiateItem", new Object[]{container, new Integer(position)});
        switch (((Integer) this.mTitles.get(position)).intValue()) {
            case C2658R.string.nutrition_tab_allergen /*2131297697*/:
                view = getAllergensView(container);
                break;
            case C2658R.string.nutrition_tab_ingredients /*2131297699*/:
                view = getIngredientsView(container);
                break;
            default:
                view = getNutritionView(container);
                break;
        }
        container.addView(view);
        return view;
    }

    public void destroyItem(ViewGroup container, int position, Object view) {
        Ensighten.evaluateEvent(this, "destroyItem", new Object[]{container, new Integer(position), view});
        container.removeView((View) view);
    }

    public int getItemPosition(Object object) {
        Ensighten.evaluateEvent(this, "getItemPosition", new Object[]{object});
        return -2;
    }

    public CharSequence getPageTitle(int position) {
        Ensighten.evaluateEvent(this, "getPageTitle", new Object[]{new Integer(position)});
        return this.mContext.getString(((Integer) this.mTitles.get(position)).intValue());
    }

    private View getNutritionView(ViewGroup container) {
        Ensighten.evaluateEvent(this, "getNutritionView", new Object[]{container});
        LayoutInflater inflater = LayoutInflater.from(this.mContext);
        ViewGroup main = (ViewGroup) inflater.inflate(C2658R.layout.nutrition_table, container, false);
        TextView servingSize = (TextView) main.findViewById(C2358R.C2357id.serving_size);
        String servingSizeLabel = this.mContext.getString(C2658R.string.serving_size);
        if (TextUtils.isEmpty(this.mPresenter.getRecipeServingSize())) {
            servingSize.setVisibility(8);
        } else {
            TextView textView = servingSize;
            textView.setText(String.format("%s: %s", new Object[]{servingSizeLabel, this.mPresenter.getRecipeServingSize()}));
        }
        TableLayout table = (TableLayout) main.findViewById(C2358R.C2357id.nutrition_table);
        boolean hideHundredG = this.mPresenter.shouldHideHundredG();
        boolean hidePerProduct = this.mPresenter.shouldHidePerProduct();
        boolean hidePercentRI = this.mPresenter.shouldHidePercentRI();
        boolean useDVInsteadOfRI = this.mPresenter.useDVInsteadOfRI();
        table.addView(getNutritionTableHeader(inflater, table, hideHundredG, hidePerProduct, hidePercentRI, useDVInsteadOfRI));
        if (!ListUtils.isEmpty(this.mNutrients)) {
            for (Nutrient nutrient : this.mNutrients) {
                table.addView(getNutritionTableRow(inflater, table, hideHundredG, hidePerProduct, hidePercentRI, nutrient, useDVInsteadOfRI));
            }
        }
        if (useDVInsteadOfRI) {
            ((TextView) main.findViewById(C2358R.C2357id.nutrition_reference_intake)).setText(C2658R.string.nutrition_daily_value);
        }
        showLegalDesclaimerView(main);
        return main;
    }

    private TableRow getNutritionTableHeader(LayoutInflater inflater, TableLayout table, boolean hideHundredG, boolean hidePerProduct, boolean hidePercentRI, boolean useDVInsteadOfRI) {
        Ensighten.evaluateEvent(this, "getNutritionTableHeader", new Object[]{inflater, table, new Boolean(hideHundredG), new Boolean(hidePerProduct), new Boolean(hidePercentRI), new Boolean(useDVInsteadOfRI)});
        TableRow header = (TableRow) inflater.inflate(C2658R.layout.nutrition_table_header, table, false);
        if (hideHundredG) {
            header.removeView(header.findViewById(C2358R.C2357id.header_100g));
        }
        if (hidePerProduct) {
            header.removeView(header.findViewById(C2358R.C2357id.header_product));
        }
        return header;
    }

    @NonNull
    private TableRow getNutritionTableRow(LayoutInflater inflater, TableLayout table, boolean hideHundredG, boolean hidePerProduct, boolean hidePercentRI, Nutrient nutrient, boolean useDVInsteadOfRI) {
        Ensighten.evaluateEvent(this, "getNutritionTableRow", new Object[]{inflater, table, new Boolean(hideHundredG), new Boolean(hidePerProduct), new Boolean(hidePercentRI), nutrient, new Boolean(useDVInsteadOfRI)});
        String unit = "";
        if (nutrient.getUnit() != null) {
            unit = nutrient.getUnit();
        }
        TableRow row = (TableRow) inflater.inflate(C2658R.layout.nutrition_table_row, table, false);
        ((TextView) row.findViewById(C2358R.C2357id.nutrient_name)).setText(nutrient.getName());
        boolean z = hideHundredG;
        setupTableCell(z, unit, row, nutrient.getHundredG(), (TextView) row.findViewById(C2358R.C2357id.nutrient_100g), nutrient.getId());
        boolean z2 = hidePerProduct;
        String str = unit;
        TableRow tableRow = row;
        setupTableCell(z2, str, tableRow, nutrient.getValue(), (TextView) row.findViewById(C2358R.C2357id.nutrient_product), nutrient.getId());
        TextView percentRI = (TextView) row.findViewById(C2358R.C2357id.nutrient_ri);
        String DV = "%";
        if (useDVInsteadOfRI) {
            DV = DV + this.mContext.getString(C2658R.string.dv_nutrient_suffix);
        }
        String value = nutrient.getAdultDailyValue();
        List<Double> nutrientIds = this.mPresenter.shouldHideRINutrientIds();
        if (!ListUtils.isEmpty(nutrientIds)) {
            if (nutrientIds.contains(Double.valueOf(Double.parseDouble(nutrient.getId())))) {
                value = "-";
                DV = "";
            }
        }
        setupTableCell(hidePercentRI, DV, row, value, percentRI, null);
        return row;
    }

    private void setupTableCell(boolean hide, String unit, TableRow row, String value, TextView textView, String mId) {
        Ensighten.evaluateEvent(this, "setupTableCell", new Object[]{new Boolean(hide), unit, row, value, textView, mId});
        if (hide) {
            row.removeView(textView);
        } else if (value != null) {
            try {
                if (!TextUtils.isEmpty(mId)) {
                    Float litersOfPetrol = Float.valueOf(Float.parseFloat(value));
                    if (litersOfPetrol.floatValue() <= 0.0f) {
                        value = "0";
                    } else if (mId.equals("1") || mId.equals("8")) {
                        value = new BigDecimal(value).stripTrailingZeros().toPlainString();
                    } else {
                        DecimalFormat df = new DecimalFormat("0.0");
                        df.setMaximumFractionDigits(2);
                        value = df.format(litersOfPetrol);
                    }
                }
                textView.setText(String.format("%s%s", new Object[]{value, unit}));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
    }

    private View getAllergensView(ViewGroup container) {
        Ensighten.evaluateEvent(this, "getAllergensView", new Object[]{container});
        LayoutInflater inflater = LayoutInflater.from(this.mContext);
        ViewGroup main = (ViewGroup) inflater.inflate(C2658R.layout.allergens_table, container, false);
        TableLayout table = (TableLayout) main.findViewById(C2358R.C2357id.allergens_table);
        if (ListUtils.isEmpty(this.mAllergens) && ListUtils.isEmpty(this.mComponentAllergens)) {
            table.setVisibility(8);
            ((TextView) main.findViewById(C2358R.C2357id.no_allergens_text)).setVisibility(0);
        } else {
            if (this.mAllergens != null) {
                for (Allergen allergen : this.mAllergens) {
                    table.addView(createAllergenRow(inflater, table, allergen.getName(), allergen.getValue()));
                }
            }
            if (this.mComponentAllergens != null) {
                for (String allergen2 : this.mComponentAllergens) {
                    table.addView(createAllergenRow(inflater, table, allergen2, this.mContext.getString(C2658R.string.yes)));
                }
            }
        }
        showLegalDesclaimerView(main);
        return main;
    }

    private TableRow createAllergenRow(LayoutInflater inflater, TableLayout table, String allergenName, String allergenValue) {
        Ensighten.evaluateEvent(this, "createAllergenRow", new Object[]{inflater, table, allergenName, allergenValue});
        TableRow row = (TableRow) inflater.inflate(C2658R.layout.allergens_table_row, table, false);
        ((TextView) row.findViewById(C2358R.C2357id.allergen_name)).setText(allergenName);
        ((TextView) row.findViewById(C2358R.C2357id.allergen_value)).setText(allergenValue);
        return row;
    }

    private View getIngredientsView(ViewGroup container) {
        Ensighten.evaluateEvent(this, "getIngredientsView", new Object[]{container});
        ViewGroup rootView = (ViewGroup) LayoutInflater.from(this.mContext).inflate(C2658R.layout.ingredients_list, container, false);
        LinearLayout list = (LinearLayout) rootView.findViewById(C2358R.C2357id.component_ingredient_list);
        TextView ingredientStatementView = (TextView) rootView.findViewById(C2358R.C2357id.ingredient_description);
        if (!ListUtils.isEmpty(this.mIngredients)) {
            list.setVisibility(0);
            ingredientStatementView.setVisibility(8);
            setupIngredientList(list);
        } else if (!TextUtils.isEmpty(this.mRecipeComponentsString)) {
            ingredientStatementView.setText(Html.fromHtml(this.mRecipeComponentsString));
        }
        setupFooters(list);
        showLegalDesclaimerView(rootView);
        return rootView;
    }

    private void showLegalDesclaimerView(ViewGroup rootView) {
        String urlDisclaimer;
        SpannableString content;
        Ensighten.evaluateEvent(this, "showLegalDesclaimerView", new Object[]{rootView});
        if (Configuration.getSharedInstance().getCurrentLanguage().equals("zh")) {
            urlDisclaimer = (String) Configuration.getSharedInstance().getValueForKey("interface.nutritionalInfo.legalDisclaimer.zh");
        } else {
            urlDisclaimer = (String) Configuration.getSharedInstance().getValueForKey("interface.nutritionalInfo.legalDisclaimer.en");
        }
        if (!TextUtils.isEmpty(urlDisclaimer)) {
            TextView nutrition_disclaimer_link = (TextView) rootView.findViewById(C2358R.C2357id.nutrition_disclaimer_link);
            nutrition_disclaimer_link.setVisibility(0);
            content = new SpannableString(nutrition_disclaimer_link.getText());
            content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
            nutrition_disclaimer_link.setText(content);
            nutrition_disclaimer_link.setOnClickListener(this);
        }
        urlDisclaimer = "";
        if (Configuration.getSharedInstance().getCurrentLanguage().equals("zh")) {
            urlDisclaimer = (String) Configuration.getSharedInstance().getValueForKey("interface.nutritionalInfo.learnMcdonalds.zh");
        } else {
            urlDisclaimer = (String) Configuration.getSharedInstance().getValueForKey("interface.nutritionalInfo.learnMcdonalds.en");
        }
        if (!TextUtils.isEmpty(urlDisclaimer)) {
            TextView learn_mcdonalds_link = (TextView) rootView.findViewById(C2358R.C2357id.learn_mcdonalds_link);
            learn_mcdonalds_link.setVisibility(0);
            content = new SpannableString(learn_mcdonalds_link.getText());
            content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
            learn_mcdonalds_link.setText(content);
            learn_mcdonalds_link.setOnClickListener(this);
        }
    }

    public void onClick(View v) {
        Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
        Bundle mArgs;
        Intent intent;
        switch (v.getId()) {
            case C2358R.C2357id.nutrition_disclaimer_link /*2131820795*/:
                String urlDisclaimer;
                if (Configuration.getSharedInstance().getCurrentLanguage().equals("zh")) {
                    urlDisclaimer = (String) Configuration.getSharedInstance().getValueForKey("interface.nutritionalInfo.legalDisclaimer.zh");
                } else {
                    urlDisclaimer = (String) Configuration.getSharedInstance().getValueForKey("interface.nutritionalInfo.legalDisclaimer.en");
                }
                if (!TextUtils.isEmpty(urlDisclaimer)) {
                    mArgs = new Bundle();
                    mArgs.putString("link", urlDisclaimer);
                    mArgs.putInt("view_title", C2658R.string.nutrition_disclaimer);
                    intent = new Intent(this.mContext, WebActivity.class);
                    intent.putExtras(mArgs);
                    intent.putExtra(URLNavigationActivity.ARG_FRAGMENT, "web");
                    this.mContext.startActivity(intent);
                    return;
                }
                return;
            case C2358R.C2357id.learn_mcdonalds_link /*2131821712*/:
                String urlearnMcdonalds;
                if (Configuration.getSharedInstance().getCurrentLanguage().equals("zh")) {
                    urlearnMcdonalds = (String) Configuration.getSharedInstance().getValueForKey("interface.nutritionalInfo.learnMcdonalds.zh");
                } else {
                    urlearnMcdonalds = (String) Configuration.getSharedInstance().getValueForKey("interface.nutritionalInfo.learnMcdonalds.en");
                }
                if (!TextUtils.isEmpty(urlearnMcdonalds)) {
                    mArgs = new Bundle();
                    mArgs.putString("link", urlearnMcdonalds);
                    mArgs.putInt("view_title", C2658R.string.learn_more_about_mcd_food_title);
                    intent = new Intent(this.mContext, WebActivity.class);
                    intent.putExtras(mArgs);
                    intent.putExtra(URLNavigationActivity.ARG_FRAGMENT, "web");
                    this.mContext.startActivity(intent);
                    return;
                }
                return;
            default:
                return;
        }
    }

    private void setupIngredientList(LinearLayout list) {
        Ensighten.evaluateEvent(this, "setupIngredientList", new Object[]{list});
        LayoutInflater inflater = (LayoutInflater) this.mContext.getSystemService("layout_inflater");
        boolean showAllergens = Configuration.getSharedInstance().getBooleanForKey("interface.nutritionalInfo.tabbedNutritionalInfo.showAllergensInIngredientsTab");
        if (this.mIngredients != null) {
            for (int position = 0; position < this.mIngredients.size(); position++) {
                String allergensText;
                View view = inflater.inflate(C2658R.layout.ingredient_list_item, null);
                TextView description = (TextView) view.findViewById(C2358R.C2357id.ingredient_description);
                TextView allergens = (TextView) view.findViewById(C2358R.C2357id.ingredient_allergens);
                TextView additionalAllergens = (TextView) view.findViewById(C2358R.C2357id.ingredient_additional_allergens);
                RecipeComponent component = (RecipeComponent) this.mIngredients.get(position);
                ((TextView) view.findViewById(C2358R.C2357id.ingredient_name)).setText(component.getProductName());
                if (component.getIngredientStatement() != null) {
                    description.setText(Html.fromHtml(component.getIngredientStatement()));
                }
                String allergensString = component.getProductAllergen();
                if (showAllergens && !TextUtils.isEmpty(allergensString)) {
                    allergensText = this.mContext.getString(C2658R.string.text_allergens_prefix, new Object[]{allergensString});
                    allergens.setVisibility(0);
                    allergens.setText(allergensText);
                }
                String additionalAllergenString = component.getProductAdditionalAllergen();
                if (showAllergens && !TextUtils.isEmpty(additionalAllergenString)) {
                    allergensText = this.mContext.getString(C2658R.string.text_additional_allergens_prefix, new Object[]{additionalAllergenString});
                    additionalAllergens.setVisibility(0);
                    additionalAllergens.setText(allergensText);
                }
                list.addView(view);
            }
        }
    }

    private void setupFooters(LinearLayout list) {
        Ensighten.evaluateEvent(this, "setupFooters", new Object[]{list});
        LayoutInflater inflater = LayoutInflater.from(list.getContext());
        if (this.mFooters != null) {
            for (int position = 0; position < this.mFooters.size(); position++) {
                View view = inflater.inflate(C2658R.layout.ingredient_list_item, null);
                TextView description = (TextView) view.findViewById(C2358R.C2357id.ingredient_description);
                String footer = (String) this.mFooters.get(position);
                ((TextView) view.findViewById(C2358R.C2357id.ingredient_name)).setVisibility(8);
                description.setText(Html.fromHtml(footer));
                list.addView(view);
            }
        }
    }

    public void setDataLayerTag(Tab tab) {
        Ensighten.evaluateEvent(this, "setDataLayerTag", new Object[]{tab});
        String tabText = null;
        if (tab.getText() != null) {
            tabText = tab.getText().toString();
        }
        if (tabText != null && this.mContext != null) {
            if (tabText.equals(this.mContext.getString(C2658R.string.nutrition_tab_nutrition))) {
                DataLayerClickListener.setDataLayerTag(tab, "NutritionTabPressed");
            } else if (tabText.equals(this.mContext.getString(C2658R.string.nutrition_tab_allergen))) {
                DataLayerClickListener.setDataLayerTag(tab, "AllergensTabPressed");
            } else if (tabText.equals(this.mContext.getString(C2658R.string.nutrition_tab_ingredients))) {
                DataLayerClickListener.setDataLayerTag(tab, "IngredientsTabPressed");
            }
        }
    }
}
