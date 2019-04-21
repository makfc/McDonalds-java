package com.newrelic.mobile.fbs;

import com.newrelic.com.google.flatbuffers.FlatBufferBuilder;
import com.newrelic.com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public final class AgentDataBundle extends Table {
    public static AgentDataBundle getRootAsAgentDataBundle(ByteBuffer _bb) {
        return getRootAsAgentDataBundle(_bb, new AgentDataBundle());
    }

    public static AgentDataBundle getRootAsAgentDataBundle(ByteBuffer _bb, AgentDataBundle obj) {
        _bb.order(ByteOrder.LITTLE_ENDIAN);
        return obj.__assign(_bb.getInt(_bb.position()) + _bb.position(), _bb);
    }

    public void __init(int _i, ByteBuffer _bb) {
        this.bb_pos = _i;
        this.f6700bb = _bb;
    }

    public AgentDataBundle __assign(int _i, ByteBuffer _bb) {
        __init(_i, _bb);
        return this;
    }

    public AgentData agentData(int j) {
        return agentData(new AgentData(), j);
    }

    public AgentData agentData(AgentData obj, int j) {
        int o = __offset(4);
        return o != 0 ? obj.__assign(__indirect(__vector(o) + (j * 4)), this.f6700bb) : null;
    }

    public int agentDataLength() {
        int o = __offset(4);
        return o != 0 ? __vector_len(o) : 0;
    }

    public static int createAgentDataBundle(FlatBufferBuilder builder, int agentDataOffset) {
        builder.startObject(1);
        addAgentData(builder, agentDataOffset);
        return endAgentDataBundle(builder);
    }

    public static void startAgentDataBundle(FlatBufferBuilder builder) {
        builder.startObject(1);
    }

    public static void addAgentData(FlatBufferBuilder builder, int agentDataOffset) {
        builder.addOffset(0, agentDataOffset, 0);
    }

    public static int createAgentDataVector(FlatBufferBuilder builder, int[] data) {
        builder.startVector(4, data.length, 4);
        for (int i = data.length - 1; i >= 0; i--) {
            builder.addOffset(data[i]);
        }
        return builder.endVector();
    }

    public static void startAgentDataVector(FlatBufferBuilder builder, int numElems) {
        builder.startVector(4, numElems, 4);
    }

    public static int endAgentDataBundle(FlatBufferBuilder builder) {
        return builder.endObject();
    }

    public static void finishAgentDataBundleBuffer(FlatBufferBuilder builder, int offset) {
        builder.finish(offset);
    }
}
