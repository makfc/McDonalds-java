package com.newrelic.mobile.fbs;

import com.newrelic.com.google.flatbuffers.FlatBufferBuilder;
import com.newrelic.com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;

public final class StringSessionAttribute extends Table {
    public static StringSessionAttribute getRootAsStringSessionAttribute(ByteBuffer _bb) {
        return getRootAsStringSessionAttribute(_bb, new StringSessionAttribute());
    }

    public static StringSessionAttribute getRootAsStringSessionAttribute(ByteBuffer _bb, StringSessionAttribute obj) {
        _bb.order(ByteOrder.LITTLE_ENDIAN);
        return obj.__assign(_bb.getInt(_bb.position()) + _bb.position(), _bb);
    }

    public void __init(int _i, ByteBuffer _bb) {
        this.bb_pos = _i;
        this.f6700bb = _bb;
    }

    public StringSessionAttribute __assign(int _i, ByteBuffer _bb) {
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

    public String value() {
        int o = __offset(6);
        return o != 0 ? __string(this.bb_pos + o) : null;
    }

    public ByteBuffer valueAsByteBuffer() {
        return __vector_as_bytebuffer(6, 1);
    }

    public static int createStringSessionAttribute(FlatBufferBuilder builder, int nameOffset, int valueOffset) {
        builder.startObject(2);
        addValue(builder, valueOffset);
        addName(builder, nameOffset);
        return endStringSessionAttribute(builder);
    }

    public static void startStringSessionAttribute(FlatBufferBuilder builder) {
        builder.startObject(2);
    }

    public static void addName(FlatBufferBuilder builder, int nameOffset) {
        builder.addOffset(0, nameOffset, 0);
    }

    public static void addValue(FlatBufferBuilder builder, int valueOffset) {
        builder.addOffset(1, valueOffset, 0);
    }

    public static int endStringSessionAttribute(FlatBufferBuilder builder) {
        int o = builder.endObject();
        builder.required(o, 4);
        return o;
    }

    /* Access modifiers changed, original: protected */
    public int keysCompare(Integer o1, Integer o2, ByteBuffer _bb) {
        return Table.compareStrings(Table.__offset(4, o1.intValue(), _bb), Table.__offset(4, o2.intValue(), _bb), _bb);
    }

    public static StringSessionAttribute __lookup_by_key(int vectorLocation, String key, ByteBuffer bb) {
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
                return new StringSessionAttribute().__assign(tableOffset, bb);
            } else {
                middle++;
                start += middle;
                span -= middle;
            }
        }
        return null;
    }
}
