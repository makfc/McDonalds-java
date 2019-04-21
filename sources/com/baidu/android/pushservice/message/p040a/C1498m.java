package com.baidu.android.pushservice.message.p040a;

/* renamed from: com.baidu.android.pushservice.message.a.m */
public enum C1498m {
    MSG_TYPE_INVALID(-1),
    MSG_TYPE_SINGLE_PRIVATE(0),
    MSG_TYPE_MULTI_PRIVATE(1),
    MSG_TYPE_SINGLE_PUBLIC(2),
    MSG_TYPE_MULTI_PUBLIC(3),
    MSG_TYPE_MULTI_PRIVATE_NOTIFICATION(5),
    MSG_TYPE_PRIVATE_MESSAGE(6),
    MSG_TYPE_RICH_MEDIA(7),
    MSG_TYPE_BAIDU_SUPPER(10),
    MSG_TYPE_ADVERTISE(11),
    MSG_TYPE_ALARM_NOTIFICATION(12),
    MSG_TYPE_ALARM_MESSAGE(13),
    MSG_TYPE_ALARM_AD_NOTIFICATION(14),
    MSG_TYPE_INNERBIND(101),
    MSG_TYPE_ULTRON_COMMAND(102),
    MSG_TYPE_LBS_APPLIST_COMMAND(103),
    MSG_TYPE_APPSTAT_COMMAND(104);
    
    /* renamed from: r */
    private int f5234r;

    private C1498m(int i) {
        this.f5234r = i;
    }

    /* renamed from: a */
    public static C1498m m6780a(int i) {
        switch (i) {
            case 0:
                return MSG_TYPE_SINGLE_PRIVATE;
            case 1:
                return MSG_TYPE_MULTI_PRIVATE;
            case 2:
                return MSG_TYPE_SINGLE_PUBLIC;
            case 3:
                return MSG_TYPE_MULTI_PUBLIC;
            case 5:
                return MSG_TYPE_MULTI_PRIVATE_NOTIFICATION;
            case 6:
                return MSG_TYPE_PRIVATE_MESSAGE;
            case 7:
                return MSG_TYPE_RICH_MEDIA;
            case 10:
                return MSG_TYPE_BAIDU_SUPPER;
            case 11:
                return MSG_TYPE_ADVERTISE;
            case 12:
                return MSG_TYPE_ALARM_NOTIFICATION;
            case 13:
                return MSG_TYPE_ALARM_MESSAGE;
            case 14:
                return MSG_TYPE_ALARM_AD_NOTIFICATION;
            case 101:
                return MSG_TYPE_INNERBIND;
            case 102:
                return MSG_TYPE_ULTRON_COMMAND;
            case 103:
                return MSG_TYPE_LBS_APPLIST_COMMAND;
            case 104:
                return MSG_TYPE_APPSTAT_COMMAND;
            default:
                return MSG_TYPE_INVALID;
        }
    }

    /* renamed from: a */
    public int mo13970a() {
        return this.f5234r;
    }
}
