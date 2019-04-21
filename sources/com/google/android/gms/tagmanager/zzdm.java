package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.analytics.Tracker;
import com.google.android.gms.analytics.ecommerce.Product;
import com.google.android.gms.analytics.ecommerce.ProductAction;
import com.google.android.gms.analytics.ecommerce.Promotion;
import com.google.android.gms.internal.zzad;
import com.google.android.gms.internal.zzae;
import com.google.android.gms.internal.zzag.zza;
import com.mcdonalds.sdk.connectors.autonavi.AutoNavi.Methods;
import com.mcdonalds.sdk.services.analytics.AnalyticsArgs;
import com.mcdonalds.sdk.services.analytics.JiceArgs;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class zzdm extends zzdj {
    /* renamed from: ID */
    private static final String f6507ID = zzad.UNIVERSAL_ANALYTICS.toString();
    private static final String zzbrP = zzae.ACCOUNT.toString();
    private static final String zzbrQ = zzae.ANALYTICS_PASS_THROUGH.toString();
    private static final String zzbrR = zzae.ENABLE_ECOMMERCE.toString();
    private static final String zzbrS = zzae.ECOMMERCE_USE_DATA_LAYER.toString();
    private static final String zzbrT = zzae.ECOMMERCE_MACRO_DATA.toString();
    private static final String zzbrU = zzae.ANALYTICS_FIELDS.toString();
    private static final String zzbrV = zzae.TRACK_TRANSACTION.toString();
    private static final String zzbrW = zzae.TRANSACTION_DATALAYER_MAP.toString();
    private static final String zzbrX = zzae.TRANSACTION_ITEM_DATALAYER_MAP.toString();
    private static final List<String> zzbrY = Arrays.asList(new String[]{"detail", JiceArgs.EVENT_CHECKOUT, "checkout_option", JiceArgs.LABEL_DASHBOARD_REGISTER_KEY, "add", "remove", "purchase", "refund"});
    private static final Pattern zzbrZ = Pattern.compile("dimension(\\d+)");
    private static final Pattern zzbsa = Pattern.compile("metric(\\d+)");
    private static Map<String, String> zzbsb;
    private static Map<String, String> zzbsc;
    private final DataLayer zzbnS;
    private final Set<String> zzbsd;
    private final zzdi zzbse;

    public zzdm(Context context, DataLayer dataLayer) {
        this(context, dataLayer, new zzdi(context));
    }

    zzdm(Context context, DataLayer dataLayer, zzdi zzdi) {
        super(f6507ID, new String[0]);
        this.zzbnS = dataLayer;
        this.zzbse = zzdi;
        this.zzbsd = new HashSet();
        this.zzbsd.add("");
        this.zzbsd.add("0");
        this.zzbsd.add("false");
    }

    private Double zzU(Object obj) {
        String str;
        String valueOf;
        if (obj instanceof String) {
            try {
                return Double.valueOf((String) obj);
            } catch (NumberFormatException e) {
                str = "Cannot convert the object to Double: ";
                valueOf = String.valueOf(e.getMessage());
                throw new RuntimeException(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            }
        } else if (obj instanceof Integer) {
            return Double.valueOf(((Integer) obj).doubleValue());
        } else {
            if (obj instanceof Double) {
                return (Double) obj;
            }
            str = "Cannot convert the object to Double: ";
            valueOf = String.valueOf(obj.toString());
            throw new RuntimeException(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
        }
    }

    private Integer zzV(Object obj) {
        String str;
        String valueOf;
        if (obj instanceof String) {
            try {
                return Integer.valueOf((String) obj);
            } catch (NumberFormatException e) {
                str = "Cannot convert the object to Integer: ";
                valueOf = String.valueOf(e.getMessage());
                throw new RuntimeException(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            }
        } else if (obj instanceof Double) {
            return Integer.valueOf(((Double) obj).intValue());
        } else {
            if (obj instanceof Integer) {
                return (Integer) obj;
            }
            str = "Cannot convert the object to Integer: ";
            valueOf = String.valueOf(obj.toString());
            throw new RuntimeException(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
        }
    }

    private void zza(Tracker tracker, Map<String, zza> map) {
        String zzgS = zzgS(AnalyticsArgs.TRANSACTION_ID);
        if (zzgS == null) {
            zzbn.m7501e("Cannot find transactionId in data layer.");
            return;
        }
        LinkedList<Map> linkedList = new LinkedList();
        try {
            Map zzm = zzm((zza) map.get(zzbrU));
            zzm.put("&t", "transaction");
            for (Entry entry : zzah(map).entrySet()) {
                zze(zzm, (String) entry.getValue(), zzgS((String) entry.getKey()));
            }
            linkedList.add(zzm);
            List<Map> zzgT = zzgT(AnalyticsArgs.TRANSACTION_PRODUCTS);
            if (zzgT != null) {
                for (Map map2 : zzgT) {
                    if (map2.get("name") == null) {
                        zzbn.m7501e("Unable to send transaction item hit due to missing 'name' field.");
                        return;
                    }
                    Map zzm2 = zzm((zza) map.get(zzbrU));
                    zzm2.put("&t", "item");
                    zzm2.put("&ti", zzgS);
                    for (Entry entry2 : zzai(map).entrySet()) {
                        zze(zzm2, (String) entry2.getValue(), (String) map2.get(entry2.getKey()));
                    }
                    linkedList.add(zzm2);
                }
            }
            for (Map map22 : linkedList) {
                tracker.send(map22);
            }
        } catch (IllegalArgumentException e) {
            zzbn.zzb("Unable to send transaction", e);
        }
    }

    private Promotion zzaf(Map<String, String> map) {
        Promotion promotion = new Promotion();
        String str = (String) map.get("id");
        if (str != null) {
            promotion.setId(String.valueOf(str));
        }
        str = (String) map.get("name");
        if (str != null) {
            promotion.setName(String.valueOf(str));
        }
        str = (String) map.get("creative");
        if (str != null) {
            promotion.setCreative(String.valueOf(str));
        }
        str = (String) map.get("position");
        if (str != null) {
            promotion.setPosition(String.valueOf(str));
        }
        return promotion;
    }

    private Product zzag(Map<String, Object> map) {
        String str;
        Product product = new Product();
        Object obj = map.get("id");
        if (obj != null) {
            product.setId(String.valueOf(obj));
        }
        obj = map.get("name");
        if (obj != null) {
            product.setName(String.valueOf(obj));
        }
        obj = map.get("brand");
        if (obj != null) {
            product.setBrand(String.valueOf(obj));
        }
        obj = map.get("category");
        if (obj != null) {
            product.setCategory(String.valueOf(obj));
        }
        obj = map.get("variant");
        if (obj != null) {
            product.setVariant(String.valueOf(obj));
        }
        obj = map.get("coupon");
        if (obj != null) {
            product.setCouponCode(String.valueOf(obj));
        }
        obj = map.get("position");
        if (obj != null) {
            product.setPosition(zzV(obj).intValue());
        }
        obj = map.get(AnalyticsArgs.TRANSACTION_ITEM_PRICE);
        if (obj != null) {
            product.setPrice(zzU(obj).doubleValue());
        }
        obj = map.get(AnalyticsArgs.TRANSACTION_ITEM_QUANTITY);
        if (obj != null) {
            product.setQuantity(zzV(obj).intValue());
        }
        for (String str2 : map.keySet()) {
            String str22;
            Matcher matcher = zzbrZ.matcher(str22);
            if (matcher.matches()) {
                try {
                    product.setCustomDimension(Integer.parseInt(matcher.group(1)), String.valueOf(map.get(str22)));
                } catch (NumberFormatException e) {
                    str = "illegal number in custom dimension value: ";
                    str22 = String.valueOf(str22);
                    zzbn.zzaW(str22.length() != 0 ? str.concat(str22) : new String(str));
                }
            } else {
                matcher = zzbsa.matcher(str22);
                if (matcher.matches()) {
                    try {
                        product.setCustomMetric(Integer.parseInt(matcher.group(1)), zzV(map.get(str22)).intValue());
                    } catch (NumberFormatException e2) {
                        str = "illegal number in custom metric value: ";
                        str22 = String.valueOf(str22);
                        zzbn.zzaW(str22.length() != 0 ? str.concat(str22) : new String(str));
                    }
                }
            }
        }
        return product;
    }

    private Map<String, String> zzah(Map<String, zza> map) {
        zza zza = (zza) map.get(zzbrW);
        if (zza != null) {
            return zzc(zza);
        }
        if (zzbsb == null) {
            HashMap hashMap = new HashMap();
            hashMap.put(AnalyticsArgs.TRANSACTION_ID, "&ti");
            hashMap.put("transactionAffiliation", "&ta");
            hashMap.put("transactionTax", "&tt");
            hashMap.put("transactionShipping", "&ts");
            hashMap.put(AnalyticsArgs.TRANSACTION_TOTAL, "&tr");
            hashMap.put("transactionCurrency", "&cu");
            zzbsb = hashMap;
        }
        return zzbsb;
    }

    private Map<String, String> zzai(Map<String, zza> map) {
        zza zza = (zza) map.get(zzbrX);
        if (zza != null) {
            return zzc(zza);
        }
        if (zzbsc == null) {
            HashMap hashMap = new HashMap();
            hashMap.put("name", "&in");
            hashMap.put(AnalyticsArgs.TRANSACTION_ITEM_SKU, "&ic");
            hashMap.put("category", "&iv");
            hashMap.put(AnalyticsArgs.TRANSACTION_ITEM_PRICE, "&ip");
            hashMap.put(AnalyticsArgs.TRANSACTION_ITEM_QUANTITY, "&iq");
            hashMap.put(AnalyticsArgs.TRANSACTION_ITEM_CURRENCY, "&cu");
            zzbsc = hashMap;
        }
        return zzbsc;
    }

    /* JADX WARNING: Removed duplicated region for block: B:55:0x0123  */
    private void zzb(com.google.android.gms.analytics.Tracker r8, java.util.Map<java.lang.String, com.google.android.gms.internal.zzag.zza> r9) {
        /*
        r7 = this;
        r1 = 0;
        r3 = new com.google.android.gms.analytics.HitBuilders$ScreenViewBuilder;
        r3.<init>();
        r0 = zzbrU;
        r0 = r9.get(r0);
        r0 = (com.google.android.gms.internal.zzag.zza) r0;
        r4 = r7.zzm(r0);
        r3.setAll(r4);
        r0 = zzbrS;
        r0 = r7.zzj(r9, r0);
        if (r0 == 0) goto L_0x008c;
    L_0x001d:
        r0 = r7.zzbnS;
        r2 = "ecommerce";
        r0 = r0.get(r2);
        r2 = r0 instanceof java.util.Map;
        if (r2 == 0) goto L_0x01d6;
    L_0x0029:
        r0 = (java.util.Map) r0;
    L_0x002b:
        r2 = r0;
    L_0x002c:
        if (r2 == 0) goto L_0x01a2;
    L_0x002e:
        r0 = "&cu";
        r0 = r4.get(r0);
        r0 = (java.lang.String) r0;
        if (r0 != 0) goto L_0x0040;
    L_0x0038:
        r0 = "currencyCode";
        r0 = r2.get(r0);
        r0 = (java.lang.String) r0;
    L_0x0040:
        if (r0 == 0) goto L_0x0047;
    L_0x0042:
        r4 = "&cu";
        r3.set(r4, r0);
    L_0x0047:
        r0 = "impressions";
        r0 = r2.get(r0);
        r4 = r0 instanceof java.util.List;
        if (r4 == 0) goto L_0x00a6;
    L_0x0051:
        r0 = (java.util.List) r0;
        r4 = r0.iterator();
    L_0x0057:
        r0 = r4.hasNext();
        if (r0 == 0) goto L_0x00a6;
    L_0x005d:
        r0 = r4.next();
        r0 = (java.util.Map) r0;
        r5 = r7.zzag(r0);	 Catch:{ RuntimeException -> 0x0073 }
        r6 = "list";
        r0 = r0.get(r6);	 Catch:{ RuntimeException -> 0x0073 }
        r0 = (java.lang.String) r0;	 Catch:{ RuntimeException -> 0x0073 }
        r3.addImpression(r5, r0);	 Catch:{ RuntimeException -> 0x0073 }
        goto L_0x0057;
    L_0x0073:
        r0 = move-exception;
        r5 = "Failed to extract a product from DataLayer. ";
        r0 = r0.getMessage();
        r0 = java.lang.String.valueOf(r0);
        r6 = r0.length();
        if (r6 == 0) goto L_0x00a0;
    L_0x0084:
        r0 = r5.concat(r0);
    L_0x0088:
        com.google.android.gms.tagmanager.zzbn.m7501e(r0);
        goto L_0x0057;
    L_0x008c:
        r0 = zzbrT;
        r0 = r9.get(r0);
        r0 = (com.google.android.gms.internal.zzag.zza) r0;
        r0 = com.google.android.gms.tagmanager.zzdl.zzl(r0);
        r2 = r0 instanceof java.util.Map;
        if (r2 == 0) goto L_0x01d3;
    L_0x009c:
        r0 = (java.util.Map) r0;
        r2 = r0;
        goto L_0x002c;
    L_0x00a0:
        r0 = new java.lang.String;
        r0.<init>(r5);
        goto L_0x0088;
    L_0x00a6:
        r0 = "promoClick";
        r0 = r2.containsKey(r0);
        if (r0 == 0) goto L_0x00f2;
    L_0x00ae:
        r0 = "promoClick";
        r0 = r2.get(r0);
        r0 = (java.util.Map) r0;
        r1 = "promotions";
        r0 = r0.get(r1);
        r0 = (java.util.List) r0;
    L_0x00be:
        r1 = 1;
        if (r0 == 0) goto L_0x0183;
    L_0x00c1:
        r4 = r0.iterator();
    L_0x00c5:
        r0 = r4.hasNext();
        if (r0 == 0) goto L_0x0111;
    L_0x00cb:
        r0 = r4.next();
        r0 = (java.util.Map) r0;
        r0 = r7.zzaf(r0);	 Catch:{ RuntimeException -> 0x00d9 }
        r3.addPromotion(r0);	 Catch:{ RuntimeException -> 0x00d9 }
        goto L_0x00c5;
    L_0x00d9:
        r0 = move-exception;
        r5 = "Failed to extract a promotion from DataLayer. ";
        r0 = r0.getMessage();
        r0 = java.lang.String.valueOf(r0);
        r6 = r0.length();
        if (r6 == 0) goto L_0x010b;
    L_0x00ea:
        r0 = r5.concat(r0);
    L_0x00ee:
        com.google.android.gms.tagmanager.zzbn.m7501e(r0);
        goto L_0x00c5;
    L_0x00f2:
        r0 = "promoView";
        r0 = r2.containsKey(r0);
        if (r0 == 0) goto L_0x01d0;
    L_0x00fa:
        r0 = "promoView";
        r0 = r2.get(r0);
        r0 = (java.util.Map) r0;
        r1 = "promotions";
        r0 = r0.get(r1);
        r0 = (java.util.List) r0;
        goto L_0x00be;
    L_0x010b:
        r0 = new java.lang.String;
        r0.<init>(r5);
        goto L_0x00ee;
    L_0x0111:
        r0 = "promoClick";
        r0 = r2.containsKey(r0);
        if (r0 == 0) goto L_0x017c;
    L_0x0119:
        r0 = "&promoa";
        r1 = "click";
        r3.set(r0, r1);
        r0 = 0;
    L_0x0121:
        if (r0 == 0) goto L_0x01a2;
    L_0x0123:
        r0 = zzbrY;
        r1 = r0.iterator();
    L_0x0129:
        r0 = r1.hasNext();
        if (r0 == 0) goto L_0x01a2;
    L_0x012f:
        r0 = r1.next();
        r0 = (java.lang.String) r0;
        r4 = r2.containsKey(r0);
        if (r4 == 0) goto L_0x0129;
    L_0x013b:
        r1 = r2.get(r0);
        r1 = (java.util.Map) r1;
        r2 = "products";
        r2 = r1.get(r2);
        r2 = (java.util.List) r2;
        if (r2 == 0) goto L_0x018b;
    L_0x014b:
        r4 = r2.iterator();
    L_0x014f:
        r2 = r4.hasNext();
        if (r2 == 0) goto L_0x018b;
    L_0x0155:
        r2 = r4.next();
        r2 = (java.util.Map) r2;
        r2 = r7.zzag(r2);	 Catch:{ RuntimeException -> 0x0163 }
        r3.addProduct(r2);	 Catch:{ RuntimeException -> 0x0163 }
        goto L_0x014f;
    L_0x0163:
        r2 = move-exception;
        r5 = "Failed to extract a product from DataLayer. ";
        r2 = r2.getMessage();
        r2 = java.lang.String.valueOf(r2);
        r6 = r2.length();
        if (r6 == 0) goto L_0x0185;
    L_0x0174:
        r2 = r5.concat(r2);
    L_0x0178:
        com.google.android.gms.tagmanager.zzbn.m7501e(r2);
        goto L_0x014f;
    L_0x017c:
        r0 = "&promoa";
        r4 = "view";
        r3.set(r0, r4);
    L_0x0183:
        r0 = r1;
        goto L_0x0121;
    L_0x0185:
        r2 = new java.lang.String;
        r2.<init>(r5);
        goto L_0x0178;
    L_0x018b:
        r2 = "actionField";
        r2 = r1.containsKey(r2);	 Catch:{ RuntimeException -> 0x01b1 }
        if (r2 == 0) goto L_0x01aa;
    L_0x0193:
        r2 = "actionField";
        r1 = r1.get(r2);	 Catch:{ RuntimeException -> 0x01b1 }
        r1 = (java.util.Map) r1;	 Catch:{ RuntimeException -> 0x01b1 }
        r0 = r7.zzg(r0, r1);	 Catch:{ RuntimeException -> 0x01b1 }
    L_0x019f:
        r3.setProductAction(r0);	 Catch:{ RuntimeException -> 0x01b1 }
    L_0x01a2:
        r0 = r3.build();
        r8.send(r0);
        return;
    L_0x01aa:
        r1 = new com.google.android.gms.analytics.ecommerce.ProductAction;	 Catch:{ RuntimeException -> 0x01b1 }
        r1.<init>(r0);	 Catch:{ RuntimeException -> 0x01b1 }
        r0 = r1;
        goto L_0x019f;
    L_0x01b1:
        r0 = move-exception;
        r1 = "Failed to extract a product action from DataLayer. ";
        r0 = r0.getMessage();
        r0 = java.lang.String.valueOf(r0);
        r2 = r0.length();
        if (r2 == 0) goto L_0x01ca;
    L_0x01c2:
        r0 = r1.concat(r0);
    L_0x01c6:
        com.google.android.gms.tagmanager.zzbn.m7501e(r0);
        goto L_0x01a2;
    L_0x01ca:
        r0 = new java.lang.String;
        r0.<init>(r1);
        goto L_0x01c6;
    L_0x01d0:
        r0 = r1;
        goto L_0x00be;
    L_0x01d3:
        r2 = r1;
        goto L_0x002c;
    L_0x01d6:
        r0 = r1;
        goto L_0x002b;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzdm.zzb(com.google.android.gms.analytics.Tracker, java.util.Map):void");
    }

    private Map<String, String> zzc(zza zza) {
        Object zzl = zzdl.zzl(zza);
        if (!(zzl instanceof Map)) {
            return null;
        }
        Map map = (Map) zzl;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Entry entry : map.entrySet()) {
            linkedHashMap.put(entry.getKey().toString(), entry.getValue().toString());
        }
        return linkedHashMap;
    }

    private void zze(Map<String, String> map, String str, String str2) {
        if (str2 != null) {
            map.put(str, str2);
        }
    }

    private ProductAction zzg(String str, Map<String, Object> map) {
        ProductAction productAction = new ProductAction(str);
        Object obj = map.get("id");
        if (obj != null) {
            productAction.setTransactionId(String.valueOf(obj));
        }
        obj = map.get("affiliation");
        if (obj != null) {
            productAction.setTransactionAffiliation(String.valueOf(obj));
        }
        obj = map.get("coupon");
        if (obj != null) {
            productAction.setTransactionCouponCode(String.valueOf(obj));
        }
        obj = map.get(Methods.LIST);
        if (obj != null) {
            productAction.setProductActionList(String.valueOf(obj));
        }
        obj = map.get("option");
        if (obj != null) {
            productAction.setCheckoutOptions(String.valueOf(obj));
        }
        obj = map.get(AnalyticsArgs.TRANSACTION_REVENUE);
        if (obj != null) {
            productAction.setTransactionRevenue(zzU(obj).doubleValue());
        }
        obj = map.get("tax");
        if (obj != null) {
            productAction.setTransactionTax(zzU(obj).doubleValue());
        }
        obj = map.get("shipping");
        if (obj != null) {
            productAction.setTransactionShipping(zzU(obj).doubleValue());
        }
        obj = map.get("step");
        if (obj != null) {
            productAction.setCheckoutStep(zzV(obj).intValue());
        }
        return productAction;
    }

    private String zzgS(String str) {
        Object obj = this.zzbnS.get(str);
        return obj == null ? null : obj.toString();
    }

    private List<Map<String, String>> zzgT(String str) {
        Object obj = this.zzbnS.get(str);
        if (obj == null) {
            return null;
        }
        if (obj instanceof List) {
            for (Object obj2 : (List) obj) {
                if (!(obj2 instanceof Map)) {
                    throw new IllegalArgumentException("Each element of transactionProducts should be of type Map.");
                }
            }
            return (List) obj;
        }
        throw new IllegalArgumentException("transactionProducts should be of type List.");
    }

    private boolean zzj(Map<String, zza> map, String str) {
        zza zza = (zza) map.get(str);
        return zza == null ? false : zzdl.zzk(zza).booleanValue();
    }

    private Map<String, String> zzm(zza zza) {
        if (zza == null) {
            return new HashMap();
        }
        Map<String, String> zzc = zzc(zza);
        if (zzc == null) {
            return new HashMap();
        }
        String str = (String) zzc.get("&aip");
        if (str != null && this.zzbsd.contains(str.toLowerCase())) {
            zzc.remove("&aip");
        }
        return zzc;
    }

    public /* bridge */ /* synthetic */ String zzJO() {
        return super.zzJO();
    }

    public /* bridge */ /* synthetic */ Set zzJP() {
        return super.zzJP();
    }

    public /* bridge */ /* synthetic */ boolean zzJf() {
        return super.zzJf();
    }

    public void zzX(Map<String, zza> map) {
        Tracker zzgK = this.zzbse.zzgK("_GTM_DEFAULT_TRACKER_");
        zzgK.enableAdvertisingIdCollection(zzj(map, "collect_adid"));
        if (zzj(map, zzbrR)) {
            zzb(zzgK, map);
        } else if (zzj(map, zzbrQ)) {
            zzgK.send(zzm((zza) map.get(zzbrU)));
        } else if (zzj(map, zzbrV)) {
            zza(zzgK, map);
        } else {
            zzbn.zzaW("Ignoring unknown tag.");
        }
    }
}
