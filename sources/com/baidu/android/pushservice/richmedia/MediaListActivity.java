package com.baidu.android.pushservice.richmedia;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;
import com.baidu.android.pushservice.p036h.C1425a;
import com.baidu.android.pushservice.richmedia.C1527c.C1526a;
import com.baidu.android.pushservice.util.C1568q;
import com.baidu.android.pushservice.util.C1568q.C1563h;
import com.baidu.android.pushservice.util.C1578v;
import com.mcdonalds.sdk.modules.notification.PushConstants;
import com.newrelic.agent.android.api.p047v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.tracing.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Instrumented
public class MediaListActivity extends Activity implements TraceFieldInterface {
    /* renamed from: r */
    private static String f5308r = "downloadUrl";
    public Trace _nr_trace;
    /* renamed from: a */
    ArrayList<HashMap<String, Object>> f5309a;
    /* renamed from: b */
    NotificationManager f5310b;
    /* renamed from: c */
    private ListView f5311c;
    /* renamed from: d */
    private int f5312d;
    /* renamed from: e */
    private int f5313e;
    /* renamed from: f */
    private int f5314f;
    /* renamed from: g */
    private int f5315g;
    /* renamed from: h */
    private int f5316h;
    /* renamed from: i */
    private int f5317i;
    /* renamed from: j */
    private int f5318j;
    /* renamed from: k */
    private int f5319k;
    /* renamed from: l */
    private LinearLayout f5320l = null;
    /* renamed from: m */
    private RemoteViews f5321m;
    /* renamed from: n */
    private int f5322n;
    /* renamed from: o */
    private int f5323o;
    /* renamed from: p */
    private int f5324p;
    /* renamed from: q */
    private int f5325q;
    /* renamed from: s */
    private final OnItemClickListener f5326s = new C15152();
    /* renamed from: t */
    private final OnItemLongClickListener f5327t = new C15183();

    /* renamed from: com.baidu.android.pushservice.richmedia.MediaListActivity$1 */
    class C15141 implements OnClickListener {
        C15141() {
        }

        public void onClick(View view) {
            MediaListActivity.this.finish();
        }
    }

    /* renamed from: com.baidu.android.pushservice.richmedia.MediaListActivity$2 */
    class C15152 implements OnItemClickListener {
        C15152() {
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            C1563h a = C1568q.m6993a(MediaListActivity.this, (String) ((HashMap) adapterView.getItemAtPosition(i)).get(MediaListActivity.f5308r));
            if (a == null) {
                return;
            }
            if (a.f5480i == C1524a.f5335f) {
                String str = a.f5476e;
                String str2 = a.f5477f;
                if (str2.length() > 0) {
                    str2 = str + "/" + str2.substring(0, str2.lastIndexOf(".")) + "/index.html";
                    Intent intent = new Intent();
                    intent.setClass(MediaListActivity.this, MediaViewActivity.class);
                    intent.setData(Uri.fromFile(new File(str2)));
                    intent.addFlags(268435456);
                    MediaListActivity.this.startActivity(intent);
                    return;
                }
                return;
            }
            MediaListActivity.this.m6855a(a.f5473b, a.f5474c, a.f5475d);
        }
    }

    /* renamed from: com.baidu.android.pushservice.richmedia.MediaListActivity$3 */
    class C15183 implements OnItemLongClickListener {

        /* renamed from: com.baidu.android.pushservice.richmedia.MediaListActivity$3$1 */
        class C15161 implements DialogInterface.OnClickListener {
            C15161() {
            }

            public void onClick(DialogInterface dialogInterface, int i) {
            }
        }

