package com.mcdonalds.app.nutrition;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import com.ensighten.Ensighten;
import com.ensighten.model.activity.EnsightenActivityHandler;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.analytics.datalayer.DataLayerManager;
import com.mcdonalds.app.analytics.datalayer.view.DataLayerClickListener;
import com.mcdonalds.app.ordering.OrderUtils;
import com.mcdonalds.app.ordering.OrderingManager;
import com.mcdonalds.app.ordering.menu.OrderDetailsFragment;
import com.mcdonalds.app.ordering.menu.OrderReceiptRecents;
import com.mcdonalds.app.ordering.methodselection.OrderMethodSelectionActivity;
import com.mcdonalds.app.p043ui.URLBasketNavigationActivity;
import com.mcdonalds.app.p043ui.URLNavigationActivity;
import com.mcdonalds.app.util.UIUtils;
import com.mcdonalds.app.util.UIUtils.MCDAlertDialogBuilder;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.modules.models.Order;
import com.mcdonalds.sdk.modules.models.OrderProduct;
import com.mcdonalds.sdk.modules.ordering.OrderManager;
import com.mcdonalds.sdk.services.analytics.AnalyticType;
import com.mcdonalds.sdk.services.analytics.Analytics;
import com.mcdonalds.sdk.services.analytics.AnalyticsArgs.ArgBuilder;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.mcdonalds.sdk.services.data.DataPasser;
import com.newrelic.agent.android.api.p047v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.tracing.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;

@Instrumented
public class RecentsOrderFragment extends Fragment implements TraceFieldInterface {
    public Trace _nr_trace;
    private Order mOrder;

