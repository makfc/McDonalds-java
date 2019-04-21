package com.baidu.android.pushservice;

import com.facebook.internal.AnalyticsEvents;

public final class PushConstants {
    /* renamed from: a */
    public static String m5756a(int i) {
        String str = AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN;
        switch (i) {
            case 0:
                return "Success";
            case 10001:
                return "Network Problem";
            case 10002:
                return "Service not available";
            case 10003:
                return "Service not available temporary";
            case 30600:
                return "Internal Server Error";
            case 30601:
                return "Method Not Allowed";
            case 30602:
                return "Request Params Not Valid";
            case 30603:
                return "Authentication Failed";
            case 30604:
                return "Quota Use Up Payment Required";
            case 30605:
                return "Data Required Not Found";
            case 30606:
                return "Request Time Expires Timeout";
            case 30607:
                return "Channel Token Timeout";
            case 30608:
                return "Bind Relation Not Found";
            case 30609:
                return "Bind Number Too Many";
            case 30610:
                return "Duplicate Operation";
            case 30611:
                return "Group Not Found";
            case 30612:
                return "Application Forbidden, Need Whitelist Authorization";
            case 30613:
                return "App Need Inied First In Push-console";
            case 30614:
                return "Number Of Tag For User Too Many";
            case 30615:
                return "Number Of Tag For App Too Many";
            case 30616:
                return "Application Do Not Have Unicast Capability";
            case 30617:
                return "Application Do Not Have Multicast Capability";
            case 30618:
                return "Application Is Not Approved, Can Not Use The Push Service";
            case 30699:
                return "Requests Are Too Frequent To Be Temporarily Rejected";
            case 40002:
                return "Get lightapp info fail";
            case 110001:
                return "User blacked this app";
            default:
                return AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN;
        }
    }
}
