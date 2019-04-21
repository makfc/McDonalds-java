package com.crashlytics.android;

import com.crashlytics.android.answers.Answers;
import com.crashlytics.android.beta.Beta;
import com.crashlytics.android.core.CrashlyticsCore;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import p041io.fabric.sdk.android.Kit;
import p041io.fabric.sdk.android.KitGroup;

public class Crashlytics extends Kit<Void> implements KitGroup {
    public final Answers answers;
    public final Beta beta;
    public final CrashlyticsCore core;
    public final Collection<? extends Kit> kits;

    public static class Builder {
    }

    public Crashlytics() {
        this(new Answers(), new Beta(), new CrashlyticsCore());
    }

    Crashlytics(Answers answers, Beta beta, CrashlyticsCore core) {
        this.answers = answers;
        this.beta = beta;
        this.core = core;
        this.kits = Collections.unmodifiableCollection(Arrays.asList(new Kit[]{answers, beta, core}));
    }

    public String getVersion() {
        return "2.9.9.32";
    }

    public String getIdentifier() {
        return "com.crashlytics.sdk.android:crashlytics";
    }

    public Collection<? extends Kit> getKits() {
        String googlePlaySdkVersionString = "!SDK-VERSION-STRING!:com.crashlytics.sdk.android:crashlytics:2.9.9.32";
        return this.kits;
    }

    /* Access modifiers changed, original: protected */
    public Void doInBackground() {
        return null;
    }
}