    /* renamed from: com.mcdonalds.app.nutrition.RecentsOrderFragment$1 */
    class C33131 implements OnClickListener {
        C33131() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            RecentsOrderFragment.access$000(RecentsOrderFragment.this);
        }
    }

    /* renamed from: com.mcdonalds.app.nutrition.RecentsOrderFragment$2 */
    class C33142 implements OnClickListener {
        C33142() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            RecentsOrderFragment.this.orderAgainTapped(Ensighten.evaluateEvent(null, "com.mcdonalds.app.nutrition.RecentsOrderFragment", "access$100", new Object[]{RecentsOrderFragment.this}));
        }
    }

    /* renamed from: com.mcdonalds.app.nutrition.RecentsOrderFragment$3 */
    class C33153 implements DialogInterface.OnClickListener {
        C33153() {
        }

        public void onClick(DialogInterface dialog, int which) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
            dialog.dismiss();
        }
    }

    public void onActivityCreated(Bundle bundle) {
        EnsightenActivityHandler.onLifecycleMethod(this, "onActivityCreated", new Object[]{bundle});
        super.onActivityCreated(bundle);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        EnsightenActivityHandler.onLifecycleMethod(this, "onActivityResult", new Object[]{new Integer(i), new Integer(i2), intent});
        super.onActivityResult(i, i2, intent);
    }

    public void onAttach(Activity activity) {
        EnsightenActivityHandler.onLifecycleMethod(this, "onAttach", new Object[]{activity});
        super.onAttach(activity);
    }

    public void onCreate(Bundle bundle) {
        TraceMachine.startTracing("RecentsOrderFragment");
        try {
            TraceMachine.enterMethod(this._nr_trace, "RecentsOrderFragment#onCreate", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "RecentsOrderFragment#onCreate", null);
            }
        }
        EnsightenActivityHandler.onLifecycleMethod(this, "onCreate", new Object[]{bundle});
        super.onCreate(bundle);
        Ensighten.processView((Object) this, "onCreate");
        TraceMachine.exitMethod();
    }

    public void onDestroy() {
        EnsightenActivityHandler.onLifecycleMethod(this, "onDestroy", null);
        super.onDestroy();
    }

    public void onDestroyView() {
        EnsightenActivityHandler.onLifecycleMethod(this, "onDestroyView", null);
        super.onDestroyView();
    }

    public void onDetach() {
        EnsightenActivityHandler.onLifecycleMethod(this, "onDetach", null);
        super.onDetach();
    }

    public void onPause() {
        EnsightenActivityHandler.onLifecycleMethod(this, "onPause", null);
        super.onPause();
        Ensighten.processView((Object) this, "onPause");
    }

    public void onResume() {
        EnsightenActivityHandler.onLifecycleMethod(this, "onResume", null);
        super.onResume();
        Ensighten.processView((Object) this, "onResume");
    }

    public void onStart() {
        ApplicationStateMonitor.getInstance().activityStarted();
        EnsightenActivityHandler.onLifecycleMethod(this, "onStart", null);
        super.onStart();
        Ensighten.processView((Object) this, "onStart");
    }

    public void onStop() {
        ApplicationStateMonitor.getInstance().activityStopped();
        EnsightenActivityHandler.onLifecycleMethod(this, "onStop", null);
        super.onStop();
    }

    public void onViewStateRestored(Bundle bundle) {
        EnsightenActivityHandler.onLifecycleMethod(this, "onViewStateRestored", new Object[]{bundle});
        super.onViewStateRestored(bundle);
        Ensighten.processView((Object) this, "onViewStateRestored");
    }

    static /* synthetic */ void access$000(RecentsOrderFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.nutrition.RecentsOrderFragment", "access$000", new Object[]{x0});
        x0.startSingleReceiptActivity();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        try {
            TraceMachine.enterMethod(this._nr_trace, "RecentsOrderFragment#onCreateView", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "RecentsOrderFragment#onCreateView", null);
            }
        }
        EnsightenActivityHandler.onLifecycleMethod(this, "onCreateView", new Object[]{layoutInflater, viewGroup, bundle});
        View rootView = layoutInflater.inflate(C2658R.layout.fragment_recents_order, viewGroup, false);
        View orderReceiptContainer = (LinearLayout) rootView.findViewById(C2358R.C2357id.order_container);
        if (this.mOrder != null) {
            OrderReceiptRecents.configureOrderReceiptForDisplay(this.mOrder, getActivity(), orderReceiptContainer, layoutInflater);
        } else {
            orderReceiptContainer.setVisibility(8);
        }
        orderReceiptContainer.setOnClickListener(new DataLayerClickListener(new C33131()));
        DataLayerClickListener.setDataLayerTag(orderReceiptContainer, "OrderPressed");
        ((Button) rootView.findViewById(C2358R.C2357id.order_again_btn)).setOnClickListener(new C33142());
        if (Configuration.getSharedInstance().hasKey("interface.orderingDisclaimerInfo")) {
            UIUtils.addDisclaimerTextView((ViewGroup) rootView.findViewById(C2358R.C2357id.warnings_container), getContext(), "menuGridView");
        }
        TraceMachine.exitMethod();
        return rootView;
    }

    private void startSingleReceiptActivity() {
        Ensighten.evaluateEvent(this, "startSingleReceiptActivity", null);
        URLNavigationActivity activity = (URLNavigationActivity) getActivity();
        Bundle bundle = new Bundle();
        bundle.putInt("order_data_passer_key", DataPasser.getInstance().putData(this.mOrder));
        activity.startActivity(SingleReceiptActivity.class, OrderDetailsFragment.NAME, bundle);
    }

    public void setOrder(Order order) {
        Ensighten.evaluateEvent(this, "setOrder", new Object[]{order});
        this.mOrder = order;
    }

    public void orderAgainTapped(final Order order) {
        Ensighten.evaluateEvent(this, "orderAgainTapped", new Object[]{order});
        Analytics.track(AnalyticType.Event, new ArgBuilder().setCategory("/order-detail").setAction("On click").setLabel("Order Again").build());
        final Order currentOrder = OrderingManager.getInstance().getCurrentOrder();
        currentOrder.setNeedsUpdatedRecipes(true);
        if (currentOrder.getItemsCount() >= OrderManager.getInstance().getMaxBasketQuantity()) {
            MCDAlertDialogBuilder.withContext(getContext()).setMessage(getResources().getString(C2658R.string.too_many_products_in_basket)).setPositiveButton(getResources().getString(C2658R.string.f6083ok), new C33153()).create().show();
            DataLayerManager.getInstance().recordError("Too many products in the basket");
        } else if (!currentOrder.canAddProducts(order)) {
            UIUtils.showInvalidDayPartsError(getContext());
        } else if (OrderUtils.orderHasUnavailableProductsOrIsEmpty(order)) {
            UIUtils.showGlobalAlertDialog(getActivity(), null, getString(C2658R.string.order_cannot_be_placed), null);
            DataLayerManager.getInstance().recordError("Must have items in basket before checking out");
        } else if (currentOrder.isEmpty()) {
            for (OrderProduct productToInsert : order.getProducts()) {
                currentOrder.addProduct(productToInsert);
            }
            currentOrder.setIsDelivery(order.isDelivery());
            if (getActivity() instanceof URLBasketNavigationActivity) {
                ((URLBasketNavigationActivity) getActivity()).updateBasketBadge();
                ((URLBasketNavigationActivity) getActivity()).startActivityForResult(OrderMethodSelectionActivity.class, 2);
            }
        } else {
            MCDAlertDialogBuilder.withContext(getActivity()).setTitle((int) C2658R.string.basket_will_be_cleared).setMessage((int) C2658R.string.cart_will_be_cleared).setPositiveButton(getString(C2658R.string.continue_button), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
                    currentOrder.clearOffers();
                    currentOrder.clearProducts();
                    for (OrderProduct productToInsert : order.getProducts()) {
                        currentOrder.addProduct(productToInsert);
                    }
                    currentOrder.setIsDelivery(order.isDelivery());
                    if (RecentsOrderFragment.this.getActivity() instanceof URLBasketNavigationActivity) {
                        ((URLBasketNavigationActivity) RecentsOrderFragment.this.getActivity()).updateBasketBadge();
                        ((URLBasketNavigationActivity) RecentsOrderFragment.this.getActivity()).startActivityForResult(OrderMethodSelectionActivity.class, 2);
                    }
                }
            }).setNegativeButton(getString(C2658R.string.button_cancel), null).create().show();
        }
    }
}
