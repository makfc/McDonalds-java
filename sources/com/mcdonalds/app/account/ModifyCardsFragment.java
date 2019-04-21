package com.mcdonalds.app.account;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.analytics.datalayer.view.DataLayerClickListener;
import com.mcdonalds.app.ordering.MPGSPaymentActivity;
import com.mcdonalds.app.ordering.PaymentProviderActivity;
import com.mcdonalds.app.p043ui.URLNavigationFragment;
import com.mcdonalds.app.util.ConfigurationUtils;
import com.mcdonalds.app.util.UIUtils;
import com.mcdonalds.app.util.UIUtils.MCDAlertDialogBuilder;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.customer.CustomerModule;
import com.mcdonalds.sdk.modules.customer.CustomerProfile;
import com.mcdonalds.sdk.modules.models.PaymentCard;
import com.mcdonalds.sdk.modules.models.PaymentMethod;
import com.mcdonalds.sdk.modules.models.PaymentMethod.PaymentMode;
import com.mcdonalds.sdk.modules.ordering.OrderManager;
import com.mcdonalds.sdk.modules.ordering.OrderingModule;
import com.mcdonalds.sdk.services.configuration.AppParameters;
import com.mcdonalds.sdk.services.configuration.Configuration;
import java.util.ArrayList;
import java.util.List;

public class ModifyCardsFragment extends URLNavigationFragment {
    public static final String NAME = ModifyCardsFragment.class.getSimpleName();
    private ModifyCardsBaseAdapter adapter;
    private boolean changesMade = false;
    private boolean in_edit_mode = false;
    private OnClickListener mAddCardClickListener = new C29942();
    private ListView mCardList;
    private CustomerModule mCustomerModule;
    private View mFooterView;
    private TextView mMaxCardAlertTextView;
    private TextView mMenuEditButton;
    private List<PaymentCard> mPaymentCards = new ArrayList();
    private TextView mTitleTextView;

