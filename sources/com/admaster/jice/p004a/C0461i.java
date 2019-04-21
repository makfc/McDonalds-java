package com.admaster.jice.p004a;

/* compiled from: JiceCore */
/* renamed from: com.admaster.jice.a.i */
public enum C0461i {
    JICEACTIVE("activate"),
    JICEAPPSTART("appstart"),
    JICESHOWPUSH("showpush"),
    JICECLICKPUSH("clickpush");
    
    private String value;

    private C0461i(String str) {
        this.value = str;
    }

    public String toString() {
        return String.valueOf(this.value);
    }
}
