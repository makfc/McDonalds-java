package com.mcdonalds.app.account;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import com.ensighten.Ensighten;

public class AuthenticatorService extends Service {
    @Nullable
    public IBinder onBind(Intent intent) {
        Ensighten.evaluateEvent(this, "onBind", new Object[]{intent});
        return new Authenticator(this).getIBinder();
    }
}