        C15183() {
        }

        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, final long j) {
            new Builder(MediaListActivity.this).setTitle("提示").setMessage("确定要删除该记录？").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    String str = (String) ((Map) MediaListActivity.this.f5309a.get((int) j)).get(MediaListActivity.f5308r);
                    C1563h a = C1568q.m6993a(MediaListActivity.this, str);
                    if (a != null) {
                        new File(a.f5476e).delete();
                    }
                    C1568q.m6999b(MediaListActivity.this, str);
                    Intent intent = new Intent();
                    intent.setClass(MediaListActivity.this, MediaListActivity.class);
                    MediaListActivity.this.startActivity(intent);
                }
            }).setNegativeButton("取消", new C15161()).show();
            return true;
        }
    }

    /* renamed from: com.baidu.android.pushservice.richmedia.MediaListActivity$4 */
    class C15204 implements C1303f {

        /* renamed from: com.baidu.android.pushservice.richmedia.MediaListActivity$4$1 */
        class C15191 implements Runnable {
            C15191() {
            }

            public void run() {
                Toast makeText = Toast.makeText(MediaListActivity.this, "下载富媒体失败", 1);
                makeText.setGravity(17, 0, 0);
                makeText.show();
            }
        }

        C15204() {
        }

        /* renamed from: a */
        public void mo13546a(C1524a c1524a) {
        }

        /* renamed from: a */
        public void mo13547a(C1524a c1524a, C1525b c1525b) {
            String c = c1524a.f5340d.mo14050c();
            if (c1525b.f5342a != c1525b.f5343b && MediaListActivity.this.f5321m != null) {
                int i = (int) ((((double) c1525b.f5342a) * 100.0d) / ((double) c1525b.f5343b));
                MediaListActivity.this.f5321m.setTextViewText(MediaListActivity.this.f5323o, i + "%");
                MediaListActivity.this.f5321m.setTextViewText(MediaListActivity.this.f5324p, c);
                MediaListActivity.this.f5321m.setProgressBar(MediaListActivity.this.f5322n, 100, i, false);
                MediaListActivity.this.f5321m.setImageViewResource(MediaListActivity.this.f5325q, 17301633);
                Notification build = VERSION.SDK_INT >= 16 ? new Notification.Builder(MediaListActivity.this).setSmallIcon(17301633).setWhen(System.currentTimeMillis()).build() : new Notification(17301633, null, System.currentTimeMillis());
                build.contentView = MediaListActivity.this.f5321m;
                build.contentIntent = PendingIntent.getActivity(MediaListActivity.this, 0, new Intent(), 0);
                build.flags |= 32;
                build.flags |= 2;
                MediaListActivity.this.f5310b.notify(c, 0, build);
            }
        }

        /* renamed from: a */
        public void mo13548a(C1524a c1524a, C1530e c1530e) {
            String c = c1524a.f5340d.mo14050c();
            MediaListActivity.this.f5310b.cancel(c, 0);
            C1563h a = C1568q.m6993a(MediaListActivity.this, c);
            if (a != null && a.f5480i == C1524a.f5335f) {
                String str = a.f5476e;
                c = a.f5477f;
                if (c.length() > 0) {
                    c = str + "/" + c.substring(0, c.lastIndexOf(".")) + "/index.html";
                    Intent intent = new Intent();
                    intent.setClass(MediaListActivity.this, MediaViewActivity.class);
                    intent.setData(Uri.fromFile(new File(c)));
                    intent.addFlags(268435456);
                    MediaListActivity.this.startActivity(intent);
                }
            }
        }

        /* renamed from: a */
        public void mo13549a(C1524a c1524a, Throwable th) {
            MediaListActivity.this.f5310b.cancel(c1524a.f5340d.mo14050c(), 0);
            MediaListActivity.this.runOnUiThread(new C15191());
        }

        /* renamed from: b */
        public void mo13550b(C1524a c1524a) {
            MediaListActivity.this.f5310b.cancel(c1524a.f5340d.mo14050c(), 0);
        }
    }

    /* renamed from: com.baidu.android.pushservice.richmedia.MediaListActivity$a */
    public class C1521a extends BaseAdapter {
        /* renamed from: b */
        private final Context f5306b;
        /* renamed from: c */
        private final ArrayList<HashMap<String, Object>> f5307c;

        public C1521a(Context context, ArrayList<HashMap<String, Object>> arrayList) {
            this.f5306b = context;
            this.f5307c = arrayList;
        }

        public int getCount() {
            return this.f5307c.size();
        }

        public Object getItem(int i) {
            return (this.f5307c == null || i >= this.f5307c.size()) ? null : this.f5307c.get(i);
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            View inflate = LayoutInflater.from(this.f5306b.getApplicationContext()).inflate(MediaListActivity.this.f5313e, null);
            inflate.setBackgroundColor(-7829368);
            ImageView imageView = (ImageView) inflate.findViewById(MediaListActivity.this.f5315g);
            TextView textView = (TextView) inflate.findViewById(MediaListActivity.this.f5316h);
            TextView textView2 = (TextView) inflate.findViewById(MediaListActivity.this.f5317i);
            TextView textView3 = (TextView) inflate.findViewById(MediaListActivity.this.f5318j);
            try {
                if (this.f5307c != null && i < this.f5307c.size()) {
                    HashMap hashMap = (HashMap) this.f5307c.get(i);
                    if (hashMap != null) {
                        if (!(textView == null || hashMap.get(PushConstants.TITLE_KEY) == null)) {
                            textView.setText(hashMap.get(PushConstants.TITLE_KEY).toString());
                        }
                        if (!(textView2 == null || hashMap.get("fromtext") == null)) {
                            textView2.setText(hashMap.get("fromtext").toString());
                        }
                        if (!(textView3 == null || hashMap.get("timetext") == null)) {
                            textView3.setText(hashMap.get("timetext").toString());
                        }
                        if (!(imageView == null || hashMap.get("img") == null)) {
                            imageView.setImageDrawable((Drawable) hashMap.get("img"));
                        }
                    }
                }
            } catch (Exception e) {
                C1425a.m6444e("MediaListActivity", "getView E: " + e);
            }
            return inflate;
        }
    }

    /* renamed from: a */
    private void m6855a(String str, String str2, String str3) {
        Uri parse = Uri.parse(str);
        String path = parse.getPath();
        String str4 = new String();
        if (path.length() > 0) {
            str4 = path.substring(0, path.lastIndexOf(47));
        }
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + "baidu/pushservice/files" + "/" + parse.getAuthority() + "/" + str4);
        C1425a.m6442c("MediaListActivity", "<<< download url " + parse.toString());
        C1527c a = C1529d.m6882a(C1526a.REQ_TYPE_GET_ZIP, parse.toString());
        a.f5347b = file.getAbsolutePath();
        a.f5348c = str2;
        a.f5349d = str3;
        new C1524a(this, new C15204(), a).start();
    }

    public void onCreate(Bundle bundle) {
        TraceMachine.startTracing("MediaListActivity");
        try {
            TraceMachine.enterMethod(this._nr_trace, "MediaListActivity#onCreate", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "MediaListActivity#onCreate", null);
            }
        }
        super.onCreate(bundle);
        this.f5312d = getResources().getIdentifier("bpush_media_list", "layout", getPackageName());
        requestWindowFeature(1);
        if (this.f5312d != 0) {
            setContentView(this.f5312d);
            Resources resources = getResources();
            String packageName = getPackageName();
            this.f5313e = resources.getIdentifier("bpush_media_list_item", "layout", packageName);
            this.f5314f = resources.getIdentifier("bpush_type_listview", "id", packageName);
            this.f5315g = resources.getIdentifier("bpush_media_list_img", "id", packageName);
            this.f5316h = resources.getIdentifier("bpush_media_list_title", "id", packageName);
            this.f5317i = resources.getIdentifier("bpush_media_list_from_text", "id", packageName);
            this.f5318j = resources.getIdentifier("bpush_media_list_time_text", "id", packageName);
            this.f5319k = resources.getIdentifier("bpush_media_none_layout", "id", packageName);
            this.f5320l = (LinearLayout) findViewById(this.f5319k);
            this.f5311c = (ListView) findViewById(this.f5314f);
            Button button = (Button) findViewById(resources.getIdentifier("bpush_media_list_return_btn", "id", packageName));
            button.setClickable(true);
            button.setOnClickListener(new C15141());
            int identifier = getResources().getIdentifier("bpush_download_progress", "layout", getPackageName());
            if (identifier != 0) {
                this.f5321m = new RemoteViews(getPackageName(), identifier);
                this.f5322n = getResources().getIdentifier("bpush_downLoad_progress", "id", getPackageName());
                this.f5323o = getResources().getIdentifier("bpush_progress_percent", "id", getPackageName());
                this.f5324p = getResources().getIdentifier("bpush_progress_text", "id", getPackageName());
                this.f5325q = getResources().getIdentifier("bpush_downLoad_icon", "id", getPackageName());
            }
            this.f5311c.setOnItemClickListener(this.f5326s);
            this.f5311c.setDividerHeight(0);
            this.f5311c.setOnItemLongClickListener(this.f5327t);
        }
        this.f5310b = (NotificationManager) getSystemService("notification");
        TraceMachine.exitMethod();
    }

    public void onResume() {
        int i = 0;
        super.onResume();
        if (this.f5312d != 0) {
            String[] strArr = new String[]{"img", PushConstants.TITLE_KEY, "fromtext", "timetext"};
            this.f5309a = new ArrayList();
            List b = C1568q.m7002b(this);
            if (b == null || b.isEmpty()) {
                this.f5320l.setVisibility(0);
                this.f5311c.setVisibility(8);
                return;
            }
            this.f5320l.setVisibility(8);
            this.f5311c.setVisibility(0);
            this.f5311c.setItemsCanFocus(true);
            PackageManager packageManager = getPackageManager();
            while (true) {
                int i2 = i;
                if (i2 < b.size()) {
                    HashMap hashMap = new HashMap();
                    try {
                        ApplicationInfo applicationInfo = packageManager.getApplicationInfo(((C1563h) b.get(i2)).f5472a, 0);
                        hashMap.put(strArr[0], packageManager.getApplicationIcon(applicationInfo));
                        hashMap.put(strArr[1], ((C1563h) b.get(i2)).f5474c);
                        hashMap.put(strArr[2], "来自：" + packageManager.getApplicationLabel(applicationInfo));
                        hashMap.put(strArr[3], C1578v.m7067a(((C1563h) b.get(i2)).f5481j));
                        hashMap.put(f5308r, ((C1563h) b.get(i2)).f5473b);
                        this.f5309a.add(hashMap);
                    } catch (NameNotFoundException e) {
                        C1425a.m6443d("MediaListActivity", "Media item package NOT found: " + ((C1563h) b.get(i2)).f5472a);
                    }
                    i = i2 + 1;
                } else {
                    this.f5311c.setAdapter(new C1521a(this, this.f5309a));
                    return;
                }
            }
        }
    }

    /* Access modifiers changed, original: protected */
    public void onStart() {
        super.onStart();
        ApplicationStateMonitor.getInstance().activityStarted();
    }

    /* Access modifiers changed, original: protected */
    public void onStop() {
        super.onStop();
        ApplicationStateMonitor.getInstance().activityStopped();
    }
}
