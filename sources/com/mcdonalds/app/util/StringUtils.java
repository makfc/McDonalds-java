package com.mcdonalds.app.util;

import android.support.annotation.Nullable;
import android.text.SpannableString;
import android.text.TextUtils;
import com.ensighten.Ensighten;

public class StringUtils {
    public static String getMobileNumberWithoutCountryCode(@Nullable String mobileNumber, @Nullable String countryCode) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.StringUtils", "getMobileNumberWithoutCountryCode", new Object[]{mobileNumber, countryCode});
        if (TextUtils.isEmpty(mobileNumber) || TextUtils.isEmpty(countryCode) || !mobileNumber.contains(countryCode)) {
            return mobileNumber;
        }
        return mobileNumber.substring(countryCode.length());
    }

    public static CharSequence formatWithSpans(String template, CharSequence... values) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.StringUtils", "formatWithSpans", new Object[]{template, values});
        return TextUtils.expandTemplate(new SpannableString(template.replaceAll("%(\\d+)\\$s", "^$1")), values);
    }
}
