package com.google.zxing.client.result;

import com.newrelic.agent.android.util.SafeJsonPrimitive;

public final class VINParsedResult extends ParsedResult {
    private final String countryCode;
    private final int modelYear;
    private final char plantCode;
    private final String sequentialNumber;
    private final String vehicleAttributes;
    private final String vehicleDescriptorSection;
    private final String vehicleIdentifierSection;
    private final String vin;
    private final String worldManufacturerID;

    public VINParsedResult(String vin, String worldManufacturerID, String vehicleDescriptorSection, String vehicleIdentifierSection, String countryCode, String vehicleAttributes, int modelYear, char plantCode, String sequentialNumber) {
        super(ParsedResultType.VIN);
        this.vin = vin;
        this.worldManufacturerID = worldManufacturerID;
        this.vehicleDescriptorSection = vehicleDescriptorSection;
        this.vehicleIdentifierSection = vehicleIdentifierSection;
        this.countryCode = countryCode;
        this.vehicleAttributes = vehicleAttributes;
        this.modelYear = modelYear;
        this.plantCode = plantCode;
        this.sequentialNumber = sequentialNumber;
    }

    public String getVIN() {
        return this.vin;
    }

    public String getWorldManufacturerID() {
        return this.worldManufacturerID;
    }

    public String getVehicleDescriptorSection() {
        return this.vehicleDescriptorSection;
    }

    public String getVehicleIdentifierSection() {
        return this.vehicleIdentifierSection;
    }

    public String getCountryCode() {
        return this.countryCode;
    }

    public String getVehicleAttributes() {
        return this.vehicleAttributes;
    }

    public int getModelYear() {
        return this.modelYear;
    }

    public char getPlantCode() {
        return this.plantCode;
    }

    public String getSequentialNumber() {
        return this.sequentialNumber;
    }

    public String getDisplayResult() {
        StringBuilder result = new StringBuilder(50);
        result.append(this.worldManufacturerID).append(SafeJsonPrimitive.NULL_CHAR);
        result.append(this.vehicleDescriptorSection).append(SafeJsonPrimitive.NULL_CHAR);
        result.append(this.vehicleIdentifierSection).append(10);
        if (this.countryCode != null) {
            result.append(this.countryCode).append(SafeJsonPrimitive.NULL_CHAR);
        }
        result.append(this.modelYear).append(SafeJsonPrimitive.NULL_CHAR);
        result.append(this.plantCode).append(SafeJsonPrimitive.NULL_CHAR);
        result.append(this.sequentialNumber).append(10);
        return result.toString();
    }
}
