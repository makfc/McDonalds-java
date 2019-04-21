package com.mcdonalds.app.nutrition;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.analytics.datalayer.DataLayerManager;
import com.mcdonalds.app.p043ui.URLNavigationFragment;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.modules.models.NutritionRecipe;
import com.mcdonalds.sdk.modules.models.RecipeComponent;
import com.mcdonalds.sdk.modules.nutrition.NutritionModule;
import com.mcdonalds.sdk.services.network.RequestManager;
import com.mcdonalds.sdk.services.network.RequestManagerServiceConnection;
import java.util.Iterator;
import java.util.List;

public class NutritionInformationFragment extends URLNavigationFragment implements OnClickListener {
    protected LinearLayout components_list_layout_new_ui_Layout;
    protected ImageView ingredients_header_arrow_new_ui;
    protected boolean isInfridientsCollapsed;
    protected boolean isNutritionCollapsed;
    protected LinearLayout ll_ingredients_information_header_new_ui;
    protected LinearLayout ll_nutrition_information_header_new_ui;
    protected String mAnalyticsTitle = null;
    protected String mCategoryName;
    protected TextView mDescription;
    protected LinearLayout mImportantNotesLayout;
    protected ImageView mItemImage;
    protected LinearLayout mNutritionDetailsHighlightLayout;
    protected LinearLayout mNutritionDetailsLayout;
    private NutritionModule mNutritionModule;
    protected NutritionRecipe mRecipe;
    protected LinearLayout mRecipeComponentsLayout;
    protected String mRecipeId;
    protected RequestManagerServiceConnection mServiceConnection;
    protected ImageView nutrition_information_header_arrow_new_ui;
    protected LinearLayout nutrition_information_layout_new_ui_Layout;

    /* renamed from: com.mcdonalds.app.nutrition.NutritionInformationFragment$1 */
    class C33101 implements AsyncListener<NutritionRecipe> {
        C33101() {
        }

        public void onResponse(NutritionRecipe response, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            if (NutritionInformationFragment.this.getNavigationActivity() != null) {
                if (response != null) {
                    NutritionInformationFragment.this.mRecipe = response;
                    if (!TextUtils.isEmpty(response.getComponentsString())) {
                        NutritionInformationFragment.this.populateRecipeComponents(response.getComponentsString(), response.getAllergensString(), response.getAdditionalAllergensString());
                    } else if (response.getComponents() != null) {
                        NutritionInformationFragment.this.populateRecipeComponents(response.getComponents());
                    }
                    NutritionInformationFragment.this.populateNutritionDetails();
                    if (!(NutritionInformationFragment.this.mRecipe.getFooters() == null || NutritionInformationFragment.this.mRecipe.getFooters().isEmpty())) {
                        StringBuilder builder = new StringBuilder();
                        Iterator<String> iterator = NutritionInformationFragment.this.mRecipe.getFooters().iterator();
                        while (iterator.hasNext()) {
                            builder.append((String) iterator.next());
                            if (iterator.hasNext()) {
                                builder.append("\n");
                            }
                        }
                        NutritionInformationFragment.this.fillImportantNoteLayout("Important Notes", builder.toString(), null);
                    }
                    DataLayerManager.getInstance().setPageSection(NutritionInformationFragment.this.getTitleFromProductInfo());
                    DataLayerManager.getInstance().setRecipe(NutritionInformationFragment.this.mRecipe);
                    DataLayerManager.getInstance().logPageLoad("RecipeInfoTabbed", "PageViewed");
                }
                if (exception != null) {
                    AsyncException.report(exception);
                }
            }
        }
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mServiceConnection = RequestManager.register(getActivity());
    }

    public void onDetach() {
        super.onDetach();
        RequestManager.unregister(getActivity(), this.mServiceConnection);
    }

    /* Access modifiers changed, original: protected */
    public String getTitle() {
        Ensighten.evaluateEvent(this, "getTitle", null);
        String title = getTitleFromProductInfo();
        if (title == null) {
            return getString(C2658R.string.title_activity_nutrition_info);
        }
        return title;
    }

    /* Access modifiers changed, original: protected */
    public String getAnalyticsTitle() {
        Ensighten.evaluateEvent(this, "getAnalyticsTitle", null);
        return this.mAnalyticsTitle + getString(C2658R.string.analytics_screen_nutrition_product);
    }

    /* Access modifiers changed, original: protected */
    public String getDataLayerPageSection() {
        Ensighten.evaluateEvent(this, "getDataLayerPageSection", null);
        return null;
    }

    /* Access modifiers changed, original: protected */
    public String getDataLayerEvent() {
        Ensighten.evaluateEvent(this, "getDataLayerEvent", null);
        return null;
    }

