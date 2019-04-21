package com.mcdonalds.app.home.dashboard.viewholder;

import android.support.p001v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.home.dashboard.DashboardAdapter.DashboardListener;

public class OfferLocationSelectorViewHolder extends ViewHolder {
    private OfferLocationSelectorListener mOfferLocationSelectorListener;
    private RadioGroup mRadioGroup;

    public interface OfferLocationSelectorListener {
        void onSelectionChange(int i);
    }

    /* renamed from: com.mcdonalds.app.home.dashboard.viewholder.OfferLocationSelectorViewHolder$3 */
    static class C24023 implements OnCheckedChangeListener {
        final /* synthetic */ OfferLocationSelectorViewHolder val$viewHolder;

        C24023(OfferLocationSelectorViewHolder offerLocationSelectorViewHolder) {
            this.val$viewHolder = offerLocationSelectorViewHolder;
        }

        public void onCheckedChanged(RadioGroup group, int checkedId) {
            Ensighten.evaluateEvent(this, "onCheckedChanged", new Object[]{group, new Integer(checkedId)});
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.dashboard.viewholder.OfferLocationSelectorViewHolder", "access$100", new Object[]{this.val$viewHolder}).onSelectionChange(checkedId);
        }
    }

    private OfferLocationSelectorViewHolder(View itemView) {
        super(itemView);
        this.mRadioGroup = (RadioGroup) itemView.findViewById(C2358R.C2357id.displayTypeSelector);
    }

    public static ViewHolder create(View selectorView, final DashboardListener dashboardListener) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.dashboard.viewholder.OfferLocationSelectorViewHolder", "create", new Object[]{selectorView, dashboardListener});
        final OfferLocationSelectorViewHolder viewHolder = new OfferLocationSelectorViewHolder(selectorView);
        final OfferLocationSelectorListener offerLocationSelectorListener = new OfferLocationSelectorListener() {
            public void onSelectionChange(int checkedId) {
                Ensighten.evaluateEvent(this, "onSelectionChange", new Object[]{new Integer(checkedId)});
                dashboardListener.onOffersTypeSelectorChanged(Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.dashboard.viewholder.OfferLocationSelectorViewHolder", "access$000", new Object[]{viewHolder}));
            }
        };
        viewHolder.setOfferLocationSelectorListener(offerLocationSelectorListener);
        viewHolder.mRadioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Ensighten.evaluateEvent(this, "onCheckedChanged", new Object[]{group, new Integer(checkedId)});
                offerLocationSelectorListener.onSelectionChange(checkedId);
            }
        });
        return viewHolder;
    }

    public static void forceTabSelection(OfferLocationSelectorViewHolder viewHolder, boolean isNearBy, boolean isDelivery) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.dashboard.viewholder.OfferLocationSelectorViewHolder", "forceTabSelection", new Object[]{viewHolder, new Boolean(isNearBy), new Boolean(isDelivery)});
        viewHolder.mRadioGroup.setOnCheckedChangeListener(null);
        if (isNearBy) {
            viewHolder.mRadioGroup.check(C2358R.C2357id.offers_type_near_you);
        } else if (isDelivery) {
            viewHolder.mRadioGroup.check(C2358R.C2357id.offers_type_delivery);
        } else {
            viewHolder.mRadioGroup.check(C2358R.C2357id.offers_type_pickup);
        }
        viewHolder.mRadioGroup.setOnCheckedChangeListener(new C24023(viewHolder));
    }

    public void setOfferLocationSelectorListener(OfferLocationSelectorListener offerLocationSelectorListener) {
        Ensighten.evaluateEvent(this, "setOfferLocationSelectorListener", new Object[]{offerLocationSelectorListener});
        this.mOfferLocationSelectorListener = offerLocationSelectorListener;
    }
}
