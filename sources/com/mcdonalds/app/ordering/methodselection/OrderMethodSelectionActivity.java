package com.mcdonalds.app.ordering.methodselection;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.p000v4.view.ViewPager;
import android.support.p000v4.view.ViewPager.OnPageChangeListener;
import android.support.p001v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.ensighten.Ensighten;
import com.ensighten.model.EnsightenGestureRecognizerFactory;
import com.ensighten.model.activity.EnsightenActivityHandler;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.account.ModifyAddressesActivity;
import com.mcdonalds.app.customer.SignInActivity;
import com.mcdonalds.app.customer.SignUpActivity;
import com.mcdonalds.app.ordering.OrderingManager;
import com.mcdonalds.app.ordering.methodselection.StorePagerAdapter.OnItemClickListener;
import com.mcdonalds.app.ordering.start.FindStoreActivity;
import com.mcdonalds.app.p043ui.dateTime.materialdatetimepicker.time.TimePickerDialog;
import com.mcdonalds.app.p043ui.dateTime.materialdatetimepicker.time.TimePickerDialog.OnTimeChangedListener;
import com.mcdonalds.app.p043ui.dateTime.materialdatetimepicker.time.TimePickerDialog.OnTimeSetListener;
import com.mcdonalds.app.storelocator.StoreDetailsActivity;
import com.mcdonalds.app.storelocator.StoreLocatorSection;
import com.mcdonalds.app.util.AnalyticsUtils;
import com.mcdonalds.app.util.UIUtils;
import com.mcdonalds.app.util.UIUtils.MCDAlertDialogBuilder;
import com.mcdonalds.app.widget.CheckableRelativeLayout;
import com.mcdonalds.app.widget.PagerIndicator;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.modules.models.CustomerAddress;
import com.mcdonalds.sdk.modules.models.Order;
import com.mcdonalds.sdk.modules.storelocator.Store;
import com.mcdonalds.sdk.services.analytics.AnalyticType;
import com.mcdonalds.sdk.services.analytics.Analytics;
import com.mcdonalds.sdk.services.analytics.AnalyticsArgs.ArgBuilder;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.mcdonalds.sdk.services.data.LocalDataManager;
import com.mcdonalds.sdk.utils.DateUtils;
import com.newrelic.agent.android.api.p047v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.tracing.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;
import java.util.Calendar;
import java.util.List;
import p041io.github.inflationx.viewpump.ViewPumpContextWrapper;

@Instrumented
public class OrderMethodSelectionActivity extends AppCompatActivity implements OnPageChangeListener, OrderMethodSelectionView, OnItemClickListener, TraceFieldInterface {
    public Trace _nr_trace;
    private CheckableRelativeLayout mAsapSelector;
    private Button mContinueButton;
    private CustomerAddress mCustomerAddress;
    private DatePickerDialog mDatePickerDialog = null;
    private RadioButton mDeliveryButton;
    private Button mDeliverySaveButton;
    private View mDeliveryView;
    private String mEdtStr;
    private Button mOrderWhereButton;
    private RadioButton mPickupButton;
    private View mPickupView;
    private OrderMethodSelectionPresenter mPresenter;
    private final OnClickListener mRegisterOnClickListener = new C359118();
    private boolean mReturnFromModifyAddr;
    private CheckableRelativeLayout mScheduledSelector;
    private TextView mScheduledText;
    private final OnClickListener mSignInOnClickListener = new C359017();
    private TextView mStoreClosedText;
    private StorePagerAdapter mStorePagerAdapter;
    private TimePickerDialog mTimePickerDialog;
    private TextView mTitle;
    private RadioGroup methodSelector;

    /* renamed from: com.mcdonalds.app.ordering.methodselection.OrderMethodSelectionActivity$10 */
    class C358310 implements OnClickListener {
        C358310() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            OrderMethodSelectionActivity.this.finish();
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.methodselection.OrderMethodSelectionActivity$14 */
    class C358714 implements OnDateChangedListener {
        C358714() {
        }

