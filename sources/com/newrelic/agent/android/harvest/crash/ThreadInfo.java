package com.newrelic.agent.android.harvest.crash;

import com.newrelic.agent.android.agentdata.HexAttributes;
import com.newrelic.agent.android.harvest.type.HarvestableObject;
import com.newrelic.agent.android.util.SafeJsonPrimitive;
import com.newrelic.com.google.gson.JsonArray;
import com.newrelic.com.google.gson.JsonElement;
import com.newrelic.com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

public class ThreadInfo extends HarvestableObject {
    private boolean crashed;
    private StackTraceElement[] stackTrace;
    private String state;
    private long threadId;
    private String threadName;
    private int threadPriority;

    private ThreadInfo() {
    }

    public ThreadInfo(Throwable throwable) {
        this.crashed = true;
        this.threadId = Thread.currentThread().getId();
        this.threadName = Thread.currentThread().getName();
        this.threadPriority = Thread.currentThread().getPriority();
        this.stackTrace = throwable.getStackTrace();
        this.state = Thread.currentThread().getState().toString();
    }

    public ThreadInfo(Thread thread, StackTraceElement[] stackTrace) {
        this.crashed = false;
        this.threadId = thread.getId();
        this.threadName = thread.getName();
        this.threadPriority = thread.getPriority();
        this.stackTrace = stackTrace;
        this.state = thread.getState().toString();
    }

    public long getThreadId() {
        return this.threadId;
    }

    public static List<ThreadInfo> extractThreads(Throwable throwable) {
        List<ThreadInfo> threads = new ArrayList();
        ThreadInfo crashedThread = new ThreadInfo(throwable);
        long crashedThreadId = crashedThread.getThreadId();
        threads.add(crashedThread);
        for (Entry<Thread, StackTraceElement[]> threadEntry : Thread.getAllStackTraces().entrySet()) {
            Thread thread = (Thread) threadEntry.getKey();
            StackTraceElement[] threadStackTrace = (StackTraceElement[]) threadEntry.getValue();
            if (thread.getId() != crashedThreadId) {
                threads.add(new ThreadInfo(thread, threadStackTrace));
            }
        }
        return threads;
    }

    public JsonObject asJsonObject() {
        JsonObject data = new JsonObject();
        data.add(HexAttributes.HEX_ATTR_THREAD_CRASHED, SafeJsonPrimitive.factory(Boolean.valueOf(this.crashed)));
        data.add(HexAttributes.HEX_ATTR_THREAD_STATE, SafeJsonPrimitive.factory(this.state));
        data.add(HexAttributes.HEX_ATTR_THREAD_NUMBER, SafeJsonPrimitive.factory(Long.valueOf(this.threadId)));
        data.add(HexAttributes.HEX_ATTR_THREAD_ID, SafeJsonPrimitive.factory(this.threadName));
        data.add(HexAttributes.HEX_ATTR_THREAD_PRI, SafeJsonPrimitive.factory(Integer.valueOf(this.threadPriority)));
        data.add("stack", getStackAsJson());
        return data;
    }

    public static ThreadInfo newFromJson(JsonObject jsonObject) {
        ThreadInfo info = new ThreadInfo();
        info.crashed = jsonObject.get(HexAttributes.HEX_ATTR_THREAD_CRASHED).getAsBoolean();
        info.state = jsonObject.get(HexAttributes.HEX_ATTR_THREAD_STATE).getAsString();
        info.threadId = jsonObject.get(HexAttributes.HEX_ATTR_THREAD_NUMBER).getAsLong();
        info.threadName = jsonObject.get(HexAttributes.HEX_ATTR_THREAD_ID).getAsString();
        info.threadPriority = jsonObject.get(HexAttributes.HEX_ATTR_THREAD_PRI).getAsInt();
        info.stackTrace = stackTraceFromJson(jsonObject.get("stack").getAsJsonArray());
        return info;
    }

    public static StackTraceElement[] stackTraceFromJson(JsonArray jsonArray) {
        StackTraceElement[] stack = new StackTraceElement[jsonArray.size()];
        int i = 0;
        Iterator it = jsonArray.iterator();
        while (it.hasNext()) {
            JsonElement jsonElement = (JsonElement) it.next();
            String fileName = "unknown";
            if (jsonElement.getAsJsonObject().get(HexAttributes.HEX_ATTR_FILENAME) != null) {
                fileName = jsonElement.getAsJsonObject().get(HexAttributes.HEX_ATTR_FILENAME).getAsString();
            }
            int i2 = i + 1;
            stack[i] = new StackTraceElement(jsonElement.getAsJsonObject().get(HexAttributes.HEX_ATTR_CLASS_NAME).getAsString(), jsonElement.getAsJsonObject().get(HexAttributes.HEX_ATTR_METHOD_NAME).getAsString(), fileName, jsonElement.getAsJsonObject().get(HexAttributes.HEX_ATTR_LINE_NUMBER).getAsInt());
            i = i2;
        }
        return stack;
    }

    public static List<ThreadInfo> newListFromJson(JsonArray jsonArray) {
        List<ThreadInfo> list = new ArrayList();
        Iterator it = jsonArray.iterator();
        while (it.hasNext()) {
            list.add(newFromJson(((JsonElement) it.next()).getAsJsonObject()));
        }
        return list;
    }

    private JsonArray getStackAsJson() {
        JsonArray data = new JsonArray();
        for (StackTraceElement element : this.stackTrace) {
            JsonObject elementJson = new JsonObject();
            if (element.getFileName() != null) {
                elementJson.add(HexAttributes.HEX_ATTR_FILENAME, SafeJsonPrimitive.factory(element.getFileName()));
            }
            elementJson.add(HexAttributes.HEX_ATTR_CLASS_NAME, SafeJsonPrimitive.factory(element.getClassName()));
            elementJson.add(HexAttributes.HEX_ATTR_METHOD_NAME, SafeJsonPrimitive.factory(element.getMethodName()));
            elementJson.add(HexAttributes.HEX_ATTR_LINE_NUMBER, SafeJsonPrimitive.factory(Integer.valueOf(element.getLineNumber())));
            data.add(elementJson);
        }
        return data;
    }
}
