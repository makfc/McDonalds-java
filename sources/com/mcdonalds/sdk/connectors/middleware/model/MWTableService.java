package com.mcdonalds.sdk.connectors.middleware.model;

import android.content.Context;
import com.google.gson.annotations.SerializedName;
import com.mcdonalds.sdk.modules.models.TableService;
import com.mcdonalds.sdk.modules.models.ZoneDefinitions;
import com.mcdonalds.sdk.utils.ListUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class MWTableService implements Serializable {
    @SerializedName("EnablePOSTableService")
    public boolean enablePOSTableService;
    @SerializedName("EnableTableServiceEatin")
    public String enableTableServiceEatin;
    @SerializedName("EnableTableServiceTakeout")
    public String enableTableServiceTakeout;
    @SerializedName("MinimumPurchaseAmount")
    public double minimumPurchaseAmount;
    @SerializedName("TableServiceEnableMap")
    public boolean tableServiceEnableMap;
    @SerializedName("TableServiceLocatorEnabled")
    public boolean tableServiceLocatorEnabled;
    @SerializedName("TableServiceLocatorMaxNumberValue")
    public int tableServiceLocatorMaxNumberValue;
    @SerializedName("ZoneDefinitions")
    public ArrayList<MWZoneDefinitions> zoneDefinitions;

    public TableService toTableService(Context context) {
        return toTableService(context, null);
    }

    public TableService toTableService(Context context, TableService tableService) {
        if (tableService == null) {
            tableService = new TableService();
        }
        tableService.setEnablePOSTableService(this.enablePOSTableService);
        tableService.setTableServiceEatin(this.enableTableServiceEatin);
        tableService.setTableServiceTakeout(this.enableTableServiceTakeout);
        tableService.setMinimumPurchaseAmount(this.minimumPurchaseAmount);
        tableService.setTableServiceEnableMap(this.tableServiceEnableMap);
        tableService.setTableServiceLocatorEnabled(this.tableServiceLocatorEnabled);
        tableService.setTableServiceLocatorMaxNumberValue(this.tableServiceLocatorMaxNumberValue);
        if (!ListUtils.isEmpty(this.zoneDefinitions)) {
            ArrayList<ZoneDefinitions> zoneDefinitionsList = new ArrayList();
            Iterator it = this.zoneDefinitions.iterator();
            while (it.hasNext()) {
                zoneDefinitionsList.add(((MWZoneDefinitions) it.next()).toZoneDefinitions(context));
            }
            tableService.setZoneDefinitionsList(zoneDefinitionsList);
        }
        return tableService;
    }
}
