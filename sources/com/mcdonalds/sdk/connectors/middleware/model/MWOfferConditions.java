package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;
import com.mcdonalds.sdk.modules.models.Offer.SaleAmountCondition;
import java.util.List;

public class MWOfferConditions {
    @SerializedName("AndConditions")
    public List<MWAndCondition> andConditions;
    @SerializedName("OrConditions")
    public List<MWOrCondition> orConditions;
    @SerializedName("PodConditions")
    public List<String> podConditions;
    @SerializedName("SaleAmountConditions")
    public List<MWSaleAmountCondition> saleAmountConditions;

    public class MWAndCondition {
        @SerializedName("DayOfWeekConditions")
        public List<String> dayOfWeekConditions;
        @SerializedName("HourOfDayConditions")
        public List<MWHourOfDayCondition> hourOfDayConditions;
    }

    public class MWHourOfDayCondition {
        @SerializedName("from")
        public String from;
        @SerializedName("to")
        /* renamed from: to */
        public String f6072to;
    }

    public class MWOrCondition {
        @SerializedName("AndConditions")
        public List<MWAndCondition> andConditions;
    }

    public class MWSaleAmountCondition {
        @SerializedName("minimum")
        public double minimum;

        public SaleAmountCondition toSaleAmountCondition() {
            SaleAmountCondition cond = new SaleAmountCondition();
            cond.setMinimum(this.minimum);
            return cond;
        }
    }
}
