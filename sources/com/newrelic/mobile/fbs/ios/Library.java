package com.newrelic.mobile.fbs.ios;

import com.newrelic.com.google.flatbuffers.FlatBufferBuilder;
import com.newrelic.com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public final class Library extends Table {
    public static Library getRootAsLibrary(ByteBuffer _bb) {
        return getRootAsLibrary(_bb, new Library());
    }

    public static Library getRootAsLibrary(ByteBuffer _bb, Library obj) {
        _bb.order(ByteOrder.LITTLE_ENDIAN);
        return obj.__assign(_bb.getInt(_bb.position()) + _bb.position(), _bb);
    }

    public void __init(int _i, ByteBuffer _bb) {
        this.bb_pos = _i;
        this.f6700bb = _bb;
    }

    public Library __assign(int _i, ByteBuffer _bb) {
        __init(_i, _bb);
        return this;
    }

    public long uuidLow() {
        int o = __offset(4);
        return o != 0 ? this.f6700bb.getLong(this.bb_pos + o) : 0;
    }

    public boolean mutateUuidLow(long uuidLow) {
        int o = __offset(4);
        if (o == 0) {
            return false;
        }
        this.f6700bb.putLong(this.bb_pos + o, uuidLow);
        return true;
    }

    public long uuidHigh() {
        int o = __offset(6);
        return o != 0 ? this.f6700bb.getLong(this.bb_pos + o) : 0;
    }

    public boolean mutateUuidHigh(long uuidHigh) {
        int o = __offset(6);
        if (o == 0) {
            return false;
        }
        this.f6700bb.putLong(this.bb_pos + o, uuidHigh);
        return true;
    }

    public long address() {
        int o = __offset(8);
        return o != 0 ? this.f6700bb.getLong(this.bb_pos + o) : 0;
    }

    public boolean mutateAddress(long address) {
        int o = __offset(8);
        if (o == 0) {
            return false;
        }
        this.f6700bb.putLong(this.bb_pos + o, address);
        return true;
    }

    public boolean userLibrary() {
        int o = __offset(10);
        return (o == 0 || this.f6700bb.get(this.bb_pos + o) == (byte) 0) ? false : true;
    }

    public boolean mutateUserLibrary(boolean userLibrary) {
        int i = 0;
        int o = __offset(10);
        if (o == 0) {
            return false;
        }
        ByteBuffer byteBuffer = this.f6700bb;
        int i2 = this.bb_pos + o;
        if (userLibrary) {
            i = 1;
        }
        byteBuffer.put(i2, (byte) i);
        return true;
    }

    public int arch() {
        int o = __offset(12);
        return o != 0 ? this.f6700bb.get(this.bb_pos + o) & 255 : 0;
    }

    public boolean mutateArch(int arch) {
        int o = __offset(12);
        if (o == 0) {
            return false;
        }
        this.f6700bb.put(this.bb_pos + o, (byte) arch);
        return true;
    }

    public static int createLibrary(FlatBufferBuilder builder, long uuidLow, long uuidHigh, long address, boolean userLibrary, int arch) {
        builder.startObject(5);
        addAddress(builder, address);
        addUuidHigh(builder, uuidHigh);
        addUuidLow(builder, uuidLow);
        addArch(builder, arch);
        addUserLibrary(builder, userLibrary);
        return endLibrary(builder);
    }

    public static void startLibrary(FlatBufferBuilder builder) {
        builder.startObject(5);
    }

    public static void addUuidLow(FlatBufferBuilder builder, long uuidLow) {
        builder.addLong(0, uuidLow, 0);
    }

    public static void addUuidHigh(FlatBufferBuilder builder, long uuidHigh) {
        builder.addLong(1, uuidHigh, 0);
    }

    public static void addAddress(FlatBufferBuilder builder, long address) {
        builder.addLong(2, address, 0);
    }

    public static void addUserLibrary(FlatBufferBuilder builder, boolean userLibrary) {
        builder.addBoolean(3, userLibrary, false);
    }

    public static void addArch(FlatBufferBuilder builder, int arch) {
        builder.addByte(4, (byte) arch, 0);
    }

    public static int endLibrary(FlatBufferBuilder builder) {
        return builder.endObject();
    }

    public static void finishLibraryBuffer(FlatBufferBuilder builder, int offset) {
        builder.finish(offset);
    }
}
