package com.mcdonalds.app.nutrition;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.Target;
import com.ensighten.Ensighten;
import com.google.gson.annotations.SerializedName;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.p043ui.URLNavigationFragment;
import com.mcdonalds.app.util.DownloadBitmap;
import com.mcdonalds.app.util.ListUtils;
import com.mcdonalds.app.util.SimpleJsonRequest;
import com.mcdonalds.app.util.UIUtils;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.models.Category;
import com.mcdonalds.sdk.modules.nutrition.NutritionModule;
import com.mcdonalds.sdk.services.analytics.AnalyticType;
import com.mcdonalds.sdk.services.analytics.Analytics;
import com.mcdonalds.sdk.services.analytics.AnalyticsArgs.ArgBuilder;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.mcdonalds.sdk.services.network.RequestManager;
import com.mcdonalds.sdk.services.network.RequestManagerServiceConnection;
import java.io.Serializable;
import java.util.List;

public class NutritionCategoryListFragment extends URLNavigationFragment {
    private final AsyncListener<List<Category>> mCategoriesListener = new C33093();
    private boolean mCategoriesLoaded;
    private NutritionCategoryListAdapter mCategoryAdapter;
    private ListView mCategoryList;
    private NutritionModule mNutritionModule;
    private final OnItemClickListener mOnClickCategoryItem = new C33082();
    private RequestManagerServiceConnection mServiceConnection;

    public class Nutri implements Serializable {
        @SerializedName("url")
        public String url;
    }

    public class NutritionResponse implements Serializable {
        @SerializedName("nutrition")
        public List<Nutri> nutritions;

        public String getUrl() {
            Ensighten.evaluateEvent(this, "getUrl", null);
            return ((Nutri) this.nutritions.get(0)).url;
        }
    }

