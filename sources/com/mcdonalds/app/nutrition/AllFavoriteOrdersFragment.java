package com.mcdonalds.app.nutrition;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.analytics.datalayer.DataLayerManager;
import com.mcdonalds.app.analytics.datalayer.view.DataLayerClickListener;
import com.mcdonalds.app.ordering.OrderUtils;
import com.mcdonalds.app.ordering.OrderingManager;
import com.mcdonalds.app.ordering.menu.OrderDetailsFragment;
import com.mcdonalds.app.ordering.menu.OrderReceiptRecents;
import com.mcdonalds.app.ordering.methodselection.OrderMethodSelectionActivity;
import com.mcdonalds.app.p043ui.URLActionBarActivity;
import com.mcdonalds.app.p043ui.URLNavigationActivity;
import com.mcdonalds.app.p043ui.URLNavigationFragment;
import com.mcdonalds.app.util.AnalyticsUtils;
import com.mcdonalds.app.util.UIUtils;
import com.mcdonalds.app.util.UIUtils.MCDAlertDialogBuilder;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.modules.models.Order;
import com.mcdonalds.sdk.modules.models.OrderProduct;
import com.mcdonalds.sdk.modules.ordering.OrderManager;
import com.mcdonalds.sdk.services.data.DataPasser;
import java.util.List;

public class AllFavoriteOrdersFragment extends URLNavigationFragment {
    public static final String NAME = AllFavoriteOrdersFragment.class.getSimpleName().toLowerCase();
    private List<Order> mFavoriteOrders;
    private int mHeight;

    private class FavoriteOrderListAdapter extends BaseAdapter {
        private LayoutInflater mInflater;

        /* renamed from: com.mcdonalds.app.nutrition.AllFavoriteOrdersFragment$FavoriteOrderListAdapter$4 */
        class C32964 implements OnClickListener {
            C32964() {
            }

            public void onClick(DialogInterface dialog, int which) {
                Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
                dialog.dismiss();
            }
        }

        class ReceiptViewHolder {
            Button mOrderAgainBtn;
            LinearLayout mReceiptLayout;
            View mZigzagView;

            ReceiptViewHolder() {
            }
        }

        static /* synthetic */ void access$300(FavoriteOrderListAdapter x0, Order x1) {
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.nutrition.AllFavoriteOrdersFragment$FavoriteOrderListAdapter", "access$300", new Object[]{x0, x1});
            x0.startSingleReceiptActivity(x1);
        }

        static /* synthetic */ void access$400(FavoriteOrderListAdapter x0, int x1, Order x2) {
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.nutrition.AllFavoriteOrdersFragment$FavoriteOrderListAdapter", "access$400", new Object[]{x0, new Integer(x1), x2});
            x0.orderAgain(x1, x2);
        }

        static /* synthetic */ void access$500(FavoriteOrderListAdapter x0, Order x1, Order x2) {
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.nutrition.AllFavoriteOrdersFragment$FavoriteOrderListAdapter", "access$500", new Object[]{x0, x1, x2});
            x0.clearBasketAndAddNewProducts(x1, x2);
        }

        private FavoriteOrderListAdapter() {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            AllFavoriteOrdersFragment.this.getNavigationActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            AllFavoriteOrdersFragment.access$102(AllFavoriteOrdersFragment.this, displayMetrics.heightPixels);
            this.mInflater = LayoutInflater.from(AllFavoriteOrdersFragment.this.getNavigationActivity());
        }

        public int getCount() {
            Ensighten.evaluateEvent(this, "getCount", null);
            return Ensighten.evaluateEvent(null, "com.mcdonalds.app.nutrition.AllFavoriteOrdersFragment", "access$200", new Object[]{AllFavoriteOrdersFragment.this}).size();
        }

        public Object getItem(int position) {
            Ensighten.evaluateEvent(this, "getItem", new Object[]{new Integer(position)});
            return Ensighten.evaluateEvent(null, "com.mcdonalds.app.nutrition.AllFavoriteOrdersFragment", "access$200", new Object[]{AllFavoriteOrdersFragment.this}).get(position);
        }

