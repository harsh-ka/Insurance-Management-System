package com.example.models;

import javax.validation.constraints.NotBlank;
import java.util.Date;

public class Policies
{
    @NotBlank
    private int PolicyTerm;
    @NotBlank
    private int TotalAmount;

    @NotBlank
    private Date StartDate;
    @NotBlank
    private Date EndDate;

    private String InsuranceId;

    public int getPolicyTerm()
    {
        return PolicyTerm;
    }

    public void setPolicyTerm(int policyTerm)
    {
        PolicyTerm = policyTerm;
    }

    public int getTotalAmount()
    {
        return TotalAmount;
    }

    public void setTotalAmount(int totalAmount)
    {
        TotalAmount = totalAmount;
    }

    public Date getStartDate()
    {
        return StartDate;
    }

    public void setStartDate(Date startDate)
    {
        StartDate = startDate;
    }

    public Date getEndDate()
    {
        return EndDate;
    }

    public void setEndDate(Date endDate)
    {
        EndDate = endDate;
    }

    public String getInsuranceId()
    {
        return InsuranceId;
    }

    public void setInsuranceId(String insuranceId)
    {
        InsuranceId = insuranceId;
    }
}
