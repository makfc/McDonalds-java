package com.newrelic.agent.android.harvest.type;

import com.newrelic.agent.android.harvest.type.Harvestable.Type;
import com.newrelic.com.google.gson.JsonPrimitive;

public abstract class HarvestableValue extends BaseHarvestable {
    public abstract JsonPrimitive asJsonPrimitive();

    public HarvestableValue() {
        super(Type.VALUE);
    }
}
