package com.google.firebase.iid;

public class zzc {
    private final FirebaseInstanceId zzbSB;

    private zzc(FirebaseInstanceId firebaseInstanceId) {
        this.zzbSB = firebaseInstanceId;
    }

    public static zzc zzUl() {
        return new zzc(FirebaseInstanceId.getInstance());
    }

    public String getId() {
        return this.zzbSB.getId();
    }
}
