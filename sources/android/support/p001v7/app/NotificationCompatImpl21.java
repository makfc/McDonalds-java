package android.support.p001v7.app;

import android.annotation.TargetApi;
import android.app.Notification.MediaStyle;
import android.media.session.MediaSession.Token;
import android.support.annotation.RequiresApi;
import android.support.p000v4.app.NotificationBuilderWithBuilderAccessor;

@TargetApi(21)
@RequiresApi
/* renamed from: android.support.v7.app.NotificationCompatImpl21 */
class NotificationCompatImpl21 {
    NotificationCompatImpl21() {
    }

    public static void addMediaStyle(NotificationBuilderWithBuilderAccessor b, int[] actionsToShowInCompact, Object token) {
        MediaStyle style = new MediaStyle(b.getBuilder());
        if (actionsToShowInCompact != null) {
            style.setShowActionsInCompactView(actionsToShowInCompact);
        }
        if (token != null) {
            style.setMediaSession((Token) token);
        }
    }
}
