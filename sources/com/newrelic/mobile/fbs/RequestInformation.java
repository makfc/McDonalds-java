package com.newrelic.mobile.fbs;

import com.newrelic.com.google.flatbuffers.FlatBufferBuilder;
import com.newrelic.com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public final class RequestInformation extends Table {
    public static RequestInformation getRootAsRequestInformation(ByteBuffer _bb) {
        return getRootAsRequestInformation(_bb, new RequestInformation());
    }

    public static RequestInformation getRootAsRequestInformation(ByteBuffer _bb, RequestInformation obj) {
        _bb.order(ByteOrder.LITTLE_ENDIAN);
        return obj.__assign(_bb.getInt(_bb.position()) + _bb.position(), _bb);
    }

    public void __init(int _i, ByteBuffer _bb) {
        this.bb_pos = _i;
        this.f6700bb = _bb;
    }

    public RequestInformation __assign(int _i, ByteBuffer _bb) {
        __init(_i, _bb);
        return this;
    }

    public long requestTimestampMs() {
        int o = __offset(4);
        return o != 0 ? this.f6700bb.getLong(this.bb_pos + o) : 0;
    }

    public boolean mutateRequestTimestampMs(long requestTimestampMs) {
        int o = __offset(4);
        if (o == 0) {
            return false;
        }
        this.f6700bb.putLong(this.bb_pos + o, requestTimestampMs);
        return true;
    }

    public long requestAddress() {
        int o = __offset(6);
        return o != 0 ? ((long) this.f6700bb.getInt(this.bb_pos + o)) & 4294967295L : 0;
    }

    public boolean mutateRequestAddress(long requestAddress) {
        int o = __offset(6);
        if (o == 0) {
            return false;
        }
        this.f6700bb.putInt(this.bb_pos + o, (int) requestAddress);
        return true;
    }

    public static int createRequestInformation(FlatBufferBuilder builder, long requestTimestampMs, long requestAddress) {
        builder.startObject(2);
        addRequestTimestampMs(builder, requestTimestampMs);
        addRequestAddress(builder, requestAddress);
        return endRequestInformation(builder);
    }

    public static void startRequestInformation(FlatBufferBuilder builder) {
        builder.startObject(2);
    }

    public static void addRequestTimestampMs(FlatBufferBuilder builder, long requestTimestampMs) {
        builder.addLong(0, requestTimestampMs, 0);
    }

    public static void addRequestAddress(FlatBufferBuilder builder, long requestAddress) {
        builder.addInt(1, (int) requestAddress, 0);
    }

    public static int endRequestInformation(FlatBufferBuilder builder) {
        return builder.endObject();
    }
}
