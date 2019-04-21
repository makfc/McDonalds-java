package com.mcdonalds.app.offers;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.analytics.datalayer.DataLayerManager;
import com.mcdonalds.app.analytics.datalayer.view.DataLayerClickListener;
import com.mcdonalds.app.navigation.NavigationManager;
import com.mcdonalds.app.ordering.ProductCustomizationActivity;
import com.mcdonalds.app.p043ui.URLNavigationFragment;
import com.mcdonalds.app.util.AnalyticsUtils;
import com.mcdonalds.app.util.AppUtils;
import com.mcdonalds.app.util.UIUtils;
import com.mcdonalds.app.widget.CheckableRelativeLayout;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.AsyncCounter;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.models.NutritionRecipe;
import com.mcdonalds.sdk.modules.models.OfferProduct;
import com.mcdonalds.sdk.modules.models.OfferProductOption;
import com.mcdonalds.sdk.modules.models.OrderProduct;
import com.mcdonalds.sdk.modules.models.Product;
import com.mcdonalds.sdk.modules.nutrition.NutritionModule;
import com.mcdonalds.sdk.modules.ordering.OrderingModule;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.mcdonalds.sdk.services.data.DataPasser;
import com.mcdonalds.sdk.utils.ListUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OfferProductFragment extends URLNavigationFragment implements OnItemClickListener {
    private static final String TAG = OfferProductFragment.class.getSimpleName();
    private MenuItem mDoneMenuItem;
    private boolean mHideCustomizationButton;
    private int mIngredientPosition;
    private int mKey;
    private NutritionModule mNutritionModule;
    private OfferProduct mOfferProduct;
    private OfferProductListAdapter mOfferProductListAdapter;
    private OrderingModule mOrderingModule;
    private ListView mProductListView;
    private ProgressBar mProgressBar;

    /* renamed from: com.mcdonalds.app.offers.OfferProductFragment$1 */
    class C33591 implements AsyncListener<List<Product>> {

        /* renamed from: com.mcdonalds.app.offers.OfferProductFragment$1$1 */
        class C33581 implements OnClickListener {
            C33581() {
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                Ensighten.evaluateEvent(this, "onClick", new Object[]{dialogInterface, new Integer(i)});
                OfferProductFragment.this.getActivity().finish();
            }
        }

        C33591() {
        }

        public void onResponse(List<Product> response, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            if (exception == null) {
                List<OrderProduct> filteredResults = new ArrayList();
                for (Product product : response) {
                    if (product != null) {
                        filteredResults.add(OrderProduct.createProduct(product, Integer.valueOf(1)));
                    }
                }
                if (filteredResults.isEmpty()) {
                    UIUtils.showGlobalAlertDialog(OfferProductFragment.this.getNavigationActivity(), "", OfferProductFragment.this.getString(C2658R.string.offer_no_products_found), new C33581());
                    DataLayerManager.getInstance().recordError("No products available");
                } else {
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.offers.OfferProductFragment", "access$000", new Object[]{OfferProductFragment.this}).addAll(filteredResults);
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.offers.OfferProductFragment", "access$100", new Object[]{OfferProductFragment.this}).setAdapter(Ensighten.evaluateEvent(null, "com.mcdonalds.app.offers.OfferProductFragment", "access$000", new Object[]{OfferProductFragment.this}));
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.offers.OfferProductFragment", "access$100", new Object[]{OfferProductFragment.this}).setChoiceMode(1);
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.offers.OfferProductFragment", "access$100", new Object[]{OfferProductFragment.this}).setItemsCanFocus(false);
                }
            } else {
                AsyncException.report(exception);
            }
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.offers.OfferProductFragment", "access$200", new Object[]{OfferProductFragment.this}).setVisibility(4);
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.offers.OfferProductFragment", "access$100", new Object[]{OfferProductFragment.this}).setVisibility(0);
        }
    }

    class OfferProductListAdapter extends ArrayAdapter<OrderProduct> {
        private Context mContext;
        private int mResource;

        public OfferProductListAdapter(Context context, int resource) {
            super(context, resource);
            this.mContext = context;
            this.mResource = resource;
        }

        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            int i;
            if (convertView == null) {
                convertView = LayoutInflater.from(this.mContext).inflate(this.mResource, null);
                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            DataLayerClickListener.setDataLayerTag(viewHolder.mLayout, "ProductItemPressed", position);
            DataLayerClickListener.setDataLayerTag(viewHolder.mHatButton, "ConfigurationSelectionButtonPressed");
            final OrderProduct item = (OrderProduct) getItem(position);
            viewHolder.mLayout.setChecked(((ListView) parent).isItemChecked(position));
            if (item.getProduct() != null) {
                viewHolder.mProductName.setText(item.getProduct().getLongName());
            } else {
                viewHolder.mProductName.setText(item.getDisplayName());
            }
            if (!(item.getProduct().getThumbnailImage() == null || getContext() == null)) {
                Glide.with(getContext()).load(item.getProduct().getThumbnailImage().getUrl()).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(viewHolder.mFoodImageThumb);
            }
            boolean showHatButton = !Ensighten.evaluateEvent(null, "com.mcdonalds.app.offers.OfferProductFragment", "access$300", new Object[]{OfferProductFragment.this}) && Ensighten.evaluateEvent(null, "com.mcdonalds.app.offers.OfferProductFragment", "access$400", new Object[]{OfferProductFragment.this, item});
            ImageButton imageButton = viewHolder.mHatButton;
            if (showHatButton) {
                i = 0;
            } else {
                i = 4;
            }
            imageButton.setVisibility(i);
            if (showHatButton) {
                viewHolder.mHatButton.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        Ensighten.evaluateEvent(this, "onClick", new Object[]{view});
                        OfferProductFragment.access$500(OfferProductFragment.this, item, position);
                    }
                });
                if (item.getCustomizations() == null || item.getCustomizations().isEmpty()) {
                    viewHolder.mProductSpecialInstructions.setVisibility(8);
                    viewHolder.mHatButton.setImageResource(C2358R.C2359drawable.icon_chef_hat);
                } else {
                    viewHolder.mHatButton.setImageResource(C2358R.C2359drawable.icon_customize_gray_selected);
                    viewHolder.mProductSpecialInstructions.setVisibility(0);
                    viewHolder.mProductSpecialInstructions.setText(item.getCustomizationsString());
                }
            } else {
                viewHolder.mHatButton.setOnClickListener(null);
            }
            if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.offers.OfferProductFragment", "access$600", new Object[]{OfferProductFragment.this}) != null) {
                viewHolder.mInfoButton.setVisibility(4);
                final ViewHolder holder = viewHolder;
                if (!AppUtils.hideNutritionIconOnOrderingPages()) {
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.offers.OfferProductFragment", "access$600", new Object[]{OfferProductFragment.this}).getRecipeForExternalId(item.getProduct().getExternalId().toString(), new AsyncListener<NutritionRecipe>() {
                        public void onResponse(final NutritionRecipe response, AsyncToken token, AsyncException exception) {
                            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
                            if (exception != null) {
                                AsyncException.report(exception);
                            } else if (OfferProductFragment.this.getNavigationActivity() != null && response != null) {
                                holder.mInfoButton.setVisibility(0);
                                holder.mInfoButton.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View view) {
                                        Ensighten.evaluateEvent(this, "onClick", new Object[]{view});
                                        OfferProductFragment.access$700(OfferProductFragment.this, response.getId());
                                    }
                                });
                            }
                        }
                    });
                }
            } else {
                viewHolder.mInfoButton.setVisibility(4);
            }
            Ensighten.getViewReturnValue(convertView, position);
            Ensighten.processView((Object) this, "getView");
            Ensighten.getViewReturnValue(null, -1);
            return convertView;
        }
    }

    static class ViewHolder {
        ImageView mFoodImageThumb;
        ImageButton mHatButton;
        ImageButton mInfoButton;
        CheckableRelativeLayout mLayout;
        TextView mProductName;
        TextView mProductSpecialInstructions;

        ViewHolder(View view) {
            this.mProductName = (TextView) view.findViewById(C2358R.C2357id.offer_product_name);
            this.mLayout = (CheckableRelativeLayout) view.findViewById(C2358R.C2357id.layout);
            this.mHatButton = (ImageButton) view.findViewById(C2358R.C2357id.hat_button);
            this.mFoodImageThumb = (ImageView) view.findViewById(C2358R.C2357id.food_image_small);
            this.mInfoButton = (ImageButton) view.findViewById(C2358R.C2357id.info_button);
            this.mProductSpecialInstructions = (TextView) view.findViewById(C2358R.C2357id.custom_special_instructions);
        }
    }

    static /* synthetic */ void access$500(OfferProductFragment x0, OrderProduct x1, int x2) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.offers.OfferProductFragment", "access$500", new Object[]{x0, x1, new Integer(x2)});
        x0.onProductCustomizeClicked(x1, x2);
    }

    static /* synthetic */ void access$700(OfferProductFragment x0, String x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.offers.OfferProductFragment", "access$700", new Object[]{x0, x1});
        x0.onProductInfoButtonClicked(x1);
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        this.mHideCustomizationButton = Configuration.getSharedInstance().getBooleanForKey("interface.hideProductCustomizationButton");
        if (getArguments().containsKey("offer_product_key")) {
            this.mOfferProduct = (OfferProduct) getArguments().getParcelable("offer_product_key");
        } else {
            this.mOfferProduct = (OfferProduct) DataPasser.getInstance().getData("offer_product_key");
        }
        this.mKey = getArguments().getInt("offer_key");
    }

    /* Access modifiers changed, original: protected */
    public String getAnalyticsTitle() {
        Ensighten.evaluateEvent(this, "getAnalyticsTitle", null);
        return getString(C2658R.string.analytics_screen_offers_product_chooser);
    }

    public void onResume() {
        super.onResume();
        getNavigationActivity().setTitle(getString(C2658R.string.choose_item));
    }

    public void onDetach() {
        super.onDetach();
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        boolean z = true;
        Ensighten.evaluateEvent(this, "onCreateOptionsMenu", new Object[]{menu, inflater});
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(C2358R.C2360menu.done, menu);
        this.mDoneMenuItem = menu.findItem(C2358R.C2357id.action_done);
        MenuItem menuItem = this.mDoneMenuItem;
        if (this.mProductListView.getCheckedItemPosition() < 0) {
            z = false;
        }
        menuItem.setEnabled(z);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        Ensighten.evaluateEvent(this, "onOptionsItemSelected", new Object[]{item});
        switch (item.getItemId()) {
            case 16908332:
            case C2358R.C2357id.action_done /*2131821900*/:
                saveAction();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void saveAction() {
        Ensighten.evaluateEvent(this, "saveAction", null);
        if (this.mProductListView.getCheckedItemPosition() >= 0) {
            offerProductSelected((OrderProduct) this.mOfferProductListAdapter.getItem(this.mProductListView.getCheckedItemPosition()));
        }
        getActivity().finish();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(C2658R.layout.fragment_offer_product, null);
        this.mProgressBar = (ProgressBar) rootView.findViewById(C2358R.C2357id.progress_bar);
        this.mProductListView = (ListView) rootView.findViewById(C2358R.C2357id.product_list);
        this.mProductListView.setOnItemClickListener(this);
        this.mOfferProductListAdapter = new OfferProductListAdapter(getActivity(), C2658R.layout.offer_product_list_item);
        this.mProgressBar.setVisibility(0);
        this.mProductListView.setVisibility(4);
        if (this.mOfferProduct != null) {
            refresh();
        }
        return rootView;
    }

    private void refresh() {
        Ensighten.evaluateEvent(this, "refresh", null);
        this.mOrderingModule = (OrderingModule) ModuleManager.getModule("ordering");
        this.mNutritionModule = (NutritionModule) ModuleManager.getModule(NutritionModule.NAME);
        final AsyncCounter<Product> counter = new AsyncCounter(this.mOfferProduct.getProducts().size(), new C33591());
        for (OfferProductOption offerProductOption : this.mOfferProduct.getProducts()) {
            this.mOrderingModule.getProductForExternalId(offerProductOption.getProductCode(), new AsyncListener<Product>() {
                public void onResponse(Product response, AsyncToken token, AsyncException exception) {
                    Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
                    if (exception == null) {
                        counter.success(response);
                    } else {
                        counter.success(null);
                    }
                }
            });
        }
    }

    private void offerProductSelected(OrderProduct product) {
        Ensighten.evaluateEvent(this, "offerProductSelected", new Object[]{product});
        Intent returnIntent = new Intent();
        Bundle bundle = new Bundle();
        DataPasser.getInstance().putData("selected_recipe_key", product);
        bundle.putInt("offer_key", this.mKey);
        returnIntent.putExtras(bundle);
        getActivity().setResult(-1, returnIntent);
    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Ensighten.evaluateEvent(this, "onItemClick", new Object[]{parent, view, new Integer(position), new Long(id)});
        this.mDoneMenuItem.setEnabled(true);
    }

    private boolean productHasIngredientsOrExtras(OrderProduct ingredient) {
        Ensighten.evaluateEvent(this, "productHasIngredientsOrExtras", new Object[]{ingredient});
        Product product = ingredient.getProduct();
        if (ListUtils.isEmpty(product.getIngredients())) {
            product.setIngredients(this.mOrderingModule.getProductIngredients(product));
        }
        if (ListUtils.isEmpty(product.getExtras())) {
            product.setExtras(this.mOrderingModule.getProductExtras(product));
        }
        if ((ListUtils.isEmpty(product.getIngredients()) || ingredient.isMeal()) && ListUtils.isEmpty(product.getExtras())) {
            return false;
        }
        return true;
    }

    private void onProductCustomizeClicked(OrderProduct product, int position) {
        Ensighten.evaluateEvent(this, "onProductCustomizeClicked", new Object[]{product, new Integer(position)});
        AnalyticsUtils.trackOnClickEvent(getAnalyticsTitle(), "PDP - Customization");
        this.mIngredientPosition = position;
        Bundle bundle = new Bundle();
        DataPasser.getInstance().putData("ARG_PRODUCT", product);
        startActivityForResult(ProductCustomizationActivity.class, "product_customization", bundle, 45352);
    }

    private void onProductInfoButtonClicked(String recipeId) {
        Ensighten.evaluateEvent(this, "onProductInfoButtonClicked", new Object[]{recipeId});
        AnalyticsUtils.trackOnClickEvent("/order/item", "PDP - Nutrition info");
        NavigationManager.getInstance().showNutrition(getActivity(), recipeId, null, null, getNavigationActivity());
    }

    public void updateCustomizationText(OrderProduct ingredient) {
        Ensighten.evaluateEvent(this, "updateCustomizationText", new Object[]{ingredient});
        int size = this.mOfferProductListAdapter.getCount();
        Map<Integer, OrderProduct> tempCustomizations = new HashMap();
        for (int i = 0; i < size; i++) {
            if (i == this.mIngredientPosition) {
                ((OrderProduct) this.mOfferProductListAdapter.getItem(i)).setCustomizations(ingredient.getCustomizations());
            } else {
                ((OrderProduct) this.mOfferProductListAdapter.getItem(i)).setCustomizations(tempCustomizations);
            }
        }
        this.mOfferProductListAdapter.notifyDataSetChanged();
        Toast.makeText(getActivity(), C2658R.string.general_saved, 1).show();
    }
}
