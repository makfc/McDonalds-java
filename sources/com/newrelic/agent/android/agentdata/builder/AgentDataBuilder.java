package com.newrelic.agent.android.agentdata.builder;

import com.newrelic.agent.android.agentdata.HexAttributes;
import com.newrelic.com.google.flatbuffers.FlatBufferBuilder;
import com.newrelic.com.google.gson.GsonBuilder;
import com.newrelic.mobile.fbs.AgentData;
import com.newrelic.mobile.fbs.AgentDataBundle;
import com.newrelic.mobile.fbs.ApplicationInfo;
import com.newrelic.mobile.fbs.BoolSessionAttribute;
import com.newrelic.mobile.fbs.DoubleSessionAttribute;
import com.newrelic.mobile.fbs.LongSessionAttribute;
import com.newrelic.mobile.fbs.StringSessionAttribute;
import com.newrelic.mobile.fbs.hex.Frame;
import com.newrelic.mobile.fbs.hex.HandledException;
import com.newrelic.mobile.fbs.hex.Thread;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class AgentDataBuilder {
    protected static void computeIfAbsent(String s, Map<String, Integer> map, FlatBufferBuilder flat) {
        if (s != null && !map.containsValue(s)) {
            map.put(s, Integer.valueOf(flat.createString((CharSequence) s)));
        }
    }

    public static FlatBufferBuilder startAndFinishAgentData(Map<String, Object> attributesMap, Set<Map<String, Object>> agentData) {
        String key;
        Map<String, Integer> stringIndexMap = new HashMap();
        FlatBufferBuilder flat = new FlatBufferBuilder();
        for (Entry<String, Object> attribute : attributesMap.entrySet()) {
            key = (String) attribute.getKey();
            String val = attribute.getValue();
            computeIfAbsent(key, stringIndexMap, flat);
            if (val instanceof String) {
                computeIfAbsent(val, stringIndexMap, flat);
            }
        }
        List<Map<String, Object>> thread = null;
        for (Map<String, Object> hex : agentData) {
            computeIfAbsent((String) hex.get("name"), stringIndexMap, flat);
            computeIfAbsent((String) hex.get(HexAttributes.HEX_ATTR_MESSAGE), stringIndexMap, flat);
            computeIfAbsent((String) hex.get(HexAttributes.HEX_ATTR_CAUSE), stringIndexMap, flat);
            thread = (List) hex.get(HexAttributes.HEX_ATTR_THREAD);
        }
        List<Integer> framesOffsets = new ArrayList();
        List<Integer> threadsOffsets = new ArrayList();
        if (thread != null) {
            for (Map<String, Object> frame : thread) {
                Map<String, Integer> frameValStringIndexMap = new HashMap();
                frameValStringIndexMap.put(HexAttributes.HEX_ATTR_FILENAME, Integer.valueOf(flat.createString((CharSequence) "")));
                for (Entry<String, Object> frameElement : frame.entrySet()) {
                    key = (String) frameElement.getKey();
                    Object val2 = frameElement.getValue();
                    if (val2 instanceof String) {
                        frameValStringIndexMap.put(key, Integer.valueOf(flat.createString((CharSequence) (String) val2)));
                    }
                }
                Frame.startFrame(flat);
                if (frameValStringIndexMap.get(HexAttributes.HEX_ATTR_CLASS_NAME) != null) {
                    Frame.addClassName(flat, ((Integer) frameValStringIndexMap.get(HexAttributes.HEX_ATTR_CLASS_NAME)).intValue());
                }
                if (frameValStringIndexMap.get(HexAttributes.HEX_ATTR_METHOD_NAME) != null) {
                    Frame.addMethodName(flat, ((Integer) frameValStringIndexMap.get(HexAttributes.HEX_ATTR_METHOD_NAME)).intValue());
                }
                if (frameValStringIndexMap.get(HexAttributes.HEX_ATTR_FILENAME) != null) {
                    Frame.addFileName(flat, ((Integer) frameValStringIndexMap.get(HexAttributes.HEX_ATTR_FILENAME)).intValue());
                }
                if (frame.get(HexAttributes.HEX_ATTR_LINE_NUMBER) != null) {
                    Frame.addLineNumber(flat, (long) ((Integer) frame.get(HexAttributes.HEX_ATTR_LINE_NUMBER)).intValue());
                }
                framesOffsets.add(Integer.valueOf(Frame.endFrame(flat)));
            }
        }
        threadsOffsets.add(Integer.valueOf(Thread.createThread(flat, Thread.createFramesVector(flat, toArray(framesOffsets)))));
        int threadVectorOffset = HandledException.createThreadsVector(flat, toArray(threadsOffsets));
        Set<Integer> stringSessionAttributes = new HashSet();
        Set<Integer> doubleSessionAttributes = new HashSet();
        Set<Integer> longSessionAttributes = new HashSet();
        Set<Integer> boolSessionAttributes = new HashSet();
        for (Entry<String, Object> attribute2 : attributesMap.entrySet()) {
            int keyIndex = ((Integer) stringIndexMap.get((String) attribute2.getKey())).intValue();
            Number val3 = attribute2.getValue();
            if (val3 instanceof String) {
                stringSessionAttributes.add(Integer.valueOf(StringSessionAttribute.createStringSessionAttribute(flat, keyIndex, ((Integer) stringIndexMap.get(val3)).intValue())));
            } else if ((val3 instanceof Double) || (val3 instanceof Float)) {
                doubleSessionAttributes.add(Integer.valueOf(DoubleSessionAttribute.createDoubleSessionAttribute(flat, keyIndex, val3.doubleValue())));
            } else if (val3 instanceof Number) {
                longSessionAttributes.add(Integer.valueOf(LongSessionAttribute.createLongSessionAttribute(flat, keyIndex, val3.longValue())));
            } else if (val3 instanceof Boolean) {
                boolSessionAttributes.add(Integer.valueOf(BoolSessionAttribute.createBoolSessionAttribute(flat, keyIndex, ((Boolean) val3).booleanValue())));
            }
        }
        int stringSessionAttributesVector = -1;
        if (!stringSessionAttributes.isEmpty()) {
            stringSessionAttributesVector = AgentData.createStringAttributesVector(flat, toArray(stringSessionAttributes));
        }
        int doubleSessionAttributesVector = -1;
        if (!doubleSessionAttributes.isEmpty()) {
            doubleSessionAttributesVector = AgentData.createDoubleAttributesVector(flat, toArray(doubleSessionAttributes));
        }
        int longSessionAttributesVector = -1;
        if (!longSessionAttributes.isEmpty()) {
            longSessionAttributesVector = AgentData.createLongAttributesVector(flat, toArray(longSessionAttributes));
        }
        int booleanSessionAttributesVector = -1;
        if (!boolSessionAttributes.isEmpty()) {
            booleanSessionAttributesVector = AgentData.createBoolAttributesVector(flat, toArray(boolSessionAttributes));
        }
        Set<Integer> handledExceptionOffsets = new HashSet();
        if (!agentData.isEmpty()) {
            for (Map<String, Object> hex2 : agentData) {
                long appUuidHigh;
                long appUuidLow;
                int nameOffset = stringIndexMapOffset(stringIndexMap, hex2.get("name"));
                int messageOffset = stringIndexMapOffset(stringIndexMap, hex2.get(HexAttributes.HEX_ATTR_MESSAGE));
                int causeOffset = stringIndexMapOffset(stringIndexMap, hex2.get(HexAttributes.HEX_ATTR_CAUSE));
                long timeStampMs = ((Long) (hex2.containsKey(HexAttributes.HEX_ATTR_TIMESTAMP_MS) ? hex2.get(HexAttributes.HEX_ATTR_TIMESTAMP_MS) : Long.valueOf(System.currentTimeMillis()))).longValue();
                try {
                    appUuidHigh = ((Long) hex2.get(HexAttributes.HEX_ATTR_APP_UUID_HI)).longValue();
                    appUuidLow = ((Long) hex2.get(HexAttributes.HEX_ATTR_APP_UUID_LO)).longValue();
                } catch (ClassCastException e) {
                    appUuidHigh = 0;
                    appUuidLow = 0;
                }
                HandledException.startHandledException(flat);
                HandledException.addAppUuidHigh(flat, appUuidHigh);
                HandledException.addAppUuidLow(flat, appUuidLow);
                if (-1 != timeStampMs) {
                    HandledException.addTimestampMs(flat, timeStampMs);
                }
                if (-1 != nameOffset) {
                    HandledException.addName(flat, nameOffset);
                }
                if (-1 != messageOffset) {
                    HandledException.addMessage(flat, messageOffset);
                }
                if (-1 != causeOffset) {
                    HandledException.addCause(flat, causeOffset);
                }
                HandledException.addThreads(flat, threadVectorOffset);
                handledExceptionOffsets.add(Integer.valueOf(HandledException.endHandledException(flat)));
            }
        }
        int handledExceptionVector = -1;
        if (!handledExceptionOffsets.isEmpty()) {
            handledExceptionVector = AgentData.createHandledExceptionsVector(flat, toArray(handledExceptionOffsets));
        }
        ApplicationInfo.startApplicationInfo(flat);
        ApplicationInfo.addPlatform(flat, 0);
        int applicationInfoOffset = ApplicationInfo.endApplicationInfo(flat);
        AgentData.startAgentData(flat);
        if (stringSessionAttributesVector != -1) {
            AgentData.addStringAttributes(flat, stringSessionAttributesVector);
        }
        if (doubleSessionAttributesVector != -1) {
            AgentData.addDoubleAttributes(flat, doubleSessionAttributesVector);
        }
        if (longSessionAttributesVector != -1) {
            AgentData.addLongAttributes(flat, longSessionAttributesVector);
        }
        if (booleanSessionAttributesVector != -1) {
            AgentData.addBoolAttributes(flat, booleanSessionAttributesVector);
        }
        if (handledExceptionVector != -1) {
            AgentData.addHandledExceptions(flat, handledExceptionVector);
        }
        AgentData.addApplicationInfo(flat, applicationInfoOffset);
        Set<Integer> agentDataOffsets = new HashSet();
        agentDataOffsets.add(Integer.valueOf(AgentData.endAgentData(flat)));
        int agentDataVector = AgentDataBundle.createAgentDataVector(flat, toArray(agentDataOffsets));
        AgentDataBundle.startAgentDataBundle(flat);
        AgentDataBundle.addAgentData(flat, agentDataVector);
        flat.finish(AgentDataBundle.endAgentDataBundle(flat));
        return flat;
    }

    private static int stringIndexMapOffset(Map<String, Integer> stringIndexMap, Object hexKey) {
        Integer offset = Integer.valueOf(-1);
        if (hexKey != null) {
            Integer index = (Integer) stringIndexMap.get(hexKey);
            if (index != null) {
                offset = index;
            }
        }
        return offset.intValue();
    }

    private static int[] toArray(Collection<Integer> c) {
        int[] a = new int[c.size()];
        int index = 0;
        for (Integer intValue : c) {
            int index2 = index + 1;
            a[index] = intValue.intValue();
            index = index2;
        }
        return a;
    }

    public static String toJsonString(AgentDataBundle agentDataBundle, int index) {
        return new GsonBuilder().enableComplexMapKeySerialization().serializeNulls().setPrettyPrinting().create().toJson(attributesMapFromAgentData(agentDataBundle.agentData(index)));
    }

    public static Map<String, Object> attributesMapFromAgentData(AgentData agentData) {
        int si;
        Map<String, Object> map = new HashMap();
        for (si = 0; si < agentData.stringAttributesLength(); si++) {
            StringSessionAttribute a = agentData.stringAttributes(si);
            map.put(a.name(), a.value());
        }
        for (si = 0; si < agentData.longAttributesLength(); si++) {
            LongSessionAttribute a2 = agentData.longAttributes(si);
            map.put(a2.name(), Long.valueOf(a2.value()));
        }
        for (si = 0; si < agentData.doubleAttributesLength(); si++) {
            DoubleSessionAttribute a3 = agentData.doubleAttributes(si);
            map.put(a3.name(), Double.valueOf(a3.value()));
        }
        for (si = 0; si < agentData.boolAttributesLength(); si++) {
            BoolSessionAttribute a4 = agentData.boolAttributes(si);
            map.put(a4.name(), Boolean.valueOf(a4.value()));
        }
        for (si = 0; si < agentData.handledExceptionsLength(); si++) {
            HandledException hex = agentData.handledExceptions(si);
            map.put(HexAttributes.HEX_ATTR_TIMESTAMP_MS, Long.valueOf(hex.timestampMs()));
            map.put(HexAttributes.HEX_ATTR_APP_UUID_HI, Long.valueOf(hex.appUuidHigh()));
            map.put(HexAttributes.HEX_ATTR_APP_UUID_LO, Long.valueOf(hex.appUuidLow()));
            map.put("name", hex.name());
            map.put(HexAttributes.HEX_ATTR_CAUSE, hex.cause());
            map.put(HexAttributes.HEX_ATTR_MESSAGE, hex.message());
            for (int t = 0; t < hex.threadsLength(); t++) {
                Thread currentThread = Thread.currentThread();
                Map<String, Object> threadMap = new LinkedHashMap();
                for (int f = 0; f < hex.threads(t).framesLength(); f++) {
                    Map<String, Object> frameMap = new LinkedHashMap();
                    frameMap.put(HexAttributes.HEX_ATTR_FILENAME, hex.threads(t).frames(f).fileName());
                    frameMap.put(HexAttributes.HEX_ATTR_LINE_NUMBER, Long.valueOf(hex.threads(t).frames(f).lineNumber()));
                    frameMap.put(HexAttributes.HEX_ATTR_CLASS_NAME, hex.threads(t).frames(f).className());
                    frameMap.put(HexAttributes.HEX_ATTR_METHOD_NAME, hex.threads(t).frames(f).methodName());
                    threadMap.put("frame " + f, frameMap);
                }
                threadMap.put(HexAttributes.HEX_ATTR_THREAD_CRASHED, Boolean.valueOf(false));
                threadMap.put(HexAttributes.HEX_ATTR_THREAD_STATE, currentThread.getState().toString());
                threadMap.put(HexAttributes.HEX_ATTR_THREAD_NUMBER, Long.valueOf(currentThread.getId()));
                threadMap.put(HexAttributes.HEX_ATTR_THREAD_ID, currentThread.getName());
                threadMap.put(HexAttributes.HEX_ATTR_THREAD_PRI, Integer.valueOf(currentThread.getPriority()));
                map.put("thread " + t, threadMap);
            }
        }
        return map;
    }
}