    /* renamed from: com.mcdonalds.app.nutrition.NutritionCategoryListFragment$2 */
    class C33082 implements OnItemClickListener {
        C33082() {
        }

        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Ensighten.evaluateEvent(this, "onItemClick", new Object[]{parent, view, new Integer(position), new Long(id)});
            Category category = (Category) Ensighten.evaluateEvent(null, "com.mcdonalds.app.nutrition.NutritionCategoryListFragment", "access$100", new Object[]{NutritionCategoryListFragment.this}).getItem(position - Ensighten.evaluateEvent(null, "com.mcdonalds.app.nutrition.NutritionCategoryListFragment", "access$000", new Object[]{NutritionCategoryListFragment.this}).getHeaderViewsCount());
            Bundle bundle = new Bundle();
            bundle.putParcelable(NutritionCategoryGridFragment.CATEGORY, category);
            NutritionCategoryListFragment.this.startActivity(NutritionCategoryGridActivity.class, NutritionCategoryGridFragment.NAME, bundle);
            Analytics.track(AnalyticType.Event, new ArgBuilder().setCategory(NutritionCategoryListFragment.this.getAnalyticsTitle()).setAction("On click").addCustom(28, category.getName()).setLabel("Nutrition category").build());
        }
    }

    /* renamed from: com.mcdonalds.app.nutrition.NutritionCategoryListFragment$3 */
    class C33093 implements AsyncListener<List<Category>> {
        C33093() {
        }

        public void onResponse(List<Category> response, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.nutrition.NutritionCategoryListFragment", "access$100", new Object[]{NutritionCategoryListFragment.this}).clear();
            if (ListUtils.isNotEmpty(response)) {
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.nutrition.NutritionCategoryListFragment", "access$100", new Object[]{NutritionCategoryListFragment.this}).addAll(response);
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.nutrition.NutritionCategoryListFragment", "access$000", new Object[]{NutritionCategoryListFragment.this}).setOnItemClickListener(Ensighten.evaluateEvent(null, "com.mcdonalds.app.nutrition.NutritionCategoryListFragment", "access$200", new Object[]{NutritionCategoryListFragment.this}));
            }
            UIUtils.stopActivityIndicator();
            NutritionCategoryListFragment.access$302(NutritionCategoryListFragment.this, true);
            if (exception != null) {
                AsyncException.report(exception);
            }
        }
    }

    static /* synthetic */ boolean access$302(NutritionCategoryListFragment x0, boolean x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.nutrition.NutritionCategoryListFragment", "access$302", new Object[]{x0, new Boolean(x1)});
        x0.mCategoriesLoaded = x1;
        return x1;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mCategoryAdapter = new NutritionCategoryListAdapter(getNavigationActivity(), C2658R.layout.nutrition_category_list_item);
        this.mCategoryAdapter.setServiceConnection(this.mServiceConnection);
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mServiceConnection = RequestManager.register(getNavigationActivity());
        if (this.mCategoryAdapter != null) {
            this.mCategoryAdapter.setServiceConnection(this.mServiceConnection);
        }
    }

    public void onDetach() {
        super.onDetach();
        RequestManager.unregister(getNavigationActivity(), this.mServiceConnection);
        if (this.mCategoryAdapter != null) {
            this.mCategoryAdapter.setServiceConnection(null);
        }
    }

    public String getAnalyticsTitle() {
        Ensighten.evaluateEvent(this, "getAnalyticsTitle", null);
        return getString(C2658R.string.analytics_screen_nutrition);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C2658R.layout.fragment_nutrition_category_list, container, false);
        this.mCategoryList = (ListView) view.findViewById(C2358R.C2357id.category_list);
        this.mCategoryList.setAdapter(this.mCategoryAdapter);
        loadHeaderImage(inflater);
        return view;
    }

    private void loadHeaderImage(LayoutInflater inflater) {
        Ensighten.evaluateEvent(this, "loadHeaderImage", new Object[]{inflater});
        View header = inflater.inflate(C2658R.layout.nutrition_category_header, this.mCategoryList, false);
        final ImageView foodMenuTop = (ImageView) header.findViewById(C2358R.C2357id.food_menu_top);
        this.mServiceConnection.processRequest(new SimpleJsonRequest((String) Configuration.getSharedInstance().getValueForKey("interface.nutritionalInfo.topImageJson"), NutritionResponse.class), new AsyncListener<NutritionResponse>() {
            public void onResponse(NutritionResponse response, AsyncToken token, AsyncException exception) {
                Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
                if (exception != null) {
                    exception.printStackTrace();
                } else if (NutritionCategoryListFragment.this.getContext() != null) {
                    Glide.with(NutritionCategoryListFragment.this.getContext()).load(response.getUrl()).asBitmap().diskCacheStrategy(DiskCacheStrategy.SOURCE).placeholder((int) C2358R.C2359drawable.transparent).into((Target) new DownloadBitmap(NutritionCategoryListFragment.this.getActivity(), foodMenuTop));
                }
            }
        });
        this.mCategoryList.addHeaderView(header, null, false);
    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.mCategoryList.setAdapter(this.mCategoryAdapter);
        if (this.mNutritionModule == null) {
            this.mNutritionModule = (NutritionModule) ModuleManager.getModule(NutritionModule.NAME);
            loadCategories();
            return;
        }
        loadCategories();
    }

    public void onResume() {
        super.onResume();
        getNavigationActivity().setTitle("");
    }

    private void loadCategories() {
        Ensighten.evaluateEvent(this, "loadCategories", null);
        if (this.mNutritionModule != null) {
            this.mNutritionModule.getAllCategories(this.mCategoriesListener);
        }
        if (!this.mCategoriesLoaded) {
            UIUtils.startActivityIndicator(getNavigationActivity(), getString(C2658R.string.label_processing_categories));
        }
    }

    public void onStart() {
        super.onStart();
        getNavigationActivity().showNavigateUp(true);
    }

    public void onStop() {
        super.onStop();
        getNavigationActivity().showNavigateUp(false);
    }
}
