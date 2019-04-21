package com.mcdonalds.app.ordering.menu;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.analytics.datalayer.DataLayerManager;
import com.mcdonalds.app.analytics.datalayer.view.DataLayerClickListener;
import com.mcdonalds.app.nutrition.SingleReceiptActivity;
import com.mcdonalds.app.ordering.OrderUtils;
import com.mcdonalds.app.ordering.OrderingManager;
import com.mcdonalds.app.ordering.methodselection.OrderMethodSelectionActivity;
import com.mcdonalds.app.p043ui.URLNavigationActivity;
import com.mcdonalds.app.util.AnalyticsUtils;
import com.mcdonalds.app.util.UIUtils;
import com.mcdonalds.app.util.UIUtils.MCDAlertDialogBuilder;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.modules.models.Order;
import com.mcdonalds.sdk.modules.models.OrderProduct;
import com.mcdonalds.sdk.modules.ordering.OrderManager;
import com.mcdonalds.sdk.modules.ordering.OrderingModule;
import com.mcdonalds.sdk.services.data.DataPasser;
import java.util.List;

public class ReceiptListAdapter extends BaseAdapter {
    Context mContext;
    OrderingModule mOrderingModule;
    List<Order> mOrders;

    private class ViewHolder {
        Button order_again_btn;
        LinearLayout receipt_layout;
        View zigzag_view;

        private ViewHolder() {
        }

        /* synthetic */ ViewHolder(ReceiptListAdapter x0, C35711 x1) {
            this();
        }
    }

    public ReceiptListAdapter(List<Order> orders, OrderingModule orderingModule, Context context) {
        this.mOrders = orders;
        this.mOrderingModule = orderingModule;
        this.mContext = context;
    }

    public int getCount() {
        Ensighten.evaluateEvent(this, "getCount", null);
        return this.mOrders.size();
    }

    public Object getItem(int position) {
        Ensighten.evaluateEvent(this, "getItem", new Object[]{new Integer(position)});
        return this.mOrders.get(position);
    }

    public long getItemId(int position) {
        Ensighten.evaluateEvent(this, "getItemId", new Object[]{new Integer(position)});
        return (long) this.mOrders.indexOf(getItem(position));
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        LayoutInflater inflater = ((URLNavigationActivity) this.mContext).getLayoutInflater();
        if (convertView == null) {
            convertView = inflater.inflate(C2658R.layout.fragment_recents_order, parent, false);
            holder = new ViewHolder(this, null);
            holder.order_again_btn = (Button) convertView.findViewById(C2358R.C2357id.order_again_btn);
            holder.receipt_layout = (LinearLayout) convertView.findViewById(C2358R.C2357id.order_container);
            holder.receipt_layout.setMinimumHeight(UIUtils.dpAsPixels(this.mContext, 260));
            holder.zigzag_view = convertView.findViewById(C2358R.C2357id.zigzag_view);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        DataLayerClickListener.setDataLayerTag(holder.receipt_layout, "OrderPressed", position);
        final Order order = (Order) this.mOrders.get(position);
        holder.receipt_layout.removeAllViews();
        OrderReceiptRecents.configureOrderReceiptForDisplay(order, this.mContext, holder.receipt_layout, inflater);
        holder.receipt_layout.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
                URLNavigationActivity activity = ReceiptListAdapter.this.mContext;
                Bundle bundle = new Bundle();
                bundle.putInt("order_data_passer_key", DataPasser.getInstance().putData(order));
                activity.startActivity(SingleReceiptActivity.class, OrderDetailsFragment.NAME, bundle);
            }
        });
        holder.order_again_btn.setOnClickListener(new OnClickListener() {

            /* renamed from: com.mcdonalds.app.ordering.menu.ReceiptListAdapter$2$1 */
            class C35721 implements DialogInterface.OnClickListener {
                C35721() {
                }

                public void onClick(DialogInterface dialog, int which) {
                    Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
                    dialog.dismiss();
                }
            }

            public void onClick(View v) {
                Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
                AnalyticsUtils.trackOnClickEvent("/order-detail", "Order Again");
                final Order currentOrder = OrderingManager.getInstance().getCurrentOrder();
                if (currentOrder.getBasketCounter() >= OrderManager.getInstance().getMaxBasketQuantity()) {
                    MCDAlertDialogBuilder.withContext(ReceiptListAdapter.this.mContext).setMessage(ReceiptListAdapter.this.mContext.getString(C2658R.string.too_many_products_in_basket)).setPositiveButton(ReceiptListAdapter.this.mContext.getString(C2658R.string.f6083ok), new C35721()).create().show();
                } else if (!currentOrder.canAddProducts(order)) {
                    UIUtils.showInvalidDayPartsError(ReceiptListAdapter.this.mContext);
                } else if (OrderUtils.orderHasUnavailableProductsOrIsEmpty(order)) {
                    UIUtils.showGlobalAlertDialog(ReceiptListAdapter.this.mContext, null, ReceiptListAdapter.this.mContext.getString(C2658R.string.order_cannot_be_placed), null);
                    DataLayerManager.getInstance().recordError("Must have items in basket before checking out");
                } else {
                    MCDAlertDialogBuilder.withContext(ReceiptListAdapter.this.mContext).setTitle((int) C2658R.string.basket_will_be_cleared).setMessage((int) C2658R.string.cart_will_be_cleared).setPositiveButton(ReceiptListAdapter.this.mContext.getString(C2658R.string.continue_button), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
                            currentOrder.clearOffers();
                            currentOrder.clearProducts();
                            for (OrderProduct productToInsert : order.getProducts()) {
                                currentOrder.addProduct(productToInsert);
                            }
                            currentOrder.setIsDelivery(order.isDelivery());
                            if (ReceiptListAdapter.this.mContext instanceof URLNavigationActivity) {
                                ((URLNavigationActivity) ReceiptListAdapter.this.mContext).startActivity(OrderMethodSelectionActivity.class);
                                ((URLNavigationActivity) ReceiptListAdapter.this.mContext).finish();
                            }
                        }
                    }).setNegativeButton(ReceiptListAdapter.this.mContext.getString(C2658R.string.button_cancel), null).create().show();
                }
            }
        });
        Ensighten.getViewReturnValue(convertView, position);
        Ensighten.processView((Object) this, "getView");
        Ensighten.getViewReturnValue(null, -1);
        return convertView;
    }
}
