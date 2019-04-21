package com.facebook.android;

import android.content.Context;
import android.os.Bundle;
import com.facebook.FacebookDialogException;
import com.facebook.FacebookException;
import com.facebook.FacebookOperationCanceledException;
import com.facebook.android.Facebook.DialogListener;
import com.facebook.widget.WebDialog;
import com.facebook.widget.WebDialog.OnCompleteListener;

@Deprecated
public class FbDialog extends WebDialog {
    private DialogListener mListener;

    /* renamed from: com.facebook.android.FbDialog$1 */
    class C19201 implements OnCompleteListener {
        C19201() {
        }

        public void onComplete(Bundle values, FacebookException error) {
            FbDialog.this.callDialogListener(values, error);
        }
    }

    public FbDialog(Context context, String url, DialogListener listener) {
        this(context, url, listener, (int) WebDialog.DEFAULT_THEME);
    }

    public FbDialog(Context context, String url, DialogListener listener, int theme) {
        super(context, url, theme);
        setDialogListener(listener);
    }

    public FbDialog(Context context, String action, Bundle parameters, DialogListener listener) {
        super(context, action, parameters, WebDialog.DEFAULT_THEME, null);
        setDialogListener(listener);
    }

    public FbDialog(Context context, String action, Bundle parameters, DialogListener listener, int theme) {
        super(context, action, parameters, theme, null);
        setDialogListener(listener);
    }

    private void setDialogListener(DialogListener listener) {
        this.mListener = listener;
        setOnCompleteListener(new C19201());
    }

    private void callDialogListener(Bundle values, FacebookException error) {
        if (this.mListener != null) {
            if (values != null) {
                this.mListener.onComplete(values);
            } else if (error instanceof FacebookDialogException) {
                FacebookDialogException facebookDialogException = (FacebookDialogException) error;
                this.mListener.onError(new DialogError(facebookDialogException.getMessage(), facebookDialogException.getErrorCode(), facebookDialogException.getFailingUrl()));
            } else if (error instanceof FacebookOperationCanceledException) {
                this.mListener.onCancel();
            } else {
                this.mListener.onFacebookError(new FacebookError(error.getMessage()));
            }
        }
    }
}
