package com.mcdonalds.app.ordering.preparepayment;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.analytics.datalayer.view.DataLayerClickListener;
import com.mcdonalds.app.ordering.OrderingManager;
import com.mcdonalds.app.util.ConfigurationUtils;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.modules.customer.CustomerProfile;
import com.mcdonalds.sdk.modules.models.PaymentCard;
import com.mcdonalds.sdk.modules.models.PaymentMethod;
import com.mcdonalds.sdk.modules.models.PaymentMethod.PaymentMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import p046se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;

public class PaymentSelectionListAdapterHeaders extends BaseAdapter implements StickyListHeadersAdapter {
    private LayoutInflater inflater;
    private final Context mContext;
    private boolean mIncludesCash = false;
    private List<PaymentInfo> mPaymentList = new ArrayList();
    private Map<PaymentMode, Integer> mPaymentMethodIds;
    private String[] sectionNames;

    public enum CardRow {
        Card,
        Add,
        One_Time
    }

    static class HeaderViewHolder {
        TextView title;
        View topBorder;

        HeaderViewHolder() {
        }
    }

    private class PaymentInfo {
        CardRow mCreditCardRowType;
        PaymentCard mPaymentCard;
        PaymentMode mPaymentMode;

        public PaymentInfo(PaymentMode paymentMode, CardRow creditCardRowType, PaymentCard paymentCard) {
            this.mPaymentMode = paymentMode;
            this.mCreditCardRowType = creditCardRowType;
            this.mPaymentCard = paymentCard;
        }

        public PaymentMode getPaymentMode() {
            Ensighten.evaluateEvent(this, "getPaymentMode", null);
            return this.mPaymentMode;
        }

        public CardRow getCreditCardRowType() {
            Ensighten.evaluateEvent(this, "getCreditCardRowType", null);
            return this.mCreditCardRowType;
        }

        public PaymentCard getPaymentCard() {
            Ensighten.evaluateEvent(this, "getPaymentCard", null);
            return this.mPaymentCard;
        }
    }

    private enum RowType {
        Checkable,
        New
    }

    static class SelectionHolder {
        TextView cardNumber;
        TextView cardType;
        ImageView iv_ali_img;
        TextView label;

        SelectionHolder() {
        }
    }

    public int getPaymentMethodID(int position) {
        Ensighten.evaluateEvent(this, "getPaymentMethodID", new Object[]{new Integer(position)});
        if (position < this.mPaymentList.size()) {
            Integer paymentMethodId = (Integer) this.mPaymentMethodIds.get(((PaymentInfo) this.mPaymentList.get(position)).getPaymentMode());
            if (paymentMethodId != null) {
                return paymentMethodId.intValue();
            }
        }
        return -1;
    }

    public PaymentSelectionListAdapterHeaders(Context context, CustomerProfile profile, LinkedHashSet<PaymentMethod> paymentTypes) {
        this.mContext = context;
        this.inflater = LayoutInflater.from(this.mContext);
        this.mPaymentMethodIds = new HashMap();
        this.sectionNames = new String[]{this.mContext.getResources().getString(C2658R.string.credit_card_header), this.mContext.getResources().getString(C2658R.string.cash), this.mContext.getString(C2658R.string.payment_processor)};
        boolean includesCreditCard = false;
        boolean includes3rdPart = false;
        Iterator it = paymentTypes.iterator();
        while (it.hasNext()) {
            PaymentMethod payment = (PaymentMethod) it.next();
            PaymentMode paymentMode = payment.getPaymentMode();
            this.mPaymentMethodIds.put(paymentMode, payment.getID());
            switch (paymentMode) {
                case Cash:
                    this.mIncludesCash = true;
                    break;
                case Credit:
                    includesCreditCard = true;
                    break;
                case ThirdPart:
                    includes3rdPart = true;
                    break;
                default:
                    break;
            }
        }
        if (this.mIncludesCash) {
            this.mPaymentList.add(new PaymentInfo(PaymentMode.Cash, null, null));
        }
        if (includes3rdPart) {
            this.mPaymentList.add(new PaymentInfo(PaymentMode.ThirdPart, null, null));
        }
        if (includesCreditCard) {
            if (profile.getCardItems() != null) {
                for (PaymentCard paymentCard : profile.getCardItems()) {
                    this.mPaymentList.add(new PaymentInfo(PaymentMode.Credit, CardRow.Card, paymentCard));
                }
            }
            if (ConfigurationUtils.isOneClickPaymentFlow()) {
                PaymentCard stub = new PaymentCard();
                stub.setPaymentMethodId((Integer) this.mPaymentMethodIds.get(PaymentMode.Credit));
                stub.setAlias(this.mContext.getString(C2658R.string.new_card));
                stub.setNewCardStub(true);
                this.mPaymentList.add(new PaymentInfo(PaymentMode.Credit, CardRow.Card, stub));
                return;
            }
            this.mPaymentList.add(new PaymentInfo(PaymentMode.Credit, CardRow.Add, null));
            this.mPaymentList.add(new PaymentInfo(PaymentMode.Credit, CardRow.One_Time, null));
        }
    }

