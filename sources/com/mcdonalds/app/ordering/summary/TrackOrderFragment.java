package com.mcdonalds.app.ordering.summary;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.p000v4.widget.SwipeRefreshLayout;
import android.support.p000v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.p043ui.DeliveryStatusView;
import com.mcdonalds.app.p043ui.URLNavigationFragment;
import com.mcdonalds.app.util.UIUtils;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.delivery.DeliveryModule;
import com.mcdonalds.sdk.modules.models.DeliveryStatusResponse;
import java.util.Arrays;
import java.util.List;

public class TrackOrderFragment extends URLNavigationFragment {
    private DeliveryModule mDeliveryModule;
    private AsyncListener<DeliveryStatusResponse> mDeliveryStatusListener = new C36932();
    private String mEDT;
    private String mOrderNumber;
    private OnRefreshListener mRefreshListener = new C36921();
    private SwipeRefreshLayout mRefresher;
    private ScrollView mScrollView;
    private List<DeliveryStatusView> mSteps;

    /* renamed from: com.mcdonalds.app.ordering.summary.TrackOrderFragment$1 */
    class C36921 implements OnRefreshListener {
        C36921() {
        }

        public void onRefresh() {
            Ensighten.evaluateEvent(this, "onRefresh", null);
            TrackOrderFragment.access$000(TrackOrderFragment.this);
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.summary.TrackOrderFragment$2 */
    class C36932 implements AsyncListener<DeliveryStatusResponse> {
        C36932() {
        }

        public void onResponse(DeliveryStatusResponse response, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            if (response == null) {
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.summary.TrackOrderFragment", "access$100", new Object[]{TrackOrderFragment.this}).setRefreshing(false);
                return;
            }
            String estimatedDeliveryTime = UIUtils.formatTime(TrackOrderFragment.this.getContext(), response.getTimestamp());
            if (response.getStatus() == 5) {
                ((DeliveryStatusView) Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.summary.TrackOrderFragment", "access$200", new Object[]{TrackOrderFragment.this}).get(4)).setVisibility(0);
                ((DeliveryStatusView) Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.summary.TrackOrderFragment", "access$200", new Object[]{TrackOrderFragment.this}).get(0)).setVisibility(8);
                ((DeliveryStatusView) Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.summary.TrackOrderFragment", "access$200", new Object[]{TrackOrderFragment.this}).get(4)).setCancelled(true);
            } else {
                for (int i = 0; i < response.getStatus(); i++) {
                    ((DeliveryStatusView) Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.summary.TrackOrderFragment", "access$200", new Object[]{TrackOrderFragment.this}).get(i)).setCompleted(true);
                    ((DeliveryStatusView) Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.summary.TrackOrderFragment", "access$200", new Object[]{TrackOrderFragment.this}).get(i)).setCancelled(false);
                }
                int deliveredIndex = Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.summary.TrackOrderFragment", "access$200", new Object[]{TrackOrderFragment.this}).size() - 2;
                if (!((DeliveryStatusView) Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.summary.TrackOrderFragment", "access$200", new Object[]{TrackOrderFragment.this}).get(deliveredIndex)).isCompleted()) {
                    ((DeliveryStatusView) Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.summary.TrackOrderFragment", "access$200", new Object[]{TrackOrderFragment.this}).get(deliveredIndex)).setCompletionTime(estimatedDeliveryTime);
                }
            }
            DeliveryStatusView lastStatus = (DeliveryStatusView) Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.summary.TrackOrderFragment", "access$200", new Object[]{TrackOrderFragment.this}).get(response.getStatus() - 1);
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.summary.TrackOrderFragment", "access$100", new Object[]{TrackOrderFragment.this}).setRefreshing(false);
            TrackOrderFragment.access$300(TrackOrderFragment.this, lastStatus);
        }
    }

    static /* synthetic */ void access$000(TrackOrderFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.summary.TrackOrderFragment", "access$000", new Object[]{x0});
        x0.fetchDeliveryStatus();
    }

    static /* synthetic */ void access$300(TrackOrderFragment x0, View x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.summary.TrackOrderFragment", "access$300", new Object[]{x0, x1});
        x0.scrollToView(x1);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mDeliveryModule = (DeliveryModule) ModuleManager.getModule(DeliveryModule.NAME);
        if (getArguments() != null) {
            this.mOrderNumber = getArguments().getString("arg_order_number");
            this.mEDT = getArguments().getString("arg_edt");
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(C2658R.layout.fragment_track_order, container, false);
        TextView orderNumberText = (TextView) v.findViewById(C2358R.C2357id.order_number_label);
        orderNumberText.setText(this.mOrderNumber);
        orderNumberText.setTextSize(2, 24.0f);
        ((TextView) v.findViewById(C2358R.C2357id.order_number_details_view_warning)).setVisibility(0);
        this.mRefresher = (SwipeRefreshLayout) v.findViewById(C2358R.C2357id.refresher);
        this.mRefresher.setOnRefreshListener(this.mRefreshListener);
        UIUtils.setDefaultRefreshColors(this.mRefresher);
        this.mScrollView = (ScrollView) v.findViewById(C2358R.C2357id.status_scroll);
        ((DeliveryStatusView) v.findViewById(C2358R.C2357id.order_delivered)).setCompletionTime(this.mEDT);
        this.mSteps = Arrays.asList(new DeliveryStatusView[]{(DeliveryStatusView) v.findViewById(C2358R.C2357id.order_received), (DeliveryStatusView) v.findViewById(C2358R.C2357id.order_in_progress), (DeliveryStatusView) v.findViewById(C2358R.C2357id.delivery_in_progress), orderDelivered, (DeliveryStatusView) v.findViewById(C2358R.C2357id.order_cancelled)});
        return v;
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Ensighten.evaluateEvent(this, "onViewCreated", new Object[]{view, savedInstanceState});
        super.onViewCreated(view, savedInstanceState);
        this.mRefresher.setRefreshing(true);
        fetchDeliveryStatus();
    }

    private void fetchDeliveryStatus() {
        Ensighten.evaluateEvent(this, "fetchDeliveryStatus", null);
        this.mDeliveryModule.setNeedsToUpdateDeliveryTracking(true);
        this.mDeliveryModule.getDeliveryStatus(this.mOrderNumber, this.mDeliveryStatusListener);
    }

    private void scrollToView(final View view) {
        Ensighten.evaluateEvent(this, "scrollToView", new Object[]{view});
        this.mScrollView.post(new Runnable() {
            public void run() {
                Ensighten.evaluateEvent(this, "run", null);
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.summary.TrackOrderFragment", "access$400", new Object[]{TrackOrderFragment.this}).smoothScrollTo(0, view.getTop());
            }
        });
    }
}