    /* Access modifiers changed, original: protected */
    public String getTitleFromProductInfo() {
        Ensighten.evaluateEvent(this, "getTitleFromProductInfo", null);
        if (this.mRecipe != null) {
            return this.mRecipe.getMarketingName() != null ? this.mRecipe.getMarketingName() : this.mRecipe.getName();
        } else {
            return null;
        }
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.mRecipeId = getArguments().getString("recipe_id");
            this.mCategoryName = getArguments().getString("category_name");
            this.mAnalyticsTitle = getArguments().getString("ARG_ANALYTICS_TAG", "");
        }
        this.mNutritionModule = new NutritionModule();
        refresh();
    }

    /* Access modifiers changed, original: protected */
    public void refresh() {
        Ensighten.evaluateEvent(this, "refresh", null);
        this.mNutritionModule.getRecipeForId(this.mRecipeId, new C33101());
    }

    /* Access modifiers changed, original: protected */
    public void populateRecipeComponents(String recipeComponentsString, String allergens, String additionalAllergen) {
        Ensighten.evaluateEvent(this, "populateRecipeComponents", new Object[]{recipeComponentsString, allergens, additionalAllergen});
        if (getActivity() != null) {
            fillRecipeLayout("", recipeComponentsString, allergens, additionalAllergen);
        }
    }

    /* Access modifiers changed, original: protected */
    public void fillRecipeLayout(String productName, String ingredients, String allergen, String additionalAllergen) {
        Ensighten.evaluateEvent(this, "fillRecipeLayout", new Object[]{productName, ingredients, allergen, additionalAllergen});
    }

    /* Access modifiers changed, original: protected */
    public void fillImportantNoteLayout(String productName, String ingredients, String allergen) {
        Ensighten.evaluateEvent(this, "fillImportantNoteLayout", new Object[]{productName, ingredients, allergen});
        View componentDetailView = ((LayoutInflater) getActivity().getSystemService("layout_inflater")).inflate(C2658R.layout.old_ingredients_list_item, this.mImportantNotesLayout, false);
        TextView componentNameView = (TextView) componentDetailView.findViewById(C2358R.C2357id.ingredient_name);
        TextView componentDescriptionView = (TextView) componentDetailView.findViewById(C2358R.C2357id.ingredient_description);
        TextView componentAllergenView = (TextView) componentDetailView.findViewById(C2358R.C2357id.allergens_text);
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
        }
        if (TextUtils.isEmpty(allergen)) {
            componentAllergenView.setVisibility(8);
        } else {
            componentAllergenView.setText(String.format(getString(C2658R.string.text_allergens_prefix), new Object[]{allergen}));
        }
        this.mImportantNotesLayout.addView(componentDetailView, new LayoutParams(-1, -2, 1.0f));
    }

    /* Access modifiers changed, original: protected */
    public void populateRecipeComponents(List<RecipeComponent> recipeComponents) {
        Ensighten.evaluateEvent(this, "populateRecipeComponents", new Object[]{recipeComponents});
        if (getActivity() != null) {
            for (RecipeComponent recipeComponent : recipeComponents) {
                fillRecipeLayout(recipeComponent.getProductName(), recipeComponent.getIngredientStatement(), recipeComponent.getProductAllergen(), recipeComponent.getProductAdditionalAllergen());
            }
        }
    }

    /* Access modifiers changed, original: protected */
    public void populateNutritionDetails() {
        Ensighten.evaluateEvent(this, "populateNutritionDetails", null);
    }

    public void onClick(View v) {
        boolean z = true;
        Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
        switch (v.getId()) {
            case C2358R.C2357id.ll_nutrition_information_header_new_ui /*2131821358*/:
                if (this.isNutritionCollapsed) {
                    z = false;
                }
                this.isNutritionCollapsed = z;
                if (this.isNutritionCollapsed) {
                    this.nutrition_information_header_arrow_new_ui.setImageResource(C2358R.C2359drawable.arrow_gray_down);
                    this.nutrition_information_layout_new_ui_Layout.setVisibility(8);
                    return;
                }
                this.nutrition_information_header_arrow_new_ui.setImageResource(C2358R.C2359drawable.arrow_gray_up);
                this.nutrition_information_layout_new_ui_Layout.setVisibility(0);
                return;
            case C2358R.C2357id.ll_ingredients_information_header_new_ui /*2131821365*/:
                if (this.isInfridientsCollapsed) {
                    z = false;
                }
                this.isInfridientsCollapsed = z;
                if (this.isInfridientsCollapsed) {
                    this.ingredients_header_arrow_new_ui.setImageResource(C2358R.C2359drawable.arrow_gray_down);
                    this.components_list_layout_new_ui_Layout.setVisibility(8);
                    this.mImportantNotesLayout.setVisibility(8);
                    return;
                }
                this.ingredients_header_arrow_new_ui.setImageResource(C2358R.C2359drawable.arrow_gray_up);
                this.components_list_layout_new_ui_Layout.setVisibility(0);
                this.mImportantNotesLayout.setVisibility(0);
                return;
            default:
                return;
        }
    }
}
