package com.mcdonalds.app.ordering.checkin;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.p001v7.app.AlertDialog;
import android.text.InputFilter;
import android.text.InputFilter.LengthFilter;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import com.alipay.sdk.app.PayTask;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.McDonaldsApplication;
import com.mcdonalds.app.analytics.datalayer.DataLayerManager;
import com.mcdonalds.app.ordering.IntentFragment;
import com.mcdonalds.app.ordering.OrderUtils;
import com.mcdonalds.app.ordering.OrderingManager;
import com.mcdonalds.app.ordering.ThirdPartActivity;
import com.mcdonalds.app.ordering.alert.AlertActivity;
import com.mcdonalds.app.ordering.alert.AlertFragment;
import com.mcdonalds.app.ordering.alipay.PayResult;
import com.mcdonalds.app.ordering.bagcharge.BagChargeActivity;
import com.mcdonalds.app.ordering.basket.BasketActivity;
import com.mcdonalds.app.ordering.basket.BasketFragment;
import com.mcdonalds.app.ordering.preparepayment.PaymentSelectionActivity;
import com.mcdonalds.app.ordering.preparepayment.PreparePaymentActivity;
import com.mcdonalds.app.ordering.summary.OrderSummaryActivity;
import com.mcdonalds.app.ordering.utils.PODUtils;
import com.mcdonalds.app.p043ui.URLNavigationActivity.PermissionListener;
import com.mcdonalds.app.p043ui.URLNavigationFragment;
import com.mcdonalds.app.util.AnalyticsUtils;
import com.mcdonalds.app.util.AppUtils;
import com.mcdonalds.app.util.ConfigurationUtils;
import com.mcdonalds.app.util.ListUtils;
import com.mcdonalds.app.util.UIUtils;
import com.mcdonalds.app.util.UIUtils.MCDAlertDialogBuilder;
import com.mcdonalds.app.util.UIUtils.MCDFullScreenAlertDialogBuilder;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.connectors.middleware.MWException;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.customer.CustomerModule;
import com.mcdonalds.sdk.modules.customer.CustomerProfile;
import com.mcdonalds.sdk.modules.delivery.DeliveryModule;
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
import com.mcdonalds.sdk.modules.models.Pod;
import com.mcdonalds.sdk.modules.models.PointOfDistribution;
import com.mcdonalds.sdk.modules.models.ProductView;
import com.mcdonalds.sdk.modules.models.TableService;
import com.mcdonalds.sdk.modules.ordering.OrderManager;
import com.mcdonalds.sdk.modules.ordering.OrderingModule;
import com.mcdonalds.sdk.modules.storelocator.Store;
import com.mcdonalds.sdk.modules.storelocator.StoreLocatorModule;
import com.mcdonalds.sdk.services.analytics.AnalyticType;
import com.mcdonalds.sdk.services.analytics.Analytics;
import com.mcdonalds.sdk.services.analytics.AnalyticsArgs.ArgBuilder;
import com.mcdonalds.sdk.services.analytics.JiceArgs;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.mcdonalds.sdk.services.data.LocalDataManager;
import com.mcdonalds.sdk.services.log.MCDLog;
import com.newrelic.agent.android.api.p047v2.TraceFieldInterface;
import com.newrelic.agent.android.instrumentation.AsyncTaskInstrumentation;
import com.newrelic.agent.android.tracing.Trace;
import com.tencent.p050mm.sdk.modelpay.PayReq;
import com.tencent.p050mm.sdk.openapi.IWXAPI;
import com.tencent.p050mm.sdk.openapi.WXAPIFactory;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.TimeZone;

public class OrderCheckinFragment extends URLNavigationFragment implements OnClickListener, IntentFragment, QRCodeListener {
    public static String sWeChatPaymentRespString;
    private static final String weChatAppId = McDonaldsApplication.getInstance().getString(C2658R.string.wechat_api_app_id);
    public static int wechatPaymentResult = 1;
    private Handler handler;
    private AsyncListener<OrderResponse> mCheckinResponseListener = new C343016();
    protected String mCode;
    protected CustomerModule mCustomerModule;
    private boolean mCvvDialogShown;
    protected DeliveryModule mDeliveryModule;
    protected AlertDialog mDialog;
    protected View mEatInButton;
    private boolean mIsStartedActivityIndicator;
    private long mLastAttemptTime;
    protected View mMainView;
    protected boolean mMainViewVisible;
    protected Order mOrder;
    private final AsyncListener<OrderResponse> mOrderResponseListener = new C34496();
    private int mOrderStoreId;
    protected OrderingModule mOrderingModule;
    protected String mPassword;
    private boolean mProcessingScannedCode;
    private boolean mProductsRemoved;
    private boolean mRequiresCVV = false;
    private boolean mRequiresPassword = false;
    private QRCodeSaleType mSaleType;
    protected boolean mShouldLaunchQRCodeScanner = true;
    private long mStartTimeToCommunicate;
    private final AsyncListener<Store> mStoreInfoAsyncListener = new C34485();
    protected StoreLocatorModule mStoreLocatorModule;
    private final AsyncListener<List<Store>> mStoreLocatorResponseListener = new C34474();
    protected View mTableServiceButton;
    protected View mTakeOutButton;
    private long mTimeBetweenAttemptsDTScanFail;
    private long mTimeoutDTScanFail;
    private List<String> mUnavailableProductCodes = new ArrayList();
    private final AsyncListener<CustomerProfile> mUpdateCardsListener = new C343418();
    private IWXAPI mWeChatApi;
    private AsyncListener<OrderResponse> preparePaymentListener = new C34529();
    private boolean subProductError;

    /* renamed from: com.mcdonalds.app.ordering.checkin.OrderCheckinFragment$10 */
    class C342310 implements DialogInterface.OnClickListener {
        C342310() {
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{dialogInterface, new Integer(i)});
            dialogInterface.dismiss();
            OrderCheckinFragment.this.startActivity(PreparePaymentActivity.class, "prepare_payment");
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.checkin.OrderCheckinFragment$11 */
    class C342411 implements OnClickListener {
        C342411() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            ((Activity) v.getContext()).onBackPressed();
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.checkin.OrderCheckinFragment$12 */
    class C342512 implements OnClickListener {
        C342512() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            OrderCheckinFragment.this.startActivity(BasketActivity.class, "basket");
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.checkin.OrderCheckinFragment$13 */
    class C342613 implements Runnable {
        C342613() {
        }

