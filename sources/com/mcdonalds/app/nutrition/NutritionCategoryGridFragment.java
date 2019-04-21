package com.mcdonalds.app.nutrition;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.analytics.datalayer.view.DataLayerClickListener;
import com.mcdonalds.app.navigation.NavigationManager;
import com.mcdonalds.app.nutrition.NutritionCategoryGridAdapter.GridItemClickListener;
import com.mcdonalds.app.p043ui.URLNavigationFragment;
import com.mcdonalds.app.util.AnalyticsUtils;
import com.mcdonalds.app.util.TextChangeFilter;
import com.mcdonalds.app.util.UIUtils;
import com.mcdonalds.app.widget.HeaderGridView;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.models.Category;
import com.mcdonalds.sdk.modules.models.NutritionRecipe;
import com.mcdonalds.sdk.modules.nutrition.NutritionModule;
import com.mcdonalds.sdk.services.network.RequestManager;
import com.mcdonalds.sdk.services.network.RequestManagerServiceConnection;
import java.util.ArrayList;
import java.util.List;

public class NutritionCategoryGridFragment extends URLNavigationFragment implements GridItemClickListener {
    public static String CATEGORY = "category";
    public static String NAME = NutritionCategoryGridFragment.class.getSimpleName();
    private NutritionCategoryGridAdapter mAdapter;
    private Category mCategory;
    private OnEditorActionListener mNutritionSearchEditorListener = new C33021();
    private EditText mSearchBar;
    private RequestManagerServiceConnection mServiceConnection;

    /* renamed from: com.mcdonalds.app.nutrition.NutritionCategoryGridFragment$1 */
    class C33021 implements OnEditorActionListener {
        C33021() {
        }

