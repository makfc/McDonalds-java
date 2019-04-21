package com.mcdonalds.app.startup;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import com.ensighten.Ensighten;
import com.mcdonalds.app.util.UIUtils;
import com.mcdonalds.gma.hongkong.C2658R;
import com.newrelic.agent.android.api.p047v2.TraceFieldInterface;
import com.newrelic.agent.android.instrumentation.HttpInstrumentation;
import com.newrelic.agent.android.tracing.Trace;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

class DownloadApkFileFromUrl extends AsyncTask<URL, Void, Boolean> implements TraceFieldInterface {
    public Trace _nr_trace;
    private Activity mActivity;
    private File mFile;

    public void _nr_setTrace(Trace trace) {
        try {
            this._nr_trace = trace;
        } catch (Exception e) {
        }
    }

    public DownloadApkFileFromUrl(Activity a) {
        this.mActivity = a;
    }

    /* Access modifiers changed, original: protected */
    public void onPreExecute() {
        Ensighten.evaluateEvent(this, "onPreExecute", null);
        this.mFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), this.mActivity.getPackageName());
        this.mActivity.getWindow().setFlags(128, 128);
        UIUtils.startActivityIndicator(this.mActivity, this.mActivity.getString(C2658R.string.binary_download_progress_message));
    }

    /* Access modifiers changed, original: protected|varargs */
    public Boolean doInBackground(URL... params) {
        Ensighten.evaluateEvent(this, "doInBackground", new Object[]{params});
        try {
            HttpURLConnection httpConnection = createURLConnection(params[0].toString());
            httpConnection.setConnectTimeout(200000);
            httpConnection.connect();
            InputStream input = new BufferedInputStream(httpConnection.getInputStream());
            OutputStream output = new FileOutputStream(this.mFile);
            byte[] data = new byte[1024];
            while (true) {
                int count = input.read(data);
                if (count != -1) {
                    output.write(data, 0, count);
                } else {
                    output.flush();
                    output.close();
                    input.close();
                    return Boolean.valueOf(true);
                }
            }
        } catch (Exception e) {
            this.mFile.delete();
            return Boolean.valueOf(false);
        }
    }

    private HttpURLConnection createURLConnection(String url) throws IOException {
        Ensighten.evaluateEvent(this, "createURLConnection", new Object[]{url});
        URLConnection connection = HttpInstrumentation.openConnection(new URL(url).openConnection());
        connection.setUseCaches(false);
        connection.setAllowUserInteraction(true);
        connection.setRequestProperty("connection", "close");
        return (HttpURLConnection) connection;
    }

    /* Access modifiers changed, original: protected */
    public void onPostExecute(Boolean result) {
        Ensighten.evaluateEvent(this, "onPostExecute", new Object[]{result});
        UIUtils.stopActivityIndicator();
        if (result.booleanValue()) {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setDataAndType(Uri.fromFile(this.mFile), "application/vnd.android.package-archive");
            this.mActivity.startActivity(intent);
        }
        this.mActivity.finish();
    }
}
