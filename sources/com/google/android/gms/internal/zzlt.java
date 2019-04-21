package com.google.android.gms.internal;

import com.google.android.gms.analytics.ecommerce.Product;
import com.google.android.gms.analytics.ecommerce.ProductAction;
import com.google.android.gms.analytics.ecommerce.Promotion;
import com.google.android.gms.analytics.zzg;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public final class zzlt extends zzg<zzlt> {
    private ProductAction zzUf;
    private final Map<String, List<Product>> zzUg = new HashMap();
    private final List<Promotion> zzUh = new ArrayList();
    private final List<Product> zzUi = new ArrayList();

    public String toString() {
        HashMap hashMap = new HashMap();
        if (!this.zzUi.isEmpty()) {
            hashMap.put(com.mcdonalds.sdk.modules.models.Product.TABLE_NAME, this.zzUi);
        }
        if (!this.zzUh.isEmpty()) {
            hashMap.put(com.mcdonalds.sdk.modules.models.Promotion.TABLE_NAME, this.zzUh);
        }
        if (!this.zzUg.isEmpty()) {
            hashMap.put("impressions", this.zzUg);
        }
        hashMap.put("productAction", this.zzUf);
        return zzg.zzj(hashMap);
    }

    public void zza(Product product, String str) {
        if (product != null) {
            Object str2;
            if (str2 == null) {
                str2 = "";
            }
            if (!this.zzUg.containsKey(str2)) {
                this.zzUg.put(str2, new ArrayList());
            }
            ((List) this.zzUg.get(str2)).add(product);
        }
    }

    /* renamed from: zza */
    public void zzb(zzlt zzlt) {
        zzlt.zzUi.addAll(this.zzUi);
        zzlt.zzUh.addAll(this.zzUh);
        for (Entry entry : this.zzUg.entrySet()) {
            String str = (String) entry.getKey();
            for (Product zza : (List) entry.getValue()) {
                zzlt.zza(zza, str);
            }
        }
        if (this.zzUf != null) {
            zzlt.zzUf = this.zzUf;
        }
    }

    public ProductAction zzll() {
        return this.zzUf;
    }

    public List<Product> zzlm() {
        return Collections.unmodifiableList(this.zzUi);
    }

    public Map<String, List<Product>> zzln() {
        return this.zzUg;
    }

    public List<Promotion> zzlo() {
        return Collections.unmodifiableList(this.zzUh);
    }
}