        public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
            Ensighten.evaluateEvent(this, "onEditorAction", new Object[]{textView, new Integer(actionId), keyEvent});
            String searchString = textView.getText().toString();
            if (actionId != 3) {
                return false;
            }
            NutritionCategoryGridFragment.access$000(NutritionCategoryGridFragment.this, searchString);
            return true;
        }
    }

    /* renamed from: com.mcdonalds.app.nutrition.NutritionCategoryGridFragment$2 */
    class C33032 implements OnScrollListener {
        C33032() {
        }

        public void onScrollStateChanged(AbsListView view, int scrollState) {
            Ensighten.evaluateEvent(this, "onScrollStateChanged", new Object[]{view, new Integer(scrollState)});
            if (scrollState == 1) {
                ((InputMethodManager) NutritionCategoryGridFragment.this.getActivity().getSystemService("input_method")).hideSoftInputFromWindow(Ensighten.evaluateEvent(null, "com.mcdonalds.app.nutrition.NutritionCategoryGridFragment", "access$100", new Object[]{NutritionCategoryGridFragment.this}).getWindowToken(), 0);
            }
        }

        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            Ensighten.evaluateEvent(this, "onScroll", new Object[]{view, new Integer(firstVisibleItem), new Integer(visibleItemCount), new Integer(totalItemCount)});
        }
    }

    /* renamed from: com.mcdonalds.app.nutrition.NutritionCategoryGridFragment$3 */
    class C33043 implements OnClickListener {
        C33043() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            if (TextUtils.isEmpty(Ensighten.evaluateEvent(null, "com.mcdonalds.app.nutrition.NutritionCategoryGridFragment", "access$100", new Object[]{NutritionCategoryGridFragment.this}).getText())) {
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.nutrition.NutritionCategoryGridFragment", "access$100", new Object[]{NutritionCategoryGridFragment.this}).clearFocus();
                UIUtils.dismissKeyboard(NutritionCategoryGridFragment.this.getNavigationActivity(), Ensighten.evaluateEvent(null, "com.mcdonalds.app.nutrition.NutritionCategoryGridFragment", "access$100", new Object[]{NutritionCategoryGridFragment.this}));
                return;
            }
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.nutrition.NutritionCategoryGridFragment", "access$100", new Object[]{NutritionCategoryGridFragment.this}).setText("");
        }
    }

    /* renamed from: com.mcdonalds.app.nutrition.NutritionCategoryGridFragment$4 */
    class C33054 implements AsyncListener<List<NutritionRecipe>> {
        C33054() {
        }

        public void onResponse(List<NutritionRecipe> response, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            if (NutritionCategoryGridFragment.this.getNavigationActivity() != null) {
                if (exception != null) {
                    AsyncException.report(exception);
                } else if (response != null) {
                    List<NutritionRecipe> recipes = Ensighten.evaluateEvent(null, "com.mcdonalds.app.nutrition.NutritionCategoryGridFragment", "access$200", new Object[]{NutritionCategoryGridFragment.this, response});
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.nutrition.NutritionCategoryGridFragment", "access$300", new Object[]{NutritionCategoryGridFragment.this}).clear();
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.nutrition.NutritionCategoryGridFragment", "access$300", new Object[]{NutritionCategoryGridFragment.this}).addAll(recipes);
                }
                UIUtils.stopActivityIndicator();
            }
        }
    }

    static /* synthetic */ void access$000(NutritionCategoryGridFragment x0, String x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.nutrition.NutritionCategoryGridFragment", "access$000", new Object[]{x0, x1});
        x0.trackNutritionPageSearch(x1);
    }

    /* Access modifiers changed, original: protected */
    public String getAnalyticsTitle() {
        Ensighten.evaluateEvent(this, "getAnalyticsTitle", null);
        return getString(C2658R.string.analytics_screen_nutrition_product);
    }

    /* Access modifiers changed, original: protected */
    public String getDataLayerPageSection() {
        Ensighten.evaluateEvent(this, "getDataLayerPageSection", null);
        return this.mCategory.getName();
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mServiceConnection = RequestManager.register(getActivity());
    }

    public void onDetach() {
        super.onDetach();
        RequestManager.unregister(getActivity(), this.mServiceConnection);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Category category = (Category) getArguments().getParcelable(CATEGORY);
        if (category != null) {
            this.mCategory = category;
            getNavigationActivity().setTitle(this.mCategory.getName());
        }
        setHasOptionsMenu(true);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C2658R.layout.fragment_nutrition_category_grid, container, false);
        View headerView = inflater.inflate(C2658R.layout.nutrition_search_header, null);
        HeaderGridView gridView = (HeaderGridView) view.findViewById(C2358R.C2357id.category_grid);
        gridView.addHeaderView(headerView);
        this.mAdapter = new NutritionCategoryGridAdapter(getNavigationActivity(), C2658R.layout.grid_item, this);
        gridView.setAdapter(this.mAdapter);
        View searchClearButton = (Button) headerView.findViewById(C2358R.C2357id.search_clear_button);
        this.mSearchBar = (EditText) headerView.findViewById(2131820676);
        this.mSearchBar.addTextChangedListener(new TextChangeFilter(this.mAdapter.getFilter()));
        this.mSearchBar.setOnEditorActionListener(this.mNutritionSearchEditorListener);
        gridView.setOnScrollListener(new C33032());
        searchClearButton.setOnClickListener(new C33043());
        DataLayerClickListener.setDataLayerTag(searchClearButton, "CancelSearchPressed");
        UIUtils.startActivityIndicator(getNavigationActivity(), getString(C2658R.string.label_processing));
        getCategories();
        return view;
    }

    private void trackNutritionPageSearch(String searchString) {
        Ensighten.evaluateEvent(this, "trackNutritionPageSearch", new Object[]{searchString});
        this.mSearchBar.clearFocus();
        UIUtils.dismissKeyboard(getNavigationActivity(), this.mSearchBar);
        if (!TextUtils.isEmpty(searchString)) {
            AnalyticsUtils.trackFoodSearch("/nutrition/food-search", searchString);
        }
    }

    private void getCategories() {
        Ensighten.evaluateEvent(this, "getCategories", null);
        NutritionModule nutritionModule = (NutritionModule) ModuleManager.getModule(NutritionModule.NAME);
        if (nutritionModule != null && this.mCategory != null) {
            nutritionModule.getAllRecipesForCategory(String.valueOf(this.mCategory.getID()), new C33054());
        }
    }

    @NonNull
    private List<NutritionRecipe> getFilteredRecipes(@NonNull List<NutritionRecipe> response) {
        Ensighten.evaluateEvent(this, "getFilteredRecipes", new Object[]{response});
        List<NutritionRecipe> recipes = new ArrayList();
        for (NutritionRecipe recipe : response) {
            if (recipe.shouldShow()) {
                recipes.add(recipe);
            }
        }
        return recipes;
    }

    public void onItemClick(NutritionRecipe recipe) {
        Ensighten.evaluateEvent(this, "onItemClick", new Object[]{recipe});
        SparseArray custom = new SparseArray();
        custom.put(29, recipe.getName());
        AnalyticsUtils.trackOnClickEvent(getAnalyticsTitle(), "Nutrition info", custom);
        NavigationManager.getInstance().showNutrition(getActivity(), recipe.getId(), this.mCategory.getName(), null, getNavigationActivity());
    }

    public boolean isSearchFocused() {
        Ensighten.evaluateEvent(this, "isSearchFocused", null);
        return this.mSearchBar.hasFocus();
    }

    public void onBackPressed() {
        Ensighten.evaluateEvent(this, "onBackPressed", null);
        this.mSearchBar.setText("");
        this.mSearchBar.clearFocus();
    }
}
