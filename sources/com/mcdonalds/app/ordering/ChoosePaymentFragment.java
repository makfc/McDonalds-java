package com.mcdonalds.app.ordering;

import android.os.Bundle;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.p043ui.URLNavigationFragment;
import com.mcdonalds.app.util.AnalyticsUtils;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.connectors.middleware.model.MWLocation;
import com.mcdonalds.sdk.modules.models.Order;
import com.mcdonalds.sdk.modules.models.OrderPayment;
import com.mcdonalds.sdk.modules.models.PaymentMethod;
import com.mcdonalds.sdk.modules.models.PaymentMethod.PaymentMode;
import com.mcdonalds.sdk.modules.ordering.OrderManager;
import com.mcdonalds.sdk.modules.storelocator.Store;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.mcdonalds.sdk.services.data.repository.PaymentMethodRepository;
import java.util.ArrayList;
import java.util.List;

public class ChoosePaymentFragment extends URLNavigationFragment {
    public static final String LOG_TAG = ChoosePaymentFragment.class.getSimpleName();
    private ViewGroup mButtonsContainer;
    private final OnClickListener mOnClickPaymentMethod = new C33651();
    private Order mOrder;

    /* renamed from: com.mcdonalds.app.ordering.ChoosePaymentFragment$1 */
    class C33651 implements OnClickListener {
        C33651() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            PaymentMethod method = (PaymentMethod) v.getTag();
            OrderPayment payment = new OrderPayment();
            payment.setPaymentMethodId(method.getID().intValue());
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.ChoosePaymentFragment", "access$000", new Object[]{ChoosePaymentFragment.this}).setPayment(payment);
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.ChoosePaymentFragment", "access$000", new Object[]{ChoosePaymentFragment.this}).setPaymentMode(method.getPaymentMode());
            SparseArray custom = new SparseArray();
            custom.put(55, method.getID().toString());
            AnalyticsUtils.trackOnClickEvent(ChoosePaymentFragment.this.getAnalyticsTitle(), method.getName(), custom);
            if (method.getPaymentMode().equals(PaymentMode.ThirdPart)) {
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.ChoosePaymentFragment", "access$000", new Object[]{ChoosePaymentFragment.this}).setPaymentMethodDisplayName(Configuration.getSharedInstance().getStringForKey("supportedPaymentMethods.thirdParty.displayName"));
            } else if (method.getPaymentMode().equals(PaymentMode.WeChat)) {
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.ChoosePaymentFragment", "access$000", new Object[]{ChoosePaymentFragment.this}).setPaymentMethodDisplayName(Configuration.getSharedInstance().getStringForKey("supportedPaymentMethods.wechatPayment.displayName"));
            } else {
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.ChoosePaymentFragment", "access$000", new Object[]{ChoosePaymentFragment.this}).setPaymentMethodDisplayName("");
            }
            ChoosePaymentFragment.this.getActivity().setResult(-1);
            ChoosePaymentFragment.this.getActivity().finish();
        }
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mOrder = OrderingManager.getInstance().getCurrentOrder();
    }

    /* Access modifiers changed, original: protected */
    public String getAnalyticsTitle() {
        Ensighten.evaluateEvent(this, "getAnalyticsTitle", null);
        return getString(C2658R.string.analytics_screen_checkout);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C2658R.layout.fragment_choose_payment_type, container, false);
        this.mButtonsContainer = (ViewGroup) view.findViewById(C2358R.C2357id.container_payment_methods);
        return view;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        displayMethods();
    }

    private void displayMethods() {
        Ensighten.evaluateEvent(this, "displayMethods", null);
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        List<PaymentMethod> methods = PaymentMethodRepository.getAll(getContext());
        if (methods != null) {
            List<Integer> allowedPaymentIds = getPaymentMethodIds();
            for (PaymentMethod method : methods) {
                if (allowedPaymentIds.contains(method.getID())) {
                    Button button = (Button) inflater.inflate(C2658R.layout.view_payment_method_button, this.mButtonsContainer, false);
                    button.setText(getPaymentName(method.getPaymentMode()));
                    button.setTag(method);
                    button.setOnClickListener(this.mOnClickPaymentMethod);
                    this.mButtonsContainer.addView(button);
                }
            }
        }
    }

    private String getPaymentName(PaymentMode paymentMode) {
        Ensighten.evaluateEvent(this, "getPaymentName", new Object[]{paymentMode});
        if (paymentMode == null) {
            return "";
        }
        if (paymentMode.equals(PaymentMode.ThirdPart)) {
            return getString(C2658R.string.alipay);
        }
        if (paymentMode.equals(PaymentMode.Cash)) {
            return getString(C2658R.string.cash);
        }
        if (paymentMode.equals(PaymentMode.WeChat)) {
            return getString(C2658R.string.we_chat_btn);
        }
        return paymentMode.name();
    }

    private List<Integer> getPaymentMethodIds() {
        Ensighten.evaluateEvent(this, "getPaymentMethodIds", null);
        int locIdToGet = this.mOrder.isDelivery() ? 4 : 2;
        ArrayList<Integer> result = new ArrayList();
        Store store = OrderManager.getInstance().getCurrentStore();
        if (store != null) {
            List<MWLocation> locations = store.getLocations();
            if (locations != null) {
                for (MWLocation loc : locations) {
                    if (locIdToGet == loc.getLocationID()) {
                        result.addAll(loc.getPaymentMethods());
                    }
                }
            }
        }
        return result;
    }
}
