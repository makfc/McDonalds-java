package com.mcdonalds.app.nutrition;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.ensighten.Ensighten;
import com.mcdonalds.app.analytics.datalayer.DataLayerManager;
import com.mcdonalds.app.util.ListUtils;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.models.NutritionRecipe;
import com.mcdonalds.sdk.modules.models.RecipeComponent;
import com.mcdonalds.sdk.modules.nutrition.NutritionModule;
import com.mcdonalds.sdk.services.configuration.Configuration;
import java.util.ArrayList;
import java.util.List;

public class NutritionInformationPresenter {
    private Context mContext;
    private NutritionRecipe mRecipe;
    private AsyncListener<NutritionRecipe> mRecipeListener = new C33111();
    private NutritionInformationView mView;

    /* renamed from: com.mcdonalds.app.nutrition.NutritionInformationPresenter$1 */
    class C33111 implements AsyncListener<NutritionRecipe> {
        C33111() {
        }

        public void onResponse(NutritionRecipe response, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            NutritionInformationPresenter.access$002(NutritionInformationPresenter.this, response);
            if (!TextUtils.isEmpty(Ensighten.evaluateEvent(null, "com.mcdonalds.app.nutrition.NutritionInformationPresenter", "access$000", new Object[]{NutritionInformationPresenter.this}).getName())) {
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.nutrition.NutritionInformationPresenter", "access$200", new Object[]{NutritionInformationPresenter.this}).displayRecipeInformation(Ensighten.evaluateEvent(null, "com.mcdonalds.app.nutrition.NutritionInformationPresenter", "access$000", new Object[]{NutritionInformationPresenter.this}).getName(), Ensighten.evaluateEvent(null, "com.mcdonalds.app.nutrition.NutritionInformationPresenter", "access$000", new Object[]{NutritionInformationPresenter.this}).getDescription(), Ensighten.evaluateEvent(null, "com.mcdonalds.app.nutrition.NutritionInformationPresenter", "access$000", new Object[]{NutritionInformationPresenter.this}).getNutrients(), Ensighten.evaluateEvent(null, "com.mcdonalds.app.nutrition.NutritionInformationPresenter", "access$000", new Object[]{NutritionInformationPresenter.this}).getAllergens(), Ensighten.evaluateEvent(null, "com.mcdonalds.app.nutrition.NutritionInformationPresenter", "access$100", new Object[]{NutritionInformationPresenter.this, Ensighten.evaluateEvent(null, "com.mcdonalds.app.nutrition.NutritionInformationPresenter", "access$000", new Object[]{NutritionInformationPresenter.this})}), Ensighten.evaluateEvent(null, "com.mcdonalds.app.nutrition.NutritionInformationPresenter", "access$000", new Object[]{NutritionInformationPresenter.this}).getComponents(), Ensighten.evaluateEvent(null, "com.mcdonalds.app.nutrition.NutritionInformationPresenter", "access$000", new Object[]{NutritionInformationPresenter.this}).getFooters(), Ensighten.evaluateEvent(null, "com.mcdonalds.app.nutrition.NutritionInformationPresenter", "access$000", new Object[]{NutritionInformationPresenter.this}).getComponentsString());
            }
            DataLayerManager.getInstance().setPageSection(Ensighten.evaluateEvent(null, "com.mcdonalds.app.nutrition.NutritionInformationPresenter", "access$000", new Object[]{NutritionInformationPresenter.this}).getMarketingName() != null ? Ensighten.evaluateEvent(null, "com.mcdonalds.app.nutrition.NutritionInformationPresenter", "access$000", new Object[]{NutritionInformationPresenter.this}).getMarketingName() : Ensighten.evaluateEvent(null, "com.mcdonalds.app.nutrition.NutritionInformationPresenter", "access$000", new Object[]{NutritionInformationPresenter.this}).getName());
            DataLayerManager.getInstance().setRecipe(Ensighten.evaluateEvent(null, "com.mcdonalds.app.nutrition.NutritionInformationPresenter", "access$000", new Object[]{NutritionInformationPresenter.this}));
            DataLayerManager.getInstance().logPageLoad("RecipeInfoTabbed", "PageViewed");
        }
    }

    static /* synthetic */ NutritionRecipe access$002(NutritionInformationPresenter x0, NutritionRecipe x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.nutrition.NutritionInformationPresenter", "access$002", new Object[]{x0, x1});
        x0.mRecipe = x1;
        return x1;
    }

