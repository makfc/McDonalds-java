package com.mcdonalds.app.ordering.customization;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p001v7.app.ActionBar;
import android.support.p001v7.widget.LinearLayoutManager;
import android.support.p001v7.widget.RecyclerView;
import android.support.p001v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.ordering.customization.ProductCustomizationAdapter.OnUpdateDataListener;
import com.mcdonalds.app.p043ui.URLNavigationFragment;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.modules.models.Ingredient;
import com.mcdonalds.sdk.modules.models.OrderProduct;
import com.mcdonalds.sdk.modules.models.Product;
import com.mcdonalds.sdk.services.data.DataPasser;
import com.mcdonalds.sdk.utils.ListUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProductCustomizationFragment extends URLNavigationFragment implements OnUpdateDataListener {
    public static String RESULT_PRODUCT = "RESULT_PRODUCT";
    public static String RESULT_PRODUCT_INDEX = "RESULT_PRODUCT_INDEX";
    private ProductCustomizationAdapter mAdapter;
    private OrderProduct mProduct;
    private int mProductIndex;
    private HashMap<Integer, OrderProduct> mProducts;
    private TextView resetButton;

    /* renamed from: com.mcdonalds.app.ordering.customization.ProductCustomizationFragment$1 */
    class C34681 implements OnClickListener {
        C34681() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            ProductCustomizationFragment.access$000(ProductCustomizationFragment.this);
        }
    }

    static /* synthetic */ void access$000(ProductCustomizationFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.customization.ProductCustomizationFragment", "access$000", new Object[]{x0});
        x0.resetAction();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments().containsKey("ARG_PRODUCT")) {
            this.mProduct = (OrderProduct) getArguments().getParcelable("ARG_PRODUCT");
        } else {
            this.mProduct = (OrderProduct) DataPasser.getInstance().getData("ARG_PRODUCT");
        }
        this.mProductIndex = getArguments().getInt("ARG_PRODUCT_INDEX");
        this.mProducts = new HashMap();
        this.mAdapter = new ProductCustomizationAdapter(getActivity());
        this.mAdapter.setOnUpdateDataListener(this);
        setHasOptionsMenu(true);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C2658R.layout.fragment_new_product_customization, container, false);
        RecyclerView list = (RecyclerView) view.findViewById(C2358R.C2357id.list);
        list.setLayoutManager(new LinearLayoutManager(view.getContext()));
        list.setAdapter(this.mAdapter);
        list.setItemAnimator(null);
        ActionBar actionBar = getNavigationActivity().getSupportActionBar();
        if (actionBar != null) {
            actionBar.setCustomView((int) C2658R.layout.action_bar_customization_custom_view);
            actionBar.setDisplayShowCustomEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(false);
            actionBar.setTitle((int) C2658R.string.customize_product);
            View actionBarView = actionBar.getCustomView();
            ((Toolbar) actionBarView.getParent()).setContentInsetsAbsolute(0, 0);
            ((TextView) actionBarView.findViewById(C2358R.C2357id.action_title)).setText(C2658R.string.customize_product);
            this.resetButton = (TextView) actionBarView.findViewById(C2358R.C2357id.action_reset);
            this.resetButton.setOnClickListener(new C34681());
        }
        return view;
    }

    private boolean isCustomizationNotDefault() {
        Ensighten.evaluateEvent(this, "isCustomizationNotDefault", null);
        for (ProductCustomizationItem item : this.mAdapter.getItems()) {
            if (item.product.getQuantity() != item.ingredient.getDefaultQuantity()) {
                return true;
            }
        }
        return false;
    }

    public void onChangeDataInAdapter() {
        Ensighten.evaluateEvent(this, "onChangeDataInAdapter", null);
        if (isCustomizationNotDefault()) {
            this.resetButton.setEnabled(true);
            this.resetButton.setTextColor(getResources().getColor(C2658R.color.mcd_red_pressed));
            return;
        }
        this.resetButton.setEnabled(false);
        this.resetButton.setTextColor(getResources().getColor(C2658R.color.mcd_red_deactivated));
    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        putDataToAdapter(false);
        onChangeDataInAdapter();
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        Ensighten.evaluateEvent(this, "onCreateOptionsMenu", new Object[]{menu, inflater});
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(C2358R.C2360menu.done, menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        Ensighten.evaluateEvent(this, "onOptionsItemSelected", new Object[]{item});
        switch (item.getItemId()) {
            case 16908332:
                return true;
            case C2358R.C2357id.action_done /*2131821900*/:
                save();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void resetAction() {
        Ensighten.evaluateEvent(this, "resetAction", null);
        this.mProduct.getCustomizations().clear();
        putDataToAdapter(true);
        onChangeDataInAdapter();
    }

    private void putDataToAdapter(boolean reset) {
        Ensighten.evaluateEvent(this, "putDataToAdapter", new Object[]{new Boolean(reset)});
        Product product = this.mProduct.getProduct();
        if (product != null) {
            List<ProductCustomizationItem> items = new ArrayList();
            boolean header = true;
            for (Ingredient ingredient : product.getIngredients()) {
                items.add(getItem(ingredient, header, 0, reset));
                header = false;
            }
            header = true;
            List<Ingredient> extras = product.getExtras();
            if (!ListUtils.isEmpty(extras)) {
                for (Ingredient ingredient2 : extras) {
                    if (ingredient2.getIsCustomerFriendly()) {
                        items.add(getItem(ingredient2, header, 1, reset));
                        header = false;
                    }
                }
            }
            this.mAdapter.setItems(items);
        }
    }

    @NonNull
    private ProductCustomizationItem getItem(Ingredient ingredient, boolean header, @ProductCustomizationType int type, boolean reset) {
        ProductCustomizationItem item;
        Ensighten.evaluateEvent(this, "getItem", new Object[]{ingredient, new Boolean(header), new Integer(type), new Boolean(reset)});
        OrderProduct customization = (OrderProduct) this.mProduct.getCustomizations().get(ingredient.getProduct().getExternalId());
        if (customization != null) {
            this.mProducts.put(customization.getProduct().getExternalId(), customization);
        } else {
            customization = getOrderProduct(ingredient.getProduct(), ingredient.getDefaultQuantity(), reset);
        }
        if (type == 0) {
            item = new ProductCustomizationIngredient(ingredient, customization);
        } else {
            item = new ProductCustomizationExtra(ingredient, customization);
        }
        item.setHeader(header);
        return item;
    }

    private OrderProduct getOrderProduct(Product product, int defaultQuantity, boolean reset) {
        Ensighten.evaluateEvent(this, "getOrderProduct", new Object[]{product, new Integer(defaultQuantity), new Boolean(reset)});
        OrderProduct orderProduct = (OrderProduct) this.mProducts.get(product.getExternalId());
        if (orderProduct != null && !reset) {
            return orderProduct;
        }
        orderProduct = OrderProduct.createProduct(product, Integer.valueOf(defaultQuantity));
        if (orderProduct != null) {
            this.mProducts.put(product.getExternalId(), orderProduct);
        }
        return orderProduct;
    }

    private void save() {
        Ensighten.evaluateEvent(this, "save", null);
        for (ProductCustomizationItem item : this.mAdapter.getItems()) {
            if (item.product.getQuantity() == item.ingredient.getDefaultQuantity() && !item.product.getIsLight()) {
                this.mProducts.remove(item.ingredient.getProduct().getExternalId());
            }
        }
        this.mProduct.setCustomizations(this.mProducts);
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        DataPasser.getInstance().putData(RESULT_PRODUCT, this.mProduct);
        bundle.putInt(RESULT_PRODUCT_INDEX, this.mProductIndex);
        intent.putExtras(bundle);
        getActivity().setResult(-1, intent);
        getActivity().finish();
    }
}
