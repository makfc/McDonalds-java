package com.mcdonalds.app.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnShowListener;
import android.content.res.Resources.NotFoundException;
import android.location.Location;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.p000v4.widget.SwipeRefreshLayout;
import android.support.p001v7.app.AlertDialog;
import android.support.p001v7.app.AlertDialog.Builder;
import android.view.ActionMode;
import android.view.ActionMode.Callback;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.ensighten.Ensighten;
import com.google.api.client.repackaged.com.google.common.base.Strings;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.analytics.datalayer.DataLayerManager;
import com.mcdonalds.app.ordering.OrderingDisclaimerItem;
import com.mcdonalds.app.p043ui.models.DayPartsResponse;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.connectors.middleware.model.MWLocation;
import com.mcdonalds.sdk.connectors.middleware.model.MWOpeningHourItem;
import com.mcdonalds.sdk.modules.models.TimeRestriction;
import com.mcdonalds.sdk.modules.storelocator.Store;
import com.mcdonalds.sdk.services.configuration.AppParameters;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.mcdonalds.sdk.services.data.LocalDataManager;
import com.mcdonalds.sdk.services.error.ErrorManager;
import com.mcdonalds.sdk.services.log.MCDLog;
import com.mcdonalds.sdk.services.network.RequestManagerServiceConnection;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

@SuppressLint({"SimpleDateFormat"})
public class UIUtils {
    public static final OnClickListener DEFAULT_DIALOG_CLICK_LISTENER = new C24681();
    private static final SimpleDateFormat ISO8601_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    private static final String LOG_TAG = UIUtils.class.getSimpleName();
    private static final DateFormat SHORT_DATE_FORMAT = DateFormat.getDateInstance(3, Locale.getDefault());
    public static final String TAG = UIUtils.class.getSimpleName();
    private static ArrayList<OrderingDisclaimerItem> orderingDisclaimerItems = new ArrayList();
    private static DayPartsListener sDayPartsListener;
    private static DayPartsResponse sDayPartsResponse;
    private static String sEmailPattern;
    private static boolean sLoadingDayParts;
    private static String sNumberPattern;
    private static ProgressDialog sProgressDialog;
    private static boolean sProgressDisplayed;
    private static String sProgressKey;
    private static String sPwdPattern;

    /* renamed from: com.mcdonalds.app.util.UIUtils$1 */
    static class C24681 implements OnClickListener {
        C24681() {
        }