    public PaymentCard getPaymentCard(int position) {
        Ensighten.evaluateEvent(this, "getPaymentCard", new Object[]{new Integer(position)});
        if (((PaymentInfo) this.mPaymentList.get(position)).getCreditCardRowType() == CardRow.Card) {
            return ((PaymentInfo) this.mPaymentList.get(position)).getPaymentCard();
        }
        throw new RuntimeException(position + "not a valid CreditCard index");
    }

    public PaymentMode getPaymentType(int position) {
        Ensighten.evaluateEvent(this, "getPaymentType", new Object[]{new Integer(position)});
        return ((PaymentInfo) this.mPaymentList.get(position)).getPaymentMode();
    }

    public CardRow getCardRowType(int position) {
        Ensighten.evaluateEvent(this, "getCardRowType", new Object[]{new Integer(position)});
        return ((PaymentInfo) this.mPaymentList.get(position)).getCreditCardRowType();
    }

    public int getCount() {
        Ensighten.evaluateEvent(this, "getCount", null);
        return this.mPaymentList.size();
    }

    public Object getItem(int position) {
        Ensighten.evaluateEvent(this, "getItem", new Object[]{new Integer(position)});
        return this.mPaymentList.get(position);
    }

    public long getItemId(int position) {
        Ensighten.evaluateEvent(this, "getItemId", new Object[]{new Integer(position)});
        return (long) position;
    }

    public int getViewTypeCount() {
        Ensighten.evaluateEvent(this, "getViewTypeCount", null);
        return RowType.values().length;
    }

