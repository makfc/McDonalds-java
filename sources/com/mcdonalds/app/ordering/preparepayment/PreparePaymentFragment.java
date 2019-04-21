package com.mcdonalds.app.ordering.preparepayment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.p000v4.content.ContextCompat;
import android.text.Html;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.analytics.datalayer.DataLayerManager;
import com.mcdonalds.app.customer.SignInActivity;
import com.mcdonalds.app.ordering.OrderUtils;
import com.mcdonalds.app.ordering.OrderingManager;
import com.mcdonalds.app.ordering.ProductUtils;
import com.mcdonalds.app.ordering.alert.AlertActivity;
import com.mcdonalds.app.ordering.alert.AlertFragment;
import com.mcdonalds.app.ordering.basket.BasketFragment;
import com.mcdonalds.app.ordering.checkin.OrderCheckinActivity;
import com.mcdonalds.app.ordering.instorepickup.ChoosePickUpActivity;
import com.mcdonalds.app.ordering.menu.MenuActivity;
import com.mcdonalds.app.ordering.pickupmethod.PickupMethodActivity;
import com.mcdonalds.app.ordering.pickupmethod.PickupMethodFragment;
import com.mcdonalds.app.ordering.start.DeliveryTimeHolder;
import com.mcdonalds.app.ordering.start.PickupLocationHolder;
import com.mcdonalds.app.ordering.utils.PODUtils;
import com.mcdonalds.app.p043ui.URLNavigationActivity;
import com.mcdonalds.app.util.AnalyticsUtils;
import com.mcdonalds.app.util.AppUtils;
import com.mcdonalds.app.util.ConfigurationUtils;
import com.mcdonalds.app.util.ListUtils;
import com.mcdonalds.app.util.UIUtils;
import com.mcdonalds.app.util.UIUtils.MCDAlertDialogBuilder;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.modules.models.CustomerAddress;
import com.mcdonalds.sdk.modules.models.Order;
import com.mcdonalds.sdk.modules.models.OrderPayment;
import com.mcdonalds.sdk.modules.models.OrderProduct;
import com.mcdonalds.sdk.modules.models.OrderResponse;
import com.mcdonalds.sdk.modules.models.PaymentCard;
import com.mcdonalds.sdk.modules.models.PaymentMethod;
import com.mcdonalds.sdk.modules.models.PaymentMethod.PaymentMode;
import com.mcdonalds.sdk.modules.models.StoreCapabilties;
import com.mcdonalds.sdk.modules.models.StoreCapabilties.StoreCapability;
import com.mcdonalds.sdk.modules.ordering.OrderManager;
import com.mcdonalds.sdk.modules.storelocator.Store;
import com.mcdonalds.sdk.services.analytics.AnalyticType;
import com.mcdonalds.sdk.services.analytics.Analytics;
import com.mcdonalds.sdk.services.analytics.AnalyticsArgs.ArgBuilder;
import com.mcdonalds.sdk.services.analytics.JiceArgs;
import com.mcdonalds.sdk.services.configuration.Configuration;
import java.text.NumberFormat;
import java.util.LinkedHashSet;
import java.util.List;

public class PreparePaymentFragment extends BasePreparePaymentFragment implements OnClickListener {
    public final String DO_NOT_SHOW_TAX_KEY = "modules.ordering.doNotShowTaxWithTotal";
    private TextView mBagCharge;
    private View mBagChargeContainer;
    private Boolean mCashPayment = Boolean.valueOf(false);
    private TextView mChosePaymentLabel;
    private EditText mCompanyName;
    private View mContainerTotals;
    private Double mDeliveryFee;
    private DeliveryTimeHolder mDeliveryTimeHolder;
    private TextView mDiscount;
    private View mDiscountContainer;
    private TextView mEnergyTotal;
    private boolean mInvoiceChecked = false;
    private boolean mIsComingFromBagCharge;
    private View mNoPaymentRequiredLabel;
    private Order mOrder;
    private TextView mOrderTotal;
    private TextView mPayWithLabel;
    private View mPaymentButton;
    private Integer mPaymentMethodId;
    private LinkedHashSet<PaymentMethod> mPaymentTypes;
    private PickupLocationHolder mPickupLocationHolder;
    private TextView mPodWarning;
    private View mProgress;
    private PaymentCard mSelectedCard;
    private TextView mTax;
    private TextView mTimeRestrictionWarning;
    private TextView mTotal;

