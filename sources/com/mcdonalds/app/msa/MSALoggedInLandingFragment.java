package com.mcdonalds.app.msa;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.p043ui.URLNavigationActivity;
import com.mcdonalds.app.p043ui.URLNavigationFragment;
import com.mcdonalds.gma.hongkong.C2658R;
import java.text.DecimalFormat;
import java.util.Calendar;

public class MSALoggedInLandingFragment extends URLNavigationFragment {
    private Handler mHandler;

    /* renamed from: com.mcdonalds.app.msa.MSALoggedInLandingFragment$1 */
    class C32651 implements OnClickListener {
        C32651() {
        }

        public void onClick(View view) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{view});
            Bundle b = new Bundle();
            b.putString(URLNavigationActivity.ARG_FRAGMENT, "msa");
            MSALoggedInLandingFragment.this.startActivity(MsaActivity.class, b);
        }
    }

    static /* synthetic */ void access$000(MSALoggedInLandingFragment x0, TextView x1, TextView x2, TextView x3) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.msa.MSALoggedInLandingFragment", "access$000", new Object[]{x0, x1, x2, x3});
        x0.setCurrentTime(x1, x2, x3);
    }

    static /* synthetic */ void access$100(MSALoggedInLandingFragment x0, TextView x1, TextView x2, TextView x3) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.msa.MSALoggedInLandingFragment", "access$100", new Object[]{x0, x1, x2, x3});
        x0.setupRefresh(x1, x2, x3);
    }

    /* Access modifiers changed, original: protected */
    public String getTitle() {
        Ensighten.evaluateEvent(this, "getTitle", null);
        return getString(C2658R.string.appmenu_msa);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(C2658R.layout.fragment_msa_landing_page_logged_in, null);
        this.mHandler = new Handler();
        TextView hour = (TextView) rootView.findViewById(C2358R.C2357id.msa_time_hour);
        TextView min = (TextView) rootView.findViewById(C2358R.C2357id.msa_time_minute);
        TextView amPm = (TextView) rootView.findViewById(C2358R.C2357id.msa_time_am_pm);
        setCurrentTime(amPm, hour, min);
        rootView.findViewById(C2358R.C2357id.msa_landing_set_alarm).setOnClickListener(new C32651());
        setupRefresh(amPm, hour, min);
        return rootView;
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.mHandler != null) {
            this.mHandler.removeCallbacksAndMessages(null);
            this.mHandler = null;
        }
    }

    private void setupRefresh(final TextView amPmView, final TextView hourView, final TextView minuteView) {
        Ensighten.evaluateEvent(this, "setupRefresh", new Object[]{amPmView, hourView, minuteView});
        if (this.mHandler != null) {
            Calendar c = Calendar.getInstance();
            long timeInMillisNow = c.getTimeInMillis();
            c.add(12, 1);
            c.set(13, 0);
            c.set(14, 100);
            this.mHandler.postDelayed(new Runnable() {
                public void run() {
                    Ensighten.evaluateEvent(this, "run", null);
                    MSALoggedInLandingFragment.access$000(MSALoggedInLandingFragment.this, amPmView, hourView, minuteView);
                    MSALoggedInLandingFragment.access$100(MSALoggedInLandingFragment.this, amPmView, hourView, minuteView);
                }
            }, c.getTimeInMillis() - timeInMillisNow);
        }
    }

    private void setCurrentTime(TextView amPmView, TextView hourView, TextView minuteView) {
        Ensighten.evaluateEvent(this, "setCurrentTime", new Object[]{amPmView, hourView, minuteView});
        if (amPmView != null && hourView != null && minuteView != null && this.mHandler != null) {
            Calendar now = Calendar.getInstance();
            int hourNum = now.get(11);
            if (hourNum == 12) {
                amPmView.setText(getString(C2658R.string.msa_time_pm));
            } else if (hourNum > 12) {
                amPmView.setText(getString(C2658R.string.msa_time_pm));
                hourNum -= 12;
            } else {
                amPmView.setText(getString(C2658R.string.msa_time_am));
            }
            int minuteNum = now.get(12);
            hourView.setText(Integer.toString(hourNum));
            minuteView.setText(new DecimalFormat("00").format((long) minuteNum));
        }
    }
}
