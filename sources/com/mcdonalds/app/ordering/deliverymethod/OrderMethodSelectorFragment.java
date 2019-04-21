package com.mcdonalds.app.ordering.deliverymethod;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.p000v4.app.Fragment;
import android.support.p000v4.app.FragmentStatePagerAdapter;
import android.support.p000v4.view.ViewPager;
import android.text.Html;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.account.ModifyAddressesActivity;
import com.mcdonalds.app.account.ModifyAddressesFragment;
import com.mcdonalds.app.customer.SignInActivity;
import com.mcdonalds.app.customer.SignUpActivity;
import com.mcdonalds.app.customer.TermsOfServiceActivity;
import com.mcdonalds.app.ordering.OrderingManager;
import com.mcdonalds.app.ordering.deliverymethod.OrderMethodStorePageFragment.DeliveryMethodStorePageFragmentListener;
import com.mcdonalds.app.ordering.start.FindStoreActivity;
import com.mcdonalds.app.ordering.start.StoreSelectionController;
import com.mcdonalds.app.p043ui.URLNavigationFragment;
import com.mcdonalds.app.storelocator.StoreDetailsActivity;
import com.mcdonalds.app.storelocator.StoreDetailsFragment;
import com.mcdonalds.app.storelocator.StoreLocationListener;
import com.mcdonalds.app.storelocator.StoreLocatorDataProvider;
import com.mcdonalds.app.storelocator.StoreLocatorInteractionListener;
import com.mcdonalds.app.storelocator.StoreLocatorSection;
import com.mcdonalds.app.util.AnalyticsUtils;
import com.mcdonalds.app.util.AppUtils;
import com.mcdonalds.app.util.OnPageSelectListener;
import com.mcdonalds.app.util.UIUtils;
import com.mcdonalds.app.util.UIUtils.MCDAlertDialogBuilder;
import com.mcdonalds.app.widget.CheckableRelativeLayout;
import com.mcdonalds.app.widget.CheckableRelativeLayout.OnCheckedChangeListener;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.customer.CustomerModule;
import com.mcdonalds.sdk.modules.delivery.DeliveryModule;
import com.mcdonalds.sdk.modules.models.CustomerAddress;
import com.mcdonalds.sdk.modules.models.Order;
import com.mcdonalds.sdk.modules.models.Order.PriceType;
import com.mcdonalds.sdk.modules.models.Pod;
import com.mcdonalds.sdk.modules.ordering.OrderManager;
import com.mcdonalds.sdk.modules.ordering.OrderingModule;
import com.mcdonalds.sdk.modules.storelocator.Store;
import com.mcdonalds.sdk.services.analytics.AnalyticType;
import com.mcdonalds.sdk.services.analytics.Analytics;
import com.mcdonalds.sdk.services.analytics.AnalyticsArgs.ArgBuilder;
import com.mcdonalds.sdk.services.analytics.JiceArgs;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.mcdonalds.sdk.services.data.LocalDataManager;
import com.mcdonalds.sdk.utils.DateUtils;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class OrderMethodSelectorFragment extends URLNavigationFragment implements DeliveryMethodStorePageFragmentListener {
    private static final ThreadLocal<SimpleDateFormat> DELIVERY_DATE_FORMATTER = new C34871();
    public static final String NAME = OrderMethodSelectorFragment.class.getSimpleName();
    private TextView mAsapETAText;
    private Date mAsapEtaDate;
    private boolean mAsapSelected;
    private CheckableRelativeLayout mAsapSelector;
    private Calendar mCalEnd;
    private Calendar mCalStart;
    private Date mChosenDate;
    private ImageButton mCollapseButton;
    private Button mContinueButton;
    private CustomerModule mCustomerModule;
    private StoreLocatorDataProvider mDataProvider;
    private boolean mDateSet;
    private String mDeliveryAddress;
    private Calendar mDeliveryDate;
    private Boolean mDeliveryEnabled;
    private View mDeliveryLayout;
    private View mDeliveryLoggedOutLayout;
    private DeliveryModule mDeliveryModule;
    private Button mDeliverySaveButton;
    private Store mDeliveryStore;
    private boolean mDeliveryStoreSelected;
    private Date mDeliveryTime;
    private boolean mExpanded = false;
    private View mExpandedContent;
    private ExternalDeliveryView mExternalDeliveryView;
    private RadioButton[] mIndicators;
    private StoreLocatorInteractionListener mInteractionListener;
    private boolean mLoggedIn;
    private RadioButton mMethodSelectorDelivery;
    private RadioGroup mMethodSelectorGroup;
    private RadioButton mMethodSelectorPickup;
    private int mMinutesUntilEarliestOrder;
    private int mMinutesUntilLatestOrder;
    private final OnPageSelectListener mOnStorePageChange = new C348518();
    private TextView mOrderFrom;
    private TextView mOrderWhenText;
    private Button mOrderWhereButton;
    private OrderingModule mOrderingModule;
    private View mPickupLayout;
    private int mPreviousState;
    private CheckableRelativeLayout mSchedDeliveryDateSelector;
    private Store mSelectedStore;
    private boolean mSendToDeliveryWebsite;
    private int mState;
    private ViewPager mStorePager;
    private boolean mStoreSelected = false;
    private StorePagerFragmentAdapter mStoresAdapter;
    private boolean mTimeSet;
    private TextView mTitle;
    private TextView mWarningTextView;

    /* renamed from: com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment$13 */
    class C348013 implements OnClickListener {
        C348013() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            AnalyticsUtils.trackOnClickEvent(Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment", "access$2700", new Object[]{OrderMethodSelectorFragment.this}), "Start Registration");
            if (AppUtils.hideTermsAndConditionsView()) {
                OrderMethodSelectorFragment.this.startActivity(SignUpActivity.class, "sign_up");
            } else {
                OrderMethodSelectorFragment.this.startActivity(TermsOfServiceActivity.class, JiceArgs.EVENT_REGISTER);
            }
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment$14 */
    class C348114 implements OnClickListener {
        C348114() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            OrderMethodSelectorFragment.this.startActivity(SignInActivity.class, JiceArgs.EVENT_CHECK_IN);
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment$15 */
    class C348215 implements AsyncListener<Object> {
        C348215() {
        }

        public void onResponse(Object response, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            Intent intent = new Intent();
            Bundle extras = new Bundle();
            extras.putBoolean("PICKUP", false);
            intent.putExtras(extras);
            if (OrderMethodSelectorFragment.this.getNavigationActivity() != null) {
                OrderMethodSelectorFragment.this.getNavigationActivity().setResult(-1, intent);
                OrderMethodSelectorFragment.this.getNavigationActivity().finish();
            }
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment$18 */
    class C348518 extends OnPageSelectListener {
        C348518() {
        }

        public void onPageSelected(int position) {
            Ensighten.evaluateEvent(this, "onPageSelected", new Object[]{new Integer(position)});
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment$1 */
    static class C34871 extends ThreadLocal<SimpleDateFormat> {
        C34871() {
        }

        /* Access modifiers changed, original: protected */
        public SimpleDateFormat initialValue() {
            return new SimpleDateFormat("MMM d, yyyy, hh:mm aaa");
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment$23 */
    class C349123 implements AsyncListener<Void> {
        C349123() {
        }

        public void onResponse(Void response, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment", "access$200", new Object[]{OrderMethodSelectorFragment.this}) != 4) {
                OrderMethodSelectorFragment.access$3800(OrderMethodSelectorFragment.this);
            }
            OrderMethodSelectorFragment.access$1600(OrderMethodSelectorFragment.this);
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment$24 */
    class C349224 implements AsyncListener<Store> {
        C349224() {
        }

        public void onResponse(Store response, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            if (exception == null) {
                OrderMethodSelectorFragment.access$502(OrderMethodSelectorFragment.this, response);
                OrderMethodSelectorFragment.access$802(OrderMethodSelectorFragment.this, UIUtils.getDateFromISO8601(response.getExpectedDeliveryTime()));
                String asapEtaDateString = UIUtils.formatDeliveryTime(OrderMethodSelectorFragment.this.getContext(), response.getCurrentDate(), Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment", "access$800", new Object[]{OrderMethodSelectorFragment.this}), true);
                if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment", "access$800", new Object[]{OrderMethodSelectorFragment.this}) != null) {
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment", "access$900", new Object[]{OrderMethodSelectorFragment.this}).setText(C2658R.string.delivery_time_preface);
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment", "access$900", new Object[]{OrderMethodSelectorFragment.this}).setVisibility(0);
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment", "access$1000", new Object[]{OrderMethodSelectorFragment.this}).setVisibility(0);
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment", "access$1100", new Object[]{OrderMethodSelectorFragment.this}).setVisibility(0);
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment", "access$1200", new Object[]{OrderMethodSelectorFragment.this}).setVisibility(0);
                }
                Order currentOrder = OrderingManager.getInstance().getCurrentOrder();
                currentOrder.setIsDelivery(true);
                currentOrder.setDeliveryAddress(OrderingManager.getInstance().getCurrentOrder().getDeliveryAddress());
                currentOrder.setDeliveryDate(Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment", "access$800", new Object[]{OrderMethodSelectorFragment.this}));
                currentOrder.setDeliveryDateString(asapEtaDateString);
                currentOrder.setPriceType(PriceType.Delivery);
                OrderMethodSelectorFragment.access$1302(OrderMethodSelectorFragment.this, true);
                if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment", "access$1400", new Object[]{OrderMethodSelectorFragment.this}) == null) {
                    OrderMethodSelectorFragment.access$1402(OrderMethodSelectorFragment.this, Calendar.getInstance());
                }
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment", "access$1400", new Object[]{OrderMethodSelectorFragment.this}).setTime(Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment", "access$800", new Object[]{OrderMethodSelectorFragment.this}));
                OrderMethodSelectorFragment.access$1500(OrderMethodSelectorFragment.this);
                OrderMethodSelectorFragment.access$1600(OrderMethodSelectorFragment.this);
            } else {
                AsyncException.report(exception);
            }
            UIUtils.stopActivityIndicator();
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment$2 */
    class C34932 implements StoreLocationListener {
        C34932() {
        }

        public void onChange(StoreLocatorDataProvider provider) {
            Ensighten.evaluateEvent(this, "onChange", new Object[]{provider});
            OrderMethodSelectorFragment.access$000(OrderMethodSelectorFragment.this);
        }

        public void onSelectedStoreChange(StoreLocatorDataProvider provider, String previousSelectionId, StoreLocatorSection previousSection, boolean shouldExpand) {
            Ensighten.evaluateEvent(this, "onSelectedStoreChange", new Object[]{provider, previousSelectionId, previousSection, new Boolean(shouldExpand)});
        }

        public void onCurrentStoreChange(StoreLocatorDataProvider provider, String previousCurrentStoreId) {
            Ensighten.evaluateEvent(this, "onCurrentStoreChange", new Object[]{provider, previousCurrentStoreId});
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment", "access$100", new Object[]{OrderMethodSelectorFragment.this}).notifyDataSetChanged();
        }

        public void refreshSelectedStore() {
            Ensighten.evaluateEvent(this, "refreshSelectedStore", null);
        }

        public void clearZoomFlag() {
            Ensighten.evaluateEvent(this, "clearZoomFlag", null);
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment$3 */
    class C34943 implements OnClickListener {
        C34943() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            OrderMethodSelectorFragment.this.getNavigationActivity().onBackPressed();
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment$4 */
    class C34954 implements OnClickListener {
        C34954() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            Bundle args = new Bundle();
            args.putBoolean("dismiss_on_place_order", true);
            OrderMethodSelectorFragment.this.startActivityForResult(FindStoreActivity.class, "store_locator", args, 18);
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment$5 */
    class C34965 implements OnClickListener {
        C34965() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            Order currentOrder = OrderingManager.getInstance().getCurrentOrder();
            switch (Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment", "access$200", new Object[]{OrderMethodSelectorFragment.this})) {
                case 0:
                case 1:
                    currentOrder.setIsDelivery(false);
                    currentOrder.setStoreId(Integer.toString(Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment", "access$300", new Object[]{OrderMethodSelectorFragment.this}).getCurrentStore().getStoreId()));
                    currentOrder.setPriceType(PriceType.TakeOut);
                    AnalyticsUtils.trackOnClickEvent("/checkout/restaurant", "Continue");
                    break;
                case 2:
                    currentOrder.setIsDelivery(false);
                    currentOrder.setStoreId(Integer.toString(currentOrder.getDeliveryStore().getStoreId()));
                    currentOrder.setPriceType(PriceType.TakeOut);
                    break;
            }
            OrderMethodSelectorFragment.access$402(OrderMethodSelectorFragment.this, true);
            currentOrder.setPriceType(PriceType.EatIn);
            LocalDataManager.getSharedInstance().setFirstTimeOrdering(false);
            Intent intent = new Intent();
            Bundle extras = new Bundle();
            extras.putBoolean("PICKUP", true);
            extras.putSerializable("STORE_KEY", OrderMethodSelectorFragment.this.getSelectedStore());
            intent.putExtras(extras);
            OrderMethodSelectorFragment.this.getNavigationActivity().setResult(-1, intent);
            OrderMethodSelectorFragment.this.getNavigationActivity().finish();
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment$6 */
    class C34986 implements OnClickListener {
        C34986() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            OrderMethodSelectorFragment.access$402(OrderMethodSelectorFragment.this, true);
            LocalDataManager.getSharedInstance().setFirstTimeOrdering(false);
            final Order deliveryOrder = OrderingManager.getInstance().getCurrentOrder();
            if (deliveryOrder.getDeliveryAddress() != null && deliveryOrder.getDeliveryDate() != null) {
                UIUtils.startActivityIndicator(OrderMethodSelectorFragment.this.getNavigationActivity(), "Saving delivery store...");
                if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment", "access$500", new Object[]{OrderMethodSelectorFragment.this}) == null) {
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment", "access$700", new Object[]{OrderMethodSelectorFragment.this}).getDeliveryStore(deliveryOrder.getDeliveryAddress(), deliveryOrder.getDeliveryDate(), Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment", "access$300", new Object[]{OrderMethodSelectorFragment.this}).getCurrentProfile(), new AsyncListener<Store>() {
                        public void onResponse(Store response, AsyncToken token, AsyncException exception) {
                            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
                            if (OrderMethodSelectorFragment.this.getNavigationActivity() != null) {
                                if (exception == null) {
                                    OrderMethodSelectorFragment.access$600(OrderMethodSelectorFragment.this, response, deliveryOrder);
                                } else {
                                    AsyncException.report(exception);
                                    deliveryOrder.setDeliveryStore(null);
                                }
                                UIUtils.stopActivityIndicator();
                            }
                        }
                    });
                    return;
                }
                OrderMethodSelectorFragment.access$600(OrderMethodSelectorFragment.this, Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment", "access$500", new Object[]{OrderMethodSelectorFragment.this}), deliveryOrder);
                UIUtils.stopActivityIndicator();
            }
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment$8 */
    class C35008 implements OnClickListener {
        C35008() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            AnalyticsUtils.trackOnClickEvent("/checkout/delivery", "Delivery Address");
            OrderMethodSelectorFragment.access$1700(OrderMethodSelectorFragment.this);
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment$9 */
    class C35019 implements OnCheckedChangeListener {
        C35019() {
        }

        public void onCheckedChanged(CheckableRelativeLayout layout, boolean isChecked) {
            Ensighten.evaluateEvent(this, "onCheckedChanged", new Object[]{layout, new Boolean(isChecked)});
            OrderMethodSelectorFragment.access$1802(OrderMethodSelectorFragment.this, isChecked);
            OrderMethodSelectorFragment.access$1500(OrderMethodSelectorFragment.this);
        }
    }

    private class StorePagerFragmentAdapter extends FragmentStatePagerAdapter {
        private final SparseArray<OrderMethodStorePageFragment> mFragments = new SparseArray();
        private List<Store> mStores;

        public StorePagerFragmentAdapter(List<Store> stores) {
            super(OrderMethodSelectorFragment.this.getChildFragmentManager());
            this.mStores = stores;
        }

        public void setStores(List<Store> stores) {
            Ensighten.evaluateEvent(this, "setStores", new Object[]{stores});
            this.mStores = stores;
        }

        public Fragment getItem(int position) {
            Ensighten.evaluateEvent(this, "getItem", new Object[]{new Integer(position)});
            OrderMethodStorePageFragment fragment = OrderMethodStorePageFragment.newInstance((Store) this.mStores.get(position), OrderMethodSelectorFragment.this);
            this.mFragments.put(position, fragment);
            return fragment;
        }

        public int getCount() {
            Ensighten.evaluateEvent(this, "getCount", null);
            return Math.min(this.mStores.size(), 3);
        }

        public int getItemPosition(Object object) {
            Ensighten.evaluateEvent(this, "getItemPosition", new Object[]{object});
            return -2;
        }
    }

    static /* synthetic */ void access$000(OrderMethodSelectorFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment", "access$000", new Object[]{x0});
        x0.initStorePager();
    }

    static /* synthetic */ boolean access$1302(OrderMethodSelectorFragment x0, boolean x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment", "access$1302", new Object[]{x0, new Boolean(x1)});
        x0.mDeliveryStoreSelected = x1;
        return x1;
    }

    static /* synthetic */ Calendar access$1402(OrderMethodSelectorFragment x0, Calendar x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment", "access$1402", new Object[]{x0, x1});
        x0.mDeliveryDate = x1;
        return x1;
    }

    static /* synthetic */ void access$1500(OrderMethodSelectorFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment", "access$1500", new Object[]{x0});
        x0.verifyDeliverySaveButtonState();
    }

    static /* synthetic */ void access$1600(OrderMethodSelectorFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment", "access$1600", new Object[]{x0});
        x0.updateViewsForStateChange();
    }

    static /* synthetic */ void access$1700(OrderMethodSelectorFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment", "access$1700", new Object[]{x0});
        x0.navigateToAddressFragment();
    }

    static /* synthetic */ boolean access$1802(OrderMethodSelectorFragment x0, boolean x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment", "access$1802", new Object[]{x0, new Boolean(x1)});
        x0.mAsapSelected = x1;
        return x1;
    }

    static /* synthetic */ boolean access$1902(OrderMethodSelectorFragment x0, boolean x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment", "access$1902", new Object[]{x0, new Boolean(x1)});
        x0.mTimeSet = x1;
        return x1;
    }

    static /* synthetic */ boolean access$2002(OrderMethodSelectorFragment x0, boolean x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment", "access$2002", new Object[]{x0, new Boolean(x1)});
        x0.mDateSet = x1;
        return x1;
    }

    static /* synthetic */ int access$202(OrderMethodSelectorFragment x0, int x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment", "access$202", new Object[]{x0, new Integer(x1)});
        x0.mState = x1;
        return x1;
    }

    static /* synthetic */ Date access$2102(OrderMethodSelectorFragment x0, Date x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment", "access$2102", new Object[]{x0, x1});
        x0.mChosenDate = x1;
        return x1;
    }

    static /* synthetic */ Date access$2502(OrderMethodSelectorFragment x0, Date x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment", "access$2502", new Object[]{x0, x1});
        x0.mDeliveryTime = x1;
        return x1;
    }

    static /* synthetic */ int access$2802(OrderMethodSelectorFragment x0, int x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment", "access$2802", new Object[]{x0, new Integer(x1)});
        x0.mPreviousState = x1;
        return x1;
    }

    static /* synthetic */ CustomerModule access$302(OrderMethodSelectorFragment x0, CustomerModule x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment", "access$302", new Object[]{x0, x1});
        x0.mCustomerModule = x1;
        return x1;
    }

    static /* synthetic */ void access$3200(OrderMethodSelectorFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment", "access$3200", new Object[]{x0});
        x0.getDeliveryWindow();
    }

    static /* synthetic */ OrderingModule access$3402(OrderMethodSelectorFragment x0, OrderingModule x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment", "access$3402", new Object[]{x0, x1});
        x0.mOrderingModule = x1;
        return x1;
    }

    static /* synthetic */ void access$3500(OrderMethodSelectorFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment", "access$3500", new Object[]{x0});
        x0.updateView();
    }

    static /* synthetic */ void access$3700(OrderMethodSelectorFragment x0, int x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment", "access$3700", new Object[]{x0, new Integer(x1)});
        x0.setWhereButtonTextSize(x1);
    }

    static /* synthetic */ void access$3800(OrderMethodSelectorFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment", "access$3800", new Object[]{x0});
        x0.initMethodSelectorGroup();
    }

    static /* synthetic */ boolean access$402(OrderMethodSelectorFragment x0, boolean x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment", "access$402", new Object[]{x0, new Boolean(x1)});
        x0.mStoreSelected = x1;
        return x1;
    }

    static /* synthetic */ Store access$502(OrderMethodSelectorFragment x0, Store x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment", "access$502", new Object[]{x0, x1});
        x0.mDeliveryStore = x1;
        return x1;
    }

    static /* synthetic */ void access$600(OrderMethodSelectorFragment x0, Store x1, Order x2) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment", "access$600", new Object[]{x0, x1, x2});
        x0.finalizeDeliveryOrder(x1, x2);
    }

    static /* synthetic */ DeliveryModule access$702(OrderMethodSelectorFragment x0, DeliveryModule x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment", "access$702", new Object[]{x0, x1});
        x0.mDeliveryModule = x1;
        return x1;
    }

    static /* synthetic */ Date access$802(OrderMethodSelectorFragment x0, Date x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment", "access$802", new Object[]{x0, x1});
        x0.mAsapEtaDate = x1;
        return x1;
    }

    public void onCreate(Bundle savedInstanceState) {
        boolean z;
        super.onCreate(savedInstanceState);
        this.mDeliveryEnabled = ModuleManager.isModuleEnabled(DeliveryModule.NAME);
        this.mCustomerModule = (CustomerModule) ModuleManager.getModule(CustomerModule.NAME);
        this.mSendToDeliveryWebsite = Configuration.getSharedInstance().getBooleanForKey("modules.delivery.sendToDeliveryWebsite");
        if (this.mDeliveryEnabled.booleanValue()) {
            this.mLoggedIn = this.mCustomerModule.isLoggedIn();
            if (!OrderingManager.getInstance().getCurrentOrder().isDelivery()) {
                this.mState = 1;
            } else if (this.mSendToDeliveryWebsite) {
                this.mState = 5;
            } else {
                this.mState = this.mLoggedIn ? 2 : 3;
                getDeliveryWindow();
            }
        } else {
            this.mState = 0;
        }
        if (LocalDataManager.getSharedInstance().isFirstTimeOrdering()) {
            z = false;
        } else {
            z = true;
        }
        this.mStoreSelected = z;
        this.mDeliveryDate = Calendar.getInstance();
    }

    private void setupStoreSelectionController() {
        Ensighten.evaluateEvent(this, "setupStoreSelectionController", null);
        StoreSelectionController storeSelectionController = new StoreSelectionController(getContext());
        storeSelectionController.addListener(new C34932());
        this.mDataProvider = storeSelectionController;
        this.mInteractionListener = storeSelectionController;
        this.mStoreSelected = !LocalDataManager.getSharedInstance().isFirstTimeOrdering();
        this.mDeliveryDate = Calendar.getInstance();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(C2658R.layout.dialog_fragment_delivery_method, container, false);
        this.mTitle = (TextView) rootView.findViewById(2131820647);
        this.mExpandedContent = rootView.findViewById(C2358R.C2357id.expanded_view);
        this.mCollapseButton = (ImageButton) rootView.findViewById(C2358R.C2357id.expand_content_button);
        RadioGroup deliveryWhenSelector = (RadioGroup) this.mExpandedContent.findViewById(C2358R.C2357id.delivery_when_selector);
        this.mSchedDeliveryDateSelector = (CheckableRelativeLayout) deliveryWhenSelector.findViewById(C2358R.C2357id.sched_selection);
        this.mMethodSelectorGroup = (RadioGroup) this.mExpandedContent.findViewById(C2358R.C2357id.method_selector);
        this.mMethodSelectorPickup = (RadioButton) this.mExpandedContent.findViewById(C2358R.C2357id.delivery_method_pickup);
        this.mMethodSelectorDelivery = (RadioButton) this.mExpandedContent.findViewById(C2358R.C2357id.delivery_method_delivery);
        this.mPickupLayout = this.mExpandedContent.findViewById(C2358R.C2357id.pickup_layout);
        this.mDeliveryLayout = this.mExpandedContent.findViewById(C2358R.C2357id.delivery_layout);
        this.mDeliveryLoggedOutLayout = this.mExpandedContent.findViewById(C2358R.C2357id.delivery_logged_out_layout);
        this.mDeliverySaveButton = (Button) this.mExpandedContent.findViewById(C2358R.C2357id.delivery_save_button);
        this.mOrderWhereButton = (Button) this.mExpandedContent.findViewById(C2358R.C2357id.order_where_button);
        this.mOrderWhenText = (TextView) this.mExpandedContent.findViewById(C2358R.C2357id.order_when);
        this.mOrderFrom = (TextView) this.mExpandedContent.findViewById(C2358R.C2357id.order_from);
        this.mAsapSelector = (CheckableRelativeLayout) deliveryWhenSelector.findViewById(C2358R.C2357id.asap_selection);
        this.mAsapETAText = (TextView) this.mAsapSelector.findViewById(C2358R.C2357id.check_box_1_subtitle);
        this.mStorePager = (ViewPager) this.mExpandedContent.findViewById(C2358R.C2357id.store_pager);
        this.mExternalDeliveryView = (ExternalDeliveryView) this.mExpandedContent.findViewById(C2358R.C2357id.mcdelivery_layout);
        this.mCollapseButton.setOnClickListener(new C34943());
        Button findAnotherButton = (Button) rootView.findViewById(C2358R.C2357id.find_another_button);
        this.mContinueButton = (Button) rootView.findViewById(C2358R.C2357id.continue_button);
        final TextView schedDeliveryDateText = (TextView) this.mSchedDeliveryDateSelector.findViewById(C2358R.C2357id.sched_delivery_date_text);
        final CheckableRelativeLayout schedDeliveryTimeSelector = (CheckableRelativeLayout) deliveryWhenSelector.findViewById(C2358R.C2357id.sched_selection);
        final TextView schedDeliveryTimeText = (TextView) schedDeliveryTimeSelector.findViewById(C2358R.C2357id.sched_delivery_date_text);
        TextView signInText = (TextView) this.mDeliveryLoggedOutLayout.findViewById(C2358R.C2357id.delivery_sign_in_text);
        Button signUpButton = (Button) this.mDeliveryLoggedOutLayout.findViewById(C2358R.C2357id.delivery_sign_up_button);
        findAnotherButton.setOnClickListener(new C34954());
        this.mContinueButton.setOnClickListener(new C34965());
        getDeliveryWindow();
        this.mDeliverySaveButton.setEnabled(false);
        this.mDeliverySaveButton.setOnClickListener(new C34986());
        if (OrderingManager.getInstance().getCurrentOrder().isDelivery()) {
            this.mDeliveryAddress = OrderingManager.getInstance().getCurrentOrder().getDeliveryAddress().getFullAddress();
            this.mOrderWhereButton.setText(this.mDeliveryAddress);
            setWhereButtonTextSize(12);
            this.mDeliveryStore = OrderingManager.getInstance().getCurrentOrder().getDeliveryStore();
            final CustomerAddress address = OrderingManager.getInstance().getCurrentOrder().getDeliveryAddress();
            if (address != null) {
                if (this.mDeliveryStore == null) {
                    UIUtils.startActivityIndicator(getContext(), (int) C2658R.string.sl_retrieve_stores);
                    this.mDeliveryModule = (DeliveryModule) ModuleManager.getModule(DeliveryModule.NAME);
                    this.mDeliveryModule.getDeliveryStore(address, null, this.mCustomerModule.getCurrentProfile(), new AsyncListener<Store>() {
                        public void onResponse(Store response, AsyncToken token, AsyncException exception) {
                            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
                            if (exception == null) {
                                OrderMethodSelectorFragment.access$502(OrderMethodSelectorFragment.this, response);
                                OrderMethodSelectorFragment.access$802(OrderMethodSelectorFragment.this, UIUtils.getDateFromISO8601(response.getExpectedDeliveryTime()));
                                String asapEtaDateString = UIUtils.formatDeliveryTime(OrderMethodSelectorFragment.this.getContext(), response.getCurrentDate(), Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment", "access$800", new Object[]{OrderMethodSelectorFragment.this}), true);
                                if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment", "access$800", new Object[]{OrderMethodSelectorFragment.this}) != null) {
                                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment", "access$900", new Object[]{OrderMethodSelectorFragment.this}).setText(C2658R.string.delivery_time_preface);
                                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment", "access$900", new Object[]{OrderMethodSelectorFragment.this}).setVisibility(0);
                                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment", "access$1000", new Object[]{OrderMethodSelectorFragment.this}).setVisibility(0);
                                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment", "access$1100", new Object[]{OrderMethodSelectorFragment.this}).setVisibility(0);
                                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment", "access$1200", new Object[]{OrderMethodSelectorFragment.this}).setVisibility(0);
                                }
                                Order currentOrder = OrderingManager.getInstance().getCurrentOrder();
                                currentOrder.setIsDelivery(true);
                                currentOrder.setDeliveryAddress(address);
                                currentOrder.setDeliveryDate(Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment", "access$800", new Object[]{OrderMethodSelectorFragment.this}));
                                currentOrder.setDeliveryDateString(asapEtaDateString);
                                currentOrder.setPriceType(PriceType.Delivery);
                                OrderMethodSelectorFragment.access$1302(OrderMethodSelectorFragment.this, true);
                                if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment", "access$1400", new Object[]{OrderMethodSelectorFragment.this}) == null) {
                                    OrderMethodSelectorFragment.access$1402(OrderMethodSelectorFragment.this, Calendar.getInstance());
                                }
                                Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment", "access$1400", new Object[]{OrderMethodSelectorFragment.this}).setTime(Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment", "access$800", new Object[]{OrderMethodSelectorFragment.this}));
                                OrderMethodSelectorFragment.access$1500(OrderMethodSelectorFragment.this);
                                OrderMethodSelectorFragment.access$1600(OrderMethodSelectorFragment.this);
                            } else {
                                AsyncException.report(exception);
                            }
                            UIUtils.stopActivityIndicator();
                        }
                    });
                } else {
                    this.mAsapEtaDate = UIUtils.getDateFromISO8601(this.mDeliveryStore.getExpectedDeliveryTime());
                    String asapEtaDateString = UIUtils.formatDeliveryTime(getContext(), this.mDeliveryStore.getCurrentDate(), this.mAsapEtaDate, true);
                    if (this.mAsapEtaDate != null) {
                        this.mAsapETAText.setText(C2658R.string.delivery_time_preface);
                        this.mAsapETAText.setVisibility(0);
                        this.mAsapSelector.setVisibility(0);
                        this.mSchedDeliveryDateSelector.setVisibility(0);
                        this.mOrderWhenText.setVisibility(0);
                    }
                    Order currentOrder = OrderingManager.getInstance().getCurrentOrder();
                    currentOrder.setIsDelivery(true);
                    currentOrder.setDeliveryAddress(address);
                    currentOrder.setDeliveryDate(this.mAsapEtaDate);
                    currentOrder.setDeliveryDateString(asapEtaDateString);
                    currentOrder.setPriceType(PriceType.Delivery);
                    this.mDeliveryStoreSelected = true;
                    if (this.mDeliveryDate == null) {
                        this.mDeliveryDate = Calendar.getInstance();
                    }
                    this.mDeliveryDate.setTime(this.mAsapEtaDate);
                    verifyDeliverySaveButtonState();
                    updateViewsForStateChange();
                }
            }
        }
        this.mOrderWhereButton.setOnClickListener(new C35008());
        this.mOrderWhenText.setVisibility(8);
        this.mAsapSelector.setOnCheckedChangeListener(new C35019());
        this.mAsapSelector.setChecked(true);
        this.mAsapSelector.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment", "access$1000", new Object[]{OrderMethodSelectorFragment.this}).setChecked(true);
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment", "access$1100", new Object[]{OrderMethodSelectorFragment.this}).setChecked(false);
                schedDeliveryTimeSelector.setChecked(false);
                schedDeliveryTimeSelector.setVisibility(8);
                schedDeliveryDateText.setText(OrderMethodSelectorFragment.this.getResources().getString(C2658R.string.schedule_delivery));
                schedDeliveryTimeText.setText(OrderMethodSelectorFragment.this.getResources().getString(C2658R.string.choose_time));
                schedDeliveryTimeText.setTypeface(Typeface.DEFAULT);
                if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment", "access$500", new Object[]{OrderMethodSelectorFragment.this}) != null) {
                    String asap = UIUtils.formatTime(OrderMethodSelectorFragment.this.getContext(), Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment", "access$800", new Object[]{OrderMethodSelectorFragment.this}));
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment", "access$900", new Object[]{OrderMethodSelectorFragment.this}).setText(OrderMethodSelectorFragment.this.getString(C2658R.string.delivery_time_preface));
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment", "access$900", new Object[]{OrderMethodSelectorFragment.this}).setVisibility(0);
                }
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment", "access$1400", new Object[]{OrderMethodSelectorFragment.this}).setTime(Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment", "access$800", new Object[]{OrderMethodSelectorFragment.this}));
                OrderMethodSelectorFragment.access$1902(OrderMethodSelectorFragment.this, false);
                OrderMethodSelectorFragment.access$2002(OrderMethodSelectorFragment.this, false);
                OrderMethodSelectorFragment.access$1802(OrderMethodSelectorFragment.this, true);
            }
        });
        this.mSchedDeliveryDateSelector.setOnClickListener(new OnClickListener() {

            /* renamed from: com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment$11$1 */
            class C34761 implements OnDateSetListener {
                C34761() {
                }

                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    Ensighten.evaluateEvent(this, "onDateSet", new Object[]{view, new Integer(year), new Integer(monthOfYear), new Integer(dayOfMonth)});
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE, MMMM dd");
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment", "access$1400", new Object[]{OrderMethodSelectorFragment.this}).set(1, year);
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment", "access$1400", new Object[]{OrderMethodSelectorFragment.this}).set(2, monthOfYear);
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment", "access$1400", new Object[]{OrderMethodSelectorFragment.this}).set(5, dayOfMonth);
                    OrderMethodSelectorFragment.access$2102(OrderMethodSelectorFragment.this, new Date(year, monthOfYear, dayOfMonth));
                    schedDeliveryDateText.setText(simpleDateFormat.format(Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment", "access$2100", new Object[]{OrderMethodSelectorFragment.this})) + UIUtils.getDayOfMonthSuffix(dayOfMonth));
                    schedDeliveryDateText.setTypeface(Typeface.DEFAULT_BOLD);
                    OrderMethodSelectorFragment.access$2002(OrderMethodSelectorFragment.this, true);
                    OrderMethodSelectorFragment.access$1500(OrderMethodSelectorFragment.this);
                }
            }

            public void onClick(View v) {
                Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
                OrderMethodSelectorFragment.access$2002(OrderMethodSelectorFragment.this, false);
                OrderMethodSelectorFragment.access$1902(OrderMethodSelectorFragment.this, false);
                OrderMethodSelectorFragment.access$1802(OrderMethodSelectorFragment.this, false);
                schedDeliveryTimeText.setText(OrderMethodSelectorFragment.this.getResources().getString(C2658R.string.choose_time));
                schedDeliveryTimeText.setTypeface(Typeface.DEFAULT);
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment", "access$1000", new Object[]{OrderMethodSelectorFragment.this}).setChecked(false);
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment", "access$900", new Object[]{OrderMethodSelectorFragment.this}).setVisibility(8);
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment", "access$900", new Object[]{OrderMethodSelectorFragment.this}).setText("");
                schedDeliveryTimeSelector.setChecked(false);
                schedDeliveryTimeSelector.setVisibility(0);
                if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment", "access$1100", new Object[]{OrderMethodSelectorFragment.this}).isChecked()) {
                    OnDateSetListener onDateSetListener = new C34761();
                    Calendar calendar = Calendar.getInstance();
                    DatePickerDialog datePickerDialog = new DatePickerDialog(OrderMethodSelectorFragment.this.getNavigationActivity(), C2658R.style.PickerTheme, onDateSetListener, calendar.get(1), calendar.get(2), calendar.get(5));
                    datePickerDialog.setMessage(OrderMethodSelectorFragment.this.getString(C2658R.string.delivery_orders_max_advance, DateUtils.formatRange(OrderMethodSelectorFragment.this.getActivity(), (long) (Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment", "access$2200", new Object[]{OrderMethodSelectorFragment.this}) * 60000))));
                    DatePicker datePicker = datePickerDialog.getDatePicker();
                    datePicker.setMinDate(calendar.getTimeInMillis());
                    datePicker.setMaxDate(Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment", "access$2300", new Object[]{OrderMethodSelectorFragment.this}).getTimeInMillis());
                    datePickerDialog.show();
                    return;
                }
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment", "access$1100", new Object[]{OrderMethodSelectorFragment.this}).setChecked(true);
                schedDeliveryDateText.setText(OrderMethodSelectorFragment.this.getResources().getString(C2658R.string.choose_date));
                schedDeliveryDateText.setTypeface(Typeface.DEFAULT);
            }
        });
        schedDeliveryTimeSelector.setOnClickListener(new OnClickListener() {

            /* renamed from: com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment$12$1 */
            class C34781 implements OnTimeSetListener {
                C34781() {
                }

                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    Ensighten.evaluateEvent(this, "onTimeSet", new Object[]{view, new Integer(hourOfDay), new Integer(minute)});
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment", "access$1400", new Object[]{OrderMethodSelectorFragment.this}).set(11, hourOfDay);
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment", "access$1400", new Object[]{OrderMethodSelectorFragment.this}).set(12, minute);
                    String amPm = Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment", "access$1400", new Object[]{OrderMethodSelectorFragment.this}).get(9) == 0 ? "AM" : "PM";
                    SimpleDateFormat hourFormat = new SimpleDateFormat("hh:mm");
                    OrderMethodSelectorFragment.access$2502(OrderMethodSelectorFragment.this, Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment", "access$1400", new Object[]{OrderMethodSelectorFragment.this}).getTime());
                    schedDeliveryTimeText.setText(String.format("%s %s", new Object[]{hourFormat.format(Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment", "access$2500", new Object[]{OrderMethodSelectorFragment.this})), amPm}));
                    schedDeliveryTimeText.setTypeface(Typeface.DEFAULT_BOLD);
                    schedDeliveryTimeSelector.setChecked(true);
                    OrderMethodSelectorFragment.access$1902(OrderMethodSelectorFragment.this, true);
                    OrderMethodSelectorFragment.access$1500(OrderMethodSelectorFragment.this);
                }
            }

            public void onClick(View v) {
                int minHour;
                int minMinute;
                int maxHour;
                int maxMinute;
                Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
                OrderMethodSelectorFragment.access$1902(OrderMethodSelectorFragment.this, false);
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment", "access$1000", new Object[]{OrderMethodSelectorFragment.this}).setChecked(false);
                OrderMethodSelectorFragment.access$1802(OrderMethodSelectorFragment.this, false);
                if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment", "access$2400", new Object[]{OrderMethodSelectorFragment.this}).get(1) == Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment", "access$1400", new Object[]{OrderMethodSelectorFragment.this}).get(1) && Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment", "access$2400", new Object[]{OrderMethodSelectorFragment.this}).get(6) == Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment", "access$1400", new Object[]{OrderMethodSelectorFragment.this}).get(6)) {
                    minHour = Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment", "access$2400", new Object[]{OrderMethodSelectorFragment.this}).get(11);
                    minMinute = Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment", "access$2400", new Object[]{OrderMethodSelectorFragment.this}).get(12);
                    maxHour = 24;
                    maxMinute = 0;
                } else if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment", "access$2300", new Object[]{OrderMethodSelectorFragment.this}).get(1) == Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment", "access$1400", new Object[]{OrderMethodSelectorFragment.this}).get(1) && Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment", "access$2300", new Object[]{OrderMethodSelectorFragment.this}).get(6) == Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment", "access$1400", new Object[]{OrderMethodSelectorFragment.this}).get(6)) {
                    minHour = 0;
                    minMinute = 0;
                    maxHour = Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment", "access$2300", new Object[]{OrderMethodSelectorFragment.this}).get(11);
                    maxMinute = Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment", "access$2300", new Object[]{OrderMethodSelectorFragment.this}).get(12);
                } else {
                    minHour = 0;
                    minMinute = 0;
                    maxHour = 24;
                    maxMinute = 0;
                }
                RangeTimePickerDialog rangeTimePickerDialog = new RangeTimePickerDialog(OrderMethodSelectorFragment.this.getNavigationActivity(), C2658R.style.PickerTheme, new C34781(), minHour, minMinute, false);
                rangeTimePickerDialog.setMessage(OrderMethodSelectorFragment.this.getString(C2658R.string.delivery_orders_min_advance, DateUtils.formatRange(OrderMethodSelectorFragment.this.getActivity(), (long) (Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment", "access$2600", new Object[]{OrderMethodSelectorFragment.this}) * 60000))));
                rangeTimePickerDialog.setMin(minHour, minMinute);
                rangeTimePickerDialog.setMax(maxHour, maxMinute);
                rangeTimePickerDialog.show();
            }
        });
        signUpButton.setOnClickListener(new C348013());
        signInText.setText(Html.fromHtml(getResources().getString(C2658R.string.delivery_sign_in)));
        signInText.setOnClickListener(new C348114());
        this.mWarningTextView = (TextView) rootView.findViewById(C2358R.C2357id.store_warning);
        return rootView;
    }

    private void navigateToAddressFragment() {
        Ensighten.evaluateEvent(this, "navigateToAddressFragment", null);
        Analytics.track(AnalyticType.Event, new ArgBuilder().setCategory("/checkout/delivery").setAction("On click").setLabel("Delivery Address").build());
        Bundle args = new Bundle();
        args.putBoolean("update_default", false);
        if (getNavigationActivity() != null) {
            startActivityForResult(ModifyAddressesActivity.class, ModifyAddressesFragment.NAME, args, 6274);
        }
    }

    private void finalizeDeliveryOrder(Store response, Order deliveryOrder) {
        Ensighten.evaluateEvent(this, "finalizeDeliveryOrder", new Object[]{response, deliveryOrder});
        deliveryOrder.setDeliveryStore(response);
        Order currentOrder = OrderingManager.getInstance().getCurrentOrder();
        currentOrder.setIsDelivery(true);
        currentOrder.setPriceType(PriceType.Delivery);
        currentOrder.setStoreId(Integer.toString(currentOrder.getDeliveryStore().getStoreId()));
        OrderingManager.getInstance().updateCurrentOrder(deliveryOrder);
        this.mCustomerModule.getCatalogUpdated(this.mCustomerModule.getCurrentProfile(), new C348215());
    }

    public void changeState(boolean change) {
        Ensighten.evaluateEvent(this, "changeState", new Object[]{new Boolean(change)});
        if (!change) {
            this.mState = this.mPreviousState;
        }
        updateViewsForStateChange();
    }

    private void initMethodSelectorGroup() {
        Ensighten.evaluateEvent(this, "initMethodSelectorGroup", null);
        this.mMethodSelectorGroup.setVisibility(0);
        final boolean mClearBasketOnOrderTypeChange = Configuration.getSharedInstance().getBooleanForKey("interface.ordering.clearBasketOnOrderTypeChange");
        this.mMethodSelectorPickup.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                int i = 1;
                Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
                OrderMethodSelectorFragment.access$2802(OrderMethodSelectorFragment.this, Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment", "access$200", new Object[]{OrderMethodSelectorFragment.this}));
                OrderMethodSelectorFragment orderMethodSelectorFragment = OrderMethodSelectorFragment.this;
                if (!Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment", "access$2900", new Object[]{OrderMethodSelectorFragment.this}).booleanValue()) {
                    i = 0;
                }
                OrderMethodSelectorFragment.access$202(orderMethodSelectorFragment, i);
                AnalyticsUtils.trackOnClickEvent("/checkout/restaurant", "Visit Restaurant");
                if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment", "access$200", new Object[]{OrderMethodSelectorFragment.this}) == Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment", "access$2800", new Object[]{OrderMethodSelectorFragment.this})) {
                    return;
                }
                if (mClearBasketOnOrderTypeChange) {
                    Order currentOrder = OrderingManager.getInstance().getCurrentOrder();
                    if (currentOrder == null || currentOrder.getBasketCounter() <= 0) {
                        OrderMethodSelectorFragment.access$1600(OrderMethodSelectorFragment.this);
                        return;
                    } else {
                        new OnDeliveryMethodChangeDialog().show(OrderMethodSelectorFragment.this.getNavigationActivity().getSupportFragmentManager(), "OnDeliveryMethodChangeDialog");
                        return;
                    }
                }
                OrderMethodSelectorFragment.access$1600(OrderMethodSelectorFragment.this);
            }
        });
        this.mMethodSelectorDelivery.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
                OrderMethodSelectorFragment.access$2802(OrderMethodSelectorFragment.this, Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment", "access$200", new Object[]{OrderMethodSelectorFragment.this}));
                AnalyticsUtils.trackOnClickEvent("/checkout/delivery", Pod.DELIVERY);
                if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment", "access$3000", new Object[]{OrderMethodSelectorFragment.this})) {
                    OrderMethodSelectorFragment.access$202(OrderMethodSelectorFragment.this, 5);
                } else {
                    OrderMethodSelectorFragment.access$202(OrderMethodSelectorFragment.this, Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment", "access$3100", new Object[]{OrderMethodSelectorFragment.this}) ? 2 : 3);
                    OrderMethodSelectorFragment.access$3200(OrderMethodSelectorFragment.this);
                }
                if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment", "access$200", new Object[]{OrderMethodSelectorFragment.this}) == Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment", "access$2800", new Object[]{OrderMethodSelectorFragment.this})) {
                    return;
                }
                if (mClearBasketOnOrderTypeChange) {
                    Order currentOrder = OrderingManager.getInstance().getCurrentOrder();
                    if (currentOrder == null || currentOrder.getBasketCounter() <= 0) {
                        OrderMethodSelectorFragment.access$1600(OrderMethodSelectorFragment.this);
                        return;
                    } else {
                        new OnDeliveryMethodChangeDialog().show(OrderMethodSelectorFragment.this.getNavigationActivity().getSupportFragmentManager(), "OnDeliveryMethodChangeDialog");
                        return;
                    }
                }
                OrderMethodSelectorFragment.access$1600(OrderMethodSelectorFragment.this);
            }
        });
    }

    private void initStorePager() {
        boolean hasOrdering;
        int i;
        Ensighten.evaluateEvent(this, "initStorePager", null);
        List<Store> stores = new ArrayList();
        List<Store> favorites = new ArrayList();
        favorites.addAll(this.mCustomerModule.getFavoriteStores());
        Store current = this.mCustomerModule.getCurrentStore();
        if (current != null) {
            stores.add(current);
            Iterator<Store> iterator = favorites.iterator();
            while (iterator.hasNext()) {
                if (((Store) iterator.next()).getStoreId() == current.getStoreId()) {
                    iterator.remove();
                }
            }
        }
        stores.addAll(favorites);
        List<Store> storeList = getStoresWithOrdering(stores);
        if (storeList.isEmpty()) {
            hasOrdering = false;
        } else {
            hasOrdering = true;
        }
        this.mContinueButton.setEnabled(hasOrdering);
        TextView textView = this.mOrderFrom;
        if (hasOrdering) {
            i = 0;
        } else {
            i = 8;
        }
        textView.setVisibility(i);
        ViewPager viewPager = this.mStorePager;
        if (hasOrdering) {
            i = 0;
        } else {
            i = 8;
        }
        viewPager.setVisibility(i);
        textView = this.mWarningTextView;
        if (hasOrdering) {
            i = 8;
        } else {
            i = 0;
        }
        textView.setVisibility(i);
        if (storeList.size() >= 2) {
            for (int i2 = 0; i2 < this.mIndicators.length; i2++) {
                RadioButton radioButton = this.mIndicators[i2];
                if (i2 < storeList.size()) {
                    i = 0;
                } else {
                    i = 8;
                }
                radioButton.setVisibility(i);
            }
        }
        if (this.mStoresAdapter == null) {
            this.mStoresAdapter = new StorePagerFragmentAdapter(storeList);
            this.mStorePager.setAdapter(this.mStoresAdapter);
        } else {
            this.mStoresAdapter.setStores(storeList);
            this.mStoresAdapter.notifyDataSetChanged();
        }
        this.mStorePager.setOffscreenPageLimit(3);
        this.mStorePager.setOnPageChangeListener(this.mOnStorePageChange);
    }

    private List<Store> getStoresWithOrdering(List<Store> storeList) {
        Ensighten.evaluateEvent(this, "getStoresWithOrdering", new Object[]{storeList});
        List<Store> stores = new ArrayList();
        if (storeList != null) {
            int size = storeList.size();
            for (int i = 0; i < size; i++) {
                Store store = (Store) storeList.get(i);
                if (store.hasMobileOrdering().booleanValue()) {
                    stores.add(store);
                }
            }
        }
        return stores;
    }

    private void updateViewsForStateChange() {
        Ensighten.evaluateEvent(this, "updateViewsForStateChange", null);
        switch (this.mState) {
            case 0:
                this.mStoreSelected = false;
                this.mMethodSelectorGroup.setVisibility(8);
                this.mMethodSelectorPickup.setVisibility(4);
                this.mMethodSelectorDelivery.setVisibility(8);
                this.mDeliveryLayout.setVisibility(8);
                this.mExternalDeliveryView.setVisibility(8);
                this.mPickupLayout.setVisibility(0);
                this.mDeliveryLoggedOutLayout.setVisibility(8);
                this.mMethodSelectorGroup.check(C2358R.C2357id.delivery_method_pickup);
                this.mTitle.setText(getResources().getString(C2658R.string.pickup_settings_title));
                this.mOrderFrom.setText(getResources().getString(C2658R.string.order_from_label));
                return;
            case 1:
                this.mStoreSelected = false;
                this.mMethodSelectorGroup.setVisibility(0);
                this.mMethodSelectorPickup.setVisibility(0);
                this.mMethodSelectorDelivery.setVisibility(0);
                this.mDeliveryLayout.setVisibility(8);
                this.mExternalDeliveryView.setVisibility(8);
                this.mPickupLayout.setVisibility(0);
                this.mDeliveryLoggedOutLayout.setVisibility(8);
                this.mMethodSelectorGroup.check(C2358R.C2357id.delivery_method_pickup);
                this.mTitle.setText(getResources().getString(C2658R.string.pickup_settings_title));
                this.mOrderFrom.setText(getResources().getString(C2658R.string.order_from_label));
                return;
            case 2:
            case 4:
                this.mStoreSelected = false;
                this.mMethodSelectorGroup.setVisibility(0);
                this.mMethodSelectorPickup.setVisibility(0);
                this.mMethodSelectorDelivery.setVisibility(0);
                this.mDeliveryLayout.setVisibility(0);
                this.mExternalDeliveryView.setVisibility(8);
                this.mPickupLayout.setVisibility(8);
                this.mDeliveryLoggedOutLayout.setVisibility(8);
                this.mMethodSelectorGroup.check(C2358R.C2357id.delivery_method_delivery);
                this.mTitle.setText(getResources().getString(C2658R.string.delivery_settings_title));
                this.mOrderFrom.setText(getResources().getString(C2658R.string.order_where));
                return;
            case 3:
                this.mStoreSelected = false;
                this.mDeliveryLoggedOutLayout.setVisibility(0);
                this.mDeliveryLayout.setVisibility(8);
                this.mPickupLayout.setVisibility(8);
                this.mExternalDeliveryView.setVisibility(8);
                this.mMethodSelectorGroup.setVisibility(0);
                this.mMethodSelectorPickup.setVisibility(0);
                this.mMethodSelectorDelivery.setVisibility(0);
                this.mMethodSelectorGroup.check(C2358R.C2357id.delivery_method_delivery);
                this.mTitle.setText(getResources().getString(C2658R.string.delivery_settings_title));
                return;
            case 5:
                this.mStoreSelected = false;
                this.mMethodSelectorGroup.setVisibility(0);
                this.mMethodSelectorPickup.setVisibility(0);
                this.mMethodSelectorDelivery.setVisibility(0);
                this.mDeliveryLayout.setVisibility(8);
                this.mDeliveryLoggedOutLayout.setVisibility(8);
                this.mExternalDeliveryView.setVisibility(0);
                this.mPickupLayout.setVisibility(8);
                this.mMethodSelectorGroup.check(C2358R.C2357id.delivery_method_delivery);
                this.mTitle.setText(getResources().getString(C2658R.string.delivery_settings_title));
                return;
            default:
                return;
        }
    }

    private void expandContent(final AsyncListener<Void> listener) {
        Ensighten.evaluateEvent(this, "expandContent", new Object[]{listener});
        Animation counterClockwiseRotation = AnimationUtils.loadAnimation(getNavigationActivity(), C2658R.anim.rotate);
        counterClockwiseRotation.setDuration(400);
        counterClockwiseRotation.setFillAfter(true);
        this.mCollapseButton.setAnimation(counterClockwiseRotation);
        this.mCollapseButton.animate();
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            public void run() {
                Ensighten.evaluateEvent(this, "run", null);
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment", "access$3300", new Object[]{OrderMethodSelectorFragment.this}).setVisibility(0);
                OrderMethodSelectorFragment.access$302(OrderMethodSelectorFragment.this, (CustomerModule) ModuleManager.getModule(CustomerModule.NAME));
                if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment", "access$2900", new Object[]{OrderMethodSelectorFragment.this}).booleanValue()) {
                    OrderMethodSelectorFragment.access$702(OrderMethodSelectorFragment.this, (DeliveryModule) ModuleManager.getModule(DeliveryModule.NAME));
                    OrderMethodSelectorFragment.access$3402(OrderMethodSelectorFragment.this, (OrderingModule) ModuleManager.getModule("ordering"));
                    OrderMethodSelectorFragment.access$3500(OrderMethodSelectorFragment.this);
                }
                if (listener != null) {
                    listener.onResponse(null, null, null);
                }
            }
        }, 400);
    }

    private void getDeliveryWindow() {
        Ensighten.evaluateEvent(this, "getDeliveryWindow", null);
        if (this.mMinutesUntilEarliestOrder == 0) {
            long currentTimeMillis = System.currentTimeMillis();
            this.mOrderingModule = (OrderingModule) ModuleManager.getModule("ordering");
            this.mMinutesUntilEarliestOrder = getSelectedStore().getMinAdvanceOrderTime();
            this.mMinutesUntilLatestOrder = getSelectedStore().getMaxAdvanceOrderTime();
            if (this.mMinutesUntilEarliestOrder == 0 && this.mMinutesUntilLatestOrder == 0) {
                this.mMinutesUntilEarliestOrder = this.mOrderingModule.getMinMinutesToAdvOrder();
                this.mMinutesUntilLatestOrder = this.mOrderingModule.getMaxMinutesToAdvOrder();
            }
            long startTimeMillis = currentTimeMillis + ((long) (this.mMinutesUntilEarliestOrder * 60000));
            this.mCalStart = Calendar.getInstance();
            this.mCalStart.setTimeInMillis(startTimeMillis);
            long endTimeMillis = currentTimeMillis + ((long) (this.mMinutesUntilLatestOrder * 60000));
            this.mCalEnd = Calendar.getInstance();
            this.mCalEnd.setTimeInMillis(endTimeMillis);
        }
    }

    public void onBackPressed(AsyncListener<Boolean> listener) {
        Ensighten.evaluateEvent(this, "onBackPressed", new Object[]{listener});
        collapseContent(listener);
    }

    private void collapseContent(final AsyncListener<Boolean> listener) {
        Ensighten.evaluateEvent(this, "collapseContent", new Object[]{listener});
        this.mExpandedContent.setVisibility(8);
        Animation clockwiseRotation = AnimationUtils.loadAnimation(getNavigationActivity(), C2658R.anim.rotate_reset);
        clockwiseRotation.setDuration(400);
        clockwiseRotation.setFillAfter(true);
        this.mCollapseButton.startAnimation(clockwiseRotation);
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            public void run() {
                Ensighten.evaluateEvent(this, "run", null);
                if (listener != null) {
                    listener.onResponse(Boolean.valueOf(Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment", "access$400", new Object[]{OrderMethodSelectorFragment.this})), null, null);
                }
            }
        }, 500);
    }

    public void onInfoButtonPressed(Store store) {
        Ensighten.evaluateEvent(this, "onInfoButtonPressed", new Object[]{store});
        AnalyticsUtils.trackOnClickEvent("/restaurant-locator", "Info Icon");
        Bundle bundle = new Bundle();
        bundle.putParcelable("extra_store_detail", store);
        bundle.putInt("extra_store_section", StoreLocatorSection.Current.ordinal());
        startActivity(StoreDetailsActivity.class, StoreDetailsFragment.NAME, bundle);
    }

    public void onStoreSelected(Store store) {
        Ensighten.evaluateEvent(this, "onStoreSelected", new Object[]{store});
        this.mSelectedStore = store;
        this.mInteractionListener.selectStore(Integer.valueOf(store.getStoreId()), null);
    }

    public Store getSelectedStore() {
        Ensighten.evaluateEvent(this, "getSelectedStore", null);
        if (this.mSelectedStore == null) {
            return OrderManager.getInstance().getCurrentStore();
        }
        return this.mSelectedStore;
    }

    private void verifyDeliverySaveButtonState() {
        boolean isDeliveryOK = true;
        Ensighten.evaluateEvent(this, "verifyDeliverySaveButtonState", null);
        Order deliveryOrder = OrderingManager.getInstance().getCurrentOrder();
        boolean timeWithinWindow = false;
        if (this.mDateSet) {
            int i;
            boolean z = this.mTimeSet;
            if (this.mAsapSelected) {
                i = 0;
            } else {
                i = 1;
            }
            if ((i & z) != 0) {
                if (this.mDeliveryTime.after(this.mCalStart.getTime()) && this.mDeliveryTime.before(this.mCalEnd.getTime())) {
                    timeWithinWindow = true;
                    deliveryOrder.setNormalOrder(false);
                } else {
                    MCDAlertDialogBuilder.withContext(getNavigationActivity()).setCancelable(false).setNeutralButton((int) C2658R.string.f6083ok, null).setMessage(getResources().getString(C2658R.string.date_before_current_time)).create().show();
                }
            }
        }
        if (!(this.mDeliveryStoreSelected && ((this.mDateSet && this.mTimeSet && timeWithinWindow) || this.mAsapSelected))) {
            isDeliveryOK = false;
        }
        this.mDeliverySaveButton.setEnabled(isDeliveryOK);
        this.mDeliverySaveButton.setBackgroundResource(isDeliveryOK ? C2358R.C2359drawable.button_red : C2358R.C2359drawable.button_light_gray);
        if (isDeliveryOK) {
            deliveryOrder.setDeliveryDate(this.mDeliveryDate.getTime());
            deliveryOrder.setDeliveryDateString(((SimpleDateFormat) DELIVERY_DATE_FORMATTER.get()).format(this.mDeliveryDate.getTime()));
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 18 && resultCode == -1) {
            initStorePager();
            ((StoreSelectionController) this.mInteractionListener).refresh();
        } else if (data != null && data.getExtras() != null) {
            this.mState = 4;
            final CustomerAddress address = (CustomerAddress) data.getExtras().getParcelable("ADDRESS_RETURN_KEY");
            if (address != null) {
                this.mOrderWhereButton.setText(address.getFullAddress());
                setWhereButtonTextSize(12);
                UIUtils.startActivityIndicator(getContext(), (int) C2658R.string.sl_retrieve_stores);
                this.mDeliveryModule.getDeliveryStore(address, null, this.mCustomerModule.getCurrentProfile(), new AsyncListener<Store>() {
                    public void onResponse(Store response, AsyncToken token, AsyncException exception) {
                        Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
                        if (exception == null) {
                            OrderMethodSelectorFragment.access$502(OrderMethodSelectorFragment.this, response);
                            OrderMethodSelectorFragment.access$802(OrderMethodSelectorFragment.this, UIUtils.getDateFromISO8601(response.getExpectedDeliveryTime()));
                            String asapEtaDateString = UIUtils.formatDeliveryTime(OrderMethodSelectorFragment.this.getContext(), response.getCurrentDate(), Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment", "access$800", new Object[]{OrderMethodSelectorFragment.this}), true);
                            if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment", "access$800", new Object[]{OrderMethodSelectorFragment.this}) != null) {
                                Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment", "access$900", new Object[]{OrderMethodSelectorFragment.this}).setText(C2658R.string.delivery_time_preface);
                                Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment", "access$900", new Object[]{OrderMethodSelectorFragment.this}).setVisibility(0);
                                Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment", "access$1000", new Object[]{OrderMethodSelectorFragment.this}).setVisibility(0);
                                Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment", "access$1100", new Object[]{OrderMethodSelectorFragment.this}).setVisibility(0);
                                Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment", "access$1200", new Object[]{OrderMethodSelectorFragment.this}).setVisibility(0);
                            }
                            Order currentOrder = OrderingManager.getInstance().getCurrentOrder();
                            currentOrder.setIsDelivery(true);
                            currentOrder.setDeliveryAddress(address);
                            currentOrder.setDeliveryDate(Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment", "access$800", new Object[]{OrderMethodSelectorFragment.this}));
                            currentOrder.setDeliveryDateString(asapEtaDateString);
                            currentOrder.setPriceType(PriceType.Delivery);
                            OrderMethodSelectorFragment.access$1302(OrderMethodSelectorFragment.this, true);
                            if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment", "access$1400", new Object[]{OrderMethodSelectorFragment.this}) == null) {
                                OrderMethodSelectorFragment.access$1402(OrderMethodSelectorFragment.this, Calendar.getInstance());
                            }
                            Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment", "access$1400", new Object[]{OrderMethodSelectorFragment.this}).setTime(Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment", "access$800", new Object[]{OrderMethodSelectorFragment.this}));
                            OrderMethodSelectorFragment.access$1500(OrderMethodSelectorFragment.this);
                            OrderMethodSelectorFragment.access$1600(OrderMethodSelectorFragment.this);
                        } else {
                            AsyncException.report(exception);
                        }
                        UIUtils.stopActivityIndicator();
                    }
                });
            }
        }
    }

    private void setWhereButtonTextSize(final int sizeInSP) {
        Ensighten.evaluateEvent(this, "setWhereButtonTextSize", new Object[]{new Integer(sizeInSP)});
        this.mOrderWhereButton.setTextSize(2, (float) sizeInSP);
        this.mOrderWhereButton.post(new Runnable() {
            public void run() {
                Ensighten.evaluateEvent(this, "run", null);
                if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorFragment", "access$3600", new Object[]{OrderMethodSelectorFragment.this}).getLineCount() > 3) {
                    OrderMethodSelectorFragment.access$3700(OrderMethodSelectorFragment.this, sizeInSP - 1);
                }
            }
        });
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupStoreSelectionController();
        expandContent(new C349123());
    }

    public void onStop() {
        super.onStop();
    }

    private void updateView() {
        Ensighten.evaluateEvent(this, "updateView", null);
        this.mDeliveryModule.getDeliveryStore(OrderingManager.getInstance().getCurrentOrder().getDeliveryAddress(), null, this.mCustomerModule.getCurrentProfile(), new C349224());
    }
}
