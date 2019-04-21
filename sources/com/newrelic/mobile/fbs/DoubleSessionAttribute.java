package com.newrelic.mobile.fbs;

import com.newrelic.com.google.flatbuffers.FlatBufferBuilder;
import com.newrelic.com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;

public final class DoubleSessionAttribute extends Table {
    public static DoubleSessionAttribute getRootAsDoubleSessionAttribute(ByteBuffer _bb) {
        return getRootAsDoubleSessionAttribute(_bb, new DoubleSessionAttribute());
    }

    public static DoubleSessionAttribute getRootAsDoubleSessionAttribute(ByteBuffer _bb, DoubleSessionAttribute obj) {
        _bb.order(ByteOrder.LITTLE_ENDIAN);
        return obj.__assign(_bb.getInt(_bb.position()) + _bb.position(), _bb);
    }

    public void __init(int _i, ByteBuffer _bb) {
        this.bb_pos = _i;
        this.f6700bb = _bb;
    }

    public DoubleSessionAttribute __assign(int _i, ByteBuffer _bb) {
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

    public double value() {
        int o = __offset(6);
        return o != 0 ? this.f6700bb.getDouble(this.bb_pos + o) : 0.0d;
    }

    public boolean mutateValue(double value) {
        int o = __offset(6);
        if (o == 0) {
            return false;
        }
        this.f6700bb.putDouble(this.bb_pos + o, value);
        return true;
    }

    public static int createDoubleSessionAttribute(FlatBufferBuilder builder, int nameOffset, double value) {
        builder.startObject(2);
        addValue(builder, value);
        addName(builder, nameOffset);
        return endDoubleSessionAttribute(builder);
    }

    public static void startDoubleSessionAttribute(FlatBufferBuilder builder) {
        builder.startObject(2);
    }

    public static void addName(FlatBufferBuilder builder, int nameOffset) {
        builder.addOffset(0, nameOffset, 0);
    }

    public static void addValue(FlatBufferBuilder builder, double value) {
        builder.addDouble(1, value, 0.0d);
    }

    public static int endDoubleSessionAttribute(FlatBufferBuilder builder) {
        int o = builder.endObject();
        builder.required(o, 4);
        return o;
    }

    /* Access modifiers changed, original: protected */
    public int keysCompare(Integer o1, Integer o2, ByteBuffer _bb) {
        return Table.compareStrings(Table.__offset(4, o1.intValue(), _bb), Table.__offset(4, o2.intValue(), _bb), _bb);
    }

    public static DoubleSessionAttribute __lookup_by_key(int vectorLocation, String key, ByteBuffer bb) {
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
                return new DoubleSessionAttribute().__assign(tableOffset, bb);
            } else {
                middle++;
                start += middle;
                span -= middle;
            }
        }
        return null;
    }
}
