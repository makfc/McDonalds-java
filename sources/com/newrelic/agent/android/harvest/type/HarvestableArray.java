package com.newrelic.agent.android.harvest.type;

import com.newrelic.agent.android.harvest.type.Harvestable.Type;
import com.newrelic.com.google.gson.JsonArray;

public abstract class HarvestableArray extends BaseHarvestable {
    public abstract JsonArray asJsonArray();

    public HarvestableArray() {
        super(Type.ARRAY);
    }
}
