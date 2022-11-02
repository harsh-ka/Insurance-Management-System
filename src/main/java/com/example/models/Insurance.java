package com.example.models;

import javax.validation.constraints.NotBlank;

public class Insurance
{
    @NotBlank
    private String InsuranceId;
    @NotBlank
    private String InsuranceType;
    @NotBlank
    private String InsuranceName;




    public String getInsuranceId() {
        return InsuranceId;
    }

    public void setInsuranceId(String insuranceId) {
        InsuranceId = insuranceId;
    }

    public String getInsuranceType() {
        return InsuranceType;
    }

    public void setInsuranceType(String insuranceType) {
        InsuranceType = insuranceType;
    }

    public String getInsuranceName() {
        return InsuranceName;
    }

    public void setInsuranceName(String insuranceName) {
        InsuranceName = insuranceName;
    }
    @Override
    public String toString() {
        return "Insurance{" +
                "InsuranceId='" + InsuranceId + '\'' +
                ", InsuranceType='" + InsuranceType + '\'' +
                ", InsuranceName='" + InsuranceName + '\'' +
                '}';
    }

    @Override
    public String toString() {
        return "Insurance{" +
                "insuranceId='" + insuranceId + '\'' +
                ", insuranceType='" + insuranceType + '\'' +
                ", insuranceName='" + insuranceName + '\'' +
                '}';
    }
}