    /* renamed from: com.mcdonalds.app.ordering.preparepayment.PreparePaymentFragment$3 */
    class C36483 implements AsyncListener<List<PaymentMethod>> {
        C36483() {
        }

        public void onResponse(List<PaymentMethod> response, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            if (response != null) {
                boolean customerPaymentMethodIdUpdated = false;
                int size = response.size();
                for (int i = 0; i < size; i++) {
                    PaymentMethod paymentMethod = (PaymentMethod) response.get(i);
                    if (paymentMethod.getPaymentMode() == PaymentMode.Cash && !customerPaymentMethodIdUpdated) {
                        customerPaymentMethodIdUpdated = true;
                        PreparePaymentFragment.access$202(PreparePaymentFragment.this, paymentMethod.getID());
                    }
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.preparepayment.PreparePaymentFragment", "access$300", new Object[]{PreparePaymentFragment.this}).add(paymentMethod);
                }
            }
            UIUtils.stopActivityIndicator();
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.preparepayment.PreparePaymentFragment$5 */
    class C36515 implements AsyncListener<Boolean> {

        /* renamed from: com.mcdonalds.app.ordering.preparepayment.PreparePaymentFragment$5$1 */
        class C36501 implements DialogInterface.OnClickListener {
            C36501() {
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                Ensighten.evaluateEvent(this, "onClick", new Object[]{dialogInterface, new Integer(i)});
                dialogInterface.dismiss();
                PreparePaymentFragment.this.getNavigationActivity().finish();
            }
        }

        C36515() {
        }

        public void onResponse(Boolean response, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            if (PreparePaymentFragment.this.getNavigationActivity() != null) {
                if (exception == null) {
                    PreparePaymentFragment.this.updateTotals();
                    PreparePaymentFragment.this.checkOrder();
                } else if (exception.getErrorCode() == OrderResponse.PRODUCT_UNAVAILABLE_AT_RESTAURANT_CODE) {
                    PreparePaymentFragment.access$400(PreparePaymentFragment.this, exception.getErrorCode());
                } else if (exception.getErrorCode() == OrderResponse.ZERO_OR_NEGAVTIVE_PRICE_ERROR_CODE) {
                    MCDAlertDialogBuilder.withContext(PreparePaymentFragment.this.getContext()).setTitle((int) C2658R.string.error_title).setMessage((int) C2658R.string.ecp_error_1606).setNeutralButton((int) C2658R.string.f6083ok, new C36501()).create().show();
                    DataLayerManager.getInstance().recordError("Zero or negative price");
                } else {
                    AsyncException.report(exception);
                    if (response.booleanValue()) {
                        PreparePaymentFragment.this.getNavigationActivity().finish();
                    }
                }
            }
            UIUtils.stopActivityIndicator();
        }
    }

    static /* synthetic */ Double access$002(PreparePaymentFragment x0, Double x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.preparepayment.PreparePaymentFragment", "access$002", new Object[]{x0, x1});
        x0.mDeliveryFee = x1;
        return x1;
    }

    static /* synthetic */ boolean access$102(PreparePaymentFragment x0, boolean x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.preparepayment.PreparePaymentFragment", "access$102", new Object[]{x0, new Boolean(x1)});
        x0.mInvoiceChecked = x1;
        return x1;
    }

    static /* synthetic */ Integer access$202(PreparePaymentFragment x0, Integer x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.preparepayment.PreparePaymentFragment", "access$202", new Object[]{x0, x1});
        x0.mPaymentMethodId = x1;
        return x1;
    }

    static /* synthetic */ void access$400(PreparePaymentFragment x0, int x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.preparepayment.PreparePaymentFragment", "access$400", new Object[]{x0, new Integer(x1)});
        x0.showOutofStockAlert(x1);
    }

    static /* synthetic */ void access$500(PreparePaymentFragment x0, CustomerAddress x1, String x2) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.preparepayment.PreparePaymentFragment", "access$500", new Object[]{x0, x1, x2});
        x0.setDeliveryHeader(x1, x2);
    }

