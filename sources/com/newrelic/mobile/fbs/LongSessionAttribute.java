package com.newrelic.mobile.fbs;

import com.newrelic.com.google.flatbuffers.FlatBufferBuilder;
import com.newrelic.com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;

public final class LongSessionAttribute extends Table {
    public static LongSessionAttribute getRootAsLongSessionAttribute(ByteBuffer _bb) {
        return getRootAsLongSessionAttribute(_bb, new LongSessionAttribute());
    }

    public static LongSessionAttribute getRootAsLongSessionAttribute(ByteBuffer _bb, LongSessionAttribute obj) {
        _bb.order(ByteOrder.LITTLE_ENDIAN);
        return obj.__assign(_bb.getInt(_bb.position()) + _bb.position(), _bb);
    }

    public void __init(int _i, ByteBuffer _bb) {
        this.bb_pos = _i;
        this.f6700bb = _bb;
    }

    public LongSessionAttribute __assign(int _i, ByteBuffer _bb) {
        __init(_i, _bb);
        return this;
    }

    public String name() {
        int o = __offset(4);
        return o != 0 ? __string(this.bb_pos + o) : null;
    }

    public ByteBuffer nameAsByteBuffer() {
        return __vector_as_bytebuffer(4, 1);
    }

    public long value() {
        int o = __offset(6);
        return o != 0 ? this.f6700bb.getLong(this.bb_pos + o) : 0;
    }

    public boolean mutateValue(long value) {
        int o = __offset(6);
        if (o == 0) {
            return false;
        }
        this.f6700bb.putLong(this.bb_pos + o, value);
        return true;
    }

    public static int createLongSessionAttribute(FlatBufferBuilder builder, int nameOffset, long value) {
        builder.startObject(2);
        addValue(builder, value);
        addName(builder, nameOffset);
        return endLongSessionAttribute(builder);
    }

    public static void startLongSessionAttribute(FlatBufferBuilder builder) {
        builder.startObject(2);
    }

    public static void addName(FlatBufferBuilder builder, int nameOffset) {
        builder.addOffset(0, nameOffset, 0);
    }

    public static void addValue(FlatBufferBuilder builder, long value) {
        builder.addLong(1, value, 0);
    }

    public static int endLongSessionAttribute(FlatBufferBuilder builder) {
        int o = builder.endObject();
        builder.required(o, 4);
        return o;
    }

    /* Access modifiers changed, original: protected */
    public int keysCompare(Integer o1, Integer o2, ByteBuffer _bb) {
        return Table.compareStrings(Table.__offset(4, o1.intValue(), _bb), Table.__offset(4, o2.intValue(), _bb), _bb);
    }

    public static LongSessionAttribute __lookup_by_key(int vectorLocation, String key, ByteBuffer bb) {
        byte[] byteKey = key.getBytes((Charset) Table.UTF8_CHARSET.get());
        int span = bb.getInt(vectorLocation - 4);
        int start = 0;
        while (span != 0) {
            int middle = span / 2;
            int tableOffset = Table.__indirect(((start + middle) * 4) + vectorLocation, bb);
            int comp = Table.compareStrings(Table.__offset(4, bb.array().length - tableOffset, bb), byteKey, bb);
            if (comp > 0) {
                span = middle;
            } else if (comp >= 0) {
                return new LongSessionAttribute().__assign(tableOffset, bb);
            } else {
                middle++;
                start += middle;
                span -= middle;
            }
        }
        return null;
    }
}
