package com.mcdonalds.app.ordering.start;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TimePicker;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.ordering.OrderingManager;
import com.mcdonalds.app.p043ui.URLNavigationFragment;
import com.mcdonalds.app.util.UIUtils.MCDAlertDialogBuilder;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.modules.models.Order;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DeliveryDateTimeFragment extends URLNavigationFragment {
    public static final String NAME = DeliveryDateTimeFragment.class.getSimpleName();
    DatePicker mDatePicker;
    private Order mOrder;
    TimePicker mTimePicker;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        this.mOrder = OrderingManager.getInstance().getCurrentOrder();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C2658R.layout.fragment_delivery_date_time, container, false);
        this.mDatePicker = (DatePicker) view.findViewById(C2358R.C2357id.datePicker);
        this.mTimePicker = (TimePicker) view.findViewById(C2358R.C2357id.timePicker);
        initializeDate();
        return view;
    }

    public void onResume() {
        super.onResume();
        this.mDatePicker.setMinDate(System.currentTimeMillis() - 1000);
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        Ensighten.evaluateEvent(this, "onCreateOptionsMenu", new Object[]{menu, inflater});
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(C2358R.C2360menu.delivery_date_time, menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        Ensighten.evaluateEvent(this, "onOptionsItemSelected", new Object[]{item});
        switch (item.getItemId()) {
            case C2358R.C2357id.action_cancel /*2131820688*/:
                getActivity().setResult(0);
                getActivity().finish();
                return true;
            case C2358R.C2357id.action_save /*2131821893*/:
                onSave();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void onSave() {
        Ensighten.evaluateEvent(this, "onSave", null);
        GregorianCalendar deliveryTime = new GregorianCalendar(this.mDatePicker.getYear(), this.mDatePicker.getMonth(), this.mDatePicker.getDayOfMonth(), this.mTimePicker.getCurrentHour().intValue(), this.mTimePicker.getCurrentMinute().intValue());
        if (deliveryTime.getTime().after(Calendar.getInstance().getTime())) {
            Intent intent = new Intent();
            intent.putExtra("DATE_RETURN_KEY", deliveryTime.getTime());
            getActivity().setResult(-1, intent);
            getActivity().finish();
            return;
        }
        MCDAlertDialogBuilder.withContext(getNavigationActivity()).setCancelable(false).setNeutralButton((int) C2658R.string.f6083ok, null).setMessage(getResources().getString(C2658R.string.date_before_current_time)).create().show();
    }

    private void initializeDate() {
        Date date;
        Ensighten.evaluateEvent(this, "initializeDate", null);
        Calendar calendar = Calendar.getInstance();
        if (this.mOrder.getDeliveryDate() != null) {
            date = this.mOrder.getDeliveryDate();
        } else {
            date = calendar.getTime();
        }
        calendar.setTime(date);
        this.mDatePicker.updateDate(calendar.get(1), calendar.get(2), calendar.get(5));
        this.mTimePicker.setCurrentHour(Integer.valueOf(calendar.get(11)));
        this.mTimePicker.setCurrentMinute(Integer.valueOf(calendar.get(12)));
    }
}
