package com.admaster.square.utils;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Order extends JSONObject implements Serializable {
    public static final String od_corderCurrencyType = "orderCurrencyType";
    public static final String od_orderAcount = "orderAmount";
    public static final String od_orderid = "orderId";
    public static final String od_payType = "orderType";
    public static final String orderItems = "orderItems";
    /* renamed from: a */
    JSONArray f250a = null;

    private Order(String str, float f, String str2) {
        try {
            Object format;
            put(od_orderid, AndroidUtil.m391a(str));
            String str3 = "0";
            try {
                format = new DecimalFormat("##0.00").format((double) f);
            } catch (Exception e) {
                format = "0";
            }
            put(od_orderAcount, format);
            put(od_corderCurrencyType, AndroidUtil.m391a(str2));
        } catch (JSONException e2) {
        }
    }

    private Order(String str, float f, String str2, String str3) {
        try {
            Object format;
            put(od_orderid, AndroidUtil.m391a(str));
            String str4 = "0";
            try {
                format = new DecimalFormat("##0.00").format((double) f);
            } catch (Exception e) {
                format = "0";
            }
            put(od_orderAcount, format);
            put(od_corderCurrencyType, AndroidUtil.m391a(str2));
            put(od_payType, AndroidUtil.m391a(str3));
        } catch (JSONException e2) {
        }
    }

    private Order() {
    }

    public static Order createOrder() {
        return new Order();
    }

    public static Order createOrder(String str, float f, String str2) {
        return new Order(str, f, str2);
    }

    public static Order createOrder(String str, float f, String str2, String str3) {
        return new Order(str, f, str2, str3);
    }

    public static JSONArray createOrderList(ArrayList<Order> arrayList) {
        return new Order().createOrderListArray(arrayList);
    }

    public JSONArray createOrderListArray(ArrayList<Order> arrayList) {
        try {
            this.f250a = new JSONArray();
            put("orderMessages", this.f250a);
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                this.f250a.put((Order) it.next());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return this.f250a;
    }

    public static JSONArray createPaySuccOrderList(ArrayList<Order> arrayList) {
        return new Order().createPaySuccOrderListArray(arrayList);
    }

    public JSONArray createPaySuccOrderListArray(ArrayList<Order> arrayList) {
        try {
            this.f250a = new JSONArray();
            put("orderMessages", this.f250a);
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                this.f250a.put((Order) it.next());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return this.f250a;
    }

    public synchronized Order addItem(String str, String str2, String str3, String str4, int i) {
        try {
            if (this.f250a == null) {
                this.f250a = new JSONArray();
                put(orderItems, this.f250a);
            }
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("itemCategory", AndroidUtil.m391a(str));
            jSONObject.put("itemId", AndroidUtil.m391a(str2));
            jSONObject.put("itemName", AndroidUtil.m391a(str3));
            try {
                jSONObject.put("itemUnitPrice", new DecimalFormat("##0.00").format(Float.valueOf(Float.parseFloat(str4))));
            } catch (Exception e) {
                jSONObject.put("itemUnitPrice", 0.0d);
            }
            jSONObject.put("itemCount", i);
            this.f250a.put(jSONObject);
        } catch (JSONException e2) {
        }
        return this;
    }

    public synchronized JSONArray addPaySuccItem(String str, int i, String str2, String str3) {
        try {
            this.f250a = new JSONArray();
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(od_orderid, AndroidUtil.m391a(str));
            jSONObject.put(od_orderAcount, i);
            jSONObject.put(od_corderCurrencyType, AndroidUtil.m391a(str2));
            jSONObject.put(od_payType, AndroidUtil.m391a(str3));
            this.f250a.put(jSONObject);
        } catch (JSONException e) {
        }
        return this.f250a;
    }
}