        public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            Ensighten.evaluateEvent(this, "onDateChanged", new Object[]{view, new Integer(year), new Integer(monthOfYear), new Integer(dayOfMonth)});
            String message = Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.methodselection.OrderMethodSelectionActivity", "access$700", new Object[]{OrderMethodSelectionActivity.this, new Integer(year), new Integer(monthOfYear), new Integer(dayOfMonth)});
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.methodselection.OrderMethodSelectionActivity", "access$600", new Object[]{OrderMethodSelectionActivity.this}).getButton(-1).setEnabled(TextUtils.isEmpty(message));
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.methodselection.OrderMethodSelectionActivity", "access$600", new Object[]{OrderMethodSelectionActivity.this}).setMessage(message);
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.methodselection.OrderMethodSelectionActivity$17 */
    class C359017 implements OnClickListener {
        C359017() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            OrderMethodSelectionActivity.this.startActivity(new Intent(OrderMethodSelectionActivity.this, SignInActivity.class));
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.methodselection.OrderMethodSelectionActivity$18 */
    class C359118 implements OnClickListener {
        C359118() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            AnalyticsUtils.trackOnClickEvent("/checkout/delivery", "Start Registration");
            OrderMethodSelectionActivity.this.startActivity(new Intent(OrderMethodSelectionActivity.this, SignUpActivity.class));
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.methodselection.OrderMethodSelectionActivity$3 */
    class C35943 implements OnClickListener {
        C35943() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            OrderMethodSelectionActivity.access$100(OrderMethodSelectionActivity.this);
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.methodselection.OrderMethodSelectionActivity$4 */
    class C35954 implements OnClickListener {
        C35954() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.methodselection.OrderMethodSelectionActivity", "access$000", new Object[]{OrderMethodSelectionActivity.this}).save();
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.methodselection.OrderMethodSelectionActivity$5 */
    class C35965 implements OnClickListener {
        C35965() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            OrderMethodSelectionActivity.access$200(OrderMethodSelectionActivity.this);
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.methodselection.OrderMethodSelectionActivity$6 */
    class C35976 implements OnClickListener {
        C35976() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.methodselection.OrderMethodSelectionActivity", "access$000", new Object[]{OrderMethodSelectionActivity.this}).setAsapDeliveryAndUpdateStore(true);
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.methodselection.OrderMethodSelectionActivity$7 */
    class C35987 implements OnClickListener {
        C35987() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.methodselection.OrderMethodSelectionActivity", "access$000", new Object[]{OrderMethodSelectionActivity.this}).setAsapDelivery(false);
            OrderMethodSelectionActivity.access$300(OrderMethodSelectionActivity.this);
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.methodselection.OrderMethodSelectionActivity$8 */
    class C35998 implements OnClickListener {
        C35998() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            OrderMethodSelectionActivity.access$300(OrderMethodSelectionActivity.this);
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.methodselection.OrderMethodSelectionActivity$9 */
    class C36029 implements OnClickListener {

        /* renamed from: com.mcdonalds.app.ordering.methodselection.OrderMethodSelectionActivity$9$1 */
        class C36001 implements AsyncListener<Void> {
            C36001() {
            }

            public void onResponse(Void response, AsyncToken token, AsyncException exception) {
                Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
                if (exception == null) {
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.methodselection.OrderMethodSelectionActivity", "access$000", new Object[]{OrderMethodSelectionActivity.this}).save();
                }
            }
        }

        /* renamed from: com.mcdonalds.app.ordering.methodselection.OrderMethodSelectionActivity$9$2 */
        class C36012 implements AsyncListener<Void> {
            C36012() {
            }

            public void onResponse(Void response, AsyncToken token, AsyncException exception) {
                Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
                if (exception == null) {
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.methodselection.OrderMethodSelectionActivity", "access$000", new Object[]{OrderMethodSelectionActivity.this}).save();
                }
            }
        }

        C36029() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.methodselection.OrderMethodSelectionActivity", "access$400", new Object[]{OrderMethodSelectionActivity.this}) == null) {
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.methodselection.OrderMethodSelectionActivity", "access$000", new Object[]{OrderMethodSelectionActivity.this}).getStoreForDelivery(new C36001());
            } else {
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.methodselection.OrderMethodSelectionActivity", "access$000", new Object[]{OrderMethodSelectionActivity.this}).setAddress(Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.methodselection.OrderMethodSelectionActivity", "access$400", new Object[]{OrderMethodSelectionActivity.this}), new C36012());
            }
        }
    }

    private class NotifyingDatePickerDialog extends DatePickerDialog {
        private OnDateChangedListener mOnDateChangedListener;

        public NotifyingDatePickerDialog(Context context, int theme, OnDateSetListener listener, int year, int monthOfYear, int dayOfMonth, OnDateChangedListener dateChangedListener) {
            super(context, theme, listener, year, monthOfYear, dayOfMonth);
            this.mOnDateChangedListener = dateChangedListener;
        }

        public void onDateChanged(DatePicker view, int year, int month, int day) {
            Ensighten.evaluateEvent(this, "onDateChanged", new Object[]{view, new Integer(year), new Integer(month), new Integer(day)});
            super.onDateChanged(view, year, month, day);
            if (this.mOnDateChangedListener != null) {
                this.mOnDateChangedListener.onDateChanged(view, year, month, day);
            }
        }
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        EnsightenGestureRecognizerFactory.getFourFingerGestureRecognizer().process(motionEvent);
        return super.dispatchTouchEvent(motionEvent);
    }

    /* Access modifiers changed, original: protected */
    public Dialog onCreateDialog(int i, Bundle bundle) {
        EnsightenActivityHandler.onLifecycleMethod(this, "onCreateDialog", new Object[]{new Integer(i), bundle});
        return super.onCreateDialog(i);
    }

