package com.mcdonalds.app.ordering.preparepayment;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.customer.SignInActivity;
import com.mcdonalds.app.ordering.OrderUtils;
import com.mcdonalds.app.ordering.OrderingManager;
import com.mcdonalds.app.ordering.ProductUtils;
import com.mcdonalds.app.ordering.alert.AlertActivity;
import com.mcdonalds.app.ordering.basket.BasketListAdapter;
import com.mcdonalds.app.ordering.basket.BasketListItem;
import com.mcdonalds.app.ordering.basket.SubtotalBasketListItem;
import com.mcdonalds.app.ordering.checkin.OrderCheckinActivity;
import com.mcdonalds.app.ordering.start.DeliveryTimeHolder;
import com.mcdonalds.app.ordering.start.PickupLocationHolder;
import com.mcdonalds.app.util.AppUtils;
import com.mcdonalds.app.util.ListUtils;
import com.mcdonalds.app.util.OrderOfferUtils;
import com.mcdonalds.app.util.OrderProductUtils;
import com.mcdonalds.app.util.UIUtils;
import com.mcdonalds.app.util.UIUtils.MCDAlertDialogBuilder;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.connectors.middleware.MWException;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.customer.CustomerModule;
import com.mcdonalds.sdk.modules.delivery.DeliveryModule;
import com.mcdonalds.sdk.modules.models.Offer;
import com.mcdonalds.sdk.modules.models.Offer.AndCondition;
import com.mcdonalds.sdk.modules.models.Offer.OfferType;
import com.mcdonalds.sdk.modules.models.Order;
import com.mcdonalds.sdk.modules.models.OrderOffer;
import com.mcdonalds.sdk.modules.models.OrderOfferProduct;
import com.mcdonalds.sdk.modules.models.OrderProduct;
import com.mcdonalds.sdk.modules.models.OrderResponse;
import com.mcdonalds.sdk.modules.models.PaymentCard;
import com.mcdonalds.sdk.modules.models.Product;
import com.mcdonalds.sdk.modules.models.ProductView;
import com.mcdonalds.sdk.modules.ordering.OrderManager;
import com.mcdonalds.sdk.modules.ordering.OrderingModule;
import com.mcdonalds.sdk.services.analytics.AnalyticType;
import com.mcdonalds.sdk.services.analytics.Analytics;
import com.mcdonalds.sdk.services.analytics.AnalyticsArgs.ArgBuilder;
import com.mcdonalds.sdk.services.analytics.JiceArgs;
import com.mcdonalds.sdk.services.analytics.conversionmaster.OrderAction;
import com.mcdonalds.sdk.services.analytics.conversionmaster.OrderItem;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.mcdonalds.sdk.services.network.RequestManager;
import com.mcdonalds.sdk.services.network.RequestManagerServiceConnection;
import com.mcdonalds.sdk.utils.DateUtils;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class PrepareOneTimePaymentFragment extends BasePreparePaymentFragment implements OnClickListener {
    private static final String LOG_TAG = PrepareOneTimePaymentFragment.class.getSimpleName();
    public final String DO_NOT_SHOW_TAX_KEY = "modules.ordering.doNotShowTaxWithTotal";
    public final String HIDE_ORDER_TOTAL = "modules.ordering.hideOrderTotal";
    private double deliveryFee;
    private EditText mCompanyName;
    private TextView mDeliveryFeeValue;
    private DeliveryLocationView mDeliveryLocation;
    private LinearLayout mDeliveryRow;
    private DeliveryTimeHolder mDeliveryTimeHolder;
    private TextView mDiscount;
    private View mDiscountContainer;
    private boolean mHeaderIsDelivery = false;
    private boolean mInvoiceChecked;
    private ListView mListView;
    private BasketListAdapter mListViewAdapter;
    private List<String> mNonProductOfferNames = new ArrayList();
    private Order mOrder;
    private EditText mOrderRemark;
    private boolean mOrderRemarkChecked;
    private final AsyncListener<Boolean> mOrderResponseListener = new C36445();
    private TextView mOrderTotal;
    private PickupLocationHolder mPickupHeaderHolder;
    private PickupLocationView mPickupLocation;
    private RequestManagerServiceConnection mServiceConnection;
    private TextView mSubtotal;
    private View mSubtotalRow;
    private TextView mTax;
    private TextView mTotal;

    /* renamed from: com.mcdonalds.app.ordering.preparepayment.PrepareOneTimePaymentFragment$1 */
    class C36401 implements AsyncListener<OrderResponse> {
        C36401() {
        }

        public void onResponse(OrderResponse response, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            if (exception == null && response != null) {
                PrepareOneTimePaymentFragment.access$002(PrepareOneTimePaymentFragment.this, response.getDeliveryFee().doubleValue());
            }
            PrepareOneTimePaymentFragment.access$100(PrepareOneTimePaymentFragment.this);
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.preparepayment.PrepareOneTimePaymentFragment$4 */
    class C36434 implements DialogInterface.OnClickListener {
        C36434() {
        }

        public void onClick(DialogInterface dialog, int which) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
            dialog.dismiss();
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.preparepayment.PrepareOneTimePaymentFragment$5 */
    class C36445 implements AsyncListener<Boolean> {
        C36445() {
        }

        public void onResponse(Boolean response, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            if (exception == null) {
                PrepareOneTimePaymentFragment.access$400(PrepareOneTimePaymentFragment.this);
                PrepareOneTimePaymentFragment.this.checkOrder();
            } else if (!Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.preparepayment.PrepareOneTimePaymentFragment", "access$500", new Object[]{PrepareOneTimePaymentFragment.this}).isDelivery()) {
                PrepareOneTimePaymentFragment.access$600(PrepareOneTimePaymentFragment.this, exception);
            } else if ((exception instanceof MWException) && exception.getErrorCode() == -1614 && Configuration.getSharedInstance().getBooleanForKey(BasePreparePaymentFragment.SHOW_LARGE_ORDER_NOTIFICATION)) {
                Bundle extras = new Bundle();
                extras.putBoolean("large_order", true);
                PrepareOneTimePaymentFragment.this.startActivity(AlertActivity.class, "large_order_call_alert", extras);
            } else {
                PrepareOneTimePaymentFragment.access$600(PrepareOneTimePaymentFragment.this, exception);
            }
            UIUtils.stopActivityIndicator();
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.preparepayment.PrepareOneTimePaymentFragment$6 */
    class C36456 implements DialogInterface.OnClickListener {
        C36456() {
        }

        public void onClick(DialogInterface dialog, int which) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
            PrepareOneTimePaymentFragment.this.getActivity().finish();
        }
    }

    static /* synthetic */ double access$002(PrepareOneTimePaymentFragment x0, double x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.preparepayment.PrepareOneTimePaymentFragment", "access$002", new Object[]{x0, new Double(x1)});
        x0.deliveryFee = x1;
        return x1;
    }

    static /* synthetic */ void access$100(PrepareOneTimePaymentFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.preparepayment.PrepareOneTimePaymentFragment", "access$100", new Object[]{x0});
        x0.afterResponse();
    }

    static /* synthetic */ boolean access$202(PrepareOneTimePaymentFragment x0, boolean x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.preparepayment.PrepareOneTimePaymentFragment", "access$202", new Object[]{x0, new Boolean(x1)});
        x0.mOrderRemarkChecked = x1;
        return x1;
    }

    static /* synthetic */ boolean access$302(PrepareOneTimePaymentFragment x0, boolean x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.preparepayment.PrepareOneTimePaymentFragment", "access$302", new Object[]{x0, new Boolean(x1)});
        x0.mInvoiceChecked = x1;
        return x1;
    }

    static /* synthetic */ void access$400(PrepareOneTimePaymentFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.preparepayment.PrepareOneTimePaymentFragment", "access$400", new Object[]{x0});
        x0.refreshCheckoutList();
    }

    static /* synthetic */ void access$600(PrepareOneTimePaymentFragment x0, AsyncException x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.preparepayment.PrepareOneTimePaymentFragment", "access$600", new Object[]{x0, x1});
        x0.showExceptionDialog(x1);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C2658R.layout.fragment_checkout_one_time_payment, container, false);
        this.mPickupHeaderHolder = new PickupLocationHolder(inflater.inflate(C2658R.layout.pickup_location, null));
        this.mPickupHeaderHolder.getPickupStoreView().setOnClickListener(this);
        this.mDeliveryTimeHolder = new DeliveryTimeHolder(inflater.inflate(C2658R.layout.delivery_location, null));
        this.mDeliveryTimeHolder.getDeliveryTimeView().setOnClickListener(this);
        this.mPickupLocation = (PickupLocationView) view.findViewById(C2358R.C2357id.pickup_view);
        this.mPickupLocation.setOnClickListener(this);
        this.mDeliveryLocation = (DeliveryLocationView) view.findViewById(C2358R.C2357id.delivery_view);
        this.mDeliveryLocation.setOnClickListener(this);
        this.mDeliveryRow = (LinearLayout) view.findViewById(C2358R.C2357id.delivery_row);
        this.mDeliveryFeeValue = (TextView) view.findViewById(C2358R.C2357id.delivery_fee_value);
        this.mOrderTotal = (TextView) view.findViewById(C2358R.C2357id.order_total_value);
        this.mSubtotalRow = view.findViewById(C2358R.C2357id.order_subtotal_container);
        this.mSubtotal = (TextView) view.findViewById(C2358R.C2357id.order_subtotal_value);
        if (!Configuration.getSharedInstance().getBooleanForKey("modules.ordering.hideOrderTotal")) {
            view.findViewById(C2358R.C2357id.order_total_container).setVisibility(0);
        }
        this.mDiscount = (TextView) view.findViewById(C2358R.C2357id.discount_value);
        this.mTax = (TextView) view.findViewById(C2358R.C2357id.tax_value);
        this.mListView = (ListView) view.findViewById(C2358R.C2357id.basket_list);
        this.mListView.setDivider(null);
        this.mListView.setDividerHeight(0);
        this.mTotal = (TextView) view.findViewById(C2358R.C2357id.total_value);
        this.mDiscountContainer = view.findViewById(C2358R.C2357id.discount_container);
        this.mDiscountContainer.setVisibility(8);
        setUpQrScanFirstTimeView(view);
        if (OrderingManager.getInstance().getCurrentOrder().isDelivery()) {
            this.mContinueButton.setText(C2658R.string.submit_order);
        } else {
            this.mContinueButton.setText(C2658R.string.checkin_tutorial_continue_button);
        }
        if (!Configuration.getSharedInstance().getBooleanForKey("modules.ordering.doNotShowTaxWithTotal", false)) {
            this.mTax = (TextView) view.findViewById(C2358R.C2357id.tax_value);
            ((View) this.mTax.getParent()).setVisibility(0);
        }
        return view;
    }

    public void onResume() {
        super.onResume();
        this.mHeaderIsDelivery = OrderingManager.getInstance().getCurrentOrder().isDelivery();
        if (!OrderManager.getInstance().getCurrentOrder().isDelivery()) {
            refreshStoreInfo();
        }
    }

    private void refreshStoreInfoAndDeliveryFee() {
        Ensighten.evaluateEvent(this, "refreshStoreInfoAndDeliveryFee", null);
        if (this.mCustomerModule == null || this.mDeliveryModule == null || this.mOrderingModule == null) {
            this.mCustomerModule = (CustomerModule) ModuleManager.getModule(CustomerModule.NAME);
            this.mDeliveryModule = (DeliveryModule) ModuleManager.getModule(DeliveryModule.NAME);
            this.mOrderingModule = (OrderingModule) ModuleManager.getModule("ordering");
        }
        this.mHeaderIsDelivery = OrderingManager.getInstance().getCurrentOrder().isDelivery();
        if (this.mHeaderIsDelivery) {
            UIUtils.startActivityIndicator(getNavigationActivity(), getString(C2658R.string.sl_retrieve_stores));
            this.mDeliveryModule.lookupDeliveryCharge(this.mOrder, new C36401());
            return;
        }
        this.deliveryFee = 0.0d;
        afterResponse();
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mServiceConnection = RequestManager.register(getActivity());
    }

    public void onDetach() {
        super.onDetach();
        RequestManager.unregister(getActivity(), this.mServiceConnection);
    }

    /* Access modifiers changed, original: protected */
    public void initialize() {
        Ensighten.evaluateEvent(this, "initialize", null);
        UIUtils.startActivityIndicator(getActivity(), (int) C2658R.string.progress_checkout_msg);
        this.mOrder = OrderingManager.getInstance().getCurrentOrder();
        refreshStoreInfo();
        refreshStoreInfoAndDeliveryFee();
    }

    private void afterResponse() {
        Ensighten.evaluateEvent(this, "afterResponse", null);
        if (Configuration.getSharedInstance().getBooleanForKey("interface.showOrderDetailsInCheckout")) {
            View container;
            this.mListViewAdapter = new BasketListAdapter(getNavigationActivity(), null, this.mOrder);
            this.mHeaderIsDelivery = OrderingManager.getInstance().getCurrentOrder().isDelivery();
            this.mListView.setAdapter(null);
            ListView listView = this.mListView;
            if (this.mHeaderIsDelivery) {
                container = this.mDeliveryTimeHolder.getContainer();
            } else {
                container = this.mPickupHeaderHolder.getContainer();
            }
            listView.addHeaderView(container);
            final View OrderFooter = View.inflate(getActivity(), C2658R.layout.order_remark, null);
            this.mListView.addFooterView(OrderFooter);
            if (OrderingManager.getInstance().getCurrentOrder().isDelivery()) {
                OrderFooter.setVisibility(0);
                ((Switch) OrderFooter.findViewById(C2358R.C2357id.orderRemarkSwitch)).setOnCheckedChangeListener(new OnCheckedChangeListener() {
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        int i = 0;
                        Ensighten.evaluateEvent(this, "onCheckedChanged", new Object[]{buttonView, new Boolean(isChecked)});
                        PrepareOneTimePaymentFragment.access$202(PrepareOneTimePaymentFragment.this, isChecked);
                        View findViewById = OrderFooter.findViewById(C2358R.C2357id.remark_container);
                        if (!isChecked) {
                            i = 8;
                        }
                        findViewById.setVisibility(i);
                    }
                });
            }
            this.mOrderRemark = (EditText) OrderFooter.findViewById(C2358R.C2357id.order_remark_content);
            final View InvoiceFooter = View.inflate(getActivity(), C2658R.layout.invoice, null);
            this.mListView.addFooterView(InvoiceFooter);
            if (Configuration.getSharedInstance().getBooleanForKey("modules.Ordering.requestTaxInvoice") && OrderingManager.getInstance().getCurrentOrder().isDelivery()) {
                InvoiceFooter.setVisibility(0);
                ((Switch) InvoiceFooter.findViewById(C2358R.C2357id.invoiceSwitch)).setOnCheckedChangeListener(new OnCheckedChangeListener() {
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        int i = 0;
                        Ensighten.evaluateEvent(this, "onCheckedChanged", new Object[]{buttonView, new Boolean(isChecked)});
                        PrepareOneTimePaymentFragment.access$302(PrepareOneTimePaymentFragment.this, isChecked);
                        View findViewById = InvoiceFooter.findViewById(C2358R.C2357id.company_container);
                        if (!isChecked) {
                            i = 8;
                        }
                        findViewById.setVisibility(i);
                    }
                });
            }
            this.mCompanyName = (EditText) InvoiceFooter.findViewById(C2358R.C2357id.company_name);
            this.mListView.setAdapter(this.mListViewAdapter);
            this.mListViewAdapter.addAll(createBasketItems());
            this.mListViewAdapter.notifyDataSetChanged();
        } else {
            this.mListView.setVisibility(8);
        }
        refreshCheckoutList();
        if (this.mCustomerModule == null || !this.mCustomerModule.isLoggedIn()) {
            UIUtils.stopActivityIndicator();
            if (getNavigationActivity() != null) {
                Bundle extras = new Bundle();
                extras.putString("EXTRA_RESULT_FRAGMENT_NAME", "prepare_one_time_payment");
                extras.putSerializable("EXTRA_RESULT_CONTAINER_CLASS", PreparePaymentActivity.class);
                startActivity(SignInActivity.class, JiceArgs.EVENT_CHECK_IN, extras);
                return;
            }
            return;
        }
        this.mOrder.setProfile(this.mCustomerModule.getCurrentProfile());
        OrderingManager.getInstance().totalize(OrderManager.getInstance().getCurrentStore(), this.mOrderResponseListener);
    }

    public void onClick(View view) {
        Ensighten.evaluateEvent(this, "onClick", new Object[]{view});
        super.onClick(view);
    }

    /* Access modifiers changed, original: protected */
    public void onContinue() {
        Ensighten.evaluateEvent(this, "onContinue", null);
        completeOrderAndNavigate();
    }

    /* Access modifiers changed, original: protected */
    public void onScanContinue() {
        Ensighten.evaluateEvent(this, "onScanContinue", null);
        completeOrderAndNavigate();
    }

    private void completeOrderAndNavigate() {
        Ensighten.evaluateEvent(this, "completeOrderAndNavigate", null);
        Order currentOrder = OrderingManager.getInstance().getCurrentOrder();
        if (!Configuration.getSharedInstance().getBooleanForKey("interface.ordering.checkOfferDayAndTimeConstraints") || checkDayAndTimeValidity()) {
            currentOrder.setOrderRemarkAvailable(this.mOrderRemarkChecked);
            currentOrder.setOrderRemark(this.mOrderRemarkChecked ? this.mOrderRemark.getText().toString() : "");
            currentOrder.setInvoiceRequested(this.mInvoiceChecked);
            currentOrder.setCompanyName(this.mInvoiceChecked ? this.mCompanyName.getText().toString() : "");
            if (currentOrder.isDelivery() || !showFirstTimeQrScan()) {
                startActivityForResult(OrderCheckinActivity.class, "one_time_order_checkin", 20);
            } else {
                this.mContinueButton.setEnabled(true);
            }
            StringBuilder productCodes = new StringBuilder();
            StringBuilder productNames = new StringBuilder();
            ArrayList<OrderItem> list = new ArrayList();
            for (OrderProduct orderProduct : currentOrder.getProducts()) {
                Product p = orderProduct.getProduct();
                list.add(new OrderItem("", orderProduct.getProductCode(), p.getLongName(), String.valueOf(this.mOrder.isDelivery() ? p.getPriceDelivery() : p.getPriceEatIn()), orderProduct.getQuantity()));
                productCodes.append(orderProduct.getProductCode()).append("_");
                productNames.append(orderProduct.getProduct().getLongName()).append("_");
            }
            if (productCodes.length() > 0) {
                productCodes.deleteCharAt(productCodes.length() - 1);
            }
            if (productNames.length() > 0) {
                productNames.deleteCharAt(productNames.length() - 1);
            }
            HashMap<String, Object> jiceMap = new HashMap();
            if (currentOrder.isDelivery()) {
                jiceMap.put(JiceArgs.EVENT_NAME, JiceArgs.EVENT_SUBMIT_ORDER);
            } else {
                jiceMap.put(JiceArgs.EVENT_NAME, JiceArgs.EVENT_CHECK_IN);
            }
            jiceMap.put(JiceArgs.LABEL_ITEM_ID, productCodes.toString());
            jiceMap.put(JiceArgs.LABEL_ITEM_IS_PICKUP, String.valueOf(currentOrder.isDelivery()));
            jiceMap.put(JiceArgs.LABEL_ITEM_NAME, productNames.toString());
            jiceMap.put(JiceArgs.LABEL_ITEM_IS_DELIVERY, String.valueOf(!currentOrder.isDelivery()));
            Analytics.track(AnalyticType.Event, new ArgBuilder().setJice(jiceMap).setConversionMaster(new OrderAction(String.valueOf(this.mCustomerModule.getCurrentProfile().getCustomerId()), "", (int) this.mOrder.getTotalValue(), list)).build());
            return;
        }
        showDayAndTimeCheckDialog();
    }

    private void showDayAndTimeCheckDialog() {
        Ensighten.evaluateEvent(this, "showDayAndTimeCheckDialog", null);
        MCDAlertDialogBuilder.withContext(getActivity()).setTitle((int) C2658R.string.offer_unavailable).setMessage((int) C2658R.string.offer_alert_msg_invalid_day_and_time).setPositiveButton((int) C2658R.string.f6083ok, new C36434()).create().show();
    }

    private boolean checkDayAndTimeValidity() {
        Ensighten.evaluateEvent(this, "checkDayAndTimeValidity", null);
        Order currentOrder = OrderingManager.getInstance().getCurrentOrder();
        Calendar current = Calendar.getInstance();
        int dayOfWeekNow = current.get(7);
        int hourOfDayNow = current.get(11);
        int minuteNow = current.get(12);
        for (OrderOffer oo : currentOrder.getOffers()) {
            Offer offer = oo.getOffer();
            if (!(offer.getAndConditions() == null || offer.getAndConditions().isEmpty())) {
                boolean test = false;
                for (AndCondition c : offer.getAndConditions()) {
                    if (dayOfWeekNow == c.getDayOfWeek() && DateUtils.timeCheck(hourOfDayNow, minuteNow, c.getHourOfDayFrom(), c.getMinuteFrom(), c.getHourOfDayTo(), c.getMinuteTo())) {
                        test = true;
                        break;
                    }
                }
                if (!test) {
                    return false;
                }
            }
        }
        return true;
    }

    public void updatedPaymentCard(PaymentCard paymentCard) {
        Ensighten.evaluateEvent(this, "updatedPaymentCard", new Object[]{paymentCard});
    }

    public void cashAsPayment() {
        Ensighten.evaluateEvent(this, "cashAsPayment", null);
    }

    private List<BasketListItem> createBasketItems() {
        Ensighten.evaluateEvent(this, "createBasketItems", null);
        List<BasketListItem> items = new ArrayList();
        if (this.mOrder != null) {
            if (this.mOrder.getProducts() != null) {
                for (OrderProduct product : this.mOrder.getProducts()) {
                    items.addAll(createProductItems(product));
                }
            }
            this.mNonProductOfferNames.clear();
            if (this.mOrder.getOffers() != null) {
                for (OrderOffer orderOffer : this.mOrder.getOffers()) {
                    items.addAll(createOfferItems(orderOffer));
                }
            }
            if (!items.isEmpty()) {
                items.addAll(createSubtotalItems());
            }
        }
        return items;
    }

    private List<BasketListItem> createSubtotalItems() {
        Ensighten.evaluateEvent(this, "createSubtotalItems", null);
        List<BasketListItem> ret = new ArrayList();
        SubtotalBasketListItem item = new SubtotalBasketListItem();
        double totalValue = this.mOrder.getTotalValue();
        OrderResponse orderResponse = OrderingManager.getInstance().getCurrentOrder().getTotalizeResult();
        if (orderResponse != null) {
            if (this.mOrder.isDelivery()) {
                totalValue = orderResponse.getOrderValue().doubleValue();
            } else {
                totalValue = orderResponse.getTotalValue().doubleValue();
            }
        }
        if (this.mHeaderIsDelivery) {
            item.setDeliveryHidden(false);
            item.setDividerHidden(Boolean.valueOf(false));
            item.setDeliveryFee(this.deliveryFee);
            if (this.mOrder.hasDeliveryFeeOffer()) {
                item.setDeliveryFeeOfferHidden(false);
                item.setOfferName(this.mOrder.getDeliveryChargeOfferName());
                item.setDeliveryFeeDiscount(this.mOrder.getDiscountedDeliveryCharge().doubleValue());
                totalValue += this.mOrder.getDiscountedDeliveryCharge().doubleValue();
            } else {
                item.setDeliveryFeeOfferHidden(true);
                totalValue += this.deliveryFee;
            }
        } else {
            item.setDeliveryFeeOfferHidden(true);
            item.setDeliveryHidden(true);
            item.setDividerHidden(Boolean.valueOf(true));
            item.setShowTotal(true);
        }
        item.setEnergyTotal(Double.toString(this.mOrder.getTotalEnergy()) + " " + OrderUtils.getTotalEnergyUnit(this.mOrder));
        item.setPriceTotal(UIUtils.getLocalizedCurrencyFormatter().format(totalValue));
        if (ListUtils.isNotEmpty(this.mOrder.getOffers()) && !TextUtils.isEmpty(this.mOrder.getStoreId())) {
            List<Integer> restaurants = ((OrderOffer) this.mOrder.getOffers().iterator().next()).getOffer().getRestaurants();
            Integer storeId = Integer.valueOf(this.mOrder.getStoreId());
            if (restaurants != null && (restaurants.contains(storeId) || restaurants.isEmpty())) {
                item.setHideOfferUnavailableContainer(true);
            }
        }
        if (this.mNonProductOfferNames.size() > 0) {
            item.setIsNonProductOfferAvailable(true);
            item.setNonProductOfferNames(this.mNonProductOfferNames);
        }
        ret.add(item);
        return ret;
    }

    private List<BasketListItem> createOfferItems(OrderOffer orderOffer) {
        Ensighten.evaluateEvent(this, "createOfferItems", new Object[]{orderOffer});
        List<BasketListItem> items = new ArrayList();
        if (orderOffer.getOffer().getOfferType() != OfferType.OFFER_TYPE_DELIVERY_FEE) {
            if (orderOffer.getOrderOfferProducts() != null) {
                for (int i = 0; i < orderOffer.getOrderOfferProducts().size(); i++) {
                    boolean isHeader;
                    boolean isFooter;
                    boolean z;
                    OrderOfferProduct orderOfferProduct = (OrderOfferProduct) orderOffer.getOrderOfferProducts().get(i);
                    if (i == 0) {
                        isHeader = true;
                    } else {
                        isHeader = false;
                    }
                    if (i == orderOffer.getOrderOfferProducts().size() - 1) {
                        isFooter = true;
                    } else {
                        isFooter = false;
                    }
                    BasketListItem newItem = new BasketListItem();
                    newItem.setBasketItem(orderOffer);
                    newItem.setOfferPriceChanged(false);
                    if (isHeader) {
                        z = false;
                    } else {
                        z = true;
                    }
                    newItem.setTopPaddingHidden(Boolean.valueOf(z));
                    if (isHeader) {
                        newItem.setHeaderHidden(Boolean.valueOf(false));
                        newItem.setHeaderIconHidden(Boolean.valueOf(false));
                        newItem.setHeaderText(orderOffer.getOffer().getName());
                    } else {
                        newItem.setHeaderHidden(Boolean.valueOf(true));
                    }
                    newItem.setDividerHidden(Boolean.valueOf(true));
                    if (isFooter) {
                        newItem.setFooterHidden(Boolean.valueOf(true));
                        newItem.setEnergyTotal(AppUtils.getEnergyTextForOrderOffer(orderOffer, OrderOfferUtils.getTotalEnergyUnit(orderOffer)));
                        newItem.setPriceTotal(UIUtils.getLocalizedCurrencyFormatter().format(this.mOrder.getOfferTotalValue()));
                    } else {
                        newItem.setFooterHidden(Boolean.valueOf(true));
                    }
                    newItem.setMakeItAMealHidden(Boolean.valueOf(true));
                    updateItemData(newItem, orderOfferProduct.getSelectedProductOption(), false);
                    OrderProduct imageProduct = orderOfferProduct.getSelectedProductOption();
                    if (imageProduct != null) {
                        newItem.setIconImage(imageProduct.getDisplayThumbnailImage());
                    }
                    items.add(newItem);
                }
            } else {
                this.mNonProductOfferNames.add(orderOffer.getOffer().getName());
            }
        }
        return items;
    }

    private List<BasketListItem> createProductItems(OrderProduct product) {
        Ensighten.evaluateEvent(this, "createProductItems", new Object[]{product});
        List<BasketListItem> items = new ArrayList();
        List<OrderProduct> subProducts = !product.isMeal() ? Collections.singletonList(product) : subProducts(product);
        int i = 0;
        while (i < subProducts.size()) {
            OrderProduct subProduct = OrderProduct.getChoiceWithinChoiceProduct((OrderProduct) subProducts.get(i));
            boolean isHeader = i == 0 && product.isMeal();
            boolean isDivider = i == 0 && !product.isMeal();
            boolean isFooter = i == subProducts.size() + -1;
            BasketListItem item = new BasketListItem();
            item.setBasketItem(product);
            item.setTimeRestriction(product.getProduct().getTimeRestriction());
            item.setTopPaddingHidden(Boolean.valueOf(!isHeader));
            if (isHeader) {
                item.setHeaderHidden(Boolean.valueOf(false));
                item.setHeaderIconHidden(Boolean.valueOf(true));
                item.setHeaderText(product.getQuantity() + " " + product.getDisplayName());
            } else {
                item.setHeaderHidden(Boolean.valueOf(true));
            }
            item.setDividerHidden(Boolean.valueOf(!isDivider));
            if (isFooter) {
                item.setFooterHidden(Boolean.valueOf(false));
                item.setFooterHidden(Boolean.valueOf(true));
                item.setEnergyTotal(product.getTotalEnergy() + " " + OrderProductUtils.getTotalEnergyUnit(product));
                item.setPriceTotal(UIUtils.getLocalizedCurrencyFormatter().format(ProductUtils.getProductTotalPrice(product)));
            } else {
                item.setFooterHidden(Boolean.valueOf(true));
            }
            item.setMakeItAMealHidden(Boolean.valueOf(true));
            boolean includeQuantity = i == 0 && !product.isMeal();
            updateItemData(item, subProduct, includeQuantity);
            OrderProduct imageProduct = subProduct;
            if (product.getChoices() != null && product.getChoices().contains(subProduct)) {
                imageProduct = (OrderProduct) product.getChoiceSolutions().get(product.getChoices().indexOf(subProduct));
            }
            item.setIconImage(imageProduct.getDisplayThumbnailImage());
            setErrorInOrderItem(item, subProduct);
            items.add(item);
            i++;
        }
        return items;
    }

    private List<OrderProduct> subProducts(OrderProduct product) {
        Ensighten.evaluateEvent(this, "subProducts", new Object[]{product});
        List<OrderProduct> ret = new ArrayList(product.getIngredients());
        if (product.getChoiceSolutions() != null && product.getChoiceSolutions().size() > 0) {
            for (int i = 0; i < product.getChoiceSolutions().size(); i++) {
                ret.add(product.getChoiceSolutions().valueAt(i));
            }
        }
        return ret;
    }

    private void updateItemData(BasketListItem item, OrderProduct product, boolean includeQuantity) {
        Ensighten.evaluateEvent(this, "updateItemData", new Object[]{item, product, new Boolean(includeQuantity)});
        if (!(product.getProduct() == null || product.getProduct().getLongName() == null)) {
            if (includeQuantity) {
                item.setItemName(product.getQuantity() + " " + product.getProduct().getLongName());
            } else {
                item.setItemName(product.getProduct().getLongName());
            }
        }
        if (product.getCustomizations() != null) {
            item.setItemInstructions(OrderProductUtils.getCustomizationsString(product));
        }
    }

    private void refreshStoreInfo() {
        Ensighten.evaluateEvent(this, "refreshStoreInfo", null);
        Order order = OrderingManager.getInstance().getCurrentOrder();
        if (order.isDelivery()) {
            this.mDeliveryLocation.update(order);
            this.mDeliveryTimeHolder.setDeliveryHeaderText(Html.fromHtml(this.mDeliveryLocation.getTimeLabel().getText().toString()));
            this.mPickupLocation.setVisibility(8);
            this.mDeliveryLocation.setVisibility(8);
            return;
        }
        String name;
        this.mPickupLocation.setStore(this.mCustomerModule.getCurrentStore());
        if (this.mCustomerModule.getCurrentStore().getStoreFavoriteName() != null) {
            name = this.mCustomerModule.getCurrentStore().getStoreFavoriteName();
        } else {
            name = this.mCustomerModule.getCurrentStore().getAddress1();
        }
        this.mPickupHeaderHolder.getContainer().findViewById(C2358R.C2357id.pickup_store_title).setVisibility(8);
        this.mPickupHeaderHolder.getStoreName().setText(Html.fromHtml(getString(C2658R.string.order_location_pickup_title) + "<br/><b>" + name + "</b>"));
        this.mDeliveryLocation.setVisibility(8);
        this.mPickupLocation.setVisibility(8);
    }

    private void showExceptionDialog(AsyncException exception) {
        Ensighten.evaluateEvent(this, "showExceptionDialog", new Object[]{exception});
        MCDAlertDialogBuilder.withContext(getNavigationActivity()).setTitle(getString(C2658R.string.error_title)).setMessage(exception.getLocalizedMessage()).setPositiveButton((int) C2658R.string.f6083ok, new C36456()).setCancelable(false).create().show();
    }

    private void setErrorInOrderItem(BasketListItem item, OrderProduct subProduct) {
        Ensighten.evaluateEvent(this, "setErrorInOrderItem", new Object[]{item, subProduct});
        Order order = OrderingManager.getInstance().getCurrentOrder();
        if (order != null && order.getTotalizeResult() != null && order.getTotalizeResult().getOrderView() != null) {
            List<ProductView> productViews = OrderingManager.getInstance().getCurrentOrder().getTotalizeResult().getOrderView().getProducts();
            if (productViews != null) {
                for (int j = 0; j < productViews.size(); j++) {
                    ProductView orderViewProduct = (ProductView) productViews.get(j);
                    if (orderViewProduct.getValidationErrorCode().intValue() == OrderResponse.PRODUCT_OUT_OF_STOCK_CODE && subProduct.getProductCode().equals(orderViewProduct.getProductCode().toString())) {
                        item.setHasError(true);
                        item.setOutOfStock(true);
                    }
                }
            }
        }
    }

    private void refreshCheckoutList() {
        Ensighten.evaluateEvent(this, "refreshCheckoutList", null);
        if (Configuration.getSharedInstance().getBooleanForKey("interface.showOrderDetailsInCheckout")) {
            this.mListViewAdapter = new BasketListAdapter(getNavigationActivity(), null, this.mOrder);
            this.mListView.setAdapter(this.mListViewAdapter);
            this.mListViewAdapter.addAll(createBasketItems());
            this.mListViewAdapter.notifyDataSetChanged();
            return;
        }
        this.mListView.setVisibility(8);
    }
}