    /* Access modifiers changed, original: protected */
    public String getAnalyticsTitle() {
        Ensighten.evaluateEvent(this, "getAnalyticsTitle", null);
        return getString(C2658R.string.analytics_screen_checkout);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mPaymentTypes = new LinkedHashSet();
        this.mOrder = OrderingManager.getInstance().getCurrentOrder();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        boolean z;
        final View view = inflater.inflate(C2658R.layout.fragment_checkout, container, false);
        this.mContainerTotals = view.findViewById(C2358R.C2357id.container_totals);
        this.mProgress = view.findViewById(C2358R.C2357id.progress);
        this.mPickupLocationHolder = new PickupLocationHolder(view.findViewById(C2358R.C2357id.pickup_view));
        this.mPickupLocationHolder.getDisclosureIcon().setVisibility(8);
        this.mDeliveryTimeHolder = new DeliveryTimeHolder(view.findViewById(C2358R.C2357id.delivery_view));
        this.mDeliveryTimeHolder.getDisclosureIcon().setVisibility(8);
        this.mDeliveryTimeHolder.getDeliveryTimeView().setOnClickListener(this);
        this.mTimeRestrictionWarning = (TextView) view.findViewById(C2358R.C2357id.time_restriction_warning);
        LinearLayout mDeliveryRow = (LinearLayout) view.findViewById(C2358R.C2357id.delivery_row);
        if (OrderingManager.getInstance().getCurrentOrder().isDelivery()) {
            mDeliveryRow.setVisibility(0);
            this.mDeliveryModule.lookupDeliveryCharge(OrderingManager.getInstance().getCurrentOrder(), new AsyncListener<OrderResponse>() {
                public void onResponse(OrderResponse response, AsyncToken token, AsyncException exception) {
                    Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
                    if (exception == null && response != null) {
                        ((TextView) view.findViewById(C2358R.C2357id.delivery_fee_value)).setText(UIUtils.getLocalizedCurrencyFormatter().format(response.getDeliveryFee() == null ? 0.0d : response.getDeliveryFee().doubleValue()));
                        PreparePaymentFragment.access$002(PreparePaymentFragment.this, response.getDeliveryFee());
                    }
                }
            });
        }
        this.mPaymentButton = view.findViewById(C2358R.C2357id.payment_container);
        this.mPaymentButton.setOnClickListener(this);
        this.mPayWithLabel = (TextView) view.findViewById(C2358R.C2357id.pay_with_label);
        this.mChosePaymentLabel = (TextView) view.findViewById(C2358R.C2357id.choose_payment_label);
        this.mNoPaymentRequiredLabel = view.findViewById(C2358R.C2357id.no_payment_required_label);
        this.mOrder = OrderManager.getInstance().getCurrentOrder();
        String paymentMethodDisplayName = this.mOrder.getPaymentMethodDisplayName();
        if (paymentMethodDisplayName != null) {
            this.mChosePaymentLabel.setText(paymentMethodDisplayName);
        }
        this.mOrderTotal = (TextView) view.findViewById(C2358R.C2357id.order_total_value);
        this.mBagCharge = (TextView) view.findViewById(C2358R.C2357id.bag_charge_value);
        this.mDiscount = (TextView) view.findViewById(C2358R.C2357id.discount_value);
        if (!Configuration.getSharedInstance().getBooleanForKey("modules.ordering.doNotShowTaxWithTotal", false)) {
            this.mTax = (TextView) view.findViewById(C2358R.C2357id.tax_value);
            ((View) this.mTax.getParent()).setVisibility(0);
        }
        this.mTotal = (TextView) view.findViewById(C2358R.C2357id.total_value);
        this.mEnergyTotal = (TextView) view.findViewById(C2358R.C2357id.total_energy);
        this.mBagChargeContainer = view.findViewById(C2358R.C2357id.bag_charge_container);
        this.mDiscountContainer = view.findViewById(C2358R.C2357id.discount_container);
        setUpQrScanFirstTimeView(view);
        Bundle extras = getActivity().getIntent().getExtras();
        if (extras == null || !extras.getBoolean("FROM_BAG_CHARGE")) {
            z = false;
        } else {
            z = true;
        }
        this.mIsComingFromBagCharge = z;
        OrderManager manager = OrderManager.getInstance();
        if (this.mIsComingFromBagCharge) {
            this.mContinueButton.setText(C2658R.string.checkout_place_order);
        } else if (manager.getCurrentOrder().isDelivery()) {
            this.mContinueButton.setText(C2658R.string.submit_order);
        } else {
            this.mContinueButton.setText(C2658R.string.checkin_tutorial_continue_button);
        }
        View invoiceContainer = view.findViewById(C2358R.C2357id.invoice_container);
        if (Configuration.getSharedInstance().getBooleanForKey("modules.ordering.requestTaxInvoice") && OrderingManager.getInstance().getCurrentOrder().isDelivery()) {
            invoiceContainer.setVisibility(0);
            ((Switch) invoiceContainer.findViewById(C2358R.C2357id.invoiceSwitch)).setOnCheckedChangeListener(new OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    int i = 0;
                    Ensighten.evaluateEvent(this, "onCheckedChanged", new Object[]{buttonView, new Boolean(isChecked)});
                    PreparePaymentFragment.access$102(PreparePaymentFragment.this, isChecked);
                    View findViewById = view.findViewById(C2358R.C2357id.company_container);
                    if (!isChecked) {
                        i = 8;
                    }
                    findViewById.setVisibility(i);
                }
            });
        }
        this.mCompanyName = (EditText) view.findViewById(C2358R.C2357id.company_name);
        this.mPodWarning = (TextView) view.findViewById(C2358R.C2357id.unavailable_pods);
        refreshStoreInfo();
        return view;
    }

    public void onResume() {
        super.onResume();
        if (this.mCashPayment.booleanValue()) {
            TextView textView = (TextView) this.mPaymentButton.findViewById(C2358R.C2357id.choose_payment_label);
            String cashLabel = getResources().getString(C2658R.string.cash);
            textView.setText(cashLabel);
            this.mOrder.setPaymentMethodDisplayName(cashLabel);
        } else if (this.mSelectedCard != null) {
            updatedPaymentCard(this.mSelectedCard);
        } else {
            List<PaymentCard> cardItems = this.mCustomerModule.getCurrentProfile().getCardItems();
            int size = cardItems.size();
            for (int i = 0; i < size; i++) {
                PaymentCard cardItem = (PaymentCard) cardItems.get(i);
                if (cardItem.isPreferred().booleanValue()) {
                    this.mSelectedCard = cardItem;
                    updatedPaymentCard(this.mSelectedCard);
                }
            }
        }
    }

    public void onNewIntent(Intent intent) {
        Ensighten.evaluateEvent(this, "onNewIntent", new Object[]{intent});
        Bundle extras = intent.getExtras();
        if (extras != null && extras.getBoolean("FROM_BAG_CHARGE")) {
            this.mContinueButton.setText(C2658R.string.checkout_place_order);
        }
        this.mOrder = OrderManager.getInstance().getCurrentOrder();
        ((TextView) this.mPaymentButton.findViewById(C2358R.C2357id.choose_payment_label)).setText(this.mOrder.getPaymentMethodDisplayName());
        initialize();
    }

    /* Access modifiers changed, original: protected */
    public void initialize() {
        Ensighten.evaluateEvent(this, "initialize", null);
        checkUnavailablePODs();
        tryTotalize();
    }

    private void checkUnavailablePODs() {
        Ensighten.evaluateEvent(this, "checkUnavailablePODs", null);
        if (this.mOrder != null && ListUtils.isNotEmpty(this.mOrder.getProducts())) {
            List<String> unavailable = PODUtils.getOrderUnavailablePODs();
            if (unavailable.size() > 0) {
                this.mPodWarning.setText(PODUtils.getUnavailablePODMessage(unavailable, getResources()));
                this.mPodWarning.setVisibility(0);
                return;
            }
            this.mPodWarning.setVisibility(8);
        }
    }

    public void tryTotalize() {
        Ensighten.evaluateEvent(this, "tryTotalize", null);
        UIUtils.startActivityIndicator(getNavigationActivity(), (int) C2658R.string.progress_checkout_msg);
        if (getNavigationActivity() != null) {
            Order order = OrderingManager.getInstance().getCurrentOrder();
            checkForTimeRestrictions();
            if (this.mCustomerModule.isLoggedIn()) {
                order.setProfile(this.mCustomerModule.getCurrentProfile());
                totalizeCurrentOrder(OrderManager.getInstance().getCurrentStore());
            } else {
                UIUtils.stopActivityIndicator();
                Bundle extras = new Bundle();
                extras.putString("EXTRA_RESULT_FRAGMENT_NAME", "mcdmobileapp://prepare_payment");
                extras.putSerializable("EXTRA_RESULT_CONTAINER_CLASS", PreparePaymentActivity.class);
                startActivityForResult(SignInActivity.class, JiceArgs.EVENT_CHECK_IN, extras, 4082);
            }
            UIUtils.startActivityIndicator(getNavigationActivity(), (int) C2658R.string.retrieving_payment_methods);
            this.mOrderingModule.getPaymentMethods(new C36483());
        }
    }

    private void checkForTimeRestrictions() {
        Ensighten.evaluateEvent(this, "checkForTimeRestrictions", null);
        StringBuilder sb = new StringBuilder(getString(C2658R.string.text_limited_availability_of));
        if (this.mOrder != null) {
            for (OrderProduct product : this.mOrder.getProducts()) {
                if (product.getProduct().getTimeRestriction() != null) {
                    sb.append(String.format(" %s - %s ", new Object[]{product.getProduct().getTimeRestriction().getFromTime(), product.getProduct().getTimeRestriction().getToTime()}));
                    this.mTimeRestrictionWarning.setVisibility(0);
                }
            }
        }
        this.mTimeRestrictionWarning.setText(sb.toString());
    }

    public void onClick(View view) {
        Ensighten.evaluateEvent(this, "onClick", new Object[]{view});
        super.onClick(view);
        if (view == this.mPaymentButton) {
            Bundle bundle = new Bundle();
            bundle.putSerializable(URLNavigationActivity.DATA_PASSER_KEY, this.mPaymentTypes);
            startActivityForResult(PaymentSelectionActivity.class, "select_payment", bundle, 4081);
        }
        if (view == this.mPickupLocationHolder.getPickupStoreView() || view == this.mDeliveryTimeHolder.getDeliveryTimeView()) {
            startActivity(MenuActivity.class, "menu_grid");
        }
    }

    /* Access modifiers changed, original: protected */
    public void onContinue() {
        Ensighten.evaluateEvent(this, "onContinue", null);
        checkPaymentAndContinue();
    }

    /* Access modifiers changed, original: protected */
    public void onScanContinue() {
        Ensighten.evaluateEvent(this, "onScanContinue", null);
        checkPaymentAndContinue();
    }

    private void checkPaymentAndContinue() {
        Ensighten.evaluateEvent(this, "checkPaymentAndContinue", null);
        Analytics.track(AnalyticType.Event, new ArgBuilder().setCategory("/checkout/restaurant").setAction("On click").setLabel("Continue").build());
        if (this.mOrder.getPayment() != null || this.mCashPayment.booleanValue() || this.mSelectedCard != null || this.mOrder.isZeroPriced()) {
            Order order = OrderingManager.getInstance().getCurrentOrder();
            setOrderPayment(order);
            order.setInvoiceRequested(this.mInvoiceChecked);
            order.setCompanyName(this.mInvoiceChecked ? this.mCompanyName.getText().toString() : "");
            completeOrderAndNavigate();
            return;
        }
        MCDAlertDialogBuilder.withContext(getNavigationActivity()).setTitle((int) C2658R.string.payment_method).setMessage((int) C2658R.string.pick_payment).setPositiveButton((int) C2658R.string.f6083ok, null).create().show();
        this.mContinueButton.setEnabled(true);
    }

    private void setOrderPayment(Order order) {
        Ensighten.evaluateEvent(this, "setOrderPayment", new Object[]{order});
        if (this.mCashPayment.booleanValue()) {
            order.setPayment(OrderPayment.fromCashPayment(this.mPaymentMethodId));
            order.setPaymentMode(PaymentMode.Cash);
        } else {
            order.setPaymentMode(null);
        }
        if (this.mSelectedCard != null) {
            setCard(order, this.mSelectedCard);
        }
    }

    private static void setCard(Order order, PaymentCard selectedCard) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.preparepayment.PreparePaymentFragment", "setCard", new Object[]{order, selectedCard});
        if (!selectedCard.equals(order.getPaymentCard())) {
            order.setPaymentCard(selectedCard);
        }
        order.setPayment(OrderPayment.fromPaymentCard(selectedCard));
    }

    private void completeOrderAndNavigate() {
        Ensighten.evaluateEvent(this, "completeOrderAndNavigate", null);
        Store store = OrderManager.getInstance().getCurrentStore();
        List<StoreCapability> availableCapabilities = new StoreCapabilties(store.getPODs()).filterAvailableCapabilities();
        boolean shouldSkipPickupScreen = false;
        if (ListUtils.isEmpty(availableCapabilities) || availableCapabilities.size() != 1) {
            if (ListUtils.isEmpty(availableCapabilities)) {
                shouldSkipPickupScreen = true;
            }
        } else if (((StoreCapability) availableCapabilities.get(0)).isHasScanner()) {
            shouldSkipPickupScreen = true;
        }
        if (shouldSkipPickupScreen && showFirstTimeQrScan()) {
            this.mContinueButton.setEnabled(true);
            return;
        }
        OrderingManager.getInstance().updateTender();
        SparseArray custom = new SparseArray();
        custom.put(55, String.valueOf(this.mPaymentMethodId));
        AnalyticsUtils.trackOnClickEvent(getAnalyticsTitle(), "Check-in at restaurant", custom);
        navigateToCheckin(shouldSkipPickupScreen, store);
    }

    private void navigateToCheckin(boolean shouldSkipPickupScreen, Store store) {
        Ensighten.evaluateEvent(this, "navigateToCheckin", new Object[]{new Boolean(shouldSkipPickupScreen), store});
        if (ConfigurationUtils.isInterimCheckinFlow()) {
            startActivity(ChoosePickUpActivity.class, "choose_pick_up");
            this.mContinueButton.setEnabled(true);
        } else if (shouldSkipPickupScreen || this.mIsComingFromBagCharge) {
            final Bundle extras = getActivity().getIntent().getExtras();
            this.mOrderingModule.getStoreOrderingCapabilties(store, new AsyncListener<StoreCapabilties>() {
                public void onResponse(StoreCapabilties storeCapabilties, AsyncToken asyncToken, AsyncException e) {
                    Ensighten.evaluateEvent(this, "onResponse", new Object[]{storeCapabilties, asyncToken, e});
                    PreparePaymentFragment.this.mContinueButton.setEnabled(true);
                    PreparePaymentFragment.this.startActivityForResult(OrderCheckinActivity.class, "ordercheckin", extras, 20);
                }
            });
        } else {
            this.mContinueButton.setEnabled(true);
            startActivity(PickupMethodActivity.class, PickupMethodFragment.NAME);
        }
    }

    private void totalizeCurrentOrder(Store currentStore) {
        Ensighten.evaluateEvent(this, "totalizeCurrentOrder", new Object[]{currentStore});
        if (currentStore == null) {
            AsyncException.report("You don't have a current store selected.");
        } else {
            OrderingManager.getInstance().totalize(currentStore, new C36515());
        }
    }

    private void refreshStoreInfo() {
        Ensighten.evaluateEvent(this, "refreshStoreInfo", null);
        final Order order = OrderingManager.getInstance().getCurrentOrder();
        if (order.isDelivery()) {
            this.mPickupLocationHolder.getContainer().setVisibility(8);
            this.mDeliveryTimeHolder.getContainer().setVisibility(0);
            if (order.getDeliveryDate() != null) {
                setDeliveryHeader(order.getDeliveryAddress(), UIUtils.formatDeliveryTime(getNavigationActivity(), order.getDeliveryStore().getCurrentDate(), order.getDeliveryDate(), true));
                return;
            }
            UIUtils.startActivityIndicator(getNavigationActivity(), "Retrieving delivery information");
            this.mDeliveryModule.getDeliveryStore(order.getDeliveryAddress(), order.getDeliveryDate(), this.mCustomerModule.getCurrentProfile(), new AsyncListener<Store>() {
                public void onResponse(Store response, AsyncToken token, AsyncException exception) {
                    Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
                    if (!(PreparePaymentFragment.this.getNavigationActivity() == null || response == null)) {
                        PreparePaymentFragment.access$500(PreparePaymentFragment.this, order.getDeliveryAddress(), UIUtils.formatDeliveryTime(PreparePaymentFragment.this.getNavigationActivity(), response.getCurrentDate(), UIUtils.getDateFromISO8601(response.getExpectedDeliveryTime()), true));
                    }
                    UIUtils.stopActivityIndicator();
                }
            });
            return;
        }
        this.mPickupLocationHolder.getContainer().setVisibility(0);
        this.mDeliveryTimeHolder.getContainer().setVisibility(8);
        Store currentStore = this.mCustomerModule.getCurrentStore();
        if (currentStore != null) {
            String name;
            if (currentStore.getStoreFavoriteName() != null) {
                name = currentStore.getStoreFavoriteName();
            } else {
                name = currentStore.getAddress1();
            }
            this.mPickupLocationHolder.getStoreName().setText(name);
            return;
        }
        this.mPickupLocationHolder.getStoreName().setText("<tap to choose a pickup location>");
    }

    private void setDeliveryHeader(CustomerAddress customerAddress, String deliveryTime) {
        Ensighten.evaluateEvent(this, "setDeliveryHeader", new Object[]{customerAddress, deliveryTime});
        this.mDeliveryTimeHolder.setDeliveryHeaderText(Html.fromHtml(((((((getString(C2658R.string.delivery_label_deliver1) + " <b>") + deliveryTime) + "</b> ") + getString(C2658R.string.delivery_label_to)) + " <b>") + customerAddress.getFullAddress()) + "</b>"));
    }

    public void updatedPaymentCard(PaymentCard paymentCard) {
        Ensighten.evaluateEvent(this, "updatedPaymentCard", new Object[]{paymentCard});
        this.mSelectedCard = paymentCard;
        this.mCashPayment = Boolean.valueOf(false);
        setCard(OrderingManager.getInstance().getCurrentOrder(), paymentCard);
        TextView textView = (TextView) this.mPaymentButton.findViewById(C2358R.C2357id.choose_payment_label);
        if (TextUtils.isEmpty(paymentCard.getNickName())) {
            textView.setText(paymentCard.getAlias());
            this.mOrder.setPaymentMethodDisplayName(paymentCard.getAlias());
            return;
        }
        textView.setText(paymentCard.getNickName());
        this.mOrder.setPaymentMethodDisplayName(paymentCard.getNickName());
    }

    public void cashAsPayment() {
        Ensighten.evaluateEvent(this, "cashAsPayment", null);
        this.mSelectedCard = null;
        this.mCashPayment = Boolean.valueOf(true);
    }

    public void updateTotals() {
        double orderValue;
        Ensighten.evaluateEvent(this, "updateTotals", null);
        NumberFormat formatter = UIUtils.getLocalizedCurrencyFormatter();
        OrderResponse orderResponse = OrderingManager.getInstance().getCurrentOrder().getTotalizeResult();
        if (orderResponse.getOrderValue() == null) {
            orderValue = 0.0d;
        } else {
            orderValue = orderResponse.getOrderValue().doubleValue();
        }
        OrderManager orderManager = OrderManager.getInstance();
        if (orderManager.allowBagCharges()) {
            OrderProduct bagProduct = orderManager.getBagProductInOrder();
            if (bagProduct != null) {
                this.mBagChargeContainer.setVisibility(0);
                double bagChargeValue = ProductUtils.getProductTotalPrice(bagProduct);
                if (orderValue >= bagChargeValue) {
                    orderValue -= bagChargeValue;
                }
                this.mBagCharge.setText(formatter.format(bagChargeValue));
            }
        }
        this.mOrderTotal.setText(formatter.format(orderValue));
        double discount = orderResponse.getTotalDiscount() == null ? 0.0d : orderResponse.getTotalDiscount().doubleValue();
        this.mDiscount.setText(formatter.format(discount));
        boolean hidden = orderResponse.getTotalDiscount() == null || orderResponse.getTotalDiscount().doubleValue() == 0.0d;
        this.mDiscountContainer.setVisibility(hidden ? 8 : 0);
        if (this.mTax != null && this.mTax.getVisibility() == 0) {
            this.mTax.setText(formatter.format(orderResponse.getTotalTax() == null ? 0.0d : orderResponse.getTotalTax().doubleValue()));
        }
        double totalValue = orderResponse.getTotalValue() == null ? 0.0d : orderResponse.getTotalValue().doubleValue();
        this.mTotal.setText(formatter.format(totalValue + (this.mDeliveryFee == null ? 0.0d : this.mDeliveryFee.doubleValue())));
        if (AppUtils.hideNutritionOnOrderingPages()) {
            this.mEnergyTotal.setVisibility(8);
        } else {
            this.mEnergyTotal.setVisibility(4);
            this.mEnergyTotal.setText(AppUtils.getEnergyTextForOrder(OrderingManager.getInstance().getCurrentOrder(), OrderUtils.getTotalEnergyUnit(OrderingManager.getInstance().getCurrentOrder())));
        }
        if (totalValue > 0.0d || discount < this.mOrder.getTotalValue()) {
            this.mPaymentButton.setEnabled(true);
            this.mPaymentButton.setBackgroundColor(ContextCompat.getColor(getContext(), 17170443));
            this.mPayWithLabel.setTextColor(ContextCompat.getColor(getContext(), C2658R.color.dark_gray_2));
            this.mChosePaymentLabel.setTextColor(ContextCompat.getColor(getContext(), C2658R.color.menu_dark_gray_1));
            this.mNoPaymentRequiredLabel.setVisibility(8);
            this.mOrder.setZeroPriced(false);
        } else {
            this.mPaymentButton.setEnabled(false);
            this.mPaymentButton.setBackgroundColor(ContextCompat.getColor(getContext(), C2658R.color.light_gray_1));
            this.mPayWithLabel.setTextColor(ContextCompat.getColor(getContext(), C2658R.color.light_gray_3));
            this.mChosePaymentLabel.setTextColor(ContextCompat.getColor(getContext(), C2658R.color.light_gray_3));
            this.mNoPaymentRequiredLabel.setVisibility(0);
            this.mOrder.setZeroPriced(true);
            this.mContinueButton.setEnabled(true);
        }
        this.mContainerTotals.setVisibility(0);
        this.mProgress.setVisibility(8);
    }

    private void showOutofStockAlert(int errorCode) {
        Ensighten.evaluateEvent(this, "showOutofStockAlert", new Object[]{new Integer(errorCode)});
        Bundle bundle = new Bundle();
        bundle.putInt(BasketFragment.PARAMETER_PRODUCT_ERROR_CODE, errorCode);
        bundle.putBoolean(AlertFragment.PARAMETER_HIDE_POSITIVE, true);
        UIUtils.stopActivityIndicator();
        getActivity().finish();
        startActivityForResult(AlertActivity.class, "check_out_items_unavailable", bundle, 37);
    }
}
