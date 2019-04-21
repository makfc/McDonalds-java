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
import com.mcdonalds.app.p043ui.URLNavigationFragment;
import com.mcdonalds.app.util.AnalyticsUtils;
import com.mcdonalds.app.util.FavoriteInputViewHolder;
import com.mcdonalds.app.util.UIUtils;
import com.mcdonalds.app.util.UIUtils.MCDAlertDialogBuilder;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.customer.CustomerModule;
import com.mcdonalds.sdk.modules.models.FavoriteItem;
import com.mcdonalds.sdk.modules.models.Order;
import com.mcdonalds.sdk.modules.models.OrderProduct;
import com.mcdonalds.sdk.modules.ordering.OrderManager;
import com.mcdonalds.sdk.services.data.DataPasser;
import java.util.ArrayList;
import java.util.List;

public class FavoriteOrderUpdateFragment extends URLNavigationFragment {
    public static final String NAME = FavoriteOrderUpdateFragment.class.getSimpleName();
    private Button favoritedOrderButton;
    private boolean isFavorited;
    private FavouriteOrderUpdateListener listener;
    private CustomerModule mCustomerModule;
    private Order mOrder;
    private LinearLayout orderReceiptContainer;
    private View rootView;

    /* renamed from: com.mcdonalds.app.ordering.menu.FavoriteOrderUpdateFragment$1 */
    class C35141 implements OnClickListener {
        C35141() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            FavoriteOrderUpdateFragment.access$000(FavoriteOrderUpdateFragment.this);
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.menu.FavoriteOrderUpdateFragment$4 */
    class C35204 implements AsyncListener<Boolean> {
        C35204() {
        }

        public void onResponse(Boolean response, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            if (exception != null) {
                FavoriteOrderUpdateFragment.access$400(FavoriteOrderUpdateFragment.this);
                FavoriteOrderUpdateFragment.access$102(FavoriteOrderUpdateFragment.this, true);
                return;
            }
            FavoriteOrderUpdateFragment.access$900(FavoriteOrderUpdateFragment.this);
            if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.menu.FavoriteOrderUpdateFragment", "access$700", new Object[]{FavoriteOrderUpdateFragment.this}) != null) {
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.menu.FavoriteOrderUpdateFragment", "access$700", new Object[]{FavoriteOrderUpdateFragment.this}).onOrderRemoved();
            }
            ((TextView) Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.menu.FavoriteOrderUpdateFragment", "access$600", new Object[]{FavoriteOrderUpdateFragment.this}).findViewById(2131820647)).setText("");
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.menu.FavoriteOrderUpdateFragment", "access$800", new Object[]{FavoriteOrderUpdateFragment.this}).setTag(Boolean.valueOf(false));
            FavoriteOrderUpdateFragment.access$102(FavoriteOrderUpdateFragment.this, false);
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.menu.FavoriteOrderUpdateFragment$5 */
    class C35215 implements DialogInterface.OnClickListener {
        C35215() {
        }

        public void onClick(DialogInterface dialog, int which) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
            dialog.dismiss();
        }
    }

    public interface FavouriteOrderUpdateListener {
        void onOrderRemoved();

        void onOrderRemovedAndAdded();
    }

    static /* synthetic */ void access$000(FavoriteOrderUpdateFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.menu.FavoriteOrderUpdateFragment", "access$000", new Object[]{x0});
        x0.orderAgain();
    }

    static /* synthetic */ boolean access$102(FavoriteOrderUpdateFragment x0, boolean x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.menu.FavoriteOrderUpdateFragment", "access$102", new Object[]{x0, new Boolean(x1)});
        x0.isFavorited = x1;
        return x1;
    }

    static /* synthetic */ void access$200(FavoriteOrderUpdateFragment x0, String x1, FavoriteInputViewHolder x2) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.menu.FavoriteOrderUpdateFragment", "access$200", new Object[]{x0, x1, x2});
        x0.addToFavorites(x1, x2);
    }

    static /* synthetic */ void access$300(FavoriteOrderUpdateFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.menu.FavoriteOrderUpdateFragment", "access$300", new Object[]{x0});
        x0.removeFavorited();
    }

    static /* synthetic */ void access$400(FavoriteOrderUpdateFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.menu.FavoriteOrderUpdateFragment", "access$400", new Object[]{x0});
        x0.setButtonFavored();
    }

    static /* synthetic */ void access$900(FavoriteOrderUpdateFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.menu.FavoriteOrderUpdateFragment", "access$900", new Object[]{x0});
        x0.setButtonFavoriteRemoved();
    }

