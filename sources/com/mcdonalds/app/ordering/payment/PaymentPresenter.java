package com.mcdonalds.app.ordering.payment;

import android.content.Context;
import android.database.ContentObserver;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.AsyncTask;
import android.os.Handler;
import android.text.TextUtils;
import com.alipay.sdk.app.PayTask;
import com.ensighten.Ensighten;
import com.mcdonalds.app.analytics.datalayer.DataLayerManager;
import com.mcdonalds.app.ordering.OrderUtils;
import com.mcdonalds.app.ordering.OrderingManager;
import com.mcdonalds.app.ordering.alipay.PayResult;
import com.mcdonalds.app.ordering.utils.PODUtils;
import com.mcdonalds.app.util.AnalyticsUtils;
import com.mcdonalds.app.util.ConfigurationUtils;
import com.mcdonalds.app.util.ListUtils;
import com.mcdonalds.app.util.UIUtils;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.customer.CustomerModule;
import com.mcdonalds.sdk.modules.customer.CustomerProfile;
import com.mcdonalds.sdk.modules.models.Choice;
import com.mcdonalds.sdk.modules.models.Order;
import com.mcdonalds.sdk.modules.models.Order.PriceType;
import com.mcdonalds.sdk.modules.models.Order.QRCodeSaleType;
import com.mcdonalds.sdk.modules.models.OrderOffer;
import com.mcdonalds.sdk.modules.models.OrderPayment;
import com.mcdonalds.sdk.modules.models.OrderProduct;
import com.mcdonalds.sdk.modules.models.OrderResponse;
import com.mcdonalds.sdk.modules.models.OrderView;
import com.mcdonalds.sdk.modules.models.PaymentCard;
import com.mcdonalds.sdk.modules.models.PaymentMethod;
import com.mcdonalds.sdk.modules.models.PaymentMethod.PaymentMode;
import com.mcdonalds.sdk.modules.models.PointOfDistribution;
import com.mcdonalds.sdk.modules.models.ProductView;
import com.mcdonalds.sdk.modules.models.TableService;
import com.mcdonalds.sdk.modules.ordering.OrderManager;
import com.mcdonalds.sdk.modules.ordering.OrderingModule;
import com.mcdonalds.sdk.modules.storelocator.Store;
import com.mcdonalds.sdk.modules.storelocator.StoreLocatorModule;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.mcdonalds.sdk.services.data.provider.Contract.CurrentStore;
import com.mcdonalds.sdk.services.log.MCDLog;
import com.newrelic.agent.android.api.p047v2.TraceFieldInterface;
import com.newrelic.agent.android.harvest.type.HarvestErrorCodes;
import com.newrelic.agent.android.instrumentation.AsyncTaskInstrumentation;
import com.newrelic.agent.android.tracing.Trace;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashSet;
import java.util.List;

public class PaymentPresenter extends BaseObservable {
    private AsyncListener<OrderResponse> mCheckinResponseListener = new C241712();
    private String mCode;
    private boolean mComingFromBagCharges;
    private Context mContext;
    private Store mCurrentStore;
    private boolean mCvvDialogShown;
    private long mLastAttemptTime;
    private PaymentCard mOneClickAddedCard;
    private Order mOrder;
    private String mOrigStoreId;
    private String mPassword;
    private AsyncListener<OrderResponse> mPreparePaymentListener = new C241611();
    private boolean mProductsRemoved;
    private boolean mQRCodeScanned;
    private final AsyncListener<CustomerProfile> mRefreshCustomerDataListener = new C24266();
    private int mRetryCheckinCounter;
    private QRCodeSaleType mSaleType;
    private boolean mShowLocationSelection;
    private long mStartTimeToCommunicate;
    private final AsyncListener<List<Store>> mStoreLocatorResponseListener = new C24288();
    ContentObserver mStoreObserver = new ContentObserver(new Handler()) {
        public void onChange(boolean selfChange) {
            Ensighten.evaluateEvent(this, "onChange", new Object[]{new Boolean(selfChange)});
            super.onChange(selfChange);
            PaymentPresenter.this.notifyPropertyChanged(40);
        }
    };
    private boolean mTableServiceSelected;
    private final AsyncListener<OrderResponse> mTotalizeListener = new C241510();
    private final AsyncListener<CustomerProfile> mUpdateCardsListener = new C24277();
    private boolean mUpdatedProducts;
    private PaymentView mView;
    private AsyncListener<PaymentCard> paymentDataCheckListener = new C24222();

    /* renamed from: com.mcdonalds.app.ordering.payment.PaymentPresenter$10 */
    class C241510 implements AsyncListener<OrderResponse> {
        C241510() {
        }

