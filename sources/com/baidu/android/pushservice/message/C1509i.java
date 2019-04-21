package com.baidu.android.pushservice.message;

/* renamed from: com.baidu.android.pushservice.message.i */
public enum C1509i {
    TYPE_INVALUE(-1),
    MSG_ID_HANDSHAKE(1),
    MSG_ID_HEARTBEAT_SERVER(2),
    MSG_ID_HEARTBEAT_CLIENT(4),
    MSG_ID_PUSH_MSG(3),
    MSG_ID_TINY_HEARTBEAT_CLIENT(5),
    MSG_ID_TINY_HEARTBEAT_SERVER(6);
    
    /* renamed from: h */
    private short f5276h;

    private C1509i(int i) {
        this.f5276h = (short) i;
    }

    /* renamed from: a */
    public static C1509i m6823a(short s) {
        switch (s) {
            case (short) 1:
                return MSG_ID_HANDSHAKE;
            case (short) 2:
                return MSG_ID_HEARTBEAT_SERVER;
            case (short) 3:
                return MSG_ID_PUSH_MSG;
            case (short) 4:
                return MSG_ID_HEARTBEAT_CLIENT;
            case (short) 5:
                return MSG_ID_TINY_HEARTBEAT_CLIENT;
            case (short) 6:
                return MSG_ID_TINY_HEARTBEAT_SERVER;
            default:
                return TYPE_INVALUE;
        }
    }

    /* renamed from: a */
    public short mo13994a() {
        return this.f5276h;
    }
}
