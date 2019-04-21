package com.newrelic.agent.android.harvest;

import com.newrelic.agent.android.harvest.type.HarvestableArray;
import com.newrelic.com.google.gson.JsonArray;
import java.util.ArrayList;
import java.util.Collection;

public class Events extends HarvestableArray {
    private final Collection<Event> events = new ArrayList();

    public JsonArray asJsonArray() {
        JsonArray array = new JsonArray();
        for (Event event : this.events) {
            array.add(event.asJson());
        }
        return array;
    }

    public void addEvent(Event event) {
        this.events.add(event);
    }
}
