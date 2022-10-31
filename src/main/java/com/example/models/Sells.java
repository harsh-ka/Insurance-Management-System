package com.example.models;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Date;

public class Sells
{
    private int agentId;

    private int clientNo;

    private int PolicyTerm;

    private String InsuranceId;

    @NotBlank
    private Date buyDate;
    @NotBlank
    private int amount;
    public int getAgentId() {
        return agentId;
    }

    public void setAgentId(int agentId) {
        this.agentId = agentId;
    }

    public int getClientNo() {
        return clientNo;
    }

    public void setClientNo(int clientNo) {
        this.clientNo = clientNo;
    }

    public int getPolicyTerm() {
        return PolicyTerm;
    }

    public void setPolicyTerm(int policyTerm) {
        PolicyTerm = policyTerm;
    }

    public String getInsuranceId() {
        return InsuranceId;
    }

    public void setInsuranceId(String insuranceId) {
        InsuranceId = insuranceId;
    }

    public Date getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(Date buyDate) {
        this.buyDate = buyDate;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
