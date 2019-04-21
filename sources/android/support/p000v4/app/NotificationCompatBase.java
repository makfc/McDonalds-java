package android.support.p000v4.app;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.p000v4.app.RemoteInputCompatBase.RemoteInput;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@RequiresApi
@RestrictTo
@TargetApi(9)
/* renamed from: android.support.v4.app.NotificationCompatBase */
public class NotificationCompatBase {
    private static Method sSetLatestEventInfo;

    /* renamed from: android.support.v4.app.NotificationCompatBase$Action */
    public static abstract class Action {

        /* renamed from: android.support.v4.app.NotificationCompatBase$Action$Factory */
        public interface Factory {
            Action build(int i, CharSequence charSequence, PendingIntent pendingIntent, Bundle bundle, RemoteInput[] remoteInputArr, boolean z);

            Action[] newArray(int i);
        }

        public abstract PendingIntent getActionIntent();

        public abstract boolean getAllowGeneratedReplies();

        public abstract Bundle getExtras();

        public abstract int getIcon();

        public abstract RemoteInput[] getRemoteInputs();

        public abstract CharSequence getTitle();
    }

    /* renamed from: android.support.v4.app.NotificationCompatBase$UnreadConversation */
    public static abstract class UnreadConversation {

        /* renamed from: android.support.v4.app.NotificationCompatBase$UnreadConversation$Factory */
        public interface Factory {
            UnreadConversation build(String[] strArr, RemoteInput remoteInput, PendingIntent pendingIntent, PendingIntent pendingIntent2, String[] strArr2, long j);
        }

        public abstract long getLatestTimestamp();

        public abstract String[] getMessages();

        public abstract String getParticipant();

        public abstract String[] getParticipants();

        public abstract PendingIntent getReadPendingIntent();

        public abstract RemoteInput getRemoteInput();

        public abstract PendingIntent getReplyPendingIntent();
    }

    public static Notification add(Notification notification, Context context, CharSequence contentTitle, CharSequence contentText, PendingIntent contentIntent, PendingIntent fullScreenIntent) {
        if (sSetLatestEventInfo == null) {
            try {
                sSetLatestEventInfo = Notification.class.getMethod("setLatestEventInfo", new Class[]{Context.class, CharSequence.class, CharSequence.class, PendingIntent.class});
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            sSetLatestEventInfo.invoke(notification, new Object[]{context, contentTitle, contentText, contentIntent});
            notification.fullScreenIntent = fullScreenIntent;
            return notification;
        } catch (IllegalAccessException | InvocationTargetException e2) {
            throw new RuntimeException(e2);
        }
    }
}
