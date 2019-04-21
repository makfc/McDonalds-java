package com.mcdonalds.app.ordering.summary;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources.NotFoundException;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.MainActivity;
import com.mcdonalds.app.analytics.datalayer.DataLayerManager;
import com.mcdonalds.app.ordering.OrderingManager;
import com.mcdonalds.app.p043ui.URLNavigationFragment;
import com.mcdonalds.app.util.AnalyticsUtils;
import com.mcdonalds.app.util.AppUtils;
import com.mcdonalds.app.util.FavoriteInputViewHolder;
import com.mcdonalds.app.util.UIUtils;
import com.mcdonalds.app.util.UIUtils.MCDAlertDialogBuilder;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.customer.CustomerModule;
import com.mcdonalds.sdk.modules.delivery.DeliveryModule;
import com.mcdonalds.sdk.modules.models.FavoriteItem;
import com.mcdonalds.sdk.modules.models.FavoriteItem.FavoriteProductType;
import com.mcdonalds.sdk.modules.models.OfferProduct;
import com.mcdonalds.sdk.modules.models.Order;
import com.mcdonalds.sdk.modules.models.OrderOffer;
import com.mcdonalds.sdk.modules.models.OrderOfferProduct;
import com.mcdonalds.sdk.modules.models.OrderProduct;
import com.mcdonalds.sdk.modules.models.OrderResponse;
import com.mcdonalds.sdk.modules.models.PaymentMethod;
import com.mcdonalds.sdk.modules.models.PaymentMethod.PaymentMode;
import com.mcdonalds.sdk.modules.models.Pod;
import com.mcdonalds.sdk.modules.models.Product;
import com.mcdonalds.sdk.modules.models.RecipeComponent;
import com.mcdonalds.sdk.modules.ordering.OrderManager;
import com.mcdonalds.sdk.modules.ordering.OrderingModule;
import com.mcdonalds.sdk.modules.storelocator.Store;
import com.mcdonalds.sdk.services.analytics.AnalyticType;
import com.mcdonalds.sdk.services.analytics.Analytics;
import com.mcdonalds.sdk.services.analytics.AnalyticsArgs.ArgBuilder;
import com.mcdonalds.sdk.services.analytics.BusinessArgs;
import com.mcdonalds.sdk.services.analytics.JiceArgs;
import com.mcdonalds.sdk.services.analytics.conversionmaster.OrderItem;
import com.mcdonalds.sdk.services.analytics.conversionmaster.OrderSuccessAction;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.mcdonalds.sdk.services.data.LocalDataManager;
import com.mcdonalds.sdk.utils.DateUtils;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class OrderSummaryFragment extends URLNavigationFragment {
    private final String CONFIG_CONTACT_US_URL = "interface.aboutMcDonald.contactUs";
    private final String CONFIG_FEEDBACK_URL = "interface.legalCopy.feedbackUrl";
    private CustomerModule mCustomerModule;
    private DeliveryModule mDeliveryModule;
    private FavoriteInputViewHolder mFavoriteInputViewHolder;
    private TextView mFavoriteNameInput;
    private List<FavoriteItem> mFavoritedItems;
    private Order mOrder;
    private OrderingModule mOrderingModule;
    private RatingBox mRatingBox;

    /* renamed from: com.mcdonalds.app.ordering.summary.OrderSummaryFragment$1 */
    class C36781 implements OnClickListener {
        C36781() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            OrderSummaryFragment.access$000(OrderSummaryFragment.this);
            HashMap<String, Object> jiceMap = new HashMap();
            jiceMap.put(JiceArgs.EVENT_NAME, JiceArgs.EVENT_SUMMARY_TRACK);
            Analytics.track(AnalyticType.Event, new ArgBuilder().setJice(jiceMap).build());
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.summary.OrderSummaryFragment$2 */
    class C36792 implements OnClickListener {
        C36792() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            String url = AppUtils.getLocalizedUrl("feedback", "interface.{0}.{1}", "interface.legalCopy.{0}");
            if (TextUtils.isEmpty(url)) {
                url = (String) Configuration.getSharedInstance().getValueForKey("interface.legalCopy.feedbackUrl");
            }
            if (TextUtils.isEmpty(url)) {
                url = (String) Configuration.getSharedInstance().getValueForKey("interface.aboutMcDonald.contactUs");
            }
            if (!TextUtils.isEmpty(url)) {
                Intent i = new Intent("android.intent.action.VIEW");
                i.setData(Uri.parse(url));
                OrderSummaryFragment.this.startActivity(i);
            }
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.summary.OrderSummaryFragment$3 */
    class C36803 implements OnClickListener {
        C36803() {
        }

        public void onClick(View view) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{view});
            OrderSummaryFragment.access$100(OrderSummaryFragment.this, view);
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.summary.OrderSummaryFragment$4 */
    class C36814 implements OnClickListener {
        C36814() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            Intent intentForPackage = OrderSummaryFragment.this.getContext().getPackageManager().getLaunchIntentForPackage("kodo.app.mcdhk");
            if (intentForPackage != null) {
                OrderSummaryFragment.this.startActivity(intentForPackage);
                return;
            }
            Intent viewIntent = new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=kodo.app.mcdhk"));
            if (viewIntent != null) {
                OrderSummaryFragment.this.startActivity(viewIntent);
            } else {
                Toast.makeText(OrderSummaryFragment.this.getContext(), C2658R.string.no_application, 0).show();
            }
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.summary.OrderSummaryFragment$5 */
    class C36825 implements OnClickListener {
        C36825() {
        }

        public void onClick(View view) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{view});
            OrderSummaryFragment.this.onSaveCardClicked();
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.summary.OrderSummaryFragment$8 */
    class C36898 implements OnClickListener {
        C36898() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            AnalyticsUtils.trackOnClickEvent(OrderSummaryFragment.this.getAnalyticsTitle(), "Cancel");
            UIUtils.dismissKeyboard(OrderSummaryFragment.this.getNavigationActivity(), Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.summary.OrderSummaryFragment", "access$200", new Object[]{OrderSummaryFragment.this}));
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.summary.OrderSummaryFragment", "access$500", new Object[]{OrderSummaryFragment.this}).hide();
        }
    }

    static /* synthetic */ void access$000(OrderSummaryFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.summary.OrderSummaryFragment", "access$000", new Object[]{x0});
        x0.navigateToTrackOrder();
    }

    static /* synthetic */ void access$100(OrderSummaryFragment x0, View x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.summary.OrderSummaryFragment", "access$100", new Object[]{x0, x1});
        x0.onAddToFavoritesClicked(x1);
    }

    static /* synthetic */ List access$602(OrderSummaryFragment x0, List x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.summary.OrderSummaryFragment", "access$602", new Object[]{x0, x1});
        x0.mFavoritedItems = x1;
        return x1;
    }

    static /* synthetic */ void access$700(OrderSummaryFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.summary.OrderSummaryFragment", "access$700", new Object[]{x0});
        x0.trackAddToFavorites();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mCustomerModule = (CustomerModule) ModuleManager.getModule(CustomerModule.NAME);
        this.mOrderingModule = (OrderingModule) ModuleManager.getModule("ordering");
        this.mDeliveryModule = (DeliveryModule) ModuleManager.getModule(DeliveryModule.NAME);
        setupModules();
        setHasOptionsMenu(true);
        trackOrderSummary();
    }

    private void trackOrderSummary() {
        Ensighten.evaluateEvent(this, "trackOrderSummary", null);
        if (this.mOrder != null) {
            String orderNumber;
            String pod;
            if (this.mOrder.isDelivery()) {
                if (this.mOrder.getCheckoutResult() != null) {
                    orderNumber = this.mOrder.getCheckoutResult().getDisplayOrderNumber();
                    pod = Pod.DELIVERY;
                } else {
                    return;
                }
            } else if (this.mOrder.getCheckinResult() != null) {
                orderNumber = this.mOrder.getCheckinResult().getDisplayOrderNumber();
                pod = Pod.PICKUP;
            } else {
                return;
            }
            String offerType = "";
            if (!this.mOrder.getOffers().isEmpty()) {
                offerType = String.valueOf(((OrderOffer) this.mOrder.getOffers().iterator().next()).getOffer().getOfferType().ordinal());
            }
            HashMap<String, Object> jiceMap = new HashMap();
            jiceMap.put(JiceArgs.EVENT_NAME, JiceArgs.EVENT_ORDER_SUMMARY);
            jiceMap.put(JiceArgs.LABEL_SUMMARY_ORDER_TYPE, pod);
            jiceMap.put(JiceArgs.LABEL_SUMMARY_OFFER_TYPE, offerType);
            jiceMap.put(JiceArgs.LABEL_SUMMARY_ORDER_ID, orderNumber);
            jiceMap.put(JiceArgs.LABEL_SUMMARY_HAS_OFFER, String.valueOf(!this.mOrder.getOffers().isEmpty()));
            ArrayList<OrderItem> list = new ArrayList();
            for (OrderProduct orderProduct : this.mOrder.getProducts()) {
                Product p = orderProduct.getProduct();
                list.add(new OrderItem("", orderProduct.getProductCode(), p.getLongName(), String.valueOf(this.mOrder.isDelivery() ? p.getPriceDelivery() : p.getPriceEatIn()), orderProduct.getQuantity()));
            }
            Analytics.track(AnalyticType.Event, new ArgBuilder().setJice(jiceMap).setConversionMaster(new OrderSuccessAction(String.valueOf(this.mCustomerModule.getCurrentProfile().getCustomerId()), orderNumber, (int) this.mOrder.getTotalValue(), list)).build());
        }
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        Ensighten.evaluateEvent(this, "onCreateOptionsMenu", new Object[]{menu, inflater});
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(C2358R.C2360menu.order_summary, menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        Ensighten.evaluateEvent(this, "onOptionsItemSelected", new Object[]{item});
        switch (item.getItemId()) {
            case C2358R.C2357id.action_close /*2131821906*/:
                Bundle bundle = new Bundle();
                bundle.putBoolean("REFRESH_LAST_ORDER", true);
                startActivity(MainActivity.class, bundle);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /* Access modifiers changed, original: protected */
    public String getAnalyticsTitle() {
        Ensighten.evaluateEvent(this, "getAnalyticsTitle", null);
        return getString(C2658R.string.analytics_screen_order_summary);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = null;
        try {
            view = inflater.inflate(C2658R.layout.fragment_order_summary, container, false);
            ((RatingBox) view.findViewById(C2358R.C2357id.order_ratingBox)).hideCommentFields(Boolean.valueOf(Configuration.getSharedInstance().getBooleanForKey("interface.hideFeedbackCommentBox")));
            OrderSummaryHeader header = new OrderSummaryHeader(view.findViewById(C2358R.C2357id.order_header));
            this.mFavoriteNameInput = (TextView) view.findViewById(C2358R.C2357id.favorite_name_input);
            this.mFavoriteInputViewHolder = new FavoriteInputViewHolder(getNavigationActivity(), view);
            String displayOrderNumber = "";
            TextView orderNumberTextView = header.getOrderNumber();
            view.findViewById(C2358R.C2357id.price_warning_textview).setVisibility(8);
            PaymentMode paymentMode = this.mOrder.getPaymentMode();
            if (paymentMode == null) {
                PaymentMethod paymentMethod = ((OrderingModule) ModuleManager.getModule("ordering")).getPaymentMethodForId(this.mOrder.getPayment().getPaymentMethodId());
                if (paymentMethod != null) {
                    paymentMode = paymentMethod.getPaymentMode();
                }
            }
            if (this.mOrder.getCheckoutResult() != null && this.mOrder.getCheckoutResult().getDisplayOrderNumber() != null) {
                boolean isLargeOrder = this.mOrder.getCheckoutResult().getOrderView().isIsLargeOrder();
                OrderResponse checkoutResult = this.mOrder.getCheckoutResult();
                if (isPaymentCash(paymentMode)) {
                    header.getDisplayMessageContainerCash().setVisibility(isLargeOrder ? 0 : 8);
                } else {
                    header.getDisplayMessageContainer().setVisibility(isLargeOrder ? 0 : 8);
                }
                displayOrderNumber = checkoutResult.getDisplayOrderNumber();
                orderNumberTextView.setTextSize(2, 24.0f);
                if (isLargeOrder) {
                    header.showLargeOrderAlert(this.mOrder.isDelivery(), getContext(), this.mOrder.getCheckoutResult().getOrderDate());
                }
                setupEDTContainer(header, checkoutResult, isLargeOrder);
                header.getTrackOrderButton().setVisibility(0);
                header.getTrackOrderButton().setOnClickListener(new C36781());
            } else if (!(this.mOrder.getCheckinResult() == null || this.mOrder.getCheckinResult().getDisplayOrderNumber() == null)) {
                if (isPaymentCash(paymentMode)) {
                    header.getDisplayMessageContainerCash().setVisibility(0);
                } else {
                    header.getDisplayMessageContainer().setVisibility(0);
                }
                header.getEDTContainer().setVisibility(8);
                header.getTrackOrderButton().setVisibility(8);
                if (this.mOrder.getCheckinResult().getOrderView().isIsLargeOrder() && Configuration.getSharedInstance().getBooleanForKey("interface.showLargerOrderNotification")) {
                    header.showLargeOrderAlert(this.mOrder.isDelivery(), getContext(), this.mOrder.getCheckinResult().getOrderDate());
                }
                displayOrderNumber = this.mOrder.getCheckinResult().getDisplayOrderNumber();
            }
            header.getOrderNumber().setText(displayOrderNumber);
            FavoritesSaveCard favoritesSaveCard = new FavoritesSaveCard(view.findViewById(C2358R.C2357id.order_save_card));
            favoritesSaveCard.getFeedbackButton().setOnClickListener(new C36792());
            if (Configuration.getSharedInstance().getBooleanForKey("interface.ordering.hideFeedbackLink")) {
                favoritesSaveCard.getFeedbackButton().setVisibility(8);
            }
            if (isFavoriteOrderProduct()) {
                favoritesSaveCard.getAddToFavorites().setText(C2658R.string.order_favorited);
            } else {
                favoritesSaveCard.getAddToFavorites().setText(C2658R.string.order_btn_add_favorites);
            }
            favoritesSaveCard.getAddToFavorites().setOnClickListener(new C36803());
            this.mRatingBox = (RatingBox) view.findViewById(C2358R.C2357id.order_ratingBox);
            this.mRatingBox.getSkipButton().setVisibility(8);
            this.mRatingBox.hideCommentFields(Boolean.valueOf(Configuration.getSharedInstance().getBooleanForKey("interface.hideFeedbackCommentBox")));
            ((Button) view.findViewById(C2358R.C2357id.myvoice)).setOnClickListener(new C36814());
            if (this.mOrder.getPaymentCard() != null) {
                favoritesSaveCard.getSaveCard().setText(String.format(getString(C2658R.string.order_btn_save_card), new Object[]{this.mOrder.getPaymentCard().getNickName()}));
                favoritesSaveCard.getSaveCard().setOnClickListener(new C36825());
            }
            configureOrderDetails(new OrderDetailsHolder(view.findViewById(C2358R.C2357id.order_details)));
            if (Configuration.getSharedInstance().hasKey("interface.orderingDisclaimerInfo")) {
                UIUtils.addDisclaimerTextView((ViewGroup) view.findViewById(C2358R.C2357id.warnings_container), getContext(), "orderSummaryView");
            }
            AnalyticsUtils.trackEvent(new ArgBuilder().setLabel("payment_confirm").setMapping("order_id", this.mOrder.getOrderNumber()).setMapping("fop_type", this.mOrder.getPayment().getOrderPaymentId()).setMapping("curr_cd", Configuration.getSharedInstance().getCurrencyCode()).setMapping("order_amt", Double.valueOf(this.mOrder.getTotalValue())).setMapping("local_timestamp", UIUtils.formatTime(getContext(), new Date())).build());
            return view;
        } catch (NotFoundException e) {
            e.printStackTrace();
            return view;
        }
    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        OrderingManager.getInstance().deleteCurrentOrder();
    }

    private void navigateToTrackOrder() {
        Ensighten.evaluateEvent(this, "navigateToTrackOrder", null);
        Bundle extras = new Bundle();
        extras.putString("arg_order_number", this.mOrder.getCheckoutResult().getDisplayOrderNumber());
        extras.putString("arg_edt", UIUtils.formatDeliveryTime(getContext(), this.mOrder.getCheckoutResult().getOrderDate(), this.mOrder.getCheckoutResult().getEstimatedDeliveryTime()));
        startActivity(TrackOrderActivity.class, "track_order", extras);
    }

    private void setupEDTContainer(OrderSummaryHeader header, OrderResponse checkoutResult, boolean isLargeOrder) {
        Ensighten.evaluateEvent(this, "setupEDTContainer", new Object[]{header, checkoutResult, new Boolean(isLargeOrder)});
        header.getEstimatedDeliveryTimeMessage().setVisibility(8);
        header.getEstimatedDeliveryTime().setVisibility(8);
        if (!Configuration.getSharedInstance().getBooleanForKey("interface.hideEDTonConfirmationScreen")) {
            Date orderDate = checkoutResult.getOrderDate();
            String formattedEDT = DateUtils.formatDateInSummary(getContext(), checkoutResult.getEstimatedDeliveryTime());
            String formattedOrderDate = UIUtils.formatTimeInSummary(getContext(), orderDate);
            if (orderDate != null) {
                header.getOrderReceivedTime().setText(formattedOrderDate);
            }
            if (!formattedEDT.isEmpty() && !isLargeOrder) {
                header.getEstimatedDeliveryTime().setText(formattedEDT);
                header.getEstimatedDeliveryTimeMessage().setVisibility(0);
                header.getEstimatedDeliveryTime().setVisibility(0);
            }
        }
    }

    private void setupModules() {
        boolean doSetup = false;
        Ensighten.evaluateEvent(this, "setupModules", null);
        if (this.mCustomerModule != null) {
            Order order = OrderingManager.getInstance().getCurrentOrder();
            if (order.isDelivery()) {
                if (this.mDeliveryModule != null) {
                    doSetup = true;
                }
            } else if (this.mOrderingModule != null) {
                doSetup = true;
            }
            if (doSetup) {
                this.mOrder = order;
                if (this.mOrder.isDelivery()) {
                    this.mDeliveryModule.setNeedsToUpdateDeliveryTracking(true);
                }
            }
        }
    }

    private void configureOrderDetails(OrderDetailsHolder details) {
        Ensighten.evaluateEvent(this, "configureOrderDetails", new Object[]{details});
        if (this.mOrder != null) {
            String paymentTitle;
            configureEmailReceiptMessage(details);
            String orderNumber = "";
            Date orderDate = null;
            if (this.mOrder.getCheckinResult() != null && this.mOrder.getCheckinResult().getDisplayOrderNumber() != null) {
                orderNumber = this.mOrder.getCheckinResult().getDisplayOrderNumber();
                orderDate = this.mOrder.getCheckinResult().getOrderDate();
            } else if (!(this.mOrder.getCheckoutResult() == null || this.mOrder.getCheckoutResult().getDisplayOrderNumber() == null)) {
                orderNumber = this.mOrder.getCheckoutResult().getDisplayOrderNumber();
                orderDate = this.mOrder.getCheckoutResult().getEstimatedDeliveryTime();
            }
            details.getOrderNumber().setText(String.format(getString(C2658R.string.order_details_number_label), new Object[]{orderNumber}));
            String format = new String();
            if (LocalDataManager.getSharedInstance().getDeviceLanguage().contentEquals("zh")) {
                format = "yyyy/MM/dd aaa hh:mm ";
            } else {
                format = "MM/dd/yyyy hh:mm aaa";
            }
            if (orderDate != null) {
                details.getOrderTimestamp().setText(new SimpleDateFormat(format, Locale.getDefault()).format(orderDate));
            }
            if (this.mOrder.getPaymentMethodDisplayName() != null) {
                paymentTitle = this.mOrder.getPaymentMethodDisplayName();
            } else if (this.mOrder.getPaymentCard() == null) {
                paymentTitle = getPaymentName(this.mOrder.getPaymentMode());
            } else if (TextUtils.isEmpty(this.mOrder.getPaymentCard().getNickName())) {
                paymentTitle = this.mOrder.getPaymentCard().getAlias();
            } else {
                paymentTitle = this.mOrder.getPaymentCard().getNickName();
            }
            OrderReceipt.configureOrderReceiptForDisplay(this.mOrder, getNavigationActivity(), details.getOrderReceiptContainer(), this.mOrder.isDelivery() ? this.mOrder.getDeliveryAddress().getFullAddress() : "", paymentTitle);
            if (ModuleManager.getSharedInstance().isNutritionAvailable()) {
                details.getCaloriesWarningView().setVisibility(0);
            }
        }
    }

    private void configureEmailReceiptMessage(OrderDetailsHolder details) {
        Ensighten.evaluateEvent(this, "configureEmailReceiptMessage", new Object[]{details});
        PaymentMode paymentMode = this.mOrder.getPaymentMode();
        if (paymentMode == null) {
            PaymentMethod paymentMethod = ((OrderingModule) ModuleManager.getModule("ordering")).getPaymentMethodForId(this.mOrder.getPayment().getPaymentMethodId());
            if (paymentMethod != null) {
                paymentMode = paymentMethod.getPaymentMode();
            }
        }
        if (!this.mOrder.isDelivery() && isPaymentCash(paymentMode)) {
            details.getEmailMsg().setVisibility(8);
        } else if (this.mOrder == null || this.mOrder.getProfile() == null || this.mOrder.getProfile().getEmailAddress() == null || this.mOrder.getProfile().getEmailAddress().isEmpty()) {
            details.getEmailMsg().setVisibility(8);
        } else {
            details.getEmailMsg().setVisibility(0);
            details.getEmailMsg().setText(Html.fromHtml(getString(C2658R.string.order_receipt_msg, this.mOrder.getProfile().getEmailAddress())));
        }
    }

    private boolean isPaymentCash(PaymentMode paymentMode) {
        Ensighten.evaluateEvent(this, "isPaymentCash", new Object[]{paymentMode});
        if (paymentMode == null || paymentMode.name() == null || paymentMode.equals(PaymentMode.Cash)) {
            return true;
        }
        return false;
    }

    private String getPaymentName(PaymentMode paymentMode) {
        Ensighten.evaluateEvent(this, "getPaymentName", new Object[]{paymentMode});
        if (isPaymentCash(paymentMode)) {
            return getString(C2658R.string.cash);
        }
        if (paymentMode.equals(PaymentMode.ThirdPart)) {
            return getString(C2658R.string.alipay);
        }
        if (paymentMode.equals(PaymentMode.WeChat)) {
            return getString(C2658R.string.order_summary_payment_display_name_WeChatPay);
        }
        return paymentMode.name();
    }

    private void onAddToFavoritesClicked(View view) {
        Ensighten.evaluateEvent(this, "onAddToFavoritesClicked", new Object[]{view});
        final Button addFavoritesButton = (Button) view;
        this.mFavoriteInputViewHolder.show();
        this.mFavoriteInputViewHolder.getSaveToFavoritesButton().setOnClickListener(new OnClickListener() {

            /* renamed from: com.mcdonalds.app.ordering.summary.OrderSummaryFragment$6$1 */
            class C36831 implements DialogInterface.OnClickListener {
                C36831() {
                }

                public void onClick(DialogInterface dialog, int which) {
                    Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
                    dialog.dismiss();
                }
            }

            /* renamed from: com.mcdonalds.app.ordering.summary.OrderSummaryFragment$6$2 */
            class C36842 implements AsyncListener<List<FavoriteItem>> {
                C36842() {
                }

                public void onResponse(List<FavoriteItem> response, AsyncToken token, AsyncException exception) {
                    Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
                    UIUtils.stopActivityIndicator();
                    if (exception == null) {
                        addFavoritesButton.setText(OrderSummaryFragment.this.getResources().getString(C2658R.string.favorited_order));
                        addFavoritesButton.setBackgroundDrawable(OrderSummaryFragment.this.getResources().getDrawable(C2358R.C2359drawable.button_red));
                        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.summary.OrderSummaryFragment", "access$500", new Object[]{OrderSummaryFragment.this}).hide();
                        addFavoritesButton.setTag(Boolean.valueOf(true));
                        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.summary.OrderSummaryFragment", "access$500", new Object[]{OrderSummaryFragment.this}).setRemoveFromFavoritesButtonVisible();
                        OrderSummaryFragment.access$602(OrderSummaryFragment.this, response);
                        OrderSummaryFragment.access$700(OrderSummaryFragment.this);
                        return;
                    }
                    AsyncException.report(exception);
                    addFavoritesButton.setTag(Boolean.valueOf(false));
                }
            }

            public void onClick(View v) {
                String favoriteName;
                Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
                if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.summary.OrderSummaryFragment", "access$200", new Object[]{OrderSummaryFragment.this}).getText() != null) {
                    favoriteName = Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.summary.OrderSummaryFragment", "access$200", new Object[]{OrderSummaryFragment.this}).getText().toString();
                } else {
                    favoriteName = "";
                }
                if (TextUtils.isEmpty(favoriteName.trim())) {
                    MCDAlertDialogBuilder.withContext(v.getContext()).setMessage(v.getContext().getString(C2658R.string.alert_error_empty_favorite_order_name_msg)).setPositiveButton(OrderSummaryFragment.this.getResources().getString(C2658R.string.f6083ok), new C36831()).create().show();
                    DataLayerManager.getInstance().recordError("Invalid custom order name");
                    return;
                }
                String category = OrderSummaryFragment.this.getAnalyticsTitle();
                AnalyticsUtils.trackOnClickEvent(category, "Save");
                AnalyticsUtils.trackOnClickEvent(category, favoriteName);
                OrderProduct bagProduct = null;
                Order favoriteOrder = Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.summary.OrderSummaryFragment", "access$300", new Object[]{OrderSummaryFragment.this});
                Store currentStore = OrderManager.getInstance().getCurrentStore();
                for (OrderProduct product : Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.summary.OrderSummaryFragment", "access$300", new Object[]{OrderSummaryFragment.this}).getProducts()) {
                    Analytics.track(AnalyticType.Event, new ArgBuilder().setBusiness(BusinessArgs.getProductFromBasket(product)).build());
                    if (currentStore != null && currentStore.isBagChargeEnabled()) {
                        if (currentStore.getBagProductCode() == Integer.parseInt(product.getProductCode()) || currentStore.getNoBagProductCode() == Integer.parseInt(product.getProductCode())) {
                            bagProduct = product;
                        }
                    }
                }
                if (bagProduct != null) {
                    favoriteOrder.removeProduct(bagProduct);
                }
                Collection<OrderOffer> orderOfferList = favoriteOrder.getOffers();
                if (orderOfferList != null && orderOfferList.size() > 0) {
                    for (OrderOffer orderOffer : orderOfferList) {
                        List<OrderOfferProduct> orderOfferProductList = orderOffer.getOrderOfferProducts();
                        if (orderOfferProductList != null && orderOfferProductList.size() > 0) {
                            for (OrderOfferProduct orderOfferProduct : orderOfferProductList) {
                                OfferProduct offerProduct = orderOfferProduct.getOfferProduct();
                                OrderProduct orderProduct = orderOfferProduct.getSelectedProductOption();
                                if (!(offerProduct == null || offerProduct.isPromoItem().booleanValue() || orderProduct == null)) {
                                    favoriteOrder.addProduct(orderProduct);
                                }
                            }
                        }
                    }
                }
                UIUtils.startActivityIndicator(OrderSummaryFragment.this.getNavigationActivity(), String.format("%s %s", new Object[]{OrderSummaryFragment.this.getString(C2658R.string.saving), favoriteName}));
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.summary.OrderSummaryFragment", "access$400", new Object[]{OrderSummaryFragment.this}).addFavoriteProductsReturningItems(Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.summary.OrderSummaryFragment", "access$400", new Object[]{OrderSummaryFragment.this}).getCurrentProfile(), (List) favoriteOrder.getProducts(), favoriteName, false, new C36842());
            }
        });
        this.mFavoriteInputViewHolder.getRemoveFromFavoritesButton().setOnClickListener(new OnClickListener() {

            /* renamed from: com.mcdonalds.app.ordering.summary.OrderSummaryFragment$7$1 */
            class C36871 implements AsyncListener<Boolean> {

                /* renamed from: com.mcdonalds.app.ordering.summary.OrderSummaryFragment$7$1$1 */
                class C36861 implements DialogInterface.OnClickListener {
                    C36861() {
                    }

                    public void onClick(DialogInterface dialog, int which) {
                        Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
                        dialog.dismiss();
                    }
                }

                C36871() {
                }

                public void onResponse(Boolean response, AsyncToken token, AsyncException exception) {
                    Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
                    if (exception == null) {
                        addFavoritesButton.setText(OrderSummaryFragment.this.getResources().getString(C2658R.string.order_btn_add_favorites));
                        addFavoritesButton.setBackgroundDrawable(OrderSummaryFragment.this.getResources().getDrawable(C2358R.C2359drawable.button_red));
                        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.summary.OrderSummaryFragment", "access$500", new Object[]{OrderSummaryFragment.this}).hide();
                        addFavoritesButton.setTag(Boolean.valueOf(false));
                        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.summary.OrderSummaryFragment", "access$500", new Object[]{OrderSummaryFragment.this}).setSaveToFavoritesButtonVisible();
                        UIUtils.stopActivityIndicator();
                        return;
                    }
                    UIUtils.stopActivityIndicator();
                    MCDAlertDialogBuilder.withContext(OrderSummaryFragment.this.getNavigationActivity()).setMessage(exception.getMessage()).setPositiveButton((int) C2658R.string.f6083ok, new C36861()).create().show();
                    addFavoritesButton.setTag(Boolean.valueOf(true));
                }
            }

            public void onClick(View v) {
                Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
                UIUtils.startActivityIndicator(OrderSummaryFragment.this.getNavigationActivity(), OrderSummaryFragment.this.getResources().getString(C2658R.string.removing_from_favorites));
                if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.summary.OrderSummaryFragment", "access$600", new Object[]{OrderSummaryFragment.this}) != null) {
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.summary.OrderSummaryFragment", "access$400", new Object[]{OrderSummaryFragment.this}).deleteFavoriteProducts(Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.summary.OrderSummaryFragment", "access$400", new Object[]{OrderSummaryFragment.this}).getCurrentProfile(), Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.summary.OrderSummaryFragment", "access$600", new Object[]{OrderSummaryFragment.this}), new C36871());
                }
            }
        });
        this.mFavoriteInputViewHolder.getCancelToFavoritesButton().setOnClickListener(new C36898());
        HashMap<String, Object> jiceMap = new HashMap();
        jiceMap.put(JiceArgs.EVENT_NAME, JiceArgs.EVENT_SUMMARY_ADD_FAV);
        Analytics.track(AnalyticType.Event, new ArgBuilder().setJice(jiceMap).build());
    }

    private void trackAddToFavorites() {
        Ensighten.evaluateEvent(this, "trackAddToFavorites", null);
        for (OrderProduct orderProduct : this.mOrder.getProducts()) {
            AnalyticsUtils.trackEvent(new ArgBuilder().setLabel("save_orderm_as_favorite").setMapping("order_id", UIUtils.formatTime(getContext(), new Date())).setMapping("product_id", orderProduct.getProductCode()).setMapping(RecipeComponent.COLUMN_PRODUCT_NAME, orderProduct.getDisplayName()).setMapping("product_quantity", Integer.valueOf(orderProduct.getQuantity())).setMapping("local_timestamp", UIUtils.formatTime(getContext(), new Date())).build());
        }
    }

    private boolean isFavoriteOrderProduct() {
        Ensighten.evaluateEvent(this, "isFavoriteOrderProduct", null);
        boolean retValue = false;
        for (OrderProduct orderProduct : this.mOrder.getProducts()) {
            if (this.mCustomerModule.getCurrentProfile().isFavoriteOrderProduct(orderProduct, FavoriteProductType.FAVORITE_PRODUCT_TYPE_ORDER)) {
                retValue = true;
            }
        }
        return retValue;
    }

    /* Access modifiers changed, original: 0000 */
    public void onSaveCardClicked() {
        Ensighten.evaluateEvent(this, "onSaveCardClicked", null);
    }
}
