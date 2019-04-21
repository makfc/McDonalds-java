package com.google.zxing.oned.rss.expanded.decoders;

import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.common.BitArray;
import com.mcdonalds.sdk.connectors.middleware.model.DCSPreference;
import com.mcdonalds.sdk.connectors.middleware.model.DCSSubscription;

public abstract class AbstractExpandedDecoder {
    private final GeneralAppIdDecoder generalDecoder;
    private final BitArray information;

    public abstract String parseInformation() throws NotFoundException, FormatException;

    AbstractExpandedDecoder(BitArray information) {
        this.information = information;
        this.generalDecoder = new GeneralAppIdDecoder(information);
    }

    /* Access modifiers changed, original: protected|final */
    public final BitArray getInformation() {
        return this.information;
    }

    /* Access modifiers changed, original: protected|final */
    public final GeneralAppIdDecoder getGeneralDecoder() {
        return this.generalDecoder;
    }

    public static AbstractExpandedDecoder createDecoder(BitArray information) {
        if (information.get(1)) {
            return new AI01AndOtherAIs(information);
        }
        if (!information.get(2)) {
            return new AnyAIDecoder(information);
        }
        switch (GeneralAppIdDecoder.extractNumericValueFromBitArray(information, 1, 4)) {
            case 4:
                return new AI013103decoder(information);
            case 5:
                return new AI01320xDecoder(information);
            default:
                switch (GeneralAppIdDecoder.extractNumericValueFromBitArray(information, 1, 5)) {
                    case 12:
                        return new AI01392xDecoder(information);
                    case 13:
                        return new AI01393xDecoder(information);
                    default:
                        switch (GeneralAppIdDecoder.extractNumericValueFromBitArray(information, 1, 7)) {
                            case 56:
                                return new AI013x0x1xDecoder(information, "310", DCSSubscription.ID_MOBILE_NOTIFICATION);
                            case 57:
                                return new AI013x0x1xDecoder(information, "320", DCSSubscription.ID_MOBILE_NOTIFICATION);
                            case 58:
                                return new AI013x0x1xDecoder(information, "310", DCSPreference.ID_FOOD_PREFERENCE_SANDWICH);
                            case 59:
                                return new AI013x0x1xDecoder(information, "320", DCSPreference.ID_FOOD_PREFERENCE_SANDWICH);
                            case 60:
                                return new AI013x0x1xDecoder(information, "310", DCSPreference.ID_FOOD_PREFERENCE_DRINK);
                            case 61:
                                return new AI013x0x1xDecoder(information, "320", DCSPreference.ID_FOOD_PREFERENCE_DRINK);
                            case 62:
                                return new AI013x0x1xDecoder(information, "310", DCSPreference.ID_FOOD_PREFERENCE_HAPPYMEAL);
                            case 63:
                                return new AI013x0x1xDecoder(information, "320", DCSPreference.ID_FOOD_PREFERENCE_HAPPYMEAL);
                            default:
                                throw new IllegalStateException("unknown decoder: " + information);
                        }
                }
        }
    }
}
