package com.newrelic.agent.android.harvest;

import com.newrelic.agent.android.harvest.type.HarvestableArray;
import com.newrelic.com.google.gson.JsonArray;
import com.newrelic.com.google.gson.JsonElement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ActivityHistory extends HarvestableArray {
    private final List<ActivitySighting> activityHistory;

    public ActivityHistory(List<ActivitySighting> activityHistory) {
        this.activityHistory = activityHistory;
    }

    public int size() {
        return this.activityHistory.size();
    }

    public JsonArray asJsonArray() {
        JsonArray data = new JsonArray();
        for (ActivitySighting sighting : this.activityHistory) {
            data.add(sighting.asJsonArray());
        }
        return data;
    }

    public JsonArray asJsonArrayWithoutDuration() {
        JsonArray data = new JsonArray();
        for (ActivitySighting sighting : this.activityHistory) {
            data.add(sighting.asJsonArrayWithoutDuration());
        }
        return data;
    }

    public static ActivityHistory newFromJson(JsonArray jsonArray) {
        List<ActivitySighting> sightings = new ArrayList();
        Iterator it = jsonArray.iterator();
        while (it.hasNext()) {
            sightings.add(ActivitySighting.newFromJson(((JsonElement) it.next()).getAsJsonArray()));
        }
        return new ActivityHistory(sightings);
    }
}
