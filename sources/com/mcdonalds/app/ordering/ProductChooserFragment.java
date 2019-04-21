package com.mcdonalds.app.ordering;

import android.app.Activity;
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
import android.widget.ListView;
import android.widget.Toast;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.navigation.NavigationManager;
import com.mcdonalds.app.ordering.ProductChooserListAdapter.OnProductChooserListener;
import com.mcdonalds.app.p043ui.URLNavigationFragment;
import com.mcdonalds.app.util.AnalyticsUtils;
import com.mcdonalds.app.util.ListUtils;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.models.Order.PriceType;
import com.mcdonalds.sdk.modules.models.OrderProduct;
import com.mcdonalds.sdk.modules.ordering.OrderManager;
import com.mcdonalds.sdk.modules.ordering.OrderingModule;
import com.mcdonalds.sdk.modules.storelocator.Store;
import com.mcdonalds.sdk.services.data.DataPasser;
import com.mcdonalds.sdk.services.network.RequestManager;
import com.mcdonalds.sdk.services.network.RequestManagerServiceConnection;
import com.mcdonalds.sdk.utils.SDKUtils;
import java.util.ArrayList;
import java.util.List;

public class ProductChooserFragment extends URLNavigationFragment implements OnItemClickListener, OnProductChooserListener {
    private double mBasePrice = -1.0d;
    private OrderProduct mChoice;
    private OrderProduct mChoiceSelection;
    private MenuItem mDoneMenuItem;
    private int mIndex;
    private int mIngredientPosition;
    private ProductChooserListAdapter mListAdapter;
    private ListView mListView;
    private List<Integer> mOutageCode;
    private String mParentName;
    private int mProductPosition;
    private RequestManagerServiceConnection mServiceConnection;

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mServiceConnection = RequestManager.register(getActivity());
    }

    public void onDetach() {
        super.onDetach();
        RequestManager.unregister(getActivity(), this.mServiceConnection);
    }

    /* Access modifiers changed, original: protected */
    public String getAnalyticsTitle() {
        Ensighten.evaluateEvent(this, "getAnalyticsTitle", null);
        return this.mParentName + getString(C2658R.string.analytics_screen_product_chooser);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        Bundle args = getArguments();
        this.mChoice = (OrderProduct) DataPasser.getInstance().getData(args.getInt("ARG_CHOICE_KEY"));
        this.mIndex = args.getInt("ARG_INDEX");
        this.mProductPosition = args.getInt("ARG_PRODUCT_POSITION");
        Store store = OrderManager.getInstance().getCurrentStore();
        if (store != null) {
            List<String> outageProductCodes = store.getOutageProducts();
            this.mOutageCode = new ArrayList();
            if (!ListUtils.isEmpty(outageProductCodes)) {
                for (String productCode : outageProductCodes) {
                    this.mOutageCode.add(Integer.valueOf(Integer.parseInt(productCode)));
                }
            }
        }
        if (args.containsKey("ARG_BASE_PRICE")) {
            this.mBasePrice = args.getDouble("ARG_BASE_PRICE");
        }
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        boolean z = true;
        Ensighten.evaluateEvent(this, "onCreateOptionsMenu", new Object[]{menu, inflater});
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(C2358R.C2360menu.done, menu);
        this.mDoneMenuItem = menu.findItem(C2358R.C2357id.action_done);
        MenuItem menuItem = this.mDoneMenuItem;
        if (this.mChoiceSelection == null) {
            z = false;
        }
        menuItem.setEnabled(z);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        Ensighten.evaluateEvent(this, "onOptionsItemSelected", new Object[]{item});
        switch (item.getItemId()) {
            case 16908332:
                saveAction();
                return true;
            case C2358R.C2357id.action_done /*2131821900*/:
                doneAction();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void doneAction() {
        Ensighten.evaluateEvent(this, "doneAction", null);
        Activity activity = getActivity();
        if (!haveProductSelected()) {
            activity.setResult(0);
        } else if (this.mOutageCode.contains(this.mChoiceSelection.getProduct().getExternalId())) {
            activity.setResult(0);
        } else {
            if (!this.mChoiceSelection.getProductCode().equals("0") && this.mChoiceSelection.getQuantity() == 0) {
                this.mChoiceSelection.setQuantity(1);
            }
            if (this.mChoiceSelection.getChoices() != null) {
                for (int choiceIndex = 0; choiceIndex < this.mChoiceSelection.getChoices().size(); choiceIndex++) {
                    OrderProduct choice = (OrderProduct) this.mChoiceSelection.getChoices().get(choiceIndex);
                    List<OrderProduct> ingredients = choice.getIngredients();
                    boolean hideSingleChoice = ProductUtils.hideSingleChoice();
                    if (choice.isSingleChoice() && hideSingleChoice) {
                        OrderProduct choiceSolution = (OrderProduct) ingredients.get(0);
                        choiceSolution.setQuantity(1);
                        this.mChoiceSelection.setChoiceSolution(choiceIndex, choiceSolution);
                    }
                }
            }
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            DataPasser.getInstance().putData("RESULT_PRODUCT", this.mChoiceSelection);
            bundle.putInt("RESULT_INDEX", this.mIndex);
            bundle.putInt("RESULT_POSITION", this.mProductPosition);
            intent.putExtras(bundle);
            activity.setResult(-1, intent);
        }
        activity.finish();
    }

    private void saveAction() {
        Ensighten.evaluateEvent(this, "saveAction", null);
        Activity activity = getActivity();
        if (haveProductSelected()) {
            if (!this.mChoiceSelection.getProductCode().equals("0") && this.mChoiceSelection.getQuantity() == 0) {
                this.mChoiceSelection.setQuantity(1);
            }
            setSingleChoiceItemIfAvailable();
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            DataPasser.getInstance().putData("RESULT_PRODUCT", this.mChoiceSelection);
            bundle.putInt("RESULT_INDEX", this.mIndex);
            bundle.putInt("RESULT_POSITION", this.mProductPosition);
            intent.putExtras(bundle);
            activity.setResult(-1, intent);
        } else {
            activity.setResult(0);
        }
        activity.finish();
    }

    private boolean setSingleChoiceItemIfAvailable() {
        Ensighten.evaluateEvent(this, "setSingleChoiceItemIfAvailable", null);
        if (!this.mChoiceSelection.getProductCode().equals("0") && this.mChoiceSelection.getQuantity() == 0) {
            this.mChoiceSelection.setQuantity(1);
        }
        boolean showAdditionalChoices = false;
        if (!ListUtils.isEmpty(this.mChoiceSelection.getChoices())) {
            for (int choiceIndex = 0; choiceIndex < this.mChoiceSelection.getChoices().size(); choiceIndex++) {
                OrderProduct choice = (OrderProduct) this.mChoiceSelection.getChoices().get(choiceIndex);
                boolean hideSingleChoice = ProductUtils.hideSingleChoice();
                if (choice.isSingleChoice() && hideSingleChoice) {
                    if (ListUtils.isEmpty(choice.getIngredients())) {
                        ProductUtils.populateProductIngredients(choice, (OrderingModule) ModuleManager.getModule("ordering"));
                    }
                    OrderProduct choiceSolution = (OrderProduct) choice.getIngredients().get(0);
                    choiceSolution.setQuantity(1);
                    this.mChoiceSelection.setChoiceSolution(choiceIndex, choiceSolution);
                } else {
                    showAdditionalChoices = true;
                }
            }
        }
        return showAdditionalChoices;
    }

    public void updateChoice(OrderProduct orderProduct) {
        Ensighten.evaluateEvent(this, "updateChoice", new Object[]{orderProduct});
        if (orderProduct == null) {
            this.mChoiceSelection = null;
            this.mListAdapter.setSelectedPosition(-1);
            this.mListAdapter.notifyDataSetChanged();
            this.mDoneMenuItem.setEnabled(false);
        } else if (orderProduct.getProductCode().equals("0")) {
            this.mChoiceSelection.setChoiceSolutions(orderProduct.getChoiceSolutions());
            this.mChoiceSelection.setChoices(orderProduct.getChoices());
        } else {
            this.mChoiceSelection = OrderProduct.getChoiceWithinChoice(this.mChoiceSelection, orderProduct);
        }
    }

    private boolean haveProductSelected() {
        Ensighten.evaluateEvent(this, "haveProductSelected", null);
        return (this.mChoiceSelection == null || getLastChild().isChoice()) ? false : true;
    }

    private OrderProduct getLastChild() {
        Ensighten.evaluateEvent(this, "getLastChild", null);
        OrderProduct orderProduct = this.mChoiceSelection;
        if (orderProduct.getChoices() == null || orderProduct.getChoices().size() != 1) {
            return orderProduct;
        }
        if (orderProduct.getChoiceSolutions() == null || orderProduct.getChoiceSolutions().size() != 1) {
            return (OrderProduct) orderProduct.getChoices().get(0);
        }
        return (OrderProduct) orderProduct.getChoiceSolutions().get(0);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C2658R.layout.fragment_list, container, false);
        this.mListAdapter = new ProductChooserListAdapter(getNavigationActivity(), this.mChoice, getBasePrice());
        if (this.mListAdapter.isEmpty()) {
            saveAction();
        }
        this.mListAdapter.setListener(this);
        this.mListView = (ListView) view.findViewById(C2358R.C2357id.list);
        this.mListView.setOnItemClickListener(this);
        this.mListView.setAdapter(this.mListAdapter);
        this.mListView.setChoiceMode(1);
        this.mListView.setItemsCanFocus(false);
        return view;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        Ensighten.evaluateEvent(this, "onItemClick", new Object[]{adapterView, view, new Integer(position), new Long(l)});
        OrderProduct orderProduct = (OrderProduct) this.mListAdapter.getItem(position);
        AnalyticsUtils.trackOnClickEvent(getAnalyticsTitle(), orderProduct.getProduct().getLongName());
        this.mChoiceSelection = getOrderProductCopy(orderProduct);
        this.mDoneMenuItem.setEnabled(true);
        if (this.mOutageCode.contains(orderProduct.getProduct().getExternalId())) {
            this.mListView.setItemChecked(position, false);
        } else {
            this.mListAdapter.setSelectedPosition(position);
            this.mListAdapter.notifyDataSetChanged();
        }
        if (!orderProduct.isChoice() && !orderProduct.getProduct().hasChoice().booleanValue()) {
            return;
        }
        if (!orderProduct.isSingleChoice() || !ProductUtils.hideSingleChoice()) {
            Intent intent = new Intent(getActivity(), ProductChooserActivity.class);
            Bundle bundle = new Bundle();
            bundle.putInt("ARG_CHOICE_KEY", DataPasser.getInstance().putData(orderProduct));
            bundle.putDouble("ARG_BASE_PRICE", getBasePrice());
            intent.putExtras(bundle);
            getActivity().startActivityForResult(intent, 64420);
        }
    }

    private OrderProduct getOrderProductCopy(OrderProduct orderProduct) {
        Ensighten.evaluateEvent(this, "getOrderProductCopy", new Object[]{orderProduct});
        OrderProduct newOrderProduct = new OrderProduct(orderProduct);
        if (newOrderProduct.isChoice()) {
            newOrderProduct.getIngredients().clear();
        }
        return newOrderProduct;
    }

    public void onProductInfoButtonClicked(String foodId) {
        Ensighten.evaluateEvent(this, "onProductInfoButtonClicked", new Object[]{foodId});
        AnalyticsUtils.trackOnClickEvent(getAnalyticsTitle(), "PDP - Nutrition info");
        NavigationManager.getInstance().showNutrition(getActivity(), foodId, null, null, getNavigationActivity());
    }

    public void onProductCustomizeClicked(OrderProduct product, int position) {
        Ensighten.evaluateEvent(this, "onProductCustomizeClicked", new Object[]{product, new Integer(position)});
        AnalyticsUtils.trackOnClickEvent(getAnalyticsTitle(), "PDP - Customization");
        this.mIngredientPosition = position;
        Bundle bundle = new Bundle();
        DataPasser.getInstance().putData("ARG_PRODUCT", product);
        startActivityForResult(ProductCustomizationActivity.class, "product_customization", bundle, 45352);
    }

    public void updateCustomizationText(OrderProduct ingredient) {
        Ensighten.evaluateEvent(this, "updateCustomizationText", new Object[]{ingredient});
        List<OrderProduct> orderProductList = this.mChoice.getIngredients();
        orderProductList.set(this.mIngredientPosition, ingredient);
        this.mListAdapter.clearAndAddAll(orderProductList);
        this.mListView.setSoundEffectsEnabled(false);
        this.mListView.performItemClick(this.mListAdapter.getView(this.mIngredientPosition, null, null), this.mIngredientPosition, this.mListAdapter.getItemId(this.mIngredientPosition));
        this.mListView.setSoundEffectsEnabled(true);
        if (ingredient.equals(this.mChoiceSelection)) {
            ProductUtils.getActualChoice(this.mChoiceSelection).setCustomizations(ingredient.getCustomizations());
        }
        Toast.makeText(getActivity(), C2658R.string.general_saved, 1).show();
    }

    private double getBasePrice() {
        Ensighten.evaluateEvent(this, "getBasePrice", null);
        PriceType priceType = OrderManager.getInstance().getCurrentOrder().getPriceType();
        if (SDKUtils.doubleEquals(this.mBasePrice, -1.0d)) {
            return this.mChoice.getBasePrice(priceType);
        }
        return this.mBasePrice;
    }
}