        public void run() {
            Ensighten.evaluateEvent(this, "run", null);
            OrderCheckinFragment.this.preparePaymentAndCheckin();
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.checkin.OrderCheckinFragment$14 */
    class C342714 implements Runnable {

        /* renamed from: com.mcdonalds.app.ordering.checkin.OrderCheckinFragment$14$1 */
        class C34281 extends AsyncTask<Void, Void, PayResult> implements TraceFieldInterface {
            public Trace _nr_trace;

            public void _nr_setTrace(Trace trace) {
                try {
                    this._nr_trace = trace;
                } catch (Exception e) {
                }
            }

            C34281() {
            }

            /* Access modifiers changed, original: protected|varargs */
            public PayResult doInBackground(Void... arg) {
                Ensighten.evaluateEvent(this, "doInBackground", new Object[]{arg});
                return Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.checkin.OrderCheckinFragment", "access$700", new Object[]{OrderCheckinFragment.this});
            }

            /* Access modifiers changed, original: protected */
            public void onPostExecute(PayResult result) {
                Ensighten.evaluateEvent(this, "onPostExecute", new Object[]{result});
                if (result == null) {
                    OrderCheckinFragment.access$800(OrderCheckinFragment.this, C2658R.string.ecp_error_default);
                } else if (result.isSuccess()) {
                    OrderCheckinFragment.this.mOrder.setAlipayResult(result.getResult().replaceAll("\\\\", "").replaceAll("\"", ""));
                    OrderingManager.getInstance().checkIn(OrderCheckinFragment.this.mOrder, OrderCheckinFragment.this.mCode, OrderCheckinFragment.this.mPassword, Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.checkin.OrderCheckinFragment", "access$900", new Object[]{OrderCheckinFragment.this}));
                } else if (result.getResultStatus().equals("6001")) {
                    OrderCheckinFragment.access$1000(OrderCheckinFragment.this, OrderCheckinFragment.this.getString(C2658R.string.tick_tock), OrderCheckinFragment.this.getString(C2658R.string.ecp_error_6006));
                } else {
                    String memo = result.getMemo();
                    if (memo == null || memo.isEmpty()) {
                        OrderCheckinFragment.access$800(OrderCheckinFragment.this, C2658R.string.ecp_error_default);
                    } else {
                        OrderCheckinFragment.access$1100(OrderCheckinFragment.this, memo);
                    }
                }
            }
        }

        C342714() {
        }

        public void run() {
            Ensighten.evaluateEvent(this, "run", null);
            UIUtils.startActivityIndicator(OrderCheckinFragment.this.getNavigationActivity(), null, OrderCheckinFragment.this.getString(C2658R.string.dialog_checking_in), false);
            C34281 c34281 = new C34281();
            Void[] voidArr = new Void[0];
            if (c34281 instanceof AsyncTask) {
                AsyncTaskInstrumentation.execute(c34281, voidArr);
            } else {
                c34281.execute(voidArr);
            }
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.checkin.OrderCheckinFragment$15 */
    class C342915 implements DialogInterface.OnClickListener {
        C342915() {
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{dialogInterface, new Integer(i)});
            if (OrderCheckinFragment.this.getActivity() != null) {
                OrderCheckinFragment.this.getActivity().finish();
            }
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.checkin.OrderCheckinFragment$16 */
    class C343016 implements AsyncListener<OrderResponse> {

        /* renamed from: com.mcdonalds.app.ordering.checkin.OrderCheckinFragment$16$1 */
        class C34311 implements DialogInterface.OnClickListener {
            C34311() {
            }

            public void onClick(DialogInterface dialog, int which) {
                Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
                OrderCheckinFragment.this.startActivity(OrderSummaryActivity.class, "order_summary");
            }
        }

        /* renamed from: com.mcdonalds.app.ordering.checkin.OrderCheckinFragment$16$2 */
        class C34322 implements DialogInterface.OnClickListener {
            C34322() {
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                Ensighten.evaluateEvent(this, "onClick", new Object[]{dialogInterface, new Integer(i)});
                dialogInterface.dismiss();
                OrderCheckinFragment.access$1602(OrderCheckinFragment.this, false);
                OrderCheckinFragment.access$1702(OrderCheckinFragment.this, false);
                OrderCheckinFragment.this.getActivity().finish();
                OrderCheckinFragment.this.startActivity(BasketActivity.class, "basket");
            }
        }

        C343016() {
        }

        public void onResponse(OrderResponse response, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            if (OrderCheckinFragment.this.getNavigationActivity() != null) {
                if (exception == null) {
                    Calendar calendar;
                    UIUtils.stopActivityIndicator();
                    if (response.getOrderDate() == null) {
                        calendar = Calendar.getInstance();
                        calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
                        response.setOrderDate(calendar.getTime());
                    }
                    if (OrderingManager.getInstance().getCurrentOrder().isDelivery()) {
                        OrderCheckinFragment.this.mOrder.setCheckoutResult(response);
                    } else {
                        OrderCheckinFragment.this.mOrder.setCheckinResult(response);
                    }
                    Analytics.trackTransaction(response, OrderCheckinFragment.this.mOrder);
                    OrderCheckinFragment.access$1200(OrderCheckinFragment.this);
                    if (!OrderingManager.getInstance().getCurrentOrder().isDelivery() || !response.getOrderView().isIsLargeOrder()) {
                        OrderCheckinFragment.this.continueToOrderSummary();
                    } else if (response.getOrderView().isConfirmationNeeded()) {
                        AppUtils.showLargeOrderAlert(OrderCheckinFragment.this.getNavigationActivity());
                    } else {
                        Date EDT = response.getOrderView().getEstimatedDeliveryTime();
                        calendar = Calendar.getInstance();
                        calendar.setTime(EDT);
                        int hour = calendar.get(11);
                        int minute = calendar.get(12);
                        String time = String.format("%02d", new Object[]{Integer.valueOf(hour)}) + ":" + String.format("%02d", new Object[]{Integer.valueOf(minute)});
                        MCDAlertDialogBuilder.withContext(OrderCheckinFragment.this.getNavigationActivity()).setMessage(OrderCheckinFragment.this.getString(C2658R.string.large_order_edt_changed_alert, time)).setPositiveButton((int) C2658R.string.f6083ok, new C34311()).setCancelable(false).create().show();
                    }
                } else if (response == null || response.getErrorCode() != 47) {
                    int status = Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.checkin.OrderCheckinFragment", "access$1400", new Object[]{OrderCheckinFragment.this});
                    if (response != null) {
                        int errorCode = response.getErrorCode();
                        if (errorCode == OrderResponse.ORDER_CAN_NOT_BE_PLACED || errorCode == OrderResponse.PRODUCT_PRICE_CHANGED || errorCode == OrderResponse.PRODUCT_SELECT_ANOTHER_PAYMENT_METHOD) {
                            AnalyticsUtils.trackEvent("Error", "On Check In", "Payment");
                        } else if (errorCode == -6010) {
                            if (status == 1) {
                                UIUtils.stopActivityIndicator();
                                OrderCheckinFragment.access$1302(OrderCheckinFragment.this, false);
                                OrderCheckinFragment.access$1500(OrderCheckinFragment.this, OrderCheckinFragment.this.getString(C2658R.string.tick_tock), OrderCheckinFragment.this.getString(C2658R.string.error_alipay_hint));
                                return;
                            } else if (status == 2) {
                                UIUtils.stopActivityIndicator();
                                OrderCheckinFragment.access$1302(OrderCheckinFragment.this, false);
                                OrderCheckinFragment.access$1500(OrderCheckinFragment.this, OrderCheckinFragment.this.getString(C2658R.string.tick_tock), OrderCheckinFragment.this.getString(C2658R.string.error_credit_hint));
                                return;
                            }
                        }
                    }
                    UIUtils.stopActivityIndicator();
                    OrderCheckinFragment.access$1302(OrderCheckinFragment.this, false);
                    if (status == 1) {
                        OrderCheckinFragment.access$1500(OrderCheckinFragment.this, OrderCheckinFragment.this.getString(C2658R.string.tick_tock), OrderCheckinFragment.this.getString(C2658R.string.error_alipay_hint));
                    } else if (status == 2) {
                        OrderCheckinFragment.access$1500(OrderCheckinFragment.this, OrderCheckinFragment.this.getString(C2658R.string.tick_tock), OrderCheckinFragment.this.getString(C2658R.string.error_credit_hint));
                    } else {
                        MCDAlertDialogBuilder.withContext(OrderCheckinFragment.this.getNavigationActivity()).setTitle(OrderCheckinFragment.this.getString(C2658R.string.error_checkin)).setMessage(exception.getLocalizedMessage()).setCancelable(false).setPositiveButton(OrderCheckinFragment.this.getString(C2658R.string.button_dismiss), new C34322()).create().show();
                    }
                } else {
                    OrderCheckinFragment.access$1302(OrderCheckinFragment.this, true);
                }
            }
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.checkin.OrderCheckinFragment$17 */
    class C343317 implements DialogInterface.OnClickListener {
        C343317() {
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{dialogInterface, new Integer(i)});
            dialogInterface.dismiss();
            OrderCheckinFragment.this.getActivity().finish();
            OrderCheckinFragment.access$1602(OrderCheckinFragment.this, false);
            OrderCheckinFragment.access$1702(OrderCheckinFragment.this, false);
            OrderCheckinFragment.this.startActivity(BasketActivity.class, "basket");
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.checkin.OrderCheckinFragment$18 */
    class C343418 implements AsyncListener<CustomerProfile> {
        C343418() {
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

    /* renamed from: com.mcdonalds.app.ordering.checkin.OrderCheckinFragment$19 */
    class C343519 implements DialogInterface.OnClickListener {
        C343519() {
        }

        public void onClick(DialogInterface dialog, int whichButton) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(whichButton)});
            dialog.dismiss();
            OrderCheckinFragment.this.getNavigationActivity().finish();
            OrderCheckinFragment.this.startActivity(PreparePaymentActivity.class, "prepare_payment");
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.checkin.OrderCheckinFragment$1 */
    class C34361 implements PermissionListener {
        C34361() {
        }

        public void onRequestPermissionsResult(int requestCode, String permission, int grantResult) {
            Ensighten.evaluateEvent(this, "onRequestPermissionsResult", new Object[]{new Integer(requestCode), permission, new Integer(grantResult)});
            if (grantResult == -1) {
                OrderCheckinFragment.this.getActivity().finish();
            } else {
                OrderCheckinFragment.this.scanQRCode();
            }
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.checkin.OrderCheckinFragment$21 */
    class C343821 implements DialogInterface.OnClickListener {
        C343821() {
        }

        public void onClick(DialogInterface dialog, int whichButton) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(whichButton)});
            dialog.dismiss();
            OrderCheckinFragment.this.getNavigationActivity().finish();
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.checkin.OrderCheckinFragment$23 */
    class C344023 implements DialogInterface.OnClickListener {
        C344023() {
        }

        public void onClick(DialogInterface dialog, int which) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
            dialog.dismiss();
            OrderCheckinFragment.this.getNavigationActivity().onBackPressed();
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.checkin.OrderCheckinFragment$24 */
    class C344124 implements DialogInterface.OnClickListener {
        C344124() {
        }

        public void onClick(DialogInterface dialog, int which) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
            OrderCheckinFragment.this.mDialog.dismiss();
            OrderCheckinFragment.access$1900(OrderCheckinFragment.this);
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.checkin.OrderCheckinFragment$25 */
    class C344225 implements AsyncListener<List<PaymentMethod>> {
        C344225() {
        }

        public void onResponse(List<PaymentMethod> paymentMethods, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{paymentMethods, token, exception});
            if (paymentMethods != null) {
                LinkedHashSet<PaymentMode> mPaymentTypes = new LinkedHashSet();
                int size = paymentMethods.size();
                for (int i = 0; i < size; i++) {
                    PaymentMode paymentMode = ((PaymentMethod) paymentMethods.get(i)).getPaymentMode();
                    if (paymentMode != PaymentMode.Cash) {
                        mPaymentTypes.add(paymentMode);
                    }
                }
                Bundle extras = new Bundle();
                extras.putBoolean("FROM_ORDER_CHECKIN", true);
                extras.putSerializable("payment_types", mPaymentTypes);
                OrderCheckinFragment.this.startActivityForResult(PaymentSelectionActivity.class, "select_payment", extras, 4081);
            }
            UIUtils.stopActivityIndicator();
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.checkin.OrderCheckinFragment$3 */
    class C34453 implements AsyncListener<Object> {

        /* renamed from: com.mcdonalds.app.ordering.checkin.OrderCheckinFragment$3$1 */
        class C34461 implements AsyncListener<Void> {
            C34461() {
            }

            public void onResponse(Void response, AsyncToken token, AsyncException exception) {
                Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
                if (ConfigurationUtils.isRegularPaymentFlow()) {
                    OrderCheckinFragment.access$100(OrderCheckinFragment.this);
                } else {
                    OrderCheckinFragment.this.preparePaymentAndCheckin();
                }
            }
        }

        C34453() {
        }

        public void onResponse(Object response, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            OrderManager.updateProductsForOrder(new C34461());
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.checkin.OrderCheckinFragment$4 */
    class C34474 implements AsyncListener<List<Store>> {
        C34474() {
        }

        public void onResponse(List<Store> response, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            if (exception == null && ListUtils.isNotEmpty(response)) {
                Store newStore = (Store) response.get(0);
                for (Store s : response) {
                    if (s.getStoreId() == Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.checkin.OrderCheckinFragment", "access$200", new Object[]{OrderCheckinFragment.this})) {
                        newStore = s;
                        break;
                    }
                }
                OrderCheckinFragment.this.mCustomerModule.setCurrentStore(newStore);
                OrderCheckinFragment.this.mCustomerModule.updateCurrentStoreInfo(Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.checkin.OrderCheckinFragment", "access$300", new Object[]{OrderCheckinFragment.this}));
                DataLayerManager.getInstance().setStore(newStore);
                return;
            }
            OrderCheckinFragment.access$400(OrderCheckinFragment.this);
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.checkin.OrderCheckinFragment$5 */
    class C34485 implements AsyncListener<Store> {
        C34485() {
        }

        public void onResponse(Store response, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            OrderCheckinFragment.access$400(OrderCheckinFragment.this);
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.checkin.OrderCheckinFragment$6 */
    class C34496 implements AsyncListener<OrderResponse> {
        C34496() {
        }

        public void onResponse(OrderResponse response, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            UIUtils.stopActivityIndicator();
            if (exception != null || response == null) {
                AsyncException.report(exception);
                if (OrderCheckinFragment.this.getNavigationActivity() != null) {
                    OrderCheckinFragment.this.getNavigationActivity().onBackPressed();
                    return;
                }
                return;
            }
            OrderCheckinFragment.this.mOrder.setTotalizeResult(response);
            OrderCheckinFragment.access$500(OrderCheckinFragment.this);
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.checkin.OrderCheckinFragment$7 */
    class C34507 implements DialogInterface.OnClickListener {
        C34507() {
        }

        public void onClick(DialogInterface dialog, int which) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
            dialog.dismiss();
            OrderCheckinFragment.this.getNavigationActivity().onBackPressed();
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.checkin.OrderCheckinFragment$8 */
    class C34518 implements DialogInterface.OnClickListener {
        C34518() {
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{dialogInterface, new Integer(i)});
            dialogInterface.dismiss();
            OrderCheckinFragment.this.startActivity(BasketActivity.class, "basket");
            OrderCheckinFragment.this.getActivity().finish();
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.checkin.OrderCheckinFragment$9 */
    class C34529 implements AsyncListener<OrderResponse> {
        C34529() {
        }

        public void onResponse(OrderResponse response, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            OrderCheckinFragment.access$600(OrderCheckinFragment.this, response, exception);
        }
    }

    static /* synthetic */ void access$000(OrderCheckinFragment x0, View x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.checkin.OrderCheckinFragment", "access$000", new Object[]{x0, x1});
        x0.normalCheckinFlow(x1);
    }

    static /* synthetic */ void access$100(OrderCheckinFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.checkin.OrderCheckinFragment", "access$100", new Object[]{x0});
        x0.displayMethods();
    }

    static /* synthetic */ void access$1000(OrderCheckinFragment x0, String x1, String x2) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.checkin.OrderCheckinFragment", "access$1000", new Object[]{x0, x1, x2});
        x0.showErrorDialog(x1, x2);
    }

    static /* synthetic */ void access$1100(OrderCheckinFragment x0, String x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.checkin.OrderCheckinFragment", "access$1100", new Object[]{x0, x1});
        x0.showErrorDialog(x1);
    }

    static /* synthetic */ void access$1200(OrderCheckinFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.checkin.OrderCheckinFragment", "access$1200", new Object[]{x0});
        x0.updatePayments();
    }

    static /* synthetic */ boolean access$1302(OrderCheckinFragment x0, boolean x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.checkin.OrderCheckinFragment", "access$1302", new Object[]{x0, new Boolean(x1)});
        x0.mIsStartedActivityIndicator = x1;
        return x1;
    }

    static /* synthetic */ void access$1500(OrderCheckinFragment x0, String x1, String x2) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.checkin.OrderCheckinFragment", "access$1500", new Object[]{x0, x1, x2});
        x0.showDialogHint(x1, x2);
    }

    static /* synthetic */ boolean access$1602(OrderCheckinFragment x0, boolean x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.checkin.OrderCheckinFragment", "access$1602", new Object[]{x0, new Boolean(x1)});
        x0.mRequiresPassword = x1;
        return x1;
    }

    static /* synthetic */ boolean access$1702(OrderCheckinFragment x0, boolean x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.checkin.OrderCheckinFragment", "access$1702", new Object[]{x0, new Boolean(x1)});
        x0.mRequiresCVV = x1;
        return x1;
    }

    static /* synthetic */ boolean access$1802(OrderCheckinFragment x0, boolean x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.checkin.OrderCheckinFragment", "access$1802", new Object[]{x0, new Boolean(x1)});
        x0.mCvvDialogShown = x1;
        return x1;
    }

    static /* synthetic */ void access$1900(OrderCheckinFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.checkin.OrderCheckinFragment", "access$1900", new Object[]{x0});
        x0.updatePaymentType();
    }

    static /* synthetic */ void access$400(OrderCheckinFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.checkin.OrderCheckinFragment", "access$400", new Object[]{x0});
        x0.displayRestaurantMismatchFragment();
    }

    static /* synthetic */ void access$500(OrderCheckinFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.checkin.OrderCheckinFragment", "access$500", new Object[]{x0});
        x0.finishCheckin();
    }

    static /* synthetic */ void access$600(OrderCheckinFragment x0, OrderResponse x1, AsyncException x2) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.checkin.OrderCheckinFragment", "access$600", new Object[]{x0, x1, x2});
        x0.processPreparePaymentResponse(x1, x2);
    }

    static /* synthetic */ void access$800(OrderCheckinFragment x0, int x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.checkin.OrderCheckinFragment", "access$800", new Object[]{x0, new Integer(x1)});
        x0.showErrorDialog(x1);
    }

    public String getAnalyticsTitle() {
        Ensighten.evaluateEvent(this, "getAnalyticsTitle", null);
        return getString(C2658R.string.analytics_screen_checkout_order_check_in);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mOrder = OrderingManager.getInstance().getCurrentOrder();
        this.mOrderingModule = (OrderingModule) ModuleManager.getModule("ordering");
        this.mDeliveryModule = (DeliveryModule) ModuleManager.getModule(DeliveryModule.NAME);
        this.mStoreLocatorModule = (StoreLocatorModule) ModuleManager.getModule(StoreLocatorModule.NAME);
        this.mCustomerModule = (CustomerModule) ModuleManager.getModule(CustomerModule.NAME);
        this.mTimeBetweenAttemptsDTScanFail = Configuration.getSharedInstance().getLongForKey("interface.checkin.timeBetweenAttemptsDTScanFail") * 1000;
        this.mTimeoutDTScanFail = Configuration.getSharedInstance().getLongForKey("interface.checkin.timeoutDTScanFail") * 1000;
        this.mCvvDialogShown = false;
        this.mWeChatApi = WXAPIFactory.createWXAPI(getContext(), weChatAppId);
        this.mWeChatApi.registerApp(weChatAppId);
        this.handler = new Handler();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C2658R.layout.fragment_order_location, container, false);
        this.mMainView = view.findViewById(C2358R.C2357id.main_view);
        if (!this.mMainViewVisible || this.mOrder.isDelivery()) {
            this.mMainView.setVisibility(4);
        } else {
            this.mMainView.setVisibility(0);
        }
        this.mTableServiceButton = view.findViewById(C2358R.C2357id.table_service_button);
        this.mTableServiceButton.setOnClickListener(this);
        this.mEatInButton = view.findViewById(C2358R.C2357id.eatin_button);
        this.mEatInButton.setOnClickListener(this);
        this.mTakeOutButton = view.findViewById(C2358R.C2357id.takeout_button);
        this.mTakeOutButton.setOnClickListener(this);
        return view;
    }

    public void onResume() {
        super.onResume();
        if (wechatPaymentResult == 0) {
            if (this.mOrder != null) {
                this.mOrder.setWechatPaymentResult(wechatPaymentResult);
                wechatPaymentResult = 1;
                OrderingManager.getInstance().checkIn(this.mOrder, this.mCode, this.mPassword, this.mCheckinResponseListener);
            }
        } else if (wechatPaymentResult == -2) {
            wechatPaymentResult = 1;
            UIUtils.stopActivityIndicator();
            showErrorDialog(sWeChatPaymentRespString);
        } else if (wechatPaymentResult == -1) {
            wechatPaymentResult = 1;
            UIUtils.stopActivityIndicator();
            showErrorDialog(sWeChatPaymentRespString);
        }
        if (!this.mOrder.isDelivery()) {
            setupTableServiceButton(this.mTableServiceButton, this.mTakeOutButton);
        }
    }

    public void onNewIntent(Intent intent) {
        Ensighten.evaluateEvent(this, "onNewIntent", new Object[]{intent});
        boolean fromBagCharge = false;
        Bundle extras = intent.getExtras();
        if (extras != null) {
            fromBagCharge = extras.getBoolean("FROM_BAG_CHARGE");
        }
        if (fromBagCharge) {
            skipQRAndLocationSelection();
        } else {
            this.mShouldLaunchQRCodeScanner = true;
        }
    }

    public void onDestroy() {
        super.onDestroy();
        UIUtils.stopActivityIndicator();
    }

    public void updateOrder() {
        Ensighten.evaluateEvent(this, "updateOrder", null);
        this.mOrder = OrderingManager.getInstance().getCurrentOrder();
        this.mOrderStoreId = Integer.parseInt(this.mOrder.getStoreId());
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        Ensighten.evaluateEvent(this, "onViewCreated", new Object[]{view, savedInstanceState});
        super.onViewCreated(view, savedInstanceState);
        boolean fromBagCharge = false;
        Bundle extras = getActivity().getIntent().getExtras();
        if (extras != null) {
            fromBagCharge = extras.getBoolean("FROM_BAG_CHARGE");
        }
        if (this.mOrder.isDelivery() || fromBagCharge) {
            skipQRAndLocationSelection();
        } else if (this.mShouldLaunchQRCodeScanner) {
            this.mShouldLaunchQRCodeScanner = false;
            updateOrder();
            scanQRCode();
        } else if (!this.mProcessingScannedCode && !this.mMainViewVisible) {
            getActivity().onBackPressed();
        }
    }

    private void skipQRAndLocationSelection() {
        Ensighten.evaluateEvent(this, "skipQRAndLocationSelection", null);
        this.mMainView.setVisibility(4);
        updateOrder();
        preparePaymentAndCheckin();
    }

    /* Access modifiers changed, original: protected */
    public void scanQRCode() {
        Ensighten.evaluateEvent(this, "scanQRCode", null);
        if (getNavigationActivity().isPermissionGranted("android.permission.CAMERA")) {
            startActivityForResult(QRScanActivity.class, "qrscan", 51);
            return;
        }
        getNavigationActivity().requestPermission("android.permission.CAMERA", 2, C2658R.string.permission_explanation_camera, new C34361());
    }

    public void setupTableServiceButton(View tableServiceButton, View takeOutButton) {
        Ensighten.evaluateEvent(this, "setupTableServiceButton", new Object[]{tableServiceButton, takeOutButton});
        if (tableServiceButton != null) {
            tableServiceButton.setVisibility(8);
            TableService tableService = this.mCustomerModule.getCurrentStore().getTableService();
            if (tableService != null && tableService.isEnablePOSTableService() && this.mOrder.getTotalValue() >= tableService.getMinimumPurchaseAmount() && this.mOrder.getPaymentMode() != PaymentMode.Cash) {
                tableServiceButton.setVisibility(0);
                updateTakeOutButton(takeOutButton);
            }
        }
    }

    public void updateTakeOutButton(View takeOutButton) {
        Ensighten.evaluateEvent(this, "updateTakeOutButton", new Object[]{takeOutButton});
        takeOutButton.setBackgroundResource(C2358R.C2359drawable.button_red);
    }

    public void onClick(final View view) {
        Ensighten.evaluateEvent(this, "onClick", new Object[]{view});
        if (!ConfigurationUtils.isDuplicateOrderCheckinFlow() || !LocalDataManager.getSharedInstance().hasObjectInCache(LocalDataManager.KEY_CHECKIN_TIMER)) {
            normalCheckinFlow(view);
        } else if (ConfigurationUtils.isDuplicateOrderCheckinAllowOrdering()) {
            UIUtils.showCheckinFlowAlert(getContext(), true, null, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
                    OrderCheckinFragment.access$000(OrderCheckinFragment.this, view);
                }
            });
        } else {
            UIUtils.showCheckinFlowAlert(getContext(), false, null, null);
        }
    }

    private void normalCheckinFlow(View view) {
        Ensighten.evaluateEvent(this, "normalCheckinFlow", new Object[]{view});
        if (view == this.mEatInButton) {
            AnalyticsUtils.trackOnClickEvent(getAnalyticsTitle(), "Eat In");
            this.mSaleType = QRCodeSaleType.EatIn;
            totalizePayment();
        } else if (view == this.mTakeOutButton) {
            AnalyticsUtils.trackOnClickEvent(getAnalyticsTitle(), "Take Out");
            this.mOrder.setPriceType(PriceType.TakeOut);
            this.mSaleType = QRCodeSaleType.TakeOut;
            OrderManager orderManager = OrderManager.getInstance();
            if (orderManager.allowBagCharges()) {
                if (orderManager.isBagChargeAdded()) {
                    OrderManager.getInstance().cleanBagsFromOrder();
                }
                proceedToBagCharge();
                return;
            }
            preparePaymentAndCheckin();
            this.mMainView.setVisibility(4);
        } else if (view == this.mTableServiceButton) {
            AnalyticsUtils.trackOnClickEvent(getAnalyticsTitle(), "Table Service");
            startActivityForResult(TableServiceActivity.class, 12303);
        }
    }

    public void totalizePayment() {
        Ensighten.evaluateEvent(this, "totalizePayment", null);
        this.mOrder.setPriceType(PriceType.EatIn);
        this.mMainView.setVisibility(4);
        OrderManager orderManager = OrderManager.getInstance();
        if (orderManager.allowBagCharges() && orderManager.isBagChargeAdded()) {
            OrderManager.getInstance().cleanBagsFromOrder();
            totalize();
            return;
        }
        preparePaymentAndCheckin();
    }

    public void onStop() {
        super.onStop();
        if (this.mDialog != null) {
            this.mDialog.cancel();
        }
        if (this.mWeChatApi != null) {
            this.mWeChatApi.unregisterApp();
        }
    }

    public void doneScanningCode(String data) {
        Ensighten.evaluateEvent(this, "doneScanningCode", new Object[]{data});
        if (getNavigationActivity() != null) {
            this.mCode = data;
            this.mCode = this.mCode.replaceAll("\r\n", "");
            Analytics.track(AnalyticType.Event, new ArgBuilder().setCategory(getAnalyticsTitle()).setAction("On scan").setLabel(this.mCode).addCustom(41, String.valueOf(this.mCode)).build());
            this.mStartTimeToCommunicate = Calendar.getInstance().getTimeInMillis();
            this.mLastAttemptTime = Calendar.getInstance().getTimeInMillis();
            this.mProcessingScannedCode = true;
            try {
                int storeId = parseStoreIdFromQrCode(this.mCode);
                if (this.mOrder.isDelivery() || storeId != this.mOrderStoreId) {
                    if (storeId != this.mOrderStoreId) {
                        handleStoreMismatch(storeId);
                    }
                } else if (ConfigurationUtils.isRegularPaymentFlow()) {
                    displayMethods();
                }
            } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
                showInvalidQRDialiog();
            }
        }
    }

    private void displayMethods() {
        Ensighten.evaluateEvent(this, "displayMethods", null);
        this.mMainViewVisible = true;
        this.mMainView.setVisibility(0);
    }

    /* Access modifiers changed, original: protected */
    public void totalize() {
        Ensighten.evaluateEvent(this, "totalize", null);
        UIUtils.startActivityIndicator(getActivity(), (int) C2658R.string.label_progress_loading);
        this.mOrderingModule.totalize(this.mOrderResponseListener);
    }

    public void resumeFromMismatch() {
        Ensighten.evaluateEvent(this, "resumeFromMismatch", null);
        CustomerModule customerModule = (CustomerModule) ModuleManager.getModule(CustomerModule.NAME);
        customerModule.getCatalogUpdated(customerModule.getCurrentProfile(), new C34453());
    }

    private int parseStoreIdFromQrCode(String code) throws NumberFormatException, StringIndexOutOfBoundsException {
        Ensighten.evaluateEvent(this, "parseStoreIdFromQrCode", new Object[]{code});
        return Integer.parseInt(code.substring(0, 10));
    }

    private void handleStoreMismatch(int storeId) {
        Ensighten.evaluateEvent(this, "handleStoreMismatch", new Object[]{new Integer(storeId)});
        if (getActivity() != null) {
            this.mOrderStoreId = storeId;
            this.mOrder.setStoreId(Integer.toString(storeId));
            this.mOrder.setNeedsUpdatedRecipes(true);
            OrderingManager.getInstance().updateCurrentOrder(this.mOrder);
            UIUtils.startActivityIndicator(getActivity(), (int) C2658R.string.label_progress_loading);
            this.mStoreLocatorModule.getStoreForId(String.valueOf(storeId), this.mStoreLocatorResponseListener);
        }
    }

    private void displayRestaurantMismatchFragment() {
        Ensighten.evaluateEvent(this, "displayRestaurantMismatchFragment", null);
        UIUtils.stopActivityIndicator();
        startActivityForResult(AlertActivity.class, "RestaurantMismatchFragment", 4087);
    }

    private void showInvalidQRDialiog() {
        Ensighten.evaluateEvent(this, "showInvalidQRDialiog", null);
        this.mDialog = MCDAlertDialogBuilder.withContext(getActivity()).setTitle(getString(C2658R.string.alert_error_title)).setMessage(getString(C2658R.string.invalid_qr_code)).setPositiveButton(getString(C2658R.string.f6083ok), new C34507()).setCancelable(false).create();
        DataLayerManager.getInstance().recordError("Must select delivery address before placing order");
        this.mDialog.show();
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

    private void finishCheckin() {
        boolean z = false;
        Ensighten.evaluateEvent(this, "finishCheckin", null);
        if (this.mCode != null) {
            int podInt = Integer.parseInt(this.mCode.substring(10, 12));
            if (PointOfDistribution.values().length > podInt && this.mOrder.getPayment() != null) {
                this.mOrder.getPayment().setPOD(PointOfDistribution.values()[podInt]);
            }
            if (this.mCode.length() == 16) {
                int saleTypeInt = Integer.parseInt(this.mCode.substring(15));
                if (saleTypeInt >= 0 && saleTypeInt < QRCodeSaleType.values().length) {
                    if (this.mSaleType == null) {
                        this.mSaleType = QRCodeSaleType.values()[saleTypeInt];
                    }
                    switch (this.mSaleType) {
                        case EatIn:
                            this.mOrder.setPriceType(PriceType.EatIn);
                            preparePaymentAndCheckin();
                            break;
                        case TakeOut:
                            this.mOrder.setPriceType(PriceType.TakeOut);
                            preparePaymentAndCheckin();
                            break;
                        case EatInTakeOut:
                            if (!this.mOrder.isDelivery()) {
                                this.mMainView.setVisibility(0);
                                break;
                            }
                            break;
                        case EatInTakeOutOther:
                            if (!this.mOrder.isDelivery()) {
                                this.mMainView.setVisibility(0);
                                break;
                            }
                            break;
                        case EatInOther:
                            this.mOrder.setPriceType(PriceType.EatIn);
                            preparePaymentAndCheckin();
                            break;
                        case TakeOutOther:
                            this.mOrder.setPriceType(PriceType.TakeOut);
                            preparePaymentAndCheckin();
                            break;
                    }
                    if (this.mMainView.getVisibility() == 0) {
                        z = true;
                    }
                    this.mMainViewVisible = z;
                }
            }
        } else if (this.mOrder.getTotalizeBeforeCheckin() == null || this.mOrder.getTotalizeResult() == null) {
            displayMethods();
        } else if (!this.mOrder.getTotalizeBeforeCheckin().getTotalValue().equals(this.mOrder.getTotalizeResult().getTotalValue())) {
            startActivityForResult(AlertActivity.class, "check_in_price_different", 21);
        }
    }

    private void proceedToBagCharge() {
        Ensighten.evaluateEvent(this, "proceedToBagCharge", null);
        startActivityForResult(BagChargeActivity.class, 10892);
    }

    public void preparePaymentAndCheckin() {
        Ensighten.evaluateEvent(this, "preparePaymentAndCheckin", null);
        if (this.mOrder.getTotalizeResult() == null) {
            MCDAlertDialogBuilder.withContext(getNavigationActivity()).setTitle(getString(C2658R.string.error_title)).setMessage(getString(C2658R.string.error_generic)).setCancelable(false).setPositiveButton(getString(C2658R.string.button_dismiss), new C34518()).create().show();
            return;
        }
        UIUtils.startActivityIndicator(getNavigationActivity(), getString(C2658R.string.dialog_preparing_payment));
        this.mProcessingScannedCode = true;
        if (this.mOrder.getPayment() != null) {
            this.mOrder.getPayment().setOrderPaymentId(null);
            if (this.mOrder.isZeroPriced()) {
                this.mOrder.getPayment().setPaymentMethodId(0);
            }
            OrderingManager.getInstance().updateCurrentOrder(this.mOrder);
        } else if (this.mOrder.isZeroPriced()) {
            OrderPayment orderPayment = new OrderPayment();
            orderPayment.setPaymentMethodId(0);
            this.mOrder.setPayment(orderPayment);
        }
        if (OrderingManager.getInstance().getCurrentOrder().isDelivery()) {
            this.mOrder.getPayment().setPOD(PointOfDistribution.Delivery);
            OrderingManager.getInstance().updateCurrentOrder(this.mOrder);
        }
        this.mOrderingModule.preparePayment(OrderUtils.getOrderWithOnlyAvailableItems(this.mOrder), this.preparePaymentListener);
    }

    private void processPreparePaymentResponse(OrderResponse response, AsyncException exception) {
        Ensighten.evaluateEvent(this, "processPreparePaymentResponse", new Object[]{response, exception});
        Bundle bundle;
        int i;
        OrderProduct subChoiceProduct;
        if (exception == null || ((exception instanceof MWException) && isaMWException((MWException) exception))) {
            UIUtils.stopActivityIndicator();
            this.mOrder.setPreparePaymentResult(response);
            this.mOrder.getPayment().setOrderPaymentId(response.getOrderPaymentId());
            this.mOrder.getPayment().setPaymentDataId(response.getPaymentDataId().intValue());
            OrderingManager.getInstance().updateTender();
            this.mRequiresPassword = response.getRequiresPassword().booleanValue();
            this.mRequiresCVV = response.getRequiresCVV().booleanValue();
            if (this.mRequiresCVV && (!cvvEntered() || (ConfigurationUtils.shouldAlwaysAskCVV() && !this.mCvvDialogShown))) {
                promptUserForCVV();
            } else if (this.mRequiresPassword && this.mPassword == null) {
                promptUserForPassword();
            } else {
                checkErrorsAndCheckIn(response);
            }
        } else if (this.mOrder.getPayment() != null && this.mOrder.getPayment().getPOD() == PointOfDistribution.DriveThru) {
            checkErrorsAndCheckIn(response);
        } else if (exception.getErrorCode() == OrderResponse.PRODUCT_UNAVAILABLE_AT_RESTAURANT_CODE) {
            bundle = new Bundle();
            bundle.putInt(BasketFragment.PARAMETER_PRODUCT_ERROR_CODE, exception.getErrorCode());
            this.subProductError = false;
            for (OrderProduct orderProduct : this.mOrder.getProducts()) {
                if (!(this.mOrderingModule.productIsInCurrentStore(orderProduct.getProduct().getExternalId().intValue()) || this.mUnavailableProductCodes.contains(orderProduct.getProductCode()))) {
                    this.mUnavailableProductCodes.add(orderProduct.getProductCode());
                }
                if (orderProduct.getRealChoices().size() > 0) {
                    this.subProductError = false;
                    for (i = 0; i < orderProduct.getRealChoices().size(); i++) {
                        subChoiceProduct = ((Choice) orderProduct.getRealChoices().get(i)).getSelection();
                        if (subChoiceProduct != null) {
                            OrderUtils.checkProductInCurrentStore(subChoiceProduct, orderProduct, this.mOrderingModule, this.mUnavailableProductCodes);
                        }
                    }
                    if (this.mUnavailableProductCodes.size() != 0) {
                        this.subProductError = true;
                    }
                }
            }
            if (this.subProductError || this.mUnavailableProductCodes.size() == this.mOrder.getProducts().size()) {
                bundle.putBoolean(AlertFragment.PARAMETER_HIDE_POSITIVE, true);
            }
            bundle.putStringArrayList(BasketFragment.PARAMETER_PROBLEMATIC_PRODUCTS_CODES, (ArrayList) this.mUnavailableProductCodes);
            UIUtils.stopActivityIndicator();
            getActivity().finish();
            startActivityForResult(AlertActivity.class, "check_in_items_unavailable", bundle, 13067);
        } else if (exception.getErrorCode() == OrderResponse.PRODUCT_UNAVAILABLE_CODE) {
            bundle = new Bundle();
            bundle.putInt(BasketFragment.PARAMETER_PRODUCT_ERROR_CODE, exception.getErrorCode());
            for (OrderProduct orderProduct2 : this.mOrder.getProducts()) {
                if (!(this.mOrderingModule.productIsInCurrentStore(orderProduct2.getProduct().getExternalId().intValue()) || this.mUnavailableProductCodes.contains(orderProduct2.getProductCode()))) {
                    this.mUnavailableProductCodes.add(orderProduct2.getProductCode());
                }
                if (orderProduct2.getRealChoices().size() > 0) {
                    this.subProductError = false;
                    for (i = 0; i < orderProduct2.getRealChoices().size(); i++) {
                        subChoiceProduct = ((Choice) orderProduct2.getRealChoices().get(i)).getSelection();
                        if (subChoiceProduct != null) {
                            OrderUtils.checkProductInCurrentStore(subChoiceProduct, orderProduct2, this.mOrderingModule, this.mUnavailableProductCodes);
                        }
                    }
                    if (this.mUnavailableProductCodes.size() != 0) {
                        this.subProductError = true;
                    }
                }
            }
            bundle.putStringArrayList(BasketFragment.PARAMETER_PROBLEMATIC_PRODUCTS_CODES, (ArrayList) this.mUnavailableProductCodes);
            UIUtils.stopActivityIndicator();
            startActivityForResult(AlertActivity.class, "check_in_all_items_unavailable", bundle, 21);
        } else {
            MCDAlertDialogBuilder.withContext(getNavigationActivity()).setTitle(getString(C2658R.string.error_title)).setMessage(exception.getLocalizedMessage()).setCancelable(false).setPositiveButton(getString(C2658R.string.button_dismiss), new C342310()).create().show();
        }
    }

    private boolean isaMWException(MWException exception) {
        Ensighten.evaluateEvent(this, "isaMWException", new Object[]{exception});
        if (exception.getErrorCode() == OrderResponse.PRODUCT_OUT_OF_STOCK_CODE || exception.getErrorCode() == OrderResponse.PRODUCT_TIME_RESTRICTION_CODE || exception.getErrorCode() == OrderResponse.PROMOTION_NOT_AVAILABLE_CODE) {
            return true;
        }
        return false;
    }

    private void checkErrorsAndCheckIn(OrderResponse orderResponse) {
        Ensighten.evaluateEvent(this, "checkErrorsAndCheckIn", new Object[]{orderResponse});
        if (!haveErrors(orderResponse) && isNotCurbsideWithCash()) {
            checkin();
        }
    }

    private boolean haveErrors(OrderResponse orderResponse) {
        Ensighten.evaluateEvent(this, "haveErrors", new Object[]{orderResponse});
        if (orderResponse == null) {
            return true;
        }
        int productError = orderResponse.getErrorCode();
        if (productError == 0) {
            if (ConfigurationUtils.shouldSkipOutOfStockErrorHandling(this.mOrder)) {
                return false;
            }
            if (hasPriceChanged(orderResponse)) {
                productError = OrderResponse.PRODUCT_PRICE_CHANGED;
            } else if (isOrderAvailableAtPOD()) {
                return false;
            } else {
                startActivity(AlertActivity.class, "check_out_pod_unavailable");
                return true;
            }
        }
        if (ConfigurationUtils.shouldSkipOutOfStockErrorHandling(this.mOrder) && ListUtils.isNotEmpty(orderResponse.getProductsOutOfStock())) {
            orderResponse.setProductsOutOfStock(new ArrayList());
            productError = orderResponse.getErrorCode();
        }
        String fragmentName = "";
        List<Integer> codes = null;
        List<Integer> offerCodes = null;
        for (OrderOffer orderOffer : this.mOrder.getOffers()) {
            if (this.mOrder.isDelivery()) {
                if (!orderOffer.getOffer().isDeliveryOffer()) {
                    if (codes == null) {
                        codes = new ArrayList();
                    }
                    codes.add(orderOffer.getOffer().getOfferId());
                }
                if (codes != null) {
                    productError = OrderResponse.OFFERS_ERROR_PICKUP_ONLY;
                    fragmentName = "check_in_offers_not_valid";
                }
            } else if (!this.mOrder.isDelivery()) {
                if (!orderOffer.getOffer().isPickupOffer()) {
                    if (codes == null) {
                        codes = new ArrayList();
                    }
                    codes.add(orderOffer.getOffer().getOfferId());
                }
                if (codes != null) {
                    productError = OrderResponse.OFFERS_ERROR_DELIVERY_ONLY;
                    fragmentName = "check_in_offers_not_valid";
                }
            }
        }
        switch (productError) {
            case OrderResponse.PROMOTION_NOT_AVAILABLE_CODE /*-8015*/:
            case OrderResponse.OFFERS_ERROR_PICKUP_ONLY /*-8003*/:
            case OrderResponse.OFFERS_ERROR_DELIVERY_ONLY /*-8002*/:
                codes = this.mOrder.getTotalizeResult().getPromotionsNotAvailable();
                fragmentName = "check_in_offers_not_valid";
                this.mProductsRemoved = true;
                break;
            case OrderResponse.OFFERS_ERROR /*-8001*/:
                offerCodes = OrderUtils.getProblematicOfferCodes(orderResponse, this.mOrder);
                fragmentName = "check_in_offers_not_valid";
                this.mProductsRemoved = true;
                break;
            case OrderResponse.PRODUCT_PRICE_CHANGED /*-6027*/:
                codes = getPriceChangeProductCodes(orderResponse);
                fragmentName = "check_in_price_different";
                break;
            case OrderResponse.PRODUCT_TIME_RESTRICTION_CODE /*-1080*/:
                codes = orderResponse.getProductsTimeRestriction();
                offerCodes = orderResponse.getPromotionsTimeRestriction();
                fragmentName = "check_in_items_time_restriction";
                break;
            case OrderResponse.PRODUCT_OUT_OF_STOCK_CODE /*-1036*/:
                codes = OrderUtils.getProblematicProductCodes(orderResponse, this.mOrder);
                offerCodes = OrderUtils.getProblematicOfferCodes(orderResponse, this.mOrder);
                fragmentName = "check_in_items_out_of_stock";
                this.mProductsRemoved = true;
                break;
            case OrderResponse.PRODUCT_UNAVAILABLE_CODE /*-1023*/:
                codes = this.mOrder.getTotalizeResult().getProductsUnavailable();
                fragmentName = "check_in_items_unavailable";
                this.mProductsRemoved = true;
                break;
            case 47:
                handleOrderNotReadyError();
                return false;
            case OrderResponse.ORDER_IS_NOT_READY_CODE /*2010*/:
                displayOrderNotReadyDialog();
                return false;
        }
        if (fragmentName.isEmpty()) {
            return false;
        }
        ArrayList<String> problematicProductsCodes = new ArrayList();
        if (codes != null) {
            int size = codes.size();
            for (int i = 0; i < size; i++) {
                problematicProductsCodes.add(((Integer) codes.get(i)).toString());
            }
        }
        ArrayList<String> problematicOffersCodes = new ArrayList();
        if (offerCodes != null) {
            for (Integer offerCode : offerCodes) {
                problematicOffersCodes.add(offerCode.toString());
            }
        }
        if (!(OrderUtils.getErrorCount(problematicProductsCodes, this.mOrder) + OrderUtils.getErrorOfferCount(problematicOffersCodes, this.mOrder) < this.mOrder.getProducts().size() + this.mOrder.getOffers().size() || productError == OrderResponse.PROMOTION_NOT_AVAILABLE_CODE || productError == OrderResponse.PRODUCT_PRICE_CHANGED)) {
            fragmentName = "check_in_all_items_unavailable";
        }
        OrderingManager.getInstance().setProductErrorCode(productError);
        Bundle bundle = new Bundle();
        bundle.putInt(BasketFragment.PARAMETER_PRODUCT_ERROR_CODE, productError);
        bundle.putStringArrayList(BasketFragment.PARAMETER_PROBLEMATIC_PRODUCTS_CODES, problematicProductsCodes);
        bundle.putStringArrayList(BasketFragment.PARAMETER_PROBLEMATIC_OFFERS_CODES, problematicOffersCodes);
        if (this.mOrder.getPreparePaymentResult() != null) {
            this.mOrder.setTotalizeResult(this.mOrder.getPreparePaymentResult());
        }
        this.mOrder.setPreparePaymentResult(null);
        startActivityForResult(AlertActivity.class, fragmentName, bundle, 21);
        return true;
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

    private void handleOrderNotReadyError() {
        Ensighten.evaluateEvent(this, "handleOrderNotReadyError", null);
        long currentMillis = System.currentTimeMillis();
        if (currentMillis - this.mStartTimeToCommunicate >= this.mTimeoutDTScanFail) {
            this.mIsStartedActivityIndicator = true;
            UIUtils.stopActivityIndicator();
            MCDFullScreenAlertDialogBuilder.withContext(getNavigationActivity()).setMessage(C2658R.string.error_communication_indicating_to_the_customer).setPositiveButtonText(C2658R.string.back_to_check_in, new C342411()).create().show();
            return;
        }
        long timeOutLastAttempt = currentMillis - this.mLastAttemptTime;
        this.mLastAttemptTime = currentMillis;
        long diff = this.mTimeBetweenAttemptsDTScanFail - timeOutLastAttempt;
        if (diff > 0) {
            startNewAttempt(diff);
        } else {
            startNewAttempt(0);
        }
    }

    private void displayOrderNotReadyDialog() {
        Ensighten.evaluateEvent(this, "displayOrderNotReadyDialog", null);
        UIUtils.stopActivityIndicator();
        MCDFullScreenAlertDialogBuilder.withContext(getNavigationActivity()).setMessage(C2658R.string.error_communication_indicating_to_the_customer).setPositiveButtonText(C2658R.string.back_to_my_order, new C342512()).create().show();
    }

    private void startNewAttempt(long diff) {
        Ensighten.evaluateEvent(this, "startNewAttempt", new Object[]{new Long(diff)});
        this.handler.postDelayed(new C342613(), diff);
    }

    public void resumeFromAlert(int productErrorCode) {
        Ensighten.evaluateEvent(this, "resumeFromAlert", new Object[]{new Integer(productErrorCode)});
        if (productErrorCode != OrderResponse.PRODUCT_PRICE_CHANGED) {
            updateOrder();
            this.mOrder.getPayment().setOrderPaymentId(null);
            totalize();
        } else if (this.mProductsRemoved) {
            preparePaymentAndCheckin();
            this.mProductsRemoved = false;
        } else {
            checkin();
        }
    }

    public void resumeFromChoosePayment() {
        Ensighten.evaluateEvent(this, "resumeFromChoosePayment", null);
        if (OrderingManager.getInstance().getCurrentOrder().isDelivery()) {
            OrderingManager.getInstance().updateTender();
        }
        preparePaymentAndCheckin();
    }

    public void checkin() {
        Ensighten.evaluateEvent(this, "checkin", null);
        checkin(true);
    }

    public void checkin(boolean checkForThirdPart) {
        Ensighten.evaluateEvent(this, "checkin", new Object[]{new Boolean(checkForThirdPart)});
        if (isAdded()) {
            String paymentUrl;
            if (this.mOrder.isZeroPriced() || this.mOrder.getPreparePaymentResult() == null) {
                paymentUrl = "";
            } else {
                paymentUrl = this.mOrder.getPreparePaymentResult().getPaymentUrl();
            }
            String paymentMtdDisplayName = this.mOrder.getPaymentMethodDisplayName();
            if (paymentMtdDisplayName != null && (paymentMtdDisplayName.equalsIgnoreCase("Alipay") || paymentMtdDisplayName.equalsIgnoreCase("支付寶") || paymentMtdDisplayName.equals(getString(C2658R.string.alipay)))) {
                getNavigationActivity().runOnUiThread(new C342714());
                for (OrderProduct product : OrderingManager.getInstance().getCurrentOrder().getProducts()) {
                    HashMap<String, Object> jiceMap = new HashMap();
                    jiceMap.put(JiceArgs.EVENT_NAME, JiceArgs.EVENT_PAYMENT_ALIPAY);
                    jiceMap.put(JiceArgs.LABEL_ITEM_ID, product.getProductCode());
                    jiceMap.put(JiceArgs.LABEL_ITEM_NAME, product.getProduct().getLongName());
                    jiceMap.put(JiceArgs.LABEL_ITEM_IS_DELIVERY, String.valueOf(product.availableAtPOD(Pod.DELIVERY)));
                    jiceMap.put(JiceArgs.LABEL_ITEM_IS_PICKUP, String.valueOf(product.availableAtPOD(Pod.PICKUP)));
                    Analytics.track(AnalyticType.Event, new ArgBuilder().setJice(jiceMap).build());
                }
            } else if (paymentMtdDisplayName != null && paymentMtdDisplayName.equalsIgnoreCase(Configuration.getSharedInstance().getStringForKey("supportedPaymentMethods.wechatPayment.displayName"))) {
                UIUtils.startActivityIndicator(getNavigationActivity(), null, getString(C2658R.string.dialog_checking_in), false);
                doWechatPayment();
            } else if (TextUtils.isEmpty(paymentUrl) || !checkForThirdPart) {
                if (ConfigurationUtils.isDuplicateOrderCheckinFlow()) {
                    UIUtils.startActivityIndicator(getNavigationActivity(), getString(C2658R.string.processing_payment_feedback));
                } else {
                    UIUtils.startActivityIndicator(getNavigationActivity(), getString(C2658R.string.dialog_checking_in));
                }
                OrderingManager.getInstance().checkIn(this.mOrder, this.mCode, this.mPassword, this.mCheckinResponseListener);
            } else {
                openThirdPartURL(paymentUrl);
            }
        }
    }

    private void showErrorDialog(String msg) {
        Ensighten.evaluateEvent(this, "showErrorDialog", new Object[]{msg});
        showErrorDialog("", msg);
    }

    private void showErrorDialog(int msgId) {
        Ensighten.evaluateEvent(this, "showErrorDialog", new Object[]{new Integer(msgId)});
        showErrorDialog("", getString(msgId));
    }

    private void showErrorDialog(String title, String msg) {
        Ensighten.evaluateEvent(this, "showErrorDialog", new Object[]{title, msg});
        AlertDialog dialog = MCDAlertDialogBuilder.withContext(getActivity()).setMessage(msg).setPositiveButton(getString(C2658R.string.f6083ok), new C342915()).create();
        if (!title.isEmpty()) {
            dialog.setTitle(title);
        }
        dialog.show();
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
        return new PayResult(new PayTask(getActivity()).pay(signature, true));
    }

    private void doWechatPayment() {
        Ensighten.evaluateEvent(this, "doWechatPayment", null);
        if (this.mWeChatApi.isWXAppInstalled()) {
            PayReq req = new PayReq();
            req.appId = this.mOrder.getPreparePaymentResult().getAppId();
            req.nonceStr = this.mOrder.getPreparePaymentResult().getNoncestr();
            req.partnerId = this.mOrder.getPreparePaymentResult().getPartnerId();
            req.prepayId = this.mOrder.getPreparePaymentResult().getPrepayid();
            req.packageValue = this.mOrder.getPreparePaymentResult().getPackage();
            req.timeStamp = this.mOrder.getPreparePaymentResult().getTimeStamp();
            req.sign = this.mOrder.getPreparePaymentResult().getSign();
            this.mWeChatApi.sendReq(req);
            return;
        }
        UIUtils.stopActivityIndicator();
        MCDAlertDialogBuilder.withContext(getActivity()).setMessage((int) C2658R.string.wechat_not_installed_alert).setPositiveButton(getString(C2658R.string.f6083ok), null).create().show();
    }

    private void openThirdPartURL(String url) {
        Ensighten.evaluateEvent(this, "openThirdPartURL", new Object[]{url});
        Bundle extras = new Bundle();
        extras.putString("payment_url", url);
        startActivityForResult(ThirdPartActivity.class, extras, 4084);
    }

    private int payStatus() {
        Ensighten.evaluateEvent(this, "payStatus", null);
        String paymentMtdDisplayName = this.mOrder.getPaymentMethodDisplayName();
        if (paymentMtdDisplayName != null && (paymentMtdDisplayName.equalsIgnoreCase("Alipay") || paymentMtdDisplayName.equalsIgnoreCase("支付寶") || paymentMtdDisplayName.equals(getString(C2658R.string.alipay)))) {
            return 1;
        }
        if (paymentMtdDisplayName == null || (!paymentMtdDisplayName.equals("Cash") && !paymentMtdDisplayName.equals("現金"))) {
            return 2;
        }
        return 0;
    }

    private void showDialogHint(String title, String msg) {
        Ensighten.evaluateEvent(this, "showDialogHint", new Object[]{title, msg});
        AlertDialog dialog = MCDAlertDialogBuilder.withContext(getActivity()).setMessage(msg).setPositiveButton((int) C2658R.string.f6083ok, new C343317()).setCancelable(false).create();
        dialog.setTitle(title);
        dialog.show();
    }

    private void updatePayments() {
        Ensighten.evaluateEvent(this, "updatePayments", null);
        if (this.mOrder.getPaymentCard() != null) {
            CustomerModule customerModule = (CustomerModule) ModuleManager.getModule(CustomerModule.NAME);
            Integer identifier = this.mOrder.getPaymentCard().getIdentifier();
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
    }

    /* Access modifiers changed, original: protected */
    public void continueToOrderSummary() {
        Ensighten.evaluateEvent(this, "continueToOrderSummary", null);
        startActivity(OrderSummaryActivity.class, "order_summary");
    }

    private boolean cvvEntered() {
        Ensighten.evaluateEvent(this, "cvvEntered", null);
        return this.mOrder.getPayment().getCVV() != null;
    }

    private void promptUserForCVV() {
        Ensighten.evaluateEvent(this, "promptUserForCVV", null);
        final EditText input = new EditText(getNavigationActivity());
        input.setInputType(129);
        input.setFilters(new InputFilter[]{new LengthFilter(ConfigurationUtils.getCVVMaxLength())});
        MCDAlertDialogBuilder.withContext(getNavigationActivity()).setTitle((int) C2658R.string.dialog_title_cvv).setMessage((int) C2658R.string.dialog_msg_cvv).setCancelable(false).setView(input).setPositiveButton((int) C2658R.string.f6083ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(whichButton)});
                dialog.dismiss();
                OrderCheckinFragment.this.mOrder.getPayment().setCVV(input.getText().toString());
                OrderCheckinFragment.access$1802(OrderCheckinFragment.this, true);
                OrderCheckinFragment.this.preparePaymentAndCheckin();
            }
        }).setNegativeButton((int) C2658R.string.cancel, new C343519()).create().show();
    }

    private void promptUserForPassword() {
        Ensighten.evaluateEvent(this, "promptUserForPassword", null);
        final EditText input = new EditText(getNavigationActivity());
        input.setInputType(129);
        MCDAlertDialogBuilder.withContext(getNavigationActivity()).setTitle((int) C2658R.string.dialog_title_password).setMessage((int) C2658R.string.dialog_msg_password).setCancelable(false).setView(input).setPositiveButton((int) C2658R.string.f6083ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(whichButton)});
                dialog.dismiss();
                OrderCheckinFragment.this.mPassword = input.getText().toString();
                OrderCheckinFragment.this.preparePaymentAndCheckin();
            }
        }).setNegativeButton((int) C2658R.string.cancel, new C343821()).create().show();
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
            this.mProcessingScannedCode = true;
            Context context = getNavigationActivity();
            this.mDialog = MCDAlertDialogBuilder.withContext(context).setTitle(context.getResources().getString(C2658R.string.curbside_header)).setMessage(context.getResources().getString(C2658R.string.curbside_message)).setPositiveButton((int) C2658R.string.f6083ok, new C344124()).setNegativeButton((int) C2658R.string.cancel, new C344023()).create();
            this.mDialog.show();
            return false;
        } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
            showInvalidQRDialiog();
            return false;
        }
    }

    private void updatePaymentType() {
        Ensighten.evaluateEvent(this, "updatePaymentType", null);
        UIUtils.startActivityIndicator(getNavigationActivity(), (int) C2658R.string.retrieving_payment_methods);
        this.mOrderingModule.getPaymentMethods(new C344225());
    }

    public void completeCheckIn(PaymentCard selectedPayment) {
        Ensighten.evaluateEvent(this, "completeCheckIn", new Object[]{selectedPayment});
        this.mOrder.setPayment(OrderPayment.fromPaymentCard(selectedPayment));
        this.mOrder.setPaymentCard(selectedPayment);
        finishCheckin();
    }
}
