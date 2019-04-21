package android.databinding.adapters;

import android.databinding.BindingMethods;
import android.widget.AutoCompleteTextView.Validator;

@BindingMethods
public class AutoCompleteTextViewBindingAdapter {

    /* renamed from: android.databinding.adapters.AutoCompleteTextViewBindingAdapter$1 */
    static class C00221 implements Validator {
        final /* synthetic */ FixText val$fixText;
        final /* synthetic */ IsValid val$isValid;

        public boolean isValid(CharSequence text) {
            if (this.val$isValid != null) {
                return this.val$isValid.isValid(text);
            }
            return true;
        }

        public CharSequence fixText(CharSequence invalidText) {
            if (this.val$fixText != null) {
                return this.val$fixText.fixText(invalidText);
            }
            return invalidText;
        }
    }

    public interface FixText {
        CharSequence fixText(CharSequence charSequence);
    }

    public interface IsValid {
        boolean isValid(CharSequence charSequence);
    }
}
