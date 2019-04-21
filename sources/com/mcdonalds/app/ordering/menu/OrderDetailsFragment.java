package com.mcdonalds.app.ordering.menu;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.analytics.datalayer.DataLayerManager;
import com.mcdonalds.app.ordering.OrderUtils;
import com.mcdonalds.app.ordering.OrderingManager;
import com.mcdonalds.app.ordering.methodselection.OrderMethodSelectionActivity;
import com.mcdonalds.app.p043ui.URLActionBarActivity;
import com.mcdonalds.app.p043ui.URLNavigationFragment;
import com.mcdonalds.app.util.AnalyticsUtils;
import com.mcdonalds.app.util.AppUtils;
import com.mcdonalds.app.util.FavoriteInputViewHolder;
import com.mcdonalds.app.util.ListUtils;
import com.mcdonalds.app.util.ServiceUtils;
import com.mcdonalds.app.util.UIUtils;
import com.mcdonalds.app.util.UIUtils.MCDAlertDialogBuilder;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.customer.CustomerModule;
import com.mcdonalds.sdk.modules.models.CustomerOrder;
import com.mcdonalds.sdk.modules.models.CustomerOrderProduct;
import com.mcdonalds.sdk.modules.models.FavoriteItem;
import com.mcdonalds.sdk.modules.models.FavoriteItem.FavoriteProductType;
import com.mcdonalds.sdk.modules.models.Order;
import com.mcdonalds.sdk.modules.models.OrderProduct;
import com.mcdonalds.sdk.modules.ordering.OrderingModule;
import com.mcdonalds.sdk.services.analytics.JiceArgs;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.mcdonalds.sdk.services.data.DataPasser;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OrderDetailsFragment extends URLNavigationFragment {
    public static final String NAME = OrderDetailsFragment.class.getSimpleName();
    private CustomerModule mCustomerModule;
    private CustomerOrder mCustomerOrder;
    private FavoriteInputViewHolder mFavoriteInputContainer;
    private TextView mFavoriteNameInput;
    private Button mFavoriteOrderButton;
    private boolean mIsFavorite;
    private final OnClickListener mOnClickAddToFavorites = new C35665();
    private final OnClickListener mOnClickCancel = new C35676();
    private final OnClickListener mOnClickFavoriteOrder = new C35644();
    private Order mOrder;
    private LinearLayout mOrderReceiptContainer;

    /* renamed from: com.mcdonalds.app.ordering.menu.OrderDetailsFragment$10 */
    class C355910 implements DialogInterface.OnClickListener {
        C355910() {
        }

        public void onClick(DialogInterface dialog, int which) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
            dialog.dismiss();
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.menu.OrderDetailsFragment$1 */
    class C35611 implements OnClickListener {
        C35611() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            OrderDetailsFragment.access$000(OrderDetailsFragment.this);
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.menu.OrderDetailsFragment$3 */
    class C35633 implements AsyncListener<List<FavoriteItem>> {
        C35633() {
        }

        public void onResponse(List<FavoriteItem> response, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            OrderDetailsFragment.access$300(OrderDetailsFragment.this);
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.menu.OrderDetailsFragment$4 */
    class C35644 implements OnClickListener {
        C35644() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            if (!Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.menu.OrderDetailsFragment", "access$400", new Object[]{OrderDetailsFragment.this})) {
                return;
            }
            if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.menu.OrderDetailsFragment", "access$500", new Object[]{OrderDetailsFragment.this})) {
                OrderDetailsFragment.access$600(OrderDetailsFragment.this);
                return;
            }
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.menu.OrderDetailsFragment", "access$700", new Object[]{OrderDetailsFragment.this}).show();
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.menu.OrderDetailsFragment", "access$700", new Object[]{OrderDetailsFragment.this}).getSaveToFavoritesButton().setOnClickListener(Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.menu.OrderDetailsFragment", "access$800", new Object[]{OrderDetailsFragment.this}));
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.menu.OrderDetailsFragment", "access$700", new Object[]{OrderDetailsFragment.this}).getCancelToFavoritesButton().setOnClickListener(Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.menu.OrderDetailsFragment", "access$900", new Object[]{OrderDetailsFragment.this}));
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.menu.OrderDetailsFragment$5 */
    class C35665 implements OnClickListener {

        /* renamed from: com.mcdonalds.app.ordering.menu.OrderDetailsFragment$5$1 */
        class C35651 implements DialogInterface.OnClickListener {
            C35651() {
            }

            public void onClick(DialogInterface dialog, int which) {
                Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
                dialog.dismiss();
            }
        }

        C35665() {
        }

        public void onClick(View v) {
            String favoriteName;
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.menu.OrderDetailsFragment", "access$1000", new Object[]{OrderDetailsFragment.this}).getText() != null) {
                favoriteName = Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.menu.OrderDetailsFragment", "access$1000", new Object[]{OrderDetailsFragment.this}).getText().toString();
            } else {
                favoriteName = "";
            }
            if (TextUtils.isEmpty(favoriteName.trim())) {
                MCDAlertDialogBuilder.withContext(v.getContext()).setMessage(v.getContext().getString(C2658R.string.alert_error_empty_favorite_order_name_msg)).setPositiveButton(OrderDetailsFragment.this.getResources().getString(C2658R.string.f6083ok), new C35651()).create().show();
                DataLayerManager.getInstance().recordError("Invalid custom order name");
                return;
            }
            UIUtils.startActivityIndicator(OrderDetailsFragment.this.getNavigationActivity(), OrderDetailsFragment.this.getResources().getString(C2658R.string.saving) + " " + favoriteName);
            OrderDetailsFragment.access$1100(OrderDetailsFragment.this, favoriteName, Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.menu.OrderDetailsFragment", "access$700", new Object[]{OrderDetailsFragment.this}));
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.menu.OrderDetailsFragment$6 */
    class C35676 implements OnClickListener {
        C35676() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.menu.OrderDetailsFragment", "access$700", new Object[]{OrderDetailsFragment.this}).hide();
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.menu.OrderDetailsFragment$8 */
    class C35698 implements AsyncListener<List<FavoriteItem>> {
        C35698() {
        }

        public void onResponse(List<FavoriteItem> response, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            OrderDetailsFragment.access$600(OrderDetailsFragment.this);
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.menu.OrderDetailsFragment$9 */
    class C35709 implements AsyncListener<Boolean> {
        C35709() {
        }

        public void onResponse(Boolean aBoolean, AsyncToken asyncToken, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{aBoolean, asyncToken, exception});
            UIUtils.stopActivityIndicator();
            if (exception == null) {
                OrderDetailsFragment.access$502(OrderDetailsFragment.this, false);
                OrderDetailsFragment.access$1200(OrderDetailsFragment.this, null);
            } else {
                AsyncException.report(exception);
            }
            OrderDetailsFragment.access$1300(OrderDetailsFragment.this);
        }
    }

    static /* synthetic */ void access$000(OrderDetailsFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.menu.OrderDetailsFragment", "access$000", new Object[]{x0});
        x0.orderAgain();
    }

    static /* synthetic */ Order access$102(OrderDetailsFragment x0, Order x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.menu.OrderDetailsFragment", "access$102", new Object[]{x0, x1});
        x0.mOrder = x1;
        return x1;
    }

    static /* synthetic */ void access$1100(OrderDetailsFragment x0, String x1, FavoriteInputViewHolder x2) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.menu.OrderDetailsFragment", "access$1100", new Object[]{x0, x1, x2});
        x0.addToFavorites(x1, x2);
    }

    static /* synthetic */ void access$1200(OrderDetailsFragment x0, String x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.menu.OrderDetailsFragment", "access$1200", new Object[]{x0, x1});
        x0.updateFavoriteName(x1);
    }

    static /* synthetic */ void access$1300(OrderDetailsFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.menu.OrderDetailsFragment", "access$1300", new Object[]{x0});
        x0.refreshAddToFavoritesButton();
    }

    static /* synthetic */ void access$200(OrderDetailsFragment x0, LayoutInflater x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.menu.OrderDetailsFragment", "access$200", new Object[]{x0, x1});
        x0.load(x1);
    }

    static /* synthetic */ void access$300(OrderDetailsFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.menu.OrderDetailsFragment", "access$300", new Object[]{x0});
        x0.loadFavorites();
    }

    static /* synthetic */ boolean access$502(OrderDetailsFragment x0, boolean x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.menu.OrderDetailsFragment", "access$502", new Object[]{x0, new Boolean(x1)});
        x0.mIsFavorite = x1;
        return x1;
    }

    static /* synthetic */ void access$600(OrderDetailsFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.menu.OrderDetailsFragment", "access$600", new Object[]{x0});
        x0.deleteFromFavorites();
    }

    /* Access modifiers changed, original: protected */
    public String getAnalyticsTitle() {
        Ensighten.evaluateEvent(this, "getAnalyticsTitle", null);
        return getString(C2658R.string.analytics_screen_order_detail);
    }

    /* Access modifiers changed, original: protected */
    public String getTitle() {
        Ensighten.evaluateEvent(this, "getTitle", null);
        return getString(C2658R.string.title_activity_order_products);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int dataPasserKey = getArguments().getInt("order_data_passer_key", -1);
        if (dataPasserKey == -1) {
            Serializable data = getArguments().getSerializable(JiceArgs.EVENT_SUBMIT_ORDER);
            if (data instanceof Order) {
                this.mOrder = (Order) data;
                return;
            } else {
                this.mCustomerOrder = (CustomerOrder) data;
                return;
            }
        }
        this.mOrder = (Order) DataPasser.getInstance().getData(dataPasserKey);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(C2658R.layout.fragment_order_details, container, false);
        rootView.findViewById(C2358R.C2357id.order_again_btn).setOnClickListener(new C35611());
        this.mOrderReceiptContainer = (LinearLayout) rootView.findViewById(C2358R.C2357id.order_container);
        this.mFavoriteOrderButton = (Button) rootView.findViewById(C2358R.C2357id.favorite_last_order);
        this.mFavoriteOrderButton.setOnClickListener(this.mOnClickFavoriteOrder);
        boolean hideNutrition = AppUtils.hideNutritionOnOrderingPages();
        boolean hideCustomiztion = Configuration.getSharedInstance().getBooleanForKey("interface.hideProductCustomizationButton");
        if (hideNutrition || hideCustomiztion) {
            rootView.findViewById(C2358R.C2357id.details_energy_warning_text).setVisibility(8);
        }
        this.mFavoriteNameInput = (TextView) rootView.findViewById(C2358R.C2357id.favorite_name_input);
        this.mFavoriteInputContainer = new FavoriteInputViewHolder(getNavigationActivity(), rootView);
        UIUtils.startActivityIndicator(getActivity(), getString(C2658R.string.title_loading));
        init(inflater);
        return rootView;
    }

    private void init(final LayoutInflater inflater) {
        Ensighten.evaluateEvent(this, "init", new Object[]{inflater});
        if (this.mCustomerModule == null) {
            this.mCustomerModule = (CustomerModule) ModuleManager.getModule(CustomerModule.NAME);
        }
        if (this.mOrder != null) {
            load(inflater);
            return;
        }
        OrderUtils.ordersFromCustomerOrders(Arrays.asList(new CustomerOrder[]{this.mCustomerOrder}), (OrderingModule) ModuleManager.getModule("ordering"), new AsyncListener<List<Order>>() {
            public void onResponse(List<Order> response, AsyncToken token, AsyncException exception) {
                Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
                if (ListUtils.isNotEmpty(response)) {
                    OrderDetailsFragment.access$102(OrderDetailsFragment.this, (Order) response.get(0));
                }
                OrderDetailsFragment.access$200(OrderDetailsFragment.this, inflater);
            }
        });
    }

    private void load(LayoutInflater inflater) {
        Ensighten.evaluateEvent(this, "load", new Object[]{inflater});
        loadFavorites();
        refreshAddToFavoritesButton();
        if (this.mOrder != null) {
            OrderReceiptRecents.configureOrderReceiptForDisplay(this.mOrder, getActivity(), this.mOrderReceiptContainer, inflater);
        }
    }

    private void loadFavorites() {
        Ensighten.evaluateEvent(this, "loadFavorites", null);
        if (this.mCustomerModule.needsFavoriteProductsRefresh()) {
            ServiceUtils.getSharedInstance().retrieveFavoriteProducts(new C35633());
            return;
        }
        this.mIsFavorite = checkFavorites(this.mCustomerModule.getFavoriteProducts());
        refreshAddToFavoritesButton();
        UIUtils.stopActivityIndicator();
    }

    private void refreshAddToFavoritesButton() {
        Ensighten.evaluateEvent(this, "refreshAddToFavoritesButton", null);
        this.mFavoriteOrderButton.setBackgroundResource(this.mIsFavorite ? C2358R.C2359drawable.button_red : C2358R.C2359drawable.button_dark_gray);
        this.mFavoriteOrderButton.setText(this.mIsFavorite ? C2658R.string.favorited_order : C2658R.string.favorite_order);
    }

    private boolean canFavorite() {
        Ensighten.evaluateEvent(this, "canFavorite", null);
        if (this.mOrder == null || ListUtils.isEmpty(this.mOrder.getProducts())) {
            return false;
        }
        return true;
    }

    private void updateFavoriteName(String favoriteName) {
        Ensighten.evaluateEvent(this, "updateFavoriteName", new Object[]{favoriteName});
        TextView title = (TextView) this.mOrderReceiptContainer.findViewById(2131820647);
        TextView subTitle = (TextView) this.mOrderReceiptContainer.findViewById(C2358R.C2357id.subtitle);
        this.mOrder.setFavoriteName(favoriteName);
        if (title != null && subTitle != null) {
            OrderReceiptRecents.updateFavoriteName(this.mOrder, title, subTitle, getContext());
        }
    }

    private boolean checkFavorites(List<FavoriteItem> favorites) {
        Ensighten.evaluateEvent(this, "checkFavorites", new Object[]{favorites});
        for (FavoriteItem item : favorites) {
            if (isSimilarOrder(item)) {
                Object[] products = this.mOrder.getProducts().toArray();
                List<CustomerOrderProduct> favoriteProducts = item.getProducts();
                boolean match = true;
                for (int i = 0; i < products.length; i++) {
                    if (i >= favoriteProducts.size()) {
                        match = false;
                        break;
                    }
                    CustomerOrderProduct favorite = (CustomerOrderProduct) favoriteProducts.get(i);
                    OrderProduct product = products[i];
                    if (favorite.getProductCode().intValue() != Integer.parseInt(product.getProductCode()) || !favorite.getQuantity().equals(Integer.valueOf(product.getQuantity()))) {
                        match = false;
                        break;
                    }
                }
                if (match) {
                    this.mOrder.setFavoriteName(item.getName());
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isSimilarOrder(FavoriteItem item) {
        Ensighten.evaluateEvent(this, "isSimilarOrder", new Object[]{item});
        return item.getType() == FavoriteProductType.FAVORITE_PRODUCT_TYPE_ORDER && ListUtils.sameSize(item.getProducts(), this.mOrder.getProducts());
    }

    private void addToFavorites(final String favoriteName, final FavoriteInputViewHolder input) {
        Ensighten.evaluateEvent(this, "addToFavorites", new Object[]{favoriteName, input});
        this.mCustomerModule.addFavoriteProducts(this.mCustomerModule.getCurrentProfile(), (List) this.mOrder.getProducts(), favoriteName, false, new AsyncListener<Boolean>() {
            public void onResponse(Boolean response, AsyncToken token, AsyncException exception) {
                Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
                UIUtils.stopActivityIndicator();
                if (exception == null) {
                    input.hide();
                    OrderDetailsFragment.access$502(OrderDetailsFragment.this, true);
                    OrderDetailsFragment.access$1200(OrderDetailsFragment.this, favoriteName);
                } else {
                    AsyncException.report(exception);
                }
                OrderDetailsFragment.access$1300(OrderDetailsFragment.this);
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.menu.OrderDetailsFragment", "access$1400", new Object[]{OrderDetailsFragment.this}).removeAllViews();
                OrderDetailsFragment.access$200(OrderDetailsFragment.this, LayoutInflater.from(OrderDetailsFragment.this.getContext()));
            }
        });
    }

    private void deleteFromFavorites() {
        Ensighten.evaluateEvent(this, "deleteFromFavorites", null);
        if (this.mCustomerModule.needsFavoriteProductsRefresh()) {
            ServiceUtils.getSharedInstance().retrieveFavoriteProducts(new C35698());
            return;
        }
        List<FavoriteItem> favoriteItemList = new ArrayList();
        for (FavoriteItem item : this.mCustomerModule.getFavoriteProducts()) {
            if (isSimilarOrder(item)) {
                for (OrderProduct product : this.mOrder.getProducts()) {
                    for (CustomerOrderProduct favorite : item.getProducts()) {
                        if (favorite.getProductCode().intValue() == Integer.parseInt(product.getProductCode())) {
                            favoriteItemList.add(item);
                        }
                    }
                }
            }
        }
        if (!favoriteItemList.isEmpty()) {
            this.mCustomerModule.deleteFavoriteProducts(this.mCustomerModule.getCurrentProfile(), favoriteItemList, new C35709());
        }
    }

    private void orderAgain() {
        Ensighten.evaluateEvent(this, "orderAgain", null);
        AnalyticsUtils.trackOnClickEvent(getAnalyticsTitle(), "Order Again");
        final Order current = OrderingManager.getInstance().getCurrentOrder();
        if (current.getBasketCounter() + this.mOrder.getProducts().size() > ModuleManager.getSharedInstance().getMaxBasketQuantity()) {
            MCDAlertDialogBuilder.withContext(getNavigationActivity()).setMessage(getString(C2658R.string.too_many_products_in_basket)).setPositiveButton(getString(C2658R.string.f6083ok), new C355910()).create().show();
            DataLayerManager.getInstance().recordError("Too many products in the basket");
        } else if (!current.canAddProducts(this.mOrder)) {
            UIUtils.showInvalidDayPartsError(getActivity());
        } else if (OrderUtils.orderHasUnavailableProductsOrIsEmpty(this.mOrder)) {
            UIUtils.showGlobalAlertDialog(getActivity(), null, getString(C2658R.string.order_cannot_be_placed), null);
            DataLayerManager.getInstance().recordError("Must have items in basket before checking out");
        } else if (current.isEmpty()) {
            for (OrderProduct orderProduct : this.mOrder.getProducts()) {
                current.addProduct(orderProduct);
            }
            current.setIsDelivery(this.mOrder.isDelivery());
            if (getActivity() instanceof URLActionBarActivity) {
                ((URLActionBarActivity) getActivity()).startActivityForResult(OrderMethodSelectionActivity.class, 2);
            }
        } else {
            MCDAlertDialogBuilder.withContext(getActivity()).setTitle((int) C2658R.string.basket_will_be_cleared).setMessage((int) C2658R.string.cart_will_be_cleared).setPositiveButton(getString(C2658R.string.continue_button), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
                    current.clearOffers();
                    current.clearProducts();
                    for (OrderProduct orderProduct : Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.menu.OrderDetailsFragment", "access$100", new Object[]{OrderDetailsFragment.this}).getProducts()) {
                        current.addProduct(orderProduct);
                    }
                    current.setIsDelivery(Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.menu.OrderDetailsFragment", "access$100", new Object[]{OrderDetailsFragment.this}).isDelivery());
                    if (OrderDetailsFragment.this.getActivity() instanceof URLActionBarActivity) {
                        ((URLActionBarActivity) OrderDetailsFragment.this.getActivity()).startActivityForResult(OrderMethodSelectionActivity.class, 2);
                    }
                }
            }).setNegativeButton(getString(C2658R.string.button_cancel), null).create().show();
        }
    }
}
