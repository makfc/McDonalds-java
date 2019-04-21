package com.mcdonalds.app.ordering.alipay;

import android.text.TextUtils;
import com.ensighten.Ensighten;

public class PayResult {
    private String memo;
    private String result;
    private String resultStatus;

    public PayResult(String rawResult) {
        if (!TextUtils.isEmpty(rawResult)) {
            for (String resultParam : rawResult.split(";")) {
                if (resultParam.startsWith("resultStatus")) {
                    this.resultStatus = gatValue(resultParam, "resultStatus");
                } else if (resultParam.startsWith("result")) {
                    this.result = gatValue(resultParam, "result");
                } else if (resultParam.startsWith("memo")) {
                    this.memo = gatValue(resultParam, "memo");
                }
            }
        }
    }

    public String toString() {
        Ensighten.evaluateEvent(this, "toString", null);
        return "resultStatus={" + this.resultStatus + "};memo={" + this.memo + "};result={" + this.result + "}";
    }

    private String gatValue(String content, String key) {
        Ensighten.evaluateEvent(this, "gatValue", new Object[]{content, key});
        String prefix = key + "={";
        return content.substring(content.indexOf(prefix) + prefix.length(), content.lastIndexOf("}"));
    }

    public String getResultStatus() {
        Ensighten.evaluateEvent(this, "getResultStatus", null);
        return this.resultStatus;
    }

    public String getMemo() {
        Ensighten.evaluateEvent(this, "getMemo", null);
        return this.memo;
    }

    public String getResult() {
        Ensighten.evaluateEvent(this, "getResult", null);
        return this.result;
    }

    public boolean isSuccess() {
        Ensighten.evaluateEvent(this, "isSuccess", null);
        return getResultStatus().equals("9000") || getResultStatus().equals("8000") || getResultStatus().equals("6004");
    }
}
