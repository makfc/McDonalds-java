package com.mcdonalds.app.nutrition;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TabLayout.Tab;
import android.support.p000v4.view.ViewPager;
import android.support.p001v7.app.ActionBar;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.p043ui.URLNavigationFragment;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.modules.models.Allergen;
import com.mcdonalds.sdk.modules.models.Nutrient;
import com.mcdonalds.sdk.modules.models.RecipeComponent;
import com.mcdonalds.sdk.services.configuration.Configuration;
import java.util.List;

public class TabbedNutritionInformationFragment extends URLNavigationFragment implements NutritionInformationView {
    public static String NAME = TabbedNutritionInformationFragment.class.getSimpleName();
    private TabbedNutritionInformationPagerAdapter mPagerAdapter;
    private NutritionInformationPresenter mPresenter;
    private View mRootView;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getNavigationActivity().getSupportActionBar();
        if (actionBar != null) {
            actionBar.setCustomView((int) C2658R.layout.action_bar_auto_fit_custom_view);
            ((TextView) actionBar.getCustomView()).setText(C2658R.string.title_activity_nutrition_info);
            actionBar.setDisplayShowCustomEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        Bundle extras = getNavigationActivity().getIntent().getExtras();
        if (extras != null) {
            this.mPresenter = new NutritionInformationPresenter(getNavigationActivity(), this, extras.getString("recipe_id"));
        }
    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.mRootView = inflater.inflate(C2658R.layout.activity_tabbed_nutrition_information, container, false);
        return this.mRootView;
    }

    public void displayRecipeInformation(String recipeName, String description, List<Nutrient> nutrients, List<Allergen> allergens, List<String> componentAllergens, List<RecipeComponent> ingredients, List<String> footers, String recipeComponentsString) {
        Ensighten.evaluateEvent(this, "displayRecipeInformation", new Object[]{recipeName, description, nutrients, allergens, componentAllergens, ingredients, footers, recipeComponentsString});
        ((TextView) getNavigationActivity().getSupportActionBar().getCustomView()).setText(recipeName);
        this.mPresenter.loadProductImage((ImageView) this.mRootView.findViewById(2131820643));
        TextView descriptionView = (TextView) this.mRootView.findViewById(C2358R.C2357id.description);
        if (description != null) {
            descriptionView.setText(formatDescription(description));
        }
        this.mPagerAdapter = new TabbedNutritionInformationPagerAdapter(getNavigationActivity(), this.mPresenter, nutrients, allergens, componentAllergens, ingredients, footers, recipeComponentsString);
        ViewPager pager = (ViewPager) this.mRootView.findViewById(C2358R.C2357id.pager);
        pager.setAdapter(this.mPagerAdapter);
        TabLayout tabs = (TabLayout) this.mRootView.findViewById(C2358R.C2357id.tabs);
        tabs.setupWithViewPager(pager);
        for (int i = 0; i < tabs.getTabCount(); i++) {
            Tab tab = tabs.getTabAt(i);
            if (tab != null) {
                tab.setCustomView((int) C2658R.layout.tab_header);
            }
            this.mPagerAdapter.setDataLayerTag(tab);
        }
    }

    private static CharSequence formatDescription(String description) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.nutrition.TabbedNutritionInformationFragment", "formatDescription", new Object[]{description});
        if (Configuration.getSharedInstance().getBooleanForKey("interface.nutritionalInfo.hideHrefRecipeDescription")) {
            description = description.replaceAll("<a[^>]+href=\"(.*?)\"[^>]*>.*?</a>", "");
        }
        return Html.fromHtml(description);
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
}
