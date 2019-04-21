package com.mcdonalds.app.ordering.preparepayment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.ordering.IntentFragment;
import com.mcdonalds.app.ordering.OrderingManager;
import com.mcdonalds.app.ordering.alert.AlertActivity;
import com.mcdonalds.app.ordering.alert.AlertFragment;
import com.mcdonalds.app.ordering.basket.BasketActivity;
import com.mcdonalds.app.ordering.basket.BasketFragment;
import com.mcdonalds.app.p043ui.URLNavigationFragment;
import com.mcdonalds.app.util.ConfigurationUtils;
import com.mcdonalds.app.util.ListUtils;
import com.mcdonalds.app.util.UIUtils.MCDAlertDialogBuilder;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.customer.CustomerModule;
import com.mcdonalds.sdk.modules.delivery.DeliveryModule;
import com.mcdonalds.sdk.modules.models.Order;
import com.mcdonalds.sdk.modules.models.OrderOffer;
import com.mcdonalds.sdk.modules.models.OrderResponse;
import com.mcdonalds.sdk.modules.models.PaymentCard;
import com.mcdonalds.sdk.modules.ordering.OrderingModule;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.mcdonalds.sdk.services.data.LocalDataManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public abstract class BasePreparePaymentFragment extends URLNavigationFragment implements OnClickListener, IntentFragment {
    protected static String SHOW_LARGE_ORDER_NOTIFICATION = "interface.showLargerOrderNotification";
    protected Button mContinueButton;
    protected CustomerModule mCustomerModule;
    protected DeliveryModule mDeliveryModule;
    private Order mOrder;
    protected OrderingModule mOrderingModule;
    private View mQrScanFtuView;
    private View mScanCancelButton;
    private View mScanContinueButton;

    /* renamed from: com.mcdonalds.app.ordering.preparepayment.BasePreparePaymentFragment$1 */
    class C36331 implements DialogInterface.OnClickListener {
        C36331() {
        }

        public void onClick(DialogInterface dialog, int which) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
            BasePreparePaymentFragment.access$000(BasePreparePaymentFragment.this);
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.preparepayment.BasePreparePaymentFragment$2 */
    class C36342 implements DialogInterface.OnClickListener {
        C36342() {
        }

        public void onClick(DialogInterface dialog, int which) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
            BasePreparePaymentFragment.this.getActivity().finish();
            BasePreparePaymentFragment.this.startActivity(BasketActivity.class);
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.preparepayment.BasePreparePaymentFragment$3 */
    class C36353 implements DialogInterface.OnClickListener {
        C36353() {
        }

        public void onClick(DialogInterface dialog, int which) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
            BasePreparePaymentFragment.this.getActivity().finish();
        }
    }

    public abstract void cashAsPayment();

    public abstract void initialize();

    public abstract void onContinue();

    public abstract void onScanContinue();

    public abstract void updatedPaymentCard(PaymentCard paymentCard);

    static /* synthetic */ void access$000(BasePreparePaymentFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.preparepayment.BasePreparePaymentFragment", "access$000", new Object[]{x0});
        x0.checkOrderErrors();
    }

    /* Access modifiers changed, original: protected */
    public String getAnalyticsTitle() {
        Ensighten.evaluateEvent(this, "getAnalyticsTitle", null);
        return getString(C2658R.string.analytics_screen_checkout);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mDeliveryModule = (DeliveryModule) ModuleManager.getModule(DeliveryModule.NAME);
        this.mCustomerModule = (CustomerModule) ModuleManager.getModule(CustomerModule.NAME);
        this.mOrderingModule = (OrderingModule) ModuleManager.getModule("ordering");
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initialize();
    }

    public void onNewIntent(Intent intent) {
        Ensighten.evaluateEvent(this, "onNewIntent", new Object[]{intent});
        initialize();
    }

    public void onClick(View view) {
        Ensighten.evaluateEvent(this, "onClick", new Object[]{view});
        if (view == this.mQrScanFtuView || view == this.mScanCancelButton) {
            markScanFtuAsSeen();
        }
        if (view == this.mScanContinueButton) {
            markScanFtuAsSeen();
            onScanContinue();
        }
        if (view == this.mContinueButton) {
            onContinue();
        }
    }

    /* Access modifiers changed, original: protected */
    public void setUpQrScanFirstTimeView(View parent) {
        Ensighten.evaluateEvent(this, "setUpQrScanFirstTimeView", new Object[]{parent});
        this.mQrScanFtuView = parent.findViewById(C2358R.C2357id.qr_scan_ftu_view);
        this.mScanCancelButton = parent.findViewById(C2358R.C2357id.scan_cancel_button);
        this.mScanContinueButton = parent.findViewById(C2358R.C2357id.scan_continue_button);
        this.mContinueButton = (Button) parent.findViewById(C2358R.C2357id.continue_button);
        this.mQrScanFtuView.setOnClickListener(this);
        this.mScanCancelButton.setOnClickListener(this);
        this.mScanContinueButton.setOnClickListener(this);
        this.mContinueButton.setOnClickListener(this);
    }

    /* Access modifiers changed, original: protected */
    public void checkOrder() {
        Ensighten.evaluateEvent(this, "checkOrder", null);
        this.mOrder = OrderingManager.getInstance().getCurrentOrder();
        if (isLargeOrder() && this.mOrder.isDelivery() && Configuration.getSharedInstance().getBooleanForKey("interface.showLargerOrderNotification")) {
            MCDAlertDialogBuilder.withContext(getActivity()).setTitle((int) C2658R.string.warning).setMessage((int) C2658R.string.large_order_review_order_notification).setNegativeButton((int) C2658R.string.button_review_order, new C36342()).setPositiveButton((int) C2658R.string.f6083ok, new C36331()).create().show();
        } else {
            checkOrderErrors();
        }
    }

    private boolean isLargeOrder() {
        Ensighten.evaluateEvent(this, "isLargeOrder", null);
        return OrderingManager.getInstance().getCurrentOrder().getTotalizeResult().getOrderView().isIsLargeOrder();
    }

    private void checkOrderErrors() {
        Ensighten.evaluateEvent(this, "checkOrderErrors", null);
        OrderingManager orderingManager = OrderingManager.getInstance();
        List<Integer> offerErrors = orderingManager.getCurrentOrder().getTotalizeResult().getPromotionsError();
        if (ListUtils.isEmpty(offerErrors)) {
            Order order = orderingManager.getCurrentOrder();
            for (OrderOffer orderOffer : orderingManager.getCurrentOrder().getOffers()) {
                String error_message = null;
                if (order.isDelivery() && !orderOffer.getOffer().isDeliveryOffer()) {
                    error_message = getString(C2658R.string.offer_unavailable_for_pod_short, getString(C2658R.string.delivery));
                } else if (!(order.isDelivery() || orderOffer.getOffer().isPickupOffer())) {
                    error_message = getString(C2658R.string.offer_unavailable_for_pod_short, getString(C2658R.string.pickup));
                }
                if (error_message != null) {
                    AsyncException.report(error_message);
                    getActivity().finish();
                }
            }
        } else {
            int offerError = ((Integer) offerErrors.get(0)).intValue() * -1;
            MCDAlertDialogBuilder.withContext(getActivity()).setTitle((int) C2658R.string.warning).setMessage(getResources().getString(getResources().getIdentifier(String.format(Locale.getDefault(), "ecp_error_%d", new Object[]{Integer.valueOf(offerError)}), "string", getActivity().getPackageName()))).setPositiveButton((int) C2658R.string.f6083ok, new C36353()).create().show();
        }
        int productError = orderingManager.getErrorCode();
        if (productError != 0) {
            String fragmentName = "";
            switch (productError) {
                case OrderResponse.PRODUCT_OUT_OF_STOCK_CODE /*-1036*/:
                    fragmentName = "check_out_items_out_of_stock";
                    break;
                case OrderResponse.PRODUCT_UNAVAILABLE_AT_RESTAURANT_CODE /*-1035*/:
                case OrderResponse.PRODUCT_UNAVAILABLE_CODE /*-1023*/:
                    fragmentName = "check_out_items_unavailable";
                    break;
            }
            orderingManager.setProductErrorCode(productError);
            if (!fragmentName.isEmpty()) {
                Bundle bundle = new Bundle();
                bundle.putInt(BasketFragment.PARAMETER_PRODUCT_ERROR_CODE, productError);
                bundle.putStringArrayList(BasketFragment.PARAMETER_PROBLEMATIC_PRODUCTS_CODES, (ArrayList) orderingManager.getProblematicProductsCodes());
                bundle.putBoolean(AlertFragment.PARAMETER_HIDE_POSITIVE, orderingManager.shouldHidePositive());
                startActivityForResult(AlertActivity.class, fragmentName, bundle, 37);
            }
        }
    }

    /* Access modifiers changed, original: protected */
    public boolean showFirstTimeQrScan() {
        Ensighten.evaluateEvent(this, "showFirstTimeQrScan", null);
        if (!LocalDataManager.getSharedInstance().isFirstTimeQrScan() || ConfigurationUtils.isInterimCheckinFlow()) {
            return false;
        }
        this.mQrScanFtuView.setVisibility(0);
        return true;
    }

    /* Access modifiers changed, original: protected */
    public void markScanFtuAsSeen() {
        Ensighten.evaluateEvent(this, "markScanFtuAsSeen", null);
        this.mQrScanFtuView.setVisibility(8);
        LocalDataManager.getSharedInstance().setFirstTimeQrScan(false);
    }
}
