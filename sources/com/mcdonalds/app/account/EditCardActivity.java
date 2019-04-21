package com.mcdonalds.app.account;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.p000v4.content.ContextCompat;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputFilter.LengthFilter;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.UnderlineSpan;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.Spinner;
import android.widget.TextView;
import com.amap.api.services.district.DistrictSearchQuery;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.p043ui.EditTextMask;
import com.mcdonalds.app.p043ui.URLActionBarActivity;
import com.mcdonalds.app.util.UIUtils;
import com.mcdonalds.app.util.UIUtils.MCDAlertDialogBuilder;
import com.mcdonalds.app.widget.ValidationListener;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.modules.models.CreditCard;
import com.mcdonalds.sdk.modules.models.PaymentMethod;
import com.newrelic.agent.android.agentdata.HexAttributes;

public class EditCardActivity extends URLActionBarActivity implements EditCardView {
    private EditText mCardExpirationMonth;
    private EditText mCardExpirationYear;
    private EditText mCardNickName;
    private EditText mCardNumber;
    private EditTextMask mCardNumberMask;
    private ValidationListener mCardNumberValidationListener;
    TextWatcher mCardNumberWatcher = new C29702();
    private EditText mCardSecurityCode;
    private ValidationListener mCardSecurityCodeValidationListener;
    private Spinner mCardTypeChooser;
    private EditText mCardholderName;
    private EditText mCity;
    private EditText mCountry;
    private Button mDoneButton;
    private OnClickListener mErrorDialogListener = new C29713();
    TextWatcher mLengthWatcher = new C29691();
    private boolean mOneTimePayment;
    private PaymentMethod mPaymentMethod;
    private EditCardPresenter mPresenter;
    private EditText mState;
    private EditText mStreetAddress;
    private EditText mZipCode;

    /* renamed from: com.mcdonalds.app.account.EditCardActivity$1 */
    class C29691 implements TextWatcher {
        C29691() {
        }

        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            Ensighten.evaluateEvent(this, "beforeTextChanged", new Object[]{s, new Integer(start), new Integer(count), new Integer(after)});
        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
            Ensighten.evaluateEvent(this, "onTextChanged", new Object[]{s, new Integer(start), new Integer(before), new Integer(count)});
        }