    /* Access modifiers changed, original: protected */
    public void onDestroy() {
        EnsightenActivityHandler.onLifecycleMethod(this, "onDestroy", null);
        super.onDestroy();
    }

    /* Access modifiers changed, original: protected */
    public void onPause() {
        EnsightenActivityHandler.onLifecycleMethod(this, "onPause", null);
        super.onPause();
        Ensighten.processView((Object) this, "onPause");
    }

    /* Access modifiers changed, original: protected */
    public void onRestart() {
        EnsightenActivityHandler.onLifecycleMethod(this, "onRestart", null);
        super.onRestart();
    }

    /* Access modifiers changed, original: protected */
    public void onResume() {
        EnsightenActivityHandler.onLifecycleMethod(this, "onResume", null);
        super.onResume();
        Ensighten.processView((Object) this, "onResume");
    }

    /* Access modifiers changed, original: protected */
    public void onStart() {
        ApplicationStateMonitor.getInstance().activityStarted();
        EnsightenActivityHandler.onLifecycleMethod(this, "onStart", null);
        super.onStart();
        Ensighten.processView((Object) this, "onStart");
    }

    /* Access modifiers changed, original: protected */
    public void onStop() {
        ApplicationStateMonitor.getInstance().activityStopped();
        EnsightenActivityHandler.onLifecycleMethod(this, "onStop", null);
        super.onStop();
    }

    static /* synthetic */ void access$100(OrderMethodSelectionActivity x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.methodselection.OrderMethodSelectionActivity", "access$100", new Object[]{x0});
        x0.findAnotherStore();
    }

    static /* synthetic */ void access$200(OrderMethodSelectionActivity x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.methodselection.OrderMethodSelectionActivity", "access$200", new Object[]{x0});
        x0.choseAddress();
    }

    static /* synthetic */ void access$300(OrderMethodSelectionActivity x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.methodselection.OrderMethodSelectionActivity", "access$300", new Object[]{x0});
        x0.showDeliveryDatePicker();
    }

    static /* synthetic */ DatePickerDialog access$602(OrderMethodSelectionActivity x0, DatePickerDialog x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.methodselection.OrderMethodSelectionActivity", "access$602", new Object[]{x0, x1});
        x0.mDatePickerDialog = x1;
        return x1;
    }

    static /* synthetic */ void access$800(OrderMethodSelectionActivity x0, Calendar x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.methodselection.OrderMethodSelectionActivity", "access$800", new Object[]{x0, x1});
        x0.showDeliveryTimePicker(x1);
    }

