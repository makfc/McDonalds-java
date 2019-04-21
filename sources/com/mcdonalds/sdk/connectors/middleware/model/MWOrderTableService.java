package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;
import com.mcdonalds.sdk.modules.models.OrderTableService;

public class MWOrderTableService {
    @SerializedName("TableServiceId")
    public Integer tableServiceId;
    @SerializedName("TableServiceZoneId")
    public Integer tableServiceZoneId;
    @SerializedName("TableTagId")
    public Integer tableTagId;

    public static MWOrderTableService fromOrderTableService(OrderTableService orderTableService) {
        MWOrderTableService ret = new MWOrderTableService();
        if (orderTableService.getTableServiceId() != 0) {
            ret.tableServiceId = Integer.valueOf(orderTableService.getTableServiceId());
        } else {
            ret.tableServiceId = null;
        }
        if (orderTableService.getTablseServiceZoneId() != 0) {
            ret.tableServiceZoneId = Integer.valueOf(orderTableService.getTablseServiceZoneId());
        } else {
            ret.tableServiceZoneId = null;
        }
        if (orderTableService.getTableTagId() != 0) {
            ret.tableTagId = Integer.valueOf(orderTableService.getTableTagId());
        } else {
            ret.tableTagId = null;
        }
        return ret;
    }
}
