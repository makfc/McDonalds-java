package org.acra.collector;

public class ThreadCollector {
    public static String collect(Thread t) {
        StringBuilder stringBuilder = new StringBuilder();
        if (t != null) {
            stringBuilder.append("id=").append(t.getId()).append("\n");
            stringBuilder.append("name=").append(t.getName()).append("\n");
            stringBuilder.append("priority=").append(t.getPriority()).append("\n");
            if (t.getThreadGroup() != null) {
                stringBuilder.append("groupName=").append(t.getThreadGroup().getName()).append("\n");
            }
        } else {
            stringBuilder.append("No broken thread, this might be a silent exception.");
        }
        return stringBuilder.toString();
    }
}