    /* Access modifiers changed, original: protected */
    public String getAnalyticsTitle() {
        Ensighten.evaluateEvent(this, "getAnalyticsTitle", null);
        return getString(C2658R.string.analytics_screen_favorites);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments().containsKey("ORDER")) {
            this.mOrder = (Order) getArguments().getParcelable("ORDER");
        } else {
            this.mOrder = (Order) DataPasser.getInstance().getData("ORDER");
        }
        this.mCustomerModule = (CustomerModule) ModuleManager.getModule(CustomerModule.NAME);
        this.isFavorited = true;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.rootView = inflater.inflate(C2658R.layout.fragment_order_details, container, false);
        ((Button) this.rootView.findViewById(C2358R.C2357id.order_again_btn)).setOnClickListener(new C35141());
        this.favoritedOrderButton = (Button) this.rootView.findViewById(C2358R.C2357id.favorite_last_order);
        this.orderReceiptContainer = (LinearLayout) this.rootView.findViewById(C2358R.C2357id.order_container);
        init(inflater);
        ((TextView) this.rootView.findViewById(2131820647)).setText(this.mOrder.getFavoriteName());
        return this.rootView;
    }

    private void init(LayoutInflater inflater) {
        Ensighten.evaluateEvent(this, "init", new Object[]{inflater});
        OrderReceiptRecents.configureOrderReceiptForDisplay(this.mOrder, getActivity(), this.orderReceiptContainer, inflater);
        setUpAddToFavoritesButton(this.rootView);
    }