    public NutritionInformationPresenter(Context context, NutritionInformationView view, String recipeId) {
        this.mView = view;
        this.mContext = context;
        loadRecipe(recipeId);
    }

    public void loadProductImage(ImageView imageView) {
        Ensighten.evaluateEvent(this, "loadProductImage", new Object[]{imageView});
        if (this.mRecipe != null && this.mRecipe.getHeroImage() != null && !TextUtils.isEmpty(this.mRecipe.getHeroImage().getUrl())) {
            Log.e("mzk", "json:" + this.mRecipe);
            Glide.with(this.mContext.getApplicationContext()).load(this.mRecipe.getHeroImage().getUrl()).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageView);
        }
    }

    public boolean shouldShowAllergens() {
        Ensighten.evaluateEvent(this, "shouldShowAllergens", null);
        return !Configuration.getSharedInstance().getBooleanForKey("interface.nutritionalInfo.tabbedNutritionalInfo.hideAllergens");
    }

    public boolean shouldShowIngredients() {
        boolean isIngredientInformationAvailable;
        Ensighten.evaluateEvent(this, "shouldShowIngredients", null);
        if (this.mRecipe == null || (ListUtils.isEmpty(this.mRecipe.getComponents()) && TextUtils.isEmpty(this.mRecipe.getComponentsString()))) {
            isIngredientInformationAvailable = false;
        } else {
            isIngredientInformationAvailable = true;
        }
        if (!isIngredientInformationAvailable || Configuration.getSharedInstance().getBooleanForKey("interface.nutritionalInfo.tabbedNutritionalInfo.hideIngredients")) {
            return false;
        }
        return true;
    }

    public List<Double> shouldHideRINutrientIds() {
        Ensighten.evaluateEvent(this, "shouldHideRINutrientIds", null);
        return (List) Configuration.getSharedInstance().getValueForKey("interface.nutritionalInfo.tabbedNutritionalInfo.nutritionTab.hiddenRINutrientIds");
    }

    public boolean shouldHideHundredG() {
        Ensighten.evaluateEvent(this, "shouldHideHundredG", null);
        return Configuration.getSharedInstance().getBooleanForKey("interface.nutritionalInfo.tabbedNutritionalInfo.nutritionTab.hiddenColumns.hundredG");
    }

    public boolean shouldHidePerProduct() {
        Ensighten.evaluateEvent(this, "shouldHidePerProduct", null);
        return Configuration.getSharedInstance().getBooleanForKey("interface.nutritionalInfo.tabbedNutritionalInfo.nutritionTab.hiddenColumns.perProduct");
    }

    public boolean shouldHidePercentRI() {
        Ensighten.evaluateEvent(this, "shouldHidePercentRI", null);
        return Configuration.getSharedInstance().getBooleanForKey("interface.nutritionalInfo.tabbedNutritionalInfo.nutritionTab.hiddenColumns.percentRI");
    }

    public boolean useDVInsteadOfRI() {
        Ensighten.evaluateEvent(this, "useDVInsteadOfRI", null);
        return Configuration.getSharedInstance().getBooleanForKey("interface.nutritionalInfo.tabbedNutritionalInfo.showDVInsteadOfRI");
    }

    public String getRecipeServingSize() {
        Ensighten.evaluateEvent(this, "getRecipeServingSize", null);
        return this.mRecipe.getServingSizeString(Configuration.getSharedInstance().getBooleanForKey("interface.nutritionalInfo.useMetricSystem"));
    }

    private void loadRecipe(String recipeId) {
        Ensighten.evaluateEvent(this, "loadRecipe", new Object[]{recipeId});
        ((NutritionModule) ModuleManager.getModule(NutritionModule.NAME)).getRecipeForId(String.valueOf(recipeId), this.mRecipeListener);
    }

    private List<String> getComponentAllergens(NutritionRecipe recipe) {
        Ensighten.evaluateEvent(this, "getComponentAllergens", new Object[]{recipe});
        List<String> result = new ArrayList();
        if (recipe.getComponents() != null) {
            for (RecipeComponent component : recipe.getComponents()) {
                if (!TextUtils.isEmpty(component.getProductAllergen())) {
                    result.add(component.getProductAllergen());
                }
                if (!TextUtils.isEmpty(component.getProductAdditionalAllergen())) {
                    result.add(component.getProductAdditionalAllergen());
                }
            }
        }
        return result;
    }
}
