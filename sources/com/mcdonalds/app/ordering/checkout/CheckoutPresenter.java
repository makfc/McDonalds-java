package com.mcdonalds.app.ordering.checkout;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.SparseArray;
import com.ensighten.Ensighten;
import com.mcdonalds.app.ordering.OrderUtils;
import com.mcdonalds.app.ordering.OrderingManager;
import com.mcdonalds.app.ordering.ProductUtils;
import com.mcdonalds.app.ordering.utils.PODUtils;
import com.mcdonalds.app.util.AnalyticsUtils;
import com.mcdonalds.app.util.AppUtils;
import com.mcdonalds.app.util.ConfigurationUtils;
import com.mcdonalds.app.util.DeliveryTimePresenter;
import com.mcdonalds.app.util.InvoicePresenter;
import com.mcdonalds.app.util.ListUtils;
import com.mcdonalds.app.util.OrderRemarkPresenter;
import com.mcdonalds.app.util.PickupLocationPresenter;
import com.mcdonalds.app.util.UIUtils;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.customer.CustomerModule;
import com.mcdonalds.sdk.modules.delivery.DeliveryModule;
import com.mcdonalds.sdk.modules.models.CustomerAddress;
import com.mcdonalds.sdk.modules.models.Order;
import com.mcdonalds.sdk.modules.models.OrderOffer;
import com.mcdonalds.sdk.modules.models.OrderPayment;
import com.mcdonalds.sdk.modules.models.OrderProduct;
import com.mcdonalds.sdk.modules.models.OrderResponse;
import com.mcdonalds.sdk.modules.models.PaymentCard;
import com.mcdonalds.sdk.modules.models.PaymentMethod;
import com.mcdonalds.sdk.modules.models.PaymentMethod.PaymentMode;
import com.mcdonalds.sdk.modules.models.StoreCapabilties;
import com.mcdonalds.sdk.modules.models.StoreCapabilties.StoreCapability;
import com.mcdonalds.sdk.modules.ordering.OrderManager;
import com.mcdonalds.sdk.modules.ordering.OrderingModule;
import com.mcdonalds.sdk.modules.storelocator.Store;
import com.mcdonalds.sdk.services.analytics.AnalyticType;
import com.mcdonalds.sdk.services.analytics.Analytics;
import com.mcdonalds.sdk.services.analytics.AnalyticsArgs.ArgBuilder;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.mcdonalds.sdk.services.data.LocalDataManager;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class CheckoutPresenter extends BaseObservable implements DeliveryTimePresenter, InvoicePresenter, OrderRemarkPresenter, PickupLocationPresenter {
    private double mBagCharge;
    private int mCashPaymentMethodId;
    private boolean mComingFromBagCharges;
    private Context mContext;
    private CustomerModule mCustomerModule;
    private double mDeliveryFee;
    private Spanned mDeliveryHeaderText;
    private AsyncListener<Store> mDeliveryStoreListener = new C24123();
    private double mDiscount;
    private boolean mLoading;
    private Order mOrder;
    private AsyncListener<List<PaymentMethod>> mPaymentMethodListener = new C24134();
    private LinkedHashSet<PaymentMethod> mPaymentMethods;
    private boolean mShowQRScanFirstTime;
    private String mStoreName;
    private double mSubtotal;
    private double mTax;
    private String mTimeRestrictionWarning;
    private double mTotal;
    private AsyncListener<Boolean> mTotalizeListener = new C24145();
    private String mUnavailablePODsMessage;
    private CheckoutView mView;
    public OnClickListener onMaxCardAlertPositiveClicked = new C24101();
    public OnClickListener onMaxCardsAlertNegativeClicked = new C24112();

    /* renamed from: com.mcdonalds.app.ordering.checkout.CheckoutPresenter$1 */
    class C24101 implements OnClickListener {
        C24101() {
        }

        public void onClick(DialogInterface dialog, int which) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.checkout.CheckoutPresenter", "access$000", new Object[]{CheckoutPresenter.this}).navigateToAccountCardsPage();
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.checkout.CheckoutPresenter$2 */
    class C24112 implements OnClickListener {
        C24112() {
        }

        public void onClick(DialogInterface dialog, int which) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.checkout.CheckoutPresenter", "access$000", new Object[]{CheckoutPresenter.this}).navigateToDashboard();
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.checkout.CheckoutPresenter$3 */
    class C24123 implements AsyncListener<Store> {
        C24123() {
        }

        public void onResponse(Store response, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            if (response != null) {
                CheckoutPresenter.access$200(CheckoutPresenter.this, Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.checkout.CheckoutPresenter", "access$100", new Object[]{CheckoutPresenter.this}).getDeliveryAddress(), response.getCurrentDate(), UIUtils.getDateFromISO8601(response.getExpectedDeliveryTime()));
            }
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.checkout.CheckoutPresenter$4 */
    class C24134 implements AsyncListener<List<PaymentMethod>> {
        C24134() {
        }

        public void onResponse(List<PaymentMethod> response, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            if (response != null) {
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.checkout.CheckoutPresenter", "access$300", new Object[]{CheckoutPresenter.this}).clear();
                boolean customerPaymentMethodIdUpdated = false;
                int size = response.size();
                for (int i = 0; i < size; i++) {
                    PaymentMethod paymentMethod = (PaymentMethod) response.get(i);
                    if (paymentMethod.getPaymentMode() == PaymentMode.Cash && !customerPaymentMethodIdUpdated) {
                        customerPaymentMethodIdUpdated = true;
                        CheckoutPresenter.access$402(CheckoutPresenter.this, paymentMethod.getID().intValue());
                    }
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.checkout.CheckoutPresenter", "access$300", new Object[]{CheckoutPresenter.this}).add(paymentMethod);
                }
            }
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.checkout.CheckoutPresenter$5 */
    class C24145 implements AsyncListener<Boolean> {
        C24145() {
        }

        public void onResponse(Boolean response, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            if (exception == null) {
                CheckoutPresenter.access$500(CheckoutPresenter.this);
                CheckoutPresenter.access$600(CheckoutPresenter.this);
                CheckoutPresenter.access$700(CheckoutPresenter.this);
            } else if (exception.getErrorCode() == OrderResponse.PRODUCT_UNAVAILABLE_AT_RESTAURANT_CODE) {
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.checkout.CheckoutPresenter", "access$000", new Object[]{CheckoutPresenter.this}).showOutOfStockAlert(exception.getErrorCode());
            } else if (exception.getErrorCode() == OrderResponse.ZERO_OR_NEGAVTIVE_PRICE_ERROR_CODE) {
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.checkout.CheckoutPresenter", "access$000", new Object[]{CheckoutPresenter.this}).showZeroOrNegativePriceError();
            } else {
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.checkout.CheckoutPresenter", "access$000", new Object[]{CheckoutPresenter.this}).showFatalError(exception.getLocalizedMessage());
            }
        }
    }

    static /* synthetic */ void access$200(CheckoutPresenter x0, CustomerAddress x1, Date x2, Date x3) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.checkout.CheckoutPresenter", "access$200", new Object[]{x0, x1, x2, x3});
        x0.setDeliveryHeaderText(x1, x2, x3);
    }

    static /* synthetic */ int access$402(CheckoutPresenter x0, int x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.checkout.CheckoutPresenter", "access$402", new Object[]{x0, new Integer(x1)});
        x0.mCashPaymentMethodId = x1;
        return x1;
    }

    static /* synthetic */ void access$500(CheckoutPresenter x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.checkout.CheckoutPresenter", "access$500", new Object[]{x0});
        x0.checkOrder();
    }

    static /* synthetic */ void access$600(CheckoutPresenter x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.checkout.CheckoutPresenter", "access$600", new Object[]{x0});
        x0.checkProductErrors();
    }

    static /* synthetic */ void access$700(CheckoutPresenter x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.checkout.CheckoutPresenter", "access$700", new Object[]{x0});
        x0.setupTotals();
    }

    public CheckoutPresenter(Context context, CheckoutView view) {
        this.mContext = context;
        this.mView = view;
    }

    public void setComingFromBagCharges(boolean comingFromBagCharges) {
        Ensighten.evaluateEvent(this, "setComingFromBagCharges", new Object[]{new Boolean(comingFromBagCharges)});
        this.mComingFromBagCharges = comingFromBagCharges;
    }

    public void initialize() {
        Ensighten.evaluateEvent(this, "initialize", null);
        this.mPaymentMethods = new LinkedHashSet();
        this.mCustomerModule = (CustomerModule) ModuleManager.getModule(CustomerModule.NAME);
        this.mOrder = OrderManager.getInstance().getCurrentOrder();
        if (this.mCustomerModule.isLoggedIn()) {
            this.mOrder.setProfile(this.mCustomerModule.getCurrentProfile());
        } else {
            this.mView.navigateToSignIn();
        }
        if (this.mOrder.isDelivery()) {
            setupDelivery();
        } else {
            setupPickup();
        }
        setLoading(true);
        setupPaymentModes();
        checkUnavailablePODs();
        checkTimeRestrictions();
        totalize();
    }

    public void chosePaymentClicked() {
        Ensighten.evaluateEvent(this, "chosePaymentClicked", null);
        this.mView.showPaymentSelection(this.mPaymentMethods);
    }

    public void continueClicked() {
        Ensighten.evaluateEvent(this, "continueClicked", null);
        checkPaymentAndContinue();
    }

    public void firstTimeScanDismissed() {
        Ensighten.evaluateEvent(this, "firstTimeScanDismissed", null);
        markScanFtuAsSeen();
        setShowQRScanFirstTime(false);
    }

    public void firstTimeScanContinue() {
        Ensighten.evaluateEvent(this, "firstTimeScanContinue", null);
        markScanFtuAsSeen();
        checkPaymentAndContinue();
    }

    @Bindable
    public boolean getShowPickupLocation() {
        Ensighten.evaluateEvent(this, "getShowPickupLocation", null);
        return !this.mOrder.isDelivery();
    }

    @Bindable
    public boolean getShowDeliveryLocation() {
        Ensighten.evaluateEvent(this, "getShowDeliveryLocation", null);
        return this.mOrder.isDelivery();
    }

    private void setDeliveryHeaderText(CustomerAddress customerAddress, Date currentDate, Date deliveryDate) {
        Ensighten.evaluateEvent(this, "setDeliveryHeaderText", new Object[]{customerAddress, currentDate, deliveryDate});
        String headerString = "";
        if (this.mOrder.isNormalOrder()) {
            Order deliveryOrder = OrderingManager.getInstance().getCurrentOrder();
            if (deliveryOrder != null) {
                String time = setAsapDeliveryDate(deliveryOrder.getDeliveryStore());
                headerString = this.mContext.getString(C2658R.string.estimated_delivery_range_5, new Object[]{"<b>" + time + "</b>", "<b>" + customerAddress.getFullAddress() + "</b>"});
            } else {
                headerString = this.mContext.getString(C2658R.string.estimated_delivery_range_4, new Object[]{"<b>" + customerAddress.getFullAddress() + "</b>"});
            }
        } else {
            headerString = ((((((this.mContext.getString(C2658R.string.delivery_label_deliver1) + " <b>") + UIUtils.formatDeliveryTime(this.mContext, currentDate, deliveryDate, true)) + "</b> ") + this.mContext.getString(C2658R.string.delivery_label_to)) + " <b>") + customerAddress.getFullAddress()) + "</b>";
            headerString = this.mContext.getString(C2658R.string.estimated_delivery_range_2, new Object[]{"<b>" + deliveryTime + "</b>", "<b>" + customerAddress.getFullAddress() + "</b>"});
        }
        this.mDeliveryHeaderText = Html.fromHtml(headerString);
        notifyPropertyChanged(5);
    }

    private String setAsapDeliveryDate(Store store) {
        Ensighten.evaluateEvent(this, "setAsapDeliveryDate", new Object[]{store});
        String time = this.mContext.getString(C2658R.string.edt_minutes, new Object[]{Integer.valueOf(30)});
        if (store == null) {
            return time;
        }
        Date nowInStoreTime = UIUtils.getDateFromISO8601(store.getNowInStoreLocalTime(), TimeZone.getDefault());
        Date edtInStoreTime = UIUtils.getDateFromISO8601(store.getExpectedDeliveryTime());
        if (Configuration.getSharedInstance().getBooleanForKey("interface.showEstimatedDeliveryTimeInMinutes")) {
            return UIUtils.formatDeliveryTimeInMinutes(this.mContext, nowInStoreTime, edtInStoreTime);
        }
        return time;
    }

    public Spanned getDeliveryHeaderText() {
        Ensighten.evaluateEvent(this, "getDeliveryHeaderText", null);
        return this.mDeliveryHeaderText;
    }

    private void setStoreName(String storeName) {
        Ensighten.evaluateEvent(this, "setStoreName", new Object[]{storeName});
        this.mStoreName = storeName;
        notifyPropertyChanged(47);
    }

    public String getStoreName() {
        Ensighten.evaluateEvent(this, "getStoreName", null);
        return this.mStoreName;
    }

    @Bindable
    public boolean getShowDeliveryFee() {
        Ensighten.evaluateEvent(this, "getShowDeliveryFee", null);
        return this.mDeliveryFee > 0.0d;
    }

    private void setDeliveryFee(double deliveryFee) {
        Ensighten.evaluateEvent(this, "setDeliveryFee", new Object[]{new Double(deliveryFee)});
        this.mDeliveryFee = deliveryFee;
        notifyPropertyChanged(25);
        notifyPropertyChanged(4);
    }

    @Bindable
    public String getDeliveryFee() {
        Ensighten.evaluateEvent(this, "getDeliveryFee", null);
        return formatCurrencyValue(this.mDeliveryFee);
    }

    @Bindable
    public boolean getShowSubtotal() {
        Ensighten.evaluateEvent(this, "getShowSubtotal", null);
        return true;
    }

    private void setSubtotal(double subtotal) {
        Ensighten.evaluateEvent(this, "setSubtotal", new Object[]{new Double(subtotal)});
        this.mSubtotal = subtotal;
        notifyPropertyChanged(39);
        notifyPropertyChanged(48);
    }

    @Bindable
    public String getSubtotal() {
        Ensighten.evaluateEvent(this, "getSubtotal", null);
        return formatCurrencyValue(this.mSubtotal);
    }

    @Bindable
    public boolean getShowBagCharge() {
        Ensighten.evaluateEvent(this, "getShowBagCharge", null);
        return this.mBagCharge != 0.0d;
    }

    private void setBagCharge(double bagCharge) {
        Ensighten.evaluateEvent(this, "setBagCharge", new Object[]{new Double(bagCharge)});
        this.mBagCharge = bagCharge;
        notifyPropertyChanged(23);
        notifyPropertyChanged(1);
    }

    @Bindable
    public String getBagCharge() {
        Ensighten.evaluateEvent(this, "getBagCharge", null);
        return formatCurrencyValue(this.mBagCharge);
    }

    @Bindable
    public boolean getShowDiscount() {
        Ensighten.evaluateEvent(this, "getShowDiscount", null);
        return this.mDiscount != 0.0d;
    }

    private void setDiscount(double discount) {
        Ensighten.evaluateEvent(this, "setDiscount", new Object[]{new Double(discount)});
        this.mDiscount = discount;
        notifyPropertyChanged(28);
        notifyPropertyChanged(6);
    }

    @Bindable
    public String getDiscount() {
        Ensighten.evaluateEvent(this, "getDiscount", null);
        return formatCurrencyValue(this.mDiscount);
    }

    @Bindable
    public boolean getShowTax() {
        Ensighten.evaluateEvent(this, "getShowTax", null);
        if (Configuration.getSharedInstance().getBooleanForKey("modules.ordering.doNotShowTaxWithTotal", false)) {
            return false;
        }
        return true;
    }

    public void setTax(double tax) {
        Ensighten.evaluateEvent(this, "setTax", new Object[]{new Double(tax)});
        this.mTax = tax;
        notifyPropertyChanged(49);
    }

    @Bindable
    public String getTax() {
        Ensighten.evaluateEvent(this, "getTax", null);
        return formatCurrencyValue(this.mTax);
    }

    @Bindable
    public String getTotalCalories() {
        Ensighten.evaluateEvent(this, "getTotalCalories", null);
        return AppUtils.getEnergyTextForOrder(this.mOrder, OrderUtils.getTotalEnergyUnit(this.mOrder));
    }

    public void setTotal(double total) {
        Ensighten.evaluateEvent(this, "setTotal", new Object[]{new Double(total)});
        this.mTotal = total;
        notifyPropertyChanged(52);
    }

    @Bindable
    public String getTotal() {
        Ensighten.evaluateEvent(this, "getTotal", null);
        return formatCurrencyValue(this.mTotal);
    }

    @Bindable
    public boolean getShowPaymentContainer() {
        Ensighten.evaluateEvent(this, "getShowPaymentContainer", null);
        return !ConfigurationUtils.isOneTimePaymentFlow();
    }

    @Bindable
    public boolean getEnablePaymentContainer() {
        Ensighten.evaluateEvent(this, "getEnablePaymentContainer", null);
        return !this.mOrder.isZeroPriced();
    }

    public void setPaymentCard(PaymentCard paymentCard) {
        Ensighten.evaluateEvent(this, "setPaymentCard", new Object[]{paymentCard});
        this.mOrder.setPayment(OrderPayment.fromPaymentCard(paymentCard));
        this.mOrder.setPaymentMode(PaymentMode.Credit);
        this.mOrder.setPaymentCard(paymentCard);
        if (TextUtils.isEmpty(paymentCard.getNickName())) {
            this.mOrder.setPaymentMethodDisplayName(paymentCard.getAlias());
        } else {
            this.mOrder.setPaymentMethodDisplayName(paymentCard.getNickName());
        }
        notifyPropertyChanged(16);
        notifyPropertyChanged(7);
    }

    public void setCashPayment() {
        Ensighten.evaluateEvent(this, "setCashPayment", null);
        this.mOrder.setPayment(OrderPayment.fromCashPayment(Integer.valueOf(this.mCashPaymentMethodId)));
        this.mOrder.setPaymentMode(PaymentMode.Cash);
        this.mOrder.setPaymentMethodDisplayName(this.mContext.getString(C2658R.string.cash));
        notifyPropertyChanged(16);
        notifyPropertyChanged(7);
    }

    public void setAlipayPayment() {
        Ensighten.evaluateEvent(this, "setAlipayPayment", null);
        this.mOrder.setPayment(OrderPayment.fromCashPayment(Integer.valueOf(5)));
        this.mOrder.setPaymentMode(PaymentMode.ThirdPart);
        this.mOrder.setPaymentMethodDisplayName(this.mContext.getString(C2658R.string.alipay));
        notifyPropertyChanged(16);
        notifyPropertyChanged(7);
    }

    public void continueWithLargeOrder() {
        Ensighten.evaluateEvent(this, "continueWithLargeOrder", null);
        checkOrderErrors();
    }

    @Bindable
    public String getPaymentMethodName() {
        Ensighten.evaluateEvent(this, "getPaymentMethodName", null);
        String paymentMethodDisplayName = this.mOrder.getPaymentMethodDisplayName();
        return paymentMethodDisplayName != null ? paymentMethodDisplayName : this.mContext.getString(C2658R.string.choose_payment_label);
    }

    @Bindable
    public boolean getShowNoPaymentRequired() {
        Ensighten.evaluateEvent(this, "getShowNoPaymentRequired", null);
        return this.mOrder.isZeroPriced();
    }

    @Bindable
    public boolean getShowInvoice() {
        Ensighten.evaluateEvent(this, "getShowInvoice", null);
        return Configuration.getSharedInstance().getBooleanForKey("modules.ordering.requestTaxInvoice") && this.mOrder.isDelivery();
    }

    @Bindable
    public boolean getInvoiceEnabled() {
        Ensighten.evaluateEvent(this, "getInvoiceEnabled", null);
        return this.mOrder.invoiceRequested();
    }

    public void setInvoiceEnabled(boolean activated) {
        Ensighten.evaluateEvent(this, "setInvoiceEnabled", new Object[]{new Boolean(activated)});
        this.mOrder.setInvoiceRequested(activated);
        notifyPropertyChanged(12);
    }

    @Bindable
    public String getPayer() {
        Ensighten.evaluateEvent(this, "getPayer", null);
        return this.mOrder.getCompanyName();
    }

    public void setPayer(String payer) {
        Ensighten.evaluateEvent(this, "setPayer", new Object[]{payer});
        this.mOrder.setCompanyName(payer);
        notifyPropertyChanged(15);
    }

    @Bindable
    public boolean getShowOrderRemark() {
        Ensighten.evaluateEvent(this, "getShowOrderRemark", null);
        return this.mOrder.isDelivery();
    }

    @Bindable
    public boolean getRemarkEnabled() {
        Ensighten.evaluateEvent(this, "getRemarkEnabled", null);
        return this.mOrder.isOrderRemarkAvailable();
    }

    public void setRemarkEnabled(boolean activated) {
        Ensighten.evaluateEvent(this, "setRemarkEnabled", new Object[]{new Boolean(activated)});
        this.mOrder.setOrderRemarkAvailable(activated);
        notifyPropertyChanged(22);
    }

    public String getRemark() {
        Ensighten.evaluateEvent(this, "getRemark", null);
        return this.mOrder.getOrderRemarkString();
    }

    public void setRemark(String remark) {
        Ensighten.evaluateEvent(this, "setRemark", new Object[]{remark});
        this.mOrder.setOrderRemark(remark);
        notifyPropertyChanged(21);
    }

    @Bindable
    public boolean getShowUnavailablePODs() {
        Ensighten.evaluateEvent(this, "getShowUnavailablePODs", null);
        return !TextUtils.isEmpty(this.mUnavailablePODsMessage);
    }

    @Bindable
    public String getUnavailablePODsMessage() {
        Ensighten.evaluateEvent(this, "getUnavailablePODsMessage", null);
        return this.mUnavailablePODsMessage;
    }

    @Bindable
    public boolean getShowTimeRestrictionWarning() {
        Ensighten.evaluateEvent(this, "getShowTimeRestrictionWarning", null);
        return !TextUtils.isEmpty(this.mTimeRestrictionWarning);
    }

    private void setTimeRestrictionWarning(String timeRestrictionWarning) {
        Ensighten.evaluateEvent(this, "setTimeRestrictionWarning", new Object[]{timeRestrictionWarning});
        this.mTimeRestrictionWarning = timeRestrictionWarning;
        notifyPropertyChanged(42);
        notifyPropertyChanged(51);
    }

    @Bindable
    public String getTimeRestrictionWarning() {
        Ensighten.evaluateEvent(this, "getTimeRestrictionWarning", null);
        return this.mTimeRestrictionWarning;
    }

    @Bindable
    public boolean getEnableContinueButton() {
        Ensighten.evaluateEvent(this, "getEnableContinueButton", null);
        return isContinueEnabled();
    }

    @Bindable
    public String getContinueButtonText() {
        Ensighten.evaluateEvent(this, "getContinueButtonText", null);
        if (this.mComingFromBagCharges) {
            return this.mContext.getString(C2658R.string.checkout_place_order);
        }
        if (this.mOrder.isDelivery()) {
            return this.mContext.getString(C2658R.string.submit_order);
        }
        return this.mContext.getString(C2658R.string.checkin_tutorial_continue_button);
    }

    private void setLoading(boolean loading) {
        Ensighten.evaluateEvent(this, "setLoading", new Object[]{new Boolean(loading)});
        this.mLoading = loading;
        notifyPropertyChanged(13);
    }

    @Bindable
    public boolean getIsLoading() {
        Ensighten.evaluateEvent(this, "getIsLoading", null);
        return this.mLoading;
    }

    private void setShowQRScanFirstTime(boolean showQRScanFirstTime) {
        Ensighten.evaluateEvent(this, "setShowQRScanFirstTime", new Object[]{new Boolean(showQRScanFirstTime)});
        this.mShowQRScanFirstTime = showQRScanFirstTime;
        notifyPropertyChanged(38);
    }

    @Bindable
    public boolean getShowQRScanFirstTime() {
        Ensighten.evaluateEvent(this, "getShowQRScanFirstTime", null);
        return this.mShowQRScanFirstTime;
    }

    private String formatCurrencyValue(double value) {
        Ensighten.evaluateEvent(this, "formatCurrencyValue", new Object[]{new Double(value)});
        return UIUtils.getLocalizedCurrencyFormatter().format(value);
    }

    private void setupPickup() {
        Ensighten.evaluateEvent(this, "setupPickup", null);
        Store currentStore = this.mCustomerModule.getCurrentStore();
        if (currentStore == null) {
            return;
        }
        if (currentStore.getStoreFavoriteName() != null) {
            setStoreName(currentStore.getStoreFavoriteName());
        } else {
            setStoreName(currentStore.getAddress1());
        }
    }

    private void setupDelivery() {
        Ensighten.evaluateEvent(this, "setupDelivery", null);
        DeliveryModule deliveryModule = (DeliveryModule) ModuleManager.getModule(DeliveryModule.NAME);
        if (this.mOrder.getDeliveryDate() != null) {
            setDeliveryHeaderText(this.mOrder.getDeliveryAddress(), this.mOrder.getDeliveryStore().getCurrentDate(), this.mOrder.getDeliveryDate());
            return;
        }
        deliveryModule.getDeliveryStore(this.mOrder.getDeliveryAddress(), this.mOrder.getDeliveryDate(), this.mCustomerModule.getCurrentProfile(), this.mDeliveryStoreListener);
    }

    public void setupPaymentModes() {
        Ensighten.evaluateEvent(this, "setupPaymentModes", null);
        OrderingModule orderingModule = (OrderingModule) ModuleManager.getModule("ordering");
        if (this.mOrder.getPaymentCard() == null) {
            List<PaymentCard> cardItems = this.mCustomerModule.getCurrentProfile().getCardItems();
            if (!ListUtils.isEmpty(cardItems)) {
                for (PaymentCard paymentCard : cardItems) {
                    if (paymentCard.isPreferred().booleanValue()) {
                        setPaymentCard(paymentCard);
                    }
                }
            }
        }
        orderingModule.getPaymentMethods(this.mPaymentMethodListener);
    }

    private void checkUnavailablePODs() {
        Ensighten.evaluateEvent(this, "checkUnavailablePODs", null);
        if (this.mOrder != null && ListUtils.isNotEmpty(this.mOrder.getProducts())) {
            List<String> unavailable = PODUtils.getOrderUnavailablePODs();
            if (unavailable.size() > 0) {
                PODUtils.getUnavailablePODMessage(unavailable, this.mContext.getResources());
            }
        }
    }

    private void checkTimeRestrictions() {
        Ensighten.evaluateEvent(this, "checkTimeRestrictions", null);
        StringBuilder builder = new StringBuilder(this.mContext.getString(C2658R.string.text_limited_availability_of));
        boolean hasTimeRestrictions = false;
        if (this.mOrder != null) {
            for (OrderProduct product : this.mOrder.getProducts()) {
                if (product.getProduct().getTimeRestriction() != null) {
                    builder.append(String.format(" %s - %s ", new Object[]{product.getProduct().getTimeRestriction().getFromTime(), product.getProduct().getTimeRestriction().getToTime()}));
                    hasTimeRestrictions = true;
                }
            }
        }
        if (hasTimeRestrictions) {
            setTimeRestrictionWarning(builder.toString());
        }
    }

    private void totalize() {
        Ensighten.evaluateEvent(this, "totalize", null);
        OrderingManager.getInstance().totalize(OrderManager.getInstance().getCurrentStore(), this.mTotalizeListener);
    }

    private void setupTotals() {
        Ensighten.evaluateEvent(this, "setupTotals", null);
        OrderResponse totalizeResponse = this.mOrder.getTotalizeResult();
        if (totalizeResponse != null) {
            double deliveryFee = safeDouble(totalizeResponse.getDeliveryFee());
            setDeliveryFee(deliveryFee);
            double subtotal = safeDouble(totalizeResponse.getOrderValue());
            OrderManager orderManager = OrderManager.getInstance();
            if (orderManager.allowBagCharges()) {
                OrderProduct bagProduct = orderManager.getBagProductInOrder();
                if (bagProduct != null) {
                    double bagChargeValue = ProductUtils.getProductTotalPrice(bagProduct);
                    if (subtotal >= bagChargeValue) {
                        subtotal -= bagChargeValue;
                    }
                    setBagCharge(bagChargeValue);
                }
            }
            setSubtotal(subtotal);
            double discount = safeDouble(totalizeResponse.getTotalDiscount());
            setDiscount(totalizeResponse.getTotalDiscount().doubleValue());
            double tax = safeDouble(totalizeResponse.getTotalTax());
            setTax(tax);
            double totalValue = safeDouble(Double.valueOf(((subtotal + deliveryFee) - discount) + tax));
            setTotal(totalValue);
            OrderingModule orderingModule = (OrderingModule) ModuleManager.getModule("ordering");
            if (totalValue > 0.0d || discount < this.mOrder.getTotalValue() || !orderingModule.allowZeroPricedOrder()) {
                this.mView.setZeroPricedOrder(false);
                this.mOrder.setZeroPriced(false);
                notifyPropertyChanged(7);
                notifyPropertyChanged(9);
                notifyPropertyChanged(34);
            } else {
                this.mView.setZeroPricedOrder(true);
                this.mOrder.setZeroPriced(true);
                notifyPropertyChanged(7);
                notifyPropertyChanged(9);
                notifyPropertyChanged(34);
            }
            setLoading(false);
        }
    }

    private double safeDouble(Double source) {
        Ensighten.evaluateEvent(this, "safeDouble", new Object[]{source});
        return source == null ? 0.0d : source.doubleValue();
    }

    private void checkOrder() {
        Ensighten.evaluateEvent(this, "checkOrder", null);
        this.mOrder = OrderingManager.getInstance().getCurrentOrder();
        if (isLargeOrder() && this.mOrder.isDelivery() && Configuration.getSharedInstance().getBooleanForKey("interface.showLargerOrderNotification")) {
            this.mView.showLargeOrderWarning();
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
        String errorMessage = null;
        if (ListUtils.isEmpty(offerErrors)) {
            for (OrderOffer orderOffer : orderingManager.getCurrentOrder().getOffers()) {
                if (this.mOrder.isDelivery() && !orderOffer.getOffer().isDeliveryOffer()) {
                    errorMessage = this.mContext.getString(C2658R.string.offer_unavailable_for_pod_short, new Object[]{this.mContext.getString(C2658R.string.delivery)});
                } else if (!(this.mOrder.isDelivery() || orderOffer.getOffer().isPickupOffer())) {
                    errorMessage = this.mContext.getString(C2658R.string.offer_unavailable_for_pod_short, new Object[]{this.mContext.getString(C2658R.string.pickup)});
                }
            }
        } else {
            int offerError = ((Integer) offerErrors.get(0)).intValue() * -1;
            errorMessage = this.mContext.getResources().getString(this.mContext.getResources().getIdentifier(String.format(Locale.getDefault(), "ecp_error_%d", new Object[]{Integer.valueOf(offerError)}), "string", this.mContext.getPackageName()));
        }
        if (errorMessage != null) {
            this.mView.showFatalError(errorMessage);
        }
    }

    private void checkProductErrors() {
        Ensighten.evaluateEvent(this, "checkProductErrors", null);
        OrderingManager orderingManager = OrderingManager.getInstance();
        int productError = orderingManager.getErrorCode();
        if (productError != 0) {
            int errorType = -1;
            switch (productError) {
                case OrderResponse.PRODUCT_UNAVAILABLE_AT_RESTAURANT_CODE /*-1035*/:
                case OrderResponse.PRODUCT_UNAVAILABLE_CODE /*-1023*/:
                    errorType = 2;
                    break;
            }
            List<Integer> outOfStockProductCodes = new ArrayList();
            outOfStockProductCodes.addAll(OrderUtils.getProblematicProductCodes(orderingManager.getCurrentOrder().getTotalizeResult(), this.mOrder));
            outOfStockProductCodes.addAll(OrderUtils.getProblematicOfferCodes(orderingManager.getCurrentOrder().getTotalizeResult(), this.mOrder));
            orderingManager.getCurrentOrder().getTotalizeResult().setProductsOutOfStock(outOfStockProductCodes);
            orderingManager.setProductErrorCode(productError);
            if (errorType != -1) {
                this.mView.showOrderErrors(errorType, productError, orderingManager.getProblematicProductsCodes(), orderingManager.shouldHidePositive());
            }
        }
    }

    private void checkPaymentAndContinue() {
        Ensighten.evaluateEvent(this, "checkPaymentAndContinue", null);
        Analytics.track(AnalyticType.Event, new ArgBuilder().setCategory("/checkout/restaurant").setAction("On click").setLabel("Continue").build());
        if (isContinueEnabled()) {
            completeOrderAndNavigate();
        } else {
            this.mView.showNoPaymentSelectedError();
        }
    }

    public void checkIfUserHasMoreThanMaxCards() {
        Ensighten.evaluateEvent(this, "checkIfUserHasMoreThanMaxCards", null);
        if (Configuration.getSharedInstance().getBooleanForKey("interface.ordering.enforceMaxCards") && this.mCustomerModule.getCurrentProfile() != null && this.mCustomerModule.getCurrentProfile().getCardItems() != null && this.mCustomerModule.getCurrentProfile().getCardItems().size() > this.mCustomerModule.getMaxAllowedCards()) {
            this.mView.showMaxCardsAlert();
        }
    }

    private boolean isContinueEnabled() {
        Ensighten.evaluateEvent(this, "isContinueEnabled", null);
        return this.mOrder.getPayment() != null || this.mOrder.isZeroPriced() || ConfigurationUtils.isOneTimePaymentFlow();
    }

    private void completeOrderAndNavigate() {
        boolean shouldSkipPickupScreen = false;
        Ensighten.evaluateEvent(this, "completeOrderAndNavigate", null);
        List<StoreCapability> availableCapabilities = new StoreCapabilties(OrderManager.getInstance().getCurrentStore().getPODs()).filterAvailableCapabilities();
        if (ListUtils.isEmpty(availableCapabilities) || (availableCapabilities.size() == 1 && ((StoreCapability) availableCapabilities.get(0)).isHasScanner())) {
            shouldSkipPickupScreen = true;
        }
        if (shouldSkipPickupScreen && shouldShowQRScanFirstTime()) {
            setShowQRScanFirstTime(true);
            return;
        }
        OrderingManager.getInstance().updateTender();
        SparseArray custom = new SparseArray();
        custom.put(55, String.valueOf(this.mCashPaymentMethodId));
        AnalyticsUtils.trackOnClickEvent(getAnalyticsTitle(), "Check-in at restaurant", custom);
        navigateToCheckin(shouldSkipPickupScreen);
    }

    private void navigateToCheckin(boolean shouldSkipPickupScreen) {
        Ensighten.evaluateEvent(this, "navigateToCheckin", new Object[]{new Boolean(shouldSkipPickupScreen)});
        if (ConfigurationUtils.isInterimCheckinFlow() && !this.mComingFromBagCharges) {
            this.mView.startInterinCheckinFlow();
        } else if (!shouldSkipPickupScreen && !this.mComingFromBagCharges) {
            this.mView.showPickupMethodSelector();
        } else if (ConfigurationUtils.isOneTimePaymentFlow()) {
            this.mView.startOneTimePaymentCheckinFlow();
        } else {
            this.mView.startRegularCheckinFlow();
        }
    }

    private boolean shouldShowQRScanFirstTime() {
        Ensighten.evaluateEvent(this, "shouldShowQRScanFirstTime", null);
        return (!LocalDataManager.getSharedInstance().isFirstTimeQrScan() || ConfigurationUtils.isInterimCheckinFlow() || this.mOrder.isDelivery()) ? false : true;
    }

    private void markScanFtuAsSeen() {
        Ensighten.evaluateEvent(this, "markScanFtuAsSeen", null);
        LocalDataManager.getSharedInstance().setFirstTimeQrScan(false);
    }

    private String getAnalyticsTitle() {
        Ensighten.evaluateEvent(this, "getAnalyticsTitle", null);
        return this.mContext.getString(C2658R.string.analytics_screen_checkout);
    }
}