    static /* synthetic */ TimePickerDialog access$902(OrderMethodSelectionActivity x0, TimePickerDialog x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.methodselection.OrderMethodSelectionActivity", "access$902", new Object[]{x0, x1});
        x0.mTimePickerDialog = x1;
        return x1;
    }

    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle bundle) {
        TraceMachine.startTracing("OrderMethodSelectionActivity");
        try {
            TraceMachine.enterMethod(this._nr_trace, "OrderMethodSelectionActivity#onCreate", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "OrderMethodSelectionActivity#onCreate", null);
            }
        }
        EnsightenActivityHandler.onLifecycleMethod(this, "onCreate", new Object[]{bundle});
        super.onCreate(bundle);
        setContentView((int) C2658R.layout.dialog_fragment_delivery_method);
        this.mPresenter = new OrderMethodSelectionPresenter(this, this);
        this.mStoreClosedText = (TextView) findViewById(C2358R.C2357id.store_closed_message);
        final boolean mClearBasketOnOrderTypeChange = Configuration.getSharedInstance().getBooleanForKey("interface.ordering.clearBasketOnOrderTypeChange");
        this.methodSelector = (RadioGroup) findViewById(C2358R.C2357id.method_selector);
        this.mPickupButton = (RadioButton) findViewById(C2358R.C2357id.delivery_method_pickup);
        this.methodSelector.setVisibility(8);
        this.mPickupButton.setVisibility(8);
        this.mPickupButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
                Order order = OrderingManager.getInstance().getCurrentOrder();
                if (!mClearBasketOnOrderTypeChange || order.isEmpty()) {
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.methodselection.OrderMethodSelectionActivity", "access$000", new Object[]{OrderMethodSelectionActivity.this}).setupPickup();
                } else if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.methodselection.OrderMethodSelectionActivity", "access$000", new Object[]{OrderMethodSelectionActivity.this}).isDelivery()) {
                    OrderMethodSelectionActivity.this.showAlertBox(true);
                }
            }
        });
        this.mDeliveryButton = (RadioButton) findViewById(C2358R.C2357id.delivery_method_delivery);
        this.mDeliveryButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
                Order order = OrderingManager.getInstance().getCurrentOrder();
                if (!mClearBasketOnOrderTypeChange || order.isEmpty()) {
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.methodselection.OrderMethodSelectionActivity", "access$000", new Object[]{OrderMethodSelectionActivity.this}).setupDelivery();
                } else if (!Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.methodselection.OrderMethodSelectionActivity", "access$000", new Object[]{OrderMethodSelectionActivity.this}).isDelivery()) {
                    OrderMethodSelectionActivity.this.showAlertBox(false);
                }
            }
        });
        this.mPickupView = findViewById(C2358R.C2357id.pickup_layout);
        ViewPager storePager = (ViewPager) findViewById(C2358R.C2357id.store_pager);
        this.mStorePagerAdapter = new StorePagerAdapter(this.mPresenter);
        this.mStorePagerAdapter.setOnItemClickListener(this);
        storePager.setAdapter(this.mStorePagerAdapter);
        ((PagerIndicator) findViewById(C2358R.C2357id.store_pager_indicator)).setupWithViewPager(storePager);
        storePager.addOnPageChangeListener(this);
        ((Button) findViewById(C2358R.C2357id.find_another_button)).setOnClickListener(new C35943());
        this.mContinueButton = (Button) findViewById(C2358R.C2357id.continue_button);
        this.mContinueButton.setOnClickListener(new C35954());
        if (this.mPresenter.hasDelivery()) {
            if (!this.mPresenter.isLoggedIn()) {
                this.mDeliveryView = findViewById(C2358R.C2357id.delivery_logged_out_layout);
            } else if (this.mPresenter.isExternalDelivery()) {
                this.mDeliveryView = findViewById(C2358R.C2357id.mcdelivery_layout);
            } else {
                this.mDeliveryView = findViewById(C2358R.C2357id.delivery_layout);
            }
            this.mOrderWhereButton = (Button) findViewById(C2358R.C2357id.order_where_button);
            this.mOrderWhereButton.setOnClickListener(new C35965());
            this.mAsapSelector = (CheckableRelativeLayout) findViewById(C2358R.C2357id.asap_selection);
            this.mAsapSelector.setOnClickListener(new C35976());
            this.mScheduledSelector = (CheckableRelativeLayout) findViewById(C2358R.C2357id.sched_selection);
            this.mScheduledSelector.setOnClickListener(new C35987());
            this.mScheduledText = (TextView) findViewById(C2358R.C2357id.sched_delivery_date_text);
            this.mScheduledText.setOnClickListener(new C35998());
            ((TextView) findViewById(C2358R.C2357id.delivery_sign_in_text)).setOnClickListener(this.mSignInOnClickListener);
            ((Button) findViewById(C2358R.C2357id.delivery_sign_up_button)).setOnClickListener(this.mRegisterOnClickListener);
            if (Configuration.getSharedInstance().getBooleanForKey("interface.ordering.showDeliverySignInAsButton")) {
                ((Button) findViewById(C2358R.C2357id.delivery_sign_in_button)).setOnClickListener(this.mSignInOnClickListener);
                findViewById(C2358R.C2357id.delivery_sign_in_text).setVisibility(8);
                findViewById(C2358R.C2357id.delivery_sign_in_button).setVisibility(0);
            }
        } else {
            this.methodSelector.setVisibility(8);
        }
        this.mDeliverySaveButton = (Button) findViewById(C2358R.C2357id.delivery_save_button);
        this.mDeliverySaveButton.setOnClickListener(new C36029());
        findViewById(C2358R.C2357id.expand_content_button).setOnClickListener(new C358310());
        this.mTitle = (TextView) findViewById(2131820647);
        this.mReturnFromModifyAddr = false;
        if (getIntent().getBooleanExtra("favorite", false)) {
            this.mPresenter.initFavorite();
        } else {
            this.mPresenter.init();
        }
        if (!(getIntent() == null || getIntent().getExtras() == null || !getIntent().getExtras().getBoolean("INIT_AS_DELIVERY"))) {
            this.mDeliveryButton.callOnClick();
        }
        Ensighten.processView((Object) this, "onCreate");
        TraceMachine.exitMethod();
    }

    private void checkIfStoreIsClosed() {
        Ensighten.evaluateEvent(this, "checkIfStoreIsClosed", null);
        Store mStore = this.mPresenter.selectedStore();
        if (mStore != null && mStore.isStoreClosed() && mStore.hasMobileOrdering().booleanValue()) {
            this.mStoreClosedText.setVisibility(0);
            this.mStoreClosedText.setText(mStore.getStoreName() + " " + getString(C2658R.string.label_store_is_currently_closed));
            return;
        }
        this.mStoreClosedText.setVisibility(8);
    }

    /* Access modifiers changed, original: protected */
    public void attachBaseContext(Context newBase) {
        Ensighten.evaluateEvent(this, "attachBaseContext", new Object[]{newBase});
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }

    public void showAlertBox(final boolean showPickup) {
        Ensighten.evaluateEvent(this, "showAlertBox", new Object[]{new Boolean(showPickup)});
        DialogInterface.OnClickListener onPositiveClick = new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
                Order order = OrderingManager.getInstance().getCurrentOrder();
                order.clearOffers();
                order.clearProducts();
                if (showPickup) {
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.methodselection.OrderMethodSelectionActivity", "access$500", new Object[]{OrderMethodSelectionActivity.this}).check(C2358R.C2357id.delivery_method_pickup);
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.methodselection.OrderMethodSelectionActivity", "access$000", new Object[]{OrderMethodSelectionActivity.this}).setupPickup();
                    return;
                }
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.methodselection.OrderMethodSelectionActivity", "access$500", new Object[]{OrderMethodSelectionActivity.this}).check(C2358R.C2357id.delivery_method_delivery);
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.methodselection.OrderMethodSelectionActivity", "access$000", new Object[]{OrderMethodSelectionActivity.this}).setupDelivery();
            }
        };
        MCDAlertDialogBuilder.withContext(this).setTitle((int) C2658R.string.basket_will_be_cleared).setMessage((int) C2658R.string.cart_will_be_cleared).setPositiveButton(getString(C2658R.string.continue_button), onPositiveClick).setNegativeButton(getString(C2658R.string.button_cancel), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
                if (showPickup) {
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.methodselection.OrderMethodSelectionActivity", "access$500", new Object[]{OrderMethodSelectionActivity.this}).check(C2358R.C2357id.delivery_method_delivery);
                } else {
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.methodselection.OrderMethodSelectionActivity", "access$500", new Object[]{OrderMethodSelectionActivity.this}).check(C2358R.C2357id.delivery_method_pickup);
                }
            }
        }).create().show();
    }

    public void showPickup() {
        Ensighten.evaluateEvent(this, "showPickup", null);
        this.mPickupButton.setChecked(true);
        this.mDeliveryButton.setChecked(false);
        if (this.mPresenter != null && this.mPresenter.hasDelivery()) {
            this.mDeliveryView.setVisibility(8);
        }
        this.mPickupView.setVisibility(0);
        this.mTitle.setText(C2658R.string.pickup_settings_title);
    }

    public void showDelivery() {
        Ensighten.evaluateEvent(this, "showDelivery", null);
        this.mPickupButton.setChecked(false);
        this.mPickupView.setVisibility(8);
        if (this.mPresenter.hasDelivery()) {
            this.mDeliveryButton.setChecked(true);
            this.mDeliveryView.setVisibility(0);
            this.mTitle.setText(C2658R.string.delivery_settings_title);
        }
    }

    public void showActivityIndicator(int messageResId) {
        Ensighten.evaluateEvent(this, "showActivityIndicator", new Object[]{new Integer(messageResId)});
        UIUtils.startActivityIndicator((Context) this, messageResId);
    }

    public void hideActivityIndicator() {
        Ensighten.evaluateEvent(this, "hideActivityIndicator", null);
        UIUtils.stopActivityIndicator();
    }

    public void updateStores(List<Store> stores) {
        int i;
        boolean hasOrdering = true;
        int i2 = 8;
        Ensighten.evaluateEvent(this, "updateStores", new Object[]{stores});
        this.mContinueButton.setEnabled(this.mPresenter.hasCurrentStoreMobileOrdering());
        if (stores.isEmpty()) {
            hasOrdering = false;
        }
        ViewPager vp = (ViewPager) findViewById(C2358R.C2357id.store_pager);
        View findViewById = findViewById(C2358R.C2357id.order_from);
        if (hasOrdering) {
            i = 0;
        } else {
            i = 8;
        }
        findViewById.setVisibility(i);
        if (hasOrdering) {
            i = 0;
        } else {
            i = 8;
        }
        vp.setVisibility(i);
        View findViewById2 = findViewById(C2358R.C2357id.store_warning);
        if (!hasOrdering) {
            i2 = 0;
        }
        findViewById2.setVisibility(i2);
        this.mStorePagerAdapter.setStores(stores);
        if (hasOrdering) {
            onPageSelected(vp.getCurrentItem());
        } else {
            this.mContinueButton.setEnabled(false);
        }
        checkIfStoreIsClosed();
    }

    public void updateSelectedStore(Store store) {
        Ensighten.evaluateEvent(this, "updateSelectedStore", new Object[]{store});
        this.mStorePagerAdapter.notifyDataSetChanged();
        checkIfStoreIsClosed();
    }

    private void findAnotherStore() {
        Ensighten.evaluateEvent(this, "findAnotherStore", null);
        Bundle args = new Bundle();
        args.putBoolean("dismiss_on_place_order", true);
        Intent intent = new Intent(this, FindStoreActivity.class);
        intent.putExtras(args);
        startActivityForResult(intent, 18);
    }

    private void choseAddress() {
        Ensighten.evaluateEvent(this, "choseAddress", null);
        Analytics.track(AnalyticType.Event, new ArgBuilder().setCategory("/checkout/delivery").setAction("On click").setLabel("Delivery Address").build());
        Bundle args = new Bundle();
        args.putBoolean("update_default", false);
        args.putBoolean("update_default_from_menu", Configuration.getSharedInstance().getBooleanForKey("interface.address.updateDefaultFromMenu"));
        args.putString("full_address", this.mOrderWhereButton.getText().toString());
        Intent intent = new Intent(this, ModifyAddressesActivity.class);
        intent.putExtra("ADDRESS_BUNDLE", args);
        startActivityForResult(intent, 6274);
    }

    public void updateSelectedAddress(String fullAddress) {
        Ensighten.evaluateEvent(this, "updateSelectedAddress", new Object[]{fullAddress});
        this.mOrderWhereButton.setText(fullAddress);
    }

    public void showDeliveryTimeSelector() {
        Ensighten.evaluateEvent(this, "showDeliveryTimeSelector", null);
        findViewById(C2358R.C2357id.delivery_when_wrapper).setVisibility(0);
    }

    public void updateAsapDeliveryDate(String deliveryDateString) {
        Ensighten.evaluateEvent(this, "updateAsapDeliveryDate", new Object[]{deliveryDateString});
        TextView ASAPText = (TextView) findViewById(C2358R.C2357id.check_box_1_subtitle);
        if (Configuration.getSharedInstance().getBooleanForKey("interface.showEstimatedDeliveryTimeInMinutes")) {
            String edt = LocalDataManager.getSharedInstance().getEdtString();
            if (this.mReturnFromModifyAddr || edt == null) {
                this.mEdtStr = deliveryDateString;
                ASAPText.setText(C2658R.string.delivery_time_preface);
                LocalDataManager.getSharedInstance().setEdtString(this.mEdtStr);
                return;
            }
            ASAPText.setText(C2658R.string.delivery_time_preface);
            return;
        }
        ASAPText.setText(C2658R.string.delivery_time_preface);
    }

    public void updateScheduledDeliveryDate(String deliveryDateString) {
        Ensighten.evaluateEvent(this, "updateScheduledDeliveryDate", new Object[]{deliveryDateString});
        this.mScheduledText.setText(deliveryDateString);
        this.mDeliverySaveButton.setEnabled(true);
    }

    private void showDeliveryDatePicker() {
        Ensighten.evaluateEvent(this, "showDeliveryDatePicker", null);
        final Calendar deliveryCalendar = Calendar.getInstance();
        this.mDatePickerDialog = new NotifyingDatePickerDialog(this, 0, new OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Ensighten.evaluateEvent(this, "onDateSet", new Object[]{view, new Integer(year), new Integer(monthOfYear), new Integer(dayOfMonth)});
                if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.methodselection.OrderMethodSelectionActivity", "access$600", new Object[]{OrderMethodSelectionActivity.this}) != null && TextUtils.isEmpty(Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.methodselection.OrderMethodSelectionActivity", "access$700", new Object[]{OrderMethodSelectionActivity.this, new Integer(year), new Integer(monthOfYear), new Integer(dayOfMonth)}))) {
                    deliveryCalendar.set(1, year);
                    deliveryCalendar.set(2, monthOfYear);
                    deliveryCalendar.set(5, dayOfMonth);
                    OrderMethodSelectionActivity.access$602(OrderMethodSelectionActivity.this, null);
                    OrderMethodSelectionActivity.access$800(OrderMethodSelectionActivity.this, deliveryCalendar);
                }
            }
        }, deliveryCalendar.get(1), deliveryCalendar.get(2), deliveryCalendar.get(5), new C358714());
        String message = getDeliveryDatePickerMessage(deliveryCalendar.get(1), deliveryCalendar.get(2), deliveryCalendar.get(5));
        this.mDatePickerDialog.setMessage(message);
        this.mDatePickerDialog.show();
        this.mDatePickerDialog.getWindow().setLayout(-1, -2);
        this.mDatePickerDialog.getButton(-1).setEnabled(TextUtils.isEmpty(message));
    }

    private void showDeliveryTimePicker(final Calendar calendar) {
        Ensighten.evaluateEvent(this, "showDeliveryTimePicker", new Object[]{calendar});
        OnTimeSetListener onTimeSetListener = new OnTimeSetListener() {
            public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {
                Ensighten.evaluateEvent(this, "onTimeSet", new Object[]{view, new Integer(hourOfDay), new Integer(minute), new Integer(second)});
                if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.methodselection.OrderMethodSelectionActivity", "access$900", new Object[]{OrderMethodSelectionActivity.this}) != null) {
                    if (TextUtils.isEmpty(Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.methodselection.OrderMethodSelectionActivity", "access$1000", new Object[]{OrderMethodSelectionActivity.this, calendar, new Integer(hourOfDay), new Integer(minute)}))) {
                        calendar.set(11, hourOfDay);
                        calendar.set(12, minute);
                        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.methodselection.OrderMethodSelectionActivity", "access$000", new Object[]{OrderMethodSelectionActivity.this}).setAsapDelivery(false);
                        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.methodselection.OrderMethodSelectionActivity", "access$000", new Object[]{OrderMethodSelectionActivity.this}).setScheduledDeliveryDate(calendar.getTime());
                        OrderMethodSelectionActivity.access$902(OrderMethodSelectionActivity.this, null);
                    }
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.methodselection.OrderMethodSelectionActivity", "access$1100", new Object[]{OrderMethodSelectionActivity.this}).setEnabled(true);
                }
            }
        };
        OnTimeChangedListener onTimeChangedListener = new OnTimeChangedListener() {
            public void onTimeChanged(int hourOfDay, int minute) {
                Ensighten.evaluateEvent(this, "onTimeChanged", new Object[]{new Integer(hourOfDay), new Integer(minute)});
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.methodselection.OrderMethodSelectionActivity", "access$900", new Object[]{OrderMethodSelectionActivity.this}).setTitlebutton(Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.methodselection.OrderMethodSelectionActivity", "access$1000", new Object[]{OrderMethodSelectionActivity.this, calendar, new Integer(hourOfDay), new Integer(minute)}));
            }
        };
        Calendar now = Calendar.getInstance();
        Calendar defaultTime = getDefaultTime();
        defaultTime.set(12, defaultTime.get(12) + 30);
        int minute = 0;
        if (defaultTime.get(12) > 30) {
            minute = 30;
        }
        this.mTimePickerDialog = TimePickerDialog.newInstance(onTimeSetListener, onTimeChangedListener, defaultTime.get(11), minute, true);
        String message = getDeliveryTimeMessage(calendar, defaultTime.get(11), minute);
        this.mTimePickerDialog.show(getFragmentManager(), "Timepickerdialog");
        this.mTimePickerDialog.setTitlebutton(message);
    }

    private Calendar getDefaultTime() {
        Ensighten.evaluateEvent(this, "getDefaultTime", null);
        Calendar currentTime = Calendar.getInstance();
        Calendar minTime = Calendar.getInstance();
        minTime.setTimeInMillis(this.mPresenter.getMinimumDeliveryDateInMillis());
        if (currentTime.before(minTime)) {
            return minTime;
        }
        Calendar maxTime = Calendar.getInstance();
        maxTime.setTimeInMillis(this.mPresenter.getMaximumDeliveryDateInMillis());
        if (currentTime.after(maxTime)) {
            return maxTime;
        }
        return currentTime;
    }

    private String getDeliveryDatePickerMessage(int year, int monthOfYear, int dayOfMonth) {
        Ensighten.evaluateEvent(this, "getDeliveryDatePickerMessage", new Object[]{new Integer(year), new Integer(monthOfYear), new Integer(dayOfMonth)});
        String message = "";
        Calendar selectedDay = Calendar.getInstance();
        selectedDay.set(year, monthOfYear, dayOfMonth);
        Calendar minTime = Calendar.getInstance();
        minTime.setTimeInMillis(this.mPresenter.getMinimumDeliveryDateInMillis());
        selectedDay.set(11, 23);
        selectedDay.set(12, 59);
        if (selectedDay.before(minTime)) {
            return getString(C2658R.string.delivery_orders_min_advance, new Object[]{DateUtils.formatRange(this, minDateMillis - System.currentTimeMillis())});
        }
        Calendar maxTime = Calendar.getInstance();
        maxTime.setTimeInMillis(this.mPresenter.getMaximumDeliveryDateInMillis());
        selectedDay.set(11, 0);
        selectedDay.set(12, 0);
        if (!selectedDay.after(maxTime)) {
            return message;
        }
        return getString(C2658R.string.delivery_orders_max_advance, new Object[]{DateUtils.formatRange(this, maxDateMillis - System.currentTimeMillis())});
    }

    private String getDeliveryTimeMessage(Calendar selectedDay, int hourOfDay, int minute) {
        Ensighten.evaluateEvent(this, "getDeliveryTimeMessage", new Object[]{selectedDay, new Integer(hourOfDay), new Integer(minute)});
        String message = "";
        selectedDay.set(11, hourOfDay);
        selectedDay.set(12, minute);
        Calendar minTime = Calendar.getInstance();
        minTime.setTimeInMillis(this.mPresenter.getMinimumDeliveryDateInMillis());
        selectedDay.set(13, minTime.get(13));
        selectedDay.set(14, minTime.get(14));
        if (selectedDay.before(minTime)) {
            return getString(C2658R.string.delivery_orders_min_advance, new Object[]{DateUtils.formatRange(this, minDateMillis - System.currentTimeMillis())});
        }
        Calendar maxTime = Calendar.getInstance();
        maxTime.setTimeInMillis(this.mPresenter.getMaximumDeliveryDateInMillis());
        selectedDay.set(13, maxTime.get(13));
        selectedDay.set(14, maxTime.get(14));
        if (!selectedDay.after(maxTime)) {
            return message;
        }
        return getString(C2658R.string.delivery_orders_max_advance, new Object[]{DateUtils.formatRange(this, maxDateMillis - System.currentTimeMillis())});
    }

    public void setAsapDelivery(boolean asapDelivery) {
        boolean z = true;
        Ensighten.evaluateEvent(this, "setAsapDelivery", new Object[]{new Boolean(asapDelivery)});
        this.mAsapSelector.setChecked(asapDelivery);
        CheckableRelativeLayout checkableRelativeLayout = this.mScheduledSelector;
        if (asapDelivery) {
            z = false;
        }
        checkableRelativeLayout.setChecked(z);
        setEDTVisibility(asapDelivery);
    }

    public void setAsapDeliveryFirst(boolean asapDelivery) {
        Ensighten.evaluateEvent(this, "setAsapDeliveryFirst", new Object[]{new Boolean(asapDelivery)});
        this.mAsapSelector.setChecked(asapDelivery);
        this.mScheduledSelector.setChecked(asapDelivery);
        setEDTVisibility(asapDelivery);
    }

    public void setSaveButtonState(boolean enabled) {
        Ensighten.evaluateEvent(this, "setSaveButtonState", new Object[]{new Boolean(enabled)});
        if (this.mPresenter.isDelivery()) {
            this.mDeliverySaveButton.setEnabled(enabled);
        } else {
            this.mContinueButton.setEnabled(enabled);
        }
    }

    public void save() {
        Ensighten.evaluateEvent(this, "save", null);
        Intent data = new Intent();
        if (!(getIntent() == null || getIntent().getExtras() == null)) {
            data.putExtra("PASS_THROUGH_ARGS", getIntent().getExtras().getBundle("PASS_THROUGH_ARGS"));
        }
        setResult(-1, data);
        finish();
    }

    public void onBackPressed() {
        Ensighten.evaluateEvent(this, "onBackPressed", null);
        super.onBackPressed();
        if (LocalDataManager.getSharedInstance().isFirstTimeDelivery() || LocalDataManager.getSharedInstance().isFirstTimeOrdering()) {
            setResult(0);
        }
    }

    /* Access modifiers changed, original: protected */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        EnsightenActivityHandler.onLifecycleMethod(this, "onActivityResult", new Object[]{new Integer(requestCode), new Integer(resultCode), data});
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == -1) {
            if (requestCode == 18) {
                this.mPresenter.setupPickup();
            } else if (requestCode == 6274) {
                this.mCustomerAddress = (CustomerAddress) data.getExtras().getParcelable("ADDRESS_RETURN_KEY");
                if (this.mCustomerAddress != null) {
                    this.mPresenter.setAddress(this.mCustomerAddress);
                }
                this.mReturnFromModifyAddr = true;
            }
        }
    }

    public void onSelectButtonClicked(Store store) {
        Ensighten.evaluateEvent(this, "onSelectButtonClicked", new Object[]{store});
        this.mPresenter.setPickupStore(store);
        this.mContinueButton.setEnabled(true);
    }

    public void onInfoButtonClicked(Store store) {
        Ensighten.evaluateEvent(this, "onInfoButtonClicked", new Object[]{store});
        AnalyticsUtils.trackOnClickEvent("/restaurant-locator", "Info Icon");
        Intent intent = new Intent(this, StoreDetailsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("extra_store_detail", store);
        bundle.putInt("extra_store_section", StoreLocatorSection.Current.ordinal());
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        Ensighten.evaluateEvent(this, "onPageScrolled", new Object[]{new Integer(position), new Float(positionOffset), new Integer(positionOffsetPixels)});
    }

    public void onPageSelected(int position) {
        Ensighten.evaluateEvent(this, "onPageSelected", new Object[]{new Integer(position)});
        if (this.mPresenter.isCurrentStore((Store) this.mStorePagerAdapter.getStores().get(position))) {
            this.mContinueButton.setEnabled(true);
        } else {
            this.mContinueButton.setEnabled(false);
        }
    }

    public void onPageScrollStateChanged(int state) {
        Ensighten.evaluateEvent(this, "onPageScrollStateChanged", new Object[]{new Integer(state)});
    }

    private void setEDTVisibility(boolean visible) {
        int i = 0;
        Ensighten.evaluateEvent(this, "setEDTVisibility", new Object[]{new Boolean(visible)});
        TextView edtTextView = (TextView) findViewById(C2358R.C2357id.check_box_1_subtitle);
        if (edtTextView != null) {
            if (!visible) {
                i = 8;
            }
            edtTextView.setVisibility(i);
        }
    }
}
