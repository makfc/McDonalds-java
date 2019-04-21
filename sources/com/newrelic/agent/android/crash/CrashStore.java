package com.newrelic.agent.android.crash;

import com.newrelic.agent.android.payload.PayloadStore;
import java.util.List;

public interface CrashStore extends PayloadStore<Crash> {
    void clear();

    int count();

    void delete(Crash crash);

    List<Crash> fetchAll();

    boolean store(Crash crash);
}
