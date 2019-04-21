package com.mcdonalds.app.p043ui;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import com.ensighten.Ensighten;

/* renamed from: com.mcdonalds.app.ui.EditTextMask */
public class EditTextMask {
    private EditText mEditText;
    private String mMask = "";
    private TextWatcher mTextWatcher = new C38021();

    /* renamed from: com.mcdonalds.app.ui.EditTextMask$1 */
    class C38021 implements TextWatcher {
        boolean isUpdating;
        String old = "";

        C38021() {
        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
            int i = 0;
            Ensighten.evaluateEvent(this, "onTextChanged", new Object[]{s, new Integer(start), new Integer(before), new Integer(count)});
            String str = Ensighten.evaluateEvent(null, "com.mcdonalds.app.ui.EditTextMask", "access$000", new Object[]{EditTextMask.this, s.toString()});
            String mask = "";
            if (this.isUpdating) {
                this.old = str;
                this.isUpdating = false;
                return;
            }
            int i2 = 0;
            char[] toCharArray = Ensighten.evaluateEvent(null, "com.mcdonalds.app.ui.EditTextMask", "access$100", new Object[]{EditTextMask.this}).toCharArray();
            int length = toCharArray.length;
            while (i < length) {
                char m = toCharArray[i];
                if (m == '#' || str.length() <= this.old.length()) {
                    try {
                        mask = mask + str.charAt(i2);
                        i2++;
                    } catch (Exception e) {
                    }
                } else {
                    mask = mask + m;
                }
                i++;
            }
            this.isUpdating = true;
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.ui.EditTextMask", "access$200", new Object[]{EditTextMask.this}).setText(mask);
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.ui.EditTextMask", "access$200", new Object[]{EditTextMask.this}).setSelection(mask.length());
        }

        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            Ensighten.evaluateEvent(this, "beforeTextChanged", new Object[]{s, new Integer(start), new Integer(count), new Integer(after)});
        }

        public void afterTextChanged(Editable s) {
            Ensighten.evaluateEvent(this, "afterTextChanged", new Object[]{s});
        }
    }

    public void setMask(String mask) {
        Ensighten.evaluateEvent(this, "setMask", new Object[]{mask});
        this.mMask = mask;
    }

    public void setEditText(EditText editText) {
        Ensighten.evaluateEvent(this, "setEditText", new Object[]{editText});
        this.mEditText = editText;
        this.mEditText.addTextChangedListener(this.mTextWatcher);
    }

    private String unmask(String s) {
        Ensighten.evaluateEvent(this, "unmask", new Object[]{s});
        return s.replaceAll("[.]", "").replaceAll("[-]", "").replaceAll("[/]", "").replaceAll("[(]", "").replaceAll("[)]", "").replace(" ", "");
    }
}