    private void setUpAddToFavoritesButton(final View rootView) {
        Ensighten.evaluateEvent(this, "setUpAddToFavoritesButton", new Object[]{rootView});
        if (this.isFavorited) {
            setButtonFavored();
        } else {
            setButtonFavoriteRemoved();
        }
        this.favoritedOrderButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
                if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.menu.FavoriteOrderUpdateFragment", "access$100", new Object[]{FavoriteOrderUpdateFragment.this})) {
                    FavoriteOrderUpdateFragment.access$300(FavoriteOrderUpdateFragment.this);
                    return;
                }
                final FavoriteInputViewHolder favoriteInputViewHolder = new FavoriteInputViewHolder(FavoriteOrderUpdateFragment.this.getNavigationActivity(), rootView);
                favoriteInputViewHolder.show();
                favoriteInputViewHolder.getSaveToFavoritesButton().setOnClickListener(new OnClickListener() {

                    /* renamed from: com.mcdonalds.app.ordering.menu.FavoriteOrderUpdateFragment$2$1$1 */
                    class C35151 implements DialogInterface.OnClickListener {
                        C35151() {
                        }

                        public void onClick(DialogInterface dialog, int which) {
                            Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
                        }
                    }

                    public void onClick(View v) {
                        Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
                        String favoriteName = String.valueOf(((TextView) rootView.findViewById(C2358R.C2357id.favorite_name_input)).getText());
                        if (TextUtils.isEmpty(favoriteName)) {
                            MCDAlertDialogBuilder.withContext(v.getContext()).setMessage(v.getContext().getString(C2658R.string.alert_error_empty_favorite_order_name_msg)).setPositiveButton(FavoriteOrderUpdateFragment.this.getResources().getString(C2658R.string.f6083ok), new C35151()).create().show();
                            return;
                        }
                        UIUtils.startActivityIndicator(FavoriteOrderUpdateFragment.this.getNavigationActivity(), FavoriteOrderUpdateFragment.this.getResources().getString(C2658R.string.saving) + " " + favoriteName);
                        FavoriteOrderUpdateFragment.access$200(FavoriteOrderUpdateFragment.this, favoriteName, favoriteInputViewHolder);
                    }
                });
            }
        });
    }

    private void setButtonFavored() {
        Ensighten.evaluateEvent(this, "setButtonFavored", null);
        this.favoritedOrderButton.setText(getResources().getString(C2658R.string.order_favorited));
        this.favoritedOrderButton.setBackgroundResource(C2358R.C2359drawable.bg_red_active);
        this.favoritedOrderButton.setTag(Boolean.valueOf(true));
        UIUtils.dismissKeyboard(getActivity(), this.favoritedOrderButton);
    }

    private void setButtonFavoriteRemoveOperationProcessing() {
        Ensighten.evaluateEvent(this, "setButtonFavoriteRemoveOperationProcessing", null);
        this.favoritedOrderButton.setText(getResources().getString(C2658R.string.order_favorited));
        this.favoritedOrderButton.setBackgroundResource(C2358R.C2359drawable.bg_red_pressed);
        this.favoritedOrderButton.setTag(Boolean.valueOf(false));
        UIUtils.dismissKeyboard(getActivity(), this.favoritedOrderButton);
    }

    private void setButtonFavoriteRemoved() {
        Ensighten.evaluateEvent(this, "setButtonFavoriteRemoved", null);
        this.favoritedOrderButton.setText(getResources().getString(C2658R.string.favorite_order));
        this.favoritedOrderButton.setBackgroundResource(C2358R.C2359drawable.button_dark_gray);
        this.favoritedOrderButton.setTag(Boolean.valueOf(false));
        UIUtils.dismissKeyboard(getActivity(), this.favoritedOrderButton);
    }

    private void addToFavorites(final String favoriteName, final FavoriteInputViewHolder favoriteInputViewHolder) {
        Ensighten.evaluateEvent(this, "addToFavorites", new Object[]{favoriteName, favoriteInputViewHolder});
        this.mCustomerModule.addFavoriteProducts(this.mCustomerModule.getCurrentProfile(), (List) this.mOrder.getProducts(), favoriteName, false, new AsyncListener<Boolean>() {

            /* renamed from: com.mcdonalds.app.ordering.menu.FavoriteOrderUpdateFragment$3$1 */
            class C35181 implements DialogInterface.OnClickListener {
                C35181() {
                }

                public void onClick(DialogInterface dialog, int which) {
                    Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
                    dialog.dismiss();
                }
            }

            public void onResponse(Boolean response, AsyncToken token, AsyncException exception) {
                Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
                if (exception == null) {
                    favoriteInputViewHolder.hide();
                    UIUtils.stopActivityIndicator();
                    FavoriteOrderUpdateFragment.access$400(FavoriteOrderUpdateFragment.this);
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.menu.FavoriteOrderUpdateFragment", "access$500", new Object[]{FavoriteOrderUpdateFragment.this}).setRecentName(favoriteName);
                    ((TextView) Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.menu.FavoriteOrderUpdateFragment", "access$600", new Object[]{FavoriteOrderUpdateFragment.this}).findViewById(2131820647)).setText(favoriteName);
                    if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.menu.FavoriteOrderUpdateFragment", "access$700", new Object[]{FavoriteOrderUpdateFragment.this}) != null) {
                        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.menu.FavoriteOrderUpdateFragment", "access$700", new Object[]{FavoriteOrderUpdateFragment.this}).onOrderRemovedAndAdded();
                        return;
                    }
                    return;
                }
                UIUtils.stopActivityIndicator();
                MCDAlertDialogBuilder.withContext(FavoriteOrderUpdateFragment.this.getNavigationActivity()).setMessage(exception.getMessage()).setPositiveButton((int) C2658R.string.f6083ok, new C35181()).create().show();
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.menu.FavoriteOrderUpdateFragment", "access$800", new Object[]{FavoriteOrderUpdateFragment.this}).setTag(Boolean.valueOf(false));
            }
        });
    }

    private void removeFavorited() {
        Ensighten.evaluateEvent(this, "removeFavorited", null);
        setButtonFavoriteRemoveOperationProcessing();
        List<FavoriteItem> favoriteItems = new ArrayList();
        FavoriteItem favoriteItem = new FavoriteItem();
        favoriteItem.setFavoriteId(this.mOrder.getFavoriteId());
        favoriteItems.add(favoriteItem);
        this.mCustomerModule.deleteFavoriteProducts(this.mCustomerModule.getCurrentProfile(), favoriteItems, new C35204());
    }

    private void orderAgain() {
        Ensighten.evaluateEvent(this, "orderAgain", null);
        AnalyticsUtils.trackOnClickEvent(getAnalyticsTitle(), "Order Again");
        Order currentOrder = OrderingManager.getInstance().getCurrentOrder();
        if (currentOrder.getBasketCounter() >= OrderManager.getInstance().getMaxBasketQuantity()) {
            MCDAlertDialogBuilder.withContext(getNavigationActivity()).setMessage(getResources().getString(C2658R.string.too_many_products_in_basket)).setPositiveButton(getResources().getString(C2658R.string.f6083ok), new C35215()).create().show();
        } else if (!currentOrder.canAddProducts(this.mOrder)) {
            UIUtils.showInvalidDayPartsError(getActivity());
        } else if (OrderUtils.orderHasUnavailableProductsOrIsEmpty(this.mOrder)) {
            UIUtils.showGlobalAlertDialog(getActivity(), null, getString(C2658R.string.order_cannot_be_placed), null);
            DataLayerManager.getInstance().recordError("Must have items in basket before checking out");
        } else {
            for (OrderProduct orderProduct : this.mOrder.getProducts()) {
                currentOrder.addProduct(orderProduct);
            }
        }
    }
}
