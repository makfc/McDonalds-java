package p049hk.com.aisoft.easyaddrui;

import java.util.regex.PatternSyntaxException;

/* renamed from: hk.com.aisoft.easyaddrui.StoreReturn */
public class StoreReturn {
    public String sCode = "";
    public String sHub1 = "";
    public String sHub2 = "";
    public String sHub3 = "";
    public String sHub4 = "";

    public StoreReturn(String mCode) {
        this.sCode = mCode;
    }

    public void setHub(String mPayLoad) {
        if (mPayLoad.indexOf("\\\"Stores\\\":[") >= 0) {
            this.sCode = "1";
            mPayLoad = mPayLoad.substring(mPayLoad.indexOf("\"Stores\":[") + "\"Stores\":[".length());
            try {
                String[] sStroreSet = mPayLoad.substring(0, mPayLoad.indexOf("]")).split("StoreNumber", -1);
                for (int i = 1; i < sStroreSet.length; i++) {
                    if (sStroreSet[i].indexOf("\\\"StoreTypeCode\\\":1") > 0) {
                        this.sHub1 = sStroreSet[i].substring(5);
                        this.sHub1 = this.sHub1.substring(0, this.sHub1.indexOf("\\\""));
                    } else if (sStroreSet[i].indexOf("\\\"StoreTypeCode\\\":2") > 0) {
                        this.sHub2 = sStroreSet[i].substring(5);
                        this.sHub2 = this.sHub2.substring(0, this.sHub2.indexOf("\\\""));
                    } else if (sStroreSet[i].indexOf("\\\"StoreTypeCode\\\":3") > 0) {
                        this.sHub3 = sStroreSet[i].substring(5);
                        this.sHub3 = this.sHub3.substring(0, this.sHub3.indexOf("\\\""));
                    } else if (sStroreSet[i].indexOf("\\\"StoreTypeCode\\\":4") > 0) {
                        this.sHub4 = sStroreSet[i].substring(5);
                        this.sHub4 = this.sHub4.substring(0, this.sHub4.indexOf("\\\""));
                    }
                }
                return;
            } catch (PatternSyntaxException e) {
                return;
            }
        }
        mPayLoad = mPayLoad.substring(mPayLoad.indexOf("\"ResultCode\":") + "\"ResultCode\":".length());
        this.sCode = mPayLoad.substring(0, mPayLoad.indexOf("}"));
    }
}