        public void afterTextChanged(Editable s) {
            Ensighten.evaluateEvent(this, "afterTextChanged", new Object[]{s});
            String text = s.toString();
            if (!TextUtils.isEmpty(text)) {
                String cardNumber = Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.EditCardActivity", "access$000", new Object[]{EditCardActivity.this}).getText().toString();
                boolean isAmex = false;
                String cardType = Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.EditCardActivity", "access$100", new Object[]{EditCardActivity.this}).getCardType(cardNumber);
                if (cardType != null) {
                    isAmex = cardType.equals(CreditCard.TYPE_AMERICAN_EXPRESS);
                }
                int length = 0;
                View nextField = null;
                if (text.equals(cardNumber)) {
                    nextField = Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.EditCardActivity", "access$200", new Object[]{EditCardActivity.this});
                    if (isAmex) {
                        length = 17;
                    } else {
                        length = 19;
                    }
                } else if (text.equals(Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.EditCardActivity", "access$300", new Object[]{EditCardActivity.this}).getText().toString())) {
                    nextField = Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.EditCardActivity", "access$400", new Object[]{EditCardActivity.this});
                    if (isAmex) {
                        length = 4;
                    } else {
                        length = 3;
                    }
                } else if (text.equals(Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.EditCardActivity", "access$200", new Object[]{EditCardActivity.this}).getText().toString())) {
                    length = 2;
                    nextField = Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.EditCardActivity", "access$500", new Object[]{EditCardActivity.this});
                } else if (text.equals(Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.EditCardActivity", "access$500", new Object[]{EditCardActivity.this}).getText().toString())) {
                    length = 2;
                    nextField = Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.EditCardActivity", "access$300", new Object[]{EditCardActivity.this});
                }
                if (nextField != null && text.length() >= length) {
                    nextField.requestFocus();
                }
            }
        }
    }

    /* renamed from: com.mcdonalds.app.account.EditCardActivity$2 */
    class C29702 implements TextWatcher {
        C29702() {
        }

        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            Ensighten.evaluateEvent(this, "beforeTextChanged", new Object[]{s, new Integer(start), new Integer(count), new Integer(after)});
        }

        public void onTextChanged(CharSequence text, int start, int before, int count) {
            Ensighten.evaluateEvent(this, "onTextChanged", new Object[]{text, new Integer(start), new Integer(before), new Integer(count)});
        }

        public void afterTextChanged(Editable s) {
            Ensighten.evaluateEvent(this, "afterTextChanged", new Object[]{s});
            String text = s.toString();
            if (!TextUtils.isEmpty(text)) {
                String cardType = Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.EditCardActivity", "access$100", new Object[]{EditCardActivity.this}).getCardType(text);
                if (cardType != null) {
                    if (cardType.equals(CreditCard.TYPE_AMERICAN_EXPRESS)) {
                        Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.EditCardActivity", "access$000", new Object[]{EditCardActivity.this}).setFilters(new InputFilter[]{new LengthFilter(17)});
                        Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.EditCardActivity", "access$300", new Object[]{EditCardActivity.this}).setFilters(new InputFilter[]{new LengthFilter(4)});
                        Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.EditCardActivity", "access$600", new Object[]{EditCardActivity.this}).setMask("#### ###### #####");
                        Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.EditCardActivity", "access$700", new Object[]{EditCardActivity.this}).setMinimumLength(17);
                        Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.EditCardActivity", "access$800", new Object[]{EditCardActivity.this}).setMinimumLength(4);
                    } else {
                        Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.EditCardActivity", "access$000", new Object[]{EditCardActivity.this}).setFilters(new InputFilter[]{new LengthFilter(19)});
                        Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.EditCardActivity", "access$300", new Object[]{EditCardActivity.this}).setFilters(new InputFilter[]{new LengthFilter(3)});
                        Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.EditCardActivity", "access$600", new Object[]{EditCardActivity.this}).setMask("#### #### #### ####");
                        Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.EditCardActivity", "access$700", new Object[]{EditCardActivity.this}).setMinimumLength(19);
                        Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.EditCardActivity", "access$800", new Object[]{EditCardActivity.this}).setMinimumLength(3);
                    }
                }
                int cardTypeDrawableResourceId = Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.EditCardActivity", "access$100", new Object[]{EditCardActivity.this}).getCardTypeDrawableResourceId(text);
                if (cardTypeDrawableResourceId != -1) {
                    Drawable drawable = ContextCompat.getDrawable(EditCardActivity.this, cardTypeDrawableResourceId);
                    if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.EditCardActivity", "access$700", new Object[]{EditCardActivity.this}).isValidated()) {
                        Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.EditCardActivity", "access$000", new Object[]{EditCardActivity.this}).setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);
                    } else {
                        Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.EditCardActivity", "access$000", new Object[]{EditCardActivity.this}).setCompoundDrawablesWithIntrinsicBounds(drawable, null, ContextCompat.getDrawable(EditCardActivity.this, C2358R.C2359drawable.icon_warn), null);
                    }
                } else if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.EditCardActivity", "access$700", new Object[]{EditCardActivity.this}).isValidated()) {
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.EditCardActivity", "access$000", new Object[]{EditCardActivity.this}).setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
                } else {
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.EditCardActivity", "access$000", new Object[]{EditCardActivity.this}).setCompoundDrawablesWithIntrinsicBounds(null, null, ContextCompat.getDrawable(EditCardActivity.this, C2358R.C2359drawable.icon_warn), null);
                }
            }
        }
    }

    /* renamed from: com.mcdonalds.app.account.EditCardActivity$3 */
    class C29713 implements OnClickListener {
        C29713() {
        }

        public void onClick(DialogInterface dialog, int which) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
            dialog.dismiss();
        }
    }

    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C2658R.layout.activity_edit_card);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.mPaymentMethod = (PaymentMethod) extras.getParcelable("payment_method");
            this.mOneTimePayment = extras.getBoolean("one_time_payment", false);
        }
        if (this.mOneTimePayment) {
            setTitle(getString(C2658R.string.one_time_payment_title));
        } else {
            setTitle(getString(C2658R.string.add_new_card_title));
        }
        this.mPresenter = new EditCardPresenter(this, this.mPaymentMethod, this.mOneTimePayment);
        this.mDoneButton = (Button) findViewById(C2358R.C2357id.done_button);
        this.mCardholderName = (EditText) findViewById(C2358R.C2357id.cardholder_name);
        addValidation(new ValidationListener(this.mCardholderName, this.mPresenter.getCardHolderNameRegex(), true, true));
        this.mCardNumber = (EditText) findViewById(C2358R.C2357id.card_number);
        this.mCardNumberValidationListener = new ValidationListener(this.mCardNumber, 4, 19, false, true);
        addValidation(this.mCardNumberValidationListener);
        this.mCardExpirationMonth = (EditText) findViewById(C2358R.C2357id.card_expiration_month);
        this.mCardExpirationYear = (EditText) findViewById(C2358R.C2357id.card_expiration_year);
        this.mCardExpirationMonth.addTextChangedListener(this.mLengthWatcher);
        this.mCardExpirationYear.addTextChangedListener(this.mLengthWatcher);
        addValidation(new ValidationListener(this.mCardExpirationMonth, this.mCardExpirationYear, 7, true, false, true));
        addValidation(new ValidationListener(this.mCardExpirationYear, this.mCardExpirationMonth, 8, true, false, true));
        TextView hintButton = (TextView) findViewById(C2358R.C2357id.hint_button);
        SpannableString hintText = new SpannableString(getString(C2658R.string.hint_button));
        hintText.setSpan(new UnderlineSpan(), 0, hintText.length(), 0);
        hintButton.setText(hintText);
        this.mCardSecurityCode = (EditText) findViewById(C2358R.C2357id.card_security_code);
        this.mCardSecurityCode.addTextChangedListener(this.mLengthWatcher);
        this.mCardSecurityCodeValidationListener = new ValidationListener(this.mCardSecurityCode, 4, 3, true, true);
        addValidation(this.mCardSecurityCodeValidationListener);
        this.mCardNumber.addTextChangedListener(this.mCardNumberWatcher);
        this.mCardNumber.addTextChangedListener(this.mLengthWatcher);
        this.mCardNumberMask = new EditTextMask();
        this.mCardNumberMask.setMask("#### #### #### ####");
        this.mCardNumberMask.setEditText(this.mCardNumber);
        setupExtraFields();
        setupAcceptedCards();
    }

    public void showHint(View v) {
        Ensighten.evaluateEvent(this, "showHint", new Object[]{v});
        startActivity(CardHintActivity.class);
    }

    public void saveCard(View v) {
        Ensighten.evaluateEvent(this, "saveCard", new Object[]{v});
        this.mPresenter.saveCard(this.mCardholderName.getText().toString(), this.mCardNumber.getText().toString(), this.mCardExpirationMonth.getText().toString(), this.mCardExpirationYear.getText().toString(), this.mCardSecurityCode.getText().toString(), this.mCardNickName.getText().toString(), this.mStreetAddress.getText().toString(), this.mCity.getText().toString(), this.mState.getText().toString(), this.mCountry.getText().toString(), this.mZipCode.getText().toString());
    }

    private void setupExtraFields() {
        Ensighten.evaluateEvent(this, "setupExtraFields", null);
        this.mCardTypeChooser = (Spinner) findViewById(C2358R.C2357id.card_type_chooser);
        if (this.mPresenter.isFieldEnabled("cardType")) {
            this.mCardTypeChooser.setAdapter(ArrayAdapter.createFromResource(this, C2658R.array.card_types, 17367049));
        } else {
            this.mCardTypeChooser.setVisibility(8);
        }
        this.mCardNickName = (EditText) findViewById(C2358R.C2357id.card_nickname);
        addValidation(new ValidationListener(this.mCardNickName, this.mPresenter.getCardNickNameRegex(), true, false));
        if (this.mOneTimePayment || !this.mPresenter.isFieldEnabled("cardNickname")) {
            this.mCardNickName.setVisibility(8);
        }
        if (!this.mPresenter.hasAddressFields()) {
            findViewById(C2358R.C2357id.address_header).setVisibility(8);
        }
        this.mStreetAddress = (EditText) findViewById(C2358R.C2357id.card_address_street);
        if (!this.mPresenter.isFieldEnabled("streetAddress")) {
            this.mStreetAddress.setVisibility(8);
        }
        this.mCity = (EditText) findViewById(C2358R.C2357id.card_address_city);
        if (!this.mPresenter.isFieldEnabled(DistrictSearchQuery.KEYWORDS_CITY)) {
            this.mCity.setVisibility(8);
        }
        this.mState = (EditText) findViewById(C2358R.C2357id.card_address_state);
        if (!this.mPresenter.isFieldEnabled(HexAttributes.HEX_ATTR_THREAD_STATE)) {
            this.mState.setVisibility(8);
        }
        this.mCountry = (EditText) findViewById(C2358R.C2357id.card_address_country);
        if (!this.mPresenter.isFieldEnabled(DistrictSearchQuery.KEYWORDS_COUNTRY)) {
            this.mCountry.setVisibility(8);
        }
        this.mZipCode = (EditText) findViewById(C2358R.C2357id.card_address_postal_code);
        if (this.mPresenter.isFieldEnabled("zipCode")) {
            EditTextMask zipCodeMask = new EditTextMask();
            zipCodeMask.setMask("### ####");
            zipCodeMask.setEditText(this.mZipCode);
            return;
        }
        this.mZipCode.setVisibility(8);
    }

    private void setupAcceptedCards() {
        int i = 0;
        Ensighten.evaluateEvent(this, "setupAcceptedCards", null);
        ViewGroup container = (ViewGroup) findViewById(C2358R.C2357id.accepted_cards_container);
        int[] drawables = this.mPresenter.getAcceptedCardsDrawableResourceIds();
        int margin = (int) TypedValue.applyDimension(1, 16.0f, getResources().getDisplayMetrics());
        LayoutParams params = new LayoutParams(-2, -2);
        params.setMargins(margin, 0, margin, 0);
        int length = drawables.length;
        while (i < length) {
            int resourceId = drawables[i];
            ImageView imageView = new ImageView(this);
            imageView.setImageDrawable(ContextCompat.getDrawable(this, resourceId));
            imageView.setLayoutParams(params);
            container.addView(imageView);
            i++;
        }
    }

    private void addValidation(ValidationListener validation) {
        Ensighten.evaluateEvent(this, "addValidation", new Object[]{validation});
        EditText validationEditText = validation.getTextField();
        validationEditText.addTextChangedListener(validation);
        validationEditText.setOnFocusChangeListener(validation);
        this.mPresenter.addValidation(validation);
    }

    public void enableSaveCard() {
        Ensighten.evaluateEvent(this, "enableSaveCard", null);
        this.mDoneButton.setEnabled(true);
    }

    public void disableSaveCard() {
        Ensighten.evaluateEvent(this, "disableSaveCard", null);
        this.mDoneButton.setEnabled(false);
    }

    public void startSavingCardIndicator() {
        Ensighten.evaluateEvent(this, "startSavingCardIndicator", null);
        UIUtils.startActivityIndicator((Context) this, (int) C2658R.string.label_processing);
    }

    public void stopSavingCardIndicator() {
        Ensighten.evaluateEvent(this, "stopSavingCardIndicator", null);
        UIUtils.stopActivityIndicator();
    }

    public void notifyCardSaved() {
        Ensighten.evaluateEvent(this, "notifyCardSaved", null);
        setResult(-1);
        finish();
    }

    public void notifyOneTimePaymentSuccess(String jsonCardInfo) {
        Ensighten.evaluateEvent(this, "notifyOneTimePaymentSuccess", new Object[]{jsonCardInfo});
        Intent intent = new Intent();
        intent.putExtra("one_time_payment", true);
        intent.putExtra("json_card_info", jsonCardInfo);
        setResult(-1, intent);
        finish();
    }

    public void notifyCardSavingError(int messageResId) {
        Ensighten.evaluateEvent(this, "notifyCardSavingError", new Object[]{new Integer(messageResId)});
        MCDAlertDialogBuilder.withContext(this).setMessage(messageResId).setPositiveButton((int) C2658R.string.f6083ok, this.mErrorDialogListener).create().show();
    }

    public void notifyCardSavingError(AsyncException exception) {
        Ensighten.evaluateEvent(this, "notifyCardSavingError", new Object[]{exception});
        AsyncException.report(exception);
    }
}