        public void onResponse(OrderResponse response, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            UIUtils.stopActivityIndicator();
            if (exception == null) {
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.payment.PaymentPresenter", "access$200", new Object[]{PaymentPresenter.this}).setTotalizeResult(response);
                PaymentPresenter.access$300(PaymentPresenter.this);
                return;
            }
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.payment.PaymentPresenter", "access$100", new Object[]{PaymentPresenter.this}).showFatalError(exception.getLocalizedMessage());
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.payment.PaymentPresenter$11 */
    class C241611 implements AsyncListener<OrderResponse> {
        C241611() {
        }

        public void onResponse(OrderResponse response, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            PaymentPresenter.access$1400(PaymentPresenter.this, response, exception);
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.payment.PaymentPresenter$12 */
    class C241712 implements AsyncListener<OrderResponse> {
        C241712() {
        }

        public void onResponse(OrderResponse response, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            PaymentPresenter.access$1508(PaymentPresenter.this);
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.payment.PaymentPresenter", "access$100", new Object[]{PaymentPresenter.this}).stopActivityIndicator();
            if (response != null && exception == null) {
                PaymentPresenter.access$1600(PaymentPresenter.this);
                if (!(response.getOrderView() == null || response.getOrderView().getPaymentCard() == null)) {
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.payment.PaymentPresenter", "access$200", new Object[]{PaymentPresenter.this}).setPaymentMethodDisplayName(response.getOrderView().getPaymentCard().getNickName());
                }
                if (OrderingManager.getInstance().isLargeOrder(response.getOrderView())) {
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.payment.PaymentPresenter", "access$100", new Object[]{PaymentPresenter.this}).showLargeOrderAlert();
                } else {
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.payment.PaymentPresenter", "access$100", new Object[]{PaymentPresenter.this}).continueToOrderSummary();
                }
            } else if (exception == null || exception.getErrorCode() != OrderResponse.ORDER_NO_PAYMENT_REGISTERED) {
                int status = Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.payment.PaymentPresenter", "access$1800", new Object[]{PaymentPresenter.this});
                if (response != null) {
                    int errorCode = response.getErrorCode();
                    if (errorCode == OrderResponse.ORDER_CAN_NOT_BE_PLACED || errorCode == OrderResponse.PRODUCT_PRICE_CHANGED || errorCode == OrderResponse.PRODUCT_SELECT_ANOTHER_PAYMENT_METHOD) {
                        AnalyticsUtils.trackEvent("Error", "On Check In", "Payment");
                    } else if (errorCode == OrderResponse.ORDER_NO_PAYMENT_REGISTERED && Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.payment.PaymentPresenter", "access$1500", new Object[]{PaymentPresenter.this}) < 2) {
                        PaymentPresenter.access$1700(PaymentPresenter.this);
                        return;
                    } else if (errorCode == HarvestErrorCodes.NSURLErrorCannotConnectToHost) {
                        if (status == 1) {
                            Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.payment.PaymentPresenter", "access$100", new Object[]{PaymentPresenter.this}).showPaymentError(Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.payment.PaymentPresenter", "access$700", new Object[]{PaymentPresenter.this}).getString(C2658R.string.tick_tock), Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.payment.PaymentPresenter", "access$700", new Object[]{PaymentPresenter.this}).getString(C2658R.string.error_alipay_hint));
                            return;
                        } else if (status == 2) {
                            Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.payment.PaymentPresenter", "access$100", new Object[]{PaymentPresenter.this}).showPaymentError(Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.payment.PaymentPresenter", "access$700", new Object[]{PaymentPresenter.this}).getString(C2658R.string.tick_tock), Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.payment.PaymentPresenter", "access$700", new Object[]{PaymentPresenter.this}).getString(C2658R.string.error_credit_hint));
                            return;
                        }
                    }
                }
                if (status == 1) {
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.payment.PaymentPresenter", "access$100", new Object[]{PaymentPresenter.this}).showFatalError(Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.payment.PaymentPresenter", "access$700", new Object[]{PaymentPresenter.this}).getString(C2658R.string.tick_tock), Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.payment.PaymentPresenter", "access$700", new Object[]{PaymentPresenter.this}).getString(C2658R.string.error_alipay_hint));
                } else if (status == 2) {
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.payment.PaymentPresenter", "access$100", new Object[]{PaymentPresenter.this}).showFatalError(Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.payment.PaymentPresenter", "access$700", new Object[]{PaymentPresenter.this}).getString(C2658R.string.tick_tock), Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.payment.PaymentPresenter", "access$700", new Object[]{PaymentPresenter.this}).getString(C2658R.string.error_credit_hint));
                } else {
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.payment.PaymentPresenter", "access$100", new Object[]{PaymentPresenter.this}).showFatalError(exception.getLocalizedMessage());
                }
            } else if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.payment.PaymentPresenter", "access$1500", new Object[]{PaymentPresenter.this}) < 2) {
                PaymentPresenter.access$1700(PaymentPresenter.this);
            } else {
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.payment.PaymentPresenter", "access$100", new Object[]{PaymentPresenter.this}).showFatalError(Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.payment.PaymentPresenter", "access$700", new Object[]{PaymentPresenter.this}).getString(C2658R.string.alert_error_title), exception.getLocalizedMessage());
            }
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.payment.PaymentPresenter$13 */
    class C241813 implements Runnable {
        C241813() {
        }

        public void run() {
            Ensighten.evaluateEvent(this, "run", null);
            PaymentPresenter.this.checkin(false);
            PaymentPresenter.this.setPaymentNewCardStub(true);
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.payment.PaymentPresenter$1 */
    class C24201 implements AsyncListener<Object> {

        /* renamed from: com.mcdonalds.app.ordering.payment.PaymentPresenter$1$1 */
        class C24211 implements AsyncListener<Void> {
            C24211() {
            }

            public void onResponse(Void response, AsyncToken token, AsyncException exception) {
                Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.payment.PaymentPresenter", "access$100", new Object[]{PaymentPresenter.this}).stopActivityIndicator();
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.payment.PaymentPresenter", "access$200", new Object[]{PaymentPresenter.this}).setPreparePaymentResult(null);
                PaymentPresenter.access$300(PaymentPresenter.this);
            }
        }

        C24201() {
        }

        public void onResponse(Object response, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            if (!Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.payment.PaymentPresenter", "access$000", new Object[]{PaymentPresenter.this})) {
                PaymentPresenter.access$002(PaymentPresenter.this, true);
                OrderManager.updateProductsForOrder(new C24211());
            }
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.payment.PaymentPresenter$2 */
    class C24222 implements AsyncListener<PaymentCard> {
        C24222() {
        }

        public void onResponse(PaymentCard response, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            if (exception == null || ConfigurationUtils.isOneClickPaymentFlow()) {
                if (response != null) {
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.payment.PaymentPresenter", "access$200", new Object[]{PaymentPresenter.this}).setPaymentMethodDisplayName(response.getNickName());
                    PaymentPresenter.access$402(PaymentPresenter.this, response);
                }
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.payment.PaymentPresenter", "access$200", new Object[]{PaymentPresenter.this}).getPayment().setNewCardStub(false);
                PaymentPresenter.this.checkin(false);
                return;
            }
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.payment.PaymentPresenter", "access$100", new Object[]{PaymentPresenter.this}).showFatalError(exception.getLocalizedMessage());
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.payment.PaymentPresenter$3 */
    class C24233 implements Runnable {
        C24233() {
        }

        public void run() {
            Ensighten.evaluateEvent(this, "run", null);
            PaymentPresenter.access$500(PaymentPresenter.this);
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.payment.PaymentPresenter$4 */
    class C24244 implements AsyncListener<List<PaymentMethod>> {
        C24244() {
        }

        public void onResponse(List<PaymentMethod> paymentMethods, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{paymentMethods, token, exception});
            if (paymentMethods != null) {
                LinkedHashSet<PaymentMode> paymentModes = new LinkedHashSet();
                int size = paymentMethods.size();
                for (int i = 0; i < size; i++) {
                    PaymentMode paymentMode = ((PaymentMethod) paymentMethods.get(i)).getPaymentMode();
                    if (paymentMode != PaymentMode.Cash) {
                        paymentModes.add(paymentMode);
                    }
                }
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.payment.PaymentPresenter", "access$100", new Object[]{PaymentPresenter.this}).showPaymentSelection(paymentModes);
            }
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.payment.PaymentPresenter", "access$100", new Object[]{PaymentPresenter.this}).stopActivityIndicator();
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.payment.PaymentPresenter$5 */
    class C24255 extends AsyncTask<Void, Void, PayResult> implements TraceFieldInterface {
        public Trace _nr_trace;

        public void _nr_setTrace(Trace trace) {
            try {
                this._nr_trace = trace;
            } catch (Exception e) {
            }
        }

        C24255() {
        }

        /* Access modifiers changed, original: protected|varargs */
        public PayResult doInBackground(Void... arg) {
            Ensighten.evaluateEvent(this, "doInBackground", new Object[]{arg});
            return Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.payment.PaymentPresenter", "access$600", new Object[]{PaymentPresenter.this});
        }

        /* Access modifiers changed, original: protected */
        public void onPostExecute(PayResult result) {
            Ensighten.evaluateEvent(this, "onPostExecute", new Object[]{result});
            if (result == null) {
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.payment.PaymentPresenter", "access$100", new Object[]{PaymentPresenter.this}).showPaymentError("", Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.payment.PaymentPresenter", "access$700", new Object[]{PaymentPresenter.this}).getString(C2658R.string.ecp_error_default));
            } else if (result.isSuccess()) {
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.payment.PaymentPresenter", "access$200", new Object[]{PaymentPresenter.this}).setAlipayResult(result.getResult().replaceAll("\\\\", "").replaceAll("\"", ""));
                OrderingManager.getInstance().checkIn(Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.payment.PaymentPresenter", "access$200", new Object[]{PaymentPresenter.this}), Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.payment.PaymentPresenter", "access$800", new Object[]{PaymentPresenter.this}), Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.payment.PaymentPresenter", "access$900", new Object[]{PaymentPresenter.this}), Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.payment.PaymentPresenter", "access$1000", new Object[]{PaymentPresenter.this}));
            } else if (result.getResultStatus() == null || !result.getResultStatus().equals("6001")) {
                String memo = result.getMemo();
                if (memo == null || memo.isEmpty()) {
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.payment.PaymentPresenter", "access$100", new Object[]{PaymentPresenter.this}).showPaymentError("", Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.payment.PaymentPresenter", "access$700", new Object[]{PaymentPresenter.this}).getString(C2658R.string.ecp_error_default));
                } else {
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.payment.PaymentPresenter", "access$100", new Object[]{PaymentPresenter.this}).showPaymentError("", memo);
                }
            } else {
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.payment.PaymentPresenter", "access$100", new Object[]{PaymentPresenter.this}).showPaymentError(Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.payment.PaymentPresenter", "access$700", new Object[]{PaymentPresenter.this}).getString(C2658R.string.tick_tock), Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.payment.PaymentPresenter", "access$700", new Object[]{PaymentPresenter.this}).getString(C2658R.string.ecp_error_6006));
            }
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.payment.PaymentPresenter$6 */
    class C24266 implements AsyncListener<CustomerProfile> {
        C24266() {
        }

        public void onResponse(CustomerProfile profile, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{profile, token, exception});
            PaymentPresenter.access$1100(PaymentPresenter.this, Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.payment.PaymentPresenter", "access$400", new Object[]{PaymentPresenter.this}).getIdentifier());
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.payment.PaymentPresenter$7 */
    class C24277 implements AsyncListener<CustomerProfile> {
        C24277() {
        }

        public void onResponse(CustomerProfile response, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            if (exception == null) {
                MCDLog.temp("Payments updated");
            } else {
                MCDLog.temp("Payments not updated");
            }
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.payment.PaymentPresenter$8 */
    class C24288 implements AsyncListener<List<Store>> {
        C24288() {
        }

        public void onResponse(List<Store> response, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            if (exception == null && ListUtils.isNotEmpty(response)) {
                if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.payment.PaymentPresenter", "access$1200", new Object[]{PaymentPresenter.this}).getStoreId() != ((Store) response.get(0)).getStoreId()) {
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.payment.PaymentPresenter", "access$100", new Object[]{PaymentPresenter.this}).showRestaurantMismatchError();
                }
                PaymentPresenter.access$1202(PaymentPresenter.this, (Store) response.get(0));
                ((CustomerModule) ModuleManager.getModule(CustomerModule.NAME)).setCurrentStore(Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.payment.PaymentPresenter", "access$1200", new Object[]{PaymentPresenter.this}));
                DataLayerManager.getInstance().setStore(Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.payment.PaymentPresenter", "access$1200", new Object[]{PaymentPresenter.this}));
                return;
            }
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.payment.PaymentPresenter", "access$200", new Object[]{PaymentPresenter.this}).setStoreId(Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.payment.PaymentPresenter", "access$1300", new Object[]{PaymentPresenter.this}));
            OrderingManager.getInstance().updateCurrentOrder(Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.payment.PaymentPresenter", "access$200", new Object[]{PaymentPresenter.this}));
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.payment.PaymentPresenter", "access$100", new Object[]{PaymentPresenter.this}).showInvalidRestaurantError();
        }
    }

    static /* synthetic */ boolean access$002(PaymentPresenter x0, boolean x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.payment.PaymentPresenter", "access$002", new Object[]{x0, new Boolean(x1)});
        x0.mUpdatedProducts = x1;
        return x1;
    }

    static /* synthetic */ void access$1100(PaymentPresenter x0, Integer x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.payment.PaymentPresenter", "access$1100", new Object[]{x0, x1});
        x0.setPreferredCard(x1);
    }

    static /* synthetic */ Store access$1202(PaymentPresenter x0, Store x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.payment.PaymentPresenter", "access$1202", new Object[]{x0, x1});
        x0.mCurrentStore = x1;
        return x1;
    }

    static /* synthetic */ void access$1400(PaymentPresenter x0, OrderResponse x1, AsyncException x2) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.payment.PaymentPresenter", "access$1400", new Object[]{x0, x1, x2});
        x0.processPreparePaymentResponse(x1, x2);
    }

    static /* synthetic */ int access$1508(PaymentPresenter x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.payment.PaymentPresenter", "access$1508", new Object[]{x0});
        int i = x0.mRetryCheckinCounter;
        x0.mRetryCheckinCounter = i + 1;
        return i;
    }

    static /* synthetic */ void access$1600(PaymentPresenter x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.payment.PaymentPresenter", "access$1600", new Object[]{x0});
        x0.updatePayments();
    }

    static /* synthetic */ void access$1700(PaymentPresenter x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.payment.PaymentPresenter", "access$1700", new Object[]{x0});
        x0.checkinAfterDelay();
    }

    static /* synthetic */ void access$300(PaymentPresenter x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.payment.PaymentPresenter", "access$300", new Object[]{x0});
        x0.finishCheckin();
    }

    static /* synthetic */ PaymentCard access$402(PaymentPresenter x0, PaymentCard x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.payment.PaymentPresenter", "access$402", new Object[]{x0, x1});
        x0.mOneClickAddedCard = x1;
        return x1;
    }

    static /* synthetic */ void access$500(PaymentPresenter x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.payment.PaymentPresenter", "access$500", new Object[]{x0});
        x0.preparePaymentAndCheckin();
    }

    public PaymentPresenter(Context context, PaymentView view) {
        this.mContext = context;
        this.mView = view;
        this.mOrder = OrderingManager.getInstance().getCurrentOrder();
        this.mCurrentStore = OrderManager.getInstance().getCurrentStore();
    }

    public void setComingFromBagCharges(boolean comingFromBagCharges) {
        Ensighten.evaluateEvent(this, "setComingFromBagCharges", new Object[]{new Boolean(comingFromBagCharges)});
        this.mComingFromBagCharges = comingFromBagCharges;
    }

    public void initialize() {
        Ensighten.evaluateEvent(this, "initialize", null);
        this.mCvvDialogShown = false;
        if (this.mOrder.isDelivery() || this.mComingFromBagCharges) {
            setShowLocationSelection(false);
            preparePaymentAndCheckin();
        } else if (this.mQRCodeScanned) {
            setShowLocationSelection(true);
        } else {
            scanQRCode();
        }
    }

    private void setShowLocationSelection(boolean showLocationSelection) {
        Ensighten.evaluateEvent(this, "setShowLocationSelection", new Object[]{new Boolean(showLocationSelection)});
        this.mShowLocationSelection = showLocationSelection;
        notifyPropertyChanged(32);
    }

    @Bindable
    public boolean getShowLocationSelection() {
        Ensighten.evaluateEvent(this, "getShowLocationSelection", null);
        return this.mShowLocationSelection;
    }

    @Bindable
    public boolean getShowTableService() {
        Ensighten.evaluateEvent(this, "getShowTableService", null);
        TableService tableService = null;
        if (this.mCurrentStore != null) {
            tableService = this.mCurrentStore.getTableService();
        }
        return tableService != null && tableService.isEnablePOSTableService() && this.mOrder.getTotalValue() >= tableService.getMinimumPurchaseAmount() && this.mOrder.getPaymentMode() != PaymentMode.Cash;
    }

    public void setQRCodeScanned(String code) {
        Ensighten.evaluateEvent(this, "setQRCodeScanned", new Object[]{code});
        try {
            this.mCode = code;
            this.mQRCodeScanned = true;
            this.mStartTimeToCommunicate = Calendar.getInstance().getTimeInMillis();
            this.mLastAttemptTime = Calendar.getInstance().getTimeInMillis();
            int storeId = Integer.parseInt(code.substring(0, 10));
            if (Integer.parseInt(this.mOrder.getStoreId()) == storeId) {
                finishCheckin();
            } else {
                handleStoreMismatch(storeId);
            }
        } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
            this.mView.showInvalidQRCodeError();
        }
    }

    public void eatIn() {
        Ensighten.evaluateEvent(this, "eatIn", null);
        AnalyticsUtils.trackOnClickEvent(getAnalyticsTitle(), "Eat In");
        this.mSaleType = QRCodeSaleType.EatIn;
        this.mTableServiceSelected = false;
        totalizePayment();
    }

    public void takeOut() {
        Ensighten.evaluateEvent(this, "takeOut", null);
        AnalyticsUtils.trackOnClickEvent(getAnalyticsTitle(), "Take Out");
        this.mOrder.setPriceType(PriceType.TakeOut);
        this.mSaleType = QRCodeSaleType.TakeOut;
        this.mTableServiceSelected = false;
        OrderManager orderManager = OrderManager.getInstance();
        if (orderManager.allowBagCharges()) {
            if (orderManager.isBagChargeAdded()) {
                OrderManager.getInstance().cleanBagsFromOrder();
            }
            this.mView.continueToBagCharges();
            return;
        }
        preparePaymentAndCheckin();
        setShowLocationSelection(false);
    }

    public void tableServices() {
        Ensighten.evaluateEvent(this, "tableServices", null);
        AnalyticsUtils.trackOnClickEvent(getAnalyticsTitle(), "Table Service");
        this.mSaleType = QRCodeSaleType.EatIn;
        this.mTableServiceSelected = true;
        totalizePayment();
    }

    public void setCVVEntered(String cvv) {
        Ensighten.evaluateEvent(this, "setCVVEntered", new Object[]{cvv});
        this.mOrder.getPayment().setCVV(cvv);
        this.mCvvDialogShown = true;
        preparePaymentAndCheckin();
    }

    public void setPasswordEntered(String password) {
        Ensighten.evaluateEvent(this, "setPasswordEntered", new Object[]{password});
        this.mPassword = password;
        preparePaymentAndCheckin();
    }

    public void cashNotAcceptedAtCurbsideErrorResolved() {
        Ensighten.evaluateEvent(this, "cashNotAcceptedAtCurbsideErrorResolved", null);
        updatePaymentType();
    }

    public void paymentSelected() {
        Ensighten.evaluateEvent(this, "paymentSelected", null);
        if (OrderingManager.getInstance().getCurrentOrder().isDelivery()) {
            OrderingManager.getInstance().updateTender();
        }
        preparePaymentAndCheckin();
    }

    public void paymentSelected(PaymentCard paymentCard) {
        Ensighten.evaluateEvent(this, "paymentSelected", new Object[]{paymentCard});
        this.mOrder.setPayment(OrderPayment.fromPaymentCard(paymentCard));
        this.mOrder.setPaymentCard(paymentCard);
        finishCheckin();
    }

    public void thirdPartyFinished() {
        Ensighten.evaluateEvent(this, "thirdPartyFinished", null);
        checkin(false);
    }

    public void tableServicesFinished(boolean success) {
        Ensighten.evaluateEvent(this, "tableServicesFinished", new Object[]{new Boolean(success)});
        if (success) {
            checkin(true);
        }
        this.mTableServiceSelected = false;
    }

    public void outOfStockErrorResolved(int productErrorCode) {
        Ensighten.evaluateEvent(this, "outOfStockErrorResolved", new Object[]{new Integer(productErrorCode)});
        if (productErrorCode != OrderResponse.PRODUCT_PRICE_CHANGED) {
            this.mOrder.getPayment().setOrderPaymentId(null);
            totalize();
        } else if (this.mProductsRemoved) {
            preparePaymentAndCheckin();
            this.mProductsRemoved = false;
        } else if (this.mTableServiceSelected) {
            this.mView.continueToTableServices();
        } else {
            checkin(true);
        }
    }

    public void restaurantMismatchResolved() {
        Ensighten.evaluateEvent(this, "restaurantMismatchResolved", null);
        this.mView.showActivityIndicator(C2658R.string.label_progress_loading);
        this.mUpdatedProducts = false;
        CustomerModule customerModule = (CustomerModule) ModuleManager.getModule(CustomerModule.NAME);
        customerModule.getCatalogUpdated(customerModule.getCurrentProfile(), new C24201());
    }

    private String getAnalyticsTitle() {
        Ensighten.evaluateEvent(this, "getAnalyticsTitle", null);
        return this.mContext.getString(C2658R.string.analytics_screen_checkout_order_check_in);
    }

    private boolean isOrderAvailableAtPOD() {
        Ensighten.evaluateEvent(this, "isOrderAvailableAtPOD", null);
        if (this.mCode == null) {
            return true;
        }
        int podId;
        if (OrderingManager.getInstance().getCurrentOrder().isDelivery()) {
            podId = 3;
        } else {
            podId = Integer.parseInt(this.mCode.substring(10, 12));
        }
        return PODUtils.validateQRCodePOD(podId, this.mOrder);
    }

    private void scanQRCode() {
        Ensighten.evaluateEvent(this, "scanQRCode", null);
        this.mView.scanQRCode();
    }

    private void handleStoreMismatch(int storeId) {
        Ensighten.evaluateEvent(this, "handleStoreMismatch", new Object[]{new Integer(storeId)});
        this.mOrigStoreId = this.mOrder.getStoreId();
        this.mOrder.setStoreId(storeId);
        this.mOrder.setNeedsUpdatedRecipes(true);
        OrderingManager.getInstance().updateCurrentOrder(this.mOrder);
        this.mView.showActivityIndicator(C2658R.string.label_progress_loading);
        ((StoreLocatorModule) ModuleManager.getModule(StoreLocatorModule.NAME)).getStoreForId(String.valueOf(storeId), this.mStoreLocatorResponseListener);
    }

    private void totalizePayment() {
        Ensighten.evaluateEvent(this, "totalizePayment", null);
        OrderManager orderManager = OrderManager.getInstance();
        if (orderManager.allowBagCharges() && orderManager.isBagChargeAdded()) {
            OrderManager.getInstance().cleanBagsFromOrder();
            totalize();
            return;
        }
        preparePaymentAndCheckin();
    }

    private void totalize() {
        Ensighten.evaluateEvent(this, "totalize", null);
        this.mView.showActivityIndicator(C2658R.string.label_progress_loading);
        ((OrderingModule) ModuleManager.getModule("ordering")).totalize(this.mTotalizeListener);
    }

    private void finishCheckin() {
        Ensighten.evaluateEvent(this, "finishCheckin", null);
        if (this.mCode == null) {
            setShowLocationSelection(true);
            return;
        }
        this.mCode = this.mCode.replaceAll("\r\n", "");
        int podInt = Integer.parseInt(this.mCode.substring(10, 12));
        if (PointOfDistribution.values().length > podInt && this.mOrder.getPayment() != null) {
            this.mOrder.getPayment().setPOD(PointOfDistribution.values()[podInt]);
        }
        if (this.mCode.length() == 16) {
            int saleTypeInt = Integer.parseInt(this.mCode.substring(15));
            if (this.mOrder.getTotalizeBeforeCheckin() != null && this.mOrder.getTotalizeResult() != null && this.mOrder.getTotalizeBeforeCheckin().getTotalValue().doubleValue() != this.mOrder.getTotalizeResult().getTotalValue().doubleValue()) {
                ArrayList<String> codes = new ArrayList();
                codes.add(getPriceChangeProductCodes(this.mOrder.getTotalizeResult()).toString());
                this.mView.showOrderErrors(4, OrderResponse.PRODUCT_PRICE_CHANGED, codes, null, false);
            } else if (saleTypeInt >= 0 && saleTypeInt < QRCodeSaleType.values().length) {
                if (this.mSaleType == null) {
                    this.mSaleType = QRCodeSaleType.values()[saleTypeInt];
                }
                switch (this.mSaleType) {
                    case EatIn:
                        this.mOrder.setPriceType(PriceType.EatIn);
                        preparePaymentAndCheckin();
                        return;
                    case TakeOut:
                        this.mOrder.setPriceType(PriceType.TakeOut);
                        preparePaymentAndCheckin();
                        return;
                    case EatInTakeOut:
                        if (!this.mOrder.isDelivery()) {
                            setShowLocationSelection(true);
                            return;
                        }
                        return;
                    case EatInTakeOutOther:
                        if (!this.mOrder.isDelivery()) {
                            setShowLocationSelection(true);
                            return;
                        }
                        return;
                    case EatInOther:
                        this.mOrder.setPriceType(PriceType.EatIn);
                        preparePaymentAndCheckin();
                        return;
                    case TakeOutOther:
                        this.mOrder.setPriceType(PriceType.TakeOut);
                        preparePaymentAndCheckin();
                        return;
                    default:
                        return;
                }
            }
        }
    }

    private void preparePaymentAndCheckin() {
        Ensighten.evaluateEvent(this, "preparePaymentAndCheckin", null);
        this.mView.showActivityIndicator(C2658R.string.dialog_preparing_payment);
        if (this.mOrder.getPayment() == null && ConfigurationUtils.isOneTimePaymentFlow()) {
            this.mView.showPaymentChooser(this.mCode);
            return;
        }
        if (OrderingManager.getInstance().getCurrentOrder().isDelivery()) {
            this.mOrder.getPayment().setPOD(PointOfDistribution.Delivery);
            OrderingManager.getInstance().updateCurrentOrder(this.mOrder);
        }
        OrderingModule orderingModule = (OrderingModule) ModuleManager.getModule("ordering");
        ArrayList<String> problematicProductsCodes = new ArrayList();
        for (OrderProduct orderProduct : this.mOrder.getProducts()) {
            if (!orderingModule.productIsInCurrentStore(orderProduct.getProduct().getExternalId().intValue())) {
                orderProduct.setUnavailable(true);
                if (!problematicProductsCodes.contains(orderProduct.getProductCode())) {
                    problematicProductsCodes.add(orderProduct.getProductCode());
                }
            }
            if (orderProduct.getRealChoices().size() > 0) {
                for (int i = 0; i < orderProduct.getRealChoices().size(); i++) {
                    OrderProduct subChoiceProduct = ((Choice) orderProduct.getRealChoices().get(i)).getSelection();
                    if (subChoiceProduct != null) {
                        OrderUtils.checkProductInCurrentStore(subChoiceProduct, orderProduct, orderingModule, problematicProductsCodes);
                    }
                }
            }
        }
        if (areAllItemsUnavailable(problematicProductsCodes, null)) {
            this.mView.showOrderErrors(3, OrderResponse.PRODUCT_UNAVAILABLE_CODE, problematicProductsCodes, new ArrayList(), false);
            return;
        }
        this.mOrder = OrderUtils.getOrderWithOnlyAvailableItems(this.mOrder);
        OrderingManager.getInstance().preparePayment(this.mOrder, this.mPreparePaymentListener);
    }

    private void processPreparePaymentResponse(OrderResponse response, AsyncException exception) {
        Ensighten.evaluateEvent(this, "processPreparePaymentResponse", new Object[]{response, exception});
        this.mRetryCheckinCounter = 0;
        if (shouldSkipException(exception)) {
            UIUtils.stopActivityIndicator();
            this.mOrder.setPreparePaymentResult(response);
            this.mOrder.getPayment().setOrderPaymentId(response.getOrderPaymentId());
            OrderingManager.getInstance().updateTender();
            boolean requiresPassword = response.getRequiresPassword().booleanValue();
            if (response.getRequiresCVV().booleanValue() && (this.mOrder.getPayment().getCVV() == null || (ConfigurationUtils.shouldAlwaysAskCVV() && !this.mCvvDialogShown))) {
                this.mView.requestCVV(ConfigurationUtils.getCVVMaxLength());
            } else if (requiresPassword && this.mPassword == null) {
                this.mView.requestPassword();
            } else {
                checkErrorsAndCheckIn(response);
            }
        } else if (isDriveThruPOD()) {
            checkErrorsAndCheckIn(response);
        } else if (isUnavailableException(exception)) {
            this.mView.stopActivityIndicator();
            showProductErrors(exception);
        } else {
            this.mView.showFatalError(exception.getLocalizedMessage());
        }
    }

    private boolean shouldSkipException(AsyncException exception) {
        Ensighten.evaluateEvent(this, "shouldSkipException", new Object[]{exception});
        if (exception == null || exception.getErrorCode() == OrderResponse.PRODUCT_OUT_OF_STOCK_CODE || exception.getErrorCode() == OrderResponse.PRODUCT_TIME_RESTRICTION_CODE || exception.getErrorCode() == OrderResponse.PROMOTION_NOT_AVAILABLE_CODE) {
            return true;
        }
        return false;
    }

    private boolean isDriveThruPOD() {
        Ensighten.evaluateEvent(this, "isDriveThruPOD", null);
        return this.mOrder.getPayment() != null && this.mOrder.getPayment().getPOD() == PointOfDistribution.DriveThru;
    }

    private boolean isUnavailableException(AsyncException exception) {
        Ensighten.evaluateEvent(this, "isUnavailableException", new Object[]{exception});
        if (exception.getErrorCode() == OrderResponse.PRODUCT_UNAVAILABLE_AT_RESTAURANT_CODE || exception.getErrorCode() == OrderResponse.PRODUCT_UNAVAILABLE_CODE) {
            return true;
        }
        return false;
    }

    private void showProductErrors(AsyncException exception) {
        Ensighten.evaluateEvent(this, "showProductErrors", new Object[]{exception});
        int productErrorCode = exception.getErrorCode();
        boolean hidePositive = false;
        boolean subProductError = false;
        List<String> unavailableProductCodes = new ArrayList();
        OrderingModule orderingModule = (OrderingModule) ModuleManager.getModule("ordering");
        for (OrderProduct orderProduct : this.mOrder.getProducts()) {
            if (!(orderingModule.productIsInCurrentStore(orderProduct.getProduct().getExternalId().intValue()) || unavailableProductCodes.contains(orderProduct.getProductCode()))) {
                unavailableProductCodes.add(orderProduct.getProductCode());
            }
            if (orderProduct.getRealChoices().size() > 0) {
                subProductError = false;
                for (int i = 0; i < orderProduct.getRealChoices().size(); i++) {
                    OrderProduct subChoiceProduct = ((Choice) orderProduct.getRealChoices().get(i)).getSelection();
                    if (subChoiceProduct != null) {
                        OrderUtils.checkProductInCurrentStore(subChoiceProduct, orderProduct, orderingModule, unavailableProductCodes);
                    }
                }
                if (unavailableProductCodes.size() != 0) {
                    subProductError = true;
                }
            }
        }
        if (subProductError || unavailableProductCodes.size() == this.mOrder.getProducts().size()) {
            hidePositive = true;
        }
        int errorType = 2;
        if (exception.getErrorCode() == OrderResponse.PRODUCT_UNAVAILABLE_CODE) {
            errorType = 3;
        }
        this.mView.showOrderErrors(errorType, productErrorCode, unavailableProductCodes, null, hidePositive);
    }

    private void checkErrorsAndCheckIn(OrderResponse orderResponse) {
        Ensighten.evaluateEvent(this, "checkErrorsAndCheckIn", new Object[]{orderResponse});
        if (!haveErrors(orderResponse) && isNotCurbsideWithCash()) {
            if (this.mTableServiceSelected) {
                this.mView.continueToTableServices();
            } else {
                checkin(true);
            }
        }
    }

    public void paymentInfoEntered(String jsonCardInfo) {
        Ensighten.evaluateEvent(this, "paymentInfoEntered", new Object[]{jsonCardInfo});
        ((CustomerModule) ModuleManager.getModule(CustomerModule.NAME)).getPaymentData(jsonCardInfo, this.paymentDataCheckListener);
    }

    public void setPaymentNewCardStub(boolean value) {
        Ensighten.evaluateEvent(this, "setPaymentNewCardStub", new Object[]{new Boolean(value)});
        this.mOrder.getPayment().setNewCardStub(value);
    }

    private boolean haveErrors(OrderResponse orderResponse) {
        Ensighten.evaluateEvent(this, "haveErrors", new Object[]{orderResponse});
        if (orderResponse == null) {
            return true;
        }
        int i;
        int productError = orderResponse.getErrorCode();
        ArrayList<String> unavailableItems = new ArrayList();
        OrderingModule orderingModule = (OrderingModule) ModuleManager.getModule("ordering");
        for (OrderProduct orderProduct : this.mOrder.getProducts()) {
            if (orderProduct.isUnavailable()) {
                unavailableItems.add(orderProduct.getProductCode());
                productError = OrderResponse.PRODUCT_UNAVAILABLE_CODE;
            }
            for (i = 0; i < orderProduct.getRealChoices().size(); i++) {
                OrderUtils.checkProductInCurrentStore(((Choice) orderProduct.getRealChoices().get(i)).getSelection(), orderProduct, orderingModule, unavailableItems);
            }
        }
        if (productError == 0) {
            if (ConfigurationUtils.shouldSkipOutOfStockErrorHandling(this.mOrder)) {
                return false;
            }
            if (!ListUtils.isEmpty(orderResponse.getPromotionsError())) {
                productError = ((Integer) orderResponse.getPromotionsError().get(0)).intValue();
            } else if (hasPriceChanged(orderResponse)) {
                productError = OrderResponse.PRODUCT_PRICE_CHANGED;
            } else if (isOrderAvailableAtPOD()) {
                return false;
            } else {
                this.mView.showOrderUnavailableAtPODError();
                return true;
            }
        }
        if (ConfigurationUtils.shouldSkipOutOfStockErrorHandling(this.mOrder) && ListUtils.isNotEmpty(orderResponse.getProductsOutOfStock())) {
            orderResponse.setProductsOutOfStock(new ArrayList());
            productError = orderResponse.getErrorCode();
        }
        List<Integer> codes = null;
        List<Integer> offerCodes = null;
        List<Integer> outOfStockProductCodes = new ArrayList();
        if (this.mOrder.isDelivery()) {
            if (orderHasNoDeliveryOffers()) {
                productError = OrderResponse.OFFERS_ERROR_PICKUP_ONLY;
            }
        } else if (orderHasNoPickupOffers()) {
            productError = OrderResponse.OFFERS_ERROR_DELIVERY_ONLY;
        }
        int errorType = -1;
        switch (productError) {
            case OrderResponse.PROMOTION_NOT_AVAILABLE_CODE /*-8015*/:
            case OrderResponse.OFFERS_ERROR_PICKUP_ONLY /*-8003*/:
            case OrderResponse.OFFERS_ERROR_DELIVERY_ONLY /*-8002*/:
                codes = this.mOrder.getTotalizeResult().getPromotionsNotAvailable();
                errorType = 6;
                this.mProductsRemoved = true;
                break;
            case OrderResponse.OFFERS_ERROR /*-8001*/:
                offerCodes = OrderUtils.getProblematicOfferCodes(orderResponse, this.mOrder);
                outOfStockProductCodes.addAll(offerCodes);
                errorType = 1;
                this.mProductsRemoved = true;
                break;
            case OrderResponse.PRODUCT_PRICE_CHANGED /*-6027*/:
                codes = getPriceChangeProductCodes(orderResponse);
                errorType = 4;
                break;
            case OrderResponse.PRODUCT_TIME_RESTRICTION_CODE /*-1080*/:
                codes = this.mOrder.getTotalizeResult().getProductsTimeRestriction();
                offerCodes = this.mOrder.getTotalizeResult().getPromotionsTimeRestriction();
                errorType = 5;
                break;
            case OrderResponse.PRODUCT_OUT_OF_STOCK_CODE /*-1036*/:
                codes = OrderUtils.getProblematicProductCodes(orderResponse, this.mOrder);
                offerCodes = OrderUtils.getProblematicOfferCodes(orderResponse, this.mOrder);
                errorType = 1;
                outOfStockProductCodes.addAll(codes);
                outOfStockProductCodes.addAll(offerCodes);
                this.mProductsRemoved = true;
                break;
            case OrderResponse.PRODUCT_UNAVAILABLE_CODE /*-1023*/:
                codes = this.mOrder.getTotalizeResult().getProductsUnavailable();
                if (codes != null) {
                    codes = new ArrayList();
                }
                codes.addAll(OrderUtils.getProblematicProductCodes(orderResponse, this.mOrder));
                errorType = 2;
                this.mProductsRemoved = true;
                break;
            case 47:
                handleOrderNotReadyError();
                return false;
            case OrderResponse.ORDER_IS_NOT_READY_CODE /*2010*/:
                this.mView.showOrderNotReadyError();
                return false;
        }
        if (errorType == -1) {
            return false;
        }
        ArrayList<String> problematicProductsCodes = new ArrayList();
        if (codes != null) {
            int size = codes.size();
            for (i = 0; i < size; i++) {
                problematicProductsCodes.add(((Integer) codes.get(i)).toString());
            }
        }
        problematicProductsCodes.addAll(unavailableItems);
        ArrayList<String> problematicOffersCodes = new ArrayList();
        if (offerCodes != null) {
            for (Integer offerCode : offerCodes) {
                problematicOffersCodes.add(offerCode.toString());
            }
        }
        if (!(!areAllItemsUnavailable(problematicProductsCodes, problematicOffersCodes) || productError == OrderResponse.PROMOTION_NOT_AVAILABLE_CODE || productError == OrderResponse.PRODUCT_PRICE_CHANGED)) {
            errorType = 3;
        }
        OrderingManager.getInstance().setProductErrorCode(productError);
        if (this.mOrder.getPreparePaymentResult() != null) {
            this.mOrder.setTotalizeResult(this.mOrder.getPreparePaymentResult());
        }
        this.mOrder.setPreparePaymentResult(null);
        this.mOrder.getTotalizeResult().setProductsOutOfStock(outOfStockProductCodes);
        this.mView.showOrderErrors(errorType, productError, problematicProductsCodes, problematicOffersCodes, false);
        return true;
    }

    private boolean areAllItemsUnavailable(ArrayList<String> problematicProductsCodes, ArrayList<String> problematicOffersCodes) {
        Ensighten.evaluateEvent(this, "areAllItemsUnavailable", new Object[]{problematicProductsCodes, problematicOffersCodes});
        int errorCount = OrderUtils.getErrorCount(problematicProductsCodes, this.mOrder);
        if (!ListUtils.isEmpty(problematicOffersCodes)) {
            errorCount += OrderUtils.getErrorOfferCount(problematicOffersCodes, this.mOrder);
        }
        int orderCount = this.mOrder.getProducts().size() + this.mOrder.getOffers().size();
        OrderManager orderManager = OrderManager.getInstance();
        if (orderManager.allowBagCharges() && orderManager.isBagChargeAdded()) {
            orderCount--;
        }
        if (errorCount >= orderCount) {
            return true;
        }
        return false;
    }

    private boolean hasPriceChanged(OrderResponse orderResponse) {
        Ensighten.evaluateEvent(this, "hasPriceChanged", new Object[]{orderResponse});
        if (this.mOrder.isDelivery() || this.mOrder.getTotalizeResult() == null || this.mOrder.getTotalizeResult().getTotalValue() == null) {
            return false;
        }
        if (Math.abs(this.mOrder.getTotalizeResult().getTotalValue().doubleValue() - orderResponse.getTotalValue().doubleValue()) > 0.01d) {
            return true;
        }
        return false;
    }

    private boolean orderHasNoDeliveryOffers() {
        Ensighten.evaluateEvent(this, "orderHasNoDeliveryOffers", null);
        for (OrderOffer orderOffer : this.mOrder.getOffers()) {
            if (!orderOffer.getOffer().isDeliveryOffer()) {
                return true;
            }
        }
        return false;
    }

    private boolean orderHasNoPickupOffers() {
        Ensighten.evaluateEvent(this, "orderHasNoPickupOffers", null);
        for (OrderOffer orderOffer : this.mOrder.getOffers()) {
            if (!orderOffer.getOffer().isPickupOffer()) {
                return true;
            }
        }
        return false;
    }

    private ArrayList<Integer> getPriceChangeProductCodes(OrderResponse orderResponse) {
        Ensighten.evaluateEvent(this, "getPriceChangeProductCodes", new Object[]{orderResponse});
        ArrayList<Integer> problematicProductCodes = new ArrayList();
        if (!(this.mOrder.getTotalizeBeforeCheckin() == null || this.mOrder.getTotalizeBeforeCheckin().getTotalValue() == null)) {
            OrderView responseOrderView = orderResponse.getOrderView();
            OrderView mOrderOrderView = this.mOrder.getTotalizeBeforeCheckin().getOrderView();
            int size = responseOrderView.getProducts().size();
            for (int i = 0; i < size; i++) {
                ProductView productViewAfterCheckin = (ProductView) responseOrderView.getProducts().get(i);
                int productCodeAfterCheckIn = productViewAfterCheckin.getProductCode().intValue();
                double productPriceAfterCheckIn = productViewAfterCheckin.getUnitPrice().doubleValue();
                double discountAmountAfterCheckIn = 0.0d;
                if (!(productViewAfterCheckin.getPromotion() == null || productViewAfterCheckin.getPromotion().getDiscountAmount() == null)) {
                    discountAmountAfterCheckIn = productViewAfterCheckin.getPromotion().getDiscountAmount().doubleValue();
                }
                int prodSize = mOrderOrderView.getProducts().size();
                for (int j = 0; j < prodSize; j++) {
                    ProductView productViewBeforeCheckin = (ProductView) mOrderOrderView.getProducts().get(j);
                    int productCodeBeforeCheckIn = productViewBeforeCheckin.getProductCode().intValue();
                    if (productCodeBeforeCheckIn == productCodeAfterCheckIn) {
                        double productPriceBeforeCheckIn = productViewBeforeCheckin.getUnitPrice().doubleValue();
                        double discountAmountBeforeCheckIn = 0.0d;
                        if (!(productViewBeforeCheckin.getPromotion() == null || productViewBeforeCheckin.getPromotion().getDiscountAmount() == null)) {
                            discountAmountBeforeCheckIn = productViewBeforeCheckin.getPromotion().getDiscountAmount().doubleValue();
                        }
                        if (productPriceBeforeCheckIn != productPriceAfterCheckIn || discountAmountBeforeCheckIn != discountAmountAfterCheckIn) {
                            problematicProductCodes.add(Integer.valueOf(productCodeBeforeCheckIn));
                            break;
                        }
                    }
                }
            }
        }
        return problematicProductCodes;
    }

    private void handleOrderNotReadyError() {
        Ensighten.evaluateEvent(this, "handleOrderNotReadyError", null);
        long timeBetweenAttemptsDTScanFail = Configuration.getSharedInstance().getLongForKey("interface.checkin.timeBetweenAttemptsDTScanFail") * 1000;
        long timeoutDTScanFail = Configuration.getSharedInstance().getLongForKey("interface.checkin.timeoutDTScanFail") * 1000;
        long currentMillis = System.currentTimeMillis();
        if (currentMillis - this.mStartTimeToCommunicate >= timeoutDTScanFail) {
            this.mView.stopActivityIndicator();
            this.mView.showOrderNotReadyToAcceptError();
            return;
        }
        long timeOutLastAttempt = currentMillis - this.mLastAttemptTime;
        this.mLastAttemptTime = currentMillis;
        long diff = timeBetweenAttemptsDTScanFail - timeOutLastAttempt;
        if (diff > 0) {
            new Handler().postDelayed(new C24233(), diff);
        } else {
            preparePaymentAndCheckin();
        }
    }

    private boolean isNotCurbsideWithCash() {
        Ensighten.evaluateEvent(this, "isNotCurbsideWithCash", null);
        if (this.mCode == null) {
            return true;
        }
        try {
            if (Integer.parseInt(this.mCode.substring(12, 13)) != 1 || this.mOrder.getPaymentCard() != null) {
                return true;
            }
            this.mView.showCashNotAcceptedAtCurbsideError();
            return false;
        } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
            this.mView.showInvalidQRCodeError();
            return false;
        }
    }

    private void updatePaymentType() {
        Ensighten.evaluateEvent(this, "updatePaymentType", null);
        this.mView.showActivityIndicator(C2658R.string.retrieving_payment_methods);
        ((OrderingModule) ModuleManager.getModule("ordering")).getPaymentMethods(new C24244());
    }

    public void checkin(boolean checkForThirdPart) {
        String paymentUrl;
        boolean paymentInfoRequired = true;
        Ensighten.evaluateEvent(this, "checkin", new Object[]{new Boolean(checkForThirdPart)});
        this.mView.showActivityIndicator(C2658R.string.dialog_checking_in);
        OrderResponse paymentUrlOrderResponse = this.mOrder.getPreparePaymentResult();
        if (paymentUrlOrderResponse == null) {
            paymentUrlOrderResponse = this.mOrder.getTotalizeResult();
        }
        if (this.mOrder.isZeroPriced() || paymentUrlOrderResponse == null) {
            paymentUrl = "";
        } else {
            paymentUrl = paymentUrlOrderResponse.getPaymentUrl();
        }
        if (!(ConfigurationUtils.isOneClickPaymentFlow() && this.mOrder.getPayment().isNewCardStub())) {
            paymentInfoRequired = false;
        }
        String paymentMtdDisplayName = this.mOrder.getPaymentMethodDisplayName();
        if (paymentInfoRequired) {
            OrderingModule orderingModule = (OrderingModule) ModuleManager.getModule("ordering");
            String registerReturnURL = "";
            if (!(orderingModule == null || this.mOrder.getPayment() == null)) {
                PaymentMethod method = orderingModule.getPaymentMethodForId(this.mOrder.getPayment().getPaymentMethodId());
                if (!(method == null || method.getRegistrationReturnURL() == null)) {
                    registerReturnURL = method.getRegistrationReturnURL();
                }
            }
            this.mView.requestPaymentInfo(paymentUrl, registerReturnURL);
        } else if (paymentMtdDisplayName != null && (paymentMtdDisplayName.equalsIgnoreCase("Alipay") || paymentMtdDisplayName.equalsIgnoreCase("支付寶") || paymentMtdDisplayName.equals(this.mContext.getString(C2658R.string.alipay)))) {
            C24255 c24255 = new C24255();
            Void[] voidArr = new Void[0];
            if (c24255 instanceof AsyncTask) {
                AsyncTaskInstrumentation.execute(c24255, voidArr);
            } else {
                c24255.execute(voidArr);
            }
        } else if (TextUtils.isEmpty(paymentUrl) || !checkForThirdPart) {
            OrderingManager.getInstance().checkIn(this.mOrder, this.mCode, this.mPassword, this.mCheckinResponseListener);
        } else {
            this.mView.openThirdPartyPaymentUrl(paymentUrl);
        }
    }

    private PayResult doAlipayPayment() {
        Ensighten.evaluateEvent(this, "doAlipayPayment", null);
        if (this.mOrder == null || this.mOrder.getPreparePaymentResult() == null) {
            return null;
        }
        String signature = this.mOrder.getPreparePaymentResult().getSignature();
        if (TextUtils.isEmpty(signature)) {
            return null;
        }
        return new PayResult(new PayTask(this.mView.getActivityForAlipay()).pay(signature, true));
    }

    private void updatePayments() {
        Ensighten.evaluateEvent(this, "updatePayments", null);
        CustomerModule customerModule = (CustomerModule) ModuleManager.getModule(CustomerModule.NAME);
        if (this.mOneClickAddedCard != null) {
            customerModule.refreshCustomerData(this.mRefreshCustomerDataListener);
        } else if (this.mOrder.getPaymentCard() != null) {
            setPreferredCard(this.mOrder.getPaymentCard().getIdentifier());
        }
    }

    private void setPreferredCard(Integer identifier) {
        Ensighten.evaluateEvent(this, "setPreferredCard", new Object[]{identifier});
        CustomerModule customerModule = (CustomerModule) ModuleManager.getModule(CustomerModule.NAME);
        List<PaymentCard> cardItems = new ArrayList(customerModule.getCurrentProfile().getCardItems());
        List<PaymentCard> preferredCards = new ArrayList();
        for (PaymentCard cardItem : cardItems) {
            if (identifier.equals(cardItem.getIdentifier())) {
                cardItem.setIsPreferred(Boolean.valueOf(true));
                preferredCards.add(cardItem);
            } else if (cardItem.isPreferred().booleanValue()) {
                cardItem.setIsPreferred(Boolean.valueOf(false));
                preferredCards.add(cardItem);
            }
        }
        customerModule.updatePayments(preferredCards, this.mUpdateCardsListener);
    }

    public void onStart() {
        Ensighten.evaluateEvent(this, "onStart", null);
        this.mContext.getContentResolver().registerContentObserver(CurrentStore.CONTENT_URI, false, this.mStoreObserver);
    }

    public void onStop() {
        Ensighten.evaluateEvent(this, "onStop", null);
        this.mContext.getContentResolver().unregisterContentObserver(this.mStoreObserver);
    }

    private int payStatus() {
        Ensighten.evaluateEvent(this, "payStatus", null);
        String paymentMtdDisplayName = this.mOrder.getPaymentMethodDisplayName();
        if (paymentMtdDisplayName != null && (paymentMtdDisplayName.equalsIgnoreCase("Alipay") || paymentMtdDisplayName.equalsIgnoreCase("支付寶") || paymentMtdDisplayName.equals(this.mContext.getString(C2658R.string.alipay)))) {
            return 1;
        }
        if (paymentMtdDisplayName == null || (!paymentMtdDisplayName.equals("Cash") && !paymentMtdDisplayName.equals("現金"))) {
            return 2;
        }
        return 0;
    }

    private void checkinAfterDelay() {
        Ensighten.evaluateEvent(this, "checkinAfterDelay", null);
        this.mView.showActivityIndicator(C2658R.string.dialog_checking_in);
        new Handler().postDelayed(new C241813(), (long) (Configuration.getSharedInstance().getIntForKey("interface.oneClickErrorDelay") * 1000));
    }

    public void handlePaymentRegistrationError() {
        Ensighten.evaluateEvent(this, "handlePaymentRegistrationError", null);
        if (ConfigurationUtils.isOneClickPaymentFlow()) {
            setPaymentNewCardStub(false);
            checkin(false);
            return;
        }
        this.mView.backToBasket();
        this.mView.finish();
    }
}
