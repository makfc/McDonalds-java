package com.kochava.base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.AnyThread;
import android.support.annotation.CheckResult;
import android.support.annotation.Keep;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.Size;
import com.facebook.Response;
import com.facebook.internal.AnalyticsEvents;
import com.kochava.base.C2900c.C2897a;
import com.mcdonalds.sdk.services.analytics.AnalyticsArgs;
import com.mcdonalds.sdk.services.analytics.tagmanager.Parameters;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.util.Date;
import java.util.Map;
import org.jetbrains.annotations.Contract;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class Tracker {
    public static final int EVENT_TYPE_ACHIEVEMENT = 1;
    public static final int EVENT_TYPE_ADD_TO_CART = 2;
    public static final int EVENT_TYPE_ADD_TO_WISH_LIST = 3;
    public static final int EVENT_TYPE_AD_VIEW = 12;
    public static final int EVENT_TYPE_CHECKOUT_START = 4;
    public static final int EVENT_TYPE_CONSENT_GRANTED = 15;
    public static final int EVENT_TYPE_LEVEL_COMPLETE = 5;
    public static final int EVENT_TYPE_PURCHASE = 6;
    public static final int EVENT_TYPE_PUSH_OPENED = 14;
    public static final int EVENT_TYPE_PUSH_RECEIVED = 13;
    public static final int EVENT_TYPE_RATING = 7;
    public static final int EVENT_TYPE_REGISTRATION_COMPLETE = 8;
    public static final int EVENT_TYPE_SEARCH = 9;
    public static final int EVENT_TYPE_TUTORIAL_COMPLETE = 10;
    public static final int EVENT_TYPE_VIEW = 11;
    public static final int LOG_LEVEL_DEBUG = 4;
    public static final int LOG_LEVEL_ERROR = 1;
    public static final int LOG_LEVEL_INFO = 3;
    public static final int LOG_LEVEL_NONE = 0;
    public static final int LOG_LEVEL_TRACE = 5;
    public static final int LOG_LEVEL_WARN = 2;
    @NonNull
    /* renamed from: a */
    private static final Tracker f6545a = new Tracker();
    @Nullable
    /* renamed from: b */
    private String f6546b = null;
    @Nullable
    /* renamed from: c */
    private String f6547c = null;
    /* renamed from: d */
    private transient int f6548d = 5;
    @Nullable
    /* renamed from: e */
    private transient LogListener f6549e = null;
    /* renamed from: f */
    private transient int f6550f = 0;
    @Nullable
    /* renamed from: g */
    private C2895a f6551g = null;

    @AnyThread
    public static class Configuration {
        @NonNull
        /* renamed from: a */
        private final Context f6042a;
        @Nullable
        /* renamed from: b */
        private String f6043b = null;
        @Nullable
        /* renamed from: c */
        private String f6044c = null;
        @Nullable
        /* renamed from: d */
        private Integer f6045d = null;
        @Nullable
        /* renamed from: e */
        private AttributionListener f6046e = null;
        @Nullable
        /* renamed from: f */
        private AttributionUpdateListener f6047f = null;
        @Nullable
        /* renamed from: g */
        private ConsentStatusChangeListener f6048g = null;
        /* renamed from: h */
        private boolean f6049h = false;
        @Nullable
        /* renamed from: i */
        private Boolean f6050i = null;
        @Nullable
        /* renamed from: j */
        private IdentityLink f6051j = null;
        @Nullable
        /* renamed from: k */
        private JSONObject f6052k = null;
        @Keep
        final String sdkVersionDeclaration = "!SDK-VERSION-STRING!:com.kochava:tracker:release:3.4.0";

        public Configuration(@NonNull Context context) {
            this.f6042a = context;
        }

        @CheckResult
        @NonNull
        public final Configuration addCustom(@Size @NonNull String str, @Size @NonNull String str2) {
            if (this.f6052k == null) {
                this.f6052k = new JSONObject();
            }
            C2901d.m7636a(str, (Object) str2, this.f6052k);
            return this;
        }

        @CheckResult
        @NonNull
        public final Configuration addCustom(@NonNull JSONObject jSONObject) {
            if (jSONObject != null && jSONObject.length() > 0) {
                if (this.f6052k == null) {
                    this.f6052k = new JSONObject();
                }
                C2901d.m7651b(this.f6052k, jSONObject);
            }
            return this;
        }

        @CheckResult
        @NonNull
        public final Configuration setAppGuid(@Size @NonNull String str) {
            this.f6043b = str;
            return this;
        }

        @CheckResult
        @NonNull
        public final Configuration setAppLimitAdTracking(boolean z) {
            this.f6050i = Boolean.valueOf(z);
            return this;
        }

        @Deprecated
        @CheckResult
        @NonNull
        public final Configuration setAttributionListener(@NonNull AttributionListener attributionListener) {
            this.f6046e = attributionListener;
            return this;
        }

        @CheckResult
        @NonNull
        public final Configuration setAttributionUpdateListener(@NonNull AttributionUpdateListener attributionUpdateListener) {
            this.f6047f = attributionUpdateListener;
            return this;
        }

        @CheckResult
        @NonNull
        public final Configuration setConsentStatusChangeListener(@NonNull ConsentStatusChangeListener consentStatusChangeListener) {
            this.f6048g = consentStatusChangeListener;
            return this;
        }

        @CheckResult
        @NonNull
        public final Configuration setIdentityLink(@NonNull IdentityLink identityLink) {
            this.f6051j = identityLink;
            return this;
        }

        @CheckResult
        @NonNull
        public final Configuration setIntelligentConsentManagement(boolean z) {
            this.f6049h = z;
            return this;
        }

        @CheckResult
        @NonNull
        public final Configuration setLogLevel(int i) {
            this.f6045d = Integer.valueOf(i);
            return this;
        }

        @CheckResult
        @NonNull
        public final Configuration setPartnerName(@Size @NonNull String str) {
            this.f6044c = str;
            return this;
        }
    }

    @AnyThread
    public static class ConsentPartner implements Parcelable {
        public static final Creator<ConsentPartner> CREATOR = new C28881();
        @NonNull
        public static String KEY_DESCRIPTION = "description";
        @NonNull
        public static String KEY_GRANTED = "granted";
        @NonNull
        public static String KEY_NAME = "name";
        @NonNull
        public static String KEY_PARTNERS = "partners";
        @NonNull
        public static String KEY_RESPONSE_TIME = "response_time";
        @NonNull
        public final String description;
        public final boolean granted;
        @NonNull
        public final String name;
        public final long responseTime;

        /* renamed from: com.kochava.base.Tracker$ConsentPartner$1 */
        static class C28881 implements Creator<ConsentPartner> {
            C28881() {
            }

            /* renamed from: a */
            public ConsentPartner createFromParcel(Parcel parcel) {
                return new ConsentPartner(parcel);
            }

            /* renamed from: a */
            public ConsentPartner[] newArray(int i) {
                return new ConsentPartner[i];
            }
        }

        protected ConsentPartner(@NonNull Parcel parcel) {
            this.name = parcel.readString();
            this.description = parcel.readString();
            this.granted = parcel.readByte() != (byte) 0;
            this.responseTime = parcel.readLong();
        }

        ConsentPartner(@NonNull JSONObject jSONObject) {
            this.name = C2901d.m7629a(jSONObject.opt(KEY_NAME), "");
            this.description = C2901d.m7629a(jSONObject.opt(KEY_DESCRIPTION), "");
            this.responseTime = C2901d.m7627a(jSONObject.opt(KEY_RESPONSE_TIME), 0);
            this.granted = C2901d.m7644a(jSONObject.opt(KEY_GRANTED), false);
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(@NonNull Parcel parcel, int i) {
            parcel.writeString(this.name);
            parcel.writeString(this.description);
            parcel.writeByte(this.granted ? (byte) 1 : (byte) 0);
            parcel.writeLong(this.responseTime);
        }
    }

    @AnyThread
    public static class Event implements Parcelable {
        @NonNull
        public static final Creator<Event> CREATOR = new C28891();
        @NonNull
        /* renamed from: a */
        final JSONObject f6538a = new JSONObject();
        @NonNull
        /* renamed from: b */
        final String f6539b;
        /* renamed from: c */
        long f6540c = -1;
        /* renamed from: d */
        boolean f6541d = false;
        @Nullable
        /* renamed from: e */
        String f6542e = null;
        @Nullable
        /* renamed from: f */
        String f6543f = null;

        /* renamed from: com.kochava.base.Tracker$Event$1 */
        static class C28891 implements Creator<Event> {
            C28891() {
            }

            @NonNull
            /* renamed from: a */
            public final Event createFromParcel(@NonNull Parcel parcel) {
                return new Event(parcel);
            }

            @Contract(pure = true)
            @NonNull
            /* renamed from: a */
            public final Event[] newArray(int i) {
                return new Event[i];
            }
        }

        public Event(int i) {
            switch (i) {
                case 1:
                    this.f6539b = "Achievement";
                    break;
                case 2:
                    this.f6539b = "Add to Cart";
                    break;
                case 3:
                    this.f6539b = "Add to Wish List";
                    break;
                case 4:
                    this.f6539b = "Checkout Start";
                    break;
                case 5:
                    this.f6539b = "Level Complete";
                    break;
                case 6:
                    this.f6539b = "Purchase";
                    break;
                case 7:
                    this.f6539b = "Rating";
                    break;
                case 8:
                    this.f6539b = "Registration Complete";
                    break;
                case 9:
                    this.f6539b = "Search";
                    break;
                case 10:
                    this.f6539b = "Tutorial Complete";
                    break;
                case 11:
                    this.f6539b = "View";
                    break;
                case 12:
                    this.f6539b = "Ad View";
                    break;
                case 13:
                    this.f6539b = "Push Received";
                    break;
                case 14:
                    this.f6539b = "Push Opened";
                    break;
                case 15:
                    this.f6539b = "Consent Granted";
                    break;
                default:
                    this.f6539b = "";
                    break;
            }
            this.f6540c = C2901d.m7626a();
        }

        protected Event(@NonNull Parcel parcel) {
            JSONObject init;
            boolean z = true;
            try {
                init = JSONObjectInstrumentation.init(parcel.readString());
            } catch (JSONException e) {
                Tracker.m7517a(2, "EVT", "Event", e);
                init = null;
            }
            if (init != null) {
                C2901d.m7651b(this.f6538a, init);
            }
            this.f6539b = parcel.readString();
            this.f6540c = parcel.readLong();
            if (parcel.readByte() == (byte) 0) {
                z = false;
            }
            this.f6541d = z;
            this.f6542e = parcel.readString();
            this.f6543f = parcel.readString();
        }

        public Event(@Size @NonNull String str) {
            if (str == null) {
                str = "";
            }
            this.f6539b = str;
            this.f6540c = C2901d.m7626a();
        }

        @CheckResult
        @NonNull
        public final Event addCustom(@Size @NonNull String str, double d) {
            C2901d.m7636a(str, Double.valueOf(d), this.f6538a);
            return this;
        }

        @CheckResult
        @NonNull
        public final Event addCustom(@Size @NonNull String str, long j) {
            C2901d.m7636a(str, Long.valueOf(j), this.f6538a);
            return this;
        }

        @CheckResult
        @NonNull
        public final Event addCustom(@Size @NonNull String str, @NonNull String str2) {
            C2901d.m7636a(str, (Object) str2, this.f6538a);
            return this;
        }

        @CheckResult
        @NonNull
        public final Event addCustom(@Size @NonNull String str, @NonNull Date date) {
            C2901d.m7636a(str, (Object) date, this.f6538a);
            return this;
        }

        @CheckResult
        @NonNull
        public final Event addCustom(@Size @NonNull String str, boolean z) {
            C2901d.m7636a(str, Boolean.valueOf(z), this.f6538a);
            return this;
        }

        @CheckResult
        @NonNull
        public final Event addCustom(@Size @NonNull JSONObject jSONObject) {
            if (jSONObject == null || jSONObject.length() < 1) {
                Tracker.m7517a(2, "EVT", "addCustom", "Invalid keyValue object");
            } else {
                C2901d.m7651b(this.f6538a, jSONObject);
            }
            return this;
        }

        @Contract(pure = true)
        public final int describeContents() {
            return 0;
        }

        /* Access modifiers changed, original: final */
        @CheckResult
        @NonNull
        public final Event enableDurationTracking() {
            this.f6541d = true;
            return this;
        }

        @CheckResult
        @NonNull
        public final Event setAction(@NonNull String str) {
            C2901d.m7636a(Parameters.ACTION, (Object) str, this.f6538a);
            return this;
        }

        @CheckResult
        @NonNull
        public final Event setAdCampaignId(@NonNull String str) {
            C2901d.m7636a("ad_campaign_id", (Object) str, this.f6538a);
            return this;
        }

        @CheckResult
        @NonNull
        public final Event setAdCampaignName(@NonNull String str) {
            C2901d.m7636a("ad_campaign_name", (Object) str, this.f6538a);
            return this;
        }

        @CheckResult
        @NonNull
        public final Event setAdDeviceType(@NonNull String str) {
            C2901d.m7636a("device_type", (Object) str, this.f6538a);
            return this;
        }

        @CheckResult
        @NonNull
        public final Event setAdGroupId(@NonNull String str) {
            C2901d.m7636a("ad_group_id", (Object) str, this.f6538a);
            return this;
        }

        @CheckResult
        @NonNull
        public final Event setAdGroupName(@NonNull String str) {
            C2901d.m7636a("ad_group_name", (Object) str, this.f6538a);
            return this;
        }

        @CheckResult
        @NonNull
        public final Event setAdMediationName(@NonNull String str) {
            C2901d.m7636a("ad_mediation_name", (Object) str, this.f6538a);
            return this;
        }

        @CheckResult
        @NonNull
        public final Event setAdNetworkName(@NonNull String str) {
            C2901d.m7636a("ad_network_name", (Object) str, this.f6538a);
            return this;
        }

        @CheckResult
        @NonNull
        public final Event setAdPlacement(@NonNull String str) {
            C2901d.m7636a("placement", (Object) str, this.f6538a);
            return this;
        }

        @CheckResult
        @NonNull
        public final Event setAdSize(@NonNull String str) {
            C2901d.m7636a("ad_size", (Object) str, this.f6538a);
            return this;
        }

        @CheckResult
        @NonNull
        public final Event setAdType(@NonNull String str) {
            C2901d.m7636a("ad_type", (Object) str, this.f6538a);
            return this;
        }

        @CheckResult
        @NonNull
        public final Event setBackground(boolean z) {
            C2901d.m7636a("background", Boolean.valueOf(z), this.f6538a);
            return this;
        }

        @CheckResult
        @NonNull
        public final Event setCheckoutAsGuest(@NonNull String str) {
            C2901d.m7636a("checkout_as_guest", (Object) str, this.f6538a);
            return this;
        }

        @CheckResult
        @NonNull
        public final Event setCompleted(boolean z) {
            C2901d.m7636a("completed", Boolean.valueOf(z), this.f6538a);
            return this;
        }

        @CheckResult
        @NonNull
        public final Event setContentId(@NonNull String str) {
            C2901d.m7636a("content_id", (Object) str, this.f6538a);
            return this;
        }

        @CheckResult
        @NonNull
        public final Event setContentType(@NonNull String str) {
            C2901d.m7636a("content_type", (Object) str, this.f6538a);
            return this;
        }

        @CheckResult
        @NonNull
        public final Event setCurrency(@NonNull String str) {
            C2901d.m7636a(AnalyticsArgs.TRANSACTION_ITEM_CURRENCY, (Object) str, this.f6538a);
            return this;
        }

        @CheckResult
        @NonNull
        public final Event setDate(@NonNull String str) {
            C2901d.m7636a("date", (Object) str, this.f6538a);
            return this;
        }

        @CheckResult
        @NonNull
        public final Event setDate(@NonNull Date date) {
            C2901d.m7636a("date", (Object) date, this.f6538a);
            return this;
        }

        @CheckResult
        @NonNull
        public final Event setDescription(@NonNull String str) {
            C2901d.m7636a("description", (Object) str, this.f6538a);
            return this;
        }

        @CheckResult
        @NonNull
        public final Event setDestination(@NonNull String str) {
            C2901d.m7636a("destination", (Object) str, this.f6538a);
            return this;
        }

        @CheckResult
        @NonNull
        public final Event setDuration(double d) {
            C2901d.m7636a(InstallReferrer.KEY_DURATION, Double.valueOf(d), this.f6538a);
            return this;
        }

        @CheckResult
        @NonNull
        public final Event setEndDate(@NonNull String str) {
            C2901d.m7636a("end_date", (Object) str, this.f6538a);
            return this;
        }

        @CheckResult
        @NonNull
        public final Event setEndDate(@NonNull Date date) {
            C2901d.m7636a("end_date", (Object) date, this.f6538a);
            return this;
        }

        @CheckResult
        @NonNull
        public final Event setGooglePlayReceipt(@Size @NonNull String str, @Size @NonNull String str2) {
            if (str == null || str2 == null || str.trim().isEmpty() || str2.trim().isEmpty()) {
                Tracker.m7517a(2, "EVT", "setGooglePlay", "Invalid Input");
            } else {
                this.f6542e = str;
                this.f6543f = str2;
            }
            return this;
        }

        @CheckResult
        @NonNull
        public final Event setItemAddedFrom(@NonNull String str) {
            C2901d.m7636a("item_added_from", (Object) str, this.f6538a);
            return this;
        }

        @CheckResult
        @NonNull
        public final Event setLevel(@NonNull String str) {
            C2901d.m7636a("level", (Object) str, this.f6538a);
            return this;
        }

        @CheckResult
        @NonNull
        public final Event setMaxRatingValue(double d) {
            C2901d.m7636a("max_rating_value", Double.valueOf(d), this.f6538a);
            return this;
        }

        @CheckResult
        @NonNull
        public final Event setName(@NonNull String str) {
            C2901d.m7636a("name", (Object) str, this.f6538a);
            return this;
        }

        @CheckResult
        @NonNull
        public final Event setOrderId(@NonNull String str) {
            C2901d.m7636a("order_id", (Object) str, this.f6538a);
            return this;
        }

        @CheckResult
        @NonNull
        public final Event setOrigin(@NonNull String str) {
            C2901d.m7636a("origin", (Object) str, this.f6538a);
            return this;
        }

        @CheckResult
        @NonNull
        public final Event setPayload(@NonNull Bundle bundle) {
            C2901d.m7636a("payload", C2901d.m7661f(bundle), this.f6538a);
            return this;
        }

        @CheckResult
        @NonNull
        public final Event setPayload(@NonNull JSONObject jSONObject) {
            C2901d.m7636a("payload", (Object) jSONObject, this.f6538a);
            return this;
        }

        @CheckResult
        @NonNull
        public final Event setPrice(double d) {
            C2901d.m7636a(AnalyticsArgs.TRANSACTION_ITEM_PRICE, Double.valueOf(d), this.f6538a);
            return this;
        }

        @CheckResult
        @NonNull
        public final Event setQuantity(double d) {
            C2901d.m7636a(AnalyticsArgs.TRANSACTION_ITEM_QUANTITY, Double.valueOf(d), this.f6538a);
            return this;
        }

        @CheckResult
        @NonNull
        public final Event setRatingValue(double d) {
            C2901d.m7636a("rating_value", Double.valueOf(d), this.f6538a);
            return this;
        }

        @CheckResult
        @NonNull
        public final Event setReceiptId(@NonNull String str) {
            C2901d.m7636a("receipt_id", (Object) str, this.f6538a);
            return this;
        }

        @CheckResult
        @NonNull
        public final Event setReferralFrom(@NonNull String str) {
            C2901d.m7636a("referral_from", (Object) str, this.f6538a);
            return this;
        }

        @CheckResult
        @NonNull
        public final Event setRegistrationMethod(@NonNull String str) {
            C2901d.m7636a("registration_method", (Object) str, this.f6538a);
            return this;
        }

        @CheckResult
        @NonNull
        public final Event setResults(@NonNull String str) {
            C2901d.m7636a("results", (Object) str, this.f6538a);
            return this;
        }

        @CheckResult
        @NonNull
        public final Event setScore(@NonNull String str) {
            C2901d.m7636a("score", (Object) str, this.f6538a);
            return this;
        }

        @CheckResult
        @NonNull
        public final Event setSearchTerm(@NonNull String str) {
            C2901d.m7636a("search_term", (Object) str, this.f6538a);
            return this;
        }

        @CheckResult
        @NonNull
        public final Event setSpatialX(double d) {
            C2901d.m7636a("spatial_x", Double.valueOf(d), this.f6538a);
            return this;
        }

        @CheckResult
        @NonNull
        public final Event setSpatialY(double d) {
            C2901d.m7636a("spatial_y", Double.valueOf(d), this.f6538a);
            return this;
        }

        @CheckResult
        @NonNull
        public final Event setSpatialZ(double d) {
            C2901d.m7636a("spatial_z", Double.valueOf(d), this.f6538a);
            return this;
        }

        @CheckResult
        @NonNull
        public final Event setStartDate(@NonNull String str) {
            C2901d.m7636a("start_date", (Object) str, this.f6538a);
            return this;
        }

        @CheckResult
        @NonNull
        public final Event setStartDate(@NonNull Date date) {
            C2901d.m7636a("start_date", (Object) date, this.f6538a);
            return this;
        }

        @CheckResult
        @NonNull
        public final Event setSuccess(@NonNull String str) {
            C2901d.m7636a(Response.SUCCESS_KEY, (Object) str, this.f6538a);
            return this;
        }

        @CheckResult
        @NonNull
        public final Event setUserId(@NonNull String str) {
            C2901d.m7636a("user_id", (Object) str, this.f6538a);
            return this;
        }

        @CheckResult
        @NonNull
        public final Event setUserName(@NonNull String str) {
            C2901d.m7636a("user_name", (Object) str, this.f6538a);
            return this;
        }

        @CheckResult
        @NonNull
        public final Event setValidated(@NonNull String str) {
            C2901d.m7636a("validated", (Object) str, this.f6538a);
            return this;
        }

        public final void writeToParcel(@NonNull Parcel parcel, int i) {
            parcel.writeString(C2901d.m7632a(this.f6538a));
            parcel.writeString(this.f6539b);
            parcel.writeLong(this.f6540c);
            parcel.writeByte(this.f6541d ? (byte) 1 : (byte) 0);
            parcel.writeString(this.f6542e);
            parcel.writeString(this.f6543f);
        }
    }

    @AnyThread
    public static class IdentityLink {
        @NonNull
        /* renamed from: a */
        final JSONObject f6544a = new JSONObject();

        @CheckResult
        @NonNull
        public final IdentityLink add(@Size @NonNull String str, @Size @NonNull String str2) {
            C2901d.m7636a(str, (Object) str2, this.f6544a);
            return this;
        }

        @CheckResult
        @NonNull
        public final IdentityLink add(@Size @NonNull Map<String, String> map) {
            JSONObject f = C2901d.m7661f(map);
            if (f == null || f.length() < 1) {
                Tracker.m7517a(2, "IDL", "add", "Invalid Input");
            } else {
                C2901d.m7651b(this.f6544a, f);
            }
            return this;
        }
    }

    private Tracker() {
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* JADX WARNING: Missing block: B:109:?, code skipped:
            return;
     */
    @android.support.annotation.AnyThread
    /* renamed from: a */
    static void m7517a(int r10, @android.support.annotation.Size @android.support.annotation.NonNull java.lang.String r11, @android.support.annotation.Size @android.support.annotation.NonNull java.lang.String r12, @android.support.annotation.Nullable java.lang.Object... r13) {
        /*
        r3 = 0;
        r6 = f6545a;
        monitor-enter(r6);
        if (r10 == 0) goto L_0x001e;
    L_0x0006:
        r1 = f6545a;	 Catch:{ all -> 0x00ac }
        r1 = r1.f6548d;	 Catch:{ all -> 0x00ac }
        if (r1 == 0) goto L_0x0012;
    L_0x000c:
        r1 = f6545a;	 Catch:{ all -> 0x00ac }
        r1 = r1.f6548d;	 Catch:{ all -> 0x00ac }
        if (r1 >= r10) goto L_0x0020;
    L_0x0012:
        r1 = f6545a;	 Catch:{ all -> 0x00ac }
        r1 = r1.f6550f;	 Catch:{ all -> 0x00ac }
        if (r1 == 0) goto L_0x001e;
    L_0x0018:
        r1 = f6545a;	 Catch:{ all -> 0x00ac }
        r1 = r1.f6550f;	 Catch:{ all -> 0x00ac }
        if (r1 >= r10) goto L_0x0020;
    L_0x001e:
        monitor-exit(r6);	 Catch:{ all -> 0x00ac }
    L_0x001f:
        return;
    L_0x0020:
        r1 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00ac }
        r1.<init>();	 Catch:{ all -> 0x00ac }
        r2 = "KO/";
        r1 = r1.append(r2);	 Catch:{ all -> 0x00ac }
        r2 = "TR/";
        r1 = r1.append(r2);	 Catch:{ all -> 0x00ac }
        r2 = 0;
        r4 = r11.length();	 Catch:{ all -> 0x00ac }
        r5 = 3;
        r4 = java.lang.Math.min(r4, r5);	 Catch:{ all -> 0x00ac }
        r1 = r1.append(r11, r2, r4);	 Catch:{ all -> 0x00ac }
        r2 = "/";
        r1 = r1.append(r2);	 Catch:{ all -> 0x00ac }
        r2 = 0;
        r4 = r12.length();	 Catch:{ all -> 0x00ac }
        r5 = 13;
        r4 = java.lang.Math.min(r4, r5);	 Catch:{ all -> 0x00ac }
        r1 = r1.append(r12, r2, r4);	 Catch:{ all -> 0x00ac }
        r7 = r1.toString();	 Catch:{ all -> 0x00ac }
        r8 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00ac }
        r8.<init>();	 Catch:{ all -> 0x00ac }
        if (r13 == 0) goto L_0x0125;
    L_0x005f:
        r5 = r3;
    L_0x0060:
        r1 = r13.length;	 Catch:{ all -> 0x00ac }
        if (r5 >= r1) goto L_0x0125;
    L_0x0063:
        r1 = r13[r5];	 Catch:{ all -> 0x00ac }
        if (r1 != 0) goto L_0x006b;
    L_0x0067:
        r1 = r5 + 1;
        r5 = r1;
        goto L_0x0060;
    L_0x006b:
        r4 = 0;
        r1 = r13[r5];	 Catch:{ Throwable -> 0x010b }
        r1 = r1 instanceof java.lang.String;	 Catch:{ Throwable -> 0x010b }
        if (r1 == 0) goto L_0x00bf;
    L_0x0072:
        r1 = r13[r5];	 Catch:{ Throwable -> 0x010b }
        r1 = com.kochava.base.C2901d.m7661f(r1);	 Catch:{ Throwable -> 0x010b }
        r2 = r13[r5];	 Catch:{ Throwable -> 0x010b }
        r2 = com.kochava.base.C2901d.m7662g(r2);	 Catch:{ Throwable -> 0x010b }
        if (r1 == 0) goto L_0x0184;
    L_0x0080:
        r4 = 2;
        r9 = r1 instanceof org.json.JSONObject;	 Catch:{ Throwable -> 0x010b }
        if (r9 != 0) goto L_0x00af;
    L_0x0085:
        r1 = r1.toString(r4);	 Catch:{ Throwable -> 0x010b }
    L_0x0089:
        if (r1 != 0) goto L_0x0096;
    L_0x008b:
        if (r2 == 0) goto L_0x0096;
    L_0x008d:
        r4 = 2;
        r1 = r2 instanceof org.json.JSONArray;	 Catch:{ Throwable -> 0x010b }
        if (r1 != 0) goto L_0x00b6;
    L_0x0092:
        r1 = r2.toString(r4);	 Catch:{ Throwable -> 0x010b }
    L_0x0096:
        if (r1 != 0) goto L_0x009c;
    L_0x0098:
        r1 = r13[r5];	 Catch:{ Throwable -> 0x010b }
        r1 = (java.lang.String) r1;	 Catch:{ Throwable -> 0x010b }
    L_0x009c:
        if (r1 == 0) goto L_0x0067;
    L_0x009e:
        r8.append(r1);	 Catch:{ all -> 0x00ac }
        r1 = r13.length;	 Catch:{ all -> 0x00ac }
        r1 = r1 + -1;
        if (r5 >= r1) goto L_0x0067;
    L_0x00a6:
        r1 = "\n";
        r8.append(r1);	 Catch:{ all -> 0x00ac }
        goto L_0x0067;
    L_0x00ac:
        r1 = move-exception;
        monitor-exit(r6);	 Catch:{ all -> 0x00ac }
        throw r1;
    L_0x00af:
        r1 = (org.json.JSONObject) r1;	 Catch:{ Throwable -> 0x010b }
        r1 = com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation.toString(r1, r4);	 Catch:{ Throwable -> 0x010b }
        goto L_0x0089;
    L_0x00b6:
        r0 = r2;
        r0 = (org.json.JSONArray) r0;	 Catch:{ Throwable -> 0x010b }
        r1 = r0;
        r1 = com.newrelic.agent.android.instrumentation.JSONArrayInstrumentation.toString(r1, r4);	 Catch:{ Throwable -> 0x010b }
        goto L_0x0096;
    L_0x00bf:
        r1 = r13[r5];	 Catch:{ Throwable -> 0x010b }
        r1 = r1 instanceof org.json.JSONObject;	 Catch:{ Throwable -> 0x010b }
        if (r1 == 0) goto L_0x00da;
    L_0x00c5:
        r1 = r13[r5];	 Catch:{ Throwable -> 0x010b }
        r1 = (org.json.JSONObject) r1;	 Catch:{ Throwable -> 0x010b }
        r2 = 2;
        r4 = r1 instanceof org.json.JSONObject;	 Catch:{ Throwable -> 0x010b }
        if (r4 != 0) goto L_0x00d3;
    L_0x00ce:
        r1 = r1.toString(r2);	 Catch:{ Throwable -> 0x010b }
        goto L_0x009c;
    L_0x00d3:
        r1 = (org.json.JSONObject) r1;	 Catch:{ Throwable -> 0x010b }
        r1 = com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation.toString(r1, r2);	 Catch:{ Throwable -> 0x010b }
        goto L_0x009c;
    L_0x00da:
        r1 = r13[r5];	 Catch:{ Throwable -> 0x010b }
        r1 = r1 instanceof org.json.JSONArray;	 Catch:{ Throwable -> 0x010b }
        if (r1 == 0) goto L_0x00f5;
    L_0x00e0:
        r1 = r13[r5];	 Catch:{ Throwable -> 0x010b }
        r1 = (org.json.JSONArray) r1;	 Catch:{ Throwable -> 0x010b }
        r2 = 2;
        r4 = r1 instanceof org.json.JSONArray;	 Catch:{ Throwable -> 0x010b }
        if (r4 != 0) goto L_0x00ee;
    L_0x00e9:
        r1 = r1.toString(r2);	 Catch:{ Throwable -> 0x010b }
        goto L_0x009c;
    L_0x00ee:
        r1 = (org.json.JSONArray) r1;	 Catch:{ Throwable -> 0x010b }
        r1 = com.newrelic.agent.android.instrumentation.JSONArrayInstrumentation.toString(r1, r2);	 Catch:{ Throwable -> 0x010b }
        goto L_0x009c;
    L_0x00f5:
        r1 = r13[r5];	 Catch:{ Throwable -> 0x010b }
        r1 = r1 instanceof java.lang.Throwable;	 Catch:{ Throwable -> 0x010b }
        if (r1 == 0) goto L_0x0104;
    L_0x00fb:
        r1 = r13[r5];	 Catch:{ Throwable -> 0x010b }
        r1 = (java.lang.Throwable) r1;	 Catch:{ Throwable -> 0x010b }
        r1 = android.util.Log.getStackTraceString(r1);	 Catch:{ Throwable -> 0x010b }
        goto L_0x009c;
    L_0x0104:
        r1 = r13[r5];	 Catch:{ Throwable -> 0x010b }
        r1 = r1.toString();	 Catch:{ Throwable -> 0x010b }
        goto L_0x009c;
    L_0x010b:
        r1 = move-exception;
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00ac }
        r2.<init>();	 Catch:{ all -> 0x00ac }
        r4 = "Failed to build message.\n";
        r2 = r2.append(r4);	 Catch:{ all -> 0x00ac }
        r1 = android.util.Log.getStackTraceString(r1);	 Catch:{ all -> 0x00ac }
        r1 = r2.append(r1);	 Catch:{ all -> 0x00ac }
        r1 = r1.toString();	 Catch:{ all -> 0x00ac }
        goto L_0x009c;
    L_0x0125:
        r1 = r8.length();	 Catch:{ all -> 0x00ac }
        if (r1 != 0) goto L_0x0130;
    L_0x012b:
        r1 = " ";
        r8.append(r1);	 Catch:{ all -> 0x00ac }
    L_0x0130:
        r1 = r8.toString();	 Catch:{ all -> 0x00ac }
        r2 = "\n";
        r2 = r1.split(r2);	 Catch:{ all -> 0x00ac }
        r4 = r2.length;	 Catch:{ all -> 0x00ac }
        r1 = r3;
    L_0x013c:
        if (r1 >= r4) goto L_0x017f;
    L_0x013e:
        r3 = r2[r1];	 Catch:{ all -> 0x00ac }
        r5 = f6545a;	 Catch:{ all -> 0x00ac }
        r5 = r5.f6550f;	 Catch:{ all -> 0x00ac }
        if (r5 <= 0) goto L_0x0159;
    L_0x0146:
        r5 = f6545a;	 Catch:{ all -> 0x00ac }
        r5 = r5.f6550f;	 Catch:{ all -> 0x00ac }
        if (r10 > r5) goto L_0x0159;
    L_0x014c:
        r5 = f6545a;	 Catch:{ all -> 0x00ac }
        r5 = r5.f6549e;	 Catch:{ all -> 0x00ac }
        if (r5 == 0) goto L_0x0159;
    L_0x0152:
        r5 = f6545a;	 Catch:{ Throwable -> 0x0182 }
        r5 = r5.f6549e;	 Catch:{ Throwable -> 0x0182 }
        r5.onLog(r10, r7, r3);	 Catch:{ Throwable -> 0x0182 }
    L_0x0159:
        r5 = f6545a;	 Catch:{ all -> 0x00ac }
        r5 = r5.f6548d;	 Catch:{ all -> 0x00ac }
        if (r5 <= 0) goto L_0x0168;
    L_0x015f:
        r5 = f6545a;	 Catch:{ all -> 0x00ac }
        r5 = r5.f6548d;	 Catch:{ all -> 0x00ac }
        if (r10 > r5) goto L_0x0168;
    L_0x0165:
        switch(r10) {
            case 0: goto L_0x0168;
            case 1: goto L_0x016b;
            case 2: goto L_0x016f;
            case 3: goto L_0x0173;
            case 4: goto L_0x0177;
            case 5: goto L_0x017b;
            default: goto L_0x0168;
        };	 Catch:{ all -> 0x00ac }
    L_0x0168:
        r1 = r1 + 1;
        goto L_0x013c;
    L_0x016b:
        android.util.Log.e(r7, r3);	 Catch:{ all -> 0x00ac }
        goto L_0x0168;
    L_0x016f:
        android.util.Log.w(r7, r3);	 Catch:{ all -> 0x00ac }
        goto L_0x0168;
    L_0x0173:
        android.util.Log.i(r7, r3);	 Catch:{ all -> 0x00ac }
        goto L_0x0168;
    L_0x0177:
        android.util.Log.d(r7, r3);	 Catch:{ all -> 0x00ac }
        goto L_0x0168;
    L_0x017b:
        android.util.Log.v(r7, r3);	 Catch:{ all -> 0x00ac }
        goto L_0x0168;
    L_0x017f:
        monitor-exit(r6);	 Catch:{ all -> 0x00ac }
        goto L_0x001f;
    L_0x0182:
        r5 = move-exception;
        goto L_0x0159;
    L_0x0184:
        r1 = r4;
        goto L_0x0089;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.kochava.base.Tracker.m7517a(int, java.lang.String, java.lang.String, java.lang.Object[]):void");
    }

    @AnyThread
    public static void addPushToken(@NonNull String str) {
        synchronized (f6545a) {
            m7517a(3, "TRA", "addPushToken", "addPushToken");
            if (f6545a.f6551g == null || str == null || str.isEmpty()) {
                m7517a(2, "TRA", "addPushToken", "Invalid Configuration or Parameter");
            } else {
                f6545a.f6551g.mo26542a(str, true);
            }
        }
    }

    public static void clearConsentShouldPrompt() {
        synchronized (f6545a) {
            m7517a(3, "TRA", "clearConsentS", "clearConsentShouldPrompt");
            if (f6545a.f6551g != null) {
                f6545a.f6551g.mo26549i();
            } else {
                m7517a(2, "TRA", "clearConsentS", "Invalid Configuration or Parameter");
            }
        }
    }

    @AnyThread
    @SuppressLint({"ObsoleteSdkInt"})
    public static void configure(@NonNull Configuration configuration) {
        Object obj = null;
        synchronized (f6545a) {
            String str = "configure";
            if (VERSION.SDK_INT < 14) {
                m7517a(1, "TRA", "configure", "Below API 14 is unsupported. Cannot Configure.");
                return;
            }
            try {
                if (f6545a.f6551g != null) {
                    m7517a(2, "TRA", "configure", "Already Configured");
                } else if (configuration == null) {
                    m7517a(1, "TRA", "configure", "Null Configuration");
                } else {
                    f6545a.f6548d = C2901d.m7625a(configuration.f6045d, 3);
                    if (configuration.f6042a == null || configuration.f6042a.getApplicationContext() == null) {
                        m7517a(1, "TRA", "configure", "Null Context");
                        return;
                    }
                    Context applicationContext = configuration.f6042a.getApplicationContext();
                    SharedPreferences sharedPreferences = applicationContext.getSharedPreferences("koov", 0);
                    sharedPreferences.edit().apply();
                    int a = C2901d.m7625a(Integer.valueOf(sharedPreferences.getInt("log_level", -1)), f6545a.f6548d);
                    if (a != f6545a.f6548d) {
                        f6545a.f6548d = a;
                        m7517a(4, "TRA", "configure", "Override LogLevel " + a);
                    }
                    JSONObject jSONObject = new JSONObject();
                    C2901d.m7637a("url_init", sharedPreferences.getString("url_init", null), jSONObject, 0);
                    C2901d.m7637a("url_push_token_add", sharedPreferences.getString("url_push_token_add", null), jSONObject, 0);
                    C2901d.m7637a("url_push_token_remove", sharedPreferences.getString("url_push_token_remove", null), jSONObject, 0);
                    C2901d.m7637a("url_get_attribution", sharedPreferences.getString("url_get_attribution", null), jSONObject, 0);
                    C2901d.m7637a("url_initial", sharedPreferences.getString("url_initial", null), jSONObject, 0);
                    C2901d.m7637a("url_update", sharedPreferences.getString("url_update", null), jSONObject, 0);
                    C2901d.m7637a("url_identity_link", sharedPreferences.getString("url_identity_link", null), jSONObject, 0);
                    C2901d.m7637a("url_event", sharedPreferences.getString("url_event", null), jSONObject, 0);
                    if (jSONObject.length() != 0) {
                        m7517a(4, "TRA", "configure", "Override URLs", jSONObject);
                    }
                    Object obj2 = (configuration.f6043b == null || configuration.f6043b.trim().isEmpty()) ? 1 : null;
                    if (configuration.f6044c == null || configuration.f6044c.trim().isEmpty()) {
                        obj = 1;
                    }
                    if ((obj2 == null || obj == null) && !(obj2 == null && obj == null)) {
                        f6545a.f6551g = new C2895a(applicationContext, getVersion(), f6545a.f6547c, configuration.f6043b, configuration.f6044c, configuration.f6046e, configuration.f6047f, configuration.f6048g, jSONObject, configuration.f6052k, configuration.f6049h);
                        if (configuration.f6050i != null) {
                            f6545a.f6551g.mo26543a(configuration.f6050i.booleanValue());
                        }
                        if (configuration.f6051j != null) {
                            f6545a.f6551g.mo26541a(configuration.f6051j);
                        }
                        m7517a(3, "TRA", "configure", "Complete: " + getVersion());
                        if (f6545a.f6548d > 3) {
                            m7517a(2, "TRA", "configure", "Log Level set higher than recommended for publishing");
                        }
                        return;
                    }
                    m7517a(1, "TRA", "configure", "Either (but not both) App Guid or Partner Name required");
                }
            } catch (Throwable th) {
                m7517a(1, "TRA", "configure", AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN, th);
                f6545a.f6551g = null;
            }
        }
    }

    @AnyThread
    public static void ext(@NonNull String str, @NonNull String str2) {
        synchronized (f6545a) {
            if (str != null) {
                if (f6545a.f6546b == null && f6545a.f6547c == null && f6545a.f6551g == null) {
                    f6545a.f6546b = str;
                    f6545a.f6547c = str2;
                }
            }
        }
    }

    @AnyThread
    @Contract(pure = true)
    @NonNull
    public static String getAttribution() {
        String a;
        synchronized (f6545a) {
            if (f6545a.f6551g != null) {
                a = f6545a.f6551g.mo26536a("attribution");
            } else {
                m7517a(2, "TRA", "getAttributio", "Invalid Configuration or Parameter");
                a = "";
            }
        }
        return a;
    }

    @NonNull
    public static String getConsentDescription() {
        String c;
        synchronized (f6545a) {
            if (f6545a.f6551g != null) {
                c = f6545a.f6551g.mo26547c();
            } else {
                m7517a(2, "TRA", "getConsentDes", "Invalid Configuration or Parameter");
                c = "";
            }
        }
        return c;
    }

    @NonNull
    public static ConsentPartner[] getConsentPartners() {
        ConsentPartner[] consentPartnerArr;
        synchronized (f6545a) {
            if (f6545a.f6551g != null) {
                JSONArray j = f6545a.f6551g.mo26550j();
                consentPartnerArr = new ConsentPartner[j.length()];
                for (int i = 0; i < consentPartnerArr.length; i++) {
                    consentPartnerArr[i] = new ConsentPartner(C2901d.m7649b(j.opt(i), true));
                }
            } else {
                m7517a(2, "TRA", "getConsentPar", "Invalid Configuration or Parameter");
                consentPartnerArr = new ConsentPartner[0];
            }
        }
        return consentPartnerArr;
    }

    @NonNull
    public static String getConsentPartnersJson() {
        String a;
        synchronized (f6545a) {
            if (f6545a.f6551g != null) {
                a = C2901d.m7631a(f6545a.f6551g.mo26550j());
            } else {
                m7517a(2, "TRA", "getConsentPar", "Invalid Configuration or Parameter");
                a = "[]";
            }
        }
        return a;
    }

    public static long getConsentResponseTime() {
        long f;
        synchronized (f6545a) {
            if (f6545a.f6551g != null) {
                f = f6545a.f6551g.mo26531f();
            } else {
                m7517a(2, "TRA", "getConsentRes", "Invalid Configuration or Parameter");
                f = 0;
            }
        }
        return f;
    }

    @AnyThread
    @NonNull
    public static String getDeviceId() {
        String a;
        synchronized (f6545a) {
            if (f6545a.f6551g != null) {
                a = f6545a.f6551g.mo26536a("kochava_device_id");
            } else {
                m7517a(2, "TRA", "getDeviceId", "Invalid Configuration or Parameter");
                a = "";
            }
        }
        return a;
    }

    @AnyThread
    @NonNull
    public static InstallReferrer getInstallReferrer() {
        InstallReferrer a;
        synchronized (f6545a) {
            if (f6545a.f6551g != null) {
                a = C2897a.m7551a(f6545a.f6551g.mo26545b("install_referrer"), false, f6545a.f6551g.mo26536a("referrer"));
            } else {
                m7517a(2, "TRA", "getInstallRef", "Invalid Configuration or Parameter");
                a = C2897a.m7551a(new JSONObject(), false, null);
            }
        }
        return a;
    }

    @AnyThread
    @Contract(pure = true)
    @NonNull
    public static String getVersion() {
        String str;
        synchronized (f6545a) {
            str = "AndroidTracker 3.4.0";
            str = f6545a.f6546b != null ? "AndroidTracker 3.4.0 (" + f6545a.f6546b + ")" : "AndroidTracker 3.4.0";
        }
        return str;
    }

    @AnyThread
    @Contract(pure = true)
    public static boolean isConfigured() {
        boolean z;
        synchronized (f6545a) {
            z = f6545a.f6551g != null;
        }
        return z;
    }

    public static boolean isConsentGranted() {
        boolean z = false;
        synchronized (f6545a) {
            if (f6545a.f6551g != null) {
                z = f6545a.f6551g.mo26530e();
            } else {
                m7517a(2, "TRA", "isConsentGran", "Invalid Configuration or Parameter");
            }
        }
        return z;
    }

    public static boolean isConsentGrantedOrNotRequired() {
        return !isConsentRequired() || isConsentGranted();
    }

    public static boolean isConsentRequired() {
        boolean z = true;
        synchronized (f6545a) {
            if (f6545a.f6551g != null) {
                z = f6545a.f6551g.mo26529d();
            } else {
                m7517a(2, "TRA", "isConsentRequ", "Invalid Configuration or Parameter");
            }
        }
        return z;
    }

    public static boolean isConsentShouldPrompt() {
        boolean z = false;
        synchronized (f6545a) {
            if (f6545a.f6551g != null) {
                z = f6545a.f6551g.mo26548h();
            } else {
                m7517a(2, "TRA", "isConsentShou", "Invalid Configuration or Parameter");
            }
        }
        return z;
    }

    @AnyThread
    @Contract(pure = true)
    public static boolean isSessionActive() {
        boolean z = false;
        synchronized (f6545a) {
            if (f6545a.f6551g != null) {
                z = f6545a.f6551g.mo26534l();
            } else {
                m7517a(2, "TRA", "isSessionActi", "Invalid Configuration or Parameter");
            }
        }
        return z;
    }

    @AnyThread
    @Contract(pure = true)
    public static boolean isSleep() {
        boolean z = false;
        synchronized (f6545a) {
            if (f6545a.f6551g != null) {
                if (f6545a.f6551g.mo26544b() == 1) {
                    z = true;
                }
            } else {
                m7517a(2, "TRA", "isSleep", "Invalid Configuration or Parameter");
            }
        }
        return z;
    }

    @AnyThread
    public static void removePushToken(@NonNull String str) {
        synchronized (f6545a) {
            m7517a(3, "TRA", "removePushTok", "removePushToken");
            if (f6545a.f6551g == null || str == null || str.isEmpty()) {
                m7517a(2, "TRA", "removePushTok", "Invalid Configuration or Parameter");
            } else {
                f6545a.f6551g.mo26542a(str, false);
            }
        }
    }

    @AnyThread
    @SuppressLint({"CheckResult"})
    public static void sendEvent(@NonNull Event event) {
        synchronized (f6545a) {
            m7517a(3, "TRA", "sendEvent", "sendEvent(Event)");
            if (f6545a.f6551g == null || event == null || event.f6539b.trim().isEmpty()) {
                m7517a(2, "TRA", "sendEvent", "Invalid Configuration or Parameter");
            } else {
                if (event.f6541d) {
                    long a = C2901d.m7626a();
                    event.setStartDate(new Date(event.f6540c)).setEndDate(new Date(a)).setDuration(((double) Math.round(((double) (a - event.f6540c)) / 100.0d)) / 10.0d);
                }
                f6545a.f6551g.mo26539a(6, event.f6539b, C2901d.m7632a(event.f6538a), event.f6542e, event.f6543f, null);
            }
        }
    }

    public static void sendEvent(@Size @NonNull String str, @NonNull String str2) {
        synchronized (f6545a) {
            m7517a(3, "TRA", "sendEvent", "sendEvent(String,String)");
            if (f6545a.f6551g == null || str == null || str2 == null || str.trim().isEmpty()) {
                m7517a(2, "TRA", "sendEvent", "Invalid Configuration or Parameter");
            } else {
                f6545a.f6551g.mo26539a(6, str, str2, null, null, null);
            }
        }
    }

    @AnyThread
    public static void sendEventDeepLink(@Size @NonNull String str) {
        synchronized (f6545a) {
            m7517a(3, "TRA", "sendEventDeep", "sendEventDeepLink");
            if (f6545a.f6551g == null || str == null || str.trim().isEmpty()) {
                m7517a(2, "TRA", "sendEventDeep", "Invalid Configuration or Parameter");
            } else {
                f6545a.f6551g.mo26539a(8, null, null, null, null, str);
            }
        }
    }

    @AnyThread
    public static void setAppLimitAdTracking(boolean z) {
        synchronized (f6545a) {
            m7517a(3, "TRA", "setAppLimitAd", "setAppLimitAdTracking");
            if (f6545a.f6551g != null) {
                f6545a.f6551g.mo26543a(z);
            } else {
                m7517a(2, "TRA", "setAppLimitAd", "Invalid Configuration or Parameter");
            }
        }
    }

    public static void setConsentGranted(boolean z) {
        synchronized (f6545a) {
            m7517a(3, "TRA", "setConsentGra", "setConsentGranted");
            if (f6545a.f6551g != null) {
                f6545a.f6551g.mo26546b(z);
            } else {
                m7517a(2, "TRA", "setConsentGra", "Invalid Configuration or Parameter");
            }
        }
    }

    public static void setDeepLinkListener(@Nullable Uri uri, @Size int i, @NonNull DeepLinkListener deepLinkListener) {
        synchronized (f6545a) {
            m7517a(3, "TRA", "setDeepLinkLi", "setDeepLinkListener");
            if (f6545a.f6551g == null || deepLinkListener == null) {
                m7517a(2, "TRA", "setDeepLinkLi", "Invalid Configuration or Parameter");
            } else {
                f6545a.f6551g.mo26540a(uri, i, deepLinkListener);
            }
        }
    }

    public static void setDeepLinkListener(@Nullable Uri uri, @NonNull DeepLinkListener deepLinkListener) {
        setDeepLinkListener(uri, 4000, deepLinkListener);
    }

    @AnyThread
    public static void setIdentityLink(@NonNull IdentityLink identityLink) {
        synchronized (f6545a) {
            m7517a(3, "TRA", "setIdentityLi", "setIdentityLink");
            if (f6545a.f6551g == null || identityLink == null || identityLink.f6544a.length() <= 0) {
                m7517a(2, "TRA", "setIdentityLi", "Invalid Configuration or Parameter");
            } else {
                f6545a.f6551g.mo26541a(identityLink);
            }
        }
    }

    @AnyThread
    static void setLogListener(@NonNull LogListener logListener, int i) {
        synchronized (f6545a) {
            f6545a.f6549e = logListener;
            if (i > 0 && i <= 5) {
                f6545a.f6550f = i;
            }
        }
    }

    @AnyThread
    public static void setSleep(boolean z) {
        int i = 1;
        synchronized (f6545a) {
            m7517a(3, "TRA", "setSleep", "setSleep");
            if (f6545a.f6551g != null) {
                C2895a c2895a = f6545a.f6551g;
                if (!z) {
                    i = 0;
                }
                c2895a.mo26538a(i);
            } else {
                m7517a(2, "TRA", "setSleep", "Invalid Configuration or Parameter");
            }
        }
    }

    @AnyThread
    static void unConfigure() {
        synchronized (f6545a) {
            String str = "unConfigure";
            try {
                m7517a(2, "TRA", "unConfigure", "UnConfigure Tracker");
                f6545a.f6548d = 3;
                f6545a.f6546b = null;
                f6545a.f6547c = null;
                f6545a.f6549e = null;
                f6545a.f6550f = 0;
                if (f6545a.f6551g != null) {
                    f6545a.f6551g.mo26537a();
                }
                f6545a.f6551g = null;
            } catch (Throwable th) {
                m7517a(1, "TRA", "unConfigure", th);
            }
        }
    }
}
