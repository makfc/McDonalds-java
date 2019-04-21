package org.acra;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnDismissListener;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class CrashReportDialog extends BaseCrashReportDialog implements OnClickListener, OnDismissListener {
    AlertDialog mDialog;
    private EditText userCommentView;
    private EditText userEmailView;

    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Builder builder = new Builder(this);
        int resDialogTitle = ACRA.getConfig().resDialogTitle();
        if (resDialogTitle != 0) {
            builder.setTitle(resDialogTitle);
        }
        resDialogTitle = ACRA.getConfig().resDialogIcon();
        if (resDialogTitle != 0) {
            builder.setIcon(resDialogTitle);
        }
        builder.setView(buildCustomView(savedInstanceState));
        builder.setPositiveButton(getText(ACRA.getConfig().resDialogPositiveButtonText()), this);
        builder.setNegativeButton(getText(ACRA.getConfig().resDialogNegativeButtonText()), this);
        this.mDialog = builder.create();
        this.mDialog.setCanceledOnTouchOutside(false);
        this.mDialog.setOnDismissListener(this);
        this.mDialog.show();
    }

    /* Access modifiers changed, original: protected */
    public View buildCustomView(Bundle savedInstanceState) {
        TextView textView;
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(1);
        linearLayout.setPadding(10, 10, 10, 10);
        linearLayout.setLayoutParams(new LayoutParams(-1, -1));
        linearLayout.setFocusable(true);
        linearLayout.setFocusableInTouchMode(true);
        ScrollView scrollView = new ScrollView(this);
        linearLayout.addView(scrollView, new LinearLayout.LayoutParams(-1, -1, 1.0f));
        LinearLayout linearLayout2 = new LinearLayout(this);
        linearLayout2.setOrientation(1);
        scrollView.addView(linearLayout2);
        TextView textView2 = new TextView(this);
        int resDialogText = ACRA.getConfig().resDialogText();
        if (resDialogText != 0) {
            textView2.setText(getText(resDialogText));
        }
        linearLayout2.addView(textView2);
        int resDialogCommentPrompt = ACRA.getConfig().resDialogCommentPrompt();
        if (resDialogCommentPrompt != 0) {
            textView = new TextView(this);
            textView.setText(getText(resDialogCommentPrompt));
            textView.setPadding(textView.getPaddingLeft(), 10, textView.getPaddingRight(), textView.getPaddingBottom());
            linearLayout2.addView(textView, new LinearLayout.LayoutParams(-1, -2));
            this.userCommentView = new EditText(this);
            this.userCommentView.setLines(2);
            if (savedInstanceState != null) {
                String string = savedInstanceState.getString("comment");
                if (string != null) {
                    this.userCommentView.setText(string);
                }
            }
            linearLayout2.addView(this.userCommentView);
        }
        resDialogCommentPrompt = ACRA.getConfig().resDialogEmailPrompt();
        if (resDialogCommentPrompt != 0) {
            textView = new TextView(this);
            textView.setText(getText(resDialogCommentPrompt));
            textView.setPadding(textView.getPaddingLeft(), 10, textView.getPaddingRight(), textView.getPaddingBottom());
            linearLayout2.addView(textView);
            this.userEmailView = new EditText(this);
            this.userEmailView.setSingleLine();
            this.userEmailView.setInputType(33);
            CharSequence charSequence = null;
            if (savedInstanceState != null) {
                charSequence = savedInstanceState.getString("email");
            }
            if (charSequence != null) {
                this.userEmailView.setText(charSequence);
            } else {
                this.userEmailView.setText(ACRA.getACRASharedPreferences().getString(ACRA.PREF_USER_EMAIL_ADDRESS, ""));
            }
            linearLayout2.addView(this.userEmailView);
        }
        return linearLayout;
    }

    public void onClick(DialogInterface dialog, int which) {
        if (which == -1) {
            String obj;
            String obj2 = this.userCommentView != null ? this.userCommentView.getText().toString() : "";
            SharedPreferences aCRASharedPreferences = ACRA.getACRASharedPreferences();
            if (this.userEmailView != null) {
                obj = this.userEmailView.getText().toString();
                Editor edit = aCRASharedPreferences.edit();
                edit.putString(ACRA.PREF_USER_EMAIL_ADDRESS, obj);
                edit.commit();
            } else {
                obj = aCRASharedPreferences.getString(ACRA.PREF_USER_EMAIL_ADDRESS, "");
            }
            sendCrash(obj2, obj);
        } else {
            cancelReports();
        }
        finish();
    }

    public void onDismiss(DialogInterface dialog) {
        finish();
    }

    /* Access modifiers changed, original: protected */
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (!(this.userCommentView == null || this.userCommentView.getText() == null)) {
            outState.putString("comment", this.userCommentView.getText().toString());
        }
        if (this.userEmailView != null && this.userEmailView.getText() != null) {
            outState.putString("email", this.userEmailView.getText().toString());
        }
    }
}