        public long getItemId(int position) {
            Ensighten.evaluateEvent(this, "getItemId", new Object[]{new Integer(position)});
            return 0;
        }

        public View getView(final int position, View convertView, ViewGroup parent) {
            ReceiptViewHolder viewHolder;
            if (convertView == null) {
                convertView = this.mInflater.inflate(C2658R.layout.fragment_recents_order, parent, false);
                viewHolder = new ReceiptViewHolder();
                viewHolder.mOrderAgainBtn = (Button) convertView.findViewById(C2358R.C2357id.order_again_btn);
                viewHolder.mReceiptLayout = (LinearLayout) convertView.findViewById(C2358R.C2357id.order_container);
                LayoutParams vi_params = (LayoutParams) viewHolder.mReceiptLayout.getLayoutParams();
                vi_params.height = (int) (((double) Ensighten.evaluateEvent(null, "com.mcdonalds.app.nutrition.AllFavoriteOrdersFragment", "access$100", new Object[]{AllFavoriteOrdersFragment.this})) * 0.33d);
                vi_params.topMargin = UIUtils.dpAsPixels(AllFavoriteOrdersFragment.this.getNavigationActivity(), 10);
                viewHolder.mReceiptLayout.setLayoutParams(vi_params);
                viewHolder.mZigzagView = convertView.findViewById(C2358R.C2357id.zigzag_view);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ReceiptViewHolder) convertView.getTag();
            }
            DataLayerClickListener.setDataLayerTag(convertView, ReceiptViewHolder.class, position);
            final Order favoriteOrder = (Order) getItem(position);
            viewHolder.mReceiptLayout.removeAllViews();
            OrderReceiptRecents.configureOrderReceiptForDisplay(favoriteOrder, AllFavoriteOrdersFragment.this.getActivity(), viewHolder.mReceiptLayout, this.mInflater);
            convertView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
                    FavoriteOrderListAdapter.access$300(FavoriteOrderListAdapter.this, favoriteOrder);
                }
            });
            viewHolder.mOrderAgainBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
                    FavoriteOrderListAdapter.access$400(FavoriteOrderListAdapter.this, position, favoriteOrder);
                }
            });
            viewHolder.mReceiptLayout.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
                    FavoriteOrderListAdapter.access$300(FavoriteOrderListAdapter.this, favoriteOrder);
                }
            });
            Ensighten.getViewReturnValue(convertView, position);
            Ensighten.processView((Object) this, "getView");
            Ensighten.getViewReturnValue(null, -1);
            return convertView;
        }

        private void startSingleReceiptActivity(Order favoriteOrder) {
            Ensighten.evaluateEvent(this, "startSingleReceiptActivity", new Object[]{favoriteOrder});
            URLNavigationActivity activity = (URLNavigationActivity) AllFavoriteOrdersFragment.this.getActivity();
            Bundle bundle = new Bundle();
            bundle.putInt("order_data_passer_key", DataPasser.getInstance().putData(favoriteOrder));
            activity.startActivity(SingleReceiptActivity.class, OrderDetailsFragment.NAME, bundle);
        }

        private void orderAgain(int orderPosition, final Order favoriteOrder) {
            Ensighten.evaluateEvent(this, "orderAgain", new Object[]{new Integer(orderPosition), favoriteOrder});
            AnalyticsUtils.trackOnClickEvent(AllFavoriteOrdersFragment.this.getAnalyticsTitle(), "Order Again");
            final Order currentOrder = OrderingManager.getInstance().getCurrentOrder();
            if (currentOrder.getBasketCounter() + favoriteOrder.getProducts().size() >= OrderManager.getInstance().getMaxBasketQuantity()) {
                MCDAlertDialogBuilder.withContext(AllFavoriteOrdersFragment.this.getNavigationActivity()).setMessage(AllFavoriteOrdersFragment.this.getResources().getString(C2658R.string.too_many_products_in_basket)).setPositiveButton(AllFavoriteOrdersFragment.this.getResources().getString(C2658R.string.f6083ok), new C32964()).create().show();
                DataLayerManager.getInstance().recordError("Too many products in the basket");
            } else if (!currentOrder.canAddProducts(favoriteOrder)) {
                UIUtils.showInvalidDayPartsError(AllFavoriteOrdersFragment.this.getNavigationActivity());
            } else if (OrderUtils.orderHasUnavailableProductsOrIsEmpty(favoriteOrder)) {
                UIUtils.showGlobalAlertDialog(AllFavoriteOrdersFragment.this.getActivity(), null, AllFavoriteOrdersFragment.this.getString(C2658R.string.order_cannot_be_placed), null);
                DataLayerManager.getInstance().recordError("Must have items in basket before checking out");
            } else if (currentOrder.isEmpty()) {
                for (OrderProduct orderProduct : favoriteOrder.getProducts()) {
                    currentOrder.addProduct(orderProduct);
                }
                currentOrder.setIsDelivery(favoriteOrder.isDelivery());
                if (AllFavoriteOrdersFragment.this.getActivity() instanceof URLActionBarActivity) {
                    ((URLActionBarActivity) AllFavoriteOrdersFragment.this.getActivity()).startActivityForResult(OrderMethodSelectionActivity.class, 2);
                }
            } else {
                MCDAlertDialogBuilder.withContext(AllFavoriteOrdersFragment.this.getActivity()).setTitle((int) C2658R.string.basket_will_be_cleared).setMessage((int) C2658R.string.cart_will_be_cleared).setPositiveButton(AllFavoriteOrdersFragment.this.getString(C2658R.string.continue_button), new OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
                        FavoriteOrderListAdapter.access$500(FavoriteOrderListAdapter.this, favoriteOrder, currentOrder);
                    }
                }).setNegativeButton(AllFavoriteOrdersFragment.this.getString(C2658R.string.button_cancel), null).create().show();
            }
        }

        private void clearBasketAndAddNewProducts(Order favoriteOrder, Order currentOrder) {
            Ensighten.evaluateEvent(this, "clearBasketAndAddNewProducts", new Object[]{favoriteOrder, currentOrder});
            currentOrder.clearOffers();
            currentOrder.clearProducts();
            for (OrderProduct orderProduct : favoriteOrder.getProducts()) {
                currentOrder.addProduct(orderProduct);
            }
            currentOrder.setIsDelivery(favoriteOrder.isDelivery());
        }
    }

    static /* synthetic */ int access$102(AllFavoriteOrdersFragment x0, int x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.nutrition.AllFavoriteOrdersFragment", "access$102", new Object[]{x0, new Integer(x1)});
        x0.mHeight = x1;
        return x1;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() == null || getArguments().getParcelableArray("favoriteOrders") == null) {
            this.mFavoriteOrders = (List) DataPasser.getInstance().getData("favoriteOrders");
        } else {
            this.mFavoriteOrders = getArguments().getParcelableArrayList("favoriteOrders");
        }
    }

    /* Access modifiers changed, original: protected */
    public String getTitle() {
        Ensighten.evaluateEvent(this, "getTitle", null);
        return getResources().getString(C2658R.string.title_activity_all_favorites);
    }

    /* Access modifiers changed, original: protected */
    public String getAnalyticsTitle() {
        Ensighten.evaluateEvent(this, "getAnalyticsTitle", null);
        return getResources().getString(C2658R.string.analytics_screen_all_orders);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ListView rootView = (ListView) inflater.inflate(C2658R.layout.fragment_all_favorite_orders, container, false);
        rootView.setAdapter(new FavoriteOrderListAdapter());
        return rootView;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((AllFavoritesActivity) getActivity()).bringBasketToFront();
    }
}
