package com.admaster.jice.p004a;

/* compiled from: StoreManager */
/* renamed from: com.admaster.jice.a.y */
public enum C0472y {
    UNACTIVE(0),
    ACTIVING(1),
    ACTIVED(2);
    
    private int value;

    private C0472y(int i) {
        this.value = 0;
        this.value = i;
    }

    public static C0472y valueOf(int i) {
        switch (i) {
            case 0:
                return UNACTIVE;
            case 1:
                return ACTIVING;
            case 2:
                return ACTIVED;
            default:
                return UNACTIVE;
        }
    }

    public int value() {
        return this.value;
    }
}