        public void onClick(DialogInterface dialog, int which) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
            dialog.dismiss();
        }
    }

    /* renamed from: com.mcdonalds.app.util.UIUtils$4 */
    static class C24714 implements Callback {
        C24714() {
        }

        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            Ensighten.evaluateEvent(this, "onCreateActionMode", new Object[]{actionMode, menu});
            return false;
        }

        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            Ensighten.evaluateEvent(this, "onPrepareActionMode", new Object[]{actionMode, menu});
            return false;
        }

        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            Ensighten.evaluateEvent(this, "onActionItemClicked", new Object[]{actionMode, menuItem});
            return false;
        }

        public void onDestroyActionMode(ActionMode actionMode) {
            Ensighten.evaluateEvent(this, "onDestroyActionMode", new Object[]{actionMode});
        }
    }

    private static class DayPartsListener extends Observable implements AsyncListener<DayPartsResponse> {
        private DayPartsListener() {
        }

        /* synthetic */ DayPartsListener(C24681 x0) {
            this();
        }

        public void onResponse(DayPartsResponse response, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            if (exception == null) {
                UIUtils.access$202(response);
                setChanged();
                notifyObservers();
            }
            UIUtils.access$302(false);
        }
    }

    public static class MCDAlertDialogBuilder {
        Builder mAlertBuilder;
        protected Context mContext;
        private Integer mDelayToDismissInSeconds;
        private OnClickListener mOnDismiss;

        /* renamed from: com.mcdonalds.app.util.UIUtils$MCDAlertDialogBuilder$1 */
        class C24721 implements OnShowListener {
            C24721() {
            }

            public void onShow(DialogInterface dialog) {
                Ensighten.evaluateEvent(this, "onShow", new Object[]{dialog});
                if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.UIUtils$MCDAlertDialogBuilder", "access$500", new Object[]{MCDAlertDialogBuilder.this}) != null && Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.UIUtils$MCDAlertDialogBuilder", "access$500", new Object[]{MCDAlertDialogBuilder.this}).intValue() > 0) {
                    MCDAlertDialogBuilder.access$600(MCDAlertDialogBuilder.this, dialog);
                }
            }
        }

        static /* synthetic */ void access$600(MCDAlertDialogBuilder x0, DialogInterface x1) {
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.UIUtils$MCDAlertDialogBuilder", "access$600", new Object[]{x0, x1});
            x0.dismissDialog(x1);
        }

        public MCDAlertDialogBuilder(@NonNull Context context) {
            this.mContext = context;
            this.mAlertBuilder = new Builder(context, C2658R.style.f8039AppTheme.Dialog);
        }

        public static MCDAlertDialogBuilder withContext(@NonNull Context context) {
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.UIUtils$MCDAlertDialogBuilder", "withContext", new Object[]{context});
            return new MCDAlertDialogBuilder(context);
        }

        public MCDAlertDialogBuilder setTitle(@StringRes int stringId) {
            Ensighten.evaluateEvent(this, "setTitle", new Object[]{new Integer(stringId)});
            this.mAlertBuilder.setTitle(this.mContext.getString(stringId));
            return this;
        }

        public MCDAlertDialogBuilder setTitle(String string) {
            Ensighten.evaluateEvent(this, "setTitle", new Object[]{string});
            this.mAlertBuilder.setTitle((CharSequence) string);
            return this;
        }

        public MCDAlertDialogBuilder setMessage(String message) {
            Ensighten.evaluateEvent(this, "setMessage", new Object[]{message});
            this.mAlertBuilder.setMessage((CharSequence) message);
            return this;
        }

        public MCDAlertDialogBuilder setMessage(@StringRes int stringId) {
            Ensighten.evaluateEvent(this, "setMessage", new Object[]{new Integer(stringId)});
            this.mAlertBuilder.setMessage(this.mContext.getString(stringId));
            return this;
        }

        public MCDAlertDialogBuilder setPositiveButton(@StringRes int stringId, OnClickListener onClickListener) {
            Ensighten.evaluateEvent(this, "setPositiveButton", new Object[]{new Integer(stringId), onClickListener});
            this.mAlertBuilder.setPositiveButton(this.mContext.getString(stringId), onClickListener);
            return this;
        }

        public MCDAlertDialogBuilder setPositiveButton(String string, OnClickListener onClickListener) {
            Ensighten.evaluateEvent(this, "setPositiveButton", new Object[]{string, onClickListener});
            this.mAlertBuilder.setPositiveButton((CharSequence) string, onClickListener);
            return this;
        }

        public MCDAlertDialogBuilder setNegativeButton(@StringRes int stringId, OnClickListener onClickListener) {
            Ensighten.evaluateEvent(this, "setNegativeButton", new Object[]{new Integer(stringId), onClickListener});
            this.mAlertBuilder.setNegativeButton(stringId, onClickListener);
            return this;
        }

        public MCDAlertDialogBuilder setNegativeButton(String string, OnClickListener onClickListener) {
            Ensighten.evaluateEvent(this, "setNegativeButton", new Object[]{string, onClickListener});
            this.mAlertBuilder.setNegativeButton((CharSequence) string, onClickListener);
            return this;
        }

        public MCDAlertDialogBuilder setNeutralButton(@StringRes int stringId, OnClickListener onClickListener) {
            Ensighten.evaluateEvent(this, "setNeutralButton", new Object[]{new Integer(stringId), onClickListener});
            this.mAlertBuilder.setNeutralButton(stringId, onClickListener);
            return this;
        }

        public MCDAlertDialogBuilder setNeutralButton(String string, OnClickListener onClickListener) {
            Ensighten.evaluateEvent(this, "setNeutralButton", new Object[]{string, onClickListener});
            this.mAlertBuilder.setNeutralButton((CharSequence) string, onClickListener);
            return this;
        }

        public MCDAlertDialogBuilder setCancelable(boolean cancelable) {
            Ensighten.evaluateEvent(this, "setCancelable", new Object[]{new Boolean(cancelable)});
            this.mAlertBuilder.setCancelable(cancelable);
            return this;
        }

        public MCDAlertDialogBuilder setOnCancelListener(OnCancelListener onCancelListener) {
            Ensighten.evaluateEvent(this, "setOnCancelListener", new Object[]{onCancelListener});
            if (onCancelListener != null) {
                this.mAlertBuilder.setOnCancelListener(onCancelListener);
            }
            return this;
        }

        public AlertDialog create() {
            Ensighten.evaluateEvent(this, "create", null);
            AlertDialog dialog = this.mAlertBuilder.create();
            dialog.setOnShowListener(onShowListener());
            DataLayerManager.getInstance().recordError(ErrorManager.getInstance().getLastError());
            return dialog;
        }

        public MCDAlertDialogBuilder setView(View view) {
            Ensighten.evaluateEvent(this, "setView", new Object[]{view});
            this.mAlertBuilder.setView(view);
            return this;
        }

        public MCDAlertDialogBuilder setDelayToDismissInSeconds(Integer delayToDismissInSeconds, OnClickListener onDismiss) {
            Ensighten.evaluateEvent(this, "setDelayToDismissInSeconds", new Object[]{delayToDismissInSeconds, onDismiss});
            this.mDelayToDismissInSeconds = delayToDismissInSeconds;
            this.mOnDismiss = onDismiss;
            return this;
        }

        private OnShowListener onShowListener() {
            Ensighten.evaluateEvent(this, "onShowListener", null);
            return new C24721();
        }

        private void dismissDialog(final DialogInterface dialog) {
            Ensighten.evaluateEvent(this, "dismissDialog", new Object[]{dialog});
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    Ensighten.evaluateEvent(this, "run", null);
                    if (dialog != null) {
                        dialog.dismiss();
                        if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.UIUtils$MCDAlertDialogBuilder", "access$700", new Object[]{MCDAlertDialogBuilder.this}) != null) {
                            Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.UIUtils$MCDAlertDialogBuilder", "access$700", new Object[]{MCDAlertDialogBuilder.this}).onClick(dialog, 0);
                        }
                    }
                }
            }, (long) (this.mDelayToDismissInSeconds.intValue() * 1000));
        }
    }

    public static class MCDFullScreenAlertDialogBuilder {
        private final Context mContext;
        private final Dialog mDialog = new Dialog(this.mContext);
        private final TextView mMessage;
        private final Button mPositiveButton;
        private final View mRootView = LayoutInflater.from(this.mContext).inflate(C2658R.layout.dialog_full_screen_alert, null);

        /* renamed from: com.mcdonalds.app.util.UIUtils$MCDFullScreenAlertDialogBuilder$1 */
        class C24741 implements View.OnClickListener {
            final /* synthetic */ MCDFullScreenAlertDialogBuilder this$0;

            public void onClick(View v) {
                Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.UIUtils$MCDFullScreenAlertDialogBuilder", "access$400", new Object[]{this.this$0}).dismiss();
            }
        }

        public MCDFullScreenAlertDialogBuilder(Context context) {
            this.mContext = context;
            this.mDialog.requestWindowFeature(1);
            this.mDialog.addContentView(this.mRootView, new LayoutParams(-1, -1));
            this.mDialog.getWindow().setLayout(-1, -1);
            this.mDialog.getWindow().setBackgroundDrawable(this.mContext.getResources().getDrawable(17301673));
            this.mMessage = (TextView) this.mDialog.findViewById(C2358R.C2357id.message);
            this.mPositiveButton = (Button) this.mDialog.findViewById(C2358R.C2357id.positive_button);
        }

        public static MCDFullScreenAlertDialogBuilder withContext(Context context) {
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.UIUtils$MCDFullScreenAlertDialogBuilder", "withContext", new Object[]{context});
            return new MCDFullScreenAlertDialogBuilder(context);
        }

        public MCDFullScreenAlertDialogBuilder setPositiveButtonText(int stringId, View.OnClickListener listener) {
            Ensighten.evaluateEvent(this, "setPositiveButtonText", new Object[]{new Integer(stringId), listener});
            this.mPositiveButton.setText(this.mContext.getResources().getString(stringId));
            this.mPositiveButton.setOnClickListener(listener);
            return this;
        }

        public MCDFullScreenAlertDialogBuilder setMessage(int stringId) {
            Ensighten.evaluateEvent(this, "setMessage", new Object[]{new Integer(stringId)});
            this.mMessage.setText(this.mContext.getResources().getString(stringId));
            return this;
        }

        public Dialog create() {
            Ensighten.evaluateEvent(this, "create", null);
            DataLayerManager.getInstance().recordError(ErrorManager.getInstance().getLastError());
            return this.mDialog;
        }
    }

    static /* synthetic */ void access$100(int x0, AsyncListener x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.UIUtils", "access$100", new Object[]{new Integer(x0), x1});
        sendDayPartsResponse(x0, x1);
    }

    static /* synthetic */ DayPartsResponse access$202(DayPartsResponse x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.UIUtils", "access$202", new Object[]{x0});
        sDayPartsResponse = x0;
        return x0;
    }

    static /* synthetic */ boolean access$302(boolean x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.UIUtils", "access$302", new Object[]{new Boolean(x0)});
        sLoadingDayParts = x0;
        return x0;
    }

    public static void init(Context context) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.UIUtils", "init", new Object[]{context});
        sEmailPattern = context.getString(C2658R.string.pattern_email_address);
        sPwdPattern = context.getString(C2658R.string.pattern_password);
    }

    public static int dpAsPixels(Context context, int dpValue) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.UIUtils", "dpAsPixels", new Object[]{context, new Integer(dpValue)});
        if (context == null) {
            return 0;
        }
        return (int) ((((float) dpValue) * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static int getDrawableIdByName(Context context, String pDrawableName) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.UIUtils", "getDrawableIdByName", new Object[]{context, pDrawableName});
        return context.getResources().getIdentifier(pDrawableName, "drawable", context.getPackageName());
    }

    public static String getText(EditText text) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.UIUtils", "getText", new Object[]{text});
        return getText(text, false);
    }

    public static String getText(EditText text, boolean notNull) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.UIUtils", "getText", new Object[]{text, new Boolean(notNull)});
        if (text.getText() != null) {
            return text.getText().toString().trim();
        }
        if (notNull) {
            return "";
        }
        return null;
    }

    public static String getStringByName(Context context, String key) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.UIUtils", "getStringByName", new Object[]{context, key});
        if (key == null) {
            return null;
        }
        if (key.startsWith("analytics_raw")) {
            return "";
        }
        if (key.startsWith("raw:")) {
            return key.substring(4);
        }
        int resourceId = context.getResources().getIdentifier(key, "string", context.getPackageName());
        if (resourceId <= 0) {
            return key;
        }
        try {
            return context.getString(resourceId);
        } catch (NotFoundException e) {
            return key;
        }
    }

    public static Float metersFromLocation(Store store, Location location) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.UIUtils", "metersFromLocation", new Object[]{store, location});
        if (location != null) {
            return Float.valueOf(store.toLocation().distanceTo(location));
        }
        return null;
    }

    public static String metersToMiles(Context context, Float meters) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.UIUtils", "metersToMiles", new Object[]{context, meters});
        return context.getString(C2658R.string.format_miles_away, new Object[]{new DecimalFormat("#0.0").format(((double) meters.floatValue()) * 6.21371E-4d)});
    }

    public static String metersToKilometers(Context context, Float meters) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.UIUtils", "metersToKilometers", new Object[]{context, meters});
        return context.getString(C2658R.string.format_kilometers_away, new Object[]{new DecimalFormat("#0.0").format((double) (meters.floatValue() / 1000.0f))});
    }

    @SuppressLint({"StringFormatMatches"})
    public static String distanceFromMeters(Context context, Float distance) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.UIUtils", "distanceFromMeters", new Object[]{context, distance});
        if (distance == null) {
            return context.getString(C2658R.string.label_unknown_distance);
        }
        Boolean useMetric = (Boolean) Configuration.getSharedInstance().getValueForKey("interface.storelocator.metricSystem");
        if (useMetric == null || !useMetric.booleanValue()) {
            return metersToMiles(context, distance);
        }
        if (distance.floatValue() > 1000.0f) {
            return metersToKilometers(context, distance);
        }
        return context.getString(C2658R.string.format_meters_away, new Object[]{distance});
    }

    public static String distanceFromMeters(Context context, Double meters) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.UIUtils", "distanceFromMeters", new Object[]{context, meters});
        return distanceFromMeters(context, Float.valueOf(meters.floatValue()));
    }

    public static String distanceFromLocation(Context context, Location location, Store store) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.UIUtils", "distanceFromLocation", new Object[]{context, location, store});
        return distanceFromMeters(context, metersFromLocation(store, location));
    }

    public static String distanceFromStore(Context context, Store store) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.UIUtils", "distanceFromStore", new Object[]{context, store});
        try {
            Location location = AppUtils.getUserLocation();
            if (location != null) {
                return distanceFromLocation(context, location, store);
            }
        } catch (IllegalStateException e) {
        }
        return distanceFromMeters(context, Double.valueOf(store.getDistance()));
    }

    public static String formatDeliveryTimeInMinutes(Context context, Date currentDate, Date edt) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.UIUtils", "formatDeliveryTimeInMinutes", new Object[]{context, currentDate, edt});
        return context.getString(C2658R.string.edt_minutes, new Object[]{Long.valueOf(((edt.getTime() - currentDate.getTime()) / 60) / 1000)});
    }

    public static String formatTimeInMinutes(Context context, Date currentDate, Date edt, boolean roundToFiveMins) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.UIUtils", "formatTimeInMinutes", new Object[]{context, currentDate, edt, new Boolean(roundToFiveMins)});
        long difference = edt.getTime() - currentDate.getTime();
        long differenceInMinutes = difference / TimeUnit.MINUTES.toMillis(1);
        long fiveMinInSeconds = TimeUnit.MINUTES.toSeconds(1) * 5;
        if (roundToFiveMins && differenceInMinutes % 5 != 0) {
            differenceInMinutes = ((difference / 5) / TimeUnit.MINUTES.toMillis(1)) * 5;
            if ((difference / TimeUnit.SECONDS.toMillis(1)) % fiveMinInSeconds >= fiveMinInSeconds / 2) {
                differenceInMinutes += 5;
            }
        }
        return context.getString(C2658R.string.edt_minutes, new Object[]{Long.valueOf(differenceInMinutes)});
    }

    public static String formatDeliveryTimeRange(Context context, Date edt) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.UIUtils", "formatDeliveryTimeRange", new Object[]{context, edt});
        String edtRangeString = AppParameters.getAppParameter(AppParameters.EDT_RANGE);
        if (edtRangeString == null) {
            return "";
        }
        int edtRange = (Integer.parseInt(edtRangeString) * 60) * 1000;
        Date edtFrom = new Date(edt.getTime() - ((long) edtRange));
        Date edtTo = new Date(edt.getTime() + ((long) edtRange));
        String formattedFromDate = formatTime(context, edtFrom);
        String formattedToDate = formatTime(context, edtTo);
        return String.format("%s - %s", new Object[]{formattedFromDate, formattedToDate});
    }

    public static String formatTimeInSummary(Context context, Date date) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.UIUtils", "formatTimeInSummary", new Object[]{context, date});
        if (date == null) {
            return context.getResources().getString(C2658R.string.unknown);
        }
        SimpleDateFormat df = new SimpleDateFormat("h:mm a");
        if (LocalDataManager.getSharedInstance().getDeviceLanguage().toLowerCase().contains("zh")) {
            df.applyPattern("ah:mm");
        }
        return df.format(date);
    }

    public static String formatTime(Context context, Date date) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.UIUtils", "formatTime", new Object[]{context, date});
        return formatTime(context, date, TimeZone.getDefault());
    }

    public static String formatTime(Context context, Date date, TimeZone timeZone) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.UIUtils", "formatTime", new Object[]{context, date, timeZone});
        if (date != null) {
            DateFormat formatter;
            Calendar tomorrowStartCalendar = Calendar.getInstance();
            tomorrowStartCalendar.set(10, 0);
            tomorrowStartCalendar.set(12, 0);
            tomorrowStartCalendar.set(13, 0);
            tomorrowStartCalendar.add(5, 1);
            if (date.before(tomorrowStartCalendar.getTime())) {
                formatter = DateFormat.getTimeInstance(3, Locale.getDefault());
            } else {
                formatter = DateFormat.getDateTimeInstance(3, 3, Locale.getDefault());
            }
            formatter.setTimeZone(timeZone);
            return formatter.format(date);
        }
        MCDLog.error("Delivery Time", "delivery time not set");
        return context.getResources().getString(C2658R.string.unknown);
    }

    public static String formatDeliveryTime(Context context, Date orderDate, Date edt) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.UIUtils", "formatDeliveryTime", new Object[]{context, orderDate, edt});
        return formatDeliveryTime(context, orderDate, edt, false);
    }

    /* JADX WARNING: Missing block: B:14:0x0052, code skipped:
            if (r0.equals("fixed") != false) goto L_0x0046;
     */
    public static java.lang.String formatDeliveryTime(android.content.Context r10, java.util.Date r11, java.util.Date r12, boolean r13) {
        /*
        r4 = 2;
        r3 = 1;
        r1 = 0;
        r2 = 0;
        r5 = "com.mcdonalds.app.util.UIUtils";
        r6 = "formatDeliveryTime";
        r7 = 4;
        r7 = new java.lang.Object[r7];
        r7[r1] = r10;
        r7[r3] = r11;
        r7[r4] = r12;
        r8 = 3;
        r9 = new java.lang.Boolean;
        r9.<init>(r13);
        r7[r8] = r9;
        com.ensighten.Ensighten.evaluateEvent(r2, r5, r6, r7);
        r2 = com.mcdonalds.sdk.services.configuration.Configuration.getSharedInstance();
        r5 = "interface.edtFormat";
        r0 = r2.getValueForKey(r5);
        r0 = (java.lang.String) r0;
        if (r0 == 0) goto L_0x0035;
    L_0x002d:
        r2 = "none";
        r2 = r0.equals(r2);
        if (r2 == 0) goto L_0x0039;
    L_0x0035:
        if (r13 == 0) goto L_0x0039;
    L_0x0037:
        r0 = "fixed";
    L_0x0039:
        if (r12 == 0) goto L_0x0078;
    L_0x003b:
        if (r0 == 0) goto L_0x0078;
    L_0x003d:
        r2 = -1;
        r5 = r0.hashCode();
        switch(r5) {
            case 97445748: goto L_0x004c;
            case 108280125: goto L_0x0055;
            case 1064901855: goto L_0x005f;
            default: goto L_0x0045;
        };
    L_0x0045:
        r1 = r2;
    L_0x0046:
        switch(r1) {
            case 0: goto L_0x0069;
            case 1: goto L_0x006e;
            case 2: goto L_0x0073;
            default: goto L_0x0049;
        };
    L_0x0049:
        r1 = "";
    L_0x004b:
        return r1;
    L_0x004c:
        r3 = "fixed";
        r3 = r0.equals(r3);
        if (r3 == 0) goto L_0x0045;
    L_0x0054:
        goto L_0x0046;
    L_0x0055:
        r1 = "range";
        r1 = r0.equals(r1);
        if (r1 == 0) goto L_0x0045;
    L_0x005d:
        r1 = r3;
        goto L_0x0046;
    L_0x005f:
        r1 = "minutes";
        r1 = r0.equals(r1);
        if (r1 == 0) goto L_0x0045;
    L_0x0067:
        r1 = r4;
        goto L_0x0046;
    L_0x0069:
        r1 = formatTime(r10, r12);
        goto L_0x004b;
    L_0x006e:
        r1 = formatDeliveryTimeRange(r10, r12);
        goto L_0x004b;
    L_0x0073:
        r1 = formatDeliveryTimeInMinutes(r10, r11, r12);
        goto L_0x004b;
    L_0x0078:
        r1 = "";
        goto L_0x004b;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mcdonalds.app.util.UIUtils.formatDeliveryTime(android.content.Context, java.util.Date, java.util.Date, boolean):java.lang.String");
    }

    public static String formatScheduledDeliveryTime(Date deliveryDate) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.UIUtils", "formatScheduledDeliveryTime", new Object[]{deliveryDate});
        SimpleDateFormat deliveryTimeFormat = new SimpleDateFormat("hh:mm");
        SimpleDateFormat deliveryDateFormat = new SimpleDateFormat("dd/MM/yy");
        SimpleDateFormat deliveryDateFormatCN = new SimpleDateFormat("yyyy/MM/dd");
        String dateString = " ";
        Calendar deliveryCal = Calendar.getInstance();
        if (deliveryDate == null) {
            return dateString;
        }
        deliveryCal.setTime(deliveryDate);
        if (Configuration.getSharedInstance().getCurrentLanguageTag().equals("zh-CHS")) {
            return deliveryDateFormatCN.format(deliveryCal.getTime()) + " " + (deliveryCal.get(9) == 0 ? "上午" : "下午") + deliveryTimeFormat.format(deliveryCal.getTime());
        }
        return deliveryDateFormat.format(deliveryCal.getTime()) + ", " + deliveryTimeFormat.format(deliveryCal.getTime()) + " " + (deliveryCal.get(9) == 0 ? "am" : "pm");
    }

    public static String getDailyStoreHoursString(Context context, Store store) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.UIUtils", "getDailyStoreHoursString", new Object[]{context, store});
        int dayOfWeek = Calendar.getInstance().get(7);
        String storeHoursString = null;
        if (store.getStoreHours() != null && store.getStoreHours().size() > dayOfWeek - 1) {
            storeHoursString = (String) store.getStoreHours().get(dayOfWeek - 1);
        }
        if (storeHoursString == null) {
            return context.getString(C2658R.string.closed_label);
        }
        return storeHoursString;
    }

    public static String getDailyStoreHoursStringByLocationId(Context context, Store store, int locationId) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.UIUtils", "getDailyStoreHoursStringByLocationId", new Object[]{context, store, new Integer(locationId)});
        int dayOfWeek = Calendar.getInstance().get(7);
        List<MWOpeningHourItem> openingHours = null;
        List<MWLocation> locations = store.getLocations();
        MWLocation location = null;
        if (locations == null) {
            return null;
        }
        for (int j = 0; j < locations.size(); j++) {
            if (((MWLocation) locations.get(j)).getLocationID() == locationId) {
                location = (MWLocation) locations.get(j);
                break;
            }
        }
        if (location != null) {
            openingHours = location.getStoreAreaOpeningHours();
        }
        if (openingHours == null) {
            return null;
        }
        if (openingHours.size() >= dayOfWeek) {
            MWOpeningHourItem openingHour = (MWOpeningHourItem) openingHours.get(dayOfWeek - 1);
            return context.getString(C2658R.string.store_opening_hours_range, new Object[]{openingHour.fromTime, openingHour.toTime});
        }
        return context.getString(C2658R.string.store_opening_hours_range, new Object[]{"0", "0"});
    }

    public static NumberFormat getLocalizedCurrencyFormatter() {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.UIUtils", "getLocalizedCurrencyFormatter", null);
        return Configuration.getSharedInstance().getCurrencyFormatter();
    }

    public static void startActivityIndicator(Context context, @StringRes int msgResId) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.UIUtils", "startActivityIndicator", new Object[]{context, new Integer(msgResId)});
        if (context != null) {
            startActivityIndicator(context, context.getString(msgResId));
        }
    }

    public static void startActivityIndicator(Context context, String key, @StringRes int msgResId) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.UIUtils", "startActivityIndicator", new Object[]{context, key, new Integer(msgResId)});
        if (context != null) {
            startActivityIndicator(context, key, context.getString(msgResId), true);
        }
    }

    public static void startActivityIndicator(Context context, String message) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.UIUtils", "startActivityIndicator", new Object[]{context, message});
        startActivityIndicator(context, null, message, true);
    }

    public static void startActivityIndicator(final Context context, String key, String message, boolean cancellable) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.UIUtils", "startActivityIndicator", new Object[]{context, key, message, new Boolean(cancellable)});
        if ((!(context instanceof Activity) || !((Activity) context).isFinishing()) && context != null && !sProgressDisplayed) {
            sProgressDialog = new ProgressDialog(context, C2658R.style.f8039AppTheme.Dialog);
            sProgressDialog.setIndeterminate(true);
            sProgressDialog.setMessage(message);
            sProgressDialog.setCanceledOnTouchOutside(false);
            if (cancellable) {
                sProgressDialog.setOnCancelListener(new OnCancelListener() {
                    public void onCancel(DialogInterface dialogInterface) {
                        Ensighten.evaluateEvent(this, "onCancel", new Object[]{dialogInterface});
                        UIUtils.stopActivityIndicator();
                        if (context instanceof Activity) {
                            ((Activity) context).onBackPressed();
                        }
                    }
                });
                sProgressDialog.setCancelable(true);
            } else {
                sProgressDialog.setCancelable(false);
            }
            sProgressKey = key;
            sProgressDialog.show();
            sProgressDisplayed = true;
        }
    }

    public static void stopActivityIndicator() {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.UIUtils", "stopActivityIndicator", null);
        stopActivityIndicator(null);
    }

    public static void stopActivityIndicator(String key) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.UIUtils", "stopActivityIndicator", new Object[]{key});
        if (sProgressKey == null && key != null) {
            return;
        }
        if ((sProgressKey == null || sProgressKey.equals(key)) && sProgressDisplayed) {
            sProgressDisplayed = false;
            if (sProgressDialog != null && sProgressDialog.isShowing()) {
                try {
                    sProgressDialog.dismiss();
                    sProgressDialog = null;
                } catch (IllegalArgumentException e) {
                    MCDLog.error(LOG_TAG, "error dimissing dialog: " + e.getMessage());
                }
            }
        }
    }

    public static String formatDateMonthDayYear(Date date) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.UIUtils", "formatDateMonthDayYear", new Object[]{date});
        return SHORT_DATE_FORMAT.format(date);
    }

    public static void setDefaultRefreshColors(SwipeRefreshLayout refreshLayout) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.UIUtils", "setDefaultRefreshColors", new Object[]{refreshLayout});
        refreshLayout.setColorSchemeResources(C2658R.color.mcd_red, C2658R.color.mcd_yellow, C2658R.color.mcd_red, C2658R.color.mcd_yellow);
    }

    public static void dismissKeyboard(Context context, View view) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.UIUtils", "dismissKeyboard", new Object[]{context, view});
        ((InputMethodManager) context.getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static void showKeyboard(Context context, View editText, boolean shouldForce) {
        int i = 2;
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.UIUtils", "showKeyboard", new Object[]{context, editText, new Boolean(shouldForce)});
        if (context != null) {
            InputMethodManager imm = (InputMethodManager) context.getSystemService("input_method");
            if (!shouldForce) {
                i = 1;
            }
            imm.showSoftInput(editText, i);
        }
    }

    public static AlertDialog showGlobalAlertDialog(Context context, String title, String message, OnClickListener onClick) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.UIUtils", "showGlobalAlertDialog", new Object[]{context, title, message, onClick});
        AlertDialog dialog = new Builder(context, C2658R.style.f8039AppTheme.Dialog).setTitle((CharSequence) title).setMessage((CharSequence) message).setCancelable(true).setIcon((int) C2358R.C2359drawable.icon_warn).setNeutralButton(context.getString(C2658R.string.f6083ok), onClick).create();
        dialog.show();
        return dialog;
    }

    public static AlertDialog showCheckinFlowAlert(Context context, boolean shouldAllowOrdering, OnClickListener okClick, OnClickListener continueClick) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.UIUtils", "showCheckinFlowAlert", new Object[]{context, new Boolean(shouldAllowOrdering), okClick, continueClick});
        Builder builder = new Builder(context, C2658R.style.f8039AppTheme.Dialog).setTitle(context.getString(C2658R.string.duplicate_order_alert_dialog_title)).setCancelable(true).setIcon((int) C2358R.C2359drawable.icon_warn);
        AlertDialog dialog;
        if (shouldAllowOrdering) {
            builder.setMessage(context.getString(C2658R.string.duplicate_order_allow_ordering_alert_dialog_text)).setNegativeButton(context.getString(C2658R.string.f6083ok), okClick).setPositiveButton(context.getString(C2658R.string.continue_button), continueClick);
            dialog = builder.create();
            dialog.show();
            return dialog;
        }
        builder.setMessage(context.getString(C2658R.string.duplicate_order_alert_dialog_text)).setNeutralButton(context.getString(C2658R.string.f6083ok), okClick);
        dialog = builder.create();
        dialog.show();
        return dialog;
    }

    public static boolean isEmailValid(String text) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.UIUtils", "isEmailValid", new Object[]{text});
        return text.matches(sEmailPattern);
    }

    public static boolean isNumberValid(String text) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.UIUtils", "isNumberValid", new Object[]{text});
        return text.matches(sNumberPattern);
    }

    public static String getDayOfMonthSuffix(int n) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.UIUtils", "getDayOfMonthSuffix", new Object[]{new Integer(n)});
        if (n >= 11 && n <= 13) {
            return "th";
        }
        switch (n % 10) {
            case 1:
                return "st";
            case 2:
                return "nd";
            case 3:
                return "rd";
            default:
                return "th";
        }
    }

    public static Date getDateFromISO8601(String iso8601FormattedString) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.UIUtils", "getDateFromISO8601", new Object[]{iso8601FormattedString});
        return getDateFromISO8601(iso8601FormattedString, TimeZone.getTimeZone("UTC"));
    }

    public static Date getDateFromISO8601(String iso8601FormattedString, TimeZone timeZone) {
        Object obj = null;
        Ensighten.evaluateEvent(obj, "com.mcdonalds.app.util.UIUtils", "getDateFromISO8601", new Object[]{iso8601FormattedString, timeZone});
        try {
            ISO8601_FORMAT.setTimeZone(timeZone);
            return ISO8601_FORMAT.parse(iso8601FormattedString);
        } catch (ParseException e) {
            return obj;
        }
    }

    public static boolean isPasswordValid(String password) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.UIUtils", "isPasswordValid", new Object[]{password});
        String pwdRegex = Configuration.getSharedInstance().getStringForKey("textValidation.passwordRegex");
        if (Strings.isNullOrEmpty(pwdRegex)) {
            return password.matches(sPwdPattern);
        }
        return password.matches(pwdRegex);
    }

    public static boolean isPhoneValid(String phone) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.UIUtils", "isPhoneValid", new Object[]{phone});
        String phoneRegex = Configuration.getSharedInstance().getStringForKey("textValidation.phoneRegex");
        if (Strings.isNullOrEmpty(phoneRegex)) {
            return true;
        }
        return phone.matches(phoneRegex);
    }

    public static void showNoNetworkAlert(Context context) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.UIUtils", "showNoNetworkAlert", new Object[]{context});
        MCDAlertDialogBuilder.withContext(context).setMessage((int) C2658R.string.trouble_connecting_message).setPositiveButton((int) C2658R.string.f6083ok, null).create().show();
    }

    public static void addPagerIndicatorDot(int i, Context context, RadioGroup group) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.UIUtils", "addPagerIndicatorDot", new Object[]{new Integer(i), context, group});
        int size = dpAsPixels(context, 7);
        int margin = dpAsPixels(context, 4);
        int padding = dpAsPixels(context, 8);
        RadioGroup.LayoutParams lp = new RadioGroup.LayoutParams(size, size);
        lp.setMargins(margin, 0, margin, 0);
        RadioButton indicator = (RadioButton) LayoutInflater.from(context).inflate(C2658R.layout.pager_indicator_dot, null);
        indicator.setPadding(padding, 0, padding, 0);
        indicator.setLayoutParams(lp);
        indicator.setId(i);
        group.addView(indicator);
    }

    public static void showInvalidDayPartsError(Context context) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.UIUtils", "showInvalidDayPartsError", new Object[]{context});
        MCDAlertDialogBuilder.withContext(context).setTitle((int) C2658R.string.warning).setMessage((int) C2658R.string.daypart_mixing_warning).setNeutralButton((int) C2658R.string.f6083ok, null).create().show();
        DataLayerManager.getInstance().recordError("Invalid day part");
    }

    public static void showInvalidCurrentDayPart(Context context, String message, OnClickListener listener) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.UIUtils", "showInvalidCurrentDayPart", new Object[]{context, message, listener});
        MCDAlertDialogBuilder.withContext(context).setTitle((int) C2658R.string.warning).setMessage(message).setNeutralButton((int) C2658R.string.f6083ok, listener).create().show();
        DataLayerManager.getInstance().recordError("Invalid day part");
    }

    public static void showTimeRestrictionAlert(TextView view, List<TimeRestriction> timeRestrictions) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.UIUtils", "showTimeRestrictionAlert", new Object[]{view, timeRestrictions});
        if (timeRestrictions == null || timeRestrictions.size() <= 0) {
            view.setVisibility(8);
            return;
        }
        String fromTime = "";
        String toTime = "";
        String fromTime2 = "";
        String toTime2 = "";
        for (TimeRestriction timeRestriction : timeRestrictions) {
            if (!fromTime.equals("")) {
                fromTime2 = timeRestriction.getFromTime();
                toTime2 = timeRestriction.getToTime();
                break;
            }
            fromTime = timeRestriction.getFromTime();
            toTime = timeRestriction.getToTime();
        }
        if (timeRestrictions.size() > 1) {
            view.setText(String.format(view.getContext().getString(C2658R.string.available_time2), new Object[]{fromTime, toTime, fromTime2, toTime2}));
        } else {
            view.setText(String.format(view.getContext().getString(C2658R.string.available_time), new Object[]{fromTime, toTime}));
        }
        view.setVisibility(0);
    }

    public static String getCurrentTimestampUsingFormat(String dateFormat, String timezoneId) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.UIUtils", "getCurrentTimestampUsingFormat", new Object[]{dateFormat, timezoneId});
        return getTimestampUsingFormat(dateFormat, timezoneId, null, null);
    }

    public static String getTimestampUsingFormat(String dateFormat, String timezoneId, Calendar date, Locale locale) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.UIUtils", "getTimestampUsingFormat", new Object[]{dateFormat, timezoneId, date, locale});
        TimeZone timeZone = timezoneId == null ? TimeZone.getTimeZone("UTC") : TimeZone.getTimeZone(timezoneId);
        if (date == null) {
            date = Calendar.getInstance(timeZone);
            date.setTimeZone(timeZone);
        }
        if (locale == null) {
            locale = Locale.getDefault();
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat, locale);
        simpleDateFormat.setTimeZone(timeZone);
        return simpleDateFormat.format(date.getTime());
    }

    public static void getDayPartImageUrl(RequestManagerServiceConnection serviceConnection, final int position, final AsyncListener<String> listener) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.UIUtils", "getDayPartImageUrl", new Object[]{serviceConnection, new Integer(position), listener});
        if (sDayPartsResponse != null) {
            sendDayPartsResponse(position, listener);
            return;
        }
        if (sDayPartsListener == null) {
            sDayPartsListener = new DayPartsListener();
        }
        sDayPartsListener.addObserver(new Observer() {
            public void update(Observable observable, Object data) {
                Ensighten.evaluateEvent(this, "update", new Object[]{observable, data});
                UIUtils.access$100(position, listener);
                observable.deleteObserver(this);
            }
        });
        if (!sLoadingDayParts) {
            sLoadingDayParts = true;
            serviceConnection.processRequest(new SimpleJsonRequest((String) Configuration.getSharedInstance().getValueForKey("interface.dayparts.topImagesJson"), DayPartsResponse.class), sDayPartsListener);
        }
    }

    private static void sendDayPartsResponse(int position, AsyncListener<String> listener) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.UIUtils", "sendDayPartsResponse", new Object[]{new Integer(position), listener});
        listener.onResponse(sDayPartsResponse.getUrl(position), null, null);
    }

    public static void disableInteraction(TextView tv) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.UIUtils", "disableInteraction", new Object[]{tv});
        tv.setLongClickable(false);
        tv.setTextIsSelectable(false);
        tv.setCustomSelectionActionModeCallback(new C24714());
    }

    public static void addDisclaimerTextView(ViewGroup rootView, Context context, String currentView) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.UIUtils", "addDisclaimerTextView", new Object[]{rootView, context, currentView});
        rootView.removeAllViews();
        Iterator it = getOrderingDisclaimerItems(context).iterator();
        while (it.hasNext()) {
            OrderingDisclaimerItem item = (OrderingDisclaimerItem) it.next();
            if (currentView.equals("basketView") && item.hasBasketView()) {
                addView(rootView, context, item);
            } else if (currentView.equals("productView") && item.hasProductView()) {
                addView(rootView, context, item);
            } else if (currentView.equals("menuGridView") && item.hasMenuGridView()) {
                addView(rootView, context, item);
            } else if (currentView.equals("orderSummaryView") && item.hasOrderSummaryView()) {
                addView(rootView, context, item);
            }
        }
    }

    private static void addView(ViewGroup rootView, Context context, OrderingDisclaimerItem item) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.UIUtils", "addView", new Object[]{rootView, context, item});
        if (context != null) {
            TextView textView = (TextView) LayoutInflater.from(context).inflate(C2658R.layout.disclaimer_text_view, rootView, false);
            textView.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
            textView.setText(item.getDescription());
            if (item.useThemeColor()) {
                textView.setTextColor(context.getResources().getColor(C2658R.color.mcd_red));
            }
            if (item.useBoldText()) {
                textView.setTypeface(textView.getTypeface(), 1);
            }
            rootView.addView(textView);
        }
    }

    private static ArrayList<OrderingDisclaimerItem> getOrderingDisclaimerItems(Context context) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.UIUtils", "getOrderingDisclaimerItems", new Object[]{context});
        if (orderingDisclaimerItems.size() > 0) {
            return orderingDisclaimerItems;
        }
        List<Map<String, Object>> items = (List) Configuration.getSharedInstance().getValueForKey("interface.orderingDisclaimerInfo");
        if (items != null) {
            for (Map<String, Object> item : items) {
                if ((!Configuration.getSharedInstance().getBooleanForKey("interface.hideProductCustomizationButton") && !AppUtils.hideNutritionOnOrderingPages()) || !((String) item.get("warningText")).equals("energy_warning_label_text")) {
                    orderingDisclaimerItems.add(new OrderingDisclaimerItem(getStringByName(context, (String) item.get("warningText")), ((Boolean) item.get("useBoldText")).booleanValue(), ((Boolean) item.get("useThemeColor")).booleanValue(), ((Boolean) item.get("productView")).booleanValue(), ((Boolean) item.get("basketView")).booleanValue(), ((Boolean) item.get("menuGridView")).booleanValue(), ((Boolean) item.get("orderSummaryView")).booleanValue()));
                }
            }
        }
        return orderingDisclaimerItems;
    }
}