    public int getItemViewType(int position) {
        Ensighten.evaluateEvent(this, "getItemViewType", new Object[]{new Integer(position)});
        if (((PaymentInfo) this.mPaymentList.get(position)).getPaymentMode() == PaymentMode.Cash) {
            return 0;
        }
        if (((PaymentInfo) this.mPaymentList.get(position)).getPaymentMode() == PaymentMode.ThirdPart) {
            return 0;
        }
        if (((PaymentInfo) this.mPaymentList.get(position)).getPaymentMode() != PaymentMode.Credit) {
            return -1;
        }
        if (((PaymentInfo) this.mPaymentList.get(position)).getCreditCardRowType() == CardRow.Card) {
            return 0;
        }
        return 1;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = createView(position, parent);
        }
        SelectionHolder selectionHolder = (SelectionHolder) convertView.getTag();
        DataLayerClickListener.setDataLayerTag(convertView, SelectionHolder.class, position);
        PaymentInfo paymentInfo = (PaymentInfo) this.mPaymentList.get(position);
        if (selectionHolder.iv_ali_img != null) {
            selectionHolder.iv_ali_img.setVisibility(8);
        }
        switch (paymentInfo.getPaymentMode()) {
            case Cash:
                CharSequence string;
                selectionHolder.cardType.setVisibility(8);
                selectionHolder.cardNumber.setTypeface(null, 1);
                TextView textView = selectionHolder.cardNumber;
                if (OrderingManager.getInstance().getCurrentOrder().isDelivery()) {
                    string = this.mContext.getString(C2658R.string.cash);
                } else {
                    string = this.mContext.getString(C2658R.string.checkable_cash_option);
                }
                textView.setText(string);
                break;
            case Credit:
                switch (paymentInfo.getCreditCardRowType()) {
                    case Card:
                        selectionHolder.cardType.setVisibility(0);
                        if (paymentInfo.getPaymentCard().isNewCardStub()) {
                            selectionHolder.cardType.setVisibility(8);
                        } else {
                            selectionHolder.cardType.setText(C2658R.string.saved_card);
                        }
                        selectionHolder.cardNumber.setTypeface(null, 0);
                        if (!TextUtils.isEmpty(paymentInfo.getPaymentCard().getNickName())) {
                            selectionHolder.cardNumber.setText(paymentInfo.getPaymentCard().getNickName());
                            break;
                        }
                        selectionHolder.cardNumber.setText(paymentInfo.getPaymentCard().getAlias());
                        break;
                    case Add:
                        selectionHolder.label.setText(C2658R.string.add_new_card_button);
                        break;
                    case One_Time:
                        selectionHolder.label.setText(C2658R.string.label_one_time_payment);
                        break;
                }
                break;
            case ThirdPart:
                selectionHolder.cardType.setVisibility(8);
                selectionHolder.cardNumber.setTypeface(null, 1);
                selectionHolder.cardNumber.setText(this.mContext.getString(C2658R.string.alipay));
                if (selectionHolder.iv_ali_img != null) {
                    selectionHolder.iv_ali_img.setVisibility(0);
                    break;
                }
                break;
        }
        Ensighten.getViewReturnValue(convertView, position);
        Ensighten.processView((Object) this, "getView");
        Ensighten.getViewReturnValue(null, -1);
        return convertView;
    }

    private View createView(int position, ViewGroup parent) {
        Ensighten.evaluateEvent(this, "createView", new Object[]{new Integer(position), parent});
        SelectionHolder selectionHolder = new SelectionHolder();
        View ret;
        switch (((PaymentInfo) this.mPaymentList.get(position)).getPaymentMode()) {
            case Cash:
                ret = this.inflater.inflate(C2658R.layout.payment_selection, parent, false);
                selectionHolder.cardType = (TextView) ret.findViewById(C2358R.C2357id.card_type);
                selectionHolder.cardNumber = (TextView) ret.findViewById(C2358R.C2357id.card_number);
                ret.setTag(selectionHolder);
                return ret;
            case Credit:
                switch (((PaymentInfo) this.mPaymentList.get(position)).getCreditCardRowType()) {
                    case Card:
                        ret = this.inflater.inflate(C2658R.layout.payment_selection, parent, false);
                        selectionHolder.cardType = (TextView) ret.findViewById(C2358R.C2357id.card_type);
                        selectionHolder.cardNumber = (TextView) ret.findViewById(C2358R.C2357id.card_number);
                        ret.setTag(selectionHolder);
                        return ret;
                    case Add:
                        ret = this.inflater.inflate(C2658R.layout.payment_selection_add, parent, false);
                        selectionHolder.label = (TextView) ret.findViewById(C2358R.C2357id.textView);
                        ret.setTag(selectionHolder);
                        return ret;
                    case One_Time:
                        ret = this.inflater.inflate(C2658R.layout.payment_selection_add, parent, false);
                        selectionHolder.label = (TextView) ret.findViewById(C2358R.C2357id.textView);
                        ret.setTag(selectionHolder);
                        return ret;
                    default:
                        return null;
                }
            case ThirdPart:
                ret = this.inflater.inflate(C2658R.layout.payment_selection, parent, false);
                selectionHolder.cardType = (TextView) ret.findViewById(C2358R.C2357id.card_type);
                selectionHolder.cardNumber = (TextView) ret.findViewById(C2358R.C2357id.card_number);
                selectionHolder.iv_ali_img = (ImageView) ret.findViewById(C2358R.C2357id.iv_ali_img);
                ret.setTag(selectionHolder);
                return ret;
            default:
                return null;
        }
    }

    public View getHeaderView(int position, View convertView, ViewGroup parent) {
        HeaderViewHolder holder;
        Ensighten.evaluateEvent(this, "getHeaderView", new Object[]{new Integer(position), convertView, parent});
        if (convertView == null) {
            convertView = this.inflater.inflate(C2658R.layout.section_header_w_bar, parent, false);
            holder = new HeaderViewHolder();
            holder.title = (TextView) convertView.findViewById(C2358R.C2357id.section_name);
            holder.topBorder = convertView.findViewById(C2358R.C2357id.top_border);
            convertView.setTag(holder);
        } else {
            holder = (HeaderViewHolder) convertView.getTag();
        }
        holder.title.setText(this.sectionNames[((PaymentInfo) this.mPaymentList.get(position)).getPaymentMode().ordinal()]);
        if (((PaymentInfo) this.mPaymentList.get(position)).getPaymentMode() == PaymentMode.Cash || ((PaymentInfo) this.mPaymentList.get(position)).getPaymentMode() == PaymentMode.ThirdPart) {
            holder.title.setVisibility(8);
        } else {
            holder.title.setVisibility(0);
        }
        if (position == this.mPaymentList.size() - 1 && this.mIncludesCash) {
            holder.topBorder.setVisibility(0);
        } else {
            holder.topBorder.setVisibility(8);
        }
        return convertView;
    }

    public long getHeaderId(int position) {
        Ensighten.evaluateEvent(this, "getHeaderId", new Object[]{new Integer(position)});
        return (long) ((PaymentInfo) this.mPaymentList.get(position)).getPaymentMode().ordinal();
    }
}
