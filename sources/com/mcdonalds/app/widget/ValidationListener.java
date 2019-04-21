package com.mcdonalds.app.widget;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputFilter.LengthFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnKeyListener;
import android.widget.EditText;
import android.widget.TextView;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.util.UIUtils;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.services.configuration.Configuration;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ValidationListener implements TextWatcher, OnFocusChangeListener {
    private String countryCode;
    private OnClickListener mAlertPositiveClickListener;
    private Callback mCallback;
    private String mEmptyErrorMessage;
    private TextView mErrorDisplay;
    private String mErrorMessage;
    private boolean mHasImage;
    private boolean mHasToMatch;
    private boolean mLinkedViews;
    private EditText mMatchingTextfield;
    private int mMinimumLength;
    private OnKeyListener mPhoneCountryCodeDeleteListener;
    private String mRegex;
    private boolean mRequired;
    private boolean mShouldDeleteCountryCode;
    private CharSequence mTempString;
    private EditText mTextField;
    private int mType;
    private OnUpdateListener mUpdateListener;
    private boolean mValidated;

    public interface Callback {
        void onFieldValidationStateChanged(boolean z);
    }

    public interface OnUpdateListener {
        void onFieldUpdate();
    }

    /* renamed from: com.mcdonalds.app.widget.ValidationListener$1 */
    class C38781 implements OnKeyListener {
        C38781() {
        }

        public boolean onKey(View v, int keyCode, KeyEvent event) {
            boolean z = true;
            Ensighten.evaluateEvent(this, "onKey", new Object[]{v, new Integer(keyCode), event});
            String fieldString = Ensighten.evaluateEvent(null, "com.mcdonalds.app.widget.ValidationListener", "access$000", new Object[]{ValidationListener.this}).getText().toString();
            if (TextUtils.isEmpty(Ensighten.evaluateEvent(null, "com.mcdonalds.app.widget.ValidationListener", "access$100", new Object[]{ValidationListener.this})) || fieldString.length() > Ensighten.evaluateEvent(null, "com.mcdonalds.app.widget.ValidationListener", "access$100", new Object[]{ValidationListener.this}).length() + 1 || keyCode != 67) {
                ValidationListener.access$202(ValidationListener.this, false);
            } else {
                ValidationListener validationListener = ValidationListener.this;
                if (TextUtils.isEmpty(fieldString)) {
                    z = false;
                }
                ValidationListener.access$202(validationListener, z);
            }
            return false;
        }
    }

    /* renamed from: com.mcdonalds.app.widget.ValidationListener$2 */
    class C38792 implements OnClickListener {
        C38792() {
        }

        public void onClick(DialogInterface dialog, int which) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
            dialog.dismiss();
        }
    }

    static /* synthetic */ boolean access$202(ValidationListener x0, boolean x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.widget.ValidationListener", "access$202", new Object[]{x0, new Boolean(x1)});
        x0.mShouldDeleteCountryCode = x1;
        return x1;
    }

    public ValidationListener(EditText field, int type, boolean hasImage, boolean required) {
        this.mPhoneCountryCodeDeleteListener = new C38781();
        this.mAlertPositiveClickListener = new C38792();
        this.mTextField = field;
        this.mType = type;
        if (this.mType == 5 && (this.mTextField instanceof EditTextPhone)) {
            this.mTextField.setOnKeyListener(this.mPhoneCountryCodeDeleteListener);
            this.countryCode = ((EditTextPhone) this.mTextField).getCountryCode();
            this.mTempString = this.countryCode;
        }
        this.mHasImage = hasImage;
        this.mRequired = required;
        this.mValidated = validate(getText(this.mTextField), getText(this.mMatchingTextfield), this.mType);
    }

    public ValidationListener(EditText field, EditText matching, int type, boolean hasImage) {
        this(field, type, hasImage, true);
        this.mMatchingTextfield = matching;
    }

    public ValidationListener(EditText field, EditText matching, TextView errorDisplay, int type, boolean hasImage) {
        this(field, type, hasImage, true);
        this.mErrorDisplay = errorDisplay;
        this.mMatchingTextfield = matching;
    }

    public ValidationListener(EditText field, int type, int minimumLength, boolean hasImage, boolean required) {
        this(field, type, hasImage, required);
        this.mMinimumLength = minimumLength;
    }

    public ValidationListener(EditText field, EditText matching, int type, boolean hasImage, boolean hasToMatch, boolean isLinkedViews) {
        this(field, type, hasImage, true);
        this.mMatchingTextfield = matching;
        this.mHasToMatch = hasToMatch;
        this.mLinkedViews = isLinkedViews;
    }

    public ValidationListener(EditText field, String regex, boolean hasImage, boolean required) {
        this(field, 9, hasImage, required);
        this.mRegex = regex;
    }

    public void beforeTextChanged(CharSequence text, int start, int count, int after) {
        Ensighten.evaluateEvent(this, "beforeTextChanged", new Object[]{text, new Integer(start), new Integer(count), new Integer(after)});
    }

    public void onTextChanged(CharSequence text, int start, int before, int count) {
        Ensighten.evaluateEvent(this, "onTextChanged", new Object[]{text, new Integer(start), new Integer(before), new Integer(count)});
        this.mValidated = validate();
        if (this.mValidated) {
            this.mTextField.setBackgroundResource(C2358R.C2359drawable.bg_form_input);
            if (this.mCallback != null) {
                this.mCallback.onFieldValidationStateChanged(true);
            }
            if (this.mErrorDisplay != null) {
                this.mErrorDisplay.setVisibility(8);
            }
        } else if (this.mCallback != null) {
            this.mCallback.onFieldValidationStateChanged(false);
        }
    }

    public void afterTextChanged(Editable editable) {
        Ensighten.evaluateEvent(this, "afterTextChanged", new Object[]{editable});
        if (this.mTextField instanceof EditTextPhone) {
            String text = this.mTextField.getText().toString();
            if (this.mShouldDeleteCountryCode || !TextUtils.isEmpty(text)) {
                if (!this.mShouldDeleteCountryCode && text.length() < this.countryCode.length()) {
                    this.mTextField.setText(this.countryCode + text);
                } else if (this.mShouldDeleteCountryCode && text.length() <= this.countryCode.length()) {
                    this.mTextField.setText("");
                }
            }
        }
        if (this.mUpdateListener != null) {
            this.mUpdateListener.onFieldUpdate();
        }
    }

    public boolean validate() {
        Ensighten.evaluateEvent(this, "validate", null);
        this.mValidated = validate(this.mTextField, this.mType, this.mHasToMatch, this.mMatchingTextfield);
        return this.mValidated;
    }

    private boolean validate(EditText editText, int type, boolean hasToMatch, EditText matchingTextField) {
        Ensighten.evaluateEvent(this, "validate", new Object[]{editText, new Integer(type), new Boolean(hasToMatch), matchingTextField});
        String text = editText.getText().toString();
        if ((editText instanceof EditTextPhone) && this.mShouldDeleteCountryCode) {
            if (TextUtils.isEmpty(text)) {
                this.mShouldDeleteCountryCode = false;
                text = "";
            } else {
                text = text.length() <= this.countryCode.length() ? "" : text.substring(this.countryCode.length());
            }
        }
        String matching = null;
        if (matchingTextField != null) {
            matching = matchingTextField.getText().toString();
        }
        boolean validated = validate(text, matching, type);
        if (type == 10) {
            validated = isPasswordValidationSuccess(validated);
        }
        if (validated && matchingTextField != null) {
            if (type == 7 || type == 8) {
                if (type == 7) {
                    validated = validate(matchingTextField, 8, false, null);
                } else {
                    validated = validate(matchingTextField, 7, false, null);
                }
            } else if (hasToMatch) {
                validated = isMatch(editText, matchingTextField);
            }
        }
        if (this.mHasImage) {
            if (TextUtils.isEmpty(text)) {
                editText.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            } else if (validated) {
                editText.setCompoundDrawablesWithIntrinsicBounds(0, 0, C2358R.C2359drawable.icon_valid, 0);
            } else {
                editText.setCompoundDrawablesWithIntrinsicBounds(0, 0, C2358R.C2359drawable.icon_warn, 0);
            }
        }
        if (matchingTextField == null || hasToMatch || !this.mLinkedViews) {
            if (hasToMatch && this.mLinkedViews) {
                if (isMatch(editText, matchingTextField)) {
                    editText.setCompoundDrawablesWithIntrinsicBounds(0, 0, C2358R.C2359drawable.icon_valid, 0);
                    editText.setBackgroundResource(C2358R.C2359drawable.bg_form_input);
                    changeValidationState(true);
                } else {
                    editText.setCompoundDrawablesWithIntrinsicBounds(0, 0, C2358R.C2359drawable.icon_warn, 0);
                    changeValidationState(false);
                }
            }
        } else if (validated) {
            matchingTextField.setCompoundDrawablesWithIntrinsicBounds(0, 0, C2358R.C2359drawable.icon_valid, 0);
            this.mTextField.setBackgroundResource(C2358R.C2359drawable.bg_form_input);
        } else {
            this.mMatchingTextfield.setCompoundDrawablesWithIntrinsicBounds(0, 0, C2358R.C2359drawable.icon_warn, 0);
        }
        return validated;
    }

    private boolean isPasswordValidationSuccess(boolean isRegexMatched) {
        Ensighten.evaluateEvent(this, "isPasswordValidationSuccess", new Object[]{new Boolean(isRegexMatched)});
        String passwordValue = this.mTextField.getText().toString();
        String confirmPasswordValue = this.mMatchingTextfield.getText().toString();
        boolean passwordMatches = passwordValue.equals(confirmPasswordValue);
        if (this.mErrorDisplay != null) {
            this.mErrorDisplay.setVisibility(8);
        }
        if (isRegexMatched) {
            this.mTextField.setCompoundDrawablesWithIntrinsicBounds(0, 0, C2358R.C2359drawable.icon_valid, 0);
        } else {
            this.mTextField.setCompoundDrawablesWithIntrinsicBounds(0, 0, C2358R.C2359drawable.icon_warn, 0);
        }
        if (!TextUtils.isEmpty(confirmPasswordValue)) {
            if (TextUtils.isEmpty(passwordValue) || !passwordMatches) {
                this.mMatchingTextfield.setCompoundDrawablesWithIntrinsicBounds(0, 0, C2358R.C2359drawable.icon_warn, 0);
                if (isRegexMatched && passwordMatch(confirmPasswordValue) && this.mErrorDisplay != null) {
                    this.mErrorDisplay.setVisibility(0);
                    this.mErrorDisplay.setText(C2658R.string.error_passwords_dont_match);
                }
            } else {
                this.mMatchingTextfield.setCompoundDrawablesWithIntrinsicBounds(0, 0, C2358R.C2359drawable.icon_valid, 0);
            }
        }
        if (isRegexMatched && passwordMatches) {
            return true;
        }
        return false;
    }

    private boolean validate(CharSequence cs, String matching, int type) {
        Ensighten.evaluateEvent(this, "validate", new Object[]{cs, matching, new Integer(type)});
        String text = cs.toString();
        boolean empty = TextUtils.isEmpty(text);
        if (empty && !this.mRequired) {
            return true;
        }
        switch (type) {
            case 0:
                return isEmail(text);
            case 1:
            case 10:
                return passwordMatch(text);
            case 3:
                return Configuration.getSharedInstance().isZipCode(text);
            case 4:
                if (!empty) {
                    if (this.mMinimumLength == 0) {
                        return true;
                    }
                    if (this.mMinimumLength > 0 && text.length() >= this.mMinimumLength) {
                        return true;
                    }
                }
                return false;
            case 5:
                setPhoneMaxDigits();
                return isPhone(text);
            case 6:
                return isSMS(text);
            case 7:
                return isValidDate(text, matching);
            case 8:
                return isValidDate(matching, text);
            case 9:
                return matchesRegex(text);
            case 11:
                return UIUtils.isNumberValid(text);
            default:
                return false;
        }
    }

    private void setPhoneMaxDigits() {
        Ensighten.evaluateEvent(this, "setPhoneMaxDigits", null);
        double maxLength = ((Double) Configuration.getSharedInstance().getValueForKey("interface.register.phoneMaxNumberOfDigits")).doubleValue();
        if (!TextUtils.isEmpty(this.countryCode)) {
            maxLength += (double) this.countryCode.length();
        }
        if (maxLength > 0.0d) {
            this.mTextField.setFilters(new InputFilter[]{new LengthFilter((int) maxLength)});
        }
    }

    private boolean isPhone(String text) {
        Ensighten.evaluateEvent(this, "isPhone", new Object[]{text});
        String pattern = Configuration.getSharedInstance().getLocalizedStringForKey("textValidation.phoneNumberRegex");
        if (!TextUtils.isEmpty(pattern)) {
            return text.matches(pattern);
        }
        if (text.length() != 8) {
            return false;
        }
        return true;
    }

    private boolean isSMS(String text) {
        Ensighten.evaluateEvent(this, "isSMS", new Object[]{text});
        String pattern = Configuration.getSharedInstance().getLocalizedStringForKey("textValidation.smsRegex");
        if (TextUtils.isEmpty(pattern)) {
            return false;
        }
        return text.matches(pattern);
    }

    private boolean isValidMonth(String month, boolean currentYear) {
        boolean z = true;
        Ensighten.evaluateEvent(this, "isValidMonth", new Object[]{month, new Boolean(currentYear)});
        if (TextUtils.isEmpty(month)) {
            return false;
        }
        Calendar calendar = Calendar.getInstance();
        int minimum = calendar.getMinimum(2);
        if (currentYear) {
            minimum = Integer.parseInt(new SimpleDateFormat("MM", Locale.getDefault()).format(new Date()));
        }
        int maximum = calendar.getMaximum(2);
        try {
            int number = Integer.parseInt(month) - 1;
            if (minimum > number || number > maximum) {
                z = false;
            }
            return z;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isValidYear(String year) {
        boolean z = true;
        Ensighten.evaluateEvent(this, "isValidYear", new Object[]{year});
        if (TextUtils.isEmpty(year)) {
            return false;
        }
        int minimum = Integer.parseInt(new SimpleDateFormat("yy", Locale.getDefault()).format(new Date()));
        int maximum = Calendar.getInstance().getMaximum(1);
        try {
            int number = Integer.parseInt(year);
            if (minimum > number || number > maximum) {
                z = false;
            }
            return z;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isCurrentYear(String year) {
        Ensighten.evaluateEvent(this, "isCurrentYear", new Object[]{year});
        if (TextUtils.isEmpty(year)) {
            return false;
        }
        return new SimpleDateFormat("yy", Locale.getDefault()).format(new Date()).equals(year);
    }

    private boolean isValidDate(String month, String year) {
        Ensighten.evaluateEvent(this, "isValidDate", new Object[]{month, year});
        boolean isCurrentYear = isCurrentYear(year);
        if (month == null) {
            return isValidYear(year);
        }
        if (year == null) {
            return isValidMonth(month, isCurrentYear);
        }
        if (isValidYear(year) && isValidMonth(month, isCurrentYear)) {
            return true;
        }
        return false;
    }

    private boolean matchesRegex(String text) {
        Ensighten.evaluateEvent(this, "matchesRegex", new Object[]{text});
        if (this.mRegex == null) {
            return true;
        }
        return text.matches(this.mRegex);
    }

    private boolean isMatch(EditText editText, EditText matchingTextField) {
        Ensighten.evaluateEvent(this, "isMatch", new Object[]{editText, matchingTextField});
        String text = getText(editText);
        String matching = getText(matchingTextField);
        boolean emailCaseSensitive = Configuration.getSharedInstance().getBooleanForKey("textValidation.emailConfirmationCaseSensitive");
        if (this.mType == 0 && !emailCaseSensitive && !TextUtils.isEmpty(matching)) {
            return matching.toLowerCase().equals(text.toLowerCase());
        } else if (TextUtils.isEmpty(matching) || !matching.equals(text)) {
            return false;
        } else {
            return true;
        }
    }

    private boolean isEmail(String text) {
        Ensighten.evaluateEvent(this, "isEmail", new Object[]{text});
        return UIUtils.isEmailValid(text);
    }

    private boolean passwordMatch(String text) {
        Ensighten.evaluateEvent(this, "passwordMatch", new Object[]{text});
        return UIUtils.isPasswordValid(text);
    }

    @NonNull
    private String getText(EditText field) {
        Ensighten.evaluateEvent(this, "getText", new Object[]{field});
        if (field == null || field.getText() == null) {
            return "";
        }
        return field.getText().toString();
    }

    public EditText getTextField() {
        Ensighten.evaluateEvent(this, "getTextField", null);
        return this.mTextField;
    }

    public void setValidationCallback(Callback callback) {
        Ensighten.evaluateEvent(this, "setValidationCallback", new Object[]{callback});
        this.mCallback = callback;
    }

    public void setErrorDisplay(TextView display) {
        Ensighten.evaluateEvent(this, "setErrorDisplay", new Object[]{display});
        this.mErrorDisplay = display;
    }

    public void setEmptyMessage(String message) {
        Ensighten.evaluateEvent(this, "setEmptyMessage", new Object[]{message});
        this.mEmptyErrorMessage = message;
    }

    public void setErrorMessage(String message) {
        Ensighten.evaluateEvent(this, "setErrorMessage", new Object[]{message});
        this.mErrorMessage = message;
    }

    public void setMinimumLength(int mMinimumLength) {
        Ensighten.evaluateEvent(this, "setMinimumLength", new Object[]{new Integer(mMinimumLength)});
        this.mMinimumLength = mMinimumLength;
    }

    public boolean isValidated() {
        Ensighten.evaluateEvent(this, "isValidated", null);
        return this.mValidated;
    }

    public void displayError() {
        Ensighten.evaluateEvent(this, "displayError", null);
        this.mTextField.setBackgroundResource(C2358R.C2359drawable.bg_form_input_error);
        if (this.mErrorDisplay != null) {
            String text = getText(this.mTextField);
            if (this.mEmptyErrorMessage == null || !TextUtils.isEmpty(text)) {
                this.mErrorDisplay.setText(this.mErrorMessage);
            } else {
                this.mErrorDisplay.setText(this.mEmptyErrorMessage);
            }
            this.mErrorDisplay.setVisibility(0);
        }
    }

    private void changeValidationState(boolean validation) {
        Ensighten.evaluateEvent(this, "changeValidationState", new Object[]{new Boolean(validation)});
        if (this.mValidated != validation) {
            this.mValidated = validation;
            if (this.mCallback != null) {
                this.mCallback.onFieldValidationStateChanged(this.mValidated);
            }
        }
    }

    public void setUpdateListener(OnUpdateListener listener) {
        Ensighten.evaluateEvent(this, "setUpdateListener", new Object[]{listener});
        this.mUpdateListener = listener;
    }

    public void onFocusChange(View v, boolean hasFocus) {
        Ensighten.evaluateEvent(this, "onFocusChange", new Object[]{v, new Boolean(hasFocus)});
        if ((v instanceof EditText) && !hasFocus && !this.mValidated) {
            displayError();
        }
    }

    public int getType() {
        Ensighten.evaluateEvent(this, "getType", null);
        return this.mType;
    }
}