    /* renamed from: com.mcdonalds.app.account.ModifyCardsFragment$1 */
    class C29911 implements OnItemClickListener {
        C29911() {
        }

        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Ensighten.evaluateEvent(this, "onItemClick", new Object[]{parent, view, new Integer(position), new Long(id)});
        }
    }

    /* renamed from: com.mcdonalds.app.account.ModifyCardsFragment$2 */
    class C29942 implements OnClickListener {

        /* renamed from: com.mcdonalds.app.account.ModifyCardsFragment$2$1 */
        class C29931 implements AsyncListener<List<PaymentMethod>> {

            /* renamed from: com.mcdonalds.app.account.ModifyCardsFragment$2$1$1 */
            class C29921 implements DialogInterface.OnClickListener {
                C29921() {
                }

                public void onClick(DialogInterface dialog, int which) {
                    Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
                    dialog.dismiss();
                }
            }

            C29931() {
            }

            public void onResponse(List<PaymentMethod> paymentMethods, AsyncToken token, AsyncException exception) {
                Ensighten.evaluateEvent(this, "onResponse", new Object[]{paymentMethods, token, exception});
                if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.ModifyCardsFragment", "access$000", new Object[]{ModifyCardsFragment.this}).shouldSaveCard()) {
                    ModifyCardsFragment.access$100(ModifyCardsFragment.this, paymentMethods);
                } else {
                    MCDAlertDialogBuilder.withContext(ModifyCardsFragment.this.getContext()).setMessage((int) C2658R.string.max_payment_card_error_account).setPositiveButton((int) C2658R.string.f6083ok, new C29921()).create().show();
                }
            }
        }

        C29942() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            ((OrderingModule) ModuleManager.getModule("ordering")).getPaymentMethods(new C29931());
        }
    }

    /* renamed from: com.mcdonalds.app.account.ModifyCardsFragment$3 */
    class C29953 implements OnClickListener {
        C29953() {
        }

        public void onClick(View v) {
            boolean z = true;
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            if (!Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.ModifyCardsFragment", "access$200", new Object[]{ModifyCardsFragment.this}).isActivated()) {
                return;
            }
            if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.ModifyCardsFragment", "access$200", new Object[]{ModifyCardsFragment.this}).getText().equals(ModifyCardsFragment.this.getActivity().getString(C2658R.string.edit))) {
                ModifyCardsFragment.access$302(ModifyCardsFragment.this, true);
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.ModifyCardsFragment", "access$200", new Object[]{ModifyCardsFragment.this}).setText(ModifyCardsFragment.this.getActivity().getString(C2658R.string.done));
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.ModifyCardsFragment", "access$400", new Object[]{ModifyCardsFragment.this}).notifyDataSetChanged();
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.ModifyCardsFragment", "access$200", new Object[]{ModifyCardsFragment.this}).setActivated(true);
                return;
            }
            ModifyCardsFragment.access$302(ModifyCardsFragment.this, false);
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.ModifyCardsFragment", "access$200", new Object[]{ModifyCardsFragment.this}).setText(ModifyCardsFragment.this.getActivity().getString(C2658R.string.edit));
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.ModifyCardsFragment", "access$400", new Object[]{ModifyCardsFragment.this}).notifyDataSetChanged();
            TextView access$200 = Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.ModifyCardsFragment", "access$200", new Object[]{ModifyCardsFragment.this});
            if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.ModifyCardsFragment", "access$500", new Object[]{ModifyCardsFragment.this}).isEmpty()) {
                z = false;
            }
            access$200.setActivated(z);
            if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.ModifyCardsFragment", "access$600", new Object[]{ModifyCardsFragment.this})) {
                ModifyCardsFragment.access$602(ModifyCardsFragment.this, false);
            }
        }
    }

    public class ModifyCardsBaseAdapter extends BaseAdapter {
        List<PaymentCard> mPaymentCards;

        /* renamed from: com.mcdonalds.app.account.ModifyCardsFragment$ModifyCardsBaseAdapter$1 */
        class C29971 implements OnClickListener {
            C29971() {
            }

            public void onClick(View v) {
                Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
                Integer index = (Integer) v.getTag();
                ((PaymentCard) ModifyCardsBaseAdapter.this.mPaymentCards.get(index.intValue())).setIsValid(Boolean.valueOf(false));
                ModifyCardsFragment.access$800(ModifyCardsFragment.this, false, index.intValue());
                ModifyCardsFragment.access$602(ModifyCardsFragment.this, true);
            }
        }

        private class ViewHolder {
            ImageView arrow_img;
            TextView card_txt;
            Button delete_btn;

            private ViewHolder() {
            }

            /* synthetic */ ViewHolder(ModifyCardsBaseAdapter x0, C29911 x1) {
                this();
            }
        }

        public ModifyCardsBaseAdapter(List<PaymentCard> mPaymentCards) {
            this.mPaymentCards = mPaymentCards;
        }

        public int getCount() {
            Ensighten.evaluateEvent(this, "getCount", null);
            return this.mPaymentCards.size();
        }

        public Object getItem(int position) {
            Ensighten.evaluateEvent(this, "getItem", new Object[]{new Integer(position)});
            return this.mPaymentCards.get(position);
        }

        public long getItemId(int position) {
            Ensighten.evaluateEvent(this, "getItemId", new Object[]{new Integer(position)});
            return (long) this.mPaymentCards.indexOf(getItem(position));
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            LayoutInflater mInflater = ModifyCardsFragment.this.getNavigationActivity().getLayoutInflater();
            if (convertView == null) {
                convertView = mInflater.inflate(C2658R.layout.cards_row, parent, false);
                holder = new ViewHolder(this, null);
                holder.delete_btn = (Button) convertView.findViewById(C2358R.C2357id.del_btn);
                holder.card_txt = (TextView) convertView.findViewById(C2358R.C2357id.card_ending_txt);
                holder.arrow_img = (ImageView) convertView.findViewById(2131820646);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            DataLayerClickListener.setDataLayerTag(convertView, ViewHolder.class, position);
            PaymentCard rowItem = (PaymentCard) getItem(position);
            if (rowItem.getNickName() != null) {
                holder.card_txt.setText(rowItem.getNickName());
            } else {
                holder.card_txt.setText(ModifyCardsFragment.this.getResources().getString(C2658R.string.card_ending_in_prefix) + rowItem.getAlias().replace("*", ""));
            }
            holder.delete_btn.setTag(Integer.valueOf(position));
            if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.ModifyCardsFragment", "access$300", new Object[]{ModifyCardsFragment.this})) {
                holder.delete_btn.setVisibility(0);
            } else {
                holder.delete_btn.setVisibility(8);
            }
            holder.delete_btn.setOnClickListener(new C29971());
            holder.arrow_img.setVisibility(8);
            Ensighten.getViewReturnValue(convertView, position);
            Ensighten.processView((Object) this, "getView");
            Ensighten.getViewReturnValue(null, -1);
            return convertView;
        }
    }

    static /* synthetic */ void access$100(ModifyCardsFragment x0, List x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.ModifyCardsFragment", "access$100", new Object[]{x0, x1});
        x0.launchAddPaymentScreen(x1);
    }

    static /* synthetic */ void access$1000(ModifyCardsFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.ModifyCardsFragment", "access$1000", new Object[]{x0});
        x0.updateTitleTextView();
    }

    static /* synthetic */ void access$1100(ModifyCardsFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.ModifyCardsFragment", "access$1100", new Object[]{x0});
        x0.checkMaxCardsLimit();
    }

    static /* synthetic */ boolean access$302(ModifyCardsFragment x0, boolean x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.ModifyCardsFragment", "access$302", new Object[]{x0, new Boolean(x1)});
        x0.in_edit_mode = x1;
        return x1;
    }

    static /* synthetic */ boolean access$602(ModifyCardsFragment x0, boolean x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.ModifyCardsFragment", "access$602", new Object[]{x0, new Boolean(x1)});
        x0.changesMade = x1;
        return x1;
    }

    static /* synthetic */ void access$800(ModifyCardsFragment x0, boolean x1, int x2) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.ModifyCardsFragment", "access$800", new Object[]{x0, new Boolean(x1), new Integer(x2)});
        x0.saveCustomerProfileChanges(x1, x2);
    }

    static /* synthetic */ void access$900(ModifyCardsFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.ModifyCardsFragment", "access$900", new Object[]{x0});
        x0.setupMaxCardAlertViewText();
    }

    /* Access modifiers changed, original: protected */
    public String getAnalyticsTitle() {
        Ensighten.evaluateEvent(this, "getAnalyticsTitle", null);
        return getString(C2658R.string.analytics_screen_account_payment_methods);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        this.mCustomerModule = (CustomerModule) ModuleManager.getModule(CustomerModule.NAME);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(C2658R.layout.fragment_modify_cards, container, false);
        this.mCardList = (ListView) rootView.findViewById(C2358R.C2357id.card_list);
        this.mCardList.setOnItemClickListener(new C29911());
        this.mMaxCardAlertTextView = (TextView) rootView.findViewById(C2358R.C2357id.max_card_alert_text);
        this.adapter = new ModifyCardsBaseAdapter(this.mPaymentCards);
        this.mCardList.setAdapter(this.adapter);
        return rootView;
    }

    private void setupMaxCardAlertViewText() {
        Ensighten.evaluateEvent(this, "setupMaxCardAlertViewText", null);
        if (this.mCustomerModule != null) {
            if (this.mPaymentCards.size() > this.mCustomerModule.getMaxAllowedCards()) {
                this.mMaxCardAlertTextView.setText(getString(C2658R.string.max_payment_card_request_removal, String.valueOf(maxAllowedCards)));
                this.mMaxCardAlertTextView.setVisibility(0);
                return;
            }
            this.mMaxCardAlertTextView.setVisibility(8);
        }
    }

    private void launchAddPaymentScreen(List<PaymentMethod> paymentMethods) {
        Ensighten.evaluateEvent(this, "launchAddPaymentScreen", new Object[]{paymentMethods});
        for (PaymentMethod paymentMethod : paymentMethods) {
            if (paymentMethod.getPaymentMode().equals(PaymentMode.Credit)) {
                Class classToLaunch;
                int requestCode;
                int paymentId = paymentMethod.getID().intValue();
                boolean useNativeInterface = Configuration.getSharedInstance().getBooleanForKey("supportedPaymentMethods.creditCard.useNativeInterface");
                Bundle args = new Bundle();
                if (Configuration.getSharedInstance().getBooleanForKey("supportedPaymentMethods.creditCard.isMPGS")) {
                    args.putInt("EXTRA_PAYMENT_ID", paymentId);
                    classToLaunch = MPGSPaymentActivity.class;
                    requestCode = 42804;
                } else if (useNativeInterface) {
                    args.putParcelable("payment_method", paymentMethod);
                    classToLaunch = EditCardActivity.class;
                    requestCode = 42807;
                } else {
                    args.putInt("EXTRA_PAYMENT_ID", paymentId);
                    classToLaunch = PaymentProviderActivity.class;
                    requestCode = 42803;
                }
                startActivityForResult(classToLaunch, args, requestCode);
                return;
            }
        }
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        List<PaymentCard> tempCards = this.mCustomerModule.getCurrentProfile().getCardItems();
        if (tempCards != null) {
            this.mPaymentCards.clear();
            for (PaymentCard paymentCard : tempCards) {
                this.mPaymentCards.add(paymentCard);
            }
            this.mFooterView = getNavigationActivity().getLayoutInflater().inflate(C2658R.layout.settings_footer_row, this.mCardList, false);
            this.mTitleTextView = (TextView) this.mFooterView.findViewById(C2358R.C2357id.tv_title);
            updateTitleTextView();
            this.mCardList.addFooterView(this.mFooterView);
            this.adapter.notifyDataSetChanged();
            if (this.mMenuEditButton != null) {
                this.mMenuEditButton.setActivated(!this.mPaymentCards.isEmpty());
            }
            if (Configuration.getSharedInstance().getBooleanForKey("supportedPaymentMethods.creditCard.checkMaxPaymentCards")) {
                checkMaxCardsLimit();
            }
            setupMaxCardAlertViewText();
        }
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        Ensighten.evaluateEvent(this, "onCreateOptionsMenu", new Object[]{menu, inflater});
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(C2358R.C2360menu.edit_settings, menu);
        this.mMenuEditButton = (TextView) menu.findItem(C2358R.C2357id.action_edit).getActionView();
        this.mMenuEditButton.setActivated(!this.mPaymentCards.isEmpty());
        this.mMenuEditButton.setOnClickListener(new C29953());
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ((requestCode == 42803 || requestCode == 42807 || requestCode == 42804) && resultCode == -1) {
            refreshCustomerProfile();
        }
    }

    private void refreshCustomerProfile() {
        Ensighten.evaluateEvent(this, "refreshCustomerProfile", null);
        if (this.mCustomerModule != null) {
            List<PaymentCard> updatedCardList = this.mCustomerModule.getCurrentProfile().getCardItems();
            if (updatedCardList != null && updatedCardList.size() > this.mPaymentCards.size()) {
                this.mPaymentCards.add(updatedCardList.get(updatedCardList.size() - 1));
                this.adapter.notifyDataSetChanged();
                if (this.mMenuEditButton != null) {
                    this.mMenuEditButton.setActivated(!this.mPaymentCards.isEmpty());
                }
                if (Configuration.getSharedInstance().getBooleanForKey("supportedPaymentMethods.creditCard.checkMaxPaymentCards")) {
                    checkMaxCardsLimit();
                }
            }
        }
    }

    private void updateTitleTextView() {
        Ensighten.evaluateEvent(this, "updateTitleTextView", null);
        if (ConfigurationUtils.isOneClickPaymentFlow()) {
            if (this.mPaymentCards.size() > 0) {
                this.mTitleTextView.setText(C2658R.string.one_click_account_settings_adding_cards);
            } else {
                this.mTitleTextView.setText(C2658R.string.one_click_no_cards);
            }
            this.mTitleTextView.setTextColor(getResources().getColor(C2658R.color.mcd_red));
            this.mFooterView.findViewById(C2358R.C2357id.btn_more).setVisibility(8);
            return;
        }
        this.mFooterView.setOnClickListener(this.mAddCardClickListener);
        this.mTitleTextView.setText(getResources().getString(C2658R.string.add_new_card_title));
    }

    private void saveCustomerProfileChanges(boolean exit, final int index) {
        Ensighten.evaluateEvent(this, "saveCustomerProfileChanges", new Object[]{new Boolean(exit), new Integer(index)});
        if (this.mCustomerModule != null && getNavigationActivity() != null) {
            UIUtils.startActivityIndicator(getNavigationActivity(), "Saving changes");
            this.mCustomerModule.updatePayments(this.mPaymentCards, new AsyncListener<CustomerProfile>() {
                public void onResponse(CustomerProfile response, AsyncToken token, AsyncException exception) {
                    boolean z = false;
                    Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
                    if (exception != null) {
                        AsyncException.report(exception);
                    } else if (ModifyCardsFragment.this.getNavigationActivity() == null || response == null) {
                        AsyncException.report(new AsyncException(ModifyCardsFragment.this.getString(C2658R.string.error_unknown)));
                    } else {
                        Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.ModifyCardsFragment", "access$500", new Object[]{ModifyCardsFragment.this}).remove(index);
                        OrderManager.getInstance().getCurrentOrder().clearPayment();
                        Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.ModifyCardsFragment", "access$400", new Object[]{ModifyCardsFragment.this}).notifyDataSetChanged();
                        if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.ModifyCardsFragment", "access$200", new Object[]{ModifyCardsFragment.this}) != null) {
                            TextView access$200 = Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.ModifyCardsFragment", "access$200", new Object[]{ModifyCardsFragment.this});
                            if (!Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.ModifyCardsFragment", "access$500", new Object[]{ModifyCardsFragment.this}).isEmpty()) {
                                z = true;
                            }
                            access$200.setActivated(z);
                        }
                        ModifyCardsFragment.access$602(ModifyCardsFragment.this, true);
                        ModifyCardsFragment.access$900(ModifyCardsFragment.this);
                        ModifyCardsFragment.access$1000(ModifyCardsFragment.this);
                        if (Configuration.getSharedInstance().getBooleanForKey("supportedPaymentMethods.creditCard.checkMaxPaymentCards")) {
                            ModifyCardsFragment.access$1100(ModifyCardsFragment.this);
                        }
                    }
                    UIUtils.stopActivityIndicator();
                }
            });
        }
    }

    private void checkMaxCardsLimit() {
        Ensighten.evaluateEvent(this, "checkMaxCardsLimit", null);
        int max = -1;
        try {
            max = Integer.parseInt(AppParameters.getAppParameter(AppParameters.MAX_PAYMENT_CARDS));
        } catch (NumberFormatException e) {
        }
        if (max < 0) {
            return;
        }
        if (this.mPaymentCards == null || this.mPaymentCards.size() < max) {
            if (this.mFooterView != null) {
                this.mFooterView.setVisibility(0);
            }
        } else if (this.mFooterView != null) {
            this.mFooterView.setVisibility(8);
        }
    }
}
