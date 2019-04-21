package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;

public class DCSFeedbackProfile extends DCSProfile {
    @SerializedName("comment")
    public DCSComment comment = new DCSComment();

    public class DCSComment {
        @SerializedName("feedback")
        public DCSFeedback feedback = new DCSFeedback();

        public class DCSFeedback {
            @SerializedName("feedbackRating")
            public String feedbackRating;
            @SerializedName("feedBackType")
            public String feedbackType;
            @SerializedName("userComment")
            public String userComment;
        }
    }
}
