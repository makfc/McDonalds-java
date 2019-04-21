package com.facebook.android;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.os.Bundle;
import com.facebook.internal.NativeProtocol;
import com.facebook.internal.ServerProtocol;
import com.facebook.internal.Utility;
import com.newrelic.agent.android.agentdata.HexAttributes;
import com.newrelic.agent.android.instrumentation.HttpInstrumentation;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import com.newrelic.agent.android.instrumentation.TransactionStateUtil;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import org.json.JSONException;
import org.json.JSONObject;

public final class Util {
    private static final String UTF8 = "UTF-8";

    @Deprecated
    public static String encodePostBody(Bundle parameters, String boundary) {
        if (parameters == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (String key : parameters.keySet()) {
            Object parameter = parameters.get(key);
            if (parameter instanceof String) {
                sb.append("Content-Disposition: form-data; name=\"" + key + "\"\r\n\r\n" + ((String) parameter));
                sb.append("\r\n--" + boundary + "\r\n");
            }
        }
        return sb.toString();
    }

    @Deprecated
    public static String encodeUrl(Bundle parameters) {
        if (parameters == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (String key : parameters.keySet()) {
            if (parameters.get(key) instanceof String) {
                if (first) {
                    first = false;
                } else {
                    sb.append("&");
                }
                sb.append(URLEncoder.encode(key) + "=" + URLEncoder.encode(parameters.getString(key)));
            }
        }
        return sb.toString();
    }

    @Deprecated
    public static Bundle decodeUrl(String s) {
        Bundle params = new Bundle();
        if (s != null) {
            for (String parameter : s.split("&")) {
                String[] v = parameter.split("=");
                try {
                    if (v.length == 2) {
                        params.putString(URLDecoder.decode(v[0], "UTF-8"), URLDecoder.decode(v[1], "UTF-8"));
                    } else if (v.length == 1) {
                        params.putString(URLDecoder.decode(v[0], "UTF-8"), "");
                    }
                } catch (UnsupportedEncodingException e) {
                }
            }
        }
        return params;
    }

    @Deprecated
    public static Bundle parseUrl(String url) {
        try {
            URL u = new URL(url.replace("fbconnect", "http"));
            Bundle b = decodeUrl(u.getQuery());
            b.putAll(decodeUrl(u.getRef()));
            return b;
        } catch (MalformedURLException e) {
            return new Bundle();
        }
    }

    @Deprecated
    public static String openUrl(String url, String method, Bundle params) throws MalformedURLException, IOException {
        String strBoundary = "3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f";
        String endLine = "\r\n";
        if (method.equals("GET")) {
            url = url + "?" + encodeUrl(params);
        }
        Utility.logd("Facebook-Util", method + " URL: " + url);
        HttpURLConnection conn = (HttpURLConnection) HttpInstrumentation.openConnection(new URL(url).openConnection());
        conn.setRequestProperty("User-Agent", System.getProperties().getProperty("http.agent") + " FacebookAndroidSDK");
        if (!method.equals("GET")) {
            Bundle dataparams = new Bundle();
            for (String key : params.keySet()) {
                Object parameter = params.get(key);
                if (parameter instanceof byte[]) {
                    dataparams.putByteArray(key, (byte[]) parameter);
                }
            }
            if (!params.containsKey("method")) {
                params.putString("method", method);
            }
            if (params.containsKey("access_token")) {
                String decoded_token = URLDecoder.decode(params.getString("access_token"));
                params.putString("access_token", decoded_token);
            }
            conn.setRequestMethod("POST");
            conn.setRequestProperty(TransactionStateUtil.CONTENT_TYPE_HEADER, "multipart/form-data;boundary=" + strBoundary);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.connect();
            OutputStream os = new BufferedOutputStream(conn.getOutputStream());
            try {
                os.write(("--" + strBoundary + endLine).getBytes());
                os.write(encodePostBody(params, strBoundary).getBytes());
                os.write((endLine + "--" + strBoundary + endLine).getBytes());
                if (!dataparams.isEmpty()) {
                    for (String key2 : dataparams.keySet()) {
                        os.write(("Content-Disposition: form-data; filename=\"" + key2 + "\"" + endLine).getBytes());
                        os.write(("Content-Type: content/unknown" + endLine + endLine).getBytes());
                        os.write(dataparams.getByteArray(key2));
                        os.write((endLine + "--" + strBoundary + endLine).getBytes());
                    }
                }
                os.flush();
            } finally {
                os.close();
            }
        }
        String response = "";
        try {
            return read(conn.getInputStream());
        } catch (FileNotFoundException e) {
            return read(conn.getErrorStream());
        }
    }

    @Deprecated
    private static String read(InputStream in) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader r = new BufferedReader(new InputStreamReader(in), 1000);
        for (String line = r.readLine(); line != null; line = r.readLine()) {
            sb.append(line);
        }
        in.close();
        return sb.toString();
    }

    @Deprecated
    public static JSONObject parseJson(String response) throws JSONException, FacebookError {
        if (response.equals("false")) {
            throw new FacebookError("request failed");
        }
        if (response.equals(ServerProtocol.DIALOG_RETURN_SCOPES_TRUE)) {
            response = "{value : true}";
        }
        JSONObject json = JSONObjectInstrumentation.init(response);
        if (json.has("error")) {
            JSONObject error = json.getJSONObject("error");
            throw new FacebookError(error.getString(HexAttributes.HEX_ATTR_MESSAGE), error.getString("type"), 0);
        } else if (json.has(NativeProtocol.BRIDGE_ARG_ERROR_CODE) && json.has("error_msg")) {
            throw new FacebookError(json.getString("error_msg"), "", Integer.parseInt(json.getString(NativeProtocol.BRIDGE_ARG_ERROR_CODE)));
        } else if (json.has(NativeProtocol.BRIDGE_ARG_ERROR_CODE)) {
            throw new FacebookError("request failed", "", Integer.parseInt(json.getString(NativeProtocol.BRIDGE_ARG_ERROR_CODE)));
        } else if (json.has("error_msg")) {
            throw new FacebookError(json.getString("error_msg"));
        } else if (!json.has("error_reason")) {
            return json;
        } else {
            throw new FacebookError(json.getString("error_reason"));
        }
    }

    @Deprecated
    public static void showAlert(Context context, String title, String text) {
        Builder alertBuilder = new Builder(context);
        alertBuilder.setTitle(title);
        alertBuilder.setMessage(text);
        alertBuilder.create().show();
    }
}
